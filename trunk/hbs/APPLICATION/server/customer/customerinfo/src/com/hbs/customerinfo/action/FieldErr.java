package com.hbs.customerinfo.action;

import java.util.Iterator;
import java.util.List;

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
	
	public FieldErr(){};
	
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
}
