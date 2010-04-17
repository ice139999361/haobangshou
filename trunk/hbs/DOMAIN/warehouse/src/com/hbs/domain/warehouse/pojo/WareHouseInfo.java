package com.hbs.domain.warehouse.pojo;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * WareHouseInfo����.
 * @author hbs
 *
 */
public class WareHouseInfo extends BaseDomain{
    
    /**
     * ���к�.
     */
    private Integer houseSeqId;
    
    /**
     * �ֿ����ͣ�ȱʡΪ��˾�ܿ�D.
     */
    private String houseType;
    
    /**
     * �ֿ������; ������� �ض��ͻ�����.
     */
    private String houseUse;
    
    /**
     * ��Ӧ�̱���.
     */
    private String vendorCode;
    
    /**
     * ��Ӧ�Ĳɹ�����.
     */
    private String poNo;
    
    /**
     * ����˾�����ϱ��.
     */
    private String partNo;
    
    /**
     * ��������.
     */
    private String pnDesc;
    
    /**
     * ��Ӧ�����ϱ��.
     */
    private String cpartNo;
    
    /**
     * ��HOUSE_USEΪ�ض��ͻ�����ʱ������дCUST_CODE.
     */
    private String custCode;
    
    /**
     * �ܿ�����������ͳ�����Ҫ����Ӧ�ļӼ�.
     */
    private Integer totalAmount;
    
    /**
     * �����Ŀ��.
     */
    private Integer lockAmount;
    
    /**
     * ���ÿ�棬���ÿ��+�������=�ܿ��.
     */
    private Integer useAmount;
    
    /**
     * ���������0��ʾ������.
     */
    private Integer maxAmount = 0;
    
    /**
     * ��С�������ȱʡΪ0.
     */
    private Integer minAmount =0;
    
    /**
     * ���״̬0---�п��ÿ�� 1---�޿��ÿ��.
     */
    private String state="0";

    
    
    public Integer getHouseSeqId() {
        return this.houseSeqId;
    }	
  
    public void setHouseSeqId(Integer houseSeqId) {
        this.houseSeqId = houseSeqId;
    }
    
    public String getHouseType() {
        return this.houseType;
    }
    
    public String getHouseTypeDesc() {
    	String retStr ="δ����״̬��" + getHouseType() +"��";
    	
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getHouseType());
    	ceParam.setEncodeType("WAREHOUSE_TYPE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
    }
  
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    
    public String getHouseUse() {
        return this.houseUse;
    }	
  
    public String getHouseUseDesc() {
    	String retStr ="δ����״̬��" + getHouseUse() +"��";
    	
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getHouseUse());
    	ceParam.setEncodeType("WAREHOUSE_USE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
    }
    public void setHouseUse(String houseUse) {
        this.houseUse = houseUse;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    public String getPoNo() {
        return this.poNo;
    }	
  
    public void setPoNo(String poNo) {
        this.poNo = poNo;
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
    
    public String getCpartNo() {
        return this.cpartNo;
    }	
  
    public void setCpartNo(String cpartNo) {
        this.cpartNo = cpartNo;
    }
    
    public String getCustCode() {
        return this.custCode;
    }	
  
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }
    
    public Integer getTotalAmount() {
        return this.totalAmount;
    }	
  
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Integer getLockAmount() {
        return this.lockAmount;
    }	
  
    public void setLockAmount(Integer lockAmount) {
        this.lockAmount = lockAmount;
    }
    
    public Integer getUseAmount() {
        return this.useAmount;
    }	
  
    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }
    
    public Integer getMaxAmount() {
        return this.maxAmount;
    }	
  
    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }
    
    public Integer getMinAmount() {
        return this.minAmount;
    }	
  
    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }
    
    public String getState() {
        return this.state;
    }	
  
    public void setState(String state) {
        this.state = state;
    }

    public String getLogKey(){
    	return this.houseSeqId.toString();
    }
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.houseSeqId != null){
			sb.append("houseSeqId=").append(this.houseSeqId).append(" ");
		}
		if(this.houseType != null){
			sb.append("houseType=").append(this.houseType).append(" ");
		}
		if(this.houseUse != null){
			sb.append("houseUse=").append(this.houseUse).append(" ");
		}
		if(this.custCode != null){
			sb.append("custCode=").append(this.custCode).append(" ");
		}
		if(this.vendorCode != null){
			sb.append("vendorCode=").append(this.vendorCode).append(" ");
		}
//		if(this.poNo != null){
//			sb.append("poNo=").append(this.poNo).append(" ");
//		}
		if(this.partNo != null){
			sb.append("partNo=").append(this.partNo).append(" ");
		}
		if(this.pnDesc != null){
			sb.append("pnDesc=").append(this.pnDesc).append(" ");
		}
		if(this.cpartNo != null){
			sb.append("cpartNo=").append(this.cpartNo).append(" ");
		}
		if(this.totalAmount != null){
			sb.append("totalAmount=").append(this.totalAmount).append(" ");
		}
		if(this.lockAmount != null){
			sb.append("lockAmount=").append(this.lockAmount).append(" ");
		}
		if(this.useAmount != null){
			sb.append("useAmount=").append(this.useAmount).append(" ");
		}
		if(this.state != null){
			sb.append("state=").append(this.state).append(" ");
		}
		
		return sb.toString();
	}

}
