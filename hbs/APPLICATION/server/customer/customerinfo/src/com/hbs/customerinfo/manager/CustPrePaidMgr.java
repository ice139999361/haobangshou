/**
 * system £ºhbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;

import com.hbs.common.manager.baseinfo.PrePaidMgr;
import com.hbs.customerinfo.constants.CustInfoConstants;

public class CustPrePaidMgr extends PrePaidMgr {

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

}
