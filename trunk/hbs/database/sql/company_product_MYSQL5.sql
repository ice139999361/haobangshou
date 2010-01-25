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
  `PART_NO` varchar(32) NOT NULL COMMENT '���ϱ��',
  `PN_DESC` varchar(128) NOT NULL COMMENT '��������',
  `PRICE` float(8,4) DEFAULT '0.0000' COMMENT '�۸�',
  `TAX_PRICE` float(8,4) DEFAULT '0.0000' COMMENT '��˰�۸�',
  `CREATE_TIME` date NOT NULL COMMENT '����ʱ��',
  `P_CLS_CODE` varchar(20) DEFAULT NULL COMMENT '������ƷС��',
  `P_CAT_CODE` varchar(32) DEFAULT NULL COMMENT '������Ʒ����'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��˾������Ϣ��';

#
# Dumping data for table t_company_part_no
#


#
# Table structure for table t_product_catagory
#

DROP TABLE IF EXISTS `t_product_catagory`;
CREATE TABLE `t_product_catagory` (
  `P_CAT_CODE` varchar(32) NOT NULL COMMENT '��Ʒ������',
  `P_CAT_NAME` varchar(64) DEFAULT NULL COMMENT '��Ʒ��������',
  `P_CAT_DESC` varchar(256) DEFAULT NULL COMMENT '��Ʒ��������'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ʒ�����';

#
# Dumping data for table t_product_catagory
#


#
# Table structure for table t_product_class
#

DROP TABLE IF EXISTS `t_product_class`;
CREATE TABLE `t_product_class` (
  `P_CLS_CODE` varchar(32) NOT NULL COMMENT '��ƷС�����',
  `P_CAT_CODE` char(32) DEFAULT NULL COMMENT '������Ʒ����',
  `P_CLS_NAME` varchar(256) DEFAULT NULL COMMENT '��ƷС������',
  `P_CLS_DESC` varchar(256) DEFAULT NULL COMMENT '��ƷС����������'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��ƷС���';

#
# Dumping data for table t_product_class
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
