# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_adjust_info
#

DROP TABLE IF EXISTS `t_adjust_info`;
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

#
# Dumping data for table t_adjust_info
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
