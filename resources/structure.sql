/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : searchhouse

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-09-24 21:47:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `ids` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `price` varchar(10) NOT NULL,
  `tags` varchar(45) DEFAULT NULL,
  `imgs` varchar(2000) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `lat` varchar(45) NOT NULL,
  `lon` varchar(45) NOT NULL,
  `lifearound` varchar(300) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `houseInfo` varchar(200) DEFAULT NULL,
  `unit` varchar(100) NOT NULL,
  PRIMARY KEY (`ids`)
) ENGINE=InnoDB AUTO_INCREMENT=605 DEFAULT CHARSET=utf8;
