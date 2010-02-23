/**
 * 
 */
package com.hbs.customerinfo.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.product.pojo.CompanyPartNo;
import com.hbs.product.manager.CompanyPartNoMgr;

/**
 * Action�ж�CustPartNoInfo��һЩͨ�ô�������
 * @author xyf
 *
 */
public class CustPartNoInfoUtil {
	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustPartNoInfoUtil.class);
	
    /**
	 * �ж��Ƿ���д��key�ֶΡ�custPartNoInfo.SeqId �� (custPartNoInfo.commCode + custPartNoInfo.state)
	 * @param custPartNoInfo
	 * @return
	 */
	public static boolean checkKeyFields(CustPartNoInfo custPartNoInfo)
	{
		try
		{
			if(custPartNoInfo == null)
				return false;
			if(custPartNoInfo.getSeqId() != 0)
				return true;
			String s = custPartNoInfo.getCommCode();
			if(s == null || s.length() == 0)
				return false;
			s = custPartNoInfo.getState();
			try
			{
				Integer.parseInt(s);
			}
			catch(Exception e)
			{
				return false;
			}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * ������Ŀͻ�������Ϣ����У�飬�ڲ�����checkSelectFields��
	 * @param custPartNoInfo	�ͻ����Ϲ�ϵ��Ϣ
	 * @return ������Ϣ����ʽ��Map<�����ֶ�,������Ϣ>
	 */
	public static List<FieldErr> checkInputFields(CustPartNoInfo custPartNoInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(custPartNoInfo == null)
		{
			list.add(new FieldErr("", "��������"));
			return list;
		}
		
		String s;
		// DONE:���checkInputFields��������Ŀͻ���Ϣ����У��
		s = custPartNoInfo.getVendorCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("venderCode","venderCodeû����д"));
		}
		s = custPartNoInfo.getCommCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CommCode","CommCodeû����д"));
		}
		s = custPartNoInfo.getCustPartNo();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CustPartNo","CustPartNoû����д"));
		}
		BigDecimal num = custPartNoInfo.getPrice();
		if(num.abs().movePointRight(3).compareTo(BigDecimal.ONE) <= 0)
		{
			list.add(new FieldErr("Price","Priceû����д"));
		}
		s = custPartNoInfo.getPartNo();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PartNo","PartNoû����д"));
		}
		else
		{
			try {
				CompanyPartNoMgr mgr = (CompanyPartNoMgr)BeanLocator.getInstance().getBean("companyPartNoMgr");
				CompanyPartNo pnInfo = mgr.getCompanyPartNo(s);
				if(pnInfo == null)
				{
					list.add(new FieldErr("PartNo", "PartNo����"));
				}
				else
				{
					custPartNoInfo.setPnDesc(pnInfo.getPnDesc());
					custPartNoInfo.setClsName(pnInfo.getClsCode());
				}
			} catch (Exception e) {
				logger.error("���PartNo����", e);
				list.add(new FieldErr("PartNo", "PartNo����"));
			}
		}
		s = custPartNoInfo.getPnDesc();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PnDesc","PnDescû����д"));
		}
		
		List<FieldErr> list2 = checkSelectFields(custPartNoInfo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
	}
	
	/**
	 * ���ѡ�����ݡ����ѡ��ֵ����дѡ���˵���ֶ�
	 * @param custPartNoInfo
	 */
	public static List<FieldErr> checkSelectFields(CustPartNoInfo custPartNoInfo)
	{
		if(custPartNoInfo == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		try
		{
			String s;
			
			int i;
			s = custPartNoInfo.getStaffId();
			if(s != null && s.length() != 0)
			{
				try{
					i = Integer.parseInt(s);
				}catch(Exception e){
					i = 0;
				}
				if(i != 0)
				{
					//TODO���û���Ϣ��Ҫ����					
					//custInfo.setStaffName(s);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("checkSelectFields����", e);
		}
		
		return list;
	}

	/**
	 * �ж��Ƿ���Ҫ����staffId
	 * @param custPartNoInfo
	 * @return
	 */
	public static boolean checkSetStaffId(CustPartNoInfo custPartNoInfo) {
		int userid = 0;
		try
		{
			String s = custPartNoInfo.getStaffId();
			userid = Integer.parseInt(s);
		}
		catch(NumberFormatException e)
		{
			userid = 0;
		}
		return (userid == 0);
	}
}
