package com.hbs.domain.common.pojo;




/**
 * DisnConfigEncode����.
 * @author sims autoCoder1.0
 *
 */
public class ConfigEncode {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * ��������.
     */
    private String encodeType;
    
    /**
     * ������.
     */
    private String encodeKey;
    
    /**
     * ����ֵ.
     */
    private String encodeValue;
    
    /**
     * ����.
     */
    private String encodeDesc;
    
    /**
     * �Ƿ���Ч.
     */
    private String isValid;
    
    /**
     * �����ͬһ�������ֵ����ֵ���������.
     */
    private Integer sortId;


    
    public String getEncodeType() {
        return this.encodeType;
    }	
  
    public void setEncodeType(String encodeType) {
        this.encodeType = encodeType;
    }
    
    public String getEncodeKey() {
        return this.encodeKey;
    }	
  
    public void setEncodeKey(String encodeKey) {
        this.encodeKey = encodeKey;
    }
    
    public String getEncodeValue() {
        return this.encodeValue;
    }	
  
    public void setEncodeValue(String encodeValue) {
        this.encodeValue = encodeValue;
    }
    
    public String getEncodeDesc() {
        return this.encodeDesc;
    }	
  
    public void setEncodeDesc(String encodeDesc) {
        this.encodeDesc = encodeDesc;
    }
    
    public String getIsValid() {
        return this.isValid;
    }	
  
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
    
    public Integer getSortId() {
        return this.sortId;
    }	
  
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

}
