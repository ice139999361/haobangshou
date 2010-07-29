-- MySQL dump 10.13  Distrib 5.1.46, for Win32 (ia32)
--
-- Host: 192.168.1.250    Database: hbs
-- ------------------------------------------------------
-- Server version	5.1.46-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `STAFF_ID` int(11) NOT NULL,
  `ACCOUNT` varchar(16) NOT NULL COMMENT '用户账号(UNIQUE)',
  `PASSWORD` varchar(32) NOT NULL COMMENT '密码',
  `LOGIN_TIME` datetime DEFAULT NULL COMMENT '登入时间',
  `LOGOUT_TIME` datetime DEFAULT NULL COMMENT '登出时间',
  `ENABLED` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效（ 有效非0； 无效0）',
  PRIMARY KEY (`STAFF_ID`),
  UNIQUE KEY `ACCOUNT` (`ACCOUNT`),
  CONSTRAINT `RLT_FK_STAFF_ID` FOREIGN KEY (`STAFF_ID`) REFERENCES `staff` (`STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='用户账号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `action`
--

DROP TABLE IF EXISTS `action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action` (
  `ACTIONS_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '所有操作ID(PK,自动生成)',
  `ACTION_ID` varchar(32) NOT NULL COMMENT '操作ID',
  `ACTION_NAME` varchar(128) NOT NULL COMMENT '操作名',
  `BUTTON_ID` varchar(32) NOT NULL COMMENT '对应的页面按钮的ID名',
  `DESCRIPTION` varchar(32) NOT NULL,
  UNIQUE KEY `ACTIONS_ID` (`ACTIONS_ID`,`ACTION_ID`(1))
) ENGINE=InnoDB AUTO_INCREMENT=10201 DEFAULT CHARSET=gbk COMMENT='操作表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `RESOURCE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源ID(PK,自动生成)',
  `ACTIONS_ID` int(11) DEFAULT NULL COMMENT '该资源页面的所有操作ID（FK->ACTION.ACTIONS_ID)',
  `RESOURCE_NAME` varchar(64) NOT NULL COMMENT '资源名',
  `DESCRIPTION` varchar(64) DEFAULT NULL COMMENT '描述',
  `URL_ADDRESS` varchar(256) DEFAULT NULL COMMENT '对应的url地址',
  `PARENT` int(11) NOT NULL DEFAULT '0' COMMENT '资源的父子关系，顶级为0',
  `IS_MENU` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RESOURCE_ID`),
  KEY `RLT_FK_ACTIONS_ID` (`ACTIONS_ID`),
  CONSTRAINT `RLT_FK_ACTIONS_ID` FOREIGN KEY (`ACTIONS_ID`) REFERENCES `action` (`ACTIONS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10201 DEFAULT CHARSET=gbk COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID(PK,自动生成)',
  `ROLE_NAME` varchar(64) NOT NULL COMMENT '角色名(UNIQUE)',
  `MEMO` varchar(256) DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `ROLE_NAME` (`ROLE_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_resource`
--

DROP TABLE IF EXISTS `role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_resource` (
  `ROLE_ID` int(11) NOT NULL COMMENT '角色ID(FK->ROLE.ROLE_ID)',
  `RESOURCE_ID` int(11) NOT NULL COMMENT '资源ID(FK->RESOURCE.ID)',
  `OPERATIONS` varchar(1024) NOT NULL COMMENT '操作（OPERATION_ID，有多个操作用半角逗号隔开.来自ACTION表）',
  KEY `RLT_ROLE_ID` (`ROLE_ID`),
  KEY `RLT_RESOURCE_ID` (`RESOURCE_ID`),
  CONSTRAINT `RLT_RESOURCE_ID` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `resource` (`RESOURCE_ID`),
  CONSTRAINT `RLT_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='角色权限对应表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `STAFF_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID(PK,自动生成)',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '用户名(UNIQUE)',
  `IDENTITY_NUMBER` varchar(32) DEFAULT NULL COMMENT '身份证号码',
  `GENDER` varchar(4) DEFAULT NULL COMMENT '性别',
  `BIRTH_DATE` varchar(16) DEFAULT NULL COMMENT '出生日期',
  `PHONE` varchar(32) DEFAULT NULL COMMENT '电话',
  `MOBILE` varchar(12) DEFAULT NULL COMMENT '手机',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `DUTY` varchar(32) DEFAULT NULL COMMENT '职务',
  `MEMO` varchar(256) DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`STAFF_ID`),
  UNIQUE KEY `STAFF_NAME` (`STAFF_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk COMMENT='用户基本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_adjust_info`
--

DROP TABLE IF EXISTS `t_adjust_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_adjust_info` (
  `APPLY_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请序列号',
  `STAFF_ID` varchar(32) DEFAULT NULL COMMENT '申请人ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '申请人',
  `APPLY_DATE` datetime DEFAULT NULL COMMENT '申请时间',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '物料的订单号（供应商的采购单）,目前为NULL',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '申请的公司调货PART_NO',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '调货物料的描述',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '仓库位置',
  `APPLY_AMOUNT` int(11) DEFAULT NULL COMMENT '申请的调货数量',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '物料的供应商编码',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '供应商简称',
  `APPLY_CONTENT` varchar(256) DEFAULT NULL COMMENT '申请说明',
  `FROM_CUST_CODE` varchar(32) DEFAULT NULL COMMENT '从那个客户编码调货',
  `FROM_C_NAME` varchar(64) DEFAULT NULL COMMENT '从客户简称',
  `TO_CUST_CODE` varchar(32) DEFAULT NULL COMMENT '调动那个客户code下',
  `TO_C_NAME` varchar(64) DEFAULT NULL COMMENT '到客户简称',
  `AUDIT_STAFF_ID` varchar(20) DEFAULT NULL COMMENT '审批人ID',
  `AUDIT_STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '审批人姓名',
  `AUDIT_DATE` datetime DEFAULT NULL COMMENT '审批时间',
  `AUDIT_AGREE` varchar(2) DEFAULT NULL COMMENT '审批是否同意  0---同意  1---不同意',
  `AUDIT_CONTENT` varchar(128) DEFAULT NULL COMMENT '审批的具体意见',
  PRIMARY KEY (`APPLY_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='调货申请表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_company_part_no`
--

DROP TABLE IF EXISTS `t_company_part_no`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_company_part_no` (
  `PART_NO` varchar(32) NOT NULL COMMENT '物料编号',
  `PN_DESC` varchar(128) NOT NULL COMMENT '物料描述',
  `PRICE` float(8,4) DEFAULT '0.0000' COMMENT '价格',
  `TAX_PRICE` float(8,4) DEFAULT '0.0000' COMMENT '含税价格',
  `CREATE_TIME` date NOT NULL COMMENT '创建时间',
  `P_CLS_CODE` varchar(20) DEFAULT NULL COMMENT '所属产品类别',
  `P_CAT_CODE` varchar(32) DEFAULT NULL COMMENT '废弃',
  PRIMARY KEY (`PART_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='公司物料信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_config_encode`
--

DROP TABLE IF EXISTS `t_config_encode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_config_encode` (
  `ENCODE_TYPE` varchar(128) NOT NULL COMMENT '编码类型',
  `ENCODE_KEY` varchar(128) NOT NULL COMMENT '编码项',
  `ENCODE_VALUE` varchar(1024) NOT NULL COMMENT '编码值',
  `ENCODE_DESC` varchar(256) DEFAULT NULL COMMENT '描述',
  `IS_VALID` varchar(1) NOT NULL DEFAULT '1' COMMENT '是否有效  1：有效  2：无效',
  `SORT_ID` varchar(3) NOT NULL COMMENT '排序号  同一个配置字典项的值按这个排序',
  PRIMARY KEY (`ENCODE_TYPE`,`ENCODE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='字典编码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_account_period`
--

DROP TABLE IF EXISTS `t_cust_account_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_account_period` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `ACCOUNT_TYPE` varchar(2) NOT NULL COMMENT '账期类型  1---月结  2---天结',
  `ACCOUNT_PERIOD` varchar(20) NOT NULL COMMENT '客户的账期设置，账期结算有效，如果月结，是月数，如果天结是天数',
  `PERIOD_START` varchar(8) NOT NULL COMMENT '账期的起始日，账期结算有效',
  `ACCOUNT_DAY` varchar(2) NOT NULL COMMENT '对账日，账期结束后的第几日，账期结算有效',
  `SETTLEMENT_DAY` varchar(4) NOT NULL DEFAULT '' COMMENT '结算日，账期结束的第几日，须大于对账日，对账期结算有效',
  `MAX_MONEY` float(12,2) DEFAULT NULL COMMENT '客户账期的最大交易金额，账期结算有效',
  `REMINDER_DAY` varchar(20) NOT NULL COMMENT '提醒设置，对账日和结算日提前几天提醒',
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`),
  CONSTRAINT `t_cust_account_period_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=262 DEFAULT CHARSET=gb2312 COMMENT='客户账期设置表，关联客户的结算信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_bank`
--

DROP TABLE IF EXISTS `t_cust_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_bank` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `ACCOUNT_NAME` varchar(256) NOT NULL DEFAULT '' COMMENT '开户户名',
  `ACCOUNT_BANK` varchar(256) DEFAULT NULL COMMENT '开户行',
  `ACCOUNT` varchar(128) DEFAULT NULL COMMENT '帐号',
  `BASE_SEQID` int(11) NOT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`),
  CONSTRAINT `t_cust_bank_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`),
  CONSTRAINT `t_cust_bank_ibfk_2` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=gb2312 COMMENT='客户银行信息，，关联客户的银行信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_order`
--

DROP TABLE IF EXISTS `t_cust_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_order` (
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编码',
  `PO_NO` varchar(50) NOT NULL DEFAULT '' COMMENT '客户订单编号',
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
  `SETTLEMENT_DAY` varchar(16) DEFAULT NULL COMMENT '月结类型，对月结有效',
  `SETTLEMENT_DAYDESC` varchar(32) DEFAULT NULL COMMENT '月结描述',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户订购单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_order_detail`
--

DROP TABLE IF EXISTS `t_cust_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_order_detail` (
  `ORDER_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一的SEQID（序列号）',
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编号',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '简称',
  `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '0' COMMENT '客户订单类型  0----客户订单  1---客户退货单',
  `PO_NO` varchar(50) NOT NULL DEFAULT '' COMMENT '客户订单号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '客户物料编号，对照T_CUST_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号，对照T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '物料描述，对照T_CUST_PART_NO_INFO',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '单价',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '税率',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如有的客户物料中有批次概念，可以填入该字段',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT '一般备注',
  `AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '订货数量',
  `MONEY` decimal(12,4) DEFAULT NULL COMMENT '总金额',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '已经发货数量',
  `COMM_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '常规库存出货数目',
  `SELF_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '本客户备货发送数目',
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
  `CONTACT_FEE` float(6,4) DEFAULT NULL COMMENT '合同费',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  `ORDERAMOUNT` int(11) DEFAULT '0' COMMENT '已下订单数量',
  PRIMARY KEY (`ORDER_SEQID`),
  KEY `C_CODE` (`C_CODE`,`PO_NO`),
  CONSTRAINT `t_cust_order_detail_ibfk_1` FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_cust_order` (`C_CODE`, `PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户订单物料明细表，关联订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_order_detail_his`
--

DROP TABLE IF EXISTS `t_cust_order_detail_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_order_detail_his` (
  `ORDER_SEQID` int(11) NOT NULL COMMENT '唯一的SEQID（序列号）',
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编号',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '简称',
  `PO_NO` varchar(16) NOT NULL COMMENT '客户订单号',
  `PO_NO_TYPE` varchar(2) DEFAULT NULL COMMENT '客户订单类型  0----客户订单  1---客户退货单',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '客户物料编号，对照T_CUST_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号，对照T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '物料描述，对照T_CUST_PART_NO_INFO',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '单价',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '税率',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如有的客户物料中有批次概念，可以填入该字段',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT '一般备注',
  `AMOUNT` int(11) NOT NULL COMMENT '订货数量',
  `MONEY` decimal(12,4) DEFAULT NULL COMMENT '总金额',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '已经发货数量',
  `COMM_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '常规库存出货数目',
  `SELF_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '本客户备货发送数目',
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
  `SALES` varchar(32) DEFAULT NULL COMMENT '销售人员姓名（业务员）',
  `CONTACT_FEE` float(6,4) DEFAULT NULL COMMENT '合同费',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  `ORDERAMOUNT` int(11) DEFAULT '0' COMMENT '已下订单数量'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户订单物料明细历史表，关联订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_order_his`
--

DROP TABLE IF EXISTS `t_cust_order_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `SETTLEMENT_DAY` varchar(16) DEFAULT NULL COMMENT '月结类型，对月结有效',
  `SETTLEMENT_DAYDESC` varchar(32) DEFAULT NULL COMMENT '月结描述',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户订购单历史表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_part_no_info`
--

DROP TABLE IF EXISTS `t_cust_part_no_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_part_no_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '物料编号',
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编码',
  `STATE` varchar(8) NOT NULL COMMENT '状态  0----正式数据  1---临时数据（没有提交审批）  2---待审批数据  3---审批不通过  4---废弃数据',
  `PN_DESC` varchar(128) NOT NULL COMMENT '物料描述',
  `PRICE` decimal(12,10) DEFAULT '0.0000000000' COMMENT '单价',
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
  `IS_PRICECHANGE` varchar(2) NOT NULL DEFAULT '0' COMMENT '价格是否变动过  0--没有  1--是',
  `C_SHORT_NAME` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=438 DEFAULT CHARSET=gb2312 COMMENT='客户物料编号同本公司物料编号对应表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_prepaid`
--

DROP TABLE IF EXISTS `t_cust_prepaid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_prepaid` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `PRE_PAID` varchar(3) DEFAULT NULL COMMENT '预付百分比',
  `REMINDER_DAY` int(11) DEFAULT NULL COMMENT '对货到付款，加提醒市场人员催款',
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`),
  CONSTRAINT `t_cust_prepaid_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=gb2312 COMMENT='客户预付费表，关联客户的结算信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_send_invoice`
--

DROP TABLE IF EXISTS `t_cust_send_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_send_invoice` (
  `INVOICE_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '操作人ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '操作人姓名',
  `CREATE_TIME` datetime NOT NULL COMMENT '开发票时间',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '送货单号',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '送货日期',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '客户编码',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '简称',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '公司物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '客户物料编号',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '物料数量',
  `ALL_MONEY` decimal(10,2) DEFAULT NULL COMMENT '总含税金额',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '本次开票金额',
  `LEFT_MONEY` decimal(10,2) DEFAULT NULL COMMENT '未开票金额',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '备注，主要填写发票号，也可以填写其他内容',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商发票记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_send_invoice_his`
--

DROP TABLE IF EXISTS `t_cust_send_invoice_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_send_invoice_his` (
  `INVOICE_SEQID` int(11) NOT NULL COMMENT '序列号',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '操作人ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '操作人姓名',
  `CREATE_TIME` datetime NOT NULL COMMENT '开发票时间',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '送货单号',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '送货日期',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '客户编码',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '简称',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '公司物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '客户物料编号',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '物料数量',
  `ALL_MONEY` decimal(10,2) DEFAULT NULL COMMENT '总含税金额',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '本次开票金额',
  `LEFT_MONEY` decimal(10,2) DEFAULT NULL COMMENT '未开票金额',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '备注，主要填写发票号，也可以填写其他内容',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商发票记录表历史';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_customer_contact_info`
--

DROP TABLE IF EXISTS `t_customer_contact_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_contact_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `CON_TYPE` varchar(2) DEFAULT NULL COMMENT '类别  1----联系人  2----收货人',
  `CON_NAME` varchar(32) NOT NULL COMMENT '联系人姓名',
  `CON_DUTY` varchar(32) NOT NULL COMMENT '联系人职务',
  `CON_TEL` varchar(64) DEFAULT NULL COMMENT '联系人固定电话',
  `CON_MOBILE` varchar(32) DEFAULT NULL COMMENT '联系人移动电话',
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
  KEY `BASE_SEQID` (`BASE_SEQID`),
  CONSTRAINT `t_customer_contact_info_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=776 DEFAULT CHARSET=gb2312 COMMENT='客户联系人信息及客户收货人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_customer_info`
--

DROP TABLE IF EXISTS `t_customer_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_info` (
  `BASE_SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `C_CODE` varchar(32) NOT NULL COMMENT '客户编码,格式GC0001',
  `STATE` varchar(2) NOT NULL COMMENT '客户状态  0----正式数据  1---临时数据（没有提交审批）  2---待审批数据  3---审批不通过  4---废弃数据  5--系统锁定',
  `SHORT_NAME` varchar(32) NOT NULL COMMENT '客户简称',
  `EN_SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '英文简称',
  `ALL_NAME` varchar(256) NOT NULL COMMENT '公司中文名称',
  `EN_NAME` varchar(256) DEFAULT NULL COMMENT '公司英文名称',
  `ADDRESS` varchar(256) DEFAULT NULL COMMENT '客户公司地址',
  `EN_ADDRESS` varchar(256) DEFAULT NULL COMMENT '公司英文地址',
  `C_TYPE` varchar(32) DEFAULT NULL COMMENT '性质：如台资，港资等',
  `C_SCALE` varchar(32) DEFAULT NULL COMMENT '规模，1-500人，大企业等',
  `WEBSITE` varchar(128) DEFAULT NULL COMMENT '客户公司网址',
  `REPRESENTATIVE` varchar(32) DEFAULT NULL COMMENT '客户法人代表',
  `TAX_CODE` char(128) DEFAULT NULL COMMENT '客户纳税人识别号',
  `COMPANY_BRANCH` varchar(64) DEFAULT NULL COMMENT '对应的分公司或分支机构，从字典表选取或手工输入',
  `CREDIT_RATE` varchar(8) DEFAULT NULL COMMENT '客户信用度，参见字典表定义  ',
  `CREDIT_DESC` char(32) DEFAULT NULL COMMENT '信用等级描述',
  `IMPORTANT_CODE` varchar(8) DEFAULT NULL COMMENT '客户的重要程度，参见字典表定义',
  `IMPORTANT_DESC` char(32) DEFAULT NULL COMMENT '客户重要程度描述',
  `SETTLEMENT_TYPE` varchar(8) DEFAULT NULL COMMENT '客户结算类型，参见字典表定义  1---账期介绍  2---预付X%,剩余货到付款  3---预付X%剩余款到发货',
  `SETTLEMENT_DESC` varchar(32) DEFAULT NULL COMMENT '结算方式描述',
  `CURRENCY` varchar(2) DEFAULT NULL COMMENT '客户结算币种，参见字典表  ',
  `CURRENCY_DESC` varchar(32) DEFAULT NULL COMMENT '结算币种描述',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '销售人员ID',
  `STAFF_NAME` varchar(20) DEFAULT NULL COMMENT '销售人员名字',
  `COMM_DESC` varchar(512) DEFAULT NULL COMMENT '客户备注',
  `VENDOR_CODE` varchar(8) DEFAULT NULL COMMENT '供应商编码',
  `CONTACT_FEE` float(6,4) DEFAULT NULL COMMENT '合同费，百分值',
  `IS_SHOW_PRICE` varchar(2) NOT NULL COMMENT '发货单是否显示单价  0---不显示  1---显示',
  `TAX_RATE` float(6,4) DEFAULT '0.0000' COMMENT '交易税率，百分值',
  `ASS_STAFF_ID` varchar(20) DEFAULT NULL COMMENT '对应的业务部助理ID',
  `ASS_STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '对应的业务部助理姓名',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '录入时间',
  `SALE_TYPE` varchar(32) DEFAULT NULL COMMENT '企业类型',
  PRIMARY KEY (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=gb2312 COMMENT='客户信息表（正式）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_customer_oper_log`
--

DROP TABLE IF EXISTS `t_customer_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `OPER_TIME` datetime NOT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '操作类型  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(128) DEFAULT NULL COMMENT '操作对象  CUST_INFO  CUST_P/N  CUST_ORDER',
  `OPER_KEY` varchar(128) DEFAULT NULL COMMENT '操作关键字  对客户信息为C_CODE  对物料为C_PART_NO  对订单为订单号  对订单明细为订单明细序列号',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL COMMENT 'MEMO',
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=716 DEFAULT CHARSET=gb2312 COMMENT='客户信息/订单 日志审批表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_customer_oper_log_his`
--

DROP TABLE IF EXISTS `t_customer_oper_log_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '序列号',
  `OPER_TIME` datetime NOT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '操作类型  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(128) DEFAULT NULL COMMENT '操作对象  CUST_INFO  CUST_P/N  CUST_ORDER',
  `OPER_KEY` varchar(128) DEFAULT NULL COMMENT '操作关键字  对客户信息为C_CODE  对物料为C_PART_NO  对订单为订单号  对订单明细为订单明细序列号',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户信息/订单 日志审批   历史表  规则为超过一年的历史信息进入历史表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_customer_settlement`
--

DROP TABLE IF EXISTS `t_customer_settlement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_settlement` (
  `C_CODE` varchar(8) DEFAULT NULL COMMENT '编码',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '简称',
  `SETTLEMENT_TYPE` varchar(2) NOT NULL COMMENT '结算方式   1--月结  2---预付X%,剩余货到付款  3---预付X%剩余款到发货',
  `ABSTRACT` varchar(16) NOT NULL COMMENT '摘要  对月结为账期  对非月结为订单号',
  `TOTAL_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `NEED_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '待结算金额',
  `SET_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '已结算金额',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务状态  1 已对账未结算  2部分结算  3已结算',
  `SALES_ID` varchar(20) DEFAULT NULL COMMENT '销售人员ID',
  `SALES_NAME` varchar(32) DEFAULT NULL COMMENT '销售姓名',
  `ASS_ID` varchar(20) DEFAULT NULL COMMENT '业务助理ID',
  `ASS_NAME` varchar(32) DEFAULT NULL COMMENT '业务助理姓名',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作员ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作员姓名'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户财务结算表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_period_spec_memo`
--

DROP TABLE IF EXISTS `t_period_spec_memo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_period_spec_memo` (
  `COMM_CODE` varchar(32) NOT NULL COMMENT '客户或供应商编码',
  `COMM_SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '客户或供应商简称',
  `COMM_TYPE` int(11) NOT NULL COMMENT '表示供应商还是客户  0---客户  1---供应商',
  `CREATE_TIME` datetime NOT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(32) NOT NULL COMMENT '操作者ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '操作者姓名',
  `MEMO` varchar(512) NOT NULL COMMENT '账期结算备注'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='财务账期结算备注表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_product_class`
--

DROP TABLE IF EXISTS `t_product_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_class` (
  `P_CLS_CODE` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品类别编码',
  `P_CLS_NAME` varchar(256) NOT NULL COMMENT '产品类别名称',
  `P_CLS_DESC` varchar(256) DEFAULT NULL COMMENT '产品类别特征描述',
  `PARENT_CLS_CODE` int(11) NOT NULL DEFAULT '0' COMMENT '本类别所属父类别 ；为0空说明没有父类别，是最大类别',
  `P_CLS_LEVEL` varchar(2) NOT NULL COMMENT '产品分层级别，一级的所属父类为0  级别可为：1---一级 /2---二级/3---三级/4---四级  等需定义到字典表中',
  PRIMARY KEY (`P_CLS_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=1101 DEFAULT CHARSET=gbk COMMENT='产品分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_sys_sequence`
--

DROP TABLE IF EXISTS `t_sys_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_sequence` (
  `SEQ_TYPE` varchar(16) NOT NULL COMMENT 'SEQENCE类型',
  `SEQ_KEY` varchar(16) NOT NULL COMMENT 'KEY值',
  `SEQ_VALUE` int(11) NOT NULL DEFAULT '0' COMMENT '当前序列值',
  `SEQ_LENGTH` int(11) NOT NULL DEFAULT '2' COMMENT '序列值要求长度',
  `SEQ_PREFIX` varchar(16) NOT NULL COMMENT '前缀',
  `SEQ_MIDDLE` varchar(16) NOT NULL COMMENT '中段'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='系统使用的特殊sequenceid的获取和存储';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_system_config`
--

DROP TABLE IF EXISTS `t_system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_system_config` (
  `CONFIG_NAME` varchar(128) NOT NULL COMMENT '参数名称',
  `CONFIG_VALUE` varchar(256) NOT NULL COMMENT '参数值',
  `VALUE_TYPE` int(11) NOT NULL COMMENT '取值类型，  0表示整型；  1表示字符串型；  2表示布尔型；  3表示日期型；',
  `VALUE_RANGE` varchar(128) DEFAULT NULL COMMENT '取值范围',
  `CONFIG_DESC` varchar(128) DEFAULT NULL COMMENT '参数描述',
  `VISIBLE_FLAG` varchar(1) DEFAULT 'Y' COMMENT '是否可见：     N－不可见     Y－可见',
  `CONFIG_REGION` varchar(128) DEFAULT NULL COMMENT '所属站点，ALL',
  `SUB_SYSTEM` varbinary(32) DEFAULT NULL COMMENT '归属子系统名称',
  `LAST_UPDATE` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`CONFIG_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='系统参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_account_period`
--

DROP TABLE IF EXISTS `t_vendor_account_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_account_period` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_TYPE` varchar(2) NOT NULL COMMENT '账期类型  1---月结  2---天结',
  `ACCOUNT_PERIOD` varchar(32) NOT NULL COMMENT '供应商的账期设置，账期结算有效，如果月结，是月数，如果天结是天数',
  `PERIOD_START` varchar(8) DEFAULT NULL COMMENT '账期的起始日，账期结算有效,格式为：YYYYMMDD',
  `ACCOUNT_DAY` varchar(2) NOT NULL COMMENT '对账日，账期结束后的第几日，账期结算有效',
  `SETTLEMENT_DAY` varchar(4) NOT NULL DEFAULT '' COMMENT '结算日，账期结束的第几日，须大于对账日，对账期结算有效',
  `MAX_MONEY` int(11) NOT NULL DEFAULT '0' COMMENT '账期的最大交易金额，账期结算有效,对供应商无用',
  `REMINDER_DAY` varchar(20) NOT NULL COMMENT '提醒设置，对账日和结算日提前几天提醒',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_ACCOUNT_PERIOD` (`BASE_SEQID`),
  CONSTRAINT `t_vendor_account_period_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=gb2312 COMMENT='供应商账期设置表，关联供应商的结算信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_bank`
--

DROP TABLE IF EXISTS `t_vendor_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_bank` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NAME` varchar(256) NOT NULL DEFAULT '' COMMENT '开户户名',
  `ACCOUNT_BANK` varchar(256) DEFAULT NULL COMMENT '开户行',
  `ACCOUNT` varchar(256) DEFAULT NULL COMMENT '帐号',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_BANK` (`BASE_SEQID`),
  CONSTRAINT `t_vendor_bank_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=gb2312 COMMENT='供应商银行信息，，关联供应商的银行信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_contact_info`
--

DROP TABLE IF EXISTS `t_vendor_contact_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `RLT_VENDOR_CONTACT_INFO` (`BASE_SEQID`),
  CONSTRAINT `t_vendor_contact_info_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=gb2312 COMMENT='联系人信息及发货人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_info`
--

DROP TABLE IF EXISTS `t_vendor_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_info` (
  `BASE_SEQID` int(11) NOT NULL AUTO_INCREMENT,
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
  `IMPORTANT_CODE` varchar(8) DEFAULT NULL COMMENT '重要程度，参见字典表定义',
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
  `TAX_RATE` decimal(6,4) DEFAULT '0.0000' COMMENT '税率，百分值',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '录入时间',
  `SALE_TYPE` varchar(32) DEFAULT NULL COMMENT '企业类型',
  PRIMARY KEY (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=gb2312 COMMENT='供应商信息表（正式）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_oper_log`
--

DROP TABLE IF EXISTS `t_vendor_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `OPER_TIME` date DEFAULT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '操作类型  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(128) DEFAULT NULL COMMENT '操作对象  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER',
  `OPER_KEY` varchar(128) DEFAULT NULL COMMENT '操作的具体对象：  对供应商信息为C_CODE  对供应商物料为C_PART_NO  对供应商订单为CPO_NO',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=gb2312 COMMENT='日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_oper_log_his`
--

DROP TABLE IF EXISTS `t_vendor_oper_log_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '序列号',
  `OPER_TIME` date DEFAULT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '操作类型  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(128) DEFAULT NULL COMMENT '操作对象  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER',
  `OPER_KEY` varchar(128) DEFAULT NULL COMMENT '操作的具体对象：  对供应商信息为C_CODE  对供应商物料为C_PART_NO  对供应商订单为CPO_NO',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='日志历史表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_order`
--

DROP TABLE IF EXISTS `t_vendor_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作人员ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '录入订单的人',
  `SALES` varchar(32) DEFAULT NULL COMMENT '负责客户的销售人员，对应客户信息的销售人员  负责采购的采购人员，对应供应商的采购人员',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '订单是否显示单价（后台根据供应商信息自动处理）',
  `ORDER_TIME` datetime DEFAULT NULL COMMENT '正式形成订单日期（业务员正式提交日期）',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '订单所属账期',
  `STATE` varchar(8) DEFAULT NULL COMMENT '订单业务状态01---采购创建采购订单（采购临时保存订单，其他人无法查看）02---采购确认采购订单,待收货入库（可以正式走流程，其他人可以查看）03---采购取消采购订单（订单取消，所有订单明细全部取消，对账期订单)04---待交期确认(账期订单)00---关闭采购订单60----部分入库61----全部入库',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '订单活动状态：  PAUSE---采购暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---采购激活订单（激活暂停的订单，采购操作）',
  `CUST_C_CODE` varchar(32) DEFAULT NULL COMMENT '针对0----客户采购单，3-- 特定客户备货有效',
  `HASTEN_REMINDER` int(11) DEFAULT NULL COMMENT '追货提醒/天',
  `SETTLEMENT_DAY` varchar(16) DEFAULT NULL COMMENT '月结类型，对月结有效',
  `SETTLEMENT_DAYDESC` varchar(32) DEFAULT NULL COMMENT '月结描述',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商采购单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_order_detail`
--

DROP TABLE IF EXISTS `t_vendor_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_order_detail` (
  `ORDER_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一的seqid（序列号）',
  `C_CODE` varchar(32) NOT NULL,
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '简称',
  `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '' COMMENT '供应商订单类型0----客户采购单1---退货单2---常规备货采购单3--特定客户备货采购单',
  `PO_NO` varchar(16) NOT NULL,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商物料编号，对照T_VENDOR_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号，对照T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '物料描述',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '单价',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '税率',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如有的客户物料中有批次概念，可以填入该字段',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT '一般备注',
  `AMOUNT` int(11) NOT NULL COMMENT '订货数量',
  `MONEY` decimal(10,2) DEFAULT NULL COMMENT '金额',
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
  `HASTEN_REMINDER` int(11) DEFAULT NULL COMMENT '追货提醒/天',
  PRIMARY KEY (`ORDER_SEQID`),
  KEY `RLT_VENDOR_ORDER_PART_NO` (`C_CODE`,`PO_NO`),
  CONSTRAINT `RLT_VENDOR_ORDER_PART_NO` FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_vendor_order` (`C_CODE`, `PO_NO`),
  CONSTRAINT `t_vendor_order_detail_ibfk_1` FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_vendor_order` (`C_CODE`, `PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商采购订单物料明细表，关联订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_order_detail_his`
--

DROP TABLE IF EXISTS `t_vendor_order_detail_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_order_detail_his` (
  `ORDER_SEQID` int(11) NOT NULL COMMENT '唯一的seqid（序列号）',
  `C_CODE` varchar(32) NOT NULL,
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '简称',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '供应商订单类型  0----订单  1---退货单',
  `PO_NO` varchar(16) NOT NULL,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商物料编号，对照T_VENDOR_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号，对照T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '物料描述',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '单价',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '税率',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如有的客户物料中有批次概念，可以填入该字段',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT '一般备注',
  `AMOUNT` int(11) NOT NULL COMMENT '订货数量',
  `MONEY` decimal(10,2) DEFAULT NULL COMMENT '金额',
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_order_his`
--

DROP TABLE IF EXISTS `t_vendor_order_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作人员ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '录入订单的人',
  `SALES` varchar(32) DEFAULT NULL COMMENT '负责客户的销售人员，对应客户信息的销售人员  负责采购的采购人员，对应供应商的采购人员',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '订单是否显示单价（后台根据供应商信息自动处理）',
  `ORDER_TIME` datetime DEFAULT NULL COMMENT '正式形成订单日期（业务员正式提交日期）',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '订单所属账期',
  `STATE` varchar(8) DEFAULT NULL COMMENT '订单业务状态  01---采购创建采购订单（采购临时保存订单，其他人无法查看）  02---采购确认采购订单（可以正式走流程，其他人可以查看）  03---采购取消采购订单（订单取消，所有订单明细全部取消，对账期订单)  04---采购确认交期(交期确认后，采购单不再修改)  00---关闭采购订单  60----部分入库  61----全部入库',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '订单活动状态：  PAUSE---采购暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---采购激活订单（激活暂停的订单，采购操作）',
  `SETTLEMENT_DAY` varchar(16) DEFAULT NULL COMMENT '月结类型，对月结有效',
  `SETTLEMENT_DAYDESC` varchar(32) DEFAULT NULL COMMENT '月结描述',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商采购单历史表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_part_no_info`
--

DROP TABLE IF EXISTS `t_vendor_part_no_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_part_no_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商物料编号',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `STATE` varchar(8) NOT NULL COMMENT '状态  0----正式数据  1---临时数据（没有提交审批）  2---待审批数据  3---审批不通过  4---废弃数据',
  `PN_DESC` varchar(128) NOT NULL COMMENT '物料描述',
  `PRICE` decimal(12,10) NOT NULL DEFAULT '0.0000000000' COMMENT '单价',
  `PRICE_TAX` decimal(6,4) DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  `CREATE_DATE` date DEFAULT NULL COMMENT '创建时间',
  `STAFF_ID` varchar(32) DEFAULT NULL COMMENT '创建人ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '创建人',
  `PART_NO` varchar(32) NOT NULL COMMENT '本公司的物料编号',
  `P_CAT_CODE` varchar(32) DEFAULT NULL COMMENT '本公司的大类',
  `P_CLS_CODE` varchar(32) DEFAULT NULL COMMENT '本公司的小类',
  `MIN_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '最小订单数量，缺省为0',
  `MIN_PACKAGE` int(11) DEFAULT NULL COMMENT '最小包装单位',
  `SAMPLE_CODE` varchar(32) DEFAULT NULL COMMENT '样品编码',
  `IS_PRICECHANGE` varchar(2) NOT NULL DEFAULT '0' COMMENT '价格是否变动过  0--没有  1--是',
  `C_SHORT_NAME` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=459 DEFAULT CHARSET=gb2312 COMMENT=' 供应商物料编号同本公司物料编号对应表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_prepaid`
--

DROP TABLE IF EXISTS `t_vendor_prepaid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_prepaid` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `PRE_PAID` varchar(3) DEFAULT NULL COMMENT '预付百分比',
  `REMINDER_DAY` varchar(3) DEFAULT NULL COMMENT '提醒人,对供应商无效',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_PREPAID` (`BASE_SEQID`),
  CONSTRAINT `t_vendor_prepaid_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=gb2312 COMMENT='供应商预付费表，关联供应商的结算信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_receive_invoice`
--

DROP TABLE IF EXISTS `t_vendor_receive_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_receive_invoice` (
  `INVOICE_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '操作人ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '操作人姓名',
  `CREATE_TIME` datetime NOT NULL COMMENT '开发票时间',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '送货单号',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '送货日期',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '供应商编码',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '简称',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '公司物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '供应商物料编号',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '物料数量',
  `ALL_MONEY` decimal(10,2) DEFAULT NULL COMMENT '总含税金额',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '本次开票金额',
  `LEFT_MONEY` decimal(10,2) DEFAULT NULL COMMENT '未开票金额',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '备注，主要填写发票号，也可以填写其他内容',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商发票记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_receive_invoice_his`
--

DROP TABLE IF EXISTS `t_vendor_receive_invoice_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_receive_invoice_his` (
  `INVOICE_SEQID` int(11) NOT NULL COMMENT '序列号',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '操作人ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '操作人姓名',
  `CREATE_TIME` datetime NOT NULL COMMENT '开发票时间',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '送货单号',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '送货日期',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '供应商编码',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '简称',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '公司物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '供应商物料编号',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '物料数量',
  `ALL_MONEY` decimal(10,2) DEFAULT NULL COMMENT '总含税金额',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '本次开票金额',
  `LEFT_MONEY` decimal(10,2) DEFAULT NULL COMMENT '未开票金额',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '备注，主要填写发票号，也可以填写其他内容',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商发票记录表历史';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_settlement`
--

DROP TABLE IF EXISTS `t_vendor_settlement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_settlement` (
  `C_CODE` varchar(8) DEFAULT NULL COMMENT '编码',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '简称',
  `SETTLEMENT_TYPE` varchar(2) NOT NULL COMMENT '结算方式   1--月结  2---预付X%,剩余货到付款  3---预付X%剩余款到发货',
  `ABSTRACT` varchar(16) NOT NULL COMMENT '摘要  对月结为账期  对非月结为订单号',
  `TOTAL_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `NEED_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '待结算金额',
  `SET_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '已结算金额',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务状态  1 已对账未结算  2部分结算  3已结算',
  `PURCHASE_ID` varchar(20) DEFAULT NULL COMMENT '采购员ID',
  `PURCHASE_NAME` varchar(32) DEFAULT NULL COMMENT '采购员姓名',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作员ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作员姓名'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商财务结算表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_waittask_config_info`
--

DROP TABLE IF EXISTS `t_waittask_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_waittask_config_info` (
  `CONFIG_ID` varchar(20) NOT NULL COMMENT '配置编号',
  `TASK_TYPE` varchar(3) NOT NULL COMMENT '待办类型:   0----业务待办  1---提醒待办  ',
  `BUSINESS_TYPE` varchar(64) NOT NULL COMMENT '具体的业务类型',
  `URL` varchar(512) NOT NULL COMMENT '待办的连接URL',
  `COMMENTS` varchar(128) NOT NULL COMMENT '待办描述',
  `SYSTEM_NAME` varchar(32) DEFAULT NULL COMMENT '子系统名称',
  `ROLE_ID` varchar(20) DEFAULT NULL COMMENT '待办处理人的角色ID',
  PRIMARY KEY (`CONFIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='待办信息配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_waittask_info`
--

DROP TABLE IF EXISTS `t_waittask_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_waittask_info` (
  `TASK_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '待办编号',
  `TASK_TYPE` varchar(30) NOT NULL COMMENT '待办类型:   0----业务待办  1---提醒待办 ',
  `BUSINESS_KEY` varchar(128) NOT NULL COMMENT '业务关键字',
  `BUSINESS_TYPE` varchar(30) NOT NULL COMMENT '业务处理类型',
  `ROLE_ID` varchar(20) DEFAULT NULL COMMENT '接受的角色',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '接受的人',
  `URL` varchar(512) NOT NULL COMMENT '申请处理的URL',
  `COMMENTS` varchar(128) NOT NULL COMMENT '待办处理描述',
  `PARAM` varchar(64) DEFAULT NULL COMMENT '可能的参数',
  `SYSTEM_NAME` varchar(32) DEFAULT NULL COMMENT '系统名称',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `EXPIRE_TIME` datetime DEFAULT NULL COMMENT '提醒待办结束时间，对提醒类待办有效，结束后，系统自动清除提醒待办',
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=596 DEFAULT CHARSET=gb2312 COMMENT='待办基本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_info`
--

DROP TABLE IF EXISTS `t_warehouse_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312 COMMENT='仓库信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_info_his`
--

DROP TABLE IF EXISTS `t_warehouse_info_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_oper_log`
--

DROP TABLE IF EXISTS `t_warehouse_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `OPER_TIME` date NOT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `OPER_OBJECT` varchar(32) NOT NULL DEFAULT '' COMMENT '操作的主题  入库单  出库单',
  `OPER_KEY` varchar(128) NOT NULL DEFAULT '' COMMENT '关键字，主题是入库单，则为供应商送货单号；出库单则为出库单号',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '操作类型  创建  修改  取消  终止  对于审批则为  同意  不同意  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL,
  `STAFF_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gb2312 COMMENT='仓库操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_oper_log_his`
--

DROP TABLE IF EXISTS `t_warehouse_oper_log_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '序列号',
  `OPER_TIME` date NOT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `OPER_OBJECT` varchar(32) NOT NULL DEFAULT '' COMMENT '操作的主题  入库单  出库单',
  `OPER_KEY` varchar(128) NOT NULL DEFAULT '' COMMENT '关键字，主题是入库单，则为供应商送货单号；出库单则为出库单号',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '操作类型  创建  修改  取消  终止  对于审批则为  同意  不同意  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL,
  `STAFF_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='仓库操作日志表历史';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_receive`
--

DROP TABLE IF EXISTS `t_warehouse_receive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_receive` (
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '供应商入库单号（供应商送货单号）',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '供应商简称',
  `PO_NO_DATE` date DEFAULT NULL COMMENT '供应商单据日期',
  `ARRAY_DATE` date NOT NULL COMMENT '货物到达日期',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '仓库位置',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '操作员',
  `OPER_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  `RECEIVE_DESC` varchar(256) NOT NULL COMMENT '收货单备注',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '所属账期',
  `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '收货单（入库单）状态  01---仓管创建入库单（临时保存入库单，其他人无法查看）  02---仓管确认入库单（入库数据证实确认，其他人可以查看）  03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)  00---关闭入库单  31---财务确认付款',
  `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '活动状态  PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）  ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）',
  `FINANCE_STATE` varchar(2) NOT NULL DEFAULT '0' COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '供应商订单类型  0----入库单  1---退货单',
  `SETTLEMENT_TYPE` varchar(8) DEFAULT NULL COMMENT '结算方式',
  PRIMARY KEY (`REC_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='入库单或收货单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_receive_detail`
--

DROP TABLE IF EXISTS `t_warehouse_receive_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_receive_detail` (
  `REC_DETAIL_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '入库明细SEQID,序列号',
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '供应商送货单号',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '供应商简称',
  `PO_NO_DATE` date DEFAULT NULL COMMENT '供应商单据日期',
  `ARRAY_DATE` date DEFAULT NULL COMMENT '货物到达日期',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '仓库位置',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '送货单对应的采购单号',
  `SETTLEMENT_TYPE` varchar(8) NOT NULL DEFAULT '' COMMENT '结算方式',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司的物料编号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商的物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `C_PRICE` decimal(12,10) DEFAULT '0.0000000000' COMMENT '采购的单价',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '税率',
  `AMOUNT` int(11) NOT NULL COMMENT '本次送货数量',
  `CUR_MONEY` decimal(10,2) DEFAULT '0.00' COMMENT '本次送货金额',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '所属账期',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '供应商订单类型  0---入库单  1---退货单',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作员ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作员姓名',
  `OPER_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '收货单（入库单）状态01---仓管创建入库单（临时保存入库单，其他人无法查看）02---仓管确认入库单（入库数据证实确认，其他人可以查看）03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)00---关闭入库单31---财务确认付款',
  `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '活动状态PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）',
  `FINANCE_PERIOD` varchar(8) DEFAULT NULL COMMENT '财务确认的账期或财务调整的账期',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊描述，针对客户订单中有批次的批次号',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  `ORDER_SEQID` varchar(12) DEFAULT NULL COMMENT '对应供应商订单明细seqid',
  PRIMARY KEY (`REC_DETAIL_SEQID`),
  KEY `RLT_RECEIVE_DETAIL` (`REC_PO_NO`),
  CONSTRAINT `RLT_RECEIVE_DETAIL` FOREIGN KEY (`REC_PO_NO`) REFERENCES `t_warehouse_receive` (`REC_PO_NO`),
  CONSTRAINT `t_warehouse_receive_detail_ibfk_1` FOREIGN KEY (`REC_PO_NO`) REFERENCES `t_warehouse_receive` (`REC_PO_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312 COMMENT='入库单明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_receive_detail_his`
--

DROP TABLE IF EXISTS `t_warehouse_receive_detail_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_receive_detail_his` (
  `REC_DETAIL_SEQID` int(11) NOT NULL COMMENT '入库明细SEQID,序列号',
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '供应商送货单号',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '供应商简称',
  `PO_NO_DATE` date DEFAULT NULL COMMENT '供应商单据日期',
  `ARRAY_DATE` date DEFAULT NULL COMMENT '货物到达日期',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '仓库位置',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '送货单对应的采购单号',
  `SETTLEMENT_TYPE` varchar(8) DEFAULT NULL COMMENT '结算方式',
  `PART_NO` varchar(32) NOT NULL COMMENT '公司的物料编号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '供应商的物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `C_PRICE` decimal(12,10) DEFAULT '0.0000000000' COMMENT '采购的单价',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '税率',
  `AMOUNT` int(11) NOT NULL COMMENT '本次送货数量',
  `CUR_MONEY` decimal(10,2) DEFAULT '0.00' COMMENT '本次送货金额',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '所属账期',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '供应商订单类型  0----入库单  1---退货单',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '操作员ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '操作员姓名',
  `OPER_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '收货单（入库单）状态01---仓管创建入库单（临时保存入库单，其他人无法查看）02---仓管确认入库单（入库数据证实确认，其他人可以查看）03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)00---关闭入库单31---财务确认付款',
  `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '活动状态PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）',
  `FINANCE_PERIOD` varchar(8) DEFAULT NULL COMMENT '财务确认的账期或财务调整的账期',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊描述，针对客户订单中有批次的批次号',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  PRIMARY KEY (`REC_DETAIL_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='入库单明细表历史';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_receive_his`
--

DROP TABLE IF EXISTS `t_warehouse_receive_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_receive_his` (
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '供应商入库单号（供应商送货单号）',
  `C_CODE` varchar(32) NOT NULL COMMENT '供应商编码',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '供应商简称',
  `PO_NO_DATE` date DEFAULT NULL COMMENT '供应商单据日期',
  `ARRAY_DATE` date NOT NULL COMMENT '货物到达日期',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '仓库位置',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '操作员',
  `OPER_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  `RECEIVE_DESC` varchar(256) NOT NULL COMMENT '收货单备注',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '所属账期',
  `STATE` varchar(8) DEFAULT NULL COMMENT '收货单（入库单）状态  01---仓管创建入库单（临时保存入库单，其他人无法查看）  02---仓管确认入库单（入库数据证实确认，其他人可以查看）  03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)  00---关闭入库单  31---财务确认付款',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '活动状态  PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）  ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）  ',
  `FINALCE_STATE` varchar(2) DEFAULT NULL COMMENT '财务结算状态  0----未对账  1---已对账',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '供应商订单类型  0----入库单  1---退货单',
  `SETTLEMENT_TYPE` varchar(8) DEFAULT NULL COMMENT '结算方式',
  PRIMARY KEY (`REC_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='入库单或收货单历史';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_send`
--

DROP TABLE IF EXISTS `t_warehouse_send`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_send_detail`
--

DROP TABLE IF EXISTS `t_warehouse_send_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_send_detail` (
  `SEND_DETAIL_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '详单序列号',
  `SEND_PO_NO` varchar(32) NOT NULL,
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '客户物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如物料批次等',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '物料单价',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '税率',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '是否显示单价',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '对应的客户订单号',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '结算方式',
  `COMM_DESC` char(128) DEFAULT NULL COMMENT '备注',
  `AMOUNT` int(11) NOT NULL COMMENT '出货数量',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '本次出货金额',
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
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '客户编码',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '客户简称',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  `CONTACT_FEE` float(6,4) DEFAULT NULL COMMENT '合同费',
  `SELF_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '特定客户备货本次出货数量，同客户订单对应',
  `COMM_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '常规库存本次出货数量，同客户订单对应',
  `HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '仓库类型，缺省为公司总库',
  PRIMARY KEY (`SEND_DETAIL_SEQID`),
  KEY `RLT_SEND_DETAIL` (`SEND_PO_NO`),
  CONSTRAINT `RLT_SEND_DETAIL` FOREIGN KEY (`SEND_PO_NO`) REFERENCES `t_warehouse_send` (`SEND_PO_NO`),
  CONSTRAINT `t_warehouse_send_detail_ibfk_1` FOREIGN KEY (`SEND_PO_NO`) REFERENCES `t_warehouse_send` (`SEND_PO_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312 COMMENT='客户发货详单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_send_detail_his`
--

DROP TABLE IF EXISTS `t_warehouse_send_detail_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_send_detail_his` (
  `SEND_DETAIL_SEQID` int(11) NOT NULL COMMENT '详单序列号',
  `SEND_PO_NO` varchar(32) NOT NULL,
  `PART_NO` varchar(32) NOT NULL COMMENT '公司物料编号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '客户物料编号',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '物料描述',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如物料批次等',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '是否含税  1--是  0--否  如果单价是含税的，则一定是1，否则可以选择是否含税交易',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '物料单价',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '税率',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '是否显示单价',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '对应的客户订单号',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '结算方式',
  `COMM_DESC` char(128) DEFAULT NULL COMMENT '备注',
  `AMOUNT` int(11) NOT NULL COMMENT '出货数量',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '本次出货金额',
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
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '客户编码',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '客户简称',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税',
  `CONTACT_FEE` float(6,4) DEFAULT NULL COMMENT '合同费',
  `SELF_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '特定客户备货本次出货数量，同客户订单对应',
  `COMM_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '常规库存本次出货数量，同客户订单对应',
  `HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '仓库类型，缺省为公司总库',
  PRIMARY KEY (`SEND_DETAIL_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户发货详单表历史';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_send_his`
--

DROP TABLE IF EXISTS `t_warehouse_send_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `STAFF_ID` int(11) NOT NULL COMMENT '用户ID(FK->STAFF.STAFF_ID)',
  `ROLE_ID` int(11) NOT NULL COMMENT '角色ID(FK->ROLE.ROLE_ID)',
  `MEMO` varchar(256) DEFAULT NULL COMMENT '注释',
  KEY `RLT_ROLE_STAFF_ID` (`STAFF_ID`),
  KEY `RLT_FK_ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `RLT_FK_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`),
  CONSTRAINT `RLT_ROLE_STAFF_ID` FOREIGN KEY (`STAFF_ID`) REFERENCES `staff` (`STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `v_customer_info`
--

DROP TABLE IF EXISTS `v_customer_info`;
/*!50001 DROP VIEW IF EXISTS `v_customer_info`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_customer_info` (
  `BASE_SEQID` int(11),
  `C_CODE` varchar(32),
  `STATE` varchar(2),
  `SHORT_NAME` varchar(32),
  `EN_SHORT_NAME` varchar(32),
  `ALL_NAME` varchar(256),
  `EN_NAME` varchar(256),
  `ADDRESS` varchar(256),
  `EN_ADDRESS` varchar(256),
  `C_TYPE` varchar(32),
  `C_SCALE` varchar(32),
  `WEBSITE` varchar(128),
  `REPRESENTATIVE` varchar(32),
  `TAX_CODE` char(128),
  `COMPANY_BRANCH` varchar(64),
  `CREDIT_RATE` varchar(8),
  `CREDIT_DESC` char(32),
  `IMPORTANT_CODE` varchar(8),
  `IMPORTANT_DESC` char(32),
  `SETTLEMENT_TYPE` varchar(8),
  `SETTLEMENT_DESC` varchar(32),
  `CURRENCY` varchar(2),
  `CURRENCY_DESC` varchar(32),
  `STAFF_ID` varchar(20),
  `STAFF_NAME` varchar(20),
  `COMM_DESC` varchar(512),
  `VENDOR_CODE` varchar(8),
  `CONTACT_FEE` float(6,4),
  `IS_SHOW_PRICE` varchar(2),
  `TAX_RATE` float(6,4),
  `ASS_STAFF_ID` varchar(20),
  `ASS_STAFF_NAME` varchar(32),
  `CREATE_TIME` datetime,
  `SALE_TYPE` varchar(32),
  `settlement_day` varchar(4)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_customer_total_settlement`
--

DROP TABLE IF EXISTS `v_customer_total_settlement`;
/*!50001 DROP VIEW IF EXISTS `v_customer_total_settlement`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_customer_total_settlement` (
  `C_CODE` varchar(32),
  `SHORT_NAME` varchar(32),
  `SETTLEMENT_TYPE` varchar(32),
  `ABSTRACT` varchar(32),
  `TOTAL_MONEY` decimal(32,2),
  `NEED_MONEY` decimal(32,2),
  `SET_MONEY` decimal(10,2),
  `FINANCE_STATE` varchar(2),
  `SALES_ID` varchar(20),
  `SALES_NAME` varchar(32),
  `ASS_ID` varchar(20),
  `ASS_NAME` varchar(32)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_customer_unsettlement`
--

DROP TABLE IF EXISTS `v_customer_unsettlement`;
/*!50001 DROP VIEW IF EXISTS `v_customer_unsettlement`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_customer_unsettlement` (
  `c_code` varchar(32),
  `short_name` varchar(32),
  `sales_id` varchar(20),
  `sales_name` varchar(20),
  `ass_id` varchar(20),
  `ass_name` varchar(32),
  `settlement_type` varchar(32),
  `abstract` varchar(32),
  `finance_state` varchar(2),
  `total_money` decimal(32,2),
  `need_money` decimal(32,2),
  `set_money` decimal(3,2)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_sales_benefit_detail`
--

DROP TABLE IF EXISTS `v_sales_benefit_detail`;
/*!50001 DROP VIEW IF EXISTS `v_sales_benefit_detail`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_sales_benefit_detail` (
  `sales_id` varchar(20),
  `sales_name` varchar(20),
  `create_time` datetime,
  `send_po_no` varchar(32),
  `cust_code` varchar(32),
  `cust_short_name` varchar(64),
  `part_no` varchar(32),
  `pn_desc` varchar(128),
  `cust_part_no` varchar(32),
  `cust_price` decimal(12,10),
  `cust_tax_rate` decimal(6,4),
  `amount` int(11),
  `cur_money` decimal(10,2),
  `benefit` decimal(23,10),
  `vendor_part_no` varchar(32),
  `vendor_price` decimal(12,10),
  `vendor_code` varchar(32),
  `vender_short_name` varchar(32)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_sales_benefit_total`
--

DROP TABLE IF EXISTS `v_sales_benefit_total`;
/*!50001 DROP VIEW IF EXISTS `v_sales_benefit_total`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_sales_benefit_total` (
  `sales_id` varchar(20),
  `sales_name` varchar(20),
  `create_time` int(6),
  `cust_code` varchar(32),
  `cust_short_name` varchar(64),
  `part_no` varchar(32),
  `pn_desc` varchar(128),
  `cust_part_no` varchar(32),
  `cust_price` decimal(12,10),
  `vendor_code` varchar(32),
  `vender_short_name` varchar(32),
  `vendor_part_no` varchar(32),
  `amount` decimal(32,0),
  `cur_money` decimal(32,2),
  `benefit` decimal(45,10)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_vendor_info`
--

DROP TABLE IF EXISTS `v_vendor_info`;
/*!50001 DROP VIEW IF EXISTS `v_vendor_info`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_vendor_info` (
  `BASE_SEQID` int(11),
  `C_CODE` varchar(32),
  `STATE` varchar(2),
  `SHORT_NAME` varchar(32),
  `EN_SHORT_NAME` varchar(32),
  `ALL_NAME` varchar(256),
  `EN_NAME` varchar(256),
  `ADDRESS` varchar(256),
  `EN_ADDRESS` varchar(256),
  `C_TYPE` varchar(16),
  `C_SCALE` varchar(16),
  `WEBSITE` varchar(128),
  `REPRESENTATIVE` varchar(32),
  `TAX_CODE` varchar(20),
  `COMPANY_BRANCH` varchar(64),
  `CREDIT_RATE` varchar(8),
  `CREDIT_DESC` varchar(32),
  `IMPORTANT_CODE` varchar(8),
  `IMPORTANT_DESC` varchar(20),
  `SETTLEMENT_TYPE` varchar(8),
  `SETTLEMENT_DESC` varchar(32),
  `CURRENCY` varchar(8),
  `CURRENCY_DESC` varchar(32),
  `STAFF_ID` varchar(20),
  `STAFF_NAME` varchar(32),
  `COMM_DESC` varchar(512),
  `CONTACT_FEE` float(4,2),
  `IS_SHOW_PRICE` varchar(2),
  `TAX_RATE` decimal(6,4),
  `CREATE_TIME` datetime,
  `SALE_TYPE` varchar(32),
  `settlement_day` varchar(4)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_vendor_total_settlement`
--

DROP TABLE IF EXISTS `v_vendor_total_settlement`;
/*!50001 DROP VIEW IF EXISTS `v_vendor_total_settlement`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_vendor_total_settlement` (
  `C_CODE` varchar(32),
  `SHORT_NAME` varchar(32),
  `SETTLEMENT_TYPE` varchar(8),
  `ABSTRACT` varchar(32),
  `TOTAL_MONEY` decimal(32,2),
  `NEED_MONEY` decimal(32,2),
  `SET_MONEY` decimal(10,2),
  `FINANCE_STATE` varchar(2),
  `PURCHASE_ID` varchar(20),
  `PURCHASE_NAME` varchar(32)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_vendor_unsettlement`
--

DROP TABLE IF EXISTS `v_vendor_unsettlement`;
/*!50001 DROP VIEW IF EXISTS `v_vendor_unsettlement`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_vendor_unsettlement` (
  `c_code` varchar(32),
  `short_name` varchar(32),
  `purchase_id` varchar(20),
  `purchase_name` varchar(32),
  `settlement_type` varchar(8),
  `abstract` varchar(32),
  `finance_state` varchar(2),
  `total_money` decimal(32,2),
  `need_money` decimal(32,2),
  `set_money` decimal(3,2)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_customer_info`
--

/*!50001 DROP TABLE IF EXISTS `v_customer_info`*/;
/*!50001 DROP VIEW IF EXISTS `v_customer_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_customer_info` AS select `info`.`BASE_SEQID` AS `BASE_SEQID`,`info`.`C_CODE` AS `C_CODE`,`info`.`STATE` AS `STATE`,`info`.`SHORT_NAME` AS `SHORT_NAME`,`info`.`EN_SHORT_NAME` AS `EN_SHORT_NAME`,`info`.`ALL_NAME` AS `ALL_NAME`,`info`.`EN_NAME` AS `EN_NAME`,`info`.`ADDRESS` AS `ADDRESS`,`info`.`EN_ADDRESS` AS `EN_ADDRESS`,`info`.`C_TYPE` AS `C_TYPE`,`info`.`C_SCALE` AS `C_SCALE`,`info`.`WEBSITE` AS `WEBSITE`,`info`.`REPRESENTATIVE` AS `REPRESENTATIVE`,`info`.`TAX_CODE` AS `TAX_CODE`,`info`.`COMPANY_BRANCH` AS `COMPANY_BRANCH`,`info`.`CREDIT_RATE` AS `CREDIT_RATE`,`info`.`CREDIT_DESC` AS `CREDIT_DESC`,`info`.`IMPORTANT_CODE` AS `IMPORTANT_CODE`,`info`.`IMPORTANT_DESC` AS `IMPORTANT_DESC`,`info`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`info`.`SETTLEMENT_DESC` AS `SETTLEMENT_DESC`,`info`.`CURRENCY` AS `CURRENCY`,`info`.`CURRENCY_DESC` AS `CURRENCY_DESC`,`info`.`STAFF_ID` AS `STAFF_ID`,`info`.`STAFF_NAME` AS `STAFF_NAME`,`info`.`COMM_DESC` AS `COMM_DESC`,`info`.`VENDOR_CODE` AS `VENDOR_CODE`,`info`.`CONTACT_FEE` AS `CONTACT_FEE`,`info`.`IS_SHOW_PRICE` AS `IS_SHOW_PRICE`,`info`.`TAX_RATE` AS `TAX_RATE`,`info`.`ASS_STAFF_ID` AS `ASS_STAFF_ID`,`info`.`ASS_STAFF_NAME` AS `ASS_STAFF_NAME`,`info`.`CREATE_TIME` AS `CREATE_TIME`,`info`.`SALE_TYPE` AS `SALE_TYPE`,`p`.`SETTLEMENT_DAY` AS `settlement_day` from (`t_customer_info` `info` join `t_cust_account_period` `p`) where ((`info`.`SETTLEMENT_TYPE` = '1') and (`info`.`BASE_SEQID` = `p`.`BASE_SEQID`)) union select `info`.`BASE_SEQID` AS `BASE_SEQID`,`info`.`C_CODE` AS `C_CODE`,`info`.`STATE` AS `STATE`,`info`.`SHORT_NAME` AS `SHORT_NAME`,`info`.`EN_SHORT_NAME` AS `EN_SHORT_NAME`,`info`.`ALL_NAME` AS `ALL_NAME`,`info`.`EN_NAME` AS `EN_NAME`,`info`.`ADDRESS` AS `ADDRESS`,`info`.`EN_ADDRESS` AS `EN_ADDRESS`,`info`.`C_TYPE` AS `C_TYPE`,`info`.`C_SCALE` AS `C_SCALE`,`info`.`WEBSITE` AS `WEBSITE`,`info`.`REPRESENTATIVE` AS `REPRESENTATIVE`,`info`.`TAX_CODE` AS `TAX_CODE`,`info`.`COMPANY_BRANCH` AS `COMPANY_BRANCH`,`info`.`CREDIT_RATE` AS `CREDIT_RATE`,`info`.`CREDIT_DESC` AS `CREDIT_DESC`,`info`.`IMPORTANT_CODE` AS `IMPORTANT_CODE`,`info`.`IMPORTANT_DESC` AS `IMPORTANT_DESC`,`info`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`info`.`SETTLEMENT_DESC` AS `SETTLEMENT_DESC`,`info`.`CURRENCY` AS `CURRENCY`,`info`.`CURRENCY_DESC` AS `CURRENCY_DESC`,`info`.`STAFF_ID` AS `STAFF_ID`,`info`.`STAFF_NAME` AS `STAFF_NAME`,`info`.`COMM_DESC` AS `COMM_DESC`,`info`.`VENDOR_CODE` AS `VENDOR_CODE`,`info`.`CONTACT_FEE` AS `CONTACT_FEE`,`info`.`IS_SHOW_PRICE` AS `IS_SHOW_PRICE`,`info`.`TAX_RATE` AS `TAX_RATE`,`info`.`ASS_STAFF_ID` AS `ASS_STAFF_ID`,`info`.`ASS_STAFF_NAME` AS `ASS_STAFF_NAME`,`info`.`CREATE_TIME` AS `CREATE_TIME`,`info`.`SALE_TYPE` AS `SALE_TYPE`,`p`.`PRE_PAID` AS `settlement_day` from (`t_customer_info` `info` join `t_cust_prepaid` `p`) where (((`info`.`SETTLEMENT_TYPE` = '2') or (`info`.`SETTLEMENT_TYPE` = '3')) and (`info`.`BASE_SEQID` = `p`.`BASE_SEQID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_customer_total_settlement`
--

/*!50001 DROP TABLE IF EXISTS `v_customer_total_settlement`*/;
/*!50001 DROP VIEW IF EXISTS `v_customer_total_settlement`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_customer_total_settlement` AS select `t_customer_settlement`.`C_CODE` AS `C_CODE`,`t_customer_settlement`.`SHORT_NAME` AS `SHORT_NAME`,`t_customer_settlement`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`t_customer_settlement`.`ABSTRACT` AS `ABSTRACT`,`t_customer_settlement`.`TOTAL_MONEY` AS `TOTAL_MONEY`,`t_customer_settlement`.`NEED_MONEY` AS `NEED_MONEY`,`t_customer_settlement`.`SET_MONEY` AS `SET_MONEY`,`t_customer_settlement`.`FINANCE_STATE` AS `FINANCE_STATE`,`t_customer_settlement`.`SALES_ID` AS `SALES_ID`,`t_customer_settlement`.`SALES_NAME` AS `SALES_NAME`,`t_customer_settlement`.`ASS_ID` AS `ASS_ID`,`t_customer_settlement`.`ASS_NAME` AS `ASS_NAME` from `t_customer_settlement` union select `v_customer_unsettlement`.`c_code` AS `C_CODE`,`v_customer_unsettlement`.`short_name` AS `SHORT_NAME`,`v_customer_unsettlement`.`settlement_type` AS `SETTLEMENT_TYPE`,`v_customer_unsettlement`.`abstract` AS `ABSTRACT`,`v_customer_unsettlement`.`total_money` AS `TOTAL_MONEY`,`v_customer_unsettlement`.`need_money` AS `NEED_MONEY`,`v_customer_unsettlement`.`set_money` AS `SET_MONEY`,`v_customer_unsettlement`.`finance_state` AS `FINANCE_STATE`,`v_customer_unsettlement`.`sales_id` AS `SALES_ID`,`v_customer_unsettlement`.`sales_name` AS `SALES_NAME`,`v_customer_unsettlement`.`ass_id` AS `ASS_ID`,`v_customer_unsettlement`.`ass_name` AS `ASS_NAME` from `v_customer_unsettlement` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_customer_unsettlement`
--

/*!50001 DROP TABLE IF EXISTS `v_customer_unsettlement`*/;
/*!50001 DROP VIEW IF EXISTS `v_customer_unsettlement`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_customer_unsettlement` AS select `vinfo`.`C_CODE` AS `c_code`,`vinfo`.`SHORT_NAME` AS `short_name`,`vinfo`.`STAFF_ID` AS `sales_id`,`vinfo`.`STAFF_NAME` AS `sales_name`,`vinfo`.`ASS_STAFF_ID` AS `ass_id`,`vinfo`.`ASS_STAFF_NAME` AS `ass_name`,`wdetail`.`SETTLEMENT_TYPE` AS `settlement_type`,`wdetail`.`FINANCE_PERIOD` AS `abstract`,`wdetail`.`FINANCE_STATE` AS `finance_state`,sum(`wdetail`.`CUR_MONEY`) AS `total_money`,sum(`wdetail`.`CUR_MONEY`) AS `need_money`,0.00 AS `set_money` from (`t_customer_info` `vinfo` join `t_warehouse_send_detail` `wdetail`) where ((`vinfo`.`STATE` = 0) and (`wdetail`.`STATE` = '02') and (`wdetail`.`SETTLEMENT_TYPE` = 1) and (`wdetail`.`FINANCE_STATE` = 1) and (`vinfo`.`C_CODE` = `wdetail`.`C_CODE`)) group by `vinfo`.`C_CODE`,`vinfo`.`SHORT_NAME`,`vinfo`.`STAFF_ID`,`vinfo`.`STAFF_NAME`,`vinfo`.`ASS_STAFF_ID`,`vinfo`.`ASS_STAFF_NAME`,`wdetail`.`SETTLEMENT_TYPE`,`wdetail`.`FINANCE_PERIOD`,`wdetail`.`FINANCE_STATE` union select `vinfo`.`C_CODE` AS `c_code`,`vinfo`.`SHORT_NAME` AS `short_name`,`vinfo`.`STAFF_ID` AS `sales_id`,`vinfo`.`STAFF_NAME` AS `sales_name`,`vinfo`.`ASS_STAFF_ID` AS `ass_id`,`vinfo`.`ASS_STAFF_NAME` AS `ass_name`,`wdetail`.`SETTLEMENT_TYPE` AS `settlement_type`,`wdetail`.`SEND_PO_NO` AS `abstract`,`wdetail`.`FINANCE_STATE` AS `finance_state`,sum(`wdetail`.`CUR_MONEY`) AS `total_money`,sum(`wdetail`.`CUR_MONEY`) AS `need_money`,0.00 AS `set_money` from (`t_customer_info` `vinfo` join `t_warehouse_send_detail` `wdetail`) where ((`vinfo`.`STATE` = 0) and (`wdetail`.`STATE` = '02') and (`wdetail`.`SETTLEMENT_TYPE` <> 1) and ((`wdetail`.`FINANCE_STATE` = 0) or (`wdetail`.`FINANCE_STATE` = 1)) and (`vinfo`.`C_CODE` = `wdetail`.`C_CODE`)) group by `vinfo`.`C_CODE`,`vinfo`.`SHORT_NAME`,`vinfo`.`STAFF_ID`,`vinfo`.`STAFF_NAME`,`vinfo`.`ASS_STAFF_ID`,`vinfo`.`ASS_STAFF_NAME`,`wdetail`.`SETTLEMENT_TYPE`,`wdetail`.`SEND_PO_NO`,`wdetail`.`FINANCE_STATE` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_sales_benefit_detail`
--

/*!50001 DROP TABLE IF EXISTS `v_sales_benefit_detail`*/;
/*!50001 DROP VIEW IF EXISTS `v_sales_benefit_detail`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_sales_benefit_detail` AS select `t`.`STAFF_ID` AS `sales_id`,`t`.`STAFF_NAME` AS `sales_name`,`w`.`CREATE_TIME` AS `create_time`,`w`.`SEND_PO_NO` AS `send_po_no`,`w`.`C_CODE` AS `cust_code`,`w`.`SHORT_NAME` AS `cust_short_name`,`w`.`PART_NO` AS `part_no`,`v`.`PN_DESC` AS `pn_desc`,`w`.`C_PART_NO` AS `cust_part_no`,`w`.`C_PRICE` AS `cust_price`,`w`.`TAX_RATE` AS `cust_tax_rate`,`w`.`AMOUNT` AS `amount`,`w`.`CUR_MONEY` AS `cur_money`,(`w`.`CUR_MONEY` - (`w`.`AMOUNT` * `v`.`PRICE`)) AS `benefit`,`v`.`C_PART_NO` AS `vendor_part_no`,`v`.`PRICE` AS `vendor_price`,`v`.`C_CODE` AS `vendor_code`,`v`.`C_SHORT_NAME` AS `vender_short_name` from ((`t_warehouse_send_detail` `w` left join `t_vendor_part_no_info` `v` on(((`w`.`VENDOR_CODE` = `v`.`C_CODE`) and (`w`.`PART_NO` = `v`.`PART_NO`)))) join `t_customer_info` `t`) where ((`t`.`C_CODE` = `w`.`C_CODE`) and (`t`.`STATE` = 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_sales_benefit_total`
--

/*!50001 DROP TABLE IF EXISTS `v_sales_benefit_total`*/;
/*!50001 DROP VIEW IF EXISTS `v_sales_benefit_total`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_sales_benefit_total` AS select `v_sales_benefit_detail`.`sales_id` AS `sales_id`,`v_sales_benefit_detail`.`sales_name` AS `sales_name`,extract(year_month from `v_sales_benefit_detail`.`create_time`) AS `create_time`,`v_sales_benefit_detail`.`cust_code` AS `cust_code`,`v_sales_benefit_detail`.`cust_short_name` AS `cust_short_name`,`v_sales_benefit_detail`.`part_no` AS `part_no`,`v_sales_benefit_detail`.`pn_desc` AS `pn_desc`,`v_sales_benefit_detail`.`cust_part_no` AS `cust_part_no`,`v_sales_benefit_detail`.`cust_price` AS `cust_price`,`v_sales_benefit_detail`.`vendor_code` AS `vendor_code`,`v_sales_benefit_detail`.`vender_short_name` AS `vender_short_name`,`v_sales_benefit_detail`.`vendor_part_no` AS `vendor_part_no`,sum(`v_sales_benefit_detail`.`amount`) AS `amount`,sum(`v_sales_benefit_detail`.`cur_money`) AS `cur_money`,sum(`v_sales_benefit_detail`.`benefit`) AS `benefit` from `v_sales_benefit_detail` group by `v_sales_benefit_detail`.`sales_id`,`v_sales_benefit_detail`.`sales_name`,`v_sales_benefit_detail`.`create_time`,`v_sales_benefit_detail`.`cust_code`,`v_sales_benefit_detail`.`cust_short_name`,`v_sales_benefit_detail`.`part_no`,`v_sales_benefit_detail`.`pn_desc`,`v_sales_benefit_detail`.`cust_part_no`,`v_sales_benefit_detail`.`cust_price`,`v_sales_benefit_detail`.`vendor_code`,`v_sales_benefit_detail`.`vender_short_name`,`v_sales_benefit_detail`.`vendor_part_no` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_vendor_info`
--

/*!50001 DROP TABLE IF EXISTS `v_vendor_info`*/;
/*!50001 DROP VIEW IF EXISTS `v_vendor_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_vendor_info` AS select `info`.`BASE_SEQID` AS `BASE_SEQID`,`info`.`C_CODE` AS `C_CODE`,`info`.`STATE` AS `STATE`,`info`.`SHORT_NAME` AS `SHORT_NAME`,`info`.`EN_SHORT_NAME` AS `EN_SHORT_NAME`,`info`.`ALL_NAME` AS `ALL_NAME`,`info`.`EN_NAME` AS `EN_NAME`,`info`.`ADDRESS` AS `ADDRESS`,`info`.`EN_ADDRESS` AS `EN_ADDRESS`,`info`.`C_TYPE` AS `C_TYPE`,`info`.`C_SCALE` AS `C_SCALE`,`info`.`WEBSITE` AS `WEBSITE`,`info`.`REPRESENTATIVE` AS `REPRESENTATIVE`,`info`.`TAX_CODE` AS `TAX_CODE`,`info`.`COMPANY_BRANCH` AS `COMPANY_BRANCH`,`info`.`CREDIT_RATE` AS `CREDIT_RATE`,`info`.`CREDIT_DESC` AS `CREDIT_DESC`,`info`.`IMPORTANT_CODE` AS `IMPORTANT_CODE`,`info`.`IMPORTANT_DESC` AS `IMPORTANT_DESC`,`info`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`info`.`SETTLEMENT_DESC` AS `SETTLEMENT_DESC`,`info`.`CURRENCY` AS `CURRENCY`,`info`.`CURRENCY_DESC` AS `CURRENCY_DESC`,`info`.`STAFF_ID` AS `STAFF_ID`,`info`.`STAFF_NAME` AS `STAFF_NAME`,`info`.`COMM_DESC` AS `COMM_DESC`,`info`.`CONTACT_FEE` AS `CONTACT_FEE`,`info`.`IS_SHOW_PRICE` AS `IS_SHOW_PRICE`,`info`.`TAX_RATE` AS `TAX_RATE`,`info`.`CREATE_TIME` AS `CREATE_TIME`,`info`.`SALE_TYPE` AS `SALE_TYPE`,`p`.`SETTLEMENT_DAY` AS `settlement_day` from (`t_vendor_info` `info` join `t_vendor_account_period` `p`) where ((`info`.`SETTLEMENT_TYPE` = '1') and (`info`.`BASE_SEQID` = `p`.`BASE_SEQID`)) union select `info`.`BASE_SEQID` AS `BASE_SEQID`,`info`.`C_CODE` AS `C_CODE`,`info`.`STATE` AS `STATE`,`info`.`SHORT_NAME` AS `SHORT_NAME`,`info`.`EN_SHORT_NAME` AS `EN_SHORT_NAME`,`info`.`ALL_NAME` AS `ALL_NAME`,`info`.`EN_NAME` AS `EN_NAME`,`info`.`ADDRESS` AS `ADDRESS`,`info`.`EN_ADDRESS` AS `EN_ADDRESS`,`info`.`C_TYPE` AS `C_TYPE`,`info`.`C_SCALE` AS `C_SCALE`,`info`.`WEBSITE` AS `WEBSITE`,`info`.`REPRESENTATIVE` AS `REPRESENTATIVE`,`info`.`TAX_CODE` AS `TAX_CODE`,`info`.`COMPANY_BRANCH` AS `COMPANY_BRANCH`,`info`.`CREDIT_RATE` AS `CREDIT_RATE`,`info`.`CREDIT_DESC` AS `CREDIT_DESC`,`info`.`IMPORTANT_CODE` AS `IMPORTANT_CODE`,`info`.`IMPORTANT_DESC` AS `IMPORTANT_DESC`,`info`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`info`.`SETTLEMENT_DESC` AS `SETTLEMENT_DESC`,`info`.`CURRENCY` AS `CURRENCY`,`info`.`CURRENCY_DESC` AS `CURRENCY_DESC`,`info`.`STAFF_ID` AS `STAFF_ID`,`info`.`STAFF_NAME` AS `STAFF_NAME`,`info`.`COMM_DESC` AS `COMM_DESC`,`info`.`CONTACT_FEE` AS `CONTACT_FEE`,`info`.`IS_SHOW_PRICE` AS `IS_SHOW_PRICE`,`info`.`TAX_RATE` AS `TAX_RATE`,`info`.`CREATE_TIME` AS `CREATE_TIME`,`info`.`SALE_TYPE` AS `SALE_TYPE`,`p`.`PRE_PAID` AS `settlement_day` from (`t_vendor_info` `info` join `t_vendor_prepaid` `p`) where (((`info`.`SETTLEMENT_TYPE` = '2') or (`info`.`SETTLEMENT_TYPE` = '3')) and (`info`.`BASE_SEQID` = `p`.`BASE_SEQID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_vendor_total_settlement`
--

/*!50001 DROP TABLE IF EXISTS `v_vendor_total_settlement`*/;
/*!50001 DROP VIEW IF EXISTS `v_vendor_total_settlement`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_vendor_total_settlement` AS select `t_vendor_settlement`.`C_CODE` AS `C_CODE`,`t_vendor_settlement`.`SHORT_NAME` AS `SHORT_NAME`,`t_vendor_settlement`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`t_vendor_settlement`.`ABSTRACT` AS `ABSTRACT`,`t_vendor_settlement`.`TOTAL_MONEY` AS `TOTAL_MONEY`,`t_vendor_settlement`.`NEED_MONEY` AS `NEED_MONEY`,`t_vendor_settlement`.`SET_MONEY` AS `SET_MONEY`,`t_vendor_settlement`.`FINANCE_STATE` AS `FINANCE_STATE`,`t_vendor_settlement`.`PURCHASE_ID` AS `PURCHASE_ID`,`t_vendor_settlement`.`PURCHASE_NAME` AS `PURCHASE_NAME` from `t_vendor_settlement` union select `v_vendor_unsettlement`.`c_code` AS `C_CODE`,`v_vendor_unsettlement`.`short_name` AS `SHORT_NAME`,`v_vendor_unsettlement`.`settlement_type` AS `SETTLEMENT_TYPE`,`v_vendor_unsettlement`.`abstract` AS `ABSTRACT`,`v_vendor_unsettlement`.`total_money` AS `TOTAL_MONEY`,`v_vendor_unsettlement`.`need_money` AS `NEED_MONEY`,`v_vendor_unsettlement`.`set_money` AS `SET_MONEY`,`v_vendor_unsettlement`.`finance_state` AS `FINANCE_STATE`,`v_vendor_unsettlement`.`purchase_id` AS `PURCHASE_ID`,`v_vendor_unsettlement`.`purchase_name` AS `PURCHASE_NAME` from `v_vendor_unsettlement` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_vendor_unsettlement`
--

/*!50001 DROP TABLE IF EXISTS `v_vendor_unsettlement`*/;
/*!50001 DROP VIEW IF EXISTS `v_vendor_unsettlement`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_vendor_unsettlement` AS select `vinfo`.`C_CODE` AS `c_code`,`vinfo`.`SHORT_NAME` AS `short_name`,`vinfo`.`STAFF_ID` AS `purchase_id`,`vinfo`.`STAFF_NAME` AS `purchase_name`,`wdetail`.`SETTLEMENT_TYPE` AS `settlement_type`,`wdetail`.`FINANCE_PERIOD` AS `abstract`,`wdetail`.`FINANCE_STATE` AS `finance_state`,sum(`wdetail`.`CUR_MONEY`) AS `total_money`,sum(`wdetail`.`CUR_MONEY`) AS `need_money`,0.00 AS `set_money` from (`t_vendor_info` `vinfo` join `t_warehouse_receive_detail` `wdetail`) where ((`vinfo`.`STATE` = 0) and (`wdetail`.`STATE` = '02') and (`wdetail`.`SETTLEMENT_TYPE` = 1) and (`wdetail`.`FINANCE_STATE` = 1) and (`vinfo`.`C_CODE` = `wdetail`.`C_CODE`)) group by `vinfo`.`C_CODE`,`vinfo`.`SHORT_NAME`,`vinfo`.`STAFF_ID`,`vinfo`.`STAFF_NAME`,`wdetail`.`SETTLEMENT_TYPE`,`wdetail`.`FINANCE_PERIOD`,`wdetail`.`FINANCE_STATE` union select `vinfo`.`C_CODE` AS `c_code`,`vinfo`.`SHORT_NAME` AS `short_name`,`vinfo`.`STAFF_ID` AS `purchase_id`,`vinfo`.`STAFF_NAME` AS `purchase_name`,`wdetail`.`SETTLEMENT_TYPE` AS `settlement_type`,`wdetail`.`REC_PO_NO` AS `abstract`,`wdetail`.`FINANCE_STATE` AS `finance_state`,sum(`wdetail`.`CUR_MONEY`) AS `total_money`,sum(`wdetail`.`CUR_MONEY`) AS `need_money`,0.00 AS `set_money` from (`t_vendor_info` `vinfo` join `t_warehouse_receive_detail` `wdetail`) where ((`vinfo`.`STATE` = 0) and (`wdetail`.`STATE` = '02') and (`wdetail`.`SETTLEMENT_TYPE` <> 1) and ((`wdetail`.`FINANCE_STATE` = 0) or (`wdetail`.`FINANCE_STATE` = 1)) and (`vinfo`.`C_CODE` = `wdetail`.`C_CODE`)) group by `vinfo`.`C_CODE`,`vinfo`.`SHORT_NAME`,`vinfo`.`STAFF_ID`,`vinfo`.`STAFF_NAME`,`wdetail`.`SETTLEMENT_TYPE`,`wdetail`.`REC_PO_NO`,`wdetail`.`FINANCE_STATE` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-07-12  8:05:00
