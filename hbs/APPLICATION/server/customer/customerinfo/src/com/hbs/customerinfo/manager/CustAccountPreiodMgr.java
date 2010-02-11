/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;


import org.apache.log4j.Logger;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.customerinfo.constants.CustInfoConstants;


/**
 * @author Administrator
 *
 */
public class CustAccountPreiodMgr extends AccountPreiodMgr {

	private static final Logger logger = Logger.getLogger(CustAccountPreiodMgr.class);
	/* (non-Javadoc)
	 * @see com.hbs.customer.common.manager.AccountPreiodMgr#getAccountPreiodDao()
	 */
	@Override
	public String getAccountPreiodDao() {
		// TODO Auto-generated method stub
		return CustInfoConstants.CUSTOMERACCOUNTPREIODDAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.customer.common.manager.AccountPreiodMgr#getLogDao()
	 */
	@Override
	public String getLogDao() {
		// TODO Auto-generated method stub
		return CustInfoConstants.CUSTOMEROPERLOGDAO;
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
