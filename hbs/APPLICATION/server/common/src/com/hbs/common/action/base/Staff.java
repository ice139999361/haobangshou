package com.hbs.common.action.base;

/**
 * ��¼�û������Ϣ
 * @author xyf
 *
 */
public class Staff {
	String staffId, staffName;
	
	/**
	 * ��ȡ�û�ID
	 * @return
	 */
	public String getStaffId() { return staffId;}
	
	/**
	 * ��ȡ�û���
	 * @return
	 */
	public String getStaffName() { return staffName; }
	
	/**
	 * �����û�ID
	 * @param a �û�ID
	 */
	public void setStaffId(String a) { staffId = a; }
	
	/**
	 * �����û���
	 * @param a �û���
	 */
	public void setStaffName(String a) { staffName = a; }
	
	public Staff(String id, String name)
	{
		staffId = id;
		staffName = name;
	}
}
