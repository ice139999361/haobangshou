package com.hbs.auth.action;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.auth.contants.AuthConstants;
import com.hbs.auth.manager.LoginMgr;
import com.hbs.auth.manager.UserInfoCacheMgr;



/**
 * 
 * @author tony.chen
 *
 */

@SuppressWarnings("serial")
public class LoginAction extends BaseAction {
	

    private static final Logger logger = Logger.getLogger(LoginAction.class);

   
	public String doLogin() {
		try {
			String userAccount = this.getHttpServletRequest().getParameter("userAccount");
			String password = this.getHttpServletRequest().getParameter("password");
			if(userAccount == null || password == null ||
					userAccount.trim().length() <= 0 || password.trim().length() <= 0 ) {
				if (logger.isInfoEnabled()) {
					logger.info("Login name / password can not be null!");
				}
				setErrorReason("login name/password can not be null");
				return ERROR;
			} else {
				LoginMgr loginMgr = (LoginMgr)getBean(AuthConstants.LOGIN_MANAGER_NAME);
				if (loginMgr.validateUser(userAccount, password)) {
					UserInfoCacheMgr userInfoCacheMgr = (UserInfoCacheMgr)getBean(AuthConstants.USER_INFO_CACHE_MANAGER_NAME);
					userInfoCacheMgr.cacheUserInfo(userAccount);
					return SUCCESS;
				} else {
					logger.error("invalidate user:" + userAccount);
					setErrorReason("Please input correct user name/password.");
					return ERROR;
				}
			}			
		} catch(Exception e) {
			logger.error("catch Exception in doLogin", e);
			setErrorReason("Global Error");
            return ERROR;
		}
	}
	
}
