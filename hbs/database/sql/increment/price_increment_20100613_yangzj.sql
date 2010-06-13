ALTER TABLE `t_cust_part_no_info`
  CHANGE COLUMN `PRICE` `PRICE` decimal(12,10) NULL DEFAULT .00000 COMMENT '单价';

ALTER TABLE `t_cust_order_detail`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '单价';

ALTER TABLE `t_cust_order_detail`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '税率';

  ALTER TABLE `t_cust_order_detail`
  CHANGE COLUMN `MONEY` `MONEY` decimal(12,4) NULL DEFAULT NULL COMMENT '总金额';



ALTER TABLE `t_cust_order_detail_his`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '单价';

ALTER TABLE `t_cust_order_detail_his`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '税率';

  ALTER TABLE `t_cust_order_detail_his`
  CHANGE COLUMN `MONEY` `MONEY` decimal(12,4) NULL DEFAULT NULL COMMENT '总金额';

ALTER TABLE `t_cust_send_invoice`
  CHANGE COLUMN `ALL_MONEY` `ALL_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '总含税金额';

ALTER TABLE `t_cust_send_invoice`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '本次开票金额';

ALTER TABLE `t_cust_send_invoice`
  CHANGE COLUMN `LEFT_MONEY` `LEFT_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '未开票金额';


  ALTER TABLE `t_cust_send_invoice_his`
  CHANGE COLUMN `ALL_MONEY` `ALL_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '总含税金额';

ALTER TABLE `t_cust_send_invoice_his`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '本次开票金额';

ALTER TABLE `t_cust_send_invoice_his`
  CHANGE COLUMN `LEFT_MONEY` `LEFT_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '未开票金额';



ALTER TABLE `t_customer_settlement`
  CHANGE COLUMN `TOTAL_MONEY` `TOTAL_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '总金额';

  ALTER TABLE `t_customer_settlement`
  CHANGE COLUMN `NEED_MONEY` `NEED_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '待结算金额';

  ALTER TABLE `t_customer_settlement`
  CHANGE COLUMN `SET_MONEY` `SET_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '已结算金额';


  ALTER TABLE `t_vendor_info`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT .0000 COMMENT '税率，百分值';


  ALTER TABLE `t_vendor_order_detail`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '单价';
  ALTER TABLE `t_vendor_order_detail`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '税率';
  ALTER TABLE `t_vendor_order_detail`
  CHANGE COLUMN `MONEY` `MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '金额';

  ALTER TABLE `t_vendor_order_detail_his`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '单价';
  ALTER TABLE `t_vendor_order_detail_his`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '税率';
  ALTER TABLE `t_vendor_order_detail_his`
  CHANGE COLUMN `MONEY` `MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '金额';

  ALTER TABLE `t_vendor_part_no_info`
  CHANGE COLUMN `PRICE` `PRICE` decimal(12,10) NOT NULL DEFAULT .00000 COMMENT '单价';

  ALTER TABLE `t_vendor_part_no_info`
  CHANGE COLUMN `PRICE_TAX` `PRICE_TAX` decimal(6,4) NULL DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税';


  ALTER TABLE `t_vendor_receive_invoice`
  CHANGE COLUMN `ALL_MONEY` `ALL_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '总含税金额';
  ALTER TABLE `t_vendor_receive_invoice`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '本次开票金额';
  ALTER TABLE `t_vendor_receive_invoice`
  CHANGE COLUMN `LEFT_MONEY` `LEFT_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '未开票金额';

  ALTER TABLE `t_vendor_receive_invoice_his`
  CHANGE COLUMN `ALL_MONEY` `ALL_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '总含税金额';
  ALTER TABLE `t_vendor_receive_invoice_his`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '本次开票金额';
  ALTER TABLE `t_vendor_receive_invoice_his`
  CHANGE COLUMN `LEFT_MONEY` `LEFT_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '未开票金额';


ALTER TABLE `t_vendor_settlement`
  CHANGE COLUMN `TOTAL_MONEY` `TOTAL_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '总金额';
ALTER TABLE `t_vendor_settlement`
  CHANGE COLUMN `NEED_MONEY` `NEED_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '待结算金额';
ALTER TABLE `t_vendor_settlement`
  CHANGE COLUMN `SET_MONEY` `SET_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '已结算金额';


ALTER TABLE `t_warehouse_receive_detail`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT .00000 COMMENT '采购的单价';
ALTER TABLE `t_warehouse_receive_detail`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '税率';
ALTER TABLE `t_warehouse_receive_detail`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT 0.00 COMMENT '本次送货金额';

ALTER TABLE `t_warehouse_receive_detail_his`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT .00000 COMMENT '采购的单价';
ALTER TABLE `t_warehouse_receive_detail_his`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '税率';
ALTER TABLE `t_warehouse_receive_detail_his`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT 0.00 COMMENT '本次送货金额';

ALTER TABLE `t_warehouse_send_detail`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '物料单价';
ALTER TABLE `t_warehouse_send_detail`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '税率';

ALTER TABLE `t_warehouse_send_detail`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '本次出货金额';


  ALTER TABLE `t_warehouse_send_detail_his`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '物料单价';
ALTER TABLE `t_warehouse_send_detail_his`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '税率';

ALTER TABLE `t_warehouse_send_detail_his`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '本次出货金额';


















