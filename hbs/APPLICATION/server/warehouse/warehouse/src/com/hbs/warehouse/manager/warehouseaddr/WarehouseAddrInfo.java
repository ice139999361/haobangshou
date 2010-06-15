package com.hbs.warehouse.manager.warehouseaddr;

import com.hbs.domain.common.pojo.base.BaseDomain;

public class WarehouseAddrInfo extends BaseDomain {
	private String id;
	private String name;
	private String address;
	private String conName;
	private String zip;
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getIsPrimary() {
		return "1".equals(id) ? "0" : "1";
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("id=").append(id).append(" ");
		sb.append("name=").append(name).append(" ");
		sb.append("conName=").append(conName).append(" ");
		sb.append("address=").append(address).append(" ");
		sb.append("zip=").append(zip).append(" ");
		sb.append("isPrimary=").append(getIsPrimary()).append(" ");
		return sb.toString();
	}
}
