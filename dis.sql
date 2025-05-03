/*
SQLyog Ultimate v8.55 
MySQL - 5.5.28 : Database - nhc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nhc` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `nhc`;

/*Table structure for table `activities` */

DROP TABLE IF EXISTS `activities`;

CREATE TABLE `activities` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `aname` varchar(50) NOT NULL,
  `atype` varchar(50) NOT NULL,
  `price` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `activities` */

insert  into `activities`(`id`,`aname`,`atype`,`price`) values (1,'Activity1','Type1',10),(2,'activity2','Type2',20),(3,'Activity3','Type1',35),(4,'Activity4','Type2',100);

/*Table structure for table `activity_order` */

DROP TABLE IF EXISTS `activity_order`;

CREATE TABLE `activity_order` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `activity` int(6) NOT NULL,
  `member` int(6) NOT NULL,
  `staff` int(6) NOT NULL,
  `book_time` datetime NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `quantity` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_aorder_activity` (`activity`),
  KEY `FK_aorder_member` (`member`),
  KEY `FK_aorder_staff` (`staff`),
  CONSTRAINT `FK_aorder_activity` FOREIGN KEY (`activity`) REFERENCES `activities` (`id`),
  CONSTRAINT `FK_aorder_member` FOREIGN KEY (`member`) REFERENCES `members` (`id`),
  CONSTRAINT `FK_aorder_staff` FOREIGN KEY (`staff`) REFERENCES `staffs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `activity_order` */

insert  into `activity_order`(`id`,`activity`,`member`,`staff`,`book_time`,`start_time`,`end_time`,`quantity`) values (2,2,2,2,'2013-12-12 01:01:01','2013-12-12 01:01:01','2013-12-12 01:01:01',13),(4,3,3,1,'2013-12-12 01:01:01','2013-12-12 01:01:01','2013-12-12 01:01:01',15),(5,4,1,2,'2013-12-12 01:01:01','2013-12-12 01:01:01','2013-12-12 01:01:01',16),(6,2,4,3,'2013-12-12 01:01:01','2013-12-12 01:01:01','2013-12-12 01:01:01',17),(7,3,1,1,'2013-12-12 01:01:01','2013-12-12 01:01:01','2013-12-12 01:01:01',18),(8,4,2,4,'2013-12-12 01:01:01','2013-12-12 01:01:01','2013-12-12 01:01:01',19),(9,3,1,3,'2013-12-12 01:01:01','2013-12-12 01:01:01','2013-12-12 01:01:01',21),(10,3,1,2,'2013-12-12 01:01:01','2013-12-12 01:01:01','2013-12-12 01:01:01',20),(11,3,3,1,'2013-04-12 01:01:01','2013-04-12 01:01:01','2013-12-12 01:01:01',15),(12,4,1,2,'2013-03-12 01:01:01','2013-03-12 01:01:01','2013-12-12 01:01:01',16),(13,2,4,3,'2013-06-12 01:01:01','2013-06-12 01:01:01','2013-12-12 01:01:01',17),(14,3,1,1,'2013-05-12 01:01:01','2013-05-12 01:01:01','2013-12-12 01:01:01',18),(15,4,2,4,'2013-04-12 01:01:01','2013-04-12 01:01:01','2013-12-12 01:01:01',19),(16,3,1,3,'2013-06-12 01:01:01','2013-06-12 01:01:01','2013-12-12 01:01:01',21),(17,3,1,2,'2013-05-12 01:01:01','2013-05-12 01:01:01','2013-12-12 01:01:01',20);

/*Table structure for table `centers` */

DROP TABLE IF EXISTS `centers`;

CREATE TABLE `centers` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `ip` varchar(15) NOT NULL,
  `dn` varchar(15) NOT NULL,
  `du` varchar(15) NOT NULL,
  `dp` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `center_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `centers` */

insert  into `centers`(`id`,`code`,`name`,`address`,`phone`,`ip`,`dn`,`du`,`dp`) values (1,'UKGR001','GR1','GR1','01234567789','192.168.56.1','nhc','root','tai123'),(2,'UKGR002','GR2','GR1','01234567789','192.168.56.101','nhc','root','tai123');

/*Table structure for table `food_beverage` */

DROP TABLE IF EXISTS `food_beverage`;

CREATE TABLE `food_beverage` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` varchar(100) NOT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `food_beverage` */

insert  into `food_beverage`(`id`,`name`,`type`,`price`) values (1,'Cafe','Drink',10),(2,'Coca cola','Drink',12),(3,'Bread','Food',13),(4,'Soup','Food',14);

/*Table structure for table `food_beverage_order` */

DROP TABLE IF EXISTS `food_beverage_order`;

CREATE TABLE `food_beverage_order` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `foot_beverage` int(6) NOT NULL,
  `member` int(6) NOT NULL,
  `staff` int(6) NOT NULL,
  `sell_time` datetime NOT NULL,
  `quantity` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_forder_food_beverage` (`foot_beverage`),
  KEY `FK_forder_member` (`member`),
  KEY `FK_forder_staff` (`staff`),
  CONSTRAINT `FK_forder_food_beverage` FOREIGN KEY (`foot_beverage`) REFERENCES `food_beverage` (`id`),
  CONSTRAINT `FK_forder_member` FOREIGN KEY (`member`) REFERENCES `members` (`id`),
  CONSTRAINT `FK_forder_staff` FOREIGN KEY (`staff`) REFERENCES `staffs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `food_beverage_order` */

insert  into `food_beverage_order`(`id`,`foot_beverage`,`member`,`staff`,`sell_time`,`quantity`) values (2,1,1,1,'2013-04-02 00:00:00',10),(3,2,2,3,'2013-04-04 00:00:00',10),(4,1,1,1,'2013-04-04 00:00:00',10),(5,3,1,2,'2013-04-04 00:00:00',10),(6,1,3,1,'2013-04-05 00:00:00',10),(7,2,1,3,'2013-04-05 00:00:00',10),(8,1,3,1,'2013-04-06 00:00:00',10),(9,2,1,2,'2013-04-06 00:00:00',10);

/*Table structure for table `info` */

DROP TABLE IF EXISTS `info`;

CREATE TABLE `info` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `key` varchar(10) NOT NULL,
  `value` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `info` */

insert  into `info`(`id`,`key`,`value`) values (1,'center','UKGR001'),(2,'name','North Greenwich'),(3,'address','Greenwich');

/*Table structure for table `members` */

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL DEFAULT 'UKGRM56789',
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_member_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `members` */

insert  into `members`(`id`,`code`,`fname`,`lname`,`address`,`phone`,`email`) values (1,'UKGR001001','Bill2','Nguyen','Can Tho','0933352232','tantantai1@gmail.com'),(2,'UKGR001002','Bill2','Nguyen','Can Tho','0933352232','tantantai2@gmail.com'),(3,'UKGR001003','Bill2','Nguyen','Can Tho','0933352232','tantantai3@gmail.com'),(4,'UKGR001004','Bill2','Nguyen','Can Tho','0933352232','tantantai4@gmail.com'),(5,'UKGR001005','Joe','Harley','1234, New street, London, UK','0123-4567','joeharley@gmail.com');

/*Table structure for table `staffs` */

DROP TABLE IF EXISTS `staffs`;

CREATE TABLE `staffs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `position` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_staff_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `staffs` */

insert  into `staffs`(`id`,`code`,`fname`,`lname`,`password`,`address`,`phone`,`email`,`position`) values (1,'UKGRS00001','Tai 1','Nguyen','123456','Can Tho','0933352232','tantantai1@gmail.com',3),(2,'UKGRS00002','Tai 2','Nguyen','123456','Can Tho','0933352232','tantantai2@gmail.com',1),(3,'UKGRM00003','Tai 3','Nguyen','123456','Can Tho','0933352232','tantantai3@gmail.com',2),(4,'UKGRH00004','Tai 4','Nguyen','123456','Can Tho','0933352232','tantantai4@gmail.com',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
