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




INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1000,NULL,'ҵ���г���','ҵ���г���','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1100,1100,'�ͻ���Ϣ¼��','�ͻ���Ϣ¼��','/customer/editorcustomer.jsp?editorType=add',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1200,1200,'�ͻ���Ϣ��ѯ','�ͻ���Ϣ��ѯ','/customer/querycustomer.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1300,1300,'�ͻ�����¼��','�ͻ�����¼��','/customer/pnrelation.jsp?editorType=add',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1400,1400,'�ͻ����ϲ�ѯ','�ͻ����ϲ�ѯ','/customer/pnquery.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1500,1500,'�ͻ�����¼��','�ͻ�����¼��','/customer/editororder.jsp?editorType=add',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1600,1600,'�ͻ�������ѯ','�ͻ�������ѯ','/customer/queryorder.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1700,1700,'�ͻ���������','�ͻ���������','/customer/editorcargotransfer.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1800,1800,'�ͻ�������ѯ','�ͻ�������ѯ','/customer/querycargotransfer.jsp?roleType=sccustomers',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2000,NULL,'�ɹ���','���ɹ�Աʹ��','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2100,2100,'��Ӧ����Ϣ¼��','¼���µĹ�Ӧ����Ϣ','/vendor/editorvendor.jsp?editorType=add',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2200,2200,'��Ӧ����Ϣ��ѯ','��Ӧ����Ϣ��ѯ','/vendor/queryvendor.jsp?roleType=cgy',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2300,2300,'��Ӧ������¼��','��Ӧ������¼��','/vendor/pnrelation.jsp?editorType=add',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2400,2400,'��Ӧ�����ϲ�ѯ','��Ӧ�����ϲ�ѯ','/vendor/pnquery.jsp?roleType=cgy',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2500,2500,'�ͻ�������ѯ','�ͻ�������ѯ','/customer/queryorder.jsp?roleType=cgy',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2600,2600,'��Ӧ�̶���¼��','��Ӧ�̶���¼��','/vendor/editororder.jsp?editorType=add',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2700,2700,'��Ӧ�̶�����ѯ','��Ӧ�̶�����ѯ','/vendor/queryorder.jsp?roleType=cgy',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3000,NULL,'ҵ���г�������','ҵ���г�������','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3100,3100,'�ͻ���Ϣ��ѯ','�ͻ�����Ϣ��ѯ','/customer/querycustomer.jsp?roleType=scmanager',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3200,3200,'�ͻ���Ϣ����','�ͻ���Ϣ����','/customer/auditcustomer.jsp',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3300,3300,'�ͻ����ϲ�ѯ','�ͻ����ϲ�ѯ','/customer/pnquery.jsp?roleType=scmanager',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3400,3400,'�ͻ���������','�ͻ���������','/customer/auditpnrelation.jsp',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3500,3500,'�ͻ�������ѯ','�ͻ�������ѯ','/customer/queryorder.jsp?roleType=scmanager',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3600,3600,'������Ϣ��ѯ','������Ϣ��ѯ','/customer/querycargotransfer.jsp?roleType=scmanager',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4000,NULL,'�ɹ�������','�ɹ�������','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4100,4100,'��Ӧ����Ϣ��ѯ','��Ӧ����Ϣ��ѯ','/vendor/queryvendor.jsp?roleType=cgm',4000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4200,4200,'��Ӧ����Ϣ����','��Ӧ����Ϣ����','/vendor/auditvendor.jsp',4000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4300,4300,'��Ӧ�����ϲ�ѯ','��Ӧ�����ϲ�ѯ','/vendor/pnquery.jsp?roleType=cgm',4000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4400,4400,'��Ӧ����������','��Ӧ����������','/vendor/auditpnrelation.jsp',4000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5000,NULL,'�ֿ����','�ֿ����','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5100,5100,'����ѯ','����ѯ','/warehouse/querywarehousestore.jsp',5000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5200,5200,'��ⵥ����','��ⵥ����','/warehouse/editorwarehousein.jsp?editorType=add',5000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5300,5300,'��ⵥ��ѯ','��ⵥ��ѯ','/warehouse/querywarehousein.jsp?roleType=warehouse',5000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5400,5400,'���ⵥ����','���ⵥ����','/warehouse/editorwarehouseout.jsp?editorType=add',5000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (5500,5500,'���ⵥ��ѯ','���ⵥ��ѯ','/warehouse/querywarehouseout.jsp?roleType=warehouse',5000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6000,NULL,'����','����','',0,'');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6100,6100,'�ͻ�����ע������','�ͻ�����ע������','/finance/editorreconciliationnote.jsp?beau=customer',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6200,6200,'��Ӧ�̶���ע������','��Ӧ�̶���ע������','/finance/editorreconciliationnote.jsp?beau=vendor',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6300,6300,'�ͻ�������ѯ','�ͻ�������ѯ','/customer/queryorder.jsp?roleType=finance',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6400,6400,'��Ӧ�̶�����ѯ','��Ӧ�̶�����ѯ','/vendor/queryorder.jsp?roleType=finance',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6500,6500,'��������','��������','/finance/inoutwarehouseclearing.jsp',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6600,6600,'�ͻ�����','�ͻ�����','/finance/customerreconciliation.jsp',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6700,6700,'��Ӧ�̶���','��Ӧ�̶���','/finance/vendorreconciliation.jsp',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6800,6800,'��ⵥ��ѯ','��ⵥ��ѯ','/warehouse/querywarehousein.jsp?roleType=finance',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6900,6900,'���ⵥ��ѯ','���ⵥ��ѯ','/warehouse/querywarehouseout.jsp?roleType=finance',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6910,6910,'�ͻ���Ʊ����','�ͻ���Ʊ����','/invoice/customerinvoicemanager.jsp',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6920,6920,'��Ӧ�̷�Ʊ����','��Ӧ�̷�Ʊ����','/invoice/vendorinvoicemanager.jsp',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (7000,NULL,'���񲿾���','���񲿾���','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (7100,7100,'�ͻ�������ѯ','�ͻ�������ѯ','/customer/queryorder.jsp?roleType=financemanager',7000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9000,NULL,'ϵͳ����','һ���˵�','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9100,9100,'�û�����','�û�����','/auth/user!list.action',9000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9101,9101,'�û�����','�û�����','/auth/editoruser.jsp?editorType=add',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9102,9102,'�û��޸�','�û��޸�','/auth/editoruser.jsp?editorType=update',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9200,9200,'��ɫ����','��ɫ����','/auth/role!list.action',9000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9201,9201,'��ɫ����','��ɫ����','/auth/editorrole.jsp?editorType=add',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9202,9202,'��ɫ�޸�','��ɫ�޸�','/auth/editorrole.jsp?editorType=update&roleId=1',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9300,9300,'Ȩ�޹���','Ȩ�޹���','/auth/user!list.action',9000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9301,9301,'�����û�Ȩ��','�����û�Ȩ��','',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9302,9302,'�޸��û�Ȩ��','�޸��û�Ȩ��','',9000,'1');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (9400,9400,'�����Է���','�����Է���','/auth/updatepassword.jsp',9000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (10000,NULL,'��˾����','��˾����','',0,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (10100,10100,'��˾����ά��','��˾����ά��','/materiel/editormateriel.jsp',10000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (10200,10200,'��˾���ϲ�ѯ','��˾���ϲ�ѯ','/materiel/querymateriel.jsp',10000,'0');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
