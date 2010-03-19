package com.hbs.domain.auth.pojo;

import java.util.List;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * Role����.
 * @author hbs
 *
 */
public class Role extends BaseDomain{
    
    /**
     * ��ɫID(PK.
     */
    private Integer roleId;
    
    /**
     * ��ɫ��(UNIQUE).
     */
    private String roleName;
    
    /**
     * ע��.
     */
    private String memo;

    // begin add by xyf
    /**
     * ��ɫ��Ӧ����Դ�б�
     */
    private List<RoleResource> resources;
    
    /**
     * ��ɫ��Ӧ����Դ�б�
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
