package com.hbs.domain.warehouse.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WarehouseSendDetail对象.
 * @author hbs
 *
 */
public class WarehouseSendDetail {
    
    /**
     * 出库明细SEQID.
     */
    private Integer sendSeqId;
    
    /**
     * 出库单号.
     */
    private String sendPoNo;
    
    /**
     * 公司物料编号.
     */
    private String partNo;
    
    /**
     * 客户物料编号.
     */
    private String custPartNo;
    
    /**
     * 物料描述.
     */
    private String pnDesc;
    
    /**
     * 特殊备注，如物料批次等.
     */
    private String specDesc;
    
    /**
     * 是否含税交易1--是0--否 如果单价是含税的，则一定是1，否则可以选择是否含税交易.
     */
    private String isTax;
    
    /**
     * 物料单价.
     */
    private BigDecimal price;
    
    /**
     * 税率.
     */
    private BigDecimal taxRate;
    
    /**
     * 是否显示单价.
     */
    private String isShowPrice;
    
    /**
     * 对应的客户订单号.
     */
    private String rltPoNo;
    
    /**
     * 结算方式.
     */
    private String settlementType;
    
    /**
     * 备注.
     */
    private String commDesc;
    
    /**
	 * @return the commDesc
	 */
	public String getCommDesc() {
		return commDesc;
	}

	/**
	 * @param commDesc the commDesc to set
	 */
	public void setCommDesc(String commDesc) {
		this.commDesc = commDesc;
	}

	/**
     * 出货数量.
     */
    private Integer amount;
    
    /**
     * 本次出货金额.
     */
    private BigDecimal curMoney;
    
    /**
     * 供应商编码.
     */
    private String vendorCode;
    
    /**
     * 供应商的采购单号，可以多个，以.
     */
    private String vendorPoNo;
    
    /**
     * 所属账期.
     */
    private String period;
    
    /**
     * 发货日期.
     */
    private Date createTime;
    
    /**
     * 收货单（入库单）状态.
     */
    private String state;
    
    /**
     * 活动状态.
     */
    private String activeState;
    
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

	public Integer getSendSeqId() {
        return this.sendSeqId;
    }	
  
    public void setSendSeqId(Integer sendSeqId) {
        this.sendSeqId = sendSeqId;
    }
    
    public String getSendPoNo() {
        return this.sendPoNo;
    }	
  
    public void setSendPoNo(String sendPoNo) {
        this.sendPoNo = sendPoNo;
    }
    
    public String getPartNo() {
        return this.partNo;
    }	
  
    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }
    
    public String getCustPartNo() {
        return this.custPartNo;
    }	
  
    public void setCustPartNo(String custPartNo) {
        this.custPartNo = custPartNo;
    }
    
    public String getPnDesc() {
        return this.pnDesc;
    }	
  
    public void setPnDesc(String pnDesc) {
        this.pnDesc = pnDesc;
    }
    
  
    public String getIsTax() {
        return this.isTax;
    }	
  
    public void setIsTax(String isTax) {
        this.isTax = isTax;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }	
  
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getTaxRate() {
        return this.taxRate;
    }	
  
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    public String getIsShowPrice() {
        return this.isShowPrice;
    }	
  
    public void setIsShowPrice(String isShowPrice) {
        this.isShowPrice = isShowPrice;
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
    
    public String getSpecDesc() {
        return this.specDesc;
    }	
  
    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc;
    }
    
    public Integer getAmount() {
        return this.amount;
    }	
  
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public BigDecimal getCurMoney() {
        return this.curMoney;
    }	
  
    public void setCurMoney(BigDecimal curMoney) {
        this.curMoney = curMoney;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    public String getVendorPoNo() {
        return this.vendorPoNo;
    }	
  
    public void setVendorPoNo(String vendorPoNo) {
        this.vendorPoNo = vendorPoNo;
    }
    
    public String getPeriod() {
        return this.period;
    }	
  
    public void setPeriod(String period) {
        this.period = period;
    }
    
   
    
    /**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
