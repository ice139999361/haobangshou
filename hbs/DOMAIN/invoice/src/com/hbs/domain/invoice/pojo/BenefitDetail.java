package com.hbs.domain.invoice.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * BenefitDetail����.
 * @author hbs
 *
 */
public class BenefitDetail extends BaseDomain{
    
    /**
     * ����ID.
     */
    private String salesId;
    
    /**
     * ������Ա.
     */
    private String salesName;
    
    /**
     * �ͻ�����.
     */
    private Date createTime;
    
    private String sendMonth;
    
    /**
     * �ͻ�����.
     */
    private String sendPoNo;
    
    /**
     * �ͻ�����.
     */
    private String custCode;
    
    /**
     * �ͻ����.
     */
    private String custShortName;
    
    /**
     * ��˾����.
     */
    private String partNo;
    
    /**
     * ��������.
     */
    private String pnDesc;
    
    /**
     * �ͻ�����.
     */
    private String custPartNo;
    
    /**
     * �ͻ�����.
     */
    private BigDecimal custPrice;
    
    /**
     * �ͻ�˰��.
     */
    private BigDecimal custTaxRate;
    
    /**
     * ����.
     */
    private BigDecimal amount;
    
    /**
     * �ܼ�.
     */
    private BigDecimal curMoney;
    
    /**
     * ����.
     */
    private BigDecimal benefit;
    
    /**
     * ��Ӧ������.
     */
    private String vendorPartNo;
    
    /**
     * ��Ӧ�̵���.
     */
    private BigDecimal vendorPrice;
    
    /**
     * ��Ӧ�̱���.
     */
    private String vendorCode;
    
    /**
     * ��Ӧ�̼��.
     */
    private String venderShortName;


    
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
    
    public Date getCreateTime() {
        return this.createTime;
    }	
  
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getSendMonth() {
		return sendMonth;
	}

	public void setSendMonth(String sendMonth) {
		this.sendMonth = sendMonth;
	}

	public String getSendPoNo() {
        return this.sendPoNo;
    }	
  
    public void setSendPoNo(String sendPoNo) {
        this.sendPoNo = sendPoNo;
    }
    
    public String getCustCode() {
        return this.custCode;
    }	
  
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }
    
    public String getCustShortName() {
        return this.custShortName;
    }	
  
    public void setCustShortName(String custShortName) {
        this.custShortName = custShortName;
    }
    
    public String getPartNo() {
        return this.partNo;
    }	
  
    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }
    
    public String getPnDesc() {
        return this.pnDesc;
    }	
  
    public void setPnDesc(String pnDesc) {
        this.pnDesc = pnDesc;
    }
    
    public String getCustPartNo() {
        return this.custPartNo;
    }	
  
    public void setCustPartNo(String custPartNo) {
        this.custPartNo = custPartNo;
    }
    
    public BigDecimal getCustPrice() {
        return this.custPrice;
    }	
  
    public void setCustPrice(BigDecimal custPrice) {
        this.custPrice = custPrice;
    }
    
    public BigDecimal getCustTaxRate() {
        return this.custTaxRate;
    }	
  
    public void setCustTaxRate(BigDecimal custTaxRate) {
        this.custTaxRate = custTaxRate;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }	
  
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getCurMoney() {
        return this.curMoney;
    }	
  
    public void setCurMoney(BigDecimal curMoney) {
        this.curMoney = curMoney;
    }
    
    public BigDecimal getBenefit() {
        return this.benefit;
    }	
  
    public void setBenefit(BigDecimal benefit) {
        this.benefit = benefit;
    }
    
    public String getVendorPartNo() {
        return this.vendorPartNo;
    }	
  
    public void setVendorPartNo(String vendorPartNo) {
        this.vendorPartNo = vendorPartNo;
    }
    
    public BigDecimal getVendorPrice() {
        return this.vendorPrice;
    }	
  
    public void setVendorPrice(BigDecimal vendorPrice) {
        this.vendorPrice = vendorPrice;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    public String getVenderShortName() {
        return this.venderShortName;
    }	
  
    public void setVenderShortName(String venderShortName) {
        this.venderShortName = venderShortName;
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("salesId=").append(salesId).append("");
		sb.append("salesName=").append(salesName).append("");
		sb.append("createTime=").append(createTime == null ? "" : createTime).append("");
		sb.append("sendMonth=").append(sendMonth).append("");
		sb.append("sendPoNo=").append(sendPoNo).append("");
		sb.append("custCode=").append(custCode).append("");
		sb.append("custShortName=").append(custShortName).append("");
		sb.append("partNo=").append(partNo).append("");
		sb.append("pnDesc=").append(pnDesc).append("");
		sb.append("custPartNo=").append(custPartNo).append("");
		sb.append("custPrice=").append(custPrice == null ? "" : custPrice).append("");
		sb.append("custTaxRate=").append(custTaxRate == null ? "" : custTaxRate).append("");
		sb.append("amount=").append(amount == null ? "" : amount).append("");
		sb.append("curMoney=").append(curMoney == null ? "" : curMoney).append("");
		sb.append("benefit=").append(benefit == null ? "" : benefit).append("");
		sb.append("vendorPartNo=").append(vendorPartNo).append("");
		sb.append("vendorPrice=").append(vendorPrice == null ? "" : vendorPrice).append("");
		sb.append("vendorCode=").append(vendorCode).append("");
		
		sb.append("venderShortName=").append(venderShortName).append("");
		
		return sb.toString();
	}
    
}
