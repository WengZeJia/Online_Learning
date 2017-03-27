/*
Navicat MySQL Data Transfer
Source Server         : cztravel02
Source Server Version : 50522
Source Host           : localhost:3306
Source Database       : contribute
Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001
Date: 2017-03-22 17:14:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '1234');
INSERT INTO `admin` VALUES ('2', 'tangly', '1234');

-- ----------------------------
-- Table structure for courese
-- ----------------------------
DROP TABLE IF EXISTS `courese`;
CREATE TABLE `courese` (
  `courese_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `c_name` varchar(255) DEFAULT NULL,
  `c_describe` varchar(1024) DEFAULT NULL,
  `start_time` varchar(200) DEFAULT NULL,
  `release_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `c_type` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`courese_id`),
  KEY `FK_COURESE_USER_ID` (`user_id`),
  CONSTRAINT `FK_COURESE_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courese
-- ----------------------------
INSERT INTO `courese` VALUES (1, 6, '暴漫', '史蒂芬孙', '2017-03-27 17:08', '2017-3-27 17:08:42', 0, '暴走大事件');


-- ----------------------------
-- Table structure for enroll
-- ----------------------------
DROP TABLE IF EXISTS `enroll`;
CREATE TABLE `enroll` (
  `enroll_id` int(11) NOT NULL AUTO_INCREMENT,
  `courese_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `enroll_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`enroll_id`),
  KEY `FK_USER_ID` (`user_id`),
  KEY `FK_COURSE_ID` (`courese_id`),
  CONSTRAINT `FK_COURSE_ID` FOREIGN KEY (`courese_id`) REFERENCES `courese` (`courese_id`),
  CONSTRAINT `FK_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enroll
-- ----------------------------
INSERT INTO `enroll` VALUES (1, 1, 1, '2017-3-27 17:09:45', 1);


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `real_name` varchar(100) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  `role` int(1) DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test', '张牧野', '2017-03-20 10:18:15', '1234', '0');
INSERT INTO `user` VALUES ('2', 'xjn', '东野圭吾', '2017-03-15 13:34:45', '1234', '0');
INSERT INTO `user` VALUES ('3', 'tangly', '唐老师', '2017-03-15 13:34:45', '1234', '1');
INSERT INTO `user` VALUES ('5', 'tangxiansheng2', '唐先生2', null, '1234', '0');
INSERT INTO `user` VALUES ('6', 'zdli', 'lisehng', '2017-03-22 10:36:42', '1234', '1');

-- ----------------------------
-- Table structure for chat_log
-- ----------------------------
DROP TABLE IF EXISTS `chat_log`;
CREATE TABLE `chat_log` (
  `chat_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `from_courese_id` int(11) DEFAULT NULL,
  `from_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`chat_log_id`),
  KEY `FK_CHAT_USER_ID` (`from_user_id`),
  KEY `FK_CHAT_COURSE_ID` (`from_courese_id`),
  CONSTRAINT `FK_CHAT_COURSE_ID` FOREIGN KEY (`from_courese_id`) REFERENCES `courese` (`courese_id`),
  CONSTRAINT `FK_CHAT_USER_ID` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat_log
-- ----------------------------