/**
 * 
 */
package com.hbs.warehousesend.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;
import com.hbs.warehousesend.manager.WareHouseSendMgr;

/**
 * �ֿ���ⵥ���࣬ʵ��doList��doGetInfo
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public abstract class WarehouseSendBaseAction extends BaseAction {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(WarehouseSendBaseAction.class);

	public static final String WAREHOUSE_SEND_MGR = "wareHouseSendMgr";
	
	WarehouseSendInfo warehouseSend;

	

	public WarehouseSendInfo getWarehouseSend() {
		return warehouseSend;
	}

	public void setWarehouseSend(WarehouseSendInfo warehouseSend) {
		this.warehouseSend = warehouseSend;
	}

	/**
	 * ���ؽ�ɫ��
	 * @return
	 */
	public abstract String getRoleName();
	
	/**
	 * �����Ƿ����ڲ�ʹ�á�true���鿴���е����ύ���ݣ���������ʱ���ݣ���false���鿴�Լ������ݣ�����staffId��
	 * @return
	 */
	protected abstract boolean getIsManager();
	
	protected WareHouseSendMgr getMgr() {
		return (WareHouseSendMgr)getBean(WAREHOUSE_SEND_MGR);
	}
	
	/**
	 * ��ѯ
	 * @action.input warehouseSend.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doList() {
		try {
			logger.debug("begin doList");
			if(warehouseSend == null)
				warehouseSend = new WarehouseSendInfo();
			if(getIsManager())
				warehouseSend.setField("noState01", true);
			else
				setMyId(false);
			setPagination(warehouseSend);
			WareHouseSendMgr mgr = getMgr();
			setResult("list", mgr.listWarehouseSendInfo(warehouseSend));
			setTotalCount(mgr.listWarehouseSendInfoCount(warehouseSend, false));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeWarehouseSendState, getRoleName()));
			logger.debug("end doList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doList", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ��ȡ���ⵥ��Ϣ
	 * @action.input warehouseSend.sendPoNo + warehouseSend.custCode
	 * @action.result warehouseSend.*
	 * @return
	 */
	public String doGetInfo() {
		try{
			logger.debug("begin doGetInfo");
			boolean success = getWarehouseSendByKey(true);
			setResult("warehouseSend", warehouseSend);
			logger.debug("end doGetInfo");
			return success ? SUCCESS : ERROR;
		}catch(Exception e) {
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
	}

	/**
	 * ���ݹؼ��ֶλ�ȡ��Ϣ
	 * @throws Exception
	 */
	protected boolean getWarehouseSendByKey(boolean isDetail) throws Exception {
		boolean isTrue = WarehouseSendUtil.checkKeyFields(warehouseSend);
		logger.debug("isTrue =" + isTrue);
		if(!isTrue) {
			logger.debug(warehouseSend.toString());
			logger.debug("����Ϊ�գ�");
			setErrorReason("����Ϊ�գ�");
			return false;
		}
		WareHouseSendMgr mgr = getMgr();
		
		warehouseSend = mgr.getWarehouseSendInfo(warehouseSend, isDetail);
		if(warehouseSend == null || StringUtils.isEmpty(warehouseSend.getOperId())) {
			logger.debug("û���ҵ�");
			setErrorReason("û�в�ѯ����Ӧ�Ĺ�Ӧ�̶�����¼��");
			return false;
		}else if(!getIsManager() && !warehouseSend.getOperId().equals(getLoginStaff().getStaffId().toString())) {
			logger.debug("Ȩ�޴���");
			setErrorReason("Ȩ�޴���");
			return false;
		}
		return true;
	}

	protected void setMyId(boolean setName) throws Exception {
		warehouseSend.setOperId(getLoginStaff().getStaffId().toString());
		if(setName)
			warehouseSend.setOperStaff(getLoginStaff().getStaffName());
	}
}
