DROP TABLE IF EXISTS `t_company_part_no`;
DROP TABLE IF EXISTS `t_product_catagory`;
DROP TABLE IF EXISTS `t_product_class`;

CREATE TABLE `t_company_part_no` (
  `PART_NO` varchar(32) NOT NULL COMMENT '物料编号',
  `PN_DESC` varchar(128) NOT NULL COMMENT '物料描述',
  `PRICE` float(8,4) default '0.0000' COMMENT '价格',
  `TAX_PRICE` float(8,4) default '0.0000' COMMENT '含税价格',
  `CREATE_TIME` date NOT NULL COMMENT '创建时间',
  `P_CLS_CODE` varchar(20) default NULL COMMENT '所属产品类别',
  `P_CAT_CODE` varchar(32) default NULL COMMENT '废弃',
  PRIMARY KEY  (`PART_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='公司物料信息表';

CREATE TABLE `t_product_class` (
  `P_CLS_CODE` int(11) NOT NULL auto_increment COMMENT '产品类别编码',
  `P_CLS_NAME` varchar(256) NOT NULL COMMENT '产品类别名称',
  `P_CLS_DESC` varchar(256) default NULL COMMENT '产品类别特征描述',
  `PARENT_CLS_CODE` int(11) NOT NULL default '0' COMMENT '本类别所属父类别 ；为0空说明没有父类别，是最大类别',
  `P_CLS_LEVEL` varchar(2) NOT NULL COMMENT '产品分层级别，一级的所属父类为0  级别可为：1---一级 /2---二级/3---三级/4---四级  等需定义到字典表中',
  PRIMARY KEY  (`P_CLS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='产品分类表';


