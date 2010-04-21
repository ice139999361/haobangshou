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
public class CustOrderScManagerAction extends CustOrderBaseAction {
	private static final Logger logger = Logger.getLogger(CustOrderScManagerAction.class);
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public String getRoleName() {
		return "scmanager";
	}

	/**
	 * 经理审批 超过账期最大金额的客户订单
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input audit 审批结果 1表示审批通过
	 * @action.input auditDesc	审批说明
	 * @action.result
	 * @return
	 */
	public String doAudit() {
		try{
			logger.debug("begin doAudit");
			if(!this.getCustOrderByBizKey())
				return ERROR;
			if(custOrder == null){
				String s = "找不到指定的客户订单！";
				setErrorReason(s);
				logger.info(s);
				return ERROR;
			}
			int i;
			if("1".equals(audit))
				i = getMgr().auditAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
			else
				i = getMgr().auditDisAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), auditDesc);
			if(i != 0) {
				logger.info("审批订单出错！");
				setErrorReason("审批订单出错！");
				return ERROR;
			}
			logger.debug("end doAudit");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAudit", e);
			setErrorReason("内部错误");
			return ERROR;
		}		
	}
	
	/**
	 * 经理审批通过超过账期最大金额的客户订单，审批结果为通过
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	审批说明
	 * @action.result
	 * @return
	 */
	@Deprecated
	public String doAuditAgree() {
		try{
			logger.debug("begin doAuditAgree");
			if(custOrder == null
					|| StringUtils.isEmpty(custOrder.getCommCode()) 
					|| StringUtils.isEmpty(custOrder.getPoNo())) {
				setErrorReason("参数为空！");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.auditAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("审批订单出错！");
				setErrorReason("审批订单出错！");
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
	 * 经理审批不通过超过账期最大金额的客户订单，审批结果为不通过
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	审批说明
	 * @action.result
	 * @return
	 */
	@Deprecated
	public String doAuditDisAgree() {
		try{
			logger.debug("begin doAuditDisAgree");
			if(custOrder == null
					|| StringUtils.isEmpty(custOrder.getCommCode()) 
					|| StringUtils.isEmpty(custOrder.getPoNo())) {
				setErrorReason("参数为空！");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.auditDisAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("审批订单出错！");
				setErrorReason("审批订单出错！");
				return ERROR;
			}
			logger.debug("end doAuditDisAgree");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAuditDisAgree", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

}
