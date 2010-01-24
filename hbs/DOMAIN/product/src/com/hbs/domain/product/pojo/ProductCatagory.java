package com.hbs.domain.product.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;
/**
 * ProductCatagory����.
 * @author hbs
 *
 */
public class ProductCatagory extends BaseDomain{
    
    /**
     * ��Ʒ������.
     */
    private String catCode;
    
    /**
     * ��Ʒ��������.
     */
    private String catName;
    
    /**
     * ��Ʒ��������.
     */
    private String catDesc;


    
    public String getCatCode() {
        return this.catCode;
    }	
  
    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }
    
    public String getCatName() {
        return this.catName;
    }	
  
    public void setCatName(String catName) {
        this.catName = catName;
    }
    
    public String getCatDesc() {
        return this.catDesc;
    }	
  
    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

}
