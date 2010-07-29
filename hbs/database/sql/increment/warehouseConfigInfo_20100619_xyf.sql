# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: 192.168.1.250:3309    Database: hbs
# ------------------------------------------------------
# Server version 5.1.46-community

#
# Dumping data for table t_config_encode
#
LOCK TABLES `t_config_encode` WRITE;
/*!40000 ALTER TABLE `t_config_encode` DISABLE KEYS */;

INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_ADDRESS','1','�����б��������˻�·5�ű�������A��1909-1910','�ֿ��ַ','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_ADDRESS','2','�����б��������纽�Ǵ���ϺϹ�ҵ��C��4¥','�ֿ��ַ','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_ADDRESS','3','�����������ǽ�24-32�����´���10¥B�� �ռ���Alan so','�ֿ��ַ','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_CONTACT','3','Alan so','�ֿ���ϵ��','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_CONTACT','2','����','�ֿ���ϵ��','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_CONTACT','1','������','�ֿ���ϵ��','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_TYPE','1','��˾�ܿ�','�ֿ������','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_TYPE','2','�����ֿ�','�ֿ������','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_TYPE','3','��۲ֿ�','�ֿ������','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_TEL','3','00852-35250807','�ֿ�绰','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_TEL','2','','�ֿ�绰','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_TEL','1','0755-27815501','�ֿ�绰','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_ZIP','1','518000','�ֿ��ʱ�','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_ZIP','2','','�ֿ��ʱ�','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_ZIP','3','','�ֿ��ʱ�','1','3');

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;