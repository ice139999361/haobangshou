package com.hbs.customerinfo.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
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
	 * 对输入的客户信息进行校验
	 * @param custInfo	客户信息
	 * @return 出错信息，格式：Map<出错字段,出错信息>
	 */
	public static List<FieldErr> checkInputFields(CustomerInfo custInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		
		String s;
		// TODO:完成checkInputFields，对输入的客户信息进行校验
		s = custInfo.getVendorCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("venderCode","venderCode没有填写"));
		}
			
		
		
		return list;
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

	/**
	 * 联系人列表字符串参数名
	 */
	static final String contactListName1 = "contactList1";
	/**
	 * 联系人列表字符串对应字段名
	 */
	static final String[] contractListFields1 = {"conName", "conDuty", "conTel", "conMobile", "conFax", "conEmail", "conQq", "conMsn", "conOther", "isPrimary"};
	/**
	 * 收货人列表字符串参数名
	 */
	static final String contactListName2 = "contactList2";
	/**
	 * 收货人列表字符串对应字段名
	 */
	static String[] contractListFields2;
	/**
	 * 银行列表字符串参数名
	 */
	static final String bankListName = "bankList";
	/**
	 * 银行列表字符串对应字段名
	 */
	static final String[] bankListFields = {"accountName", "accountBank", "account"};

	static{
		// 根据联系人字段名生成收货人字段名
		ArrayList<String> l = new ArrayList<String>();
		l.addAll(Arrays.asList(contractListFields1));
		int id = l.size() - 1;	// 最后一个字段还是在最后。
		l.add(id, "conAddress");
		l.add(id, "conZip");
		
		contractListFields2 = (String[])l.toArray();
	}
	
	/**
	 * 处理上传的List数据。将String数组转换为List
	 */
	@SuppressWarnings("unchecked")
	public static void processListData(CustomerInfo custInfo, HttpServletRequest request) throws Exception
	{
		// Done: 处理上传的List数据
		if(true)
			return;
		try
		{
			String commCode = custInfo.getCommCode();
			String state = custInfo.getState();
			List<ContactInfo> listAll = new ArrayList<ContactInfo>();
			try
			{
				List<ContactInfo> list = splitIntoList(ContactInfo.class, request.getParameterValues(contactListName1), contractListFields1);
				Iterator<ContactInfo> it = list.iterator();
				while(it.hasNext())
				{
					it.next().setCommCode(commCode);
					it.next().setState(state);
					it.next().setConType("1");
				}
				listAll.addAll(list);
			}
			catch(Exception e)
			{
				
			}
			
			try
			{
				List<ContactInfo> list = splitIntoList(ContactInfo.class, request.getParameterValues(contactListName2), contractListFields2);
				Iterator<ContactInfo> it = list.iterator();
				while(it.hasNext())
				{
					it.next().setCommCode(commCode);
					it.next().setState(state);
					it.next().setConType("2");
				}
				listAll.addAll(list);
			}
			catch(Exception e)
			{
				
			}
			if(listAll.size()>0)
				custInfo.setListContactInfo(listAll);
			
			try
			{
				custInfo.setListBankInfo(splitIntoList(BankInfo.class, request.getParameterValues(bankListName), bankListFields));
			}
			catch(Exception e)
			{
				
			}
		}
		catch(Exception e)
		{
		
		}
	}
	
	/**
	 * 将一组数据写入对象列表中
	 * @param itemClass	列表对象的Class
	 * @param values	一组数据。一个字符串数组，每个字符串对应一行数据，列分隔符为“,”
	 * @param fieldNames	每列数据对应的字段名
	 * @return
	 */
	private static List splitIntoList(Class itemClass, String[] values, String[] fieldNames)
	{
		//DONE:完成splitIntoList
		try
		{
			ArrayList list = new ArrayList();
			for(int i = 0; i < values.length; i++)
			{
				try
				{
					Object o = itemClass.newInstance();
					splitIntoFields(o, values[i], fieldNames);
					list.add(o);
				}
				catch(Exception e)
				{
					
				}
			}
			return list;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	/**
	 * 将一行的数据写到对象中
	 * @param o 待写入的对象
	 * @param values	一行数据，每列以“,”分隔
	 * @param fieldNames	每列数据对应的字段名
	 */
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames)
	{
		splitIntoFields(o, values, fieldNames, ",");
	}
	
	/**
	 * 将一行的数据写到对象中
	 * @param o 待写入的对象
	 * @param values	一行数据
	 * @param fieldNames	每列数据对应的字段名
	 * @param spliter	values的列分隔符
	 */
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames, String spliter)
	{
		if(o == null || values == null || fieldNames == null)
			return;
		try
		{
			String[] ar = values.split(spliter);
			// 数据列数不能少于给出的字段名数
			if(ar.length < fieldNames.length)
				return;
			
			// 将数据接入对应字段
			for(int i = 0; i < fieldNames.length; i++)
			{
				try
				{
					o.getClass().getDeclaredField(fieldNames[i]).set(o, ar[i]);
				}
				catch(Exception e)
				{
					
				}
			}
			
		}
		catch(Exception e)
		{
			
		}
	
	}

}
