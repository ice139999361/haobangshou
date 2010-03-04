/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hbs.common.manager.systemconfig.SystemConfigMgr;
import com.hbs.common.manager.waittask.WaitTaskMgr;
import com.hbs.common.utils.DateUtils;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.domain.common.pojo.SystemConfig;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;

/**
 * @author Administrator
 *
 */
public class CustOrderUtils {
	private static  Map<String,String> hmWaitTask = new HashMap<String,String>();
	
	
	static {
		//
		hmWaitTask.put(CustOrderConstants.ORDER_STATE_50, "CUST_ORDER_001");
		//
		hmWaitTask.put(CustOrderConstants.ORDER_STATE_20, "CUST_ORDER_002");
		//
		hmWaitTask.put(CustOrderConstants.ORDER_STATE_30, "CUST_ORDER_008");
		
		hmWaitTask.put(CustOrderConstants.ORDER_STATE_33, "CUST_ORDER_009");
		
		hmWaitTask.put(CustOrderConstants.ORDER_STATE_70, "CUST_ORDER_010");
		
		hmWaitTask.put(CustOrderConstants.ORDER_STATE_31, "CUST_ORDER_011");
	}
	
	public static void processCreateWaitTask(String TaskCfgId , String state , WaitTaskInfo waitTaskInfo){
		WaitTaskMgr.deleteWaitTask(waitTaskInfo.getBusinessKey());
		
		String cfgId = TaskCfgId;
		if(null == cfgId){
			cfgId = hmWaitTask.get(state);
		}
		WaitTaskMgr.createWaitTask(cfgId, waitTaskInfo);
	}
	
	
	public static void processDeleteWaitTask(String waitBusinessKey){
		WaitTaskMgr.deleteWaitTask(waitBusinessKey);
	}
	
	/**
	 * 获取提醒待办过期时间
	 * @return
	 */
	public static Date getExpireTime(){		
		String strL = "5";
		SystemConfig config = SystemConfigMgr.findSystemConfig("CUST_ORDER_REMINDER_DAY");
		if(null != config){
			strL = config.getConfigValue();
		}
		return DateUtils.getNeedDate(new Date(), strL, true);
	}
}
