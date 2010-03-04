# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Dumping data for table t_system_config
#
DELETE FROM `t_system_config`;
COMMIT;
INSERT INTO `t_system_config` VALUES ('ADJUST_REMINDER_DAY','2',0,NULL,'调货管理提醒时长','Y',NULL,NULL,NULL);
INSERT INTO `t_system_config` VALUES ('CUST_ORDER_REMINDER_DAY','3',0,NULL,'客户订单提醒时长','Y',NULL,NULL,NULL);
INSERT INTO `t_system_config` VALUES ('CUST_REMINDER_DAY','5',0,NULL,'客户对账日/结算日提醒时长(天)','Y',NULL,NULL,NULL);
INSERT INTO `t_system_config` VALUES ('PREPAID_REMINDER_DAY','5',0,NULL,'客户催款提醒时长(天)','Y',NULL,NULL,NULL);
INSERT INTO `t_system_config` VALUES ('VENDOR_REMINDER_DAY','5',0,NULL,'供应商对账日/结算日提醒时长(天)','Y',NULL,NULL,NULL);
COMMIT;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
