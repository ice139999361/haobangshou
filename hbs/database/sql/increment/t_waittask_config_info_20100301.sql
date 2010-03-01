# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: 192.168.1.29    Database: testdb
# ------------------------------------------------------
# Server version 5.0.81-community

#
# Dumping data for table t_waittask_config_info
#
delete from `t_waittask_config_info`;
commit;

INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('ADJUSTMENT_001','0','调货信息','abc.jsp','$staffName提交了物料$partNo的调货申请，请您审批！','adjust',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('ADJUSTMENT_002','1','调货信息','abc.jsp','$staffName审批了物料$partNo的调货申请，审批意见：同意！','adjust',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('ADJUSTMENT_003','1','调货信息','abc.jsp','$staffName审批了物料$partNo的调货申请，审批意见：不同意！','adjust',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER001','0','客户资料','abc.jsp','$staffName提交了客户$businessKey的资料，请您审批！','customer','1');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER002','1','客户资料','ccc.jsp','$staffName审批了客户$businessKey的资料，审批结果：同意！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER003','0','客户资料','ddd.jsp','$staffName审批了客户$businessKey的资料，审批结果：不同意！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_001','0','客户订单','aaa.jsp','$staffName提交了客户$businessKey的订单，超出账期最大金额请您审批！','customer','1');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_002','0','客户订单','abc.jsp','$staffName提交了客户$businessKey的订单，请您确认！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_003','0','客户订单','aaa.jsp','经理$staffName审批通过了客户$businessKey的订单，请您确认！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_004','0','客户订单','aaa.jsp','经理$staffName审批不通过客户$businessKey的订单，请您处理！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_005','0','客户订单','aaa.jsp','采购$staffName不同意客户$businessKey的订单的交期，请处理！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_006','0','客户订单','abc.jp','$staffName修改了客户$businessKey的交期，请您确认！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_008','0','客户订单','ccc.jsp','$staffName提交了客户$businessKey的预付费订单，请您确认收到预付费！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_PARTNO_001','0','客户物料','123.jsp','$staffName提交了客户$commCode的物料$cpartNo信息，请您审批!','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_PARTNO_002','1','客户物料','abc.jsp','$staffName审批了客户$commCode的物料$cpartNo信息，审批结果：同意！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_PARTNO_003','0','客户物料','abc.jsp','$staffName审批了客户$commCode的物料$cpartNo信息，审批结果：不同意！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_REMINDER_001','1','对账日提醒','abc.jsp','客户$custCode的账期$period对账日$accountDay即将到来，请准备对账!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_REMINDER_002','1','对账日提醒','abc.jsp','客户$custCode的账期$period结算日$accountDay即将到来，请准备结算!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_REMINDER_003','1','催款提醒','abc.jsp','客户$custCode的的订单$custPoNo的$custPartNo已发货，发货单号为$sendPoNo,请及时催款!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR001','0','供应商资料','abc.jsp','$staffName提交了供应商$commCode($shortName)的资料，请您审批！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR002','1','供应商资料','abc.jsp','$staffName审批了供应商$commCode($shortName)的资料，审批结果：同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR003','0','供应商资料','abc.jsp','$staffName审批了供应商$commCode($shortName)的资料，审批结果：不同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_001','0','供应商物料','abc.jsp','$staffName提交了供应商$commCode的物料$cpartNo信息，请您审批!','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_002','1','供应商物料','abc.jsp','$staffName审批了供应商$commCode的物料$cpartNo信息，审批结果：同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_003','0','供应商物料','abc.jsp','$staffName审批了供应商$commCode的物料$cpartNo信息，审批结果：不同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_REMINDER_001','1','对账日提醒','abc.jsp','供应商$custCode的账期$period对账日$accountDay即将到来，请准备对账!',NULL,NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_REMINDER_002','1','对账日提醒','abc.jsp','供应商$custCode的账期$period结算日$accountDay即将到来，请准备结算!',NULL,NULL);

commit;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
