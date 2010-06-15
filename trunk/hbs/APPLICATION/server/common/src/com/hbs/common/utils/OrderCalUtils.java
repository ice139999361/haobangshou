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
			BigDecimal bContactFee = new BigDecimal(0.0);;
			if(contactFee != null){
				bContactFee = contactFee;
			}
			ret = (price.multiply(new BigDecimal(amount))).multiply(new BigDecimal(1).subtract(bContactFee));
			
			break;
		case 1://��˰����
			BigDecimal dPriceTax = new BigDecimal(0.0);
			if(null !=priceTax){//�жϼ۸��Ƿ�˰
				dPriceTax = priceTax;
				
			}
			BigDecimal dContactFee =new BigDecimal(0.0);
			if(null != contactFee){//���ں�ͬ��
				dContactFee = contactFee;
			}
			//if(dPriceTax == 0){//���۲���˰���Ǻ�˰���ף���Ҫ����˰��
				ret = (price.multiply(dPriceTax.add(new BigDecimal(1))).multiply(new BigDecimal(amount))).multiply(new BigDecimal(1).subtract(dContactFee));
			//}else{//�����Ѿ���˰
			//	ret = price.multiply(new BigDecimal(1)).subtract(new BigDecimal(dContactFee)).multiply(new BigDecimal(amount));
			//}
		}
		
		return ret;
	}
	
	
	public static void main(String [] args){
		//calOrderMoney(BigDecimal price , String isTax , BigDecimal priceTax ,BigDecimal taxRate ,BigDecimal contactFee, Integer amount)
		BigDecimal price = new BigDecimal(0.15678901);
		String isTax ="0";
		BigDecimal priceTax = new BigDecimal(0.17);
		BigDecimal contactFee = new BigDecimal(0.0);
		Integer amount= 100;
		
		BigDecimal ret = OrderCalUtils.calOrderMoney(price , isTax , priceTax ,null ,contactFee,  amount);
		
		System.out.println(ret.toString());
		
	}
}
