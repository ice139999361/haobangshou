/**
 * 
 */
package com.hbs.common.utils;

import com.hbs.domain.auth.pojo.Staff;

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
		return new Staff(Integer.parseInt(id), "user_" + id);
	}
}
