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
  `INVOICE_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '����������',
  `CREATE_TIME` datetime NOT NULL COMMENT '����Ʊʱ��',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '�ͻ�����',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '��˾���ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ����ϱ��',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '��������',
  `ALL_MONEY` float(9,2) DEFAULT NULL COMMENT '�ܺ�˰���',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '���ο�Ʊ���',
  `LEFT_MONEY` float(9,2) DEFAULT NULL COMMENT 'δ��Ʊ���',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '��ע����Ҫ��д��Ʊ�ţ�Ҳ������д��������',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̷�Ʊ��¼��';

#
# Dumping data for table t_cust_send_invoice
#


#
# Table structure for table t_cust_send_invoice_his
#

DROP TABLE IF EXISTS `t_cust_send_invoice_his`;
CREATE TABLE `t_cust_send_invoice_his` (
  `INVOICE_SEQID` int(11) NOT NULL COMMENT '���к�',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '����������',
  `CREATE_TIME` datetime NOT NULL COMMENT '����Ʊʱ��',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '�ͻ�����',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '��˾���ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ����ϱ��',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '��������',
  `ALL_MONEY` float(9,2) DEFAULT NULL COMMENT '�ܺ�˰���',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '���ο�Ʊ���',
  `LEFT_MONEY` float(9,2) DEFAULT NULL COMMENT 'δ��Ʊ���',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '��ע����Ҫ��д��Ʊ�ţ�Ҳ������д��������',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̷�Ʊ��¼����ʷ';

#
# Dumping data for table t_cust_send_invoice_his
#


#
# Table structure for table t_vendor_receive_invoice
#

DROP TABLE IF EXISTS `t_vendor_receive_invoice`;
CREATE TABLE `t_vendor_receive_invoice` (
  `INVOICE_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '����������',
  `CREATE_TIME` datetime NOT NULL COMMENT '����Ʊʱ��',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '�ͻ�����',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '��˾���ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '��Ӧ�����ϱ��',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '��������',
  `ALL_MONEY` float(9,2) DEFAULT NULL COMMENT '�ܺ�˰���',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '���ο�Ʊ���',
  `LEFT_MONEY` float(9,2) DEFAULT NULL COMMENT 'δ��Ʊ���',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '��ע����Ҫ��д��Ʊ�ţ�Ҳ������д��������',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̷�Ʊ��¼��';

#
# Dumping data for table t_vendor_receive_invoice
#


#
# Table structure for table t_vendor_receive_invoice_his
#

DROP TABLE IF EXISTS `t_vendor_receive_invoice_his`;
CREATE TABLE `t_vendor_receive_invoice_his` (
  `INVOICE_SEQID` int(11) NOT NULL COMMENT '���к�',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '����������',
  `CREATE_TIME` datetime NOT NULL COMMENT '����Ʊʱ��',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '�ͻ�����',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '��˾���ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '��Ӧ�����ϱ��',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '��������',
  `ALL_MONEY` float(9,2) DEFAULT NULL COMMENT '�ܺ�˰���',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '���ο�Ʊ���',
  `LEFT_MONEY` float(9,2) DEFAULT NULL COMMENT 'δ��Ʊ���',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '��ע����Ҫ��д��Ʊ�ţ�Ҳ������д��������',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̷�Ʊ��¼����ʷ';

#
# Dumping data for table t_vendor_receive_invoice_his
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
