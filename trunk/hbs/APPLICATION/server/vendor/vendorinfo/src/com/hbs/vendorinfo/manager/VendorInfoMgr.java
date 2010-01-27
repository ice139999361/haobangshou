/**
 * system ��hbs
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
	 * ���湩Ӧ����Ϣ����ʱ����,����״̬Ϊ��ʱ״̬
	 * @param vInfo
	 * @return 0---�ɹ�   1---ʧ��
	 * @throws Exception
	 */
	public int saveTempVendorInfo(VendorInfo vInfo) throws Exception{
		vInfo.setState(new Integer(StateConstants.STATE_1).toString());
		return insertVendorInfo(vInfo);
	}
	
	/**
	 * ���湩Ӧ�̻�����Ϣ
	 * @param vInfo
	 * @return
	 * @throws Exception
	 */
	private int insertVendorInfo(VendorInfo vInfo) throws Exception{
		int ret =0;
		VendorInfoDao  vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		VendorInfo vendorInfo = vInfoDao.findVendorInfoByBase(vInfo);
		Integer baseSeqId =0; //����Ϣ���뷵�ص�baseSeqId
		if(null == vendorInfo){
			baseSeqId = vInfoDao.insertVendorInfo(vInfo);
			/** ��ϵ����Ϣ */
			List<ContactInfo> contactInfoList = vInfo.getListContactInfo();
			if(null != contactInfoList && contactInfoList.size() >0){//������ϵ����Ϣ
				for(ContactInfo cInfo : contactInfoList){
					cInfo.setBaseSeqId(baseSeqId.toString());
					cInfo.setState(vInfo.getState());
				}
				ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_CONTACTMGR);
				contactInfoMgr.insertContactInfoList(contactInfoList);
			}
			
			/**  ������Ϣ */
			List<BankInfo> bankInfoList = vInfo.getListBankInfo();
			if(null != bankInfoList && bankInfoList.size() >0){//����������Ϣ
				for(BankInfo bInfo : bankInfoList){
					bInfo.setBaseSeqId(baseSeqId.toString());
					bInfo.setState(vInfo.getState());
				}
				BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
				bankInfoMgr.insertBankInfoList(bankInfoList);
			}
			
			/**  ������Ϣ  */
			AccountPreiod aPreiod = vInfo.getAccountPreiod();
			if(null != aPreiod){
				aPreiod.setBaseSeqId(baseSeqId.toString());
				aPreiod.setState(vInfo.getState());
				AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
				accountPreiodMgr.insertAccountPreiod(aPreiod);
			}
			
			/**  Ԥ������Ϣ			 */
			PrePaidInfo pInfo = vInfo.getPrePaidInfo();
			if(null != pInfo){
				pInfo.setBaseSeqId(baseSeqId.toString());
				pInfo.setState(vInfo.getState());
				PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_PREPAIDMGR);
				prePaidMgr.insertPrePaidInfo(pInfo);
			}
			int iState = Integer.parseInt(vInfo.getState());
			VendorLogUtils.operLog(vInfo.getStaffId(), vInfo.getStaffName(), (iState ==2 ? "�޸�" : "����"), "��Ӧ����Ϣ", vInfo.getLogKey(), null, null);
		}else{
			ret =1;
		}
		
		return ret;
	}
	
	/**
	 * �ύ��������,���ݵ�״̬Ϊ��ʱ״̬,����Ϊ�쵼�����ܾ���״̬,�ſ����ύ����
	 * ����״̬�޸ĵ�ͬʱ����Ҫ������֪ͨ 
	 * @param vInfo
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int commitVendorInfo(VendorInfo vInfo) throws Exception{
		int ret =0;
		//��ȡ��Ҫ�ύ���ݵ�״̬
		int iState = Integer.parseInt(vInfo.getState());
		if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3 ){
			vInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = this.innerUpdateVendorInfo(vInfo, vInfo.getStaffId(), vInfo.getStaffName(), null);
			if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", vInfo.getStaffName());
				hmParam.put("$commCode", vInfo.getCommCode());
				hmParam.put("$shortName", vInfo.getShortName());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setBusinessKey(vInfo.getWaitTaskKey());
				VendorWaitTaskUtils.processCreateWaitTask("VENDOR001", null, waitTaskInfo);				
			}
		}else{//���ݵ�״̬��ʾ�����ύ
			ret =2; 
		}
		return ret;
	}
	
	
	
	
	/**
	 * ɾ����ʽ��Ϣ����ʱ��Ϣ
	 * @param vInfo
	 * @param isDelCurrent  �Ƿ�ɾ�����β���������
	 * @throws Exception
	 */
	private void deleteVendorInfo(VendorInfo vInfo,boolean isDelCurrent) throws Exception{
		/** ��ϵ����Ϣ */
		List<ContactInfo> contactInfoList = vInfo.getListContactInfo();
		if(null != contactInfoList && contactInfoList.size() >0){//������ϵ����Ϣ	
			for(ContactInfo cInfo : contactInfoList){
				cInfo.setState(vInfo.getState());
				ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_CONTACTMGR);
				contactInfoMgr.deleteContactInfo(cInfo, isDelCurrent);
			}
		}
		/**  ������Ϣ */
		List<BankInfo> bankInfoList = vInfo.getListBankInfo();
		if(null != bankInfoList && bankInfoList.size() >0){//����������Ϣ
			for(BankInfo cInfo : bankInfoList){
				cInfo.setState(vInfo.getState());
				BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
				bankInfoMgr.deleteBankInfo(cInfo, isDelCurrent);
			}
		}
		
		/**  ������Ϣ  */
		AccountPreiod aPreiod = vInfo.getAccountPreiod();
		if(null != aPreiod){	
			aPreiod.setState(vInfo.getState());
			CustAccountPreiodMgr custAccountPreiodMgr =(CustAccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
			custAccountPreiodMgr.deleteAccountPreiod(aPreiod, isDelCurrent);
		}
		
		/**  Ԥ������Ϣ			 */
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
	  * ���¿ͻ�������Ϣ���������¿ͻ���Ϣ��������Ϣ
	 * ��ϵ����Ϣ��������Ϣ��Ԥ������Ϣ�����µ���Ϣ����������Ϣ
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
		case 0:  //��Ϊ��ʽ���ݣ�����ͨ��,��ɾ����״̬Ϊ0�����ݣ��ٲ����µ�״̬Ϊ0������,ͬʱɾ������������
			deleteVendorInfo(vInfo,true);
			int baseSeqId = vInfoDao.insertVendorInfo(vInfo);
			vInfo.setBaseSeqId(baseSeqId);
			strLogType = "��������";
			break;
		case 1://û���ύ�������޸�		
			vInfoDao.updateVendorInfo(vInfo);
			strLogType = "�޸���ʱ����";
			break;
		case 2://�ύ����,
			vInfoDao.updateVendorInfo(vInfo);
			strLogType = "�ύ��������";
			break;
		case 3://������ͨ������ֻ�޸�״̬
			vInfoDao.updateVendorInfoByState(vInfo);
			strLogType = "������ͨ������";
			break;
		case 4://��������ֻ�޸�״̬��ͬʱ�����ǰ���ܴ��ڵķϳ�����			
			deleteVendorInfo(vInfo,false);
			vInfoDao.updateVendorInfoByState(vInfo);
			strLogType = "��������";
			break;
		case 5://��������ֻ�޸�״̬
			vInfoDao.updateVendorInfoByState(vInfo);
			strLogType = "��������";
			break;
		case 6 ://�������ݣ�ֻ�޸�״̬
			VendorInfo veInfo = new VendorInfo();
			veInfo.setBaseSeqId(vInfo.getBaseSeqId());
			veInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			vInfoDao.updateVendorInfoByState(vInfo);
			strLogType = "��������";
		default:
			ret =1;
			
		}		
		VendorLogUtils.operLog(staffId, staffName, strLogType, "��Ӧ����Ϣ", vInfo.getLogKey(), null, otherInfo);
		/** ��ϵ����Ϣ */
		List<ContactInfo> contactInfoList = vInfo.getListContactInfo();
		if(null != contactInfoList && contactInfoList.size() >0){//������ϵ����Ϣ	
			for(ContactInfo cInfo : contactInfoList){
				cInfo.setState(vInfo.getState());
				cInfo.setBaseSeqId((vInfo.getBaseSeqId()).toString());
			}
			ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_CONTACTMGR);
			contactInfoMgr.updateContactInfoList(contactInfoList, null, null, null);
		}
		/**  ������Ϣ */
		List<BankInfo> bankInfoList = vInfo.getListBankInfo();
		if(null != bankInfoList && bankInfoList.size() >0){//����������Ϣ
			for(BankInfo cInfo : bankInfoList){
				cInfo.setState(vInfo.getState());
				cInfo.setBaseSeqId((vInfo.getBaseSeqId()).toString());
			}
			BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
			bankInfoMgr.updateBankInfoList(bankInfoList);
		}
		
		/**  ������Ϣ  */
		AccountPreiod aPreiod = vInfo.getAccountPreiod();
		if(null != aPreiod){	
			aPreiod.setState(vInfo.getState());
			aPreiod.setBaseSeqId((vInfo.getBaseSeqId()).toString());
			AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
			accountPreiodMgr.updateAccountPreiod(aPreiod);
		}
		
		/**  Ԥ������Ϣ			 */
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
