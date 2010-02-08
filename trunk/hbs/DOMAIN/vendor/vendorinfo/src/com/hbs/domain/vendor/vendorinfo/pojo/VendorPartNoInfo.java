package com.hbs.domain.vendor.vendorinfo.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * VendorPartNoInfo����.
 * @author hbs
 *
 */

public class VendorPartNoInfo extends BaseDomain {
    
	private Integer seqId;
	
    /**
     * ��Ӧ�����ϱ��.
     */
    private String custPartNo;
    
    /**
     * ��Ӧ�̱���.
     */
    private String commCode;
    
    /**
     * ״̬0----��ʽ����1---��ʱ���ݣ�û���ύ������2---����������3---������ͨ��4---��������.
     */
    private String state;
    
    /**
     * ��������.
     */
    private String pnDesc;
    
    /**
     * ����.
     */
    private BigDecimal price;
    
    /**
     * ����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰.
     */
    private BigDecimal priceTax;
    
    /**
     * ����ʱ��.
     */
    private Date createDate;
    
    /**
     * ������.
     */
    private String staffId;
    
    /**
     * ������.
     */
    private String staffName;
    
    /**
     * ����˾�����ϱ��.
     */
    private String partNo;
    
    /**
     * ����˾�Ĵ���.
     */
    private String catCode;
    
    /**
     * ����˾��С��.
     */
    private String clsName;
    
    /**
     * ��С����������ȱʡΪ0.
     */
    private Integer minAmount=0;
    
    /**
     * ��С��װ��λ.
     */
    private Integer minPackage =0;
    
    /**
     * ��Ʒ���.
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
		sb.append("��˾���ϱ���:").append(partNo).append("  ");
		sb.append("��Ӧ�����ϱ���:").append(custPartNo).append("  ");
		sb.append("��Ӧ�̱���:").append(commCode).append("  ");
		sb.append("����:").append(price.floatValue()).append("  ");
		sb.append("��˰����:").append(priceTax.floatValue()).append("  ");
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
