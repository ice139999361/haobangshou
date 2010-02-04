package com.hbs.domain.warehouse.pojo;

import java.util.Date;
import java.util.List;

import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * WarehouseRecInfo����.
 * @author hbs
 *
 */
public class WarehouseRecInfo extends BaseDomain{
    
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
    private Date operTime;
    
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
    private String state="01";
    
    /**
     * �״̬.
     */
    private String activeState="ACTIVE";
    
    /**
     * �������״̬0----δ����1---�Ѷ���.
     */
    private String financeState ="0";
    
    /**
     * ��Ӧ�̶�������0----��ⵥ1---�˻���.
     */
    private String poNoType;
    
    /**
     * ���㷽ʽ
     */
    private String settlementType;

    /**
     * ��Ӧ����ⵥ��ϸ
     */
    private List<WarehouseRecDetail> detailList;
    
    
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

	public List<WarehouseRecDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<WarehouseRecDetail> detailList) {
		this.detailList = detailList;
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
    
    public Date getOperTime() {
        return this.operTime;
    }	
  
    public void setOperTime(Date operTime) {
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

    public String getLogKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.recPoNo).append(";").append(this.vendorCode);
    	return sb.toString();
    }
    
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(null != this.recPoNo){
			sb.append("recPoNo=").append(this.recPoNo).append(" ");
		}
		if(null != this.vendorCode){
			sb.append("vendorCode=").append(this.vendorCode).append(" ");
		}
		if(null != this.shortName){
			sb.append("shortName=").append(this.shortName).append(" ");
		}
		if(null != this.poNoDate){
			sb.append("poNoDate=").append(DateUtils.getFormatDate(this.poNoDate,null)).append(" ");
		}
		if(null != this.applyDate){
			sb.append("applyDate=").append(DateUtils.getFormatDate(this.applyDate,null)).append(" ");
		}
		if(null != this.houseType){
			sb.append("houseType=").append(this.houseType).append(" ");
		}
		if(null != this.operId){
			sb.append("operId=").append(this.operId).append(" ");
		}
		if(null != this.operStaff){
			sb.append("operStaff=").append(this.operStaff).append(" ");
		}
		if(null != this.operTime){
			sb.append("operTime=").append(DateUtils.getFormatDate(this.operTime,null)).append(" ");
		}
		if(null != this.receiveDesc){
			sb.append("receiveDesc=").append(this.receiveDesc).append(" ");
		}
		if(null != this.period){
			sb.append("period=").append(this.period).append(" ");
		}
		if(null != this.state){
			sb.append("state=").append(this.state).append(" ");
		}
		if(null != this.activeState){
			sb.append("activeState=").append(this.activeState).append(" ");
		}
		if(null != this.financeState){
			sb.append("financeState=").append(this.financeState).append(" ");
		}
		
		if(null != this.poNoType){
			sb.append("poNoType=").append(this.poNoType).append(" ");
		}
		
		if(null != this.detailList){
			sb.append("detailList=").append((this.detailList).size()).append(" ");
		}
		
		return sb.toString();
	}

}
