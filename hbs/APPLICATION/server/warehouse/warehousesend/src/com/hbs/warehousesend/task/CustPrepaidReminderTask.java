/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.DateUtils;
import com.hbs.common.utils.ExpireTimeUtil;
import com.hbs.customerinfo.manager.CustPrePaidMgr;

import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;
import com.hbs.domain.customer.customerinfo.dao.CustomerInfoDao;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseWaitTaskMgr;
import com.hbs.warehousesend.manager.WareHouseSendDetailMgr;

/**
 * @author Administrator
 *
 */
public class CustPrepaidReminderTask {
	private static final Logger logger = Logger.getLogger(CustPrepaidReminderTask.class);
	private static final String ACCOUNT_PREIOD_STATE_0 ="0"; //正常状态
	private static final String CustPrePaidMgr ="custPrePaidMgr";
	private static final String CUSTOMERINFODAO = "customerInfoDao";
	
	public void processPrePaidReminder(){
		logger.info("获取需要货到付款的订单！");
		try{
			List<WarehouseSendDetail> detailList = getSendDetailList();
			if(null != detailList){
				String curDate = DateUtils.getCurFormatDate(DateUtils.DATEFORMAT);
				for( WarehouseSendDetail detail : detailList){
					processSingelReminder(detail,curDate );
				}
			}else{
				logger.info("本次没有需要处理提醒的货到付款的订单！");
			}
		}catch(Exception e){
			logger.error("客户 款到发货 催款提醒执行错误" , e);
		}
	}
	/**
	 * 获取需要催款提醒的预付费订单
	 * @return
	 * @throws Exception
	 */
	private List<WarehouseSendDetail> getSendDetailList()throws Exception{
		WareHouseSendDetailMgr wsMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
		WarehouseSendDetail detail = new WarehouseSendDetail();
		detail.setSettlementType("2");
		detail.setField("beginPoNoDate", DateUtils.getFormatDate(new Date(), "30", "yyyy-MM-dd",false));
		detail.setField("endPoNoDate", DateUtils.getFormatDate(new Date(), null,"yyyy-MM-dd",false));
		detail.setActiveState(WareHouseConstants.WAREHOUSE_SEND_ACTIVE);
		return wsMgr.listWarehouseSendDetail(detail);
	}
	/**
	 * 处理单条订单的催款提醒待办
	 * @param detail
	 * @param curDate
	 * @throws Exception
	 */
	private void processSingelReminder(WarehouseSendDetail detail,String curDate)throws Exception{
		logger.info("需要处理的货到付款的订单信息为：" + detail.toString());
		//获取提醒日
		String reminderDay = getReminderDay(detail);
		if(reminderDay != null){
			String day = DateUtils.getFormatDate(detail.getCreateTime(), reminderDay, DateUtils.DATEFORMAT, true);
			if(day.equals(curDate)){
				processWaitTask(detail , "CUST_REMINDER_003");
			}
		}else{
			logger.info("需要处理的货到付款的订单信息,没有提醒日设置，不做处理!");
		}
		
	}
	
	/**
	 * 获取预付费的提醒日
	 * @param detail
	 * @return
	 */
	private String getReminderDay(WarehouseSendDetail detail) {
		String ret = null;
		PrePaidInfo prePaidInfo = new PrePaidInfo();
		prePaidInfo.setState(ACCOUNT_PREIOD_STATE_0);
		prePaidInfo.setBaseSeqId(detail.getCustCode());
		try{
			CustPrePaidMgr cMgr = (CustPrePaidMgr)BeanLocator.getInstance().getBean(CustPrePaidMgr);
			prePaidInfo = cMgr.getPrePaidInfo(prePaidInfo);
			if(null !=prePaidInfo){
				ret = prePaidInfo.getReminderDay();
		}
		}catch(Exception e){
			logger.error("获取预付费客户的预付费信息错误：" , e);
			ret = null;
		}
		return ret;
	}
	/**
	 * 处理催款提醒待办
	 * @param detail
	 * @param cfgId
	 */
	private void processWaitTask(WarehouseSendDetail detail,String cfgId){
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$custCode", detail.getCustCode());
		hmParam.put("$custPoNo", detail.getRltPoNo());
		hmParam.put("$custPartNo", detail.getCustPartNo());
		hmParam.put("$sendPoNo", detail.getSendPoNo());
		waitTaskInfo.setHmParam(hmParam);
		waitTaskInfo.setStaffId(getCustInfoOfSalesID(detail));
		waitTaskInfo.setExpireTime(ExpireTimeUtil.getExpireTime("PREPAID_REMINDER_DAY"));
		waitTaskInfo.setBusinessKey(detail.getCustCode()+ "--" + detail.getCustPartNo() +"催款提醒-"+ cfgId);
		WareHouseWaitTaskMgr.processCreateWaitTask(cfgId, waitTaskInfo);
		
	}
	
	/**
	 * 获取客户信息中的销售人员，催款提醒待办使用
	 * @param detail
	 * @return
	 */
	private String getCustInfoOfSalesID(WarehouseSendDetail detail){
		String salesID = null;
		try{
		CustomerInfo retInfo =  new CustomerInfo();
		retInfo.setCommCode(detail.getCustCode());
		retInfo.setState("0");
		CustomerInfoDao customerInfoDao = (CustomerInfoDao)BeanLocator.getInstance().getBean(CUSTOMERINFODAO);
		
		retInfo = customerInfoDao.findCustomerInfoByBase(retInfo);
		if(null != retInfo){
			salesID = retInfo.getStaffId();
		}
		}catch(Exception e){
			logger.error("获取客户的销售人员时错误，" , e);
		}
		return salesID;
		
	}
}
