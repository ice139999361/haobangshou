package com.hbs.domain.auth.pojo;

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

}
