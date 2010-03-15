package com.hbs.domain.invoice.pojo;

import java.util.Date;

import com.hbs.common.utils.DateUtils;

/**
 * PeriodSpecMemo����.
 * @author hbs
 *
 */
public class PeriodSpecMemo {
    
    /**
     * �ͻ���Ӧ�̱���.
     */
    private String commCode;
    
    /**
     * �ͻ���Ӧ�̼��.
     */
    private String commShortName;
    
    /**
     * ��ʾ��Ӧ�̻��ǿͻ�0---�ͻ�1---��Ӧ��.
     */
    private Integer commType;
    
    /**
     * ������ID.
     */
    private String staffId;
    
    /**
     * ������.
     */
    private String staffName;
    
    /**
     * ����ʱ��.
     */
    private Date createTime;
    
    /**
     * ���ڽ��㱸ע.
     */
    private String memo;


    
    public String getCommCode() {
        return this.commCode;
    }	
  
    public void setCommCode(String commCode) {
        this.commCode = commCode;
    }
    
    public String getCommShortName() {
        return this.commShortName;
    }	
  
    public void setCommShortName(String commShortName) {
        this.commShortName = commShortName;
    }
    
    public Integer getCommType() {
        return this.commType;
    }	
  
    public void setCommType(Integer commType) {
        this.commType = commType;
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
    
    public Date getCreateTime() {
        return this.createTime;
    }	
  
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getMemo() {
        return this.memo;
    }	
  
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    public String getLogKey(){
    	StringBuilder sb = new StringBuilder(this.commCode);
    	sb.append(";").append(this.commType).append(";");
    	sb.append("���㱸ע");
    	return sb.toString();
    }
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("createTime=").append(this.createTime == null ? " " : DateUtils.getFormatDate(this.createTime,null)).append(" ");
		sb.append("commCode=").append(this.commCode).append(" ");
		sb.append("commShortName=").append(this.commShortName).append(" ");
		sb.append("commType=").append(this.commType).append(" ");
		sb.append("memo=").append(this.memo).append(" ");		
		return sb.toString();
	}
}
