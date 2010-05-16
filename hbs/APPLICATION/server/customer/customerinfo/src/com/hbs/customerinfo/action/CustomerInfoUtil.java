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
	 * 判断是否填写了key字段。custInfo.baseSeqId 或 custInfo.commCode
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
	 * 对输入的客户信息进行校验，内部调用checkSelectFields。
	 * @param custInfo	客户信息
	 * @return 出错信息，格式：Map<出错字段,出错信息>
	 */
	public static List<FieldErr> checkInputFields(CustomerInfo custInfo) throws Exception
	{
		if(custInfo == null)
			return new ArrayList<FieldErr>();
		
		List<FieldErr> list = checkSelectFields(custInfo);
		if(list == null)
			list = new ArrayList<FieldErr>();
		
		String s;
		// DONE:完成checkInputFields，对输入的客户信息进行校验
		s = custInfo.getVendorCode();
//		if(StringUtils.isEmpty(s))
//		{
//			list.add(new FieldErr("venderCode","venderCode没有填写"));
//		}
		s = custInfo.getCommCode();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("CommCode","CommCode没有填写"));
		}
		s = custInfo.getShortName();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("ShortName","ShortName没有填写"));
		}
		s = custInfo.getAllName();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("AllName","AllName没有填写"));
		}
		s = custInfo.getIsShowPrice();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("IsShowPrice","IsShowPrice没有填写"));
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
			list.add(new FieldErr("AssStaff","AssStaff没有填写"));
		}
		s = custInfo.getSettlementType();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("SettlementType","SettlementType没有填写"));
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
			accountPreiod.setAccounDay("1");//对账日，缺省1
			accountPreiod.setAccountType("1");//账期类型，缺省1 月结
			accountPreiod.setAccountPeriod("1"); //账期  缺省1  一个月
		}
		PrePaidInfo prePaidInfo = custInfo.getPrePaidInfo();
		if(prePaidInfo != null) {
			prePaidInfo.setCommCode(custInfo.getCommCode());
			prePaidInfo.setState(custInfo.getState());
			prePaidInfo.setBaseSeqId(baseSeqId);
			
			// 处理prepaid的reminderday
			if(custInfo.getSettlementType().equals("2")) {
				s = prePaidInfo.getReminderDay();
				if(StringUtils.isEmpty(s))
					list.add(new FieldErr("ReminderDay", "ReminderDay没有填写"));
			}
		}

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
	
	/**
	 * 前台传来的列表数据的分隔符：||;;
	 */
	public static final String splitter = "\\|\\|;;";
	
	public static final String fieldNameSplitter = ",";
	private static final String contactListFields1 = "contactlistFields";
	private static final String contactListFields2 = "consigneelistFields";
	private static final String bankListFields = "custbanklistFields";
	
	/**
	 * 处理上传的List数据。将String数组转换为List
	 */
	@SuppressWarnings("unchecked")
	public static void processListData(CustomerInfo custInfo, HttpServletRequest request) throws Exception
	{
		// Done: 处理上传的List数据
		
			Integer id = custInfo.getBaseSeqId();
			String baseSeqId = id == null ? null : id.toString();
			String commCode = custInfo.getCommCode();
			String state = custInfo.getState();
			List<ContactInfo> listAll = new ArrayList<ContactInfo>();
			
				logger.debug("联系人信息值数据：" + request.getParameterValues(contactListName1));
				logger.debug("联系人信息字段值："+request.getParameter(contactListFields1));
				
				List<ContactInfo> list = ListDataUtil.splitIntoList(ContactInfo.class, 
						request.getParameterValues(contactListName1), 
						request.getParameter(contactListFields1).split(fieldNameSplitter), 
						splitter);
				if(null != list && list.size()>0 ){
					logger.debug("联系人信息数量为：" + list.size());
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
					logger.debug("联系人信息数量为：0");
				}
			
			
			
				logger.debug("收货人信息值数据：" + request.getParameterValues(contactListName2));
				logger.debug("收货人信息字段值："+request.getParameter(contactListFields2));
				List<ContactInfo> listCon = ListDataUtil.splitIntoList(ContactInfo.class, 
						request.getParameterValues(contactListName2), 
						request.getParameter(contactListFields2).split(fieldNameSplitter), 
						splitter);
				if(null != listCon && listCon.size()>0 ){
					logger.debug("收货人信息数量为：" + list.size());
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
					logger.debug("收货人信息数量为：0");
				}
			
			if(listAll.size()>0)
				custInfo.setListContactInfo(listAll);
			
			
				logger.debug("银行信息值数据：" + request.getParameterValues(bankListName));
				logger.debug("银行信息字段值："+request.getParameter(bankListFields));
				List<BankInfo> listBank = ListDataUtil.splitIntoList(BankInfo.class, 
						request.getParameterValues(bankListName), 
						request.getParameter(bankListFields).split(fieldNameSplitter), 
						splitter);
				if(null != listBank && listBank.size() >0){
					logger.debug("银行信息数量为：" + listBank.size());
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
					logger.debug("银行信息数量为：0");
				}
			
		
	}
	
	/**
	 * 检查选择数据。检查选项值，填写选项的说明字段
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
					list.add(new FieldErr("importantCode", "importantCode的值不正确"));
				else
				{
					
					custInfo.setImportantDesc(ce.getEncodeValue());
				}
			}
				
			s = custInfo.getCreditRate();
			// 没有填写CreditRate，缺省为一般（3）
			if(StringUtils.isEmpty(s))
				s = "3";
			//if(StringUtils.isNotEmpty(s))
			{
				ce = getEncode("CREDIT_RATE", s);
				if(ce == null)
					list.add(new FieldErr("CreditRate", "CreditRate的值不正确"));
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
					list.add(new FieldErr("SettlementType", "SettlementType的值不正确"));
				else{					
					custInfo.setSettlementDesc(ce.getEncodeValue());
				}
			}
			
			s = custInfo.getCurrency();
			if(StringUtils.isNotEmpty(s))
			{
				ce = getEncode("CURRENCY", s);
				if(ce == null)
					list.add(new FieldErr("Currency", "Currency的值不正确"));
				else{
					
					custInfo.setCurrencyDesc(ce.getEncodeValue());
				}
			}
			
			s = custInfo.getIsShowPrice();
			if(StringUtils.isNotEmpty(s))
			{
				ce = getEncode("IS_SHOW_PRICE", s);
				if(ce == null)
					list.add(new FieldErr("IsShowPrice", "IsShowPrice的值不正确"));
				
			}

			s = custInfo.getStaffId();
			if(StringUtils.isNotEmpty(s))
			{
				//DONE：用户信息需要处理
				Staff u = StaffUtil.getStaffById(s);
				if(u == null)
					list.add(new FieldErr("StaffId", "StaffId错误"));
				else
					custInfo.setStaffName(u.getStaffName());
			}
			s = custInfo.getAssStaffId();
			if(StringUtils.isNotEmpty(s))
			{
				//DONE：用户信息需要处理
				Staff u = StaffUtil.getStaffById(s);
				if(u == null)
					list.add(new FieldErr("AssStaffId", "AssStaffId错误"));
				else
					custInfo.setAssStaffName(u.getStaffName());
			}
		
		
		return list;
	}
	
	/**
	 * 获取编码。如果s可以解析为数值，则将s当作key，否则当作value
	 * @param type
	 * @param s
	 * @return 编码对象，如果找不到，则返回null
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
	 * 获取客户信息详情，将联系人与收货人分开。
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
