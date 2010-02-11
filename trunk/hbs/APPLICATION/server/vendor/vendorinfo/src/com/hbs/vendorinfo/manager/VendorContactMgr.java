/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;

import org.apache.log4j.Logger;

import com.hbs.common.manager.baseinfo.ContactMgr;
import com.hbs.vendorinfo.constants.VendorInfoConstants;

/**
 * @author Administrator
 *
 */
public class VendorContactMgr extends ContactMgr {

	private static final Logger logger = Logger.getLogger(VendorContactMgr.class);
	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.ContactMgr#getContactInfoDao()
	 */
	@Override
	public String getContactInfoDao() {
		// TODO Auto-generated method stub
		return VendorInfoConstants.VENDOR_CONTACTINFODAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.ContactMgr#getLogDao()
	 */
	@Override
	public String getLogDao() {
		// TODO Auto-generated method stub
		return VendorInfoConstants.VENDOR_OPERLOGDAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.ContactMgr#getLogger()
	 */
	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}

}
