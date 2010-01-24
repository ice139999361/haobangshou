package com.hbs.domain.product.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * CompanyPartNo����,��չBaseDomain,֧�ַ�ҳ,����.
 * @author hbs
 *
 */
public class CompanyPartNo extends BaseDomain{
    
    /**
     * ���ϱ��.
     */
    private String partNo;
    
    /**
     * ��������.
     */
    private String pnDesc;
    
    /**
     * �۸�.
     */
    private BigDecimal price;
    
    /**
     * ��˰�۸�.
     */
    private BigDecimal taxPrice;
    
    /**
     * ����ʱ��.
     */
    private Date createTime;
    
    /**
     * ������ƷС��.
     */
    private String clsCode;
    
    /**
     * ������Ʒ����.
     */
    private String catCode;


    
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
    
    public BigDecimal getPrice() {
        return this.price;
    }	
  
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getTaxPrice() {
        return this.taxPrice;
    }	
  
    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }	
  
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getClsCode() {
        return this.clsCode;
    }	
  
    public void setClsCode(String clsCode) {
        this.clsCode = clsCode;
    }
    
    public String getCatCode() {
        return this.catCode;
    }	
  
    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

}
