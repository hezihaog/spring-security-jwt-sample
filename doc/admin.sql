/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : admin

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 05/09/2020 11:18:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '0正常，1停用',
  `roles` varchar(255) DEFAULT NULL COMMENT '多个角色用逗号间隔',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'admin', '$2a$10$jigYNWCquiQjcoPEmDziB.LbjeOXWmvRuUnybWy74DUImZH0qvbGa', '0', 'ROLE_ADMINE,ROLE__USER');
INSERT INTO `users` VALUES (2, 'user', '$2a$10$jigYNWCquiQjcoPEmDziB.LbjeOXWmvRuUnybWy74DUImZH0qvbGa', '0', 'ROLE_USER');
INSERT INTO `users` VALUES (3, 'alex', '$2a$10$jigYNWCquiQjcoPEmDziB.LbjeOXWmvRuUnybWy74DUImZH0qvbGa', '0', 'ROLE_ADMIN,ROLE__USER');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
