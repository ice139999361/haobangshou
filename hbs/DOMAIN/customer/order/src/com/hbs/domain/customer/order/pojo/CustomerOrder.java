package com.hbs.domain.customer.order.pojo;

import java.util.Date;
import java.util.List;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * CustomerOrder对象.
 * @author hbs
 *
 */
public class CustomerOrder extends BaseDomain{
    
    /**
     * 客户编码.
     */
    private String commCode;
    
    /**
     * 客户订单编号.
     */
    private String poNo;
    
    /**
     * 客户订单类型0----客户订单1---客户退货单.
     */
    private String poNoType;
    
    /**
     * 客户简称.
     */
    private String shortName;
    
    /**
     * 客户订单日期.
     */
    private Date oderTime;
    
    /**
     * 联系人，对应客户联系人中的主联系人.
     */
    private String conName;
    
    /**
     * 电话，对应客户主联系人的电话.
     */
    private String conTel;
    
    /**
     * 传真，对应客户主联系人的传真.
     */
    private String conFax;
    
    /**
     * 对应客户信息中对应的分公司或分支机构.
     */
    private String companyBranch;
    
    /**
     * 结算方式，对应客户信息中的结算方式.
     */
    private String settlementType;
    
    /**
     * 收货人，对应客户信息的收货人.
     */
    private String receiveName;
    
    /**
     * 收货地址，对应客户信息的收货地址.
     */
    private String receiveAddress;
    
    /**
     * 收货邮编，对应客户信息中的收货邮编.
     */
    private String receiveZip;
    
    /**
     * 录入人ID(业务部助理).
     */
    private String staffId;
    
    /**
     * 录入订单的人(业务部助理).
     */
    private String staffName;
    
    /**
     * 销售人员ID.
     */
    private String salesId;
    
    /**
     * 负责客户的销售人员，对应客户信息的销售人员.
     */
    private String sales;
    
    /**
     * 客户订单是否显示单价（后台根据客户信息自动处理）.
     */
    private String isShowPrice;
    
    /**
     * 供应商编码.
     */
    private String vendorCode;
    
    /**
     * 初次录入系统的日期.
     */
    private Date fristCreateTime;
    
    /**
     * 正式形成订单日期（业务员正式提交日期）.
     */
    private Date createTime;
    
    /**
     * 订单所属账期.
     */
    private String period;
    
    /**
     * 订单的业务状态.
     */
    private String state;
    
    /**
     * 订单的活动状态.
     */
    private String activeState="ACTIVE";
    /**
     * 订单明细列表
     */
    private List<CustOrderDetail> orderDetailList;
    
    
    
    /**
	 * @return the orderDetailList
	 */
	public List<CustOrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	/**
	 * @param orderDetailList the orderDetailList to set
	 */
	public void setOrderDetailList(List<CustOrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public String getCommCode() {
        return this.commCode;
    }	
  
    public void setCommCode(String commCode) {
        this.commCode = commCode;
    }
    
    public String getPoNo() {
        return this.poNo;
    }	
  
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }
    
    public String getPoNoType() {
        return this.poNoType;
    }	
  
    public void setPoNoType(String poNoType) {
        this.poNoType = poNoType;
    }
    
    public String getShortName() {
        return this.shortName;
    }	
  
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public Date getOderTime() {
        return this.oderTime;
    }	
  
    public void setOderTime(Date oderTime) {
        this.oderTime = oderTime;
    }
    
    public String getConName() {
        return this.conName;
    }	
  
    public void setConName(String conName) {
        this.conName = conName;
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
  
    public void setSettlementType(String SettlementType) {
        this.settlementType = SettlementType;
    }
    
    public String getReceiveName() {
        return this.receiveName;
    }	
  
    public void setReceiveName(String ReceiveName) {
        this.receiveName = ReceiveName;
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
    
    public String getStaffId() {
        return this.staffId;
    }	
  
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    public String getStaffName() {
        return this.staffName;
    }	
  
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    public String getSalesId() {
        return this.salesId;
    }	
  
    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }
    
    public String getSales() {
        return this.sales;
    }	
  
    public void setSales(String sales) {
        this.sales = sales;
    }
    
    /**
     * 获取是否显示单价描述
     * @return
     */
    public String getIsShowPriceDesc(){
    	String retStr ="未定义";
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
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    public Date getFristCreateTime() {
        return this.fristCreateTime;
    }	
  
    public void setFristCreateTime(Date fristCreateTime) {
        this.fristCreateTime = fristCreateTime;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }	
  
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
  
    public String getStateDesc() {
    	String retStr ="未定义(" + getState() + ")";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getSettlementType());
    	ceParam.setEncodeType("CUST_ORDER_TYPE");
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

    public String getBizKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.commCode).append(";");
    	sb.append(this.poNo);
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
    	sb.append(this.commCode).append("订单号为");
    	sb.append(this.poNo);
    	return sb.toString();
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("commCode=").append(this.commCode).append(" ");
		sb.append("poNo=").append(this.poNo).append(" ");
		sb.append("poNoType=").append(this.poNoType).append(" ");
		sb.append("shortName=").append(this.shortName).append(" ");
		sb.append("oderTime=").append(this.oderTime == null ? null : DateUtils.getFormatDate(this.oderTime,null)).append(" ");
		sb.append("conName=").append(this.conName).append(" ");
		sb.append("conTel=").append(this.conTel).append(" ");
		sb.append("conFax=").append(this.conFax).append(" ");
		sb.append("companyBranch=").append(this.companyBranch).append(" ");
		sb.append("settlementType=").append(this.settlementType).append(" ");
		sb.append("receiveName=").append(this.receiveName).append(" ");
		sb.append("receiveAddress=").append(this.receiveAddress).append(" ");
		sb.append("receiveZip=").append(this.receiveZip).append(" ");
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("salesId=").append(this.salesId).append(" ");
		sb.append("sales=").append(this.sales).append(" ");
		sb.append("isShowPrice=").append(this.isShowPrice).append(" ");
		sb.append("vendorCode=").append(this.vendorCode).append(" ");
		sb.append("fristCreateTime=").append(this.fristCreateTime == null ? null : DateUtils.getFormatDate(this.fristCreateTime,null)).append(" ");
		sb.append("createTime=").append(this.createTime == null ? null : DateUtils.getFormatDate(this.createTime,null)).append(" ");
		sb.append("period=").append(this.period).append(" ");
		sb.append("state=").append(this.state).append(" ");
		sb.append("activeState=").append(this.activeState).append(" ");
		sb.append("orderDetailList=").append(this.orderDetailList == null ? null : orderDetailList.size()).append(" ");
		
		return sb.toString();
	}
}
