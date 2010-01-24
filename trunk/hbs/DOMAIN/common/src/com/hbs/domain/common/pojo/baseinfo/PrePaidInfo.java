package com.hbs.domain.common.pojo.baseinfo;


/**
 * PrePaidInfo对象.
 * @author hbs
 *
 */
public class PrePaidInfo {
    
	/**
	 * 序列号
	 */
	private String seqId;
	
	/**
	 * 引用的基本信息ID
	 */
	private String baseSeqId;
	
	
    /**
     * 客户/供应商编码.
     */
    private String commCode;
    
    /**
     * 状态0----正式数据1---临时数据（没有提交审批）2---待审批数据3---审批不通过4---废弃数据.
     */
    private String state;
    
    /**
     * 预付百分比.
     */
    private String prePaid;
    
    /**
     * 对货到付款，加提醒市场人员催款.
     */
    private String reminderDay;


    
    public String getSeqId() {
		return seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	public String getBaseSeqId() {
		return baseSeqId;
	}

	public void setBaseSeqId(String baseSeqId) {
		this.baseSeqId = baseSeqId;
	}

	public String getCommCode() {
        return this.commCode;
    }	
  
    public void setCommCode(String commCode) {
        this.commCode = commCode;
    }
    
    public String getState() {
        return this.state;
    }	
  
    public void setState(String state) {
        this.state = state;
    }
    
    public String getPrePaid() {
        return this.prePaid;
    }	
  
    public void setPrePaid(String prePaid) {
        this.prePaid = prePaid;
    }
    
    public String getReminderDay() {
        return this.reminderDay;
    }	
  
    public void setReminderDay(String reminderDay) {
        this.reminderDay = reminderDay;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(baseSeqId).append(";");
		sb.append(commCode).append(";");
		sb.append(state).append(";");
		sb.append(prePaid).append(";");
		sb.append(reminderDay);
		return sb.toString();
	}

}
