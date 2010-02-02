package com.hbs.customerinfo.action;

import java.util.Iterator;
import java.util.List;

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
	
	public FieldErr(){};
	
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
}
