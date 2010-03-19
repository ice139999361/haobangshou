package com.hbs.auth.action;

import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.auth.contants.AuthConstants;
import com.hbs.auth.manager.StaffMgr;
import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.auth.pojo.Staff;

@SuppressWarnings("serial")
public class StaffAction extends BaseAction {
	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(StaffAction.class);
	
	private Staff staff;

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	protected StaffMgr getMgr() {
		return (StaffMgr)getBean(AuthConstants.STAFF_MANAGER_NAME);
	}
	
	/**
	 * ��ȡ�û���Ϣ
	 * @action.input staff.staffId
	 * @action.result staff.*
	 * @return
	 */
	public String doGetInfo() {
		try {
			if(staff == null || staff.getStaffId() == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			setResult("staff", getMgr().findStaff(staff.getStaffId().toString()));
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * ��ѯ�û���Ϣ
	 * @action.input staff.*
	 * @action.result list List<Staff>
	 * @action.result count ����
	 * @return
	 */
	public String doList() {
		try {
			if(staff == null) 
				staff = new Staff();
			setTotalCount(getMgr().listStaffCount(staff));
			setResult("list", getMgr().listStaff(staff));
			setResult("count", getTotalCount());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
	
	/**
	 * �����û���Ϣ
	 * @action.input staff.*
	 * @return
	 */
	public String doSave() {
		try {
			if(staff == null) {
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
			StaffMgr mgr = getMgr();
			if(null == staff.getStaffId()) {
				staff.setStaffId(mgr.insertStaff(staff));
				setResult("staffId", staff.getStaffId());
			} else {
				mgr.updateStaff(staff);
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
		if(StringUtils.isEmpty(staff.getDuty())) {
			errs.add(new FieldErr("duty", "dutyû����д"));
		}
		if(StringUtils.isEmpty(staff.getStaffName())) {
			errs.add(new FieldErr("staffName", "staffNameû����д"));
		}
		// TODO������û���Ϣ���
		return errs;
	}

	/**
	 * ɾ���û���Ϣ
	 * @action.input staff.staffId
	 * @return
	 */
	public String doDelete() {
		try {
			if(staff == null || staff.getStaffId() == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			getMgr().deleteStaff(staff.getStaffId().toString());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doDelete", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
