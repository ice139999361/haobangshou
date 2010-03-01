/**
 * system ��hbs
 * desc: ���ڶ��������Ѵ��������
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.task;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;


public abstract class PeriodReminderTask {
	private static final String ACCOUNT_PREIOD_STATE_0 ="0"; //����״̬
	/**
	 * ����ʵ�֣��ǿͻ����ڻ��ǹ�Ӧ�����ڵĹ�����
	 * @return
	 */
	public abstract String getAccountPreiodMgr();
	/**
	 * ����ʵ�֣���ȡ��־��¼��
	 * @return
	 */
	public abstract Logger getLogger();
	/**
	 * ����ʵ�֣��ǿͻ����ڻ��ǹ�Ӧ�����ڣ���¼��־��
	 * @return
	 */
	public abstract String getAccountPreiodType();
	/**
	 * ��ȡ���ڵĶ�������
	 * @param commCode  �ͻ�/��Ӧ�̱���
	 * @param prePreiod �����ڰ�����������ǰ�Ķ���
	 * @return
	 */
	public abstract Integer getPreiodOrderCount(String commCode , String prePreiod)throws Exception;
	
	/**
	 * ����ʵ�֣��������������
	 * @param preiod ������Ϣ
	 * @param accountPreiod  ����
	 * @param aDay ������
	 */
	public abstract void processAWaitTask(AccountPreiod preiod , String accountPreiod , String aDay);
	/**
	 * ����ʵ�֣��������������
	 * @param preiod ������Ϣ
	 * @param accountPreiod  ����
	 * @param aDay ������
	 */
	public abstract void processSWaitTask(AccountPreiod preiod, String accountPreiod , String aDay);
	/**
	 * ��ȡ������Ϣ�б�
	 * @return
	 */
	private  List<AccountPreiod> getAccountPreiodList() throws Exception{
		AccountPreiod aPreiod = new AccountPreiod();
		aPreiod.setState(ACCOUNT_PREIOD_STATE_0);
		AccountPreiodMgr accountMgr = (AccountPreiodMgr)BeanLocator.getInstance().getBean(getAccountPreiodMgr());
		return accountMgr.listAccountPreiod(aPreiod);
	}
	
	public void processReminder() {
		getLogger().info("ɨ��������Ϣ,����Ϊ��" + getAccountPreiodType());
		try{
			List<AccountPreiod> listPreiod = getAccountPreiodList();
			if(null != listPreiod){
				getLogger().info("����ɨ����Ҫ�����������Ϣ����Ϊ! " + listPreiod.size());
				String strCurDate = DateUtils.getCurFormatDate(DateUtils.DATEFORMAT);
				getLogger().info("��ǰ����Ϊ��" + strCurDate);
				
				for(AccountPreiod preiod : listPreiod){
					processSingleReminder(preiod,strCurDate);
				}
			}else{
				getLogger().info("����ɨ��û����Ҫ�����������Ϣ!");
			}
		}catch(Exception e){
			getLogger().error("�������ڶ����պͽ��������Ѵ��󣬴���ԭ��" ,e);
		}
	}
	
	private void processSingleReminder(AccountPreiod preiod,String strCurDate) throws Exception{
		getLogger().info("��ȡ��������ϢΪ��" + preiod.toString());
		//��ȡ��ǰ���ڵ�ǰһ�����ڵĶ����պͽ����յ�������
		String strAReminderDay = DateUtils.getReminderDay(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod(), preiod.getAccounDay(), preiod.getReminderDay());
		String strSReminderDay = DateUtils.getReminderDay(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod(), preiod.getSettlementDay(), preiod.getReminderDay());
		//�жϵ�ǰ�����Ƿ���ڶ����պͽ�����		
		//���ڶ����գ����������ѣ���ѯǰһ�������Ƿ���ڶ���
		if(strAReminderDay.equals(strCurDate)){
			getLogger().info("��ǰ����Ϊ" + getAccountPreiodType() + preiod.getCommCode() + "�Ķ���������!");
			String strCurPreiod = DateUtils.getDatePeriod(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod());
			String strPrePreiod = DateUtils.getPrePeriod(strCurPreiod, preiod.getAccountPeriod());
			
			//��ѯ�������Ƿ���ڶ���
			Integer iCount = getPreiodOrderCount(preiod.getCommCode(), strPrePreiod);
			if(iCount > 0){
				getLogger().info("���ڴ��ڶ������������ѣ�");
				String aDay = DateUtils.getAccountDay(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod(), preiod.getAccounDay());
				processAWaitTask(preiod,strPrePreiod,aDay);
			}
		}
		//���ڽ����գ����������ѣ���ѯǰһ�������Ƿ���ڶ���
		if(strSReminderDay.equals(strCurDate)){
			getLogger().info("��ǰ����Ϊ" + getAccountPreiodType() + preiod.getCommCode() + "�Ľ���������!");
			String strCurPreiod = DateUtils.getDatePeriod(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod());
			String strPrePreiod = DateUtils.getPrePeriod(strCurPreiod, preiod.getAccountPeriod());
			
			//��ѯ�������Ƿ���ڶ���
			Integer iCount = getPreiodOrderCount(preiod.getCommCode(), strPrePreiod);
			if(iCount > 0){
				getLogger().info("���ڴ��ڶ������������ѣ�");
				String aDay = DateUtils.getAccountDay(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod(), preiod.getSettlementDay());
				processSWaitTask(preiod,strPrePreiod,aDay);
			}
		}
	}
}
