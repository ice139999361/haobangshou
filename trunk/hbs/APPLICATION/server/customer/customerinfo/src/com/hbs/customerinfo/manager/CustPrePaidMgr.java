/**
 * system £ºhbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;

import org.apache.log4j.Logger;

import com.hbs.common.manager.baseinfo.PrePaidMgr;
import com.hbs.customerinfo.constants.CustInfoConstants;

public class CustPrePaidMgr extends PrePaidMgr {

	private static final Logger logger = Logger.getLogger(CustPrePaidMgr.class);
	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.PrePaidMgr#getLogDao()
	 */
	@Override
	public String getLogDao() {
		// TODO Auto-generated method stub
		return CustInfoConstants.CUSTOMEROPERLOGDAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.PrePaidMgr#getPrePaidDao()
	 */
	@Override
	public String getPrePaidDao() {
		// TODO Auto-generated method stub
		return CustInfoConstants.CUSTOMERPREPAIDINFODAO;
		
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
