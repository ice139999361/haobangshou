ALTER TABLE `t_warehouse_receive`
  CHANGE COLUMN `STATE` `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '收货单（入库单）状态  01---仓管创建入库单（临时保存入库单，其他人无法查看）  02---仓管确认入库单（入库数据证实确认，其他人可以查看）  03---仓管取消入库单（需要在确认入库单之前操作，确认的入库单不能操作)  00---关闭入库单  31---财务确认付款';
ALTER TABLE `t_warehouse_receive`
  CHANGE COLUMN `ACTIVE_STATE` `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '活动状态  PAUSE---仓管暂停入库单（暂停状态，入库单的业务状态停留在当前，除仓管外，不能激活入库单）  ACTIVE---仓管激活入库单（激活暂停的入库单，仓管操作）';
ALTER TABLE `testdb`.`t_warehouse_receive`
  CHANGE COLUMN `FINANCE_STATE` `FINANCE_STATE` varchar(2) NOT NULL DEFAULT '0' COMMENT '财务结算状态  0----未对账  1---已对账';
