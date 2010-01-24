/**
 * system ��hbs
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
	 * ��ȡ�ύ�Ķ����Ľ�Ҫ��Ϊ��״̬
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public String commitCustomerOrder(CustomerOrder cOrder) throws Exception;
	
	/**
	 * ���ݿͻ������Ľ������ͻ�ȡ����
	 * ��Ԥ������Ч
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public String getPeriod(CustomerOrder cOrder) throws Exception;
	
	/**
	 * ���ݿͻ�������ϸ�Ľ������ͻ�ȡ����
	 * ��Ԥ������Ч
	 * @param cOrder
	 * @return
	 * @throws Exception
	 */
	public String getDetailPeriod(CustOrderDetail orderDetail)throws Exception;
}
