package com.hbs.domain.warehouse.pojo;

import java.util.Date;

/**
 * WarehouseSendInfo����.
 * @author hbs
 *
 */
public class WarehouseSendInfo {
    
    /**
     * �������ţ�ϵͳ�Զ�����������GSYYYYMMDDXXX.
     */
    private String sendPoNo;
    
    /**
     * �ͻ�����.
     */
    private String custCode;
    
    /**
     * �ͻ����.
     */
    private String shortName;
    
    /**
     * �ͻ��ջ�������.
     */
    private String receiveName;
    
    /**
     * �ͻ����ջ���ַ.
     */
    private String receiveAddress;
    
    /**
     * �ͻ��ջ��ʱ�.
     */
    private String receiveZip;
    
    /**
     * �ͻ��ջ��˵绰.
     */
    private String conTel;
    
    /**
     * �ͻ��ջ��˴���.
     */
    private String conFax;
    
    /**
     * ��Ӧ�Ĺ����ֹ�˾���֧����.
     */
    private String companyBranch;
    
    /**
     * �ֿ�λ��.
     */
    private String houseType;
    
    /**
     * ���㷽ʽ.
     */
    private String settlement_type;
    
    /**
     * ��������.
     */
    private Date createDate;
    
    /**
     * ������ID.
     */
    private String operId;
    
    /**
     * ����Ա.
     */
    private String operStaff;
    
    /**
     * ��������ע.
     */
    private String sendDesc;
    
    /**
     * ��������.
     */
    private String period;
    
    /**
     * �ջ�������ⵥ��״̬.
     */
    private String state;
    
    /**
     * �״̬.
     */
    private String activeState;
    
    /**
     * �������״̬0----δ����1---�Ѷ���.
     */
    private String financeState;
    
    /**
     * �ͻ���������0----���ⵥ1---�˻���.
     */
    private String poNoType;


    
    public String getSendPoNo() {
        return this.sendPoNo;
    }	
  
    public void setSendPoNo(String sendPoNo) {
        this.sendPoNo = sendPoNo;
    }
    
    public String getCustCode() {
        return this.custCode;
    }	
  
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }
    
    public String getShortName() {
        return this.shortName;
    }	
  
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public String getReceiveName() {
        return this.receiveName;
    }	
  
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
    
    public String getReceiveAddress() {
        return this.receiveAddress;
    }	
  
    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }
    
    public String getReceiveZip() {
        return this.receiveZip;
    }	
  
    public void setReceiveZip(String receiveZip) {
        this.receiveZip = receiveZip;
    }
    
    public String getConTel() {
        return this.conTel;
    }	
  
    public void setConTel(String conTel) {
        this.conTel = conTel;
    }
    
    public String getConFax() {
        return this.conFax;
    }	
  
    public void setConFax(String conFax) {
        this.conFax = conFax;
    }
    
    public String getCompanyBranch() {
        return this.companyBranch;
    }	
  
    public void setCompanyBranch(String companyBranch) {
        this.companyBranch = companyBranch;
    }
    
    public String getHouseType() {
        return this.houseType;
    }	
  
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    
    public String getSettlement_type() {
        return this.settlement_type;
    }	
  
    public void setSettlement_type(String settlement_type) {
        this.settlement_type = settlement_type;
    }
    
    public Date getCreateDate() {
        return this.createDate;
    }	
  
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    
    public String getSendDesc() {
        return this.sendDesc;
    }	
  
    public void setSendDesc(String sendDesc) {
        this.sendDesc = sendDesc;
    }
    
    public String getPeriod() {
        return this.period;
    }	
  
    public void setPeriod(String period) {
        this.period = period;
    }
    
    public String getState() {
        return this.state;
    }	
  
    public void setState(String state) {
        this.state = state;
    }
    
    public String getActiveState() {
        return this.activeState;
    }	
  
    public void setActiveState(String activeState) {
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
