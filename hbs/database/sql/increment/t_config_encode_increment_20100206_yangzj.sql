# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: 192.168.1.29    Database: testdb
# ------------------------------------------------------
# Server version 5.0.81-community

#
# Dumping data for table t_config_encode
#
delete from `t_config_encode`;
commit;
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMPANY_BRANCH','1','������','��˾��֧����','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMPANY_BRANCH','2','¡��','��˾��֧����','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CREDIT_RATE','1','��','�ͻ���Ӧ�����ó̶�','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CREDIT_RATE','2','��','�ͻ���Ӧ�����ó̶�','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CREDIT_RATE','3','һ��','�ͻ���Ӧ�����ó̶�','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CREDIT_RATE','4','��','�ͻ���Ӧ�����ó̶�','1','4');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CREDIT_RATE','5','����','�ͻ���Ӧ�����ó̶�','1','5');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CURRENCY','1','�����','�������','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CURRENCY','2','��Ԫ','�������','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CURRENCY','3','�۱�','�������','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('IMPORTANT_CODE','1','һ��','�ͻ���Ӧ����Ҫ�̶�','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('IMPORTANT_CODE','2','��Ҫ','�ͻ���Ӧ����Ҫ�̶�','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('IMPORTANT_CODE','3','�ǳ���Ҫ','�ͻ���Ӧ����Ҫ�̶�','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('IS_PRIMARY','0','��','�Ƿ�������ϵ��','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('IS_PRIMARY','1','��','�Ƿ�������ϵ��','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('IS_SHOW_PRICE','0','��','�Ƿ���ʾ����','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('IS_SHOW_PRICE','1','��','�Ƿ���ʾ����','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('P_CLS_LEVEL','1','һ������','��Ʒ�ȼ�','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('P_CLS_LEVEL','2','��������','��Ʒ�ȼ�','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('P_CLS_LEVEL','3','��������','��Ʒ�ȼ�','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('SETTLEMENT_TYPE','1','���ڽ���','���㷽ʽ','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('SETTLEMENT_TYPE','2','Ԥ��X%,ʣ���������','���㷽ʽ','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('SETTLEMENT_TYPE','3','Ԥ��X%ʣ������','���㷽ʽ','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_TYPE','1','��˾�ܿ�','�ֿ������','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_USE','1','�ض��ͻ����ϱ���','������;','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('WAREHOUSE_USE','2','�������ϱ���','������;','1','2');
commit;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
