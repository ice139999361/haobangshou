package com.hbs.domain.product.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * ProductClass����.
 * @author hbs
 *
 */
public class ProductClass extends BaseDomain{
    
    /**
     * ��ƷС�����.
     */
    private String clsCode;
    
    /**
     * ������Ʒ����.
     */
    private String catCode;
    
    /**
     * ��ƷС������.
     */
    private String clsName;
    
    /**
     * ��ƷС����������.
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
