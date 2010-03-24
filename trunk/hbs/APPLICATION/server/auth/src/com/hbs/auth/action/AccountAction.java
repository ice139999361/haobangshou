package com.hbs.auth.action;

import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.auth.contants.AuthConstants;
import com.hbs.auth.manager.AccountMgr;
import com.hbs.auth.manager.LoginMgr;
import com.hbs.auth.manager.StaffMgr;
import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.auth.pojo.Account;

@SuppressWarnings("serial")
public class AccountAction extends BaseAction {
	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(AccountAction.class);
	
	private Account account;

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
	protected AccountMgr getMgr() {
		return (AccountMgr)getBean(AuthConstants.ACCOUNT_MANAGER_NAME);
	}
	
	/**
	 * ��ȡ�˻���Ϣ
	 * @action.input account.account
	 * @action.result account.*
	 * @return
	 */
	public String doGetInfo() {
		try {
			if(account == null || StringUtils.isEmpty(account.getAccount())) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			setResult("account", getMgr().findAccount(account.getAccount()));
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ��ѯ�˻���Ϣ
	 * @action.input account.*
	 * @action.result list List<Account>
	 * @action.result count ����
	 * @return
	 */
	public String doList() {
		try {
			if(account == null) 
				account = new Account();
			setTotalCount(getMgr().listAccountCount(account));
			setResult("list", getMgr().listAccount(account));
			setResult("count", getTotalCount());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �����˻���Ϣ
	 * @action.input account.*
	 * @return
	 */
	public String doSave() {
		try {
			if(account == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			List<FieldErr> errs = checkInputFields();
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			account.setPassword(LoginMgr.transformPassword(account.getPassword()));
			AccountMgr mgr = getMgr();
			if(null == mgr.findAccount(account.getAccount())) {
				mgr.insertAccount(account);
			} else {
				mgr.updateAccount(account);
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSave", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	private List<FieldErr> checkInputFields() throws Exception {
		List<FieldErr> errs = new Vector<FieldErr>();
		if(account.getEnabled() == null)
			account.setEnabled(1);
		if(StringUtils.isEmpty(account.getAccount())) {
			errs.add(new FieldErr("account", "accountû����д"));
		}
		if(StringUtils.isEmpty(account.getPassword())) {
			errs.add(new FieldErr("password", "passwordû����д"));
		}
		Integer staff = account.getStaffId();
		if(staff == null) {
			errs.add(new FieldErr("staffId", "staffIdû����д"));
		} else {
			StaffMgr staffMgr = (StaffMgr)getBean(AuthConstants.STAFF_MANAGER_NAME);
			if(staffMgr.findStaff(staff.toString()) == null)
				errs.add(new FieldErr("staffId", "staffId����"));
		}
		return errs;
	}

	/**
	 * ɾ���˻���Ϣ
	 * @action.input account.account
	 * @return
	 */
	public String doDelete() {
		try {
			if(account == null || StringUtils.isEmpty(account.getAccount())) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			getMgr().deleteAccount(account.getAccount());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doDelete", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
