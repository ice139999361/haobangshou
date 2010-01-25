# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_cust_send_invoice
#

DROP TABLE IF EXISTS `t_cust_send_invoice`;
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
  `ALL_MONEY` float(9,2) DEFAULT NULL COMMENT '总含税金额',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '本次开票金额',
  `LEFT_MONEY` float(9,2) DEFAULT NULL COMMENT '未开票金额',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '备注，主要填写发票号，也可以填写其他内容',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商发票记录表';

#
# Dumping data for table t_cust_send_invoice
#


#
# Table structure for table t_cust_send_invoice_his
#

DROP TABLE IF EXISTS `t_cust_send_invoice_his`;
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
  `ALL_MONEY` float(9,2) DEFAULT NULL COMMENT '总含税金额',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '本次开票金额',
  `LEFT_MONEY` float(9,2) DEFAULT NULL COMMENT '未开票金额',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '备注，主要填写发票号，也可以填写其他内容',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商发票记录表历史';

#
# Dumping data for table t_cust_send_invoice_his
#


#
# Table structure for table t_vendor_receive_invoice
#

DROP TABLE IF EXISTS `t_vendor_receive_invoice`;
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
  `ALL_MONEY` float(9,2) DEFAULT NULL COMMENT '总含税金额',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '本次开票金额',
  `LEFT_MONEY` float(9,2) DEFAULT NULL COMMENT '未开票金额',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '备注，主要填写发票号，也可以填写其他内容',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商发票记录表';

#
# Dumping data for table t_vendor_receive_invoice
#


#
# Table structure for table t_vendor_receive_invoice_his
#

DROP TABLE IF EXISTS `t_vendor_receive_invoice_his`;
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
  `ALL_MONEY` float(9,2) DEFAULT NULL COMMENT '总含税金额',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '本次开票金额',
  `LEFT_MONEY` float(9,2) DEFAULT NULL COMMENT '未开票金额',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '备注，主要填写发票号，也可以填写其他内容',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='供应商发票记录表历史';

#
# Dumping data for table t_vendor_receive_invoice_his
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
