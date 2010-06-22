package com.hbs.vendororder.action;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerinfo.action.CustomerInfoUtil;
import com.hbs.customerorder.action.detail.CustOrderDetailBaseAction;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendorinfo.action.VendorInfoNormalAction;
import com.hbs.vendorinfo.manager.VendorInfoMgr;
import com.hbs.vendororder.action.detail.VendorOrderDetailBaseAction;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.manager.VendorOrderDetailMgr;

public class VendorOrderUtil {
	private static final String detailListName = "orderlist";
	private static final String detailListFields = "orderlistFields";
	
	private static final Logger logger = Logger.getLogger(VendorOrderUtil.class);
	
	@SuppressWarnings("unchecked")
	public static boolean checkCommCode(VendorOrder vendorOrder, Map otherData) throws Exception 
	{
		if(vendorOrder == null)
			return false;
		String commCode = vendorOrder.getCommCode();
		if(StringUtils.isEmpty(commCode))
			return false;
		VendorInfoMgr mgr = (VendorInfoMgr)BeanLocator.getInstance().getBean(VendorInfoNormalAction.vendorInfoMgrName);
		VendorInfo vInfo = new VendorInfo();
		vInfo.setCommCode(commCode);
		vInfo.setState("0");
		vInfo = mgr.getVendorInfo(vInfo, true);
		if(vInfo == null)
			return false;
		if(otherData != null)
			otherData.put("vendorInfo", vInfo);
		return true;
	}

	@SuppressWarnings("unchecked")
	public static List<FieldErr> checkInputFields(VendorOrder vendorOrder,
			Map otherData) 
	{
		@SuppressWarnings("unused")
		VendorInfo vInfo = (VendorInfo)otherData.get("vendorInfo");
		
		List<FieldErr> errs = new Vector<FieldErr>();
		if(StringUtils.isEmpty(vendorOrder.getCommCode()))
			errs.add(new FieldErr("CommCode", "��Ӧ�̱���û����д"));
		if(StringUtils.isEmpty(vendorOrder.getConName()))
			errs.add(new FieldErr("ConName", "��ϵ��û����д"));
		if(StringUtils.isEmpty(vendorOrder.getConTel()))
			errs.add(new FieldErr("ConTel", "��ϵ�绰û����д"));
		if(StringUtils.isEmpty(vendorOrder.getReceiveName()))
			errs.add(new FieldErr("ReceiveName", "�ջ���û����д"));
		if(StringUtils.isEmpty(vendorOrder.getReceiveAddress()))
			errs.add(new FieldErr("ReceiveAddress", "�ջ���ַû����д"));
		if(StringUtils.isEmpty(vendorOrder.getSettlementType()))
			errs.add(new FieldErr("SettlementType", "���㷽ʽû����д"));
		if(StringUtils.isEmpty(vendorOrder.getCompanyBranch()))
			errs.add(new FieldErr("CompanyBranch", "��Ӧ�ֹ�˾û����д"));
		if(StringUtils.isEmpty(vendorOrder.getIsShowPrice()))
			errs.add(new FieldErr("IsShowPrice", "�Ƿ���ʾ�۸�û����д"));
		
		if(vendorOrder.getVendorOrderDetailList() != null && vendorOrder.getVendorOrderDetailList().size() > 0)
		for(VendorOrderDetail info : vendorOrder.getVendorOrderDetailList()) {
			if(info == null)
				continue;
			if(StringUtils.isEmpty(info.getCpartNo()))
				errs.add(new FieldErr("CpartNo", "��Ӧ���ͺ�û����д"));
			if(StringUtils.isEmpty(info.getPartNo()))
				errs.add(new FieldErr("PartNo", "GLE�ͺ�û����д"));
			if(info.getAmount() == null)
				errs.add(new FieldErr("Amount", "����û����д"));
			if(info.getMoney() == null)
				errs.add(new FieldErr("Money", "���û����д"));
			if(StringUtils.isEmpty(info.getIsTax())){
				errs.add(new FieldErr("IsTax", "�Ƿ�˰����û����д"));
			}else{
				if(info.getCpriceTax() != null && info.getCpriceTax().compareTo(new BigDecimal(0)) != 0 && info.getIsTax() == "0"){
					errs.add(new FieldErr("IsTax", "�Ƿ�˰���״���"));
					
				}
			}
		}
		
		// TODO:VendorOrderUtil.checkInputFields
		return errs;
	}

	@SuppressWarnings("unchecked")
	public static void processListData(VendorOrder vendorOrder,
			HttpServletRequest request, Map otherData) 
	{
		try {
			List<VendorOrderDetail> list = ListDataUtil.splitIntoList(VendorOrderDetail.class, 
				request.getParameterValues(detailListName), 
				request.getParameter(detailListFields).split(CustomerInfoUtil.fieldNameSplitter), 
				CustomerInfoUtil.splitter);
			if(list == null || list.size() <= 0)
				return;
			
			String commCode = vendorOrder.getCommCode();
			VendorInfo vInfo = (VendorInfo)otherData.get("vendorInfo");
			String state = vendorOrder.getState();
			String poNo = vendorOrder.getPoNo();
			String poNoType = vendorOrder.getPoNoType();
			String staffId = vendorOrder.getStaffId();
			String staffName = vendorOrder.getStaffName();
			String shortName = vendorOrder.getShortName();
			String settlementType = vendorOrder.getSettlementType();
			String isShowPrice = vendorOrder.getIsShowPrice();
			BigDecimal taxRate = null;
			if(vInfo != null) {
				taxRate = vInfo.getTaxRate();
				if(StringUtils.isEmpty(shortName)){
						shortName = vInfo.getShortName();
						vendorOrder.setShortName(shortName);
				}
				if(StringUtils.isEmpty(settlementType)){
					settlementType = vInfo.getSettlementType();
					vendorOrder.setSettlementType(settlementType);
				}
				if(StringUtils.isEmpty(isShowPrice)){
					isShowPrice = vInfo.getIsShowPrice();
					vendorOrder.setIsShowPrice(isShowPrice);
				}
			}
			
			Iterator<VendorOrderDetail> it = list.iterator();
			while(it.hasNext())
			{
				VendorOrderDetail info = it.next();
				
				
				if(info == null)
					continue;
				
				info.setCommCode(commCode);
				info.setState(state);
				info.setPoNo(poNo);
				if(VendorOrderConstants.VENDOR_PO_NO_TYPE_1.equals(poNoType)){
					//�˻�
					info.setPoNoType(poNoType);
				}else{
					logger.debug("calc poNoType of " + info.toString());
					String newtype = null;
					if("window".equals(info.getFromTo())){
						// ������Ŀ1
						newtype = VendorOrderConstants.VENDOR_PO_NO_TYPE_0;
						if(info.getOperSeqId() != null){
							CustOrderDetailMgr codMgr = (CustOrderDetailMgr)BeanLocator.getInstance().getBean(CustOrderDetailBaseAction.custOrderDetailMgrName);
							CustOrderDetail cod = codMgr.findCustOrderDetailById(info.getOperSeqId().toString());
							if(cod != null){
								info.setCustCcode(cod.getCommCode());
								info.setRltOrderPoNo(cod.getPoNo());
							}else{
								logger.info("can't find CustOrderDetail by id " + info.getOperSeqId());
							}
							info.setOperSeqId(null);
						}
					}else{
						if(info.getOperSeqId() == null){
							// ������Ŀ2
							if(StringUtils.isEmpty(info.getCustCcode()))
								newtype = VendorOrderConstants.VENDOR_PO_NO_TYPE_2;
							else
								newtype = VendorOrderConstants.VENDOR_PO_NO_TYPE_3;
						}else{
							VendorOrderDetailMgr vodMgr = (VendorOrderDetailMgr)BeanLocator.getInstance().getBean(VendorOrderDetailBaseAction.vendorOrderDetailMgrName);
							VendorOrderDetail d2 = vodMgr.getVendorOrderDetailById(info.getOperSeqId().toString());
							if(d2 != null)
								newtype = d2.getPoNoType();
						}
					}
					logger.debug("calc poNoType = " + newtype);
					info.setPoNoType(newtype);
				}
				// DONE:����������������poNoType
				//info.setPoNoType(poNoType);
				info.setShortName(shortName);
				info.setStaffId(staffId);
				info.setStaffName(staffName);
				info.setSettlementType(settlementType);
				info.setTaxRate(taxRate);
				if(StringUtils.isEmpty(info.getIsTax()) && info.getCpriceTax() != null){
					info.setIsTax(info.getCpriceTax().compareTo(new BigDecimal(0)) == 0 ? "0" : "1");
				}
			}
			vendorOrder.setVendorOrderDetailList(list);
		} catch (Exception e) {
			logger.info("processListData����detailList����", e);
		}
	}

}
