/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;


public abstract class PeriodReminderTask {
	private static final String ACCOUNT_PREIOD_STATE_0 ="0"; //����״̬
	
	public abstract String getAccountPreiodMgr();
	
	public abstract Logger getLogger();
	
	public abstract String getAccountPreiodType();
	
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
				for(AccountPreiod preiod : listPreiod){
					processSingleReminder(preiod);
				}
			}else{
				getLogger().info("����ɨ��û����Ҫ�����������Ϣ!");
			}
		}catch(Exception e){
			getLogger().error("�������ڶ����պͽ��������Ѵ��󣬴���ԭ��" ,e);
		}
	}
	
	private void processSingleReminder(AccountPreiod preiod){
		getLogger().info("��ȡ��������ϢΪ��" + preiod.toString());
		
	}
}
