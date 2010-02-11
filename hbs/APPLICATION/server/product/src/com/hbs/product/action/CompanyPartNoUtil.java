/**
 * 
 */
package com.hbs.product.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.domain.product.pojo.CompanyPartNo;

/**
 * @author xyf
 *
 */
public class CompanyPartNoUtil {
	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CompanyPartNoUtil.class);
	
	/**
	 * 对输入的客户物料信息进行校验，内部调用checkSelectFields。
	 * @param partNo	客户物料关系信息
	 * @return 出错信息，格式：Map<出错字段,出错信息>
	 */
	public static List<FieldErr> checkInputFields(CompanyPartNo partNo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(partNo == null)
			return list;
		
		String s;
		// DONE:完成checkInputFields，对输入的客户信息进行校验
		s = partNo.getPartNo();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PartNo","PartNo没有填写"));
		}
		s = partNo.getPnDesc();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PnDesc","PnDesc没有填写"));
		}
		s = partNo.getClsCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CustPartNo","CustPartNo没有填写"));
		} else {
			i
			
		}
		s = partNo.getPnDesc();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PnDesc","PnDesc没有填写"));
		}
		BigDecimal num = partNo.getPrice();
		if(num.abs().movePointRight(3).compareTo(BigDecimal.ONE) <= 0)
		{
			list.add(new FieldErr("Price","Price没有填写"));
		}
		s = partNo.getPartNo();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PartNo","PartNo没有填写"));
		}
		
		List<FieldErr> list2 = checkSelectFields(partNo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
	}
	
	/**
	 * 检查选择数据。检查选项值，填写选项的说明字段
	 * @param partNo
	 */
	public static List<FieldErr> checkSelectFields(CompanyPartNoUtil partNo)
	{
		if(partNo == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		try
		{
			String s;
			
			int i;
		}
		catch(Exception e)
		{
			logger.info("checkSelectFields出错", e);
		}
		
		return list;
	}
}
