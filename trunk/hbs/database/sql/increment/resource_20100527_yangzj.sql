INSERT INTO `action` SET `ACTIONS_ID`=6930, `ACTION_ID`='1', `ACTION_NAME`='', `BUTTON_ID`='query', `DESCRIPTION`='查询';
INSERT INTO `action` SET `ACTIONS_ID`=6940, `ACTION_ID`='1', `ACTION_NAME`='', `BUTTON_ID`='query', `DESCRIPTION`='查询';

INSERT INTO `resource` SET `RESOURCE_ID`=6930, `ACTIONS_ID`=6930, `RESOURCE_NAME`='供应商信息查询', `DESCRIPTION`='供应商信息查询', `URL_ADDRESS`='/vendor/queryvendor.jsp?roleType=cw', `PARENT`=6000 , `IS_MENU`='0';

INSERT INTO `testdb`.`resource` SET `RESOURCE_ID`=6940, `ACTIONS_ID`=6940, `RESOURCE_NAME`='客户信息查询', `DESCRIPTION`='客户信息查询', `URL_ADDRESS`='/customer/querycustomer.jsp?roleType=cw', `PARENT`=6000, `IS_MENU`='0';
commit;

INSERT INTO `action` SET `ACTIONS_ID`=1900, `ACTION_ID`='1', `ACTION_NAME`='', `BUTTON_ID`='query', `DESCRIPTION`='查询';
INSERT INTO `action` SET `ACTIONS_ID`=2800, `ACTION_ID`='1', `ACTION_NAME`='', `BUTTON_ID`='query', `DESCRIPTION`='查询';

INSERT INTO `resource` SET `RESOURCE_ID`=1900, `ACTIONS_ID`=1900, `RESOURCE_NAME`='客户未发货订单查询', `DESCRIPTION`='客户未发货订单查询', `URL_ADDRESS`='/customer/querynosendorder.jsp?roleType=sccustomers', `PARENT`=1000, `IS_MENU`='0';
INSERT INTO `resource` SET `RESOURCE_ID`=2800, `ACTIONS_ID`=2800, `RESOURCE_NAME`='供应商未交货订单查询', `DESCRIPTION`='供应商未交货订单查询', `URL_ADDRESS`='/vendor/querynocommitorder.jsp?roleType=cgy', `PARENT`=2000, `IS_MENU`='0';
commit;