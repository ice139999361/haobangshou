/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehouse.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.warehouse.dao.WareHouseInfoDao;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;

/**
 * @author Administrator
 *
 */
public class WarehouseMgr {

	private static final Logger logger = Logger.getLogger(WarehouseMgr.class);
	
	/**
	 * ���ݴ����ҵ��������ѯ�ֿ���Ϣ
	 * @param wInfo  ҵ������������houseType 
	 * houseUse vendorCode partNo cpartNo custCode state
	 * @return  null �� �����ֿ���Ϣ �������ѯ�������ڼ�����׳��������쳣
	 * @throws Exception
	 */
	public WareHouseInfo getWareHouseInfoByBizKey(WareHouseInfo wInfo) throws Exception{
		return findWareHouseInfoByBizKey(null,wInfo);
	}
	
	/**
	 * ����������ѯ�ֿ���Ϣ
	 * @param houseSeqId
	 * @return  null �� �����ֿ���Ϣ
	 * @throws Exception
	 */
	public WareHouseInfo getWareHouseInfoById(String houseSeqId) throws Exception{
		WareHouseInfo retInfo = null;
		logger.debug("���ݴ����������ѯ�ֿ���Ϣ������Ϊ��" + houseSeqId);
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		retInfo = whInfoDao.findWareHouseInfoById(houseSeqId);
		return retInfo;
	}
	
	/**
	 * �������Ϣ���棬�Ȳ�ѯ��棬���������update����������insert����
	 * ����ǰ���ж� �������  = �������� + �������� ������ȣ����׳��쳣
	 * @param wInfo
	 * @return
	 * @throws Exception
	 */
	public int saveInWareHouseInfo(WareHouseInfo wInfo) throws Exception{
		int ret =0;
		logger.debug("�����������Ϣ����Ĳ���Ϊ��" + wInfo.toString());
		
		int wInfoTotalAmount = (wInfo.getTotalAmount() == null ? 0 : wInfo.getTotalAmount());
		int wInfoLockAmount = (wInfo.getLockAmount()== null ? 0 : wInfo.getLockAmount());
		int wInfoUseAmount = (wInfo.getUseAmount()== null ? 0 : wInfo.getUseAmount());
		
		if(wInfoLockAmount + wInfoUseAmount !=  wInfoTotalAmount){			
			StringBuilder sb = new StringBuilder("-1 ����Ŀ������  != �������� + ��������  ����!");
			sb.append("����Ŀ������=").append(wInfoTotalAmount);
			sb.append("��������=").append(wInfoLockAmount);
			sb.append("��������=").append(wInfoUseAmount);
			logger.debug(sb.toString());
			throw new Exception(sb.toString());
		}else{
			//���ҿ����Ƿ����ҵ�������Ŀ����Ϣ
			WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
			WareHouseInfo existWInfo = findWareHouseInfoByBizKey(whInfoDao,wInfo);
			
			if(null == existWInfo){//���в�������Ҫ���ֵĿ����Ϣ
				logger.debug("���в�������Ҫ���ֵĿ����Ϣ,����Ӳ�����");			
				whInfoDao.insertWareHouseInfo(wInfo);
				
			}else{//������ͬ�����Ϣ����update����
				
				existWInfo.setTotalAmount(existWInfo.getTotalAmount().intValue() + wInfoTotalAmount);
				existWInfo.setLockAmount(existWInfo.getLockAmount().intValue() + wInfoLockAmount);
				existWInfo.setUseAmount(existWInfo.getUseAmount().intValue() + wInfoUseAmount);
				if(existWInfo.getUseAmount().intValue() == 0){//���ÿ��Ϊ0�����״̬Ϊ�����ã��������
					existWInfo.setState(WareHouseConstants.WAREHOUSE_INFO_STATE_1);				
				}else{
					existWInfo.setState(WareHouseConstants.WAREHOUSE_INFO_STATE_0);
				}
				whInfoDao.updateWareHouseInfo(existWInfo);
			}
		}
		return ret;
	}
	/**
	 * ��������Ϣ���棬 ��������������������������
	 * �Ȳ�ѯ�ֿ������Ƿ���ڣ���������ڣ����׳��쳣
	 * ����ǰ�ж� �������  = �������� + �������� ������ȣ����׳��쳣
	 * ͬʱ�ж� ������������������������������� >=0
	 * @param wInfo
	 * @return
	 * @throws Exception
	 */
	public int saveOutWareHouseInfo(WareHouseInfo wInfo) throws Exception{
		int ret =0;
		logger.debug("�����������Ϣ����Ĳ���Ϊ��" + wInfo.toString());
		int wInfoTotalAmount = (wInfo.getTotalAmount() == null ? 0 : wInfo.getTotalAmount());
		int wInfoLockAmount = (wInfo.getLockAmount()== null ? 0 : wInfo.getLockAmount());
		//����ҵ��������ѯ������Ƿ����
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		WareHouseInfo existWInfo = findWareHouseInfoByBizKey(whInfoDao,wInfo);
		if(null != existWInfo){//�ֿ���ڿ����Ϣ
			int newTotalAmount = wInfo.getTotalAmount().intValue() - wInfoTotalAmount;
			int newLockAmount = wInfo.getLockAmount().intValue() - wInfoLockAmount;
			int existUseAmount = wInfo.getUseAmount().intValue();
			if(newTotalAmount < 0 || newLockAmount <0 ||
					(newTotalAmount != (newLockAmount + existUseAmount))){//���µ����ݲ���ȷ���׳��쳣
				throw new Exception("�����ڵ���ϢΪ��" + existWInfo.toString() + "���ݱ�����²���ȷ���޷�����");
			}else{
				existWInfo.setTotalAmount(newTotalAmount);
				existWInfo.setLockAmount(newLockAmount);
			}
			whInfoDao.updateWareHouseInfo(existWInfo);
			
		}else{//�ֿ��в����ڿ����Ϣ
			throw new Exception("�ֿ��в�������Ҫ����Ŀ����Ϣ,�޷��������������ϢΪ��" + wInfo.toString());
		}
		return ret;
	}
	
	/**
	 * �Բֿ�Ŀ����Ϣ�����������������������Ӳ������Կ���������������
	 * �Ȳ�ѯ�ֿ������Ƿ���ڣ���������ڣ����׳��쳣
	 * ����ǰ�ж� �������  = �������� + �������� ������ȣ����׳��쳣
	 * ͬʱ�ж� �������� >=0
	 * @param wInfo
	 * @param staffId ������ID
	 * @param staffName ����������
	 * @param content  ����˵��
	 * @return
	 * @throws Exception
	 */
	public int saveLockWareHouseInfo(WareHouseInfo wInfo , String staffId,String staffName ,String content) throws Exception{
		int ret =0;
		logger.debug("����ֿ����������Ϣ����Ĳ���Ϊ��" + wInfo.toString());		
		
		int wInfoLockAmount = (wInfo.getLockAmount()== null ? 0 : wInfo.getLockAmount());
		int wInfoUseAmount = (wInfo.getUseAmount()== null ? 0 : wInfo.getUseAmount());
		
		//����ҵ��������ѯ������Ƿ����
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		WareHouseInfo existWInfo = findWareHouseInfoByBizKey(whInfoDao,wInfo);
		if(null != existWInfo){//�ֿ���ڿ����Ϣ
			int existTotalAmount = existWInfo.getTotalAmount().intValue();
			int newLockAmount = existWInfo.getLockAmount().intValue() + wInfoLockAmount;
			int newUseAmount = existWInfo.getUseAmount().intValue() - wInfoUseAmount;
			if(newUseAmount < 0 || 
					(existTotalAmount != (newLockAmount + newUseAmount))){//���µ����ݲ���ȷ���׳��쳣
				throw new Exception("�����ڵ���ϢΪ��" + existWInfo.toString() + "���ݱ�����²���ȷ���޷�����");
			}else{				
				existWInfo.setLockAmount(newLockAmount);
				existWInfo.setUseAmount(newUseAmount);
				if(newUseAmount == 0){
					existWInfo.setState(WareHouseConstants.WAREHOUSE_INFO_STATE_1);
				}
				
			}
			
			whInfoDao.updateWareHouseInfo(existWInfo);
			//��¼������־
			if(null != staffId){
				WareHouseLogUtils.operLog(staffId, staffName, "�������", "�ֿ���Ϣ", wInfo.getLogKey(), wInfo.toString(), content);
			}
		}else{//�ֿ��в����ڿ����Ϣ
			throw new Exception("�ֿ��в�������Ҫ�����Ŀ����Ϣ,�޷���������������ϢΪ��" + wInfo.toString());
		}
		return ret;
	}
	/**
	 * ���ÿ����Ϣ�������Сֵ�������澯ʹ��
	 * @param wInfo
	 * @param staffId  ������ID
	 * @param staffName  ����������
	 * @param content  ����˵��������¼��־ʹ��
	 * @return
	 * @throws Exception
	 */
	public int setMinMaxWareHouseInfo(WareHouseInfo wInfo ,String  staffId,String staffName,String content) throws Exception{
		int ret =0;
		logger.debug("���òֿ��������С�����Ϣ����Ĳ���Ϊ��" + wInfo.toString());	
		//����ҵ��������ѯ������Ƿ����
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		WareHouseInfo existWInfo = findWareHouseInfoByBizKey(whInfoDao,wInfo);
		if(null != existWInfo){//�ֿ���ڿ����Ϣ			
			existWInfo.setMaxAmount(wInfo.getMaxAmount());
			existWInfo.setMinAmount(wInfo.getMinAmount());
			whInfoDao.updateWareHouseInfo(existWInfo);
			//��¼������־
			if(null != staffId){
				WareHouseLogUtils.operLog(staffId, staffName, "��ֵ����", "�ֿ���Ϣ", wInfo.getLogKey(), wInfo.toString(), content);
			}
		}else{//�ֿ��в����ڿ����Ϣ
			throw new Exception("�ֿ��в�������Ҫ���ÿ�������С�Ŀ����Ϣ,�޷������ò�������ϢΪ��" + wInfo.toString());
		}
		return ret;
	}
	
	public List<WareHouseInfo> listWareHouseInfo(WareHouseInfo wInfo) throws Exception{
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		return whInfoDao.listWareHouseInfo(wInfo);
	}
	
	public Integer listWareHouseInfoCount(WareHouseInfo wInfo) throws Exception{
		WareHouseInfoDao whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		return whInfoDao.listWareHouseInfoCount(wInfo);
	}
	/**
	 * ���ݴ����ҵ��������ѯ�ֿ���Ϣ
	 * @param wInfo  ҵ������������houseType 
	 * houseUse vendorCode partNo cpartNo custCode state
	 *    
	 * @return  null �� �����ֿ���Ϣ �������ѯ�������ڼ�����׳��������쳣
	 * @throws Exception
	 */
	private WareHouseInfo findWareHouseInfoByBizKey(WareHouseInfoDao whInfoDao ,WareHouseInfo wInfo) throws Exception{
		WareHouseInfo retInfo = null;
		logger.debug("���ݴ���Ĳ�����ѯ�ֿ���Ϣ������Ϊ��" + wInfo.toString());
		if(null == whInfoDao){
			whInfoDao =(WareHouseInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_DAO);
		}
		retInfo = whInfoDao.findWareHouseInfoByBizKey(wInfo);
		return retInfo;
	}
}
