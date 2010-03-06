/**
 * 
 */
package com.hbs.common.utils;

import org.apache.commons.lang.StringUtils;

import com.hbs.auth.contants.AuthConstants;
import com.hbs.auth.manager.StaffMgr;
import com.hbs.common.springhelper.BeanLocator;
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
		if (StringUtils.isEmpty(id))
			return null;
		// DONE:StaffUtil.getStaffById
		StaffMgr mgr = (StaffMgr)BeanLocator.getInstance().getBean(AuthConstants.STAFF_MANAGER_NAME);
		return mgr.findStaff(id);
		//return new Staff(Integer.parseInt(id), "user_" + id);
	}
}
