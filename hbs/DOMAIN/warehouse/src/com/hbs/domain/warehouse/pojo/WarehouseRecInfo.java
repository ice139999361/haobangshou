package com.hbs.domain.warehouse.pojo;

import java.util.Date;

/**
 * WarehouseRecInfo对象.
 * @author hbs
 *
 */
public class WarehouseRecInfo {
    
    /**
     * 供应商入库单号（供应商送货单号）.
     */
    private String recPoNo;
    
    /**
     * 供应商编码.
     */
    private String vendorCode;
    
    /**
     * 供应商简称.
     */
    private String shortName;
    
    /**
     * 供应商单据日期.
     */
    private Date poNoDate;
    
    /**
     * 货物到达日期.
     */
    private Date applyDate;
    
    /**
     * 仓库位置.
     */
    private String houseType;
    
    /**
     * 操作人ID.
     */
    private String operId;
    
    /**
     * 操作员.
     */
    private String operStaff;
    
    /**
     * 操作时间.
     */
    private String operTime;
    
    /**
     * 收货单备注.
     */
    private String receiveDesc;
    
    /**
     * 所属账期.
     */
    private String period;
    
    /**
     * 收货单（入库单）状态.
     */
    private Integer state;
    
    /**
     * 活动状态.
     */
    private Integer activeState;
    
    /**
     * 财务结算状态0----未对账1---已对账.
     */
    private String financeState;
    
    /**
     * 供应商订单类型0----入库单1---退货单.
     */
    private String poNoType;


    
    public String getRecPoNo() {
        return this.recPoNo;
    }	
  
    public void setRecPoNo(String recPoNo) {
        this.recPoNo = recPoNo;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    public String getShortName() {
        return this.shortName;
    }	
  
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public Date getPoNoDate() {
        return this.poNoDate;
    }	
  
    public void setPoNoDate(Date poNoDate) {
        this.poNoDate = poNoDate;
    }
    
    public Date getApplyDate() {
        return this.applyDate;
    }	
  
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    
    public String getHouseType() {
        return this.houseType;
    }	
  
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    
    public String getOperId() {
        return this.operId;
    }	
  
    public void setOperId(String operId) {
        this.operId = operId;
    }
    
    public String getOperStaff() {
        return this.operStaff;
    }	
  
    public void setOperStaff(String operStaff) {
        this.operStaff = operStaff;
    }
    
    public String getOperTime() {
        return this.operTime;
    }	
  
    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }
    
    public String getReceiveDesc() {
        return this.receiveDesc;
    }	
  
    public void setReceiveDesc(String receiveDesc) {
        this.receiveDesc = receiveDesc;
    }
    
    public String getPeriod() {
        return this.period;
    }	
  
    public void setPeriod(String period) {
        this.period = period;
    }
    
    public Integer getState() {
        return this.state;
    }	
  
    public void setState(Integer state) {
        this.state = state;
    }
    
    public Integer getActiveState() {
        return this.activeState;
    }	
  
    public void setActiveState(Integer activeState) {
        this.activeState = activeState;
    }
    
    public String getFinanceState() {
        return this.financeState;
    }	
  
    public void setFinanceState(String financeState) {
        this.financeState = financeState;
    }
    
    public String getPoNoType() {
        return this.poNoType;
    }	
  
    public void setPoNoType(String poNoType) {
        this.poNoType = poNoType;
    }

}
