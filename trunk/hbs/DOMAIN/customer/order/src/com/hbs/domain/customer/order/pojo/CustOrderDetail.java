package com.hbs.domain.customer.order.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.ConfigEncode;
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
    private String settlementType;
    
    /**
     * 客户订单编号.
     */
    private String poNo;
    
    /**
     * 客户物料编号.
     */
    private String cpartNo;
    
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
    private Integer deliveryAmount;
    
    /**
     * 本客户库存发货数量
     */
    private Integer selfDeliveryAmount;
    /**
     * 通用库存发货数量
     */
    private Integer commDeliveryAmount;
    /**
     * 发货对应的仓库总库/其他库，缺省为公司总库1
     */
    private String deliveryHouseType ="1";
    
    /**
     * 仓库锁定数量.
     */
    private Integer lockAmount;
    
    /**
     * 本客户锁定数量.
     */
    private Integer selfLockAmount;
    
    /**
     * 通用库存锁定数量.
     */
    private Integer commLockAmount;
    
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

    /**
     * 合同费
     */
    private BigDecimal contactFee;
    
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
    
    public String getCpartNo() {
        return this.cpartNo;
    }	
  
    public void setCpartNo(String cpartNo) {
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
    
    public Integer getSelfDeliveryAmount() {
		return selfDeliveryAmount;
	}

	public void setSelfDeliveryAmount(Integer selfDeliveryAmount) {
		this.selfDeliveryAmount = selfDeliveryAmount;
	}

	public Integer getCommDeliveryAmount() {
		return commDeliveryAmount;
	}

	public void setCommDeliveryAmount(Integer commDeliveryAmount) {
		this.commDeliveryAmount = commDeliveryAmount;
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
    
    /**
     * 获取结算方式描述
     * @return
     */
    public String getSettlementTypeDesc(){
    	String retStr ="未定义";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getSettlementType());
    	ceParam.setEncodeType("SETTLEMENT_TYPE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeDesc();
    	}
    	return retStr;
    }
    
    public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
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

	public String getDeliveryHouseType() {
		return deliveryHouseType;
	}

	public void setDeliveryHouseType(String deliveryHouseType) {
		this.deliveryHouseType = deliveryHouseType;
	}

	public BigDecimal getContactFee() {
		return contactFee;
	}

	public void setContactFee(BigDecimal contactFee) {
		this.contactFee = contactFee;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("operSeqId=").append(this.operSeqId).append(" ");
		sb.append("commCode=").append(this.commCode).append(" ");
		sb.append("shortName=").append(this.shortName).append(" ");
		sb.append("poNoType=").append(this.poNoType).append(" ");
		sb.append("settlementType=").append(this.settlementType).append(" ");
		sb.append("poNo=").append(this.poNo).append(" ");
		sb.append("cpartNo=").append(this.cpartNo).append(" ");
		sb.append("partNo=").append(this.partNo).append(" ");
		sb.append("pnDesc=").append(this.pnDesc).append(" ");
		
		sb.append("cprice=").append(this.cprice != null ? this.cprice.floatValue() : " ").append(" ");
		
		sb.append("cpriceTax=").append(this.cpriceTax != null ? this.cpriceTax.floatValue() : " ").append(" ");
		sb.append("isTax=").append(this.isTax).append(" ");
		sb.append("taxRate=").append(this.taxRate != null ? this.taxRate.floatValue() : "  ").append(" ");
		sb.append("specDesc=").append(this.specDesc).append(" ");
		sb.append("commDesc=").append(this.commDesc).append(" ");
		sb.append("amount=").append(this.amount).append(" ");
		sb.append("money=").append(this.money != null ? this.money.floatValue() : "  ").append(" ");
		sb.append("selfDeliveryAmount=").append(this.selfDeliveryAmount).append(" ");
		sb.append("commDeliveryAmount=").append(this.commDeliveryAmount).append(" ");
		sb.append("deliveryAmount=").append(this.deliveryAmount).append(" ");
		sb.append("deliveryHouseType=").append(this.deliveryHouseType).append(" ");
		sb.append("lockAmount=").append(this.lockAmount).append(" ");
		sb.append("selfLockAmount=").append(this.selfLockAmount).append(" ");
		sb.append("commLockAmount=").append(this.commLockAmount).append(" ");
		sb.append("orgDeliveryDate=").append(this.orgDeliveryDate != null ? DateUtils.getFormatDate(this.orgDeliveryDate,null) : null).append(" ");
		sb.append("preDeliveryDate=").append(this.preDeliveryDate != null ? DateUtils.getFormatDate(this.preDeliveryDate,null) : null).append(" ");
		sb.append("verDeliveryDate=").append(this.verDeliveryDate != null ? DateUtils.getFormatDate(this.verDeliveryDate,null) : null).append(" ");
		sb.append("period=").append(this.period).append(" ");
		sb.append("rltOrderPoNo=").append(this.rltOrderPoNo).append(" ");
		sb.append("vendorCode=").append(this.vendorCode).append(" ");
		sb.append("state=").append(this.state).append(" ");
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("salesId=").append(this.salesId).append(" ");
		sb.append("sales=").append(this.sales).append(" ");
		sb.append("activeState=").append(this.activeState).append(" ");
		sb.append("rltSendPoNo=").append(this.rltSendPoNo).append(" ");
		sb.append("contactFee=").append(this.contactFee != null ? this.contactFee.floatValue() : "  ").append(" ");
		
		return sb.toString();
	}
}
