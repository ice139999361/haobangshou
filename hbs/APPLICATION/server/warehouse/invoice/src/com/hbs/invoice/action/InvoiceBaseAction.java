/**
 * 
 */
package com.hbs.invoice.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public abstract class InvoiceBaseAction extends BaseAction {

	protected InvoiceInfo invoice;

	public InvoiceInfo getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceInfo invoice) {
		this.invoice = invoice;
	}
	
	protected abstract Logger getLogger();
	
	/**
	 * 根据id获取发票信息，调用适当的mgr
	 * @return
	 * @throws Exception
	 */
	protected abstract InvoiceInfo getInvoiceByIdAbstract() throws Exception;
	
	/**
	 * 查询发票，调用适当的mgr
	 * @throws Exception
	 */
	protected abstract List<InvoiceInfo> listInvoiceAbstract() throws Exception;
	
	/**
	 * 查询发票数量，调用适当的mgr
	 * @throws Exception
	 */
	protected abstract int listInvoiceCountAbstract() throws Exception;

	/**
	 * 新增发票
	 * @return
	 * @throws Exception
	 */
	protected abstract int saveInvoiceAbstract() throws Exception;

	/**
	 * 更新发票
	 * @return
	 * @throws Exception
	 */
	protected abstract int updateInvoiceAbstract() throws Exception;

	/**
	 * 删除发票
	 * @return
	 * @throws Exception
	 */
	protected abstract int deleteInvoiceAbstract() throws Exception;

	/**
	 * 返回是否经理，内部使用。true：查看所有的已提交数据（不包括临时数据）；false：查看自己的数据（根据staffId）
	 * @return
	 */
	protected abstract boolean getIsManager();

	/**
	 * 返回角色名
	 * @return
	 */
	public abstract String getRoleName();
	
	/**
	 * 获取发票信息
	 * @return
	 * @throws Exception
	 */
	protected InvoiceInfo getInvoiceInfo() throws Exception {
		if(invoice == null || !InvoiceUtil.checkKeyFields(invoice)){
			getLogger().debug("参数为空！");
			setErrorReason("参数为空！");
			return null;
		}
		InvoiceInfo invoice2 = null;
		if(invoice.getInvoiceSeqId() != null)
			invoice2 = getInvoiceByIdAbstract();
		else
			invoice2 = getInvoiceByKey();
		
		return invoice2;
	}
	
	/**
	 * 根据关键字段获取发票信息，调用适当的mgr
	 * @return
	 * @throws Exception
	 */
	protected InvoiceInfo getInvoiceByKey() throws Exception {
		List<InvoiceInfo> list = listInvoiceAbstract();
		if(list == null || list.size() <= 1){
			getLogger().info("没有找到");
			setErrorReason("没有找到");
			return null;
		}
		invoice = list.get(0);
		return getInvoiceByIdAbstract();
	}

	/**
	 * 获取发票信息
	 * @action.input invoice.*
	 * @action.result invoice.*
	 * @return
	 */
	public String doGetInfo() {
		try {
			invoice = getInvoiceInfo();
			if(invoice == null)
				return ERROR;
			else if(!getIsManager() && !invoice.getStaffId().equals(getLoginStaff().getStaffId().toString())) {
				getLogger().debug("权限错误");
				setErrorReason("权限错误");
				return ERROR;
			}
			setResult("invoice", invoice);
			
			return SUCCESS;
		} catch (Exception e) {
			getLogger().error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 查询
	 * @action.input invoice.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList() {
		try {
			getLogger().debug("begin doList");
			if(invoice == null)
				invoice = new InvoiceInfo();
			if(getIsManager())
				invoice.setField("noState01", true);
			else
				setMyId(false);
			setPagination(invoice);
			setResult("list", listInvoiceAbstract());
			setTotalCount(listInvoiceCountAbstract());
			setResult("count", getTotalCount());
			//setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeWarehouseSendState, getRoleName()));
			getLogger().debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			getLogger().error("catch Exception in doList", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 保存
	 * @action.input invoice.*
	 * @return
	 */
	public String doSave() {
		try {
			getLogger().debug("begin doSave");
			if (invoice == null) {
				getLogger().info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			if(StringUtils.isEmpty(invoice.getStaffId()))
				setMyId(true);
			if(invoice.getCreateTime() == null)
				invoice.setCreateTime(new Date());
			
			List<FieldErr> errs = InvoiceUtil.checkInputFields(invoice, null);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				getLogger().info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			int i;
			if(invoice.getInvoiceSeqId() == null){
				i = saveInvoiceAbstract();
				if(i > 0){
					invoice.setInvoiceSeqId(i);
					setResult("seqId", i);
					i = 0;
				}else if(i == 0)
					i = -999;
			}else
				i = updateInvoiceAbstract();
			
			if(i != 0) {
				String s = "保存失败！";
				getLogger().info(s + " i=" + i);
				setErrorReason(s);
				return ERROR;
			}
			
			// DONE：doSave
			getLogger().debug("end doSave");
			return SUCCESS;
		} catch(Exception e) {
			getLogger().error("catch Exception in doSave", e);
			setErrorReason("内部错误");
			return ERROR;
		}		
	}
	
	/**
	 * 删除发票
	 * @action.input invoice.*
	 * @return
	 */
	public String doDelete() {
		getLogger().debug("进入删除发票管理！");
		try{
			if(!InvoiceUtil.checkKeyFields(invoice)){
				getLogger().info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			int i = deleteInvoiceAbstract();
			if(i != 0) {
				getLogger().info("删除失败！");
				setErrorReason("删除失败！");
				return ERROR;
			}
			return SUCCESS;
		} catch(Exception e) {
			getLogger().error("catch Exception in doDelete", e);
			setErrorReason("内部错误");
			return ERROR;
		}	
	}
	
	protected void setMyId(boolean setName) throws Exception {
		invoice.setStaffId(getLoginStaff().getStaffId().toString());
		if(setName)
			invoice.setStaffName(getLoginStaff().getStaffName());
	}
}
