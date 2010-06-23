/**
 * 客户信息Action
 */
package com.hbs.customerinfo.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.common.manager.syssequence.SysSequenceMgr;
import com.hbs.customerinfo.manager.CustContactMgr;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * 普通角色客户信息Action
 * @author xyf
 * @actions doList doGetInfo doSaveTemp doSave doDelete
 */
@SuppressWarnings("serial")
public class CustomerInfoNormalAction extends BaseAction {

	/**
	 * Manager名
	 */
	public static final String custInfoMgrName = "customerInfoMgr";

	/**
	 * logger.
	 */
	private static final Logger logger = Logger
			.getLogger(CustomerInfoNormalAction.class);

	public static final String roleName = "scnormal";
	
	CustomerInfo custInfo;

	/**
	 * 获取客户信息
	 * @return 客户信息
	 */
	public CustomerInfo getCustInfo() {
		return custInfo;
	}

	/**
	 * 设置客户信息
	 * @param a 客户信息
	 */
	public void setCustInfo(CustomerInfo a) {
		this.custInfo = a;
	}

	/**
	 * 查询，限定自己能管理的客户信息。调用mgr.getCustomerInfoListByUser。
	 * @action.input custInfo.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doList");
			if (custInfo == null) {
				custInfo = new CustomerInfo();
			}
			setPagination(custInfo);
			setMyId(false);
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			setResult("list", mgr.getCustomerInfoList(custInfo));
			setTotalCount(mgr.getCustomerInfoCount(custInfo));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			if (logger.isDebugEnabled())
				logger.debug("end doList");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doList.", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

				/**
				 * 临时保存用户信息
				 * @action.input custInfo.*
				 * @action.result	seqId	insert的id。如果没有insert操作，则没有这一项。
				 * @return
				 */
				public String doSaveTemp() {
					try {
						if (logger.isDebugEnabled())
							logger.debug("begin doSaveTemp");
			
						if (custInfo == null) {
							logger.info("参数错误！");
							setErrorReason("参数错误！");
							return ERROR;
						}
						
						custInfo.setState("1");
						if (CustomerInfoUtil.checkSetStaffId(custInfo))
							setMyId(true);
						CustomerInfoUtil.processListData(custInfo, this.getHttpServletRequest());
						
						List<FieldErr> errs = CustomerInfoUtil.checkInputFields(custInfo);
						if (!errs.isEmpty()) {
							String s = FieldErr.formFieldsErrString(errs);
							logger.info(s);
							setErrorReason(s);
							return ERROR;
						}
			
						CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
					    if(checkCustName(mgr,custInfo)){
					    	setErrorReason("您提交的客户资料存在着相同的客户简称或客户全称！不能提交");
							return ERROR;
					    }
			//			if(custInfo.getBaseSeqId() == null) {
			//				CustomerInfo cInfo = new CustomerInfo();
			//				cInfo.setCommCode(custInfo.getCommCode());
			//				Integer i = mgr.getCustomerInfoCount(cInfo);
			//				if(i == null || i.compareTo(0) > 0) {
			//					logger.error("客户编码重复！" + custInfo.getCommCode());
			//					setErrorReason("客户编码重复！");
			//					return ERROR;
			//				}
			//			}
						
						//CustomerInfo info2 = mgr.getCustomerInfo(custInfo, false);
						int ret;
			//			if (info2 != null)
			//				ret = mgr.updateCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName());
			//			else
			//				ret = mgr.saveTempCustomerInfo(custInfo);
			//			
			//			if (ret < 0) {
			//				logger.info("临时保存出错！");
			//				setErrorReason("临时保存出错！");
			//				return ERROR;
			//			}
						ret = mgr.saveTempCustomerInfo(custInfo);
						if(ret > 0) {
							this.setResult("seqId", ret);
							if (logger.isDebugEnabled()) logger.debug("seqId="+ret);
						}
						setResult("state", "1");
						this.setAlertMsg("临时保存成功！");
						if (logger.isDebugEnabled())
							logger.debug("end doSaveTemp");
						return SUCCESS;
					} catch (Exception e) {
						logger.error("catch Exception in doSaveTemp", e);
						setErrorReason(e.getMessage());
						return ERROR;
					}
				}

	/**
	 * 保存用户信息，对于不同的状态，进行不同的操作
	 * @action.input custInfo.*
	 * @action.result	seqId	insert的id。如果没有insert操作，则没有这一项。
	 * @return
	 */
	public String doSave() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doSave");

			if (custInfo == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			
			if (CustomerInfoUtil.checkSetStaffId(custInfo))
				setMyId(true);
			CustomerInfoUtil.processListData(custInfo, this
					.getHttpServletRequest());
			List<FieldErr> errs = CustomerInfoUtil.checkInputFields(custInfo);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}

			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);

			
		    if(checkCustName(mgr,custInfo)){
		    	setErrorReason("您提交的客户资料存在着相同的客户简称或客户全称！不能提交");
				return ERROR;
		    }
//			if(custInfo.getBaseSeqId() == null) {
//				CustomerInfo cInfo = new CustomerInfo();
//				cInfo.setCommCode(custInfo.getCommCode());
//				Integer i = mgr.getCustomerInfoCount(cInfo);
//				if(i == null || i.compareTo(0) > 0) {
//					logger.error("客户编码重复！" + custInfo.getCommCode());
//					setErrorReason("客户编码重复！");
//					return ERROR;
//				}
//			}
//			CustomerInfo info2 = mgr.getCustomerInfo(custInfo, false);
			int ret;
//			if (info2 != null) {
//				if(custInfo.getState().equals("1"))
//					ret = mgr.commitCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName());
//				else
//					ret = mgr.updateCustomerInfo(custInfo, getLoginStaff()
//						.getStaffId().toString(), getLoginStaff().getStaffName());
//			}
//			else {
//				//custInfo.setCreditRate("3");
//				//custInfo.setState("1");
//				ret = mgr.commitCustomerInfo(custInfo, getLoginStaff()
//						.getStaffId().toString(), getLoginStaff().getStaffName());
//			}
			ret = mgr.commitCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName());
			//int ret = mgr.commitCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName());
			if (ret < 0) {
				String s;
				switch (ret) {
				case -1:
					s = "无此状态！";
					break;
				case -2:
					s = "状态不正确！";
					break;
				default:
					s = "保存出错！";
					logger.info(s + " ret=" + ret);
					break;
				}
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			if(ret > 0) {
				this.setResult("seqId", ret);
				if (logger.isDebugEnabled()) logger.debug("seqId="+ret);
			}
			setResult("state", "2");
			this.setAlertMsg("提交成功！");
			if (logger.isDebugEnabled())
				logger.debug("end doSave");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doSave", e);
			setErrorReason(e.getMessage());
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
	public String doGetInfo() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doGetInfo");
			if (!CustomerInfoUtil.checkKeyFields(custInfo)) {
				logger.info("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			setMyId(false);
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			custInfo = CustomerInfoUtil.getCustomerInfo(mgr, custInfo);
			String id = getLoginStaff().getStaffId().toString();
			if(custInfo != null &&
					StringUtils.isNotEmpty(id) &&
					(id.equals(custInfo.getStaffId()) || id.equals(custInfo.getAssStaffId()))
				)
			{
				this.setResult("custInfo", custInfo);
				if (logger.isDebugEnabled())
					logger.debug("end doGetInfo");
				return SUCCESS;
			}
			this.setErrorReason("权限错误");
			return ERROR;
		} catch (Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 删除审批不通过的数据
	 * 
	 * @action.input custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @return
	 */
	public String doDelete() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doGetInfo");
			if (!CustomerInfoUtil.checkKeyFields(custInfo)) {
				logger.info("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			setMyId(false);
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			custInfo = mgr.getCustomerInfo(custInfo, true);
			if (custInfo == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			try {
				if (3 != Integer.parseInt(custInfo.getState())) {
					logger.info("状态不正确！");
					setErrorReason("状态不正确！");
					return ERROR;
				}
			} catch (Exception e) {
				logger.info("状态不正确！" + e.getMessage());
				setErrorReason("状态不正确！");
				return ERROR;
			}
			if(!getLoginStaff().getStaffId().toString().equals(custInfo.getStaffId())){
				logger.info("权限错误！");
				setErrorReason("权限错误！");
				return ERROR;
			}
			int i = mgr.deleteCustomerInfo(custInfo, getLoginStaff().getStaffId().toString(), 
					getLoginStaff().getStaffName(), 
					getHttpServletRequest().getParameter("delDesc"));
			switch (i) {
			case 0:
				this.setAlertMsg("删除成功！");
				return SUCCESS;
			case 2:
				logger.info("状态不正确！");
				setErrorReason("状态不正确！");
				return ERROR;
			default:
				logger.info("删除出错！" + " ret=" + i);
				setErrorReason("删除出错！");
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 获取正式数据中的收货人信息
	 * @action.input	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doGetConsigneeList()
	{
		try
		{
			if (logger.isDebugEnabled())
				logger.debug("begin doGetConsigneeList");
			setResult("list", getPersonList("2"));
			logger.debug("end doGetConsigneeList");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doGetConsigneeList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 获取正式数据中的联系人信息
	 * @action.input	custInfo.baseSeqId 或 (custInfo.commCode + custInfo.state)
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doGetContactList()
	{
		try
		{
			if (logger.isDebugEnabled())
				logger.debug("begin doGetContactList");
			setResult("list", getPersonList("1"));
			logger.debug("end doGetContactList");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doGetContactList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 获取正式数据中的联系人信息，doGetConsigneeList、doGetContactList的具体操作函数
	 * @param type 联系人类别。1：联系人；2：收货人
	 * @return
	 */
	protected List<ContactInfo> getPersonList(String type) throws Exception
	{
		if (!CustomerInfoUtil.checkKeyFields(custInfo)) {
			logger.info("参数为空！");
			setErrorReason("参数为空！");
			return null;
		}
		CustContactMgr mgr = (CustContactMgr)getBean("custContactMgr");
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setState("0");
		contactInfo.setConType(type);
		Integer id = custInfo.getBaseSeqId();
		if(id != null)
			contactInfo.setBaseSeqId(id.toString());
		else
			contactInfo.setCommCode(custInfo.getCommCode());
		
		return mgr.listContactInfo(contactInfo);
	}
	
	/**
	 * 根据seqId获取联系人信息
	 * @action.input seqId
	 * @action.result contactInfo.*
	 * @return
	 */
	public String doGetContactInfoById() {
		try {
			String s = this.getHttpServletRequest().getParameter("seqId");
			logger.debug("begin doGetContactInfoById " + s);
			if(StringUtils.isEmpty(s)) {
				logger.info("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			CustContactMgr mgr = (CustContactMgr)getBean("custContactMgr");
			setResult("contactInfo", mgr.getContactInfo(s));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doGetContactInfoById", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 检查编码
	 * @action.input value
	 * @return
	 */
	public String doCheckCommCode() {
		try {
			String s = this.getHttpServletRequest().getParameter("value");
			logger.debug("begin doCheckCommCode " + s);
			if(StringUtils.isEmpty(s)) {
				logger.info("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			custInfo = new CustomerInfo();
			custInfo.setCommCode(s);
			CustomerInfoMgr mgr = (CustomerInfoMgr)getBean(custInfoMgrName);
			Integer i = mgr.getCustomerInfoCount(custInfo);
			if(i == null || i.compareTo(0) > 0 ){
				logger.debug("编码重复！");
				setErrorReason("编码重复！");
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doCheckCommCode", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	
	/**
	 * 获取新的客户编码
	 * @return
	 */
	public String doGetNewCustomerCode(){
		try {
			
			logger.debug("begin doGetNewCustomerCode" );
			
			
			String vendorCode = SysSequenceMgr.getCode(SysSequenceMgr.GC_CODE);
			setResult("commCode",vendorCode);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doGetNewCustomerCode", e);
			setErrorReason("无法获取新的客户编码！请检查配置！");
			return ERROR;
		}
	}
	/**
	 * 设置STAFF信息为当前用户信息
	 * 
	 * @param setName 是否设置用户名。
	 * 	为true时设置staffName为当前用户的staffName；为false时设置staffName为null。
	 * 	在查询时，为false；在增、改时，为true。
	 * @throws Exception
	 */
	protected void setMyId(boolean setName) throws Exception {
		custInfo.setStaffId(getLoginStaff().getStaffId().toString());
		custInfo.setStaffName(setName ? getLoginStaff().getStaffName() : null);
	}

	/**
	 * 查询所有客户简称和名称
	 * @param mgr
	 * @param info
	 * @return
	 * @throws Exception
	 */
	private boolean checkCustName(CustomerInfoMgr mgr , CustomerInfo info) throws Exception{
		boolean ret = false;
		
		
		Integer icount = mgr.getCustomerInfoCheckCount(info);
		if(icount != null && icount.intValue() >0){
			ret = true;
		}
		return ret;
	}

}
