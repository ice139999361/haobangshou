ALTER TABLE `t_vendor_order`  CHANGE COLUMN `STATE` `STATE` varchar(8) NULL DEFAULT NULL COMMENT '订单业务状态01---采购创建采购订单（采购临时保存订单，其他人无法查看）02---采购确认采购订单,待收货入库（可以正式走流程，其他人可以查看）03---采购取消采购订单（订单取消，所有订单明细全部取消，对账期订单)04---待交期确认(账期订单)00---关闭采购订单60----部分入库61----全部入库';

ALTER TABLE `t_vendor_order_detail`  CHANGE COLUMN `STATE` `STATE` varchar(8) NULL DEFAULT '0' COMMENT '订单业务状态01---采购创建采购订单（采购临时保存订单，其他人无法查看）02---采购确认采购订单,待收货入库（可以正式走流程，其他人可以查看）03---采购取消采购订单（订单取消，所有订单明细全部取消，对账期订单)04---待交期确认(账期订单)00---关闭采购订单60----部分入库61----全部入库';

ALTER TABLE `t_vendor_order`  ADD COLUMN `CUST_C_CODE` varchar(32) NULL DEFAULT NULL COMMENT '针对0----客户采购单，3-- 特定客户备货有效';

ALTER TABLE `t_vendor_order_detail`  CHANGE COLUMN `STATE` `STATE` varchar(8) NULL DEFAULT '0' COMMENT '订单业务状态01---采购创建采购订单（采购临时保存订单，其他人无法查看）02---采购确认采购订单,待收货入库（可以正式走流程，其他人可以查看）03---采购取消采购订单（订单取消，所有订单明细全部取消，对账期订单)04---待交期确认(账期订单)00---关闭采购订单60----部分入库61----全部入库';

ALTER TABLE `t_vendor_order_detail`  ADD COLUMN `CUST_C_CODE` varchar(32) NULL DEFAULT NULL COMMENT '针对0----客户采购单，3-- 特定客户备货有效';

ALTER TABLE `t_vendor_order_detail`  CHANGE COLUMN `PO_NO_TYPE` `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '' COMMENT '供应商订单类型0----客户采购单1---退货单2---常规备货采购单3--特定客户备货采购单';

ALTER TABLE `t_vendor_order`  CHANGE COLUMN `PO_NO_TYPE` `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '0' COMMENT '供应商订单类型0----客户采购单1---退货单2---常规备货采购单3--特定客户备货采购单';
C