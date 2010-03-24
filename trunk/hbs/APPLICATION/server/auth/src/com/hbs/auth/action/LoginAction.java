package com.hbs.auth.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.authfilter.User;
import com.hbs.domain.auth.pojo.Account;
import com.hbs.auth.contants.AuthConstants;
import com.hbs.auth.manager.AccountMgr;
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
					User user = userInfoCacheMgr.cacheUserInfo(userAccount);
					getSession().setAttribute("user", user);
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
	
	/**
	 * ÐÞ¸Ä×Ô¼ºµÄÃÜÂë
	 * @action.input oldPassword	newPassword	rePassword
	 * @author xyf
	 * @return
	 */
	public String doChangePassword() {
		try {
			String oldPassword = this.getHttpServletRequest().getParameter("oldPassword");
			String newPassword = this.getHttpServletRequest().getParameter("newPassword");
			String rePassword = this.getHttpServletRequest().getParameter("rePassword");
			
			if(!StringUtils.equals(newPassword, rePassword)){
				logger.error("ÃÜÂë²»Ò»ÖÂ");
				setErrorReason("ÃÜÂë²»Ò»ÖÂ");
	            return ERROR;
			}
			AccountMgr mgr = (AccountMgr)getBean(AuthConstants.ACCOUNT_MANAGER_NAME);
			Account account = mgr.findAccountById(getLoginStaff().getStaffId().toString());
			if(account == null){
				logger.error("µÇÂ¼´íÎó");
				setErrorReason("µÇÂ¼´íÎó");
	            return ERROR;
			}
			if(!StringUtils.equals(account.getPassword(), LoginMgr.transformPassword(oldPassword))){
				logger.error("ÃÜÂë´íÎó");
				setErrorReason("ÃÜÂë´íÎó");
	            return ERROR;
			}
			// Ö»¸üÐÂpassword
			account = new Account();
			account.setStaffId(getLoginStaff().getStaffId());
			account.setPassword(newPassword);
			mgr.updateAccount(account);
			
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doChangePassword", e);
			setErrorReason("ÄÚ²¿´íÎó");
            return ERROR;
		}
	}
}
