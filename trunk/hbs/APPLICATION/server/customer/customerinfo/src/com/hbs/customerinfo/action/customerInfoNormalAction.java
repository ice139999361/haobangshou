/**
 * 客户信息Action
 */
package com.hbs.customerinfo.action;

import java.lang.reflect.Array;
import java.util.Map;

import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.common.josn.JSONException;
import com.hbs.common.josn.JSONUtil;

/**
 * 普通角色客户信息Action
 * @author xyf
 * @actions doList doGetInfo doSaveTemp doSave
 */
@SuppressWarnings("serial")
public class customerInfoNormalAction extends BaseAction {

	/**
	 * 联系人列表字符串参数名
	 */
	static final String contactListName1 = "contactList1";
	/**
	 * 收货人列表字符串参数名
	 */
	static final String contactListName2 = "contactList2";
	/**
	 * 银行列表字符串参数名
	 */
	static final String bankListName = "bankList";
	
	CustomerInfo custInfo;
	
	/**
	 * 获取客户信息
	 * @return 客户信息
	 */
	public CustomerInfo getCustInfo(){ return custInfo;}
	
	/**
	 * 设置客户信息
	 * @param a 客户信息
	 */
	public void setCustInfo(CustomerInfo a) { this.custInfo = a; }
	
	/**
	 * 处理上传的List数据。将String数组转换为List
	 */
	protected void processListData() throws Exception
	{
		// TODO: 处理上传的List数据
		try
		{
			String s = null;
			@SuppressWarnings("unused")
			Object o = null;
			
			Object aa = custInfo.getListContactInfo();
			s = this.getHttpServletRequest().getParameter(contactListName1);
			o = JSONUtil.deserialize(s);
			for(int i = 0; i < Array.getLength(o); i++)
			{
				Map item = (Map)Array.get(o, i);
				
			}
		}
		catch(JSONException e)
		{
			throw new Exception("列表数据格式错误！", e);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * 查询，限定自己能管理的客户信息。调用mgr.getCustomerInfoListByUser。
	 * @action.input custInfo.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList()
	{
		try
		{
			setPagination(custInfo);
			setMyId(false);
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			return SUCCESS;
		}
		catch(Exception e)
		{
			setErrorReason(e.getMessage(), e);
			return ERROR;
		}
	}
	
	/**
	 * 临时保存用户信息
	 * @action.input custInfo.*
	 * @return
	 */
	public String doSaveTemp()
	{
		try
		{
			processListData();
			int userid = 0;
			try
			{
				String s = custInfo.getStaffId();
				userid = Integer.parseInt(s);
			}
			catch(NumberFormatException e)
			{
				userid = 0;
			}
			if(userid == 0)
				setMyId(true);
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			int ret = mgr.saveTempCustomerInfo(custInfo);
			if(ret != 0)
			{
				setErrorReason("临时保存出错！");
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			setErrorReason(e.getMessage(), e);
            return ERROR;
		}
	}
	
	/**
	 * 保存用户信息，对于不同的状态，进行不同的操作
	 * @action.input custInfo.*
	 * @return
	 */
	public String doSave()
	{
		try
		{
			processListData();
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			int ret = mgr.updateCustomerInfo(custInfo, getLoginStaff().getStaffId(), getLoginStaff().getStaffName());
			if(ret != 0)
			{
				String s;
				switch(ret)
				{
				case 1:
					s = "无此状态！";
					break;
				case 2:
					s = "状态不正确！";
					break;
				default:
					s = "保存出错！";
				}
				setErrorReason(s);
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			setErrorReason(e.getMessage(), e);
            return ERROR;
		}
	}
	
	/**
	 * 获取客户详细信息
	 * @action.input 
	 *	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.result custInfo.*
	 * @return
	 */
	public String doGetInfo()
	{
		try
		{
			setMyId(false);
			CustomerInfoMgr mgr = new CustomerInfoMgr();
			custInfo = mgr.getCustomerInfo(custInfo, true);
			this.setResult("custInfo", custInfo);
			return SUCCESS;
		}
		catch(Exception e)
		{
			setErrorReason(e.getMessage(), e);
            return ERROR;
		}
	}
	
	/**
	 * 设置STAFF信息为当前用户信息
	 * @param setName 是否设置用户名。为true时设置staffName为当前用户的staffName；为false时设置staffName为null。
	 * 在查询时，为false；在增、改时，为true。
	 * @throws Exception
	 */
	protected void setMyId(boolean setName) throws Exception
	{
		custInfo.setStaffId(getLoginStaff().getStaffId());
		custInfo.setStaffName(setName ? getLoginStaff().getStaffName() : null);
	}
	
}
