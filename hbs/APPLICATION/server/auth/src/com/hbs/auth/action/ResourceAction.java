package com.hbs.auth.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.auth.contants.AuthConstants;
import com.hbs.auth.manager.ActionMgr;
import com.hbs.auth.manager.ResourceMgr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.auth.pojo.Action;
import com.hbs.domain.auth.pojo.Resource;

@SuppressWarnings("serial")
public class ResourceAction extends BaseAction {
	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(ResourceAction.class);

	private Resource resource;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	protected ResourceMgr getRMgr() {
		return (ResourceMgr)getBean(AuthConstants.RESOURCE_MANAGER_NAME);
	}
	
	/**
	 * 查询资源
	 * @action.result list List<Resource> + dynamicFields.actions(List<Action>)
	 * @action.result count 数量
	 * @return
	 */
	public String doList() {
		try {
			if(resource == null)
				resource = new Resource();
			setTotalCount(getRMgr().listResourceCount(resource));
			List<Resource> list = getRMgr().listResource(resource);
			ActionMgr amgr = (ActionMgr)getBean(AuthConstants.ACTION_MANAGER_NAME);
			for(Resource res : list) {
				Action action = new Action();
				action.setActionId(res.getActionsId().toString());
				res.setField("actions", amgr.listAction(action));
			}
			setResult("list", list);
			setResult("count", getTotalCount());
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doGetAllRes", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
