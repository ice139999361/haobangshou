package com.hbs.common.manager.waittask;

import java.util.HashMap;
import java.util.Map;

public class WaitTaskConstants {
	private static Map<String , String > mustMap = new HashMap<String, String>();
	
	static {
		mustMap.put("ADJUSTMENT_001", "您有comments条调货申请需要审批,请您审批!");
		mustMap.put("CUSTOMER001", "您有comments条客户资料需要审批,请您审批!");
		mustMap.put("CUSTOMER003", "您有comments条客户资料审批不通过,请您处理!");
		mustMap.put("CUSTOMER003", "您有comments条客户资料审批不通过,请您处理!");
		mustMap.put("CUST_ORDER_001", "您有comments条客户订单超出最大金额,请您审批!");
		mustMap.put("CUST_ORDER_002", "您有comments条客户订单需要处理,请您处理!");
		mustMap.put("CUST_ORDER_003", "您有comments条客户订单需要处理,请您处理!");
		mustMap.put("CUST_ORDER_004", "您有comments条客户订单经理审批不通过,请您处理!");
		mustMap.put("CUST_ORDER_005", "您有comments条客户订单采购不同意交期,请您处理!");
		mustMap.put("CUST_ORDER_006", "您有comments条客户订单业助修改了交期,请您处理!");
		mustMap.put("CUST_ORDER_007", "您有comments条客户订单被财务退回,请您处理!");
		mustMap.put("CUST_ORDER_008", "您有comments条客户订单请求确认预付费,请您处理!");
		mustMap.put("CUST_ORDER_009", "您有comments条客户订单预付x%，剩余款到发货，但未收到剩余货款，申请先发货,请您处理!");
		mustMap.put("CUST_PARTNO_001", "您有comments条客户物料需要审批,请您审批!");
		mustMap.put("CUST_PARTNO_003", "您有comments条客户物料审批不通过,请您处理!");
		mustMap.put("VENDOR001", "您有comments条供应商资料需要审批,请您审批!");
		mustMap.put("VENDOR003", "您有comments条供应商资料审批不通过,请您处理!");
		mustMap.put("VENDOR_PARTNO_001", "您有comments条供应商物料需要审批,请您审批!");
		mustMap.put("VENDOR_PARTNO_003", "您有comments条供应商物料审批不通过,请您处理!");
	}
	
	
	public static String getComments(String cfgID){
		return mustMap.get(cfgID);
	}
}
