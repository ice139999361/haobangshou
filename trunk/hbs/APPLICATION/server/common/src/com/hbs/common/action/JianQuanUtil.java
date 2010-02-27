/**
 * 
 */
package com.hbs.common.action;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;

/**
 * ��Ȩ��غ���
 * @author xyf
 *
 */
public class JianQuanUtil {

	public static final String TypeCustState = "cust_state";
	public static final String TypeCustPartNoState = "cust_state";
	public static final String TypeCustOrderState = "custOrder_state";
	public static final String TypeCustOrderDetailState = "custOrderDetail_state";
	
	public static final String TypeVendorOrderState = "vendorOrder_state";
	
	/**
	 * ��ȡ��Ȩ�б�
	 * @param type	���
	 * @param role	��ɫ
	 * @return
	 */
	public static String[] getJQ(String type, String role) {
		try {
			ConfigEncode ce = new ConfigEncode();
			ce.setEncodeType("jq_"+type);
			ce.setEncodeKey(role);
			ce = ConfigEncodeMgr.getConfigEncode(ce);
			if(ce == null)
				return new String[0];
			return ce.getEncodeValue().split(",");
		} catch (Exception e) {
			return new String[0];
		}
	}
	
}
