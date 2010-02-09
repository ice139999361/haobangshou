/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.manager.helper;

import com.hbs.common.utils.DateUtils;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;
import com.hbs.warehousesend.utils.AccountPreiodUtils;


/**
 * @author Administrator
 *
 */
public class SendPeriodHelper implements WarehouseSendHelper {

	/* (non-Javadoc)
	 * @see com.hbs.warehousesend.manager.helper.WarehouseSendHelper#getPeriod(com.hbs.domain.warehouse.pojo.WarehouseSendInfo)
	 */
	public String getPeriod(WarehouseSendInfo whrInfo) throws Exception {
		String period = null;
		AccountPreiod aPreiod = AccountPreiodUtils.getCustomerAccountPreiod(whrInfo.getCustCode());
		period = DateUtils.getDatePeriod(whrInfo.getCreateDate(), aPreiod.getPeriodStart(), aPreiod.getAccountPeriod());
		return period;
	}

	/* (non-Javadoc)
	 * @see com.hbs.warehousesend.manager.helper.WarehouseSendHelper#getPeriod(com.hbs.domain.warehouse.pojo.WarehouseSendDetail)
	 */
	public String getPeriod(WarehouseSendDetail whrInfoDetail) throws Exception {
		String period = null;
		AccountPreiod aPreiod = AccountPreiodUtils.getCustomerAccountPreiod(whrInfoDetail.getCustCode());
		period = DateUtils.getDatePeriod(whrInfoDetail.getCreateTime(), aPreiod.getPeriodStart(), aPreiod.getAccountPeriod());
		return period;
	}

}
