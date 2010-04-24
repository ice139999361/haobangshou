package com.hbs.common.authfilter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hbs.domain.auth.pojo.Resource;

/**
 * 
 * @author tony.chen
 *
 */

public class User {

	private Integer staffId;
	private String starffName;
	private String userAccount;
	private HashMap<String,ArrayList<String>> resourceButtons;
	private HashMap<String,String> actionNames;
	private List<String> roleList;
	private List<Resource> resList;//��ǰ�û���������Դ�˵�
	
		
	public List<Resource> getResList() {
		return resList;
	}

	public void setResList(List<Resource> resList) {
		this.resList = resList;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStarffName() {
		return starffName;
	}
	
	public void setStarffName(String starffName) {
		this.starffName = starffName;
	}
	
	public String getUserAccount() {
		return userAccount;
	}
	
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	public HashMap<String, ArrayList<String>> getResourceButtons() {
		return resourceButtons;
	}
	
	public void setResourceButtons(
			HashMap<String, ArrayList<String>> resourceButtons) {
		this.resourceButtons = resourceButtons;
	}
	
	public HashMap<String, String> getActionNames() {
		return actionNames;
	}
	
	public void setActionNames(HashMap<String, String> actionNames) {
		this.actionNames = actionNames;
	}
	
	public User(){}
  	
}
