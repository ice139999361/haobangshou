/**
 * 
 */
package com.hbs.common.utils;

import com.hbs.common.action.base.Staff;

/**
 * ϵͳ�û���������
 * @author xyf
 *
 */
public class StaffUtil {

	/**
	 * ����ID��ȡ�û���Ϣ
	 * @param id �û�ID
	 * @return
	 */
	public static Staff getStaffById(String id) {
		if (id == null)
			return null;
		// TODO:StaffUtil.getStaffById
		return new Staff(id, "user_" + id);
	}
}
