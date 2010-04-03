package com.hbs.common.authfilter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
