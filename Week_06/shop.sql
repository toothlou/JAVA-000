/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50731
Source Host           : 127.0.0.1:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-11-25 23:40:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `bid` varchar(50) NOT NULL COMMENT '业务主键',
  `goods_name` varchar(100) NOT NULL COMMENT '商品名称',
  `goods_desc` varchar(1000) NOT NULL COMMENT '商品描述',
  `goods_price` double NOT NULL COMMENT '商品单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `bid` varchar(50) NOT NULL COMMENT '业务主键',
  `user_bid` varchar(50) NOT NULL COMMENT '用户bid',
  `order_title` varchar(100) NOT NULL COMMENT '订单标题',
  `order_desc` varchar(1000) NOT NULL COMMENT '订单描述',
  `createtime` datetime NOT NULL COMMENT '订单明细',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `bid` varchar(50) NOT NULL COMMENT '业务主键',
  `order_bid` varchar(50) NOT NULL COMMENT '订单bid',
  `goods_bid` varchar(50) NOT NULL COMMENT '商品bid',
  `goods_count` int(11) NOT NULL COMMENT '商品数量',
  `goods_amount_orgin` double NOT NULL COMMENT '商品原始金额',
  `goods_amount_pay` double NOT NULL COMMENT '商品实付金额',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单明细表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `bid` varchar(50) NOT NULL COMMENT '业务主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `salt` varchar(50) NOT NULL COMMENT '用于加密的盐',
  `password` varchar(50) NOT NULL COMMENT '加密后的密码',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
