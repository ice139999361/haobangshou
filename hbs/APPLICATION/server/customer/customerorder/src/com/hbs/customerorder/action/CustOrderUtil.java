/**
 * 
 */
package com.hbs.customerorder.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerinfo.action.CustomerInfoUtil;
import com.hbs.customerinfo.constants.CustInfoConstants;
import com.hbs.customerinfo.manager.CustPartNoInfoMgr;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

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
			// vendorCode不再保存在custInfo中了，应该从物料关联中获取
			//String vendorCode = custOrder.getVendorCode();

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
				info.setTaxRate((BigDecimal)otherData.get("taxRate"));
				// vendorCode不再保存在custInfo中了，应该从物料关联中获取
				//info.setVendorCode(vendorCode);
				try{
					if(StringUtils.isNotEmpty(info.getCpartNo())){
						CustPartNoInfoMgr pninfoMgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTPARTNOINFOMGR);
						CustPartNoInfo pninfo = new CustPartNoInfo();
						pninfo.setCommCode(commCode);
						pninfo.setCustPartNo(info.getCpartNo());
						pninfo.setState("0");
						pninfo = pninfoMgr.getCustPartNoInfoByBizKey(pninfo);
						if(pninfo != null && StringUtils.isNotEmpty(pninfo.getVendorCode()))
							info.setVendorCode(pninfo.getVendorCode());
						else
							logger.error("获取VendorCode失败：" + info.toString());
					}
				}catch(Exception e){
					logger.error("catch Exception in processListData.", e);
				}
			}
			custOrder.setOrderDetailList(list);
		} catch (Exception e) {
			logger.info("processListData处理detailList出错", e);
		}
	}

	/**
	 * 判断commCode是否存在，并且复制一些数据。
	 * @param custOrder
	 * @param otherData 存储一些客户信息，以备其他函数使用
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
					otherData.put("taxRate", custInfo.getTaxRate());
					otherData.put("custInfo", custInfo);
				} else
					logger.info("checkCommCode : otherData is null");
				
				if(StringUtils.isEmpty(custOrder.getSettlementType())) {
					custOrder.setSettlementType(custInfo.getSettlementType());
					custOrder.setShortName(custInfo.getShortName());
					// vendorCode不再保存在custInfo中了，应该从物料关联中获取
					//custOrder.setVendorCode(custInfo.getVendorCode());
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
			list.add(new FieldErr("PoNo","客户订单号没有填写"));
		}
		if(null == custOrder.getOderTime())
			list.add(new FieldErr("OrderTime","客户订单日期没有填写"));
		

		if(StringUtils.isEmpty(custOrder.getConName()))
			list.add(new FieldErr("ConName", "联系人没有填写"));
		if(StringUtils.isEmpty(custOrder.getConTel()))
			list.add(new FieldErr("ConTel", "联系电话没有填写"));
		
		if(StringUtils.isEmpty(custOrder.getReceiveName()))
			list.add(new FieldErr("ReceiveName", "收货人没有填写"));
		if(StringUtils.isEmpty(custOrder.getReceiveAddress()))
			list.add(new FieldErr("ReceiveAddress", "收货地址没有填写"));
		
		List<CustOrderDetail> details = custOrder.getOrderDetailList();
		if(null != details && details.size() > 0) {
			for(CustOrderDetail info : details) {
				if(StringUtils.isEmpty(info.getPartNo()))
					list.add(new FieldErr("PartNo", "GLE型号没有填写"));
				else {
					/*
					// DONE:复制价格等数据，这些信息都从前台传来，不需要在此设置
					if( null == info.getCprice() && StringUtils.isNotEmpty(info.getCpartNo())) {
						CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean("custPartNoInfoMgr");
						CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
						CustPartNoInfo custPartNoInfo2 = null;
						custPartNoInfo.setCommCode(info.getCommCode());
						custPartNoInfo.setPartNo(info.getPartNo());
						//custPartNoInfo.setState("0");
						custPartNoInfo.setCustPartNo(info.getCpartNo());
						try {
							logger.info("in: " + custPartNoInfo.getLogBizKey());
							custPartNoInfo2 = mgr.getCustPartNoInfoByBizKey(custPartNoInfo);
							logger.info("out: " + custPartNoInfo2.getLogContent());
						} catch (Exception e) {
							logger.info("err:", e);
						}
						if(custPartNoInfo2 != null) {
							info.setCprice(custPartNoInfo2.getPrice());
							info.setCpriceTax(custPartNoInfo2.getPriceTax());
							info.setPnDesc(custPartNoInfo2.getPnDesc());
						} else {
							list.add(new FieldErr("CpartNo", "客户型号无法找到"));
						}
					}
					*/
				}
				if(info.getCprice() == null)
					list.add(new FieldErr("Cprice", "单价没有填写"));
				if(info.getCpriceTax() == null)
					list.add(new FieldErr("CpriceTax", "税率没有填写"));
				if(StringUtils.isEmpty(info.getCpartNo()))
					list.add(new FieldErr("CPartNo", "客户型号没有填写"));
				
				if(StringUtils.isEmpty(info.getIsTax())){
					//list.add(new FieldErr("IsTax", "IsTax没有填写"));
					/*
					if(otherData == null)
						list.add(new FieldErr("IsTax", "IsTax没有填写"));
					else{
						info.setIsTax((0 == BigDecimal.ZERO.compareTo((BigDecimal)(otherData.get("taxRate"))))?"0":"1");
					}
					*/
					info.setIsTax( (0 == BigDecimal.ZERO.compareTo(info.getCpriceTax())) ? "0" : "1");
				}else{
					if(0 != BigDecimal.ZERO.compareTo(info.getCpriceTax()) && info.getIsTax().equals("0"))
						list.add(new FieldErr("IsTax", "是否含税交易错误"));
				}
				Integer money = info.getAmount();
				if(null == money)
					list.add(new FieldErr("Amount", "数量没有填写"));
				/*
				else
					try {
						info.setMoney(info.getCprice().multiply(new BigDecimal(money.intValue())));
					} catch (Exception e) {
						info.setMoney(new BigDecimal(money.intValue()));
					}
				*/
				if(info.getMoney() == null)
					list.add(new FieldErr("Money", "金额没有填写"));
				if(info.getOrgDeliveryDate() == null)
					list.add(new FieldErr("OrgDeliveryDate", "交货日期没有填写"));
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
			@SuppressWarnings("unused")
			String s;
			// DONE:CustOrderUtil.checkSelectFields
			@SuppressWarnings("unused")
			int i;

		}
		catch(Exception e)
		{
			logger.info("checkSelectFields出错", e);
		}
		
		return list;
	}
	
	
}
