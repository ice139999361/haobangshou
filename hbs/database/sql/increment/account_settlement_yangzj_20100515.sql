ALTER TABLE `t_cust_account_period`
  CHANGE COLUMN `SETTLEMENT_DAY` `SETTLEMENT_DAY` varchar(4) NOT NULL DEFAULT '' COMMENT '�����գ����ڽ����ĵڼ��գ�����ڶ����գ������ڽ�����Ч';

ALTER TABLE `t_vendor_account_period`
  CHANGE COLUMN `SETTLEMENT_DAY` `SETTLEMENT_DAY` varchar(4) NOT NULL DEFAULT '' COMMENT '�����գ����ڽ����ĵڼ��գ�����ڶ����գ������ڽ�����Ч';
