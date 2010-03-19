package com.hbs.vendororder.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.manager.VendorOrderMgr;

@SuppressWarnings("serial")
public class VendorOrderCgNormalAction extends VendorOrderBaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(VendorOrderBaseAction.class);

	@Override
	protected boolean getIsManager() {
		return false;
	}

	@Override
	public String getRoleName() {
		return "cgnormal";
	}

	/**
	 * 临时保存供应商订单
	 * @action.input	vendorOrder.*
	 * @action.result	poNo	插入时返回订单号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doSaveTemp() {
		try {
			logger.debug("begin doSaveTemp");
			if (vendorOrder == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			
			if(StringUtils.isEmpty(vendorOrder.getPoNoType()))
				vendorOrder.setPoNoType(VendorOrderConstants.VENDOR_PO_NO_TYPE_0);
			
			vendorOrder.setState(VendorOrderConstants.VENDOR_ORDER_STATE_01);
			if(vendorOrder.getCreateTime() == null)
				vendorOrder.setCreateTime(new Date());
			if(StringUtils.isEmpty(vendorOrder.getStaffId()))
			{
				vendorOrder.setStaffId(getLoginStaff().getStaffId().toString());
				vendorOrder.setStaffName(getLoginStaff().getStaffName());
			}
			
			// DONE:listdata、检查
			Map otherData = new HashMap();
			if(!VendorOrderUtil.checkCommCode(vendorOrder, otherData)) {
				logger.info("供应商编码错误！");
				setErrorReason("供应商编码错误！");
				return ERROR;
			}
			VendorOrderUtil.processListData(vendorOrder, this.getHttpServletRequest(), otherData);
			List<FieldErr> errs = VendorOrderUtil.checkInputFields(vendorOrder, otherData);
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			VendorOrderMgr mgr = (VendorOrderMgr)getBean(VENDOR_ORDER_MGR);
			boolean isNew = StringUtils.isEmpty(vendorOrder.getPoNo());
			int i = mgr.saveTempVendorOrder(vendorOrder, "");
			if(i != 0) {
				logger.info("临时保存失败！");
				setErrorReason("保存失败！");
				return ERROR;
			}
			if(isNew) {
				logger.info("doSaveTemp new poNo = " + vendorOrder.getPoNo());
				setResult("poNo", vendorOrder.getPoNo());
			}
			logger.debug("end doSaveTemp");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSaveTemp", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	/**
	 * 正式保存供应商订单
	 * @action.input	vendorOrder.*
	 * @action.result	poNo	插入时返回订单号
	 * @return
	 */
	public String doSave() {
		try {
			logger.debug("begin doSave");
			if (vendorOrder == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			boolean isNew = StringUtils.isEmpty((vendorOrder.getPoNo()));
			boolean isTemp = VendorOrderConstants.VENDOR_ORDER_STATE_01.equals(vendorOrder.getState());
			// 只有新记录或状态为临时保存才正确
			if(isNew || isTemp) {
				// 先执行临时保存
				String ret = doSaveTemp();
				if(ret.equals(SUCCESS)) {
					// 再获取数据，正式提交
					VendorOrderMgr mgr = (VendorOrderMgr)getBean(VENDOR_ORDER_MGR);
					vendorOrder = mgr.getVendorOrder(vendorOrder.getCommCode(), vendorOrder.getPoNo(), true);
					int i = mgr.commitVendorOrder(vendorOrder, null);
					if(i != 0) {
						logger.info("提交失败！");
						setErrorReason("提交失败！");
						return ERROR;
					} else {
						// 成功
					}
				} else {
					return ret;
				}
			} else {
				logger.info("状态错误！");
				setErrorReason("状态错误！");
				return ERROR;
			}
			logger.debug("end doSave");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSave", e);
			setErrorReason("内部错误");
			return ERROR;
		}
		
	}
}
