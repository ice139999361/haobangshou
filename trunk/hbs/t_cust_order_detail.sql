/*
Created		2009-12-7
Modified		2010-1-24
Project		
Model		
Company		
Author		
Version		
Database		mySQL 5 
*/




Create table T_CUST_ORDER_DETAIL (
	ORDER_SEQID Int NOT NULL AUTO_INCREMENT COMMENT '唯一的seqid（序列号）',
	C_CODE Varchar(32) NOT NULL COMMENT '客户编号',
	SHORT_NAME Varchar(32) COMMENT '简称',
	PO_NO_TYPE Varchar(2) NOT NULL COMMENT '客户订单类型  0----客户订单  1---客户退货单',
	PO_NO Varchar(16) NOT NULL COMMENT '客户订单号',
	C_PART_NO Varchar(32) NOT NULL COMMENT '客户物料编号，对照T_CUST_PART_NO_INFO',
	PART_NO Varchar(32) NOT NULL COMMENT '公司物料编号，对照T_CUST_PART_NO_INFO',
	PN_DESC Varchar(64) NOT NULL COMMENT '物料描述，对照T_CUST_PART_NO_INFO',
	C_PRICE Float(8,4) COMMENT '单价',
	C_PRICE_TAX Float(6,4) COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
	IS_TAX Varchar(2) COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
	TAX_RATE Float(4,2) COMMENT '税率',
	SPEC_DESC Varchar(32) COMMENT '特殊备注，如有的客户物料中有批次概念，可以填入该字段',
	COMM_DESC Varchar(128) COMMENT '一般备注',
	AMOUNT Int NOT NULL COMMENT '订货数量',
	MONEY Float(9,2) COMMENT '总金额',
	DELIVERY_AMOUNT Int DEFAULT 0 COMMENT '已经发货数量',
	LOCK_AMOUNT Int DEFAULT 0 COMMENT '仓库锁定数量',
	SELF_LOCK_AMOUNT Int DEFAULT 0 COMMENT ' 本客户锁定数量',
	COMM_LOCK_AMOUNT Int DEFAULT 0 COMMENT '通用库存锁定数量',
	ORG_DELIVERY_DATE Date COMMENT '订单原始交货日期',
	PRE_DELIVERY_DATE Date COMMENT '业务部期望订单的交货日期',
	VER_DELIVERY_DATE Date COMMENT '采购部最终确认交货日期',
	PERIOD Varchar(8) COMMENT '订单明细所属账期',
	RLT_ORDER_PO_NO Varchar(128) COMMENT '关联的采购单号，多个采购单号以,分割',
	VENDOR_CODE Varchar(32) COMMENT '供应商编码',
	STATE Varchar(8) COMMENT '订单明细状态  01---业务创建客户订单明细（业务临时保存订单明细，其他人无法查看）  02---业务确认客户订单明细（可以正式走流程，其他人可以查看）  03---业务取消客户订单（当前订单明细取消，对账期订单，取消只能在采购确认之前，对预付，取消只能在财务收到预付款之前)  21 ---待发货 （账期订单，采购确认订单，订单明细为待发货，其他情况状态不变）  22----待财务确认发货（预付X%，剩余款到发货）  60----部分出货  61----全部出货',
	ACTIVE_STATE Varchar(8) COMMENT ' 订单明细活动状态  PAUSE---业务暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---业务激活订单（激活暂停的订单，业务操作）',
	RLT_SEND_PO_NO Varchar(128) COMMENT '关联的出货单号，可以多个，以,分割',
	SETTLEMENT_TYPE Varchar(32) COMMENT '结算方式',
	STAFF_ID Varchar(20) COMMENT '操作人员ID',
	STAFF_NAME Varchar(32) COMMENT '操作人员姓名',
	SALES_ID Varchar(20) COMMENT '销售人员ID',
	SALES Varchar(32) COMMENT '销售人员  ',
 Primary Key (ORDER_SEQID),
 Foreign Key (C_CODE,PO_NO) references T_CUST_ORDER (C_CODE,PO_NO) on delete  restrict on update  restrict
) ENGINE = innodb
COMMENT = '客户订单物料明细表，关联订单表';












