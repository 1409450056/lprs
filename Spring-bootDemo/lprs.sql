/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : lprs

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 05/10/2019 14:50:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for numpic
-- ----------------------------
DROP TABLE IF EXISTS `numpic`;
CREATE TABLE `numpic`  (
  `number` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `url` varchar(2083) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of numpic
-- ----------------------------
INSERT INTO `numpic` VALUES ('苏E6HX29', 'http://localhost:8080/image/28f5c5b1-c794-4b17-918c-2166001db6bc.jpg');
INSERT INTO `numpic` VALUES ('闽HB1508', 'http://localhost:8080/image/84749f13-47b1-42fa-971e-7927de557202.jpg');
INSERT INTO `numpic` VALUES ('鲁B995EQ', 'http://localhost:8080/image/32a7c9c5-85f1-49a5-9a1a-bb552a32afa1.jpg');
INSERT INTO `numpic` VALUES ('黑A16341', 'http://localhost:8080/image/8f126cc9-f381-454d-a38b-42e6897b1f54.jpg');

-- ----------------------------
-- Table structure for orderprice
-- ----------------------------
DROP TABLE IF EXISTS `orderprice`;
CREATE TABLE `orderprice`  (
  `orderNo` int(11) NOT NULL,
  `price` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`orderNo`) USING BTREE,
  CONSTRAINT `orderNo` FOREIGN KEY (`orderNo`) REFERENCES `orders` (`orderNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderprice
-- ----------------------------
INSERT INTO `orderprice` VALUES (8, 0);
INSERT INTO `orderprice` VALUES (14, 22);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orderNo` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `deployTime` timestamp(0) NULL DEFAULT NULL,
  `leftTime` timestamp(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`orderNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (8, '粤AY3R28', '2019-09-22 12:47:21', '2019-09-22 13:16:51', 1);
INSERT INTO `orders` VALUES (14, '粤A97999', '2019-09-27 09:13:27', '2019-09-27 09:13:56', 1);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (3, 'zhou997', 'oopoopasd', 'user');
INSERT INTO `users` VALUES (21, 'asfas22', '123123ff', 'user');
INSERT INTO `users` VALUES (29, 'czj', 'czj123', 'admin');
INSERT INTO `users` VALUES (30, 'ljq', '123123', 'user');

SET FOREIGN_KEY_CHECKS = 1;
