package com.hbs.auth.action;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.auth.contants.AuthConstants;
import com.hbs.auth.manager.ResourceMgr;
import com.hbs.auth.manager.RoleMgr;
import com.hbs.auth.manager.RoleResourceMgr;
import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.auth.pojo.Resource;
import com.hbs.domain.auth.pojo.Role;
import com.hbs.domain.auth.pojo.RoleResource;

@SuppressWarnings("serial")
public class RoleAction extends BaseAction {
	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(RoleAction.class);
	
	/**
	 * Role比较器。根据RoleId进行比较
	 */
	public static final Comparator<Role> compRoleId = new Comparator<Role>(){
		public int compare(Role o1, Role o2) {
			return o1.getRoleId() - o2.getRoleId();
		}
	};
		
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	protected RoleMgr getMgr() {
		return (RoleMgr)getBean(AuthConstants.ROLE_MANAGER_NAME);
	}
	
	protected RoleResourceMgr getRrMgr() {
		return (RoleResourceMgr)getBean(AuthConstants.ROLE_RESOURCE_MANAGER_NAME);
	}
	
	/**
	 * 获取角色信息
	 * @action.input role.roleId
	 * @action.result role.*
	 * @return
	 */
	public String doGetInfo() {
		try {
			if(role == null || role.getRoleId() == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			Role role2 = getMgr().findRole(role.getRoleId().toString());
			if(role2 != null) {
				RoleResource res = new RoleResource();
				res.setRoleId(role2.getRoleId());
				role2.setResources(getRrMgr().listRoleResource(res));
			} else {
				logger.info("找不到角色！");
				setErrorReason("找不到角色！");
				return ERROR;
			}
			setResult("role", role2);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 查询角色信息
	 * @action.input role.*
	 * @action.result list List<Role>
	 * @action.result count 数量
	 * @return
	 */
	public String doList() {
		try {
			if(role == null) 
				role = new Role();
			setTotalCount(getMgr().listRoleCount(role));
			setResult("list", getMgr().listRole(role));
			setResult("count", getTotalCount());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 保存角色信息
	 * @action.input role.*
	 * @return
	 */
	public String doSave() {
		try {
			if(role == null) {
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
			RoleMgr mgr = getMgr();
			if(null == role.getRoleId()) {
				Role role2 = new Role();
				role2.setRoleName(role.getRoleName());
				if(mgr.listRoleCount(role2) > 0){
					logger.info("数据重复！");
					setErrorReason("数据重复！");
					return ERROR;
				}
				role.setRoleId(mgr.insertRole(role));
				setResult("roleId", role.getRoleId());
			} else {
				mgr.updateRole(role);
			}
			
			// 保存对应的资源列表
			logger.debug("process role resources");
			Integer roleId = role.getRoleId();
			if(roleId != null && role.getResources() != null) {
				RoleResourceMgr rrMgr = getRrMgr();
				rrMgr.deleteRoleResource(roleId.toString());
				for(RoleResource res : role.getResources()) {
					res.setRoleId(roleId);
					rrMgr.insertRoleResource(res);
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
		if(StringUtils.isEmpty(role.getRoleName())) {
			errs.add(new FieldErr("roleName", "roleName没有填写"));
		}
		if(StringUtils.isEmpty(role.getMemo())) {
			errs.add(new FieldErr("memo", "memo没有填写"));
		}
		
		// DONE：完成资源列表的创建
		List<RoleResource> rrlist = role.getResources();
		if(rrlist == null) {
			rrlist = new Vector<RoleResource>();
			role.setResources(rrlist);
		}
		
		ResourceMgr rmgr = (ResourceMgr)getBean(AuthConstants.RESOURCE_MANAGER_NAME);
		List<Resource> ress = rmgr.listResource(new Resource());
		Map<Integer, Resource> idmap = new HashMap<Integer, Resource>();
		Set<Integer> hasResource = new HashSet<Integer>();
		Set<Integer> leftResource = new HashSet<Integer>();
		
		for(Resource res : ress) {
			if(res == null || res.getResourceId() == null)
				continue;
			idmap.put(res.getResourceId(), res);
		}
		for(Resource res : ress) {
			if(res == null || res.getResourceId() == null)
				continue;
			String rFieldName = ResourceAction.prefixResource + res.getResourceId().toString();
			String[] actionArr = this.getHttpServletRequest().getParameterValues(rFieldName);
			if(actionArr == null || actionArr.length <= 0)
				continue;
			StringBuffer sb = new StringBuffer();
			for(String action : actionArr){
				if(StringUtils.isEmpty(action) || 
						!action.startsWith(ResourceAction.prefixAction))
					continue;
				String actionId = action.substring(ResourceAction.prefixAction.length());
				try {
					Integer.parseInt(actionId);
				}catch(Exception e){
					continue;
				}
				if(sb.length() > 0)
					sb.append(",");
				sb.append(actionId);
			}
			RoleResource rr = new RoleResource();
			rr.setRoleId(role.getRoleId());
			rr.setResourceId(res.getResourceId());
			rr.setOperations(sb.toString());
			hasResource.add(res.getResourceId());
			rrlist.add(rr);
			
			// 处理父菜单
			Resource res2 = res;
			for(Integer parent = res2.getParent(); 
				parent != null && !parent.equals(0) && ((res2 = idmap.get(parent)) != null); 
				parent = res2.getParent()){
				leftResource.add(parent);
			}
		}
		
		leftResource.removeAll(hasResource);
		for(Integer resId : leftResource) {
			// resId是已经检查过的（在处理父菜单的循环中）。如果不需要其他信息，则不用获取Resource信息
			/*
			Resource res = idmap.get(resId);
			if(res == null)
				continue;
			 */
			RoleResource rr = new RoleResource();
			rr.setRoleId(role.getRoleId());
			rr.setResourceId(resId);
			rr.setOperations("");
			rrlist.add(rr);
		}
		if(logger.isDebugEnabled()){
			StringBuffer sb = new StringBuffer();
			for(RoleResource rr: rrlist) {
				if(sb.length() > 0)
					sb.append("\r\n");
				sb.append(rr.getRoleId()).append(',')
				.append(rr.getResourceId()).append(':')
				.append(rr.getOperations());
			}
			
			logger.debug("post RoleResource data:" + sb.toString());
		}
		return errs;
	}

	/**
	 * 删除角色信息
	 * @action.input role.roleId
	 * @return
	 */
	public String doDelete() {
		try {
			if(role == null || role.getRoleId() == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			getRrMgr().deleteRoleResource(role.getRoleId().toString());
			getMgr().deleteRole(role.getRoleId().toString());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doDelete", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
