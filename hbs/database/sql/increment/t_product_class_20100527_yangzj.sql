# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Dumping data for table t_product_class
#

INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (100,'电感','电感',0,'1');
INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (200,'电容','电容',0,'1');
INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (300,'变压器','变压器',0,'1');
INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (400,'电阻','电阻',0,'1');
INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (500,'陶瓷振荡器','陶瓷振荡器',0,'1');
INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (600,'开关变压器','开关变压器',0,'1');
INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (700,'滤波器','滤波器',0,'1');
INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (800,'二极管','二极管',0,'1');
INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (900,'三极管','三极管',0,'1');
INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (1000,'磁环','磁环',0,'1');
INSERT INTO `t_product_class` (`P_CLS_CODE`,`P_CLS_NAME`,`P_CLS_DESC`,`PARENT_CLS_CODE`,`P_CLS_LEVEL`) VALUES (1100,'IC','IC',0,'1');
commit;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
