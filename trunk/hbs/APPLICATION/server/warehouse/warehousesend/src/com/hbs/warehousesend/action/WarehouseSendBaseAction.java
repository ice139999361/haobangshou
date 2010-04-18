/**
 * 
 */
package com.hbs.warehousesend.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;

import com.hbs.warehousesend.manager.WareHouseSendDetailMgr;
import com.hbs.warehousesend.manager.WareHouseSendMgr;

/**
 * 仓库入库单基类，实现doList、doGetInfo
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public abstract class WarehouseSendBaseAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(WarehouseSendBaseAction.class);

	public static final String WAREHOUSE_SEND_MGR = "wareHouseSendMgr";
	
	public static final String WAREHOUSE_SEND_DETAIL_MGR ="wareHouseSendDetailMgr";
	
	WareHouseSendDetailMgr getMgrDetail(){
		return (WareHouseSendDetailMgr)getBean(WAREHOUSE_SEND_DETAIL_MGR);
	}
	
	WarehouseSendInfo warehouseSend;
	WarehouseSendDetail warehouseSendDetail;
	

	public WarehouseSendDetail getWarehouseSendDetail() {
		return warehouseSendDetail;
	}

	public void setWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) {
		this.warehouseSendDetail = warehouseSendDetail;
	}

	public WarehouseSendInfo getWarehouseSend() {
		return warehouseSend;
	}

	public void setWarehouseSend(WarehouseSendInfo warehouseSend) {
		this.warehouseSend = warehouseSend;
	}

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
	
	protected WareHouseSendMgr getMgr() {
		return (WareHouseSendMgr)getBean(WAREHOUSE_SEND_MGR);
	}
	
	/**
	 * 查询
	 * @action.input warehouseSend.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList() {
		try {
			logger.debug("begin doList");
			if(warehouseSend == null)
				warehouseSend = new WarehouseSendInfo();
			if(getIsManager())
				warehouseSend.setField("noState01", true);
			else
				setMyId(false);
			setPagination(warehouseSend);
			WareHouseSendMgr mgr = getMgr();
			setResult("list", mgr.listWarehouseSendInfo(warehouseSend));
			setTotalCount(mgr.listWarehouseSendInfoCount(warehouseSend, false));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeWarehouseSendState, getRoleName()));
			logger.debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 供财务管理发票使用，新增发票时选择出货单
	 * @return
	 */
	public String doListDetail() {
		try {
			logger.debug("begin doListDetail");
			if(warehouseSendDetail == null){
				warehouseSendDetail = new WarehouseSendDetail();
			}
			warehouseSendDetail.setField("notInState", "'01','03'");			
			setPagination(warehouseSendDetail);
			WareHouseSendDetailMgr mgr = getMgrDetail();
			setResult("list", mgr.listWarehouseSendDetail(warehouseSendDetail));
			setTotalCount(mgr.listWarehouseSendDetailCount(warehouseSendDetail));
			setResult("count", getTotalCount());			
			logger.debug("end doListDetail");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doListDetail", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 供财务管理对账使用
	 * @return
	 */
	public String doListFinanceDetail() {
		try {
			logger.debug("begin doListFinanceDetail");
			if(warehouseSendDetail == null){
				warehouseSendDetail = new WarehouseSendDetail();
			}
			
			if(StringUtils.isEmpty(warehouseSendDetail.getCustCode())){
				setErrorReason("请输入客户编码!");
				return ERROR;
			}
			String startTime = (String)warehouseSendDetail.getDynamicFields().get("beginPoNoDate");
			String endTime =   (String)warehouseSendDetail.getDynamicFields().get("endPoNoDate");
			String finacePeriod = warehouseSendDetail.getFinancePeriod();
			
			if( StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime) && StringUtils.isEmpty(finacePeriod)){
				setErrorReason("请输入时间范围或账期!");
				return ERROR;
			}
			if(StringUtils.isNotEmpty(warehouseSendDetail.getFinancePeriod())){
				warehouseSendDetail.setField("lessFinancePeriod", warehouseSendDetail.getFinancePeriod());
			}
			warehouseSendDetail.setField("notInState", "'01','03'");			
			WareHouseSendDetailMgr mgr = getMgrDetail();
			setResult("list", mgr.listWarehouseSendDetail(warehouseSendDetail));
			
					
			logger.debug("end doListFinanceDetail");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doListFinanceDetail", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 获取出库单信息
	 * @action.input warehouseSend.sendPoNo + warehouseSend.custCode
	 * @action.result warehouseSend.*
	 * @return
	 */
	public String doGetInfo() {
		try{
			logger.debug("begin doGetInfo");
			boolean success = getWarehouseSendByKey(true);
			setResult("warehouseSend", warehouseSend);
			logger.debug("end doGetInfo");
			return success ? SUCCESS : ERROR;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 根据关键字段获取信息
	 * @throws Exception
	 */
	protected boolean getWarehouseSendByKey(boolean isDetail) throws Exception {
		boolean isTrue = WarehouseSendUtil.checkKeyFields(warehouseSend);
		logger.debug("isTrue =" + isTrue);
		if(!isTrue) {
			logger.debug(warehouseSend.toString());
			logger.debug("参数为空！");
			setErrorReason("参数为空！");
			return false;
		}
		WareHouseSendMgr mgr = getMgr();
		
		warehouseSend = mgr.getWarehouseSendInfo(warehouseSend, isDetail);
		if(warehouseSend == null || StringUtils.isEmpty(warehouseSend.getOperId())) {
			logger.debug("没有找到");
			setErrorReason("没有查询到相应的供应商订单记录！");
			return false;
		}else if(!getIsManager() && !warehouseSend.getOperId().equals(getLoginStaff().getStaffId().toString())) {
			logger.debug("权限错误");
			setErrorReason("权限错误");
			return false;
		}
		return true;
	}

	protected void setMyId(boolean setName) throws Exception {
		warehouseSend.setOperId(getLoginStaff().getStaffId().toString());
		if(setName)
			warehouseSend.setOperStaff(getLoginStaff().getStaffName());
	}
}
