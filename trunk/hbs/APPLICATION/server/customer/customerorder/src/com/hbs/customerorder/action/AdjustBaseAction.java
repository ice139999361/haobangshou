package com.hbs.customerorder.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.customerorder.manager.AdjustMgr;
import com.hbs.domain.adjust.pojo.AdjustInfo;

@SuppressWarnings("serial")
public abstract class AdjustBaseAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(AdjustBaseAction.class);
	
	/**
	 * 返回角色名
	 * @return
	 */
	public abstract String getRoleName();
	/**
	 * 返回是否经理，内部使用。true：查看所有的已提交数据（不包括临时数据）；false：查看自己的数据（根据staffId）
	 * @return
	 */
	protected abstract boolean getIsManager();
	
	public static final String ADJUST_MGR = "adjustMgr";
	
	protected AdjustMgr getMgr(){
		return (AdjustMgr)getBean(ADJUST_MGR);
	}
	
	protected AdjustInfo adjustInfo;

	public AdjustInfo getAdjustInfo() {
		return adjustInfo;
	}
	public void setAdjustInfo(AdjustInfo adjustInfo) {
		this.adjustInfo = adjustInfo;
	}
	
	/**
	 * 查询
	 * @action.input adjustInfo.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList() {
		try {
			logger.debug("begin doList");
			if(adjustInfo == null)
				adjustInfo = new AdjustInfo();
			if(getIsManager())
				adjustInfo.setField("noState01", true);
			else
				setMyId(false);
			setPagination(adjustInfo);
			AdjustMgr mgr = getMgr();
			setResult("list", mgr.getAdjustInfoList(adjustInfo));
			setTotalCount(mgr.getAdjustInfoListCount(adjustInfo));
			setResult("count", getTotalCount());
			//setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeAdjustState, getRoleName()));
			logger.debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 获取调货信息
	 * @action.input adjustInfo.applySeqId 或 adjustInfo.*
	 * @action.result adjustInfo.*
	 * @return
	 */
	public String doGetInfo() {
		try{
			logger.debug("begin doGetInfo");
			if(!checkKeyFields()) {
				logger.debug("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			AdjustMgr mgr = getMgr();
			if(adjustInfo.getApplySeqId() == null){
				List<AdjustInfo> list = mgr.getAdjustInfoList(adjustInfo);
				if(list.size() > 0){
					adjustInfo = list.get(0);
				}else{
					adjustInfo = null;
				}
			}else {
				adjustInfo = mgr.getAdjustInfo(adjustInfo.getApplySeqId().toString());
			}
			if(adjustInfo == null || adjustInfo.getStaffId() == null) {
				logger.debug("没有找到");
				setErrorReason("没有找到");
				return ERROR;
			}else if(!getIsManager() && !adjustInfo.getStaffId().equals(getLoginStaff().getStaffId().toString())) {
				logger.debug("权限错误");
				setErrorReason("权限错误");
				return ERROR;
			}
			setResult("adjustInfo", adjustInfo);
			logger.debug("end doGetInfo");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	protected boolean checkKeyFields() {
		if(adjustInfo == null)
			return false;
		if(adjustInfo.getApplySeqId() != null)
			return true;
		if(StringUtils.isEmpty(adjustInfo.getPartNo()) 
				|| StringUtils.isEmpty(adjustInfo.getFromCustCode()) 
				|| StringUtils.isEmpty(adjustInfo.getToCustCode()))
			return false;
		return true;
	}
	
	protected void setMyId(boolean setName) throws Exception {
		adjustInfo.setStaffId(getLoginStaff().getStaffId().toString());
		if(setName)
			adjustInfo.setStaffName(getLoginStaff().getStaffName());
	}
}
