ALTER TABLE `hbs`.`t_cust_order_detail`
  CHANGE COLUMN `PO_NO` `PO_NO` varchar(50) NOT NULL DEFAULT '' COMMENT '�ͻ�������';
SHOW CREATE TABLE `hbs`.`t_cust_order_detail`;

ALTER TABLE `hbs`.`t_cust_order`
  CHANGE COLUMN `PO_NO` `PO_NO` varchar(50) NOT NULL DEFAULT '' COMMENT '�ͻ��������';
SHOW CREATE TABLE `hbs`.`t_cust_order`;
