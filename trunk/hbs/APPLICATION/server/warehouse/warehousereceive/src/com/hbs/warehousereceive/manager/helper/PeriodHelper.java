/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousereceive.manager.helper;

import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;
import com.hbs.warehousereceive.utils.AccountPreiodUtils;


/**
 * @author Administrator
 *
 */
public class PeriodHelper implements WarehouseHelper {

	/* (non-Javadoc)
	 * @see com.hbs.warehousereceive.manager.helper.WarehouseHelper#getPeriod(com.hbs.domain.warehouse.pojo.WarehouseRecInfo)
	 */
	public String getPeriod(WarehouseRecInfo whrInfo) throws Exception {
		String period = null;
		AccountPreiod aPreiod = AccountPreiodUtils.getVendorAccountPreiod(whrInfo.getVendorCode());
		period = DateUtils.getDatePeriod(whrInfo.getApplyDate(), aPreiod.getPeriodStart(), aPreiod.getAccountPeriod());
		return period;
	}

	/* (non-Javadoc)
	 * @see com.hbs.warehousereceive.manager.helper.WarehouseHelper#getPeriod(com.hbs.domain.warehouse.pojo.WarehouseRecDetail)
	 */
	public String getPeriod(WarehouseRecDetail whrInfoDetail) throws Exception {
		String period = null;
		AccountPreiod aPreiod = AccountPreiodUtils.getVendorAccountPreiod(whrInfoDetail.getVendorCode());
		period = DateUtils.getDatePeriod(whrInfoDetail.getApplyDate(), aPreiod.getPeriodStart(), aPreiod.getAccountPeriod());
		return period;
	}

}
