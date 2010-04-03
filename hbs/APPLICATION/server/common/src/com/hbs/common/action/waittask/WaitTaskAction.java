/**
 * system ：hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.action.waittask;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.manager.waittask.WaitTaskMgr;
import com.hbs.domain.auth.pojo.Staff;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;

@SuppressWarnings("serial")
public class WaitTaskAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(WaitTaskAction.class);
	public String doGetWaitTaskInfo() {
		try{
			Staff staff = getLoginStaff();
			if(null == staff){
				logger.info("无登录信息，无法获取待办！");
				setErrorReason("无登录信息，请先登录！");
				return ERROR;
			}else{
				String staffId = staff.getStaffId().toString();
				List<String> roleList = getLoginStaffRole();
				List<WaitTaskInfo> waitMust = new ArrayList<WaitTaskInfo>();
				List<WaitTaskInfo> waitReminder = new ArrayList<WaitTaskInfo>();
				if(roleList == null){
					roleList = new ArrayList<String>();
					roleList.add("1");
				}
				if(null != roleList){
					for(String roleid : roleList){
						//获取待处理的待办						
						List<WaitTaskInfo> waitInfo = WaitTaskMgr.listWaitTask(roleid, staffId, "0", null);
						if(null != waitInfo){
							waitMust.addAll(waitInfo);
						}
					}
					setResult("waitMust", waitMust);
					
					//获取提醒类待办					
					for(String roleid : roleList){
						//获取待处理的待办						
						List<WaitTaskInfo> waitInfo = WaitTaskMgr.listWaitTask(roleid, staffId, "1", null);
						if(null != waitInfo){
							waitReminder.addAll(waitInfo);
						}
					}
					setResult("waitReminder", waitReminder);
				}				
			}
		}catch(Exception e){
			logger.info("获取待办信息错误！" , e);
			setErrorReason("获取待办信息错误！");
			return ERROR;
		}		
		return SUCCESS;
	}
}
