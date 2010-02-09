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

INSERT INTO `t_config_encode` VALUES ('COMPANY_BRANCH','1','������','��˾��֧����','1','1');
INSERT INTO `t_config_encode` VALUES ('COMPANY_BRANCH','2','¡��','��˾��֧����','1','2');
INSERT INTO `t_config_encode` VALUES ('CREDIT_RATE','1','��','�ͻ���Ӧ�����ó̶�','1','1');
INSERT INTO `t_config_encode` VALUES ('CREDIT_RATE','2','��','�ͻ���Ӧ�����ó̶�','1','2');
INSERT INTO `t_config_encode` VALUES ('CREDIT_RATE','3','һ��','�ͻ���Ӧ�����ó̶�','1','3');
INSERT INTO `t_config_encode` VALUES ('CREDIT_RATE','4','��','�ͻ���Ӧ�����ó̶�','1','4');
INSERT INTO `t_config_encode` VALUES ('CREDIT_RATE','5','����','�ͻ���Ӧ�����ó̶�','1','5');
INSERT INTO `t_config_encode` VALUES ('CURRENCY','1','�����','�������','1','1');
INSERT INTO `t_config_encode` VALUES ('CURRENCY','2','��Ԫ','�������','1','2');
INSERT INTO `t_config_encode` VALUES ('CURRENCY','3','�۱�','�������','1','3');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','0','��ʽ����','�ͻ���Ϣ״̬','1','1');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','1','��ʱ����','�ͻ���Ϣ״̬','1','2');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','2','����������','�ͻ���Ϣ״̬','1','3');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','3','������ͨ������','�ͻ���Ϣ״̬','1','4');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','4','��������','�ͻ���Ϣ״̬','1','5');
INSERT INTO `t_config_encode` VALUES ('CUSTOMER_INFO_STATE','5','��������','�ͻ���Ϣ״̬','1','6');
INSERT INTO `t_config_encode` VALUES ('IMPORTANT_CODE','1','һ��','�ͻ���Ӧ����Ҫ�̶�','1','1');
INSERT INTO `t_config_encode` VALUES ('IMPORTANT_CODE','2','��Ҫ','�ͻ���Ӧ����Ҫ�̶�','1','2');
INSERT INTO `t_config_encode` VALUES ('IMPORTANT_CODE','3','�ǳ���Ҫ','�ͻ���Ӧ����Ҫ�̶�','1','3');
INSERT INTO `t_config_encode` VALUES ('IS_PRIMARY','0','��','�Ƿ�������ϵ��','1','1');
INSERT INTO `t_config_encode` VALUES ('IS_PRIMARY','1','��','�Ƿ�������ϵ��','1','2');
INSERT INTO `t_config_encode` VALUES ('IS_SHOW_PRICE','0','��','�Ƿ���ʾ����','1','1');
INSERT INTO `t_config_encode` VALUES ('IS_SHOW_PRICE','1','��','�Ƿ���ʾ����','1','2');
INSERT INTO `t_config_encode` VALUES ('P_CLS_LEVEL','1','һ������','��Ʒ�ȼ�','1','1');
INSERT INTO `t_config_encode` VALUES ('P_CLS_LEVEL','2','��������','��Ʒ�ȼ�','1','2');
INSERT INTO `t_config_encode` VALUES ('P_CLS_LEVEL','3','��������','��Ʒ�ȼ�','1','3');
INSERT INTO `t_config_encode` VALUES ('SETTLEMENT_TYPE','1','���ڽ���','���㷽ʽ','1','1');
INSERT INTO `t_config_encode` VALUES ('SETTLEMENT_TYPE','2','Ԥ��X%,ʣ���������','���㷽ʽ','1','2');
INSERT INTO `t_config_encode` VALUES ('SETTLEMENT_TYPE','3','Ԥ��X%ʣ������','���㷽ʽ','1','3');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_TYPE','1','��˾�ܿ�','�ֿ������','1','1');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_USE','1','�ض��ͻ����ϱ���','������;','1','1');
INSERT INTO `t_config_encode` VALUES ('WAREHOUSE_USE','2','�������ϱ���','������;','1','2');
/*!40000 ALTER TABLE `t_config_encode` ENABLE KEYS */;
COMMIT;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
