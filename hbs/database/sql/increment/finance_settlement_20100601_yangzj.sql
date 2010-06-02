/** 创建供应商未对账的视图 */
create or replace VIEW v_vendor_unsettlement as 
select  vinfo.c_code as c_code , vinfo.short_name as short_name, vinfo.staff_id as purchase_id, 
vinfo.staff_name as purchase_name, wdetail.settlement_type as settlement_type,
wdetail.finance_period as abstract,
 wdetail.finance_state as finance_state  , sum(wdetail.cur_money) as total_money
 ,sum(wdetail.cur_money) as need_money , 00000000.00 as set_money
from t_vendor_info vinfo , t_warehouse_receive_detail wdetail

where vinfo.state =0 and wdetail.state = '02'  and wdetail.settlement_type =1
and wdetail.finance_state=1 and vinfo.c_code = wdetail.c_code
group by c_code , short_name , purchase_id , purchase_name, settlement_type,abstract, finance_state 

union 

select  vinfo.c_code as c_code , vinfo.short_name as short_name, vinfo.staff_id as purchase_id, 
vinfo.staff_name as purchase_name, wdetail.settlement_type as settlement_type,
wdetail.rec_po_no as abstract,
 wdetail.finance_state as finance_state  , sum(wdetail.cur_money) as total_money
 ,sum(wdetail.cur_money) as need_money , 00000000.00 as set_money
from t_vendor_info vinfo , t_warehouse_receive_detail wdetail

where vinfo.state =0 and wdetail.state = '02'  and wdetail.settlement_type != 1
and (wdetail.finance_state=0 or wdetail.finance_state=1) and vinfo.c_code = wdetail.c_code
group by c_code , short_name , purchase_id , purchase_name, settlement_type,abstract, finance_state ;

/** 创建客户未对账的视图*/
create or replace VIEW v_customer_unsettlement as 
select  vinfo.c_code as c_code , vinfo.short_name as short_name, vinfo.staff_id as sales_id, 
vinfo.staff_name as sales_name,vinfo.ass_staff_id as ass_id, 
vinfo.ass_staff_name as ass_name, wdetail.settlement_type as settlement_type,
wdetail.finance_period as abstract,
 wdetail.finance_state as finance_state  , sum(wdetail.cur_money) as total_money
 ,sum(wdetail.cur_money) as need_money , 00000000.00 as set_money
from t_customer_info vinfo , t_warehouse_send_detail wdetail

where vinfo.state =0 and wdetail.state = '02'  and wdetail.settlement_type =1
and wdetail.finance_state=1 and vinfo.c_code = wdetail.c_code
group by c_code , short_name , sales_id , sales_name, ass_id , ass_name, settlement_type,abstract, finance_state 

union 

select  vinfo.c_code as c_code , vinfo.short_name as short_name, vinfo.staff_id as sales_id, 
vinfo.staff_name as sales_name,vinfo.ass_staff_id as ass_id, 
vinfo.ass_staff_name as ass_name, wdetail.settlement_type as settlement_type,
wdetail.send_po_no as abstract,
 wdetail.finance_state as finance_state  , sum(wdetail.cur_money) as total_money
 ,sum(wdetail.cur_money) as need_money , 00000000.00 as set_money
from t_customer_info vinfo , t_warehouse_send_detail wdetail

where vinfo.state =0 and wdetail.state = '02'  and wdetail.settlement_type != 1
and (wdetail.finance_state=0 or wdetail.finance_state=1) and vinfo.c_code = wdetail.c_code
group by c_code , short_name , sales_id , sales_name, ass_id , ass_name, settlement_type,abstract, finance_state;

/** 创建客户结算表 */
CREATE TABLE `t_customer_settlement` (
  `C_CODE` varchar(8) DEFAULT NULL COMMENT '编码',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '简称',
  `SETTLEMENT_TYPE` varchar(2) NOT NULL COMMENT '结算方式   1--月结  2---预付X%,剩余货到付款  3---预付X%剩余款到发货',
  `ABSTRACT` varchar(16) NOT NULL COMMENT '摘要  对月结为账期  对非月结为订单号',
  `TOTAL_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `NEED_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '待结算金额',
  `SET_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '已结算金额',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务状态  1 已对账未结算  2部分结算  3已结算',
  `SALES_ID` varchar(20) DEFAULT NULL COMMENT '销售人员ID',
  `SALES_NAME` varchar(32) DEFAULT NULL COMMENT '销售姓名',
  `ASS_ID` varchar(20) DEFAULT NULL COMMENT '业务助理ID',
  `ASS_NAME` varchar(32) DEFAULT NULL COMMENT '业务助理姓名',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作员ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作员姓名'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户财务结算表';

/** 创建供应商结算表 */

CREATE TABLE `t_vendor_settlement` (
  `C_CODE` varchar(8) DEFAULT NULL COMMENT '编码',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '简称',
  `SETTLEMENT_TYPE` varchar(2) NOT NULL COMMENT '结算方式   1--月结  2---预付X%,剩余货到付款  3---预付X%剩余款到发货',
  `ABSTRACT` varchar(16) NOT NULL COMMENT '摘要  对月结为账期  对非月结为订单号',
  `TOTAL_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `NEED_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '待结算金额',
  `SET_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '已结算金额',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务状态  1 已对账未结算  2部分结算  3已结算',
  `PURCHASE_ID` varchar(20) DEFAULT NULL COMMENT '采购员ID',
  `PURCHASE_NAME` varchar(32) DEFAULT NULL COMMENT '采购员姓名',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作员ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作员姓名'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商财务结算表';

/** 创建客户总结算表 */
create or replace view v_customer_total_settlement as 
select C_CODE,SHORT_NAME,SETTLEMENT_TYPE,ABSTRACT,TOTAL_MONEY,NEED_MONEY,SET_MONEY,FINANCE_STATE,SALES_ID,SALES_NAME,ASS_ID,ASS_NAME
from t_customer_settlement
union 
select C_CODE,SHORT_NAME,SETTLEMENT_TYPE,ABSTRACT,TOTAL_MONEY,NEED_MONEY,SET_MONEY,FINANCE_STATE,SALES_ID,SALES_NAME,ASS_ID,ASS_NAME
from v_customer_unsettlement;

/** 创建供应商总结算表 */
create or replace view v_vendor_total_settlement as 
select C_CODE,SHORT_NAME,SETTLEMENT_TYPE,ABSTRACT,TOTAL_MONEY,NEED_MONEY,SET_MONEY,FINANCE_STATE,PURCHASE_ID,PURCHASE_NAME
from t_vendor_settlement
union 
select C_CODE,SHORT_NAME,SETTLEMENT_TYPE,ABSTRACT,TOTAL_MONEY,NEED_MONEY,SET_MONEY,FINANCE_STATE,PURCHASE_ID,PURCHASE_NAME
from v_vendor_unsettlement;

