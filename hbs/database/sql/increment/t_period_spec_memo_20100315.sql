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
  `COMM_CODE` varchar(32) NOT NULL COMMENT '�ͻ���Ӧ�̱���',
  `COMM_SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '�ͻ���Ӧ�̼��',
  `COMM_TYPE` int(11) NOT NULL COMMENT '��ʾ��Ӧ�̻��ǿͻ�  0---�ͻ�  1---��Ӧ��',
  `CREATE_TIME` datetime NOT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(32) NOT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '����������',
  `MEMO` varchar(512) NOT NULL COMMENT '���ڽ��㱸ע'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�������ڽ��㱸ע��';

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
