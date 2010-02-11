/**
 * system ��hbs
 * desc:    ��ϵ����Ϣ������
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.manager.baseinfo;


import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customer.common.constants.StateConstants;
import com.hbs.domain.common.dao.baseinfo.ContactInfoDao;

import com.hbs.domain.common.pojo.baseinfo.ContactInfo;

/**
 * ���༰ʵ�ֵ����๩�ͻ�������Ϣ�͹�Ӧ�̻�����Ϣ����
 * actionһ�㲻��Ҫ���ã�action��Ե�ֻ�ǿͻ�������Ϣ
 * ������͹�Ӧ�̻�����Ϣ������
 */
public abstract class ContactMgr {
	
	
	/**
	 * ���󷽷�������ʵ��
	 * @return
	 */
	public abstract String getContactInfoDao();
	
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
	 * ������ϵ����Ϣ,������Ϣ�Ĳ��룬����ǵ������룬��״̬Ϊ��������ֱ���ύ������
	 * ����Ǹ��ͻ���Ϣͬʱ�ύ�������ͻ���Ϣ��״̬	 
	 * @param bankInfo
	 * @param state
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int insertContactInfo(ContactInfo contactInfo) throws Exception{
		int ret =0;
		getLogger().debug("������ϵ����Ϣ������Ĳ���Ϊ��" + contactInfo.toString());
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		ContactInfo cInfo = contactInfoDao.findContactInfo(contactInfo);
		if(null == cInfo){
		contactInfoDao.insertContactInfo(contactInfo);
		}else{
			throw new Exception("������ϵ����Ϣ,��������Ϣ�Ѵ��ڣ�����������");
		}
		
		return ret;
	}
	
	/**
	 * ��������
	 * @param contactInfoList
	 * @param state
	 * @param staffId
	 * @param staffName
	 * @return
	 * @throws Exception
	 */
	public int insertContactInfoList(List<ContactInfo> contactInfoList) throws Exception{
		int ret =0;
		getLogger().debug("����������ϵ����Ϣ����������Ϊ��" + contactInfoList.size());
		for(ContactInfo contactInfo : contactInfoList){
			insertContactInfo( contactInfo);
		}
		return ret;
	}
	/**
	 * ɾ����ʽ��Ϣ����ʱ��Ϣ����������Ϣ����
	 * @param contactInfo
	 * @param isDelCurrent  �Ƿ�ͬʱɾ�����β���������
	 * @throws Exception
	 */
	public void deleteContactInfo(ContactInfo contactInfo,boolean isDelCurrent)throws Exception{
		getLogger().debug("ɾ����ϵ����Ϣ,����Ĳ���Ϊ��" + contactInfo.toString());
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		contactInfoDao.deleteContactInfo(contactInfo);
		if(isDelCurrent){
			contactInfoDao.deleteContactInfoByID(contactInfo.getSeqId());
		}
	}
	
	/**
	 * ������ϵ����Ϣ
	 * @param bankInfo
	 * @param state
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int updateContactInfo(ContactInfo contactInfo)throws Exception{
		int ret =0;
		getLogger().debug("������ϵ����Ϣ,����Ĳ���Ϊ��" + contactInfo.toString());
		int state = Integer.parseInt(contactInfo.getState());
		getLogger().debug("����������Ϣ,״̬Ϊ��" + state);
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		//String strLogType = null;
		switch (state){
		case 0:  //����ͨ��,��ɾ�������,ͬʱɾ������������
		     //ɾ�������ɻ�����Ϣ��ɾ��
			//contactInfoDao.deleteContactInfo(contactInfo);
			contactInfoDao.insertContactInfo(contactInfo);			
			//contactInfoDao.deleteContactInfoByID(contactInfo.getSeqId());
			//strLogType = "��������";
			break;
		case 1://û���ύ�������޸�		
			contactInfoDao.updateContactInfo(contactInfo);
			//strLogType = "�޸���ʱ����";
			break;
		case 2://�ύ�����޸�
			contactInfoDao.updateContactInfo(contactInfo);
			//strLogType = "�ύ��ʱ����";
			break;
		case 3://������ͨ������ֻ�޸�״̬
			contactInfoDao.updateContactInfoByState(contactInfo);
			//strLogType = "������ͨ������";
			break;
		case 4://�������ݣ����Ѿ������������������ͬʱ����ѱ��η���������״̬��Ϊ����
			//ɾ�����������ɻ�����Ϣ����
			//contactInfoDao.deleteContactInfo(contactInfo);
			contactInfoDao.updateContactInfoByState(contactInfo);
			//strLogType = "��������";
			break;
		case 5://��������ֻ�޸�״̬
			contactInfoDao.updateContactInfoByState(contactInfo);
			//strLogType = "��������";
			break;
			
		case 6 ://�������ݣ�ֻ�޸�״̬
			
			contactInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			contactInfoDao.updateContactInfoByState(contactInfo);
			//strLogType = "��������";
		default:
			throw new Exception("������ϵ����Ϣ ,�޴����͵�״̬���޷����и��£�");
		}
		
		return ret;
	}
	/**
	 * ��������
	 * @param contactInfoList
	 * @param staffId
	 * @param staffName
	 * @param otherInfo
	 * @return
	 * @throws Exception
	 */
	public int updateContactInfoList(List<ContactInfo> contactInfoList, String staffId,String staffName,String otherInfo)throws Exception{
		int ret =0 ;
		getLogger().debug("����������ϵ����Ϣ����������Ϊ��" + contactInfoList.size());
		for(ContactInfo contactInfo : contactInfoList){
			updateContactInfo( contactInfo);
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
//	private void operLog(String staffId,String staffName,String logType,String operKey,String otherInfo){
//		OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(getLogDao());
//		OperLog log = new OperLog();
//		log.setStaffId(staffId);
//		log.setStaffName(staffName);
//		log.setOperTime(new Date());
//		log.setOperObject("��ϵ����Ϣ");
//		log.setOperKey(operKey);
//		log.setOperType(logType);
//		if(otherInfo != null){
//			log.setOperContent(otherInfo);
//		}
//		logDao.insertOperLog(log);
//	}
	
	/**
	 * ����������ѯ��ϵ��
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public ContactInfo getContactInfo(ContactInfo contactInfo) throws Exception{
		getLogger().debug("����ҵ��������ѯ��ϵ����Ϣ,����Ĳ���Ϊ��" + contactInfo.toString());
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		return contactInfoDao.findContactInfo(contactInfo);
	}
	
	public ContactInfo getContactInfo(String id) throws Exception{
		getLogger().debug("��������ID��ѯ��ϵ����Ϣ,����Ĳ���Ϊ��" + id);
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		return contactInfoDao.findContactInfoById(id);
	}
	/**
	 * ��ѯ��ϵ����Ϣ�б�
	 * @param contactInfo
	 * @return
	 * @throws Exception
	 */
	public List<ContactInfo> listContactInfo(ContactInfo contactInfo) throws Exception{
		getLogger().debug("��ѯ��ϵ����Ϣ�б�,����Ĳ���Ϊ��" + contactInfo.toString());
		ContactInfoDao contactInfoDao =(ContactInfoDao)BeanLocator.getInstance().getBean(getContactInfoDao());
		return contactInfoDao.listContactInfo(contactInfo);
	}
}
