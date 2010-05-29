# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gbk' */;

# Host: localhost    Database: hbstestdb
# ------------------------------------------------------
# Server version 5.0.67-community-nt

#
# Dumping data for table t_system_config
#

/*!40101 SET NAMES gb2312 */;

INSERT INTO `t_system_config` (`CONFIG_NAME`,`CONFIG_VALUE`,`VALUE_TYPE`,`VALUE_RANGE`,`CONFIG_DESC`,`VISIBLE_FLAG`,`CONFIG_REGION`,`SUB_SYSTEM`,`LAST_UPDATE`) VALUES ('VendorDate2CustDate','3',0,'>=0','供应商交期到客户交期的天数，供应商订单明细交期确认后，加上此值，即客户订单明细交期','Y','ALL','VendorOrder',NULL);

/*!40101 SET NAMES gbk */;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
