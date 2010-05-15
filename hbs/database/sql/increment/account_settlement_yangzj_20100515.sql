ALTER TABLE `t_cust_account_period`
  CHANGE COLUMN `SETTLEMENT_DAY` `SETTLEMENT_DAY` varchar(4) NOT NULL DEFAULT '' COMMENT '结算日，账期结束的第几日，须大于对账日，对账期结算有效';

ALTER TABLE `t_vendor_account_period`
  CHANGE COLUMN `SETTLEMENT_DAY` `SETTLEMENT_DAY` varchar(4) NOT NULL DEFAULT '' COMMENT '结算日，账期结束的第几日，须大于对账日，对账期结算有效';
ALTER TABLE `t_cust_account_period`
  CHANGE COLUMN `MAX_MONEY` `MAX_MONEY` float(12,2) NULL DEFAULT NULL COMMENT '客户账期的最大交易金额，账期结算有效';
