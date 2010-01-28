package com.hbs.domain.common.pojo;


/**
 * SysSequence对象.
 * @author hbs
 *
 */
public class SysSequence {
    
    /**
     * SEQENCE类型.
     */
    private String seqType;
    
    /**
     * KEY值.
     */
    private String seqKey;
    
    /**
     * 当前序列值.
     */
    private Integer seqValue;
    
    /**
     * 序列值要求长度.
     */
    private Integer seqLength;
    
    /**
     * 前缀.
     */
    private String seqPrefix;
    
    /**
     * 中段.
     */
    private String seqMiddle;


    
    public String getSeqType() {
        return this.seqType;
    }	
  
    public void setSeqType(String seqType) {
        this.seqType = seqType;
    }
    
    public String getSeqKey() {
        return this.seqKey;
    }	
  
    public void setSeqKey(String seqKey) {
        this.seqKey = seqKey;
    }
    
    public Integer getSeqValue() {
        return this.seqValue;
    }	
  
    public void setSeqValue(Integer seqValue) {
        this.seqValue = seqValue;
    }
    
    public Integer getSeqLength() {
        return this.seqLength;
    }	
  
    public void setSeqLength(Integer seqLength) {
        this.seqLength = seqLength;
    }
    
    public String getSeqPrefix() {
        return this.seqPrefix;
    }	
  
    public void setSeqPrefix(String seqPrefix) {
        this.seqPrefix = seqPrefix;
    }
    
    public String getSeqMiddle() {
        return this.seqMiddle;
    }	
  
    public void setSeqMiddle(String seqMiddle) {
        this.seqMiddle = seqMiddle;
    }

}
