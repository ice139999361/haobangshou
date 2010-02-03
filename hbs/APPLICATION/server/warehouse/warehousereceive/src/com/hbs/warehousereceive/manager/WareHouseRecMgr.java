/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousereceive.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WarehouseRecInfoDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;
import com.hbs.vendororder.constants.VendorOrderConstants;
import com.hbs.vendororder.manager.helper.VendorOrderState;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;
import com.hbs.warehousereceive.manager.helper.WarehouseHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseRecMgr {
	private static final Logger logger = Logger.getLogger(WareHouseRecMgr.class);
	
	
	public int saveWareHouseRecInfo(WarehouseRecInfo whrInfo,String content) throws Exception{
		int ret =0;
		logger.debug("������ⵥ��Ϣ������Ĳ���Ϊ��" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		//���ݴ���Ĳ�������ҵ��������ѯ�Ƿ������ⵥ
		WarehouseRecInfo existInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(null == existInfo){//�����ڣ���Ҫinsert����
			//�������ڣ������ϵ���ʱ��Ϊ��׼����
			WarehouseHelper helper =(WarehouseHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING + whrInfo.getPoNoType() + whrInfo.getSettlementType());
			whrInfo.setPeriod(helper.getPeriod(whrInfo));
			whrInfo.setState(WareHouseConstants.WAREHOUSE_REC_INFO_01);
			whrInfoDao.insertWarehouseRecInfo(whrInfo);
			WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), "����", "��Ӧ���������", whrInfo.getLogKey(), null, content);
//			//��ⵥ��ϸ����
//			List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
//			if(null != detailList){//��ⵥ��ϸ����
//				
//			}
			
		}else{//����,���޸Ĳ���
			//�ж���ⵥ״̬
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//�����޸�
				whrInfoDao.updateWarehouseRecInfo(whrInfo);
				WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), "�޸�", "��Ӧ���������", whrInfo.getLogKey(), null, content);
			}else{//״̬����ȷ�������޸�
				ret = -1;
			}
		}
		if(ret ==0){//������ⵥ��ϸ
			
		}
		
		return ret;
	}
	
	
	
}
