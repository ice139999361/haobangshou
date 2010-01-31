/**
 * system £ºhbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendororder.manager.helper;

import com.hbs.common.utils.DateUtils;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.utils.AccountPreiodUtils;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.vendor.order.pojo.VendorOrder;

public class PeriodVendorOrderState implements VendorOrderState {

	/* (non-Javadoc)
	 * @see com.hbs.vendororder.manager.helper.VendorOrderState#getPeriod(com.hbs.domain.vendor.order.pojo.VendorOrder)
	 */
	public String getPeriod(VendorOrder order) throws Exception {
		String period = null;
		AccountPreiod aPreiod = AccountPreiodUtils.getVendorAccountPreiod(order.getCommCode());
		period = DateUtils.getDatePeriod(order.getCreateTime(), aPreiod.getPeriodStart(), aPreiod.getAccountPeriod());
		return period;
	}

	/* (non-Javadoc)
	 * @see com.hbs.vendororder.manager.helper.VendorOrderState#getCommitState(com.hbs.domain.vendor.order.pojo.VendorOrder)
	 */
	public String getCommitState(/*VendorOrder order*/) throws Exception {
		// TODO Auto-generated method stub
		return VendorOrderConstants.VENDOR_ORDER_STATE_04;
	}

}
