/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : wechat

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-07-12 07:45:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `wcuser`
-- ----------------------------
DROP TABLE IF EXISTS `wcuser`;
CREATE TABLE `wcUser` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL,
  `passWord` varchar(256) NOT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `telephone` varchar(16) DEFAULT NULL,
  `headUrl` varchar(128) DEFAULT NULL,
  `signature` varchar(256) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `location` varchar(128) DEFAULT NULL,
  `birthday` varchar(32) DEFAULT NULL,
  `type` varchar(4) DEFAULT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifyDate` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wcuser
-- ----------------------------
INSERT INTO `wcuser` VALUES ('1', 'vip', 'secret', null, null, null, null, null, null, null, null, '2016-07-06 07:08:20', '20160706070820');
INSERT INTO `wcuser` VALUES ('2', 'zhangsan', '1234', null, null, null, null, null, null, null, null, '2016-07-07 22:44:17', '20160707224417');
INSERT INTO `wcuser` VALUES ('3', 'hj', '1234', null, null, null, null, null, null, null, null, '2016-07-07 23:15:06', '20160707231506');
INSERT INTO `wcuser` VALUES ('4', '15715768373', '123456', null, null, null, null, null, null, null, null, '2016-07-08 06:37:58', '20160708063758');
INSERT INTO `wcuser` VALUES ('5', '15882428141', '123456', null, null, null, null, null, null, null, null, '2016-07-11 07:03:57', '20160711070357');
INSERT INTO `wcuser` VALUES ('6', '15855555855', '123456', null, null, null, null, null, null, null, null, '2016-07-11 07:11:26', '20160711071126');
