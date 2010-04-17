package com.hbs.domain.warehouse.pojo;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * WareHouseInfo对象.
 * @author hbs
 *
 */
public class WareHouseInfo extends BaseDomain{
    
    /**
     * 序列号.
     */
    private Integer houseSeqId;
    
    /**
     * 仓库类型，缺省为公司总库D.
     */
    private String houseType;
    
    /**
     * 仓库货物用途 常规货物 特定客户货物.
     */
    private String houseUse;
    
    /**
     * 供应商编码.
     */
    private String vendorCode;
    
    /**
     * 对应的采购单号.
     */
    private String poNo;
    
    /**
     * 本公司的物料编号.
     */
    private String partNo;
    
    /**
     * 物料描述.
     */
    private String pnDesc;
    
    /**
     * 供应商物料编号.
     */
    private String cpartNo;
    
    /**
     * 当HOUSE_USE为特定客户备货时必须填写CUST_CODE.
     */
    private String custCode;
    
    /**
     * 总库存数量，入库和出库需要做相应的加减.
     */
    private Integer totalAmount;
    
    /**
     * 锁定的库存.
     */
    private Integer lockAmount;
    
    /**
     * 可用库存，可用库存+锁定库存=总库存.
     */
    private Integer useAmount;
    
    /**
     * 最大库存数，0表示无限制.
     */
    private Integer maxAmount = 0;
    
    /**
     * 最小库存数，缺省为0.
     */
    private Integer minAmount =0;
    
    /**
     * 库存状态0---有可用库存 1---无可用库存.
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
    	String retStr ="未定义状态【" + getHouseType() +"】";
    	
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
    	String retStr ="未定义状态【" + getHouseUse() +"】";
    	
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
