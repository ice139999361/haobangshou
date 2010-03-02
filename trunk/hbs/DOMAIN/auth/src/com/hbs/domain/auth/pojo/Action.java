package com.hbs.domain.auth.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * Action对象.
 * @author hbs
 *
 */
public class Action extends BaseDomain{
    
    /**
     * 所有操作ID(PK.
     */
    private Integer actionsId;
    
    /**
     * 操作ID.
     */
    private String actionId;
    
    /**
     * 操作名.
     */
    private String actionName;
    
    /**
     * 对应的页面按钮的ID名.
     */
    private String buttonId;


    
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

}
