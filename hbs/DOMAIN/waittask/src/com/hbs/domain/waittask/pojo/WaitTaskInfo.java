package com.hbs.domain.waittask.pojo;

import java.util.Date;
import java.util.Map;

/**
 * WaitTaskInfo对象.
 * @author hbs
 *
 */
public class WaitTaskInfo {
    
    /**
     * 待办编号.
     */
    private String taskId;
    
    /**
     * 待办类型:   0----业务待办  1---提醒待办.
     */
    private String taskType;
    
    /**
     * 业务关键字.
     */
    private String businessKey;
    
    /**
     * 具体的业务类型.
     */
    private String businessType;
    
    /**
     * 接受的角色.
     */
    private String roleId;
    
    /**
     * 接受的人.
     */
    private String staffId;
    
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
     * 可能的参数.
     */
    private String param;
    
    /**
     * 创建时间.
     */
    private Date createTime;
    
    /**
     * 提醒待办结束时间，对提醒类待办有效，结束后，系统自动清除提醒待办.
     */
    private Date expireTime;

    /**
     * 待办提交人
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
