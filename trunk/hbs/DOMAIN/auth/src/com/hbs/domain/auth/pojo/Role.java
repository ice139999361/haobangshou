package com.hbs.domain.auth.pojo;

import java.util.List;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * Role对象.
 * @author hbs
 *
 */
public class Role extends BaseDomain{
    
    /**
     * 角色ID(PK.
     */
    private Integer roleId;
    
    /**
     * 角色名(UNIQUE).
     */
    private String roleName;
    
    /**
     * 注释.
     */
    private String memo;

    // begin add by xyf
    /**
     * 角色对应的资源列表
     */
    private List<RoleResource> resources;
    
    /**
     * 角色对应的资源列表
     */
    public List<RoleResource> getResources() {
		return resources;
	}

	public void setResources(List<RoleResource> resources) {
		this.resources = resources;
	}
	// end add by xyf
	
	public Integer getRoleId() {
        return this.roleId;
    }	
  
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return this.roleName;
    }	
  
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getMemo() {
        return this.memo;
    }	
  
    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("roleId=").append(roleId).append(";");
		sb.append("roleName=").append(roleName).append(";");
		sb.append("memo=").append(memo);		
		return sb.toString();
	}
}
