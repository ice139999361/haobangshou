/**
 * 
 */
package com.hbs.customerorder.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerinfo.action.CustomerInfoUtil;
import com.hbs.customerinfo.constants.CustInfoConstants;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xyf
 *
 */
public class CustOrderUtil {
	
	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(CustOrderUtil.class);
	private static final String detailListName = "orderlist";
	private static final String detailListFields = "orderlistFields";

	/**
	 * 处理录入上传的列表数据
	 * @param custOrder
	 * @param request
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	public static void processListData(CustomerOrder custOrder, HttpServletRequest request, Map otherData) throws Exception
	{
		try {
			List<CustOrderDetail> list = ListDataUtil.splitIntoList(CustOrderDetail.class, 
				request.getParameterValues(detailListName), 
				request.getParameter(detailListFields).split(CustomerInfoUtil.fieldNameSplitter), 
				CustomerInfoUtil.splitter);
			
			String commCode = custOrder.getCommCode();
			String state = custOrder.getState();
			String poNo = custOrder.getPoNo();
			String poNoType = custOrder.getPoNoType();
			String shortName = custOrder.getShortName();
			String sales = custOrder.getSales();
			String salesId = custOrder.getSalesId();
			String staffId = custOrder.getStaffId();
			String staffName = custOrder.getStaffName();
			String settlementType = custOrder.getSettlementType();
			String vendorCode = custOrder.getVendorCode();

			Iterator<CustOrderDetail> it = list.iterator();
			while(it.hasNext())
			{
				CustOrderDetail info = it.next();
				if(info == null)
					continue;
				
				//Done:ContactFee如何获取
				if(otherData != null)
					info.setContactFee((BigDecimal)otherData.get("contactFee"));
				
				info.setCommCode(commCode);
				info.setState(state);
				info.setPoNo(poNo);
				info.setPoNoType(poNoType);
				info.setShortName(shortName);
				info.setSales(sales);
				info.setSalesId(salesId);
				info.setStaffId(staffId);
				info.setStaffName(staffName);
				info.setSettlementType(settlementType);
				info.setVendorCode(vendorCode);
			}
			custOrder.setOrderDetailList(list);
		} catch (Exception e) {
			logger.info("processListData处理detailList出错", e);
		}
	}

	/**
	 * 判断commCode是否存在，并且复制一些数据。
	 * @param custOrder
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean checkCommCode(CustomerOrder custOrder, Map otherData) {
		try {
			if(custOrder == null)
				return false;
			
			String s = custOrder.getCommCode();
			if(StringUtils.isEmpty(s))
				return false;
			
			CustomerInfo custInfo = new CustomerInfo();
			custInfo.setCommCode(s);
			custInfo.setState("0");
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTINFOMGR);
			custInfo = mgr.getCustomerInfo(custInfo, false);
			if(custInfo != null)
			{
				if(otherData != null) {
					otherData.put("contactFee", custInfo.getContactFee());
					otherData.put("custInfo", custInfo);
				} else
					logger.info("checkCommCode : otherData is null");
				
				if(StringUtils.isEmpty(custOrder.getSettlementType())) {
					custOrder.setSettlementType(custInfo.getSettlementType());
					custOrder.setShortName(custInfo.getShortName());
					custOrder.setVendorCode(custInfo.getVendorCode());
					custOrder.setCompanyBranch(custInfo.getCompanyBranch());
					custOrder.setIsShowPrice(custInfo.getIsShowPrice());
					custOrder.setSalesId(custInfo.getStaffId());
					custOrder.setSales(custInfo.getStaffName());
				}
				return true;
			}else{
				return false;
			}
		
		} catch (Exception e) {
			logger.error("catch Exception in checkCommCode", e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<FieldErr> checkInputFields(CustomerOrder custOrder, Map otherData) {
		if(custOrder == null)
			return new ArrayList<FieldErr>();
		
		List<FieldErr> list = checkSelectFields(custOrder);
		if(list == null)
			list = new ArrayList<FieldErr>();
		
		String s;
		s = custOrder.getPoNo();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("PoNo","PoNo没有填写"));
		}
		if(null == custOrder.getOderTime())
			list.add(new FieldErr("OrderTime","OrderTime没有填写"));
		
		/*
		//TODO:选择联系人
		if(StringUtils.isEmpty(custOrder.getConName()))
			list.add(new FieldErr("ConName", "ConName没有填写"));
		
		if(StringUtils.isEmpty(custOrder.getReceiveName()))
			list.add(new FieldErr("ReceiveName", "ReceiveName没有填写"));
		 */
		
		List<CustOrderDetail> details = custOrder.getOrderDetailList();
		if(null != details && details.size() > 0) {
			for(CustOrderDetail info : details) {
				if(StringUtils.isEmpty(info.getPartNo()))
					list.add(new FieldErr("PartNo", "PartNo没有填写"));
				if(StringUtils.isEmpty(info.getCpartNo()))
					list.add(new FieldErr("CPartNo", "CPartNo没有填写"));
				if(StringUtils.isEmpty(info.getIsTax()))
					list.add(new FieldErr("IsTax", "IsTax没有填写"));
				Integer money = info.getAmount();
				if(null == money)
					list.add(new FieldErr("Amount", "Amount没有填写"));
				else
					try {
						info.setMoney(info.getCprice().multiply(new BigDecimal(money.intValue())));
					} catch (Exception e) {
						info.setMoney(new BigDecimal(money.intValue()));
					}
			}
		}
		//DONE:CustOrderUtil.checkInputFields
		return list;
	}

	private static List<FieldErr> checkSelectFields(CustomerOrder custOrder) {
		if(custOrder == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		try
		{
			String s;
			// TODO:CustOrderUtil.checkSelectFields
			int i;

		}
		catch(Exception e)
		{
			logger.info("checkSelectFields出错", e);
		}
		
		return list;
	}
	
	
}
