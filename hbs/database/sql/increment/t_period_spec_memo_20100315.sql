# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_period_spec_memo
#

CREATE TABLE `t_period_spec_memo` (
  `COMM_CODE` varchar(32) NOT NULL COMMENT '客户或供应商编码',
  `COMM_SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '客户或供应商简称',
  `COMM_TYPE` int(11) NOT NULL COMMENT '表示供应商还是客户  0---客户  1---供应商',
  `CREATE_TIME` datetime NOT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(32) NOT NULL COMMENT '操作者ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '操作者姓名',
  `MEMO` varchar(512) NOT NULL COMMENT '账期结算备注'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='财务账期结算备注表';

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
