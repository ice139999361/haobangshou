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
  `CONFIG_NAME` varchar(128) NOT NULL COMMENT '参数名称',
  `CONFIG_VALUE` varchar(256) NOT NULL COMMENT '参数值',
  `VALUE_TYPE` int(11) NOT NULL COMMENT '取值类型，  0表示整型；  1表示字符串型；  2表示布尔型；  3表示日期型；',
  `VALUE_RANGE` varchar(128) DEFAULT NULL COMMENT '取值范围',
  `CONFIG_DESC` varchar(128) DEFAULT NULL COMMENT '参数描述',
  `VISIBLE_FLAG` varchar(1) DEFAULT 'Y' COMMENT '是否可见：     N－不可见     Y－可见',
  `CONFIG_REGION` varchar(128) DEFAULT NULL COMMENT '所属站点，ALL',
  `SUB_SYSTEM` varbinary(32) DEFAULT NULL COMMENT '归属子系统名称',
  `LAST_UPDATE` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`CONFIG_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='系统参数配置表';

#
# Dumping data for table t_system_config
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
