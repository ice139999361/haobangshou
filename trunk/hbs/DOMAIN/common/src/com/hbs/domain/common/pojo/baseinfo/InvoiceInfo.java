package com.hbs.domain.common.pojo.baseinfo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * InvoiceInfo对象.
 * @author hbs
 *
 */
public class InvoiceInfo extends BaseDomain{
    
    /**
     * 序列号.
     */
    private Integer invoiceSeqId;
    
    /**
     * 申请人ID.
     */
    private String staffId;
    
    /**
     * 申请人.
     */
    private String staffName;
    
    /**
     * 开发票时间.
     */
    private Date createTime;
    
    /**
     * 送货单号.
     */
    private String poNo;
    
    /**
     * 送货日期.
     */
    private String PO_NO_DATE;
    
    /**
     * 客户编码.
     */
    private Date ccode;
    
    /**
     * 简称.
     */
    private Date shortName;
    
    /**
     * 物料编号.
     */
    private String partNo;
    
    /**
     * 客户物料编号.
     */
    private String cpartNo;
    
    /**
     * 物料的描述.
     */
    private String pnDesc;
    
    /**
     * 物料数量.
     */
    private Integer amount;
    
    /**
     * 总含税金额.
     */
    private BigDecimal allMoney;
    
    /**
     * 本次开票金额.
     */
    private BigDecimal curMoney;
    
    /**
     * 未开票金额.
     */
    private BigDecimal leftMoney;
    
    /**
     * 备注，主要填写发票号，也可以填写其他内容.
     */
    private String invoiceDesc;


    
    public Integer getInvoiceSeqId() {
        return this.invoiceSeqId;
    }	
  
    public void setInvoiceSeqId(Integer invoiceSeqId) {
        this.invoiceSeqId = invoiceSeqId;
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
    
    public Date getCreateTime() {
        return this.createTime;
    }	
  
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getPoNo() {
        return this.poNo;
    }	
  
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }
    
    public String getPO_NO_DATE() {
        return this.PO_NO_DATE;
    }	
  
    public void setPO_NO_DATE(String PO_NO_DATE) {
        this.PO_NO_DATE = PO_NO_DATE;
    }
    
    public Date getCcode() {
        return this.ccode;
    }	
  
    public void setCcode(Date ccode) {
        this.ccode = ccode;
    }
    
    public Date getShortName() {
        return this.shortName;
    }	
  
    public void setShortName(Date shortName) {
        this.shortName = shortName;
    }
    
    public String getPartNo() {
        return this.partNo;
    }	
  
    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }
    
    public String getCpartNo() {
        return this.cpartNo;
    }	
  
    public void setCpartNo(String cpartNo) {
        this.cpartNo = cpartNo;
    }
    
    public String getPnDesc() {
        return this.pnDesc;
    }	
  
    public void setPnDesc(String pnDesc) {
        this.pnDesc = pnDesc;
    }
    
    public Integer getAmount() {
        return this.amount;
    }	
  
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public BigDecimal getAllMoney() {
        return this.allMoney;
    }	
  
    public void setAllMoney(BigDecimal allMoney) {
        this.allMoney = allMoney;
    }
    
    public BigDecimal getCurMoney() {
        return this.curMoney;
    }	
  
    public void setCurMoney(BigDecimal curMoney) {
        this.curMoney = curMoney;
    }
    
    public BigDecimal getLeftMoney() {
        return this.leftMoney;
    }	
  
    public void setLeftMoney(BigDecimal leftMoney) {
        this.leftMoney = leftMoney;
    }
    
    public String getInvoiceDesc() {
        return this.invoiceDesc;
    }	
  
    public void setInvoiceDesc(String invoiceDesc) {
        this.invoiceDesc = invoiceDesc;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(staffId).append(";");
		sb.append(staffName).append(";");
		sb.append(createTime).append(";");
		sb.append(poNo).append(";");
		sb.append(PO_NO_DATE).append(";");
		sb.append(ccode).append(";");
		sb.append(shortName).append(";");
		sb.append(partNo).append(";");
		sb.append(cpartNo).append(";");
		sb.append(pnDesc).append(";");
		sb.append(amount).append(";");
		sb.append(allMoney).append(";");
		sb.append(curMoney).append(";");
		sb.append(leftMoney).append(";");
		sb.append(invoiceDesc);
		return sb.toString();
	}

}
