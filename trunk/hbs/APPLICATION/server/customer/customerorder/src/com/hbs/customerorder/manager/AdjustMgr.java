/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.manager;

import com.hbs.domain.adjust.pojo.AdjustInfo;


public class AdjustMgr {

	
	public int saveAdjustInfo(AdjustInfo adjustInfo) throws Exception{
		int ret =0;
		
		//��ȡ����ĵ�������
		@SuppressWarnings("unused")
		Integer iMount = adjustInfo.getApplyAmount();
		//�Ӳֿ��л�ȡ�������ͻ��Ŀ���������ж��Ƿ��������
		return ret;
	}
}
