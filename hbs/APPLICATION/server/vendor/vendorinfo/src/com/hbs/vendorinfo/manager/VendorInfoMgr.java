/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.common.manager.baseinfo.BankInfoMgr;
import com.hbs.common.manager.baseinfo.ContactMgr;
import com.hbs.common.manager.baseinfo.PrePaidMgr;
import com.hbs.common.manager.syssequence.SysSequenceMgr;


import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.ExpireTimeUtil;




import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;




import com.hbs.domain.vendor.vendorinfo.dao.VendorInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.vendor.common.constants.StateConstants;
import com.hbs.vendor.common.utils.VendorLogUtils;
import com.hbs.vendor.common.utils.VendorWaitTaskUtils;
import com.hbs.vendorinfo.constants.VendorInfoConstants;

public class VendorInfoMgr {
	private static final String VENDORINFO_DAO ="vendorInfoDao";
	private static final Logger logger = Logger.getLogger(VendorInfoMgr.class);
	/**
	 * 保存供应商信息的临时数据,数据状态为临时状态
	 * @param vInfo
	 * @return > 0---成功   -1---失败
	 * @throws Exception
	 */
	public int saveTempVendorInfo(VendorInfo vInfo) throws Exception{
		int ret = 0;
		logger.debug("保存供应商信息的临时数据,数据状态为临时状态,输入的参数为:" + vInfo.toString());
		Integer seqId = vInfo.getBaseSeqId();
		if(seqId == null){//新保存
			String commCode = SysSequenceMgr.getCode(SysSequenceMgr.GV_CODE);
			vInfo.setCommCode(commCode);
			vInfo.setState(new Integer(StateConstants.STATE_1).toString());
			ret =  insertVendorInfo(vInfo);
		}else{//修改保存
			ret = updateVendorInfo(vInfo);
		}
		return ret;
	}
	
	/**
	 * 保存供应商基本信息
	 * @param vInfo
	 * @return baseSeqId--成功  
	 * @throws Exception
	 */
	private int insertVendorInfo(VendorInfo vInfo) throws Exception{
		int ret =0;
		VendorInfoDao  vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		VendorInfo vendorInfo = vInfoDao.findVendorInfoByBase(vInfo);
		Integer baseSeqId =0; //主信息插入返回的baseSeqId
		if(null == vendorInfo){
			baseSeqId = vInfoDao.insertVendorInfo(vInfo);
			ret = baseSeqId;
			
			/** 联系人信息 */
			List<ContactInfo> contactInfoList = vInfo.getListContactInfo();
			if(null != contactInfoList && contactInfoList.size() >0){//存在联系人信息
				for(ContactInfo cInfo : contactInfoList){
					cInfo.setBaseSeqId(baseSeqId.toString());
					cInfo.setCommCode(vInfo.getCommCode());
					cInfo.setState(vInfo.getState());
				}
				ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_CONTACTMGR);
				contactInfoMgr.insertContactInfoList(contactInfoList);
			}
			
			/**  银行信息 */
			List<BankInfo> bankInfoList = vInfo.getListBankInfo();
			if(null != bankInfoList && bankInfoList.size() >0){//存在银行信息
				for(BankInfo bInfo : bankInfoList){
					bInfo.setBaseSeqId(baseSeqId.toString());
					bInfo.setCommCode(vInfo.getCommCode());
					bInfo.setState(vInfo.getState());
				}
				BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
				bankInfoMgr.insertBankInfoList(bankInfoList);
			}
			
			/**  处理账期信息和预付费信息  */			
			//先清除，账期或预付费
			AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
			accountPreiodMgr.deleteAccountPreiod(baseSeqId.toString());
			PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_PREPAIDMGR);
			prePaidMgr.deletePrePaidInfo(baseSeqId.toString());
			int iSettleMent = Integer.parseInt(vInfo.getSettlementType());
			
			switch (iSettleMent){
			case 1:
				/**  账期信息  */
				AccountPreiod aPreiod = vInfo.getAccountPreiod();
				if(null != aPreiod){
					aPreiod.setBaseSeqId(baseSeqId.toString());
					aPreiod.setCommCode(vInfo.getCommCode());
					aPreiod.setState(vInfo.getState());
					
					accountPreiodMgr.insertAccountPreiod(aPreiod);
				}
				break;
			case 2:
			case 3:
				/**  预付费信息			 */
				PrePaidInfo pInfo = vInfo.getPrePaidInfo();
				if(null != pInfo){
					pInfo.setBaseSeqId(baseSeqId.toString());
					pInfo.setCommCode(vInfo.getCommCode());
					pInfo.setState(vInfo.getState());				
					prePaidMgr.insertPrePaidInfo(pInfo);
				}
				break;
			}
			int iState = Integer.parseInt(vInfo.getState());
			VendorLogUtils.operLog(vInfo.getStaffId(), vInfo.getStaffName(), (iState ==2 ? "提交审批数据"  : "新增"), "供应商信息", vInfo.getLogKey(), null, null);
		}else{
			throw new Exception("插入的信息已经存在，不能重复操作！");
		}
		
		return ret;
	}
	
	/**
	 * 提交数据审批,
	 * 获取提交数据的baseSeqId ，如果不存在，表示没有保存过，需要先保存
	 * 数据的状态为临时状态,或者为领导审批拒绝的状态,才可以提交审批
	 * 数据状态修改的同时，需要发待办通知 
	 * @param vInfo
	  * @return > 0---成功    -1---存在重复数据  -2---状态不正确
	 * @throws Exception
	 */
	public int commitVendorInfo(VendorInfo vInfo) throws Exception{
		logger.debug("提交数据审批,输入的参数为：" + vInfo.toString());
		int ret =0;
		//获取提交数据的baseSeqId ，如果不存在，表示没有保存过，需要先保存
		Integer ibaseSeqId = vInfo.getBaseSeqId();
		if(null == ibaseSeqId){
			String commCode = SysSequenceMgr.getCode(SysSequenceMgr.GV_CODE);
			vInfo.setCommCode(commCode);
			vInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = this.insertVendorInfo(vInfo);
			WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
			Map<String , String> hmParam = new HashMap<String,String>();
			hmParam.put("$staffName", vInfo.getStaffName());
			hmParam.put("$commCode", vInfo.getCommCode());
			hmParam.put("$shortName", vInfo.getShortName());
			waitTaskInfo.setHmParam(hmParam);
			waitTaskInfo.setBusinessKey(vInfo.getWaitTaskKey());
			VendorWaitTaskUtils.processCreateWaitTask("VENDOR001", null, waitTaskInfo);
		}else{
			//获取需要提交数据的状态
			int iState = Integer.parseInt(vInfo.getState());
			if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3 ){
				vInfo.setState(new Integer(StateConstants.STATE_2).toString());
				ret = this.innerUpdateVendorInfo(vInfo, vInfo.getStaffId(), vInfo.getStaffName(), null);
				if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
					ret = vInfo.getBaseSeqId();
					WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
					Map<String , String> hmParam = new HashMap<String,String>();
					hmParam.put("$staffName", vInfo.getStaffName());
					hmParam.put("$commCode", vInfo.getCommCode());
					hmParam.put("$shortName", vInfo.getShortName());
					waitTaskInfo.setHmParam(hmParam);
					waitTaskInfo.setBusinessKey(vInfo.getWaitTaskKey());
					VendorWaitTaskUtils.processCreateWaitTask("VENDOR001", null, waitTaskInfo);				
				}
			}else{//数据的状态表示不能提交
				logger.debug("提交正式数据的修改审批，提交的数据状态为：" + vInfo.getState());
				ret = updateVendorInfo(vInfo);
				if(ret >= 0){//发待办通知,先取消可能的待办，再添加新的待办
					ret = vInfo.getBaseSeqId();
					WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
					Map<String , String> hmParam = new HashMap<String,String>();
					hmParam.put("$staffName", vInfo.getStaffName());
					hmParam.put("$commCode", vInfo.getCommCode());
					hmParam.put("$shortName", vInfo.getShortName());
					waitTaskInfo.setHmParam(hmParam);
					waitTaskInfo.setBusinessKey(vInfo.getWaitTaskKey());
					VendorWaitTaskUtils.processCreateWaitTask("VENDOR001", null, waitTaskInfo);
				}
			}
		}
		return ret;
	}
	
	/**
	 * 审批同意供应商资料
	 * @param vInfo
	 * @param auditId   审批人ID
	 * @param auditName 审批人姓名
	 * @param auditDesc 审批意见
	 * @return  0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int auditAgreeVendorInfo(VendorInfo vInfo , String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		logger.debug("审批同意供应商资料,输入的参数为：" + vInfo.toString());
		int iState = Integer.parseInt(vInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			vInfo.setState(new Integer(StateConstants.STATE_0).toString());
			ret = this.innerUpdateVendorInfo(vInfo, auditId, auditName, auditDesc);
			if(ret == 0){//发提醒待办通知
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", auditName);
				hmParam.put("$commCode", vInfo.getCommCode());
				hmParam.put("$shortName", vInfo.getShortName());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setStaffId(vInfo.getStaffId());
				waitTaskInfo.setBusinessKey(vInfo.getWaitTaskKey());
				waitTaskInfo.setExpireTime(ExpireTimeUtil.getExpireTime("VENDORINFO_REMINDER_DAY"));
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR002", null, waitTaskInfo);				
			}
		}else{
			ret =2;
		}
		return ret;
	}
	/**
	 * 审批不同意供应商资料
	 * @param vInfo
	 * @param auditId   审批人ID
	 * @param auditName 审批人姓名
	 * @param auditDesc 审批意见
	 * @return  0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int auditDisAgreeVendorInfo(VendorInfo vInfo , String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		logger.debug("审批不同意供应商资料,输入的参数为：" + vInfo.toString());
		int iState = Integer.parseInt(vInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			vInfo.setState(new Integer(StateConstants.STATE_3).toString());
			ret = this.innerUpdateVendorInfo(vInfo, auditId, auditName, auditDesc);
			if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", auditName);
				hmParam.put("$commCode", vInfo.getCommCode());
				hmParam.put("$shortName", vInfo.getShortName());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setStaffId(vInfo.getStaffId());
				waitTaskInfo.setBusinessKey(vInfo.getWaitTaskKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR003", null, waitTaskInfo);				
			}
		}else{
			ret =2;
		}
		return ret;
	}
	
	/**
	 * 修改供应商信息，修改前的状态可能不同，需要区别对待
	 * 修改前状态为1 ，对临时数据做修改
	 * 修改前状态为0 ，对正式数据做修改，直接提交领导审批
	 * 修改前状态为3 ，对审批不通过的数据修改，直接提交领导审批
		//其他状态不存在修改操作
	 * @param vInfo
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int updateVendorInfo(VendorInfo vInfo) throws Exception{
		logger.debug("修改供应商信息,输入的参数为：" + vInfo.toString());
		int ret =0;
		int iState = Integer.parseInt(vInfo.getState());
		//状态为1 ，对临时数据做修改
		//状态为0 ，对正式数据做修改，直接提交领导审批
		//状态为3 ，对审批不通过的数据修改，直接提交领导审批
		//其他状态不存在修改操作
		switch(iState) {
		case 1:
			ret = this.innerUpdateVendorInfo(vInfo, vInfo.getStaffId(), vInfo.getStaffName(), null);
			break;
		case 3:
			vInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = this.innerUpdateVendorInfo(vInfo, vInfo.getStaffId(), vInfo.getStaffName(), null);
			if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", vInfo.getStaffName());
				hmParam.put("$commCode", vInfo.getCommCode());
				hmParam.put("$shortName", vInfo.getShortName());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setBusinessKey(vInfo.getWaitTaskKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR001", null, waitTaskInfo);				
			}
			break;
		case 0:
			vInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = insertVendorInfo(vInfo);
			if(ret > 0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", vInfo.getStaffName());
				hmParam.put("$commCode", vInfo.getCommCode());
				hmParam.put("$shortName", vInfo.getShortName());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setBusinessKey(vInfo.getWaitTaskKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR001", null, waitTaskInfo);				
			}
			break;
			
		default:
			ret =2;
		}
		
		return ret;
	}
	
	
	/**
	 * 执行更新客户的销售人员信息
	 * @param custInfo
	 * @param staffId
	 * @param staffName
	 * @return
	 */
	public int updateVendorSalesInfo(VendorInfo vendorInfo, String staffId,String staffName ,String otherInfo){
		int ret = 0;
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		vInfoDao.updateVendorInfo(vendorInfo);		
		VendorLogUtils.operLog(vendorInfo.getStaffId(), vendorInfo.getStaffName(), "采购变更", "供应商信息", vendorInfo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * 锁定供应商资料，财务执行，只能对正式数据做锁定操作
	 * 供应商应该没有锁定操作
	 * @param vInfo
	 * @param staffId  操作人ID
	 * @param staffName 操作人姓名
	 * @param lockDesc  锁定说明
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	@Deprecated
	public int lockVendorInfo(VendorInfo vInfo , String staffId,String staffName,String lockDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(vInfo.getState());
		switch(iState){
		case 0:
			vInfo.setState(new Integer(StateConstants.STATE_5).toString());
			ret = innerUpdateVendorInfo(vInfo,staffId,staffName,lockDesc);
			break;
		default:
			ret =2;
		}
		return ret;
		
	}
	
	/**
	 * 解锁供应商资料，财务执行，只能对锁定数据做解锁操作
	 * 供应商应该没有解锁操作 
	 * @param vInfo
	 * @param staffId  操作人ID
	 * @param staffName 操作人姓名
	 * @param lockDesc  解锁说明
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	@Deprecated
	public int unlockVendorInfo(VendorInfo vInfo , String staffId,String staffName,String lockDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(vInfo.getState());
		switch(iState){
		case 5:
			vInfo.setState(new Integer(StateConstants.STATE_6).toString());
			ret = innerUpdateVendorInfo(vInfo,staffId,staffName,lockDesc);
			break;
		default:
			ret =2;
		}
		return ret;
	}
	
	
	/**
	 * 废除供应商数据，只有在审批不通过的状态，才能有废除操作
	 * @param vInfo	 
	 * @param delDesc   废除原因
	 * @return
	 * @throws Exception
	 */
	public int deleteVendorInfo(VendorInfo vInfo,String delDesc) throws Exception{
		int ret =0;
		logger.debug("废除供应商数据,输入的参数为：" + vInfo.toString());
		int iState = Integer.parseInt(vInfo.getState());
		switch(iState){
		case 3:
			vInfo.setState(new Integer(StateConstants.STATE_4).toString());
			ret = innerUpdateVendorInfo(vInfo,vInfo.getStaffId(),vInfo.getStaffName(),delDesc);
			break;
		default:
			ret =2;
		}
		return ret;
	}
	
	
	 /**
     * 查询单条供应商信息
     * @param vInfo 查询的字段为：baseSeqId 或 commCode,State组合 
     * @param isAll   是否包含所有信息，联系人信息，银行信息，账期信息
     * @return VendorInfo
     * @throws Exception
     */
	public VendorInfo getVendorInfo(VendorInfo vInfo,boolean isAll)throws Exception{
		VendorInfo retInfo = null;
		logger.debug("查询单条供应商信息,输入的参数为：" + vInfo.toString());
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		if(null != vInfo.getBaseSeqId()){//以主键查询
			retInfo = vInfoDao.findVendorInfoByID(vInfo.getBaseSeqId().toString());
		}else{//以commCode,State组合
			retInfo = vInfoDao.findVendorInfoByBase(vInfo);
		}
		if((null != retInfo) && isAll){//需要查询附属的信息
			/** 银行信息  */
			retInfo.setListBankInfo(getBankInfoList(retInfo.getCommCode(),retInfo.getState()));
			/** 联系人信息 */
			retInfo.setListContactInfo(getContactInfoList(retInfo.getCommCode(),retInfo.getState()));
			
			/**  获取账期或预付费信息   对应单个客户，只能有一种结算信息 */
			int iSettleMent = Integer.parseInt(retInfo.getSettlementType());
			switch(iSettleMent){
			case 1:	
				/**  获取账期或预付费信息    */
				retInfo.setAccountPreiod(getAccountPreiod(retInfo.getCommCode(),retInfo.getState()));
				break;
			case 2:
			case 3:				
				retInfo.setPrePaidInfo(getPrePaidInfo(retInfo.getCommCode(),retInfo.getState()));
				break;
			}
		}
		return retInfo;
	}
	
	
	/**
	 * 获取满足条件的供应商信息，支持模糊查询及分页查询
	 * @param cInfo
	 * @return
	 * @throws Exception
	 */
	public List<VendorInfo> getVendorInfoList(VendorInfo vInfo)throws Exception{
		logger.debug("获取满足条件的供应商信息,输入的参数为：" + vInfo.toString());
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		return vInfoDao.listVendorInfo(vInfo);
	}
	/**
	 * 获取满足条件的供应商数量
	 * @param cInfo
	 * @return
	 * @throws Exception
	 */
	public Integer getVendorInfoCount(VendorInfo vInfo)throws Exception{
		logger.debug("获取满足条件的供应商数量,输入的参数为：" + vInfo.toString());
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		return vInfoDao.listVendorInfoCount(vInfo);
	}
	
	
	/**
	 * 获取银行信息
	 * @param commCode 客户编码
	 * @param state   状态
	 * @return
	 * @throws Exception
	 */
	private List<BankInfo> getBankInfoList(String commCode, String state) throws Exception{
		BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
		BankInfo bInfo = new BankInfo();
		bInfo.setCommCode(commCode);
		bInfo.setState(state);
		return bankInfoMgr.listBankInfo(bInfo);
	}
	/**
	 * 获取联系人信息
	 * @param commCode
	 * @param state
	 * @return
	 * @throws Exception
	 */
	private List<ContactInfo> getContactInfoList(String commCode, String state) throws Exception{
		ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_CONTACTMGR);
		ContactInfo cInfo = new ContactInfo();
		cInfo.setCommCode(commCode);
		cInfo.setState(state);
		return contactInfoMgr.listContactInfo(cInfo);
	}
	/**
	 * 获取账期信息
	 * @param commCode
	 * @param state
	 * @return
	 * @throws Exception
	 */
	private AccountPreiod getAccountPreiod(String commCode, String state) throws Exception{
		AccountPreiodMgr custAccountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
		AccountPreiod aInfo = new AccountPreiod();
		aInfo.setCommCode(commCode);
		aInfo.setState(state);
		return custAccountPreiodMgr.getAccountPreiod(aInfo);
	}
	/**
	 * 获取预付费信息
	 * @param commCode
	 * @param state
	 * @return
	 * @throws Exception
	 */
	private PrePaidInfo getPrePaidInfo(String commCode, String state) throws Exception{
		PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_PREPAIDMGR);
		PrePaidInfo  pInfo = new PrePaidInfo();
		pInfo.setCommCode(commCode);
		pInfo.setState(state);
		return prePaidMgr.getPrePaidInfo(pInfo);
	}
	/**
	 * 删除正式信息及临时信息
	 * @param vInfo
	 * @param isDelCurrent  是否删除本次操作的数据
	 * @throws Exception
	 */
	private void cancelVendorInfo(VendorInfo vInfo,boolean isDelCurrent) throws Exception{
		/** 联系人信息 */
		List<ContactInfo> contactInfoList = vInfo.getListContactInfo();
		if(null != contactInfoList && contactInfoList.size() >0){//存在联系人信息	
			for(ContactInfo cInfo : contactInfoList){
				cInfo.setState(vInfo.getState());
				ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_CONTACTMGR);
				contactInfoMgr.deleteContactInfo(cInfo, isDelCurrent);
			}
		}
		/**  银行信息 */
		List<BankInfo> bankInfoList = vInfo.getListBankInfo();
		if(null != bankInfoList && bankInfoList.size() >0){//存在银行信息
			for(BankInfo cInfo : bankInfoList){
				cInfo.setState(vInfo.getState());
				BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
				bankInfoMgr.deleteBankInfo(cInfo, isDelCurrent);
			}
		}
		
		/**  账期信息  */
		AccountPreiod aPreiod = vInfo.getAccountPreiod();
		if(null != aPreiod){	
			aPreiod.setState(vInfo.getState());			
		}else{
			aPreiod = new AccountPreiod();
			aPreiod.setState(vInfo.getState());
			aPreiod.setCommCode(vInfo.getCommCode());
		}
		AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
		accountPreiodMgr.deleteAccountPreiod(aPreiod, isDelCurrent);
		
		/**  预付费信息			 */
		PrePaidInfo pInfo = vInfo.getPrePaidInfo();
		if(null != pInfo){
			pInfo.setState(vInfo.getState());			
		}else{
			pInfo = new PrePaidInfo();
			pInfo.setState(vInfo.getState());
			pInfo.setCommCode(vInfo.getCommCode());
		}
		PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_PREPAIDMGR);
		prePaidMgr.deletePrePaidInfo(pInfo, isDelCurrent);
		
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		vInfoDao.deleteVendorInfoByBase(vInfo);
		
		if(isDelCurrent){
			vInfoDao.deleteVendorInfoByID(vInfo.getBaseSeqId().toString());
		}
	}
	
	/**
	  * 更新客户基本信息，包括更新客户信息，银行信息
	 * 联系人信息，账期信息或预付费信息，更新的信息包括所有信息
	 * @param vInfo
	 * @param staffId  
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	private int innerUpdateVendorInfo(VendorInfo vInfo,String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(vInfo.getState());
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		String strLogType = null;
		switch(state){
		case 0:  //成为正式数据，审批通过,先删除老状态为0的数据，再插入新的状态为0的数据,同时删除待审批数据
			cancelVendorInfo(vInfo,true);
			int baseSeqId = vInfoDao.insertVendorInfo(vInfo);
			vInfo.setBaseSeqId(baseSeqId);
			strLogType = "审批数据";
			break;
		case 1://没有提交的数据修改		
			vInfoDao.updateVendorInfo(vInfo);
			strLogType = "修改临时数据";
			break;
		case 2://提交数据,
			vInfoDao.updateVendorInfo(vInfo);
			strLogType = "提交审批数据";
			break;
		case 3://审批不通过数据只修改状态
			vInfoDao.updateVendorInfoByState(vInfo);
			strLogType = "审批不通过数据";
			break;
		case 4://废弃数据只修改状态，同时清除以前可能存在的废除数据			
			cancelVendorInfo(vInfo,false);
			vInfoDao.updateVendorInfoByState(vInfo);
			strLogType = "废弃数据";
			break;
		case 5://锁定数据只修改状态
			vInfoDao.updateVendorInfoByState(vInfo);
			strLogType = "锁定数据";
			break;
		case 6 ://解锁数据，只修改状态
			VendorInfo veInfo = new VendorInfo();
			veInfo.setBaseSeqId(vInfo.getBaseSeqId());
			veInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			vInfoDao.updateVendorInfoByState(vInfo);
			strLogType = "解锁数据";
		default:
			ret =1;
			
		}		
		VendorLogUtils.operLog(staffId, staffName, strLogType, "供应商信息", vInfo.getLogKey(), null, otherInfo);
		/** 联系人信息 */
		List<ContactInfo> contactInfoList = vInfo.getListContactInfo();
		if(null != contactInfoList && contactInfoList.size() >0){//存在联系人信息	
			for(ContactInfo cInfo : contactInfoList){
				cInfo.setState(vInfo.getState());
				cInfo.setCommCode(vInfo.getCommCode());
				cInfo.setBaseSeqId((vInfo.getBaseSeqId()).toString());
			}
			ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_CONTACTMGR);
			contactInfoMgr.updateContactInfoList(contactInfoList, null, null, null);
		}
		/**  银行信息 */
		List<BankInfo> bankInfoList = vInfo.getListBankInfo();
		if(null != bankInfoList && bankInfoList.size() >0){//存在银行信息
			for(BankInfo cInfo : bankInfoList){
				cInfo.setState(vInfo.getState());
				cInfo.setCommCode(vInfo.getCommCode());
				cInfo.setBaseSeqId((vInfo.getBaseSeqId()).toString());
			}
			BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
			bankInfoMgr.updateBankInfoList(bankInfoList);
		}
		/**  处理账期信息和预付费信息  */
		AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
		accountPreiodMgr.deleteAccountPreiod(vInfo.getBaseSeqId().toString());
		PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_PREPAIDMGR);
		prePaidMgr.deletePrePaidInfo(vInfo.getBaseSeqId().toString());
		
		//获取结算信息类型
		int iSettlement = Integer.parseInt(vInfo.getSettlementType());
		switch(iSettlement){
		case 1:
			/**  账期信息  */
			AccountPreiod aPreiod = vInfo.getAccountPreiod();
			if(null != aPreiod){	
				aPreiod.setState(vInfo.getState());
				aPreiod.setCommCode(vInfo.getCommCode());
				aPreiod.setBaseSeqId((vInfo.getBaseSeqId()).toString());
				
				accountPreiodMgr.insertAccountPreiod(aPreiod);
			}
			break;
		case 2:
		case 3:
		
			/**  预付费信息			 */
			PrePaidInfo pInfo = vInfo.getPrePaidInfo();
			if(null != pInfo){
				pInfo.setState(vInfo.getState());
				pInfo.setBaseSeqId((vInfo.getBaseSeqId()).toString());
				pInfo.setCommCode(vInfo.getCommCode());
				prePaidMgr.insertPrePaidInfo(pInfo);
			}
			break;
		}
		
		return ret;
	}
}
