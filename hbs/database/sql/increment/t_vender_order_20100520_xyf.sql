ALTER TABLE `hbstestdb`.`t_vendor_order`
  ADD COLUMN `HASTEN_REMINDER` int(11) NULL COMMENT '׷������/��';
ALTER TABLE `hbstestdb`.`t_vendor_order_detail`
  ADD COLUMN `HASTEN_REMINDER` int(11) default NULL COMMENT '׷������/��';
