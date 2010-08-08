package com.hbs.domain.customer.order_lock.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * @author xyf
 *
 */
public class CustOrderLockInfo extends BaseDomain {

	private String custCode;
	private String custPoNo;
	private Integer custOrderSeqId;
	private String houseType;
	private String houseUse;
	private Integer lockAmount;
	private String custPartNo;
	private String partNo;
	private String specDesc;
	private String vendorCode;
	
	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCustPoNo() {
		return custPoNo;
	}

	public void setCustPoNo(String custPoNo) {
		this.custPoNo = custPoNo;
	}

	public Integer getCustOrderSeqId() {
		return custOrderSeqId;
	}

	public void setCustOrderSeqId(Integer custOrderSeqId) {
		this.custOrderSeqId = custOrderSeqId;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getHouseUse() {
		return houseUse;
	}

	public void setHouseUse(String houseUse) {
		this.houseUse = houseUse;
	}

	public String getCustPartNo() {
		return custPartNo;
	}

	public void setCustPartNo(String custPartNo) {
		this.custPartNo = custPartNo;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public Integer getLockAmount() {
		return lockAmount;
	}

	public void setLockAmount(Integer lockAmount) {
		this.lockAmount = lockAmount;
	}

	public String getSpecDesc() {
		return specDesc;
	}

	public void setSpecDesc(String specDesc) {
		this.specDesc = specDesc;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.custCode != null){
			sb.append("custCode=").append(this.custCode).append(" ");
		}
		if(this.custPoNo != null){
			sb.append("custPoNo=").append(this.custPoNo).append(" ");
		}
		if(this.custOrderSeqId != null){
			sb.append("custOrderSeqId=").append(this.custOrderSeqId).append(" ");
		}
		if(this.houseType != null){
			sb.append("houseType=").append(this.houseType).append(" ");
		}
		if(this.houseUse != null){
			sb.append("houseUse=").append(this.houseUse).append(" ");
		}
		if(this.lockAmount != null){
			sb.append("lockAmount=").append(this.lockAmount).append(" ");
		}
		if(this.custPartNo != null){
			sb.append("custPartNo=").append(this.custPartNo).append(" ");
		}
		if(this.partNo != null){
			sb.append("partNo=").append(this.partNo).append(" ");
		}
		if(this.specDesc != null){
			sb.append("specDesc=").append(this.specDesc).append(" ");
		}
		if(this.vendorCode != null){
			sb.append("vendorCode=").append(this.vendorCode).append(" ");
		}
		
		return sb.toString();
	}
}
