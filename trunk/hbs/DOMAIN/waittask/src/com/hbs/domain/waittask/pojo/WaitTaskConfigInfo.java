package com.hbs.domain.waittask.pojo;


/**
 * WaitTaskConfigInfo对象.
 * @author hbs
 *
 */
public class WaitTaskConfigInfo {
    
    /**
     * 配置编号.
     */
    private String configId;
    
    /**
     * 待办类型:   0----业务待办  1---提醒待办.
     */
    private String taskType;
    
    /**
     * 具体的业务类型.
     */
    private String businessType;
    
    /**
     * 待办的连接URL.
     */
    private String url;
    
    /**
     * 待办描述.
     */
    private String comments;
    
    /**
     * 子系统名称.
     */
    private String systemName;
    
    /**
     * 待办对应的角色ID
     */
    private String roleId;


    
    public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getConfigId() {
        return this.configId;
    }	
  
    public void setConfigId(String configId) {
        this.configId = configId;
    }
    
    public String getTaskType() {
        return this.taskType;
    }	
  
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    
    public String getBusinessType() {
        return this.businessType;
    }	
  
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    
    public String getUrl() {
        return this.url;
    }	
  
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getComments() {
        return this.comments;
    }	
  
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public String getSystemName() {
        return this.systemName;
    }	
  
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

}
