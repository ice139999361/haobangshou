package com.hbs.vendorinfo.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;

/**
 * Action�ж�CustomerInfo��һЩͨ�ô�������
 * @author xyf
 *
 */
public class VendorInfoUtil {

	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorInfoUtil.class);
    
	/**
	 * �ж��Ƿ���д��key�ֶΡ�vendorInfo.baseSeqId �� (vendorInfo.commCode + vendorInfo.state)
	 * @param vendorInfo
	 * @return
	 */
	public static boolean checkKeyFields(VendorInfo vendorInfo)
	{
		try
		{
			if(vendorInfo == null)
				return false;
			if(vendorInfo.getBaseSeqId() != 0)
				return true;
			String s = vendorInfo.getCommCode();
			if(s == null || s.length() == 0)
				return false;
			s = vendorInfo.getState();
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
	 * @param vendorInfo	�ͻ���Ϣ
	 * @return ������Ϣ����ʽ��Map<�����ֶ�,������Ϣ>
	 */
	public static List<FieldErr> checkInputFields(VendorInfo vendorInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(vendorInfo == null)
			return list;
		
		String s;
		// DONE:���checkInputFields��������Ŀͻ���Ϣ����У��
		s = vendorInfo.getVendorCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("venderCode","venderCodeû����д"));
		}
		s = vendorInfo.getCommCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CommCode","CommCodeû����д"));
		}
		s = vendorInfo.getShortName();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("ShortName","ShortNameû����д"));
		}
		s = vendorInfo.getAllName();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("AllName","AllNameû����д"));
		}
		s = vendorInfo.getIsShowPrice();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("IsShowPrice","IsShowPriceû����д"));
		}
		
		/*
		s = vendorInfo.get.getAssStaffId();
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
		*/
		
		List<FieldErr> list2 = checkSelectFields(vendorInfo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
	}
		
	/**
	 * �ж��Ƿ���Ҫ����staffId
	 * @param vendorInfo
	 */
	public static boolean checkSetStaffId(VendorInfo vendorInfo)
	{
		int userid = 0;
		try
		{
			String s = vendorInfo.getStaffId();
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
	private static final String contactListName1 = "contactlist";
	/**
	 * �ջ����б��ַ���������
	 */
	private static final String contactListName2 = "consigneelist";
	/**
	 * �����б��ַ���������
	 */
	private static final String bankListName = "custbanklist";
	/**
	 * ��ϵ���б��ַ�����Ӧ�ֶ���
	 */
	
	private static final String splitter = "\\|\\|;;";
	
	private static final String contactListFields1 = "contactlistFields";
	private static final String contactListFields2 = "consigneelistFields";
	private static final String bankListFields = "custbanklistFields";
	private static final String fieldNameSplitter = ",";
	
	/**
	 * �����ϴ���List���ݡ���String����ת��ΪList
	 */
	@SuppressWarnings("unchecked")
	public static void processListData(VendorInfo vendorInfo, HttpServletRequest request) throws Exception
	{
		// Done: �����ϴ���List����
		if(false)
			return;
		try
		{
			Integer id = vendorInfo.getBaseSeqId();
			String baseSeqId = id == null ? null : id.toString();
			String commCode = vendorInfo.getCommCode();
			String state = vendorInfo.getState();
			List<ContactInfo> listAll = new ArrayList<ContactInfo>();
			try
			{
				List<ContactInfo> list = ListDataUtil.splitIntoList(ContactInfo.class, 
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
				logger.info("processListData����contactList1����", e);
			}
			
			try
			{
				List<ContactInfo> list = ListDataUtil.splitIntoList(ContactInfo.class, 
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
				logger.info("processListData����contactList2����", e);
			}
			if(listAll.size()>0)
				vendorInfo.setListContactInfo(listAll);
			
			try
			{
				List<BankInfo> list = ListDataUtil.splitIntoList(BankInfo.class, 
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
					vendorInfo.setListBankInfo(list);
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
	 * ���ѡ�����ݡ����ѡ��ֵ����дѡ���˵���ֶ�
	 * @param vendorInfo
	 */
	public static List<FieldErr> checkSelectFields(VendorInfo vendorInfo)
	{
		if(vendorInfo == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		try
		{
			String s;
			ConfigEncode ce;
			s = vendorInfo.getImportantCode();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("IMPORTANT_CODE", s);
				if(ce == null)
					list.add(new FieldErr("importantCode", "importantCode��ֵ����ȷ"));
				else
				{
					vendorInfo.setImportantCode(ce.getEncodeKey());
					vendorInfo.setImportantDesc(ce.getEncodeValue());
				}
			}
				
			s = vendorInfo.getCreditRate();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("CREDIT_RATE", s);
				if(s == null)
					list.add(new FieldErr("CreditRate", "CreditRate��ֵ����ȷ"));
				else
				{
					vendorInfo.setCreditRate(ce.getEncodeKey());
					vendorInfo.setCreditDesc(ce.getEncodeValue());
				}
			}
				
			s = vendorInfo.getSettlementType();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("SETTLEMENT_TYPE", s);
				if(s == null)
					list.add(new FieldErr("SettlementType", "SettlementType��ֵ����ȷ"));
				else{
					vendorInfo.setSettlementType(ce.getEncodeKey());
					vendorInfo.setSettlementDesc(ce.getEncodeValue());
				}
			}
			
			s = vendorInfo.getCurrency();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("CURRENCY", s);
				if(s == null)
					list.add(new FieldErr("Currency", "Currency��ֵ����ȷ"));
				else
				{
					vendorInfo.setCurrency(ce.getEncodeKey());
					vendorInfo.setCurrencyDesc(ce.getEncodeValue());
				}
			}
			
			s = vendorInfo.getIsShowPrice();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("IS_SHOW_PRICE", s);
				if(s == null)
					list.add(new FieldErr("IsShowPrice", "IsShowPrice��ֵ����ȷ"));
				else
				{
					vendorInfo.setIsShowPrice(ce.getEncodeKey());
				}
			}

			int i;
			s = vendorInfo.getStaffId();
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
					//vendorInfo.setStaffName(s);
				}
			}
			/*
			s = vendorInfo.getAssStaffId();
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
					//vendorInfo.setAssStaffName(s);
				}
			}
			*/
		}
		catch(Exception e)
		{
			logger.info("checkSelectFields����", e);
		}
		
		return list;
	}
	
	/**
	 * ��ȡ���롣���s���Խ���Ϊ��ֵ����s����key��������value
	 * @param type
	 * @param s
	 * @return �����������Ҳ������򷵻�null
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
