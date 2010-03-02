package com.hbs.domain.auth.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * RoleResource对象.
 * @author hbs
 *
 */
public class RoleResource extends BaseDomain{
    
    /**
     * 角色ID(FK->ROLE.ROLE_ID).
     */
    private Integer roleId;
    
    /**
     * 资源ID(FK->RESOURCE.ID).
     */
    private Integer resourceId;
    
    /**
     * 操作（OPERATION_ID，有多个操作用半角逗号隔开.来自ACTION表）.
     */
    private String operations;


    
    public Integer getRoleId() {
        return this.roleId;
    }	
  
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    public Integer getResourceId() {
        return this.resourceId;
    }	
  
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
    
    public String getOperations() {
        return this.operations;
    }	
  
    public void setOperations(String operations) {
        this.operations = operations;
    }

}
