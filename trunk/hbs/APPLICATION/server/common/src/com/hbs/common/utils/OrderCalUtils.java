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

	/**
	 * 计算总金额
	 * @param price  价格
	 * @param isTax   1--是0--否  是否含税交易
	 * @param priceTax  单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税
	 * @param taxRate  税率
	 * @param contactFee 合同费
	 * @param amount  物料数量
	 * @return
	 */
	public static BigDecimal calOrderMoney(BigDecimal price , String isTax , BigDecimal priceTax ,BigDecimal taxRate ,BigDecimal contactFee, Integer amount){
		BigDecimal ret =null;
		int iTax = Integer.parseInt(isTax);
		switch(iTax){
		case 0://不含税交易
			ret = price.multiply(new BigDecimal(amount));
			break;
		case 1://含税交易
			double dPriceTax = 0.0;
			if(null !=priceTax){//判断价格是否含税
				dPriceTax = priceTax.doubleValue();
			}
			double dContactFee =0.0;
			if(null != contactFee){//存在合同费
				dContactFee = contactFee.doubleValue();
			}
			if(dPriceTax == 0){//单价不含税，是含税交易，需要加上税率
				ret = price.multiply(taxRate.add(new BigDecimal(1)).subtract(new BigDecimal(dContactFee))).multiply(new BigDecimal(amount));
			}else{//单价已经含税
				ret = price.multiply(new BigDecimal(1)).subtract(new BigDecimal(dContactFee)).multiply(new BigDecimal(amount));
			}
		}
		
		return ret;
	}
}
