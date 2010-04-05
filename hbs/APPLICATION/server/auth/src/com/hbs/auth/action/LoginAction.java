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
				setErrorReason("登录的用户名或密码不能为空!");
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
					setErrorReason("请输入正确的用户名和密码！");
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
	 * 修改自己的密码
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
				logger.error("密码不一致");
				setErrorReason("密码不一致");
	            return ERROR;
			}
			AccountMgr mgr = (AccountMgr)getBean(AuthConstants.ACCOUNT_MANAGER_NAME);
			Account account = mgr.findAccountById(getLoginStaff().getStaffId().toString());
			if(account == null){
				logger.error("登录错误");
				setErrorReason("登录错误");
	            return ERROR;
			}
			if(!StringUtils.equals(account.getPassword(), LoginMgr.transformPassword(oldPassword))){
				logger.error("密码错误");
				setErrorReason("密码错误");
	            return ERROR;
			}
			// 只更新password
			account = new Account();
			account.setStaffId(getLoginStaff().getStaffId());
			account.setPassword(newPassword);
			mgr.updateAccount(account);
			
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doChangePassword", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}
}
