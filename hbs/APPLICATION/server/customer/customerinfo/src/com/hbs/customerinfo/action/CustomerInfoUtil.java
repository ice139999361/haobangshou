package com.hbs.customerinfo.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.domain.auth.pojo.Staff;
import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.common.utils.StaffUtil;
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
		boolean ret = true;
		try
		{
			if(null == custInfo){
				ret = false;
			}else{
				Integer i = custInfo.getBaseSeqId();
				String code = custInfo.getCommCode();
				if((null == i || i.intValue() == 0) && (StringUtils.isEmpty(code))){
					ret = false;
				}
			}
		}
		catch(Exception e)
		{
			ret = false;
		}
		if(custInfo.getState() == null){
			custInfo.setState("0");
		}
		return ret;
	}
	
	/**
	 * �ж��Ƿ���д��key�ֶΡ�custInfo.baseSeqId �� custInfo.commCode
	 * @param custInfo
	 * @return
	 */
	public static boolean checkKeyFields2(CustomerInfo custInfo) {
		try
		{
			if(custInfo == null)
				return false;
			Integer i = custInfo.getBaseSeqId();
			if(i != null && !i.equals(0))
				return true;
			String s = custInfo.getCommCode();
			if(StringUtils.isEmpty(s))
				return false;
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
	public static List<FieldErr> checkInputFields(CustomerInfo custInfo) throws Exception
	{
		if(custInfo == null)
			return new ArrayList<FieldErr>();
		
		List<FieldErr> list = checkSelectFields(custInfo);
		if(list == null)
			list = new ArrayList<FieldErr>();
		
		String s;
		// DONE:���checkInputFields��������Ŀͻ���Ϣ����У��
		s = custInfo.getVendorCode();
//		if(StringUtils.isEmpty(s))
//		{
//			list.add(new FieldErr("venderCode","venderCodeû����д"));
//		}
		s = custInfo.getCommCode();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("CommCode","CommCodeû����д"));
		}
		s = custInfo.getShortName();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("ShortName","ShortNameû����д"));
		}
		s = custInfo.getAllName();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("AllName","AllNameû����д"));
		}
		s = custInfo.getIsShowPrice();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("IsShowPrice","IsShowPriceû����д"));
		}
		s = custInfo.getAssStaffId();
		if(StringUtils.isEmpty(s))
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
		s = custInfo.getSettlementType();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("SettlementType","SettlementTypeû����д"));
		}
		
		String baseSeqId = null;
		try {
			baseSeqId = custInfo.getBaseSeqId().toString();
		} catch (Exception e) {
		}
		String settleMentType = custInfo.getSettlementType();
		if(settleMentType.equals("1")){
			custInfo.setPrePaidInfo(null);
		}else{
			custInfo.setAccountPreiod(null);
		}
		AccountPreiod accountPreiod = custInfo.getAccountPreiod();
		if(accountPreiod != null) {
			accountPreiod.setCommCode(custInfo.getCommCode());
			accountPreiod.setState(custInfo.getState());
			accountPreiod.setBaseSeqId(baseSeqId);
			accountPreiod.setAccounDay("1");//�����գ�ȱʡ1
			accountPreiod.setAccountType("1");//�������ͣ�ȱʡ1 �½�
			accountPreiod.setAccountPeriod("1"); //����  ȱʡ1  һ����
		}
		PrePaidInfo prePaidInfo = custInfo.getPrePaidInfo();
		if(prePaidInfo != null) {
			prePaidInfo.setCommCode(custInfo.getCommCode());
			prePaidInfo.setState(custInfo.getState());
			prePaidInfo.setBaseSeqId(baseSeqId);
			
			// ����prepaid��reminderday
			if(custInfo.getSettlementType().equals("2")) {
				s = prePaidInfo.getReminderDay();
				if(StringUtils.isEmpty(s))
					list.add(new FieldErr("ReminderDay", "ReminderDayû����д"));
			}
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
	
	/**
	 * ǰ̨�������б����ݵķָ�����||;;
	 */
	public static final String splitter = "\\|\\|;;";
	
	public static final String fieldNameSplitter = ",";
	private static final String contactListFields1 = "contactlistFields";
	private static final String contactListFields2 = "consigneelistFields";
	private static final String bankListFields = "custbanklistFields";
	
	/**
	 * �����ϴ���List���ݡ���String����ת��ΪList
	 */
	@SuppressWarnings("unchecked")
	public static void processListData(CustomerInfo custInfo, HttpServletRequest request) throws Exception
	{
		// Done: �����ϴ���List����
		
			Integer id = custInfo.getBaseSeqId();
			String baseSeqId = id == null ? null : id.toString();
			String commCode = custInfo.getCommCode();
			String state = custInfo.getState();
			List<ContactInfo> listAll = new ArrayList<ContactInfo>();
			
				logger.debug("��ϵ����Ϣֵ���ݣ�" + request.getParameterValues(contactListName1));
				logger.debug("��ϵ����Ϣ�ֶ�ֵ��"+request.getParameter(contactListFields1));
				
				List<ContactInfo> list = ListDataUtil.splitIntoList(ContactInfo.class, 
						request.getParameterValues(contactListName1), 
						request.getParameter(contactListFields1).split(fieldNameSplitter), 
						splitter);
				if(null != list && list.size()>0 ){
					logger.debug("��ϵ����Ϣ����Ϊ��" + list.size());
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
				}else{
					logger.debug("��ϵ����Ϣ����Ϊ��0");
				}
			
			
			
				logger.debug("�ջ�����Ϣֵ���ݣ�" + request.getParameterValues(contactListName2));
				logger.debug("�ջ�����Ϣ�ֶ�ֵ��"+request.getParameter(contactListFields2));
				List<ContactInfo> listCon = ListDataUtil.splitIntoList(ContactInfo.class, 
						request.getParameterValues(contactListName2), 
						request.getParameter(contactListFields2).split(fieldNameSplitter), 
						splitter);
				if(null != listCon && listCon.size()>0 ){
					logger.debug("�ջ�����Ϣ����Ϊ��" + list.size());
					Iterator<ContactInfo> it = listCon.iterator();
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
					listAll.addAll(listCon);
				}else{
					logger.debug("�ջ�����Ϣ����Ϊ��0");
				}
			
			if(listAll.size()>0)
				custInfo.setListContactInfo(listAll);
			
			
				logger.debug("������Ϣֵ���ݣ�" + request.getParameterValues(bankListName));
				logger.debug("������Ϣ�ֶ�ֵ��"+request.getParameter(bankListFields));
				List<BankInfo> listBank = ListDataUtil.splitIntoList(BankInfo.class, 
						request.getParameterValues(bankListName), 
						request.getParameter(bankListFields).split(fieldNameSplitter), 
						splitter);
				if(null != listBank && listBank.size() >0){
					logger.debug("������Ϣ����Ϊ��" + listBank.size());
					Iterator<BankInfo> it = listBank.iterator();
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
						custInfo.setListBankInfo(listBank);
				}else{
					logger.debug("������Ϣ����Ϊ��0");
				}
			
		
	}
	
	/**
	 * ���ѡ�����ݡ����ѡ��ֵ����дѡ���˵���ֶ�
	 * @param custInfo
	 */
	public static List<FieldErr> checkSelectFields(CustomerInfo custInfo) throws Exception
	{
		if(custInfo == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		
			String s;
			ConfigEncode ce;
			s = custInfo.getImportantCode();
			if(StringUtils.isNotEmpty(s))
			{
				ce = getEncode("IMPORTANT_CODE", s);
				if(ce == null)
					list.add(new FieldErr("importantCode", "importantCode��ֵ����ȷ"));
				else
				{
					
					custInfo.setImportantDesc(ce.getEncodeValue());
				}
			}
				
			s = custInfo.getCreditRate();
			// û����дCreditRate��ȱʡΪһ�㣨3��
			if(StringUtils.isEmpty(s))
				s = "3";
			//if(StringUtils.isNotEmpty(s))
			{
				ce = getEncode("CREDIT_RATE", s);
				if(ce == null)
					list.add(new FieldErr("CreditRate", "CreditRate��ֵ����ȷ"));
				else
				{					
					custInfo.setCreditDesc(ce.getEncodeValue());
				}
			}
			
			s = custInfo.getSettlementType();
			if(StringUtils.isNotEmpty(s))
			{
				ce = getEncode("SETTLEMENT_TYPE", s);
				if(ce == null)
					list.add(new FieldErr("SettlementType", "SettlementType��ֵ����ȷ"));
				else{					
					custInfo.setSettlementDesc(ce.getEncodeValue());
				}
			}
			
			s = custInfo.getCurrency();
			if(StringUtils.isNotEmpty(s))
			{
				ce = getEncode("CURRENCY", s);
				if(ce == null)
					list.add(new FieldErr("Currency", "Currency��ֵ����ȷ"));
				else{
					
					custInfo.setCurrencyDesc(ce.getEncodeValue());
				}
			}
			
			s = custInfo.getIsShowPrice();
			if(StringUtils.isNotEmpty(s))
			{
				ce = getEncode("IS_SHOW_PRICE", s);
				if(ce == null)
					list.add(new FieldErr("IsShowPrice", "IsShowPrice��ֵ����ȷ"));
				
			}

			s = custInfo.getStaffId();
			if(StringUtils.isNotEmpty(s))
			{
				//DONE���û���Ϣ��Ҫ����
				Staff u = StaffUtil.getStaffById(s);
				if(u == null)
					list.add(new FieldErr("StaffId", "StaffId����"));
				else
					custInfo.setStaffName(u.getStaffName());
			}
			s = custInfo.getAssStaffId();
			if(StringUtils.isNotEmpty(s))
			{
				//DONE���û���Ϣ��Ҫ����
				Staff u = StaffUtil.getStaffById(s);
				if(u == null)
					list.add(new FieldErr("AssStaffId", "AssStaffId����"));
				else
					custInfo.setAssStaffName(u.getStaffName());
			}
		
		
		return list;
	}
	
	/**
	 * ��ȡ���롣���s���Խ���Ϊ��ֵ����s����key��������value
	 * @param type
	 * @param s
	 * @return �����������Ҳ������򷵻�null
	 */
	public static ConfigEncode getEncode(String type, String s)
	{
		ConfigEncode ce = new ConfigEncode();
		
		ce.setEncodeType(type);		
		ce.setEncodeKey(s);
		
		
		ce = ConfigEncodeMgr.getConfigEncode(ce);
		
		return ce;
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
