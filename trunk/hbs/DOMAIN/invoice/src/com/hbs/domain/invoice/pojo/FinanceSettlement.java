package com.hbs.domain.invoice.pojo;

import java.math.BigDecimal;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * FinanceSettlement对象.
 * @author hbs
 *
 */
public class FinanceSettlement extends BaseDomain{
    
    /**
     * 编码.
     */
    private String commCode;
    
    /**
     * 简称.
     */
    private String shortName;
    
    /**
     * 结算方式.
     */
    private String settlementType;
    
    /**
     * 摘要.
     */
    private String summery;
    
    /**
     * 总.
     */
    private BigDecimal totalMoney;
    
    /**
     * 待.
     */
    private BigDecimal needMoney;
    
    /**
     * 已.
     */
    private BigDecimal dealMoney;
    
    /**
     * 本次.
     */
    private BigDecimal curMoney;
    
    /**
     * 财状.
     */
    private String financeState;
    
    /**
     * .
     */
    private String salesId;
    
    /**
     * .
     */
    private String salesName;
    
    /**
     * .
     */
    private String assId;
    
    /**
     * .
     */
    private String assName;
    
    /**
     * .
     */
    private String staffId;
    
    /**
     * .
     */
    private String staffName;


    
    public String getCommCode() {
        return this.commCode;
    }	
  
    public void setCommCode(String commCode) {
        this.commCode = commCode;
    }
    
    public String getShortName() {
        return this.shortName;
    }	
  
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public String getSettlementType() {
        return this.settlementType;
    }
    public String getSettlementTypeDesc() {
    	String retStr ="未定义";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getSettlementType());
    	ceParam.setEncodeType("SETTLEMENT_TYPE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
    }
  
    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }
    
    public String getSummery() {
        return this.summery;
    }	
  
    public void setSummery(String summery) {
        this.summery = summery;
    }
    
    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }	
  
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    public BigDecimal getNeedMoney() {
        return this.needMoney;
    }	
  
    public void setNeedMoney(BigDecimal needMoney) {
        this.needMoney = needMoney;
    }
    
    public BigDecimal getDealMoney() {
        return this.dealMoney;
    }	
  
    public void setDealMoney(BigDecimal dealMoney) {
        this.dealMoney = dealMoney;
    }
    
    public BigDecimal getCurMoney() {
        return this.curMoney;
    }	
  
    public void setCurMoney(BigDecimal curMoney) {
        this.curMoney = curMoney;
    }
    
    public String getFinanceState() {
        return this.financeState;
    }	
    public String getFinanceStateDesc() {
    	String retStr ="未定义";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getFinanceState());
    	ceParam.setEncodeType("F_STATE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
    }
    public void setFinanceState(String financeState) {
        this.financeState = financeState;
    }
    
    public String getSalesId() {
        return this.salesId;
    }	
  
    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }
    
    public String getSalesName() {
        return this.salesName;
    }	
  
    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }
    
    public String getAssId() {
        return this.assId;
    }	
  
    public void setAssId(String assId) {
        this.assId = assId;
    }
    
    public String getAssName() {
        return this.assName;
    }	
  
    public void setAssName(String assName) {
        this.assName = assName;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("commCode").append(this.commCode).append(";");
		sb.append("shortName").append(this.shortName).append(";");
		sb.append("settlementType").append(this.settlementType).append(";");
		sb.append("summery").append(this.summery).append(";");
		sb.append("totalMoney").append(this.totalMoney == null ? null : this.totalMoney.floatValue()).append(";");
		sb.append("needMoney").append(this.needMoney == null ? null : this.needMoney.floatValue()).append(";");
		sb.append("dealMoney").append(this.dealMoney == null ? null : this.dealMoney.floatValue()).append(";");
		sb.append("curMoney").append(this.curMoney == null ? null : this.curMoney.floatValue()).append(";");
		sb.append("financeState").append(this.financeState).append(";");
		sb.append("salesId").append(this.salesId).append(";");
		sb.append("salesName").append(this.salesName).append(";");
		sb.append("assId").append(this.assId).append(";");
		sb.append("assName").append(this.assName).append(";");
		sb.append("staffId").append(this.staffId).append(";");
		sb.append("staffName").append(this.staffName).append(";");
		
		
		return sb.toString();
	}

    
    
}
