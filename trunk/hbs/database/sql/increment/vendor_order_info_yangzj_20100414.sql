ALTER TABLE `t_vendor_order`  CHANGE COLUMN `OPER_ID` `STAFF_ID` varchar(20) NULL DEFAULT NULL COMMENT '操作人员ID';
ALTER TABLE `t_vendor_order`  CHANGE COLUMN `OPER_STAFF` `STAFF_NAME` varchar(32) NULL DEFAULT NULL COMMENT '录入订单的人';
  
ALTER TABLE `t_vendor_order_his`  CHANGE COLUMN `OPER_ID` `STAFF_ID` varchar(20) NULL DEFAULT NULL COMMENT '操作人员ID';

ALTER TABLE `t_vendor_order_his`  CHANGE COLUMN `OPER_STAFF` `STAFF_NAME` varchar(32) NULL DEFAULT NULL COMMENT '录入订单的人';

ALTER TABLE `t_vendor_order_his`  CHANGE COLUMN `ACTIVE_SATE` `ACTIVE_STATE` varchar(8) NULL DEFAULT NULL COMMENT '订单活动状态：  PAUSE---采购暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---采购激活订单（激活暂停的订单，采购操作）';

ALTER TABLE `t_vendor_order`  CHANGE COLUMN `ACTIVE_SATE` `ACTIVE_STATE` varchar(8) NULL DEFAULT NULL COMMENT '订单活动状态：  PAUSE---采购暂停订单（暂停状态，订单的业务状态停留在当前，除业务外，不能激活订单）  ACTIVE---采购激活订单（激活暂停的订单，采购操作）';

