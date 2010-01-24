/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.manager.helper;

import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

/**
 * @author Administrator
 *
 */
public interface CustOrderState {

	/**
	 * 获取提交的订单的将要成为的状态
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public String commitCustomerOrder(CustomerOrder cOrder) throws Exception;
	
	/**
	 * 根据客户订单的结算类型获取账期
	 * 对预付费无效
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public String getPeriod(CustomerOrder cOrder) throws Exception;
	
	/**
	 * 根据客户订单明细的结算类型获取账期
	 * 对预付费无效
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public String getDetailPeriod(CustOrderDetail orderDetail)throws Exception;
}
