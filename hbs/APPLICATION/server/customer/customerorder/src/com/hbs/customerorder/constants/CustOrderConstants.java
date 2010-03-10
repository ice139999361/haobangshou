/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.constants;

/**
 * @author Administrator
 *
 */
public final class CustOrderConstants {
	/**
	 * 业务临时保存数据
	 */
	public static final String ORDER_STATE_01="01"; //业务临时保存数据
	/**
	 * 
	 */
	public static final String ORDER_STATE_03="03"; //业务取消数据
	/**
	 * 业务取消数据
	 */
	public static final String ORDER_STATE_04 = "04"; //待业务确认交期
	/**
	 * 待业务确认交期
	 */
	public static final String ORDER_STATE_50 = "50"; //账期金额超出最大，待领导审批
	/**
	 * /账期金额超出最大，待领导审批
	 */
	public static final String ORDER_STATE_52 = "52"; //账期金额超出最大，领导审批不通过
	/**
	 * 账期金额超出最大，领导审批不通过
	 */
	
	public static final String ORDER_STATE_20 = "20"; //待采购确认
	
	/**
	 * 待采购确认
	 */
	public static final String ORDER_STATE_21 = "21"; //采购确认,备货中
	
	/**
	 * 采购确认,备货中
	 */
	public static final String ORDER_STATE_30 = "30";//待财务确认预付
	
	/** 
	 * 31---待财务确认发货（针对预付X%，款到发货，财务确认后，状态转为70）
	 */
	public static final String ORDER_STATE_31 = "31";
	/**
	 * 33---款到发货而款未到，申请待经理审批
	 */
	public static final String ORDER_STATE_33 = "33";
	/**
	 * 待财务确认预付
	 */
	public static final String ORDER_STATE_39 = "39";//预付订单财务退单
	
	/**
	 * 预付订单财务退单
	 */
	public static final String ORDER_STATE_60 = "60";//部分出货
	/**
	 * 部分出货
	 */
	public static final String ORDER_STATE_61 = "61";//全部出货
	/**
	 * 全部出货
	 */
	public static final String ORDER_STATE_70 = "70";//订单明细部分备货
	/**
	 * 订单明细部分备货
	 */
	public static final String ORDER_STATE_71 = "71";//订单明细全部备货
	
	
	
	/**
	 * 活动状态为激活
	 */
	public static final String ORDER_ACTIVE_STATE="ACTIVE";
	/**
	 * 活动状态为暂停
	 */
	public static final String ORDER_PAUSE_STATE ="PAUSE";
	/**
	 * 客户订单明细dao
	 */
	public static final String CUST_ORDERDETAIL_DAO ="custOrderDetailDao";
	
	/**
	 * 调货DAO
	 */
	public static final String ADJUST_INFO_DAO ="adjustInfoDao";
	/**
	 * 仓库明细管理类
	 */
	public static final String WAREHOUSE_INFO_MGR="warehouseMgr";
	/**
	 * 特定客户备货
	 */
	public static final String WAREHOUSE_INFO_HOUSE_USE_1="1";//特定客户备货
	/**
	 * 常规备货
	 */
	public static final String WAREHOUSE_INFO_HOUSE_USE_2="2";//常规备货
	
	public static final String CUSTORDERMGR="custOrderMgr";
	/**
	 * 账期结算
	 */
	public static final int CUST_ORDER_SETTLEMENT_TYPE_1= 1;
	/**
	 * 预付X%,剩余货到付款
	 */
	public static final int CUST_ORDER_SETTLEMENT_TYPE_2= 2;
	
	/**
	 * 预付X%剩余款到发货
	 */
	public static final int CUST_ORDER_SETTLEMENT_TYPE_3= 3;
	/**
	 * 客户订单为订货单
	 */
	public static final String CUST_ORDER_PONO_TYPE_0="0";
}
