# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Dumping data for table role
#

INSERT INTO `role` VALUES (3,'超级管理员角色','拥有系统所有资源权限');
INSERT INTO `role` VALUES (4,'系统管理员角色','管理系统的权限分配，无其他权限');
INSERT INTO `role` VALUES (5,'业务员角色','维护客户信息，无其他权限');
INSERT INTO `role` VALUES (6,'公司物料维护角色','本公司的物料维护，无其他权限');
INSERT INTO `role` VALUES (7,'业务助理角色','客户物料维护/客户订单处理/客户调货/客户信息维护');
INSERT INTO `role` VALUES (8,'业务市场部经理角色','业务市场部经理角色');
INSERT INTO `role` VALUES (9,'采购部员工角色','采购部员工角色');
INSERT INTO `role` VALUES (10,'采购部经理角色','采购部经理角色');
INSERT INTO `role` VALUES (11,'仓库管理部角色','仓库管理部角色');
INSERT INTO `role` VALUES (12,'财务部角色','财务部角色');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
