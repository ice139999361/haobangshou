/**
 * 
 */
package com.hbs.vendororder.action;

import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;

/**
 * @author xyf
 * 
 */
public class VendorOrderDetailWithSelectType extends VendorOrderDetail {
	private String selectType;
	private String fromTo;

	public String getFromTo() {
		return fromTo;
	}

	public void setFromTo(String fromTo) {
		this.fromTo = fromTo;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
}
