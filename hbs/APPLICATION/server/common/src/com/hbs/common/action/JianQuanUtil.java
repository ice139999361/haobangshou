/**
 * 
 */
package com.hbs.common.action;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;

/**
 * 鉴权相关函数
 * @author xyf
 *
 */
public class JianQuanUtil {

	public static final String TypeCustState = "cust_state";
	
	/**
	 * 获取鉴权列表
	 * @param type	类别
	 * @param role	角色
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
