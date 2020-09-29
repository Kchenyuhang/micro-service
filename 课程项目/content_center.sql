/*
 Navicat Premium Data Transfer

 Source Server         : my_cloud_sql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 112.74.99.22:3306
 Source Schema         : content_center

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 29/09/2020 18:35:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mid_user_share
-- ----------------------------
DROP TABLE IF EXISTS `mid_user_share`;
CREATE TABLE `mid_user_share`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `share_id` int(0) NOT NULL COMMENT 'share.id',
  `user_id` int(0) NOT NULL COMMENT 'user.id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_mid_user_share_share1_idx`(`share_id`) USING BTREE,
  INDEX `fk_mid_user_share_user1_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-分享中间表【描述用户购买的分享】' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mid_user_share
-- ----------------------------
INSERT INTO `mid_user_share` VALUES (1, 1, 1);
INSERT INTO `mid_user_share` VALUES (2, 1, 2);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `show_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0:否 1:是',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, 'Java学习', 0, '2020-09-29 15:20:52');
INSERT INTO `notice` VALUES (2, 'Python学习', 0, '2020-09-29 15:21:05');

-- ----------------------------
-- Table structure for rocketmq_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `rocketmq_transaction_log`;
CREATE TABLE `rocketmq_transaction_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `transaction_Id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事务id',
  `log` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'RocketMQ事务日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(0) NOT NULL DEFAULT 0 COMMENT '发布人id',
  `title` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `is_original` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否原创 0:否 1:是',
  `author` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '作者',
  `cover` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '封面',
  `summary` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '概要信息',
  `price` int(0) NOT NULL DEFAULT 0 COMMENT '价格（需要的积分）',
  `download_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '下载地址',
  `buy_count` int(0) NOT NULL DEFAULT 0 COMMENT '下载数 ',
  `show_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0:否 1:是',
  `audit_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过',
  `reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '审核不通过原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分享表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES (1, 3, '面向对象编程', '2020-09-29 15:24:34', '2020-09-29 15:24:36', 0, '李四', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/s26097721.jpg', '《JavaScript面向对象编程指南》内容包括：JavaScript作为一门浏览器语言的核心思想；面向对象编程的基础知识及其在JavaScript中的运用；数据类型、操作符以及流程控制语句；函数、闭包、对象和原型等概念，以代码重用为目的的继承模式；BOM、DOM、浏览器事件、AJAX和JSON；如何实现JavaScript中缺失的面向对象特性，如对象的私有成员与私有方法；如何应用适当的编程模式，发挥JavaScript语言特有的优势；如何应用设计模式解决常见问题等。', 65, 'https://book.douban.com/subject/21372235/', 0, 0, 'PASSED', '');
INSERT INTO `share` VALUES (2, 2, 'Python编程 : 从入门到实践', '2020-09-29 15:40:25', '2020-09-29 15:40:28', 0, '[美] 埃里克·马瑟斯', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/s28891775.jpg', '本书是一本针对所有层次的Python 读者而作的Python 入门书。全书分两部分：第一部分介绍用Python 编程所必须了解的基本概念，包括matplotlib、NumPy 和Pygal 等强大的Python 库和工具介绍，以及列表、字典、if 语句、类、文件与异常、代码测试等内容；第二部分将理论付诸实践，讲解如何开发三个项目，包括简单的Python 2D 游戏开发如何利用数据生成交互式的信息图，以及创建和定制简单的Web 应用，并帮读者解决常见编程问题和困惑。', 89, 'https://book.douban.com/subject/26829016/', 0, 0, 'PASSED', '');

SET FOREIGN_KEY_CHECKS = 1;
