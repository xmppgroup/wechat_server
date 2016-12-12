/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306_wangzhe
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : wechat

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-07-13 16:03:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wcfriend
-- ----------------------------
DROP TABLE IF EXISTS `wcfriend`;
CREATE TABLE `wcfriend` (
  `friendId` int(11) NOT NULL AUTO_INCREMENT,
  `ownerName` varchar(32) NOT NULL,
  `contactName` varchar(32) NOT NULL,
  `subType` varchar(8) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `flag` int(11) NOT NULL DEFAULT '0',
  `modifyDate` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`friendId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wcfriend
-- ----------------------------
