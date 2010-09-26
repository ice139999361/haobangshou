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
	 * 取消该订单明细
	 * @action.input orderDetail.*
	 * @action.input memo 取消原因
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
				logger.error("取消出错！ ret = " + i);
				setErrorReason("取消出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doCancel", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 业务提交变更后的交期，提交给采购处理
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
			// 获取客户指定交期，如果==null，则使用上一个交期
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
					String str = "日期格式错误！";
					logger.debug(str + " " + s);
					setErrorReason(str);
					return ERROR;
				}
			}
			if(d != null)
				orderDetail.setPreDeliveryDate(d);
			else{
				// DONE: 使用上一个交期
				//orderDetail.setVerDeliveryDate(orderDetail.getPreDeliveryDate());
			}

			int i = mgr.salesConfirmDetailDelivery(orderDetail, getMemo());
			if(i != 0) {
				logger.error("提交出错！ ret = " + i);
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmDelivery", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 切换ActiveState
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
				logger.error("提交出错！ ret = " + i);
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doControlActiveState", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 账期客户订单明细交期将到，但货未备齐，业务助理决定部分发货
	 * 状态由05----交期到，待业务确认发货（货未备齐） 转为原来状态70
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
				logger.error("提交出错！ ret = " + i);
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmSend", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 账期客户订单明细交期将到，但货未备齐，业务助理决定不部分发货
	 * 状态由05----交期到，待业务确认发货（货未备齐） 转为原来状态71
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
				logger.error("提交出错！ ret = " + i);
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doConfirmSend", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 修改订单明细的部分项目
	 * @action.input orderDetail.*
	 * @action.input deliveryDate 新交期
	 * @action.input amount 新数量
	 * @action.input vendorCode 供应商编码
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
				String message = "状态错误！" + orderDetail.getStateDesc();
				logger.debug("doChangeSomeField: " + message);
				this.setErrorReason(message);
				return ERROR;
			}
			CustOrderDetailDao cDetailDao = (CustOrderDetailDao)BeanLocator.getInstance().getBean(CustOrderConstants.CUST_ORDERDETAIL_DAO);
			String changes = "";
			// 交期修改
			try{
				String val = getHttpServletRequest().getParameter("deliveryDate");
				if(StringUtils.isNotEmpty(val)){
					logger.debug("doChangeSomeField 交期=" + val);
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
					changes += "交期:" + ((oldDeliveryDate == null) ? "NULL" : ListDataUtil.formatDate(oldDeliveryDate)) + "->" + ListDataUtil.formatDate(newDeliveryDate) + " ";
					//cDetailDao.updateCustOrderDetailByState(orderDetail);
				}
			}catch(Exception e){logger.info("doChangeSomeField 交期错误："+ e.getMessage());}
			// 数量修改
			int newAmount = 0;
			try{
				newAmount = Integer.parseInt(getHttpServletRequest().getParameter("amount"));
			}catch(Exception e){}
			if(newAmount > 0 && !orderDetail.getAmount().equals(newAmount)){
				logger.debug("doChangeSomeField 数量=" + orderDetail.getAmount() + "->" + newAmount + " state=" + state);
				changes += "数量:" + orderDetail.getAmount() + "->" + newAmount + " ";
				// DONE: 根据状态修改数量
				orderDetail.setAmount(newAmount);
				orderDetail.setMoney(OrderCalUtils.calOrderMoney(orderDetail.getCprice(), orderDetail.getIsTax(),orderDetail.getTaxRate(), orderDetail.getCpriceTax(),orderDetail.getContactFee(), orderDetail.getAmount()));
				int selfLock = IntegerUtils.intValue(orderDetail.getSelfLockAmount());
				int commLock = IntegerUtils.intValue(orderDetail.getCommLockAmount());
				int oldLock = selfLock + commLock;
				if(oldLock > newAmount){
					int selfDelta = 0, commDelta = 0;
					// 按比例分配
					selfDelta = (oldLock - newAmount) * selfLock / oldLock;
					commDelta = (oldLock - newAmount) - selfDelta;
					selfLock -= selfDelta;
					commLock -= commDelta;
					orderDetail.setSelfLockAmount(selfLock);
					orderDetail.setCommLockAmount(commLock);
					orderDetail.setLockAmount(selfLock + commLock);
					WarehouseMgr whMgr = (WarehouseMgr)getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
					if(selfDelta > 0){
						changes += "减少特定客户库存锁定" + selfDelta + " ";
						// 修改特定客户库存
						WareHouseInfo wh = new WareHouseInfo();
						wh.setCustCode(orderDetail.getCommCode());
						wh.setPartNo(orderDetail.getPartNo());
						wh.setVendorCode(orderDetail.getVendorCode());
						wh.setLockAmount(0 - selfDelta);
						wh.setUseAmount(selfDelta);
						whMgr.saveLockWareHouseInfo(wh, null, null, memo);
					}
					if(commDelta > 0){
						// 修改通用库存
						changes += "减少通用库存锁定" + commDelta + " ";
						WareHouseInfo wh = new WareHouseInfo();
						wh.setPartNo(orderDetail.getPartNo());
						wh.setVendorCode(orderDetail.getVendorCode());
						wh.setLockAmount(0 - commDelta);
						wh.setUseAmount(commDelta);
						whMgr.saveLockWareHouseInfo(wh, null, null, memo);
					}
				}
			}
			//供应商编码修改
			try{
				String val = getHttpServletRequest().getParameter("vendorCode");
				if(StringUtils.isNotEmpty(val)){
					if(!val.equals(orderDetail.getVendorCode())){
						logger.debug("doChangeSomeField 供应商编码:" + val);
						VendorInfo vinfo = new VendorInfo();
						VendorInfoMgr vmgr = (VendorInfoMgr)getBean("vendorInfoMgr");
						vinfo.setState("0");
						vinfo.setCommCode(val);
						vinfo = vmgr.getVendorInfo(vinfo, false);
						if(vinfo != null){
							String str = orderDetail.getVendorCode();
							orderDetail.setVendorCode(val);
							changes += "供应商编码:" + str + "->" + val + " ";
						}
					}
				}
			}catch(Exception e){}

			if(changes.length() > 0){
				cDetailDao.updateCustOrderDetail(orderDetail);

				// 添加提醒
				VendorOrderDetail vod = new VendorOrderDetail();
				vod.setRltOrderPoNo(orderDetail.getPoNo());
				vod.setPartNo(orderDetail.getPartNo());
				vod.setSpecDesc(orderDetail.getSpecDesc());
				VendorOrderDetailMgr vodMgr = (VendorOrderDetailMgr)getBean(VendorOrderConstants.VENDOR_ORDER_DETAIL_MGR);
				List<VendorOrderDetail> list = vodMgr.getVendorOrderDetailList(vod);
				logger.debug("doChangeSomeField 添加提醒" + (list == null ? 0 : list.size()) + "条");
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
						"修改部分信息" , "客户订单明细", orderDetail.getBizKey(), orderDetail.getBizKey() + " " + changes, memo);
			}
			logger.debug("end doChangeSomeField " + changes);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doChangeSomeField", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
