/**
 * system ��hbs
 * desc:    ���ڿͻ�������ϸ���ڽ�������û�б��룬����ҵ�������Ƿ񷢻�
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

public class OrderPeriodSendReminderTask implements SendReminderTask {

	private static final Logger logger = Logger.getLogger(OrderPrePaidSendReminderTask.class);
	/* (non-Javadoc)
	 * @see com.hbs.customerorder.task.SendReminderTask#reminder()
	 */
	public void reminder() {
		logger.info("ɨ����Ҫ���ѵ����ڿͻ�������ϸ���ڽ�������û�б��룡");
		try{
			List<CustOrderDetail> detailList = getCustOrderDetailList();
			if(null == detailList || detailList.size() ==0){
				logger.info("����ɨ�����Ҫ�������ڿͻ�������ϸ���ڽ�������û�б��������Ϊ0��");
			}else{
				logger.info("����ɨ�����Ҫ�������ڿͻ�������ϸ���ڽ�������û�б��������Ϊ" + detailList.size());
				for(CustOrderDetail detail : detailList){
					logger.info("��ǰ����Ŀͻ�������ϸ��ϢΪ��" + detail.toString());
					processWaitTask(detail,"CUST_ORDER_015");
					logger.info("�޸����ڶ�����ϸ״̬Ϊ05���ڵ�����ҵ��ȷ�Ϸ�������δ���룩");
					//�޸Ŀͻ�������ϸ��״̬Ϊ05���ڵ�����ҵ��ȷ�Ϸ�������δ���룩
					CustOrderDetailMgr detailMgr = (CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
					detail.setState(CustOrderConstants.ORDER_STATE_05);
					detailMgr.updateCustDetailByState(detail);
				}
			}
		}catch(Exception e){
			logger.error("����ɨ����Ҫ�������ڿͻ�������ϸ���ڽ�������û�б������ԭ��" ,e);
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
		waitTaskInfo.setBusinessKey(detail.getBizKey());
		waitTaskInfo.setExpireTime(detail.getVerDeliveryDate());
		waitTaskInfo.setStaffId(detail.getStaffId());
		CustOrderUtils.processCreateWaitTask(cfgId,null, waitTaskInfo);
	}

	/**
	 * ��ȡ1�����ڽ��㣩�Ŀͻ�������ϸ
	 * ����Ϊ��ǰ����+ ������������ = ��������
	 *        1�����ڽ��㣩
	 *       ״̬Ϊ 70����δ���룩 
	 *       �ͻ�����0
	 *       �״̬ΪACTIVE
	 * @return
	 */
	private List<CustOrderDetail> getCustOrderDetailList()throws Exception{
		CustOrderDetail detail = new CustOrderDetail();
		detail.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
		detail.setPoNoType(CustOrderConstants.CUST_ORDER_PONO_TYPE_0);		
		detail.setState(CustOrderConstants.ORDER_STATE_70);
		detail.setSettlementType(new Integer(CustOrderConstants.CUST_ORDER_SETTLEMENT_TYPE_1).toString());
		//��������
		detail.setVerDeliveryDate(ExpireTimeUtil.getExpireTime("CUST_ORDER_PERIOD_1_REMINDER_DAY"));
		CustOrderDetailMgr detailMgr = (CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
		
		return detailMgr.listCustOrderDetail(detail);
	}
}
