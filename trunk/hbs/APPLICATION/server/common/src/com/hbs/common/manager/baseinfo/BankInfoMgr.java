/**
 * system ��hbs
 * desc:    ������Ϣ�Ľӿ��࣬����ͳһ�������ͻ��͹�Ӧ��ʹ��
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.manager.baseinfo;

import java.util.Date;
import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customer.common.constants.StateConstants;

import com.hbs.domain.common.dao.baseinfo.BankInfoDao;
import com.hbs.domain.common.dao.baseinfo.OperLogDao;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.OperLog;

/**
 * @author yangzj
 *
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
	 * ����������Ϣ,������Ϣ�Ĳ��룬����ǵ������룬��״̬Ϊ��������ֱ���ύ������
	 * ����Ǹ��ͻ���Ϣͬʱ�ύ�������ͻ���Ϣ��״̬	 
	 * @param bankInfo
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int insertBankInfo(BankInfo bankInfo,String staffId,String staffName) throws Exception{
		int ret =0;
		BankInfoDao bankInfoDao =(BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		bankInfoDao.insertBankInfo(bankInfo);
		if(null != staffId){//˵��������Ϣ�ǵ�������,��Ҫ������¼������־
//			int state = Integer.parseInt(bankInfo.getState());
//			switch (state){
//			case 1://������ʱ���ݣ���û���ύ
//			case 2://�����ύ������
//			}
			operLog(staffId,staffName, "����", bankInfo.getBaseSeqId(),null);
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
	public int insertBankInfoList(List<BankInfo> bankInfoList ,String staffId,String staffName) throws Exception{
		int ret =0;
		for(BankInfo bankInfo : bankInfoList){
			insertBankInfo(bankInfo, staffId, staffName);
		}
		return ret;
	}
	/**
	 * ����������Ϣ
	 * @param bankInfo
	 * @param state
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int updateBankInfo(BankInfo bankInfo, String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(bankInfo.getState());
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		String strLogType = null;
		switch (state){
		case 0:  //����ͨ��,��ɾ�������,ͬʱɾ������������,����δ��
			bankInfoDao.deleteBankInfo(bankInfo);
			bankInfoDao.insertBankInfo(bankInfo);			
			bankInfoDao.deleteBankInfoByID(bankInfo.getSeqId());
			strLogType = "��������";
			break;
		case 1://û���ύ�������޸�		
			bankInfoDao.updateBankInfo(bankInfo);
			strLogType = "�޸���ʱ����";
			break;
		case 2://�ύ����ֻ�޸�״̬
			bankInfoDao.updateBankInfo(bankInfo);
			strLogType = "�ύ��ʱ����";
			break;
		case 3://������ͨ������ֻ�޸�״̬
			bankInfoDao.updateBankInfoByState(bankInfo);
			strLogType = "������ͨ������";
			break;
		case 4://��������ֻ�޸�״̬
			bankInfoDao.deleteBankInfo(bankInfo);
			bankInfoDao.updateBankInfoByState(bankInfo);
			strLogType = "��������";
			break;
		case 5://��������ֻ�޸�״̬
			bankInfoDao.updateBankInfoByState(bankInfo);
			strLogType = "��������";
			break;
			
		case 6 ://�������ݣ�ֻ�޸�״̬
			
			bankInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			bankInfoDao.updateBankInfoByState(bankInfo);
			strLogType = "��������";
		default:
			ret =1;
		}
		if(null != staffName){
			operLog( staffId, staffName, strLogType, bankInfo.getBaseSeqId(), otherInfo);
		}
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
	public int updateBankInfoList(List<BankInfo> bankInfoList ,String staffId,String staffName,String otherInfo) throws Exception{
		int ret =0;
		for(BankInfo bankInfo : bankInfoList){
			updateBankInfo( bankInfo,  staffId, staffName, otherInfo);
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
	private void operLog(String staffId,String staffName,String logType,String operKey,String otherInfo){
		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(getLogDao());
		OperLog log = new OperLog();
		log.setStaffId(staffId);
		log.setStaffName(staffName);
		log.setOperTime(new Date());
		log.setOperObject("������Ϣ");
		log.setOperKey(operKey);
		log.setOperType(logType);
		if(otherInfo != null){
			log.setOperContent(otherInfo);
		}
		logDao.insertOperLog(log);
	}
	
	/**
	 * ��ѯ������Ϣ����������ѯ
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public BankInfo getBankInfo(String pk) throws Exception{
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		return bankInfoDao.findBankInfo(pk);
	}
	
	/**
	 * ��ѯ������Ϣ�б�
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 */
	public List<BankInfo> listBankInfo(BankInfo bankInfo) throws Exception{
		BankInfoDao bankInfoDao = (BankInfoDao)BeanLocator.getInstance().getBean(getBankInfoDao());
		return bankInfoDao.listBankInfo(bankInfo);
	}
}
