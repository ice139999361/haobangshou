# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Dumping data for table t_waittask_config_info
#

INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER001','0','客户资料','abc.jsp','$staffName提交了客户$businessKey的资料，请您审批！','customer','1');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER002','1','客户资料','ccc.jsp','$staffName审批了客户$businessKey的资料，审批结果：同意！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUSTOMER003','0','客户资料','ddd.jsp','$staffName审批了客户$businessKey的资料，审批结果：不同意！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_001','0','客户订单','aaa.jsp','$staffName提交了客户$businessKey的订单，超出账期最大金额请您审批！','customer','1');
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_002','0','客户订单','abc.jsp','$staffName提交了客户$businessKey的订单，请您确认！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_003','0','客户订单','aaa.jsp','经理$staffName审批通过了客户$businessKey的订单，请您确认！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_004','0','客户订单','aaa.jsp','经理$staffName审批不通过客户$businessKey的订单，请您处理！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_005','0','客户订单','aaa.jsp','采购$staffName不同意客户$businessKey的订单的交期，请处理！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_006','0','客户订单','abc.jp','$staffName修改了客户$businessKey的交期，请您确认！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_007','0','客户订单','aaa.jsp','$staffName确认了客户$businessKey的预付款，请您处理！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_008','0','客户订单','ccc.jsp','$staffName提交了客户$businessKey的预付费订单，请您确认收到预付费！','customer',NULL);
INSERT INTO `t_waittask_config_info` (`CONFIG_ID`,`TASK_TYPE`,`BUSINESS_TYPE`,`URL`,`COMMENTS`,`SYSTEM_NAME`,`ROLE_ID`) VALUES ('CUST_ORDER_009','0','客户订单','aaa.jsp','财务$staffName退回了客户$businessKey的预付款订单，请您处理！','customer',NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
