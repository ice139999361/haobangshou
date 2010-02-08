# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_customer_info
#

DROP TABLE IF EXISTS `t_customer_info`;
CREATE TABLE `t_customer_info` (
  `BASE_SEQID` int(11) NOT NULL auto_increment,
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编码,格式GC0001',
  `STATE` varchar(2) NOT NULL COMMENT '客户状态  0----正式数据  1---临时数据（没有提交审批）  2---待审批数据  3---审批不通过  4---废弃数据  5--系统锁定',
  `SHORT_NAME` varchar(32) NOT NULL COMMENT '客户简称',
  `EN_SHORT_NAME` varchar(32) default NULL COMMENT '英文简称',
  `ALL_NAME` varchar(256) NOT NULL COMMENT '公司中文名称',
  `EN_NAME` varchar(20) default NULL COMMENT '公司英文名称',
  `ADDRESS` varchar(256) default NULL COMMENT '客户公司地址',
  `EN_ADDRESS` varchar(256) default NULL COMMENT '公司英文地址',
  `C_TYPE` varchar(32) default NULL COMMENT '性质：如台资，港资等',
  `C_SCALE` varchar(32) default NULL COMMENT '规模，1-500人，大企业等',
  `WEBSITE` varchar(128) default NULL COMMENT '客户公司网址',
  `REPRESENTATIVE` varchar(32) default NULL COMMENT '客户法人代表',
  `TAX_CODE` char(128) default NULL COMMENT '客户纳税人识别号',
  `COMPANY_BRANCH` varchar(64) default NULL COMMENT '对应的分公司或分支机构，从字典表选取或手工输入',
  `CREDIT_RATE` varchar(8) default NULL COMMENT '客户信用度，参见字典表定义  ',
  `CREDIT_DESC` char(32) default NULL COMMENT '信用等级描述',
  `IMPORTANT_CODE` varchar(8) default NULL COMMENT '客户的重要程度，参见字典表定义',
  `IMPORTANT_DESC` char(32) default NULL COMMENT '客户重要程度描述',
  `SETTLEMENT_TYPE` varchar(8) default NULL COMMENT '客户结算类型，参见字典表定义  1---账期介绍  2---预付X%,剩余货到付款  3---预付X%剩余款到发货',
  `SETTLEMENT_DESC` varchar(32) default NULL COMMENT '结算方式描述',
  `CURRENCY` varchar(2) default NULL COMMENT '客户结算币种，参见字典表  ',
  `CURRENCY_DESC` varchar(32) default NULL COMMENT '结算币种描述',
  `STAFF_ID` varchar(20) default NULL COMMENT '销售人员ID',
  `STAFF_NAME` varchar(20) default NULL COMMENT '销售人员名字',
  `COMM_DESC` varchar(512) default NULL COMMENT '客户备注',
  `VENDOR_CODE` varchar(8) NOT NULL COMMENT '供应商编码',
  `CONTACT_FEE` float(6,4) default NULL COMMENT '合同费，百分值',
  `IS_SHOW_PRICE` varchar(2) NOT NULL COMMENT '发货单是否显示单价  0---不显示  1---显示',
  `TAX_RATE` float(6,4) default '0.0000' COMMENT '交易税率，百分值',
  `ASS_STAFF_ID` varchar(20) default NULL COMMENT '对应的业务部助理ID',
  `ASS_STAFF_NAME` varchar(32) default NULL COMMENT '对应的业务部助理姓名',
  `CREATE_TIME` datetime default NULL COMMENT '录入时间',
  PRIMARY KEY  (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户信息表（正式）';


#
# Dumping data for table t_customer_info
#


#
# Table structure for table t_cust_account_period
#

DROP TABLE IF EXISTS `t_cust_account_period`;
CREATE TABLE `t_cust_account_period` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `ACCOUNT_TYPE` varchar(2) NOT NULL COMMENT '账期类型  1---月结  2---天结',
  `ACCOUNT_PERIOD` varchar(20) NOT NULL COMMENT '客户的账期设置，账期结算有效，如果月结，是月数，如果天结是天数',
  `PERIOD_START` varchar(8) NOT NULL COMMENT '账期的起始日，账期结算有效',
  `ACCOUNT_DAY` varchar(2) NOT NULL COMMENT '对账日，账期结束后的第几日，账期结算有效',
  `SETTLEMENT_DAY` varchar(2) NOT NULL COMMENT '结算日，账期结束的第几日，须大于对账日，对账期结算有效',
  `MAX_MONEY` float(9,2) NOT NULL COMMENT '客户账期的最大交易金额，账期结算有效',
  `REMINDER_DAY` varchar(20) NOT NULL COMMENT '提醒设置，对账日和结算日提前几天提醒',
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户账期设置表，关联客户的结算信息';

#
# Dumping data for table t_cust_account_period
#


#
# Table structure for table t_cust_bank
#

DROP TABLE IF EXISTS `t_cust_bank`;
CREATE TABLE `t_cust_bank` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `ACCOUNT_NAME` varchar(32) NOT NULL COMMENT '开户户名',
  `ACCOUNT_BANK` varchar(64) DEFAULT NULL COMMENT '开户行',
  `ACCOUNT` varchar(32) DEFAULT NULL COMMENT '帐号',
  `BASE_SEQID` int(11) NOT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`),
  CONSTRAINT `t_cust_bank_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户银行信息，，关联客户的银行信息';


#
# Dumping data for table t_cust_bank
#


#
# Table structure for table t_cust_order
#

DROP TABLE IF EXISTS `t_cust_order`;
CREATE TABLE `t_cust_order` (
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编码',
  `PO_NO` varchar(16) NOT NULL COMMENT '客户订单编号',
  `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '0' COMMENT '客户订单类型  0----客户订单  1---客户退货单',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '客户简称',
  `ORDER_TIME` date DEFAULT NULL COMMENT '客户订单日期',
  `CON_NAME` varchar(32) DEFAULT NULL COMMENT '联系人，对应客户联系人中的主联系人',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '电话，对应客户主联系人的电话',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '传真，对应客户主联系人的传真',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '对应客户信息中对应的分公司或分支机构',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '结算方式，对应客户信息中的结算方式',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '收货人，对应客户信息的收货人',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '收货地址，对应客户信息的收货地址',
  `RECEIVE_ZIP` varchar(16) DEFAULT NULL COMMENT '收货邮编，对应客户信息中的收货邮编',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '录入人ID(业务部助理)',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '录入订单的人(业务部助理)',
  `SALES_ID` varchar(20) DEFAULT NULL COMMENT '销售ID',
  `SALES` varchar(32) DEFAULT NULL COMMENT '负责客户的销售人员，对应客户信息的销售人员',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '客户订单是否显示单价（后台根据客户信息自动处理）',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '供应商编码，对界面隐藏',
  `FIRST_CREATE_TIME` datetime DEFAULT NULL COMMENT '初次录入系统的日期',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '正式形成订单日期（业务员正式提交日期）',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '订单所属账期',
  `STATE` varchar(8) DEFAULT NULL COMMENT '订单的业务状态  01---业务创建客户订单（业务临时保存订单，其他人无法查看）  02---业务确认客户订单（可以正式走流程，其他人可以查看）  03---业务取消客户订单（订单取消，所有订单明细全部取消，对账期订单，取消只能在采购确认之前，对预付，取消只能在财务收到预付款之前)  04---待业务确认交期(只对账期订单有效，本状态是采购修改交期后，显示的状态，等待业务确认交期，交期的概念针对订单明细，只要有一个订单明细需要确认交期，订单状态就为待交期确认)  05---待业务确认发货（货未备齐，待业务',
  `ACTIVE_STATE` varchar(8) DEFAULT 'ACTIVE' COMMENT '订单的活动状态  PAUSE---业务暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---业务激活订单（激活暂停的订单，业务操作）',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户订购单表';

#
# Dumping data for table t_cust_order
#


#
# Table structure for table t_cust_order_detail
#

DROP TABLE IF EXISTS `t_cust_order_detail`;
CREATE TABLE `t_cust_order_detail` (
  `ORDER_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一的SEQID（序列号）',
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编号',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '简称',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '客户订单类型  0----客户订单  1---客户退货单',
  `PO_NO` varchar(16) NOT NULL COMMENT '客户订单号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '客户物料编号，对照T_CUST_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号，对照T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '物料描述，对照T_CUST_PART_NO_INFO',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '单价',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '税率',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如有的客户物料中有批次概念，可以填入该字段',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT '一般备注',
  `AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '订货数量',
  `MONEY` float(9,2) DEFAULT NULL COMMENT '总金额',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '已经发货数量',
  `LOCK_AMOUNT` int(11) DEFAULT '0' COMMENT '仓库锁定数量',
  `SELF_LOCK_AMOUNT` int(11) DEFAULT '0' COMMENT ' 本客户锁定数量',
  `COMM_LOCK_AMOUNT` int(11) DEFAULT '0' COMMENT '通用库存锁定数量',
  `ORG_DELIVERY_DATE` date DEFAULT NULL COMMENT '订单原始交货日期',
  `PRE_DELIVERY_DATE` date DEFAULT NULL COMMENT '业务部期望订单的交货日期',
  `VER_DELIVERY_DATE` date DEFAULT NULL COMMENT '采购部最终确认交货日期',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '订单明细所属账期',
  `RLT_ORDER_PO_NO` varchar(128) DEFAULT NULL COMMENT '关联的采购单号，多个采购单号以,分割',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '供应商编码',
  `STATE` varchar(8) DEFAULT NULL COMMENT '订单明细状态  01---业务创建客户订单明细（业务临时保存订单明细，其他人无法查看）  02---业务确认客户订单明细（可以正式走流程，其他人可以查看）  03---业务取消客户订单（当前订单明细取消，对账期订单，取消只能在采购确认之前，对预付，取消只能在财务收到预付款之前)  21 ---待发货 （账期订单，采购确认订单，订单明细为待发货，其他情况状态不变）  22----待财务确认发货（预付X%，剩余款到发货）  60----部分出货  61----全部出货',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT ' 订单明细活动状态  PAUSE---业务暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---业务激活订单（激活暂停的订单，业务操作）',
  `RLT_SEND_PO_NO` varchar(128) DEFAULT NULL COMMENT '关联的出货单号，可以多个，以,分割',
  `DELIVERY_HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '发货对应的仓库总库/其他库，缺省为公司总库1',
  `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '结算类型',
  `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '操作员ID(业务助理)',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作员姓名（业务助理）',
  `SALES_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '销售人员ID(业务员)',
  `SALES` varchar(32) DEFAULT NULL COMMENT '销售人员姓名（业务员）',
  PRIMARY KEY (`ORDER_SEQID`),
  KEY `C_CODE` (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户订单物料明细表，关联订单表';


#
# Dumping data for table t_cust_order_detail
#


#
# Table structure for table t_cust_order_detail_his
#

DROP TABLE IF EXISTS `t_cust_order_detail_his`;
CREATE TABLE `t_cust_order_detail_his` (
  `ORDER_SEQID` int(11) NOT NULL COMMENT '唯一的SEQID（序列号）',
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编号',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '简称',
  `PO_NO` varchar(16) NOT NULL COMMENT '客户订单号',
  `PO_NO_TYPE` varchar(2) DEFAULT NULL COMMENT '客户订单类型  0----客户订单  1---客户退货单',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '客户物料编号，对照T_CUST_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号，对照T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '物料描述，对照T_CUST_PART_NO_INFO',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '单价',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '税率',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如有的客户物料中有批次概念，可以填入该字段',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT '一般备注',
  `AMOUNT` int(11) NOT NULL COMMENT '订货数量',
  `MONEY` float(9,2) DEFAULT NULL COMMENT '总金额',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '已经发货数量',
  `LOCK_AMOUNT` int(11) DEFAULT '0' COMMENT '仓库锁定数量',
  `SELF_LOCK_AMOUNT` int(11) DEFAULT NULL,
  `COMM_LOCK_AMOUNT` int(11) DEFAULT NULL,
  `ORG_DELIVERY_DATE` date DEFAULT NULL COMMENT '订单原始交货日期',
  `PRE_DELIVERY_DATE` date DEFAULT NULL COMMENT '业务部期望订单的交货日期',
  `VER_DELIVERY_DATE` date DEFAULT NULL COMMENT '采购部最终确认交货日期',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '订单明细所属账期',
  `RLT_ORDER_PO_NO` varchar(128) DEFAULT NULL COMMENT '关联的采购单号，多个采购单号以,分割',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '供应商编码',
  `STATE` varchar(8) DEFAULT NULL COMMENT '订单明细状态  01---业务创建客户订单明细（业务临时保存订单明细，其他人无法查看）  02---业务确认客户订单明细（可以正式走流程，其他人可以查看）  03---业务取消客户订单（当前订单明细取消，对账期订单，取消只能在采购确认之前，对预付，取消只能在财务收到预付款之前)  21 ---待发货 （账期订单，采购确认订单，订单明细为待发货，其他情况状态不变）  22----待财务确认发货（预付X%，剩余款到发货）  60----部分出货  61----全部出货',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT ' 订单明细活动状态  PAUSE---业务暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---业务激活订单（激活暂停的订单，业务操作）',
  `RLT_SEND_PO_NO` varchar(128) DEFAULT NULL COMMENT '关联的出货单号，可以多个，以,号分隔',
  `DELIVERY_HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '发货对应的仓库总库/其他库，缺省为公司总库1',
  `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '结算类型',
  `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '操作员ID(业务助理)',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作员姓名（业务助理）',
  `SALES_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '销售人员ID(业务员)',
  `SALES` varchar(32) DEFAULT NULL COMMENT '销售人员姓名（业务员）'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户订单物料明细历史表，关联订单表';


#
# Dumping data for table t_cust_order_detail_his
#


#
# Table structure for table t_cust_order_his
#

DROP TABLE IF EXISTS `t_cust_order_his`;
CREATE TABLE `t_cust_order_his` (
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编码',
  `PO_NO` varchar(16) NOT NULL COMMENT '客户订单编号',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '客户简称',
  `ORDER_TIME` date DEFAULT NULL COMMENT '客户订单日期',
  `CON_NAME` varchar(32) DEFAULT NULL COMMENT '联系人，对应客户联系人中的主联系人',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '电话，对应客户主联系人的电话',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '传真，对应客户主联系人的传真',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '对应客户信息中对应的分公司或分支机构',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '结算方式，对应客户信息中的结算方式',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '收货人，对应客户信息的收货人',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '收货地址，对应客户信息的收货地址',
  `RECEIVE_ZIP` varchar(16) DEFAULT NULL COMMENT '收货邮编，对应客户信息中的收货邮编',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '录入人ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '录入订单的人',
  `SALES` varchar(32) DEFAULT NULL COMMENT '负责客户的销售人员，对应客户信息的销售人员',
  `SALES_ID` char(20) DEFAULT NULL,
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '客户订单是否显示单价（后台根据客户信息自动处理）',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '供应商编码，对界面隐藏',
  `FIRST_CREATE_TIME` datetime DEFAULT NULL COMMENT '初次录入系统的日期',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '正式形成订单日期（业务员正式提交日期）',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '订单所属账期',
  `STATE` varchar(8) DEFAULT NULL COMMENT '订单的业务状态  01---业务创建客户订单（业务临时保存订单，其他人无法查看）  02---业务确认客户订单（可以正式走流程，其他人可以查看）  03---业务取消客户订单（订单取消，所有订单明细全部取消，对账期订单，取消只能在采购确认之前，对预付，取消只能在财务收到预付款之前)  04---待业务确认交期(只对账期订单有效，本状态是采购修改交期后，显示的状态，等待业务确认交期，交期的概念针对订单明细，只要有一个订单明细需要确认交期，订单状态就为待交期确认)  05---待业务确认发货（货未备齐，待业务',
  `ACTIVE_STATE` varchar(8) DEFAULT 'ACTIVE' COMMENT '订单的活动状态  PAUSE---业务暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---业务激活订单（激活暂停的订单，业务操作）',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户订购单历史表';

#
# Dumping data for table t_cust_order_his
#


#
# Table structure for table t_cust_part_no_info
#

DROP TABLE IF EXISTS `t_cust_part_no_info`;
CREATE TABLE `t_cust_part_no_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '物料编号',
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编码',
  `STATE` varchar(8) NOT NULL COMMENT '状态  0----正式数据  1---临时数据（没有提交审批）  2---待审批数据  3---审批不通过  4---废弃数据',
  `PN_DESC` varchar(128) NOT NULL COMMENT '物料描述',
  `PRICE` float(8,4) NOT NULL COMMENT '单价',
  `PRICE_TAX` float(6,4) NOT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '创建人',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '创建人',
  `PART_NO` varchar(32) NOT NULL COMMENT '本公司的物料编号',
  `P_CAT_CODE` varchar(32) DEFAULT NULL COMMENT '本公司的大类',
  `P_CLS_CODE` varchar(32) DEFAULT NULL COMMENT '本公司的小类',
  `MIN_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '最小订单数量，缺省为0',
  `SAMPLE_CODE` varchar(32) DEFAULT NULL COMMENT '样品编号',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '供应商编码',
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户物料编号同本公司物料编号对应表';

#
# Dumping data for table t_cust_part_no_info
#


#
# Table structure for table t_cust_prepaid
#

DROP TABLE IF EXISTS `t_cust_prepaid`;
CREATE TABLE `t_cust_prepaid` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `PRE_PAID` varchar(3) DEFAULT NULL COMMENT '预付百分比',
  `REMINDER_DAY` int(11) DEFAULT NULL COMMENT '对货到付款，加提醒市场人员催款',
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户预付费表，关联客户的结算信息';

#
# Dumping data for table t_cust_prepaid
#


#
# Table structure for table t_customer_contact_info
#

DROP TABLE IF EXISTS `t_customer_contact_info`;
CREATE TABLE `t_customer_contact_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `CON_TYPE` varchar(2) DEFAULT NULL COMMENT '类别  1----联系人  2----收货人',
  `CON_NAME` varchar(32) NOT NULL COMMENT '联系人姓名',
  `CON_DUTY` varchar(32) NOT NULL COMMENT '联系人职务',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '联系人固定电话',
  `CON_MOBILE` varchar(16) DEFAULT NULL COMMENT '联系人移动电话',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '联系人传真',
  `CON_MAIL` varchar(128) DEFAULT NULL COMMENT '联系人邮箱',
  `CON_QQ` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `CON_MSN` varchar(32) DEFAULT NULL COMMENT 'MSN',
  `CON_OTHER` varchar(512) DEFAULT NULL COMMENT '联系人其他信息',
  `CON_ADDRESS` varchar(256) DEFAULT NULL COMMENT '收货地址，对收货人有效',
  `CON_ZIP` varchar(16) DEFAULT NULL COMMENT '收货邮编，对收货人有效',
  `IS_PRIMARY` varchar(2) DEFAULT NULL COMMENT '是否是主联系人，这里指是否是客户的采购人员  0----是  1---不是',
  `BASE_SEQID` int(11) NOT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户联系人信息及客户收货人信息';

#
# Dumping data for table t_customer_contact_info
#




#
# Table structure for table t_customer_oper_log
#

DROP TABLE IF EXISTS `t_customer_oper_log`;
CREATE TABLE `t_customer_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `OPER_TIME` datetime NOT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '操作类型  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(20) DEFAULT NULL COMMENT '操作对象  CUST_INFO  CUST_P/N  CUST_ORDER',
  `OPER_KEY` varchar(32) DEFAULT NULL COMMENT '操作关键字  对客户信息为C_CODE  对物料为C_PART_NO  对订单为订单号  对订单明细为订单明细序列号',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL COMMENT 'MEMO',
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户信息/订单 日志审批表';

#
# Dumping data for table t_customer_oper_log
#


#
# Table structure for table t_customer_oper_log_his
#

DROP TABLE IF EXISTS `t_customer_oper_log_his`;
CREATE TABLE `t_customer_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '序列号',
  `OPER_TIME` datetime NOT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '操作类型  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(20) DEFAULT NULL COMMENT '操作对象  CUST_INFO  CUST_P/N  CUST_ORDER',
  `OPER_KEY` varchar(32) DEFAULT NULL COMMENT '操作关键字  对客户信息为C_CODE  对物料为C_PART_NO  对订单为订单号  对订单明细为订单明细序列号',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户信息/订单 日志审批   历史表  规则为超过一年的历史信息进入历史表';

#
# Dumping data for table t_customer_oper_log_his
#


#
#  Foreign keys for table t_cust_account_period
#

ALTER TABLE `t_cust_account_period`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_cust_bank
#

ALTER TABLE `t_cust_bank`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_cust_order_detail
#

ALTER TABLE `t_cust_order_detail`
  ADD FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_cust_order` (`C_CODE`, `PO_NO`);

#
#  Foreign keys for table t_cust_prepaid
#

ALTER TABLE `t_cust_prepaid`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_customer_contact_info
#

ALTER TABLE `t_customer_contact_info`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
