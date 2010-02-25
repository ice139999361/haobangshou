package com.hbs.domain.common.pojo.baseinfo;

import java.math.BigDecimal;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;


/**
 * AccountPreiod对象.
 * @author hbs
 *
 */
public class AccountPreiod {
    
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
     * 账期类型1---月结2---天结.
     */
    private String accountType;
    
    /**
     * 客户的账期设置，账期结算有效，如果月结，是月数，如果天结是天数.
     */
    private String accountPeriod;
    
    /**
     * 账期的起始日，账期结算有效.
     */
    private String periodStart;
    
    /**
     * 对账日，账期结束后的第几日，账期结算有效.
     */
    private String accounDay;
    
    /**
     * 结算日，账期结束的第几日，须大于对账日，对账期结算有效.
     */
    private String settlementDay;
    
    /**
     * 客户账期的最大交易金额，账期结算有效.
     */
    private BigDecimal maxMoney = new BigDecimal(0);
    
    /**
     * 提醒设置，对账日和结算日提前几天提醒.
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
    
    /**
     * 获取账期结算的账期类型
     * @return
     */
    public String getAccountTypeDesc(){
    	String retStr ="未定义";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getAccountType());
    	ceParam.setEncodeType("ACCOUNT_TYPE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeDesc();
    	}
    	return retStr;
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
