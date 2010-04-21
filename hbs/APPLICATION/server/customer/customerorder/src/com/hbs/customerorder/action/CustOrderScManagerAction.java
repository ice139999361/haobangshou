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
	 * �������� �������������Ŀͻ�����
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input audit ������� 1��ʾ����ͨ��
	 * @action.input auditDesc	����˵��
	 * @action.result
	 * @return
	 */
	public String doAudit() {
		try{
			logger.debug("begin doAudit");
			if(!this.getCustOrderByBizKey())
				return ERROR;
			if(custOrder == null){
				String s = "�Ҳ���ָ���Ŀͻ�������";
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
				logger.info("������������");
				setErrorReason("������������");
				return ERROR;
			}
			logger.debug("end doAudit");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAudit", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}		
	}
	
	/**
	 * ��������ͨ���������������Ŀͻ��������������Ϊͨ��
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	����˵��
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
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.auditAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("������������");
				setErrorReason("������������");
				return ERROR;
			}
			logger.debug("end doAuditAgree");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ����������ͨ���������������Ŀͻ��������������Ϊ��ͨ��
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	����˵��
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
				setErrorReason("����Ϊ�գ�");
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.auditDisAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("������������");
				setErrorReason("������������");
				return ERROR;
			}
			logger.debug("end doAuditDisAgree");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAuditDisAgree", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

}
