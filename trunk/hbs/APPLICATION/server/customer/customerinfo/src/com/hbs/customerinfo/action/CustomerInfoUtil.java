package com.hbs.customerinfo.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.common.manager.configencode.ConfigEncodeMgr;

/**
 * Action�ж�CustomerInfo��һЩͨ�ô�������
 * @author xyf
 *
 */
public class CustomerInfoUtil {

	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustomerInfoUtil.class);
    
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
	 * ������Ŀͻ���Ϣ����У�飬�ڲ�����checkSelectFields��
	 * @param custInfo	�ͻ���Ϣ
	 * @return ������Ϣ����ʽ��Map<�����ֶ�,������Ϣ>
	 */
	public static List<FieldErr> checkInputFields(CustomerInfo custInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(custInfo == null)
			return list;
		
		String s;
		// DONE:���checkInputFields��������Ŀͻ���Ϣ����У��
		s = custInfo.getVendorCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("venderCode","venderCodeû����д"));
		}
		s = custInfo.getCommCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CommCode","CommCodeû����д"));
		}
		s = custInfo.getShortName();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("ShortName","ShortNameû����д"));
		}
		s = custInfo.getAllName();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("AllName","AllNameû����д"));
		}
		s = custInfo.getIsShowPrice();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("IsShowPrice","IsShowPriceû����д"));
		}
		s = custInfo.getAssStaffId();
		if(s == null || s.length() == 0)
		{
			int i;
			try{
				i = Integer.parseInt(s);
			}catch(Exception e){
				i=0;
			}
			if(i == 0)
			list.add(new FieldErr("AssStaff","AssStaffû����д"));
		}
		
		List<FieldErr> list2 = checkSelectFields(custInfo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
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
	static final String contactListName1 = "contactlist";
	/**
	 * ��ϵ���б��ַ�����Ӧ�ֶ���
	 */
	static final String[] contractListFields1 = {"conName", "conDuty", "conTel", "conMobile", "conFax", "conEmail", "conQq", "conMsn", "conOther", "isPrimary"};
	/**
	 * �ջ����б��ַ���������
	 */
	static final String contactListName2 = "consigneelist";
	/**
	 * �ջ����б��ַ�����Ӧ�ֶ���
	 */
	static String[] contractListFields2;
	/**
	 * �����б��ַ���������
	 */
	static final String bankListName = "custbanklist";
	/**
	 * �����б��ַ�����Ӧ�ֶ���
	 */
	static final String[] bankListFields = {"accountName", "accountBank", "account"};
	
	private static final String spliter = "||;;";

	static{
		// ������ϵ���ֶ��������ջ����ֶ���
		ArrayList<String> l = new ArrayList<String>();
		l.addAll(Arrays.asList(contractListFields1));
		int id = l.size() - 1;	// ���һ���ֶλ��������
		l.add(id, "conAddress");
		l.add(id, "conZip");
		
		//contractListFields2 = (String[])l.toArray();
		contractListFields2 = new String[l.size()];
		l.toArray(contractListFields2);
	}
	
	/**
	 * �����ϴ���List���ݡ���String����ת��ΪList
	 */
	public static void processListData(CustomerInfo custInfo, HttpServletRequest request) throws Exception
	{
		// Done: �����ϴ���List����
		if(false)
			return;
		try
		{
			String commCode = custInfo.getCommCode();
			String state = custInfo.getState();
			List<ContactInfo> listAll = new ArrayList<ContactInfo>();
			try
			{
				List<ContactInfo> list = splitIntoList(ContactInfo.class, request.getParameterValues(contactListName1), contractListFields1, spliter);
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
				logger.info("processListData����contactList1����", e);
			}
			
			try
			{
				List<ContactInfo> list = splitIntoList(ContactInfo.class, request.getParameterValues(contactListName2), contractListFields2, spliter);
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
				logger.info("processListData����contactList2����", e);
			}
			if(listAll.size()>0)
				custInfo.setListContactInfo(listAll);
			
			try
			{
				custInfo.setListBankInfo(splitIntoList(BankInfo.class, request.getParameterValues(bankListName), bankListFields, spliter));
			}
			catch(Exception e)
			{
				logger.info("processListData����bankList����", e);
			}
		}
		catch(Exception e)
		{
			logger.info("processListData����", e);
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
		return splitIntoList(itemClass, values, fieldNames, ",");
	}
	
	/**
	 * ��һ������д������б���
	 * @param itemClass	�б�����Class
	 * @param values	һ�����ݡ�һ���ַ������飬ÿ���ַ�����Ӧһ������
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 * @param spliter	values�ķָ���
	 * @return
	 */
	private static List splitIntoList(Class itemClass, String[] values, String[] fieldNames, String spliter)
	{
		//DONE:���splitIntoList
		try
		{
			ArrayList list = new ArrayList();
			if(values == null)
				return list;
			
			for(int i = 0; i < values.length; i++)
			{
				try
				{
					Object o = itemClass.newInstance();
					splitIntoFields(o, values[i], fieldNames, spliter);
					list.add(o);
				}
				catch(Exception e)
				{
					logger.info("splitIntoList��������"+values[i]+"����", e);
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
	@SuppressWarnings("unused")
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
					logger.info("splitIntoFields�����ֶ�"+fieldNames[i]+"����", e);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("splitIntoFields����", e);
		}
	}

	/**
	 * ���ѡ�����ݡ����ѡ��ֵ����дѡ���˵���ֶ�
	 * @param custInfo
	 */
	public static List<FieldErr> checkSelectFields(CustomerInfo custInfo)
	{
		if(custInfo == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		try
		{
			String s;
			ConfigEncode ce = new ConfigEncode();
			ConfigEncode ce2;
			
			s = custInfo.getImportantCode();
			if(s != null && s.length() != 0)
			{
				ce.setEncodeType("IMPORTANT");
				ce.setEncodeKey(s);
				ce2 = ConfigEncodeMgr.getConfigEncode(ce);
				if(ce2 == null)
					list.add(new FieldErr("importantCode", "importantCode��ֵ����ȷ"));
				else
					custInfo.setImportantDesc(ce2.getEncodeValue());
			}
				
			s = custInfo.getCreditRate();
			if(s != null && s.length() != 0)
			{
				ce.setEncodeType("CREDIT");
				ce.setEncodeKey(s);
				ce2 = ConfigEncodeMgr.getConfigEncode(ce);
				if(ce2 == null)
					list.add(new FieldErr("CreditRate", "CreditRate��ֵ����ȷ"));
				else
					custInfo.setCreditDesc(ce2.getEncodeValue());
			}
				
			s = custInfo.getSettlementType();
			if(s != null && s.length() != 0)
			{
				ce.setEncodeType("SETTLEMENT");
				ce.setEncodeKey(s);
				ce2 = ConfigEncodeMgr.getConfigEncode(ce);
				if(ce2 == null)
					list.add(new FieldErr("SettlementType", "SettlementType��ֵ����ȷ"));
				else
					custInfo.setSettlementType(ce2.getEncodeValue());
			}
			
			s = custInfo.getCurrency();
			if(s != null && s.length() != 0)
			{
				ce.setEncodeType("Currency");
				ce.setEncodeKey(s);
				ce2 = ConfigEncodeMgr.getConfigEncode(ce);
				if(ce2 == null)
					list.add(new FieldErr("Currency", "Currency��ֵ����ȷ"));
				else
					custInfo.setCurrency(ce2.getEncodeValue());
			}
			
			int i;
			s = custInfo.getStaffId();
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
			s = custInfo.getAssStaffId();
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
					//custInfo.setAssStaffName(s);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("checkSelectFields����", e);
		}
		
		return list;
	}
}
