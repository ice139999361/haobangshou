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
# Dumping data for table t_waittask_config_info
#
delete from t_waittask_config_info;
commit;
/*!40000 ALTER TABLE `t_waittask_config_info` DISABLE KEYS */;

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
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR001','0','供应商资料','abc.jsp','$staffName提交了供应商$commCode($shortName)的资料，请您审批！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR002','1','供应商资料','abc.jsp','$staffName审批了供应商$commCode($shortName)的资料，审批结果：同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR003','0','供应商资料','abc.jsp','$staffName审批了供应商$commCode($shortName)的资料，审批结果：不同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_001','0','供应商物料','abc.jsp','$staffName提交了供应商$commCode的物料$cpartNo信息，请您审批!','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_002','1','供应商物料','abc.jsp','$staffName审批了供应商$commCode的物料$cpartNo信息，审批结果：同意！','vendor',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('VENDOR_PARTNO_003','0','供应商物料','abc.jsp','$staffName审批了供应商$commCode的物料$cpartNo信息，审批结果：不同意！','vendor',NULL);
/*!40000 ALTER TABLE `t_waittask_config_info` ENABLE KEYS */;
commit;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
