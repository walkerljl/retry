/**
 * 定义表结构
 */

/**
 * 重试任务表
 */
CREATE TABLE retry_job(
	`id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
	`biz_type` VARCHAR(64) NOT NULL COMMENT '业务类型',
	`biz_id` VARCHAR(256) NOT NULL COMMENT '业务ID',
	`priority` TINYINT NOT NULL COMMENT '优先级,1:高,2:中,3:低',
	`target_identifier` VARCHAR(512) NOT NULL COMMENT '目标标识符',
	`attempts` INTEGER NOT NULL COMMENT '当前重试次数',
	`max_attempts` INTEGER NOT NULL COMMENT '最大重试次数',
	`retry_rule` VARCHAR(128) NOT NULL COMMENT '重试规则',
	`last_retry_time` DATETIME NOT NULL COMMENT '上次重试时间',
	`next_retry_time` DATETIME NOT NULL COMMENT '下次重试时间',
	`ext_info` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
	`remark` VARCHAR(256) DEFAULT '' COMMENT '备注',
	`status` VARCHAR(16) NOT NULL COMMENT '状态',
	`creator` VARCHAR(128) NOT NULL COMMENT '创建者',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	`modifier` VARCHAR(128) NOT NULL COMMENT '修改者',
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '重试任务表';
/** 添加索引、约束等*/
ALTER TABLE `retry_job` ADD INDEX idx_biz_id(`biz_id`);
ALTER TABLE `retry_job` ADD UNIQUE KEY(`biz_id`);

/**
 * 重试参数表
 */
CREATE TABLE retry_param(
	`id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
	`retry_job_id` BIGINT(20) NOT NULL COMMENT '重试任务ID',
	`value` VARCHAR(4096) NOT NULL COMMENT '参数值',
	`ext_info` VARCHAR(1024) DEFAULT '' COMMENT '扩展信息',
	`remark` VARCHAR(256) DEFAULT '' COMMENT '备注',
	`status` VARCHAR(16) NOT NULL COMMENT '状态',
	`creator` VARCHAR(64) NOT NULL COMMENT '创建者',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	`modifier` VARCHAR(128) NOT NULL COMMENT '修改者',
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '重试参数表';
/** 添加索引、约束等*/
ALTER TABLE `retry_param` ADD INDEX idx_retry_job_id(`retry_job_id`);

/**
 * 重试日志表
 */
CREATE TABLE retry_log(
	`id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
	`retry_job_id` VARCHAR(128) NOT NULL COMMENT '重试任务ID',
	`attempts` INT NOT NULL COMMENT '当前重试次数',
	`status` VARCHAR(16) NOT NULL COMMENT '状态',
	`description` VARCHAR(1024) DEFAULT '' COMMENT '描述',
	`remark` VARCHAR(256) DEFAULT '' COMMENT '备注',
	`creator` VARCHAR(128) NOT NULL COMMENT '创建者',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '重试日志';
ALTER TABLE `retry_log` ADD INDEX idx_retry_job_id(`retry_job_id`);
