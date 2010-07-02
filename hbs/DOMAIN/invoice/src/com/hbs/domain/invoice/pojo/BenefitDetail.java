package com.hbs.domain.invoice.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * BenefitDetail����.
 * @author hbs
 *
 */
public class BenefitDetail {
    
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
    
    /**
     * �ͻ�����.
     */
    private String sendOoNo;
    
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
    
    public String getSendOoNo() {
        return this.sendOoNo;
    }	
  
    public void setSendOoNo(String sendOoNo) {
        this.sendOoNo = sendOoNo;
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

}
