/**
 * system ��hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.vendororder.manager.helper;

import com.hbs.domain.vendor.order.pojo.VendorOrder;

public interface VendorOrderState {

	/**
	 * ���ݹ�Ӧ�̶�����ȡ������������
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public String getPeriod(VendorOrder vOrder)throws Exception;
	
	/**
	 * �����ύ�Ķ������ͻ�ȡ������״̬
	 * @param vOrder
	 * @return
	 * @throws Exception
	 */
	public String getCommitState(/*VendorOrder vOrder*/)throws Exception;
}
