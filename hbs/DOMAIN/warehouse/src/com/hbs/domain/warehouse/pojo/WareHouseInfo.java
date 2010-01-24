package com.hbs.domain.warehouse.pojo;


/**
 * WareHouseInfo����.
 * @author hbs
 *
 */
public class WareHouseInfo {
    
    /**
     * ���к�.
     */
    private Integer houseSeqId;
    
    /**
     * �ֿ����ͣ�ȱʡΪ��˾�ܿ�D.
     */
    private String houseType;
    
    /**
     * �ֿ������; ������� �ض��ͻ�����.
     */
    private String houseUse;
    
    /**
     * ��Ӧ�̱���.
     */
    private String vendorCode;
    
    /**
     * ��Ӧ�Ĳɹ�����.
     */
    private String poNo;
    
    /**
     * ����˾�����ϱ��.
     */
    private String partNo;
    
    /**
     * ��������.
     */
    private String pnDesc;
    
    /**
     * ��Ӧ�����ϱ��.
     */
    private String cpartNo;
    
    /**
     * ��HOUSE_USEΪ�ض��ͻ�����ʱ������дCUST_CODE.
     */
    private String custCode;
    
    /**
     * �ܿ�����������ͳ�����Ҫ����Ӧ�ļӼ�.
     */
    private Integer totalAmount;
    
    /**
     * �����Ŀ��.
     */
    private Integer lockAmount;
    
    /**
     * ���ÿ�棬���ÿ��+�������=�ܿ��.
     */
    private Integer useAmount;
    
    /**
     * ���������0��ʾ������.
     */
    private Integer maxAmount;
    
    /**
     * ��С�������ȱʡΪ0.
     */
    private Integer minAmount;
    
    /**
     * ���״̬0---�п��ÿ�� 1---�޿��ÿ��.
     */
    private String state;


    
    public Integer getHouseSeqId() {
        return this.houseSeqId;
    }	
  
    public void setHouseSeqId(Integer houseSeqId) {
        this.houseSeqId = houseSeqId;
    }
    
    public String getHouseType() {
        return this.houseType;
    }	
  
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    
    public String getHouseUse() {
        return this.houseUse;
    }	
  
    public void setHouseUse(String houseUse) {
        this.houseUse = houseUse;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    public String getPoNo() {
        return this.poNo;
    }	
  
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }
    
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
    
    public String getCpartNo() {
        return this.cpartNo;
    }	
  
    public void setCpartNo(String cpartNo) {
        this.cpartNo = cpartNo;
    }
    
    public String getCustCode() {
        return this.custCode;
    }	
  
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }
    
    public Integer getTotalAmount() {
        return this.totalAmount;
    }	
  
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Integer getLockAmount() {
        return this.lockAmount;
    }	
  
    public void setLockAmount(Integer lockAmount) {
        this.lockAmount = lockAmount;
    }
    
    public Integer getUseAmount() {
        return this.useAmount;
    }	
  
    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }
    
    public Integer getMaxAmount() {
        return this.maxAmount;
    }	
  
    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }
    
    public Integer getMinAmount() {
        return this.minAmount;
    }	
  
    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }
    
    public String getState() {
        return this.state;
    }	
  
    public void setState(String state) {
        this.state = state;
    }

}
