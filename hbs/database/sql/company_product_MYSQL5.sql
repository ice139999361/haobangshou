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
  `PRICE` float(8,4) default '0.0000' COMMENT '�۸�',
  `TAX_PRICE` float(8,4) default '0.0000' COMMENT '��˰�۸�',
  `CREATE_TIME` date NOT NULL COMMENT '����ʱ��',
  `P_CLS_CODE` varchar(20) default NULL COMMENT '������Ʒ���',
  `P_CAT_CODE` varchar(32) default NULL COMMENT '����',
  PRIMARY KEY  (`PART_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='��˾������Ϣ��';

#
# Dumping data for table t_company_part_no
#


#
# Table structure for table t_product_catagory
#

DROP TABLE IF EXISTS `t_product_catagory`;

#
# Dumping data for table t_product_catagory
#


#
# Table structure for table t_product_class
#

DROP TABLE IF EXISTS `t_product_class`;
CREATE TABLE `t_product_class` (
  `P_CLS_CODE` int(11) NOT NULL auto_increment COMMENT '��Ʒ������',
  `P_CLS_NAME` varchar(256) NOT NULL COMMENT '��Ʒ�������',
  `P_CLS_DESC` varchar(256) default NULL COMMENT '��Ʒ�����������',
  `PARENT_CLS_CODE` int(11) NOT NULL default '0' COMMENT '�������������� ��Ϊ0��˵��û�и������������',
  `P_CLS_LEVEL` varchar(2) NOT NULL COMMENT '��Ʒ�ֲ㼶��һ������������Ϊ0  �����Ϊ��1---һ�� /2---����/3---����/4---�ļ�  ���趨�嵽�ֵ����',
  PRIMARY KEY  (`P_CLS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='��Ʒ�����';
#
# Dumping data for table t_product_class
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;