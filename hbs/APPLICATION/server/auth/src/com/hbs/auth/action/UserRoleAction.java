package com.hbs.auth.action;

import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.auth.contants.AuthConstants;
import com.hbs.auth.manager.RoleMgr;
import com.hbs.auth.manager.StaffMgr;
import com.hbs.auth.manager.StaffRoleMgr;
import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.auth.pojo.Staff;
import com.hbs.domain.auth.pojo.UserRole;

@SuppressWarnings("serial")
public class UserRoleAction extends BaseAction {
	
	/**
	 * 前台传来的用户名的字段名
	 * @see UserRoleAction.fixUserName()
	 */
	private static final String userNameFieldName = "userRole.userName";

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(UserRoleAction.class);
	
	private static final String roleIdName = "roleId";
	
	private UserRole userRole;

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	protected StaffRoleMgr getMgr() {
		return (StaffRoleMgr)getBean(AuthConstants.STAFF_ROLE_MANAGER_NAME);
	}
	
	/**
	 * 获取用户权限信息
	 * @action.input userRole.staffId
	 * @action.result roles List<UserRole>
	 * @return
	 */
	public String doGetInfo() {
		try {
			if(userRole == null){
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			fixUserName();
			if(null == userRole.getStaffId()) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			setResult("roles", getMgr().listUserRole(userRole));
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 查询用户权限信息
	 * @action.input userRole.*
	 * @action.result list List<UserRole>
	 * @action.result count 数量
	 * @return
	 */
	public String doList() {
		try {
			if(userRole == null) 
				userRole = new UserRole();
			fixUserName();
			setTotalCount(getMgr().listUserRoleCount(userRole));
			setResult("list", getMgr().listUserRole(userRole));
			setResult("count", getTotalCount());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 保存用户权限信息
	 * @action.input userRole.staffId
	 * @action.input roleId 多个
	 * @return
	 */
	public String doSave() {
		try {
			if(userRole == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			fixUserName();
			List<FieldErr> errs = checkInputFields();
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			String[] roles = this.getHttpServletRequest().getParameterValues(roleIdName);
			if(roles != null) {
				StaffRoleMgr mgr = getMgr();
				mgr.deleteUserRole(userRole.getStaffId().toString());
				UserRole ur2 = new UserRole();
				ur2.setStaffId(userRole.getStaffId());
				RoleMgr rmgr = (RoleMgr)getBean("roleMgr");
				for(String roleId : roles) {
					try {
						ur2.setRoleId(Integer.parseInt(roleId));
					}catch(Exception e) {
						logger.info("catch Exception in doSave roleid=" + roleId);
						continue;
					}
					if(null == rmgr.findRole(roleId)) {
						logger.error("roleId " + roleId + " is not found!");
						continue;
					}
					try{
						mgr.insertUserRole(ur2);
					}catch(Exception e){
						logger.error("catch Exception in doSave roleid=" + roleId, e);
					}
				}
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSave", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	private List<FieldErr> checkInputFields() throws Exception {
		List<FieldErr> errs = new Vector<FieldErr>();
		Integer staff = userRole.getStaffId();
		if(staff == null) {
			errs.add(new FieldErr("staffId", "staffId没有填写"));
		} else {
			StaffMgr staffMgr = (StaffMgr)getBean(AuthConstants.STAFF_MANAGER_NAME);
			if(staffMgr.findStaff(staff.toString()) == null)
				errs.add(new FieldErr("staffId", "staffId错误"));
		}
		return errs;
	}

	/**
	 * 删除用户权限信息
	 * @action.input userRole.staffId
	 * @return
	 */
	public String doDelete() {
		try {
			if(userRole == null){
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			fixUserName();
			if(null == userRole.getStaffId()) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			getMgr().deleteUserRole(userRole.getStaffId().toString());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doDelete", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 根据userName设置staffId
	 * @see UserRoleAction.userNameFieldName
	 */
	private void fixUserName() {
		try {
			if(userRole == null)
				userRole = new UserRole();
			else if(userRole.getStaffId() != null)
				return;
			String userName = this.getHttpServletRequest().getParameter(userNameFieldName);
			if(StringUtils.isEmpty(userName))
				return;
			StaffMgr mgr = (StaffMgr)getBean(AuthConstants.STAFF_MANAGER_NAME);
			Staff staff = new Staff();
			staff.setStaffName(userName);
			List<Staff> list = mgr.listStaff(staff);
			if(list == null || list.size() <= 0)
				return;
			userRole.setStaffId(list.get(0).getStaffId());
		} catch (Exception e) {
			logger.error("catch Exception in fixUserName", e);
		}
		
	}

}
