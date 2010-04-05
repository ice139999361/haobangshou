# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Dumping data for table resource
#




INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1000,NULL,'业务市场部','业务市场部','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1100,1100,'客户信息录入','客户信息录入','/customer/editorcustomer.jsp?editorType=add',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1200,1200,'客户信息查询','客户信息查询','/customer/querycustomer.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1300,1300,'客户物料录入','客户物料录入','/customer/pnrelation.jsp?editorType=add',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1400,1400,'客户物料查询','客户物料查询','/customer/pnquery.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1500,1500,'客户订单录入','客户订单录入','/customer/editororder.jsp?editorType=add',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1600,1600,'客户订单查询','客户订单查询','/customer/queryorder.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1700,1700,'客户调货申请','客户调货申请','/customer/editorcargotransfer.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1800,1800,'客户调货查询','客户调货查询','/customer/querycargotransfer.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2000,NULL,'采购部','供采购员使用','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2100,2100,'供应商信息录入','录入新的供应商信息','/vendor/editorvendor.jsp?editorType=add',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2200,2200,'供应商信息查询','供应商信息查询','/vendor/queryvendor.jsp?roleType=cgy',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2300,2300,'供应商物料录入','供应商物料录入','/vendor/pnrelation.jsp?editorType=add',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2400,2400,'供应商物料查询','供应商物料查询','/vendor/pnquery.jsp?roleType=cgy',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2500,2500,'客户订单查询','客户订单查询','/customer/queryorder.jsp?roleType=cgy',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2600,2600,'供应商订单录入','供应商订单录入','/vendor/editororder.jsp?editorType=add',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2700,2700,'供应商订单查询','供应商订单查询','/vendor/queryorder.jsp?roleType=cgy',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3000,NULL,'业务市场部经理','业务市场部经理','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3100,3100,'客户信息查询','客户向信息查询','/customer/querycustomer.jsp?roleType=scmanager',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3200,3200,'客户信息审批','客户信息审批','/customer/auditcustomer.jsp',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3300,3300,'客户物料查询','客户物料查询','/customer/pnquery.jsp?roleType=scmanager',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3400,3400,'客户物料审批','客户物料审批','/customer/auditpnrelation.jsp',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3500,3500,'客户订单查询','客户订单查询','/customer/queryorder.jsp?roleType=scmanager',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3600,3600,'调货信息查询','调货信息查询','/customer/querycargotransfer.jsp?roleType=scmanager',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4000,NULL,'采购部经理','采购部经理','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4100,4100,'供应商信息查询','供应商信息查询','/vendor/queryvendor.jsp?roleType=cgm',4000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4200,4200,'供应商信息审批','供应商信息审批','/vendor/auditvendor.jsp',4000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4300,4300,'供应商物料查询','供应商物料查询','/vendor/pnquery.jsp?roleType=cgm',4000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4400,4400,'供应商物料审批','供应商物料审批','/vendor/auditpnrelation.jsp',4000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5000,NULL,'仓库管理部','仓库管理部','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5100,5100,'库存查询','库存查询','/warehouse/querywarehousestore.jsp',5000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5200,5200,'入库单处理','入库单处理','/warehouse/editorwarehousein.jsp?editorType=add',5000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5300,5300,'入库单查询','入库单查询','/warehouse/querywarehousein.jsp?roleType=warehouse',5000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5400,5400,'出库单处理','出库单处理','/warehouse/editorwarehouseout.jsp?editorType=add',5000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5500,5500,'出库单查询','出库单查询','/warehouse/querywarehouseout.jsp?roleType=warehouse',5000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6000,NULL,'财务部','财务部','',0,'');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6100,6100,'客户对账注意事项','客户对账注意事项','/finance/editorreconciliationnote.jsp?beau=customer',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6200,6200,'供应商对账注意事项','供应商对账注意事项','/finance/editorreconciliationnote.jsp?beau=vendor',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6300,6300,'客户订单查询','客户订单查询','/customer/queryorder.jsp?roleType=finance',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6400,6400,'供应商订单查询','供应商订单查询','/vendor/queryorder.jsp?roleType=finance',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6500,6500,'出入库结算','出入库结算','/finance/inoutwarehouseclearing.jsp',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6600,6600,'客户对账','客户对账','/finance/customerreconciliation.jsp',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6700,6700,'供应商对账','供应商对账','/finance/vendorreconciliation.jsp',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6800,6800,'入库单查询','入库单查询','/warehouse/querywarehousein.jsp?roleType=finance',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6900,6900,'出库单查询','出库单查询','/warehouse/querywarehouseout.jsp?roleType=finance',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6910,6910,'客户发票管理','客户发票管理','/invoice/customerinvoicemanager.jsp',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6920,6920,'供应商发票管理','供应商发票管理','/invoice/vendorinvoicemanager.jsp',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (7000,NULL,'财务部经理','财务部经理','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (7100,7100,'客户订单查询','客户订单查询','/customer/queryorder.jsp?roleType=financemanager',7000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9000,NULL,'系统管理','一级菜单','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9100,9100,'用户管理','用户管理','/auth/user!list.action',9000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9101,9101,'用户新增','用户新增','/auth/editoruser.jsp?editorType=add',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9102,9102,'用户修改','用户修改','/auth/editoruser.jsp?editorType=update',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9200,9200,'角色管理','角色管理','/auth/role!list.action',9000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9201,9201,'角色新增','角色新增','/auth/editorrole.jsp?editorType=add',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9202,9202,'角色修改','角色修改','/auth/editorrole.jsp?editorType=update&roleId=1',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9300,9300,'权限管理','权限管理','/auth/user!list.action',9000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9301,9301,'新增用户权限','新增用户权限','',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9302,9302,'修改用户权限','修改用户权限','',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9400,9400,'密码自服务','密码自服务','/auth/updatepassword.jsp',9000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (10000,NULL,'公司物料','公司物料','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (10100,10100,'公司物料维护','公司物料维护','/materiel/editormateriel.jsp',10000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (10200,10200,'公司物料查询','公司物料查询','/materiel/querymateriel.jsp',10000,'0');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
