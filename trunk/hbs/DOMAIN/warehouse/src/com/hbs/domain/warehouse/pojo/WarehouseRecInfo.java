package com.hbs.domain.warehouse.pojo;

import java.util.Date;

/**
 * WarehouseRecInfo����.
 * @author hbs
 *
 */
public class WarehouseRecInfo {
    
    /**
     * ��Ӧ����ⵥ�ţ���Ӧ���ͻ����ţ�.
     */
    private String recPoNo;
    
    /**
     * ��Ӧ�̱���.
     */
    private String vendorCode;
    
    /**
     * ��Ӧ�̼��.
     */
    private String shortName;
    
    /**
     * ��Ӧ�̵�������.
     */
    private Date poNoDate;
    
    /**
     * ���ﵽ������.
     */
    private Date applyDate;
    
    /**
     * �ֿ�λ��.
     */
    private String houseType;
    
    /**
     * ������ID.
     */
    private String operId;
    
    /**
     * ����Ա.
     */
    private String operStaff;
    
    /**
     * ����ʱ��.
     */
    private String operTime;
    
    /**
     * �ջ�����ע.
     */
    private String receiveDesc;
    
    /**
     * ��������.
     */
    private String period;
    
    /**
     * �ջ�������ⵥ��״̬.
     */
    private Integer state;
    
    /**
     * �״̬.
     */
    private Integer activeState;
    
    /**
     * �������״̬0----δ����1---�Ѷ���.
     */
    private String financeState;
    
    /**
     * ��Ӧ�̶�������0----��ⵥ1---�˻���.
     */
    private String poNoType;


    
    public String getRecPoNo() {
        return this.recPoNo;
    }	
  
    public void setRecPoNo(String recPoNo) {
        this.recPoNo = recPoNo;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    public String getShortName() {
        return this.shortName;
    }	
  
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public Date getPoNoDate() {
        return this.poNoDate;
    }	
  
    public void setPoNoDate(Date poNoDate) {
        this.poNoDate = poNoDate;
    }
    
    public Date getApplyDate() {
        return this.applyDate;
    }	
  
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    
    public String getHouseType() {
        return this.houseType;
    }	
  
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    
    public String getOperId() {
        return this.operId;
    }	
  
    public void setOperId(String operId) {
        this.operId = operId;
    }
    
    public String getOperStaff() {
        return this.operStaff;
    }	
  
    public void setOperStaff(String operStaff) {
        this.operStaff = operStaff;
    }
    
    public String getOperTime() {
        return this.operTime;
    }	
  
    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }
    
    public String getReceiveDesc() {
        return this.receiveDesc;
    }	
  
    public void setReceiveDesc(String receiveDesc) {
        this.receiveDesc = receiveDesc;
    }
    
    public String getPeriod() {
        return this.period;
    }	
  
    public void setPeriod(String period) {
        this.period = period;
    }
    
    public Integer getState() {
        return this.state;
    }	
  
    public void setState(Integer state) {
        this.state = state;
    }
    
    public Integer getActiveState() {
        return this.activeState;
    }	
  
    public void setActiveState(Integer activeState) {
        this.activeState = activeState;
    }
    
    public String getFinanceState() {
        return this.financeState;
    }	
  
    public void setFinanceState(String financeState) {
        this.financeState = financeState;
    }
    
    public String getPoNoType() {
        return this.poNoType;
    }	
  
    public void setPoNoType(String poNoType) {
        this.poNoType = poNoType;
    }

}
