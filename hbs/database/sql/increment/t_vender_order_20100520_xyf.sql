ALTER TABLE `hbstestdb`.`t_vendor_order`
  ADD COLUMN `HASTEN_REMINDER` int(11) NULL COMMENT '追货提醒/天';
ALTER TABLE `hbstestdb`.`t_vendor_order_detail`
  ADD COLUMN `HASTEN_REMINDER` int(11) default NULL COMMENT '追货提醒/天';
