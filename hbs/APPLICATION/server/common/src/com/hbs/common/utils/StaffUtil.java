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
		if (StringUtils.isEmpty(id))
			return null;
		// DONE:StaffUtil.getStaffById
		StaffMgr mgr = (StaffMgr)BeanLocator.getInstance().getBean(AuthConstants.STAFF_MANAGER_NAME);
		return mgr.findStaff(id);
		//return new Staff(Integer.parseInt(id), "user_" + id);
	}
}
