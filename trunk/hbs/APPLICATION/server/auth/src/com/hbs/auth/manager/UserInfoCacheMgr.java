
package com.hbs.auth.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.authfilter.User;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.pojo.Account;
import com.hbs.domain.auth.pojo.Action;
import com.hbs.domain.auth.pojo.Resource;
import com.hbs.domain.auth.pojo.RoleResource;
import com.hbs.domain.auth.pojo.Staff;
import com.hbs.domain.auth.pojo.UserRole;
import com.hbs.auth.contants.AuthConstants;

public class UserInfoCacheMgr {
	
	private static final Logger logger = Logger.getLogger(UserInfoCacheMgr.class);
	
	
	/**
	 * cache the user info to session
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	public User cacheUserInfo(String userAccount) throws Exception{		
		User user = new User();
		if (logger.isDebugEnabled()) {
			logger.debug("invoking method cacheUserInfo(String userAccount),parameters: userAccount=" + userAccount);
		}
		//cache staff infomation
		AccountMgr accountMgr = (AccountMgr)BeanLocator.getInstance().getBean(AuthConstants.ACCOUNT_MANAGER_NAME);
		Account account = accountMgr.findAccount(userAccount);
		Integer staffId = account.getStaffId();
		user.setStaffId(staffId);
		user.setUserAccount(account.getAccount());
		StaffMgr staffMgr = (StaffMgr)BeanLocator.getInstance().getBean(AuthConstants.STAFF_MANAGER_NAME);
		Staff staff = staffMgr.findStaff(staffId.toString());
		user.setStarffName(staff.getStaffName());
		//cache buttons and action names
		StaffRoleMgr staffRoleMgr = (StaffRoleMgr)BeanLocator.getInstance().getBean(AuthConstants.STAFF_ROLE_MANAGER_NAME);
		UserRole userRole = staffRoleMgr.findUserRole(staffId.toString());
		Integer roleId = userRole.getRoleId();		
		RoleResourceMgr roleResourceMgr = (RoleResourceMgr)BeanLocator.getInstance().getBean(AuthConstants.ROLE_RESOURCE_MANAGER_NAME);
		RoleResource rr = new RoleResource();		
		rr.setRoleId(roleId);		
		List<RoleResource> roleResources = roleResourceMgr.listRoleResource(rr);
		HashMap<String, String> actionNames = new HashMap<String,String>(64);
		HashMap<String,ArrayList<String>> resourceButtons = new HashMap<String,ArrayList<String>>(64);
		for (RoleResource roleResource : roleResources) {
			String resourceId = roleResource.getResourceId().toString();
			String operations = roleResource.getOperations();
			String[] tempButtons = operations.split(",");			
			ArrayList<String> buttons = new ArrayList<String>();
			for (String button : tempButtons) {
				buttons.add(button);
			}
			//cache buttons
			resourceButtons.put(resourceId, buttons);
			//cache action names
			ResourceMgr resourceMgr = (ResourceMgr)BeanLocator.getInstance().getBean(AuthConstants.RESOURCE_MANAGER_NAME);
			Resource resource = resourceMgr.findResource(resourceId);
			Integer actionsId = resource.getActionsId();
			ActionMgr actionMgr = (ActionMgr)BeanLocator.getInstance().getBean(AuthConstants.ACTION_MANAGER_NAME);
			Action tempAction = new Action();
			tempAction.setActionsId(actionsId);
			List<Action> actions = actionMgr.listAction(tempAction);
			for (Action action : actions) {
				String actionName = action.getActionName();
				actionNames.put(actionName, actionName);
			}
		}	
		user.setActionNames(actionNames);
		user.setResourceButtons(resourceButtons);
		return user;
	}

}
