package com.hbs.domain.common.pojo.baseinfo;

import java.math.BigDecimal;


/**
 * AccountPreiod����.
 * @author hbs
 *
 */
public class AccountPreiod {
    
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
     * ��������1---�½�2---���.
     */
    private String accountType;
    
    /**
     * �ͻ����������ã����ڽ�����Ч������½ᣬ��������������������.
     */
    private String accountPeriod;
    
    /**
     * ���ڵ���ʼ�գ����ڽ�����Ч.
     */
    private String periodStart;
    
    /**
     * �����գ����ڽ�����ĵڼ��գ����ڽ�����Ч.
     */
    private String accounDay;
    
    /**
     * �����գ����ڽ����ĵڼ��գ�����ڶ����գ������ڽ�����Ч.
     */
    private String settlementDay;
    
    /**
     * �ͻ����ڵ�����׽����ڽ�����Ч.
     */
    private BigDecimal maxMoney = new BigDecimal(0);
    
    /**
     * �������ã������պͽ�������ǰ��������.
     */
    private String reminderDay;


    
    /**
	 * @return the seqId
	 */
	public String getSeqId() {
		return seqId;
	}

	/**
	 * @param seqId the seqId to set
	 */
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	
	/**
	 * @return the baseSeqId
	 */
	public String getBaseSeqId() {
		return baseSeqId;
	}

	/**
	 * @param baseSeqId the baseSeqId to set
	 */
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
    
    public String getAccountType() {
        return this.accountType;
    }	
  
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public String getAccountPeriod() {
        return this.accountPeriod;
    }	
  
    public void setAccountPeriod(String accountPeriod) {
        this.accountPeriod = accountPeriod;
    }
    
    public String getPeriodStart() {
        return this.periodStart;
    }	
  
    public void setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
    }
    
    public String getAccounDay() {
        return this.accounDay;
    }	
  
    public void setAccounDay(String accounDay) {
        this.accounDay = accounDay;
    }
    
    public String getSettlementDay() {
        return this.settlementDay;
    }	
  
    public void setSettlementDay(String settlementDay) {
        this.settlementDay = settlementDay;
    }
    
    public BigDecimal getMaxMoney() {
        return this.maxMoney;
    }	
  
    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
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
		sb.append(seqId).append(";");
		sb.append(baseSeqId).append(";");
		sb.append(commCode).append(";");
		sb.append(state).append(";");
		sb.append(accountType).append(";");
		sb.append(accountPeriod).append(";");
		sb.append(periodStart).append(";");
		sb.append(accounDay).append(";");
		sb.append(settlementDay).append(";");
		sb.append(maxMoney).append(";");
		return sb.toString();
	}

}
