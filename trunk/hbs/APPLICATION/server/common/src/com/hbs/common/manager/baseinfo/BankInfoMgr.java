/**
 * system ��hbs
 * desc:    ������Ϣ�Ľӿ��࣬����ͳһ�������ͻ��͹�Ӧ��ʹ��
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.manager.baseinfo;


import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customer.common.constants.StateConstants;

import com.hbs.domain.common.dao.baseinfo.BankInfoDao;

import com.hbs.domain.common.pojo.baseinfo.BankInfo;


/**
 * ���༰ʵ�ֵ����๩�ͻ�������Ϣ�͹�Ӧ�̻�����Ϣ����
 * actionһ�㲻��Ҫ���ã�action��Ե�ֻ�ǿͻ�������Ϣ
 * ������͹�Ӧ�̻�����Ϣ������
 */
public abstract class BankInfoMgr {

	/**
	 * ���󷽷�������ʵ��
	 * @return
	 */
	public abstract String getBankInfoDao();
	
	/**
	 *  ���󷽷�������ʵ��
	 * @return
	 */
	public abstract String getLogDao();
	
	/**
	 * ���󷽷�����ȡ�������־���
	 * @return
	 */
	public abstract Logger getLogger();
	/**
	 * ����������Ϣ,������Ϣ�Ĳ��룬
	 * �ͻ���Ϣͬʱ�ύ�������ͻ���Ϣ��״̬	 
	 * @param bankInfo	 
	 * @return  0--�ɹ� 
	 * @throws Exception
	 */
	public int insertBankInfo(BankInfo bankInfo) throws Exception{
		int ret =0;
		getLogger().debug("����������Ϣ������Ĳ���Ϊ��" + bankInfo.toString());
		BankInfoDao bankInfoDao =(BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		BankInfo bInfo = bankInfoDao.findBankInfo(bankInfo);
		if(null == bInfo){
			bankInfoDao.insertBankInfo(bankInfo);
		}else{
			//ret = 1;
			throw new Exception("����������Ϣ,��������Ϣ�Ѵ��ڣ�����������");
		}
		return ret;
	}
	/**
	 * ��������������Ϣ
	 * @param bankInfoList
	 * @param staffId
	 * @param staffName
	 * @return
	 * @throws Exception
	 */
	public int insertBankInfoList(List<BankInfo> bankInfoList) throws Exception{
		int ret =0;
		getLogger().debug("��������������Ϣ����������Ϊ��" + bankInfoList.size());
		for(BankInfo bankInfo : bankInfoList){
			insertBankInfo(bankInfo);
		}
		return ret;
	}
	/**
	 * ɾ����ʽ��Ϣ����ʱ��Ϣ����������Ϣ����
	 * @param bankInfo
	 * @param isDelCurrent  �Ƿ�ͬʱɾ�����β���������
	 * @throws Exception
	 */
	public void deleteBankInfo(BankInfo bankInfo,boolean isDelCurrent)throws Exception{
		getLogger().debug("ɾ��������Ϣ,����Ĳ���Ϊ��" + bankInfo.toString());
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		bankInfoDao.deleteBankInfo(bankInfo);
		if(isDelCurrent){
			bankInfoDao.deleteBankInfoByID(bankInfo.getBaseSeqId());
		}		
	}
	/**
	 * ����������Ϣ
	 * @param bankInfo
	 * @param state
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	private int updateBankInfo(BankInfo bankInfo)throws Exception{
		int ret =0;
		getLogger().debug("����������Ϣ,����Ĳ���Ϊ��" + bankInfo.toString());
		int state = Integer.parseInt(bankInfo.getState());
		getLogger().debug("����������Ϣ,״̬Ϊ��" + state);
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		//String strLogType = null;
		switch (state){
		case 0:  //����ͨ��,��ɾ�������,ͬʱɾ������������
		         //ɾ�������ɻ�����Ϣ��ɾ��
			//bankInfoDao.deleteBankInfo(bankInfo);
			bankInfoDao.insertBankInfo(bankInfo);			
			//bankInfoDao.deleteBankInfoByID(bankInfo.getSeqId());
			//strLogType = "��������";
			break;
		case 1://û���ύ�������޸�	
			//Ϊ��֤�б����ݱ仯�ܹ�ʵ�֣���ɾ�����еģ������²��룬ɾ�������ڵ�������ɾ��
			//bankInfoDao.deleteBankInfoByID(bankInfo.getBaseSeqId());
			bankInfoDao.insertBankInfo(bankInfo);
			//bankInfoDao.updateBankInfo(bankInfo);
			//strLogType = "�޸���ʱ����";
			break;
		case 2://�ύ����ֻ�޸�״̬
			//Ϊ��֤�б����ݱ仯�ܹ�ʵ�֣���ɾ�����еģ������²��� ,ɾ�������ڵ�������ɾ��
			//bankInfoDao.deleteBankInfoByID(bankInfo.getBaseSeqId());
			bankInfoDao.insertBankInfo(bankInfo);
			//bankInfoDao.updateBankInfo(bankInfo);
			//strLogType = "�ύ��ʱ����";
			break;
		case 3://������ͨ������ֻ�޸�״̬
			bankInfoDao.updateBankInfoByState(bankInfo);
			//strLogType = "������ͨ������";
			break;
		case 4://�������ݣ����Ѿ������������������ͬʱ����ѱ��η���������״̬��Ϊ����
			//ɾ�����������ɻ�����Ϣ����
			//bankInfoDao.deleteBankInfo(bankInfo);
			bankInfoDao.updateBankInfoByState(bankInfo);
			//strLogType = "��������";
			break;
		case 5://��������ֻ�޸�״̬
			bankInfoDao.updateBankInfoByState(bankInfo);
			//strLogType = "��������";
			break;
			
		case 6 ://�������ݣ�ֻ�޸�״̬
			
			bankInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			bankInfoDao.updateBankInfoByState(bankInfo);
			//strLogType = "��������";
		default:
			throw new Exception("����������Ϣ ,�޴����͵�״̬���޷����и��£�");
		}
//		if(null != staffName){
//			operLog( staffId, staffName, strLogType, bankInfo.getBaseSeqId(), otherInfo);
//		}
		return ret;
	}
	
	/**
	 * ��������������Ϣ
	 * @param bankInfoList
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	public int updateBankInfoList(List<BankInfo> bankInfoList ) throws Exception{
		int ret =0;
		getLogger().debug("��������������Ϣ����������Ϊ��" + bankInfoList.size());
		boolean isDel = true;
		for(BankInfo bankInfo : bankInfoList){
			if(isDel){
				int state = Integer.parseInt(bankInfo.getState());
				switch(state){
				case 1:
				case 2:
					BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
					bankInfoDao.deleteBankInfoByID(bankInfo.getBaseSeqId());
					isDel = false;
					break;
					
				}
			}
			updateBankInfo( bankInfo);
		}
		return ret;
	}
	
	/**
	 * ��¼������־
	 * @param staffId
	 * @param staffName
	 * @param logType
	 * @param operKey
	 * @param otherInfo
	 */
//	private void operLog(String staffId,String staffName,String logType,String operKey,String otherInfo){
//		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(getLogDao());
//		OperLog log = new OperLog();
//		log.setStaffId(staffId);
//		log.setStaffName(staffName);
//		log.setOperTime(new Date());
//		log.setOperObject("������Ϣ");
//		log.setOperKey(operKey);
//		log.setOperType(logType);
//		if(otherInfo != null){
//			log.setOperContent(otherInfo);
//		}
//		logDao.insertOperLog(log);
//	}
	
	/**
	 * ��ѯ������Ϣ����������ѯ
	 * @param bankInfo 
	 * @return
	 * @throws Exception
	 */
	public BankInfo getBankInfo(BankInfo bankInfo) throws Exception{
		getLogger().debug("��ѯ����������Ϣ,����Ĳ���Ϊ��" + bankInfo.toString());
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		return bankInfoDao.findBankInfo(bankInfo);
	}
	
	public BankInfo getBankInfoById(String seqId) throws Exception{
		getLogger().debug("����������ѯ����������Ϣ,����Ĳ���Ϊ��" + seqId);
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		return bankInfoDao.findBankInfoById(seqId);
	}
	/**
	 * ��ѯ������Ϣ�б�
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 */
	public List<BankInfo> listBankInfo(BankInfo bankInfo) throws Exception{
		getLogger().debug("��ѯ������Ϣ,����Ĳ���Ϊ��" + bankInfo.toString());
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		return bankInfoDao.listBankInfo(bankInfo);
	}
}
