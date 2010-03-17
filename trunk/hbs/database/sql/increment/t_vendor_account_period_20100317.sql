ALTER TABLE `t_vendor_account_period`
  CHANGE COLUMN `PERIOD_START` `PERIOD_START` varchar(8) DEFAULT NULL COMMENT '账期的起始日，账期结算有效,格式YYYYMMDD';
