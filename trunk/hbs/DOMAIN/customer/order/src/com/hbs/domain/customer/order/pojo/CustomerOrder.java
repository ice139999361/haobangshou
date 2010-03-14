package com.hbs.domain.customer.order.pojo;

import java.util.Date;
import java.util.List;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * CustomerOrder����.
 * @author hbs
 *
 */
public class CustomerOrder extends BaseDomain{
    
    /**
     * �ͻ�����.
     */
    private String commCode;
    
    /**
     * �ͻ��������.
     */
    private String poNo;
    
    /**
     * �ͻ���������0----�ͻ�����1---�ͻ��˻���.
     */
    private String poNoType;
    
    /**
     * �ͻ����.
     */
    private String shortName;
    
    /**
     * �ͻ���������.
     */
    private Date oderTime;
    
    /**
     * ��ϵ�ˣ���Ӧ�ͻ���ϵ���е�����ϵ��.
     */
    private String conName;
    
    /**
     * �绰����Ӧ�ͻ�����ϵ�˵ĵ绰.
     */
    private String conTel;
    
    /**
     * ���棬��Ӧ�ͻ�����ϵ�˵Ĵ���.
     */
    private String conFax;
    
    /**
     * ��Ӧ�ͻ���Ϣ�ж�Ӧ�ķֹ�˾���֧����.
     */
    private String companyBranch;
    
    /**
     * ���㷽ʽ����Ӧ�ͻ���Ϣ�еĽ��㷽ʽ.
     */
    private String settlementType;
    
    /**
     * �ջ��ˣ���Ӧ�ͻ���Ϣ���ջ���.
     */
    private String receiveName;
    
    /**
     * �ջ���ַ����Ӧ�ͻ���Ϣ���ջ���ַ.
     */
    private String receiveAddress;
    
    /**
     * �ջ��ʱ࣬��Ӧ�ͻ���Ϣ�е��ջ��ʱ�.
     */
    private String receiveZip;
    
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
     * �ͻ������Ƿ���ʾ���ۣ���̨���ݿͻ���Ϣ�Զ�����.
     */
    private String isShowPrice;
    
    /**
     * ��Ӧ�̱���.
     */
    private String vendorCode;
    
    /**
     * ����¼��ϵͳ������.
     */
    private Date fristCreateTime;
    
    /**
     * ��ʽ�γɶ������ڣ�ҵ��Ա��ʽ�ύ���ڣ�.
     */
    private Date createTime;
    
    /**
     * ������������.
     */
    private String period;
    
    /**
     * ������ҵ��״̬.
     */
    private String state;
    
    /**
     * �����Ļ״̬.
     */
    private String activeState="ACTIVE";
    /**
     * ������ϸ�б�
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
     * ��ȡ��˾���֧��������
     * @return
     */
    public String getCompanyBranchDesc(){
    	String retStr ="δ����";
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
    	String retStr ="δ����(" + getState() + ")";
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
    	sb.append(this.commCode).append("������Ϊ");
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
