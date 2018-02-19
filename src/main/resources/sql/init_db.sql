/*
 Navicat Premium Backup

 Source Server         : 120.24.91.23
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 120.24.91.23
 Source Database       : snacks

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 02/19/2018 21:27:18 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收货地址自增id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `name` varchar(20) NOT NULL COMMENT '收货人姓名',
  `phone` varchar(11) NOT NULL COMMENT '收货人手机号',
  `city_code` int(11) NOT NULL COMMENT '城市编号',
  `details` varchar(100) NOT NULL COMMENT '详细地址',
  `default` int(1) NOT NULL DEFAULT '0' COMMENT '是默认:1 不是默认:0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `attribute`
-- ----------------------------
DROP TABLE IF EXISTS `attribute`;
CREATE TABLE `attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性自增id',
  `name` varchar(20) NOT NULL COMMENT '属性名称',
  `category_id` int(11) NOT NULL COMMENT '分类表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品sku对应的属性';

-- ----------------------------
--  Table structure for `attribute_option`
-- ----------------------------
DROP TABLE IF EXISTS `attribute_option`;
CREATE TABLE `attribute_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '可选项自增id',
  `name` varchar(20) NOT NULL COMMENT '属性可选项名称',
  `attribute_id` int(11) NOT NULL COMMENT '属性id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='属性的可选项';

-- ----------------------------
--  Table structure for `cart`
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车自增id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `sku_id` bigint(20) NOT NULL COMMENT '商品sku',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '正常状态:1 商品失效(下架):2 清空购物车(不需要了):3 已经支付了(已经付款):0 现在处理直接删除(优化可以考虑做)',
  `count` int(11) NOT NULL DEFAULT '1' COMMENT '相同sku数量',
  `price` decimal(10,2) NOT NULL COMMENT '相同sku单价',
  `sum` decimal(10,2) NOT NULL COMMENT '相同sku总价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id自增',
  `name` varchar(20) NOT NULL COMMENT '分类名称',
  `parent_id` int(11) NOT NULL COMMENT '父类id',
  `img_url` varchar(20) NOT NULL COMMENT '类别图片信息（只有一级父类有）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表（有子父类别）';

-- ----------------------------
--  Table structure for `collection`
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `sku_id` bigint(20) NOT NULL COMMENT '商品sku',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '正常状态:1 商品失效(下架):2 清空购物车(不需要了):4 已经支付了(已经付款):8 现在处理直接删除(优化可以考虑做)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='我的收藏信息表';

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NOT NULL COMMENT '评论用户id关联用户表',
  `goods_sku_id` bigint(20) NOT NULL COMMENT '商品的id用商品sku id',
  `content` varchar(255) NOT NULL DEFAULT '未评论，系统默认好评' COMMENT '评论内容',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '好评:1 中评:2 差评:3 默认好评',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品评论表';

-- ----------------------------
--  Table structure for `goods_details`
-- ----------------------------
DROP TABLE IF EXISTS `goods_details`;
CREATE TABLE `goods_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品详情表自增id',
  `spu_id` bigint(20) NOT NULL COMMENT '商品spu_id',
  `img_url` varchar(20) NOT NULL COMMENT '图片url',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '商品图片排序字段（看具体完成情况实现）根据数值大小排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `goods_option`
-- ----------------------------
DROP TABLE IF EXISTS `goods_option`;
CREATE TABLE `goods_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品属性自增id',
  `sku_id` bigint(20) NOT NULL COMMENT '商品skuid',
  `attribute_id` int(11) NOT NULL COMMENT '商品的属性',
  `option_id` int(11) NOT NULL COMMENT '商品属性可选项',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品的属性选项表';

-- ----------------------------
--  Table structure for `goods_sku`
-- ----------------------------
DROP TABLE IF EXISTS `goods_sku`;
CREATE TABLE `goods_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'sku id自增',
  `name` varchar(50) NOT NULL COMMENT 'sku名称',
  `spu_id` bigint(20) NOT NULL COMMENT '商品spu id',
  `price` decimal(10,0) NOT NULL COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品sku表';

-- ----------------------------
--  Table structure for `goods_spu`
-- ----------------------------
DROP TABLE IF EXISTS `goods_spu`;
CREATE TABLE `goods_spu` (
  `id` bigint(20) NOT NULL COMMENT '商铺spu自增id',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名称',
  `category_id` int(11) NOT NULL COMMENT '分类id',
  `details` varchar(255) NOT NULL DEFAULT '' COMMENT '商品详情文字描述',
  `price_bottom` decimal(10,2) NOT NULL COMMENT '价格区间最低（冗余字段）',
  `price_top` decimal(10,2) NOT NULL COMMENT '价格区间最高（冗余字段）',
  `comment_count` int(11) NOT NULL COMMENT '商品评论数量（冗余字段）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商铺spu表';

-- ----------------------------
--  Table structure for `index_carousel`
-- ----------------------------
DROP TABLE IF EXISTS `index_carousel`;
CREATE TABLE `index_carousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页图片id自增',
  `img_url` varchar(50) NOT NULL COMMENT '图片url',
  `spu_id` bigint(20) NOT NULL COMMENT '跳转的商品spu_id',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '首页轮播图的排序字段 默认:1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='首页大轮播图';

-- ----------------------------
--  Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL COMMENT '用户订单信息自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_id` varchar(20) NOT NULL COMMENT '订单id 被表suborder关联',
  `sum` decimal(10,2) NOT NULL COMMENT '该订单总价',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '订单状态 1:待付款 2:待发货 4:待收货 8:待评价 16:订单关闭',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `modified_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单修改时间（一般指修改状态时间）',
  `deleted_at` datetime NOT NULL COMMENT '订单过期时间（一般定义在收货后七天）',
  `delivery_free` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '快递费用 默认0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
--  Table structure for `suborder`
-- ----------------------------
DROP TABLE IF EXISTS `suborder`;
CREATE TABLE `suborder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '子订单自增主键',
  `order_id` varchar(20) NOT NULL COMMENT 'order表的order_id',
  `sku_id` bigint(20) NOT NULL COMMENT '商品sku_id',
  `count` int(11) NOT NULL COMMENT '相同sku数量',
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `sum` decimal(10,2) NOT NULL COMMENT '相同sku的总价（冗余）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表suborder';

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` bigint(20) NOT NULL COMMENT '帐号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `alias_name` varchar(20) NOT NULL COMMENT '昵称',
  `email` varchar(20) NOT NULL COMMENT '邮箱',
  `phone` char(11) NOT NULL COMMENT '电话',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '用户状态，默认1有效 0已经删除',
  `last_login_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登陆时间',
  `avatar_url` varchar(50) NOT NULL COMMENT '头像url',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `idx_id` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表\ndeleted_at = created_at未删除\n';

SET FOREIGN_KEY_CHECKS = 1;
