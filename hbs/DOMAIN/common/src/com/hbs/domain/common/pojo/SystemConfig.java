package com.hbs.domain.common.pojo;

import java.util.Date;

/**
 * SystemConfig对象.
 * @author sims autoCoder1.0
 *
 */
public class SystemConfig {
    
    /**
     * 参数名称.
     */
    private String configName;
    
    /**
     * 参数值.
     */
    private String configValue;
    
    /**
     * 类型.
     */
    private Integer valueType;
    
    /**
     * 取值范围.
     */
    private String valueRange;
    
    /**
     * 参数描述.
     */
    private String configDesc;
    
    /**
     * 是否可见.
     */
    private String visibaleFlag;
    
    /**
     * 归属站点.
     */
    private String configRegion;
    
    /**
     * 归属子系统名称.
     */
    private String subSystem;
    
    /**
     * 最后更新时间.
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
