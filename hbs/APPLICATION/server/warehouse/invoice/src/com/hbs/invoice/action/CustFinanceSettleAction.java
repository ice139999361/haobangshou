package com.hbs.invoice.action;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;

import com.hbs.common.springhelper.BeanLocator;

import com.hbs.domain.auth.pojo.Staff;
import com.hbs.domain.invoice.pojo.FinanceSettlement;
import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;
import com.hbs.invoice.manager.CustFinanceSettlementMgr;
import com.hbs.warehousesend.manager.WareHouseSendDetailMgr;


@SuppressWarnings("serial")
public class CustFinanceSettleAction extends BaseAction {

	
	protected static final Logger logger = Logger.getLogger(CustFinanceSettleAction.class);
	
	private FinanceSettlement settlement;
	
	private String roleType;
	
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	
	
	

	public FinanceSettlement getSettlement() {
		return settlement;
	}

	public void setSettlement(FinanceSettlement settlement) {
		this.settlement = settlement;
	}

	/**
	 * get the vendor settlement list 
	 * for ALL
	 * @return
	 */
	public String doList(){
		try {
			logger.debug("begin list the customer settlement list the function is doList()");
			if(settlement == null)
				settlement = new FinanceSettlement();
			if(StringUtils.isNotEmpty(roleType)){
			    Staff staff = getLoginStaff();
				if(roleType.equals("scywy")){//业务
					settlement.setSalesId(staff.getStaffId().toString());
				}else if(roleType.equals("scywyzl")){//助理
					settlement.setAssId(staff.getStaffId().toString());
				}
			}else{
				logger.debug("系统参数不正确(无用户角色)，无法查询!");
				setErrorReason("系统参数不正确(无用户角色)，无法查询!");
				return ERROR;
			}
			setPagination(settlement);
			setResult("list", listFinanceSettlement(settlement));
			setTotalCount(listFinanceSettlementCount(settlement));
			
			
			logger.debug("end list the customer settlement list the function is doList()");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in customer settlement list doList", e);
			setErrorReason(e.getMessage());
			return ERROR;
		}
	}
	
	/**
	 * get the vendor settlement list 
	 * for ALL
	 * @return
	 */
	public String doListDetail(){
		try {
			logger.debug("begin list the customer settlement list  the function is doListDetail()");
			if(settlement == null){
				logger.debug("无输入参数，无法查询!");
				setErrorReason("无输入参数，无法查询!");
				return ERROR;
			}
			
			setPagination(settlement);
			setResult("list", listSendDetail(settlement));
			setTotalCount(listSendDetailCount(settlement));
			
			
			logger.debug("end list the customer settlement list the function is doListDetail()");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in customer settlement list doListDetail", e);
			setErrorReason(e.getMessage());
			return ERROR;
		}
	}
	
	public String doSaveSettlement(){
		logger.debug("begin to save the customer settlement ,the input parameter is :" + settlement);
		try{
			if(settlement == null){
				logger.debug("the input customer settlement info is null , can not to save!");
				setErrorReason("您没有输入客户结算信息，不能保存!");
				return ERROR;
			}else{
				Staff staff = getLoginStaff();
				if(staff == null){
					logger.debug("您没有登录，不能保存，请您先登录系统!");				
					setErrorReason("您没有登录，不能保存，请您先登录系统!");
					return ERROR;
				}else{
					settlement.setStaffId(staff.getStaffId().toString());
					settlement.setStaffName(staff.getStaffName());
				}
				String financeState = settlement.getFinanceState();
				if(financeState == null || financeState.equals("3")){
					logger.debug("the input customer settlement info is settled , can not to save!");
					setErrorReason("您输入客户结算信息已经结算完毕，不能再次结算!");
					return ERROR;
				}
				//the settlement number detail
				BigDecimal totalMoney = settlement.getTotalMoney();
				BigDecimal needMoney = settlement.getNeedMoney();
				BigDecimal dealMoney = settlement.getDealMoney();
				BigDecimal curMoney = settlement.getCurMoney();
				if(curMoney == null || curMoney.compareTo(new BigDecimal(0))== 0){
					logger.debug("the input customer settlement info curMoney is null or 0 , can not to save!");
					setErrorReason("您没有输入客户的本次结算金额，不能执行结算操作!");
					return ERROR;
				}
				
				if(curMoney.compareTo(needMoney)>0){
					logger.debug("the input customer settlement info curMoney is too much , can not to save!");
					setErrorReason("您输入客户的本次结算金额超过了待结算金额，不能执行结算操作!");
					return ERROR;
				}else{
					needMoney = needMoney.subtract(curMoney);
					settlement.setNeedMoney(needMoney);
					dealMoney = dealMoney.add(curMoney);
					settlement.setDealMoney(dealMoney);
					if(dealMoney.compareTo(totalMoney) == 0){
						settlement.setFinanceState("3");
					}else{
						settlement.setFinanceState("2");
					}
					CustFinanceSettlementMgr mgr = (CustFinanceSettlementMgr)BeanLocator.getInstance().getBean("custFinanceSettlementMgr");
					mgr.saveSettlement(settlement);					
				}
			}
			logger.debug("end to save the customer settlement保存客户结算信息成功!");
			//setErrorReason("保存客户结算信息成功!");
			return SUCCESS;
		}catch (Exception e){
			logger.debug("保存结算失败，失败原因：",e);
			setErrorReason("保存客户结算信息失败!"+ e);
			return ERROR;
		}
		
	}
	
	
	
	
	private List<FinanceSettlement> listFinanceSettlement(FinanceSettlement fSettlement) throws Exception{
		logger.debug("get the customer finance settlement info ,the input parameteris :" + fSettlement);
		CustFinanceSettlementMgr mgr = (CustFinanceSettlementMgr)BeanLocator.getInstance().getBean("custFinanceSettlementMgr");
		List<FinanceSettlement> listSettle = mgr.listFinanceSettlement(fSettlement);
		return listSettle;
	}
	
	private Integer listFinanceSettlementCount(FinanceSettlement fSettlement) throws Exception{
		logger.debug("get the customer finance settlement count ,the input parameteris :" + fSettlement);
		CustFinanceSettlementMgr mgr = (CustFinanceSettlementMgr)BeanLocator.getInstance().getBean("custFinanceSettlementMgr");
		Integer iCount = mgr.listFinanceSettlementCount(fSettlement);
		return iCount;
	}
	
	private List<WarehouseSendDetail> listSendDetail(FinanceSettlement fSettlement) throws Exception{
		logger.debug("get the customer finance settlement detail info ,the input parameteris :" + fSettlement);
		WareHouseSendDetailMgr detailMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean("wareHouseSendDetailMgr");
		WarehouseSendDetail detail = new WarehouseSendDetail();
		detail.setCustCode(fSettlement.getCommCode());
		String type = fSettlement.getSettlementType();
		detail.setSettlementType(type);
		if(type.equals("1")){
			detail.setPeriod(fSettlement.getSummery());
		}else{
			detail.setSendPoNo(fSettlement.getSummery());
		}		
		List<WarehouseSendDetail> listSettle = detailMgr.listWarehouseSendDetail(detail);
		return listSettle;
	}
	
	private Integer listSendDetailCount(FinanceSettlement fSettlement) throws Exception{
		logger.debug("get the customer finance settlement detail count ,the input parameteris :" + fSettlement);
		WareHouseSendDetailMgr detailMgr = (WareHouseSendDetailMgr)BeanLocator.getInstance().getBean("wareHouseSendDetailMgr");
		WarehouseSendDetail detail = new WarehouseSendDetail();
		detail.setCustCode(fSettlement.getCommCode());
		String type = fSettlement.getSettlementType();
		detail.setSettlementType(type);
		if(type.equals("1")){
			detail.setPeriod(fSettlement.getSummery());
		}else{
			detail.setSendPoNo(fSettlement.getSummery());
		}
		Integer iCount = detailMgr.listWarehouseSendDetailCount(detail);
		return iCount;
	}
}
