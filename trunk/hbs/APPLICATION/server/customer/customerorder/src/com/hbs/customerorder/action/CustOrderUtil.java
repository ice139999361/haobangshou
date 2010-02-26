/**
 * 
 */
package com.hbs.customerorder.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.ListDataUtil;
import com.hbs.customerinfo.action.CustomerInfoUtil;
import com.hbs.customerinfo.constants.CustInfoConstants;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

/**
 * @author xyf
 *
 */
public class CustOrderUtil {
	
	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(CustOrderUtil.class);
	private static final String detailListName = "orderlist";
	private static final String detailListFields = "orderlistFields";

	/**
	 * 
	 * @param custOrder
	 * @param request
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	public static void processListData(CustomerOrder custOrder, HttpServletRequest request) throws Exception
	{
		String commCode = custOrder.getCommCode();
		String state = custOrder.getState();
		try {
			List<CustOrderDetail> list = ListDataUtil.splitIntoList(CustOrderDetail.class, 
				request.getParameterValues(detailListName), 
				request.getParameter(detailListFields).split(CustomerInfoUtil.fieldNameSplitter), 
				CustomerInfoUtil.splitter);
			Iterator<CustOrderDetail> it = list.iterator();
			while(it.hasNext())
			{
				CustOrderDetail info = it.next();
				if(info == null)
					continue;
				//TODO:CustOrderUtil.processListData
				//info.setCommCode(commCode);
				//info.setState(state);
				
			}
		} catch (Exception e) {
			logger.info("processListData处理detailList出错", e);
		}
	}

	/**
	 * 判断commCode是否存在
	 * @param commCode
	 * @return
	 */
	public static boolean checkCommCode(String commCode) {
		try {
			CustomerInfo custInfo = new CustomerInfo();
			custInfo.setCommCode(commCode);
			custInfo.setState("0");
			CustomerInfoMgr mgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(CustInfoConstants.CUSTINFOMGR);
			return null != mgr.getCustomerInfo(custInfo, false);
		} catch (Exception e) {
			logger.error("catch Exception in checkCommCode", e);
			return false;
		}
	}

	public static List<FieldErr> checkInputFields(CustomerOrder custOrder) {
		if(custOrder == null)
			return new ArrayList<FieldErr>();
		
		List<FieldErr> list = checkSelectFields(custOrder);
		if(list == null)
			list = new ArrayList<FieldErr>();
		
		String s;
		s = custOrder.getPoNo();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("PoNo","PoNo没有填写"));
		}
		//TODO:CustOrderUtil.checkInputFields
		return list;
	}

	private static List<FieldErr> checkSelectFields(CustomerOrder custOrder) {
		if(custOrder == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		try
		{
			String s;
			// TODO:CustOrderUtil.checkSelectFields
			int i;

		}
		catch(Exception e)
		{
			logger.info("checkSelectFields出错", e);
		}
		
		return list;
	}
	
	
}
