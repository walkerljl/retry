/**
 * 数据初始化
 */

/**
 * `retry_job`数据初始化脚本
 */
insert into `retry_job`(`id`,`biz_type`,`biz_id`,`priority`,`target_identifier`,`attempts`,`max_attempts`,`last_retry_time`,
  `next_retry_time`,`retry_rule`,`ext_info`,`remark`,`status`,`creator`,`gmt_create`,`modifier`,`gmt_modified`)
values
(1,'hello','01',1,'CreateUserRetryHandler',0,10,NOW(),NOW(),5000,'','','unprocess','xingxun',NOW(),'xingxun',NOW());

/**
 * `retry_param`数据初始化脚本
 */
insert into `retry_param`(`id`,`retry_job_id`,`value`,`ext_info`,`remark`,`status`,`creator`,`gmt_create`,`modifier`,`gmt_modified`)
values
(1,1,'{"id":"xingxun","name":"行寻"}','','','normal','xingxun',NOW(),'xingxun',NOW());

