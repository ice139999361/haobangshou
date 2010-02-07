/**
 * system ：hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehouse.common.constants;

public interface WareHouseConstants {

	public static final String WAREHOUSE_INFO_DAO = "wareHouseInfoDao";
	
	public static final String WAREHOUSE_REC_INFO_DAO = "warehouseRecInfoDao";
	public static final String WAREHOUSE_REC_DETAIL_DAO = "warehouseRecDetailDao";
	
	
	
	public static final String WAREHOUSE_REC_DETAILMGR ="wareHouseRecDetailMgr";
	
	public static final String  VENDOR_ORDER_DETAILMGR="vendorOrderDetailMgr";
	
	public static final String  CUST_ORDER_DETAILMGR ="custOrderDetailMgr";
	
	public static final String WAREHOUSE_INFO_MGR ="WarehouseMgr";
	
	public static final String WAREHOUSE_REC_ACTIVE="ACTIVE";
	public static final String WAREHOUSE_REC_PAUSE = "PAUSE";
	
	
	public static final String WAREHOUSE_REC_FINANCE_STATE_0="0";  //未对账
	public static final String WAREHOUSE_REC_FINANCE_STATE_1="1";  //已对账
	public static final String WAREHOUSE_REC_FINANCE_STATE_2="2";  //部分对账
	
	
	public static final String WAREHOUSE_REC_INFO_01 ="01";//临时保存入库单
	public static final String WAREHOUSE_REC_INFO_02 ="02";//确认入库单
	public static final String WAREHOUSE_REC_INFO_03 ="03";//入库单被取消
	
	
	public static final String WAREHOUSE_INFO_STATE_0 ="0"; //库存可用
	public static final String WAREHOUSE_INFO_STATE_1 ="1"; //库存不可用
	
	
	public static final String WAREHOUSE_USE_TYPE_1 = "1"; //特定客户备货
	public static final String WAREHOUSE_USE_TYPE_2 = "2"; //常规备货
	
	
	public static final String PRE_SPRING="warehouse";
}
