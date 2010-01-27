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

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.common.manager.baseinfo.BankInfoMgr;
import com.hbs.common.manager.baseinfo.ContactMgr;
import com.hbs.common.manager.baseinfo.PrePaidMgr;

import com.hbs.common.springhelper.BeanLocator;

import com.hbs.customerinfo.manager.CustAccountPreiodMgr;

import com.hbs.vendor.common.constants.StateConstants;

import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;


import com.hbs.domain.vendor.vendorinfo.dao.VendorInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.vendor.common.utils.VendorLogUtils;
import com.hbs.vendor.common.utils.VendorWaitTaskUtils;
import com.hbs.vendorinfo.constants.VendorInfoConstants;

public class VendorInfoMgr {
	private static final String VENDORINFO_DAO ="vendorInfoDao";
	
	/**
	 * 保存供应商信息的临时数据,数据状态为临时状态
	 * @param vInfo
	 * @return 0---成功   1---失败
	 * @throws Exception
	 */
	public int saveTempVendorInfo(VendorInfo vInfo) throws Exception{
		vInfo.setState(new Integer(StateConstants.STATE_1).toString());
		return insertVendorInfo(vInfo);
	}
	
	/**
	 * 保存供应商基本信息
	 * @param vInfo
	 * @return
	 * @throws Exception
	 */
	private int insertVendorInfo(VendorInfo vInfo) throws Exception{
		int ret =0;
		VendorInfoDao  vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		VendorInfo vendorInfo = vInfoDao.findVendorInfoByBase(vInfo);
		Integer baseSeqId =0; //主信息插入返回的baseSeqId
		if(null == vendorInfo){
			baseSeqId = vInfoDao.insertVendorInfo(vInfo);
			/** 联系人信息 */
			List<ContactInfo> contactInfoList = vInfo.getListContactInfo();
			if(null != contactInfoList && contactInfoList.size() >0){//存在联系人信息
				for(ContactInfo cInfo : contactInfoList){
					cInfo.setBaseSeqId(baseSeqId.toString());
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
					bInfo.setState(vInfo.getState());
				}
				BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
				bankInfoMgr.insertBankInfoList(bankInfoList);
			}
			
			/**  账期信息  */
			AccountPreiod aPreiod = vInfo.getAccountPreiod();
			if(null != aPreiod){
				aPreiod.setBaseSeqId(baseSeqId.toString());
				aPreiod.setState(vInfo.getState());
				AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
				accountPreiodMgr.insertAccountPreiod(aPreiod);
			}
			
			/**  预付费信息			 */
			PrePaidInfo pInfo = vInfo.getPrePaidInfo();
			if(null != pInfo){
				pInfo.setBaseSeqId(baseSeqId.toString());
				pInfo.setState(vInfo.getState());
				PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_PREPAIDMGR);
				prePaidMgr.insertPrePaidInfo(pInfo);
			}
			int iState = Integer.parseInt(vInfo.getState());
			VendorLogUtils.operLog(vInfo.getStaffId(), vInfo.getStaffName(), (iState ==2 ? "修改" : "新增"), "供应商信息", vInfo.getLogKey(), null, null);
		}else{
			ret =1;
		}
		
		return ret;
	}
	
	/**
	 * 提交数据审批,数据的状态为临时状态,或者为领导审批拒绝的状态,才可以提交审批
	 * 数据状态修改的同时，需要发待办通知 
	 * @param vInfo
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int commitVendorInfo(VendorInfo vInfo) throws Exception{
		int ret =0;
		//获取需要提交数据的状态
		int iState = Integer.parseInt(vInfo.getState());
		if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3 ){
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
		}else{//数据的状态表示不能提交
			ret =2; 
		}
		return ret;
	}
	
	
	
	
	/**
	 * 删除正式信息及临时信息
	 * @param vInfo
	 * @param isDelCurrent  是否删除本次操作的数据
	 * @throws Exception
	 */
	private void deleteVendorInfo(VendorInfo vInfo,boolean isDelCurrent) throws Exception{
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
			CustAccountPreiodMgr custAccountPreiodMgr =(CustAccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
			custAccountPreiodMgr.deleteAccountPreiod(aPreiod, isDelCurrent);
		}
		
		/**  预付费信息			 */
		PrePaidInfo pInfo = vInfo.getPrePaidInfo();
		if(null != pInfo){
			pInfo.setState(vInfo.getState());
			PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_PREPAIDMGR);
			prePaidMgr.deletePrePaidInfo(pInfo, isDelCurrent);
		}
		
		
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
			deleteVendorInfo(vInfo,true);
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
			deleteVendorInfo(vInfo,false);
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
				cInfo.setBaseSeqId((vInfo.getBaseSeqId()).toString());
			}
			BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
			bankInfoMgr.updateBankInfoList(bankInfoList);
		}
		
		/**  账期信息  */
		AccountPreiod aPreiod = vInfo.getAccountPreiod();
		if(null != aPreiod){	
			aPreiod.setState(vInfo.getState());
			aPreiod.setBaseSeqId((vInfo.getBaseSeqId()).toString());
			AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
			accountPreiodMgr.updateAccountPreiod(aPreiod);
		}
		
		/**  预付费信息			 */
		PrePaidInfo pInfo = vInfo.getPrePaidInfo();
		if(null != pInfo){
			pInfo.setState(vInfo.getState());
			pInfo.setBaseSeqId((vInfo.getBaseSeqId()).toString());
			PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_PREPAIDMGR);
			prePaidMgr.updatePrePaidInfo(pInfo);
		}
		
		return ret;
	}
}
