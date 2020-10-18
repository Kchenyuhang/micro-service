/*
 Navicat Premium Data Transfer

 Source Server         : my_cloud_sql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 112.74.99.22:3306
 Source Schema         : user_center

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 18/10/2020 19:14:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus_event_log
-- ----------------------------
DROP TABLE IF EXISTS `bonus_event_log`;
CREATE TABLE `bonus_event_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT 'user.id',
  `value` int(0) NULL DEFAULT NULL COMMENT '积分操作值',
  `event` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发生的事件',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_bonus_event_log_user1_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分变更记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bonus_event_log
-- ----------------------------
INSERT INTO `bonus_event_log` VALUES (1, 3, 50, 'CONTRIBUTE', '2020-10-08 16:06:38', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (2, 2, 50, 'CONTRIBUTE', '2020-10-08 16:09:50', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (3, 1, 50, 'CONTRIBUTE', '2020-10-08 16:13:41', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (4, 1, 50, 'CONTRIBUTE', '2020-10-08 16:17:52', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (5, 3, 50, 'CONTRIBUTE', '2020-10-15 11:48:23', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (6, 1, -10, 'BUY', '2020-10-15 14:55:37', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (7, 1, -89, 'BUY', '2020-10-15 14:57:24', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (8, 1, -10, 'BUY', '2020-10-15 14:59:23', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (9, 1, -10, 'BUY', '2020-10-15 15:00:56', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (10, 1, -89, 'BUY', '2020-10-15 15:02:34', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (11, 1, -128, 'BUY', '2020-10-15 15:05:15', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (12, 1, -10, 'BUY', '2020-10-15 15:06:58', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (13, 1, -10, 'BUY', '2020-10-15 16:00:33', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (14, 1, -10, 'BUY', '2020-10-15 16:01:11', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (15, 1, -89, 'BUY', '2020-10-15 16:02:39', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (16, 1, -128, 'BUY', '2020-10-15 16:03:41', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (17, 1, -10, 'BUY', '2020-10-15 16:27:31', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (18, 1, -10, 'BUY', '2020-10-15 18:08:31', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (19, 1, -128, 'BUY', '2020-10-15 18:13:30', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (20, 1, -89, 'BUY', '2020-10-16 07:51:18', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (21, 1, -89, 'BUY', '2020-10-16 07:54:38', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (22, 2, 20, 'SIGN_IN', '2020-10-16 09:25:08', '签到');
INSERT INTO `bonus_event_log` VALUES (23, 3, 20, 'SIGN_IN', '2020-10-16 09:28:47', '签到');
INSERT INTO `bonus_event_log` VALUES (24, 5, 20, 'SIGN_IN', '2020-10-16 09:32:46', '签到');
INSERT INTO `bonus_event_log` VALUES (25, 4, 20, 'SIGN_IN', '2020-10-16 09:37:59', '签到');
INSERT INTO `bonus_event_log` VALUES (30, 1, 20, 'SIGN_IN', '2020-10-16 09:59:54', '签到');
INSERT INTO `bonus_event_log` VALUES (31, 1, 20, 'SIGN_IN', '2020-10-17 16:27:09', '签到');
INSERT INTO `bonus_event_log` VALUES (32, 1, 50, 'CONTRIBUTE', '2020-10-18 11:44:22', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (33, 1, 50, 'CONTRIBUTE', '2020-10-18 11:45:54', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (34, 1, 50, 'CONTRIBUTE', '2020-10-18 12:32:40', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (35, 1, 20, 'SIGN_IN', '2020-10-18 13:22:24', '签到');
INSERT INTO `bonus_event_log` VALUES (36, 1, -123, 'BUY', '2020-10-18 13:25:47', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (37, 1, 50, 'CONTRIBUTE', '2020-10-18 13:28:49', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (38, 1, -10, 'BUY', '2020-10-18 17:53:58', '兑换分享...');
INSERT INTO `bonus_event_log` VALUES (39, 1, -110, 'BUY', '2020-10-18 17:55:35', '兑换分享...');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `wx_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '微信id',
  `wx_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '微信昵称',
  `roles` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `bonus` int(0) NOT NULL DEFAULT 300 COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分享' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'oPBXK5eJtMF6VHC_4jea-klA83zU', '大橙子boy', 'admin', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM4R0LicsZvht2o6HZRZa0Z3NZVxHbibeObmazMhJ2Cmt9qUBUJbJCfCZW8QWtp6UnrJgMPJ0ckajC2w/132', '2020-10-13 20:24:35', '2020-10-13 20:24:35', 311);
INSERT INTO `user` VALUES (2, 'xiaoming', '小明', 'user', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/avatar/P1.jpg', '2020-09-29 15:23:05', '2020-09-29 15:23:07', 550);
INSERT INTO `user` VALUES (3, 'zhangsan', '张三', 'user', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', '2020-09-29 15:24:00', '2020-09-29 15:24:03', 600);
INSERT INTO `user` VALUES (4, 'sfdgdh', '大橙子', 'user', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/avatar/P3.jpg', '2020-09-24 11:05:55', '2020-09-24 11:05:57', 600);
INSERT INTO `user` VALUES (5, 'dsgrhtj', '小红', 'user', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/avatar/P3.jpg', '2020-10-16 09:41:18', '2020-10-16 09:41:20', 300);

SET FOREIGN_KEY_CHECKS = 1;
