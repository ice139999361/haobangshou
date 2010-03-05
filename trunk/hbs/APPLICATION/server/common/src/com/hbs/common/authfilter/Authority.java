package com.hbs.common.authfilter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hbs.customerinfo.manager.CustAccountPreiodMgr;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * @author tony.chen
 *
 */

public class Authority extends AbstractInterceptor {
	
	private static final Logger logger = Logger.getLogger(Authority.class);
	
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		User user = (User)session.get("user");		
		if (user != null) {			
			//user already logged in then check the action names authoritied to the user.
			HashMap<String,String> actionNames = user.getActionNames();
			//String requestActionName = invocation.getAction().getClass().getName();
			HttpServletRequest request = ServletActionContext.getRequest();
			String requestActionName = request.getRequestURI();
			if (logger.isDebugEnabled()) {
				logger.debug("user:" + user.getUserAccount() + " is requesting action:" + requestActionName);
			}
			if (actionNames.get(requestActionName) != null) {
				return invocation.invoke();
			} else {
				return "login";
			}
		} else { //the use does not login
			if (logger.isDebugEnabled()) {
				logger.debug("Refused user:" + user.getUserAccount());
			}
			return "login";
		}
	}
}