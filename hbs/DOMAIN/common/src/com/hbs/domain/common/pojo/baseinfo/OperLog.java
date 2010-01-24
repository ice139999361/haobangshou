package com.hbs.domain.common.pojo.baseinfo;

import java.util.Date;

/**
 * OperLog对象.
 * @author hbs
 *
 */
public class OperLog {
    
    /**
     * 序列号.
     */
    private Integer seqId;
    
    /**
     * 操作时间.
     */
    private Date operTime;
    
    /**
     * 操作人ID.
     */
    private String staffId;
    
    /**
     * 操作人名称.
     */
    private String staffName;
    
    /**
     * 操作类型.
     */
    private String operType;
    
    /**
     * 操作对象.
     */
    private String operObject;
    
    /**
     * 操作关键字.
     */
    private String operKey;
    
    /**
     * 具体说明.
     */
    private String operContent;
    
    /**
     * .
     */
    private String memo;


    
    public Integer getSeqId() {
        return this.seqId;
    }	
  
    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }
    
    public Date getOperTime() {
        return this.operTime;
    }	
  
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
    
    public String getStaffId() {
        return this.staffId;
    }	
  
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    public String getStaffName() {
        return this.staffName;
    }	
  
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    public String getOperType() {
        return this.operType;
    }	
  
    public void setOperType(String operType) {
        this.operType = operType;
    }
    
    public String getOperObject() {
        return this.operObject;
    }	
  
    public void setOperObject(String operObject) {
        this.operObject = operObject;
    }
    
    public String getOperKey() {
        return this.operKey;
    }	
  
    public void setOperKey(String operKey) {
        this.operKey = operKey;
    }
    
    public String getOperContent() {
        return this.operContent;
    }	
  
    public void setOperContent(String operContent) {
        this.operContent = operContent;
    }
    
    public String getMemo() {
        return this.memo;
    }	
  
    public void setMemo(String memo) {
        this.memo = memo;
    }

}
