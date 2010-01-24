/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.utils;

import java.math.BigDecimal;

/**
 * @author Administrator
 *
 */
public class OrderCalUtils {

	
	public static BigDecimal calOrderMoney(BigDecimal price , String isTax , BigDecimal priceTax , Integer amount){
		BigDecimal ret =null;
		int iTax = Integer.parseInt(isTax);
		switch(iTax){
		case 0:
			ret = price.multiply(new BigDecimal(amount));
			break;
		case 1:
			double dPriceTax = priceTax.doubleValue();
			if(dPriceTax ==0){//单价不含税
				ret = price.multiply(priceTax.add(new BigDecimal(1))).multiply(new BigDecimal(amount));
			}else{
				ret = price.multiply(new BigDecimal(amount));
			}
		}
		
		return ret;
	}
}
