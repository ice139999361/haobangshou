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
	public static final String ORDER_STATE_01="01"; //业务临时保存数据
	
	public static final String ORDER_STATE_03="03"; //业务取消数据
	
	public static final String ORDER_STATE_04 = "04"; //待业务确认交期
	
	public static final String ORDER_STATE_50 = "50"; //账期金额超出最大，待领导审批
	public static final String ORDER_STATE_52 = "52"; //账期金额超出最大，领导审批不通过
	
	public static final String ORDER_STATE_20 = "20"; //待采购确认
	
	
	public static final String ORDER_STATE_21 = "21"; //采购确认,备货中
	
	
	public static final String ORDER_STATE_30 = "30";//待财务确认预付
	
	public static final String ORDER_STATE_39 = "39";//预付订单财务退单
	
	
	public static final String ORDER_STATE_60 = "60";//部分出货
	
	public static final String ORDER_STATE_61 = "61";//全部出货
	
	
	
	
	public static final String ORDER_ACTIVE_STATE="ACTIVE";
	public static final String ORDER_PAUSE_STATE ="PAUSE";
	
	public static final String CUST_ORDERDETAIL_DAO ="custOrderDetailDao";
}
