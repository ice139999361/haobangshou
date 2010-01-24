/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.manager.helper;

import java.math.BigDecimal;


import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.DateUtils;

import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.customerorder.manager.CustOrderMgr;
import com.hbs.customerorder.utils.AccountPreiodUtils;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;

import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;



public class PeriodCustOrderState implements CustOrderState {

	/* (non-Javadoc)
	 * @see com.hbs.customerorder.manager.CustOrderState#commitCustomerOrder(com.hbs.domain.customer.order.pojo.CustomerOrder)
	 */
	public String commitCustomerOrder(CustomerOrder cOrder) throws Exception {
		String retState =null;
		boolean isMaxMoney = comparePeriodMaxMoney(cOrder);
			if(isMaxMoney){//max money 50
				retState = CustOrderConstants.ORDER_STATE_50;				
			}else{//20
			retState = CustOrderConstants.ORDER_STATE_20;				
			}
		return retState;	
	}
	
	
	private boolean comparePeriodMaxMoney(CustomerOrder cOrder) throws Exception{
		boolean ret = false;
		String period = cOrder.getPeriod();
		AccountPreiod aPreiod = AccountPreiodUtils.getCustAccountPreiod(cOrder.getCommCode());
		
		if(null != period){
			if(aPreiod.getMaxMoney().intValue() != 0){
				BigDecimal maxMoney = aPreiod.getMaxMoney();
				CustOrderDetail orderDetail = new CustOrderDetail();
				orderDetail.setCommCode(cOrder.getCommCode());
				orderDetail.setPoNo(cOrder.getPoNo());
				orderDetail.setPeriod(cOrder.getPeriod());
				
				CustOrderDetailMgr detailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
				BigDecimal curMoney = detailMgr.getTotalMoneyByPeriod(orderDetail);
				ret = (maxMoney.compareTo(curMoney) < 0) ? true : false;
			}		
		}
		return ret;
	}


	/* (non-Javadoc)
	 * @see com.hbs.customerorder.manager.helper.CustOrderState#getPeriod(com.hbs.domain.customer.order.pojo.CustomerOrder)
	 */
	public String getPeriod(CustomerOrder order) throws Exception {
		String period = null;
		AccountPreiod aPreiod = AccountPreiodUtils.getCustAccountPreiod(order.getCommCode());
		period = DateUtils.getDatePeriod(order.getOderTime(), aPreiod.getPeriodStart(), aPreiod.getAccountPeriod());
		return period;
	}


	/* (non-Javadoc)
	 * @see com.hbs.customerorder.manager.helper.CustOrderState#getDetailPeriod(com.hbs.domain.customer.order.pojo.CustOrderDetail)
	 */
	public String getDetailPeriod(CustOrderDetail orderDetail) throws Exception {
		String period = null;
		CustOrderMgr cOrderMgr =(CustOrderMgr)BeanLocator.getInstance().getBean("custOrderMgr");
		CustomerOrder order = new CustomerOrder();
		order.setCommCode(orderDetail.getCommCode());
		order.setPoNo(orderDetail.getPoNo());
		order = cOrderMgr.findCustomerOrderByBizKey(order, false);
		period =order.getPeriod(); 
		return period;
	}

}
