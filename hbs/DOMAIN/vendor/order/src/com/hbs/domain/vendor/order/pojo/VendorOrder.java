package com.hbs.domain.vendor.order.pojo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendorinfo.manager.VendorInfoMgr;

/**
 * VendorOrder����.
 * @author hbs
 *
 */
public class VendorOrder extends BaseDomain{
    
    /**
     * ��Ӧ�̱���.
     */
    private String commCode;
    
    /**
     * ��Ӧ�̶������.
     */
    private String poNo;
    
    /**
     * ��Ӧ�̶�������
        0----�ͻ��ɹ���
        1---�˻���
        2---���汸���ɹ���
        3--�ض��ͻ������ɹ���
     */
    private String poNoType;
    
    /**
     * ���.
     */
    private String shortName;
    
    /**
     * ��������.
     */
    private Date createTime;
    
    /**
     * ��ϵ�ˣ���Ӧ��ϵ���е�����ϵ��.
     */
    private String conName;
    
    /**
     * �绰����Ӧ����ϵ�˵ĵ绰.
     */
    private String conTel;
    
    /**
     * ���棬��Ӧ����ϵ�˵Ĵ���.
     */
    private String conFax;
    
    /**
     * ��Ӧ��Ϣ�ж�Ӧ�ķֹ�˾���֧����.
     */
    private String companyBranch;
    
    /**
     * ���㷽ʽ����Ӧ��Ϣ�еĽ��㷽ʽ.
     */
    private String settlementType;
    
    /**
     * �ջ��ˣ���Ӧ��Ϣ���ջ���.
     */
    private String receiveName;
    
    /**
     * �ջ���ַ����Ӧ��Ϣ���ջ���ַ.
     */
    private String receiveAddress;
    
    /**
     * �ջ��ʱ࣬��Ӧ��Ϣ�е��ջ��ʱ�.
     */
    private String receiveZip;
    
    /**
     * ¼����ID.
     */
    private String staffId;
    
    /**
     * ¼�붩������.
     */
    private String staffName;
    
    /**
     * �������۵�������Ա����Ӧ��Ӧ�̵�������Ա.
     */
    private String sales;
    
    /**
     * �ͻ������Ƿ���ʾ���ۣ���̨���ݿͻ���Ϣ�Զ�����.
     */
    private String isShowPrice;
    
    /**
     * ��ʽ�γɶ�������.
     */
    private Date orderTime;
    
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
    private String activeState;
    
    /**
     * ���0----�ͻ��ɹ�����3-- �ض��ͻ�������Ч
     */
    private String custCcode;

    /**
     * ׷������/��
     */
    private Integer hastenReminder;
    
    /**
     * �½�����
     */
    private String settlementDay;
    /**
     * �½���������
     */
    private String settlementDayDesc;
    
    
    
    public String getSettlementDay() {
		return settlementDay;
	}

	public void setSettlementDay(String settlementDay) {
		this.settlementDay = settlementDay;
	}

	public String getSettlementDayDesc() {
		return settlementDayDesc;
	}

	public void setSettlementDayDesc(String settlementDayDesc) {
		this.settlementDayDesc = settlementDayDesc;
	}

	public String getCustCcode() {
		return custCcode;
	}

	public void setCustCcode(String custCcode) {
		this.custCcode = custCcode;
	}

	/**
     * ������ϸ�б�
     */
    private List<VendorOrderDetail> vendorOrderDetailList;
    
    public List<VendorOrderDetail> getVendorOrderDetailList() {
		return vendorOrderDetailList;
	}

	public void setVendorOrderDetailList(
			List<VendorOrderDetail> vendorOrderDetailList) {
		this.vendorOrderDetailList = vendorOrderDetailList;
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
    
    public Date getCreateTime() {
        return this.createTime;
    }	
  
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    	if(StringUtils.isEmpty(getCompanyBranch())){
    		retStr ="��ֵ";
    	}else{
	    	ceParam.setEncodeKey(getCompanyBranch());
	    	ceParam.setEncodeType("COMPANY_BRANCH");
	    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
	    	if(null != cEncode){
	    		retStr = cEncode.getEncodeValue();
	    	}
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
  
    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
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
    
    public Date getOrderTime() {
        return this.orderTime;
    }	
  
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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
    
    /**
     * ��ȡ״̬����
     * @return
     */
    public String getStateDesc(){
    	String retStr ="δ����(" + getState() +")";
    	if(StringUtils.isNotEmpty(getState())){
	    	ConfigEncode ceParam = new ConfigEncode();
	    	ceParam.setEncodeKey(getState());
	    	ceParam.setEncodeType("VENDOR_ORDER_STATE");
	    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
	    	if(null != cEncode){
	    		retStr = cEncode.getEncodeValue();
	    	}
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

    public String getLogKey(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.commCode).append(";").append(this.poNo);
    	return sb.toString();
    }

    public String getCurrencyDesc(){
    	try {
    		VendorInfo ci = new VendorInfo();
			ci.setCommCode(this.getCommCode());
			ci.setState("0");
			VendorInfoMgr m = (VendorInfoMgr)BeanLocator.getInstance().getBean("vendorInfoMgr");
			ci = m.getVendorInfo(ci, false);
			return ci.getCurrencyDesc();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(VendorOrder.class);
			logger.error("catch Exception in getCurrencyDesc state=" + getState(), e);
			return null;
		}
    }

    @Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("commCode=").append(this.commCode).append(" ");
		sb.append("poNo=").append(this.poNo).append(" ");
		sb.append("poNoType=").append(this.poNoType).append(" ");
		sb.append("createTime=").append(this.createTime == null ? null : DateUtils.getFormatDate(this.createTime,null)).append(" ");
		sb.append("conName=").append(this.conName).append(" ");
		sb.append("conTel=").append(this.conTel).append(" ");
		sb.append("conFax=").append(this.conFax).append(" ");
		sb.append("companyBranch=").append(this.companyBranch).append(" ");
		sb.append("settlementType=").append(this.settlementType).append(" ");
		sb.append("settlementDay=").append(this.settlementDay).append(" ");
		sb.append("settlementDayDesc=").append(this.settlementDayDesc).append(" ");
		sb.append("receiveName=").append(this.receiveName).append(" ");
		sb.append("receiveAddress=").append(this.receiveAddress).append(" ");
		sb.append("receiveZip=").append(this.receiveZip).append(" ");
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("sales=").append(this.sales).append(" ");
		sb.append("isShowPrice=").append(this.isShowPrice).append(" ");
		sb.append("orderTime=").append(this.orderTime == null ? null : DateUtils.getFormatDate(this.orderTime,null)).append(" ");
		sb.append("period=").append(this.period).append(" ");
		sb.append("state=").append(this.state).append(" ");
		sb.append("activeState=").append(this.activeState).append(" ");
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
