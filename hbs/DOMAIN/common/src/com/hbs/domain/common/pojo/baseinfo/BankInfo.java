package com.hbs.domain.common.pojo.baseinfo;


/**
 * BankInfo����.
 * @author hbs
 *
 */
public class BankInfo {
    
	/**
	 * ���к�
	 */
	private String seqId;
	
	/**
	 * ���õĻ�����ϢID
	 */
	private String baseSeqId;
	
    /**
     * �ͻ�/��Ӧ�̱���.
     */
    private String commCode;
    
    /**
     * ״̬0----��ʽ����1---��ʱ���ݣ�û���ύ������2---����������3---������ͨ��4---��������.
     */
    private String state;
    
    /**
     * ��������.
     */
    private String accountName;
    
    /**
     * ������.
     */
    private String accountBank;
    
    /**
     * �ʺ�.
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
