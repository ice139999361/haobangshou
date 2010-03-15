package com.hbs.domain.invoice.pojo;

import java.util.Date;

import com.hbs.common.utils.DateUtils;

/**
 * PeriodSpecMemo对象.
 * @author hbs
 *
 */
public class PeriodSpecMemo {
    
    /**
     * 客户或供应商编码.
     */
    private String commCode;
    
    /**
     * 客户或供应商简称.
     */
    private String commShortName;
    
    /**
     * 表示供应商还是客户0---客户1---供应商.
     */
    private Integer commType;
    
    /**
     * 操作人ID.
     */
    private String staffId;
    
    /**
     * 操作人.
     */
    private String staffName;
    
    /**
     * 操作时间.
     */
    private Date createTime;
    
    /**
     * 账期结算备注.
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
    	sb.append("结算备注");
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
