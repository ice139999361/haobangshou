package com.hbs.domain.vendor.vendorinfo.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * VendorPartNoInfo����.
 * @author hbs
 *
 */
public class VendorPartNoInfo {
    
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
    private Integer minAmount;
    
    /**
     * ��С��װ��λ.
     */
    private Integer minPackage;
    
    /**
     * ��Ʒ���.
     */
    private String sampleCode;


    
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

}
