/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : searchhouse

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-09-24 22:12:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `ids` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL COMMENT '标题',
  `price` varchar(10) NOT NULL COMMENT '价格',
  `tags` varchar(45) DEFAULT NULL COMMENT '特点',
  `imgs` varchar(2000) DEFAULT NULL COMMENT '图片链接',
  `time` varchar(45) DEFAULT NULL COMMENT '更新时间',
  `lat` varchar(45) NOT NULL COMMENT '纬度',
  `lon` varchar(45) NOT NULL COMMENT '经度',
  `lifearound` varchar(300) DEFAULT NULL COMMENT '生活设施',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `houseInfo` varchar(200) DEFAULT NULL COMMENT '房子信息',
  `unit` varchar(100) NOT NULL COMMENT '价格单位',
  PRIMARY KEY (`ids`)
) ENGINE=InnoDB AUTO_INCREMENT=605 DEFAULT CHARSET=utf8;
