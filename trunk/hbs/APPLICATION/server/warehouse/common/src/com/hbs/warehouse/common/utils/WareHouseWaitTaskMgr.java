/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehouse.common.utils;

import com.hbs.common.manager.waittask.WaitTaskMgr;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;

/**
 * @author Administrator
 *
 */
public class WareHouseWaitTaskMgr {
	public static void processCreateWaitTask(String TaskCfgId , WaitTaskInfo waitTaskInfo){
		WaitTaskMgr.deleteWaitTask(waitTaskInfo.getBusinessKey());
		
		String cfgId = TaskCfgId;

		WaitTaskMgr.createWaitTask(cfgId, waitTaskInfo);
	}
	
	
	public static void processDeleteWaitTask(String waitBusinessKey){
		WaitTaskMgr.deleteWaitTask(waitBusinessKey);
	}
}
