ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '收货单（入库单）状态01---仓管创建入库单（临时保存入库单，其他人无法查看）02---仓管确认入库单（入库数据证实确认，其他人可以查看）03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)00---关闭入库单31---财务确认付款';

ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '活动状态PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）';

ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `FINANCE_PERIOD` varchar(8) NULL DEFAULT NULL COMMENT '财务确认的账期或财务调整的账期';

ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '收货单（入库单）状态01---仓管创建入库单（临时保存入库单，其他人无法查看）02---仓管确认入库单（入库数据证实确认，其他人可以查看）03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)00---关闭入库单31---财务确认付款';
                                       
ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '活动状态PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）';
                                      
ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `FINANCE_PERIOD` varchar(8) NULL DEFAULT NULL COMMENT '财务确认的账期或财务调整的账期';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `FINANCE_PERIOD` varchar(8) NULL DEFAULT NULL COMMENT '财务确认的账期或财务调整的账期';
ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `FINANCE_PERIOD` varchar(8) NULL DEFAULT NULL COMMENT '财务确认的账期或财务调整的账期';