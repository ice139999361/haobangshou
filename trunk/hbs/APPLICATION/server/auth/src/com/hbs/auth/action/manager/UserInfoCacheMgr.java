
package com.hbs.auth.manager;

import org.apache.log4j.Logger;

import com.hbs.common.authfilter.User;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.pojo.Account;


public class UserInfoCacheMgr {
	
	private static final String ACCOUNT_MANAGER_NAME = "accountMgr";
	private static final String STAFF_MANAGER_NAME = "staffMgr";
	private static final String RESOURCE_MANAGER_NAME = "resourceMgr";
	private static final String ROLE_MANAGER_NAME = "roleMgr";
	private static final String ACTION_MANAGER_NAME = "actionMgr";
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
		AccountMgr accountMgr = (AccountMgr)BeanLocator.getInstance().getBean(ACCOUNT_MANAGER_NAME);
		Account account = accountMgr.findAccount(userAccount);
		if (account != null) {
			user.setStaffId(account.getStaffId());			
		}
		return user;
	}

}
