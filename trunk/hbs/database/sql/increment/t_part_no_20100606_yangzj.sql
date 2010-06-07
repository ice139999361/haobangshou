ALTER TABLE `t_cust_part_no_info`
  CHANGE COLUMN `PRICE` `PRICE` float(9,5) NULL DEFAULT 0.00 COMMENT '单价';

ALTER TABLE `t_vendor_part_no_info`
  CHANGE COLUMN `PRICE` `PRICE` float(9,5) NOT NULL DEFAULT 0.00000 COMMENT '单价';

