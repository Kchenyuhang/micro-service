/*
 Navicat Premium Data Transfer

 Source Server         : my_cloud_sql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 112.74.99.22:3306
 Source Schema         : course_center

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 24/09/2020 17:01:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_enabled` tinyint(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES (1, '微服务技术', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/7f41b776db0ef26d6bb4d92c61435bcc.jpg', '软件1851', 0, 1);
INSERT INTO `t_course` VALUES (2, '前端框架技术', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/qianduankuangjia.jpg', '软件1851', 1, 1);
INSERT INTO `t_course` VALUES (3, '工业软件后端架构技术', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/houduanruanjianjiagou.jpg', '软件1851', 0, 1);
INSERT INTO `t_course` VALUES (4, 'web开发', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/web.jpg', '软件1851', 1, 4);
INSERT INTO `t_course` VALUES (5, '面向对象高级程序设计', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/mianxiangduixianggaoji.jpg', '软件1851', 1, 4);
INSERT INTO `t_course` VALUES (6, '面向对象程序设计', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/mianxiangduixiang.jpg', '软件1851', 1, 1);
INSERT INTO `t_course` VALUES (7, '学习园地', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/xuxiyuandi.jpg', '软件1851', 1, 1);
INSERT INTO `t_course` VALUES (8, '校企合作开发', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/xiaoqihezuokaifa.png', '软件1851', 0, 1);
INSERT INTO `t_course` VALUES (9, '微服务技术', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/7f41b776db0ef26d6bb4d92c61435bcc.jpg', '软件1851', 0, 2);
INSERT INTO `t_course` VALUES (10, '前端框架技术', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/qianduankuangjia.jpg', '软件1851', 0, 2);
INSERT INTO `t_course` VALUES (11, '工业软件后端技术', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/houduanruanjianjiagou.jpg', '软件1851', 1, 2);
INSERT INTO `t_course` VALUES (12, 'web开发', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/web.jpg', '软件1851', 1, 2);
INSERT INTO `t_course` VALUES (13, '面向对象高级程序设计', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/mianxiangduixianggaoji.jpg', '软件1851', 1, 2);
INSERT INTO `t_course` VALUES (14, '面向对象程序设计', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/mianxiangduixiang.jpg', '软件1851', 1, 2);
INSERT INTO `t_course` VALUES (15, '学习园地', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/xuxiyuandi.jpg', '软件1851', 0, 2);
INSERT INTO `t_course` VALUES (16, '校企合作开发', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/xiaoqihezuokaifa.png', '软件1851', 0, 2);
INSERT INTO `t_course` VALUES (17, '微服务技术', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/7f41b776db0ef26d6bb4d92c61435bcc.jpg', '软件1851', 0, 4);
INSERT INTO `t_course` VALUES (18, '前端框架技术', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/qianduankuangjia.jpg', '软件1851', 1, 3);
INSERT INTO `t_course` VALUES (19, '工业软件后端架构', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/houduanruanjianjiagou.jpg', '软件1851', 1, 3);
INSERT INTO `t_course` VALUES (20, 'web开发', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/web.jpg', '软件1851', 1, 3);
INSERT INTO `t_course` VALUES (21, '面向对象高级程序设计', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/mianxiangduixianggaoji.jpg', '软件1851', 1, 3);
INSERT INTO `t_course` VALUES (22, '面向对象设计', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/mianxiangduixiang.jpg', '软件1851', 1, 3);
INSERT INTO `t_course` VALUES (23, '学习园地', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/xuxiyuandi.jpg', '软件1851', 1, 3);
INSERT INTO `t_course` VALUES (24, '校企合作开发', '许莫淇', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/xiaoqihezuokaifa.png', '软件1851', 0, 3);

SET FOREIGN_KEY_CHECKS = 1;
