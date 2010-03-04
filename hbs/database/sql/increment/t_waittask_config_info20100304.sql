# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Dumping data for table t_waittask_config_info
#
DELETE FROM `t_waittask_config_info`;
COMMIT;
INSERT INTO `t_waittask_config_info` VALUES ('ADJUSTMENT_001','0','������Ϣ','abc.jsp','$staffName�ύ������$partNo�ĵ������룬����������','adjust',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('ADJUSTMENT_002','1','������Ϣ','abc.jsp','$staffName����������$partNo�ĵ������룬���������ͬ�⣡','adjust',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('ADJUSTMENT_003','1','������Ϣ','abc.jsp','$staffName����������$partNo�ĵ������룬�����������ͬ�⣡','adjust',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUSTOMER001','0','�ͻ�����','abc.jsp','$staffName�ύ�˿ͻ�$businessKey�����ϣ�����������','customer','1');
INSERT INTO `t_waittask_config_info` VALUES ('CUSTOMER002','1','�ͻ�����','ccc.jsp','$staffName�����˿ͻ�$businessKey�����ϣ����������ͬ�⣡','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUSTOMER003','0','�ͻ�����','ddd.jsp','$staffName�����˿ͻ�$businessKey�����ϣ������������ͬ�⣡','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_001','0','�ͻ�����','aaa.jsp','$staffName�ύ�˿ͻ�$businessKey�Ķ����������������������������','customer','1');
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_002','0','�ͻ�����','abc.jsp','$staffName�ύ�˿ͻ�$businessKey�Ķ���������ȷ�ϣ�','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_003','0','�ͻ�����','aaa.jsp','����$staffName����ͨ���˿ͻ�$businessKey�Ķ���������ȷ�ϣ�','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_004','0','�ͻ�����','aaa.jsp','����$staffName������ͨ���ͻ�$businessKey�Ķ�������������','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_005','0','�ͻ�����','aaa.jsp','�ɹ�$staffName��ͬ��ͻ�$businessKey�Ķ����Ľ��ڣ��봦��','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_006','0','�ͻ�����','abc.jp','$staffName�޸��˿ͻ�$businessKey�Ľ��ڣ�����ȷ�ϣ�','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_007','0','�ͻ�����','abc.jp','����$staffName�˻����˿ͻ�$businessKey�Ķ���������ȷ�ϣ�',NULL,NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_008','0','�ͻ�����','ccc.jsp','$staffName�ύ�˿ͻ�$businessKey��Ԥ���Ѷ���������ȷ���յ�Ԥ���ѣ�','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_009','0','�ͻ�����','abc.jsp','����$staffName�ύ�˿ͻ�$custCode��Ԥ��x%��ʣ����������δ�յ�ʣ���������ȷ����������� ��','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_010','1','�ͻ�����','abc.jsp','�쵼$staffName�����˿ͻ�$custCode��Ԥ��x%��ʣ����������δ�յ�ʣ���������ȷ��������������ͬ�� ��',NULL,NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_ORDER_011','1','�ͻ�����','abc.jsp','�쵼$staffName�����˿ͻ�$custCode��Ԥ��x%��ʣ����������δ�յ�ʣ���������ȷ����������������ͬ�� ��',NULL,NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_PARTNO_001','0','�ͻ�����','123.jsp','$staffName�ύ�˿ͻ�$commCode������$cpartNo��Ϣ����������!','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_PARTNO_002','1','�ͻ�����','abc.jsp','$staffName�����˿ͻ�$commCode������$cpartNo��Ϣ�����������ͬ�⣡','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_PARTNO_003','0','�ͻ�����','abc.jsp','$staffName�����˿ͻ�$commCode������$cpartNo��Ϣ�������������ͬ�⣡','customer',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_REMINDER_001','1','����������','abc.jsp','�ͻ�$custCode������$period������$accountDay������������׼������!',NULL,NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_REMINDER_002','1','����������','abc.jsp','�ͻ�$custCode������$period������$accountDay������������׼������!',NULL,NULL);
INSERT INTO `t_waittask_config_info` VALUES ('CUST_REMINDER_003','1','�߿�����','abc.jsp','�ͻ�$custCode�ĵĶ���$custPoNo��$custPartNo�ѷ�������������Ϊ$sendPoNo,�뼰ʱ�߿�!',NULL,NULL);
INSERT INTO `t_waittask_config_info` VALUES ('VENDOR001','0','��Ӧ������','abc.jsp','$staffName�ύ�˹�Ӧ��$commCode($shortName)�����ϣ�����������','vendor',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('VENDOR002','1','��Ӧ������','abc.jsp','$staffName�����˹�Ӧ��$commCode($shortName)�����ϣ����������ͬ�⣡','vendor',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('VENDOR003','0','��Ӧ������','abc.jsp','$staffName�����˹�Ӧ��$commCode($shortName)�����ϣ������������ͬ�⣡','vendor',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('VENDOR_PARTNO_001','0','��Ӧ������','abc.jsp','$staffName�ύ�˹�Ӧ��$commCode������$cpartNo��Ϣ����������!','vendor',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('VENDOR_PARTNO_002','1','��Ӧ������','abc.jsp','$staffName�����˹�Ӧ��$commCode������$cpartNo��Ϣ�����������ͬ�⣡','vendor',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('VENDOR_PARTNO_003','0','��Ӧ������','abc.jsp','$staffName�����˹�Ӧ��$commCode������$cpartNo��Ϣ�������������ͬ�⣡','vendor',NULL);
INSERT INTO `t_waittask_config_info` VALUES ('VENDOR_REMINDER_001','1','����������','abc.jsp','��Ӧ��$custCode������$period������$accountDay������������׼������!',NULL,NULL);
INSERT INTO `t_waittask_config_info` VALUES ('VENDOR_REMINDER_002','1','����������','abc.jsp','��Ӧ��$custCode������$period������$accountDay������������׼������!',NULL,NULL);
COMMIT;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
