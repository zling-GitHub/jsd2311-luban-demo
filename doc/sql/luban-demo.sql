/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.33-log : Database - csmall_db
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;
/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`luban_demo_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `luban_demo_db`;

/*Table structure for table `cart_tbl` */

DROP TABLE IF EXISTS `cart_tbl`;

CREATE TABLE `cart_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `product_code` varchar(32) DEFAULT NULL COMMENT '商品编码',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `count` int(11) DEFAULT '0' COMMENT '购买数量',
  `price` int(11) DEFAULT '0' COMMENT '商品单价',
  PRIMARY KEY (`id`),
  UNIQUE KEY `commodity_code` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cart_tbl` */

/*Table structure for table `order_tbl` */

DROP TABLE IF EXISTS `order_tbl`;

CREATE TABLE `order_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `product_code` varchar(32) DEFAULT NULL COMMENT '商品编码,也可以是商品id',
  `count` int(11) DEFAULT '0' COMMENT '购买这个商品的数量',
  `total_money` int(11) DEFAULT '0' COMMENT '订单金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8;

/*Data for the table `order_tbl` */

/*Table structure for table `stock_log_tbl` */

DROP TABLE IF EXISTS `stock_log_tbl`;

CREATE TABLE `stock_log_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_code` varchar(32) DEFAULT NULL,
  `order_sn` varchar(64) DEFAULT NULL,
  `reduce_stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `stock_log_tbl` */

/*Table structure for table `stock_tbl` */

DROP TABLE IF EXISTS `stock_tbl`;

CREATE TABLE `stock_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_code` varchar(32) DEFAULT NULL COMMENT '商品编码',
  `stock` int(11) DEFAULT '0' COMMENT '商品库存',
  PRIMARY KEY (`id`),
  UNIQUE KEY `commodity_code` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `stock_tbl` */

insert  into `stock_tbl`(`id`,`product_code`,`stock`) values (1,'PC101',1000);
insert  into `stock_tbl`(`id`,`product_code`,`stock`) values (2,'PC100',1000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
