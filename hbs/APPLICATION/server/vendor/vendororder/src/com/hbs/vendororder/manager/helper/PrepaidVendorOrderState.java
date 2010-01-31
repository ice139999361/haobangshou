/**
 * system £ºhbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendororder.manager.helper;

import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.vendororder.constants.VendorOrderConstants;

public class PrepaidVendorOrderState implements VendorOrderState {

	/* (non-Javadoc)
	 * @see com.hbs.vendororder.manager.helper.VendorOrderState#getPeriod(com.hbs.domain.vendor.order.pojo.VendorOrder)
	 */
	public String getPeriod(VendorOrder order) throws Exception {		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hbs.vendororder.manager.helper.VendorOrderState#getCommitState(com.hbs.domain.vendor.order.pojo.VendorOrder)
	 */
	public String getCommitState(/*VendorOrder order*/) throws Exception {
		// TODO Auto-generated method stub
		return VendorOrderConstants.VENDOR_ORDER_STATE_02;
	}

}
