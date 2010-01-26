/**
 * system ��hbs
 * desc:    ���ڵĳ����࣬����ͳһ�������ͻ��͹�Ӧ��ʹ��
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.manager.baseinfo;


import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customer.common.constants.StateConstants;

import com.hbs.domain.common.dao.baseinfo.AccountPreiodDao;

import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;


/**
 * ���༰ʵ�ֵ����๩�ͻ�������Ϣ�͹�Ӧ�̻�����Ϣ����
 * actionһ�㲻��Ҫ���ã�action��Ե�ֻ�ǿͻ�������Ϣ
 * ������͹�Ӧ�̻�����Ϣ������
 */
public abstract class AccountPreiodMgr {

	/**
	 * ���󷽷�������ʵ��
	 * @return
	 */
	public abstract String getAccountPreiodDao();
	
	/**
	 *  ���󷽷�������ʵ��
	 * @return
	 */
	public abstract String getLogDao();
	/**
	 * ����������Ϣ,������Ϣ�Ĳ��� ����Ǹ��ͻ���Ϣͬʱ�ύ�������ͻ���Ϣ��״̬
	 * 
	 * @param accountPreiod
	 * @throws Exception
	 * @return 0--�ɹ� 1---���ݿ��Ѿ������ظ�����
	 */

	public int insertAccountPreiod(AccountPreiod accountPreiod)
			throws Exception {

		int ret = 0;
		AccountPreiodDao aPreiodDao = (AccountPreiodDao) BeanLocator
				.getInstance().getBean(
						getAccountPreiodDao());
		AccountPreiod tempPreiod = aPreiodDao.findAccountPreiod(accountPreiod);
		if (null == tempPreiod) {
			aPreiodDao.insertAccountPreiod(accountPreiod);
		} else {
			ret = 1;
		}
		return ret;
	}
/**
 * ɾ����ʽ��Ϣ����ʱ��Ϣ����������Ϣ����
 * @param accountPreiod
 * @param isDelCurrent  �Ƿ�ͬʱɾ�����β���������
 * @throws Exception
 */
	public void deleteAccountPreiod(AccountPreiod accountPreiod,boolean isDelCurrent) throws Exception{
		AccountPreiodDao aPreiodDao = (AccountPreiodDao)BeanLocator.getInstance().getBean(getAccountPreiodDao());
		aPreiodDao.deleteAccountPreiod(accountPreiod);
		if(isDelCurrent){
			aPreiodDao.deleteAccountPreiodByID(accountPreiod.getSeqId());
		}
	}
	/**
	 * ����������Ϣ
	 * 
	 * @param accountPreiod
	 * @param staffId
	 * @param staffName
	 * @throws Exception
	 */
	public int updateAccountPreiod(AccountPreiod accountPreiod) throws Exception {
		int ret =0;
		int state = Integer.parseInt(accountPreiod.getState());
		AccountPreiodDao aPreiodDao = (AccountPreiodDao)BeanLocator.getInstance().getBean(getAccountPreiodDao());
		//String strLogType = null;
		switch (state){
		case 0:  //����ͨ��,��ɾ�������,ͬʱɾ������������
			     //ɾ�������ɻ�����Ϣ��ɾ��
			//aPreiodDao.deleteAccountPreiod(accountPreiod);
			aPreiodDao.insertAccountPreiod(accountPreiod);			
			//aPreiodDao.deleteAccountPreiodByID(accountPreiod.getSeqId());
			//strLogType = "��������";
			break;
		case 1://û���ύ�������޸�		
			aPreiodDao.updateAccountPreiod(accountPreiod);
			//strLogType = "�޸���ʱ����";
			break;
		case 2://�ύ�����޸�
			aPreiodDao.updateAccountPreiod(accountPreiod);
			//strLogType = "�ύ��ʱ����";
			break;
		case 3://������ͨ������ֻ�޸�״̬
			aPreiodDao.updateAccountPreiodByState(accountPreiod);
			//strLogType = "������ͨ������";
			break;
		case 4://�������ݣ����Ѿ������������������ͬʱ����ѱ��η���������״̬��Ϊ����
			//ɾ�����������ɻ�����Ϣ����
			//aPreiodDao.deleteAccountPreiod(accountPreiod);
			aPreiodDao.updateAccountPreiodByState(accountPreiod);
			//strLogType = "��������";
			break;
		case 5://��������ֻ�޸�״̬
			aPreiodDao.updateAccountPreiodByState(accountPreiod);
			//strLogType = "��������";
			break;
		case 6 ://�������ݣ�ֻ�޸�״̬
			
			accountPreiod.setState(new Integer(StateConstants.STATE_0).toString());
			
			aPreiodDao.updateAccountPreiodByState(accountPreiod);
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
//			log.setOperKey(accountPreiod.getBaseSeqId());
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
	 * @param accountPreiod
	 * @return
	 * @throws Exception
	 */
	public AccountPreiod getAccountPreiod(AccountPreiod accountPreiod)throws Exception{
		AccountPreiodDao aPreiodDao = (AccountPreiodDao)BeanLocator.getInstance().getBean(getAccountPreiodDao());
		return aPreiodDao.findAccountPreiod(accountPreiod);
	}
	
	/**
	 * ��ȡ�����б���Ϣ��һ���ͻ����ܻ��ѯ�����������Ϣ����Ҫ����������״̬
	 * @param accountPreiod
	 * @return
	 * @throws Exception
	 */
	public List<AccountPreiod> listAccountPreiod(AccountPreiod accountPreiod) throws Exception{
		AccountPreiodDao aPreiodDao = (AccountPreiodDao)BeanLocator.getInstance().getBean(getAccountPreiodDao());
		return aPreiodDao.listAccountPreiod(accountPreiod);
	}
}
