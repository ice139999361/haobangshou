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
    
    /**
     * 资源对应的url地址
     */
    private String urlAddress;
    
    /**
	 * @return the urlAddress
	 */
	public String getUrlAddress() {
		return urlAddress;
	}

	/**
     * 资源父子关系标识 0---标识顶级资源 1--一级资源，依次类推
     */
    private Integer parent;
    
    /**
     * 是否是菜单资源，0---是  1---不是
     */
    private Integer isMenu;
    
	/**
	 * @param urlAddress the urlAddress to set
	 */
	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}

	/**
	 * @return the parent
	 */
	public Integer getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Integer parent) {
		this.parent = parent;
	}

	/**
	 * @return the isMenu
	 */
	public Integer getIsMenu() {
		return isMenu;
	}

	/**
	 * @param isMenu the isMenu to set
	 */
	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	
    
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

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("resourceId=").append(resourceId).append(";");
		sb.append("actionsId=").append(actionsId).append(";");
		sb.append("resourceName=").append(resourceName).append(";");
		sb.append("description=").append(description);		
		return sb.toString();
	}
}
