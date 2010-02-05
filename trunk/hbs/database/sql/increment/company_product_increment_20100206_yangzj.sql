DROP TABLE IF EXISTS `t_company_part_no`;
DROP TABLE IF EXISTS `t_product_catagory`;
DROP TABLE IF EXISTS `t_product_class`;

CREATE TABLE `t_company_part_no` (
  `PART_NO` varchar(32) NOT NULL COMMENT '���ϱ��',
  `PN_DESC` varchar(128) NOT NULL COMMENT '��������',
  `PRICE` float(8,4) default '0.0000' COMMENT '�۸�',
  `TAX_PRICE` float(8,4) default '0.0000' COMMENT '��˰�۸�',
  `CREATE_TIME` date NOT NULL COMMENT '����ʱ��',
  `P_CLS_CODE` varchar(20) default NULL COMMENT '������Ʒ���',
  `P_CAT_CODE` varchar(32) default NULL COMMENT '����',
  PRIMARY KEY  (`PART_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='��˾������Ϣ��';

CREATE TABLE `t_product_class` (
  `P_CLS_CODE` int(11) NOT NULL auto_increment COMMENT '��Ʒ������',
  `P_CLS_NAME` varchar(256) NOT NULL COMMENT '��Ʒ�������',
  `P_CLS_DESC` varchar(256) default NULL COMMENT '��Ʒ�����������',
  `PARENT_CLS_CODE` int(11) NOT NULL default '0' COMMENT '�������������� ��Ϊ0��˵��û�и������������',
  `P_CLS_LEVEL` varchar(2) NOT NULL COMMENT '��Ʒ�ֲ㼶��һ������������Ϊ0  �����Ϊ��1---һ�� /2---����/3---����/4---�ļ�  ���趨�嵽�ֵ����',
  PRIMARY KEY  (`P_CLS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='��Ʒ�����';


