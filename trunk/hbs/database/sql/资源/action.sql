# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Dumping data for table action
#

INSERT INTO `action` VALUES (1,'ACTION_ID','ACTION_NAME','BUTTON_ID','DESCRIPTION');
INSERT INTO `action` VALUES (100,'1','customerInfo/customerInfo!save.action','commit','提交');
INSERT INTO `action` VALUES (100,'2','customerInfo/customerInfo!saveTemp.action','savetemp','保存');
INSERT INTO `action` VALUES (200,'1','customerInfo/customerInfo!list.action','query','查询');
INSERT INTO `action` VALUES (300,'1','vendorInfo/vendorInfo!save.action','commit','提交');
INSERT INTO `action` VALUES (300,'2','vendorInfo/vendorInfo!saveTemp.action','savetemp','保存');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
