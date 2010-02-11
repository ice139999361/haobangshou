/**
 * system £ºhbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;

import org.apache.log4j.Logger;

import com.hbs.common.manager.baseinfo.ContactMgr;
import com.hbs.customerinfo.constants.CustInfoConstants;

public class CustContactMgr extends ContactMgr {

	private static final Logger logger = Logger.getLogger(CustContactMgr.class);
	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.ContactMgr#getContactInfoDao()
	 */
	@Override
	public String getContactInfoDao() {
		// TODO Auto-generated method stub
		return CustInfoConstants.CUSTOMERCONTACTINFODAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.ContactMgr#getLogDao()
	 */
	@Override
	public String getLogDao() {
		// TODO Auto-generated method stub
		return CustInfoConstants.CUSTOMEROPERLOGDAO;
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
