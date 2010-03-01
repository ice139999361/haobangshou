/**
 * system ��hbs
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

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.DateUtils;
import com.hbs.customerinfo.manager.CustPrePaidMgr;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;
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
	private static final String ACCOUNT_PREIOD_STATE_0 ="0"; //����״̬
	private static final String CustPrePaidMgr ="custPrePaidMgr";
	
	public void processPrePaidReminder() throws Exception{
		logger.info("��ȡ��Ҫ��������Ķ�����");
		List<WarehouseSendDetail> detailList = getSendDetailList();
		if(null != detailList){
			String curDate = DateUtils.getCurFormatDate(DateUtils.DATEFORMAT);
			for( WarehouseSendDetail detail : detailList){
				
			}
		}else{
			logger.info("����û����Ҫ�������ѵĻ�������Ķ�����");
		}
	}
	
	public List<WarehouseSendDetail> getSendDetailList()throws Exception{
		WareHouseSendDetailMgr wsMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_DETAILMGR);
		WarehouseSendDetail detail = new WarehouseSendDetail();
		detail.setSettlementType("2");
		detail.setField("beginPoNoDate", DateUtils.getFormatDate(new Date(), "30", "yyyy-MM-dd",false));
		detail.setField("endPoNoDate", DateUtils.getFormatDate(new Date(), null,"yyyy-MM-dd",false));
		detail.setActiveState(WareHouseConstants.WAREHOUSE_SEND_ACTIVE);
		return wsMgr.listWarehouseSendDetail(detail);
	}
	
	private void processSingelReminder(WarehouseSendDetail detail,String curDate)throws Exception{
		logger.info("��Ҫ����Ļ�������Ķ�����ϢΪ��" + detail.toString());
		//��ȡ������
		String reminderDay = getReminderDay(detail);
		if(reminderDay != null){
			String day = DateUtils.getFormatDate(detail.getCreateTime(), reminderDay, DateUtils.DATEFORMAT, true);
			if(day.equals(curDate)){
				
			}
		}else{
			logger.info("��Ҫ����Ļ�������Ķ�����Ϣ,û�����������ã���������!");
		}
		
	}
	
	/**
	 * ��ȡԤ���ѵ�������
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
			logger.error("��ȡԤ���ѿͻ���Ԥ������Ϣ����" , e);
			ret = null;
		}
		return ret;
	}
	
	private void processWaitTask(AccountPreiod preiod, String accountPreiod , String aDay,String cfgId){
		WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
		Map<String , String> hmParam = new HashMap<String,String>();
		hmParam.put("$custCode", preiod.getCommCode());
		hmParam.put("$period", accountPreiod);
		hmParam.put("$accountDay", aDay);
		waitTaskInfo.setHmParam(hmParam);
		waitTaskInfo.setBusinessKey(preiod.getCommCode()+"������-"+ cfgId);
		WareHouseWaitTaskMgr.processCreateWaitTask(cfgId, waitTaskInfo);
		
	}
}
