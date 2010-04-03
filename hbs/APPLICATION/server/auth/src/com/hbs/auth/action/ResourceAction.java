package com.hbs.auth.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.hbs.auth.contants.AuthConstants;
import com.hbs.auth.manager.ActionMgr;
import com.hbs.auth.manager.ResourceMgr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.auth.pojo.Action;
import com.hbs.domain.auth.pojo.Resource;
import com.hbs.domain.auth.pojo.Role;

@SuppressWarnings("serial")
public class ResourceAction extends BaseAction {
	public static final String prefixAction = "a_";

	public static final String prefixResource = "r_";

	protected static final String childrenFieldName = "children";

	/**
	 * Resource比较器。根据ResourceId进行比较
	 */
	public static final Comparator<Resource> compResourceId = new Comparator<Resource>(){
		public int compare(Resource o1, Resource o2) {
			return o1.getResourceId() - o2.getResourceId();
		}
	};
	
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
	 * @action.input resource.*
	 * @action.result list List<Resource> + dynamicFields.actions (List<Action>)
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
				if(res.getActionsId() != null){
					Action action = new Action();
					action.setActionsId(res.getActionsId());
					res.setField("actions", amgr.listAction(action));
				}
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
	
	/**
	 * 查询资源
	 * @action.input resource.*
	 * @action.result list List<Resource> + dynamicFields.actions (List<Action>)
	 * @action.result count 数量
	 * @return
	 */
	public String doList2() {
		try {
			/*
			if(resource == null)
				resource = new Resource();
			setTotalCount(getRMgr().listResourceCount(resource));
			List<Resource> list = getRMgr().listResource(resource);
			ActionMgr amgr = (ActionMgr)getBean(AuthConstants.ACTION_MANAGER_NAME);
			Vector<Map<String, Object>> list2 = new Vector<Map<String, Object>>();
			for(Resource res : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", res.getResourceName());
				map.put("name", prefixResource + res.getResourceId().toString());
				if(res.getActionsId() != null) {
					Action action = new Action();
					action.setActionsId(res.getActionsId());
					List<Action> actionList = amgr.listAction(action);
					Vector<Map<String, Object>> actionList2 = new Vector<Map<String, Object>>();
					for(Action a : actionList) {
						Map<String, Object> actionMap = new HashMap<String, Object>();
						actionMap.put("title", a.getDescription());
						actionMap.put("value", prefixAction+a.getActionId());
						actionList2.add(actionMap);
					}
					map.put("list", actionList2);
					res.setField("actions", actionList);
				}
				list2.add(map);
			}
			//setResult("list", list);
			//setResult("count", getTotalCount());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title","资源操作列表");
			map.put("list", list2);
			setResult("list", new Map[] {map});
			*/
			
			///*
			if(!SUCCESS.equals(doMenu())){
				return ERROR;
			}
			ActionMgr amgr = (ActionMgr)getBean(AuthConstants.ACTION_MANAGER_NAME);
			List<Resource> menulist = (List<Resource>)this.getResult().get("menu");
			this.getResult().remove("menu");
			List<Map<String, Object>> list = new Vector<Map<String, Object>>();
			for(Resource r1 : menulist){
				Map<String, Object> m1 = new HashMap<String, Object>();
				m1.put("title", r1.getResourceName());
				list.add(m1);
				List<Map<String, Object>> list2 = new Vector<Map<String, Object>>();
				m1.put("list", list2);
				Object o = r1.getField("isLeaf");
				if(o != null && o.equals(true) )
					continue;
				o = r1.getField(childrenFieldName);
				if(o == null)
					continue;
				List<Resource> sublist = (List<Resource>)o;
				for(Resource r2 : sublist){
					Map<String, Object> m2 = new HashMap<String, Object>();
					m2.put("title", r2.getResourceName());
					m2.put("name", prefixResource + r2.getResourceId());
					if(r2.getActionsId() != null) {
						Action action = new Action();
						action.setActionsId(r2.getActionsId());
						List<Action> actionList = amgr.listAction(action);
						Vector<Map<String, Object>> actionList2 = new Vector<Map<String, Object>>();
						for(Action a : actionList) {
							Map<String, Object> actionMap = new HashMap<String, Object>();
							actionMap.put("title", a.getDescription());
							actionMap.put("value", prefixAction+a.getActionId());
							actionList2.add(actionMap);
						}
						m2.put("list", actionList2);
					}
					list2.add(m2);
				}
			}
			setResult("list", list);
			//*/
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doGetAllRes", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 获取菜单
	 * @action.result menu: List<Resource> + dnamicFields.children (List<Resource>) + dynamicFields.isLeaf (boolean)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doMenu() {
		try {
			// 过滤资源信息
			com.hbs.common.authfilter.User user = null;
			try {
				user = (com.hbs.common.authfilter.User)getSession().getAttribute("user");
			} catch (Exception e1) {
				logger.error("catch Exception in getuser" + e1);
			}
			resource = new Resource();
			HashMap<String,ArrayList<String>> resourceButtons = user == null ? null : user.getResourceButtons();
			if(resourceButtons == null)
				logger.error("resourceButtons == null");
			HashMap<Integer, Resource> idmap = new HashMap<Integer, Resource>();
			List<Resource> list = getRMgr().listResource(resource);
			List<Resource> list2 = new Vector<Resource>();
			if(list.size()>0)
			for(Resource res : list) {
				if(res == null || res.getResourceId() == null)
					continue;
				if(resourceButtons == null || 
						(resourceButtons.containsKey(res.getResourceId().toString())
						&& !res.getIsMenu().equals(0))
						){
					list2.add(res);
					idmap.put(res.getResourceId(), res);
				}
			}
			
			// 按照parent、id排序
			if(list2.size() > 0)
			Collections.sort(list2, new Comparator<Resource>(){
				private int getParent(Resource res) {
					try{
						return res.getParent().intValue();
						//return 0;
					}catch(Exception e){
						return 0;
					}
				}
				private int getId(Resource res){
					try{
						return res.getResourceId().intValue();
					}catch(Exception e){
						return 0;
					}
				}
				public int compare(Resource o1, Resource o2) {
					if(o1 == null) return -1;
					if(o2 == null) return 1;
					int i = getParent(o1) - getParent(o2);
					if(i == 0)
						return getId(o1) - getId(o2);
					else
						return i;
				}
				
			});
			
			// 整形
			if(list2.size() > 0)
			for(Resource res : list2){
				Integer i = res.getParent();
				if(i == null || i.equals(0))
					continue;
				List<Resource> sublist = (List<Resource>)idmap.get(i).getField(childrenFieldName);
				if(sublist == null) {
					sublist = new Vector<Resource>();
					idmap.get(i).setField(childrenFieldName, sublist);
				}
				sublist.add(res);
				//list2.remove(res); // 不能删除，否则在下一个for时会出异常
			}
			
			// 设置isLeaf
			if(list2.size() > 0)
			for(Iterator<Resource> it = list2.iterator();it.hasNext();){
				Resource res = it.next();
				List<Resource> sublist = (List<Resource>)res.getField(childrenFieldName);
				res.setField("isLeaf", sublist == null || sublist.size() == 0);
				if(res.getParent() == null || !res.getParent().equals(0))
					it.remove();
			}
			
			setResult("menu", list2);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doGetAllRes", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
