-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: bookworm
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attribute_master`
--

DROP TABLE IF EXISTS `attribute_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute_master` (
  `attribute_id` int NOT NULL AUTO_INCREMENT,
  `attribute_name` varchar(50) NOT NULL,
  PRIMARY KEY (`attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute_master`
--

LOCK TABLES `attribute_master` WRITE;
/*!40000 ALTER TABLE `attribute_master` DISABLE KEYS */;
INSERT INTO `attribute_master` VALUES (1,'Page Number'),(2,'MB'),(4,'Length'),(5,'Duration'),(6,'Duration');
/*!40000 ALTER TABLE `attribute_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attribute_master_ob`
--

DROP TABLE IF EXISTS `attribute_master_ob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute_master_ob` (
  `attribute_master_attribute_id` int NOT NULL,
  `ob_product_type_attribute_id` int NOT NULL,
  PRIMARY KEY (`attribute_master_attribute_id`,`ob_product_type_attribute_id`),
  UNIQUE KEY `UK822c8dbw083etjmyeo7fsdysy` (`ob_product_type_attribute_id`),
  CONSTRAINT `FKnidwtea2nm60pmjnvxsetkhug` FOREIGN KEY (`attribute_master_attribute_id`) REFERENCES `attribute_master` (`attribute_id`),
  CONSTRAINT `FKr64e6c1duqsyle91e4ps4be14` FOREIGN KEY (`ob_product_type_attribute_id`) REFERENCES `product_type_attribute` (`product_type_attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute_master_ob`
--

LOCK TABLES `attribute_master_ob` WRITE;
/*!40000 ALTER TABLE `attribute_master_ob` DISABLE KEYS */;
/*!40000 ALTER TABLE `attribute_master_ob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficiary_master`
--

DROP TABLE IF EXISTS `beneficiary_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `beneficiary_master` (
  `ben_id` bigint NOT NULL AUTO_INCREMENT,
  `ben_acc_no` varchar(255) DEFAULT NULL,
  `ben_acc_type` varchar(255) DEFAULT NULL,
  `ben_bank_branch` varchar(255) DEFAULT NULL,
  `ben_bank_name` varchar(255) DEFAULT NULL,
  `ben_contact_no` varchar(255) DEFAULT NULL,
  `ben_email_id` varchar(255) DEFAULT NULL,
  `ben_ifsc` varchar(255) DEFAULT NULL,
  `ben_name` varchar(255) NOT NULL,
  `ben_pan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ben_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiary_master`
--

LOCK TABLES `beneficiary_master` WRITE;
/*!40000 ALTER TABLE `beneficiary_master` DISABLE KEYS */;
INSERT INTO `beneficiary_master` VALUES (1,'2345','Savings','Andheri','ICICI','9769591905','rohitjambhekar25@gmail.com','2009','Rohit Jambhekar','10009'),(2,'2345','Savings','Parle','ICICI','9769591906','avimalik25@gmail.com','2009','Avi Malik','10009');
/*!40000 ALTER TABLE `beneficiary_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_details`
--

DROP TABLE IF EXISTS `cart_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_details` (
  `product_genre_id` int NOT NULL AUTO_INCREMENT,
  `cost` double DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`product_genre_id`),
  KEY `FK5u7nakxaradawhygg84syvu92` (`cart_id`),
  KEY `FKlfyc1r1caest795hguh2nto2m` (`product_id`),
  CONSTRAINT `FK5u7nakxaradawhygg84syvu92` FOREIGN KEY (`cart_id`) REFERENCES `cart_master` (`cart_id`),
  CONSTRAINT `FKlfyc1r1caest795hguh2nto2m` FOREIGN KEY (`product_id`) REFERENCES `product_master` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_details`
--

LOCK TABLES `cart_details` WRITE;
/*!40000 ALTER TABLE `cart_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_master`
--

DROP TABLE IF EXISTS `cart_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_master` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `cost` double NOT NULL,
  `number_of_books` int NOT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_master`
--

LOCK TABLES `cart_master` WRITE;
/*!40000 ALTER TABLE `cart_master` DISABLE KEYS */;
INSERT INTO `cart_master` VALUES (17,0,0),(18,0,0);
/*!40000 ALTER TABLE `cart_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_master`
--

DROP TABLE IF EXISTS `customer_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_master` (
  `customer_id` bigint NOT NULL AUTO_INCREMENT,
  `customer_email` varchar(50) NOT NULL,
  `customer_name` varchar(100) NOT NULL,
  `customer_password` varchar(40) NOT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `pan` varchar(255) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  `library_package_id` int DEFAULT NULL,
  `shelf_id` int DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `UK3tthjqiod5oppfa0lkognddny` (`customer_email`),
  KEY `FK2k3s16ymddveplpedlecwfoq` (`cart_id`),
  KEY `FKmi5t7oi1lb0en440j8scq7n1d` (`library_package_id`),
  KEY `FKmphhgq22uo5h5jerykgxsf7f7` (`shelf_id`),
  CONSTRAINT `FK2k3s16ymddveplpedlecwfoq` FOREIGN KEY (`cart_id`) REFERENCES `cart_master` (`cart_id`),
  CONSTRAINT `FKmi5t7oi1lb0en440j8scq7n1d` FOREIGN KEY (`library_package_id`) REFERENCES `library_package` (`library_id`),
  CONSTRAINT `FKmphhgq22uo5h5jerykgxsf7f7` FOREIGN KEY (`shelf_id`) REFERENCES `my_shelf` (`shelf_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_master`
--

LOCK TABLES `customer_master` WRITE;
/*!40000 ALTER TABLE `customer_master` DISABLE KEYS */;
INSERT INTO `customer_master` VALUES (1,'rohitjambhekar25@gmail.com','Rohit Jambhekar','Rohit2000#','2024-06-08 00:00:00.000000','2000','9769591905',17,1,1),(2,'ganesh123@gmail.com','Ganesh Wagh','1234','2000-06-01 00:00:00.000000','200001','9833190625',18,NULL,NULL);
/*!40000 ALTER TABLE `customer_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `genre_id` int NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(50) NOT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Thriller'),(2,'Romance'),(3,'Comedy'),(4,'Adventure');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `invoice_id` bigint NOT NULL AUTO_INCREMENT,
  `invoice_amount` double NOT NULL,
  `invoice_date` date NOT NULL,
  `customer_id` bigint NOT NULL,
  PRIMARY KEY (`invoice_id`),
  KEY `FKk9j7m0iwl2u5ccibh3piocfj` (`customer_id`),
  CONSTRAINT `FKk9j7m0iwl2u5ccibh3piocfj` FOREIGN KEY (`customer_id`) REFERENCES `customer_master` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (54,50,'2024-08-07',1),(55,25,'2024-08-07',1),(56,50,'2024-08-08',1),(57,50,'2024-08-08',1),(58,100,'2024-08-08',1),(59,50,'2024-08-08',1),(60,25,'2024-08-08',1);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_detail`
--

DROP TABLE IF EXISTS `invoice_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_detail` (
  `invdtl_id` bigint NOT NULL AUTO_INCREMENT,
  `base_price` double NOT NULL,
  `quantity` int DEFAULT NULL,
  `rent_no_of_days` int DEFAULT NULL,
  `tran_type` varchar(1) NOT NULL,
  `invoice_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`invdtl_id`),
  KEY `FKit1rbx4thcr6gx6bm3gxub3y4` (`invoice_id`),
  KEY `FKbg5ko4eg8ngijc0mmbshx244m` (`product_id`),
  CONSTRAINT `FKbg5ko4eg8ngijc0mmbshx244m` FOREIGN KEY (`product_id`) REFERENCES `product_master` (`product_id`),
  CONSTRAINT `FKit1rbx4thcr6gx6bm3gxub3y4` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_detail`
--

LOCK TABLES `invoice_detail` WRITE;
/*!40000 ALTER TABLE `invoice_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `type-id` int NOT NULL AUTO_INCREMENT,
  `language` varchar(50) NOT NULL,
  PRIMARY KEY (`type-id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'English'),(2,'Marathi'),(3,'Hindi'),(4,'Punjabi'),(5,'Kokani'),(6,'French');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language_ob`
--

DROP TABLE IF EXISTS `language_ob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language_ob` (
  `language_type-id` int NOT NULL,
  `ob_product_id` bigint NOT NULL,
  PRIMARY KEY (`language_type-id`,`ob_product_id`),
  UNIQUE KEY `UK7c13sl6udqqv430sthjbtgkv8` (`ob_product_id`),
  CONSTRAINT `FK7m203dri3oded3wq5xgdtecjs` FOREIGN KEY (`language_type-id`) REFERENCES `language` (`type-id`),
  CONSTRAINT `FKgk5snmtg9jy2sgypow0tna5wx` FOREIGN KEY (`ob_product_id`) REFERENCES `product_master` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language_ob`
--

LOCK TABLES `language_ob` WRITE;
/*!40000 ALTER TABLE `language_ob` DISABLE KEYS */;
/*!40000 ALTER TABLE `language_ob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library_package`
--

DROP TABLE IF EXISTS `library_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `library_package` (
  `library_id` int NOT NULL AUTO_INCREMENT,
  `cost` double NOT NULL,
  `package_expiry_days` int NOT NULL,
  `package_name` varchar(50) NOT NULL,
  `number_of_books_allowed` int NOT NULL,
  PRIMARY KEY (`library_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library_package`
--

LOCK TABLES `library_package` WRITE;
/*!40000 ALTER TABLE `library_package` DISABLE KEYS */;
INSERT INTO `library_package` VALUES (1,100,5,'Premium package',5),(2,50,2,'Basic package',1);
/*!40000 ALTER TABLE `library_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_shelf`
--

DROP TABLE IF EXISTS `my_shelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_shelf` (
  `shelf_id` int NOT NULL AUTO_INCREMENT,
  `number_of_books` int NOT NULL,
  PRIMARY KEY (`shelf_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_shelf`
--

LOCK TABLES `my_shelf` WRITE;
/*!40000 ALTER TABLE `my_shelf` DISABLE KEYS */;
INSERT INTO `my_shelf` VALUES (1,7);
/*!40000 ALTER TABLE `my_shelf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prod_beneficiary_master`
--

DROP TABLE IF EXISTS `prod_beneficiary_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prod_beneficiary_master` (
  `prod_ben_id` bigint NOT NULL AUTO_INCREMENT,
  `prod_ben_percentage` double DEFAULT NULL,
  `prod_ben_ben_id` bigint DEFAULT NULL,
  `prod_ben_product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`prod_ben_id`),
  KEY `FKgqlve71mqynncj54su1j8p0f` (`prod_ben_ben_id`),
  KEY `FKro49rqhoxlfvvm65ehip5x4vv` (`prod_ben_product_id`),
  CONSTRAINT `FKgqlve71mqynncj54su1j8p0f` FOREIGN KEY (`prod_ben_ben_id`) REFERENCES `beneficiary_master` (`ben_id`),
  CONSTRAINT `FKro49rqhoxlfvvm65ehip5x4vv` FOREIGN KEY (`prod_ben_product_id`) REFERENCES `product_master` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prod_beneficiary_master`
--

LOCK TABLES `prod_beneficiary_master` WRITE;
/*!40000 ALTER TABLE `prod_beneficiary_master` DISABLE KEYS */;
INSERT INTO `prod_beneficiary_master` VALUES (1,5,1,1),(2,15,1,2),(3,10,2,1);
/*!40000 ALTER TABLE `prod_beneficiary_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_genre`
--

DROP TABLE IF EXISTS `product_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_genre` (
  `product_genre_id` int NOT NULL AUTO_INCREMENT,
  `genre_id` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `genre id` int DEFAULT NULL,
  PRIMARY KEY (`product_genre_id`),
  KEY `FKgnrbtvcov8slndmp3fhkk1ck8` (`genre_id`),
  KEY `FKpr2vuxttb0c3sd9gge1udlxny` (`product_id`),
  CONSTRAINT `FKgnrbtvcov8slndmp3fhkk1ck8` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`),
  CONSTRAINT `FKpr2vuxttb0c3sd9gge1udlxny` FOREIGN KEY (`product_id`) REFERENCES `product_master` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_genre`
--

LOCK TABLES `product_genre` WRITE;
/*!40000 ALTER TABLE `product_genre` DISABLE KEYS */;
INSERT INTO `product_genre` VALUES (1,1,1,NULL),(2,4,1,NULL),(3,2,2,NULL),(4,1,2,NULL),(5,3,10,NULL);
/*!40000 ALTER TABLE `product_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_master`
--

DROP TABLE IF EXISTS `product_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_master` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `is_library` bit(1) NOT NULL,
  `is_rentable` bit(1) NOT NULL,
  `min_rent_days` int DEFAULT NULL,
  `product_author` varchar(255) DEFAULT NULL,
  `product_baseprice` double DEFAULT NULL,
  `product_description_long` varchar(500) DEFAULT NULL,
  `product_description_short` varchar(255) DEFAULT NULL,
  `product_english_name` varchar(255) DEFAULT NULL,
  `product_isbn` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_off_price_expirydate` date DEFAULT NULL,
  `product_offerprice` double DEFAULT NULL,
  `product_sp_cost` double DEFAULT NULL,
  `rent_per_day` double DEFAULT NULL,
  `product_lang_id` int DEFAULT NULL,
  `product_publisher_id` int DEFAULT NULL,
  `product_image` varchar(200) NOT NULL,
  `type_id` int DEFAULT NULL,
  `product_type_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FKo36vykiqmvpkwywha5ataw4qj` (`product_lang_id`),
  KEY `FKadi8xwbcxk444ylbnuq5u91yg` (`product_publisher_id`),
  KEY `FKkcts4g1k0uso9vl3j60smvv8r` (`type_id`),
  KEY `FKsgj4q91802cvm6kfjqg909b6s` (`product_type_id`),
  CONSTRAINT `FKadi8xwbcxk444ylbnuq5u91yg` FOREIGN KEY (`product_publisher_id`) REFERENCES `publisher_master` (`publisher_id`),
  CONSTRAINT `FKkcts4g1k0uso9vl3j60smvv8r` FOREIGN KEY (`type_id`) REFERENCES `product_type` (`type_id`),
  CONSTRAINT `FKo36vykiqmvpkwywha5ataw4qj` FOREIGN KEY (`product_lang_id`) REFERENCES `language` (`type-id`),
  CONSTRAINT `FKsgj4q91802cvm6kfjqg909b6s` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_master`
--

LOCK TABLES `product_master` WRITE;
/*!40000 ALTER TABLE `product_master` DISABLE KEYS */;
INSERT INTO `product_master` VALUES (1,_binary '',_binary '',2,'JK Rowling',24,'Harry potter is  very great book','gr8','Harry Potter and Goblet of fire','true','HarryPotter and Goblet of fire',NULL,NULL,23,NULL,1,2,'http://localhost:8080/files/HarryPotter.jpeg',1,NULL),(2,_binary '\0',_binary '',2,'George RR Martin',12,'A fantasy book','Book on rise and fall of targaryen dynasty','Fire and Blood','1000000002','Fire and Blood','2024-09-23',23,25,2,1,NULL,'http://localhost:8080/files/fireandblood.jpeg',1,NULL),(3,_binary '\0',_binary '',2,'Agatha Christie',12,'A crime book','Mystery book','Mystery Book','1000000002','Death on Nile','2024-09-23',23,25,2,1,NULL,'http://localhost:8080/files/agathachristie.jpg',1,NULL),(4,_binary '\0',_binary '',2,'ABC',12,'A Biography','Biography','Biogrphy on APJ Abdul Kalam','1000000002','Wings on Fire','2024-10-25',23,25,2,1,NULL,'http://localhost:8080/files/apjkalam.jpeg',1,NULL),(5,_binary '',_binary '\0',2,'George RR Martin',20,'Tales of Dunk and Egg','Tales of Aegon V Targaryen and his squire','Hedge Knight','10000009','Tales of Dunk and Egg','2025-09-10',19,23,2,1,NULL,'http://localhost:8080/files/Hedgeknight.jpg',1,NULL),(6,_binary '',_binary '\0',2,'Kalki',20,'Rise and fall of chola dynasty','Book about cholas','Ponniyin Selvan','10000009','Ponniyin Selvan part 1','2026-09-10',19,23,2,1,NULL,'http://localhost:8080/files/ps.jpg',1,NULL),(7,_binary '',_binary '',2,'Mohan dhariya',21,'The author is an ardent admirer of Chhatrapati Shivaji Maharaj and to remove the misunderstanding which are yet prevalent in the minds of many. This small booklet has been written with specific intention to remove any misunderstanding about Chhatrapati Shivaji Maharaj.','The author is an ardent admirer of Chhatrapati Shivaji Maharaj','The Great Secular King CHHATRAPATI SHIVAJI','100008','The Great Secular King CHHATRAPATI SHIVAJI','2027-07-17',21,24,4,1,NULL,'http://localhost:8080/files/TheGreatSecularKingCover.jpg',1,NULL),(9,_binary '',_binary '',2,'Ranveer Allahabadia',23,'Ranveer show indias smartes podcast','Smartest podcast of India','TRS','10009','The Ranveer Show','2024-09-07',23,12,2,2,NULL,'http://localhost:8080/files/OIP%20(1).jpeg',2,2),(10,_binary '',_binary '',3,'Rohit',21,'Comedy book','Funny book','Comedy books','1009','Comedy Book','2025-09-07',21,12,2,1,NULL,'http://localhost:8080/files/A1bvKqZsNDL._SL1500_.jpg',2,2);
/*!40000 ALTER TABLE `product_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_on_shelf`
--

DROP TABLE IF EXISTS `product_on_shelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_on_shelf` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `base_price` double NOT NULL,
  `rent_no_of_days` int DEFAULT NULL,
  `tran_type` varchar(255) NOT NULL,
  `product_id` bigint NOT NULL,
  `shelf_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4itqmc25pwogay2j9a3ll16j5` (`product_id`),
  KEY `FK5igrjnxwq3xv1sbnhmu5xbayn` (`shelf_id`),
  CONSTRAINT `FK4itqmc25pwogay2j9a3ll16j5` FOREIGN KEY (`product_id`) REFERENCES `product_master` (`product_id`),
  CONSTRAINT `FK5igrjnxwq3xv1sbnhmu5xbayn` FOREIGN KEY (`shelf_id`) REFERENCES `my_shelf` (`shelf_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_on_shelf`
--

LOCK TABLES `product_on_shelf` WRITE;
/*!40000 ALTER TABLE `product_on_shelf` DISABLE KEYS */;
INSERT INTO `product_on_shelf` VALUES (5,25,NULL,'B',2,1),(6,25,NULL,'B',3,1),(7,25,NULL,'B',2,1),(8,25,NULL,'B',3,1),(9,25,NULL,'B',2,1),(10,23,NULL,'B',5,1),(11,23,NULL,'B',6,1),(34,20,NULL,'L',6,1),(35,20,NULL,'L',5,1),(36,24,NULL,'L',1,1),(37,23,NULL,'L',9,1),(38,21,NULL,'L',10,1);
/*!40000 ALTER TABLE `product_on_shelf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` VALUES (1,'Books'),(2,'Audio'),(3,'Video'),(4,'Cassete'),(6,'Podcasts');
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type_attribute`
--

DROP TABLE IF EXISTS `product_type_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_type_attribute` (
  `product_type_attribute_id` int NOT NULL AUTO_INCREMENT,
  `attribute_id` int DEFAULT NULL,
  `product_type_id` int DEFAULT NULL,
  PRIMARY KEY (`product_type_attribute_id`),
  KEY `FKrregjc9ymj00gwvajw9vb200u` (`attribute_id`),
  KEY `FKjs31xy0qbc6mageu4sput2hem` (`product_type_id`),
  CONSTRAINT `FKjs31xy0qbc6mageu4sput2hem` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`type_id`),
  CONSTRAINT `FKrregjc9ymj00gwvajw9vb200u` FOREIGN KEY (`attribute_id`) REFERENCES `attribute_master` (`attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type_attribute`
--

LOCK TABLES `product_type_attribute` WRITE;
/*!40000 ALTER TABLE `product_type_attribute` DISABLE KEYS */;
INSERT INTO `product_type_attribute` VALUES (1,1,1),(2,2,1);
/*!40000 ALTER TABLE `product_type_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type_attribute_value`
--

DROP TABLE IF EXISTS `product_type_attribute_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_type_attribute_value` (
  `product_type_attribute_id` int NOT NULL AUTO_INCREMENT,
  `product_attributevalue` varchar(255) NOT NULL,
  `attribute_id` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`product_type_attribute_id`),
  KEY `FKi0po7paw4frr99il3wjjjyj2i` (`attribute_id`),
  KEY `FKhw3hu8b5likdkygfnbs2lln5o` (`product_id`),
  CONSTRAINT `FKhw3hu8b5likdkygfnbs2lln5o` FOREIGN KEY (`product_id`) REFERENCES `product_master` (`product_id`),
  CONSTRAINT `FKi0po7paw4frr99il3wjjjyj2i` FOREIGN KEY (`attribute_id`) REFERENCES `attribute_master` (`attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type_attribute_value`
--

LOCK TABLES `product_type_attribute_value` WRITE;
/*!40000 ALTER TABLE `product_type_attribute_value` DISABLE KEYS */;
INSERT INTO `product_type_attribute_value` VALUES (1,'25',1,1);
/*!40000 ALTER TABLE `product_type_attribute_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type_ob`
--

DROP TABLE IF EXISTS `product_type_ob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_type_ob` (
  `product_type_type_id` int NOT NULL,
  `ob_product_id` bigint NOT NULL,
  PRIMARY KEY (`product_type_type_id`,`ob_product_id`),
  UNIQUE KEY `UKrcjkpthp4wo712gfpavmkycgu` (`ob_product_id`),
  CONSTRAINT `FKh859f7c8o5jrsgqcye7g7qx7f` FOREIGN KEY (`product_type_type_id`) REFERENCES `product_type` (`type_id`),
  CONSTRAINT `FKkwrlrxabomnf42x692mic3qfr` FOREIGN KEY (`ob_product_id`) REFERENCES `product_master` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type_ob`
--

LOCK TABLES `product_type_ob` WRITE;
/*!40000 ALTER TABLE `product_type_ob` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_type_ob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type_ob1`
--

DROP TABLE IF EXISTS `product_type_ob1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_type_ob1` (
  `product_type_type_id` int NOT NULL,
  `ob1_product_type_attribute_id` int NOT NULL,
  PRIMARY KEY (`product_type_type_id`,`ob1_product_type_attribute_id`),
  UNIQUE KEY `UKo32k0m29vyy7v9y4mye62tcjh` (`ob1_product_type_attribute_id`),
  CONSTRAINT `FK5eyr2kup075y4tqlb6hreys7h` FOREIGN KEY (`product_type_type_id`) REFERENCES `product_type` (`type_id`),
  CONSTRAINT `FKr5u2xfefrc7mntm4rjqm53ad0` FOREIGN KEY (`ob1_product_type_attribute_id`) REFERENCES `product_type_attribute` (`product_type_attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type_ob1`
--

LOCK TABLES `product_type_ob1` WRITE;
/*!40000 ALTER TABLE `product_type_ob1` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_type_ob1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type_products`
--

DROP TABLE IF EXISTS `product_type_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_type_products` (
  `product_type_type_id` int NOT NULL,
  `products_product_id` bigint NOT NULL,
  PRIMARY KEY (`product_type_type_id`,`products_product_id`),
  UNIQUE KEY `UKlex1b1mlfdkf11huecxq8b49m` (`products_product_id`),
  CONSTRAINT `FK75ay5ngjgbfghoij6l789us6g` FOREIGN KEY (`product_type_type_id`) REFERENCES `product_type` (`type_id`),
  CONSTRAINT `FKqse2plawc2v756alqwnsqrhmv` FOREIGN KEY (`products_product_id`) REFERENCES `product_master` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type_products`
--

LOCK TABLES `product_type_products` WRITE;
/*!40000 ALTER TABLE `product_type_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_type_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher_master`
--

DROP TABLE IF EXISTS `publisher_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher_master` (
  `publisher_id` int NOT NULL AUTO_INCREMENT,
  `publisher_name` varchar(50) NOT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher_master`
--

LOCK TABLES `publisher_master` WRITE;
/*!40000 ALTER TABLE `publisher_master` DISABLE KEYS */;
INSERT INTO `publisher_master` VALUES (2,'HarperCollins'),(3,'RoliBooks'),(4,'Arihant');
/*!40000 ALTER TABLE `publisher_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `royalty_calculation`
--

DROP TABLE IF EXISTS `royalty_calculation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `royalty_calculation` (
  `roy_cal_id` int NOT NULL AUTO_INCREMENT,
  `base_price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `royalty_date` datetime(6) DEFAULT NULL,
  `royalty_on_base_price` double DEFAULT NULL,
  `sales_price` double DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  `ben_id` bigint DEFAULT NULL,
  `invoice_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`roy_cal_id`),
  KEY `idx_ben_id` (`ben_id`),
  KEY `idx_invoice_id` (`invoice_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `fk_royalty_calculation` (`ben_id`,`invoice_id`,`product_id`),
  CONSTRAINT `FK6ef225pnw8r22kw85xfua6hni` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`),
  CONSTRAINT `FKas49u6dxu8mu28ylsq0cucx1b` FOREIGN KEY (`product_id`) REFERENCES `product_master` (`product_id`),
  CONSTRAINT `FKrjpqmehnwed5d3l60foq44e7j` FOREIGN KEY (`ben_id`) REFERENCES `beneficiary_master` (`ben_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `royalty_calculation`
--

LOCK TABLES `royalty_calculation` WRITE;
/*!40000 ALTER TABLE `royalty_calculation` DISABLE KEYS */;
INSERT INTO `royalty_calculation` VALUES (29,25,0,'2024-08-08 11:10:23.578048',3.75,25,'P',1,58,2),(30,25,0,'2024-08-08 11:17:02.649794',3.75,25,'P',1,59,2),(31,25,0,'2024-08-08 12:46:57.812990',3.75,25,'P',1,60,2);
/*!40000 ALTER TABLE `royalty_calculation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-08 20:37:39
