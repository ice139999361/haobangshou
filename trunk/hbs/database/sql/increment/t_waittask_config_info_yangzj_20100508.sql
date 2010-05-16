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

INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('ADJUSTMENT_001','0','调货信息','/customer/querycargotransfer.jsp?roleType=scmanager','$staffName提交了物料$partNo的调货申请，请您审批！','adjust','8');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('ADJUSTMENT_002','1','调货信息','','$staffName审批了物料$partNo的调货申请，审批意见：同意！','adjust',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('ADJUSTMENT_003','1','调货信息','','$staffName审批了物料$partNo的调货申请，审批意见：不同意！','adjust',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER001','0','客户资料','/customer/auditcustomer.jsp','$staffName提交了客户$businessKey的资料，请您审批！','customer','8');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER002','1','客户资料','','$staffName审批了客户$businessKey的资料，审批结果：同意！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER003','0','客户资料','/customer/querycustomer.jsp?roleType=sccustomers','$staffName审批了客户$businessKey的资料，审批结果：不同意！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_001','0','客户订单','/customer/queryorder.jsp?roleType=scmanager','$staffName提交了客户$businessKey的订单，超出账期最大金额请您审批！','customer','8');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_002','0','客户订单','/customer/queryorder.jsp?roleType=cgy','$staffName提交了客户$businessKey的订单，请您确认！','customer','9');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_003','0','客户订单','/customer/queryorder.jsp?roleType=cgy','经理$staffName审批通过了客户$businessKey的订单，请您确认！','customer','9');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_004','0','客户订单','/customer/queryorder.jsp?roleType=sccustomers','经理$staffName审批不通过客户$businessKey的订单，请您处理！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_005','0','客户订单','/customer/queryorder.jsp?roleType=sccustomers','采购$staffName不同意客户$businessKey的订单的交期，请处理！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_006','0','客户订单','/customer/queryorder.jsp?roleType=cgy','$staffName修改了客户$businessKey的交期，请您确认！','customer','9');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_007','0','客户订单','/customer/queryorder.jsp?roleType=sccustomers','财务$staffName退回了了客户$businessKey的订单，请您确认！',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_008','0','客户订单','/customer/queryorder.jsp?roleType=finance','$staffName提交了客户$businessKey的预付费订单，请您确认收到预付费！','customer','12');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_009','0','客户订单','/customer/queryorder.jsp?roleType=scmanager','财务$staffName提交了客户$custCode的预付x%，剩余款到发货，但未收到剩余货款，申请先发货，请审批 ！','customer','8');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_010','1','客户订单','','领导$staffName审批了客户$custCode的预付x%，剩余款到发货，但未收到剩余货款，申请先发货，审批意见：同意 ！',NULL,'12');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_011','1','客户订单','','领导$staffName审批了客户$custCode的预付x%，剩余款到发货，但未收到剩余货款，申请先发货，审批意见：不同意 ！',NULL,'12');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_012','1','客户订单','','客户$custCode的订单$poNo的物料$partNo将于$verDeliveryDate发货，目前货未备齐，请及时催货 ！','customer','9');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_013','1','客户订单','','客户$custCode的订单$poNo的物料$partNo将于$verDeliveryDate发货，目前货已备齐，请及时发货！','customer','11');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_014','1','客户订单','','客户$custCode的订单$poNo的物料$partNo将于$verDeliveryDate发货，目前货已备齐，未收款（款到发货），请及时催款 ！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_015','0','客户订单','/customer/queryorder.jsp?roleType=sccustomers','客户$custCode的订单$poNo的物料$partNo将于$verDeliveryDate发货，目前货未备齐，请决定是否发货 ！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_016','1','客户订单','','客户$custCode的订单$poNo的物料$partNo将于$verDeliveryDate发货，目前货未备齐，业务决定部分发货，请及时发货！',NULL,'11');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_PARTNO_001','0','客户物料','/customer/auditpnrelation.jsp','$staffName提交了客户$commCode的物料$cpartNo信息，请您审批!','customer','8');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_PARTNO_002','1','客户物料','','$staffName审批了客户$commCode的物料$cpartNo信息，审批结果：同意！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_PARTNO_003','0','客户物料','/customer/pnquery.jsp?roleType=sccustomers','$staffName审批了客户$commCode的物料$cpartNo信息，审批结果：不同意！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_REMINDER_001','1','对账日提醒','','客户$custCode的账期$period对账日$accountDay即将到来，请准备对账!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_REMINDER_002','1','对账日提醒','','客户$custCode的账期$period结算日$accountDay即将到来，请准备结算!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_REMINDER_003','1','催款提醒','','客户$custCode的的订单$custPoNo的$custPartNo已发货，发货单号为$sendPoNo,请及时催款!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR001','0','供应商资料','/vendor/auditvendor.jsp','$staffName提交了供应商$commCode($shortName)的资料，请您审批！','vendor','10');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR002','1','供应商资料','','$staffName审批了供应商$commCode($shortName)的资料，审批结果：同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR003','0','供应商资料','/vendor/queryvendor.jsp?roleType=cgy','$staffName审批了供应商$commCode($shortName)的资料，审批结果：不同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_001','0','供应商物料','/vendor/auditpnrelation.jsp','$staffName提交了供应商$commCode的物料$cpartNo信息，请您审批!','vendor','10');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_002','1','供应商物料','','$staffName审批了供应商$commCode的物料$cpartNo信息，审批结果：同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_003','0','供应商物料','http://localhost:8080/admin/vendor/pnquery.jsp?roleType=cgy','$staffName审批了供应商$commCode的物料$cpartNo信息，审批结果：不同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_REMINDER_001','1','对账日提醒','','供应商$custCode的账期$period对账日$accountDay即将到来，请准备对账!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_REMINDER_002','1','对账日提醒','','供应商$custCode的账期$period结算日$accountDay即将到来，请准备结算!',NULL,NULL);

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
