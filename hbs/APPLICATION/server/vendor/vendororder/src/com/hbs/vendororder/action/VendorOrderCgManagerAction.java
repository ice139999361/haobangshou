/**
 * 
 */
package com.hbs.vendororder.action;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class VendorOrderCgManagerAction extends VendorOrderBaseAction  {

	@Override
	protected boolean getIsManager() {
		return true;
	}

	@Override
	public String getRoleName() {
		return "cgmanager";
	}

}
