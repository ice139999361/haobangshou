package com.hbs.domain.invoice.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * BenefitDetail对象.
 * @author hbs
 *
 */
public class BenefitDetail {
    
    /**
     * 销售ID.
     */
    private String salesId;
    
    /**
     * 销售人员.
     */
    private String salesName;
    
    /**
     * 送货日期.
     */
    private Date createTime;
    
    /**
     * 送货单号.
     */
    private String sendOoNo;
    
    /**
     * 客户编码.
     */
    private String custCode;
    
    /**
     * 客户简称.
     */
    private String custShortName;
    
    /**
     * 公司物料.
     */
    private String partNo;
    
    /**
     * 物料描述.
     */
    private String pnDesc;
    
    /**
     * 客户物料.
     */
    private String custPartNo;
    
    /**
     * 客户单价.
     */
    private BigDecimal custPrice;
    
    /**
     * 客户税率.
     */
    private BigDecimal custTaxRate;
    
    /**
     * 数量.
     */
    private BigDecimal amount;
    
    /**
     * 总价.
     */
    private BigDecimal curMoney;
    
    /**
     * 利差.
     */
    private BigDecimal benefit;
    
    /**
     * 供应商物料.
     */
    private String vendorPartNo;
    
    /**
     * 供应商单价.
     */
    private BigDecimal vendorPrice;
    
    /**
     * 供应商编码.
     */
    private String vendorCode;
    
    /**
     * 供应商简称.
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
