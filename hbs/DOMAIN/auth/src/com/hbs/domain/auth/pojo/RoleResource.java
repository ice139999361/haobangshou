package com.hbs.domain.auth.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * RoleResource����.
 * @author hbs
 *
 */
public class RoleResource extends BaseDomain{
    
    /**
     * ��ɫID(FK->ROLE.ROLE_ID).
     */
    private Integer roleId;
    
    /**
     * ��ԴID(FK->RESOURCE.ID).
     */
    private Integer resourceId;
    
    /**
     * ������OPERATION_ID���ж�������ð�Ƕ��Ÿ���.����ACTION��.
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
