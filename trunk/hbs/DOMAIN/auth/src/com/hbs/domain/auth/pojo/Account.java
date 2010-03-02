package com.hbs.domain.auth.pojo;

import java.util.Date;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * Account对象.
 * @author hbs
 *
 */
public class Account extends BaseDomain{
    
    /**
     * 用户ID.
     */
    private Integer staffId;
    
    /**
     * 用户账号(UNIQUE).
     */
    private String account;
    
    /**
     * 密码.
     */
    private String password;
    
    /**
     * 登入时间.
     */
    private Date loginTime;
    
    /**
     * 登出时间.
     */
    private Date logoutTime;
    
    /**
     * 是否有效（ 有效非0； 无效0）.
     */
    private Integer enabled;


    
    public Integer getStaffId() {
        return this.staffId;
    }	
  
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    
    public String getAccount() {
        return this.account;
    }	
  
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getPassword() {
        return this.password;
    }	
  
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Date getLoginTime() {
        return this.loginTime;
    }	
  
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    
    public Date getLogoutTime() {
        return this.logoutTime;
    }	
  
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
    
    public Integer getEnabled() {
        return this.enabled;
    }	
  
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

}
