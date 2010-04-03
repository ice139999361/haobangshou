/**
 * system ��hbs
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
				logger.info("�޵�¼��Ϣ���޷���ȡ���죡");
				setErrorReason("�޵�¼��Ϣ�����ȵ�¼��");
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
						//��ȡ������Ĵ���						
						List<WaitTaskInfo> waitInfo = WaitTaskMgr.listWaitTask(roleid, staffId, "0", null);
						if(null != waitInfo){
							waitMust.addAll(waitInfo);
						}
					}
					setResult("waitMust", waitMust);
					
					//��ȡ���������					
					for(String roleid : roleList){
						//��ȡ������Ĵ���						
						List<WaitTaskInfo> waitInfo = WaitTaskMgr.listWaitTask(roleid, staffId, "1", null);
						if(null != waitInfo){
							waitReminder.addAll(waitInfo);
						}
					}
					setResult("waitReminder", waitReminder);
				}				
			}
		}catch(Exception e){
			logger.info("��ȡ������Ϣ����" , e);
			setErrorReason("��ȡ������Ϣ����");
			return ERROR;
		}		
		return SUCCESS;
	}
}
