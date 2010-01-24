/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.manager.helper;

import com.hbs.common.manager.baseinfo.PrePaidMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

/**
 * @author Administrator
 *
 */
public class PrepaidCustOrderState implements CustOrderState {

	/* (non-Javadoc)
	 * @see com.hbs.customerorder.manager.helper.CustOrderState#commitCustomerOrder(com.hbs.domain.customer.order.pojo.CustomerOrder)
	 */
	public String commitCustomerOrder(CustomerOrder order) throws Exception {
		//获取预付费信息
		String retState = null;
		PrePaidMgr paidMgr = (PrePaidMgr)BeanLocator.getInstance().getBean("custPrePaidMgr");
		PrePaidInfo prePaidInfo = new PrePaidInfo();
		prePaidInfo.setCommCode(order.getCommCode());
		prePaidInfo.setState("0");
		prePaidInfo = paidMgr.getPrePaidInfo(prePaidInfo);
		if(null != prePaidInfo){
			int iprePaid = Integer.parseInt(prePaidInfo.getPrePaid());
			if(iprePaid > 0){ //预付大于0
				retState = CustOrderConstants.ORDER_STATE_30;
			}else{
				retState = CustOrderConstants.ORDER_STATE_20;
			}
		}		
		return retState;
	}

	/* (non-Javadoc)
	 * @see com.hbs.customerorder.manager.helper.CustOrderState#getPeriod(com.hbs.domain.customer.order.pojo.CustomerOrder)
	 */
	public String getPeriod(CustomerOrder order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hbs.customerorder.manager.helper.CustOrderState#getDetailPeriod(com.hbs.domain.customer.order.pojo.CustOrderDetail)
	 */
	public String getDetailPeriod(CustOrderDetail orderDetail) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
