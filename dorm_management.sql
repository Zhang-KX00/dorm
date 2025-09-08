/*
 Navicat Premium Dump SQL

 Source Server         : msyql
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : dorm_management

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 11/04/2025 19:45:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `full_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (7, 'xsy', '$2a$10$NEwXUMIutoVDPMsKdgp.1ejPh7OKjLq6pUXRbYcNdsuXgrQskZ/SG', '超级管理员', 'superadmin@example.com', NULL, 'SUPER_ADMIN', NULL, '2025-04-04 17:48:36', '2025-04-05 10:44:59');
INSERT INTO `admin` VALUES (9, 'zxk', '$2a$10$KqwnAafVxU/9jfCORK8Ib.ljbOuiY7VdUkgyjDy/f3P8AS7lHq3LW', '肖善祎', '123456@qq.com', NULL, 'ADMIN', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for dorm
-- ----------------------------
DROP TABLE IF EXISTS `dorm`;
CREATE TABLE `dorm`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `building_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `room_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `total_beds` int NOT NULL,
  `available_beds` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_building`(`building_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dorm
-- ----------------------------
INSERT INTO `dorm` VALUES (1, 'A栋', '101', 4, 0, '2025-04-04 15:58:21');
INSERT INTO `dorm` VALUES (2, 'A栋', '102', 4, 4, '2025-04-04 15:58:21');
INSERT INTO `dorm` VALUES (3, 'B栋', '201', 2, 1, '2025-04-04 15:58:21');
INSERT INTO `dorm` VALUES (4, 'B栋', '202', 2, 0, '2025-04-04 15:58:21');
INSERT INTO `dorm` VALUES (5, 'C栋', '301', 6, 3, '2025-04-04 15:58:21');
INSERT INTO `dorm` VALUES (15, 'O栋', '1203', 0, 2, '2025-04-10 12:07:43');
INSERT INTO `dorm` VALUES (16, 'O栋', '1203', 2, 0, '2025-04-10 12:09:37');
INSERT INTO `dorm` VALUES (17, 'O栋', '1203', 4, 1, '2025-04-10 12:14:27');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` bigint NULL DEFAULT NULL,
  `dorm_id` int NULL DEFAULT NULL,
  `type` enum('水电','家具','网络','其他') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `status` enum('待处理','维修中','已完成') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '待处理',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `finish_time` datetime NULL DEFAULT NULL,
  `repair` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKoaenejk7mw3b8ydcu8exhycgg`(`dorm_id` ASC) USING BTREE,
  INDEX `FK82t490hpytnp9cfdwfes9u4ho`(`student_id` ASC) USING BTREE,
  CONSTRAINT `FK82t490hpytnp9cfdwfes9u4ho` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKoaenejk7mw3b8ydcu8exhycgg` FOREIGN KEY (`dorm_id`) REFERENCES `dorm` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dorm_id` int NULL DEFAULT NULL,
  `check_in_date` date NULL DEFAULT NULL,
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `student_id`(`student_id` ASC) USING BTREE,
  INDEX `dorm_id`(`dorm_id` ASC) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`dorm_id`) REFERENCES `dorm` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (2, '李四', '20230002', '男', 17, NULL, '软件工程', '软件三班');
INSERT INTO `student` VALUES (3, '王五', '20230003', '女', 3, '2023-09-02', '软件工程', '软件三班');
INSERT INTO `student` VALUES (4, '赵六', '20230004', '男', NULL, NULL, '软件工程', '软件三班');
INSERT INTO `student` VALUES (5, '钱七', '20230005', '男', 1, NULL, '软件工程', '软件三班');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '通知内容',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `status` int NULL DEFAULT 0 COMMENT '状态：0-草稿 1-已发布 2-已撤回',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '系统维护通知', '本周六凌晨2:00-4:00进行系统维护，届时系统将无法访问', '2025-04-11 08:32:36', '2025-04-11 08:32:47', 'admin', 1);
INSERT INTO `sys_notice` VALUES (2, '宿舍卫生检查', '下周一上午9点进行全校宿舍卫生大检查，请做好准备', '2025-04-11 08:32:36', '2025-04-11 08:32:47', 'admin', 1);
INSERT INTO `sys_notice` VALUES (3, '暑期住宿申请', '暑期留校住宿申请现已开放，截止日期6月30日', '2025-04-11 08:32:36', '2025-04-11 08:32:47', 'admin', 1);
INSERT INTO `sys_notice` VALUES (4, '消防安全培训', '本周五下午3点在3号楼报告厅举行消防安全培训', '2025-04-10 08:32:36', '2025-04-11 08:32:47', 'admin', 1);
INSERT INTO `sys_notice` VALUES (5, '宿舍电费充值', '宿舍电费充值系统已升级，新增微信支付功能', '2025-04-09 08:32:36', '2025-04-11 08:32:47', 'admin', 1);

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` bigint NULL DEFAULT NULL,
  `visitor_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `visit_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `visit_time` datetime NULL DEFAULT NULL,
  `leave_time` datetime NULL DEFAULT NULL,
  `admin_id` int NULL DEFAULT NULL,
  `status` enum('待审批','已审批') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKr7xdhlxnwqq66kq70gbo2127y`(`admin_id` ASC) USING BTREE,
  INDEX `fk_visitor_student`(`student_id` ASC) USING BTREE,
  CONSTRAINT `FK1yjsybg516vyxav6aq7jo3fkr` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_visitor_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKr7xdhlxnwqq66kq70gbo2127y` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of visitor
-- ----------------------------
INSERT INTO `visitor` VALUES (6, 2, '张三', NULL, NULL, NULL, '2025-04-10 13:49:00', NULL, NULL, '待审批', NULL, '11111111111', '瞅瞅他');

-- ----------------------------
-- Table structure for visitor_permission_result
-- ----------------------------
DROP TABLE IF EXISTS `visitor_permission_result`;
CREATE TABLE `visitor_permission_result`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` bigint NULL DEFAULT NULL,
  `student_name` bigint NULL DEFAULT NULL,
  `admin_id` int NULL DEFAULT NULL,
  `allowed` tinyint(1) NOT NULL,
  `decision_time` datetime NOT NULL,
  `reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` enum('待审批','已审批') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK8d6mfafca0tkkciw1tt3yp8dp`(`admin_id` ASC) USING BTREE,
  INDEX `FKjy21qdb3yho0t07mpqxwt3ptt`(`student_id` ASC) USING BTREE,
  INDEX `FKek6kfuylmo5wb2hio6urepf55`(`student_name` ASC) USING BTREE,
  CONSTRAINT `FK8d6mfafca0tkkciw1tt3yp8dp` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKek6kfuylmo5wb2hio6urepf55` FOREIGN KEY (`student_name`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKjy21qdb3yho0t07mpqxwt3ptt` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of visitor_permission_result
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
