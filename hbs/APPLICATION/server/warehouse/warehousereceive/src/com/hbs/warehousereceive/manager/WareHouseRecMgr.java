/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousereceive.manager;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WarehouseRecInfoDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;
import com.hbs.warehouse.common.constants.WareHouseConstants;

/**
 * @author Administrator
 *
 */
public class WareHouseRecMgr {
	private static final Logger logger = Logger.getLogger(WareHouseRecMgr.class);
	
	
	public int saveWareHouseRecInfo(WarehouseRecInfo whrInfo) throws Exception{
		int ret =0;
		logger.debug("������ⵥ��Ϣ������Ĳ���Ϊ��" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		//���ݴ���Ĳ�������ҵ��������ѯ�Ƿ������ⵥ
		WarehouseRecInfo existInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(null == existInfo){//�����ڣ���Ҫinsert����
			
		}else{//����,���޸Ĳ���
			//�ж���ⵥ״̬
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//�����޸�
				
			}else{//״̬����ȷ�������޸�
				ret = -1;
			}
		}
		
		return ret;
	}
	
}
