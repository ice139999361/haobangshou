/**
 * system ��hbs
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
	 * �����ܽ��
	 * @param price  �۸�
	 * @param isTax   1--��0--��  �Ƿ�˰����
	 * @param priceTax  ����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰
	 * @param taxRate  ˰��
	 * @param contactFee ��ͬ��
	 * @param amount  ��������
	 * @return
	 */
	public static BigDecimal calOrderMoney(BigDecimal price , String isTax , BigDecimal priceTax ,BigDecimal taxRate ,BigDecimal contactFee, Integer amount){
		BigDecimal ret =null;
		int iTax = Integer.parseInt(isTax);
		switch(iTax){
		case 0://����˰����
			ret = price.multiply(new BigDecimal(amount));
			break;
		case 1://��˰����
			double dPriceTax = 0.0;
			if(null !=priceTax){//�жϼ۸��Ƿ�˰
				dPriceTax = priceTax.doubleValue();
			}
			double dContactFee =0.0;
			if(null != contactFee){//���ں�ͬ��
				dContactFee = contactFee.doubleValue();
			}
			if(dPriceTax == 0){//���۲���˰���Ǻ�˰���ף���Ҫ����˰��
				ret = price.multiply(taxRate.add(new BigDecimal(1)).subtract(new BigDecimal(dContactFee))).multiply(new BigDecimal(amount));
			}else{//�����Ѿ���˰
				ret = price.multiply(new BigDecimal(1)).subtract(new BigDecimal(dContactFee)).multiply(new BigDecimal(amount));
			}
		}
		
		return ret;
	}
}
