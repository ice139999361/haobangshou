INSERT INTO `action` SET `ACTIONS_ID`=3700, `ACTION_ID`='1', `ACTION_NAME`='', `BUTTON_ID`='query', `DESCRIPTION`='��ѯ';
INSERT INTO `action` SET `ACTIONS_ID`=3700, `ACTION_ID`='2', `ACTION_NAME`='', `BUTTON_ID`='update', `DESCRIPTION`='�޸�';
INSERT INTO `resource` SET `RESOURCE_ID`=3700, `ACTIONS_ID`=3700, `RESOURCE_NAME`='�޸Ŀͻ�������Ա��Ϣ', `DESCRIPTION`='�޸Ŀͻ�������Ա��Ϣ', `URL_ADDRESS`='/customer/updatedetailcustomer.jsp', `PARENT`=3000, `IS_MENU`='0';
commit;
INSERT INTO `action` SET `ACTIONS_ID`=4600, `ACTION_ID`='1', `ACTION_NAME`='', `BUTTON_ID`='query', `DESCRIPTION`='��ѯ';
INSERT INTO `action` SET `ACTIONS_ID`=4600, `ACTION_ID`='2', `ACTION_NAME`='', `BUTTON_ID`='update', `DESCRIPTION`='�޸�';
INSERT INTO `resource` SET `RESOURCE_ID`=4600, `ACTIONS_ID`=4600, `RESOURCE_NAME`='�޸Ĺ�Ӧ�̲ɹ�Ա��Ϣ', `DESCRIPTION`='�޸Ĺ�Ӧ�̲ɹ�Ա��Ϣ', `URL_ADDRESS`='/vendor/updatedetailvendor.jsp', `PARENT`=4000, `IS_MENU`='0';
commit;
UPDATE `testdb`.`action` SET `ACTIONS_ID`=4400 WHERE `ACTIONS_ID`=4500 AND `ACTION_ID`='2' AND `ACTION_NAME`='' AND `BUTTON_ID`='audit' AND `DESCRIPTION`='����';
commit;