
package com.hbs.auth.manager;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.pojo.Account;
import com.hbs.auth.contants.AuthConstants;


public class LoginMgr {
	
	private static final Logger logger = Logger.getLogger(LoginMgr.class);
	
	
	/**
	 * validate user according to the account and password.
	 * 
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean validateUser(String userAccount, String password) throws Exception{
		boolean ret = true;
		if (logger.isDebugEnabled()) {
			logger.debug("invoking method validateUser(String userAccount, String password),parameters: userAccount=" + userAccount + " password=" + password);
		}
		//validate the login information
		AccountMgr accountMgr = (AccountMgr)BeanLocator.getInstance().getBean(AuthConstants.ACCOUNT_MANAGER_NAME);
		Account account = accountMgr.findAccount(userAccount);
		if (account == null) { 
			ret = false;
		} else {
			if (!account.getPassword().equals(password)) {
				ret = false;
			} 	
		}
		if (ret) {
			if (logger.isDebugEnabled()) {
				logger.debug("Welcome, " + userAccount);
			}
		}
		return ret;
	}

}
