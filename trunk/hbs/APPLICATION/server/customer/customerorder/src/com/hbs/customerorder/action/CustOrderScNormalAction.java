/**
 * 
 */
package com.hbs.customerorder.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.CustOrderMgr;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class CustOrderScNormalAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(CustOrderScNormalAction.class);

	public static final String roleName = "scnormal";

	CustomerOrder custOrder;

	/**
	 * @return the custOrder
	 */
	public CustomerOrder getCustOrder() {
		return custOrder;
	}

	/**
	 * @param custOrder the custOrder to set
	 */
	public void setCustOrder(CustomerOrder custOrder) {
		this.custOrder = custOrder;
	}
	
	/**
	 * 临时保存
	 * @action.input custOrder.*
	 * @action.result 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doSaveTemp()
	{
		try {
			logger.debug("begin doSaveTemp " + custOrder);

			if (custOrder == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			
			if(StringUtils.isEmpty(custOrder.getPoNoType()))
				custOrder.setPoNoType("0");
			if(StringUtils.isEmpty(custOrder.getActiveState()))
				custOrder.setActiveState(CustOrderConstants.ORDER_ACTIVE_STATE);
			custOrder.setState(CustOrderConstants.ORDER_STATE_01);
			if(custOrder.getFristCreateTime() == null)
				custOrder.setFristCreateTime(new Date());
			if(StringUtils.isEmpty(custOrder.getStaffId()))
			{
				custOrder.setStaffId(getLoginStaff().getStaffId().toString());
				custOrder.setStaffName(getLoginStaff().getStaffName());
			}
			
			// DONE:listdata、检查
			Map otherData = new HashMap();
			if(!CustOrderUtil.checkCommCode(custOrder, otherData)) {
				logger.info("客户编码错误！");
				setErrorReason("客户编码错误！");
				return ERROR;
			}
			CustOrderUtil.processListData(custOrder, this.getHttpServletRequest(), otherData);
			List<FieldErr> errs = CustOrderUtil.checkInputFields(custOrder, otherData);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			if(null != custOrder.getOrderDetailList() && custOrder.getOrderDetailList().size() > 0) {
				Map<String, Boolean> map = new HashMap<String, Boolean>();
				for(CustOrderDetail info : custOrder.getOrderDetailList()) {
					String s = "本公司物料：" + info.getPartNo() + "，特殊备注：" + info.getSpecDesc() + "，重复";
					if(map.containsKey(s)){
						errs.add(new FieldErr("",s));
					}
					map.put(s, true);
				}
				if(errs.size() > 0){
					String s = FieldErr.formFieldsErrString(errs);
					logger.info(s);
					setErrorReason(s);
					return ERROR;
				}
			}
			
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			CustomerOrder custOrder2 = null;
			try {
				custOrder2 = mgr.findCustomerOrderByBizKey(custOrder, false);
			} catch(Exception e) {
				
			}
			int ret = -1;
			try {
				for(CustOrderDetail info : custOrder.getOrderDetailList()) {
					info.setDeliveryAmount(0);
					info.setSelfDeliveryAmount(0);
					info.setCommDeliveryAmount(0);
				}
				if(custOrder2 == null)
				{
					ret = mgr.saveTempCustomerOrder(custOrder);
				}
				else
					ret = mgr.updateTempCustomerOrder(custOrder);
			} catch (Exception e) {
				logger.error("catch Exception in doSaveTemp", e);
			}
			
			if (ret != 0) {
				logger.info("临时保存出错！");
				setErrorReason("临时保存出错！");
				return ERROR;
			}
			setResult("state", "1");
			this.setAlertMsg("临时保存成功！");
			if (logger.isDebugEnabled())
				logger.debug("end doSaveTemp");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSaveTemp", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	

	/**
	 * 正式提交
	 * @action.input custOrder.*
	 * @action.result 
	 * @return
	 */
	public String doCommit() {
		try{
			logger.debug("begin doCommit");
			String ret = doSaveTemp();
			if(ret.equals(SUCCESS)) {
				CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
				int i = mgr.commitCustomerOrder(custOrder);
				if(i != 0) {
					logger.info("提交出错！");
					setErrorReason("提交出错！");
					return ERROR;
				}
			}
			setAlertMsg("提交成功！");
			logger.debug("end doCommit");
			return ret;
		}catch(Exception e) {
			logger.error("catch Exception in doCommit", e);
			setErrorReason("内部错误");
			return ERROR;
		}		
	}
	
	/**
	 * 查询，限定自己能管理的客户范围。
	 * @action.input custOrder.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList() {
		try {
			logger.debug("begin doList");
			if(custOrder == null)
				custOrder = new CustomerOrder();
			setPagination(custOrder);
			setMyId(false);
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			setResult("list", mgr.listCustomerOrder(custOrder));
			setTotalCount(mgr.listCustomerOrderCount(custOrder));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustOrderState, roleName));
			logger.debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 获取客户订单信息
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.result custOrder.*
	 * @return
	 */
	public String doGetInfo() {
		try{
			logger.debug("begin doGetInfo");
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			CustomerOrder custOrder2 = findCustOrder(mgr, true);
			if(custOrder2 == null)
				return ERROR;
			setResult("custOrder", custOrder2);
			logger.debug("end doGetInfo");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 取消客户订单
	 * @action.input custOrder.commCode + custOrder.poNo
	 * @action.input memo	取消说明
	 * @action.result custOrder.*
	 * @return
	 */
	public String doCancel() {
		try{
			logger.debug("begin doCancel");
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			custOrder = findCustOrder(mgr, true);
			if(custOrder == null)
				return ERROR;
			setMyId(true);
			String cancelContent = this.getHttpServletRequest().getParameter("memo");
			int i = mgr.cancelCustOrder(custOrder, cancelContent);
			if(i != 0) {
				logger.info("取消订单出错！");
				setErrorReason("取消订单出错！");
				return ERROR;
			}
			logger.debug("end doCancel");
			return SUCCESS;
		}catch(Exception e) {
			logger.error("catch Exception in doCancel", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 切换ActiveState
	 * @action.input custOrder.*
	 * @action.input memo
	 * @return
	 */
	public String doControlActiveState() {
		try {
			CustOrderMgr mgr = (CustOrderMgr)getBean(CustOrderConstants.CUSTORDERMGR);
			CustomerOrder custOrder2 = findCustOrder(mgr, true);
			if(custOrder2 == null)
				return ERROR;
			setMyId(true);
			int i = mgr.controlActiveState(custOrder2, this.getHttpServletRequest().getParameter("memo"));
			if(i != 0) {
				logger.error("提交出错！ ret = " + i);
				setErrorReason("提交出错！");
				return ERROR;
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doControlActiveState", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**获取客户订单信息，并判断权限
	 * @param mgr
	 * @param isAll	是否包含详情信息
	 * @return	null表示错误，已经将错误信息调用setErrorReason。
	 * @throws Exception
	 */
	private CustomerOrder findCustOrder(CustOrderMgr mgr, boolean isAll) throws Exception {
		if(custOrder == null
				|| StringUtils.isEmpty(custOrder.getCommCode()) 
				|| StringUtils.isEmpty(custOrder.getPoNo())) {
			logger.debug("参数为空！");
			setErrorReason("参数为空！");
			return null;
		}
		CustomerOrder custOrder2 = mgr.findCustomerOrderByBizKey(custOrder, isAll);
		if(custOrder2 == null) {
			logger.debug("没有找到");
			setErrorReason("没有找到");
			return null;
		} else if(!getLoginStaff().getStaffId().toString().equals(custOrder2.getStaffId())) {
			logger.debug("权限错误");
			setErrorReason("权限错误");
			return null;
		}
		return custOrder2;
	}

	private void setMyId(boolean setName) throws Exception {
		custOrder.setStaffId(getLoginStaff().getStaffId().toString());
		if(setName)
			custOrder.setStaffName(getLoginStaff().getStaffName());
	}
}
