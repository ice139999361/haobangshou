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
	 * ������Ŀͻ�������Ϣ����У�飬�ڲ�����checkSelectFields��
	 * @param partNo	�ͻ����Ϲ�ϵ��Ϣ
	 * @return ������Ϣ����ʽ��Map<�����ֶ�,������Ϣ>
	 */
	public static List<FieldErr> checkInputFields(CompanyPartNo partNo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(partNo == null)
			return list;
		
		String s;
		// DONE:���checkInputFields��������Ŀͻ���Ϣ����У��
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
			list.add(new FieldErr("CustPartNo","CustPartNoû����д"));
		} else {
			i
			
		}
		s = partNo.getPnDesc();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PnDesc","PnDescû����д"));
		}
		BigDecimal num = partNo.getPrice();
		if(num.abs().movePointRight(3).compareTo(BigDecimal.ONE) <= 0)
		{
			list.add(new FieldErr("Price","Priceû����д"));
		}
		s = partNo.getPartNo();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PartNo","PartNoû����д"));
		}
		
		List<FieldErr> list2 = checkSelectFields(partNo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
	}
	
	/**
	 * ���ѡ�����ݡ����ѡ��ֵ����дѡ���˵���ֶ�
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
			logger.info("checkSelectFields����", e);
		}
		
		return list;
	}
}
