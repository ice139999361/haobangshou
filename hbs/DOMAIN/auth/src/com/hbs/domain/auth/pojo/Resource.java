package com.hbs.domain.auth.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * Resource对象.
 * @author hbs
 *
 */
public class Resource extends BaseDomain{
    
    /**
     * 资源ID(PK.
     */
    private Integer resourceId;
    
    /**
     * 该资源页面的所有操作ID（FK->ACTION.ACTIONS_ID).
     */
    private Integer actionsId;
    
    /**
     * 资源名.
     */
    private String resourceName;
    
    /**
     * 描述.
     */
    private String description;


    
    public Integer getResourceId() {
        return this.resourceId;
    }	
  
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
    
    public Integer getActionsId() {
        return this.actionsId;
    }	
  
    public void setActionsId(Integer actionsId) {
        this.actionsId = actionsId;
    }
    
    public String getResourceName() {
        return this.resourceName;
    }	
  
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    public String getDescription() {
        return this.description;
    }	
  
    public void setDescription(String description) {
        this.description = description;
    }

}
