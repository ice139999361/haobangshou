package com.hbs.domain.common.pojo.baseinfo;


/**
 * PrePaidInfo����.
 * @author hbs
 *
 */
public class PrePaidInfo {
    
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
     * Ԥ���ٷֱ�.
     */
    private String prePaid;
    
    /**
     * �Ի�������������г���Ա�߿�.
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
