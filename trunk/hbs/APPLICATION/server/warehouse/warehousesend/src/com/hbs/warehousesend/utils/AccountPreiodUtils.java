/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.utils;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;

/**
 * @author Administrator
 *
 */
public class AccountPreiodUtils {
	private static final String STATE ="0";
	private static final String BEAN_NAME ="custAccountPreiodMgr";
	
	public static  AccountPreiod getCustomerAccountPreiod(String commCode)throws Exception{
		AccountPreiod aPreiod = new AccountPreiod();
		aPreiod.setCommCode(commCode);
		aPreiod.setState(STATE);
		AccountPreiodMgr aPreiodMgr = (AccountPreiodMgr)BeanLocator.getInstance().getBean(BEAN_NAME);
		aPreiod = aPreiodMgr.getAccountPreiod(aPreiod);
		return aPreiod;
	}
}
