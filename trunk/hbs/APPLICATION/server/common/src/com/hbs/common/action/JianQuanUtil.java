/**
 * 
 */
package com.hbs.common.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;

/**
 * 鉴权相关函数
 * @author xyf
 *
 */
public class JianQuanUtil {
	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(JianQuanUtil.class);

	public static final String TypeCustState = "cust_state";
	public static final String TypeCustPartNoState = "cust_state";
	
	public static final String TypeCustOrderState = "custOrder_state";
	public static final String TypeCustOrderDetailState = "custOrderDetail_state";
	
	public static final String TypeVendorOrderState = "vendorOrder_state";
	public static final String TypeVendorOrderDetailState = "vendorOrderDetail_state";

	public static final String TypeWarehouseRecState = "warehouseRec_state";
	public static final String TypeWarehouseSendState = "warehouseSend_state";

	//public static final String TypeAdjustState = "adjust_state";

	
	/**
	 * 获取鉴权列表
	 * @param type	类别
	 * @param role	角色
	 * @return
	 */
	public static String[] getJQ(String type, String role) {
		try {
			if(logger.isDebugEnabled())	logger.debug("getJQ " + type + ";" + role);
			if(StringUtils.isEmpty(type) || StringUtils.isEmpty(role)) {
				return new String[0];
			}
			ConfigEncode ce = new ConfigEncode();
			ce.setEncodeType("jq_"+type);
			ce.setEncodeKey(role);
			ce = ConfigEncodeMgr.getConfigEncode(ce);
			if(ce == null)
				return new String[0];
			if(logger.isDebugEnabled())	logger.debug("getJQ return:" + ce.getEncodeValue());
			return ce.getEncodeValue().split(",");
		} catch (Exception e) {
			logger.error("catch exception in getJQ", e);
			return new String[0];
		}
	}
	
}
