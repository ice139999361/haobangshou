package com.hbs.domain.vendor.order.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * VendorOrderDetail����.
 * @author hbs
 *
 */
public class VendorOrderDetail extends BaseDomain{
    
    /**
     * Ψһ��seqid�����кţ�.
     */
    private Integer operSeqId;
    
    /**
     * ��Ӧ�̱���.
     */
    private String commCode;
    
    /**
     * ���.
     */
    private String shortName;
    
    /**
     * ��Ӧ�̶�������
        0----�ͻ��ɹ���
        1---�˻���
        2---���汸���ɹ���
        3--�ض��ͻ������ɹ���
     */
    private String poNoType;
    
    /**
     * �������.
     */
    private String poNo;
    
    /**
     * ���ϱ��.
     */
    private String cpartNo;
    
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
     * �Ƿ�˰����1--��0--����������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����.
     */
    private String isTax;
    
    /**
     * ˰��.
     */
    private BigDecimal taxRate ;
    
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
    private Integer amount =0;
    
    /**
     * �ܽ��.
     */
    private BigDecimal money;
    
    /**
     * �Ѿ��ջ�����.
     */
    private Integer deliveryAmount;
    
    /**
     * ����ԭʼ��������.
     */
    private Date orgDeliveryDate;
    
    /**
     * �ɹ�������ȷ�Ͻ�������.
     */
    private Date verDeliveryDate;
    
    /**
     * ������ϸ��������.
     */
    private String period;
    
    /**
     * �����Ŀͻ������ţ������ж���ͻ��������ţ���.
     */
    private String rltOrderPoNo;
    
    /**
     * ������ϸ״̬.
     */
    private String state;
    
    /**
     * �����Ļ״̬.
     */
    private String activeState="ACTIVE";
    
    /**
     * ��������ⵥ�ţ������ж������.
     */
    private String rltRecPoNo;


    /**
     * ������ԱID
     */
    private String staffId;
    
    /**
     * ������Ա����
     */
    private String staffName;
    
    /**
     * ���㷽ʽ
     */
    private String settlementType;
    
    
    /**
     * ���0----�ͻ��ɹ�����3-- �ض��ͻ�������Ч
     */
    private String custCcode;
    
    /**
     * ׷������/��
     */
    private Integer hastenReminder;

    public String getCustCcode() {
		return custCcode;
	}

	public void setCustCcode(String custCcode) {
		this.custCcode = custCcode;
	}

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
     * ��ȡ���㷽ʽ����
     * @return
     */
    public String getSettlementTypeDesc(){
    	String retStr ="δ����";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getSettlementType());
    	ceParam.setEncodeType("SETTLEMENT_TYPE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
    }
	
	/**
	 * @return the settlementType
	 */
	public String getSettlementType() {
		return settlementType;
	}

	/**
	 * @param settlementType the settlementType to set
	 */
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public Integer getOperSeqId() {
        return this.operSeqId;
    }	
  
    public void setOperSeqId(Integer operSeqId) {
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
    
    public String getCpartNo() {
        return this.cpartNo;
    }	
  
    public void setCpartNo(String cpartNo) {
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
    
    public Date getOrgDeliveryDate() {
        return this.orgDeliveryDate;
    }	
  
    public void setOrgDeliveryDate(Date orgDeliveryDate) {
        this.orgDeliveryDate = orgDeliveryDate;
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
    
    public String getState() {
        return this.state;
    }	
    public String getStateDesc() {
    	String retStr ="δ����";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getState());
    	ceParam.setEncodeType("VENDOR_ORDER_DETAIL_STATE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
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
    
    public String getRltRecPoNo() {
        return this.rltRecPoNo;
    }	
  
    public void setRltRecPoNo(String rltRecPoNo) {
        this.rltRecPoNo = rltRecPoNo;
    }

    
    public BigDecimal getCpriceTax() {
		return cpriceTax;
	}

	public void setCpriceTax(BigDecimal cpriceTax) {
		this.cpriceTax = cpriceTax;
	}

	public String getLogKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.commCode).append(";").append(this.poNo).append(this.operSeqId);
    	return sb.toString();
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("operSeqId=").append(this.operSeqId).append(" ");
		sb.append("commCode=").append(this.commCode).append(" ");
		sb.append("shortName=").append(this.shortName).append(" ");
		sb.append("poNoType=").append(this.poNoType).append(" ");
		sb.append("poNo=").append(this.poNo).append(" ");
		sb.append("cpartNo=").append(this.cpartNo).append(" ");
		sb.append("partNo=").append(this.partNo).append(" ");
		sb.append("pnDesc=").append(this.pnDesc).append(" ");
		sb.append("cprice=").append(this.cprice == null ? null : this.cprice.floatValue()).append(" ");
		sb.append("cpriceTax=").append(this.cpriceTax != null ? this.cpriceTax.floatValue() : " ").append(" ");
		sb.append("isTax=").append(this.isTax).append(" ");
		sb.append("taxRate=").append(this.taxRate == null ? null : this.taxRate.floatValue()).append(" ");
		sb.append("specDesc=").append(this.specDesc).append(" ");
		sb.append("commDesc=").append(this.commDesc).append(" ");
		sb.append("amount=").append(this.amount).append(" ");
		sb.append("money=").append(this.money == null ? null : this.money.floatValue()).append(" ");
		sb.append("deliveryAmount=").append(this.deliveryAmount).append(" ");
		sb.append("orgDeliveryDate=").append(this.orgDeliveryDate == null ? null : DateUtils.getFormatDate(this.orgDeliveryDate,null)).append(" ");
		sb.append("verDeliveryDate=").append(this.verDeliveryDate == null ? null : DateUtils.getFormatDate(this.verDeliveryDate,null)).append(" ");
		sb.append("period=").append(this.period).append(" ");
		sb.append("rltOrderPoNo=").append(this.rltOrderPoNo).append(" ");
		sb.append("state=").append(this.state).append(" ");
		sb.append("activeState=").append(this.activeState).append(" ");
		sb.append("rltRecPoNo=").append(this.rltRecPoNo).append(" ");
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("settlementType=").append(this.settlementType).append(" ");
		sb.append("custCcode=").append(this.custCcode).append(" ");
		sb.append("hastenReminder=").append(this.hastenReminder).append(" ");
		return sb.toString();
	}
    
    
	public Integer getHastenReminder() {
		return hastenReminder;
	}

	public void setHastenReminder(Integer hastenReminder) {
		this.hastenReminder = hastenReminder;
	}
    
}
