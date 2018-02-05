/*
Navicat MySQL Data Transfer

Source Server         : uifuture
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : split_horizon

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-02-05 22:24:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user0
-- ----------------------------
DROP TABLE IF EXISTS `user0`;
CREATE TABLE `user0` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(32) NOT NULL DEFAULT '',
  `pwd` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user0
-- ----------------------------

-- ----------------------------
-- Table structure for user1
-- ----------------------------
DROP TABLE IF EXISTS `user1`;
CREATE TABLE `user1` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(32) NOT NULL DEFAULT '',
  `pwd` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user1
-- ----------------------------

-- ----------------------------
-- Table structure for user2
-- ----------------------------
DROP TABLE IF EXISTS `user2`;
CREATE TABLE `user2` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(32) NOT NULL DEFAULT '',
  `pwd` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user2
-- ----------------------------

-- ----------------------------
-- Table structure for uuid
-- ----------------------------
DROP TABLE IF EXISTS `uuid`;
CREATE TABLE `uuid` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uuid
-- ----------------------------
