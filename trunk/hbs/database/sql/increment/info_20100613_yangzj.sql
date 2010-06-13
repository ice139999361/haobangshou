ALTER TABLE `t_customer_info`
  ADD COLUMN `SALE_TYPE` varchar(32) NULL DEFAULT NULL COMMENT '企业类型';

 ALTER TABLE `t_vendor_info`
  ADD COLUMN `SALE_TYPE` varchar(32) NULL DEFAULT NULL COMMENT '企业类型';

