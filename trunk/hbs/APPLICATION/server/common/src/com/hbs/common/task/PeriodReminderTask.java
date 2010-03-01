/**
 * system ：hbs
 * desc: 账期订单的提醒处理基础类
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
	private static final String ACCOUNT_PREIOD_STATE_0 ="0"; //正常状态
	/**
	 * 子类实现，是客户账期还是供应商账期的管理类
	 * @return
	 */
	public abstract String getAccountPreiodMgr();
	/**
	 * 子类实现，获取日志记录类
	 * @return
	 */
	public abstract Logger getLogger();
	/**
	 * 子类实现，是客户账期还是供应商账期，记录日志用
	 * @return
	 */
	public abstract String getAccountPreiodType();
	/**
	 * 获取账期的订单数量
	 * @param commCode  客户/供应商编码
	 * @param prePreiod 此账期包括此账期以前的订单
	 * @return
	 */
	public abstract Integer getPreiodOrderCount(String commCode , String prePreiod)throws Exception;
	
	/**
	 * 子类实现，处理对账日提醒
	 * @param preiod 账期信息
	 * @param accountPreiod  账期
	 * @param aDay 对账日
	 */
	public abstract void processAWaitTask(AccountPreiod preiod , String accountPreiod , String aDay);
	/**
	 * 子类实现，处理结算日提醒
	 * @param preiod 账期信息
	 * @param accountPreiod  账期
	 * @param aDay 结算日
	 */
	public abstract void processSWaitTask(AccountPreiod preiod, String accountPreiod , String aDay);
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
				String strCurDate = DateUtils.getCurFormatDate(DateUtils.DATEFORMAT);
				getLogger().info("当前日期为：" + strCurDate);
				
				for(AccountPreiod preiod : listPreiod){
					processSingleReminder(preiod,strCurDate);
				}
			}else{
				getLogger().info("本次扫描没有需要处理的账期信息!");
			}
		}catch(Exception e){
			getLogger().error("处理账期对账日和结算日提醒错误，错误原因：" ,e);
		}
	}
	
	private void processSingleReminder(AccountPreiod preiod,String strCurDate) throws Exception{
		getLogger().info("获取的账期信息为：" + preiod.toString());
		//获取当前账期的前一个账期的对账日和结算日的提醒日
		String strAReminderDay = DateUtils.getReminderDay(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod(), preiod.getAccounDay(), preiod.getReminderDay());
		String strSReminderDay = DateUtils.getReminderDay(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod(), preiod.getSettlementDay(), preiod.getReminderDay());
		//判断当前日期是否等于对账日和结算日		
		//等于对账日，发对账提醒，查询前一个账期是否存在订单
		if(strAReminderDay.equals(strCurDate)){
			getLogger().info("当前日期为" + getAccountPreiodType() + preiod.getCommCode() + "的对账提醒日!");
			String strCurPreiod = DateUtils.getDatePeriod(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod());
			String strPrePreiod = DateUtils.getPrePeriod(strCurPreiod, preiod.getAccountPeriod());
			
			//查询该账期是否存在订单
			Integer iCount = getPreiodOrderCount(preiod.getCommCode(), strPrePreiod);
			if(iCount > 0){
				getLogger().info("账期存在订单，处理提醒！");
				String aDay = DateUtils.getAccountDay(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod(), preiod.getAccounDay());
				processAWaitTask(preiod,strPrePreiod,aDay);
			}
		}
		//等于结算日，发结算提醒，查询前一个账期是否存在订单
		if(strSReminderDay.equals(strCurDate)){
			getLogger().info("当前日期为" + getAccountPreiodType() + preiod.getCommCode() + "的结算提醒日!");
			String strCurPreiod = DateUtils.getDatePeriod(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod());
			String strPrePreiod = DateUtils.getPrePeriod(strCurPreiod, preiod.getAccountPeriod());
			
			//查询该账期是否存在订单
			Integer iCount = getPreiodOrderCount(preiod.getCommCode(), strPrePreiod);
			if(iCount > 0){
				getLogger().info("账期存在订单，处理提醒！");
				String aDay = DateUtils.getAccountDay(new Date(), preiod.getPeriodStart(), preiod.getAccountPeriod(), preiod.getSettlementDay());
				processSWaitTask(preiod,strPrePreiod,aDay);
			}
		}
	}
}
