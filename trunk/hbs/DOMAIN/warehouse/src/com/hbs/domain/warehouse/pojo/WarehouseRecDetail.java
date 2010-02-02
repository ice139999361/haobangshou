package com.hbs.domain.warehouse.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WarehouseRecDetail对象.
 * @author hbs
 *
 */
public class WarehouseRecDetail {
    
    /**
     * 入库明细SEQID.
     */
    private Integer recDetailSeqId;
    
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
     * 送货单对应的采购单号.
     */
    private String rltPoNo;
    
    /**
     * 结算方式.
     */
    private String settlementType;
    
    /**
     * 公司的物料编号.
     */
    private String partNo;
    
    /**
     * 供应商的物料编号.
     */
    private String venPartNo;
    
    /**
     * 物料描述.
     */
    private String pnDesc;
    
    /**
     * 采购的单价.
     */
    private BigDecimal price;
    
    /**
     * 是否含税1--是0--否 如果单价是含税的，则一定是1，否则可以选择是否含税交易.
     */
    private String isTax;
    
    /**
     * 税率.
     */
    private BigDecimal taxRate;
    
    /**
     * 本次送货数量.
     */
    private Integer amount;
    
    /**
     * 所属账期.
     */
    private String period;
    
    /**
     * 财务结算状态0----未对账1---已对账.
     */
    private String financeState;
    
    /**
     * 供应商订单类型0---入库单1---退货单.
     */
    private String poNoType;

    /**
     * 操作人ID
     */
    private String staffId;
    /**
     * 操作人姓名
     */
    private String staffName;
    /**
     * 操作时间
     */
    private Date operTime;
    

    
   

	/**
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the operTime
	 */
	public Date getOperTime() {
		return operTime;
	}

	/**
	 * @param operTime the operTime to set
	 */
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public Integer getRecDetailSeqId() {
        return this.recDetailSeqId;
    }	
  
    public void setRecDetailSeqId(Integer recDetailSeqId) {
        this.recDetailSeqId = recDetailSeqId;
    }
    
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
    
    public String getRltPoNo() {
        return this.rltPoNo;
    }	
  
    public void setRltPoNo(String rltPoNo) {
        this.rltPoNo = rltPoNo;
    }
    
    public String getSettlementType() {
        return this.settlementType;
    }	
  
    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }
    
    public String getPartNo() {
        return this.partNo;
    }	
  
    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }
    
    public String getVenPartNo() {
        return this.venPartNo;
    }	
  
    public void setVenPartNo(String venPartNo) {
        this.venPartNo = venPartNo;
    }
    
    public String getPnDesc() {
        return this.pnDesc;
    }	
  
    public void setPnDesc(String pnDesc) {
        this.pnDesc = pnDesc;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }	
  
    public void setPrice(BigDecimal price) {
        this.price = price;
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
    
    public Integer getAmount() {
        return this.amount;
    }	
  
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public String getPeriod() {
        return this.period;
    }	
  
    public void setPeriod(String period) {
        this.period = period;
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
