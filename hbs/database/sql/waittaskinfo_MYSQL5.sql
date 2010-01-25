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
  `CONFIG_ID` varchar(20) NOT NULL COMMENT '配置编号',
  `TASK_TYPE` varchar(3) NOT NULL COMMENT '待办类型:   0----业务待办  1---提醒待办  ',
  `BUSINESS_TYPE` varchar(64) NOT NULL COMMENT '具体的业务类型',
  `URL` varchar(512) NOT NULL COMMENT '待办的连接URL',
  `COMMENTS` varchar(128) NOT NULL COMMENT '待办描述',
  `SYSTEM_NAME` varchar(32) DEFAULT NULL COMMENT '子系统名称',
  `ROLE_ID` varchar(20) DEFAULT NULL COMMENT '待办处理人的角色ID',
  PRIMARY KEY (`CONFIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='待办信息配置表';

#
# Dumping data for table t_waittask_config_info
#


#
# Table structure for table t_waittask_info
#

DROP TABLE IF EXISTS `t_waittask_info`;
CREATE TABLE `t_waittask_info` (
  `TASK_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '待办编号',
  `TASK_TYPE` varchar(30) NOT NULL COMMENT '待办类型:   0----业务待办  1---提醒待办 ',
  `BUSINESS_KEY` varchar(128) NOT NULL COMMENT '业务关键字',
  `BUSINESS_TYPE` varchar(30) NOT NULL COMMENT '业务处理类型',
  `ROLE_ID` varchar(20) DEFAULT NULL COMMENT '接受的角色',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '接受的人',
  `URL` varchar(512) NOT NULL COMMENT '申请处理的URL',
  `COMMENTS` varchar(128) NOT NULL COMMENT '待办处理描述',
  `PARAM` varchar(64) DEFAULT NULL COMMENT '可能的参数',
  `SYSTEM_NAME` varchar(32) DEFAULT NULL COMMENT '系统名称',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `EXPIRE_TIME` datetime DEFAULT NULL COMMENT '提醒待办结束时间，对提醒类待办有效，结束后，系统自动清除提醒待办',
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='待办基本信息表';

#
# Dumping data for table t_waittask_info
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
