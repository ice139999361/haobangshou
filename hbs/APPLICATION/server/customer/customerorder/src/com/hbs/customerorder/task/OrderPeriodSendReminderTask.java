/**
 * system ：hbs
 * desc:    账期客户订单明细交期将到，货没有备齐，提醒业务助理是否发货
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
		logger.info("扫描需要提醒的账期客户订单明细交期将到，货没有备齐！");
		try{
			List<CustOrderDetail> detailList = getCustOrderDetailList();
			if(null == detailList || detailList.size() ==0){
				logger.info("本次扫描的需要提醒账期客户订单明细交期将到，货没有备齐的数量为0！");
			}else{
				logger.info("本次扫描的需要提醒账期客户订单明细交期将到，货没有备齐的数量为" + detailList.size());
				for(CustOrderDetail detail : detailList){
					logger.info("当前处理的客户订单明细信息为：" + detail.toString());
					processWaitTask(detail,"CUST_ORDER_015");
					logger.info("修改账期订单明细状态为05交期到，待业务确认发货（货未备齐）");
					//修改客户订单明细的状态为05交期到，待业务确认发货（货未备齐）
					CustOrderDetailMgr detailMgr = (CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
					detail.setState(CustOrderConstants.ORDER_STATE_05);
					detailMgr.updateCustDetailByState(detail);
				}
			}
		}catch(Exception e){
			logger.error("处理扫描需要提醒账期客户订单明细交期将到，货没有备齐出错！原因：" ,e);
		}
	}
	/**
	 * 产生待办
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
	 * 获取1（账期结算）的客户订单明细
	 * 条件为当前日期+ 提醒日期天数 = 发货日期
	 *        1（账期结算）
	 *       状态为 70（货未备齐） 
	 *       客户订单0
	 *       活动状态为ACTIVE
	 * @return
	 */
	private List<CustOrderDetail> getCustOrderDetailList()throws Exception{
		CustOrderDetail detail = new CustOrderDetail();
		detail.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
		detail.setPoNoType(CustOrderConstants.CUST_ORDER_PONO_TYPE_0);		
		detail.setState(CustOrderConstants.ORDER_STATE_70);
		detail.setSettlementType(new Integer(CustOrderConstants.CUST_ORDER_SETTLEMENT_TYPE_1).toString());
		//发货日期
		detail.setVerDeliveryDate(ExpireTimeUtil.getExpireTime("CUST_ORDER_PERIOD_1_REMINDER_DAY"));
		CustOrderDetailMgr detailMgr = (CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
		
		return detailMgr.listCustOrderDetail(detail);
	}
}
