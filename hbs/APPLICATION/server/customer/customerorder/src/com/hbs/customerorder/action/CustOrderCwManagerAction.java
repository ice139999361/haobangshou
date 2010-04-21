/**
 * 
 */
package com.hbs.customerorder.action;

import org.apache.log4j.Logger;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustOrderCwManagerAction extends CustOrderBaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(CustOrderCwManagerAction.class);

	public static final String roleName = "cwmanager";

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public String getRoleName() {
		return roleName;
	}

}
