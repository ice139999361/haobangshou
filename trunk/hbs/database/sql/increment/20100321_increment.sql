ALTER TABLE `t_vendor_info`
  ADD COLUMN `CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '录入时间';
  
ALTER TABLE `t_vendor_oper_log`
  CHANGE COLUMN `OPER_OBJECT` `OPER_OBJECT` varchar(128) NULL DEFAULT NULL COMMENT '操作对象  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER';

ALTER TABLE `t_vendor_oper_log`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '操作的具体对象：  对供应商信息为C_CODE  对供应商物料为C_PART_NO  对供应商订单为CPO_NO';

ALTER TABLE `t_vendor_oper_log_his`
  CHANGE COLUMN `OPER_OBJECT` `OPER_OBJECT` varchar(128) NULL DEFAULT NULL COMMENT '操作对象  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER';

ALTER TABLE `t_vendor_oper_log_his`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '操作的具体对象：  对供应商信息为C_CODE  对供应商物料为C_PART_NO  对供应商订单为CPO_NO';


ALTER TABLE `t_customer_oper_log`
  CHANGE COLUMN `OPER_OBJECT` `OPER_OBJECT` varchar(128) NULL DEFAULT NULL COMMENT '操作对象  CUST_INFO  CUST_P/N  CUST_ORDER';


ALTER TABLE `t_customer_oper_log`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '操作关键字  对客户信息为C_CODE  对物料为C_PART_NO  对订单为订单号  对订单明细为订单明细序列号';

ALTER TABLE `t_customer_oper_log_his`
  CHANGE COLUMN `OPER_OBJECT` `OPER_OBJECT` varchar(128) NULL DEFAULT NULL COMMENT '操作对象  CUST_INFO  CUST_P/N  CUST_ORDER';

ALTER TABLE `t_customer_oper_log_his`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '操作关键字  对客户信息为C_CODE  对物料为C_PART_NO  对订单为订单号  对订单明细为订单明细序列号';
  
