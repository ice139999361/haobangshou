/**
 *
 */
package com.hbs.customerorder.action.detail;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.hbs.common.manager.waittask.WaitTaskMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.ExpireTimeUtil;
import com.hbs.common.utils.IntegerUtils;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.common.utils.OrderCalUtils;
import com.hbs.customer.common.utils.CustLogUtils;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.domain.customer.order.dao.CustOrderDetailDao;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.vendorinfo.manager.VendorInfoMgr;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.manager.VendorOrderDetailMgr;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.manager.WarehouseMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustOrderDetailScNormalAction extends CustOrderDetailBaseAction {
	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(CustOrderDetailScNormalAction.class);

	/**
	 * ȡ���ö�����ϸ
	 * @action.input orderDetail.*
	 * @action.input memo ȡ��ԭ��
	 * @return
	 */
	public String doCancel() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			int i = mgr.cancelOrderDetail(orderDetail, getMemo());
			if(i != 0) {
				logger.error("ȡ������ ret = " + i);
				setErrorReason("ȡ������");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doCancel", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ҵ���ύ�����Ľ��ڣ��ύ���ɹ�����
	 * @action.input orderDetail.*
	 * @action.input memo
	 * @return
	 */
	public String doConfirmDelivery() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			// ��ȡ�ͻ�ָ�����ڣ����==null����ʹ����һ������
			Date d = null;
			String s = this.getHttpServletRequest().getParameter("custDate");
			if(StringUtils.isNotEmpty(s)){
				try {
					DateTimeFormatter fmt = DateTimeFormat.forPattern(ListDataUtil.DATEFORMAT);
					DateTime dt = fmt.parseDateTime(s);
					Calendar c = Calendar.getInstance();
					c.set(dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
					d = c.getTime();
				} catch (Exception e) {
					String str = "���ڸ�ʽ����";
					logger.debug(str + " " + s);
					setErrorReason(str);
					return ERROR;
				}
			}
			if(d != null)
				orderDetail.setPreDeliveryDate(d);
			else{
				// DONE: ʹ����һ������
				//orderDetail.setVerDeliveryDate(orderDetail.getPreDeliveryDate());
			}

			int i = mgr.salesConfirmDetailDelivery(orderDetail, getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDelivery", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * �л�ActiveState
	 * @action.input orderDetail.*
	 * @action.input memo
	 * @return
	 */
	public String doControlActiveState() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			if(CustOrderConstants.ORDER_ACTIVE_STATE.equals(orderDetail.getActiveState()))
				orderDetail.setActiveState(CustOrderConstants.ORDER_PAUSE_STATE);
			else
				orderDetail.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);

			int i = mgr.controlActiveState(orderDetail, getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doControlActiveState", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ���ڿͻ�������ϸ���ڽ���������δ���룬ҵ������������ַ���
	 * ״̬��05----���ڵ�����ҵ��ȷ�Ϸ�������δ���룩 תΪԭ��״̬70
	 * @action.input orderDetail.*
	 * @action.input memo
	 * @return
	 */
	public String doConfirmSend() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			int i = mgr.salesConfirmSendDetail(orderDetail, getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmSend", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ���ڿͻ�������ϸ���ڽ���������δ���룬ҵ��������������ַ���
	 * ״̬��05----���ڵ�����ҵ��ȷ�Ϸ�������δ���룩 תΪԭ��״̬71
	 * @action.input orderDetail.*
	 * @action.input memo
	 * @return
	 */
	public String doConfirmNotSend() {
		try {
			if(!findOrderDetail()) {
				return ERROR;
			}
			setMyId(true);
			int i = mgr.salesConfirmNotSendDetail(orderDetail, getMemo());
			if(i != 0) {
				logger.error("�ύ���� ret = " + i);
				setErrorReason("�ύ����");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmSend", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * �޸Ķ�����ϸ�Ĳ�����Ŀ
	 * @action.input orderDetail.*
	 * @action.input deliveryDate �½���
	 * @action.input amount ������
	 * @action.input vendorCode ��Ӧ�̱���
	 * @action.input memo
	 * @return
	 */
	public String doChangeSomeField() {
		try {
			logger.debug("begin doChangeSomeField");
			if(!findOrderDetail()) {
				return ERROR;
			}
			String state = orderDetail.getState();
			String memo = null;
			try{memo = getHttpServletRequest().getParameter("memo");}catch(Exception e){}
			final String[] validList = {CustOrderConstants.ORDER_STATE_20, CustOrderConstants.ORDER_STATE_21,CustOrderConstants.ORDER_STATE_71,CustOrderConstants.ORDER_STATE_70};
			boolean ok = false;
			for(String s : validList){
				if(s.equals(state)){
					ok = true;
					break;
				}
			}
			if(!ok){
				String message = "״̬����" + orderDetail.getStateDesc();
				logger.debug("doChangeSomeField: " + message);
				this.setErrorReason(message);
				return ERROR;
			}
			CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
			String changes = "";
			// �����޸�
			try{
				String val = getHttpServletRequest().getParameter("deliveryDate");
				if(StringUtils.isNotEmpty(val)){
					logger.debug("doChangeSomeField ����=" + val);
					Date newDeliveryDate = ListDataUtil.parseDate(val);
					Date oldDeliveryDate = orderDetail.getVerDeliveryDate();
					if(CustOrderConstants.ORDER_STATE_20.equals(state)){
						orderDetail.setOrgDeliveryDate(newDeliveryDate);
						if(orderDetail.getPreDeliveryDate() != null)
							orderDetail.setPreDeliveryDate(newDeliveryDate);
					}else{
						if(orderDetail.getVerDeliveryDate() != null)
							orderDetail.setPreDeliveryDate(orderDetail.getVerDeliveryDate());
						orderDetail.setVerDeliveryDate(newDeliveryDate);
					}
					changes += "����:" + ((oldDeliveryDate == null) ? "NULL" : ListDataUtil.formatDate(oldDeliveryDate)) + "->" + ListDataUtil.formatDate(newDeliveryDate) + " ";
					//cDetailDao.updateCustOrderDetailByState(orderDetail);
				}
			}catch(Exception e){logger.info("doChangeSomeField ���ڴ���"+ e.getMessage());}
			// �����޸�
			int newAmount = 0;
			try{
				newAmount = Integer.parseInt(getHttpServletRequest().getParameter("amount"));
			}catch(Exception e){}
			if(newAmount > 0 && !orderDetail.getAmount().equals(newAmount)){
				logger.debug("doChangeSomeField ����=" + orderDetail.getAmount() + "->" + newAmount + " state=" + state);
				changes += "����:" + orderDetail.getAmount() + "->" + newAmount + " ";
				// DONE: ����״̬�޸�����
				orderDetail.setAmount(newAmount);
				orderDetail.setMoney(OrderCalUtils.calOrderMoney(orderDetail.getCprice(), orderDetail.getIsTax(),orderDetail.getTaxRate(), orderDetail.getCpriceTax(),orderDetail.getContactFee(), orderDetail.getAmount()));
				int selfLock = IntegerUtils.intValue(orderDetail.getSelfLockAmount());
				int commLock = IntegerUtils.intValue(orderDetail.getCommLockAmount());
				int oldLock = selfLock + commLock;
				if(oldLock > newAmount){
					int selfDelta = 0, commDelta = 0;
					// ����������
					selfDelta = (oldLock - newAmount) * selfLock / oldLock;
					commDelta = (oldLock - newAmount) - selfDelta;
					selfLock -= selfDelta;
					commLock -= commDelta;
					orderDetail.setSelfLockAmount(selfLock);
					orderDetail.setCommLockAmount(commLock);
					orderDetail.setLockAmount(selfLock + commLock);
					WarehouseMgr whMgr = (WarehouseMgr)getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
					if(selfDelta > 0){
						changes += "�����ض��ͻ��������" + selfDelta + " ";
						// �޸��ض��ͻ����
						WareHouseInfo wh = new WareHouseInfo();
						wh.setCustCode(orderDetail.getCommCode());
						wh.setPartNo(orderDetail.getPartNo());
						wh.setVendorCode(orderDetail.getVendorCode());
						wh.setLockAmount(0 - selfDelta);
						wh.setUseAmount(selfDelta);
						whMgr.saveLockWareHouseInfo(wh, null, null, memo);
					}
					if(commDelta > 0){
						// �޸�ͨ�ÿ��
						changes += "����ͨ�ÿ������" + commDelta + " ";
						WareHouseInfo wh = new WareHouseInfo();
						wh.setPartNo(orderDetail.getPartNo());
						wh.setVendorCode(orderDetail.getVendorCode());
						wh.setLockAmount(0 - commDelta);
						wh.setUseAmount(commDelta);
						whMgr.saveLockWareHouseInfo(wh, null, null, memo);
					}
				}
			}
			//��Ӧ�̱����޸�
			try{
				String val = getHttpServletRequest().getParameter("vendorCode");
				if(StringUtils.isNotEmpty(val)){
					if(!val.equals(orderDetail.getVendorCode())){
						logger.debug("doChangeSomeField ��Ӧ�̱���:" + val);
						VendorInfo vinfo = new VendorInfo();
						VendorInfoMgr vmgr = (VendorInfoMgr)getBean("vendorInfoMgr");
						vinfo.setState("0");
						vinfo.setCommCode(val);
						vinfo = vmgr.getVendorInfo(vinfo, false);
						if(vinfo != null){
							String str = orderDetail.getVendorCode();
							orderDetail.setVendorCode(val);
							changes += "��Ӧ�̱���:" + str + "->" + val + " ";
						}
					}
				}
			}catch(Exception e){}

			if(changes.length() > 0){
				cDetailDao.updateCustOrderDetail(orderDetail);

				// �������
				VendorOrderDetail vod = new VendorOrderDetail();
				vod.setRltOrderPoNo(orderDetail.getPoNo());
				vod.setPartNo(orderDetail.getPartNo());
				vod.setSpecDesc(orderDetail.getSpecDesc());
				VendorOrderDetailMgr vodMgr = (VendorOrderDetailMgr)getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
				List<VendorOrderDetail> list = vodMgr.getVendorOrderDetailList(vod);
				logger.debug("doChangeSomeField �������" + (list == null ? 0 : list.size()) + "��");
				if(list != null && list.size() > 0){
					WaitTaskInfo wt = new WaitTaskInfo();
					//wt.setBusinessKey(orderDetail.getWaitTaskBizKey());
					Map<String , String> hmParam = new HashMap<String,String>();
					hmParam.put("$assStaffName", getLoginStaff().getStaffName());
					hmParam.put("$businessKey", orderDetail.getWaitTaskBizKey());
					hmParam.put("$changes", changes);
					hmParam.put("$memo", StringUtils.isEmpty(memo)?"":"("+memo+")");
					wt.setHmParam(hmParam);
					Date expireTime = ExpireTimeUtil.getExpireTime("CUST_ORDER_REMINDER_DAY");
					wt.setExpireTime(expireTime);
					for(VendorOrderDetail vod1 : list){
						String val = vod1.getCommCode() + ";" + vod1.getPoNo();
						hmParam.put("$vendorBizkey", val);
						wt.setBusinessKey(val);
						wt.setStaffId(vod1.getStaffId());
						wt.setStaffName(vod1.getStaffName());
						WaitTaskMgr.createWaitTask("CUST_ORDER_017", wt);
					}
				}
			}
			if(getLoginStaff() != null){
				CustLogUtils.operLog(getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(),
						"�޸Ĳ�����Ϣ" , "�ͻ�������ϸ", orderDetail.getBizKey(), orderDetail.getBizKey() + " " + changes, memo);
			}
			logger.debug("end doChangeSomeField " + changes);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doChangeSomeField", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
