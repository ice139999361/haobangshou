package com.hbs.vendororder.action;

import java.math.BigDecimal;
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
import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendorinfo.action.VendorInfoNormalAction;
import com.hbs.vendorinfo.manager.VendorInfoMgr;

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
		vInfo = mgr.getVendorInfo(vInfo, true);
		if(vInfo == null)
			return false;
		if(otherData != null)
			otherData.put("vendorInfo", vInfo);
		return true;
	}

	public static List<FieldErr> checkInputFields(VendorOrder vendorOrder,
			Map otherData) 
	{
		// TODO Auto-generated method stub
		return null;
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
			
			String commCode = vendorOrder.getCommCode();
			String state = vendorOrder.getState();
			String poNo = vendorOrder.getPoNo();
			String poNoType = vendorOrder.getPoNoType();
			String shortName = vendorOrder.getShortName();
			String staffId = vendorOrder.getStaffId();
			String staffName = vendorOrder.getStaffName();
			String settlementType = vendorOrder.getSettlementType();
			VendorInfo vInfo = (VendorInfo)otherData.get("vendorInfo");
			BigDecimal taxRate = null;
			if(vInfo != null) {
				taxRate = vInfo.getTaxRate();
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
				// TODO:根据详情数据设置poNoType
				//info.setPoNoType(poNoType);
				info.setShortName(shortName);
				info.setStaffId(staffId);
				info.setStaffName(staffName);
				info.setSettlementType(settlementType);
				info.setTaxRate(taxRate);
			}
			vendorOrder.setVendorOrderDetailList(list);
		} catch (Exception e) {
			logger.info("processListData处理detailList出错", e);
		}
	}

}
