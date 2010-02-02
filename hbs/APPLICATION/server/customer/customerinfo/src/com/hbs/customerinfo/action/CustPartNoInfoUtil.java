/**
 * 
 */
package com.hbs.customerinfo.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

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
	 * 判断是否填写了key字段。custPNInfo.SeqId 或 (custPNInfo.commCode + custPNInfo.state)
	 * @param custPNInfo
	 * @return
	 */
	public static boolean checkKeyFields(CustPartNoInfo custPNInfo)
	{
		try
		{
			if(custPNInfo == null)
				return false;
			if(custPNInfo.getSeqId() != 0)
				return true;
			String s = custPNInfo.getCommCode();
			if(s == null || s.length() == 0)
				return false;
			s = custPNInfo.getState();
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
	 * 对输入的客户信息进行校验，内部调用checkSelectFields。
	 * @param custPNInfo	客户信息
	 * @return 出错信息，格式：Map<出错字段,出错信息>
	 */
	public static List<FieldErr> checkInputFields(CustPartNoInfo custPNInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(custPNInfo == null)
			return list;
		
		String s;
		// DONE:完成checkInputFields，对输入的客户信息进行校验
		s = custPNInfo.getVendorCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("venderCode","venderCode没有填写"));
		}
		s = custPNInfo.getCommCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CommCode","CommCode没有填写"));
		}
		
		List<FieldErr> list2 = checkSelectFields(custPNInfo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
	}
	
	/**
	 * 检查选择数据。检查选项值，填写选项的说明字段
	 * @param custPNInfo
	 */
	public static List<FieldErr> checkSelectFields(CustPartNoInfo custPNInfo)
	{
		if(custPNInfo == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		try
		{
			String s;
			ConfigEncode ce = new ConfigEncode();
			ConfigEncode ce2;
			
			int i;
			s = custPNInfo.getStaffId();
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
		
}
