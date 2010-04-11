/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.warehousereceive.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.OrderCalUtils;
import com.hbs.customerorder.manager.CustOrderDetailMgr;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.domain.warehouse.dao.WarehouseRecDetailDao;

import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;

import com.hbs.vendororder.manager.VendorOrderDetailMgr;
import com.hbs.warehouse.common.constants.WareHouseConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;
import com.hbs.warehouse.manager.WarehouseMgr;
import com.hbs.warehousereceive.manager.helper.WarehouseHelper;

/**
 * @author Administrator
 *
 */
public class WareHouseRecDetailMgr {
	private static final Logger logger = Logger.getLogger(WareHouseRecDetailMgr.class);
	
	/**
	 * 保存入库单明细，保存时先判断库中是否存在，不存在insert操作
	 * 否则 update操作，update操作的状态必须是 01 临时状态才能保存修改
	 * 如果状态为确认，则
	 * 如果是确认操作，则还需要执行如下操作
		 * 判断入库的物料明细对于的供应商订单编号，
		 * 查询出订单明细，判断订单明细的订单类型
		 * 2---常规备货采购单
		 * 3---特定客户备货采购单
		 * 则需要更新仓库库存，更新供应商订单的到货数量
		 * 0----客户采购单
		 * 则除需要更新仓库库存，更新供应商订单的到货数量外
		 * 还需要更新到客户订单锁定的数量上去，执行自动锁定 
	 * @param detail
	 * @param isflowRec 是否跟随主入库单同时保存
	 * @param content   保存意见
	 * @return  >= 0 成功  -1 此状态的明细不允许修改
	 * @throws Exception
	 */
	public int saveWareHouseRecDetail(WarehouseRecDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		String st =detail.getState();
		if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){
			logger.debug("保存供应商入库物料明细，传入的参数为:" + detail.toString());
		}else if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_02)){
			logger.debug("确认供应商入库物料明细，传入的参数为:" + detail.toString());
		}else{
			logger.debug("确认供应商入库物料明细,状态为取消，不做处理，传入的参数为:" + detail.toString());
			return ret;
		}
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		//计算金额
		detail.setCurrMoney(OrderCalUtils.calOrderMoney(detail.getPrice(), detail.getIsTax(), detail.getTaxRate(),detail.getPriceTax(),null, detail.getAmount()));
		//设置账期
		String period = detail.getPeriod();
		if(period == null){
			WarehouseHelper helper =(WarehouseHelper)BeanLocator.getInstance().getBean(WareHouseConstants.PRE_SPRING + detail.getPoNoType() + detail.getSettlementType());
			detail.setPeriod(helper.getPeriod(detail));
			detail.setFinancePeriod(detail.getPeriod());				
		}
		WarehouseRecDetail existDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		if(null == existDetail){//不存在，insert操作，
			logger.debug("数据库中无此入库单明细，insert操作！");
			
			Integer id = detailDao.insertWarehouseRecDetail(detail);
			detail.setRecDetailSeqId(id);
			ret = id;
			WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (detail.getState().equals(WareHouseConstants.WAREHOUSE_REC_INFO_02) ? "确认" : "新增"), "入库单物料明细", detail.getLogKey(), null, content);
		}else{//做update操作
			String state = existDetail.getState();
			logger.debug("数据库中存在入库单明细，update操作！明细状态为：" + state);
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//状态为临时，可以修改
				
				detail.setRecDetailSeqId(existDetail.getRecDetailSeqId());
				detailDao.updateWarehouseRecDetail(detail);
				ret = detail.getRecDetailSeqId();
				WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (detail.getState().equals(WareHouseConstants.WAREHOUSE_REC_INFO_02) ? "确认" : "修改"), "入库单物料明细", detail.getLogKey(), null, content);
			}else{//状态不正确，不允许修改
				logger.debug("数据库中存在入库单明细，update操作！状态不正确，不允许修改！");
				ret = -1;
			}
			
		}
		/**
		 * 如果是确认操作，则还需要执行如下操作
		 * 判断入库的物料明细对于的供应商订单编号，
		 * 查询出订单明细，判断订单明细的订单类型
		 * 2---常规备货采购单
		 * 3---特定客户备货采购单
		 * 则需要更新仓库库存，更新供应商订单的到货数量
		 * 0----客户采购单
		 * 则除需要更新仓库库存，更新供应商订单的到货数量外
		 * 还需要更新到客户订单锁定的数量上去，执行自动锁定
		*/
		if(st.equals(WareHouseConstants.WAREHOUSE_REC_INFO_02)){
			logger.debug("入库单明细入库，处理对应的供应商采购单！");
			//更新对于的采购单明细的已到货数量
			processVendorOrderDetail( detail);
		}
		return ret;
	}
	private int processVendorOrderDetail(WarehouseRecDetail detail) throws Exception{
		int ret =0;
		VendorOrderDetail vOrderDetail = new VendorOrderDetail();
		//设置供应商编码
		vOrderDetail.setCommCode(detail.getVendorCode());
		//设置供应商的采购的PONO
		vOrderDetail.setPoNo(detail.getRltPoNo());
		//设置供应商的物料编码
		vOrderDetail.setCpartNo(detail.getCpartNo());
		//设置本公司的物料编码
		vOrderDetail.setPartNo(detail.getPartNo());
		//设置供应商采购单中的特殊备注
		vOrderDetail.setSpecDesc(detail.getSpecDesc());
		logger.debug("调用供应商采购单明细管理类，更新已到货数量！设置的参数为：" + vOrderDetail.toString());
		VendorOrderDetailMgr vOrderDetailMgr =(VendorOrderDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.VENDOR_ORDER_DETAILMGR);
		ret = vOrderDetailMgr.updateOrderDetailByAmount(vOrderDetail);
		if(ret ==0 ){
			logger.debug("供应商采购单明细已到货数量更新成功！");
			logger.debug("查询采购单，判断采购的类型！");
			VendorOrderDetail existOrderDetail = vOrderDetailMgr.getVendorOrderDetailByBizKey(vOrderDetail);
			String vPonoType = existOrderDetail.getPoNoType();
			
			String custCode = existOrderDetail.getCustCcode();
			String custPoNo = existOrderDetail.getRltOrderPoNo();
			String custSpecDesc = existOrderDetail.getSpecDesc();
			switch(Integer.parseInt(vPonoType)){
			case 0: //客户供应商采购单
				logger.debug("供应商采购单为客户订单！");
				ret =processCustOrderDetail( detail, custCode, custPoNo, custSpecDesc);
				break;
			case 1: //退货单
				logger.debug("供应商采购单为退货单！");
				break;
			case 2: //常规备货
				logger.debug("供应商采购单为常规备货订单！");
				ret = processWarehouseInfo(detail,custCode);
				break;
				
			case 3: //特定客户备货				
				logger.debug("供应商采购单为特定客户备货订单！");
				ret = processWarehouseInfo(detail,custCode);
				break;
			default:
				throw new Exception("入库单对应的供应商采购单的订单类型不正确，无法后续处理！");
				
			}
			
		}else{			
			throw new Exception("供应商商采购单更新已到货数量失败！失败代码：" + ret);
		}
		return ret;
	}
	/**
	 * 私有函数，处理客户订单的情况，更新仓库总数和可用数量，锁定数量
	 * @param detail
	 * @param custCode
	 * @param custPoNo
	 * @param custSpecDesc
	 * @return
	 * @throws Exception
	 */
	private int processCustOrderDetail(WarehouseRecDetail detail,String custCode,String custPoNo,String custSpecDesc) throws Exception{
		int ret =0;
		CustOrderDetail orderDetail = new CustOrderDetail();
		//设置客户编码
		orderDetail.setCommCode(custCode);
		//设置客户订单的pono
		orderDetail.setRltOrderPoNo(custPoNo);
		//设置物料编号
		orderDetail.setPartNo(detail.getPartNo());
		//设置特殊备注
		orderDetail.setSpecDesc(custSpecDesc);
		CustOrderDetailMgr custDetailMgr =(CustOrderDetailMgr)BeanLocator.getInstance().getBean(WareHouseConstants.CUST_ORDER_DETAILMGR);
		CustOrderDetail existDetail = custDetailMgr.findCustOrderDetailByBizKey(orderDetail);
		//可能的仓库保存的可用数量
		int iuseAmount =0;
		if(existDetail != null){
			int ineedLock = existDetail.getAmount() - existDetail.getLockAmount();
			if(ineedLock >= detail.getAmount()){//客户订单中需要锁定的数量> 本次送货数量
				ineedLock = detail.getAmount();
			}else{
				iuseAmount = detail.getAmount() - ineedLock;
			}
			orderDetail.setOperSeqId(existDetail.getOperSeqId());
			orderDetail.setSelfLockAmount(ineedLock);
			//客户订单增加锁定数量
			custDetailMgr.lockOrderDetailAmount(orderDetail, null, null, null);
			//仓库库存数量更新
			WareHouseInfo wInfo = new WareHouseInfo();
			//设置仓库类型，总库还是其他
			wInfo.setHouseType(detail.getHouseType());
			
			wInfo.setHouseType(WareHouseConstants.WAREHOUSE_USE_TYPE_1);
				
			//设置供应商编码
			wInfo.setVendorCode(detail.getVendorCode());
			//设置本公司物料编码
			wInfo.setPartNo(detail.getPartNo());
			//设置供应商的物料编码
			wInfo.setCpartNo(detail.getCpartNo());
			//设置增加的库存
			wInfo.setTotalAmount(detail.getAmount());
			wInfo.setUseAmount(iuseAmount);
			wInfo.setLockAmount(ineedLock);
			//设置客户编码
			wInfo.setCustCode(custCode);
			WarehouseMgr whMgr= (WarehouseMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
			ret = whMgr.saveLockWareHouseInfo(wInfo, null, null, null);
		}else{
			throw new Exception("按照关键字无法找到对应的客户订单，无法入库，关键字为：" + orderDetail.toString());
			
		}
		return ret;
	}
	/**
	 * 私有函数，处理常规备货和特定客户备货的情况，更新仓库总数和可用数量
	 * @param detail
	 * @param custCode
	 * @return
	 * @throws Exception
	 */
	private int processWarehouseInfo(WarehouseRecDetail detail,String custCode) throws Exception{
		int ret =0;
		WareHouseInfo wInfo = new WareHouseInfo();
		//设置仓库类型，总库还是其他
		wInfo.setHouseType(detail.getHouseType());
		//设置仓库用途
		String vPonoType = detail.getPoNoType();
		switch(Integer.parseInt(vPonoType)){
		case 0: //客户供应商采购单
		case 1: //退货单
		case 3: //特定客户备货
			wInfo.setHouseType(WareHouseConstants.WAREHOUSE_USE_TYPE_1);
			break;		
		case 2: //常规备货
			wInfo.setHouseType(WareHouseConstants.WAREHOUSE_USE_TYPE_2);
			break;	
			
		default:
			throw new Exception("入库单对应的仓库信息仓库用途不正确，无法后续处理！");
		}
		//设置供应商编码
		wInfo.setVendorCode(detail.getVendorCode());
		//设置本公司物料编码
		wInfo.setPartNo(detail.getPartNo());
		//设置供应商的物料编码
		wInfo.setCpartNo(detail.getCpartNo());
		//设置增加的库存
		wInfo.setTotalAmount(detail.getAmount());
		wInfo.setUseAmount(detail.getAmount());
		//设置客户编码
		wInfo.setCustCode(custCode);
		WarehouseMgr whMgr= (WarehouseMgr)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_INFO_MGR);
		ret = whMgr.saveInWareHouseInfo(wInfo);
		if(ret !=0){
			throw new Exception("入库单明细更新库存错误，错误代码：" + ret);
		}
		return ret;
		
	}
	/**
	 * 批量保存入库单明细，调用单条保存方法
	 * @param detailList
	 * @param isflowRec  是否跟随主订单保存
	 * @param content  备注，记录日志
	 * @return  0 成功 
	 * @throws Exception
	 */
	public int saveWareHouseRecDetailList(List<WarehouseRecDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("批量保存入库单明细，明细的数量为:" + detailList.size());
		for(WarehouseRecDetail detail : detailList){
			Integer i = saveWareHouseRecDetail(detail,isflowRec,content);
			logger.debug("批量保存入库单明细,保存的结果为：" + i);
		}
		return ret;
	}
	
	/**
	 * 取消入库单明细，先判断取消的入库单明细是否存在，不存在，返回-2
	 * 存在，判断状态是否为临时状态，其他状态不允许取消。
	 * @param detail
	 * @param isflowRec 是否跟随主记录
	 * @param content 取消意见，记录日志
	 * @return 0--成功  -1 状态不正确，不能取消 -2 取消的明细不存在
	 * @throws Exception
	 */
	public int cancelWareHouseRecDetail(WarehouseRecDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("取消入库单明细信息,传入的参数为：" + detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		WarehouseRecDetail existDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		if(null != existDetail){//存在入库单明细
			String state = existDetail.getState();
			logger.debug("数据库中存在入库单明细，明细状态为：" + state);
			if(state.equals(WareHouseConstants.WAREHOUSE_REC_INFO_01)){//为临时状态，可以取消
				existDetail.setState(WareHouseConstants.WAREHOUSE_REC_INFO_03);
				detailDao.updateWarehouseRecDetailByState(existDetail);
				WareHouseLogUtils.operLog(existDetail.getStaffId(), existDetail.getStaffName(), "取消", "入库单明细", existDetail.getLogKey(), null, content);
			}else{//非临时状态，不能取消
				logger.debug("数据库中存在入库单明细，明细状态已经为非临时状态，不能做取消操作！");
				ret =-1;
			}
		}else{//不存在入库单明细，无法做取消
			ret = -2;
		}
		return ret;
	}
	
	/**
	 * 批量取消入库单明细，调用单条取消方法
	 * @param detailList
	 * @param isflowRec 是否跟随入库单同时操作
	 * @param content  取消说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int cancelWareHouseRecDetailList(List<WarehouseRecDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("批量取消入库单明细，明细的数量为:" + detailList.size());
		for(WarehouseRecDetail detail : detailList){
			cancelWareHouseRecDetail(detail,isflowRec,content);			
		}
		return ret;
	}
	/**
	 * 暂停/激活供应商入库单明细，传入的参数不需要修改原始状态
	 * 由系统做反向操作
	 * @param detail
	 * @param isflowRec
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveState(WarehouseRecDetail detail , boolean isflowRec,String content) throws Exception{
		int ret =0;
		logger.debug("供应商入库单明细暂停/激活操作，传入的参数为：" + detail.toString());
		String activeState = detail.getActiveState();
		if(activeState.equals(WareHouseConstants.WAREHOUSE_REC_ACTIVE)){
			detail.setActiveState(WareHouseConstants.WAREHOUSE_REC_PAUSE);
		}else{
			detail.setActiveState(WareHouseConstants.WAREHOUSE_REC_ACTIVE);
		}
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		detailDao.updateWarehouseRecDetailByActiveState(detail);
		WareHouseLogUtils.operLog(detail.getStaffId(), detail.getStaffName(), (activeState.equals(WareHouseConstants.WAREHOUSE_REC_ACTIVE) ? "激活" : "暂停"), "供应商入库单", detail.getLogKey(), null, content);
		return ret;
	}
	/**
	  * 批量暂停/激活供应商入库单明细，传入的参数不需要修改原始状态
	 * 由系统做反向操作
	 * @param detailList
	 * @param isflowRec
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public int controlActiveStateList(List<WarehouseRecDetail> detailList,boolean isflowRec,String content) throws Exception{
		int ret =0;
		for(WarehouseRecDetail detail : detailList){
			controlActiveState(detail ,  isflowRec, content);
		}
		return ret;
	}
	/**
	 * 财务调整账期，前台需要控制2个条件
	 * 1。结算类型为账期结算
	 * 2.财务状态为未对账
	 * 其他的不允许调整账期
	 * @param detail
	 * @param staffId  财务ID
	 * @param staffName  财务姓名
	 * @param content   说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int adjustFinancePeriod(WarehouseRecDetail detail,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("财务调整供应商入库单明细账期，传入的参数为：" + detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		detailDao.updateWarehouseRecDetailByFinancePeriod(detail);
		WareHouseLogUtils.operLog(staffId, staffName, "调整账期", "供应商入库单明细", detail.getLogKey(), null, content);
		return ret;
	}
	/**
	 * 财务批量调整账期，前台需要控制2个条件
	 * 1。结算类型为账期结算
	 * 2.财务状态为未对账
	 * 其他的不允许调整账期
	 * @param detailList
	 * @param staffId  财务ID
	 * @param staffName  财务姓名
	 * @param content   说明，记录日志
	 * @return
	 * @throws Exception
	 */
	public int adjustFinancePeriodList(List<WarehouseRecDetail> detailList,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("财务调整供应商入库单明细列表账期，数目为：" + detailList.size());
		for(WarehouseRecDetail detail : detailList){
			adjustFinancePeriod( detail, staffId, staffName,  content);
		}
		return ret;
	}
	/**
	 * 财务设置已对账,针对入库单明细，
	 * 财务已对账对入库单建议不设置
	 * 
	 * @param detail
	 * @param staffId
	 * @param staffName
	 * @param content
	 * @return 0 成功  -1 财务已经对账，不需要再对
	 * @throws Exception
	 */
	public int setFinanceState(WarehouseRecDetail detail,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("财务设置已对账，传入的参数为：" + detail.toString());
		String financeState = detail.getFinanceState();
		if(financeState.equals(WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_1)){
			ret =-1;
			logger.debug("本条供应商入库单明细已经对账，不需要再处理!");
		}else{
			WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
			detail.setFinanceState(WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_1);
			detailDao.updateWarehouseRecDetailByFinanceState(detail);
			WareHouseLogUtils.operLog(staffId, staffName, "财务对账", "供应商入库单明细", detail.getLogKey(), null, content);
			//根据明细确定入库单的财务状态
//			WarehouseRecDetail rDetail = new WarehouseRecDetail();
//			rDetail.setRecPoNo(detail.getRecPoNo());
//			rDetail.setVendorCode(detail.getVendorCode());
//			rDetail.setState(WareHouseConstants.WAREHOUSE_REC_INFO_02);
//			List<WarehouseRecDetail> dList = detailDao.listWarehouseRecDetail(rDetail);
//			String infoFState = WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_1;
//			for(WarehouseRecDetail recDetail : dList){
//				String fstate = recDetail.getFinanceState();
//				if(fstate.equals(WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_0)){
//					infoFState = WareHouseConstants.WAREHOUSE_REC_FINANCE_STATE_0;
//					break;
//				}
//			}
//			WarehouseRecInfo recInfo = new WarehouseRecInfo();
//			recInfo.setRecPoNo(detail.getRecPoNo());
//			recInfo.setFinanceState(infoFState);
//			recInfo.setVendorCode(detail.getVendorCode());
//			WarehouseRecInfoDao whrInfoDao =(WarehouseRecInfoDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_INFO_DAO);
//			whrInfoDao.updateWarehouseRecInfoByFinanceState(recInfo);
		}
		return ret;
	}
	
	
	public int setFinanceStateList(List<WarehouseRecDetail> detailList,String staffId,String staffName, String content) throws Exception{
		int ret =0;
		logger.debug("财务批量设置已对账，数量为：" + detailList.size());
		for(WarehouseRecDetail recDetail : detailList){
			setFinanceState(recDetail, staffId, staffName,  content);
		}
		return ret;
	}
	/**
	 * 查询单条入库单明细，条件可以是主键的sequence
	 * 也可以是组合业务关键字：REC_PO_NO C_CODE SETTLEMENT_TYPE PART_NO C_PART_NO PO_NO_TYPE SPEC_DESC
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public WarehouseRecDetail getWarehouseRecDetail(WarehouseRecDetail detail) throws Exception{
		WarehouseRecDetail retDetail = null;
		logger.debug("查询单条入库单明细，输入的参数为：" +detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		retDetail = detailDao.findWarehouseRecDetailByBizKey(detail);
		return retDetail;
	}
	
	/**
	 * 查询入库单明细列表
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public List<WarehouseRecDetail> getWarehouseRecDetailList(WarehouseRecDetail detail) throws Exception{
		List<WarehouseRecDetail> retDetailList = null;
		logger.debug("查询入库单明细列表，输入的参数为：" +detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		retDetailList = detailDao.listWarehouseRecDetail(detail);
		return retDetailList;
	}
	
	/**
	 * 根据查询条件，查询符合条件的入库单明细总数
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public Integer getWarehouseRecDetailCount(WarehouseRecDetail detail) throws Exception{
		Integer retCount = 0;
		logger.debug("查询入库单明细列表数量，输入的参数为：" +detail.toString());
		WarehouseRecDetailDao detailDao = (WarehouseRecDetailDao)BeanLocator.getInstance().getBean(WareHouseConstants.WAREHOUSE_REC_DETAIL_DAO);
		retCount = detailDao.listWarehouseRecDetailCount(detail);
		return retCount;
	}
}
