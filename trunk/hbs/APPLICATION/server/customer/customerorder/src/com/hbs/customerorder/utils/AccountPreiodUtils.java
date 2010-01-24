/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.utils;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;

/**
 * @author Administrator
 *
 */
public class AccountPreiodUtils {

	
	public static  AccountPreiod getCustAccountPreiod(String commCode)throws Exception{
		AccountPreiod aPreiod = new AccountPreiod();
		aPreiod.setCommCode(commCode);
		aPreiod.setState("0");
		AccountPreiodMgr aPreiodMgr = (AccountPreiodMgr)BeanLocator.getInstance().getBean("custAccountPreiodMgr");
		aPreiod = aPreiodMgr.getAccountPreiod(aPreiod);
		return aPreiod;
	}
}
