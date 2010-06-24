package com.hbs.customerinfo.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hbs.common.manager.waittask.WaitTaskMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.DateUtils;

import com.hbs.customerinfo.manager.CustPartNoInfoMgr;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;



public class CustomerInfoJob implements StatefulJob {
	private static Logger logger = Logger.getLogger(CustomerInfoJob.class);
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("开始启动启动客户圈地扫描，提醒处理线程：" + context.getJobDetail().getFullName());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String reminderType = dataMap.getString("customerInfoType");
		logger.info("处理的客户资料圈地提醒类型为：" + reminderType);
		try{
			CustomerInfo cInfo = new CustomerInfo();
			//扫描超过2天的提醒
			Date endDate = DateUtils.getNeedDate(new Date(), "2" , false);
			cInfo.setField("likeEndTime", endDate);
			Date startDate = DateUtils.getNeedDate(new Date(), "3" , false);
			cInfo.setField("likeBeginTime", startDate);
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean("customerInfoMgr");
			List<CustomerInfo> listInfo = mgr.getCustomerInfoList(cInfo);
			if(listInfo != null && listInfo.size() >0){//存在这样的数据，查询物料，看看是否存在
				CustPartNoInfoMgr pMgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean("custPartNoInfoMgr");
				for(CustomerInfo info : listInfo){
					CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
					custPartNoInfo.setCommCode(info.getCommCode());
					Integer iCount = pMgr.listCustPartNoInfoCount(custPartNoInfo);
					if(!(iCount != null && iCount.intValue() > 0)){//发出提醒
						//业务员提醒
						WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
						Map<String , String> hmParam = new HashMap<String,String>();
						hmParam.put("$commCode", info.getCommCode());
						hmParam.put("$shortName", info.getShortName());
						waitTaskInfo.setHmParam(hmParam);
						waitTaskInfo.setExpireTime(DateUtils.getNeedDate(new Date(), "2", true));
						waitTaskInfo.setStaffId(info.getStaffId());
						waitTaskInfo.setBusinessKey(info.getCommCode()+info.getShortName());
						WaitTaskMgr.deleteWaitTask(info.getCommCode()+info.getShortName());
						WaitTaskMgr.createWaitTask("CUST_NO_PARTNO_001", waitTaskInfo);
						//经理待办
						WaitTaskInfo waitTaskInfo1 = new WaitTaskInfo();
						Map<String , String> hmParam1 = new HashMap<String,String>();
						hmParam1.put("$staffName", info.getStaffName());
						hmParam1.put("$commCode", info.getCommCode());
						hmParam1.put("$shortName", info.getShortName());
						waitTaskInfo1.setHmParam(hmParam1);
						waitTaskInfo1.setBusinessKey(info.getCommCode()+info.getShortName());
						WaitTaskMgr.deleteWaitTask(info.getCommCode()+info.getShortName());
						WaitTaskMgr.createWaitTask("CUST_NO_PARTNO_002", waitTaskInfo1);
					}
				}
			}
			//清除
			endDate = DateUtils.getNeedDate(new Date(), "3" , false);
			cInfo.setField("likeEndTime", endDate);
			startDate = DateUtils.getNeedDate(new Date(), "4" , false);
			cInfo.setField("likeBeginTime", startDate);
			listInfo = mgr.getCustomerInfoList(cInfo);
			if(listInfo != null && listInfo.size() >0){//存在这样的数据，查询物料，看看是否存在
				CustPartNoInfoMgr pMgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean("custPartNoInfoMgr");
				for(CustomerInfo info : listInfo){
					CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
					custPartNoInfo.setCommCode(info.getCommCode());
					Integer iCount = pMgr.listCustPartNoInfoCount(custPartNoInfo);
					if(!(iCount != null && iCount.intValue() > 0)){//删除
						mgr.realDelCustomerInfo((info.getBaseSeqId()).toString());
					}else{
						WaitTaskMgr.deleteWaitTask(info.getCommCode()+info.getShortName());
					}
				}
			}
		}
		catch(Exception e){
			logger.error("处理客户圈地定时任务失败，失败原因：" ,e);
		}
		logger.info("完成客户资料圈地提醒定时任务!");

	}

}
