package com.hbs.customerinfo.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
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
	 */
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
	private static final String contactListName1 = "contactlist";
	/**
	 * 收货人列表字符串参数名
	 */
	private static final String contactListName2 = "consigneelist";
	/**
	 * 银行列表字符串参数名
	 */
	private static final String bankListName = "custbanklist";
	/**
	 * 联系人列表字符串对应字段名
	 */
	
	private static final String splitter = "\\|\\|;;";
	
	private static final String contactListFields1 = "contactlistFields";
	private static final String contactListFields2 = "consigneelistFields";
	private static final String bankListFields = "custbanklistFields";
	private static final String fieldNameSplitter = ",";
	
	/*
	static final String[] contractListFields1 = {"conName", "conDuty", "conTel", "conMobile", "conFax", "conEmail", "conQq", "conMsn", "conOther", "isPrimary"};
	static String[] contractListFields2;
	static final String[] bankListFields = {"accountName", "accountBank", "account"};
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
	*/
	
	/**
	 * 处理上传的List数据。将String数组转换为List
	 */
	@SuppressWarnings("unchecked")
	public static void processListData(CustomerInfo custInfo, HttpServletRequest request) throws Exception
	{
		// Done: 处理上传的List数据
		if(false)
			return;
		try
		{
			Integer id = custInfo.getBaseSeqId();
			String baseSeqId = id == null ? null : id.toString();
			String commCode = custInfo.getCommCode();
			String state = custInfo.getState();
			List<ContactInfo> listAll = new ArrayList<ContactInfo>();
			try
			{
				List<ContactInfo> list = splitIntoList(ContactInfo.class, 
						request.getParameterValues(contactListName1), 
						request.getParameter(contactListFields1).split(fieldNameSplitter), 
						splitter);
				Iterator<ContactInfo> it = list.iterator();
				while(it.hasNext())
				{
					ContactInfo info = it.next();
					if(info == null)
						continue;
					info.setBaseSeqId(baseSeqId);
					info.setCommCode(commCode);
					info.setState(state);
					info.setConType("1");
				}
				listAll.addAll(list);
			}
			catch(Exception e)
			{
				logger.info("processListData处理contactList1出错", e);
			}
			
			try
			{
				List<ContactInfo> list = splitIntoList(ContactInfo.class, 
						request.getParameterValues(contactListName2), 
						request.getParameter(contactListFields2).split(fieldNameSplitter), 
						splitter);
				Iterator<ContactInfo> it = list.iterator();
				while(it.hasNext())
				{
					ContactInfo info = it.next();
					if(info == null)
						continue;
					info.setBaseSeqId(baseSeqId);
					info.setCommCode(commCode);
					info.setState(state);
					info.setConType("2");
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
				List<BankInfo> list = splitIntoList(BankInfo.class, 
						request.getParameterValues(bankListName), 
						request.getParameter(bankListFields).split(fieldNameSplitter), 
						splitter);
				Iterator<BankInfo> it = list.iterator();
				while(it.hasNext())
				{
					BankInfo info = it.next();
					if(info == null)
						continue;
					info.setBaseSeqId(baseSeqId);
					info.setCommCode(commCode);
					info.setState(state);
				}
				if(list.size() > 0)
					custInfo.setListBankInfo(list);
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
	@SuppressWarnings({"unused", "unchecked"})
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
	@SuppressWarnings("unchecked")
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
				if(fieldNames[i] == null || fieldNames[i].length() == 0)
					continue;
				try
				{
					o.getClass().getDeclaredField(fieldNames[i]).set(o, ar[i]);
				}
				catch(Exception e)
				{
					if(logger.isDebugEnabled())	logger.debug("splitIntoFields处理字段"+fieldNames[i]+"出错", e);
					String setName = "set" + fieldNames[i].substring(0, 1).toUpperCase() + fieldNames[i].substring(1);
					try{
						o.getClass().getDeclaredMethod(setName, String.class).invoke(o, ar[i]);
					}catch(Exception e1){
						if(logger.isDebugEnabled())	logger.debug("splitIntoFields处理"+setName+"(String)出错", e1);
						try{
							o.getClass().getDeclaredMethod(setName, Integer.class).invoke(o, Integer.parseInt(ar[i]));
						}catch(Exception e2){
							if(logger.isDebugEnabled())	logger.debug("splitIntoFields处理"+setName+"(Integer)出错", e2);
						}
					}
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
			ConfigEncode ce;
			s = custInfo.getImportantCode();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("IMPORTANT_CODE", s);
				if(ce == null)
					list.add(new FieldErr("importantCode", "importantCode的值不正确"));
				else
				{
					custInfo.setImportantCode(ce.getEncodeKey());
					custInfo.setImportantDesc(ce.getEncodeValue());
				}
			}
				
			s = custInfo.getCreditRate();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("CREDIT_RATE", s);
				if(s == null)
					list.add(new FieldErr("CreditRate", "CreditRate的值不正确"));
				else
				{
					custInfo.setCreditRate(ce.getEncodeKey());
					custInfo.setCreditDesc(ce.getEncodeValue());
				}
			}
				
			s = custInfo.getSettlementType();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("SETTLEMENT_TYPE", s);
				if(s == null)
					list.add(new FieldErr("SettlementType", "SettlementType的值不正确"));
				else{
					custInfo.setSettlementType(ce.getEncodeKey());
					custInfo.setSettlementDesc(ce.getEncodeValue());
				}
			}
			
			s = custInfo.getCurrency();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("CURRENCY", s);
				if(s == null)
					list.add(new FieldErr("Currency", "Currency的值不正确"));
				else
				{
					custInfo.setCurrency(ce.getEncodeKey());
					custInfo.setCurrencyDesc(ce.getEncodeValue());
				}
			}
			
			s = custInfo.getIsShowPrice();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("IS_SHOW_PRICE", s);
				if(s == null)
					list.add(new FieldErr("IsShowPrice", "IsShowPrice的值不正确"));
				else
				{
					custInfo.setIsShowPrice(ce.getEncodeKey());
				}
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
	
	/**
	 * 获取编码。如果s可以解析为数值，则将s当作key，否则当作value
	 * @param type
	 * @param s
	 * @return 编码对象，如果找不到，则返回null
	 */
	private static ConfigEncode getEncode(String type, String s)
	{
		ConfigEncode ce = new ConfigEncode();
		List<ConfigEncode> ce2;
		ce.setEncodeType(type);
		try{
			Integer.parseInt(s);
			ce.setEncodeKey(s);
		}catch(Exception e){
			ce.setEncodeValue(s);
		}
		
		ce2 = ConfigEncodeMgr.getListConfigEncode(ce);
		if(ce2 == null || ce2.size() == 0)
			return null;
		else
			return ce2.get(0);
	}
}
