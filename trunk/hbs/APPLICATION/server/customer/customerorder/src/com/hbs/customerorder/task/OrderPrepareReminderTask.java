/**
 * system ��hbs
 * desc:    ��ѯ��Ҫ�����ĵ���û�б���Ķ�����ϸ�����Ѳɹ��߻�
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
	 * ��ȡ��δ����Ŀͻ�������ϸ
	 * ����Ϊ��ǰ����+ ������������ = ��������
	 
	 *       ״̬Ϊ 70�����ֱ����� 
	 *       �ͻ�����0
	 *       �״̬ΪACTIVE
	 * @return
	 */
	public List<CustOrderDetail> getCustOrderDetailList()throws Exception{
		CustOrderDetail detail = new CustOrderDetail();
		detail.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
		detail.setPoNoType(CustOrderConstants.CUST_ORDER_PONO_TYPE_0);		
		detail.setState(CustOrderConstants.ORDER_STATE_70);
		//��������
		detail.setVerDeliveryDate(ExpireTimeUtil.getExpireTime("CUST_ORDER_PREPARE_REMINDER_DAY"));
		CustOrderDetailMgr detailMgr = (CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
		
		return detailMgr.listCustOrderDetail(detail);
	}
	
	
}
