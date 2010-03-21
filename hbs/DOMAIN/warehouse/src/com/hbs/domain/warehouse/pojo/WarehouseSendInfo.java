package com.hbs.domain.warehouse.pojo;

import java.util.Date;
import java.util.List;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * WarehouseSendInfo对象.
 * @author hbs
 *
 */
public class WarehouseSendInfo extends BaseDomain{
    
    /**
     * 发货单号，系统自动产生，规则：GSYYYYMMDDXXX.
     */
    private String sendPoNo;
    
    /**
     * 客户编码.
     */
    private String custCode;
    
    /**
     * 客户简称.
     */
    private String shortName;
    
    /**
     * 客户收货人姓名.
     */
    private String receiveName;
    
    /**
     * 客户的收货地址.
     */
    private String receiveAddress;
    
    /**
     * 客户收货邮编.
     */
    private String receiveZip;
    
    /**
     * 客户收货人电话.
     */
    private String conTel;
    
    /**
     * 客户收货人传真.
     */
    private String conFax;
    
    /**
     * 对应的供货分公司或分支机构.
     */
    private String companyBranch;
    
    /**
     * 仓库位置.
     */
    private String houseType;
    
    /**
     * 结算方式.
     */
    private String settlementType;
    
    /**
     * 开单日期.
     */
    private Date createDate;
    
    /**
     * 操作人ID.
     */
    private String operId;
    
    /**
     * 操作员.
     */
    private String operStaff;
    
    /**
     * 发货单备注.
     */
    private String sendDesc;
    
    /**
     * 所属账期.
     */
    private String period;
    
    /**
     * 收货单（入库单）状态.
     */
    private String state;
    
    /**
     * 活动状态.
     */
    private String activeState;
    
    /**
     * 财务结算状态0----未对账1---已对账.
     */
    private String financeState;
    
    /**
     * 客户订单类型0----出库单1---退货单.
     */
    private String poNoType;
    /**
     * 出库单明细列表
     */
    private List<WarehouseSendDetail> detailList;
    
    /**
	 * @return the detailList
	 */
	public List<WarehouseSendDetail> getDetailList() {
		return detailList;
	}

	/**
	 * @param detailList the detailList to set
	 */
	public void setDetailList(List<WarehouseSendDetail> detailList) {
		this.detailList = detailList;
	}

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
    
    /**
     * 获取公司或分支机构描述
     * @return
     */
    public String getCompanyBranchDesc(){
    	String retStr ="未定义";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getCompanyBranch());
    	ceParam.setEncodeType("COMPANY_BRANCH");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
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
    
    /**
     * 获取结算方式描述
     * @return
     */
    public String getSettlementTypeDesc(){
    	String retStr ="未定义";
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

    public String getLogKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.sendPoNo).append(";").append(this.custCode);
    	return sb.toString();
    }
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("sendPoNo=").append(this.sendPoNo).append(" ");
		sb.append("custCode=").append(this.custCode).append(" ");
		sb.append("shortName=").append(this.shortName).append(" ");
		sb.append("receiveName=").append(this.receiveName).append(" ");
		sb.append("receiveAddress=").append(this.receiveAddress).append(" ");
		sb.append("receiveZip=").append(this.receiveZip).append(" ");
		sb.append("conTel=").append(this.conTel).append(" ");
		sb.append("conFax=").append(this.conFax).append(" ");
		sb.append("companyBranch=").append(this.companyBranch).append(" ");
		sb.append("houseType=").append(this.houseType).append(" ");
		sb.append("settlement_type=").append(this.settlementType).append(" ");
		sb.append("createDate=").append(this.createDate == null ? " " :DateUtils.getFormatDate(this.createDate,null)).append(" ");
		sb.append("operId=").append(this.operId).append(" ");
		sb.append("operStaff=").append(this.operStaff).append(" ");
		sb.append("sendDesc=").append(this.sendDesc).append(" ");
		sb.append("period=").append(this.period).append(" ");
		sb.append("state=").append(this.state).append(" ");
		sb.append("activeState=").append(this.activeState).append(" ");
		sb.append("financeState=").append(this.financeState).append(" ");
		
		sb.append("poNoType=").append(this.poNoType).append(" ");
		sb.append("detailList.size()=").append(this.detailList == null ? 0 : this.detailList.size()).append(" ");
		
		return sb.toString();
	}

}
