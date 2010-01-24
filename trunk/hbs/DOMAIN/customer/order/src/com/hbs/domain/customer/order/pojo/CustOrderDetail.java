package com.hbs.domain.customer.order.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * CustOrderDetail对象.
 * @author hbs
 *
 */
public class CustOrderDetail extends BaseDomain{
    
    /**
     * 唯一的seqid（序列号）.
     */
    private String operSeqId;
    
    /**
     * 客户编码.
     */
    private String commCode;
    
    /**
     * 客户简称.
     */
    private String shortName;
    
    /**
     * 客户订单类型0-客户订单1-客户退货单.
     */
    private String poNoType;
    
    /**
     * 结算方式，对应客户信息中的结算方式.
     */
    private String SettlementType;
    
    /**
     * 客户订单编号.
     */
    private String poNo;
    
    /**
     * 客户物料编号.
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
     * 单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税
     */
    private BigDecimal cpriceTax;
    
    /**
     * 是否含税1--是0--否如果单价是含税的，则一定是1，否则可以选择是否含税交易.
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
     * 已经发货数量.
     */
    private Integer deliveryAmount=0;
    
    /**
     * 仓库锁定数量.
     */
    private Integer lockAmount=0;
    
    /**
     * 本客户锁定数量.
     */
    private Integer selfLockAmount=0;
    
    /**
     * 通用库存锁定数量.
     */
    private Integer commLockAmount=0;
    
    /**
     * 订单原始交货日期.
     */
    private Date orgDeliveryDate;
    
    /**
     * 业务部期望订单的交货日期.
     */
    private Date preDeliveryDate;
    
    /**
     * 采购部最终确认交货日期.
     */
    private Date verDeliveryDate;
    
    /**
     * 订单明细所属账期.
     */
    private String period;
    
    /**
     * 关联的采购单号，多个采购单号以.
     */
    private String rltOrderPoNo;
    
    /**
     * 供应商编码.
     */
    private String vendorCode;
    
    /**
     * 订单明细状态.
     */
    private String state;
    
    
    /**
     * 录入人ID(业务部助理).
     */
    private String staffId;
    
    /**
     * 录入订单的人(业务部助理).
     */
    private String staffName;
    
    /**
     * 销售人员ID.
     */
    private String salesId;
    
    /**
     * 负责客户的销售人员，对应客户信息的销售人员.
     */
    private String sales;
    
    /**
     * 订单的活动状态.
     */
    private String activeState="ACTIVE";
    
    /**
     * 关联的出货单号，可以多个，以.
     */
    private String rltSendPoNo;


    
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
    
    /**
	 * @return the cpriceTax
	 */
	public BigDecimal getCpriceTax() {
		return cpriceTax;
	}

	/**
	 * @param cpriceTax the cpriceTax to set
	 */
	public void setCpriceTax(BigDecimal cpriceTax) {
		this.cpriceTax = cpriceTax;
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
    
    public Integer getLockAmount() {
        return this.lockAmount;
    }	
  
    public void setLockAmount(Integer lockAmount) {
        this.lockAmount = lockAmount;
    }
    
    public Integer getSelfLockAmount() {
        return this.selfLockAmount;
    }	
  
    public void setSelfLockAmount(Integer selfLockAmount) {
        this.selfLockAmount = selfLockAmount;
    }
    
    public Integer getCommLockAmount() {
        return this.commLockAmount;
    }	
  
    public void setCommLockAmount(Integer commLockAmount) {
        this.commLockAmount = commLockAmount;
    }
    
    public Date getOrgDeliveryDate() {
        return this.orgDeliveryDate;
    }	
  
    public void setOrgDeliveryDate(Date orgDeliveryDate) {
        this.orgDeliveryDate = orgDeliveryDate;
    }
    
    public Date getPreDeliveryDate() {
        return this.preDeliveryDate;
    }	
  
    public void setPreDeliveryDate(Date preDeliveryDate) {
        this.preDeliveryDate = preDeliveryDate;
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
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
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
    
    public String getRltSendPoNo() {
        return this.rltSendPoNo;
    }	
  
    public void setRltSendPoNo(String rltSendPoNo) {
        this.rltSendPoNo = rltSendPoNo;
    }
    
    
    public String getSettlementType() {
		return SettlementType;
	}

	public void setSettlementType(String settlementType) {
		SettlementType = settlementType;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getBizKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.commCode).append(";");
    	sb.append(this.poNo).append(";");
    	sb.append(this.cpartNo).append(";");
    	if(this.specDesc != null){
    		sb.append(this.specDesc).append(";");
    	}
    	sb.append(this.operSeqId);
    	return sb.toString();
    }
	
	public String getLogBizKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.commCode).append(";");
    	sb.append(this.poNo);
    	
    	return sb.toString();
    }
	
    public String getWaitTaskBizKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.commCode).append("的订单号");
    	sb.append(this.poNo);
    	
    	return sb.toString();
    }
}
