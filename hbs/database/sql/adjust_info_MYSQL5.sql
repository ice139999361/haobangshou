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
  `APPLY_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '�������к�',
  `STAFF_ID` varchar(32) DEFAULT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '������',
  `APPLY_DATE` datetime DEFAULT NULL COMMENT '����ʱ��',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '���ϵĶ����ţ���Ӧ�̵Ĳɹ�����,ĿǰΪNULL',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '����Ĺ�˾����PART_NO',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '�������ϵ�����',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '�ֿ�λ��',
  `APPLY_AMOUNT` int(11) DEFAULT NULL COMMENT '����ĵ�������',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '���ϵĹ�Ӧ�̱���',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '��Ӧ�̼��',
  `APPLY_CONTENT` varchar(256) DEFAULT NULL COMMENT '����˵��',
  `FROM_CUST_CODE` varchar(32) DEFAULT NULL COMMENT '���Ǹ��ͻ��������',
  `FROM_C_NAME` varchar(64) DEFAULT NULL COMMENT '�ӿͻ����',
  `TO_CUST_CODE` varchar(32) DEFAULT NULL COMMENT '�����Ǹ��ͻ�code��',
  `TO_C_NAME` varchar(64) DEFAULT NULL COMMENT '���ͻ����',
  `AUDIT_STAFF_ID` varchar(20) DEFAULT NULL COMMENT '������ID',
  `AUDIT_STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����������',
  `AUDIT_DATE` datetime DEFAULT NULL COMMENT '����ʱ��',
  `AUDIT_AGREE` varchar(2) DEFAULT NULL COMMENT '�����Ƿ�ͬ��  0---ͬ��  1---��ͬ��',
  `AUDIT_CONTENT` varchar(128) DEFAULT NULL COMMENT '�����ľ������',
  PRIMARY KEY (`APPLY_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='���������';

#
# Dumping data for table t_adjust_info
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
