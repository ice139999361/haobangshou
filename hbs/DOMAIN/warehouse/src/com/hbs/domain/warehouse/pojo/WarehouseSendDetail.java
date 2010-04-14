package com.hbs.domain.warehouse.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * WarehouseSendDetail����.
 * @author hbs
 *
 */
public class WarehouseSendDetail extends BaseDomain{
    
    /**
     * ������ϸSEQID.
     */
    private Integer sendSeqId;
    
    /**
     * ���ⵥ��.
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
     * ����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰
     */
    private BigDecimal priceTax;
    
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
	 * @return the custCode
	 */
	public String getCustCode() {
		return custCode;
	}

	/**
	 * @param custCode the custCode to set
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

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
    private Integer amount=0;
    /**
     * ���ͻ�������������ͬ�ͻ�������Ӧ
     */
    private Integer selfAmount =0;
    /**
     * ͨ�ÿ�����������ͬ�ͻ�������Ӧ
     */
    private Integer commAmount =0;
    
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
    @Deprecated
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
     * ��ͬ��
     */
    private BigDecimal contactFee;
    
    /**
     *  ����ȷ�ϵ����ڻ�������������
     */
    private String financePeriod;
    
    /**
     * ���γ����ֿ����ͣ�ͬ�ͻ�������Ӧ
     */
    private String houseType;

	public String getFinancePeriod() {
		return financePeriod;
	}

	public Integer getSelfAmount() {
		return selfAmount;
	}

	public void setSelfAmount(Integer selfAmount) {
		this.selfAmount = selfAmount;
	}

	public Integer getCommAmount() {
		return commAmount;
	}

	public void setCommAmount(Integer commAmount) {
		this.commAmount = commAmount;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public void setFinancePeriod(String financePeriod) {
		this.financePeriod = financePeriod;
	}

	public BigDecimal getPriceTax() {
		return priceTax;
	}

	public void setPriceTax(BigDecimal priceTax) {
		this.priceTax = priceTax;
	}

	public BigDecimal getContactFee() {
		return contactFee;
	}

	public void setContactFee(BigDecimal contactFee) {
		this.contactFee = contactFee;
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
    
    /**
     * ��ȡ�Ƿ���ʾ��������
     * @return
     */
    public String getIsShowPriceDesc(){
    	String retStr ="δ����";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getIsShowPrice());
    	ceParam.setEncodeType("IS_SHOW_PRICE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
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
    @Deprecated
    public String getVendorPoNo() {
        return this.vendorPoNo;
    }	
    @Deprecated
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
	
	public String getStateDesc() {
		String retStr ="δ����״̬��" + getState() +"��";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getState());
    	ceParam.setEncodeType("WAREHOUSE_OUT_DETAIL_STATE");
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

    
    public String getLogKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.sendPoNo).append(";");
    	sb.append(this.custCode).append(";");
    	sb.append(this.sendSeqId);
    	return sb.toString();
    	
    }
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("sendSeqId=").append(this.sendSeqId == null ? "  " : this.sendSeqId.intValue()).append(" ");
		sb.append("sendPoNo=").append(this.sendPoNo).append(" ");
		sb.append("custCode=").append(this.custCode).append(" ");
		sb.append("shortName=").append(this.shortName).append(" ");
		sb.append("partNo=").append(this.partNo).append(" ");
		sb.append("custPartNo=").append(this.custPartNo).append(" ");
		sb.append("pnDesc=").append(this.pnDesc).append(" ");
		sb.append("specDesc=").append(this.specDesc).append(" ");
		sb.append("isTax=").append(this.isTax).append(" ");
		sb.append("price=").append(this.price == null ? " " : this.price.floatValue()).append(" ");
		sb.append("priceTax=").append(this.priceTax == null ? " " : this.priceTax.floatValue()).append(" ");
		sb.append("taxRate=").append(this.taxRate == null ? " " : this.taxRate.floatValue()).append(" ");
		sb.append("isShowPrice=").append(this.isShowPrice).append(" ");
		sb.append("rltPoNo=").append(this.rltPoNo).append(" ");
		sb.append("settlementType=").append(this.settlementType).append(" ");
		sb.append("commDesc=").append(this.commDesc).append(" ");
		sb.append("amount=").append(this.amount.intValue()).append(" ");
		sb.append("selfAmount=").append(this.selfAmount.intValue()).append(" ");
		sb.append("commAmount=").append(this.commAmount.intValue()).append(" ");
		sb.append("curMoney=").append(this.curMoney == null ? " " : this.curMoney.floatValue()).append(" ");
		sb.append("vendorCode=").append(this.vendorCode).append(" ");
		sb.append("vendorPoNo=").append(this.vendorPoNo).append(" ");
		sb.append("period=").append(this.period).append(" ");
		sb.append("createTime=").append(this.createTime == null ? " " : DateUtils.getFormatDate(this.createTime,null)).append(" ");
		sb.append("state=").append(this.state).append(" ");
		sb.append("activeState=").append(this.activeState).append(" ");
		sb.append("financeState=").append(this.financeState).append(" ");
		sb.append("financePeriod=").append(this.financePeriod).append(" ");
		sb.append("poNoType=").append(this.poNoType).append(" ");
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("houseType=").append(this.houseType).append(" ");
		sb.append("contactFee=").append(this.contactFee == null ? " " : this.contactFee.floatValue()).append(" ");
		return sb.toString();
	}

    
}
