/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;

import java.util.Date;
import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.constants.CustInfoConstants;

import com.hbs.domain.common.dao.baseinfo.OperLogDao;
import com.hbs.domain.common.pojo.baseinfo.OperLog;
import com.hbs.domain.customer.customerinfo.dao.CustPartNoInfoDao;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;


public class CustPartNoInfoMgr {

	private static final String CUSTOMERPARTNOINFODAO ="customerPartNoInfoDao";
	
	/**
	 * �������Ϲ�ϵ������������Ϣ�������������û���������������Ϣ����������Ҫ�ṩ�û�
	 * @param custPartNoInfo
	 * @param staffId
	 * @param staffName
	 * @return 0--�ɹ�  1--�����ظ�����
	 * @throws Exception
	 */
	public int insertCustPartNoInfo(CustPartNoInfo custPartNoInfo,String staffId,String staffName) throws Exception{
		int ret =0;
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		//��ѯ���Ϲ�ϵ���жϲ���������Ƿ��Ѿ�����
		CustPartNoInfo tempInfo = new CustPartNoInfo();
		tempInfo.setCommCode(custPartNoInfo.getCommCode());
		tempInfo.setCustPartNo(custPartNoInfo.getCustPartNo());
		tempInfo.setPartNo(custPartNoInfo.getPartNo());
		tempInfo.setState(custPartNoInfo.getState());
		
		tempInfo = custPartNoInfoDao.findCustPartNoInfoByBizKey(tempInfo);
		Integer seqID =0;
		if(null != tempInfo){//������
			seqID = custPartNoInfoDao.insertCustPartNoInfo(custPartNoInfo);
		}else{
			ret = 1;
		}
		if(null != staffId){//������������¼��־
			operLog( staffId, staffName, "����", seqID.toString(), null);
		}
		return ret;
	}
	/**
	 * ����������Ϣ
	 * @param custPartNoInfoList
	 * @param staffId
	 * @param staffName
	 * @return
	 * @throws Exception
	 */
	public int insertCustPartNoInfoList(List<CustPartNoInfo>  custPartNoInfoList,String staffId,String staffName) throws Exception{
		int ret =0;
		for(CustPartNoInfo custPartNoInfo : custPartNoInfoList){
			insertCustPartNoInfo( custPartNoInfo, staffId, staffName);
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
	public int updateCustPartNoInfo(CustPartNoInfo custPartNoInfo,String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(custPartNoInfo.getState());
		CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)BeanLocator.getInstance().getBean(CUSTOMERPARTNOINFODAO);
		String strLogType = null;
		switch (state){
		case 0:  //����ͨ��,��ɾ�������,ͬʱɾ������������,����δ��
			custPartNoInfoDao.deleteCustPartNoInfoByBizKey(custPartNoInfo);
			custPartNoInfoDao.insertCustPartNoInfo(custPartNoInfo);			
			custPartNoInfoDao.deleteCustPartNoInfoByID(custPartNoInfo.getSeqId().toString());
			strLogType = "��������";
			break;
		case 1://û���ύ�������޸�		
			custPartNoInfoDao.updateCustPartNoInfo(custPartNoInfo);
			strLogType = "�޸���ʱ����";
			break;
		case 2://�ύ����ֻ�޸�״̬
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
			operLog( staffId, staffName, strLogType, custPartNoInfo.getSeqId().toString(), otherInfo);
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
	public int updateCustPartNoInfoList(List<CustPartNoInfo> custPartNoInfoList,String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		for(CustPartNoInfo custPartNoInfo : custPartNoInfoList){
			updateCustPartNoInfo( custPartNoInfo, staffId, staffName, otherInfo);
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
		log.setOperObject("���϶��չ�ϵ��Ϣ");
		log.setOperKey(operKey);
		log.setOperType(logType);
		if(otherInfo != null){
			log.setOperContent(otherInfo);
		}
		logDao.insertOperLog(log);
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
