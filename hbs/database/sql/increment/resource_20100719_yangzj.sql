INSERT INTO `action` SET `ACTIONS_ID`=3900, `ACTION_ID`='1', `ACTION_NAME`='', `BUTTON_ID`='query', `DESCRIPTION`='��ѯ';
INSERT INTO `action` SET `ACTIONS_ID`=4800, `ACTION_ID`='1', `ACTION_NAME`='', `BUTTON_ID`='query', `DESCRIPTION`='��ѯ';

INSERT INTO `testdb2`.`resource` SET `RESOURCE_ID`=3900, `ACTIONS_ID`=3900, `RESOURCE_NAME`='�ͻ�δ�����ͻ�������ϸ', `DESCRIPTION`='�ͻ�δ�����ͻ�������ϸ', `URL_ADDRESS`='/customer/querynosendordermgr.jsp?roleType=sc', `PARENT`=3000, `IS_MENU`='0';
INSERT INTO `testdb2`.`resource` SET `RESOURCE_ID`=4800, `ACTIONS_ID`=4800, `RESOURCE_NAME`='��Ӧ��δ����������ѯ', `DESCRIPTION`='��Ӧ��δ����������ѯ', `URL_ADDRESS`='/vendor/querynocommitordermgr.jsp?roleType=cg', `PARENT`=4000, `IS_MENU`='0';
