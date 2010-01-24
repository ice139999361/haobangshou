package com.hbs.domain.product.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * ProductClass对象.
 * @author hbs
 *
 */
public class ProductClass extends BaseDomain{
    
    /**
     * 产品小类编码.
     */
    private String clsCode;
    
    /**
     * 所属产品大类.
     */
    private String catCode;
    
    /**
     * 产品小类名称.
     */
    private String clsName;
    
    /**
     * 产品小类特征描述.
     */
    private String clsDesc;


    
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
    
    public String getClsName() {
        return this.clsName;
    }	
  
    public void setClsName(String clsName) {
        this.clsName = clsName;
    }
    
    public String getClsDesc() {
        return this.clsDesc;
    }	
  
    public void setClsDesc(String clsDesc) {
        this.clsDesc = clsDesc;
    }

}
