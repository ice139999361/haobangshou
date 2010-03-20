package com.hbs.auth.action;

import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.auth.contants.AuthConstants;
import com.hbs.auth.manager.AccountMgr;
import com.hbs.auth.manager.StaffMgr;
import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.auth.pojo.Account;
import com.hbs.domain.auth.pojo.Staff;

@SuppressWarnings("serial")
public class UserAction extends BaseAction  {
	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	private Staff staff;
	
	private Account account;

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	protected AccountMgr getAMgr() {
		return (AccountMgr)getBean(AuthConstants.ACCOUNT_MANAGER_NAME);
	}
	
	protected StaffMgr getSMgr() {
		return (StaffMgr)getBean(AuthConstants.STAFF_MANAGER_NAME);
	}
	
	/**
	 * 获取用户信息
	 * @action.input staff.staffId
	 * @action.result staff.*
	 * @action.result account.*
	 * @return
	 */
	public String doGetInfo() {
		try {
			if(staff == null || staff.getStaffId() == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			setResult("staff", getSMgr().findStaff(staff.getStaffId().toString()));
			setResult("account", getAMgr().findAccountById(staff.getStaffId().toString()));
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 保存账户信息
	 * @action.input staff.*
	 * @action.input account.*
	 * @return
	 */
	public String doSave() {
		try {
			if(staff == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			List<FieldErr> errs = checkInputFields();
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			StaffMgr mgr = getSMgr();
			if(staff.getStaffId() == null) {
				mgr.insertStaff(staff);
				account.setStaffId(staff.getStaffId());
				getAMgr().insertAccount(account);
			} else {
				mgr.updateStaff(staff);
				account.setStaffId(staff.getStaffId());
				getAMgr().updateAccount(account);
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSave", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	private List<FieldErr> checkInputFields() {
		List<FieldErr> errs = new Vector<FieldErr>();
		// staff部分
		if(StringUtils.isEmpty(staff.getDuty())) {
			errs.add(new FieldErr("duty", "duty没有填写"));
		}
		if(StringUtils.isEmpty(staff.getStaffName())) {
			errs.add(new FieldErr("staffName", "staffName没有填写"));
		}
		// TODO：完成用户信息检查 UserAction
		
		// account部分
		if(account.getEnabled() == null)
			account.setEnabled(1);
		if(StringUtils.isEmpty(account.getAccount())) {
			errs.add(new FieldErr("account", "account没有填写"));
		}
		if(StringUtils.isEmpty(account.getPassword())) {
			errs.add(new FieldErr("password", "password没有填写"));
		}
		return errs;
	}

	/**
	 * 删除用户信息
	 * @action.input staff.staffId
	 * @return
	 */
	public String doDelete() {
		try {
			if(staff == null || staff.getStaffId() == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			account = getAMgr().findAccountById(staff.getStaffId().toString());
			if(account != null)
				getAMgr().deleteAccount(account.getAccount());
			getSMgr().deleteStaff(staff.getStaffId().toString());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doDelete", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 查询用户信息
	 * @action.input staff.* + (dynamicFields.account)
	 * @action.result list List<Staff> + (dynamicFields.account)
	 * @action.result count 数量
	 * @return
	 */
	public String doList() {
		try {
			if(staff == null) 
				staff = new Staff();
			setTotalCount(getSMgr().listStaffCount(staff));
			List<Staff> list = getSMgr().listStaff(staff);
			// 获取account，放在dynamicFields.account
			AccountMgr amgr = getAMgr();
			for(Staff s : list) {
				Account a = amgr.findAccountById(s.getStaffId().toString());
				if(a != null)
					s.setField("account", a.getAccount());
			}
			setResult("list", list);
			setResult("count", getTotalCount());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	

}
