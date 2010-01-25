# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_waittask_config_info
#

DROP TABLE IF EXISTS `t_waittask_config_info`;
CREATE TABLE `t_waittask_config_info` (
  `CONFIG_ID` varchar(20) NOT NULL COMMENT '���ñ��',
  `TASK_TYPE` varchar(3) NOT NULL COMMENT '��������:   0----ҵ�����  1---���Ѵ���  ',
  `BUSINESS_TYPE` varchar(64) NOT NULL COMMENT '�����ҵ������',
  `URL` varchar(512) NOT NULL COMMENT '���������URL',
  `COMMENTS` varchar(128) NOT NULL COMMENT '��������',
  `SYSTEM_NAME` varchar(32) DEFAULT NULL COMMENT '��ϵͳ����',
  `ROLE_ID` varchar(20) DEFAULT NULL COMMENT '���촦���˵Ľ�ɫID',
  PRIMARY KEY (`CONFIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='������Ϣ���ñ�';

#
# Dumping data for table t_waittask_config_info
#


#
# Table structure for table t_waittask_info
#

DROP TABLE IF EXISTS `t_waittask_info`;
CREATE TABLE `t_waittask_info` (
  `TASK_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '������',
  `TASK_TYPE` varchar(30) NOT NULL COMMENT '��������:   0----ҵ�����  1---���Ѵ��� ',
  `BUSINESS_KEY` varchar(128) NOT NULL COMMENT 'ҵ��ؼ���',
  `BUSINESS_TYPE` varchar(30) NOT NULL COMMENT 'ҵ��������',
  `ROLE_ID` varchar(20) DEFAULT NULL COMMENT '���ܵĽ�ɫ',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '���ܵ���',
  `URL` varchar(512) NOT NULL COMMENT '���봦���URL',
  `COMMENTS` varchar(128) NOT NULL COMMENT '���촦������',
  `PARAM` varchar(64) DEFAULT NULL COMMENT '���ܵĲ���',
  `SYSTEM_NAME` varchar(32) DEFAULT NULL COMMENT 'ϵͳ����',
  `CREATE_TIME` datetime NOT NULL COMMENT '����ʱ��',
  `EXPIRE_TIME` datetime DEFAULT NULL COMMENT '���Ѵ������ʱ�䣬�������������Ч��������ϵͳ�Զ�������Ѵ���',
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='���������Ϣ��';

#
# Dumping data for table t_waittask_info
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
