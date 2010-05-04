/**
 * 
 */
package com.hbs.customerorder.action.detail;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerorder.constants.CustOrderConstants;

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
}
