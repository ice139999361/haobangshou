/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendorinfo.manager;

import org.apache.log4j.Logger;

import com.hbs.common.manager.baseinfo.BankInfoMgr;
import com.hbs.vendorinfo.constants.VendorInfoConstants;

/**
 * @author Administrator
 *
 */
public class VendorBankInfoMgr extends BankInfoMgr {

	private static final Logger logger = Logger.getLogger(VendorBankInfoMgr.class);
	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.BankInfoMgr#getBankInfoDao()
	 */
	@Override
	public String getBankInfoDao() {
		// TODO Auto-generated method stub
		return VendorInfoConstants.VENDOR_BANKINFODAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.BankInfoMgr#getLogDao()
	 */
	@Override
	public String getLogDao() {
		// TODO Auto-generated method stub
		return VendorInfoConstants.VENDOR_OPERLOGDAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.BankInfoMgr#getLogger()
	 */
	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}

}
