/*
Navicat MySQL Data Transfer

Source Server         : poorman
Source Server Version : 50553
Source Host           : 139.224.194.154:3306
Source Database       : searchhouse

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2016-11-18 20:57:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for House
-- ----------------------------
DROP TABLE IF EXISTS `House`;
CREATE TABLE `House` (
  `ids` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL COMMENT '标题',
  `price` int(8) NOT NULL COMMENT '价格',
  `tags` varchar(45) DEFAULT NULL COMMENT '特点',
  `imgs` varchar(2000) DEFAULT NULL COMMENT '图片链接',
  `time` varchar(45) DEFAULT NULL COMMENT '更新时间',
  `lat` varchar(45) NOT NULL COMMENT '纬度',
  `lon` varchar(45) NOT NULL COMMENT '经度',
  `lifearound` varchar(300) DEFAULT NULL COMMENT '生活设施',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `houseInfo` varchar(200) DEFAULT NULL COMMENT '房子信息',
  `unit` varchar(100) NOT NULL COMMENT '价格单位',
  `URL` varchar(200) NOT NULL COMMENT '网址',
  PRIMARY KEY (`ids`)
) ENGINE=InnoDB AUTO_INCREMENT=4667 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
