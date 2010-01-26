/**
 * system ��hbs
 * desc:   Ԥ������Ϣ�Ĺ����� 
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.manager.baseinfo;


import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customer.common.constants.StateConstants;



import com.hbs.domain.common.dao.baseinfo.PrePaidInfoDao;


import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;
/**
 * ���༰ʵ�ֵ����๩�ͻ�������Ϣ�͹�Ӧ�̻�����Ϣ����
 * actionһ�㲻��Ҫ���ã�action��Ե�ֻ�ǿͻ�������Ϣ
 * ������͹�Ӧ�̻�����Ϣ������
 */
public abstract class PrePaidMgr {
	
	/**
	 * ���󷽷�������ʵ��
	 * @return
	 */
	public abstract String getPrePaidDao();
	
	/**
	 *  ���󷽷�������ʵ��
	 * @return
	 */
	public abstract String getLogDao();
	/**
	 * ����Ԥ������Ϣ,Ԥ������Ϣ�Ĳ���
	 * ����Ǹ��ͻ���Ϣͬʱ�ύ�������ͻ���Ϣ��״̬
	 * @param accountPreiod
	 * @throws Exception
	 * @return 0--�ɹ� 1---���ݿ��Ѿ������ظ�����
	 */
	public int insertPrePaidInfo(PrePaidInfo prePaidInfo) throws Exception{
		int ret = 0;
		PrePaidInfoDao aPrepaidDao = (PrePaidInfoDao) BeanLocator
				.getInstance().getBean(
						getPrePaidDao());
		PrePaidInfo tempPrePaid = aPrepaidDao.findPrePaidInfo(prePaidInfo);
		if (null == tempPrePaid) {
			aPrepaidDao.insertPrePaidInfo(prePaidInfo);
		} else {
			ret = 1;
		}
		return ret;
	}
	
	/**
	 * ɾ����ʽ��Ϣ����ʱ��Ϣ����������Ϣ����
	 * @param prePaidInfo
	 * @param isDelCurrent  �Ƿ�ͬʱɾ�����β���������
	 * @throws Exception
	 */
	public void deletePrePaidInfo(PrePaidInfo prePaidInfo,boolean isDelCurrent)throws Exception{
		PrePaidInfoDao aPrepaidDao = (PrePaidInfoDao)BeanLocator.getInstance().getBean(getPrePaidDao());
		aPrepaidDao.deletePrePaidInfo(prePaidInfo);
		if(isDelCurrent){
			aPrepaidDao.deletePrePaidInfoByID(prePaidInfo.getSeqId());
		}
	}
	
	
	/**
	 * ����Ԥ������Ϣ
	 * @param accountPreiod
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int updatePrePaidInfo(PrePaidInfo prePaidInfo)throws Exception{
		int ret =0;
		int state = Integer.parseInt(prePaidInfo.getState());
		PrePaidInfoDao aPrepaidDao = (PrePaidInfoDao)BeanLocator.getInstance().getBean(getPrePaidDao());
		//String strLogType = null;
		switch (state){
		case 0:  //����ͨ��,��ɾ�������,ͬʱɾ������������
		     //ɾ�������ɻ�����Ϣ��ɾ��
			//aPrepaidDao.deletePrePaidInfo(prePaidInfo);
			aPrepaidDao.insertPrePaidInfo(prePaidInfo);			
			//aPrepaidDao.deletePrePaidInfoByID(prePaidInfo.getSeqId());
			//strLogType = "��������";
			break;
		case 1://û���ύ�������޸�		
			aPrepaidDao.updatePrePaidInfo(prePaidInfo);
			//strLogType = "�޸���ʱ����";
			break;
		case 2://�ύ����ֻ�޸�״̬
			aPrepaidDao.updatePrePaidInfo(prePaidInfo);
			//strLogType = "�ύ��ʱ����";
			break;
		case 3://������ͨ������ֻ�޸�״̬
			aPrepaidDao.updatePrePaidInfoByState(prePaidInfo);
			//strLogType = "������ͨ������";
			break;
		case 4://�������ݣ����Ѿ������������������ͬʱ����ѱ��η���������״̬��Ϊ����
			//ɾ�����������ɻ�����Ϣ����
			//aPrepaidDao.deletePrePaidInfo(prePaidInfo);
			aPrepaidDao.updatePrePaidInfoByState(prePaidInfo);
			//strLogType = "��������";
			break;
		case 5://��������ֻ�޸�״̬
			aPrepaidDao.updatePrePaidInfoByState(prePaidInfo);
			//strLogType = "��������";
			break;
		case 6 ://�������ݣ�ֻ�޸�״̬
			
			prePaidInfo.setState(new Integer(StateConstants.STATE_0).toString());
			
			aPrepaidDao.updatePrePaidInfoByState(prePaidInfo);
			//strLogType = "��������";
		default:
			ret =1;
		}
		
//		if(null != staffId){//����������¼��������Ҫ��¼������־
//			OperLogDao logDao = (OperLogDao)BeanLocator.getInstance().getBean(getLogDao());
//			OperLog log = new OperLog();
//			log.setStaffId(staffId);
//			log.setStaffName(staffName);
//			log.setOperTime(new Date());
//			log.setOperObject("������Ϣ");
//			log.setOperKey(prePaidInfo.getBaseSeqId());
//			log.setOperType(strLogType);
//			if(otherInfo != null){
//				log.setOperContent(otherInfo);
//			}
//			logDao.insertOperLog(log);
//		}
		return ret;
	}
	
	/**
	 * ��ȡ����������Ϣ������ͨ��commcode,state ��Ψһ��λ 
	 * @param prePaidInfo
	 * @return
	 * @throws Exception
	 */
	public PrePaidInfo getPrePaidInfo(PrePaidInfo prePaidInfo)throws Exception{
		PrePaidInfoDao aPrepaidDao = (PrePaidInfoDao) BeanLocator
		.getInstance().getBean(
				getPrePaidDao());
		return aPrepaidDao.findPrePaidInfo(prePaidInfo);
	}
	
	/**
	 * ��ȡ�����б���Ϣ��һ���ͻ����ܻ��ѯ�����������Ϣ����Ҫ����������״̬
	 * @param accountPreiod
	 * @return
	 * @throws Exception
	 */
	public List<PrePaidInfo> listPrePaidInfo(PrePaidInfo prePaidInfo)throws Exception{
		PrePaidInfoDao aPrepaidDao = (PrePaidInfoDao) BeanLocator
		.getInstance().getBean(
				getPrePaidDao());
		return aPrepaidDao.listPrePaidInfo(prePaidInfo);
	}
}
