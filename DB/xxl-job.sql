/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.26 : Database - xxl_job
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xxl_job` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `xxl_job`;

/*Table structure for table `xxl_job_group` */

DROP TABLE IF EXISTS `xxl_job_group`;

CREATE TABLE `xxl_job_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) NOT NULL COMMENT '执行器名称',
  `order` tinyint(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `address_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `xxl_job_group` */

insert  into `xxl_job_group`(`id`,`app_name`,`title`,`order`,`address_type`,`address_list`) values (1,'xxl-job-executor-sample','示例执行器',1,0,NULL),(3,'xxl-job-winning','xxl案例',3,1,'10.17.18.37:8888,10.17.18.37:8899'),(7,'xxl-job-winning','xxl集群',2,0,NULL),(8,'xxl-job-demo','xxl-demo',4,0,NULL),(9,'xxl-job-winning','xxl-http',5,0,NULL),(10,'xxl-job-winning','xxl分片',6,0,NULL),(11,'xxl-job-winning','测试执行',7,0,NULL);

/*Table structure for table `xxl_job_info` */

DROP TABLE IF EXISTS `xxl_job_info`;

CREATE TABLE `xxl_job_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128) NOT NULL COMMENT '任务执行CRON',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `xxl_job_info` */

insert  into `xxl_job_info`(`id`,`job_group`,`job_cron`,`job_desc`,`add_time`,`update_time`,`author`,`alarm_email`,`executor_route_strategy`,`executor_handler`,`executor_param`,`executor_block_strategy`,`executor_timeout`,`executor_fail_retry_count`,`glue_type`,`glue_source`,`glue_remark`,`glue_updatetime`,`child_jobid`,`trigger_status`,`trigger_last_time`,`trigger_next_time`) values (1,1,'0 0 0 * * ? *','测试任务1','2018-11-03 22:21:31','2019-07-09 14:39:54','XXL','932356624@qq.com','FIRST','demoJobHandler','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2018-11-03 22:21:31','',1,0,1562688000000),(9,3,'0/2 * * * * ? ','测试任务1','2019-07-08 16:26:48','2019-07-08 17:31:23','方堃','','ROUND','myXxlJob','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2019-07-08 16:26:48','',0,0,0),(10,7,'0/2 * * * * ? ','测试任务1','2019-07-08 16:44:11','2019-07-08 18:06:29','方堃','','ROUND','myXxlJob','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2019-07-08 16:44:11','',0,0,0),(11,8,'0/2 * * * * ? ','测试任务1','2019-07-08 17:14:10','2019-07-08 17:17:50','方堃','','FIRST','','','SERIAL_EXECUTION',0,0,'GLUE_GROOVY','package com.xxl.job.service.handler;\n\nimport com.xxl.job.core.log.XxlJobLogger;\nimport com.xxl.job.core.biz.model.ReturnT;\nimport com.xxl.job.core.handler.IJobHandler;\n\nimport java.time.LocalDate;\nimport java.time.LocalTime;\nimport java.time.format.DateTimeFormatter;\n\npublic class DemoGlueJobHandler extends IJobHandler {\n\n	@Override\n	public ReturnT<String> execute(String param) throws Exception {\n		XxlJobLogger.log(\"XXL-JOB, Hello World.\");\n      XxlJobLogger.log(LocalTime.now().atDate(LocalDate.now()).format(DateTimeFormatter.ofPattern(\"yyyy/MM/dd HH:mm:ss\"))+\"-->>> 端口号为8889 MyXxlJob执行任务\");\n		return ReturnT.SUCCESS;\n	}\n\n}\n','添加一个时间','2019-07-08 17:15:41','',0,0,0),(14,9,'0/5 * * * * ? ','测试任务1','2019-07-08 19:00:14','2019-07-08 19:00:14','方堃','','ROUND','myHttphandler','http://10.17.18.37:7777/run','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2019-07-08 19:00:14','',0,0,0),(16,7,'0/5 * * * * ? ','测试任务2','2019-07-08 19:03:24','2019-07-08 19:26:50','方堃','','FIRST','myHttphandler','http://10.17.18.37:7777/run','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2019-07-08 19:03:24','',0,0,0),(17,8,'0/5 * * * * ? ','测试任务2','2019-07-08 19:05:38','2019-07-08 19:25:41','方堃','','FIRST','myHttphandler','http://10.17.18.37:7777/run','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2019-07-08 19:05:38','',0,0,0),(19,10,'0/5 * * * * ? ','测试任务1','2019-07-08 19:55:49','2019-07-09 14:55:07','方堃','1650849422@qq.com','SHARDING_BROADCAST','myShardingJobHandler','[{\"name\":\"ID001\",\"age\":20},{\"name\":\"ID002\",\"age\":21},{\"name\":\"ID003\",\"age\":22},{\"name\":\"ID004\",\"age\":23},{\"name\":\"ID005\",\"age\":24},{\"name\":\"ID006\",\"age\":25},{\"name\":\"ID007\",\"age\":26},{\"name\":\"ID008\",\"age\":27},{\"name\":\"ID009\",\"age\":28},{\"name\":\"ID010\",\"age\":29},{\"name\":\"ID011\",\"age\":30},{\"name\":\"ID012\",\"age\":31}]','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2019-07-08 19:55:49','',0,0,0),(20,11,'0/5 * * * * ? ','测试执行','2019-07-09 14:36:55','2019-07-09 14:42:19','王松涛','','FIRST','myXxlJob','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2019-07-09 14:36:55','',0,0,0);

/*Table structure for table `xxl_job_lock` */

DROP TABLE IF EXISTS `xxl_job_lock`;

CREATE TABLE `xxl_job_lock` (
  `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `xxl_job_lock` */

insert  into `xxl_job_lock`(`lock_name`) values ('schedule_lock');

/*Table structure for table `xxl_job_log` */

DROP TABLE IF EXISTS `xxl_job_log`;

CREATE TABLE `xxl_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`),
  KEY `I_handle_code` (`handle_code`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

/*Data for the table `xxl_job_log` */

/*Table structure for table `xxl_job_logglue` */

DROP TABLE IF EXISTS `xxl_job_logglue`;

CREATE TABLE `xxl_job_logglue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
  `add_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `xxl_job_logglue` */

insert  into `xxl_job_logglue`(`id`,`job_id`,`glue_type`,`glue_source`,`glue_remark`,`add_time`,`update_time`) values (1,11,'GLUE_GROOVY','package com.xxl.job.service.handler;\n\nimport com.xxl.job.core.log.XxlJobLogger;\nimport com.xxl.job.core.biz.model.ReturnT;\nimport com.xxl.job.core.handler.IJobHandler;\n\nimport java.time.LocalDate;\nimport java.time.LocalTime;\nimport java.time.format.DateTimeFormatter;\n\npublic class DemoGlueJobHandler extends IJobHandler {\n\n	@Override\n	public ReturnT<String> execute(String param) throws Exception {\n		XxlJobLogger.log(\"XXL-JOB, Hello World.\");\n      XxlJobLogger.log(LocalTime.now().atDate(LocalDate.now()).format(DateTimeFormatter.ofPattern(\"yyyy/MM/dd HH:mm:ss\"))+\"-->>> 端口号为8889 MyXxlJob执行任务\");\n		return ReturnT.SUCCESS;\n	}\n\n}\n','添加一个时间','2019-07-08 17:15:41','2019-07-08 17:15:41');

/*Table structure for table `xxl_job_registry` */

DROP TABLE IF EXISTS `xxl_job_registry`;

CREATE TABLE `xxl_job_registry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(255) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`),
  KEY `i_u` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

/*Data for the table `xxl_job_registry` */

/*Table structure for table `xxl_job_user` */

DROP TABLE IF EXISTS `xxl_job_user`;

CREATE TABLE `xxl_job_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `xxl_job_user` */

insert  into `xxl_job_user`(`id`,`username`,`password`,`role`,`permission`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e',1,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
