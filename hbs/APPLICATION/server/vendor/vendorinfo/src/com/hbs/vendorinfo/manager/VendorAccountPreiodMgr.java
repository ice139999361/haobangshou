/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;

import org.apache.log4j.Logger;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.vendorinfo.constants.VendorInfoConstants;


public class VendorAccountPreiodMgr extends AccountPreiodMgr {

	private static final Logger logger = Logger.getLogger(VendorAccountPreiodMgr.class);
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

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.AccountPreiodMgr#getLogger()
	 */
	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}

}
