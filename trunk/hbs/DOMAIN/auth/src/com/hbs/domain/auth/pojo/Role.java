package com.hbs.domain.auth.pojo;

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
