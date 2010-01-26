package com.hbs.customerinfo.action;

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
}
