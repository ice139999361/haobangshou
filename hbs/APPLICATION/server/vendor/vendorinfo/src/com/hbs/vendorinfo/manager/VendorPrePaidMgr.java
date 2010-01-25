/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;

import com.hbs.common.manager.baseinfo.PrePaidMgr;
import com.hbs.vendorinfo.constants.VendorInfoConstants;

/**
 * @author Administrator
 *
 */
public class VendorPrePaidMgr extends PrePaidMgr {

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.PrePaidMgr#getLogDao()
	 */
	@Override
	public String getLogDao() {
		// TODO Auto-generated method stub
		return VendorInfoConstants.VENDOR_PREPAIDINFODAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.PrePaidMgr#getPrePaidDao()
	 */
	@Override
	public String getPrePaidDao() {
		// TODO Auto-generated method stub
		return VendorInfoConstants.VENDOR_OPERLOGDAO;
	}

}
