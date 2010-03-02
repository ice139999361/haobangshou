package com.hbs.domain.auth.pojo;

import java.util.Date;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * Account����.
 * @author hbs
 *
 */
public class Account extends BaseDomain{
    
    /**
     * �û�ID.
     */
    private Integer staffId;
    
    /**
     * �û��˺�(UNIQUE).
     */
    private String account;
    
    /**
     * ����.
     */
    private String password;
    
    /**
     * ����ʱ��.
     */
    private Date loginTime;
    
    /**
     * �ǳ�ʱ��.
     */
    private Date logoutTime;
    
    /**
     * �Ƿ���Ч�� ��Ч��0�� ��Ч0��.
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
