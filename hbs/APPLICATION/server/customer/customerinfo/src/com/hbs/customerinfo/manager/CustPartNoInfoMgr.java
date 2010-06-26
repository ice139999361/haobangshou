/**
 * system ��hbs
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
	 * ����ͻ���ʱ���Ϲ�ϵ��Ϣ��״̬Ϊ1
	 * @param custPartNoInfo
	 * @return 0--�ɹ�  1--�����ظ�����
	 * @throws Exception
	 */
	public int saveTempCustPartNoInfo(CustPartNoInfo custPartNoInfo)throws Exception{
		logger.debug("����ͻ���ʱ���Ϲ�ϵ��Ϣ������Ĳ���Ϊ��" + custPartNoInfo.toString());
		custPartNoInfo.setState(new Integer(StateConstants.STATE_1).toString());
		return insertCustPartNoInfo(custPartNoInfo);
	}
	/**
	 * ��������ͻ���ʱ���Ϲ�ϵ��Ϣ��״̬Ϊ1
	 * @param custPartNoInfoList
	 * @return
	 * @throws Exception
	 */
	public int saveTempCustPartNoInfoList(List<CustPartNoInfo> custPartNoInfoList)throws Exception{
		int ret =0;
		logger.debug("����ͻ���ʱ���Ϲ�ϵ��Ϣ�б��б�����Ϊ��" + custPartNoInfoList.size());
		for(CustPartNoInfo custPartNoInfo : custPartNoInfoList){
			custPartNoInfo.setState(new Integer(StateConstants.STATE_1).toString());
			logger.debug("����ͻ���ʱ���Ϲ�ϵ��Ϣ������Ĳ���Ϊ��" + custPartNoInfo.toString());
			insertCustPartNoInfo( custPartNoInfo);
		}
		return ret;		
	}
	/**
	  * �ύ��������,���ݵ�״̬Ϊ��ʱ״̬,����Ϊ�쵼�����ܾ���״̬,�ſ����ύ����
	 * ����״̬�޸ĵ�ͬʱ����Ҫ������֪ͨ
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public int commitCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws Exception{
		int ret =0;
		logger.debug("�ύ�ͻ����Ϲ�ϵ��Ϣ�б�����Ϊ��" + custPartNoInfo.toString());
		CustPartNoInfo existInfo = this.getCustPartNoInfoByBizKey(custPartNoInfo);
		if(existInfo != null){//��������
			//��ȡ�ύ���ݴ�״̬
			int iState = Integer.parseInt(existInfo.getState());
			
			if(iState == StateConstants.STATE_1 || iState == StateConstants.STATE_3){
				custPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
				ret = this.innerUpdateCustPartNoInfo(custPartNoInfo, custPartNoInfo.getStaffId(), custPartNoInfo.getStaffName(), null);
			}else if(iState == StateConstants.STATE_0){
				custPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
				ret = this.insertCustPartNoInfo(custPartNoInfo);
			}
		}else{//����������
			custPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = this.insertCustPartNoInfo(custPartNoInfo);
		}
		//���촦��
		
		if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
	 * �����ύ�ͻ����Ϲ�ϵ
	 * @param custPartNoInfoList
	 * @return
	 * @throws Exception
	 */
	public int commitCustPartNoInfoList(List<CustPartNoInfo> custPartNoInfoList) throws Exception{
		int ret =0;
		logger.debug("�ύ�ͻ���ʱ���Ϲ�ϵ��Ϣ�б��б�����Ϊ��" + custPartNoInfoList.size());
		for(CustPartNoInfo custPartNoInfo : custPartNoInfoList){
			
			logger.debug("�ύ�ͻ���ʱ���Ϲ�ϵ��Ϣ������Ĳ���Ϊ��" + custPartNoInfo.toString());
			commitCustPartNoInfo( custPartNoInfo);
		}
		return ret;		
	}
	/**
	 * ����ͬ��ͻ���������
	 * @param custPartNoInfo
	 * @param auditId  ������ID
	 * @param auditName ����������
	 * @param auditDesc �������
	 * @return  0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int auditAgreeCustPartNoInfo(CustPartNoInfo custPartNoInfo, String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		logger.debug("����ͬ��ͻ���ʱ���Ϲ�ϵ��Ϣ�б�����Ϊ��" + custPartNoInfo.toString());
		int iState = Integer.parseInt(custPartNoInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			custPartNoInfo.setState(new Integer(StateConstants.STATE_0).toString());
			ret = innerUpdateCustPartNoInfo(custPartNoInfo,auditId,auditName,auditDesc);
			if(ret ==0){//�����Ѵ���֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
			logger.debug("����ͬ��ͻ���ʱ���Ϲ�ϵ��Ϣ�б�״̬����ȷ!");
			ret =2;//��ʾ�����ύ��״̬����ȷ
		}
		
		return 0;
	}
	
	/**
	 * ������ͬ��ͻ���������
	 * @param custPartNoInfo
	 * @param auditId  ������ID
	 * @param auditName  ����������
	 * @param auditDesc  �������
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int auditDisAgreeCustPartNoInfo(CustPartNoInfo custPartNoInfo ,String auditId, String auditName,String auditDesc) throws Exception{
		int ret =0;
		logger.debug("������ͬ��ͻ���ʱ���Ϲ�ϵ��Ϣ�б�����Ϊ��" + custPartNoInfo.toString());
		int iState = Integer.parseInt(custPartNoInfo.getState());
		if(iState == StateConstants.STATE_2 ){
			custPartNoInfo.setState(new Integer(StateConstants.STATE_3).toString());
			ret = innerUpdateCustPartNoInfo(custPartNoInfo,auditId,auditName,auditDesc);
			if(ret ==0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
			logger.debug("����ͬ��ͻ���ʱ���Ϲ�ϵ��Ϣ�б�״̬����ȷ!");
			ret =2;//��ʾ�����ύ��״̬����ȷ
		}
		
		return 0;
	}
	
	/**
	 * �޸Ŀͻ�������Ϣ���޸�ǰ��״̬���ܲ�ͬ����Ҫ����Դ�
	 * �޸�ǰ״̬Ϊ1 ������ʱ�������޸�
	 * �޸�ǰ״̬Ϊ0 ������ʽ�������޸ģ�ֱ���ύ�쵼����
	 * �޸�ǰ״̬Ϊ3 ����������ͨ���������޸ģ�ֱ���ύ�쵼����
		//����״̬�������޸Ĳ��� 
	 * @param custPartNoInfo
	 * @return 0---�ɹ�   1--�޴�״̬  2---״̬����ȷ
	 * @throws Exception
	 */
	public int updateCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws Exception{
		int ret =0;
		logger.debug("�޸Ŀͻ����Ϲ�ϵ��Ϣ�б�����Ϊ��" + custPartNoInfo.toString());
		int iState = Integer.parseInt(custPartNoInfo.getState());
		//״̬Ϊ1 ������ʱ�������޸�
		//״̬Ϊ0 ������ʽ�������޸ģ�ֱ���ύ�쵼����
		//״̬Ϊ3 ����������ͨ���������޸ģ�ֱ���ύ�쵼����
		//����״̬�������޸Ĳ���
		switch(iState) {
		case 1:
			ret = innerUpdateCustPartNoInfo(custPartNoInfo,custPartNoInfo.getStaffId(),custPartNoInfo.getStaffName(),null);
			break;
		case 3:
			custPartNoInfo.setState(new Integer(StateConstants.STATE_2).toString());
			ret = innerUpdateCustPartNoInfo(custPartNoInfo,custPartNoInfo.getStaffId(),custPartNoInfo.getStaffName(),null);
			if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
			if(ret == 0){//������֪ͨ,��ȡ�����ܵĴ��죬������µĴ���
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
	 * * �ϳ��ͻ����ݣ�ֻ����������ͨ����״̬�������зϳ�����
	 * @param custPartNoInfo
	 * @param delDesc   �ϳ�ԭ��
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
	 * ��ȡ���ϵ���ʷ�۸�䶯����ͨ��������ȡ������ʷ��¼
	 * @param custPartNoInfo �� partNo custPartNo commCode �������ֶ�����־�ؼ��֣��ش��� 
	 * @return
	 * @throws Exception
	 */
	public List<OperLog> getPartNoChange(CustPartNoInfo custPartNoInfo) throws Exception{
		OperLog logKey = new OperLog();
		logKey.setOperKey(custPartNoInfo.getLogBizKey()+"��������");
		return CustLogUtils.getLogList(logKey);
	}
	
	/**
	 * �������Ϲ�ϵ���ж��Ƿ������ͬҵ��ؼ��ֵ����ݴ��ڣ�������ڣ���update����
	 * @param custPartNoInfo	
	 * @return 0--�ɹ�  -1 �Ѿ������޸Ĵ���������
	 * @throws Exception
	 */
	private int insertCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws Exception{
		int ret =0;
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		//��ѯ���Ϲ�ϵ���жϲ���������Ƿ��Ѿ�����
		CustPartNoInfo tempInfo = new CustPartNoInfo();
		tempInfo.setCommCode(custPartNoInfo.getCommCode());
		tempInfo.setCustPartNo(custPartNoInfo.getCustPartNo());
		tempInfo.setPartNo(custPartNoInfo.getPartNo());
		tempInfo.setState(custPartNoInfo.getState());
		
		tempInfo = custPartNoInfoDao.findCustPartNoInfoByBizKey(tempInfo);
		//Integer seqID =0;
		if(null == tempInfo){//������
			custPartNoInfoDao.insertCustPartNoInfo(custPartNoInfo);
			//��¼��־			
			CustLogUtils.operLog(custPartNoInfo.getStaffId(), custPartNoInfo.getStaffName(), (null == tempInfo ?"����" : "�޸�"), "���϶��չ�ϵ��Ϣ", custPartNoInfo.getLogBizKey(), custPartNoInfo.getLogContent(), null);
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
	 * ������Ϣ
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
		case 0:  //����ͨ��,��ɾ�������,ͬʱɾ������������,����δ��
			String seqId = custPartNoInfo.getSeqId().toString();
			custPartNoInfo.setIsPriceChange(getIsPriceChange(custPartNoInfoDao,custPartNoInfo));
			custPartNoInfoDao.deleteCustPartNoInfoByBizKey(custPartNoInfo);
			custPartNoInfoDao.insertCustPartNoInfo(custPartNoInfo);			
			custPartNoInfoDao.deleteCustPartNoInfoByID(seqId);
			strLogType = "��������";
			break;
		case 1://û���ύ�������޸�		
			custPartNoInfoDao.updateCustPartNoInfo(custPartNoInfo);
			strLogType = "�޸���ʱ����";
			break;
		case 2://�ύ�����޸�
			custPartNoInfoDao.updateCustPartNoInfo(custPartNoInfo);
			strLogType = "�ύ��ʱ����";
			break;
		case 3://������ͨ������ֻ�޸�״̬
			custPartNoInfoDao.updateCustPartNoInfoByState(custPartNoInfo);
			strLogType = "������ͨ������";
			break;
		case 4://��������ֻ�޸�״̬			
			custPartNoInfoDao.updateCustPartNoInfoByState(custPartNoInfo);
			strLogType = "��������";
			break;
		case 5://��������ֻ�޸�״̬
			custPartNoInfoDao.updateCustPartNoInfoByState(custPartNoInfo);
			strLogType = "��������";
			break;
		default:
			ret =1;
		}
		if(null != staffName){			
			CustLogUtils.operLog(staffId, staffName, strLogType, "�ͻ�������Ϣ", (state == 0 ? custPartNoInfo.getLogBizKey()+ strLogType: custPartNoInfo.getLogBizKey()), custPartNoInfo.getLogContent(), otherInfo);
		}
		return ret;
	}
	
	/**
	 * ��������
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
	 * ��������id��ȡ��Ϣ
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public CustPartNoInfo getCustPartNoInfoByID(String seqId)throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.findCustPartNoInfoByID(seqId);
	}
	
	/**
	 * ����ҵ��������ȡ��Ϣ
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public CustPartNoInfo getCustPartNoInfoByBizKey(CustPartNoInfo custPartNoInfo) throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.findCustPartNoInfoByBizKey(custPartNoInfo);
	}
	
	/**
	 * ��ȡ�б���Ϣ
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public List<CustPartNoInfo> listCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.listCustPartNoInfo(custPartNoInfo);
	}
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param custPartNoInfo
	 * @return
	 * @throws Exception
	 */
	public Integer listCustPartNoInfoCount(CustPartNoInfo custPartNoInfo) throws Exception{
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		return custPartNoInfoDao.listCustPartNoInfoCount(custPartNoInfo);
	}

}
