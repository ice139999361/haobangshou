/**
 * system ��hbs
 * desc:    ��ѯ��Ҫ������Ԥ���Ѷ�����ϸ�����Ѳֿⷢ��
 *          ��Ϊ2�����
 *          Ԥ�����������
 *                      �Ի�δ���룬 ���Ѳɹ���δ����
 *                      ���ѱ��룬���Ѳֿⷢ��
 *          Ԥ���������
 *                     �Ի�δ���룬 ���Ѳɹ���δ����
 *                     ���ѱ��룬δ�տ����ҵ������߿�
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
	 * ��ȡԤ���ѵĿͻ�����
	 * ����Ϊ��ǰ����+ ������������ = ��������
	 *       ��������Ϊ 2��Ԥ��x% ʣ�������� �� 3��Ԥ��X% ��ʣ��������
	 *       ״̬Ϊ 70�����ֱ�������71 �����ѱ��룩��31��������ȷ�ϸ�� 
	 *       �ͻ�����0
	 *       �״̬ΪACTIVE
	 * @return
	 */
	public List<CustOrderDetail> getCustOrderDetailList(){
		CustOrderDetail detail = new CustOrderDetail();
		detail.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
		detail.setPoNoType(CustOrderConstants.CUST_ORDER_PONO_TYPE_0);
		detail.setField("settlementTypeList", "'2','3'");//��������
		detail.setField("stateList", "'70','71','31'");//״̬
		//����
		//detail.setVerDeliveryDate(verDeliveryDate);
		return null;
	}
}
