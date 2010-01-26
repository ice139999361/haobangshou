package com.hbs.customerinfo.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * Action中对CustomerInfo的一些通用处理函数集
 * @author xyf
 *
 */
public class CustomerInfoUtil {
	
	/**
	 * 判断是否填写了key字段。custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @param custInfo
	 * @return
	 */
	public static boolean checkKeyFields(CustomerInfo custInfo)
	{
		try
		{
			if(custInfo == null)
				return false;
			if(custInfo.getBaseSeqId() != 0)
				return true;
			String s = custInfo.getCommCode();
			if(s == null || s.length() == 0)
				return false;
			s = custInfo.getState();
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
	 * 字段出错信息。包含Field、Message
	 * @author xyf
	 *
	 */
	public final class FieldErr
	{
		String field, msg;
		
		public String getField() { return field; }
		public void setField(String s) { field = s; }
		public String getMessage() { return msg; }
		public void setMessage(String s) { msg = s; }
		
		public FieldErr(String field, String message)
		{
			this.field = field;
			this.msg = message;
		}
	}
	
	/**
	 * 对输入的客户信息进行校验
	 * @param custInfo	客户信息
	 * @return 出错信息，格式：Map<出错字段,出错信息>
	 */
	public static List<FieldErr> checkInputFields(CustomerInfo custInfo)
	{
		ArrayList<FieldErr> map = new ArrayList<FieldErr>();
		
		
		
		
		return map;
	}
	
	/**
	 * 根据checkInputFields的出错信息生成出错字符串
	 * @param err checkInputFields的出错信息
	 * @return
	 */
	public static String formFieldsErrString(List<FieldErr> err)
	{
		if(err == null || err.size() == 0)
			return "";
		
		StringBuilder sb = new StringBuilder();
		Iterator<FieldErr> it = err.iterator();
		
		while(it.hasNext())
		{
			FieldErr e = it.next();
			if(sb.length()>0)
				sb.append("<br />");
			sb.append(e.getField());
			sb.append(":");
			sb.append(e.getMessage());
		}
		
		return sb.toString();
	}
	
	/**
	 * 判断是否需要设置staffId
	 * @param custInfo
s	 */
	public static boolean checkSetStaffId(CustomerInfo custInfo)
	{
		int userid = 0;
		try
		{
			String s = custInfo.getStaffId();
			userid = Integer.parseInt(s);
		}
		catch(NumberFormatException e)
		{
			userid = 0;
		}
		return (userid == 0);
	}
}
