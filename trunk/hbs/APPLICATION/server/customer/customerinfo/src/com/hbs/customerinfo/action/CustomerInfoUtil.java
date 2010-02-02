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
 * Action中对CustomerInfo的一些通用处理函数集
 * @author xyf
 *
 */
public class CustomerInfoUtil {

	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustomerInfoUtil.class);
    
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
	 * 对输入的客户信息进行校验，内部调用checkSelectFields。
	 * @param custInfo	客户信息
	 * @return 出错信息，格式：Map<出错字段,出错信息>
	 */
	public static List<FieldErr> checkInputFields(CustomerInfo custInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(custInfo == null)
			return list;
		
		String s;
		// DONE:完成checkInputFields，对输入的客户信息进行校验
		s = custInfo.getVendorCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("venderCode","venderCode没有填写"));
		}
		s = custInfo.getCommCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CommCode","CommCode没有填写"));
		}
		s = custInfo.getShortName();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("ShortName","ShortName没有填写"));
		}
		s = custInfo.getAllName();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("AllName","AllName没有填写"));
		}
		s = custInfo.getIsShowPrice();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("IsShowPrice","IsShowPrice没有填写"));
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
			list.add(new FieldErr("AssStaff","AssStaff没有填写"));
		}
		
		List<FieldErr> list2 = checkSelectFields(custInfo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
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
	static final String contactListName1 = "contactlist";
	/**
	 * 联系人列表字符串对应字段名
	 */
	static final String[] contractListFields1 = {"conName", "conDuty", "conTel", "conMobile", "conFax", "conEmail", "conQq", "conMsn", "conOther", "isPrimary"};
	/**
	 * 收货人列表字符串参数名
	 */
	static final String contactListName2 = "consigneelist";
	/**
	 * 收货人列表字符串对应字段名
	 */
	static String[] contractListFields2;
	/**
	 * 银行列表字符串参数名
	 */
	static final String bankListName = "custbanklist";
	/**
	 * 银行列表字符串对应字段名
	 */
	static final String[] bankListFields = {"accountName", "accountBank", "account"};
	
	private static final String spliter = "||;;";

	static{
		// 根据联系人字段名生成收货人字段名
		ArrayList<String> l = new ArrayList<String>();
		l.addAll(Arrays.asList(contractListFields1));
		int id = l.size() - 1;	// 最后一个字段还是在最后。
		l.add(id, "conAddress");
		l.add(id, "conZip");
		
		//contractListFields2 = (String[])l.toArray();
		contractListFields2 = new String[l.size()];
		l.toArray(contractListFields2);
	}
	
	/**
	 * 处理上传的List数据。将String数组转换为List
	 */
	public static void processListData(CustomerInfo custInfo, HttpServletRequest request) throws Exception
	{
		// Done: 处理上传的List数据
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
				logger.info("processListData处理contactList1出错", e);
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
				logger.info("processListData处理contactList2出错", e);
			}
			if(listAll.size()>0)
				custInfo.setListContactInfo(listAll);
			
			try
			{
				custInfo.setListBankInfo(splitIntoList(BankInfo.class, request.getParameterValues(bankListName), bankListFields, spliter));
			}
			catch(Exception e)
			{
				logger.info("processListData处理bankList出错", e);
			}
		}
		catch(Exception e)
		{
			logger.info("processListData出错", e);
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
		return splitIntoList(itemClass, values, fieldNames, ",");
	}
	
	/**
	 * 将一组数据写入对象列表中
	 * @param itemClass	列表对象的Class
	 * @param values	一组数据。一个字符串数组，每个字符串对应一行数据
	 * @param fieldNames	每列数据对应的字段名
	 * @param spliter	values的分隔符
	 * @return
	 */
	private static List splitIntoList(Class itemClass, String[] values, String[] fieldNames, String spliter)
	{
		//DONE:完成splitIntoList
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
					logger.info("splitIntoList处理数据"+values[i]+"出错", e);
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
	@SuppressWarnings("unused")
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
					logger.info("splitIntoFields处理字段"+fieldNames[i]+"出错", e);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("splitIntoFields出错", e);
		}
	}

	/**
	 * 检查选择数据。检查选项值，填写选项的说明字段
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
					list.add(new FieldErr("importantCode", "importantCode的值不正确"));
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
					list.add(new FieldErr("CreditRate", "CreditRate的值不正确"));
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
					list.add(new FieldErr("SettlementType", "SettlementType的值不正确"));
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
					list.add(new FieldErr("Currency", "Currency的值不正确"));
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
					//TODO：用户信息需要处理					
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
					//TODO：用户信息需要处理					
					//custInfo.setAssStaffName(s);
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
