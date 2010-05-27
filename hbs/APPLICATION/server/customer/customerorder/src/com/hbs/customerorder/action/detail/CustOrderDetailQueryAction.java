package com.hbs.customerorder.action.detail;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;


@SuppressWarnings("serial")
public class CustOrderDetailQueryAction extends BaseAction {
	public static final String custOrderDetailMgrName = "custOrderDetailMgr";

	/**
	 * logger.
	 */
	protected static Logger logger = Logger.getLogger(CustOrderDetailQueryAction.class);
	
	CustOrderDetail corderDetail;

	public CustOrderDetail getCorderDetail() {
		return corderDetail;
	}

	public void setCorderDetail(CustOrderDetail corderDetail) {
		this.corderDetail = corderDetail;
	}
	
	
	/**
	 * δ����������ϸ��ѯ�����ۻ�����ʹ�ã�
	 * @action.input vorderDetail.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doQueryList() {
		try {
			logger.debug("begin doQueryList");
			if(corderDetail == null){
				corderDetail = new CustOrderDetail();
			}
			//��װ��Ҫ�������Ĳ�ѯ����
			//�û���Ϣ
			String staffId = getLoginStaff().getStaffId().toString();
			if(StringUtils.isEmpty(staffId)){
				logger.error("û�е�¼ϵͳ�����¼!");
				setErrorReason("û�е�¼ϵͳ�����¼!");
				return ERROR;
			}else{
				corderDetail.setField("queryStaffId", staffId);
			}
			//����������= �ѷ�������
			corderDetail.setField("notComplete", "notComplete");
			//����
			corderDetail.setPoNoType("0");
			corderDetail.setActiveState("ACTIVE");
			corderDetail.setField("noState", "'01','03'");
			corderDetail.setSort("VER_DELIVERY_DATE DESC");
			setPagination(corderDetail);
			
			
			CustOrderDetailMgr mgr = (CustOrderDetailMgr)getBean(custOrderDetailMgrName);
			setResult("list", mgr.listCustOrderDetail(corderDetail));
			setTotalCount(mgr.listCustOrderDetailCount(corderDetail));
			setResult("count", getTotalCount());
			
			logger.debug("end doQueryList");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doQueryList", e);
			setErrorReason("��ѯʧ�ܣ����Ժ�����!");
			return ERROR;
		}
	}
	
}
