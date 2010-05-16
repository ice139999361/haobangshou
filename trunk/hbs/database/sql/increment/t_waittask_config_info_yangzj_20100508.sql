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

INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('ADJUSTMENT_001','0','������Ϣ','/customer/querycargotransfer.jsp?roleType=scmanager','$staffName�ύ������$partNo�ĵ������룬����������','adjust','8');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('ADJUSTMENT_002','1','������Ϣ','','$staffName����������$partNo�ĵ������룬���������ͬ�⣡','adjust',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('ADJUSTMENT_003','1','������Ϣ','','$staffName����������$partNo�ĵ������룬�����������ͬ�⣡','adjust',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER001','0','�ͻ�����','/customer/auditcustomer.jsp','$staffName�ύ�˿ͻ�$businessKey�����ϣ�����������','customer','8');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER002','1','�ͻ�����','','$staffName�����˿ͻ�$businessKey�����ϣ����������ͬ�⣡','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER003','0','�ͻ�����','/customer/querycustomer.jsp?roleType=sccustomers','$staffName�����˿ͻ�$businessKey�����ϣ������������ͬ�⣡','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_001','0','�ͻ�����','/customer/queryorder.jsp?roleType=scmanager','$staffName�ύ�˿ͻ�$businessKey�Ķ����������������������������','customer','8');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_002','0','�ͻ�����','/customer/queryorder.jsp?roleType=cgy','$staffName�ύ�˿ͻ�$businessKey�Ķ���������ȷ�ϣ�','customer','9');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_003','0','�ͻ�����','/customer/queryorder.jsp?roleType=cgy','����$staffName����ͨ���˿ͻ�$businessKey�Ķ���������ȷ�ϣ�','customer','9');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_004','0','�ͻ�����','/customer/queryorder.jsp?roleType=sccustomers','����$staffName������ͨ���ͻ�$businessKey�Ķ�������������','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_005','0','�ͻ�����','/customer/queryorder.jsp?roleType=sccustomers','�ɹ�$staffName��ͬ��ͻ�$businessKey�Ķ����Ľ��ڣ��봦��','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_006','0','�ͻ�����','/customer/queryorder.jsp?roleType=cgy','$staffName�޸��˿ͻ�$businessKey�Ľ��ڣ�����ȷ�ϣ�','customer','9');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_007','0','�ͻ�����','/customer/queryorder.jsp?roleType=sccustomers','����$staffName�˻����˿ͻ�$businessKey�Ķ���������ȷ�ϣ�',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_008','0','�ͻ�����','/customer/queryorder.jsp?roleType=finance','$staffName�ύ�˿ͻ�$businessKey��Ԥ���Ѷ���������ȷ���յ�Ԥ���ѣ�','customer','12');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_009','0','�ͻ�����','/customer/queryorder.jsp?roleType=scmanager','����$staffName�ύ�˿ͻ�$custCode��Ԥ��x%��ʣ����������δ�յ�ʣ���������ȷ����������� ��','customer','8');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_010','1','�ͻ�����','','�쵼$staffName�����˿ͻ�$custCode��Ԥ��x%��ʣ����������δ�յ�ʣ���������ȷ��������������ͬ�� ��',NULL,'12');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_011','1','�ͻ�����','','�쵼$staffName�����˿ͻ�$custCode��Ԥ��x%��ʣ����������δ�յ�ʣ���������ȷ����������������ͬ�� ��',NULL,'12');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_012','1','�ͻ�����','','�ͻ�$custCode�Ķ���$poNo������$partNo����$verDeliveryDate������Ŀǰ��δ���룬�뼰ʱ�߻� ��','customer','9');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_013','1','�ͻ�����','','�ͻ�$custCode�Ķ���$poNo������$partNo����$verDeliveryDate������Ŀǰ���ѱ��룬�뼰ʱ������','customer','11');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_014','1','�ͻ�����','','�ͻ�$custCode�Ķ���$poNo������$partNo����$verDeliveryDate������Ŀǰ���ѱ��룬δ�տ����������뼰ʱ�߿� ��','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_015','0','�ͻ�����','/customer/queryorder.jsp?roleType=sccustomers','�ͻ�$custCode�Ķ���$poNo������$partNo����$verDeliveryDate������Ŀǰ��δ���룬������Ƿ񷢻� ��','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_016','1','�ͻ�����','','�ͻ�$custCode�Ķ���$poNo������$partNo����$verDeliveryDate������Ŀǰ��δ���룬ҵ��������ַ������뼰ʱ������',NULL,'11');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_PARTNO_001','0','�ͻ�����','/customer/auditpnrelation.jsp','$staffName�ύ�˿ͻ�$commCode������$cpartNo��Ϣ����������!','customer','8');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_PARTNO_002','1','�ͻ�����','','$staffName�����˿ͻ�$commCode������$cpartNo��Ϣ�����������ͬ�⣡','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_PARTNO_003','0','�ͻ�����','/customer/pnquery.jsp?roleType=sccustomers','$staffName�����˿ͻ�$commCode������$cpartNo��Ϣ�������������ͬ�⣡','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_REMINDER_001','1','����������','','�ͻ�$custCode������$period������$accountDay������������׼������!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_REMINDER_002','1','����������','','�ͻ�$custCode������$period������$accountDay������������׼������!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_REMINDER_003','1','�߿�����','','�ͻ�$custCode�ĵĶ���$custPoNo��$custPartNo�ѷ�������������Ϊ$sendPoNo,�뼰ʱ�߿�!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR001','0','��Ӧ������','/vendor/auditvendor.jsp','$staffName�ύ�˹�Ӧ��$commCode($shortName)�����ϣ�����������','vendor','10');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR002','1','��Ӧ������','','$staffName�����˹�Ӧ��$commCode($shortName)�����ϣ����������ͬ�⣡','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR003','0','��Ӧ������','/vendor/queryvendor.jsp?roleType=cgy','$staffName�����˹�Ӧ��$commCode($shortName)�����ϣ������������ͬ�⣡','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_001','0','��Ӧ������','/vendor/auditpnrelation.jsp','$staffName�ύ�˹�Ӧ��$commCode������$cpartNo��Ϣ����������!','vendor','10');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_002','1','��Ӧ������','','$staffName�����˹�Ӧ��$commCode������$cpartNo��Ϣ�����������ͬ�⣡','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_003','0','��Ӧ������','http://localhost:8080/admin/vendor/pnquery.jsp?roleType=cgy','$staffName�����˹�Ӧ��$commCode������$cpartNo��Ϣ�������������ͬ�⣡','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_REMINDER_001','1','����������','','��Ӧ��$custCode������$period������$accountDay������������׼������!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_REMINDER_002','1','����������','','��Ӧ��$custCode������$period������$accountDay������������׼������!',NULL,NULL);

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
