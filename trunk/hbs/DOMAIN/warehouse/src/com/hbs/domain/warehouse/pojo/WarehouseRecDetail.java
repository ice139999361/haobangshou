package com.hbs.domain.warehouse.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * WarehouseRecDetail����.
 * @author hbs
 *
 */
public class WarehouseRecDetail extends BaseDomain{
    
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
    private String cpartNo;
    
    /**
     * ��������.
     */
    private String pnDesc;
    
    /**
     * �ɹ��ĵ���.
     */
    private BigDecimal price;
    
    /**
     * ����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰
     */
    private BigDecimal priceTax;
    
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
     * �����ͻ����
     */
    private BigDecimal currMoney;
    
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
     * ҵ��״̬
     */
    private String state;
    /**
     * �״̬
     */
    private String activeState;
    /**
     * �������������
     */
    private String financePeriod;
    
    /**
     * ������������Կͻ������������ε����κ�
     */
    private String specDesc;
    
   

	public String getSpecDesc() {
		return specDesc;
	}

	public void setSpecDesc(String specDesc) {
		this.specDesc = specDesc;
	}

	/**
	 * @return the currMoney
	 */
	public BigDecimal getCurrMoney() {
		return currMoney;
	}

	/**
	 * @param currMoney the currMoney to set
	 */
	public void setCurrMoney(BigDecimal currMoney) {
		this.currMoney = currMoney;
	}

	public String getState() {
		return state;
	}
	public String getStateDesc(){
		String retStr ="δ����״̬��" + getState() +"��";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getState());
    	ceParam.setEncodeType("WAREHOUSE_IN_DETAIL_STATE");
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
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}

	public String getFinancePeriod() {
		return financePeriod;
	}

	public void setFinancePeriod(String financePeriod) {
		this.financePeriod = financePeriod;
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
    
    public String getPartNo() {
        return this.partNo;
    }	
  
    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }
    
    public String getCpartNo() {
        return this.cpartNo;
    }	
  
    public void setCpartNo(String cpartNo) {
        this.cpartNo = cpartNo;
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

    
    public BigDecimal getPriceTax() {
		return priceTax;
	}

	public void setPriceTax(BigDecimal priceTax) {
		this.priceTax = priceTax;
	}

	public String getLogKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.recPoNo).append(";");
    	sb.append(this.vendorCode).append(";");
    	sb.append(this.recDetailSeqId);
    	return sb.toString();
    	
    }
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.recDetailSeqId != null){
			sb.append("recDetailSeqId=").append(this.recDetailSeqId).append(" ");
		}
		if(this.recPoNo != null){
			sb.append("recPoNo=").append(this.recPoNo).append(" ");
		}
		if(this.vendorCode != null){
			sb.append("vendorCode=").append(this.vendorCode).append(" ");
		}
		if(this.shortName != null){
			sb.append("shortName=").append(this.shortName).append(" ");
		}
		if(this.poNoDate != null){
			sb.append("poNoDate=").append(DateUtils.getFormatDate(this.poNoDate,null)).append(" ");
		}
		if(this.applyDate != null){
			sb.append("applyDate=").append(DateUtils.getFormatDate(this.applyDate,null)).append(" ");
		}
		if(this.houseType != null){
			sb.append("houseType=").append(this.houseType).append(" ");
		}
		if(this.rltPoNo != null){
			sb.append("rltPoNo=").append(this.rltPoNo).append(" ");
		}
		if(this.settlementType != null){
			sb.append("settlementType=").append(this.settlementType).append(" ");
		}
		if(this.partNo != null){
			sb.append("partNo=").append(this.partNo).append(" ");
		}
		if(this.cpartNo != null){
			sb.append("venPartNo=").append(this.cpartNo).append(" ");
		}
		if(this.pnDesc != null){
			sb.append("pnDesc=").append(this.pnDesc).append(" ");
		}
		if(this.price != null){
			sb.append("price=").append(this.price.floatValue()).append(" ");
		}
		if(this.priceTax != null){
			sb.append("priceTax=").append(this.priceTax.floatValue()).append(" ");
		}
		if(this.isTax != null){
			sb.append("isTax=").append(this.isTax).append(" ");
		}
		if(this.taxRate != null){
			sb.append("taxRate=").append(this.taxRate.floatValue()).append(" ");
		}
		if(this.amount != null){
			sb.append("amount=").append(this.amount).append(" ");
		}
		if(this.period != null){
			sb.append("period=").append(this.period).append(" ");
		}
		if(this.financeState != null){
			sb.append("financeState=").append(this.financeState).append(" ");
		}
		if(this.poNoType != null){
			sb.append("poNoType=").append(this.poNoType).append(" ");
		}
		if(this.staffId != null){
			sb.append("staffId=").append(this.staffId).append(" ");
		}
		if(this.staffName != null){
			sb.append("staffName=").append(this.staffName).append(" ");
		}
		if(this.operTime != null){
			sb.append("operTime=").append(DateUtils.getFormatDate(this.operTime,null)).append(" ");
		}
		if(this.state != null){
			sb.append("state=").append(this.state).append(" ");
		}
		if(this.activeState != null){
			sb.append("activeState=").append(this.activeState).append(" ");
		}
		if(this.financePeriod != null){
			sb.append("financePeriod=").append(this.financePeriod).append(" ");
		}	
		if(this.specDesc != null){
			sb.append("specDesc=").append(this.specDesc).append(" ");
		}	
		
		return sb.toString();
	}

    
    
}
