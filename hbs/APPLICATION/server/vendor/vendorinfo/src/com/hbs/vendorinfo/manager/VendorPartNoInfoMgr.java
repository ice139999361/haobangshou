/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;



import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.ExpireTimeUtil;
import com.hbs.domain.common.pojo.baseinfo.OperLog;


import com.hbs.domain.vendor.vendorinfo.dao.VendorPartNoInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.vendor.common.constants.StateConstants;
import com.hbs.vendor.common.utils.VendorLogUtils;
import com.hbs.vendor.common.utils.VendorWaitTaskUtils;


public class VendorPartNoInfoMgr {

	private static final String VENDOR_PARTNOINFODAO ="vendorPartNoInfoDao";
	private static final Logger logger = Logger.getLogger(VendorPartNoInfoMgr.class);
	/**
	 * 保存供应商临时物料关系信息，状态为1
	 * @param vPartNoInfo
	 * @return 0--成功  1--存在重复数据
	 * @throws Exception
	 */
	public int saveTempVendorPartNoInfo(VendorPartNoInfo vPartNoInfo)throws Exception{
		vPartNoInfo.setState(new Integer(StateConstants.STATE_1).toString());
		return insertVendorPartNoInfo(vPartNoInfo);
	}
	/**
	 * 批量保存供应商临时物料关系信息，状态为1
	 * @param vPartNoInfoList
	 * @return
	 * @throws Exception
	 */
	public int saveTempVendorPartNoInfoList(List<VendorPartNoInfo> vPartNoInfoList)throws Exception{
		int ret =0;
		for(VendorPartNoInfo vPartNoInfo : vPartNoInfoList){
			vPartNoInfo.setState(new Integer(StateConstants.STATE_1).toString());
			insertVendorPartNoInfo( vPartNoInfo);
		}
		return ret;		
	}
	/**
	  * 提交数据审批,数据的状态为临时状态,或者为领导审批拒绝的状态,才可以提交审批
	 * 数据状态修改的同时，需要发待办通知
	 * @param vPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public int commitVendorPartNoInfo(VendorPartNoInfo vPartNoInfo) throws Exception{
		int ret =0;
		logger.debug("提交供应商物料关系信息列表，输入为：" + vPartNoInfo.toString());
		String state = vPartNoInfo.getState();
		if(StringUtils.isEmpty(state)){//不存在状态，新增
			VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
			Integer i = vPartNoInfoDao.listVendorPartNoInfoCheckCount(vPartNoInfo);
			if(i >0){//已经存在相同的客户物料，不允许提交
				throw new Exception("已经存在供应商("+vPartNoInfo.getCommCode() + ")的物料(" +  vPartNoInfo.getCustPartNo()+")信息！" );
			}
		}
		VendorPartNoInfo existInfo = this.getVendorPartNoInfoByBizKey(vPartNoInfo);
		if(existInfo != null){
			//获取提交数据打状态
			int iState = Integer.parseInt(existInfo.getState());
			if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3){
				vPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
				ret = this.innerUpdateVendorPartNoInfo(vPartNoInfo, vPartNoInfo.getStaffId(), vPartNoInfo.getStaffName(), null);
			}else if(iState == StateConstants.STATE_0){
				vPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
				this.insertVendorPartNoInfo(vPartNoInfo);
			}
		}else{
			vPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			this.insertVendorPartNoInfo(vPartNoInfo);
		}
		//待办处理
		
		if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", vPartNoInfo.getStaffName());
			hmParam.put("$commCode", vPartNoInfo.getCommCode());
			hmParam.put("$cpartNo", vPartNoInfo.getCustPartNo());
			waitTaskInfo.setHmParam(hmParam);
			waitTaskInfo.setBusinessKey(vPartNoInfo.getWaitTaskBizKey());
			VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
			VendorWaitTaskUtils.processCreateWaitTask("VENDOR_PARTNO_001", null, waitTaskInfo);
			
		}
		return ret;
	}
	
	
	/**
	 * 审批同意供应商物料资料
	 * @param vPartNoInfo
	 * @param auditId  审批人ID
	 * @param auditName 审批人姓名
	 * @param auditDesc 审批意见
	 * @return  0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int auditAgreeCustPartNoInfo(VendorPartNoInfo vPartNoInfo, String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(vPartNoInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			vPartNoInfo.setState(new Integer(StateConstants.STATE_0).toString());
			ret = innerUpdateVendorPartNoInfo(vPartNoInfo,auditId,auditName,auditDesc);
			if(ret ==0){//发提醒待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", auditName);
				hmParam.put("$commCode", vPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", vPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setStaffId(vPartNoInfo.getStaffId());
				waitTaskInfo.setBusinessKey(vPartNoInfo.getWaitTaskBizKey());
				waitTaskInfo.setExpireTime(ExpireTimeUtil.getExpireTime("VENDOR_PARTNO_REMINDER_DAY"));
				VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR_PARTNO_002", null, waitTaskInfo);
				
				
			}
		}else{
			ret =2;//表示数据提交的状态不正确
		}
		
		return 0;
	}
	
	/**
	 * 审批不同意供应商物料资料
	 * @param vPartNoInfo
	 * @param auditId  审批人ID
	 * @param auditName  审批人姓名
	 * @param auditDesc  审批意见
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int auditDisAgreeCustPartNoInfo(VendorPartNoInfo vPartNoInfo,String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(vPartNoInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			vPartNoInfo.setState(new Integer(StateConstants.STATE_3).toString());
			ret = innerUpdateVendorPartNoInfo(vPartNoInfo,auditId,auditName,auditDesc);
			if(ret ==0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", auditName);
				hmParam.put("$commCode", vPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", vPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setStaffId(vPartNoInfo.getStaffId());
				waitTaskInfo.setBusinessKey(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR_PARTNO_003", null, waitTaskInfo);
				
			}
		}else{
			ret =2;//表示数据提交的状态不正确
		}
		
		return 0;
	}
	
	/**
	 * 修改供应商物料信息，修改前的状态可能不同，需要区别对待
	 * 修改前状态为1 ，对临时数据做修改
	 * 修改前状态为0 ，对正式数据做修改，直接提交领导审批
	 * 修改前状态为3 ，对审批不通过的数据修改，直接提交领导审批
		//其他状态不存在修改操作 
	 * @param vPartNoInfo
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int updateVendorPartNoInfo(VendorPartNoInfo vPartNoInfo) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(vPartNoInfo.getState());
		//状态为1 ，对临时数据做修改
		//状态为0 ，对正式数据做修改，直接提交领导审批
		//状态为3 ，对审批不通过的数据修改，直接提交领导审批
		//其他状态不存在修改操作
		switch(iState) {
		case 1:
			ret = innerUpdateVendorPartNoInfo(vPartNoInfo,vPartNoInfo.getStaffId(),vPartNoInfo.getStaffName(),null);
			break;
		case 3:
			vPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = innerUpdateVendorPartNoInfo(vPartNoInfo,vPartNoInfo.getStaffId(),vPartNoInfo.getStaffName(),null);
			if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", vPartNoInfo.getStaffName());
				hmParam.put("$commCode", vPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", vPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setBusinessKey(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR_PARTNO_001", null, waitTaskInfo);
			}
			break;
		case 0:
			vPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = insertVendorPartNoInfo(vPartNoInfo);
			if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", vPartNoInfo.getStaffName());
				hmParam.put("$commCode", vPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", vPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setBusinessKey(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR_PARTNO_001", null, waitTaskInfo);
			}
			break;
			
		default:
			ret =2;
		}
		
		return ret;
	}
	/**
	 * * 废除供应商物料数据，只有在审批不通过的状态，才能有废除操作
	 * @param vPartNoInfo
	 * @param delDesc   废除原因
	 * @return
	 * @throws Exception
	 */
	public int deleteVendorPartNoInfo(VendorPartNoInfo vPartNoInfo,String delDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(vPartNoInfo.getState());
		switch(iState){
		case 3:
		case 0:
		case 2:
		case 1:
			vPartNoInfo.setState(new Integer(StateConstants.STATE_4).toString());
			ret = innerUpdateVendorPartNoInfo(vPartNoInfo,vPartNoInfo.getStaffId(),vPartNoInfo.getStaffName(),delDesc);
			logger.debug("清除该供应商物料的待办");
			VendorWaitTaskUtils.processDeleteWaitTask(vPartNoInfo.getWaitTaskBizKey());
			break;
		default:
			ret =2;
		}
		return ret;
	}
	
	/**
	 * 获取物料的历史价格变动，变通做法，获取操作历史记录
	 * @param vPartNoInfo （ partNo custPartNo commCode 这三个字段是日志关键字，必传） 
	 * @return
	 * @throws Exception
	 */
	public List<OperLog> getPartNoChange(VendorPartNoInfo vPartNoInfo) throws Exception{
		OperLog logKey = new OperLog();
		logKey.setOperKey(vPartNoInfo.getLogBizKey()+"审批数据");
		
		return VendorLogUtils.getLogList(logKey);
	}
	/**
	 * 新增物料关系，判断是否存在相同业务关键字的数据存在，如果存在，做update操作
	 * @param vPartNoInfo	
	 * @return 0--成功  
	 * @throws Exception
	 */
	private int insertVendorPartNoInfo(VendorPartNoInfo vPartNoInfo) throws Exception{
		int ret =0;
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		//查询物料关系，判断插入的数据是否已经存在
		VendorPartNoInfo tempInfo = new VendorPartNoInfo();
		tempInfo.setCommCode(vPartNoInfo.getCommCode());
		tempInfo.setCustPartNo(vPartNoInfo.getCustPartNo());
		tempInfo.setPartNo(vPartNoInfo.getPartNo());
		tempInfo.setState(vPartNoInfo.getState());
		
		tempInfo = vPartNoInfoDao.findVendorPartNoInfoByBizKey(tempInfo);
		//Integer seqID =0;
		if(null == tempInfo){//不存在
			vPartNoInfoDao.insertVendorPartNoInfo(vPartNoInfo);
			//记录日志			
			VendorLogUtils.operLog(vPartNoInfo.getStaffId(), vPartNoInfo.getStaffName(), "新增", "物料对照关系信息", vPartNoInfo.getLogBizKey(), vPartNoInfo.getLogContent(), null);
		}else{
			ret = -1;
		}
		
		
		return ret;
	}
	
	private String getIsPriceChange(VendorPartNoInfoDao vPartNoInfoDao,VendorPartNoInfo vPartNoInfo)throws Exception{
		String ret = "0";
		if(vPartNoInfo.getIsPriceChange().equals("1")){
			ret ="1";
		}else{
			VendorPartNoInfo existInfo = vPartNoInfoDao.findVendorPartNoInfoByBizKey(vPartNoInfo);
			if(existInfo != null){
				BigDecimal existPrice = existInfo.getPrice();
				BigDecimal vPrice = vPartNoInfo.getPrice();
				if(existPrice.compareTo(vPrice) != 0){
					ret ="1";
				}
			}
		}
		return ret;
	}
	
	/**
	 * 更新信息
	 * @param vPartNoInfo
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	private int innerUpdateVendorPartNoInfo(VendorPartNoInfo vPartNoInfo,String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(vPartNoInfo.getState());
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		String strLogType = null;
		switch (state){
		case 0:  //审批通过,先删除后插入,同时删除待审批数据,待办未做
			String seqId = vPartNoInfo.getSeqId().toString();
			vPartNoInfo.setIsPriceChange(getIsPriceChange(vPartNoInfoDao,vPartNoInfo));
			vPartNoInfoDao.deleteVendorPartNoInfoByBizKey(vPartNoInfo);
			vPartNoInfoDao.insertVendorPartNoInfo(vPartNoInfo);			
			vPartNoInfoDao.deleteVendorPartNoInfoByID(seqId);
			strLogType = "审批数据";
			break;
		case 1://没有提交的数据修改		
			vPartNoInfoDao.updateVendorPartNoInfo(vPartNoInfo);
			strLogType = "修改临时数据";
			break;
		case 2://提交数据修改
			vPartNoInfoDao.updateVendorPartNoInfo(vPartNoInfo);
			strLogType = "提交临时数据";
			break;
		case 3://审批不通过数据只修改状态
			vPartNoInfoDao.updateVendorPartNoInfoByState(vPartNoInfo);
			strLogType = "审批不通过数据";
			break;
		case 4://废弃数据只修改状态			
			//vPartNoInfoDao.updateVendorPartNoInfoByState(vPartNoInfo);
			vPartNoInfoDao.deleteVendorPartNoInfoByID(vPartNoInfo.getSeqId().toString());
			strLogType = "废弃数据";
			break;
		case 5://锁定数据只修改状态
			vPartNoInfoDao.updateVendorPartNoInfoByState(vPartNoInfo);
			strLogType = "锁定数据";
			break;
		default:
			ret =1;
		}
		if(null != staffName){			
			VendorLogUtils.operLog(staffId, staffName, strLogType, "供应商物料信息",(state ==0 ? vPartNoInfo.getLogBizKey()+strLogType : vPartNoInfo.getLogBizKey()), vPartNoInfo.getLogContent(), otherInfo);
		}
		return ret;
	}
	
	/**
	 * 批量更新
	 * @param vPartNoInfoList
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	public int updateVendorPartNoInfoList(List<VendorPartNoInfo> vPartNoInfoList)throws Exception{
		int ret =0;
		for(VendorPartNoInfo vPartNoInfo : vPartNoInfoList){
			updateVendorPartNoInfo( vPartNoInfo);
		}
		
		return ret;
	}
	
	
	/**
	 * 根据数据id获取信息
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public VendorPartNoInfo getVendorPartNoInfoByID(String seqId)throws Exception{
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		return vPartNoInfoDao.findVendorPartNoInfoByID(seqId);
	}
	
	/**
	 * 根据业务主键获取信息
	 * @param vPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public VendorPartNoInfo getVendorPartNoInfoByBizKey(VendorPartNoInfo vPartNoInfo) throws Exception{
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		return vPartNoInfoDao.findVendorPartNoInfoByBizKey(vPartNoInfo);
	}
	
	/**
	 * 获取列表信息
	 * @param vPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public List<VendorPartNoInfo> listVendorPartNoInfo(VendorPartNoInfo vPartNoInfo) throws Exception{
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		return vPartNoInfoDao.listVendorPartNoInfo(vPartNoInfo);
	}
	
	/**
	 * 获取总记录数
	 * @param vPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public Integer listVendorPartNoInfoCount(VendorPartNoInfo vPartNoInfo) throws Exception{
		VendorPartNoInfoDao vPartNoInfoDao = (VendorPartNoInfoDao)BeanLocator.getInstance().getBean(VENDOR_PARTNOINFODAO);
		return vPartNoInfoDao.listVendorPartNoInfoCount(vPartNoInfo);
	}

}
