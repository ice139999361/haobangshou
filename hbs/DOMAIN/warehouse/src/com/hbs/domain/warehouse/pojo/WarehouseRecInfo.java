package com.hbs.domain.warehouse.pojo;

import java.util.Date;
import java.util.List;

import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * WarehouseRecInfo对象.
 * @author hbs
 *
 */
public class WarehouseRecInfo extends BaseDomain{
    
    /**
     * 供应商入库单号（供应商送货单号）.
     */
    private String recPoNo;
    
    /**
     * 供应商编码.
     */
    private String vendorCode;
    
    /**
     * 供应商简称.
     */
    private String shortName;
    
    /**
     * 供应商单据日期.
     */
    private Date poNoDate;
    
    /**
     * 货物到达日期.
     */
    private Date applyDate;
    
    /**
     * 仓库位置.
     */
    private String houseType;
    
    /**
     * 操作人ID.
     */
    private String operId;
    
    /**
     * 操作员.
     */
    private String operStaff;
    
    /**
     * 操作时间.
     */
    private Date operTime;
    
    /**
     * 收货单备注.
     */
    private String receiveDesc;
    
    /**
     * 所属账期.
     */
    private String period;
    
    /**
     * 收货单（入库单）状态.
     */
    private String state="01";
    
    /**
     * 活动状态.
     */
    private String activeState="ACTIVE";
    
    /**
     * 财务结算状态0----未对账1---已对账.
     */
    private String financeState ="0";
    
    /**
     * 供应商订单类型0----入库单1---退货单.
     */
    private String poNoType;
    
    /**
     * 结算方式
     */
    private String settlementType;

    /**
     * 供应商入库单明细
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
