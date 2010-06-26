/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hbs.common.manager.waittask.WaitTaskMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.ExpireTimeUtil;
import com.hbs.customer.common.constants.StateConstants;
import com.hbs.customer.common.utils.CustLogUtils;
import com.hbs.domain.common.pojo.baseinfo.OperLog;
import com.hbs.domain.customer.customerinfo.dao.CustPartNoInfoDao;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;

import com.hbs.domain.waittask.pojo.WaitTaskInfo;


public class CustPartNoInfoMgr {

	private static final String CUSTOMERPARTNOINFODAO ="customerPartNoInfoDao";
	private static final Logger logger = Logger.getLogger(CustPartNoInfoMgr.class);
	/**
	 * 保存客户临时物料关系信息，状态为1
	 * @param custPartNoInfo
	 * @return 0--成功  1--存在重复数据
	 * @throws Exception
	 */
	public int saveTempCustPartNoInfo(CustPartNoInfo custPartNoInfo)throws Exception{
		logger.debug("保存客户临时物料关系信息，传入的参数为：" + custPartNoInfo.toString());
		custPartNoInfo.setState(new Integer(StateConstants.STATE_1).toString());
		return insertCustPartNoInfo(custPartNoInfo);
	}
	/**
	 * 批量保存客户临时物料关系信息，状态为1
	 * @param custPartNoInfoList
	 * @return
	 * @throws Exception
	 */
	public int saveTempCustPartNoInfoList(List<CustPartNoInfo> custPartNoInfoList)throws Exception{
		int ret =0;
		logger.debug("保存客户临时物料关系信息列表，列表数量为：" + custPartNoInfoList.size());
		for(CustPartNoInfo custPartNoInfo : custPartNoInfoList){
			custPartNoInfo.setState(new Integer(StateConstants.STATE_1).toString());
			logger.debug("保存客户临时物料关系信息，传入的参数为：" + custPartNoInfo.toString());
			insertCustPartNoInfo( custPartNoInfo);
		}
		return ret;		
	}
	/**
	  * 提交数据审批,数据的状态为临时状态,或者为领导审批拒绝的状态,才可以提交审批
	 * 数据状态修改的同时，需要发待办通知
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public int commitCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws Exception{
		int ret =0;
		logger.debug("提交客户物料关系信息列表，输入为：" + custPartNoInfo.toString());
		CustPartNoInfo existInfo = this.getCustPartNoInfoByBizKey(custPartNoInfo);
		if(existInfo != null){//存在数据
			//获取提交数据打状态
			int iState = Integer.parseInt(existInfo.getState());
			
			if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3){
				custPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
				ret = this.innerUpdateCustPartNoInfo(custPartNoInfo, custPartNoInfo.getStaffId(), custPartNoInfo.getStaffName(), null);
			}else if(iState == StateConstants.STATE_0){
				custPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
				ret = this.insertCustPartNoInfo(custPartNoInfo);
			}
		}else{//不存在数据
			custPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = this.insertCustPartNoInfo(custPartNoInfo);
		}
		//待办处理
		
		if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", custPartNoInfo.getStaffName());
			hmParam.put("$commCode", custPartNoInfo.getCommCode());
			hmParam.put("$cpartNo", custPartNoInfo.getCustPartNo());
			waitTaskInfo.setHmParam(hmParam);
			waitTaskInfo.setBusinessKey(custPartNoInfo.getWaitTaskBizKey());
			WaitTaskMgr.deleteWaitTask(custPartNoInfo.getWaitTaskBizKey());
			WaitTaskMgr.createWaitTask("CUST_PARTNO_001", waitTaskInfo);
		}
		return ret;
	}
	
	/**
	 * 批量提交客户物料关系
	 * @param custPartNoInfoList
	 * @return
	 * @throws Exception
	 */
	public int commitCustPartNoInfoList(List<CustPartNoInfo> custPartNoInfoList) throws Exception{
		int ret =0;
		logger.debug("提交客户临时物料关系信息列表，列表数量为：" + custPartNoInfoList.size());
		for(CustPartNoInfo custPartNoInfo : custPartNoInfoList){
			
			logger.debug("提交客户临时物料关系信息，传入的参数为：" + custPartNoInfo.toString());
			commitCustPartNoInfo( custPartNoInfo);
		}
		return ret;		
	}
	/**
	 * 审批同意客户物料资料
	 * @param custPartNoInfo
	 * @param auditId  审批人ID
	 * @param auditName 审批人姓名
	 * @param auditDesc 审批意见
	 * @return  0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int auditAgreeCustPartNoInfo(CustPartNoInfo custPartNoInfo, String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		logger.debug("审批同意客户临时物料关系信息列表，输入为：" + custPartNoInfo.toString());
		int iState = Integer.parseInt(custPartNoInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			custPartNoInfo.setState(new Integer(StateConstants.STATE_0).toString());
			ret = innerUpdateCustPartNoInfo(custPartNoInfo,auditId,auditName,auditDesc);
			if(ret ==0){//发提醒待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", auditName);
				hmParam.put("$commCode", custPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", custPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setStaffId(custPartNoInfo.getStaffId());
				waitTaskInfo.setExpireTime(ExpireTimeUtil.getExpireTime("CUST_PARTNO_REMINDER_DAY"));
				waitTaskInfo.setBusinessKey(custPartNoInfo.getWaitTaskBizKey());
				WaitTaskMgr.deleteWaitTask(custPartNoInfo.getWaitTaskBizKey());
				WaitTaskMgr.createWaitTask("CUST_PARTNO_002", waitTaskInfo);
			}
		}else{
			logger.debug("审批同意客户临时物料关系信息列表，状态不正确!");
			ret =2;//表示数据提交的状态不正确
		}
		
		return 0;
	}
	
	/**
	 * 审批不同意客户物料资料
	 * @param custPartNoInfo
	 * @param auditId  审批人ID
	 * @param auditName  审批人姓名
	 * @param auditDesc  审批意见
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int auditDisAgreeCustPartNoInfo(CustPartNoInfo custPartNoInfo ,String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		logger.debug("审批不同意客户临时物料关系信息列表，输入为：" + custPartNoInfo.toString());
		int iState = Integer.parseInt(custPartNoInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			custPartNoInfo.setState(new Integer(StateConstants.STATE_3).toString());
			ret = innerUpdateCustPartNoInfo(custPartNoInfo,auditId,auditName,auditDesc);
			if(ret ==0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", auditName);
				hmParam.put("$commCode", custPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", custPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setStaffId(custPartNoInfo.getStaffId());
				
				waitTaskInfo.setBusinessKey(custPartNoInfo.getWaitTaskBizKey());
				WaitTaskMgr.deleteWaitTask(custPartNoInfo.getWaitTaskBizKey());
				WaitTaskMgr.createWaitTask("CUST_PARTNO_003", waitTaskInfo);
			}
		}else{
			logger.debug("审批同意客户临时物料关系信息列表，状态不正确!");
			ret =2;//表示数据提交的状态不正确
		}
		
		return 0;
	}
	
	/**
	 * 修改客户物料信息，修改前的状态可能不同，需要区别对待
	 * 修改前状态为1 ，对临时数据做修改
	 * 修改前状态为0 ，对正式数据做修改，直接提交领导审批
	 * 修改前状态为3 ，对审批不通过的数据修改，直接提交领导审批
		//其他状态不存在修改操作 
	 * @param custPartNoInfo
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int updateCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws Exception{
		int ret =0;
		logger.debug("修改客户物料关系信息列表，输入为：" + custPartNoInfo.toString());
		int iState = Integer.parseInt(custPartNoInfo.getState());
		//状态为1 ，对临时数据做修改
		//状态为0 ，对正式数据做修改，直接提交领导审批
		//状态为3 ，对审批不通过的数据修改，直接提交领导审批
		//其他状态不存在修改操作
		switch(iState) {
		case 1:
			ret = innerUpdateCustPartNoInfo(custPartNoInfo,custPartNoInfo.getStaffId(),custPartNoInfo.getStaffName(),null);
			break;
		case 3:
			custPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = innerUpdateCustPartNoInfo(custPartNoInfo,custPartNoInfo.getStaffId(),custPartNoInfo.getStaffName(),null);
			if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", custPartNoInfo.getStaffName());
				hmParam.put("$commCode", custPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", custPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setBusinessKey(custPartNoInfo.getWaitTaskBizKey());
				WaitTaskMgr.deleteWaitTask(custPartNoInfo.getWaitTaskBizKey());
				WaitTaskMgr.createWaitTask("CUST_PARTNO_001", waitTaskInfo);
			}
			break;
		case 0:
			custPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = insertCustPartNoInfo(custPartNoInfo);
			if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", custPartNoInfo.getStaffName());
				hmParam.put("$commCode", custPartNoInfo.getCommCode());
				hmParam.put("$cpartNo", custPartNoInfo.getCustPartNo());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setBusinessKey(custPartNoInfo.getWaitTaskBizKey());
				WaitTaskMgr.deleteWaitTask(custPartNoInfo.getWaitTaskBizKey());
				WaitTaskMgr.createWaitTask("CUST_PARTNO_001", waitTaskInfo);
			}
			break;
			
		default:
			ret =2;
		}
		
		return ret;
	}
	/**
	 * * 废除客户数据，只有在审批不通过的状态，才能有废除操作
	 * @param custPartNoInfo
	 * @param delDesc   废除原因
	 * @return
	 * @throws Exception
	 */
	public int deleteCustPartNoInfo(CustPartNoInfo custPartNoInfo,String delDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(custPartNoInfo.getState());
		switch(iState){
		case 3:
			custPartNoInfo.setState(new Integer(StateConstants.STATE_4).toString());
			ret = innerUpdateCustPartNoInfo(custPartNoInfo,custPartNoInfo.getStaffId(),custPartNoInfo.getStaffName(),delDesc);
			break;
		default:
			ret =2;
		}
		return ret;
	}
	/**
	 * 获取物料的历史价格变动，变通做法，获取操作历史记录
	 * @param custPartNoInfo （ partNo custPartNo commCode 这三个字段是日志关键字，必传） 
	 * @return
	 * @throws Exception
	 */
	public List<OperLog> getPartNoChange(CustPartNoInfo custPartNoInfo) throws Exception{
		OperLog logKey = new OperLog();
		logKey.setOperKey(custPartNoInfo.getLogBizKey()+"审批数据");
		return CustLogUtils.getLogList(logKey);
	}
	
	/**
	 * 新增物料关系，判断是否存在相同业务关键字的数据存在，如果存在，则update操作
	 * @param custPartNoInfo	
	 * @return 0--成功  -1 已经存在修改待审批数据
	 * @throws Exception
	 */
	private int insertCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws Exception{
		int ret =0;
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		//查询物料关系，判断插入的数据是否已经存在
		CustPartNoInfo tempInfo = new CustPartNoInfo();
		tempInfo.setCommCode(custPartNoInfo.getCommCode());
		tempInfo.setCustPartNo(custPartNoInfo.getCustPartNo());
		tempInfo.setPartNo(custPartNoInfo.getPartNo());
		tempInfo.setState(custPartNoInfo.getState());
		
		tempInfo = custPartNoInfoDao.findCustPartNoInfoByBizKey(tempInfo);
		//Integer seqID =0;
		if(null == tempInfo){//不存在
			custPartNoInfoDao.insertCustPartNoInfo(custPartNoInfo);
			//记录日志			
			CustLogUtils.operLog(custPartNoInfo.getStaffId(), custPartNoInfo.getStaffName(), (null == tempInfo ?"新增" : "修改"), "物料对照关系信息", custPartNoInfo.getLogBizKey(), custPartNoInfo.getLogContent(), null);
		}else{
			//this.innerUpdateCustPartNoInfo(custPartNoInfo, custPartNoInfo.getStaffId(), custPartNoInfo.getStaffName(), null);
			ret = -1;
		}
		
		
		return ret;
	}
	
	private String getIsPriceChange(CustPartNoInfoDao custPartNoInfoDao,CustPartNoInfo custPartNoInfo) throws Exception{
		String ret = "0";
		if(custPartNoInfo.getIsPriceChange().equals("1")){
			ret ="1";
		}else{
			CustPartNoInfo existInfo = custPartNoInfoDao.findCustPartNoInfoByBizKey(custPartNoInfo);
			if(existInfo != null){
				BigDecimal existPrice = existInfo.getPrice();
				BigDecimal vPrice = custPartNoInfo.getPrice();
				if(existPrice.compareTo(vPrice) != 0){
					ret ="1";
				}
			}
		}
		return ret;
	}
	/**
	 * 更新信息
	 * @param custPartNoInfo
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	private int innerUpdateCustPartNoInfo(CustPartNoInfo custPartNoInfo,String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(custPartNoInfo.getState());
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		String strLogType = null;
		switch (state){
		case 0:  //审批通过,先删除后插入,同时删除待审批数据,待办未做
			String seqId = custPartNoInfo.getSeqId().toString();
			custPartNoInfo.setIsPriceChange(getIsPriceChange(custPartNoInfoDao,custPartNoInfo));
			custPartNoInfoDao.deleteCustPartNoInfoByBizKey(custPartNoInfo);
			custPartNoInfoDao.insertCustPartNoInfo(custPartNoInfo);			
			custPartNoInfoDao.deleteCustPartNoInfoByID(seqId);
			strLogType = "审批数据";
			break;
		case 1://没有提交的数据修改		
			custPartNoInfoDao.updateCustPartNoInfo(custPartNoInfo);
			strLogType = "修改临时数据";
			break;
		case 2://提交数据修改
			custPartNoInfoDao.updateCustPartNoInfo(custPartNoInfo);
			strLogType = "提交临时数据";
			break;
		case 3://审批不通过数据只修改状态
			custPartNoInfoDao.updateCustPartNoInfoByState(custPartNoInfo);
			strLogType = "审批不通过数据";
			break;
		case 4://废弃数据只修改状态			
			custPartNoInfoDao.updateCustPartNoInfoByState(custPartNoInfo);
			strLogType = "废弃数据";
			break;
		case 5://锁定数据只修改状态
			custPartNoInfoDao.updateCustPartNoInfoByState(custPartNoInfo);
			strLogType = "锁定数据";
			break;
		default:
			ret =1;
		}
		if(null != staffName){			
			CustLogUtils.operLog(staffId, staffName, strLogType, "客户物料信息", (state == 0 ? custPartNoInfo.getLogBizKey()+ strLogType: custPartNoInfo.getLogBizKey()), custPartNoInfo.getLogContent(), otherInfo);
		}
		return ret;
	}
	
	/**
	 * 批量更新
	 * @param custPartNoInfoList
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	public int updateCustPartNoInfoList(List<CustPartNoInfo> custPartNoInfoList)throws Exception{
		int ret =0;
		for(CustPartNoInfo custPartNoInfo : custPartNoInfoList){
			updateCustPartNoInfo( custPartNoInfo);
		}
		
		return ret;
	}
	
	
	/**
	 * 根据数据id获取信息
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public CustPartNoInfo getCustPartNoInfoByID(String seqId)throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.findCustPartNoInfoByID(seqId);
	}
	
	/**
	 * 根据业务主键获取信息
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public CustPartNoInfo getCustPartNoInfoByBizKey(CustPartNoInfo custPartNoInfo) throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.findCustPartNoInfoByBizKey(custPartNoInfo);
	}
	
	/**
	 * 获取列表信息
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public List<CustPartNoInfo> listCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.listCustPartNoInfo(custPartNoInfo);
	}
	
	/**
	 * 获取总记录数
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public Integer listCustPartNoInfoCount(CustPartNoInfo custPartNoInfo) throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.listCustPartNoInfoCount(custPartNoInfo);
	}

}
