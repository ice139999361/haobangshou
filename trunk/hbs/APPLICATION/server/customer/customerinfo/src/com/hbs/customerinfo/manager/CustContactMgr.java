/**
 * system £ºhbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;

import com.hbs.common.manager.baseinfo.ContactMgr;
import com.hbs.customerinfo.constants.CustInfoConstants;

public class CustContactMgr extends ContactMgr {

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

}
