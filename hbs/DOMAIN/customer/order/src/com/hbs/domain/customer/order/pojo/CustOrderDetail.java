package com.hbs.domain.customer.order.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * CustOrderDetail����.
 * @author hbs
 *
 */
public class CustOrderDetail extends BaseDomain{
    
    /**
     * Ψһ��seqid�����кţ�.
     */
    private String operSeqId;
    
    /**
     * �ͻ�����.
     */
    private String commCode;
    
    /**
     * �ͻ����.
     */
    private String shortName;
    
    /**
     * �ͻ���������0-�ͻ�����1-�ͻ��˻���.
     */
    private String poNoType;
    
    /**
     * ���㷽ʽ����Ӧ�ͻ���Ϣ�еĽ��㷽ʽ.
     */
    private String SettlementType;
    
    /**
     * �ͻ��������.
     */
    private String poNo;
    
    /**
     * �ͻ����ϱ��.
     */
    private Date cpartNo;
    
    /**
     * ��˾���ϱ��.
     */
    private String partNo;
    
    /**
     * ��������.
     */
    private String pnDesc;
    
    /**
     * ����.
     */
    private BigDecimal cprice;
    
    /**
     * ����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰
     */
    private BigDecimal cpriceTax;
    
    /**
     * �Ƿ�˰1--��0--����������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����.
     */
    private String isTax;
    
    /**
     * ˰��.
     */
    private BigDecimal taxRate;
    
    /**
     * ���ⱸע�����еĿͻ������������θ������������ֶ�.
     */
    private String specDesc;
    
    /**
     * һ�㱸ע.
     */
    private String commDesc;
    
    /**
     * ��������.
     */
    private Integer amount;
    
    /**
     * �ܽ��.
     */
    private BigDecimal money;
    
    /**
     * �Ѿ���������.
     */
    private Integer deliveryAmount=0;
    
    /**
     * �ֿ���������.
     */
    private Integer lockAmount=0;
    
    /**
     * ���ͻ���������.
     */
    private Integer selfLockAmount=0;
    
    /**
     * ͨ�ÿ����������.
     */
    private Integer commLockAmount=0;
    
    /**
     * ����ԭʼ��������.
     */
    private Date orgDeliveryDate;
    
    /**
     * ҵ�����������Ľ�������.
     */
    private Date preDeliveryDate;
    
    /**
     * �ɹ�������ȷ�Ͻ�������.
     */
    private Date verDeliveryDate;
    
    /**
     * ������ϸ��������.
     */
    private String period;
    
    /**
     * �����Ĳɹ����ţ�����ɹ�������.
     */
    private String rltOrderPoNo;
    
    /**
     * ��Ӧ�̱���.
     */
    private String vendorCode;
    
    /**
     * ������ϸ״̬.
     */
    private String state;
    
    
    /**
     * ¼����ID(ҵ������).
     */
    private String staffId;
    
    /**
     * ¼�붩������(ҵ������).
     */
    private String staffName;
    
    /**
     * ������ԱID.
     */
    private String salesId;
    
    /**
     * ����ͻ���������Ա����Ӧ�ͻ���Ϣ��������Ա.
     */
    private String sales;
    
    /**
     * �����Ļ״̬.
     */
    private String activeState="ACTIVE";
    
    /**
     * �����ĳ������ţ����Զ������.
     */
    private String rltSendPoNo;


    
    public String getOperSeqId() {
        return this.operSeqId;
    }	
  
    public void setOperSeqId(String operSeqId) {
        this.operSeqId = operSeqId;
    }
    
    public String getCommCode() {
        return this.commCode;
    }	
  
    public void setCommCode(String commCode) {
        this.commCode = commCode;
    }
    
    public String getShortName() {
        return this.shortName;
    }	
  
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public String getPoNoType() {
        return this.poNoType;
    }	
  
    public void setPoNoType(String poNoType) {
        this.poNoType = poNoType;
    }
    
    public String getPoNo() {
        return this.poNo;
    }	
  
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }
    
    public Date getCpartNo() {
        return this.cpartNo;
    }	
  
    public void setCpartNo(Date cpartNo) {
        this.cpartNo = cpartNo;
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
    
    public BigDecimal getCprice() {
        return this.cprice;
    }	
  
    public void setCprice(BigDecimal cprice) {
        this.cprice = cprice;
    }
    
    /**
	 * @return the cpriceTax
	 */
	public BigDecimal getCpriceTax() {
		return cpriceTax;
	}

	/**
	 * @param cpriceTax the cpriceTax to set
	 */
	public void setCpriceTax(BigDecimal cpriceTax) {
		this.cpriceTax = cpriceTax;
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
    
    public String getSpecDesc() {
        return this.specDesc;
    }	
  
    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc;
    }
    
    public String getCommDesc() {
        return this.commDesc;
    }	
  
    public void setCommDesc(String commDesc) {
        this.commDesc = commDesc;
    }
    
    public Integer getAmount() {
        return this.amount;
    }	
  
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }	
  
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public Integer getDeliveryAmount() {
        return this.deliveryAmount;
    }	
  
    public void setDeliveryAmount(Integer deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }
    
    public Integer getLockAmount() {
        return this.lockAmount;
    }	
  
    public void setLockAmount(Integer lockAmount) {
        this.lockAmount = lockAmount;
    }
    
    public Integer getSelfLockAmount() {
        return this.selfLockAmount;
    }	
  
    public void setSelfLockAmount(Integer selfLockAmount) {
        this.selfLockAmount = selfLockAmount;
    }
    
    public Integer getCommLockAmount() {
        return this.commLockAmount;
    }	
  
    public void setCommLockAmount(Integer commLockAmount) {
        this.commLockAmount = commLockAmount;
    }
    
    public Date getOrgDeliveryDate() {
        return this.orgDeliveryDate;
    }	
  
    public void setOrgDeliveryDate(Date orgDeliveryDate) {
        this.orgDeliveryDate = orgDeliveryDate;
    }
    
    public Date getPreDeliveryDate() {
        return this.preDeliveryDate;
    }	
  
    public void setPreDeliveryDate(Date preDeliveryDate) {
        this.preDeliveryDate = preDeliveryDate;
    }
    
    public Date getVerDeliveryDate() {
        return this.verDeliveryDate;
    }	
  
    public void setVerDeliveryDate(Date verDeliveryDate) {
        this.verDeliveryDate = verDeliveryDate;
    }
    
    public String getPeriod() {
        return this.period;
    }	
  
    public void setPeriod(String period) {
        this.period = period;
    }
    
    public String getRltOrderPoNo() {
        return this.rltOrderPoNo;
    }	
  
    public void setRltOrderPoNo(String rltOrderPoNo) {
        this.rltOrderPoNo = rltOrderPoNo;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
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
    
    public String getRltSendPoNo() {
        return this.rltSendPoNo;
    }	
  
    public void setRltSendPoNo(String rltSendPoNo) {
        this.rltSendPoNo = rltSendPoNo;
    }
    
    
    public String getSettlementType() {
		return SettlementType;
	}

	public void setSettlementType(String settlementType) {
		SettlementType = settlementType;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getBizKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.commCode).append(";");
    	sb.append(this.poNo).append(";");
    	sb.append(this.cpartNo).append(";");
    	if(this.specDesc != null){
    		sb.append(this.specDesc).append(";");
    	}
    	sb.append(this.operSeqId);
    	return sb.toString();
    }
	
	public String getLogBizKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.commCode).append(";");
    	sb.append(this.poNo);
    	
    	return sb.toString();
    }
	
    public String getWaitTaskBizKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.commCode).append("�Ķ�����");
    	sb.append(this.poNo);
    	
    	return sb.toString();
    }
}
