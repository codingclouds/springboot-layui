SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `log`;
-- ----------------------------
-- Table structure for log
-- ----------------------------
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `role_name` varchar(10) DEFAULT NULL COMMENT '角色',
  `ip` varchar(20) DEFAULT NULL COMMENT '用户IP',
  `busi` varchar(50) DEFAULT NULL COMMENT '业务模块',
  `oper_type` int(2) DEFAULT NULL COMMENT '操作类型：1-增；2-查；删-3；改-4；',
  `method` varchar(50) DEFAULT NULL COMMENT '调用方法',
  `req_data` text CHARACTER SET utf8mb4  COMMENT '请求参数',
  `resp_data` text CHARACTER SET utf8mb4 COMMENT '返回参数',
  `start_time` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '结束时间',
  `status_cd` int(1) DEFAULT NULL COMMENT '状态：0-无效；1-有效；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_admin_user
-- ----------------------------