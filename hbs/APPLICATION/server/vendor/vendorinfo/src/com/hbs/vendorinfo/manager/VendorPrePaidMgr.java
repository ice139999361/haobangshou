/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;

import org.apache.log4j.Logger;

import com.hbs.common.manager.baseinfo.PrePaidMgr;
import com.hbs.vendorinfo.constants.VendorInfoConstants;

/**
 * @author Administrator
 *
 */
public class VendorPrePaidMgr extends PrePaidMgr {

	private static final Logger logger = Logger.getLogger(VendorPrePaidMgr.class);
	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.PrePaidMgr#getLogDao()
	 */
	@Override
	public String getLogDao() {
		// TODO Auto-generated method stub
		return VendorInfoConstants.VENDOR_OPERLOGDAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.PrePaidMgr#getPrePaidDao()
	 */
	@Override
	public String getPrePaidDao() {
		// TODO Auto-generated method stub
		return VendorInfoConstants.VENDOR_PREPAIDINFODAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.PrePaidMgr#getLogger()
	 */
	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}

}
