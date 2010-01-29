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
	 * ������Ŀͻ���Ϣ����У��
	 * @param custInfo	�ͻ���Ϣ
	 * @return ������Ϣ����ʽ��Map<�����ֶ�,������Ϣ>
	 */
	public static List<FieldErr> checkInputFields(CustomerInfo custInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		
		String s;
		// TODO:���checkInputFields��������Ŀͻ���Ϣ����У��
		s = custInfo.getVendorCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("venderCode","venderCodeû����д"));
		}
			
		
		
		return list;
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

	/**
	 * ��ϵ���б��ַ���������
	 */
	static final String contactListName1 = "contactList1";
	/**
	 * ��ϵ���б��ַ�����Ӧ�ֶ���
	 */
	static final String[] contractListFields1 = {"conName", "conDuty", "conTel", "conMobile", "conFax", "conEmail", "conQq", "conMsn", "conOther", "isPrimary"};
	/**
	 * �ջ����б��ַ���������
	 */
	static final String contactListName2 = "contactList2";
	/**
	 * �ջ����б��ַ�����Ӧ�ֶ���
	 */
	static String[] contractListFields2;
	/**
	 * �����б��ַ���������
	 */
	static final String bankListName = "bankList";
	/**
	 * �����б��ַ�����Ӧ�ֶ���
	 */
	static final String[] bankListFields = {"accountName", "accountBank", "account"};

	static{
		// ������ϵ���ֶ��������ջ����ֶ���
		ArrayList<String> l = new ArrayList<String>();
		l.addAll(Arrays.asList(contractListFields1));
		int id = l.size() - 1;	// ���һ���ֶλ��������
		l.add(id, "conAddress");
		l.add(id, "conZip");
		
		contractListFields2 = (String[])l.toArray();
	}
	
	/**
	 * �����ϴ���List���ݡ���String����ת��ΪList
	 */
	@SuppressWarnings("unchecked")
	public static void processListData(CustomerInfo custInfo, HttpServletRequest request) throws Exception
	{
		// Done: �����ϴ���List����
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
	 * ��һ������д������б���
	 * @param itemClass	�б�����Class
	 * @param values	һ�����ݡ�һ���ַ������飬ÿ���ַ�����Ӧһ�����ݣ��зָ���Ϊ��,��
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 * @return
	 */
	private static List splitIntoList(Class itemClass, String[] values, String[] fieldNames)
	{
		//DONE:���splitIntoList
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
	 * ��һ�е�����д��������
	 * @param o ��д��Ķ���
	 * @param values	һ�����ݣ�ÿ���ԡ�,���ָ�
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 */
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames)
	{
		splitIntoFields(o, values, fieldNames, ",");
	}
	
	/**
	 * ��һ�е�����д��������
	 * @param o ��д��Ķ���
	 * @param values	һ������
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 * @param spliter	values���зָ���
	 */
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames, String spliter)
	{
		if(o == null || values == null || fieldNames == null)
			return;
		try
		{
			String[] ar = values.split(spliter);
			// ���������������ڸ������ֶ�����
			if(ar.length < fieldNames.length)
				return;
			
			// �����ݽ����Ӧ�ֶ�
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
