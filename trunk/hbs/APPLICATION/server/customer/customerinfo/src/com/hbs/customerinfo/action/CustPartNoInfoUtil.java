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
 * Action中对CustPartNoInfo的一些通用处理函数集
 * @author xyf
 *
 */
public class CustPartNoInfoUtil {
	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustPartNoInfoUtil.class);
	
    /**
	 * 判断是否填写了key字段。custPartNoInfo.SeqId 或 (custPartNoInfo.commCode + custPartNoInfo.state)
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
	 * 对输入的客户物料信息进行校验，内部调用checkSelectFields。
	 * @param custPartNoInfo	客户物料关系信息
	 * @return 出错信息，格式：Map<出错字段,出错信息>
	 */
	public static List<FieldErr> checkInputFields(CustPartNoInfo custPartNoInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(custPartNoInfo == null)
		{
			list.add(new FieldErr("", "参数错误"));
			return list;
		}
		
		String s;
		// DONE:完成checkInputFields，对输入的客户信息进行校验
		s = custPartNoInfo.getVendorCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("venderCode","venderCode没有填写"));
		}
		s = custPartNoInfo.getCommCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CommCode","CommCode没有填写"));
		}
		s = custPartNoInfo.getCustPartNo();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CustPartNo","CustPartNo没有填写"));
		}
		BigDecimal num = custPartNoInfo.getPrice();
		if(num.abs().movePointRight(3).compareTo(BigDecimal.ONE) <= 0)
		{
			list.add(new FieldErr("Price","Price没有填写"));
		}
		s = custPartNoInfo.getPartNo();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PartNo","PartNo没有填写"));
		}
		else
		{
			try {
				CompanyPartNoMgr mgr = (CompanyPartNoMgr)BeanLocator.getInstance().getBean("companyPartNoMgr");
				CompanyPartNo pnInfo = mgr.getCompanyPartNo(s);
				if(pnInfo == null)
				{
					list.add(new FieldErr("PartNo", "PartNo错误"));
				}
				else
				{
					custPartNoInfo.setPnDesc(pnInfo.getPnDesc());
					custPartNoInfo.setClsName(pnInfo.getClsCode());
				}
			} catch (Exception e) {
				logger.error("检查PartNo出错", e);
				list.add(new FieldErr("PartNo", "PartNo错误"));
			}
		}
		s = custPartNoInfo.getPnDesc();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PnDesc","PnDesc没有填写"));
		}
		
		List<FieldErr> list2 = checkSelectFields(custPartNoInfo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
	}
	
	/**
	 * 检查选择数据。检查选项值，填写选项的说明字段
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
					//TODO：用户信息需要处理					
					//custInfo.setStaffName(s);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("checkSelectFields出错", e);
		}
		
		return list;
	}

	/**
	 * 判断是否需要设置staffId
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
