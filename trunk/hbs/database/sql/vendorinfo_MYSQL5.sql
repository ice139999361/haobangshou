# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community


#
# Table structure for table t_vendor_info
#

DROP TABLE IF EXISTS `t_vendor_info`;
CREATE TABLE `t_vendor_info` (
  `BASE_SEQID` int(11) NOT NULL auto_increment COMMENT '   ',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码，格式GV0001',
  `STATE` varchar(2) NOT NULL COMMENT '状态  0----正式数据  1---临时数据（没有提交审批）  2---待审批数据  3---审批不通过  4---废弃数据  5----锁定',
  `SHORT_NAME` varchar(32) NOT NULL COMMENT '供应商简称',
  `EN_SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '英文简称',
  `ALL_NAME` varchar(256) NOT NULL COMMENT '中文名称',
  `EN_NAME` varchar(256) DEFAULT NULL COMMENT '英文名称',
  `ADDRESS` varchar(256) DEFAULT NULL COMMENT '公司地址',
  `EN_ADDRESS` varchar(256) DEFAULT NULL COMMENT '公司英文地址',
  `C_TYPE` varchar(16) DEFAULT NULL COMMENT '供应商性质,如：台资，港资等',
  `C_SCALE` varchar(16) DEFAULT NULL COMMENT '供应商规模，如1-50人，大规模等',
  `WEBSITE` varchar(128) DEFAULT NULL COMMENT '公司网址',
  `REPRESENTATIVE` varchar(32) DEFAULT NULL COMMENT '法人代表',
  `TAX_CODE` varchar(20) DEFAULT NULL COMMENT '纳税人识别号',
  `COMPANY_BRANCH` varchar(64) DEFAULT NULL COMMENT '对应的分公司或分支机构，从字典表选取或手工输入',
  `CREDIT_RATE` varchar(8) DEFAULT NULL COMMENT '信用度，参见字典表定义  ',
  `CREDIT_DESC` varchar(32) DEFAULT NULL COMMENT '信用等级描述',
  `IMPORTORT_CODE` varchar(8) DEFAULT NULL COMMENT '重要程度，参见字典表定义',
  `IMPORTANT_DESC` varchar(20) DEFAULT NULL COMMENT '重要程度描述',
  `SETTLEMENT_TYPE` varchar(8) DEFAULT NULL COMMENT '结算类型，参见字典表定义  1---账期介绍  2---预付X%,剩余货到付款  3---预付X%剩余款到发货',
  `SETTLEMENT_DESC` varchar(32) DEFAULT NULL COMMENT '结算方式描述',
  `CURRENCY` varchar(8) DEFAULT NULL COMMENT '结算币种，参见字典表  ',
  `CURRENCY_DESC` varchar(32) DEFAULT NULL COMMENT '结算币种描述',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作人员ID(采购)',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作人员名字(采购)',
  `COMM_DESC` varchar(512) DEFAULT NULL COMMENT '备注',
  `CONTACT_FEE` float(4,2) DEFAULT NULL COMMENT '合同费，百分值',
  `IS_SHOW_PRICE` varchar(2) NOT NULL COMMENT '是否显示单价  0---不显示  1---显示',
  `TAX_RATE` float(4,2) DEFAULT '0.00' COMMENT '税率，百分值',
  PRIMARY KEY (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商信息表（正式）';

#
# Dumping data for table t_vendor_info
#

#
# Table structure for table t_vendor_account_period
#

DROP TABLE IF EXISTS `t_vendor_account_period`;
CREATE TABLE `t_vendor_account_period` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_TYPE` varchar(2) NOT NULL COMMENT '账期类型  1---月结  2---天结',
  `ACCOUNT_PERIOD` varchar(32) NOT NULL COMMENT '供应商的账期设置，账期结算有效，如果月结，是月数，如果天结是天数',
  `PERIOD_START` varchar(2) NOT NULL COMMENT '账期的起始日，账期结算有效',
  `ACCOUNT_DAY` varchar(2) NOT NULL COMMENT '对账日，账期结束后的第几日，账期结算有效',
  `SETTLEMENT_DAY` varchar(2) NOT NULL COMMENT '结算日，账期结束的第几日，须大于对账日，对账期结算有效',
  `MAX_MONEY` int(11) NOT NULL DEFAULT '0' COMMENT '账期的最大交易金额，账期结算有效,对供应商无用',
  `REMINDER_DAY` varchar(20) NOT NULL COMMENT '提醒设置，对账日和结算日提前几天提醒',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_ACCOUNT_PERIOD` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商账期设置表，关联供应商的结算信息';

#
# Dumping data for table t_vendor_account_period
#


#
# Table structure for table t_vendor_bank
#

DROP TABLE IF EXISTS `t_vendor_bank`;
CREATE TABLE `t_vendor_bank` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NAME` varchar(32) NOT NULL COMMENT '开户户名',
  `ACCOUNT_BANK` varchar(64) DEFAULT NULL COMMENT '开户行',
  `ACCOUNT` varchar(32) DEFAULT NULL COMMENT '帐号',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_BANK` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商银行信息，，关联供应商的银行信息';

#
# Dumping data for table t_vendor_bank
#


#
# Table structure for table t_vendor_contact_info
#

DROP TABLE IF EXISTS `t_vendor_contact_info`;
CREATE TABLE `t_vendor_contact_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `CON_TYPE` varchar(2) DEFAULT NULL COMMENT '类别  1----联系人  2----发货人',
  `CON_NAME` varchar(32) NOT NULL COMMENT '联系人姓名',
  `CON_DUTY` varchar(32) NOT NULL COMMENT '联系人职务',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '联系人固定电话',
  `CON_MOBILE` varchar(16) DEFAULT NULL COMMENT '联系人移动电话',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '联系人传真',
  `CON_MAIL` varchar(128) DEFAULT NULL COMMENT '联系人邮箱',
  `CON_QQ` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `CON_MSN` varchar(32) DEFAULT NULL COMMENT 'MSN',
  `CON_OTHER` varchar(512) DEFAULT NULL COMMENT '联系人其他信息',
  `CON_ADDRESS` varchar(256) DEFAULT NULL COMMENT '收货地址',
  `CON_ZIP` varchar(16) DEFAULT NULL COMMENT '收货邮编',
  `IS_PRIMARY` varchar(2) DEFAULT NULL COMMENT '是否是主联系人  0----是  1---不是  这里指是否是供应商的销售人员',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_CONTACT_INFO` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='联系人信息及发货人信息';

#
# Dumping data for table t_vendor_contact_info
#





#
# Table structure for table t_vendor_oper_log
#

DROP TABLE IF EXISTS `t_vendor_oper_log`;
CREATE TABLE `t_vendor_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `OPER_TIME` date DEFAULT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '操作类型  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(20) DEFAULT NULL COMMENT '操作对象  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER',
  `OPER_KEY` varchar(20) DEFAULT NULL COMMENT '操作的具体对象：  对供应商信息为C_CODE  对供应商物料为C_PART_NO  对供应商订单为CPO_NO',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='日志表';

#
# Dumping data for table t_vendor_oper_log
#


#
# Table structure for table t_vendor_oper_log_his
#

DROP TABLE IF EXISTS `t_vendor_oper_log_his`;
CREATE TABLE `t_vendor_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '序列号',
  `OPER_TIME` date DEFAULT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '操作类型  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(20) DEFAULT NULL COMMENT '操作对象  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER',
  `OPER_KEY` varchar(20) DEFAULT NULL COMMENT '操作的具体对象：  对供应商信息为C_CODE  对供应商物料为C_PART_NO  对供应商订单为CPO_NO',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='日志历史表';

#
# Dumping data for table t_vendor_oper_log_his
#


#
# Table structure for table t_vendor_order
#

DROP TABLE IF EXISTS `t_vendor_order`;
CREATE TABLE `t_vendor_order` (
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `PO_NO` varchar(16) NOT NULL COMMENT '供应商订单编号,系统自动产生格式：P年月日001，P091212001',
  `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '0' COMMENT '供应商订单类型0----客户采购单1---退货单2---常规备货采购单3--特定客户备货采购单',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '简称',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建日期',
  `CON_NAME` varchar(32) DEFAULT NULL COMMENT '联系人，对应供应商联系人中的主联系人',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '电话，对应供应商主联系人的电话',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '传真，对应供应商主联系人的传真',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '对应供应商信息中对应的分公司或分支机构',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '结算方式，对应供应商信息中的结算方式',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '收货人',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '收货地址',
  `RECEIVE_ZIP` varchar(16) DEFAULT NULL COMMENT '收货邮编',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '操作人员ID',
  `OPER_STAFF` varchar(32) DEFAULT NULL COMMENT '录入订单的人',
  `SALES` varchar(32) DEFAULT NULL COMMENT '负责客户的销售人员，对应客户信息的销售人员  负责采购的采购人员，对应供应商的采购人员',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '订单是否显示单价（后台根据供应商信息自动处理）',
  `ORDER_TIME` datetime DEFAULT NULL COMMENT '正式形成订单日期（业务员正式提交日期）',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '订单所属账期',
  `STATE` varchar(8) DEFAULT NULL COMMENT '订单业务状态01---采购创建采购订单（采购临时保存订单，其他人无法查看）02---采购确认采购订单,待收货入库（可以正式走流程，其他人可以查看）03---采购取消采购订单（订单取消，所有订单明细全部取消，对账期订单)04---待交期确认(账期订单)00---关闭采购订单60----部分入库61----全部入库',
  `ACTIVE_SATE` varchar(8) DEFAULT NULL COMMENT '订单活动状态：  PAUSE---采购暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---采购激活订单（激活暂停的订单，采购操作）',
  `CUST_C_CODE` varchar(32) DEFAULT NULL COMMENT '针对0----客户采购单，3-- 特定客户备货有效',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商采购单表';


#
# Dumping data for table t_vendor_order
#


#
# Table structure for table t_vendor_order_detail
#

DROP TABLE IF EXISTS `t_vendor_order_detail`;

CREATE TABLE `t_vendor_order_detail` (
  `ORDER_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一的seqid（序列号）',
  `C_CODE` varchar(32) NOT NULL,
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '简称',
  `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '' COMMENT '供应商订单类型0----客户采购单1---退货单2---常规备货采购单3--特定客户备货采购单',
  `PO_NO` varchar(16) NOT NULL,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商物料编号，对照T_VENDOR_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号，对照T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '物料描述',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '单价',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '税率',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如有的客户物料中有批次概念，可以填入该字段',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT '一般备注',
  `AMOUNT` int(11) NOT NULL COMMENT '订货数量',
  `MONEY` float(9,2) DEFAULT NULL COMMENT '金额',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '已经收货数量',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '所属账期',
  `ORG_DELIVERY_DATE` date DEFAULT NULL COMMENT '原始交货日期',
  `VER_DELIVERY_DATE` date DEFAULT NULL COMMENT '确认交货日期',
  `RLT_ORDER_PO_NO` varchar(128) DEFAULT NULL COMMENT '关联的客户订单号，可能有多个客户订单单号，以,分割',
  `STATE` varchar(8) DEFAULT '0' COMMENT '订单业务状态01---采购创建采购订单（采购临时保存订单，其他人无法查看）02---采购确认采购订单,待收货入库（可以正式走流程，其他人可以查看）03---采购取消采购订单（订单取消，所有订单明细全部取消，对账期订单)04---待交期确认(账期订单)00---关闭采购订单60----部分入库61----全部入库',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '订单明细的活动状态：  PAUSE---采购暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---采购激活订单（激活暂停的订单，采购操作）  ',
  `RLT_REC_PO_NO` varchar(128) DEFAULT NULL COMMENT '关联的入库单号，可能有多个，以,号分隔',
  `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员ID',
  `STAFF_NAME` varchar(32) NOT NULL DEFAULT '' COMMENT '操作人姓名',
  `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '结算方式',
  `CUST_C_CODE` varchar(32) DEFAULT NULL COMMENT '针对0----客户采购单，3-- 特定客户备货有效',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  PRIMARY KEY (`ORDER_SEQID`),
  KEY `RLT_VENDOR_ORDER_PART_NO` (`C_CODE`,`PO_NO`),
  CONSTRAINT `RLT_VENDOR_ORDER_PART_NO` FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_vendor_order` (`C_CODE`, `PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商采购订单物料明细表，关联订单表';



#
# Dumping data for table t_vendor_order_detail
#


#
# Table structure for table t_vendor_order_detail_his
#

DROP TABLE IF EXISTS `t_vendor_order_detail_his`;
CREATE TABLE `t_vendor_order_detail_his` (
  `ORDER_SEQID` int(11) NOT NULL COMMENT '唯一的seqid（序列号）',
  `C_CODE` varchar(32) NOT NULL,
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '简称',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '供应商订单类型  0----订单  1---退货单',
  `PO_NO` varchar(16) NOT NULL,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商物料编号，对照T_VENDOR_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号，对照T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '物料描述',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '单价',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '税率',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如有的客户物料中有批次概念，可以填入该字段',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT '一般备注',
  `AMOUNT` int(11) NOT NULL COMMENT '订货数量',
  `MONEY` float(9,2) DEFAULT NULL COMMENT '金额',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '已经收货数量',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '所属账期',
  `ORG_DELIVERY_DATE` date DEFAULT NULL COMMENT '原始交货日期',
  `VER_DELIVERY_DATE` date DEFAULT NULL COMMENT '确认交货日期',
  `RLT_ORDER_PO_NO` varchar(128) DEFAULT NULL COMMENT '关联的客户订单号，可能有多个客户订单号，以,分割',
  `STATE` varchar(8) DEFAULT '0' COMMENT '业务状态：  01---采购创建订单明细（采购临时保存订单明细，其他人无法查看）  02---采购确认采购订单明细（可以正式走流程，其他人可以查看）  03---采购取消采购订单（当前订单明细取消)  04---待收货入库  00---关闭采购订单  60----部分入库  61----全部入库',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '订单明细的活动状态：  PAUSE---采购暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---采购激活订单（激活暂停的订单，采购操作）  ',
  `RLT_REC_PO_NO` varchar(128) DEFAULT NULL COMMENT '关联的入库单号，可能有多个，以,号分隔',
  `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员ID',
  `STAFF_NAME` varchar(32) NOT NULL DEFAULT '' COMMENT '操作人姓名',
  `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '结算方式',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  PRIMARY KEY (`ORDER_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商采购订单物料明细历史表，关联订单表';


#
# Dumping data for table t_vendor_order_detail_his
#


#
# Table structure for table t_vendor_order_his
#

DROP TABLE IF EXISTS `t_vendor_order_his`;
CREATE TABLE `t_vendor_order_his` (
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `PO_NO` varchar(16) NOT NULL COMMENT '供应商订单编号,系统自动产生格式：P年月日001，P091212001',
  `PO_NO_TYPE` varchar(20) DEFAULT NULL COMMENT '供应商订单类型  0----订单  1---退货单',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '简称',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建日期',
  `CON_NAME` varchar(32) DEFAULT NULL COMMENT '联系人，对应供应商联系人中的主联系人',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '电话，对应供应商主联系人的电话',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '传真，对应供应商主联系人的传真',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '对应供应商信息中对应的分公司或分支机构',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '结算方式，对应供应商信息中的结算方式',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '收货人',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '收货地址',
  `RECEIVE_ZIP` varchar(16) DEFAULT NULL COMMENT '收货邮编',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '操作人员ID',
  `OPER_STAFF` varchar(32) DEFAULT NULL COMMENT '录入订单的人',
  `SALES` varchar(32) DEFAULT NULL COMMENT '负责客户的销售人员，对应客户信息的销售人员  负责采购的采购人员，对应供应商的采购人员',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '订单是否显示单价（后台根据供应商信息自动处理）',
  `ORDER_TIME` datetime DEFAULT NULL COMMENT '正式形成订单日期（业务员正式提交日期）',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '订单所属账期',
  `STATE` varchar(8) DEFAULT NULL COMMENT '订单业务状态  01---采购创建采购订单（采购临时保存订单，其他人无法查看）  02---采购确认采购订单（可以正式走流程，其他人可以查看）  03---采购取消采购订单（订单取消，所有订单明细全部取消，对账期订单)  04---采购确认交期(交期确认后，采购单不再修改)  00---关闭采购订单  60----部分入库  61----全部入库',
  `ACTIVE_SATE` varchar(8) DEFAULT NULL COMMENT '订单活动状态：  PAUSE---采购暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---采购激活订单（激活暂停的订单，采购操作）',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商采购单历史表';

#
# Dumping data for table t_vendor_order_his
#


#
# Table structure for table t_vendor_part_no_info
#

DROP TABLE IF EXISTS `t_vendor_part_no_info`;
CREATE TABLE `t_vendor_part_no_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商物料编号',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `STATE` varchar(8) NOT NULL COMMENT '状态  0----正式数据  1---临时数据（没有提交审批）  2---待审批数据  3---审批不通过  4---废弃数据',
  `PN_DESC` varchar(128) NOT NULL COMMENT '物料描述',
  `PRICE` float NOT NULL COMMENT '单价',
  `PRICE_TAX` float(5,3) NOT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  `CREATE_DATE` date DEFAULT NULL COMMENT '创建时间',
  `STAFF_ID` varchar(32) DEFAULT NULL COMMENT '创建人ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '创建人',
  `PART_NO` varchar(32) NOT NULL COMMENT '本公司的物料编号',
  `P_CAT_CODE` varchar(32) DEFAULT NULL COMMENT '本公司的大类',
  `P_CLS_CODE` varchar(32) DEFAULT NULL COMMENT '本公司的小类',
  `MIN_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '最小订单数量，缺省为0',
  `MIN_PACKAGE` int(11) DEFAULT NULL COMMENT '最小包装单位',
  `SIMPLE_CODE` varchar(32) DEFAULT NULL COMMENT '样品编码',
  `IS_PRICECHANGE` varchar(2) NOT NULL DEFAULT '0' COMMENT '价格是否变动过  0--没有  1--是',
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT=' 供应商物料编号同本公司物料编号对应表';


#
# Dumping data for table t_vendor_part_no_info
#


#
# Table structure for table t_vendor_prepaid
#

DROP TABLE IF EXISTS `t_vendor_prepaid`;
CREATE TABLE `t_vendor_prepaid` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `PRE_PAID` varchar(3) DEFAULT NULL COMMENT '预付百分比',
  `REMINDER_DAY` varchar(3) DEFAULT NULL COMMENT '提醒人,对供应商无效',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_PREPAID` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商预付费表，关联供应商的结算信息';

#
# Dumping data for table t_vendor_prepaid
#


#
#  Foreign keys for table t_vendor_account_period
#

ALTER TABLE `t_vendor_account_period`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_vendor_bank
#

ALTER TABLE `t_vendor_bank`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_vendor_contact_info
#

ALTER TABLE `t_vendor_contact_info`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_vendor_order_detail
#

ALTER TABLE `t_vendor_order_detail`
  ADD FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_vendor_order` (`C_CODE`, `PO_NO`);

#
#  Foreign keys for table t_vendor_prepaid
#

ALTER TABLE `t_vendor_prepaid`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
