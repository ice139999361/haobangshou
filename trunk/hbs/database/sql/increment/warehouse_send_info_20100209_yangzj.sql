ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `C_CODE` varchar(32) NULL DEFAULT NULL COMMENT '�ͻ�����';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `SHORT_NAME` varchar(64) NULL DEFAULT NULL COMMENT '�ͻ����';

ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `C_CODE` varchar(32) NULL DEFAULT NULL COMMENT '�ͻ�����';

ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `SHORT_NAME` varchar(64) NULL DEFAULT NULL COMMENT '�ͻ����';