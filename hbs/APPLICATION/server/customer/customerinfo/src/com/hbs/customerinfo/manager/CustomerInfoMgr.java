/**
 * system ��hbs
 * desc:  �ͻ���Ϣ������
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
	 * ����ͻ���Ϣ����ʱ����,����״̬Ϊ��ʱ״̬,
	 * @param custInfo
	 * @return  0---�ɹ�   1---ʧ��
	 * @throws Exception
	 */
	public int saveTempCustomerInfo(CustomerInfo custInfo) throws Exception{
		custInfo.setState(new Integer(StateConstants.STATE_1).toString());
		return insertCustomerInfo(custInfo);
	}
	/**
	 * �ύ��������,���ݵ�״̬Ϊ��ʱ״̬,����Ϊ�쵼�����ܾ���״̬,�ſ����ύ����
	 * ����״̬�޸ĵ�ͬʱ����Ҫ������֪ͨ
	 * @param custInfo
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int commitCustomerInfo(CustomerInfo custInfo,String staffId,String staffName) throws Exception{
		int ret =0;
		//��ȡ�ύ���ݴ�״̬
		int iState = Integer.parseInt(custInfo.getState());
		if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3){
			custInfo.setState(new Integer(StateConstants.STATE_2).toString());
				ret = innerUpdateCustomerInfo(custInfo,staffId,staffName,null);	
				if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
			ret =2;//��ʾ�����ύ��״̬����ȷ
		}
		
		return ret;
	}
	/**
	 * ����ͬ��ͻ�����
	 * @param custInfo
	 * @param auditName  ������
	 * @param auditDesc  �������
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int auditAgreeCustomerInfo(CustomerInfo custInfo , String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(custInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			custInfo.setState(new Integer(StateConstants.STATE_0).toString());
			ret = innerUpdateCustomerInfo(custInfo,auditId,auditName,auditDesc);
			if(ret ==0){//�����Ѵ���֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
			ret =2;//��ʾ�����ύ��״̬����ȷ
		}
		
		return 0;
	}
	
	/**
	 * ������ͬ��ͻ�����
	 * @param custInfo
	 * @param auditName  ������
	 * @param auditDesc  �������
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int auditDisAgreeCustomerInfo(CustomerInfo custInfo ,String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(custInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			custInfo.setState(new Integer(StateConstants.STATE_3).toString());
			ret = innerUpdateCustomerInfo(custInfo,auditId,auditName,auditDesc);
			if(ret ==0){//�����Ѵ���֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
			ret =2;//��ʾ�����ύ��״̬����ȷ
		}
		
		return 0;
	}
	/**
	 * �޸Ŀͻ���Ϣ���޸�ǰ��״̬���ܲ�ͬ����Ҫ����Դ�
	 * �޸�ǰ״̬Ϊ1 ������ʱ�������޸�
	 * �޸�ǰ״̬Ϊ0 ������ʽ�������޸ģ�ֱ���ύ�쵼����
	 * �޸�ǰ״̬Ϊ3 ����������ͨ���������޸ģ�ֱ���ύ�쵼����
		//����״̬�������޸Ĳ���
	 * @param custInfo
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int updateCustomerInfo(CustomerInfo custInfo, String staffId,String staffName) throws Exception{
		int ret =0;
		int iState = Integer.parseInt(custInfo.getState());
		//״̬Ϊ1 ������ʱ�������޸�
		//״̬Ϊ0 ������ʽ�������޸ģ�ֱ���ύ�쵼����
		//״̬Ϊ3 ����������ͨ���������޸ģ�ֱ���ύ�쵼����
		//����״̬�������޸Ĳ���
		switch(iState) {
		case 1:
			ret = innerUpdateCustomerInfo(custInfo,staffId,staffName,null);
			break;
		case 3:
			custInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = innerUpdateCustomerInfo(custInfo,staffId,staffName,null);
			if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
			if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
	 * �����ͻ����ϣ�����ִ�У�ֻ�ܶ���ʽ��������������
	 * @param custInfo
	 * @param staffId  ������ID
	 * @param staffName ����������
	 * @param lockDesc  ����˵��
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
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
	 * �����ͻ����ϣ�����ִ�У�ֻ�ܶ�������������������
	 * @param custInfo
	 * @param staffId  ������ID
	 * @param staffName ����������
	 * @param lockDesc  ����˵��
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
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
	 * �ϳ��ͻ����ݣ�ֻ����������ͨ����״̬�������зϳ�����
	 * @param custInfo
	 * @param staffId   ������ID
	 * @param staffName  ������
	 * @param delDesc   �ϳ�ԭ��
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
     * @param custInfo ��ѯ���ֶ�Ϊ��baseSeqId �� commCode,State��� 
     * @param isAll   �Ƿ����������Ϣ����ϵ����Ϣ��������Ϣ��������Ϣ
     * @return CustomerInfo
     * @throws Exception
     */
	public CustomerInfo getCustomerInfo(CustomerInfo custInfo,boolean isAll)throws Exception{
		CustomerInfo retInfo = null;
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		if(null != custInfo.getBaseSeqId()){//��������ѯ
			retInfo = customerInfoDao.findCustomerInfoByID(custInfo.getBaseSeqId().toString());
		}else{//��commCode,State���
			retInfo = customerInfoDao.findCustomerInfoByBase(custInfo);
		}
		if((null != retInfo) && isAll){//��Ҫ��ѯ��������Ϣ
			/** ������Ϣ  */
			retInfo.setListBankInfo(getBankInfoList(retInfo.getCommCode(),retInfo.getState()));
			/** ��ϵ����Ϣ */
			retInfo.setListContactInfo(getContactInfoList(retInfo.getCommCode(),retInfo.getState()));
			
			/**  ��ȡ���ڻ�Ԥ������Ϣ    */
			retInfo.setAccountPreiod(getAccountPreiod(retInfo.getCommCode(),retInfo.getState()));
			retInfo.setPrePaidInfo(getPrePaidInfo(retInfo.getCommCode(),retInfo.getState()));
		}
		return retInfo;
	}
	/**
	 * ��ȡ������Ϣ
	 * @param commCode �ͻ�����
	 * @param state   ״̬
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
	 * ��ȡ��ϵ����Ϣ
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
	 * ��ȡ������Ϣ
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
	 * ��ȡԤ������Ϣ
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
	 * ��ȡ����ͻ���Ϣ��֧��ģ����ѯ����ҳ��ѯ
	 * @param cInfo
	 * @return
	 * @throws Exception
	 */
	public List<CustomerInfo> getCustomerInfoList(CustomerInfo cInfo)throws Exception{
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		return customerInfoDao.listCustomerInfo(cInfo);
	}
	/**
	 * ��ȡ���������Ŀͻ�����
	 * @param cInfo
	 * @return
	 * @throws Exception
	 */
	public Integer getCustomerInfoCount(CustomerInfo cInfo)throws Exception{
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		return customerInfoDao.listCustomerInfoCount(cInfo);
	}
	/**
	 * ����ͻ�������Ϣ��������Ϣ�����ͻ���Ϣ��������Ϣ
	 * ��ϵ����Ϣ��������Ϣ��Ԥ������Ϣ
	 * ����Ŀͻ���Ϣ״̬Ϊ��ʱ���ݣ�
	 * ��ʽ�ύ��״̬Ϊ������������
	 * ��������ͨ�����״̬Ϊ��ʽ����
	 * @param customerInfo
	 * @return 0--�ɹ�  1--�����ظ�����
	 * @throws Exception
	 */
	private int insertCustomerInfo(CustomerInfo customerInfo) throws Exception{
		int ret =0;		
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		CustomerInfo tempInfo = customerInfoDao.findCustomerInfoByBase(customerInfo);
		Integer baseSeqId =0; //����Ϣ���뷵�ص�baseSeqId
		if(null == tempInfo){
			baseSeqId = customerInfoDao.insertCustomerInfo(customerInfo);
			/** ��ϵ����Ϣ */
			List<ContactInfo> contactInfoList = customerInfo.getListContactInfo();
			if(null != contactInfoList && contactInfoList.size() >0){//������ϵ����Ϣ
				for(ContactInfo cInfo : contactInfoList){
					cInfo.setBaseSeqId(baseSeqId.toString());
					cInfo.setState(customerInfo.getState());
				}
				ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTCONTACTMGR);
				contactInfoMgr.insertContactInfoList(contactInfoList);
			}
			/**  ������Ϣ */
			List<BankInfo> bankInfoList = customerInfo.getListBankInfo();
			if(null != bankInfoList && bankInfoList.size() >0){//����������Ϣ
				for(BankInfo bInfo : bankInfoList){
					bInfo.setBaseSeqId(baseSeqId.toString());
					bInfo.setState(customerInfo.getState());
				}
				BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTBANKINFOMGR);
				bankInfoMgr.insertBankInfoList(bankInfoList);
			}
			
			/**  ������Ϣ  */
			AccountPreiod aPreiod = customerInfo.getAccountPreiod();
			if(null != aPreiod){
				aPreiod.setBaseSeqId(baseSeqId.toString());
				aPreiod.setState(customerInfo.getState());
				AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTACCOUNTPREIODMGR);
				accountPreiodMgr.insertAccountPreiod(aPreiod);
			}
			
			/**  Ԥ������Ϣ			 */
			PrePaidInfo pInfo = customerInfo.getPrePaidInfo();
			if(null != pInfo){
				pInfo.setBaseSeqId(baseSeqId.toString());
				pInfo.setState(customerInfo.getState());
				PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTPREPAIDMGR);
				prePaidMgr.insertPrePaidInfo(pInfo);
			}
//			/** ���϶Թ�ϵ��Ϣ*/
//			List<CustPartNoInfo>  custPartNoInfoList = customerInfo.getPartNoInfoList();
//			if(null != custPartNoInfoList && custPartNoInfoList.size() >0){
//				
//				CustPartNoInfoMgr custPartNoInfoMgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTPARTNOINFOMGR);
//				custPartNoInfoMgr.insertCustPartNoInfoList(custPartNoInfoList, null, null);
//			}			
			//��¼������־
			int iState = Integer.parseInt(customerInfo.getState());
			operLog( customerInfo.getStaffId(), customerInfo.getStaffName(), (iState ==2 ? "�޸�" : "����"), baseSeqId.toString(), null);
		}else{
			ret =1;
		}
		
		return ret;
	}
	
	/**
	 * ������־
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
		log.setOperObject("�ͻ���Ϣ");
		log.setOperKey(operKey);
		log.setOperType(logType);
		if(otherInfo != null){
			log.setOperContent(otherInfo);
		}
		logDao.insertOperLog(log);
	}
	/**
	 * ɾ����ʽ��Ϣ����ʱ��Ϣ
	 * @param customerInfo  
	 * @param isDelCurrent  �Ƿ�ɾ�����β���������
	 * @throws Exception
	 */
	private void deleteCustomerInfo(CustomerInfo customerInfo,boolean isDelCurrent) throws Exception{
		/** ��ϵ����Ϣ */
		List<ContactInfo> contactInfoList = customerInfo.getListContactInfo();
		if(null != contactInfoList && contactInfoList.size() >0){//������ϵ����Ϣ	
			for(ContactInfo cInfo : contactInfoList){
				cInfo.setState(customerInfo.getState());
				ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTCONTACTMGR);
				contactInfoMgr.deleteContactInfo(cInfo, isDelCurrent);
			}
		}
		/**  ������Ϣ */
		List<BankInfo> bankInfoList = customerInfo.getListBankInfo();
		if(null != bankInfoList && bankInfoList.size() >0){//����������Ϣ
			for(BankInfo cInfo : bankInfoList){
				cInfo.setState(customerInfo.getState());
				BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTBANKINFOMGR);
				bankInfoMgr.deleteBankInfo(cInfo, isDelCurrent);
			}
		}
		
		/**  ������Ϣ  */
		AccountPreiod aPreiod = customerInfo.getAccountPreiod();
		if(null != aPreiod){	
			aPreiod.setState(customerInfo.getState());
			CustAccountPreiodMgr custAccountPreiodMgr =(CustAccountPreiodMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTACCOUNTPREIODMGR);
			custAccountPreiodMgr.deleteAccountPreiod(aPreiod, isDelCurrent);
		}
		
		/**  Ԥ������Ϣ			 */
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
	 * ���¿ͻ�������Ϣ���������¿ͻ���Ϣ��������Ϣ
	 * ��ϵ����Ϣ��������Ϣ��Ԥ������Ϣ�����µ���Ϣ����������Ϣ
	 * @param customerInfo
	 * @throws Exception
	 */
	private int innerUpdateCustomerInfo(CustomerInfo customerInfo,String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(customerInfo.getState());
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		String strLogType = null;
		switch(state){
		case 0:  //��Ϊ��ʽ���ݣ�����ͨ��,��ɾ����״̬Ϊ0�����ݣ��ٲ����µ�״̬Ϊ0������,ͬʱɾ������������
			deleteCustomerInfo(customerInfo,true);
			int baseSeqId = customerInfoDao.insertCustomerInfo(customerInfo);
			customerInfo.setBaseSeqId(baseSeqId);
			strLogType = "��������";
			break;
		case 1://û���ύ�������޸�		
			customerInfoDao.updateCustomerInfo(customerInfo);
			strLogType = "�޸���ʱ����";
			break;
		case 2://�ύ����,
			customerInfoDao.updateCustomerInfo(customerInfo);
			strLogType = "�ύ��������";
			break;
		case 3://������ͨ������ֻ�޸�״̬
			customerInfoDao.updateCustomerInfoByState(customerInfo);
			strLogType = "������ͨ������";
			break;
		case 4://��������ֻ�޸�״̬��ͬʱ�����ǰ���ܴ��ڵķϳ�����			
			deleteCustomerInfo(customerInfo,false);
			customerInfoDao.updateCustomerInfoByState(customerInfo);
			strLogType = "��������";
			break;
		case 5://��������ֻ�޸�״̬
			customerInfoDao.updateCustomerInfoByState(customerInfo);
			strLogType = "��������";
			break;
		case 6 ://�������ݣ�ֻ�޸�״̬
			CustomerInfo cInfo = new CustomerInfo();
			cInfo.setBaseSeqId(customerInfo.getBaseSeqId());
			cInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			customerInfoDao.updateCustomerInfoByState(cInfo);
			strLogType = "��������";
		default:
			ret =1;
			
		}
		operLog( staffId, staffName, strLogType, customerInfo.getBaseSeqId().toString(), otherInfo);
		
		/** ��ϵ����Ϣ */
		List<ContactInfo> contactInfoList = customerInfo.getListContactInfo();
		if(null != contactInfoList && contactInfoList.size() >0){//������ϵ����Ϣ	
			for(ContactInfo cInfo : contactInfoList){
				cInfo.setState(customerInfo.getState());
				cInfo.setBaseSeqId((customerInfo.getBaseSeqId()).toString());
			}
			ContactMgr contactInfoMgr = (ContactMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTCONTACTMGR);
			contactInfoMgr.updateContactInfoList(contactInfoList, null, null, null);
		}
		/**  ������Ϣ */
		List<BankInfo> bankInfoList = customerInfo.getListBankInfo();
		if(null != bankInfoList && bankInfoList.size() >0){//����������Ϣ
			for(BankInfo cInfo : bankInfoList){
				cInfo.setState(customerInfo.getState());
				cInfo.setBaseSeqId((customerInfo.getBaseSeqId()).toString());
			}
			BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTBANKINFOMGR);
			bankInfoMgr.updateBankInfoList(bankInfoList);
		}
		
		/**  ������Ϣ  */
		AccountPreiod aPreiod = customerInfo.getAccountPreiod();
		if(null != aPreiod){	
			aPreiod.setState(customerInfo.getState());
			aPreiod.setBaseSeqId((customerInfo.getBaseSeqId()).toString());
			CustAccountPreiodMgr custAccountPreiodMgr =(CustAccountPreiodMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTACCOUNTPREIODMGR);
			custAccountPreiodMgr.updateAccountPreiod(aPreiod);
		}
		
		/**  Ԥ������Ϣ			 */
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
