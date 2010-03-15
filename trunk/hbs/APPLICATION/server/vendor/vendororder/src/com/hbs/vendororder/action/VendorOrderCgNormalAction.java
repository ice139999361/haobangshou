package com.hbs.vendororder.action;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class VendorOrderCgNormalAction extends VendorOrderBaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(VendorOrderBaseAction.class);

	@Override
	protected boolean getIsManager() {
		return false;
	}

	@Override
	public String getRoleName() {
		return "cgnormal";
	}

	
}
