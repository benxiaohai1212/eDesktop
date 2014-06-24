/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : edesktop-manager

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2014-06-24 10:09:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(50) NOT NULL COMMENT '公司名称',
  `shortname` varchar(50) NOT NULL COMMENT '公司简称',
  `clouduser_id` varchar(20) NOT NULL COMMENT '云用户id',
  `cloud_user` varchar(20) NOT NULL COMMENT '云用户名称',
  `mark_id` int(11) DEFAULT '0' COMMENT '云平台那边的公司ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('4', '专享云-测试3', '111', '182', '179497', '360');

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deptname` varchar(256) NOT NULL COMMENT '部门名称',
  `company_id` int(11) NOT NULL COMMENT '公司id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('12', 'tyuiuu', '315');
INSERT INTO `dept` VALUES ('13', '5678', '315');

-- ----------------------------
-- Table structure for `desktop`
-- ----------------------------
DROP TABLE IF EXISTS `desktop`;
CREATE TABLE `desktop` (
  `description` longtext COMMENT '描述',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instance_id` varchar(11) DEFAULT NULL COMMENT '云主机id',
  `instance_name` longtext NOT NULL COMMENT '桌面名称',
  `port` longtext NOT NULL COMMENT '远程连接端口',
  `ip` longtext NOT NULL COMMENT 'ip地址',
  `instance_type` varchar(20) NOT NULL COMMENT '配置',
  `distribute_status` varchar(20) NOT NULL DEFAULT '空闲' COMMENT '分配状态',
  `date_created` datetime DEFAULT NULL,
  `date_amended` datetime DEFAULT NULL,
  `auto_hand` char(20) DEFAULT NULL COMMENT '1:是自动导入,手动添加:0(以后用)',
  `status` char(20) DEFAULT NULL COMMENT '1:正常，0:非正常',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of desktop
-- ----------------------------
INSERT INTO `desktop` VALUES (null, '74', 'i-20B50456', 'test-gre-145', 'null', '', '通用A型', '空闲', '2014-05-28 18:23:51', '2014-05-28 18:23:51', '1', null);
INSERT INTO `desktop` VALUES (null, '75', 'i-2C4805D0', 'win2003-dc正式', 'null', '', '通用B型', '空闲', '2014-04-30 13:38:10', '2014-04-30 13:38:10', '1', null);
INSERT INTO `desktop` VALUES (null, '76', 'i-37030749', 'sslvpn-new', 'null', '0.0.0.0', '标准A型', '空闲', '2014-05-15 10:19:36', '2014-05-15 10:19:36', '1', null);
INSERT INTO `desktop` VALUES (null, '77', 'i-490E07F1', 'centos5.8-2', 'null', '', '通用B型', '空闲', '2014-03-31 16:57:03', '2014-03-31 16:57:03', '1', null);
INSERT INTO `desktop` VALUES (null, '78', 'i-241804CD', 'win 2003 易桌面', 'null', '0.0.0.0', '通用B型', '空闲', '2014-05-05 13:50:57', '2014-05-05 13:50:57', '1', null);
INSERT INTO `desktop` VALUES (null, '79', 'i-4E1D09CA', '克隆', 'null', '0.0.0.0', '通用B型', '空闲', '2014-05-13 14:23:24', '2014-05-13 14:23:24', '1', null);
INSERT INTO `desktop` VALUES (null, '80', 'i-4F5E09C1', 'sslvpn-NEW130', 'null', '', '标准A型', '空闲', '2014-05-23 12:01:20', '2014-05-23 12:01:20', '1', null);
INSERT INTO `desktop` VALUES (null, '81', 'i-53A00910', 'sslvpn-vdi', 'null', '', '标准A型', '空闲', '2014-05-06 21:30:48', '2014-05-06 21:30:48', '1', null);
INSERT INTO `desktop` VALUES (null, '82', 'i-49D9094E', '云桌面WebService', 'null', '0.0.0.0', '标准A型', '空闲', '2014-05-07 09:18:44', '2014-05-07 09:18:44', '1', null);
INSERT INTO `desktop` VALUES (null, '83', 'i-4A230935', 'test', 'null', '0.0.0.0', '通用A型', '空闲', '2014-05-13 15:52:52', '2014-05-13 15:52:52', '1', null);
INSERT INTO `desktop` VALUES (null, '89', 'i-3A80078C', 'windows2008-dc', 'null', '', '标准A型', '空闲', '2014-04-30 10:19:35', '2014-04-30 10:19:35', '1', 'null');
INSERT INTO `desktop` VALUES (null, '90', 'i-56420AAC', 'windows2003', 'null', '0.0.0.0', '标准B型', '空闲', '2014-03-31 16:44:08', '2014-03-31 16:44:08', '1', 'null');
INSERT INTO `desktop` VALUES (null, '91', 'i-47A8075A', 'centos5.8-1', 'null', '0.0.0.0', '通用B型', '空闲', '2014-03-31 16:45:18', '2014-03-31 16:45:18', '1', 'null');
INSERT INTO `desktop` VALUES (null, '92', 'i-479E07C4', '2008-dc-正式', '3389', '59.42.242.145', '标准A型', '空闲', '2014-04-30 11:48:33', '2014-04-30 11:48:33', '1', 'null');
INSERT INTO `desktop` VALUES (null, '93', 'i-37030749', 'sslvpn-new', 'null', '0.0.0.0', '标准A型', '空闲', '2014-05-15 10:19:36', '2014-05-15 10:19:36', '1', null);
INSERT INTO `desktop` VALUES (null, '94', 'i-241804CD', 'win 2003 易桌面', 'null', '0.0.0.0', '通用B型', '空闲', '2014-05-05 13:50:57', '2014-05-05 13:50:57', '1', null);
INSERT INTO `desktop` VALUES (null, '95', 'i-4E1D09CA', '克隆', 'null', '0.0.0.0', '通用B型', '空闲', '2014-05-13 14:23:24', '2014-05-13 14:23:24', '1', null);
INSERT INTO `desktop` VALUES (null, '96', 'i-49D9094E', '云桌面WebService', 'null', '0.0.0.0', '标准A型', '空闲', '2014-05-07 09:18:44', '2014-05-07 09:18:44', '1', null);
INSERT INTO `desktop` VALUES (null, '97', 'i-4A230935', 'test', 'null', '0.0.0.0', '通用A型', '空闲', '2014-05-13 15:52:52', '2014-05-13 15:52:52', '1', null);
INSERT INTO `desktop` VALUES (null, '98', 'i-56420AAC', 'windows2003', 'null', '0.0.0.0', '标准B型', '空闲', '2014-03-31 16:44:08', '2014-03-31 16:44:08', '1', 'null');
INSERT INTO `desktop` VALUES (null, '99', 'i-47A8075A', 'centos5.8-1', 'null', '0.0.0.0', '通用B型', '空闲', '2014-03-31 16:45:18', '2014-03-31 16:45:18', '1', 'null');
INSERT INTO `desktop` VALUES (null, '100', 'i-479E07C4', '2008-dc-正式', '3389', '59.42.242.145', '标准A型', '空闲', '2014-04-30 11:48:33', '2014-04-30 11:48:33', '1', 'null');

-- ----------------------------
-- Table structure for `desktop_temporary`
-- ----------------------------
DROP TABLE IF EXISTS `desktop_temporary`;
CREATE TABLE `desktop_temporary` (
  `id` int(11) NOT NULL,
  `instance_id` varchar(11) DEFAULT NULL COMMENT '云主机id',
  `instance_name` longtext NOT NULL COMMENT '桌面名称',
  `ip` longtext NOT NULL COMMENT 'ip地址',
  `instance_type` varchar(20) NOT NULL COMMENT '配置',
  `date_created` datetime DEFAULT NULL,
  `date_amended` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of desktop_temporary
-- ----------------------------

-- ----------------------------
-- Table structure for `domain`
-- ----------------------------
DROP TABLE IF EXISTS `domain`;
CREATE TABLE `domain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain_name` longtext NOT NULL COMMENT '域服务器域名',
  `domain_ip` longtext NOT NULL COMMENT '域服务器ip',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of domain
-- ----------------------------
INSERT INTO `domain` VALUES ('8', 'www', '2');

-- ----------------------------
-- Table structure for `euser`
-- ----------------------------
DROP TABLE IF EXISTS `euser`;
CREATE TABLE `euser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(128) NOT NULL COMMENT '用户名',
  `username` varchar(128) NOT NULL COMMENT '显示名',
  `company_id` int(11) NOT NULL COMMENT '公司id',
  `department_id` int(11) DEFAULT NULL COMMENT '部门id',
  `group_id` int(11) DEFAULT '0' COMMENT '组id',
  `status` char(2) DEFAULT '0' COMMENT '状态',
  `desktop_count` int(11) DEFAULT '0' COMMENT '桌面数量',
  `domain_id` longtext NOT NULL COMMENT '域id',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `confirm_password` varchar(20) NOT NULL COMMENT '确认密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(50) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of euser
-- ----------------------------
INSERT INTO `euser` VALUES ('3', '3', '44', '2', '0', '99', '0', '-1', '0', '3', '3', '3', '3');
INSERT INTO `euser` VALUES ('7', '11', '11', '1', '0', '99', '0', '1', '1', 'www11!', 'www11!', '', '');
INSERT INTO `euser` VALUES ('8', '22', '22', '1', '0', '99', '0', '-1', '1', 'www11!', 'www11!', '', '');
INSERT INTO `euser` VALUES ('9', '33', '33', '1', '15', '97', '0', '0', '1', 'wangjiaowang11!', 'wangjiaowang11!', '', '');
INSERT INTO `euser` VALUES ('10', 'ss', 'ss', '1', '0', '0', '0', '0', '1', 'wangjiaowang11!', 'wangjiaowang11!', '', '');
INSERT INTO `euser` VALUES ('11', 'ff', 'ff', '1', '0', '97', '0', '2', '1', 'wangjiaowangjiao11!', 'wangjiaowangjiao11!', '', '');
INSERT INTO `euser` VALUES ('12', 'hjh', '侯建慧', '1', '0', '102', '0', '0', '', 'adminhjh1', 'adminhjh1', 'hjh@123.com', null);

-- ----------------------------
-- Table structure for `euser_desktop`
-- ----------------------------
DROP TABLE IF EXISTS `euser_desktop`;
CREATE TABLE `euser_desktop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `euser_id` varchar(11) DEFAULT NULL COMMENT '用户id',
  `desktop_id` varchar(11) DEFAULT NULL COMMENT '桌面id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of euser_desktop
-- ----------------------------
INSERT INTO `euser_desktop` VALUES ('85', '11', 'NaN');
INSERT INTO `euser_desktop` VALUES ('86', '11', 'NaN');
INSERT INTO `euser_desktop` VALUES ('91', '7', 'NaN');

-- ----------------------------
-- Table structure for `group_strategy`
-- ----------------------------
DROP TABLE IF EXISTS `group_strategy`;
CREATE TABLE `group_strategy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(11) DEFAULT NULL COMMENT '组id',
  `strategies_id` varchar(11) DEFAULT NULL COMMENT '策略id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_strategy
-- ----------------------------
INSERT INTO `group_strategy` VALUES ('62', '99', '1');
INSERT INTO `group_strategy` VALUES ('63', '99', '5');
INSERT INTO `group_strategy` VALUES ('106', '100', '1');
INSERT INTO `group_strategy` VALUES ('107', '100', '4');
INSERT INTO `group_strategy` VALUES ('119', '102', '31');

-- ----------------------------
-- Table structure for `groups`
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL COMMENT '组名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_name` (`group_name`)
) ENGINE=MyISAM AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('97', 'qwe', '2014-06-06 14:27:02');
INSERT INTO `groups` VALUES ('98', '大幅度', '2014-06-06 14:51:02');
INSERT INTO `groups` VALUES ('99', 'sdsa', '2014-06-12 11:47:50');
INSERT INTO `groups` VALUES ('100', '', '2014-06-17 14:57:49');
INSERT INTO `groups` VALUES ('101', '凉快', '2014-06-19 10:55:29');
INSERT INTO `groups` VALUES ('102', 'sslvpn测试（刘）', '2014-06-20 10:35:49');
INSERT INTO `groups` VALUES ('103', '1111', '2014-06-20 17:13:16');

-- ----------------------------
-- Table structure for `strategies`
-- ----------------------------
DROP TABLE IF EXISTS `strategies`;
CREATE TABLE `strategies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL COMMENT '组id',
  `strategies_name` varchar(50) NOT NULL COMMENT '策略名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of strategies
-- ----------------------------
INSERT INTO `strategies` VALUES ('1', '0', 'U盘', '2014-06-04 00:00:00');
INSERT INTO `strategies` VALUES ('5', '0', 'ddd', '2014-04-06 00:00:00');
INSERT INTO `strategies` VALUES ('6', '0', 'aa', '2014-06-05 16:46:04');
INSERT INTO `strategies` VALUES ('7', '0', 'tt', '2014-06-05 17:05:37');
INSERT INTO `strategies` VALUES ('8', '0', 'ddf', '2014-06-06 09:40:25');
INSERT INTO `strategies` VALUES ('27', '0', 'asf', '2014-06-12 11:35:59');
INSERT INTO `strategies` VALUES ('30', '0', 'usb', '2014-06-20 10:19:22');
INSERT INTO `strategies` VALUES ('31', '0', '打印机', '2014-06-20 10:19:54');
INSERT INTO `strategies` VALUES ('32', '0', '剪切板', '2014-06-20 10:20:04');
INSERT INTO `strategies` VALUES ('33', '0', '本地磁盘E', '2014-06-20 10:20:22');
INSERT INTO `strategies` VALUES ('34', '0', '', '2014-06-20 14:44:48');
INSERT INTO `strategies` VALUES ('35', '0', 'aaaaa', '2014-06-20 17:13:42');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', 'admin');
