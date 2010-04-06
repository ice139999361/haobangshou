/**
 * 
 */
package com.hbs.warehousereceive.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerinfo.action.CustomerInfoUtil;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;

/**
 * @author xyf
 *
 */
public class WarehouseRecUtil {
	private static final Logger logger = Logger.getLogger(WarehouseRecUtil.class);
	private static final String detailListName = "orderlist";
	private static final String detailListFields = "orderlistFields";

	public static boolean checkKeyFields(WarehouseRecInfo warehouse) {
		boolean ret = false;		
		logger.debug(warehouse == null 
				|| StringUtils.isEmpty(warehouse.getVendorCode())
				|| StringUtils.isEmpty(warehouse.getRecPoNo()));
		if(warehouse == null 
				|| StringUtils.isEmpty(warehouse.getVendorCode())
				|| StringUtils.isEmpty(warehouse.getRecPoNo())){
			ret = true;
		}
		
		logger.debug("ret =" + ret);
		return ret;
		// DONE:WarehouseRecUtil.checkKeyFields
	}

	@SuppressWarnings("unchecked")
	public static void processListData(WarehouseRecInfo warehouseRec,
			HttpServletRequest request, Map otherData) {
		try {
			List<WarehouseRecDetail> list = ListDataUtil.splitIntoList(WarehouseRecDetail.class, 
				request.getParameterValues(detailListName), 
				request.getParameter(detailListFields).split(CustomerInfoUtil.fieldNameSplitter), 
				CustomerInfoUtil.splitter);
			warehouseRec.setDetailList(list);
			
			if(list.size() <= 0)
				return;
			
			String vendorCode = warehouseRec.getVendorCode();
			String recPoNo = warehouseRec.getRecPoNo();
			String shortName = warehouseRec.getShortName();
			Date poNoDate = warehouseRec.getPoNoDate();
			Date applyDate = warehouseRec.getApplyDate();
			String houseType = warehouseRec.getHouseType();
			String settlementType = warehouseRec.getSettlementType();
			String poNoType = warehouseRec.getPoNoType();
			String staffId = warehouseRec.getOperId();
			String staffName = warehouseRec.getOperStaff();
			Date operTime = warehouseRec.getOperTime();
			
			for(WarehouseRecDetail detail : list){
				if(detail == null)
					continue;
				detail.setVendorCode(vendorCode);
				detail.setRecPoNo(recPoNo);
				detail.setShortName(shortName);
				detail.setPoNoDate(poNoDate);
				detail.setApplyDate(applyDate);
				detail.setHouseType(houseType);
				detail.setSettlementType(settlementType);
				detail.setPoNoType(poNoType);
				detail.setStaffId(staffId);
				detail.setStaffName(staffName);
				detail.setOperTime(operTime);
			}
		} catch (Exception e) {
			logger.info("processListData����detailList����", e);
		}

	}

	@SuppressWarnings("unchecked")
	public static List<FieldErr> checkInputFields(
			WarehouseRecInfo warehouseRec, Map otherData) {
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		if(StringUtils.isEmpty(warehouseRec.getVendorCode()))
			list.add(new FieldErr("VendorCode","VendorCodeû����д"));
		if(StringUtils.isEmpty(warehouseRec.getRecPoNo()))
			list.add(new FieldErr("RecPoNo","RecPoNoû����д"));
		if(StringUtils.isEmpty(warehouseRec.getShortName()))
			list.add(new FieldErr("ShortName","ShortNameû����д"));
		if(warehouseRec.getPoNoDate() == null)
			list.add(new FieldErr("PoNoDate","PoNoDateû����д"));
		if(StringUtils.isEmpty(warehouseRec.getHouseType()))
			list.add(new FieldErr("HouseType","HouseTypeû����д"));
		if(StringUtils.isEmpty(warehouseRec.getSettlementType()))
			list.add(new FieldErr("SettlementType","SettlementTypeû����д"));
		if(StringUtils.isEmpty(warehouseRec.getPoNoType()))
			list.add(new FieldErr("PoNoType","PoNoTypeû����д"));
		
		
		for(WarehouseRecDetail detail : warehouseRec.getDetailList()){
			if(detail == null)
				continue;
			if(StringUtils.isEmpty(detail.getCpartNo()))
				list.add(new FieldErr("CpartNo","CpartNoû����д"));
			if(StringUtils.isEmpty(detail.getPartNo()))
				list.add(new FieldErr("PartNo","PartNoû����д"));
			if(StringUtils.isEmpty(detail.getPnDesc()))
				list.add(new FieldErr("PnDesc","PnDescû����д"));
			if(detail.getAmount() == null)
				list.add(new FieldErr("Amount","Amountû����д"));
			if(detail.getTaxRate() == null)
				list.add(new FieldErr("TaxRate","TaxRateû����д"));
			if(StringUtils.isEmpty(detail.getIsTax()))
				list.add(new FieldErr("IsTax","IsTaxû����д"));
		}
		return list;
	}

}
