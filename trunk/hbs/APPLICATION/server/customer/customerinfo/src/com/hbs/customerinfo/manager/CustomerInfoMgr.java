/**
 * system ：hbs
 * desc:  客户信息管理类
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.common.manager.baseinfo.BankInfoMgr;
import com.hbs.common.manager.baseinfo.ContactMgr;
import com.hbs.common.manager.baseinfo.PrePaidMgr;
import com.hbs.common.manager.waittask.WaitTaskMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customer.common.constants.StateConstants;
import com.hbs.customerinfo.constants.CustInfoConstants;
import com.hbs.domain.common.dao.baseinfo.OperLogDao;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.pojo.baseinfo.OperLog;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;

import com.hbs.domain.customer.customerinfo.dao.CustomerInfoDao;
//import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
/**
 * @author yangzj
 *
 */
public class CustomerInfoMgr {
	
	private static final String CUSTOMERINFODAO = "customerInfoDao";
	
	/**
	 * 保存客户信息的临时数据,数据状态为临时状态,
	 * @param custInfo
	 * @return  0---成功   1---失败
	 * @throws Exception
	 */
	public int saveTempCustomerInfo(CustomerInfo custInfo) throws Exception{
		custInfo.setState(new Integer(StateConstants.STATE_1).toString());
		return insertCustomerInfo(custInfo);
	}
	/**
	 * 提交数据审批,数据的状态为临时状态,或者为领导审批拒绝的状态,才可以提交审批
	 * 数据状态修改的同时，需要发待办通知
	 * @param custInfo
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int commitCustomerInfo(CustomerInfo custInfo,String staffId,String staffName) throws Exception{
		int ret =0;
		//获取提交数据打状态
		int iState = Integer.parseInt(custInfo.getState());
		if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3){
			custInfo.setState(new Integer(StateConstants.STATE_2).toString());
				ret = innerUpdateCustomerInfo(custInfo,staffId,staffName,null);	
				if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
					WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
					Map<String , String> hmParam = new HashMap<String,String>();
					hmParam.put("$staffName", custInfo.getStaffName());
					hmParam.put("$businessKey", custInfo.getCommCode());
					waitTaskInfo.setHmParam(hmParam);
					waitTaskInfo.setBusinessKey(custInfo.getCommCode());
					WaitTaskMgr.deleteWaitTask(custInfo.getCommCode());
					WaitTaskMgr.createWaitTask("CUSTOMER001", waitTaskInfo);
				}
		}else{
			ret =2;//表示数据提交的状态不正确
		}
		
		return ret;
	}
	/**
	 * 审批同意客户资料
	 * @param custInfo
	 * @param auditName  审批人
	 * @param auditDesc  审批意见
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int auditAgreeCustomerInfo(CustomerInfo custInfo , String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(custInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			custInfo.setState(new Integer(StateConstants.STATE_0).toString());
			ret = innerUpdateCustomerInfo(custInfo,auditId,auditName,auditDesc);
			if(ret ==0){//发提醒待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", custInfo.getStaffName());
				hmParam.put("$businessKey", custInfo.getCommCode());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setStaffId(custInfo.getStaffId());
				waitTaskInfo.setBusinessKey(custInfo.getCommCode());
				WaitTaskMgr.deleteWaitTask(custInfo.getCommCode());
				WaitTaskMgr.createWaitTask("CUSTOMER002", waitTaskInfo);
			}
		}else{
			ret =2;//表示数据提交的状态不正确
		}
		
		return 0;
	}
	
	/**
	 * 审批不同意客户资料
	 * @param custInfo
	 * @param auditName  审批人
	 * @param auditDesc  审批意见
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int auditDisAgreeCustomerInfo(CustomerInfo custInfo ,String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(custInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			custInfo.setState(new Integer(StateConstants.STATE_3).toString());
			ret = innerUpdateCustomerInfo(custInfo,auditId,auditName,auditDesc);
			if(ret ==0){//发提醒待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", custInfo.getStaffName());
				hmParam.put("$businessKey", custInfo.getCommCode());
				waitTaskInfo.setHmParam(hmParam);
				waitTaskInfo.setStaffId(custInfo.getStaffId());
				
				waitTaskInfo.setBusinessKey(custInfo.getCommCode());
				WaitTaskMgr.deleteWaitTask(custInfo.getCommCode());
				WaitTaskMgr.createWaitTask("CUSTOMER003", waitTaskInfo);
			}
		}else{
			ret =2;//表示数据提交的状态不正确
		}
		
		return 0;
	}
	/**
	 * 修改客户信息，修改前的状态可能不同，需要区别对待
	 * 修改前状态为1 ，对临时数据做修改
	 * 修改前状态为0 ，对正式数据做修改，直接提交领导审批
	 * 修改前状态为3 ，对审批不通过的数据修改，直接提交领导审批
		//其他状态不存在修改操作
	 * @param custInfo
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int updateCustomerInfo(CustomerInfo custInfo, String staffId,String staffName) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(custInfo.getState());
		//状态为1 ，对临时数据做修改
		//状态为0 ，对正式数据做修改，直接提交领导审批
		//状态为3 ，对审批不通过的数据修改，直接提交领导审批
		//其他状态不存在修改操作
		switch(iState) {
		case 1:
			ret = innerUpdateCustomerInfo(custInfo,staffId,staffName,null);
			break;
		case 3:
			custInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = innerUpdateCustomerInfo(custInfo,staffId,staffName,null);
			if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", custInfo.getStaffName());
				hmParam.put("$businessKey", custInfo.getCommCode());
				waitTaskInfo.setHmParam(hmParam);				
				waitTaskInfo.setBusinessKey(custInfo.getCommCode());
				WaitTaskMgr.deleteWaitTask(custInfo.getCommCode());
				WaitTaskMgr.createWaitTask("CUSTOMER001", waitTaskInfo);
			}
			break;
		case 0:
			custInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = insertCustomerInfo(custInfo);
			if(ret == 0){//发待办通知,先取消可能的待办，再添加新的待办
				WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
				Map<String , String> hmParam = new HashMap<String,String>();
				hmParam.put("$staffName", custInfo.getStaffName());
				hmParam.put("$businessKey", custInfo.getCommCode());
				waitTaskInfo.setHmParam(hmParam);				
				waitTaskInfo.setBusinessKey(custInfo.getCommCode());
				WaitTaskMgr.deleteWaitTask(custInfo.getCommCode());
				WaitTaskMgr.createWaitTask("CUSTOMER001", waitTaskInfo);
			}
			break;
			
		default:
			ret =2;
		}
		
		return ret;
	}
	/**
	 * 锁定客户资料，财务执行，只能对正式数据做锁定操作
	 * @param custInfo
	 * @param staffId  操作人ID
	 * @param staffName 操作人姓名
	 * @param lockDesc  锁定说明
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int lockCustomerInfo(CustomerInfo custInfo , String staffId,String staffName,String lockDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(custInfo.getState());
		switch(iState){
		case 0:
			custInfo.setState(new Integer(StateConstants.STATE_5).toString());
			ret = innerUpdateCustomerInfo(custInfo,staffId,staffName,lockDesc);
			break;
		default:
			ret =2;
		}
		return ret;
		
	}
	/**
	 * 解锁客户资料，财务执行，只能对锁定数据做解锁操作
	 * @param custInfo
	 * @param staffId  操作人ID
	 * @param staffName 操作人姓名
	 * @param lockDesc  解锁说明
	 * @return 0---成功   1--无此状态  2---状态不正确
	 * @throws Exception
	 */
	public int unlockCustomerInfo(CustomerInfo custInfo , String staffId,String staffName,String lockDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(custInfo.getState());
		switch(iState){
		case 5:
			custInfo.setState(new Integer(StateConstants.STATE_6).toString());
			ret = innerUpdateCustomerInfo(custInfo,staffId,staffName,lockDesc);
			break;
		default:
			ret =2;
		}
		return ret;
	}
	/**
	 * 废除客户数据，只有在审批不通过的状态，才能有废除操作
	 * @param custInfo
	 * @param staffId   操作人ID
	 * @param staffName  操作人
	 * @param delDesc   废除原因
	 * @return
	 * @throws Exception
	 */
	public int deleteCustomerInfo(CustomerInfo custInfo,String staffId,String staffName,String delDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(custInfo.getState());
		switch(iState){
		case 3:
			custInfo.setState(new Integer(StateConstants.STATE_4).toString());
			ret = innerUpdateCustomerInfo(custInfo,staffId,staffName,delDesc);
			break;
		default:
			ret =2;
		}
		return ret;
	}
    /**
     * 
     * @param custInfo 查询的字段为：baseSeqId 或 commCode,State组合 
     * @param isAll   是否包含所有信息，联系人信息，银行信息，账期信息
     * @return CustomerInfo
     * @throws Exception
     */
	public CustomerInfo getCustomerInfo(CustomerInfo custInfo,boolean isAll)throws Exception{
		CustomerInfo retInfo = null;
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		if(null != custInfo.getBaseSeqId()){//以主键查询
			retInfo = customerInfoDao.findCustomerInfoByID(custInfo.getBaseSeqId().toString());
		}else{//以commCode,State组合
			retInfo = customerInfoDao.findCustomerInfoByBase(custInfo);
		}
		if((null != retInfo) && isAll){//需要查询附属的信息
			/** 银行信息  */
			retInfo.setListBankInfo(getBankInfoList(retInfo.getCommCode(),retInfo.getState()));
			/** 联系人信息 */
			retInfo.setListContactInfo(getContactInfoList(retInfo.getCommCode(),retInfo.getState()));
			
			/**  获取账期或预付费信息    */
			retInfo.setAccountPreiod(getAccountPreiod(retInfo.getCommCode(),retInfo.getState()));
			retInfo.setPrePaidInfo(getPrePaidInfo(retInfo.getCommCode(),retInfo.getState()));
		}
		return retInfo;
	}
	/**
	 * 获取银行信息
	 * @param commCode 客户编码
	 * @param state   状态
	 * @return
	 * @throws Exception
	 */
	private List<BankInfo> getBankInfoList(String commCode, String state) throws Exception{
		BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTBANKINFOMGR);
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
		ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTCONTACTMGR);
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
		CustAccountPreiodMgr custAccountPreiodMgr =(CustAccountPreiodMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTACCOUNTPREIODMGR);
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
		PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTPREPAIDMGR);
		PrePaidInfo  pInfo = new PrePaidInfo();
		pInfo.setCommCode(commCode);
		pInfo.setState(state);
		return prePaidMgr.getPrePaidInfo(pInfo);
	}
	/**
	 * 获取满足客户信息，支持模糊查询及分页查询
	 * @param cInfo
	 * @return
	 * @throws Exception
	 */
	public List<CustomerInfo> getCustomerInfoList(CustomerInfo cInfo)throws Exception{
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		return customerInfoDao.listCustomerInfo(cInfo);
	}
	/**
	 * 获取满足条件的客户数量
	 * @param cInfo
	 * @return
	 * @throws Exception
	 */
	public Integer getCustomerInfoCount(CustomerInfo cInfo)throws Exception{
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		return customerInfoDao.listCustomerInfoCount(cInfo);
	}
	/**
	 * 插入客户基本信息，基本信息包括客户信息，银行信息
	 * 联系人信息，账期信息或预付费信息
	 * 插入的客户信息状态为临时数据，
	 * 正式提交后，状态为待经理审批，
	 * 经理审批通过后的状态为正式数据
	 * @param customerInfo
	 * @return 0--成功  1--存在重复数据
	 * @throws Exception
	 */
	private int insertCustomerInfo(CustomerInfo customerInfo) throws Exception{
		int ret =0;		
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		CustomerInfo tempInfo = customerInfoDao.findCustomerInfoByBase(customerInfo);
		Integer baseSeqId =0; //主信息插入返回的baseSeqId
		if(null == tempInfo){
			baseSeqId = customerInfoDao.insertCustomerInfo(customerInfo);
			/** 联系人信息 */
			List<ContactInfo> contactInfoList = customerInfo.getListContactInfo();
			if(null != contactInfoList && contactInfoList.size() >0){//存在联系人信息
				for(ContactInfo cInfo : contactInfoList){
					cInfo.setBaseSeqId(baseSeqId.toString());
					cInfo.setState(customerInfo.getState());
				}
				ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTCONTACTMGR);
				contactInfoMgr.insertContactInfoList(contactInfoList);
			}
			/**  银行信息 */
			List<BankInfo> bankInfoList = customerInfo.getListBankInfo();
			if(null != bankInfoList && bankInfoList.size() >0){//存在银行信息
				for(BankInfo bInfo : bankInfoList){
					bInfo.setBaseSeqId(baseSeqId.toString());
					bInfo.setState(customerInfo.getState());
				}
				BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTBANKINFOMGR);
				bankInfoMgr.insertBankInfoList(bankInfoList);
			}
			
			/**  账期信息  */
			AccountPreiod aPreiod = customerInfo.getAccountPreiod();
			if(null != aPreiod){
				aPreiod.setBaseSeqId(baseSeqId.toString());
				aPreiod.setState(customerInfo.getState());
				AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTACCOUNTPREIODMGR);
				accountPreiodMgr.insertAccountPreiod(aPreiod);
			}
			
			/**  预付费信息			 */
			PrePaidInfo pInfo = customerInfo.getPrePaidInfo();
			if(null != pInfo){
				pInfo.setBaseSeqId(baseSeqId.toString());
				pInfo.setState(customerInfo.getState());
				PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTPREPAIDMGR);
				prePaidMgr.insertPrePaidInfo(pInfo);
			}
//			/** 物料对关系信息*/
//			List<CustPartNoInfo>  custPartNoInfoList = customerInfo.getPartNoInfoList();
//			if(null != custPartNoInfoList && custPartNoInfoList.size() >0){
//				
//				CustPartNoInfoMgr custPartNoInfoMgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTPARTNOINFOMGR);
//				custPartNoInfoMgr.insertCustPartNoInfoList(custPartNoInfoList, null, null);
//			}			
			//记录操作日志
			int iState = Integer.parseInt(customerInfo.getState());
			operLog( customerInfo.getStaffId(), customerInfo.getStaffName(), (iState ==2 ? "修改" : "新增"), baseSeqId.toString(), null);
		}else{
			ret =1;
		}
		
		return ret;
	}
	
	/**
	 * 操作日志
	 * @param staffId
	 * @param staffName
	 * @param logType
	 * @param operKey
	 * @param otherInfo
	 */
	private void operLog(String staffId,String staffName,String logType,String operKey,String otherInfo){
		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTOMEROPERLOGDAO);
		OperLog log = new OperLog();
		log.setStaffId(staffId);
		log.setStaffName(staffName);
		log.setOperTime(new Date());
		log.setOperObject("客户信息");
		log.setOperKey(operKey);
		log.setOperType(logType);
		if(otherInfo != null){
			log.setOperContent(otherInfo);
		}
		logDao.insertOperLog(log);
	}
	/**
	 * 删除正式信息及临时信息
	 * @param customerInfo  
	 * @param isDelCurrent  是否删除本次操作的数据
	 * @throws Exception
	 */
	private void deleteCustomerInfo(CustomerInfo customerInfo,boolean isDelCurrent) throws Exception{
		/** 联系人信息 */
		List<ContactInfo> contactInfoList = customerInfo.getListContactInfo();
		if(null != contactInfoList && contactInfoList.size() >0){//存在联系人信息	
			for(ContactInfo cInfo : contactInfoList){
				cInfo.setState(customerInfo.getState());
				ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTCONTACTMGR);
				contactInfoMgr.deleteContactInfo(cInfo, isDelCurrent);
			}
		}
		/**  银行信息 */
		List<BankInfo> bankInfoList = customerInfo.getListBankInfo();
		if(null != bankInfoList && bankInfoList.size() >0){//存在银行信息
			for(BankInfo cInfo : bankInfoList){
				cInfo.setState(customerInfo.getState());
				BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTBANKINFOMGR);
				bankInfoMgr.deleteBankInfo(cInfo, isDelCurrent);
			}
		}
		
		/**  账期信息  */
		AccountPreiod aPreiod = customerInfo.getAccountPreiod();
		if(null != aPreiod){	
			aPreiod.setState(customerInfo.getState());
			CustAccountPreiodMgr custAccountPreiodMgr =(CustAccountPreiodMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTACCOUNTPREIODMGR);
			custAccountPreiodMgr.deleteAccountPreiod(aPreiod, isDelCurrent);
		}
		
		/**  预付费信息			 */
		PrePaidInfo pInfo = customerInfo.getPrePaidInfo();
		if(null != pInfo){
			pInfo.setState(customerInfo.getState());
			PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTPREPAIDMGR);
			prePaidMgr.deletePrePaidInfo(pInfo, isDelCurrent);
		}
		
		
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		customerInfoDao.deleteCustomerInfoByBase(customerInfo);
		if(isDelCurrent){
			customerInfoDao.deleteCustomerInfoByID(customerInfo.getBaseSeqId().toString());
		}
	}
	/**
	 * 更新客户基本信息，包括更新客户信息，银行信息
	 * 联系人信息，账期信息或预付费信息，更新的信息包括所有信息
	 * @param customerInfo
	 * @throws Exception
	 */
	private int innerUpdateCustomerInfo(CustomerInfo customerInfo,String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(customerInfo.getState());
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		String strLogType = null;
		switch(state){
		case 0:  //成为正式数据，审批通过,先删除老状态为0的数据，再插入新的状态为0的数据,同时删除待审批数据
			deleteCustomerInfo(customerInfo,true);
			int baseSeqId = customerInfoDao.insertCustomerInfo(customerInfo);
			customerInfo.setBaseSeqId(baseSeqId);
			strLogType = "审批数据";
			break;
		case 1://没有提交的数据修改		
			customerInfoDao.updateCustomerInfo(customerInfo);
			strLogType = "修改临时数据";
			break;
		case 2://提交数据,
			customerInfoDao.updateCustomerInfo(customerInfo);
			strLogType = "提交审批数据";
			break;
		case 3://审批不通过数据只修改状态
			customerInfoDao.updateCustomerInfoByState(customerInfo);
			strLogType = "审批不通过数据";
			break;
		case 4://废弃数据只修改状态，同时清除以前可能存在的废除数据			
			deleteCustomerInfo(customerInfo,false);
			customerInfoDao.updateCustomerInfoByState(customerInfo);
			strLogType = "废弃数据";
			break;
		case 5://锁定数据只修改状态
			customerInfoDao.updateCustomerInfoByState(customerInfo);
			strLogType = "锁定数据";
			break;
		case 6 ://解锁数据，只修改状态
			CustomerInfo cInfo = new CustomerInfo();
			cInfo.setBaseSeqId(customerInfo.getBaseSeqId());
			cInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			customerInfoDao.updateCustomerInfoByState(cInfo);
			strLogType = "解锁数据";
		default:
			ret =1;
			
		}
		operLog( staffId, staffName, strLogType, customerInfo.getBaseSeqId().toString(), otherInfo);
		
		/** 联系人信息 */
		List<ContactInfo> contactInfoList = customerInfo.getListContactInfo();
		if(null != contactInfoList && contactInfoList.size() >0){//存在联系人信息	
			for(ContactInfo cInfo : contactInfoList){
				cInfo.setState(customerInfo.getState());
				cInfo.setBaseSeqId((customerInfo.getBaseSeqId()).toString());
			}
			ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTCONTACTMGR);
			contactInfoMgr.updateContactInfoList(contactInfoList, null, null, null);
		}
		/**  银行信息 */
		List<BankInfo> bankInfoList = customerInfo.getListBankInfo();
		if(null != bankInfoList && bankInfoList.size() >0){//存在银行信息
			for(BankInfo cInfo : bankInfoList){
				cInfo.setState(customerInfo.getState());
				cInfo.setBaseSeqId((customerInfo.getBaseSeqId()).toString());
			}
			BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTBANKINFOMGR);
			bankInfoMgr.updateBankInfoList(bankInfoList);
		}
		
		/**  账期信息  */
		AccountPreiod aPreiod = customerInfo.getAccountPreiod();
		if(null != aPreiod){	
			aPreiod.setState(customerInfo.getState());
			aPreiod.setBaseSeqId((customerInfo.getBaseSeqId()).toString());
			CustAccountPreiodMgr custAccountPreiodMgr =(CustAccountPreiodMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTACCOUNTPREIODMGR);
			custAccountPreiodMgr.updateAccountPreiod(aPreiod);
		}
		
		/**  预付费信息			 */
		PrePaidInfo pInfo = customerInfo.getPrePaidInfo();
		if(null != pInfo){
			pInfo.setState(customerInfo.getState());
			pInfo.setBaseSeqId((customerInfo.getBaseSeqId()).toString());
			PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTPREPAIDMGR);
			prePaidMgr.updatePrePaidInfo(pInfo);
		}
		
		return ret;
	}
}
