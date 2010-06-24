INSERT INTO `t_waittask_config_info` SET `CONFIG_ID`='CUST_NO_PARTNO_001', `TASK_TYPE`='1', `BUSINESS_TYPE`='新客户无物料', `URL`='', `COMMENTS`='您提交的客户资料$commCode($shortName)在2日内无交易物料，请及时输入客户物料，否则1天后将清除该客户信息!', `SYSTEM_NAME`='customer';



INSERT INTO `testdb`.`t_waittask_config_info` SET `CONFIG_ID`='CUST_NO_PARTNO_002', `TASK_TYPE`='1', `BUSINESS_TYPE`='新客户无物料', `URL`='', `COMMENTS`='$staffName提交的客户资料$commCode($shortName)在2日内无交易物料,该客户将在1天后被清除!',`ROLE_ID`='8',`SYSTEM_NAME`='customer';
