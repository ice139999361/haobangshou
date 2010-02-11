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
	 * ������ı���˾������Ϣ����У�飬�ڲ�����checkSelectFields��
	 * @param partNo	�ͻ����Ϲ�ϵ��Ϣ
	 * @return ������Ϣ����ʽ��Map<�����ֶ�,������Ϣ>
	 */
	public static List<FieldErr> checkInputFields(CompanyPartNo partNo)
	{
		if(partNo == null)
			return new ArrayList<FieldErr>();
		List<FieldErr> list = checkSelectFields(partNo);
		if(list == null)
			list = new ArrayList<FieldErr>();

		String s;
		// DONE:���checkInputFields�����������Ϣ����У��
		s = partNo.getPartNo();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PartNo","PartNoû����д"));
		}
		s = partNo.getPnDesc();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PnDesc","PnDescû����д"));
		}
		s = partNo.getClsCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("ClsCode","ClsCodeû����д"));
		}
		
		Date d = partNo.getCreateTime();
		if(d == null)
			partNo.setCreateTime(new Date());
		
		/*
		BigDecimal num = partNo.getPrice();
		if(num.abs().movePointRight(3).compareTo(BigDecimal.ONE) <= 0)
		{
			list.add(new FieldErr("Price","Priceû����д"));
		}
		*/
		
		return list;
	}
	
	/**
	 * ���ѡ�����ݡ����ѡ��ֵ����дѡ���˵���ֶ�
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
				list.add(new FieldErr("clsCode", "clsCodeȡֵ����"));
			}
		}
		catch(Exception e)
		{
			logger.info("checkSelectFields����", e);
		}
		
		return list;
	}
}
