INSERT INTO `action` SET `ACTIONS_ID`=3900, `ACTION_ID`='1', `ACTION_NAME`='', `BUTTON_ID`='query', `DESCRIPTION`='查询';
INSERT INTO `action` SET `ACTIONS_ID`=4800, `ACTION_ID`='1', `ACTION_NAME`='', `BUTTON_ID`='query', `DESCRIPTION`='查询';

INSERT INTO `testdb2`.`resource` SET `RESOURCE_ID`=3900, `ACTIONS_ID`=3900, `RESOURCE_NAME`='客户未发货客户订单明细', `DESCRIPTION`='客户未发货客户订单明细', `URL_ADDRESS`='/customer/querynosendordermgr.jsp?roleType=sc', `PARENT`=3000, `IS_MENU`='0';
INSERT INTO `testdb2`.`resource` SET `RESOURCE_ID`=4800, `ACTIONS_ID`=4800, `RESOURCE_NAME`='供应商未交货订单查询', `DESCRIPTION`='供应商未交货订单查询', `URL_ADDRESS`='/vendor/querynocommitordermgr.jsp?roleType=cg', `PARENT`=4000, `IS_MENU`='0';
