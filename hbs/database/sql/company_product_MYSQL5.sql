# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_company_part_no
#

DROP TABLE IF EXISTS `t_company_part_no`;
CREATE TABLE `t_company_part_no` (
  `PART_NO` varchar(32) NOT NULL COMMENT '物料编号',
  `PN_DESC` varchar(128) NOT NULL COMMENT '物料描述',
  `PRICE` float(8,4) DEFAULT '0.0000' COMMENT '价格',
  `TAX_PRICE` float(8,4) DEFAULT '0.0000' COMMENT '含税价格',
  `CREATE_TIME` date NOT NULL COMMENT '创建时间',
  `P_CLS_CODE` varchar(20) DEFAULT NULL COMMENT '所属产品小类',
  `P_CAT_CODE` varchar(32) DEFAULT NULL COMMENT '所属产品大类'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='公司物料信息表';

#
# Dumping data for table t_company_part_no
#


#
# Table structure for table t_product_catagory
#

DROP TABLE IF EXISTS `t_product_catagory`;
CREATE TABLE `t_product_catagory` (
  `P_CAT_CODE` varchar(32) NOT NULL COMMENT '产品大类编号',
  `P_CAT_NAME` varchar(64) DEFAULT NULL COMMENT '产品大类名称',
  `P_CAT_DESC` varchar(256) DEFAULT NULL COMMENT '产品大类描述'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='产品大类表';

#
# Dumping data for table t_product_catagory
#


#
# Table structure for table t_product_class
#

DROP TABLE IF EXISTS `t_product_class`;
CREATE TABLE `t_product_class` (
  `P_CLS_CODE` varchar(32) NOT NULL COMMENT '产品小类编码',
  `P_CAT_CODE` char(32) DEFAULT NULL COMMENT '所属产品大类',
  `P_CLS_NAME` varchar(256) DEFAULT NULL COMMENT '产品小类名称',
  `P_CLS_DESC` varchar(256) DEFAULT NULL COMMENT '产品小类特征描述'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='产品小类表';

#
# Dumping data for table t_product_class
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
