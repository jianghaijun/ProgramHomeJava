/*
Navicat MySQL Data Transfer

Source Server         : Learn
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ProgramHome

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-11-02 18:40:45
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT '超级用户' COMMENT '用户名',
  `password` varchar(255) NOT NULL,
  `userHead` varchar(255) DEFAULT '' COMMENT '用户头像',
  `userPhone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码',
  `userId` varchar(255) NOT NULL DEFAULT '' COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('6', '超级用户', '24ce8333f79e48ee', '', '13352277622', '72e72bf5a0ca44d9a801dbb78a075012');
INSERT INTO `t_user` VALUES ('7', '超级用户', '4ff77a60267e29ae4f710fbddd167603', '', '123', '541dd76adfe44bd0a712b9545a85f080');
INSERT INTO `t_user` VALUES ('8', '张三', '5086161e64ad49c2', '', '13352277222', '381f3d8b4e3642b29fc5f58bc6823564');
