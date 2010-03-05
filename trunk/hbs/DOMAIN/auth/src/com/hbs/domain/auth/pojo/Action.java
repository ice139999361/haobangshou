package com.hbs.domain.auth.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * Action����.
 * @author hbs
 *
 */
public class Action extends BaseDomain{
    
    /**
     * ���в���ID(PK.
     */
    private Integer actionsId;
    
    /**
     * ����ID.
     */
    private String actionId;
    
    /**
     * ������.
     */
    private String actionName;
    
    /**
     * ��Ӧ��ҳ�水ť��ID��.
     */
    private String buttonId;

    private String description;
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getActionsId() {
        return this.actionsId;
    }	
  
    public void setActionsId(Integer actionsId) {
        this.actionsId = actionsId;
    }
    
    public String getActionId() {
        return this.actionId;
    }	
  
    public void setActionId(String actionId) {
        this.actionId = actionId;
    }
    
    public String getActionName() {
        return this.actionName;
    }	
  
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    
    public String getButtonId() {
        return this.buttonId;
    }	
  
    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("actionsId=").append(actionsId).append(";");
		sb.append("actionId=").append(actionId).append(";");
		sb.append("actionName=").append(actionName).append(";");
		sb.append("description=").append(description).append(";");
		sb.append("buttonId=").append(buttonId);		
		return sb.toString();
	}
}
