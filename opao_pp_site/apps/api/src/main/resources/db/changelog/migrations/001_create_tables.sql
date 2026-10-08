--liquibase formatted sql

--changeset opao-team:forward-engineered-workbench-v1
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE DATABASE  IF NOT EXISTS `opoppr` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `opoppr`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: opoppr
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `business_type`
--

DROP TABLE IF EXISTS `business_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_type` (
  `business_type_id` int NOT NULL AUTO_INCREMENT,
  `business_code` int NOT NULL,
  `business_description` varchar(30) NOT NULL,
  PRIMARY KEY (`business_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_type`
--

LOCK TABLES `business_type` WRITE;
/*!40000 ALTER TABLE `business_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `business_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('create-business-type-table-v1','opao-team','db/changelog/migrations/001_create_business_type_table.sql','2026-10-08 10:47:22',1,'EXECUTED','9:21c48552d1844d587206be6e9b42ba0d','sql','',NULL,'5.0.3',NULL,NULL,'1474440289'),('seed-business-types-from-csv-v1','opao-team','db/changelog/migrations/002_seed_business_types.sql','2026-10-08 10:47:22',2,'EXECUTED','9:807c960829dc078a49d40ee4c53e8cca','sql','',NULL,'5.0.3',NULL,NULL,'1474440289');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int NOT NULL,
  `LOCKED` tinyint NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,0,NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form`
--

DROP TABLE IF EXISTS `form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form` (
  `form_id` int NOT NULL AUTO_INCREMENT,
  `bill_number` varchar(30) NOT NULL,
  `filing_year` int NOT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `pin` varchar(6) NOT NULL,
  `title` varchar(80) NOT NULL,
  `form_type_id` int NOT NULL,
  `status_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`form_id`),
  KEY `FKqn3i3e79yup1aylf8egjivyqe` (`form_type_id`),
  KEY `FKf4kbbo6q0f52jvv8n8lu84eih` (`status_id`),
  KEY `FKsniuo4i0n35d0lw0pjlc2iqwe` (`user_id`),
  CONSTRAINT `FKf4kbbo6q0f52jvv8n8lu84eih` FOREIGN KEY (`status_id`) REFERENCES `form_status` (`status_id`),
  CONSTRAINT `FKqn3i3e79yup1aylf8egjivyqe` FOREIGN KEY (`form_type_id`) REFERENCES `form_type` (`form_type_id`),
  CONSTRAINT `FKsniuo4i0n35d0lw0pjlc2iqwe` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form`
--

LOCK TABLES `form` WRITE;
/*!40000 ALTER TABLE `form` DISABLE KEYS */;
/*!40000 ALTER TABLE `form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_status`
--

DROP TABLE IF EXISTS `form_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_status` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_status`
--

LOCK TABLES `form_status` WRITE;
/*!40000 ALTER TABLE `form_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `form_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_type`
--

DROP TABLE IF EXISTS `form_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_type` (
  `form_type_id` int NOT NULL AUTO_INCREMENT,
  `form_name` varchar(45) NOT NULL,
  PRIMARY KEY (`form_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_type`
--

LOCK TABLES `form_type` WRITE;
/*!40000 ALTER TABLE `form_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `form_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noa_pp_lat5`
--

DROP TABLE IF EXISTS `noa_pp_lat5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noa_pp_lat5` (
  `noa_pp_lat_5_id` int NOT NULL AUTO_INCREMENT,
  `addr1` varchar(80) DEFAULT NULL,
  `addr2` varchar(80) DEFAULT NULL,
  `altid` varchar(30) DEFAULT NULL,
  `cityname` varchar(40) DEFAULT NULL,
  `contact_email` varchar(75) DEFAULT NULL,
  `contact_fax` varchar(10) DEFAULT NULL,
  `contact_name` varchar(40) DEFAULT NULL,
  `contact_phone` varchar(10) DEFAULT NULL,
  `contact_send_emails` bit(1) DEFAULT NULL,
  `jur` varchar(6) NOT NULL,
  `ownername` varchar(40) DEFAULT NULL,
  `parid` varchar(30) NOT NULL,
  `pin` varchar(6) DEFAULT NULL,
  `property_address` varchar(50) DEFAULT NULL,
  `statecode` varchar(2) DEFAULT NULL,
  `tax_preparer_email` varchar(75) DEFAULT NULL,
  `tax_preparer_name` varchar(50) DEFAULT NULL,
  `tax_preparer_phone` varchar(10) DEFAULT NULL,
  `tax_preparer_prepared_date` datetime(6) DEFAULT NULL,
  `taxpayer_name` varchar(50) DEFAULT NULL,
  `taxpayer_prepared_date` datetime(6) DEFAULT NULL,
  `taxyr` int NOT NULL,
  `zip1` varchar(5) DEFAULT NULL,
  `business_type_id` int DEFAULT NULL,
  `form_id` int NOT NULL,
  PRIMARY KEY (`noa_pp_lat_5_id`),
  KEY `FK3qvq4i0e5b5ghgtrdtumnwoj4` (`business_type_id`),
  KEY `FK1srjplh0ahd2skcbfac5mrsmq` (`form_id`),
  CONSTRAINT `FK1srjplh0ahd2skcbfac5mrsmq` FOREIGN KEY (`form_id`) REFERENCES `form` (`form_id`),
  CONSTRAINT `FK3qvq4i0e5b5ghgtrdtumnwoj4` FOREIGN KEY (`business_type_id`) REFERENCES `business_type` (`business_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noa_pp_lat5`
--

LOCK TABLES `noa_pp_lat5` WRITE;
/*!40000 ALTER TABLE `noa_pp_lat5` DISABLE KEYS */;
/*!40000 ALTER TABLE `noa_pp_lat5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noa_pp_lat5_filing`
--

DROP TABLE IF EXISTS `noa_pp_lat5_filing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noa_pp_lat5_filing` (
  `noa_pp_lat_5_filing_id` int NOT NULL AUTO_INCREMENT,
  `acquisition_cost` bigint DEFAULT NULL,
  `category` varchar(10) NOT NULL,
  `consigner_mailing_addr` varchar(50) DEFAULT NULL,
  `consigner_owner_name` varchar(50) DEFAULT NULL,
  `consigner_rental_amt` bigint DEFAULT NULL,
  `consigner_tel_no` varchar(10) DEFAULT NULL,
  `effective_life` int DEFAULT NULL,
  `fileyr` int NOT NULL,
  `item_description` varchar(50) DEFAULT NULL,
  `jur` varchar(6) NOT NULL,
  `nounits` int DEFAULT NULL,
  `parid` varchar(30) NOT NULL,
  `pptype` varchar(10) NOT NULL,
  `taxyr` int NOT NULL,
  `yracqd` int DEFAULT NULL,
  `noa_pp_lat_5_id` int NOT NULL,
  `property_asset_id` int DEFAULT NULL,
  PRIMARY KEY (`noa_pp_lat_5_filing_id`),
  KEY `FKs6lx73h6g8vy0gp6tncm35ob0` (`noa_pp_lat_5_id`),
  KEY `FK63821w4wbj2ot63n3srom8qks` (`property_asset_id`),
  CONSTRAINT `FK63821w4wbj2ot63n3srom8qks` FOREIGN KEY (`property_asset_id`) REFERENCES `property_asset` (`property_asset_id`),
  CONSTRAINT `FKs6lx73h6g8vy0gp6tncm35ob0` FOREIGN KEY (`noa_pp_lat_5_id`) REFERENCES `noa_pp_lat5` (`noa_pp_lat_5_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noa_pp_lat5_filing`
--

LOCK TABLES `noa_pp_lat5_filing` WRITE;
/*!40000 ALTER TABLE `noa_pp_lat5_filing` DISABLE KEYS */;
/*!40000 ALTER TABLE `noa_pp_lat5_filing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noa_pp_lat5_inventories`
--

DROP TABLE IF EXISTS `noa_pp_lat5_inventories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noa_pp_lat5_inventories` (
  `noa_pp_lat_5_inventories_id` int NOT NULL AUTO_INCREMENT,
  `fileyr` int NOT NULL,
  `inventory_amt` bigint DEFAULT NULL,
  `inventory_month` int DEFAULT NULL,
  `inventory_type` varchar(2) DEFAULT NULL,
  `jur` varchar(6) NOT NULL,
  `parid` varchar(30) NOT NULL,
  `taxyr` int NOT NULL,
  `noa_pp_lat_5_id` int NOT NULL,
  PRIMARY KEY (`noa_pp_lat_5_inventories_id`),
  KEY `FKc4f62cctib51ulwyd838m6r21` (`noa_pp_lat_5_id`),
  CONSTRAINT `FKc4f62cctib51ulwyd838m6r21` FOREIGN KEY (`noa_pp_lat_5_id`) REFERENCES `noa_pp_lat5` (`noa_pp_lat_5_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noa_pp_lat5_inventories`
--

LOCK TABLES `noa_pp_lat5_inventories` WRITE;
/*!40000 ALTER TABLE `noa_pp_lat5_inventories` DISABLE KEYS */;
/*!40000 ALTER TABLE `noa_pp_lat5_inventories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_asset`
--

DROP TABLE IF EXISTS `property_asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property_asset` (
  `property_asset_id` int NOT NULL AUTO_INCREMENT,
  `asset_description` varchar(255) NOT NULL,
  `category` varchar(5) NOT NULL,
  `effective_life` int NOT NULL,
  `pptype` varchar(10) NOT NULL,
  `section_number` int NOT NULL,
  PRIMARY KEY (`property_asset_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_asset`
--

LOCK TABLES `property_asset` WRITE;
/*!40000 ALTER TABLE `property_asset` DISABLE KEYS */;
/*!40000 ALTER TABLE `property_asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `creation_time` datetime(6) NOT NULL,
  `email_address` varchar(75) NOT NULL,
  `failed_logins` int DEFAULT NULL,
  `full_name` varchar(50) NOT NULL,
  `last_login_time` datetime(6) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `username` varchar(75) NOT NULL,
  `user_role_id` int NOT NULL,
  `user_status_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKh2wc2dtfdo8maylne7mgubowq` (`user_role_id`),
  KEY `FKo6g0t5ih8a5bsioca8qh5ukg3` (`user_status_id`),
  CONSTRAINT `FKh2wc2dtfdo8maylne7mgubowq` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`user_role_id`),
  CONSTRAINT `FKo6g0t5ih8a5bsioca8qh5ukg3` FOREIGN KEY (`user_status_id`) REFERENCES `user_status` (`user_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2026-01-15 08:30:00.000000','johndoe@gmail.com',0,'John Doe','2026-09-28 14:22:15.000000','$2a$12$eImiTxAk4vmM8ajUXwZ6DeV.T0kL1A8y6G37pY2eB7Gj5Z1q2w3e4','1234567890','johndoe',1,1),(2,'2026-02-20 10:15:30.000000','jane.smith@example.com',1,'Jane Smith','2026-09-29 09:11:04.000000','$2a$12$K7vjF8xMw2bP6mN3q4zO.uX8yZ9w1v2u3t4s5r6q7p8o9n0m1l2k3','5550147281','janesmith',2,1),(3,'2026-03-05 11:45:12.000000','robert.johnson@example.com',0,'Robert Johnson','2026-09-25 18:34:50.000000','$2a$12$7v9w8x7y6z5a4b3c2d1e0uFvGwHxIyJzKaLbMcNdOePfQgRhSiTjU','5550129384','rjohnson',2,1),(4,'2026-04-12 14:20:00.000000','emily.davis@example.com',3,'Emily Davis','2026-09-12 07:15:22.000000','$2a$12$z2y1x0w9v8u7t6s5r4q3p2o1n0m9l8k7j6i5h4g3f2e1d0c9b8a7b','5550174639','emily_d',2,2),(5,'2026-05-18 16:55:45.000000','michael.brown@example.com',0,'Michael Brown','2026-09-29 11:05:00.000000','$2a$12$A1b2C3d4E5f6G7h8I9j0kLmNoPqRsTuVwXyZ0123456789abcdefg','5550138472','mbrown',1,1),(6,'2026-06-22 09:00:15.000000','sarah.miller@example.com',0,'Sarah Miller','2026-09-27 13:44:19.000000','$2a$12$9876543210abcdefhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQ','5550183746','smiller',2,1),(7,'2026-07-01 07:10:00.000000','david.wilson@example.com',0,'David Wilson',NULL,'$2a$12$ZaXyWvUtSrQpOnMlKjIhGfEdCbAzYxWvUtSrQpOnMlKjIhGfEdCbA','5550162534','dwilson',2,3),(8,'2026-07-19 12:35:22.000000','jessica.taylor@example.com',2,'Jessica Taylor','2026-09-20 21:18:41.000000','$2a$12$bB3cC4dD5eEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ','5550159483','jtaylor',2,1),(9,'2026-08-05 15:40:10.000000','james.anderson@example.com',0,'James Anderson','2026-09-29 02:50:33.000000','$2a$12$1a2b3c4d5e6f7g8h9i0jKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz12','5550193847','janderson',3,1),(10,'2026-08-30 17:22:00.000000','amanda.thomas@example.com',0,'Amanda Thomas','2026-09-28 16:01:12.000000','$2a$12$MmNnOoPpQqRrSsTtUuVvWwXxYyZz1a2b3c4d5e6f7g8h9i0jKkLlM','5550128374','amanda_t',2,1),(12,'2026-09-29 18:03:38.289533','josephadogeri@example.com',0,'ado ado ado ado',NULL,'SecuredNewPassword','5045414308','string me along ',3,2),(13,'2026-10-07 16:27:04.079309','johndoe@gmail.com',0,'string me along',NULL,'P@ssw0rd123','1234567890','thegreatestuser',2,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_change`
--

DROP TABLE IF EXISTS `user_change`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_change` (
  `user_change_id` int NOT NULL AUTO_INCREMENT,
  `initiated_time` datetime(6) NOT NULL,
  `verification_code` varchar(255) NOT NULL,
  `user_change_type_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_change_id`),
  KEY `FK72o63idhsxpefxaqeoxe4ke9` (`user_change_type_id`),
  KEY `FKodv0pkjelonq3t8gcseilffth` (`user_id`),
  CONSTRAINT `FK72o63idhsxpefxaqeoxe4ke9` FOREIGN KEY (`user_change_type_id`) REFERENCES `user_change_type` (`user_change_type_id`),
  CONSTRAINT `FKodv0pkjelonq3t8gcseilffth` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_change`
--

LOCK TABLES `user_change` WRITE;
/*!40000 ALTER TABLE `user_change` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_change` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_change_type`
--

DROP TABLE IF EXISTS `user_change_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_change_type` (
  `user_change_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_change_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_change_type`
--

LOCK TABLES `user_change_type` WRITE;
/*!40000 ALTER TABLE `user_change_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_change_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_role_id` int NOT NULL,
  `description` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'Full system access.','Admin'),(2,'Standard access.','User'),(3,'Restricted accesss.','Guest');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_status`
--

DROP TABLE IF EXISTS `user_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_status` (
  `user_status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_status`
--

LOCK TABLES `user_status` WRITE;
/*!40000 ALTER TABLE `user_status` DISABLE KEYS */;
INSERT INTO `user_status` VALUES (1,'Enabled'),(2,'Locked'),(3,'Pending');
/*!40000 ALTER TABLE `user_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-08 11:11:38
