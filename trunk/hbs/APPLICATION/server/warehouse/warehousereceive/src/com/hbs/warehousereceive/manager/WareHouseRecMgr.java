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

import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;
import com.hbs.warehousereceive.manager.helper.WarehouseHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseRecMgr {
	private static final Logger logger = Logger.getLogger(WareHouseRecMgr.class);
	
	/**
	 * ������ⵥ��������ϸ��
	 * �����ж��Ƿ���ڣ������ڣ���insert����
	 * ����update���������״̬����ȷ���򷵻�-1
	 * @param whrInfo
	 * @param content
	 * @return 0  �ɹ�  -1 ״̬����ȷ����������
	 * @throws Exception
	 */
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
			List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
			if(null != detailList){//��ⵥ��ϸ����
				logger.debug("������ⵥ��Ϣ����ⵥ�´�����ⵥ��ϸ��������ⵥ��ϸ������Ϊ��" + detailList.size());
				WareHouseRecDetailMgr detailMgr = (WareHouseRecDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAILMGR);
				detailMgr.saveWareHouseRecDetailList(detailList, true, content);
			}
		}
		
		return ret;
	}
	
	public int cancelWareHouseRecInfo(WarehouseRecInfo whrInfo,String content) throws Exception{
		int ret =0;
		logger.debug("ȡ����ⵥ��Ϣ������Ĳ���Ϊ��" + whrInfo.toString());
		WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
		//���ݴ���Ĳ�������ҵ��������ѯ�Ƿ������ⵥ
		WarehouseRecInfo existInfo = whrInfoDao.findWarehouseRecInfo(whrInfo);
		if(null != existInfo){//����
			String state = existInfo.getState();
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//����ȡ��
				whrInfo.setState(WareHouseConstants.WAREHOUSE_REC_INFO_03);
				whrInfoDao.updateWarehouseRecInfoByState(whrInfo);
				WareHouseLogUtils.operLog(whrInfo.getOperId(), whrInfo.getOperStaff(), "ȡ��", "��Ӧ���������", whrInfo.getLogKey(), null, content);
				
				//��ⵥ��ϸ
				List<WarehouseRecDetail> detailList = whrInfo.getDetailList();
				if(null != detailList){//��ⵥ��ϸ����
					
				}
			}else{//״̬����ȷ������ȡ��
				logger.debug("ȡ����ⵥ��Ϣ,���ڵ���ⵥ״̬Ϊ��" + existInfo.getState() +"�޷���ȡ������");
				ret =-1;
			}
			
		}else{//Ҫȡ������ⵥ������
			logger.debug("ȡ����ⵥ��Ϣ,���ݴ���Ĳ������޷��ҵ���Ӧ����ⵥ���޷�ȡ����");
			ret = -2;
		}
		
		return ret;
	}
	
}
