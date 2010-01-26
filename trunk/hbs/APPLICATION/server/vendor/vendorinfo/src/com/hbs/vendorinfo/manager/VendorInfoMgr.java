/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;

import java.util.List;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.common.manager.baseinfo.BankInfoMgr;
import com.hbs.common.manager.baseinfo.ContactMgr;
import com.hbs.common.manager.baseinfo.PrePaidMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.constants.CustInfoConstants;
import com.hbs.customerinfo.manager.CustAccountPreiodMgr;
import com.hbs.vendor.common.constants.StateConstants;

import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;
import com.hbs.domain.customer.customerinfo.dao.CustomerInfoDao;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.vendor.vendorinfo.dao.VendorInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendor.common.utils.VendorLogUtils;
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
	
	
	public int commitVendorInfo(VendorInfo vInfo) throws Exception{
		int ret =0;
		//��ȡ��Ҫ�ύ���ݵ�״̬
		int iState = Integer.parseInt(vInfo.getState());
		if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3 ){
			vInfo.setState(new Integer(StateConstants.STATE_2).toString());
			
		}else{//���ݵ�״̬��ʾ�����ύ
			ret =2; 
		}
		return ret;
	}
	
	
	
	/**
	 * ���¿ͻ�������Ϣ���������¿ͻ���Ϣ��������Ϣ
	 * ��ϵ����Ϣ��������Ϣ��Ԥ������Ϣ�����µ��������������Ϣ
	 * @param customerInfo
	 * @throws Exception
	 */
	private int innerUpdateVendorInfo(VendorInfo vInfo,String otherInfo)throws Exception{
		int ret =0;
		//��ȡ����״̬
		int state = Integer.parseInt(vInfo.getState());
		
		
		return ret;
	}
}
