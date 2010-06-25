INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (1910,'1','','query','查询');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (1920,'1','','query','查询');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (3800,'1','','query','查询');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (2900,'1','','query','查询');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (4700,'1','','query','查询');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (6950,'1','','query','查询');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (6950,'2','','update','保存');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (6960,'1','','query','查询');
INSERT INTO `action` (`ACTIONS_ID`,`ACTION_ID`,`ACTION_NAME`,`BUTTON_ID`,`DESCRIPTION`) VALUES (6960,'2','','update','保存');
commit;

INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1910,1910,'客户结算查询','客户结算查询(业务)','/customer/querycustomerorder.jsp?roleType=scywy',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (1920,1920,'客户结算查询','客户结算查询(业助)','/customer/querycustomerorder.jsp?roleType=scywyzl',1000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (2900,2900,'供应商结算查询','供应商结算查询','/vendor/queryvendororder.jsp?roleType=cgbcgy',2000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (3800,3800,'客户结算查询','客户结算查询','/customer/querycustomerorder.jsp?roleType=scjl',3000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (4700,4700,'供应商结算查询','供应商结算查询','/vendor/queryvendororder.jsp?roleType=cgbjl',4000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6950,6950,'客户结算','客户结算查询','/customer/querycustomerorder.jsp?roleType=caiwu',6000,'0');
INSERT INTO `resource` (`RESOURCE_ID`,`ACTIONS_ID`,`RESOURCE_NAME`,`DESCRIPTION`,`URL_ADDRESS`,`PARENT`,`IS_MENU`) VALUES (6960,6960,'供应商结算','供应商结算查询','/vendor/queryvendororder.jsp?roleType=caiwu',6000,'0');
commit;
