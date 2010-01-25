# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_system_config
#

DROP TABLE IF EXISTS `t_system_config`;
CREATE TABLE `t_system_config` (
  `CONFIG_NAME` varchar(128) NOT NULL COMMENT '��������',
  `CONFIG_VALUE` varchar(256) NOT NULL COMMENT '����ֵ',
  `VALUE_TYPE` int(11) NOT NULL COMMENT 'ȡֵ���ͣ�  0��ʾ���ͣ�  1��ʾ�ַ����ͣ�  2��ʾ�����ͣ�  3��ʾ�����ͣ�',
  `VALUE_RANGE` varchar(128) DEFAULT NULL COMMENT 'ȡֵ��Χ',
  `CONFIG_DESC` varchar(128) DEFAULT NULL COMMENT '��������',
  `VISIBLE_FLAG` varchar(1) DEFAULT 'Y' COMMENT '�Ƿ�ɼ���     N�����ɼ�     Y���ɼ�',
  `CONFIG_REGION` varchar(128) DEFAULT NULL COMMENT '����վ�㣬ALL',
  `SUB_SYSTEM` varbinary(32) DEFAULT NULL COMMENT '������ϵͳ����',
  `LAST_UPDATE` datetime DEFAULT NULL COMMENT '����޸�ʱ��',
  PRIMARY KEY (`CONFIG_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='ϵͳ�������ñ�';

#
# Dumping data for table t_system_config
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
