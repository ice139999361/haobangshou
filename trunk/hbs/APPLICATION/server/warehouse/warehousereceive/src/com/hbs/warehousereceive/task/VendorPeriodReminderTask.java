/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousereceive.task;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.task.PeriodReminderTask;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;

import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseWaitTaskMgr;
import com.hbs.warehousereceive.manager.WareHouseRecDetailMgr;


/**
 * @author Administrator
 *
 */
public class VendorPeriodReminderTask extends PeriodReminderTask {

	private static final String VENDOR_ACCOUNTPREIODMGR ="vendorAccountPreiodMgr";
	private static final String VENDOR_ACCOUNT_PREIOD="供应商账期";
	private static final String LESS_PERIOD ="lessPeriod";
	private static final Logger logger = Logger.getLogger(VendorPeriodReminderTask.class);
	/* (non-Javadoc)
	 * @see com.hbs.common.task.PeriodReminderTask#getAccountPreiodMgr()
	 */
	@Override
	public String getAccountPreiodMgr() {
		// TODO Auto-generated method stub
		return VENDOR_ACCOUNTPREIODMGR;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.task.PeriodReminderTask#getAccountPreiodType()
	 */
	@Override
	public String getAccountPreiodType() {
		// TODO Auto-generated method stub
		return VENDOR_ACCOUNT_PREIOD;
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
	public Integer getPreiodOrderCount(String commCode, String prePreiod)
			throws Exception {
		WareHouseRecDetailMgr wrMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
		WarehouseRecDetail detail = new WarehouseRecDetail();
		detail.setVendorCode(commCode);
		detail.setSettlementType("1");
		detail.setFinanceState(WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_0);
		detail.setActiveState(WareHouseConstants.WAREHOUSE_REC_ACTIVE);
		detail.setField(LESS_PERIOD, prePreiod);
		Integer iRet = wrMgr.getWarehouseRecDetailCount(detail);
		return iRet;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.task.PeriodReminderTask#processAWaitTask(com.hbs.domain.common.pojo.baseinfo.AccountPreiod, java.lang.String, java.lang.String)
	 */
	@Override
	public void processAWaitTask(AccountPreiod preiod, String accountPreiod,
			String day) {
		processWaitTask( preiod,  accountPreiod ,  day, "VENDOR_REMINDER_001");

	}

	/* (non-Javadoc)
	 * @see com.hbs.common.task.PeriodReminderTask#processSWaitTask(com.hbs.domain.common.pojo.baseinfo.AccountPreiod, java.lang.String, java.lang.String)
	 */
	@Override
	public void processSWaitTask(AccountPreiod preiod, String accountPreiod,
			String day) {
		processWaitTask( preiod,  accountPreiod ,  day, "VENDOR_REMINDER_001");

	}

	private void processWaitTask(AccountPreiod preiod, String accountPreiod , String aDay,String cfgId){
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$custCode", preiod.getCommCode());
		hmParam.put("$period", accountPreiod);
		hmParam.put("$accountDay", aDay);
		waitTaskInfo.setHmParam(hmParam);
		waitTaskInfo.setBusinessKey(preiod.getCommCode()+"提醒日-"+ cfgId);
		WareHouseWaitTaskMgr.processCreateWaitTask(cfgId, waitTaskInfo);
		
	}
}
