/**
 * system ：hbs
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
	private static final String ACCOUNT_PREIOD_STATE_0 ="0"; //正常状态
	
	public abstract String getAccountPreiodMgr();
	
	public abstract Logger getLogger();
	
	public abstract String getAccountPreiodType();
	
	/**
	 * 获取账期信息列表
	 * @return
	 */
	private  List<AccountPreiod> getAccountPreiodList() throws Exception{
		AccountPreiod aPreiod = new AccountPreiod();
		aPreiod.setState(ACCOUNT_PREIOD_STATE_0);
		AccountPreiodMgr accountMgr = (AccountPreiodMgr)BeanLocator.getInstance().getBean(getAccountPreiodMgr());
		return accountMgr.listAccountPreiod(aPreiod);
	}
	
	public void processReminder() {
		getLogger().info("扫描账期信息,类型为：" + getAccountPreiodType());
		try{
			List<AccountPreiod> listPreiod = getAccountPreiodList();
			if(null != listPreiod){
				getLogger().info("本次扫描需要处理的账期信息数量为! " + listPreiod.size());
				for(AccountPreiod preiod : listPreiod){
					processSingleReminder(preiod);
				}
			}else{
				getLogger().info("本次扫描没有需要处理的账期信息!");
			}
		}catch(Exception e){
			getLogger().error("处理账期对账日和结算日提醒错误，错误原因：" ,e);
		}
	}
	
	private void processSingleReminder(AccountPreiod preiod){
		getLogger().info("获取的账期信息为：" + preiod.toString());
		
	}
}
