/**
 * 
 */
package com.hbs.customerorder.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.CustOrderMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustOrderCwNormalAction extends CustOrderBaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(CustOrderCwNormalAction.class);

	public static final String roleName = "cwnormal";

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 财务确认了客户订单的预付款，待采购处理
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	审批说明
	 * @action.result
	 * @return
	 */
	public String doFinanceAgree() {
		try{
			logger.debug("begin doAuditAgree");
			if(!this.getCustOrderByBizKey())
				return ERROR;
			if(custOrder == null){
				String s = "找不到指定的客户订单！";
				setErrorReason(s);
				logger.info(s);
				return ERROR;
			}
			CustOrderMgr mgr = getMgr();
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.financeAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("处理订单出错！");
				setErrorReason("处理订单出错！");
				return ERROR;
			}
			logger.debug("end doAuditAgree");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 财务退回待财务预付款确认客户订单，待业务助理处理
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	审批说明
	 * @action.result
	 * @return
	 */
	public String doFinanceDisAgree() {
		try{
			logger.debug("begin doAuditAgree");
			if(!this.getCustOrderByBizKey())
				return ERROR;
			if(custOrder == null){
				String s = "找不到指定的客户订单！";
				setErrorReason(s);
				logger.info(s);
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.financeDisAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("处理订单出错！");
				setErrorReason("处理订单出错！");
				return ERROR;
			}
			logger.debug("end doAuditAgree");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}}
