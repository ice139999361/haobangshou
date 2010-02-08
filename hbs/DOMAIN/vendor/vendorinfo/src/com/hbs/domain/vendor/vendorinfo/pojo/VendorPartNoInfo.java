package com.hbs.domain.vendor.vendorinfo.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * VendorPartNoInfo对象.
 * @author hbs
 *
 */

public class VendorPartNoInfo extends BaseDomain {
    
	private Integer seqId;
	
    /**
     * 供应商物料编号.
     */
    private String custPartNo;
    
    /**
     * 供应商编码.
     */
    private String commCode;
    
    /**
     * 状态0----正式数据1---临时数据（没有提交审批）2---待审批数据3---审批不通过4---废弃数据.
     */
    private String state;
    
    /**
     * 物料描述.
     */
    private String pnDesc;
    
    /**
     * 单价.
     */
    private BigDecimal price;
    
    /**
     * 单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税.
     */
    private BigDecimal priceTax;
    
    /**
     * 创建时间.
     */
    private Date createDate;
    
    /**
     * 创建人.
     */
    private String staffId;
    
    /**
     * 创建人.
     */
    private String staffName;
    
    /**
     * 本公司的物料编号.
     */
    private String partNo;
    
    /**
     * 本公司的大类.
     */
    private String catCode;
    
    /**
     * 本公司的小类.
     */
    private String clsName;
    
    /**
     * 最小订单数量，缺省为0.
     */
    private Integer minAmount=0;
    
    /**
     * 最小包装单位.
     */
    private Integer minPackage =0;
    
    /**
     * 样品编号.
     */
    private String sampleCode;


    
    public Integer getSeqId() {
		return seqId;
	}

	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}

	public String getCustPartNo() {
        return this.custPartNo;
    }	
  
    public void setCustPartNo(String custPartNo) {
        this.custPartNo = custPartNo;
    }
    
    public String getCommCode() {
        return this.commCode;
    }	
  
    public void setCommCode(String commCode) {
        this.commCode = commCode;
    }
    
    public String getState() {
        return this.state;
    }	
  
    public void setState(String state) {
        this.state = state;
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
    
    public BigDecimal getPriceTax() {
        return this.priceTax;
    }	
  
    public void setPriceTax(BigDecimal priceTax) {
        this.priceTax = priceTax;
    }
    
    public Date getCreateDate() {
        return this.createDate;
    }	
  
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    
    public String getPartNo() {
        return this.partNo;
    }	
  
    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }
    
    public String getCatCode() {
        return this.catCode;
    }	
  
    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }
    
    public String getClsName() {
        return this.clsName;
    }	
  
    public void setClsName(String clsName) {
        this.clsName = clsName;
    }
    
    public Integer getMinAmount() {
        return this.minAmount;
    }	
  
    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }
    
    public Integer getMinPackage() {
        return this.minPackage;
    }	
  
    public void setMinPackage(Integer minPackage) {
        this.minPackage = minPackage;
    }
    
    public String getSampleCode() {
        return this.sampleCode;
    }	
  
    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    
    public String getLogBizKey(){
		StringBuilder sb = new StringBuilder();		
		sb.append(partNo).append(";");
		sb.append(custPartNo).append(";");
		sb.append(commCode);
		return sb.toString();
	}
	
    public String getLogContent(){
		StringBuilder sb = new StringBuilder();
		sb.append("公司物料编码:").append(partNo).append("  ");
		sb.append("供应商物料编码:").append(custPartNo).append("  ");
		sb.append("供应商编码:").append(commCode).append("  ");
		sb.append("单价:").append(price.floatValue()).append("  ");
		sb.append("含税单价:").append(priceTax.floatValue()).append("  ");
		return sb.toString();
	}
    
	public String getWaitTaskBizKey(){
		StringBuilder sb = new StringBuilder();		
		sb.append(partNo).append(";").append(this.custPartNo).append(";");
		sb.append(commCode);		
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("seqId=").append(this.seqId).append(" ");
		sb.append("custPartNo=").append(this.custPartNo).append(" ");
		sb.append("commCode=").append(this.commCode).append(" ");
		sb.append("state=").append(this.state).append(" ");
		sb.append("pnDesc=").append(this.pnDesc).append(" ");
		sb.append("price=").append(this.price == null ? null : this.price.floatValue()).append(" ");
		sb.append("priceTax=").append(this.priceTax == null ? null : this.priceTax.floatValue()).append(" ");
		sb.append("createDate=").append(this.createDate == null ? null : DateUtils.getFormatDate(this.createDate,null)).append(" ");
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("partNo=").append(this.partNo).append(" ");
		sb.append("catCode=").append(this.catCode).append(" ");
		sb.append("clsName=").append(this.clsName).append(" ");
		sb.append("minAmount=").append(this.minAmount).append(" ");
		sb.append("minPackage=").append(this.minPackage).append(" ");
		sb.append("sampleCode=").append(this.sampleCode).append(" ");
		
		return sb.toString();
	}
	
	
}
