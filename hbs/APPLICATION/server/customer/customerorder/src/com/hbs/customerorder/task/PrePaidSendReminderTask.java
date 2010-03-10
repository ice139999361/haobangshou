/**
 * system ：hbs
 * desc:    查询需要发货的预付费订单明细，提醒仓库发货
 *          分为2中情况
 *          预付，货到付款，
 *                      对货未备齐， 提醒采购货未备齐
 *                      货已备齐，提醒仓库发货
 *          预付，款到发货
 *                     对货未备齐， 提醒采购货未备齐
 *                     货已备齐，未收款，提醒业务助理催款
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.task;

import java.util.List;

import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;

public class PrePaidSendReminderTask implements SendReminderTask {

	/* (non-Javadoc)
	 * @see com.hbs.customerorder.task.SendReminderTask#reminder()
	 */
	public void reminder() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 获取预付费的客户订单
	 * 条件为当前日期+ 提醒日期天数 = 发货日期
	 *       结算类型为 2（预付x% 剩余货到付款） 或 3（预付X% ，剩余款到发货）
	 *       状态为 70（部分备货），71 （货已备齐），31（待财务确认付款） 
	 *       客户订单0
	 *       活动状态为ACTIVE
	 * @return
	 */
	public List<CustOrderDetail> getCustOrderDetailList(){
		CustOrderDetail detail = new CustOrderDetail();
		detail.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
		detail.setPoNoType(CustOrderConstants.CUST_ORDER_PONO_TYPE_0);
		detail.setField("settlementTypeList", "'2','3'");//结算类型
		detail.setField("stateList", "'70','71','31'");//状态
		//日期
		//detail.setVerDeliveryDate(verDeliveryDate);
		return null;
	}
}
