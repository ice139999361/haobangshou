/**
 * system ：hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendororder.manager.helper;

import com.hbs.domain.vendor.order.pojo.VendorOrder;

public interface VendorOrderState {

	/**
	 * 根据供应商订单获取订单所属账期
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public String getPeriod(VendorOrder vOrder)throws Exception;
	
	/**
	 * 根据提交的订单类型获取订单的状态
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public String getCommitState(/*VendorOrder vOrder*/)throws Exception;
}
