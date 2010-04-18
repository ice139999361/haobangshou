/**
 * 
 */
package com.hbs.customerorder.action;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.customerinfo.action.CustomerInfoNormalAction;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.customerorder.manager.AdjustMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.product.pojo.CompanyPartNo;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.product.constants.ProductConstants;
import com.hbs.product.manager.CompanyPartNoMgr;
import com.hbs.vendorinfo.action.VendorInfoNormalAction;
import com.hbs.vendorinfo.manager.VendorInfoMgr;

/**
 * 采购员调货action
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class AdjustCgNormalAction extends AdjustBaseAction {
	private static final Logger logger = Logger.getLogger(AdjustCgNormalAction.class);
	
	@Override
	protected boolean getIsManager() {
		return false;
	}

	@Override
	public String getRoleName() {
		return "cgnormal";
	}

	/**
	 * 保存
	 * @action.input adjustInfo.*
	 * @action.result	applySeqId 新记录的id，可能不出现
	 * @return
	 */
	public String doSave() {
		try {
			logger.debug("begin doSave");
			if (adjustInfo == null) {
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			
			if(StringUtils.isEmpty(adjustInfo.getStaffId()))
				setMyId(true);
			if(adjustInfo.getApplyDate() == null)
				adjustInfo.setApplyDate(new Date());
			
			List<FieldErr> errs = checkInputFields();
			if (!errs.isEmpty()) {
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			
			AdjustMgr mgr = getMgr();
			boolean isNew = adjustInfo.getApplySeqId() == null;
			int i = mgr.saveAdjustInfo(adjustInfo);
			if(i <= 0) {
				String s;
				switch(i){
				case -1:
					s = "库存中不存在能够被调货的库存信息！无法执行调货!";
					break;
				case -2:
					s = "库存中的被调货库存物料数量小于需要的调货数量，无法执行调货！";
					break;
				default:
					s = "保存失败！";
				}
				logger.info(s + " ret = " + i);
				setErrorReason(s);
				return ERROR;
			}
			if(isNew) {
				logger.info("doSave new applySeqId = " + adjustInfo.getApplySeqId());
				setResult("applySeqId", adjustInfo.getApplySeqId());
			}
			logger.debug("end doSave");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doSave", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	protected List<FieldErr> checkInputFields() {
		List<FieldErr> errs = new Vector<FieldErr>();
		if(adjustInfo == null){
			errs.add(new FieldErr("", "参数错误！"));
			return errs;
		}
		CustomerInfoMgr custMgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
		CustomerInfo custInfo = new CustomerInfo();
		CustomerInfo custInfo2;
		custInfo.setState("0");
		if(StringUtils.isEmpty(adjustInfo.getFromCustCode())){
			errs.add(new FieldErr("fromCustCode", "fromCustCode没有填写！"));
		}else{
			custInfo.setCommCode(adjustInfo.getFromCustCode());
			try {
				custInfo2 = custMgr.getCustomerInfo(custInfo, false);
				if(custInfo2 != null)
					adjustInfo.setFromCustName(custInfo2.getShortName());
				else
					errs.add(new FieldErr("fromCustCode", "fromCustCode错误！"));
			} catch (Exception e) {
				logger.debug("catch Exception in checkInputFields FromCustCode", e);
				errs.add(new FieldErr("fromCustCode", "fromCustCode错误！"));
			}
		}
		if(StringUtils.isEmpty(adjustInfo.getToCustCode())){
			errs.add(new FieldErr("toCustCode", "toCustCode没有填写！"));
		}else{
			custInfo.setCommCode(adjustInfo.getToCustCode());
			try {
				custInfo2 = custMgr.getCustomerInfo(custInfo, false);
				if(custInfo2 != null)
					adjustInfo.setToCustName(custInfo2.getShortName());
				else
					errs.add(new FieldErr("toCustCode", "toCustCode错误！"));
			} catch (Exception e) {
				logger.debug("catch Exception in checkInputFields ToCustCode", e);
				errs.add(new FieldErr("toCustCode", "toCustCode错误！"));
			}
		}
		if(StringUtils.isEmpty(adjustInfo.getPartNo())){
			errs.add(new FieldErr("partNo", "partNo没有填写！"));
		}else{
			CompanyPartNoMgr pnMgr = (CompanyPartNoMgr)getBean(ProductConstants.COMPANY_PART_NO_MGR);
			try {
				CompanyPartNo pn = pnMgr.getCompanyPartNo(adjustInfo.getPartNo());
				if(pn != null)
					adjustInfo.setPnDesc(pn.getPnDesc());
				else
					errs.add(new FieldErr("partNo", "partNo错误！"));
			} catch (Exception e) {
				logger.debug("catch Exception in checkInputFields PartNo", e);
				errs.add(new FieldErr("partNo", "partNo错误！"));
			}
		}
		if(StringUtils.isEmpty(adjustInfo.getVendorCode())){
			errs.add(new FieldErr("vendorCode", "vendorCode没有填写！"));
		}else{
			VendorInfoMgr vendorMgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			VendorInfo vendorInfo = new VendorInfo();
			vendorInfo.setState("0");
			vendorInfo.setCommCode(adjustInfo.getVendorCode());
			try {
				vendorInfo = vendorMgr.getVendorInfo(vendorInfo, false);
				if(vendorInfo != null)
					adjustInfo.setShortName(vendorInfo.getShortName());
				else
					errs.add(new FieldErr("vendorCode", "vendorCode错误！"));
			} catch (Exception e) {
				logger.debug("catch Exception in checkInputFields VendorCode", e);
				errs.add(new FieldErr("vendorCode", "vendorCode错误！"));
			}
		}
		if(StringUtils.isEmpty(adjustInfo.getHouseType())){
			errs.add(new FieldErr("houseType", "houseType没有填写！"));
		}else{
			ConfigEncode ce = new ConfigEncode();
			ce.setEncodeType("WAREHOUSE_TYPE");
			ce.setEncodeKey(adjustInfo.getHouseType());
			ce = ConfigEncodeMgr.getConfigEncode(ce);
			if(ce != null){
				
			}else{
				errs.add(new FieldErr("houseType", "houseType错误！"));
			}
		}
		if(adjustInfo.getApplyAmount() == null || adjustInfo.getApplyAmount() <= 0){
			errs.add(new FieldErr("applyAmount", "applyAmount错误！"));
		}
		
		return errs;
	}
}
