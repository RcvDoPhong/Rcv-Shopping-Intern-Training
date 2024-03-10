-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for shopping_intern_db
DROP DATABASE IF EXISTS `shopping_intern_db`;
CREATE DATABASE IF NOT EXISTS `shopping_intern_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shopping_intern_db`;

-- Dumping structure for table shopping_intern_db.mst_products
DROP TABLE IF EXISTS `mst_products`;
CREATE TABLE IF NOT EXISTS `mst_products` (
  `product_id` varchar(20) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_image` varchar(255) DEFAULT 'product_placeholder.png',
  `product_price` double NOT NULL DEFAULT '0',
  `is_sales` tinyint DEFAULT '1' COMMENT '0: Dừng bán hoặc dừng sản xuất, 1: Còn hàng, 2: Hết hàng',
  `description` text,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_name` (`product_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table shopping_intern_db.mst_users
DROP TABLE IF EXISTS `mst_users`;
CREATE TABLE IF NOT EXISTS `mst_users` (
  `user_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `verify_email` varchar(100) DEFAULT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1' COMMENT '0: Không hoạt động, 1: Hoạt động',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '0: Bình thường, 1: Đã xóa',
  `group_role` varchar(50) NOT NULL,
  `last_login_at` timestamp NULL DEFAULT NULL,
  `last_login_ip` varchar(40) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for trigger shopping_intern_db.mst_products_before_insert
DROP TRIGGER IF EXISTS `mst_products_before_insert`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `mst_products_before_insert` BEFORE INSERT ON `mst_products` FOR EACH ROW BEGIN
	SET @PRODUCT_ID = (SELECT COUNT(*) FROM mst_products) + 1;
	SET NEW.product_id = CONCAT(UPPER(LEFT(NEW.product_name, 1)), LPAD(@PRODUCT_ID, 9, '0'));
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

CREATE TABLE IF NOT EXISTS `mst_customers` (
  `customer_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `tel_num` VARCHAR(14) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1' COMMENT '0: Không hoạt động, 1: Hoạt động',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
