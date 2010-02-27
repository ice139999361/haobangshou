/**
 * 
 */
package com.hbs.common.utils;

import com.hbs.common.action.base.Staff;

/**
 * 系统用户函数集合
 * @author xyf
 *
 */
public class StaffUtil {

	/**
	 * 根据ID获取用户信息
	 * @param id 用户ID
	 * @return
	 */
	public static Staff getStaffById(String id) {
		if (id == null)
			return null;
		// TODO:StaffUtil.getStaffById
		return new Staff(id, "user_" + id);
	}
}
