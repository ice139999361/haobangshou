/**
 * system ：hbs
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
	
	
	public int commitVendorInfo(VendorInfo vInfo) throws Exception{
		int ret =0;
		//获取需要提交数据的状态
		int iState = Integer.parseInt(vInfo.getState());
		if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3 ){
			vInfo.setState(new Integer(StateConstants.STATE_2).toString());
			
		}else{//数据的状态表示不能提交
			ret =2; 
		}
		return ret;
	}
	
	
	
	/**
	 * 更新客户基本信息，包括更新客户信息，银行信息
	 * 联系人信息，账期信息或预付费信息，更新的形象包括所有信息
	 * @param customerInfo
	 * @throws Exception
	 */
	private int innerUpdateVendorInfo(VendorInfo vInfo,String otherInfo)throws Exception{
		int ret =0;
		//获取数据状态
		int state = Integer.parseInt(vInfo.getState());
		
		
		return ret;
	}
}
