package com.hbs.customerinfo.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * Action�ж�CustomerInfo��һЩͨ�ô�������
 * @author xyf
 *
 */
public class CustomerInfoUtil {
	
	/**
	 * �ж��Ƿ���д��key�ֶΡ�custInfo.baseSeqId �� (custInfo.commCode + custInfo.state)
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
	 * �ֶγ�����Ϣ������Field��Message
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
	 * ������Ŀͻ���Ϣ����У��
	 * @param custInfo	�ͻ���Ϣ
	 * @return ������Ϣ����ʽ��Map<�����ֶ�,������Ϣ>
	 */
	public static List<FieldErr> checkInputFields(CustomerInfo custInfo)
	{
		ArrayList<FieldErr> map = new ArrayList<FieldErr>();
		
		
		
		
		return map;
	}
	
	/**
	 * ����checkInputFields�ĳ�����Ϣ���ɳ����ַ���
	 * @param err checkInputFields�ĳ�����Ϣ
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
	 * �ж��Ƿ���Ҫ����staffId
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
