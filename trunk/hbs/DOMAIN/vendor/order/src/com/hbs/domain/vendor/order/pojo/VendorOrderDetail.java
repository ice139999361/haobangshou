package com.hbs.domain.vendor.order.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * VendorOrderDetail对象.
 * @author hbs
 *
 */
public class VendorOrderDetail extends BaseDomain{
    
    /**
     * 唯一的seqid（序列号）.
     */
    private Integer operSeqId;
    
    /**
     * 供应商编码.
     */
    private String commCode;
    
    /**
     * 简称.
     */
    private String shortName;
    
    /**
     * 供应商订单类型
        0----客户采购单
        1---退货单
        2---常规备货采购单
        3--特定客户备货采购单
     */
    private String poNoType;
    
    /**
     * 订单编号.
     */
    private String poNo;
    
    /**
     * 物料编号.
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
     * 是否含税交易1--是0--否如果单价是含税的，则一定是1，否则可以选择是否含税交易.
     */
    private String isTax;
    
    /**
     * 税率.
     */
    private BigDecimal taxRate ;
    
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
    private Integer amount =0;
    
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
    private String activeState="ACTIVE";
    
    /**
     * 关联的入库单号，可能有多个，以.
     */
    private String rltRecPoNo;


    /**
     * 操作人员ID
     */
    private String staffId;
    
    /**
     * 操作人员姓名
     */
    private String staffName;
    
    /**
     * 结算方式
     */
    private String settlementType;
    
    
    /**
     * 针对0----客户采购单，3-- 特定客户备货有效
     */
    private String custCcode;
    
    /**
     * 追货提醒/天
     */
    private Integer hastenReminder;

    public String getCustCcode() {
		return custCcode;
	}

	public void setCustCcode(String custCcode) {
		this.custCcode = custCcode;
	}

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
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
    }
	
	/**
	 * @return the settlementType
	 */
	public String getSettlementType() {
		return settlementType;
	}

	/**
	 * @param settlementType the settlementType to set
	 */
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public Integer getOperSeqId() {
        return this.operSeqId;
    }	
  
    public void setOperSeqId(Integer operSeqId) {
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
    public String getStateDesc() {
    	String retStr ="未定义";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getState());
    	ceParam.setEncodeType("VENDOR_ORDER_DETAIL_STATE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
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

    
    public BigDecimal getCpriceTax() {
		return cpriceTax;
	}

	public void setCpriceTax(BigDecimal cpriceTax) {
		this.cpriceTax = cpriceTax;
	}

	public String getLogKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.commCode).append(";").append(this.poNo).append(this.operSeqId);
    	return sb.toString();
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("operSeqId=").append(this.operSeqId).append(" ");
		sb.append("commCode=").append(this.commCode).append(" ");
		sb.append("shortName=").append(this.shortName).append(" ");
		sb.append("poNoType=").append(this.poNoType).append(" ");
		sb.append("poNo=").append(this.poNo).append(" ");
		sb.append("cpartNo=").append(this.cpartNo).append(" ");
		sb.append("partNo=").append(this.partNo).append(" ");
		sb.append("pnDesc=").append(this.pnDesc).append(" ");
		sb.append("cprice=").append(this.cprice == null ? null : this.cprice.floatValue()).append(" ");
		sb.append("cpriceTax=").append(this.cpriceTax != null ? this.cpriceTax.floatValue() : " ").append(" ");
		sb.append("isTax=").append(this.isTax).append(" ");
		sb.append("taxRate=").append(this.taxRate == null ? null : this.taxRate.floatValue()).append(" ");
		sb.append("specDesc=").append(this.specDesc).append(" ");
		sb.append("commDesc=").append(this.commDesc).append(" ");
		sb.append("amount=").append(this.amount).append(" ");
		sb.append("money=").append(this.money == null ? null : this.money.floatValue()).append(" ");
		sb.append("deliveryAmount=").append(this.deliveryAmount).append(" ");
		sb.append("orgDeliveryDate=").append(this.orgDeliveryDate == null ? null : DateUtils.getFormatDate(this.orgDeliveryDate,null)).append(" ");
		sb.append("verDeliveryDate=").append(this.verDeliveryDate == null ? null : DateUtils.getFormatDate(this.verDeliveryDate,null)).append(" ");
		sb.append("period=").append(this.period).append(" ");
		sb.append("rltOrderPoNo=").append(this.rltOrderPoNo).append(" ");
		sb.append("state=").append(this.state).append(" ");
		sb.append("activeState=").append(this.activeState).append(" ");
		sb.append("rltRecPoNo=").append(this.rltRecPoNo).append(" ");
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("settlementType=").append(this.settlementType).append(" ");
		sb.append("custCcode=").append(this.custCcode).append(" ");
		sb.append("hastenReminder=").append(this.hastenReminder).append(" ");
		return sb.toString();
	}
    
    
	public Integer getHastenReminder() {
		return hastenReminder;
	}

	public void setHastenReminder(Integer hastenReminder) {
		this.hastenReminder = hastenReminder;
	}
    
}
