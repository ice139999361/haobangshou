INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (1910,'1','','query','��ѯ');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (1920,'1','','query','��ѯ');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (3800,'1','','query','��ѯ');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (2900,'1','','query','��ѯ');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (4700,'1','','query','��ѯ');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (6950,'1','','query','��ѯ');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (6950,'2','','update','����');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (6960,'1','','query','��ѯ');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (6960,'2','','update','����');
commit;

INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1910,1910,'�ͻ������ѯ','�ͻ������ѯ(ҵ��)','/customer/querycustomerorder.jsp?roleType=scywy',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1920,1920,'�ͻ������ѯ','�ͻ������ѯ(ҵ��)','/customer/querycustomerorder.jsp?roleType=scywyzl',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2900,2900,'��Ӧ�̽����ѯ','��Ӧ�̽����ѯ','/vendor/queryvendororder.jsp?roleType=cgbcgy',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3800,3800,'�ͻ������ѯ','�ͻ������ѯ','/customer/querycustomerorder.jsp?roleType=scjl',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4700,4700,'��Ӧ�̽����ѯ','��Ӧ�̽����ѯ','/vendor/queryvendororder.jsp?roleType=cgbjl',4000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6950,6950,'�ͻ�����','�ͻ������ѯ','/customer/querycustomerorder.jsp?roleType=caiwu',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6960,6960,'��Ӧ�̽���','��Ӧ�̽����ѯ','/vendor/queryvendororder.jsp?roleType=caiwu',6000,'0');
commit;
