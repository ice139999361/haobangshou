package com.hbs.domain.vendor.order.pojo;

import java.util.Date;

/**
 * VendorOrder对象.
 * @author hbs
 *
 */
public class VendorOrder {
    
    /**
     * 供应商编码.
     */
    private String commCode;
    
    /**
     * 供应商订单编号.
     */
    private String poNo;
    
    /**
     * 订单类型0----订单1---退货单.
     */
    private String poNoType;
    
    /**
     * 简称.
     */
    private String shortName;
    
    /**
     * 创建日期.
     */
    private Date createTime;
    
    /**
     * 联系人，对应联系人中的主联系人.
     */
    private String conName;
    
    /**
     * 电话，对应主联系人的电话.
     */
    private String conTel;
    
    /**
     * 传真，对应主联系人的传真.
     */
    private String conFax;
    
    /**
     * 对应信息中对应的分公司或分支机构.
     */
    private String companyBranch;
    
    /**
     * 结算方式，对应信息中的结算方式.
     */
    private String SettlementType;
    
    /**
     * 收货人，对应信息的收货人.
     */
    private String ReceiveName;
    
    /**
     * 收货地址，对应信息的收货地址.
     */
    private String receiveAddress;
    
    /**
     * 收货邮编，对应信息中的收货邮编.
     */
    private String receiveZip;
    
    /**
     * 录入人ID.
     */
    private String staffId;
    
    /**
     * 录入订单的人.
     */
    private String staffName;
    
    /**
     * 负责销售的销售人员，对应供应商的销售人员.
     */
    private String sales;
    
    /**
     * 客户订单是否显示单价（后台根据客户信息自动处理）.
     */
    private String isShowPrice;
    
    /**
     * 正式形成订单日期.
     */
    private Date orderTime;
    
    /**
     * 订单所属账期.
     */
    private String period;
    
    /**
     * 订单的业务状态.
     */
    private String state;
    
    /**
     * 订单的活动状态.
     */
    private String activeState;


    
    public String getCommCode() {
        return this.commCode;
    }	
  
    public void setCommCode(String commCode) {
        this.commCode = commCode;
    }
    
    public String getPoNo() {
        return this.poNo;
    }	
  
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }
    
    public String getPoNoType() {
        return this.poNoType;
    }	
  
    public void setPoNoType(String poNoType) {
        this.poNoType = poNoType;
    }
    
    public String getShortName() {
        return this.shortName;
    }	
  
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }	
  
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getConName() {
        return this.conName;
    }	
  
    public void setConName(String conName) {
        this.conName = conName;
    }
    
    public String getConTel() {
        return this.conTel;
    }	
  
    public void setConTel(String conTel) {
        this.conTel = conTel;
    }
    
    public String getConFax() {
        return this.conFax;
    }	
  
    public void setConFax(String conFax) {
        this.conFax = conFax;
    }
    
    public String getCompanyBranch() {
        return this.companyBranch;
    }	
  
    public void setCompanyBranch(String companyBranch) {
        this.companyBranch = companyBranch;
    }
    
    public String getSettlementType() {
        return this.SettlementType;
    }	
  
    public void setSettlementType(String SettlementType) {
        this.SettlementType = SettlementType;
    }
    
    public String getReceiveName() {
        return this.ReceiveName;
    }	
  
    public void setReceiveName(String ReceiveName) {
        this.ReceiveName = ReceiveName;
    }
    
    public String getReceiveAddress() {
        return this.receiveAddress;
    }	
  
    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }
    
    public String getReceiveZip() {
        return this.receiveZip;
    }	
  
    public void setReceiveZip(String receiveZip) {
        this.receiveZip = receiveZip;
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
    
    public String getSales() {
        return this.sales;
    }	
  
    public void setSales(String sales) {
        this.sales = sales;
    }
    
    public String getIsShowPrice() {
        return this.isShowPrice;
    }	
  
    public void setIsShowPrice(String isShowPrice) {
        this.isShowPrice = isShowPrice;
    }
    
    public Date getOrderTime() {
        return this.orderTime;
    }	
  
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    
    public String getPeriod() {
        return this.period;
    }	
  
    public void setPeriod(String period) {
        this.period = period;
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

}
