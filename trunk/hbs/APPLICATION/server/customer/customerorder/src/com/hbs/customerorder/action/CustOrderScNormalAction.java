/**
 * 
 */
package com.hbs.customerorder.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerorder.constants.CustOrderConstants;
import com.hbs.customerorder.manager.CustOrderMgr;
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
	
	public String doSaveTemp()
	{
		try {
			logger.info("begin doSaveTemp " + custOrder);

			if (custOrder == null) {
				logger.info("��������");
				setErrorReason("��������");
				return ERROR;
			}
			if(StringUtils.isEmpty(custOrder.getPoNoType()))
				custOrder.setPoNoType("0");
			custOrder.setState("1");
			// TODO:listdata�����
			if(!CustOrderUtil.checkCommCode(custOrder)) {
				logger.info("�ͻ��������");
				setErrorReason("�ͻ��������");
				return ERROR;
			}
			CustOrderUtil.processListData(custOrder, this.getHttpServletRequest());
			CustOrderMgr mgr = (CustOrderMgr) BeanLocator.getInstance().getBean(CustOrderConstants.CUSTORDERMGR);
			CustomerOrder custOrder2 = null;
			try {
				custOrder2 = mgr.findCustomerOrderByBizKey(custOrder, false);
			} catch(Exception e) {
				
			}
			List<FieldErr> errs = CustOrderUtil.checkInputFields(custOrder);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}

			int ret = -1;
			try {
				if(custOrder2 == null)
					ret = mgr.saveTempCustomerOrder(custOrder);
				else
					ret = mgr.commitCustomerOrder(custOrder);
			} catch (Exception e) {
				logger.error("catch Exception in doSaveTemp", e);
			}
			
			if (ret != 0) {
				logger.info("��ʱ�������");
				setErrorReason("��ʱ�������");
				return ERROR;
			}
			setResult("state", "1");
			this.setAlertMsg("��ʱ����ɹ���");
			if (logger.isDebugEnabled())
				logger.debug("end doSaveTemp");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSaveTemp", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}
}
