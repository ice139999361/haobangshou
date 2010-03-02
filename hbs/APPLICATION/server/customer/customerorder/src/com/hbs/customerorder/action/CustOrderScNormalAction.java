/**
 * 
 */
package com.hbs.customerorder.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
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
			
			custOrder.setState("1");
			if(custOrder.getFristCreateTime() == null)
				custOrder.setFristCreateTime(new Date());
			if(StringUtils.isEmpty(custOrder.getStaffId()))
			{
				custOrder.setStaffId(getLoginStaff().getStaffId());
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
			
			CustOrderMgr mgr = (CustOrderMgr) BeanLocator.getInstance().getBean(CustOrderConstants.CUSTORDERMGR);
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
			setMyId();
			CustOrderMgr mgr = (CustOrderMgr) BeanLocator.getInstance().getBean(CustOrderConstants.CUSTORDERMGR);
			setResult("list", mgr.listCustomerOrder(custOrder));
			setTotalCount(mgr.listCustomerOrderCount(custOrder));
			setResult("count", getTotalCount());
			logger.debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	private void setMyId() throws Exception {
		custOrder.setStaffId(getLoginStaff().getStaffId());
	}
}
