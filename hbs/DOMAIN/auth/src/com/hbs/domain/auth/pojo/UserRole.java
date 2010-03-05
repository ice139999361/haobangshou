package com.hbs.domain.auth.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * UserRole����.
 * @author hbs
 *
 */
public class UserRole extends BaseDomain{
    
    /**
     * �û�ID(PK.
     */
    private Integer staffId;
    
    /**
     * ��ɫID(FK->ROLE.ROLE_ID).
     */
    private Integer roleId;
    
    /**
     * ע��.
     */
    private String memo;


    
    public Integer getStaffId() {
        return this.staffId;
    }	
  
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    
    public Integer getRoleId() {
        return this.roleId;
    }	
  
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
		sb.append("staffId=").append(staffId).append(";");
		sb.append("memo=").append(memo);		
		return sb.toString();
	}
}
