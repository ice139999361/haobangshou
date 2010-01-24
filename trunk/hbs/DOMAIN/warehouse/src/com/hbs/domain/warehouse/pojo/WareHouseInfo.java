package com.hbs.domain.warehouse.pojo;


/**
 * WareHouseInfo对象.
 * @author hbs
 *
 */
public class WareHouseInfo {
    
    /**
     * 序列号.
     */
    private Integer houseSeqId;
    
    /**
     * 仓库类型，缺省为公司总库D.
     */
    private String houseType;
    
    /**
     * 仓库货物用途 常规货物 特定客户货物.
     */
    private String houseUse;
    
    /**
     * 供应商编码.
     */
    private String vendorCode;
    
    /**
     * 对应的采购单号.
     */
    private String poNo;
    
    /**
     * 本公司的物料编号.
     */
    private String partNo;
    
    /**
     * 物料描述.
     */
    private String pnDesc;
    
    /**
     * 供应商物料编号.
     */
    private String cpartNo;
    
    /**
     * 当HOUSE_USE为特定客户备货时必须填写CUST_CODE.
     */
    private String custCode;
    
    /**
     * 总库存数量，入库和出库需要做相应的加减.
     */
    private Integer totalAmount;
    
    /**
     * 锁定的库存.
     */
    private Integer lockAmount;
    
    /**
     * 可用库存，可用库存+锁定库存=总库存.
     */
    private Integer useAmount;
    
    /**
     * 最大库存数，0表示无限制.
     */
    private Integer maxAmount;
    
    /**
     * 最小库存数，缺省为0.
     */
    private Integer minAmount;
    
    /**
     * 库存状态0---有可用库存 1---无可用库存.
     */
    private String state;


    
    public Integer getHouseSeqId() {
        return this.houseSeqId;
    }	
  
    public void setHouseSeqId(Integer houseSeqId) {
        this.houseSeqId = houseSeqId;
    }
    
    public String getHouseType() {
        return this.houseType;
    }	
  
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    
    public String getHouseUse() {
        return this.houseUse;
    }	
  
    public void setHouseUse(String houseUse) {
        this.houseUse = houseUse;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    public String getPoNo() {
        return this.poNo;
    }	
  
    public void setPoNo(String poNo) {
        this.poNo = poNo;
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
    
    public String getCpartNo() {
        return this.cpartNo;
    }	
  
    public void setCpartNo(String cpartNo) {
        this.cpartNo = cpartNo;
    }
    
    public String getCustCode() {
        return this.custCode;
    }	
  
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }
    
    public Integer getTotalAmount() {
        return this.totalAmount;
    }	
  
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Integer getLockAmount() {
        return this.lockAmount;
    }	
  
    public void setLockAmount(Integer lockAmount) {
        this.lockAmount = lockAmount;
    }
    
    public Integer getUseAmount() {
        return this.useAmount;
    }	
  
    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }
    
    public Integer getMaxAmount() {
        return this.maxAmount;
    }	
  
    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }
    
    public Integer getMinAmount() {
        return this.minAmount;
    }	
  
    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }
    
    public String getState() {
        return this.state;
    }	
  
    public void setState(String state) {
        this.state = state;
    }

}
