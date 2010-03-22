/**
 * 
 */
package com.hbs.invoice.action;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.invoice.pojo.PeriodSpecMemo;
import com.hbs.invoice.manager.PeriodSpecMemoMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class PeriodSpecMemoAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(PeriodSpecMemoAction.class);

	private PeriodSpecMemo specMemo;

	public PeriodSpecMemo getSpecMemo() {
		return specMemo;
	}

	public void setSpecMemo(PeriodSpecMemo specMemo) {
		this.specMemo = specMemo;
	}
	
	protected PeriodSpecMemoMgr getMgr() {
		return (PeriodSpecMemoMgr)getBean("periodSpecMemoMgr"); 
	}
	
	public String doGetInfo() {
		try {
			if(specMemo == null || StringUtils.isEmpty(specMemo.getCommCode())){
				logger.debug("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			setResult("specMemo", getMgr().getSepcMemo(specMemo.getCommCode()));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
	
	public String doSave() {
		try {
			if(specMemo == null || 
					StringUtils.isEmpty(specMemo.getCommCode()) ||
					StringUtils.isEmpty(specMemo.getMemo())){
				logger.debug("参数为空！");
				setErrorReason("参数为空！");
				return ERROR;
			}
			PeriodSpecMemoMgr mgr = getMgr();
			PeriodSpecMemo o = mgr.getSepcMemo(specMemo.getCommCode());
			if(o == null){
				specMemo.setCreateTime(new Date());
				specMemo.setStaffId(getLoginStaff().getStaffId().toString());
				specMemo.setStaffName(getLoginStaff().getStaffName());
				Integer commType = specMemo.getCommCode().substring(0, 2).compareToIgnoreCase("GC") == 0 ? 1 : 2;
				specMemo.setCommType(commType);
				mgr.saveSepcMemo(specMemo);
			}else{
				o.setMemo(specMemo.getMemo());
				mgr.updateSepcMemo(o);
			}

			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}
}
