/**
 * 
 */
package com.hbs.product.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.product.pojo.CompanyPartNo;
import com.hbs.domain.product.pojo.ProductClass;
import com.hbs.product.manager.ProductClassMgr;

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
	 * 对输入的本公司物料信息进行校验，内部调用checkSelectFields。
	 * @param partNo	客户物料关系信息
	 * @return 出错信息，格式：Map<出错字段,出错信息>
	 */
	public static List<FieldErr> checkInputFields(CompanyPartNo partNo)
	{
		if(partNo == null)
			return new ArrayList<FieldErr>();
		List<FieldErr> list = checkSelectFields(partNo);
		if(list == null)
			list = new ArrayList<FieldErr>();

		String s;
		// DONE:完成checkInputFields，对输入的信息进行校验
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
			list.add(new FieldErr("ClsCode","ClsCode没有填写"));
		}
		
		Date d = partNo.getCreateTime();
		if(d == null)
			partNo.setCreateTime(new Date());
		
		/*
		BigDecimal num = partNo.getPrice();
		if(num.abs().movePointRight(3).compareTo(BigDecimal.ONE) <= 0)
		{
			list.add(new FieldErr("Price","Price没有填写"));
		}
		*/
		
		return list;
	}
	
	/**
	 * 检查选择数据。检查选项值，填写选项的说明字段
	 * @param partNo
	 */
	public static List<FieldErr> checkSelectFields(CompanyPartNo partNo)
	{
		if(partNo == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		
		try
		{
			ProductClassMgr mgr = (ProductClassMgr)BeanLocator.getInstance().getBean("productClassMgr");
			ProductClass cls = mgr.getProductClass(partNo.getClsCode());
			if(cls == null) {
				partNo.setClsCode(null);
				list.add(new FieldErr("clsCode", "clsCode取值错误"));
			}
		}
		catch(Exception e)
		{
			logger.info("checkSelectFields出错", e);
		}
		
		return list;
	}
}
