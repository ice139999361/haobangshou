/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.task;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.task.PeriodReminderTask;

import com.hbs.common.utils.ExpireTimeUtil;

import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseWaitTaskMgr;
import com.hbs.warehousesend.manager.WareHouseSendDetailMgr;

/**
 * @author Administrator
 *
 */
public class CustPeriodReminderTask extends PeriodReminderTask {

	private static final String CUSTACCOUNTPREIODMGR ="custAccountPreiodMgr";
	private static final String CUST_ACCOUNT_PREIOD="客户账期";
	private static final String LESS_PERIOD ="lessPeriod";
	
	private static final Logger logger = Logger.getLogger(CustPeriodReminderTask.class);
	/* (non-Javadoc)
	 * @see com.hbs.common.task.PeriodReminderTask#getAccountPreiodMgr()
	 */
	@Override
	public String getAccountPreiodMgr() {
		// TODO Auto-generated method stub
		return CUSTACCOUNTPREIODMGR;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.task.PeriodReminderTask#getAccountPreiodType()
	 */
	@Override
	public String getAccountPreiodType() {
		// TODO Auto-generated method stub
		return CUST_ACCOUNT_PREIOD;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.task.PeriodReminderTask#getLogger()
	 */
	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.task.PeriodReminderTask#getPreiodOrderCount(java.lang.String, java.lang.String)
	 */
	@Override
	public Integer getPreiodOrderCount(String commCode, String prePreiod) throws Exception{
		// TODO Auto-generated method stub
		WareHouseSendDetailMgr wsMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
		WarehouseSendDetail detail = new WarehouseSendDetail();
		detail.setCustCode(commCode);
		detail.setSettlementType("1");
		detail.setFinanceState(WareHouseConstants.WAREHOUSE_SEND_FINANCE_STATE_0);
		detail.setActiveState(WareHouseConstants.WAREHOUSE_SEND_ACTIVE);
		
		detail.setField(LESS_PERIOD, prePreiod);
		Integer iRet = wsMgr.listWarehouseSendDetailCount(detail);
		return iRet;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.task.PeriodReminderTask#processAWaitTask(com.hbs.domain.common.pojo.baseinfo.AccountPreiod)
	 */
	@Override
	public void processAWaitTask(AccountPreiod preiod , String accountPreiod , String aDay) {
		// TODO Auto-generated method stub
		processWaitTask( preiod,  accountPreiod ,  aDay, "CUST_REMINDER_001");
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.task.PeriodReminderTask#processSWaitTask(com.hbs.domain.common.pojo.baseinfo.AccountPreiod)
	 */
	@Override
	public void processSWaitTask(AccountPreiod preiod, String accountPreiod , String aDay) {
		// TODO Auto-generated method stub
		processWaitTask( preiod,  accountPreiod ,  aDay, "CUST_REMINDER_002");
	}

	private void processWaitTask(AccountPreiod preiod, String accountPreiod , String aDay,String cfgId){
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$custCode", preiod.getCommCode());
		hmParam.put("$period", accountPreiod);
		hmParam.put("$accountDay", aDay);
		waitTaskInfo.setHmParam(hmParam);
		waitTaskInfo.setExpireTime(ExpireTimeUtil.getExpireTime("CUST_REMINDER_DAY"));
		waitTaskInfo.setBusinessKey(preiod.getCommCode()+"提醒日-"+ cfgId);
		WareHouseWaitTaskMgr.processCreateWaitTask(cfgId, waitTaskInfo);
		
	}
	
	
}
