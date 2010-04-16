/**
 * 
 */
package com.hbs.vendororder.action;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class VendorOrderCwNormalAction extends VendorOrderBaseAction  {

	@Override
	protected boolean getIsManager() {
		return true;
	}

	@Override
	public String getRoleName() {
		return "cwnormal";
	}

}
