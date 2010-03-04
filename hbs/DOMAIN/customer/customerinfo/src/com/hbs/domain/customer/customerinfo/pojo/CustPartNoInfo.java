package com.hbs.domain.customer.customerinfo.pojo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * CustPartNoInfo对象.
 * @author hbs
 *
 */
public class CustPartNoInfo extends BaseDomain{
	
	private Integer seqId;
    
    /**
     * 物料编号.
     */
    private String custPartNo;
    
    /**
     * 客户编码.
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
    private Integer minAmount;
    
    /**
     * 样品编号.
     */
    private String sampleCode;
    
    /**
     * 供应商编码.
     */
    private String vendorCode;

    /**
     * 价格是否变动过  0--没有  1--是
     */
    private String isPriceChange ="0";
    
    /**
	 * @return the seqId
	 */
	public Integer getSeqId() {
		return seqId;
	}

	/**
	 * @param seqId the seqId to set
	 */
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
    
    public String getSampleCode() {
        return this.sampleCode;
    }	
  
    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

	public String getIsPriceChange() {
		return isPriceChange;
	}

	public void setIsPriceChange(String isPriceChange) {
		this.isPriceChange = isPriceChange;
	}

	/**
	 * 根据state返回其中文说明
	 * @return
	 */
	public String getStateDesc() {
		try {
			
			ConfigEncode ce = new ConfigEncode();
			ce.setEncodeType("CUSTOMER_INFO_STATE");
			ce.setEncodeKey(getState());
			ce = ConfigEncodeMgr.getConfigEncode(ce);
			if(ce == null)
				return null;
			else
				return ce.getEncodeValue();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CustPartNoInfo.class);
			logger.error("catch Exception in getStateDesc state=" + getState(), e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(seqId).append(";");
		sb.append(custPartNo).append(";");
		sb.append(commCode).append(";");
		sb.append(state).append(";");
		sb.append(pnDesc).append(";");
		sb.append(price).append(";");
		sb.append(priceTax).append(";");
		sb.append(createDate).append(";");
		sb.append(staffId).append(";");
		sb.append(staffName).append(";");
		sb.append(partNo).append(";");
		sb.append(catCode).append(";");
		sb.append(clsName).append(";");
		sb.append(minAmount).append(";");
		sb.append(sampleCode).append(";");
		sb.append(vendorCode);
		return super.toString();
	}
	
	public String getLogContent(){
		StringBuilder sb = new StringBuilder();
		sb.append("公司物料编码:").append(partNo).append("  ");
		sb.append("客户物料编码:").append(custPartNo).append("  ");
		sb.append("客户编码:").append(commCode).append("  ");
		sb.append("单价:").append(price.floatValue()).append("  ");
		sb.append("含税单价:").append(priceTax.floatValue()).append("  ");
		return sb.toString();
	}
	public String getLogBizKey(){
		StringBuilder sb = new StringBuilder();		
		sb.append(partNo).append(";");
		sb.append(custPartNo).append(";");
		sb.append(commCode);		
		return sb.toString();
	}
	
	public String getWaitTaskBizKey(){
		StringBuilder sb = new StringBuilder();		
		sb.append(partNo).append(";").append(this.custPartNo).append(";");
		sb.append(commCode);		
		return sb.toString();
	}
}
