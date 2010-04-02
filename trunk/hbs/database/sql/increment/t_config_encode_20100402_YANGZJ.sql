# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: 192.168.1.61    Database: testdb
# ------------------------------------------------------
# Server version 5.0.81-community

#
# Dumping data for table t_config_encode
#

DELETE from t_config_encode where encode_type  LIKE 'CUST_ORDER%' OR ENCODE_TYPE like 'VENDOR_ORDER%';
COMMIT;

INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','01','��ʱ����','�ͻ�������ϸ״̬','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','03','������ϸȡ��','�ͻ�������ϸ״̬','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','04','��ҵ��ȷ�Ͻ���','�ͻ�������ϸ״̬','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','05','��ҵ��ȷ�Ϸ���','�ͻ�������ϸ״̬','1','4');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','20','���ɹ�����','�ͻ�������ϸ״̬','1','5');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','21','�ɹ�������','�ͻ�������ϸ״̬','1','6');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','30','������ȷ��Ԥ����','�ͻ�������ϸ״̬','1','13');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','31','������ȷ�Ϸ���','�ͻ�������ϸ״̬','1','15');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','32','������ȷ���յ����','�ͻ�������ϸ״̬','1','16');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','33','���������������δ��','�ͻ�������ϸ״̬','1','17');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','39','�����˻ض�����ϸ','�ͻ�������ϸ״̬','1','14');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','50','�������������','�ͻ�������ϸ״̬','1','7');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','52','������ͨ�������','�ͻ�������ϸ״̬','1','8');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','61','���ַ���','�ͻ�������ϸ״̬','1','9');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','62','ȫ������','�ͻ�������ϸ״̬','1','10');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','70','������','�ͻ�������ϸ״̬','1','12');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_DETAIL_STATE','71','���ֱ���','�ͻ�������ϸ״̬','1','11');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','01','��ʱ����','�ͻ�����״̬','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','03','����ȡ��','�ͻ�����״̬','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','04','��ҵ��ȷ�Ͻ���','�ͻ�����״̬','1','10');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','20','���ɹ�����','�ͻ�����״̬','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','21','�ɹ�������','�ͻ�����״̬','1','4');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','30','������ȷ��Ԥ��','�ͻ�����״̬','1','5');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','39','�����˻ض���','�ͻ�����״̬','1','6');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','50','�������������','�ͻ�����״̬','1','9');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','52','������ͨ�������','�ͻ�����״̬','1','11');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','60','ȫ������','�ͻ�����״̬','1','8');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('CUST_ORDER_STATE','61','���ַ���','�ͻ�����״̬','1','7');

INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_DETAIL_STATE','01','��ʱ����','��Ӧ�̶�����ϸ״̬','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_DETAIL_STATE','02','ȷ�϶�����ϸ','��Ӧ�̶�����ϸ״̬','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_DETAIL_STATE','03','ȡ��������ϸ','��Ӧ�̶�����ϸ״̬','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_DETAIL_STATE','04','������ȷ��','��Ӧ�̶�����ϸ״̬','1','4');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_DETAIL_STATE','60','�������','��Ӧ�̶�����ϸ״̬','1','5');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_DETAIL_STATE','61','ȫ�����','��Ӧ�̶�����ϸ״̬','1','6');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_STATE','01','��ʱ����','��Ӧ�̶���״̬','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_STATE','02','ȷ�϶���','��Ӧ�̶���״̬','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_STATE','03','ȡ������','��Ӧ�̶���״̬','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_STATE','04','������ȷ��','��Ӧ�̶���״̬','1','6');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_STATE','60','�������','��Ӧ�̶���״̬','1','4');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('VENDOR_ORDER_STATE','61','ȫ�����','��Ӧ�̶���״̬','1','5');

COMMIT;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
