/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;

import com.hbs.common.manager.baseinfo.ContactMgr;
import com.hbs.vendorinfo.constants.VendorInfoConstants;

/**
 * @author Administrator
 *
 */
public class VendorContactMgr extends ContactMgr {

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

}
