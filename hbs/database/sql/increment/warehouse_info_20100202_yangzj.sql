ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `STAFF_ID` varchar(20) NULL DEFAULT NULL COMMENT '操作员ID';

ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `STAFF_NAME` varchar(32) NULL DEFAULT NULL COMMENT '操作员姓名';

ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `OPER_TIME` datetime NULL DEFAULT NULL COMMENT '操作时间';


ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `STAFF_ID` varchar(20) NULL DEFAULT NULL COMMENT '操作员ID';
                                      
ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `STAFF_NAME` varchar(32) NULL DEFAULT NULL COMMENT '操作员姓名';
                                      
ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `OPER_TIME` datetime NULL DEFAULT NULL COMMENT '操作时间';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `STAFF_ID` varchar(20) NULL DEFAULT NULL COMMENT '操作员ID';                                            

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `STAFF_NAME` varchar(32) NULL DEFAULT NULL COMMENT '操作员姓名';                                        

ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `STAFF_ID` varchar(20) NULL DEFAULT NULL COMMENT '操作员ID';                                            
                                   
ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `STAFF_NAME` varchar(32) NULL DEFAULT NULL COMMENT '操作员姓名'; 