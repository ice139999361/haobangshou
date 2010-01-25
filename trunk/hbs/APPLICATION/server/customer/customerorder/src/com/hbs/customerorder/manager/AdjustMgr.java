/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.manager;

import com.hbs.domain.adjust.pojo.AdjustInfo;


public class AdjustMgr {

	
	public int saveAdjustInfo(AdjustInfo adjustInfo) throws Exception{
		int ret =0;
		
		//获取申请的调货数量
		@SuppressWarnings("unused")
		Integer iMount = adjustInfo.getApplyAmount();
		//从仓库中获取被调货客户的库存数量，判断是否满足调货
		return ret;
	}
}
