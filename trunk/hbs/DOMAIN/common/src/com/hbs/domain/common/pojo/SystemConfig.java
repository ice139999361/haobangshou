package com.hbs.domain.common.pojo;

import java.util.Date;

/**
 * SystemConfig����.
 * @author sims autoCoder1.0
 *
 */
public class SystemConfig {
    
    /**
     * ��������.
     */
    private String configName;
    
    /**
     * ����ֵ.
     */
    private String configValue;
    
    /**
     * ����.
     */
    private Integer valueType;
    
    /**
     * ȡֵ��Χ.
     */
    private String valueRange;
    
    /**
     * ��������.
     */
    private String configDesc;
    
    /**
     * �Ƿ�ɼ�.
     */
    private String visibaleFlag;
    
    /**
     * ����վ��.
     */
    private String configRegion;
    
    /**
     * ������ϵͳ����.
     */
    private String subSystem;
    
    /**
     * ������ʱ��.
     */
    private Date lastUpdate;


    
    public String getConfigName() {
        return this.configName;
    }	
  
    public void setConfigName(String configName) {
        this.configName = configName;
    }
    
    public String getConfigValue() {
        return this.configValue;
    }	
  
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
    
    public Integer getValueType() {
        return this.valueType;
    }	
  
    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }
    
    public String getValueRange() {
        return this.valueRange;
    }	
  
    public void setValueRange(String valueRange) {
        this.valueRange = valueRange;
    }
    
    public String getConfigDesc() {
        return this.configDesc;
    }	
  
    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }
    
    public String getVisibaleFlag() {
        return this.visibaleFlag;
    }	
  
    public void setVisibaleFlag(String visibaleFlag) {
        this.visibaleFlag = visibaleFlag;
    }
    
    public String getConfigRegion() {
        return this.configRegion;
    }	
  
    public void setConfigRegion(String configRegion) {
        this.configRegion = configRegion;
    }
    
    public String getSubSystem() {
        return this.subSystem;
    }	
  
    public void setSubSystem(String subSystem) {
        this.subSystem = subSystem;
    }
    
    public Date getLastUpdate() {
        return this.lastUpdate;
    }	
  
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
