package com.hbs.domain.auth.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * Resource����.
 * @author hbs
 *
 */
public class Resource extends BaseDomain{
    
    /**
     * ��ԴID(PK.
     */
    private Integer resourceId;
    
    /**
     * ����Դҳ������в���ID��FK->ACTION.ACTIONS_ID).
     */
    private Integer actionsId;
    
    /**
     * ��Դ��.
     */
    private String resourceName;
    
    /**
     * ����.
     */
    private String description;
    
    /**
     * ��Դ��Ӧ��url��ַ
     */
    private String urlAddress;
    
    /**
	 * @return the urlAddress
	 */
	public String getUrlAddress() {
		return urlAddress;
	}

	/**
     * ��Դ���ӹ�ϵ��ʶ 0---��ʶ������Դ 1--һ����Դ����������
     */
    private Integer parent;
    
    /**
     * �Ƿ��ǲ˵���Դ��0---��  1---����
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
