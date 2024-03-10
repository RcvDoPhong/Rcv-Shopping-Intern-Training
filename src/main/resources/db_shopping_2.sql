-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.35 - MySQL Community Server - GPL
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
CREATE DATABASE IF NOT EXISTS `shopping_intern_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shopping_intern_db`;

-- Dumping structure for table shopping_intern_db.mst_customers
CREATE TABLE IF NOT EXISTS `mst_customers` (
  `customer_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `tel_num` varchar(14) NOT NULL,
  `address` varchar(255) NOT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1' COMMENT '0: Không hoạt động, 1: Hoạt động',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table shopping_intern_db.mst_customers: ~6 rows (approximately)
INSERT INTO `mst_customers` (`customer_id`, `customer_name`, `email`, `tel_num`, `address`, `is_active`, `created_at`, `updated_at`) VALUES
	(1, 'test', 'test@email.com', '0101010101', 'test address', 1, '2024-03-10 10:48:55', '2024-03-10 10:48:55'),
	(23, 'New customer', 'new_customer@email.com', '0303032001', 'new address', 1, '2024-03-10 11:43:08', '2024-03-10 11:43:08'),
	(24, 'test new new_customer', 'new_customer2@email.com', '20202020', 'awiofjioawefoi', 1, '2024-03-10 11:48:25', '2024-03-10 15:23:43'),
	(25, 'New customer account', 'new_customer32@email.com', '0372963918', 'awiofjioawefoi', 1, '2024-03-10 11:56:12', '2024-03-10 15:11:50'),
	(26, 'admin33321', 'admin_test223@email.com', '0988528352', 'adminwfwf123', 1, '2024-03-10 12:03:46', '2024-03-10 15:55:01'),
	(27, 'New customer 2', 'new_admin_@eaminc.com', '0372963918', 'awiofjioawefoi22213', 0, '2024-03-10 14:25:06', '2024-03-10 15:55:04'),
	(28, 'test new new_customer', 'new_customer23@email.com', '0988528352', 'awiofjioawefoi222', 1, '2024-03-10 15:55:13', '2024-03-10 15:55:13');

-- Dumping structure for table shopping_intern_db.mst_products
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

-- Dumping data for table shopping_intern_db.mst_products: ~14 rows (approximately)
INSERT INTO `mst_products` (`product_id`, `product_name`, `product_image`, `product_price`, `is_sales`, `description`, `created_at`, `updated_at`) VALUES
	('A000000009', 'awefwefwefwef', 'product_placeholder.png', 11, 1, 'awefwaefewf', '2024-03-10 06:31:46', '2024-03-10 06:43:36'),
	('S000000011', 'Sony Playstation 1', 'product_placeholder.png', 100, 0, 'Test product 1.0', '2024-03-10 07:51:56', '2024-03-10 07:51:56'),
	('T000000001', 'Test product 1', 'product_placeholder.png', 10, 1, 'test description', '2024-03-10 06:00:20', '2024-03-10 06:00:20'),
	('T000000002', 'Test product with image', 'product_placeholder.png', 10, 1, 'test 333', '2024-03-10 06:00:45', '2024-03-10 06:00:45'),
	('T000000003', 'Test product with image upload', 'product_placeholder.png', 10, 0, 'afwefwfwaefwf', '2024-03-10 06:04:50', '2024-03-10 06:04:50'),
	('T000000004', 'Test new product with new name 1', 'product_placeholder.png', 10, 1, 'iwjfioawef', '2024-03-10 06:08:08', '2024-03-10 06:08:08'),
	('T000000005', 'tét new product with new name 2.0', 'tét_new_product_with_new_name_2.0_867756_20240310-131158.jpg', 11, 1, 'iawfjiaowfieowof', '2024-03-10 06:11:58', '2024-03-10 06:17:05'),
	('T000000006', 'Test product with image333', 'product_placeholder.png', 111, 1, 'awefwaef', '2024-03-10 06:26:27', '2024-03-10 06:26:27'),
	('T000000007', 'Test product with image33333', 'product_placeholder.png', 1110, 2, 'awfwefwef', '2024-03-10 06:27:37', '2024-03-10 07:17:37'),
	('T000000008', 'terstsetst3333', 'terstsetst3333_463231_20240310-134359.jpg', 110, 0, 'awefwef', '2024-03-10 06:31:10', '2024-03-10 07:16:31'),
	('T000000010', 'Test new product image file', 'Test_new_product_image_file_381660_20240310-134203.jpg', 11, 1, 'aEFAWFWAF', '2024-03-10 06:41:57', '2024-03-10 07:42:46'),
	('T000000012', 'Test product 13', 'Test_product_13_363792_20240310-145222.png', 0, 0, 'awfawfawfe', '2024-03-10 07:52:22', '2024-03-10 15:54:37'),
	('T000000014', 'terstsetst333', 'terstsetst333_619710_20240310-225444.png', 111, 1, 'awefawef', '2024-03-10 15:54:30', '2024-03-10 15:54:44'),
	('T000000015', 'Test product 12', 'Test_product_12_947030_20240310-225110.jpg', 10, 1, 'wefwaff', '2024-03-10 15:32:56', '2024-03-10 15:51:10');

-- Dumping structure for table shopping_intern_db.mst_users
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table shopping_intern_db.mst_users: ~3 rows (approximately)
INSERT INTO `mst_users` (`user_id`, `user_name`, `email`, `password`, `remember_token`, `verify_email`, `is_active`, `is_delete`, `group_role`, `last_login_at`, `last_login_ip`, `created_at`, `updated_at`) VALUES
	(23, 'Admin6', 'admin@email.com', '$2a$10$9BcOai8DpjyjhxzjugFQ..VEwzrH..HTfNCGaAuqhjFiNpo.TySz6', NULL, NULL, 1, 0, 'Reviewer', NULL, NULL, '2024-03-10 07:52:53', '2024-03-10 07:52:53'),
	(24, 'Test new user', 'test_new_user@email.com', '$2a$10$9TBHkZSftqxOFTuLSSYOlea4.DOARSVdYSsWoNgfk8Us4PVokW2x6', NULL, NULL, 1, 1, 'Reviewer', NULL, NULL, '2024-03-10 08:49:16', '2024-03-10 15:38:15'),
	(25, 'tEt new user 1.0', 'test_user_1@email.com', '$2a$10$ReWDwgbau3sgPNxGV0cplOBXUmXcllzRPigSxUqDdIZL4.ZCRjWI6', NULL, NULL, 0, 1, 'Admin', NULL, NULL, '2024-03-10 09:08:22', '2024-03-10 09:08:22'),
	(31, 'Test new user 3', 'test_new_user2@email.com', '$2a$10$sML.PXWvyeDfKKybSeGTEelGVMThYHKMW5TGNzA2sqcqLw51RZouG', NULL, NULL, 0, 0, 'Admin', NULL, NULL, '2024-03-10 15:50:42', '2024-03-10 15:50:42'),
	(32, 'Test new user 312', 'admin33@email.com', '$2a$10$mVC7zuDty.BbRR7udhm28.0efKu7D5GZ6vfJHGJQuzma/3Gz0GBou', NULL, NULL, 1, 0, 'Reviewer', NULL, NULL, '2024-03-10 15:54:15', '2024-03-10 15:54:15');

-- Dumping structure for trigger shopping_intern_db.mst_products_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `mst_products_before_insert` BEFORE INSERT ON `mst_products` FOR EACH ROW BEGIN
	SET @PRODUCT_ID = (SELECT COUNT(*) FROM mst_products) + 1;
	SET NEW.product_id = CONCAT(UPPER(LEFT(NEW.product_name, 1)), LPAD(@PRODUCT_ID, 9, '0'));
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
