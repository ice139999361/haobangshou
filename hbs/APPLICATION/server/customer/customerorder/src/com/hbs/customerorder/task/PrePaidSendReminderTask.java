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

public class PrePaidSendReminderTask implements SendReminderTask {

	/* (non-Javadoc)
	 * @see com.hbs.customerorder.task.SendReminderTask#reminder()
	 */
	public void reminder() {
		// TODO Auto-generated method stub

	}

}
