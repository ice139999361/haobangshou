ALTER TABLE `t_warehouse_info`  CHANGE COLUMN `STATE` `STATE` varchar(2) NOT NULL DEFAULT '0' COMMENT '库存状态  0---有可用库存  1---无可用库存';

ALTER TABLE `t_warehouse_info`  CHANGE COLUMN `HOUSE_TYPE` `HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '' COMMENT '仓库类型，缺省为公司总库';

ALTER TABLE `t_warehouse_info`  CHANGE COLUMN `HOUSE_USE` `HOUSE_USE` varchar(2) NOT NULL DEFAULT '' COMMENT '仓库货物用途  常规货物  特定客户货物';


