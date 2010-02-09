/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousesend.manager;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WarehouseSendInfoDao;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;
import com.hbs.warehouse.common.constants.WareHouseConstants;

/**
 * @author Administrator
 *
 */
public class WareHouseSendMgr {
	private static final Logger logger = Logger.getLogger(WareHouseSendMgr.class);
	
	public int saveWareHouseSendInfo(WarehouseSendInfo sendInfo,String content) throws Exception{
		int ret =0;
		String st = sendInfo.getState();
		String sendPoNo = sendInfo.getSendPoNo();
		if(st.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){
			logger.debug("������ⵥ��Ϣ������Ĳ���Ϊ��" + sendInfo.toString());
		}else{
			logger.debug("ȷ�ϳ��ⵥ��Ϣ������Ĳ���Ϊ��" + sendInfo.toString());
		}
		WarehouseSendInfoDao whInfoDao = (WarehouseSendInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_SEND_INFO_DAO);
		
		if(null == sendPoNo){//�����ڳ������ĵ���
			
		}else{//���ڳ������ĵ���
			//��ѯ��������Ϣ
			WarehouseSendInfo existInfo = whInfoDao.findWarehouseSendInfo(sendInfo);
			if(null == existInfo){//�޴˳�������Ϣ
				logger.debug("���ݴ���ĳ��ⵥ��Ϣ���ڿ����޷���ѯ��������ִ�в���!");
				ret = -2;
			}else{
				String state = existInfo.getState();
				if(state.equals(WareHouseConstants.WAREHOUSE_SEND_INFO_01)){//״̬Ϊ��ʱ״̬�������޸�
					
				}else{//״̬Ϊ����ʱ״̬�������޸�
					logger.debug("���ݴ���ĳ��ⵥ��Ϣ����ѯ��״̬Ϊ��" + state +"�������޸Ĳ�����");
					ret =-1;
				}
			}
			
		}
		return ret;
	}
}
