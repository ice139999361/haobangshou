package com.hbs.domain.waittask.pojo;


/**
 * WaitTaskConfigInfo����.
 * @author hbs
 *
 */
public class WaitTaskConfigInfo {
    
    /**
     * ���ñ��.
     */
    private String configId;
    
    /**
     * ��������:   0----ҵ�����  1---���Ѵ���.
     */
    private String taskType;
    
    /**
     * �����ҵ������.
     */
    private String businessType;
    
    /**
     * ���������URL.
     */
    private String url;
    
    /**
     * ��������.
     */
    private String comments;
    
    /**
     * ��ϵͳ����.
     */
    private String systemName;
    
    /**
     * �����Ӧ�Ľ�ɫID
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
