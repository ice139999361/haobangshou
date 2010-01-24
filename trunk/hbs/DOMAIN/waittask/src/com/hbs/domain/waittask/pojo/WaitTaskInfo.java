package com.hbs.domain.waittask.pojo;

import java.util.Date;
import java.util.Map;

/**
 * WaitTaskInfo����.
 * @author hbs
 *
 */
public class WaitTaskInfo {
    
    /**
     * ������.
     */
    private String taskId;
    
    /**
     * ��������:   0----ҵ�����  1---���Ѵ���.
     */
    private String taskType;
    
    /**
     * ҵ��ؼ���.
     */
    private String businessKey;
    
    /**
     * �����ҵ������.
     */
    private String businessType;
    
    /**
     * ���ܵĽ�ɫ.
     */
    private String roleId;
    
    /**
     * ���ܵ���.
     */
    private String staffId;
    
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
     * ���ܵĲ���.
     */
    private String param;
    
    /**
     * ����ʱ��.
     */
    private Date createTime;
    
    /**
     * ���Ѵ������ʱ�䣬�������������Ч��������ϵͳ�Զ�������Ѵ���.
     */
    private Date expireTime;

    /**
     * �����ύ��
     */
    private String staffName;
    
    Map<String,String> hmParam ;
    
    
    
    /**
	 * @return the hmParam
	 */
	public Map<String, String> getHmParam() {
		return hmParam;
	}

	/**
	 * @param hmParam the hmParam to set
	 */
	public void setHmParam(Map<String, String> hmParam) {
		this.hmParam = hmParam;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getTaskId() {
        return this.taskId;
    }	
  
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public String getTaskType() {
        return this.taskType;
    }	
  
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    
    public String getBusinessKey() {
        return this.businessKey;
    }	
  
    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
    
    public String getBusinessType() {
        return this.businessType;
    }	
  
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    
    public String getRoleId() {
        return this.roleId;
    }	
  
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    public String getStaffId() {
        return this.staffId;
    }	
  
    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
    
    public String getParam() {
        return this.param;
    }	
  
    public void setParam(String param) {
        this.param = param;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }	
  
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getExpireTime() {
        return this.expireTime;
    }	
  
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

}
