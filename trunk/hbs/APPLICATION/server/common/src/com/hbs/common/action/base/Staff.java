package com.hbs.common.action.base;

/**
 * 登录用户身份信息
 * @author xyf
 *
 */
public class Staff {
	String staffId, staffName;
	
	/**
	 * 获取用户ID
	 * @return
	 */
	public String getStaffId() { return staffId;}
	
	/**
	 * 获取用户名
	 * @return
	 */
	public String getStaffName() { return staffName; }
	
	/**
	 * 设置用户ID
	 * @param a 用户ID
	 */
	public void setStaffId(String a) { staffId = a; }
	
	/**
	 * 设置用户名
	 * @param a 用户名
	 */
	public void setStaffName(String a) { staffName = a; }
	
	public Staff(String id, String name)
	{
		staffId = id;
		staffName = name;
	}
}
