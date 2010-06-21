ALTER TABLE `t_cust_order_detail`
  ADD COLUMN `ORDERAMOUNT` int(11) NULL DEFAULT 0 COMMENT '已下订单数量';

  ALTER TABLE `t_cust_order_detail_his`
  ADD COLUMN `ORDERAMOUNT` int(11) NULL DEFAULT 0 COMMENT '已下订单数量';


  ALTER TABLE `t_cust_order`
  ADD COLUMN `SETTLEMENT_DAY` varchar(16) NULL DEFAULT NULL COMMENT '月结类型，对月结有效';


ALTER TABLE `t_cust_order`
  ADD COLUMN `SETTLEMENT_DAYDESC` varchar(32) NULL DEFAULT NULL COMMENT '月结描述';

   ALTER TABLE `t_cust_order_his`
  ADD COLUMN `SETTLEMENT_DAY` varchar(16) NULL DEFAULT NULL COMMENT '月结类型，对月结有效';


ALTER TABLE `t_cust_order_his`
  ADD COLUMN `SETTLEMENT_DAYDESC` varchar(32) NULL DEFAULT NULL COMMENT '月结描述';


ALTER TABLE `t_vendor_order`
  ADD COLUMN `SETTLEMENT_DAY` varchar(16) NULL DEFAULT NULL COMMENT '月结类型，对月结有效';


ALTER TABLE `t_vendor_order`
  ADD COLUMN `SETTLEMENT_DAYDESC` varchar(32) NULL DEFAULT NULL COMMENT '月结描述';

   ALTER TABLE `t_vendor_order_his`
  ADD COLUMN `SETTLEMENT_DAY` varchar(16) NULL DEFAULT NULL COMMENT '月结类型，对月结有效';


ALTER TABLE `t_vendor_order_his`
  ADD COLUMN `SETTLEMENT_DAYDESC` varchar(32) NULL DEFAULT NULL COMMENT '月结描述';
