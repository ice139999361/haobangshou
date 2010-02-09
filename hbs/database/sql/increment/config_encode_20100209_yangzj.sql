# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Dumping data for table t_config_encode
#
DELETE FROM `t_config_encode`;
COMMIT;
/*!40000 ALTER TABLE `t_config_encode` DISABLE KEYS */;

INSERT INTO `t_config_encode` VALUES ('COMPANY_BRANCH','1','格莱尔','公司分支机构','1','1');
INSERT INTO `t_config_encode` VALUES ('COMPANY_BRANCH','2','隆信','公司分支机构','1','2');
INSERT INTO `t_config_encode` VALUES ('CREDIT_RATE','1','优','客户或供应商信用程度','1','1');
INSERT INTO `t_config_encode` VALUES ('CREDIT_RATE','2','良','客户或供应商信用程度','1','2');
INSERT INTO `t_config_encode` VALUES ('CREDIT_RATE','3','一般','客户或供应商信用程度','1','3');
INSERT INTO `t_config_encode` VALUES ('CREDIT_RATE','4','差','客户或供应商信用程度','1','4');
INSERT INTO `t_config_encode` VALUES ('CREDIT_RATE','5','极差','客户或供应商信用程度','1','5');
INSERT INTO `t_config_encode` VALUES ('CURRENCY','1','人民币','结算币种','1','1');
INSERT INTO `t_config_encode` VALUES ('CURRENCY','2','美元','结算币种','1','2');
INSERT INTO `t_config_encode` VALUES ('CURRENCY','3','港币','结算币种','1','3');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','0','正式数据','客户信息状态','1','1');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','1','临时数据','客户信息状态','1','2');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','2','待审批数据','客户信息状态','1','3');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','3','审批不通过数据','客户信息状态','1','4');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','4','废弃数据','客户信息状态','1','5');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','5','锁定数据','客户信息状态','1','6');
INSERT INTO `t_config_encode` VALUES ('IMPORTANT_CODE','1','一般','客户或供应商重要程度','1','1');
INSERT INTO `t_config_encode` VALUES ('IMPORTANT_CODE','2','重要','客户或供应商重要程度','1','2');
INSERT INTO `t_config_encode` VALUES ('IMPORTANT_CODE','3','非常重要','客户或供应商重要程度','1','3');
INSERT INTO `t_config_encode` VALUES ('IS_PRIMARY','0','是','是否是主联系人','1','1');
INSERT INTO `t_config_encode` VALUES ('IS_PRIMARY','1','否','是否是主联系人','1','2');
INSERT INTO `t_config_encode` VALUES ('IS_SHOW_PRICE','0','是','是否显示单价','1','1');
INSERT INTO `t_config_encode` VALUES ('IS_SHOW_PRICE','1','否','是否显示单价','1','2');
INSERT INTO `t_config_encode` VALUES ('P_CLS_LEVEL','1','一级分类','产品等级','1','1');
INSERT INTO `t_config_encode` VALUES ('P_CLS_LEVEL','2','二级分类','产品等级','1','2');
INSERT INTO `t_config_encode` VALUES ('P_CLS_LEVEL','3','三级分类','产品等级','1','3');
INSERT INTO `t_config_encode` VALUES ('SETTLEMENT_TYPE','1','账期结算','结算方式','1','1');
INSERT INTO `t_config_encode` VALUES ('SETTLEMENT_TYPE','2','预付X%,剩余货到付款','结算方式','1','2');
INSERT INTO `t_config_encode` VALUES ('SETTLEMENT_TYPE','3','预付X%剩余款到发货','结算方式','1','3');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_TYPE','1','公司总库','仓库的种类','1','1');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_USE','1','特定客户物料备货','物料用途','1','1');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_USE','2','常规物料备货','物料用途','1','2');
/*!40000 ALTER TABLE `t_config_encode` ENABLE KEYS */;
COMMIT;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
