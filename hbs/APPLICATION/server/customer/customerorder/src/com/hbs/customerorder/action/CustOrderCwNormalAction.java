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
	 * ����ȷ���˿ͻ�������Ԥ������ɹ�����
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	����˵��
	 * @action.result
	 * @return
	 */
	public String doFinanceAgree() {
		try{
			logger.debug("begin doAuditAgree");
			if(!this.getCustOrderByBizKey())
				return ERROR;
			if(custOrder == null){
				String s = "�Ҳ���ָ���Ŀͻ�������";
				setErrorReason(s);
				logger.info(s);
				return ERROR;
			}
			CustOrderMgr mgr = getMgr();
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.financeAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("����������");
				setErrorReason("����������");
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
	 * �����˻ش�����Ԥ����ȷ�Ͽͻ���������ҵ��������
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	����˵��
	 * @action.result
	 * @return
	 */
	public String doFinanceDisAgree() {
		try{
			logger.debug("begin doAuditAgree");
			if(!this.getCustOrderByBizKey())
				return ERROR;
			if(custOrder == null){
				String s = "�Ҳ���ָ���Ŀͻ�������";
				setErrorReason(s);
				logger.info(s);
				return ERROR;
			}
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			String memo = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.financeDisAgreeCustOrder(custOrder, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), memo);
			if(i != 0) {
				logger.info("����������");
				setErrorReason("����������");
				return ERROR;
			}
			logger.debug("end doAuditAgree");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doAuditAgree", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}}
