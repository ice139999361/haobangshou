package com.hbs.domain.adjust.pojo;

import java.util.Date;

import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * AdjustInfo对象.
 * @author hbs
 *
 */
public class AdjustInfo extends BaseDomain{
    
    /**
     * 申请序列号.
     */
    private Integer applySeqId;
    
    /**
     * 申请人ID.
     */
    private String staffId;
    
    /**
     * 申请人.
     */
    private String staffName;
    
    /**
     * 申请时间.
     */
    private Date applyDate;
    
    /**
     * 物料的订单号（供应商的采购单）.
     */
    @Deprecated
    private String poNo;
    
    /**
     * 申请的公司调货PART_NO.
     */
    private String partNo;
    
    /**
     * 调货物料的描述.
     */
    private String pnDesc;
    
    /**
     * 仓库位置.
     */
    private String houseType;
    
    /**
     * 申请的调货数量.
     */
    private Integer applyAmount;
    
    /**
     * 物料的供应商编码.
     */
    private String vendorCode;
    
    /**
     * 供应商简称.
     */
    private String shortName;
    
    /**
     * 申请说明.
     */
    private String applyContent;
    
    /**
     * 从那个客户编码调货.
     */
    private String fromCustCode;
    
    /**
     * 从客户简称.
     */
    private String fromCustName;
    
    /**
     * 调动那个客户code下.
     */
    private String toCustCode;
    
    /**
     * 到客户简称.
     */
    private String toCustName;
    
    /**
     * 审批人ID.
     */
    private String auditStaffId;
    
    /**
     * 审批人姓名.
     */
    private String auditStaffName;
    
    /**
     * 审批时间.
     */
    private Date auditDate;
    
    /**
     * 审批是否同意0---同意1---不同意.
     */
    private String auditAgree;
    
    /**
     * 审批的具体意见.
     */
    private String auditContent;


    
    public Integer getApplySeqId() {
        return this.applySeqId;
    }	
  
    public void setApplySeqId(Integer applySeqId) {
        this.applySeqId = applySeqId;
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
    
    public Date getApplyDate() {
        return this.applyDate;
    }	
  
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    @Deprecated
    public String getPoNo() {
        return this.poNo;
    }	
    @Deprecated
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
    
    public String getHouseType() {
        return this.houseType;
    }	
  
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    
    public Integer getApplyAmount() {
        return this.applyAmount;
    }	
  
    public void setApplyAmount(Integer applyAmount) {
        this.applyAmount = applyAmount;
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
    
    public String getApplyContent() {
        return this.applyContent;
    }	
  
    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent;
    }
    
    public String getFromCustCode() {
        return this.fromCustCode;
    }	
  
    public void setFromCustCode(String fromCustCode) {
        this.fromCustCode = fromCustCode;
    }
    
    public String getFromCustName() {
        return this.fromCustName;
    }	
  
    public void setFromCustName(String fromCustName) {
        this.fromCustName = fromCustName;
    }
    
    public String getToCustCode() {
        return this.toCustCode;
    }	
  
    public void setToCustCode(String toCustCode) {
        this.toCustCode = toCustCode;
    }
    
    public String getToCustName() {
        return this.toCustName;
    }	
  
    public void setToCustName(String toCustName) {
        this.toCustName = toCustName;
    }
    
    public String getAuditStaffId() {
        return this.auditStaffId;
    }	
  
    public void setAuditStaffId(String auditStaffId) {
        this.auditStaffId = auditStaffId;
    }
    
    public String getAuditStaffName() {
        return this.auditStaffName;
    }	
  
    public void setAuditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
    }
    
    public Date getAuditDate() {
        return this.auditDate;
    }	
  
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }
    
    public String getAuditAgree() {
        return this.auditAgree;
    }	
  
    public void setAuditAgree(String auditAgree) {
        this.auditAgree = auditAgree;
    }
    
    public String getAuditContent() {
        return this.auditContent;
    }	
  
    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }

    
    public String getLogBizKey(){
    	return this.applySeqId.toString() +";adjustment";
    }
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("applySeqId=").append(this.applySeqId == null ? null : this.applySeqId.intValue()).append(" ");
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("applyDate=").append(this.applyDate == null ? null : DateUtils.getFormatDate(this.applyDate,null)).append(" ");
		sb.append("poNo=").append(this.poNo).append(" ");
		sb.append("partNo=").append(this.partNo).append(" ");
		sb.append("pnDesc=").append(this.pnDesc).append(" ");
		sb.append("houseType=").append(this.houseType).append(" ");
		sb.append("applyAmount=").append(this.applyAmount).append(" ");
		sb.append("vendorCode=").append(this.vendorCode).append(" ");
		sb.append("shortName=").append(this.shortName).append(" ");
		sb.append("applyContent=").append(this.applyContent).append(" ");
		sb.append("fromCustCode=").append(this.fromCustCode).append(" ");
		sb.append("fromCustName=").append(this.fromCustName).append(" ");
		sb.append("toCustCode=").append(this.toCustCode).append(" ");
		sb.append("toCustName=").append(this.toCustName).append(" ");
		sb.append("auditStaffId=").append(this.auditStaffId).append(" ");
		sb.append("auditStaffName=").append(this.auditStaffName).append(" ");
		sb.append("auditDate=").append(this.auditDate == null ? null : DateUtils.getFormatDate(this.auditDate,null) ).append(" ");
		sb.append("auditAgree=").append(this.auditAgree).append(" ");
		sb.append("auditContent=").append(this.auditContent).append(" ");
		
		return sb.toString();
	}

}
