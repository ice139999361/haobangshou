package com.hbs.domain.warehouse.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WarehouseSendDetail����.
 * @author hbs
 *
 */
public class WarehouseSendDetail {
    
    /**
     * ������ϸSEQID.
     */
    private Integer sendSeqId;
    
    /**
     * ���ⵥ��.
     */
    private String sendPoNo;
    
    /**
     * ��˾���ϱ��.
     */
    private String partNo;
    
    /**
     * �ͻ����ϱ��.
     */
    private String custPartNo;
    
    /**
     * ��������.
     */
    private String pnDesc;
    
    /**
     * ���ⱸע�����������ε�.
     */
    private String specDesc;
    
    /**
     * �Ƿ�˰����1--��0--�� ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����.
     */
    private String isTax;
    
    /**
     * ���ϵ���.
     */
    private BigDecimal price;
    
    /**
     * ˰��.
     */
    private BigDecimal taxRate;
    
    /**
     * �Ƿ���ʾ����.
     */
    private String isShowPrice;
    
    /**
     * ��Ӧ�Ŀͻ�������.
     */
    private String rltPoNo;
    
    /**
     * ���㷽ʽ.
     */
    private String settlementType;
    
    /**
     * ��ע.
     */
    private String commDesc;
    
    /**
	 * @return the commDesc
	 */
	public String getCommDesc() {
		return commDesc;
	}

	/**
	 * @param commDesc the commDesc to set
	 */
	public void setCommDesc(String commDesc) {
		this.commDesc = commDesc;
	}

	/**
     * ��������.
     */
    private Integer amount;
    
    /**
     * ���γ������.
     */
    private BigDecimal curMoney;
    
    /**
     * ��Ӧ�̱���.
     */
    private String vendorCode;
    
    /**
     * ��Ӧ�̵Ĳɹ����ţ����Զ������.
     */
    private String vendorPoNo;
    
    /**
     * ��������.
     */
    private String period;
    
    /**
     * ��������.
     */
    private Date createTime;
    
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

	public Integer getSendSeqId() {
        return this.sendSeqId;
    }	
  
    public void setSendSeqId(Integer sendSeqId) {
        this.sendSeqId = sendSeqId;
    }
    
    public String getSendPoNo() {
        return this.sendPoNo;
    }	
  
    public void setSendPoNo(String sendPoNo) {
        this.sendPoNo = sendPoNo;
    }
    
    public String getPartNo() {
        return this.partNo;
    }	
  
    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }
    
    public String getCustPartNo() {
        return this.custPartNo;
    }	
  
    public void setCustPartNo(String custPartNo) {
        this.custPartNo = custPartNo;
    }
    
    public String getPnDesc() {
        return this.pnDesc;
    }	
  
    public void setPnDesc(String pnDesc) {
        this.pnDesc = pnDesc;
    }
    
  
    public String getIsTax() {
        return this.isTax;
    }	
  
    public void setIsTax(String isTax) {
        this.isTax = isTax;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }	
  
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getTaxRate() {
        return this.taxRate;
    }	
  
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    public String getIsShowPrice() {
        return this.isShowPrice;
    }	
  
    public void setIsShowPrice(String isShowPrice) {
        this.isShowPrice = isShowPrice;
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
    
    public String getSpecDesc() {
        return this.specDesc;
    }	
  
    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc;
    }
    
    public Integer getAmount() {
        return this.amount;
    }	
  
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public BigDecimal getCurMoney() {
        return this.curMoney;
    }	
  
    public void setCurMoney(BigDecimal curMoney) {
        this.curMoney = curMoney;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    public String getVendorPoNo() {
        return this.vendorPoNo;
    }	
  
    public void setVendorPoNo(String vendorPoNo) {
        this.vendorPoNo = vendorPoNo;
    }
    
    public String getPeriod() {
        return this.period;
    }	
  
    public void setPeriod(String period) {
        this.period = period;
    }
    
   
    
    /**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
