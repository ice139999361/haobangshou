

INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMM_TYPE','����','����','��ҵ����','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMM_TYPE','��Ӫ','��Ӫ','��ҵ����','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMM_TYPE','����','����','��ҵ����','1','5');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMM_TYPE','̨��','̨��','��ҵ����','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMM_TYPE','����','����','��ҵ����','1','4');
commit;

UPDATE `t_config_encode` SET `ENCODE_VALUE`='�½�' WHERE `ENCODE_TYPE`='SETTLEMENT_TYPE' AND `ENCODE_KEY`='1';
commit;
