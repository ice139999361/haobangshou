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
import com.hbs.common.utils.OrderCalUtils;
import com.hbs.domain.warehouse.dao.WarehouseRecDetailDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;
import com.hbs.warehousereceive.manager.helper.WarehouseHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseRecDetailMgr {
	private static final Logger logger = Logger.getLogger(WareHouseRecDetailMgr.class);
	
	/**
	 * ������ⵥ��ϸ������ʱ���жϿ����Ƿ���ڣ�������insert����
	 * ���� update������update������״̬������ 01 ��ʱ״̬���ܱ����޸�
	 * @param detail
	 * @param isflowRec �Ƿ��������ⵥͬʱ����
	 * @param content   �������
	 * @return  >= 0 �ɹ�  -1 ��״̬����ϸ�������޸�
	 * @throws Exception
	 */
	public int saveWareHouseRecDetail(WarehouseRecDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("����������ϸ������Ĳ���Ϊ:" + detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		WarehouseRecDetail existDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		if(null == existDetail){//�����ڣ�insert������
			logger.debug("���ݿ����޴���ⵥ��ϸ��insert������");
			//������
			detail.setCurrMoney(OrderCalUtils.calOrderMoney(detail.getPrice(), detail.getIsTax(), detail.getTaxRate(), detail.getAmount()));
			//��������
			String period = detail.getPeriod();
			if(period == null){
				WarehouseHelper helper =(WarehouseHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING + detail.getPoNoType() + detail.getSettlementType());
				detail.setPeriod(helper.getPeriod(detail));
				detail.setFinancePeriod(detail.getPeriod());				
			}
			Integer id = detailDao.insertWarehouseRecDetail(detail);
			detail.setRecDetailSeqId(id);
			ret = id;
			WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "����", "��ⵥ��ϸ", detail.getLogKey(), null, content);
		}else{//��update����
			String state = existDetail.getState();
			logger.debug("���ݿ��д�����ⵥ��ϸ��update��������ϸ״̬Ϊ��" + state);
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//״̬Ϊ��ʱ�������޸�
				//������
				detail.setCurrMoney(OrderCalUtils.calOrderMoney(detail.getPrice(), detail.getIsTax(), detail.getTaxRate(), detail.getAmount()));
				detail.setRecDetailSeqId(existDetail.getRecDetailSeqId());
				detailDao.updateWarehouseRecDetail(detail);
				WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), "�޸�", "��ⵥ��ϸ", detail.getLogKey(), null, content);
			}else{//״̬����ȷ���������޸�
				logger.debug("���ݿ��д�����ⵥ��ϸ��update������״̬����ȷ���������޸ģ�");
				ret = -1;
			}
			ret = detail.getRecDetailSeqId();
		}
		return ret;
	}
	
	/**
	 * ����������ⵥ��ϸ�����õ������淽��
	 * @param detailList
	 * @param isflowRec  �Ƿ��������������
	 * @param content  ��ע����¼��־
	 * @return  0 �ɹ� 
	 * @throws Exception
	 */
	public int saveWareHouseRecDetailList(List<WarehouseRecDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("����������ⵥ��ϸ����ϸ������Ϊ:" + detailList.size());
		for(WarehouseRecDetail detail : detailList){
			Integer i = saveWareHouseRecDetail(detail,isflowRec,content);
			logger.debug("����������ⵥ��ϸ,����Ľ��Ϊ��" + i);
		}
		return ret;
	}
	
	
	public int cancelWareHouseRecDetail(WarehouseRecDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("ȡ����ⵥ��ϸ��Ϣ,����Ĳ���Ϊ��" + detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		WarehouseRecDetail existDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		if(null != existDetail){//������ⵥ��ϸ
			
		}else{
			ret = -2;
		}
		return ret;
	}
}
