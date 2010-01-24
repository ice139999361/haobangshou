package com.hbs.domain.common.pojo.baseinfo;


/**
 * BankInfo对象.
 * @author hbs
 *
 */
public class BankInfo {
    
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
     * 开户户名.
     */
    private String accountName;
    
    /**
     * 开户行.
     */
    private String accountBank;
    
    /**
     * 帐号.
     */
    private String account;


    
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
    
    public String getAccountName() {
        return this.accountName;
    }	
  
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    public String getAccountBank() {
        return this.accountBank;
    }	
  
    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }
    
    public String getAccount() {
        return this.account;
    }	
  
    public void setAccount(String account) {
        this.account = account;
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
		sb.append(accountName).append(";");
		sb.append(accountBank).append(";");
		sb.append(account).append(";");
		
		return sb.toString();
	}

}
