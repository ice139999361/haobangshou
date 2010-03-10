/**
 * system ：hbs
 * desc:    查询需要发货的但货没有备齐的订单明细，提醒采购催货
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.task;

import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.ExpireTimeUtil;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;

public class OrderPrepareReminderTask implements SendReminderTask {

	/* (non-Javadoc)
	 * @see com.hbs.customerorder.task.SendReminderTask#reminder()
	 */
	public void reminder() {
		// TODO Auto-generated method stub

	}
	/**
	 * 获取货未备齐的客户订单明细
	 * 条件为当前日期+ 提醒日期天数 = 发货日期
	 
	 *       状态为 70（部分备货） 
	 *       客户订单0
	 *       活动状态为ACTIVE
	 * @return
	 */
	public List<CustOrderDetail> getCustOrderDetailList()throws Exception{
		CustOrderDetail detail = new CustOrderDetail();
		detail.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
		detail.setPoNoType(CustOrderConstants.CUST_ORDER_PONO_TYPE_0);		
		detail.setState(CustOrderConstants.ORDER_STATE_70);
		//发货日期
		detail.setVerDeliveryDate(ExpireTimeUtil.getExpireTime("CUST_ORDER_PREPARE_REMINDER_DAY"));
		CustOrderDetailMgr detailMgr = (CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
		
		return detailMgr.listCustOrderDetail(detail);
	}
	
	
}
