package com.hbs.domain.vendor.order.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * VendorOrderDetail对象.
 * @author hbs
 *
 */
public class VendorOrderDetail {
    
    /**
     * 唯一的seqid（序列号）.
     */
    private String operSeqId;
    
    /**
     * 供应商编码.
     */
    private String commCode;
    
    /**
     * 简称.
     */
    private String shortName;
    
    /**
     * 订单类型0-订单1-退货单.
     */
    private String poNoType;
    
    /**
     * 订单编号.
     */
    private String poNo;
    
    /**
     * 物料编号.
     */
    private Date cpartNo;
    
    /**
     * 公司物料编号.
     */
    private String partNo;
    
    /**
     * 物料描述.
     */
    private String pnDesc;
    
    /**
     * 单价.
     */
    private BigDecimal cprice;
    
    /**
     * 是否含税交易1--是0--否如果单价是含税的，则一定是1，否则可以选择是否含税交易.
     */
    private String isTax;
    
    /**
     * 税率.
     */
    private BigDecimal taxRate;
    
    /**
     * 特殊备注，如有的客户物料中有批次概念，可以填入该字段.
     */
    private String specDesc;
    
    /**
     * 一般备注.
     */
    private String commDesc;
    
    /**
     * 订货数量.
     */
    private Integer amount;
    
    /**
     * 总金额.
     */
    private BigDecimal money;
    
    /**
     * 已经收货数量.
     */
    private Integer deliveryAmount;
    
    /**
     * 订单原始交货日期.
     */
    private Date orgDeliveryDate;
    
    /**
     * 采购部最终确认交货日期.
     */
    private Date verDeliveryDate;
    
    /**
     * 订单明细所属账期.
     */
    private String period;
    
    /**
     * 关联的客户订单号，可能有多个客户订单单号，以.
     */
    private String rltOrderPoNo;
    
    /**
     * 订单明细状态.
     */
    private String state;
    
    /**
     * 订单的活动状态.
     */
    private String activeState;
    
    /**
     * 关联的入库单号，可能有多个，以.
     */
    private String rltRecPoNo;


    
    public String getOperSeqId() {
        return this.operSeqId;
    }	
  
    public void setOperSeqId(String operSeqId) {
        this.operSeqId = operSeqId;
    }
    
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
    
    public String getPoNoType() {
        return this.poNoType;
    }	
  
    public void setPoNoType(String poNoType) {
        this.poNoType = poNoType;
    }
    
    public String getPoNo() {
        return this.poNo;
    }	
  
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }
    
    public Date getCpartNo() {
        return this.cpartNo;
    }	
  
    public void setCpartNo(Date cpartNo) {
        this.cpartNo = cpartNo;
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
    
    public BigDecimal getCprice() {
        return this.cprice;
    }	
  
    public void setCprice(BigDecimal cprice) {
        this.cprice = cprice;
    }
    
    public String getIsTax() {
        return this.isTax;
    }	
  
    public void setIsTax(String isTax) {
        this.isTax = isTax;
    }
    
    public BigDecimal getTaxRate() {
        return this.taxRate;
    }	
  
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    public String getSpecDesc() {
        return this.specDesc;
    }	
  
    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc;
    }
    
    public String getCommDesc() {
        return this.commDesc;
    }	
  
    public void setCommDesc(String commDesc) {
        this.commDesc = commDesc;
    }
    
    public Integer getAmount() {
        return this.amount;
    }	
  
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }	
  
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public Integer getDeliveryAmount() {
        return this.deliveryAmount;
    }	
  
    public void setDeliveryAmount(Integer deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }
    
    public Date getOrgDeliveryDate() {
        return this.orgDeliveryDate;
    }	
  
    public void setOrgDeliveryDate(Date orgDeliveryDate) {
        this.orgDeliveryDate = orgDeliveryDate;
    }
    
    public Date getVerDeliveryDate() {
        return this.verDeliveryDate;
    }	
  
    public void setVerDeliveryDate(Date verDeliveryDate) {
        this.verDeliveryDate = verDeliveryDate;
    }
    
    public String getPeriod() {
        return this.period;
    }	
  
    public void setPeriod(String period) {
        this.period = period;
    }
    
    public String getRltOrderPoNo() {
        return this.rltOrderPoNo;
    }	
  
    public void setRltOrderPoNo(String rltOrderPoNo) {
        this.rltOrderPoNo = rltOrderPoNo;
    }
    
    public String getState() {
        return this.state;
    }	
  
    public void setState(String state) {
        this.state = state;
    }
    
    public String getActiveState() {
        return this.activeState;
    }	
  
    public void setActiveState(String activeState) {
        this.activeState = activeState;
    }
    
    public String getRltRecPoNo() {
        return this.rltRecPoNo;
    }	
  
    public void setRltRecPoNo(String rltRecPoNo) {
        this.rltRecPoNo = rltRecPoNo;
    }

}
