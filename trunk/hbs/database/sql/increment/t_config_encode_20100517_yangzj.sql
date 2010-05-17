

INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMM_TYPE','港资','港资','企业性质','1','3');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMM_TYPE','民营','民营','企业性质','1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMM_TYPE','其它','其它','企业性质','1','5');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMM_TYPE','台资','台资','企业性质','1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('COMM_TYPE','外资','外资','企业性质','1','4');
commit;

UPDATE `t_config_encode` SET `ENCODE_VALUE`='月结' WHERE `ENCODE_TYPE`='SETTLEMENT_TYPE' AND `ENCODE_KEY`='1';
commit;
