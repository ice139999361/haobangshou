ALTER TABLE `t_cust_order_detail`
  ADD COLUMN `ORDERAMOUNT` int(11) NULL DEFAULT 0 COMMENT '���¶�������';

  ALTER TABLE `t_cust_order_detail_his`
  ADD COLUMN `ORDERAMOUNT` int(11) NULL DEFAULT 0 COMMENT '���¶�������';


  ALTER TABLE `t_cust_order`
  ADD COLUMN `SETTLEMENT_DAY` varchar(16) NULL DEFAULT NULL COMMENT '�½����ͣ����½���Ч';


ALTER TABLE `t_cust_order`
  ADD COLUMN `SETTLEMENT_DAYDESC` varchar(32) NULL DEFAULT NULL COMMENT '�½�����';

   ALTER TABLE `t_cust_order_his`
  ADD COLUMN `SETTLEMENT_DAY` varchar(16) NULL DEFAULT NULL COMMENT '�½����ͣ����½���Ч';


ALTER TABLE `t_cust_order_his`
  ADD COLUMN `SETTLEMENT_DAYDESC` varchar(32) NULL DEFAULT NULL COMMENT '�½�����';


ALTER TABLE `t_vendor_order`
  ADD COLUMN `SETTLEMENT_DAY` varchar(16) NULL DEFAULT NULL COMMENT '�½����ͣ����½���Ч';


ALTER TABLE `t_vendor_order`
  ADD COLUMN `SETTLEMENT_DAYDESC` varchar(32) NULL DEFAULT NULL COMMENT '�½�����';

   ALTER TABLE `t_vendor_order_his`
  ADD COLUMN `SETTLEMENT_DAY` varchar(16) NULL DEFAULT NULL COMMENT '�½����ͣ����½���Ч';


ALTER TABLE `t_vendor_order_his`
  ADD COLUMN `SETTLEMENT_DAYDESC` varchar(32) NULL DEFAULT NULL COMMENT '�½�����';
