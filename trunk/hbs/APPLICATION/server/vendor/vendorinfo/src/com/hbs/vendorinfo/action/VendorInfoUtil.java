package com.hbs.vendorinfo.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.Staff;
import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.common.utils.StaffUtil;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendorinfo.manager.VendorInfoMgr;

/**
 * Action中对CustomerInfo的一些通用处理函数集
 * @author xyf
 *
 */
public class VendorInfoUtil {

	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorInfoUtil.class);
    
	/**
	 * 判断是否填写了key字段。vendorInfo.baseSeqId 或 (vendorInfo.commCode + vendorInfo.state)
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
	 * 对输入的客户信息进行校验，内部调用checkSelectFields。
	 * @param vendorInfo	客户信息
	 * @return 出错信息，格式：Map<出错字段,出错信息>
	 */
	public static List<FieldErr> checkInputFields(VendorInfo vendorInfo)
	{
		if(vendorInfo == null)
			return new ArrayList<FieldErr>();
		
		List<FieldErr> list = checkSelectFields(vendorInfo);
		if(list == null)
			list = new ArrayList<FieldErr>();
		
		String s;
		// DONE:完成checkInputFields，对输入的客户信息进行校验
		s = vendorInfo.getVendorCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("venderCode","venderCode没有填写"));
		}
		s = vendorInfo.getCommCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CommCode","CommCode没有填写"));
		}
		s = vendorInfo.getShortName();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("ShortName","ShortName没有填写"));
		}
		s = vendorInfo.getAllName();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("AllName","AllName没有填写"));
		}
		s = vendorInfo.getIsShowPrice();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("IsShowPrice","IsShowPrice没有填写"));
		}
		
		String baseSeqId = null;
		try {
			baseSeqId = vendorInfo.getBaseSeqId().toString();
		} catch (Exception e) {
		}
		AccountPreiod accountPreiod = vendorInfo.getAccountPreiod();
		if(accountPreiod != null) {
			accountPreiod.setCommCode(vendorInfo.getCommCode());
			accountPreiod.setState(vendorInfo.getState());
			accountPreiod.setBaseSeqId(baseSeqId);
		}
		PrePaidInfo prePaidInfo = vendorInfo.getPrePaidInfo();
		if(prePaidInfo != null)
			prePaidInfo.setCommCode(vendorInfo.getCommCode());
			prePaidInfo.setState(vendorInfo.getState());
			prePaidInfo.setBaseSeqId(baseSeqId);
		
		return list;
	}
		
	/**
	 * 判断是否需要设置staffId
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
	
	/**
	 * 处理上传的List数据。将String数组转换为List
	 */
	@SuppressWarnings("unchecked")
	public static void processListData(VendorInfo vendorInfo, HttpServletRequest request) throws Exception
	{
		// Done: 处理上传的List数据
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
				logger.info("processListData处理contactList1出错", e);
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
				logger.info("processListData处理contactList2出错", e);
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
				logger.info("processListData处理bankList出错", e);
			}
		}
		catch(Exception e)
		{
			logger.info("processListData出错", e);
		}
	}
	
	/**
	 * 检查选择数据。检查选项值，填写选项的说明字段
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
					list.add(new FieldErr("importantCode", "importantCode的值不正确"));
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
					list.add(new FieldErr("CreditRate", "CreditRate的值不正确"));
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
					list.add(new FieldErr("SettlementType", "SettlementType的值不正确"));
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
					list.add(new FieldErr("Currency", "Currency的值不正确"));
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
					list.add(new FieldErr("IsShowPrice", "IsShowPrice的值不正确"));
				else
				{
					vendorInfo.setIsShowPrice(ce.getEncodeKey());
				}
			}

			s = vendorInfo.getStaffId();
			if(StringUtils.isNotEmpty(s))
			{
				Staff u = StaffUtil.getStaffById(s);
				if(u == null)
					list.add(new FieldErr("StaffId", "StaffId错误"));
				else
					vendorInfo.setStaffName(u.getStaffName());
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
		if(s.equals("请选择"))
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
	 * 获取供应商信息详情，将联系人与收货人分开。
	 * @param mgr
	 * @param vendorInfo
	 * @return
	 */
	public static VendorInfo getVendorInfo(VendorInfoMgr mgr, VendorInfo vendorInfo) {
		try {
			vendorInfo = mgr.getVendorInfo(vendorInfo, true);
			if(vendorInfo == null || vendorInfo.getListContactInfo() == null)
				return vendorInfo;
			Iterator<ContactInfo> it = vendorInfo.getListContactInfo().iterator();
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
			vendorInfo.setField(contactListName1, l1);
			vendorInfo.setField(contactListName2, l2);
		} catch (Exception e) {
		}
		return vendorInfo;
	}
}
