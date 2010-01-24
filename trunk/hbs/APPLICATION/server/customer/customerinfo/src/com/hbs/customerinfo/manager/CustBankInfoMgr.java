/**
 * system £ºhbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerinfo.manager;

import com.hbs.common.manager.baseinfo.BankInfoMgr;
import com.hbs.customerinfo.constants.CustInfoConstants;

public class CustBankInfoMgr extends BankInfoMgr {

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.BankInfoMgr#getBankInfoDao()
	 */
	@Override
	public String getBankInfoDao() {
		// TODO Auto-generated method stub
		return CustInfoConstants.CUSTOMERBANKINFODAO;
	}

	/* (non-Javadoc)
	 * @see com.hbs.common.manager.baseinfo.BankInfoMgr#getLogDao()
	 */
	@Override
	public String getLogDao() {
		// TODO Auto-generated method stub
		return CustInfoConstants.CUSTOMEROPERLOGDAO;
	}

}
