/**
 * 
 */
package com.hbs.customerorder.action.detail;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustOrderDetailBaseAction extends BaseAction {

	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(CustOrderDetailBaseAction.class);
	
	CustOrderDetail orderDetail;

	/**
	 * @return the orderDetail
	 */
	public CustOrderDetail getOrderDetail() {
		return orderDetail;
	}

	/**
	 * @param orderDetail the orderDetail to set
	 */
	public void setOrderDetail(CustOrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	/**
	 * 检查订单详情的关键字段是否填写
	 * @return
	 */
	protected boolean checkKeyFields() {
		try {
			if(orderDetail == null)
				return false;
			if(StringUtils.isEmpty(orderDetail.getOperSeqId())) {
				if(StringUtils.isEmpty(orderDetail.getCommCode()) 
						|| StringUtils.isEmpty(orderDetail.getCpartNo()) )
					return false;
			}
		}catch(Exception e) {
			logger.error("catch Exception in checkKeyFields", e);
		}
		return true;
	}
	
}
