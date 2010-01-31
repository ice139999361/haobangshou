/**
 * system ：hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendororder.constants;

public interface VendorOrderConstants {

	
	public static final String VENDOR_ORDER_STATE_01="01"; //采购临时保存数据
	
	public static final String VENDOR_ORDER_STATE_03="03"; //采购取消订单数据
	
	public static final String VENDOR_ORDER_STATE_02="02"; //采购确认订单数据,待入库
	
	public static final String VENDOR_ORDER_STATE_04="04"; //待交期确认
	
	public static final String VENDOR_ORDER_STATE_60="60"; //采购单部分物料入库
	
	public static final String VENDOR_ORDER_STATE_61="61"; //采购单全部物料入库
	
	
	
	public static final String VENDOR_PO_NO_TYPE_0 ="0";//客户订单的采购单
	
	public static final String VENDOR_PO_NO_TYPE_1 ="1";//供应商的退货单
	
	public static final String VENDOR_PO_NO_TYPE_2 ="2";//常规备货采购单
	
	public static final String VENDOR_PO_NO_TYPE_3 ="3";//特定客户备货采购单
	
	
	public static final String VENDOR_ORDER_ACTIVE_STATE="ACTIVE";
	public static final String VENDOR_ORDER_PAUSE_STATE ="PAUSE";
	
	
	public static final String PRE_SPRING="vendorOrder";
	
	public static final String VENDOR_ORDER_DAO = "vendorOrderDao";
	public static final String VENDOR_ORDER_DETAIL_DAO ="vendorOrderDetailDao";
	
	public static final String CUST_ORDER_DETAIL_DAO = "custOrderDetailDao";
	
	
	
	public static final String VENDOR_ORDER_DETAIL_MGR ="vendorOrderDetailMgr";
}
