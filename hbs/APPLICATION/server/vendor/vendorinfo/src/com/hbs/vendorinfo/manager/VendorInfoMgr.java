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
	 * ���湩Ӧ����Ϣ����ʱ����,����״̬Ϊ��ʱ״̬
	 * @param vInfo
	 * @return > 0---�ɹ�   -1---ʧ��
	 * @throws Exception
	 */
	public int saveTempVendorInfo(VendorInfo vInfo) throws Exception{
		int ret = 0;
		logger.debug("���湩Ӧ����Ϣ����ʱ����,����״̬Ϊ��ʱ״̬,����Ĳ���Ϊ:" + vInfo.toString());
		Integer seqId = vInfo.getBaseSeqId();
		if(seqId == null){//�±���
			String commCode = SysSequenceMgr.getCode(SysSequenceMgr.GV_CODE);
			vInfo.setCommCode(commCode);
			vInfo.setState(new Integer(StateConstants.STATE_1).toString());
			ret =  insertVendorInfo(vInfo);
		}else{//�޸ı���
			ret = updateVendorInfo(vInfo);
		}
		return ret;
	}
	
	/**
	 * ���湩Ӧ�̻�����Ϣ
	 * @param vInfo
	 * @return baseSeqId--�ɹ�  
	 * @throws Exception
	 */
	private int insertVendorInfo(VendorInfo vInfo) throws Exception{
		int ret =0;
		VendorInfoDao  vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		VendorInfo vendorInfo = vInfoDao.findVendorInfoByBase(vInfo);
		Integer baseSeqId =0; //����Ϣ���뷵�ص�baseSeqId
		if(null == vendorInfo){
			baseSeqId = vInfoDao.insertVendorInfo(vInfo);
			ret = baseSeqId;
			
			/** ��ϵ����Ϣ */
			List<ContactInfo> contactInfoList = vInfo.getListContactInfo();
			if(null != contactInfoList && contactInfoList.size() >0){//������ϵ����Ϣ
				for(ContactInfo cInfo : contactInfoList){
					cInfo.setBaseSeqId(baseSeqId.toString());
					cInfo.setCommCode(vInfo.getCommCode());
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
					bInfo.setCommCode(vInfo.getCommCode());
					bInfo.setState(vInfo.getState());
				}
				BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
				bankInfoMgr.insertBankInfoList(bankInfoList);
			}
			
			/**  ����������Ϣ��Ԥ������Ϣ  */			
			//����������ڻ�Ԥ����
			AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
			accountPreiodMgr.deleteAccountPreiod(baseSeqId.toString());
			PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_PREPAIDMGR);
			prePaidMgr.deletePrePaidInfo(baseSeqId.toString());
			int iSettleMent = Integer.parseInt(vInfo.getSettlementType());
			
			switch (iSettleMent){
			case 1:
				/**  ������Ϣ  */
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
				/**  Ԥ������Ϣ			 */
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
			VendorLogUtils.operLog(vInfo.getStaffId(), vInfo.getStaffName(), (iState ==2 ? "�ύ��������"  : "����"), "��Ӧ����Ϣ", vInfo.getLogKey(), null, null);
		}else{
			throw new Exception("�������Ϣ�Ѿ����ڣ������ظ�������");
		}
		
		return ret;
	}
	
	/**
	 * �ύ��������,
	 * ��ȡ�ύ���ݵ�baseSeqId ����������ڣ���ʾû�б��������Ҫ�ȱ���
	 * ���ݵ�״̬Ϊ��ʱ״̬,����Ϊ�쵼�����ܾ���״̬,�ſ����ύ����
	 * ����״̬�޸ĵ�ͬʱ����Ҫ������֪ͨ 
	 * @param vInfo
	  * @return > 0---�ɹ�    -1---�����ظ�����  -2---״̬����ȷ
	 * @throws Exception
	 */
	public int commitVendorInfo(VendorInfo vInfo) throws Exception{
		logger.debug("�ύ��������,����Ĳ���Ϊ��" + vInfo.toString());
		int ret =0;
		//��ȡ�ύ���ݵ�baseSeqId ����������ڣ���ʾû�б��������Ҫ�ȱ���
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
			//��ȡ��Ҫ�ύ���ݵ�״̬
			int iState = Integer.parseInt(vInfo.getState());
			if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3 ){
				vInfo.setState(new Integer(StateConstants.STATE_2).toString());
				ret = this.innerUpdateVendorInfo(vInfo, vInfo.getStaffId(), vInfo.getStaffName(), null);
				if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
			}else{//���ݵ�״̬��ʾ�����ύ
				logger.debug("�ύ��ʽ���ݵ��޸��������ύ������״̬Ϊ��" + vInfo.getState());
				ret = updateVendorInfo(vInfo);
				if(ret >= 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
	 * ����ͬ�⹩Ӧ������
	 * @param vInfo
	 * @param auditId   ������ID
	 * @param auditName ����������
	 * @param auditDesc �������
	 * @return  0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int auditAgreeVendorInfo(VendorInfo vInfo , String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		logger.debug("����ͬ�⹩Ӧ������,����Ĳ���Ϊ��" + vInfo.toString());
		int iState = Integer.parseInt(vInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			vInfo.setState(new Integer(StateConstants.STATE_0).toString());
			ret = this.innerUpdateVendorInfo(vInfo, auditId, auditName, auditDesc);
			if(ret == 0){//�����Ѵ���֪ͨ
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
	 * ������ͬ�⹩Ӧ������
	 * @param vInfo
	 * @param auditId   ������ID
	 * @param auditName ����������
	 * @param auditDesc �������
	 * @return  0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int auditDisAgreeVendorInfo(VendorInfo vInfo , String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		logger.debug("������ͬ�⹩Ӧ������,����Ĳ���Ϊ��" + vInfo.toString());
		int iState = Integer.parseInt(vInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			vInfo.setState(new Integer(StateConstants.STATE_3).toString());
			ret = this.innerUpdateVendorInfo(vInfo, auditId, auditName, auditDesc);
			if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
	 * �޸Ĺ�Ӧ����Ϣ���޸�ǰ��״̬���ܲ�ͬ����Ҫ����Դ�
	 * �޸�ǰ״̬Ϊ1 ������ʱ�������޸�
	 * �޸�ǰ״̬Ϊ0 ������ʽ�������޸ģ�ֱ���ύ�쵼����
	 * �޸�ǰ״̬Ϊ3 ����������ͨ���������޸ģ�ֱ���ύ�쵼����
		//����״̬�������޸Ĳ���
	 * @param vInfo
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int updateVendorInfo(VendorInfo vInfo) throws Exception{
		logger.debug("�޸Ĺ�Ӧ����Ϣ,����Ĳ���Ϊ��" + vInfo.toString());
		int ret =0;
		int iState = Integer.parseInt(vInfo.getState());
		//״̬Ϊ1 ������ʱ�������޸�
		//״̬Ϊ0 ������ʽ�������޸ģ�ֱ���ύ�쵼����
		//״̬Ϊ3 ����������ͨ���������޸ģ�ֱ���ύ�쵼����
		//����״̬�������޸Ĳ���
		switch(iState) {
		case 1:
			ret = this.innerUpdateVendorInfo(vInfo, vInfo.getStaffId(), vInfo.getStaffName(), null);
			break;
		case 3:
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
			break;
		case 0:
			vInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = insertVendorInfo(vInfo);
			if(ret > 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
	 * ִ�и��¿ͻ���������Ա��Ϣ
	 * @param custInfo
	 * @param staffId
	 * @param staffName
	 * @return
	 */
	public int updateVendorSalesInfo(VendorInfo vendorInfo, String staffId,String staffName ,String otherInfo){
		int ret = 0;
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		vInfoDao.updateVendorInfo(vendorInfo);		
		VendorLogUtils.operLog(vendorInfo.getStaffId(), vendorInfo.getStaffName(), "�ɹ����", "��Ӧ����Ϣ", vendorInfo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * ������Ӧ�����ϣ�����ִ�У�ֻ�ܶ���ʽ��������������
	 * ��Ӧ��Ӧ��û����������
	 * @param vInfo
	 * @param staffId  ������ID
	 * @param staffName ����������
	 * @param lockDesc  ����˵��
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
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
	 * ������Ӧ�����ϣ�����ִ�У�ֻ�ܶ�������������������
	 * ��Ӧ��Ӧ��û�н������� 
	 * @param vInfo
	 * @param staffId  ������ID
	 * @param staffName ����������
	 * @param lockDesc  ����˵��
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
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
	 * �ϳ���Ӧ�����ݣ�ֻ����������ͨ����״̬�������зϳ�����
	 * @param vInfo	 
	 * @param delDesc   �ϳ�ԭ��
	 * @return
	 * @throws Exception
	 */
	public int deleteVendorInfo(VendorInfo vInfo,String delDesc) throws Exception{
		int ret =0;
		logger.debug("�ϳ���Ӧ������,����Ĳ���Ϊ��" + vInfo.toString());
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
     * ��ѯ������Ӧ����Ϣ
     * @param vInfo ��ѯ���ֶ�Ϊ��baseSeqId �� commCode,State��� 
     * @param isAll   �Ƿ����������Ϣ����ϵ����Ϣ��������Ϣ��������Ϣ
     * @return VendorInfo
     * @throws Exception
     */
	public VendorInfo getVendorInfo(VendorInfo vInfo,boolean isAll)throws Exception{
		VendorInfo retInfo = null;
		logger.debug("��ѯ������Ӧ����Ϣ,����Ĳ���Ϊ��" + vInfo.toString());
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		if(null != vInfo.getBaseSeqId()){//��������ѯ
			retInfo = vInfoDao.findVendorInfoByID(vInfo.getBaseSeqId().toString());
		}else{//��commCode,State���
			retInfo = vInfoDao.findVendorInfoByBase(vInfo);
		}
		if((null != retInfo) && isAll){//��Ҫ��ѯ��������Ϣ
			/** ������Ϣ  */
			retInfo.setListBankInfo(getBankInfoList(retInfo.getCommCode(),retInfo.getState()));
			/** ��ϵ����Ϣ */
			retInfo.setListContactInfo(getContactInfoList(retInfo.getCommCode(),retInfo.getState()));
			
			/**  ��ȡ���ڻ�Ԥ������Ϣ   ��Ӧ�����ͻ���ֻ����һ�ֽ�����Ϣ */
			int iSettleMent = Integer.parseInt(retInfo.getSettlementType());
			switch(iSettleMent){
			case 1:	
				/**  ��ȡ���ڻ�Ԥ������Ϣ    */
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
	 * ��ȡ���������Ĺ�Ӧ����Ϣ��֧��ģ����ѯ����ҳ��ѯ
	 * @param cInfo
	 * @return
	 * @throws Exception
	 */
	public List<VendorInfo> getVendorInfoList(VendorInfo vInfo)throws Exception{
		logger.debug("��ȡ���������Ĺ�Ӧ����Ϣ,����Ĳ���Ϊ��" + vInfo.toString());
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		return vInfoDao.listVendorInfo(vInfo);
	}
	/**
	 * ��ȡ���������Ĺ�Ӧ������
	 * @param cInfo
	 * @return
	 * @throws Exception
	 */
	public Integer getVendorInfoCount(VendorInfo vInfo)throws Exception{
		logger.debug("��ȡ���������Ĺ�Ӧ������,����Ĳ���Ϊ��" + vInfo.toString());
		VendorInfoDao vInfoDao = (VendorInfoDao)BeanLocator.getInstance().getBean(VENDORINFO_DAO);
		return vInfoDao.listVendorInfoCount(vInfo);
	}
	
	
	/**
	 * ��ȡ������Ϣ
	 * @param commCode �ͻ�����
	 * @param state   ״̬
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
	 * ��ȡ��ϵ����Ϣ
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
	 * ��ȡ������Ϣ
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
	 * ��ȡԤ������Ϣ
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
	 * ɾ����ʽ��Ϣ����ʱ��Ϣ
	 * @param vInfo
	 * @param isDelCurrent  �Ƿ�ɾ�����β���������
	 * @throws Exception
	 */
	private void cancelVendorInfo(VendorInfo vInfo,boolean isDelCurrent) throws Exception{
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
		}else{
			aPreiod = new AccountPreiod();
			aPreiod.setState(vInfo.getState());
			aPreiod.setCommCode(vInfo.getCommCode());
		}
		AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
		accountPreiodMgr.deleteAccountPreiod(aPreiod, isDelCurrent);
		
		/**  Ԥ������Ϣ			 */
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
			cancelVendorInfo(vInfo,true);
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
			cancelVendorInfo(vInfo,false);
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
				cInfo.setCommCode(vInfo.getCommCode());
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
				cInfo.setCommCode(vInfo.getCommCode());
				cInfo.setBaseSeqId((vInfo.getBaseSeqId()).toString());
			}
			BankInfoMgr bankInfoMgr =(BankInfoMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_BANKINFOMGR);
			bankInfoMgr.updateBankInfoList(bankInfoList);
		}
		/**  ����������Ϣ��Ԥ������Ϣ  */
		AccountPreiodMgr accountPreiodMgr =(AccountPreiodMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_ACCOUNTPREIODMGR);
		accountPreiodMgr.deleteAccountPreiod(vInfo.getBaseSeqId().toString());
		PrePaidMgr prePaidMgr =(PrePaidMgr)BeanLocator.getInstance().getBean(VendorInfoConstants.VENDOR_PREPAIDMGR);
		prePaidMgr.deletePrePaidInfo(vInfo.getBaseSeqId().toString());
		
		//��ȡ������Ϣ����
		int iSettlement = Integer.parseInt(vInfo.getSettlementType());
		switch(iSettlement){
		case 1:
			/**  ������Ϣ  */
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
		
			/**  Ԥ������Ϣ			 */
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
