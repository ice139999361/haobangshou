/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;


import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.customerinfo.constants.CustInfoConstants;


/**
 * @author Administrator
 *
 */
public class CustAccountPreiodMgr extends AccountPreiodMgr {

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


	


	
}
