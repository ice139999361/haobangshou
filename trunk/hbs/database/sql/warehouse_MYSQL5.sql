# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_warehouse_info
#

DROP TABLE IF EXISTS `t_warehouse_info`;
CREATE TABLE `t_warehouse_info` (
  `HOUSE_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '仓库类型，缺省为公司总库',
  `HOUSE_USE` varchar(32) NOT NULL COMMENT '仓库货物用途  常规货物  特定客户货物',
  `VENDOR_CODE` varchar(32) NOT NULL COMMENT ' 供应商编码',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '对应的采购单号,目前的设计方案不用',
  `PART_NO` varchar(32) NOT NULL COMMENT '本公司的物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商物料编号',
  `CUST_CODE` varchar(32) DEFAULT NULL COMMENT '当HOUSE_USE为特定客户备货时必须填写CUST_CODE',
  `TOTAL_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '总库存数量，入库和出库需要做相应的加减',
  `LOCK_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '锁定的库存，采购人员在处理订单时锁定一定数量给订单，出库时锁定库存和总库存同时减操作；采购锁定时，锁定数量加，可用库存减',
  `USE_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '可用库存，可用库存+锁定库存=总库存  入库时，可以库存和锁定库存同时加操作',
  `MAX_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '最大库存数，0表示无限制',
  `MIN_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '最小库存数，缺省为0',
  `STATE` varchar(2) NOT NULL COMMENT '库存状态  0---有可用库存  1---无可用库存',
  PRIMARY KEY (`HOUSE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='仓库信息表';

#
# Dumping data for table t_warehouse_info
#


#
# Table structure for table t_warehouse_info_his
#

DROP TABLE IF EXISTS `t_warehouse_info_his`;
CREATE TABLE `t_warehouse_info_his` (
  `HOUSE_SEQID` varchar(20) NOT NULL COMMENT '序列号',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '仓库类型，缺省为公司总库',
  `HOUSE_USE` varchar(32) NOT NULL COMMENT '仓库货物用途  常规货物  特定客户货物',
  `VENDOR_CODE` varchar(32) NOT NULL COMMENT ' 供应商编码',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '对应的采购单号',
  `PART_NO` varchar(32) NOT NULL COMMENT '本公司的物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商物料编号',
  `CUST_CODE` varchar(32) DEFAULT NULL COMMENT '当HOUSE_USE为特定客户备货时必须填写CUST_CODE',
  `TOTAL_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '总库存数量，入库和出库需要做相应的加减',
  `LOCK_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '锁定的库存，采购人员在处理订单时锁定一定数量给订单，出库时锁定库存和总库存同时减操作；采购锁定时，锁定数量加，可用库存减',
  `USE_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '可用库存，可用库存+锁定库存=总库存  入库时，可以库存和锁定库存同时加操作',
  `MAX_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '最大库存数，0表示无限制',
  `MIN_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '最小库存数，缺省为0',
  `STATE` varchar(2) NOT NULL COMMENT '库存状态  0---有可用库存  1---无可用库存',
  PRIMARY KEY (`HOUSE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='仓库信息历史表';

#
# Dumping data for table t_warehouse_info_his
#


#
# Table structure for table t_warehouse_oper_log
#

DROP TABLE IF EXISTS `t_warehouse_oper_log`;
CREATE TABLE `t_warehouse_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `OPER_TIME` date NOT NULL COMMENT '操作时间',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '操作人',
  `OPER_SUBJECT` varchar(32) NOT NULL COMMENT '操作的主题  入库单  出库单',
  `OPER_KEY` varchar(64) NOT NULL COMMENT '关键字，主题是入库单，则为供应商送货单号；出库单则为出库单号          ',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '操作类型  创建  修改  取消  终止  对于审批则为  同意  不同意  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='仓库操作日志表';

#
# Dumping data for table t_warehouse_oper_log
#


#
# Table structure for table t_warehouse_oper_log_his
#

DROP TABLE IF EXISTS `t_warehouse_oper_log_his`;
CREATE TABLE `t_warehouse_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '序列号',
  `OPER_TIME` date NOT NULL COMMENT '操作时间',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '操作人',
  `OPER_SUBJECT` varchar(32) NOT NULL COMMENT '操作的主题  入库单  出库单',
  `OPER_KEY` varchar(64) NOT NULL COMMENT '关键字，主题是入库单，则为供应商送货单号；出库单则为出库单号          ',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '操作类型  创建  修改  取消  终止  对于审批则为  同意  不同意  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='仓库操作日志表历史';

#
# Dumping data for table t_warehouse_oper_log_his
#


#
# Table structure for table t_warehouse_receive
#

DROP TABLE IF EXISTS `t_warehouse_receive`;
CREATE TABLE `t_warehouse_receive` (
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '供应商入库单号（供应商送货单号）',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '供应商简称',
  `PO_NO_DATE` date default NULL COMMENT '供应商单据日期',
  `ARRAY_DATE` date NOT NULL COMMENT '货物到达日期',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '仓库位置',
  `OPER_ID` varchar(20) default NULL COMMENT '操作人ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '操作员',
  `OPER_TIME` datetime default NULL COMMENT '操作时间',
  `RECEIVE_DESC` varchar(256) NOT NULL COMMENT '收货单备注',
  `PERIOD` varchar(8) default NULL COMMENT '所属账期',
  `STATE` varchar(8) default NULL COMMENT '收货单（入库单）状态  01---仓管创建入库单（临时保存入库单，其他人无法查看）  02---仓管确认入库单（入库数据证实确认，其他人可以查看）  03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)  00---关闭入库单  31---财务确认付款',
  `ACTIVE_STATE` varchar(8) default NULL COMMENT '活动状态  PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）  ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）  ',
  `FINANCE_STATE` varchar(2) default NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '供应商订单类型  0----入库单  1---退货单',
  `SETTLEMENT_TYPE` varchar(8) default NULL COMMENT '结算方式',
  PRIMARY KEY  (`REC_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='入库单或收货单';


#
# Dumping data for table t_warehouse_receive
#


#
# Table structure for table t_warehouse_receive_detail
#

DROP TABLE IF EXISTS `t_warehouse_receive_detail`;
CREATE TABLE `t_warehouse_receive_detail` (
  `REC_DETAIL_SEQID` int(11) NOT NULL auto_increment COMMENT '入库明细SEQID,序列号',
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '供应商送货单号',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `SHORT_NAME` varchar(32) default NULL COMMENT '供应商简称',
  `PO_NO_DATE` date default NULL COMMENT '供应商单据日期',
  `ARRAY_DATE` date default NULL COMMENT '货物到达日期',
  `HOUSE_TYPE` varchar(32) default NULL COMMENT '仓库位置',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '送货单对应的采购单号',
  `SETTLEMENT_TYPE` varchar(8) NOT NULL default '' COMMENT '结算方式',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司的物料编号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商的物料编号',
  `PN_DESC` varchar(64) default NULL COMMENT '物料描述',
  `C_PRICE` float(8,4) default '0.0000' COMMENT '采购的单价',
  `IS_TAX` varchar(2) default NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` float(4,2) default NULL COMMENT '税率',
  `AMOUNT` int(11) NOT NULL COMMENT '本次送货数量',
  `CUR_MONEY` float(8,2) default '0.00' COMMENT '本次送货金额',
  `PERIOD` varchar(8) default NULL COMMENT '所属账期',
  `FINANCE_STATE` varchar(2) default NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '供应商订单类型  0---入库单  1---退货单',
  `STAFF_ID` varchar(20) default NULL COMMENT '操作员ID',
  `STAFF_NAME` varchar(32) default NULL COMMENT '操作员姓名',
  `OPER_TIME` datetime default NULL COMMENT '操作时间',
  `STATE` varchar(8) NOT NULL default '01' COMMENT '收货单（入库单）状态01---仓管创建入库单（临时保存入库单，其他人无法查看）02---仓管确认入库单（入库数据证实确认，其他人可以查看）03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)00---关闭入库单31---财务确认付款',
  `ACTIVE_STATE` varchar(8) NOT NULL default 'ACTIVE' COMMENT '活动状态PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）',
  `FINANCE_PERIOD` varchar(8) default NULL COMMENT '财务确认的账期或财务调整的账期',
  PRIMARY KEY  (`REC_DETAIL_SEQID`),
  KEY `RLT_RECEIVE_DETAIL` (`REC_PO_NO`),
  CONSTRAINT `t_warehouse_receive_detail_ibfk_1` FOREIGN KEY (`REC_PO_NO`) REFERENCES `t_warehouse_receive` (`REC_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='入库单明细表';




#
# Dumping data for table t_warehouse_receive_detail
#


#
# Table structure for table t_warehouse_receive_detail_his
#

DROP TABLE IF EXISTS `t_warehouse_receive_detail_his`;
CREATE TABLE `t_warehouse_receive_detail_his` (
  `REC_DETAIL_SEQID` int(11) NOT NULL COMMENT '入库明细SEQID,序列号',
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '供应商送货单号',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `SHORT_NAME` varchar(32) default NULL COMMENT '供应商简称',
  `PO_NO_DATE` date default NULL COMMENT '供应商单据日期',
  `ARRAY_DATE` date default NULL COMMENT '货物到达日期',
  `HOUSE_TYPE` varchar(32) default NULL COMMENT '仓库位置',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '送货单对应的采购单号',
  `SETTLEMENT_TYPE` varchar(8) default NULL COMMENT '结算方式',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司的物料编号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商的物料编号',
  `PN_DESC` varchar(64) default NULL COMMENT '物料描述',
  `C_PRICE` float(8,4) default '0.0000' COMMENT '采购的单价',
  `IS_TAX` varchar(2) default NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` float(4,2) default NULL COMMENT '税率',
  `AMOUNT` int(11) NOT NULL COMMENT '本次送货数量',
  `CUR_MONEY` float(8,2) default '0.00' COMMENT '本次送货金额',
  `PERIOD` varchar(8) default NULL COMMENT '所属账期',
  `FINANCE_STATE` varchar(2) default NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '供应商订单类型  0----入库单  1---退货单',
  `STATE` varchar(8) NOT NULL default '01' COMMENT '收货单（入库单）状态01---仓管创建入库单（临时保存入库单，其他人无法查看）02---仓管确认入库单（入库数据证实确认，其他人可以查看）03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)00---关闭入库单31---财务确认付款',
  `ACTIVE_STATE` varchar(8) NOT NULL default 'ACTIVE' COMMENT '活动状态PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）',
  `FINANCE_PERIOD` varchar(8) default NULL COMMENT '财务确认的账期或财务调整的账期',
  PRIMARY KEY  (`REC_DETAIL_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='入库单明细表历史';




#
# Dumping data for table t_warehouse_receive_detail_his
#


#
# Table structure for table t_warehouse_receive_his
#

DROP TABLE IF EXISTS `t_warehouse_receive_his`;
CREATE TABLE `t_warehouse_receive_his` (
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '供应商入库单号（供应商送货单号）',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '供应商简称',
  `PO_NO_DATE` date default NULL COMMENT '供应商单据日期',
  `ARRAY_DATE` date NOT NULL COMMENT '货物到达日期',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '仓库位置',
  `OPER_ID` varchar(20) default NULL COMMENT '操作人ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '操作员',
  `OPER_TIME` datetime default NULL COMMENT '操作时间',
  `RECEIVE_DESC` varchar(256) NOT NULL COMMENT '收货单备注',
  `PERIOD` varchar(8) default NULL COMMENT '所属账期',
  `STATE` varchar(8) default NULL COMMENT '收货单（入库单）状态  01---仓管创建入库单（临时保存入库单，其他人无法查看）  02---仓管确认入库单（入库数据证实确认，其他人可以查看）  03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)  00---关闭入库单  31---财务确认付款',
  `ACTIVE_STATE` varchar(8) default NULL COMMENT '活动状态  PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）  ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）  ',
  `FINALCE_STATE` varchar(2) default NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '供应商订单类型  0----入库单  1---退货单',
  `SETTLEMENT_TYPE` varchar(8) default NULL COMMENT '结算方式',
  PRIMARY KEY  (`REC_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='入库单或收货单历史';


#
# Dumping data for table t_warehouse_receive_his
#


#
# Table structure for table t_warehouse_send
#

DROP TABLE IF EXISTS `t_warehouse_send`;
CREATE TABLE `t_warehouse_send` (
  `SEND_PO_NO` varchar(32) NOT NULL COMMENT '发货单号，系统自动产生，规则：GSYYYYMMDDXXX',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '客户编码',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '客户简称',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '客户收货人姓名',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '客户的收货地址',
  `RECEIVE_ZIP` varchar(8) DEFAULT NULL COMMENT '客户收货邮编',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '客户收货人电话',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '客户收货人传真',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '对应的供货分公司或分支机构',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '仓库说明',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '结算方式',
  `CREATE_DATE` datetime NOT NULL COMMENT '开单日期',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '操作员',
  `SEND_DESC` varchar(256) DEFAULT NULL COMMENT '发货单备注',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '所属账期',
  `STATE` varchar(16) DEFAULT NULL COMMENT '送货单业务状态  01---仓管创建出库单（临时保存出库单，其他人无法查看）  02---仓管确认出库单（出库单数据证实确认，其他人可以查看）  03---仓管取消出库单（需要在确认出库单之前操作，确认的出库单不能操作)  00---关闭出库单  31---财务确认收款',
  `ACTIVE_STATE` varchar(8) DEFAULT 'ACTIVE' COMMENT '出库单活动状态  PAUSE---仓管暂停出库单（暂停状态，出库单的业务状态停留在当前，除仓管外，不能激活出库单）  ACTIVE---仓管激活出库单（激活暂停的出库单，仓管操作）',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '客户订单类型  0----出库单  1---退货单',
  PRIMARY KEY (`SEND_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='出货单信息表（出库单或发货单）';

#
# Dumping data for table t_warehouse_send
#


#
# Table structure for table t_warehouse_send_detail
#

DROP TABLE IF EXISTS `t_warehouse_send_detail`;
CREATE TABLE `t_warehouse_send_detail` (
  `SEND_DETAIL_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '详单序列号',
  `SEND_PO_NO` varchar(32) NOT NULL,
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '客户物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如物料批次等',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '物料单价',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '税率',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '是否显示单价',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '对应的客户订单号',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '结算方式',
  `COMM_DESC` char(128) DEFAULT NULL COMMENT '备注',
  `AMOUNT` int(11) NOT NULL COMMENT '出货数量',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '本次出货金额',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '所属账期',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '供应商编码',
  `VENDOR_PO_NO` varchar(128) DEFAULT NULL COMMENT '供应商的采购单号，可以多个，以,分割',
  `STATE` varchar(16) DEFAULT NULL COMMENT '业务状态',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '活动状态',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) DEFAULT NULL COMMENT '客户订单类型  0----出库单  1---退货单',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '发货日期',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作员ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作员姓名',
  `FINANCE_PERIOD` varchar(8) DEFAULT NULL COMMENT '财务确认的账期或财务调整的账期',
  PRIMARY KEY (`SEND_DETAIL_SEQID`),
  KEY `RLT_SEND_DETAIL` (`SEND_PO_NO`),
  CONSTRAINT `RLT_SEND_DETAIL` FOREIGN KEY (`SEND_PO_NO`) REFERENCES `t_warehouse_send` (`SEND_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户发货详单表';



#
# Dumping data for table t_warehouse_send_detail
#


#
# Table structure for table t_warehouse_send_detail_his
#

DROP TABLE IF EXISTS `t_warehouse_send_detail_his`;
CREATE TABLE `t_warehouse_send_detail_his` (
  `SEND_DETAIL_SEQID` int(11) NOT NULL COMMENT '详单序列号',
  `SEND_PO_NO` varchar(32) NOT NULL,
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '客户物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如物料批次等',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '物料单价',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '税率',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '是否显示单价',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '对应的客户订单号',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '结算方式',
  `COMM_DESC` char(128) DEFAULT NULL COMMENT '备注',
  `AMOUNT` int(11) NOT NULL COMMENT '出货数量',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '本次出货金额',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '所属账期',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '供应商编码',
  `VENDOR_PO_NO` varchar(128) DEFAULT NULL COMMENT '供应商的采购单号，可以多个，以,分割',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '客户订单类型  0----出库单  1---退货单',
  `STATE` varchar(16) DEFAULT NULL COMMENT '业务状态',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '活动状态',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '发货日期',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作员ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作员姓名',
  `FINANCE_PERIOD` varchar(8) DEFAULT NULL COMMENT '财务确认的账期或财务调整的账期',
  PRIMARY KEY (`SEND_DETAIL_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户发货详单表历史';



#
# Dumping data for table t_warehouse_send_detail_his
#


#
# Table structure for table t_warehouse_send_his
#

DROP TABLE IF EXISTS `t_warehouse_send_his`;
CREATE TABLE `t_warehouse_send_his` (
  `SEND_PO_NO` varchar(32) NOT NULL COMMENT '发货单号，系统自动产生，规则：GSYYYYMMDDXXX',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '客户编码',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '客户简称',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '客户收货人姓名',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '客户的收货地址',
  `RECEIVE_ZIP` varchar(8) DEFAULT NULL COMMENT '客户收货邮编',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '客户收货人电话',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '客户收货人传真',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '对应的供货分公司或分支机构',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '仓库说明',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '结算方式',
  `CREATE_DATE` datetime NOT NULL COMMENT '开单日期',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '操作员',
  `SEND_DESC` varchar(256) DEFAULT NULL COMMENT '发货单备注',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '所属账期',
  `STATE` varchar(16) DEFAULT NULL COMMENT '送货单业务状态  01---仓管创建出库单（临时保存出库单，其他人无法查看）  02---仓管确认出库单（出库单数据证实确认，其他人可以查看）  03---仓管取消出库单（需要在确认出库单之前操作，确认的出库单不能操作)  00---关闭出库单  31---财务确认收款',
  `ACTIVE_STATE` varchar(8) DEFAULT 'ACTIVE' COMMENT '出库单活动状态  PAUSE---仓管暂停出库单（暂停状态，出库单的业务状态停留在当前，除仓管外，不能激活出库单）  ACTIVE---仓管激活出库单（激活暂停的出库单，仓管操作）',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_YPE` varchar(2) DEFAULT NULL COMMENT '客户订单类型  0----出库单  1---退货单',
  PRIMARY KEY (`SEND_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='出货单信息表（出库单或发货单）历史';

#
# Dumping data for table t_warehouse_send_his
#


#
#  Foreign keys for table t_warehouse_receive_detail
#

ALTER TABLE `t_warehouse_receive_detail`
  ADD FOREIGN KEY (`REC_PO_NO`) REFERENCES `t_warehouse_receive` (`REC_PO_NO`);

#
#  Foreign keys for table t_warehouse_send_detail
#

ALTER TABLE `t_warehouse_send_detail`
  ADD FOREIGN KEY (`SEND_PO_NO`) REFERENCES `t_warehouse_send` (`SEND_PO_NO`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
