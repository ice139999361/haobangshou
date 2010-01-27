/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendor.common.utils;



import com.hbs.common.manager.waittask.WaitTaskMgr;

import com.hbs.domain.waittask.pojo.WaitTaskInfo;

/**
 * @author Administrator
 *
 */
public class VendorWaitTaskUtils {
//	private static  Map<String,String> hmWaitTask = new HashMap<String,String>();
	
	
//	static {
//		//
//		hmWaitTask.put(CustOrderConstants.ORDER_STATE_50, "CUST_ORDER_001");
//		//
//		hmWaitTask.put(CustOrderConstants.ORDER_STATE_20, "CUST_ORDER_002");
//		//
//		hmWaitTask.put(CustOrderConstants.ORDER_STATE_30, "CUST_ORDER_008");
//	}
	
	public static void processCreateWaitTask(String TaskCfgId , String state , WaitTaskInfo waitTaskInfo){
		WaitTaskMgr.deleteWaitTask(waitTaskInfo.getBusinessKey());
		
		String cfgId = TaskCfgId;
//		if(null == cfgId){
//			cfgId = hmWaitTask.get(state);
//		}
		WaitTaskMgr.createWaitTask(cfgId, waitTaskInfo);
	}
	
	
	public static void processDeleteWaitTask(String waitBusinessKey){
		WaitTaskMgr.deleteWaitTask(waitBusinessKey);
	}
}
