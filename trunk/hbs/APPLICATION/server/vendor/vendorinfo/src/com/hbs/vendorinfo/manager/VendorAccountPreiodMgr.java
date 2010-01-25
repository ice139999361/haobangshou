/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.vendorinfo.constants.VendorInfoConstants;


public class VendorAccountPreiodMgr extends AccountPreiodMgr {

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.AccountPreiodMgr#getAccountPreiodDao()
	 */
	@Override
	public String getAccountPreiodDao() {
		// TODO Auto-generated method stub
		return VendorInfoConstants.VENDOR_ACCOUNTPREIODDAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.AccountPreiodMgr#getLogDao()
	 */
	@Override
	public String getLogDao() {
		// TODO Auto-generated method stub
		return VendorInfoConstants.VENDOR_OPERLOGDAO;
	}

}
