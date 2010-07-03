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
	 * ����¼���ϴ����б�����
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
			// vendorCode���ٱ�����custInfo���ˣ�Ӧ�ô����Ϲ����л�ȡ
			//String vendorCode = custOrder.getVendorCode();

			Iterator<CustOrderDetail> it = list.iterator();
			while(it.hasNext())
			{
				CustOrderDetail info = it.next();
				if(info == null)
					continue;
				
				//Done:ContactFee��λ�ȡ
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
				// vendorCode���ٱ�����custInfo���ˣ�Ӧ�ô����Ϲ����л�ȡ
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
							logger.error("��ȡVendorCodeʧ�ܣ�" + info.toString());
					}
				}catch(Exception e){
					logger.error("catch Exception in processListData.", e);
				}
			}
			custOrder.setOrderDetailList(list);
		} catch (Exception e) {
			logger.info("processListData����detailList����", e);
		}
	}

	/**
	 * �ж�commCode�Ƿ���ڣ����Ҹ���һЩ���ݡ�
	 * @param custOrder
	 * @param otherData �洢һЩ�ͻ���Ϣ���Ա���������ʹ��
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
					// vendorCode���ٱ�����custInfo���ˣ�Ӧ�ô����Ϲ����л�ȡ
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
			list.add(new FieldErr("PoNo","�ͻ�������û����д"));
		}
		if(null == custOrder.getOderTime())
			list.add(new FieldErr("OrderTime","�ͻ���������û����д"));
		

		if(StringUtils.isEmpty(custOrder.getConName()))
			list.add(new FieldErr("ConName", "��ϵ��û����д"));
		if(StringUtils.isEmpty(custOrder.getConTel()))
			list.add(new FieldErr("ConTel", "��ϵ�绰û����д"));
		
		if(StringUtils.isEmpty(custOrder.getReceiveName()))
			list.add(new FieldErr("ReceiveName", "�ջ���û����д"));
		if(StringUtils.isEmpty(custOrder.getReceiveAddress()))
			list.add(new FieldErr("ReceiveAddress", "�ջ���ַû����д"));
		
		List<CustOrderDetail> details = custOrder.getOrderDetailList();
		if(null != details && details.size() > 0) {
			for(CustOrderDetail info : details) {
				if(StringUtils.isEmpty(info.getPartNo()))
					list.add(new FieldErr("PartNo", "GLE�ͺ�û����д"));
				else {
					/*
					// DONE:���Ƽ۸�����ݣ���Щ��Ϣ����ǰ̨����������Ҫ�ڴ�����
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
							list.add(new FieldErr("CpartNo", "�ͻ��ͺ��޷��ҵ�"));
						}
					}
					*/
				}
				if(info.getCprice() == null)
					list.add(new FieldErr("Cprice", "����û����д"));
				if(info.getCpriceTax() == null)
					list.add(new FieldErr("CpriceTax", "˰��û����д"));
				if(StringUtils.isEmpty(info.getCpartNo()))
					list.add(new FieldErr("CPartNo", "�ͻ��ͺ�û����д"));
				
				if(StringUtils.isEmpty(info.getIsTax())){
					//list.add(new FieldErr("IsTax", "IsTaxû����д"));
					/*
					if(otherData == null)
						list.add(new FieldErr("IsTax", "IsTaxû����д"));
					else{
						info.setIsTax((0 == BigDecimal.ZERO.compareTo((BigDecimal)(otherData.get("taxRate"))))?"0":"1");
					}
					*/
					info.setIsTax( (0 == BigDecimal.ZERO.compareTo(info.getCpriceTax())) ? "0" : "1");
				}else{
					if(0 != BigDecimal.ZERO.compareTo(info.getCpriceTax()) && info.getIsTax().equals("0"))
						list.add(new FieldErr("IsTax", "�Ƿ�˰���״���"));
				}
				Integer money = info.getAmount();
				if(null == money)
					list.add(new FieldErr("Amount", "����û����д"));
				/*
				else
					try {
						info.setMoney(info.getCprice().multiply(new BigDecimal(money.intValue())));
					} catch (Exception e) {
						info.setMoney(new BigDecimal(money.intValue()));
					}
				*/
				if(info.getMoney() == null)
					list.add(new FieldErr("Money", "���û����д"));
				if(info.getOrgDeliveryDate() == null)
					list.add(new FieldErr("OrgDeliveryDate", "��������û����д"));
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
			logger.info("checkSelectFields����", e);
		}
		
		return list;
	}
	
	
}
