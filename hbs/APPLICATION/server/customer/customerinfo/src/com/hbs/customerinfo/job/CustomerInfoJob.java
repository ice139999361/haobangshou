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
		logger.info("��ʼ���������ͻ�Ȧ��ɨ�裬���Ѵ����̣߳�" + context.getJobDetail().getFullName());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String reminderType = dataMap.getString("customerInfoType");
		logger.info("����Ŀͻ�����Ȧ����������Ϊ��" + reminderType);
		try{
			CustomerInfo cInfo = new CustomerInfo();
			//ɨ�賬��2�������
			Date endDate = DateUtils.getNeedDate(new Date(), "2" , false);
			cInfo.setField("likeEndTime", endDate);
			Date startDate = DateUtils.getNeedDate(new Date(), "3" , false);
			cInfo.setField("likeBeginTime", startDate);
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean("customerInfoMgr");
			List<CustomerInfo> listInfo = mgr.getCustomerInfoList(cInfo);
			if(listInfo != null && listInfo.size() >0){//�������������ݣ���ѯ���ϣ������Ƿ����
				CustPartNoInfoMgr pMgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean("custPartNoInfoMgr");
				for(CustomerInfo info : listInfo){
					CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
					custPartNoInfo.setCommCode(info.getCommCode());
					Integer iCount = pMgr.listCustPartNoInfoCount(custPartNoInfo);
					if(!(iCount != null && iCount.intValue() > 0)){//��������
						//ҵ��Ա����
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
						//�������
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
			//���
			endDate = DateUtils.getNeedDate(new Date(), "3" , false);
			cInfo.setField("likeEndTime", endDate);
			startDate = DateUtils.getNeedDate(new Date(), "4" , false);
			cInfo.setField("likeBeginTime", startDate);
			listInfo = mgr.getCustomerInfoList(cInfo);
			if(listInfo != null && listInfo.size() >0){//�������������ݣ���ѯ���ϣ������Ƿ����
				CustPartNoInfoMgr pMgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean("custPartNoInfoMgr");
				for(CustomerInfo info : listInfo){
					CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
					custPartNoInfo.setCommCode(info.getCommCode());
					Integer iCount = pMgr.listCustPartNoInfoCount(custPartNoInfo);
					if(!(iCount != null && iCount.intValue() > 0)){//ɾ��
						mgr.realDelCustomerInfo((info.getBaseSeqId()).toString());
					}else{
						WaitTaskMgr.deleteWaitTask(info.getCommCode()+info.getShortName());
					}
				}
			}
		}
		catch(Exception e){
			logger.error("����ͻ�Ȧ�ض�ʱ����ʧ�ܣ�ʧ��ԭ��" ,e);
		}
		logger.info("��ɿͻ�����Ȧ�����Ѷ�ʱ����!");

	}

}
