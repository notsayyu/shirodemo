/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shirodemo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-17 19:51:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', 'a', '00a');
INSERT INTO `goods` VALUES ('2', 'b', '001');
INSERT INTO `goods` VALUES ('3', 'c', '001');
INSERT INTO `goods` VALUES ('4', 'd', '001');
INSERT INTO `goods` VALUES ('5', 'e', '001');
INSERT INTO `goods` VALUES ('6', 'f', '002');
INSERT INTO `goods` VALUES ('7', 'g', '002');
INSERT INTO `goods` VALUES ('8', 'h', '002');
INSERT INTO `goods` VALUES ('10', 'i', '002');
INSERT INTO `goods` VALUES ('11', 'j', '002');
INSERT INTO `goods` VALUES ('12', 'k', '002');
INSERT INTO `goods` VALUES ('13', 'l', '002');
INSERT INTO `goods` VALUES ('14', 'm', '002');
INSERT INTO `goods` VALUES ('15', 'n', '002');
INSERT INTO `goods` VALUES ('20', 'y', '21');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(20) DEFAULT NULL,
  `description` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'add', '增加');
INSERT INTO `permission` VALUES ('2', 'delete', '删除');
INSERT INTO `permission` VALUES ('3', 'update', '修改');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'user');
INSERT INTO `role` VALUES ('2', 'admin');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ctime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', '1', '001', '2018-07-08 12:28:49');
INSERT INTO `test` VALUES ('1', '2', '002', '2018-07-08 12:28:53');
INSERT INTO `test` VALUES ('1', '3', '003', '2018-07-08 12:28:56');
INSERT INTO `test` VALUES ('2', '3', '004', '2018-07-08 12:28:59');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `user` VALUES ('2', '李四', 'e10adc3949ba59abbe56e057f20f883e', '2');
