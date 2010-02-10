package com.hbs.customerinfo.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

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
		if(custInfo == null)
			return new ArrayList<FieldErr>();
		
		List<FieldErr> list = checkSelectFields(custInfo);
		if(list == null)
			list = new ArrayList<FieldErr>();
		
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
		
		String baseSeqId = null;
		try {
			baseSeqId = custInfo.getBaseSeqId().toString();
		} catch (Exception e) {
		}
		AccountPreiod accountPreiod = custInfo.getAccountPreiod();
		if(accountPreiod != null) {
			accountPreiod.setCommCode(custInfo.getCommCode());
			accountPreiod.setState(custInfo.getState());
			accountPreiod.setBaseSeqId(baseSeqId);
		}
		PrePaidInfo prePaidInfo = custInfo.getPrePaidInfo();
		if(prePaidInfo != null) {
			prePaidInfo.setCommCode(custInfo.getCommCode());
			prePaidInfo.setState(custInfo.getState());
			prePaidInfo.setBaseSeqId(baseSeqId);
		}
		return list;
	}
		
	/**
	 * �ж��Ƿ���Ҫ����staffId
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
	public static void processListData(CustomerInfo custInfo, HttpServletRequest request) throws Exception
	{
		// Done: �����ϴ���List����
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
				custInfo.setListContactInfo(listAll);
			
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
					custInfo.setListBankInfo(list);
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
					list.add(new FieldErr("importantCode", "importantCode��ֵ����ȷ"));
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
				if(ce == null)
					list.add(new FieldErr("CreditRate", "CreditRate��ֵ����ȷ"));
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
				if(ce == null)
					list.add(new FieldErr("SettlementType", "SettlementType��ֵ����ȷ"));
				else{
					custInfo.setSettlementType(ce.getEncodeKey());
					custInfo.setSettlementDesc(ce.getEncodeValue());
				}
			}
			
			s = custInfo.getCurrency();
			if(s != null && s.length() != 0)
			{
				ce = getEncode("CURRENCY", s);
				if(ce == null)
					list.add(new FieldErr("Currency", "Currency��ֵ����ȷ"));
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
				if(ce == null)
					list.add(new FieldErr("IsShowPrice", "IsShowPrice��ֵ����ȷ"));
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
		if(s.equals("��ѡ��"))
		{
			return ce;
		}
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
	
	/**
	 * ��ȡ�ͻ���Ϣ���飬����ϵ�����ջ��˷ֿ���
	 * @param mgr
	 * @param custInfo
	 * @return
	 */
	public static CustomerInfo getCustomerInfo(CustomerInfoMgr mgr, CustomerInfo custInfo) {
		try {
			custInfo = mgr.getCustomerInfo(custInfo, true);
			if(custInfo == null || custInfo.getListContactInfo() == null)
				return custInfo;
			Iterator<ContactInfo> it = custInfo.getListContactInfo().iterator();
			List<ContactInfo> l1 = new ArrayList<ContactInfo>();
			List<ContactInfo> l2 = new ArrayList<ContactInfo>();
			while(it.hasNext()) {
				ContactInfo info = it.next();
				if(info == null)
					continue;
				if(info.getConType().equals("1")) {
					l1.add(info);
				} else {
					l2.add(info);
				}
			}
			custInfo.setField(contactListName1, l1);
			custInfo.setField(contactListName2, l2);
		} catch (Exception e) {
		}
		return custInfo;
	}
}
