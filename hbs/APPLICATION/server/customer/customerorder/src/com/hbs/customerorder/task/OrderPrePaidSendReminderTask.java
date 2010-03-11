/**
 * system ��hbs
 * desc:    ���ѱ��룬δ�տ����ҵ������߿�
 *          3��Ԥ��X% ��ʣ��������
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.DateUtils;
import com.hbs.common.utils.ExpireTimeUtil;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.customerorder.utils.CustOrderUtils;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;

public class OrderPrePaidSendReminderTask implements SendReminderTask {

	private static final Logger logger = Logger.getLogger(OrderPrePaidSendReminderTask.class);
	/* (non-Javadoc)
	 * @see com.hbs.customerorder.task.SendReminderTask#reminder()
	 */
	public void reminder() {
		logger.info("ɨ����Ҫ���ѵĻ��ѱ��룬δ�տ�Ŀͻ�������ϸ��");
		try{
			List<CustOrderDetail> detailList = getCustOrderDetailList();
			if(null == detailList || detailList.size() ==0){
				logger.info("����ɨ�����Ҫ���ѻ��ѱ��룬δ�տ�Ŀͻ�������ϸ������Ϊ0��");
			}else{
				logger.info("����ɨ�����Ҫ���ѻ��ѱ��룬δ�տ�Ŀͻ�������ϸ������Ϊ" + detailList.size());
				for(CustOrderDetail detail : detailList){
					logger.info("��ǰ����Ŀͻ�������ϸ��ϢΪ��" + detail.toString());
					processWaitTask(detail,"CUST_ORDER_014");
				}
			}
		}catch(Exception e){
			logger.error("����ɨ����Ҫ���ѵĻ��ѱ��룬δ�տ�Ŀͻ�������ϸ����ԭ��" ,e);
		}
	}
	/**
	 * ��������
	 * @param detail
	 * @param cfgId
	 * @throws Exception
	 */
	private void processWaitTask(CustOrderDetail detail,String cfgId)throws Exception{
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$custCode", detail.getCommCode());
		hmParam.put("$poNo", detail.getPoNo());
		hmParam.put("$partNo", detail.getPartNo());
		hmParam.put("$veryDeliveryDate", DateUtils.getFormatDate(detail.getVerDeliveryDate(),DateUtils.DETAIL_DATEFORMAT));
		waitTaskInfo.setHmParam(hmParam);
		waitTaskInfo.setBusinessKey(detail.getBizKey()+"������-"+ cfgId);
		waitTaskInfo.setExpireTime(detail.getVerDeliveryDate());
		waitTaskInfo.setStaffId(detail.getStaffId());
		CustOrderUtils.processCreateWaitTask(cfgId,null, waitTaskInfo);
	}

	
	/**
	 * ��ȡ3��Ԥ��X% ��ʣ���������Ŀͻ�����
	 * ����Ϊ��ǰ����+ ������������ = ��������
	 *        3��Ԥ��X% ��ʣ��������
	 *       ״̬Ϊ 31��������ȷ�ϸ�� 
	 *       �ͻ�����0
	 *       �״̬ΪACTIVE
	 * @return
	 */
	private List<CustOrderDetail> getCustOrderDetailList()throws Exception{
		CustOrderDetail detail = new CustOrderDetail();
		detail.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
		detail.setPoNoType(CustOrderConstants.CUST_ORDER_PONO_TYPE_0);		
		detail.setState(CustOrderConstants.ORDER_STATE_31);
		detail.setSettlementType(new Integer(CustOrderConstants.CUST_ORDER_SETTLEMENT_TYPE_3).toString());
		//��������
		detail.setVerDeliveryDate(ExpireTimeUtil.getExpireTime("CUST_ORDER_PREPAID_3_REMINDER_DAY"));
		CustOrderDetailMgr detailMgr = (CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
		
		return detailMgr.listCustOrderDetail(detail);
	}
}
