package com.hbs.domain.product.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;
/**
 * ProductCatagory对象.
 * @author hbs
 *
 */
public class ProductCatagory extends BaseDomain{
    
    /**
     * 产品大类编号.
     */
    private String catCode;
    
    /**
     * 产品大类名称.
     */
    private String catName;
    
    /**
     * 产品大类描述.
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
