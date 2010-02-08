ALTER TABLE `t_vendor_part_no_info`  ADD COLUMN `IS_PRICECHANGE` varchar(2) NOT NULL DEFAULT '0' COMMENT '价格是否变动过  0--没有  1--是';
ALTER TABLE `t_cust_part_no_info`  ADD COLUMN `IS_PRICECHANGE` varchar(2) NOT NULL DEFAULT '0' COMMENT '价格是否变动过  0--没有  1--是';

