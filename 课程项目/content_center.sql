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

 Date: 18/10/2020 19:13:51
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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-分享中间表【描述用户购买的分享】' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mid_user_share
-- ----------------------------
INSERT INTO `mid_user_share` VALUES (1, 1, 1);
INSERT INTO `mid_user_share` VALUES (2, 4, 1);
INSERT INTO `mid_user_share` VALUES (3, 3, 1);
INSERT INTO `mid_user_share` VALUES (4, 2, 2);
INSERT INTO `mid_user_share` VALUES (5, 2, 1);
INSERT INTO `mid_user_share` VALUES (6, 12, 1);
INSERT INTO `mid_user_share` VALUES (7, 8, 1);
INSERT INTO `mid_user_share` VALUES (8, 13, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, 'Java学习', 0, '2020-09-29 15:20:52');
INSERT INTO `notice` VALUES (2, 'Python学习', 0, '2020-09-29 15:21:05');
INSERT INTO `notice` VALUES (3, '时间简史', 0, '2020-10-05 09:45:49');
INSERT INTO `notice` VALUES (4, '这还是一个半成品~~！', 1, '2020-10-17 16:25:28');

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
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分享表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES (1, 3, 'JavaScript面向对象编程指南', '2020-09-29 15:24:34', '2020-09-29 15:24:36', 1, '蔡一帆', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/s26097721.jpg', '测试', 10, '虚假连接', 0, 1, 'PASS', '测试方法');
INSERT INTO `share` VALUES (2, 2, 'Python编程 : 从入门到实践', '2020-09-29 15:40:25', '2020-09-29 15:40:28', 1, '[美] 埃里克·马瑟斯', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/s28891775.jpg', '本书是一本针对所有层次的Python 读者而作的Python 入门书。', 89, 'https://book.douban.com/subject/26829016/', 0, 1, 'PASS', 'good');
INSERT INTO `share` VALUES (3, 1, '代码大全（第2版）', '2020-10-05 10:19:19', '2020-10-05 10:19:22', 1, '[美] 史蒂夫·迈克康奈尔', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/s1495029.jpg', '第2版的《代码大全》是著名IT畅销书作者史蒂夫·迈克康奈尔11年前的经典著作的全新演绎：第2版不是第一版的简单修订增补，而是完全进行了重写；增加了很多与时俱进的内容。', 128, 'https://book.douban.com/subject/1477390/', 0, 1, 'PASS', 'good-1');
INSERT INTO `share` VALUES (4, 1, '测试新增', '2020-10-07 09:35:40', '2020-10-07 09:35:40', 1, '陈宇航', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/s1495029.jpg', '测试', 10, 'https://baidu.com', 0, 1, 'PASS', '很赞');
INSERT INTO `share` VALUES (8, 1, '测试新增', '2020-10-07 09:35:40', '2020-10-07 09:35:40', 1, '陈宇航', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/s1495029.jpg', '测试', 10, 'https://baidu.com', 0, 1, 'PASS', '很赞');
INSERT INTO `share` VALUES (9, 1, '测试新增', '2020-10-07 09:35:40', '2020-10-07 09:35:40', 1, '陈宇航', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/s28891775.jpg', '测试', 10, 'https://baidu.com', 0, 1, 'PASS', '很赞');
INSERT INTO `share` VALUES (10, 1, '测试新增', '2020-10-07 09:35:40', '2020-10-07 09:35:40', 1, '陈宇航', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', '测试', 10, 'https://baidu.com', 0, 1, 'PASS', '很赞');
INSERT INTO `share` VALUES (11, 1, '测试新增', '2020-10-07 09:35:40', '2020-10-07 09:35:40', 1, '陈宇航', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', '测试', 10, 'https://baidu.com', 0, 0, 'REJECTED', '很赞');
INSERT INTO `share` VALUES (12, 1, 'Java', '2020-10-17 19:21:47', '2020-10-17 19:21:47', 0, '侯粤嘉', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', 'summary', 123, 'aa', 0, 1, 'PASS', 'nice');
INSERT INTO `share` VALUES (13, 1, 'Python', '2020-10-17 19:27:08', '2020-10-17 19:27:08', 0, '杨苏祥', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', 'bestmatch', 110, 'bbb', 0, 1, 'PASS', '不错呦');
INSERT INTO `share` VALUES (14, 1, 'MySQL', '2020-10-17 19:30:47', '2020-10-17 19:30:47', 0, '郭瑞昌', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', 'that\'s very good', 100, 'ccc', 0, 1, 'PASS', 'good');
INSERT INTO `share` VALUES (15, 1, 'Linux', '2020-10-17 19:38:02', '2020-10-17 19:38:02', 0, '杨晶', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', 'interesting', 100, 'ggg', 0, 1, 'PASS', 'sss');
INSERT INTO `share` VALUES (16, 1, 'HTML5', '2020-10-17 19:44:29', '2020-10-17 19:44:29', 0, '许源', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', 'good', 100, 'xxx', 0, 0, 'REJECTED', 'aaa');
INSERT INTO `share` VALUES (17, 1, 'Microservice', '2020-10-17 19:46:54', '2020-10-17 19:46:54', 0, '田震', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', 'I like it very much', 90, 'ttt', 0, 0, 'NOT_YET', '待审核');
INSERT INTO `share` VALUES (18, 1, 'Flutter', '2020-10-17 19:50:20', '2020-10-17 19:50:20', 0, '王锋', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', 'new technology', 120, 'www', 0, 0, 'NOT_YET', '待审核');
INSERT INTO `share` VALUES (19, 1, 'xxx', '2020-10-18 13:24:27', '2020-10-18 13:24:27', 0, 'cyh', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', 'ceshi', 10, 'aaa', 0, 0, 'NOT_YET', '待审核');
INSERT INTO `share` VALUES (20, 1, '演示', '2020-10-18 17:52:30', '2020-10-18 17:52:30', 0, '陈宇航', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', 'summary', 10, 'aaa', 0, 0, 'NOT_YET', '待审核');

SET FOREIGN_KEY_CHECKS = 1;
