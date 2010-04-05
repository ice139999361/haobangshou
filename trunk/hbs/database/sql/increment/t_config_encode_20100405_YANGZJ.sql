# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Dumping data for table t_config_encode
#

delete from 't_config_encode' where encode_type like 'WAREHOUSE_IN%' OR encode_type like 'WAREHOUSE_OUT%';
COMMIT;
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_IN_DETAIL_STATE','1','临时保存','入库单明细状态','1','1');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_IN_DETAIL_STATE','2','正式提交','入库单明细状态','1','2');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_IN_DETAIL_STATE','3','取消','入库单明细状态','1','3');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_IN_STATE','1','临时保存','入库单状态','1','1');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_IN_STATE','2','正式提交','入库单状态','1','2');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_IN_STATE','3','取消','入库单状态','1','3');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_OUT_DETAIL_STATE','1','临时保存','出库单明细状态','1','1');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_OUT_DETAIL_STATE','2','正式提交','出库单明细状态','1','2');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_OUT_DETAIL_STATE','3','取消','出库单明细状态','1','3');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_OUT_STATE','1','临时保存','出库单状态','1','1');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_OUT_STATE','2','正式提交','出库单状态','1','2');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_OUT_STATE','3','取消','出库单状态','1','3');
COMMIT;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
