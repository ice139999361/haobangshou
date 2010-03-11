/**
 * system ：hbs
 * desc:    在发货日期的前几天提醒仓库发货给客户
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

public class OrderMustSendReminderTask implements SendReminderTask {

	private static final Logger logger = Logger.getLogger(OrderMustSendReminderTask.class);
	/* (non-Javadoc)
	 * @see com.hbs.customerorder.task.SendReminderTask#reminder()
	 */
	public void reminder() {
		logger.info("扫描需要提醒的需要发货的客户订单明细！");
		try{
			List<CustOrderDetail> detailList = getCustOrderDetailList();
			if(null == detailList || detailList.size() ==0){
				logger.info("本次扫描的需要提醒需要发货的客户订单明细的数量为0！");
			}else{
				logger.info("本次扫描的需要提醒需要发货的客户订单明细的数量为" + detailList.size());
				for(CustOrderDetail detail : detailList){
					logger.info("当前处理的客户订单明细信息为：" + detail.toString());
					processWaitTask(detail,"CUST_ORDER_013");
				}
			}
		}catch(Exception e){
			logger.error("处理扫描需要提醒的货未备齐的客户订单明细出错！原因：" ,e);
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
		waitTaskInfo.setBusinessKey(detail.getBizKey()+"提醒日-"+ cfgId);
		waitTaskInfo.setExpireTime(detail.getVerDeliveryDate());
		CustOrderUtils.processCreateWaitTask(cfgId,null, waitTaskInfo);
	}
	
	/**
	 * 获取需要发货的客户订单明细
	 * 条件为当前日期+ 提醒日期天数 = 发货日期
	 
	 *       状态为 70（货已备齐） 
	 *       客户订单0
	 *       活动状态为ACTIVE
	 * @return
	 */
	private List<CustOrderDetail> getCustOrderDetailList()throws Exception{
		CustOrderDetail detail = new CustOrderDetail();
		detail.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
		detail.setPoNoType(CustOrderConstants.CUST_ORDER_PONO_TYPE_0);		
		detail.setState(CustOrderConstants.ORDER_STATE_70);
		//发货日期
		detail.setVerDeliveryDate(ExpireTimeUtil.getExpireTime("CUST_ORDER_SEND_REMINDER_DAY"));
		CustOrderDetailMgr detailMgr = (CustOrderDetailMgr)BeanLocator.getInstance().getBean("custOrderDetailMgr");
		
		return detailMgr.listCustOrderDetail(detail);
	}
	

}
