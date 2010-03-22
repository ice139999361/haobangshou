# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Dumping data for table resource
#

INSERT INTO `resource` VALUES (1000,NULL,'市场部业务员','供业务员使用一级菜单',NULL,0,'0');
INSERT INTO `resource` VALUES (1100,100,'客户信息录入','录入新的客户信息','customer/editorcustomer.jsp?editorType=add',1000,'0');
INSERT INTO `resource` VALUES (1200,200,'客户信息查询','市场部查询客户信息','customer/querycustomer.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` VALUES (2000,NULL,'采购部采购员','供采购员使用一级菜单',NULL,0,'0');
INSERT INTO `resource` VALUES (2100,300,'供应商信息录入','录入新的供应商信息','vendor/editorvendor.jsp?editorType=add',2000,'0');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
