package com.hbs.domain.warehouse.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WarehouseRecDetail����.
 * @author hbs
 *
 */
public class WarehouseRecDetail {
    
    /**
     * �����ϸSEQID.
     */
    private Integer recDetailSeqId;
    
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
     * �ͻ�����Ӧ�Ĳɹ�����.
     */
    private String rltPoNo;
    
    /**
     * ���㷽ʽ.
     */
    private String settlementType;
    
    /**
     * ��˾�����ϱ��.
     */
    private String partNo;
    
    /**
     * ��Ӧ�̵����ϱ��.
     */
    private String venPartNo;
    
    /**
     * ��������.
     */
    private String pnDesc;
    
    /**
     * �ɹ��ĵ���.
     */
    private BigDecimal price;
    
    /**
     * �Ƿ�˰1--��0--�� ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����.
     */
    private String isTax;
    
    /**
     * ˰��.
     */
    private BigDecimal taxRate;
    
    /**
     * �����ͻ�����.
     */
    private Integer amount;
    
    /**
     * ��������.
     */
    private String period;
    
    /**
     * �������״̬0----δ����1---�Ѷ���.
     */
    private String financeState;
    
    /**
     * ��Ӧ�̶�������0---��ⵥ1---�˻���.
     */
    private String poNoType;

    /**
     * ������ID
     */
    private String staffId;
    /**
     * ����������
     */
    private String staffName;
    /**
     * ����ʱ��
     */
    private Date operTime;
    

    
   

	/**
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the operTime
	 */
	public Date getOperTime() {
		return operTime;
	}

	/**
	 * @param operTime the operTime to set
	 */
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public Integer getRecDetailSeqId() {
        return this.recDetailSeqId;
    }	
  
    public void setRecDetailSeqId(Integer recDetailSeqId) {
        this.recDetailSeqId = recDetailSeqId;
    }
    
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
    
    public String getRltPoNo() {
        return this.rltPoNo;
    }	
  
    public void setRltPoNo(String rltPoNo) {
        this.rltPoNo = rltPoNo;
    }
    
    public String getSettlementType() {
        return this.settlementType;
    }	
  
    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }
    
    public String getPartNo() {
        return this.partNo;
    }	
  
    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }
    
    public String getVenPartNo() {
        return this.venPartNo;
    }	
  
    public void setVenPartNo(String venPartNo) {
        this.venPartNo = venPartNo;
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
    
    public String getIsTax() {
        return this.isTax;
    }	
  
    public void setIsTax(String isTax) {
        this.isTax = isTax;
    }
    
    public BigDecimal getTaxRate() {
        return this.taxRate;
    }	
  
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    public Integer getAmount() {
        return this.amount;
    }	
  
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public String getPeriod() {
        return this.period;
    }	
  
    public void setPeriod(String period) {
        this.period = period;
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
