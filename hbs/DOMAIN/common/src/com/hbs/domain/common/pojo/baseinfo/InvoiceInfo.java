package com.hbs.domain.common.pojo.baseinfo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * InvoiceInfo����.
 * @author hbs
 *
 */
public class InvoiceInfo extends BaseDomain{
    
    /**
     * ���к�.
     */
    private Integer invoiceSeqId;
    
    /**
     * ������ID.
     */
    private String staffId;
    
    /**
     * ������.
     */
    private String staffName;
    
    /**
     * ����Ʊʱ��.
     */
    private Date createTime;
    
    /**
     * �ͻ�����.
     */
    private String poNo;
    
    /**
     * �ͻ�����.
     */
    private String poNoDate;
    
    /**
     * �ͻ�����.
     */
    private Date ccode;
    
    /**
     * ���.
     */
    private Date shortName;
    
    /**
     * ���ϱ��.
     */
    private String partNo;
    
    /**
     * �ͻ����ϱ��.
     */
    private String cpartNo;
    
    /**
     * ���ϵ�����.
     */
    private String pnDesc;
    
    /**
     * ��������.
     */
    private Integer amount;
    
    /**
     * �ܺ�˰���.
     */
    private BigDecimal allMoney;
    
    /**
     * ���ο�Ʊ���.
     */
    private BigDecimal curMoney;
    
    /**
     * δ��Ʊ���.
     */
    private BigDecimal leftMoney;
    
    /**
     * ��ע����Ҫ��д��Ʊ�ţ�Ҳ������д��������.
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
    
    public String getPoNoDate() {
        return this.poNoDate;
    }	
  
    public void setPoNoDate(String poNoDate) {
        this.poNoDate = poNoDate;
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

    
    public String getLogKey(){
    	StringBuilder sb = new StringBuilder("custInvoice");
    	sb.append(";").append(this.poNo).append(";");
    	sb.append(this.partNo);
    	return sb.toString();
    }
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("createTime=").append(this.createTime == null ? " " : DateUtils.getFormatDate(this.createTime,null)).append(" ");
		sb.append("poNo=").append(this.poNo).append(" ");
		sb.append("poNoDate=").append(this.poNoDate).append(" ");
		sb.append("ccode=").append(this.ccode).append(" ");
		sb.append("shortName=").append(this.shortName).append(" ");
		sb.append("partNo=").append(this.partNo).append(" ");
		sb.append("cpartNo=").append(this.cpartNo).append(" ");
		sb.append("pnDesc=").append(this.pnDesc).append(" ");
		sb.append("amount=").append(this.amount == null ? "0" :this.allMoney.intValue() ).append(" ");
		sb.append("allMoney=").append(this.allMoney == null ? "0" :this.allMoney.intValue() ).append(" ");		
		sb.append("curMoney=").append(this.curMoney == null ? "0" : this.curMoney.intValue()).append(" ");
		sb.append("leftMoney=").append(this.leftMoney== null ? "0" : this.leftMoney.intValue()).append(" ");
		sb.append("invoiceDesc=").append(this.invoiceDesc);
		return sb.toString();
	}

}
