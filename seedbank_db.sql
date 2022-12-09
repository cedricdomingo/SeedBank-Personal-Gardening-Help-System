-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.18-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `registereduser`
--

DROP TABLE IF EXISTS `registereduser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registereduser` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `alias` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registereduser`
--

LOCK TABLES `registereduser` WRITE;
/*!40000 ALTER TABLE `registereduser` DISABLE KEYS */;
INSERT INTO `registereduser` VALUES (1,'null','null','null'),(2,'Johnny','Test','Johnny Test'),(3,'Quality','Assurance','Quality Assurance'),(4,'Test','Test',NULL),(5,'Test2','Test','Tester1'),(6,'Test3','Test3','Test3');
/*!40000 ALTER TABLE `registereduser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `report_type` int(11) DEFAULT NULL,
  `report_date` date DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  UNIQUE KEY `report_id_UNIQUE` (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,1,1,'2022-03-01'),(2,2,1,'2022-03-01'),(3,2,2,'2022-03-01'),(4,2,3,'2022-03-01');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seedentry`
--

DROP TABLE IF EXISTS `seedentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seedentry` (
  `entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `entry_date` date NOT NULL,
  `seed_id` int(11) NOT NULL,
  `quantity_in_grams` int(11) NOT NULL,
  `method_obtained` varchar(9) NOT NULL,
  `expiry_date` date NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`entry_id`),
  UNIQUE KEY `entry_id_UNIQUE` (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seedentry`
--

LOCK TABLES `seedentry` WRITE;
/*!40000 ALTER TABLE `seedentry` DISABLE KEYS */;
INSERT INTO `seedentry` VALUES (1,2,'2019-01-21',1,500,'Harvested','2022-01-21','Wasted'),(2,2,'2019-01-21',2,400,'Harvested','2022-01-21','Wasted'),(3,2,'2019-01-21',3,300,'Harvested','2022-01-21','Wasted'),(4,2,'2019-01-21',4,200,'Harvested','2022-01-21','Wasted'),(5,2,'2019-01-21',5,100,'Harvested','2022-01-21','Wasted'),(6,2,'2020-01-23',6,500,'Purchased','2023-01-23','Planted'),(7,2,'2020-01-23',7,400,'Purchased','2023-01-23','Planted'),(8,2,'2020-01-23',8,300,'Purchased','2023-01-23','Planted'),(9,2,'2020-01-23',9,200,'Purchased','2023-01-23','Planted'),(10,2,'2020-01-23',10,100,'Purchased','2023-01-23','Planted'),(11,2,'2021-01-23',11,1000,'Harvested','2024-01-23','Ready'),(12,2,'2022-01-23',12,900,'Harvested','2025-01-23','Ready'),(13,2,'2020-01-23',13,800,'Harvested','2023-01-23','Ready'),(14,2,'2020-01-23',14,700,'Harvested','2023-01-23','Ready'),(15,2,'2020-01-23',15,600,'Harvested','2023-01-23','Ready');
/*!40000 ALTER TABLE `seedentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seedtype`
--

DROP TABLE IF EXISTS `seedtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seedtype` (
  `seed_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `planting_time` varchar(12) NOT NULL,
  `image_hyperlink` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`seed_id`),
  UNIQUE KEY `seed_id_UNIQUE` (`seed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seedtype`
--

LOCK TABLES `seedtype` WRITE;
/*!40000 ALTER TABLE `seedtype` DISABLE KEYS */;
INSERT INTO `seedtype` VALUES (1,'Amaranth','Direct-Sow','May','https://i.imgur.com/waHYo66.jpg'),(2,'Artichoke','Indoors','February','https://i.imgur.com/uYuyyOD.jpg'),(3,'Arugula','Direct-Sow','March','https://i.imgur.com/3uLKoSt.jpg'),(4,'Asparagus ','Indoors','February','https://i.imgur.com/CzqutNb.jpg'),(5,'Basil','Indoors','May','https://i.imgur.com/QA90pCB.jpg'),(6,'Beets','Direct-Sow','June','https://i.imgur.com/E9yRi8C.jpg'),(7,'Broccoli','Indoors','April','https://i.imgur.com/Y8SC5IG.jpg'),(8,'Brussel Sprouts','Transplant','July','https://i.imgur.com/hLQS9NN.jpg'),(9,'Cabbage','Direct-Sow','March','https://i.imgur.com/Xu8b5Ij.jpg'),(10,'Celery','Transplant','June','https://i.imgur.com/UM2Ab8y.jpg'),(11,'Chickpeas','Direct-Sow','April','https://i.imgur.com/0etsIr6.jpg'),(12,'Claytonia','Direct-Sow','February','https://i.imgur.com/h2oLwle.jpg'),(13,'Collards','Direct-Sow','August','https://i.imgur.com/KxWb5If.jpg'),(14,'Corn','Direct-Sow','June','https://i.imgur.com/08vff89.jpg'),(15,'Cress','Direct-Sow','September','https://i.imgur.com/pRoGBQU.jpg'),(16,'Cucumbers','Transplant','May','https://i.imgur.com/yfzr4ER.jpg'),(17,'Eggplant','Transplant','June','https://i.imgur.com/y83ewAf.jpg'),(18,'Garlic','Direct-Sow','November','https://i.imgur.com/2ggTbP5.jpg'),(19,'Kale','Direct-Sow','September','https://i.imgur.com/w7yNJCP.jpg'),(20,'Leeks','Indoors','April','https://i.imgur.com/j5o9ooE.jpg'),(21,'Lettuce','Direct-Sow','April','https://i.imgur.com/cYS5kWa.jpg'),(22,'Mustard','Direct-Sow','February','https://i.imgur.com/kh7OZ8l.jpg'),(23,'Okra','Transplant','June','https://i.imgur.com/oUCkl1f.jpg'),(24,'Onions','Indoors','January','https://i.imgur.com/WWY2ttV.jpg'),(25,'Parsnip','Direct-Sow','April','https://i.imgur.com/MZMbEGN.jpg'),(26,'Peas','Direct-Sow','July','https://i.imgur.com/LNQJ0EH.jpg'),(27,'Pepper','Indoors','June','https://i.imgur.com/AGVRIfW.jpg'),(28,'Radish','Direct-Sow','May','https://i.imgur.com/vhMt12J.jpg'),(29,'Rutabaga','Direct-Sow','July','https://i.imgur.com/MkqS83R.jpg'),(30,'Sesame','Transplant','May','https://i.imgur.com/8v6e1TQ.jpg'),(31,'Soya Beans','Direct-Sow','May','https://i.imgur.com/SAbgc3P.jpg'),(32,'Spinach','Transplant','October','https://i.imgur.com/SckYuzj.jpg'),(33,'Squash','Indoors','June','https://i.imgur.com/2ip9Hpg.jpg'),(34,'Tomatoes','Transplant','June','https://i.imgur.com/vsR3FKw.jpg'),(35,'Turnips','Direct-Sow','April','https://i.imgur.com/QqHnamG.jpg');
/*!40000 ALTER TABLE `seedtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topharvested`
--

DROP TABLE IF EXISTS `topharvested`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topharvested` (
  `report_id` int(11) NOT NULL,
  `top_harvested_1` int(11) DEFAULT NULL,
  `top_harvested_2` int(11) DEFAULT NULL,
  `top_harvested_3` int(11) DEFAULT NULL,
  `top_harvested_4` int(11) DEFAULT NULL,
  `top_harvested_5` int(11) DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  UNIQUE KEY `report_id_UNIQUE` (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topharvested`
--

LOCK TABLES `topharvested` WRITE;
/*!40000 ALTER TABLE `topharvested` DISABLE KEYS */;
INSERT INTO `topharvested` VALUES (1,NULL,NULL,NULL,NULL,NULL),(2,11,12,13,14,15);
/*!40000 ALTER TABLE `topharvested` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toppurchased`
--

DROP TABLE IF EXISTS `toppurchased`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `toppurchased` (
  `report_id` int(11) NOT NULL,
  `top_purchased_1` int(11) DEFAULT NULL,
  `top_purchased_2` int(11) DEFAULT NULL,
  `top_purchased_3` int(11) DEFAULT NULL,
  `top_purchased_4` int(11) DEFAULT NULL,
  `top_purchased_5` int(11) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toppurchased`
--

LOCK TABLES `toppurchased` WRITE;
/*!40000 ALTER TABLE `toppurchased` DISABLE KEYS */;
INSERT INTO `toppurchased` VALUES (3,6,7,8,9,10);
/*!40000 ALTER TABLE `toppurchased` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topwasted`
--

DROP TABLE IF EXISTS `topwasted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topwasted` (
  `report_id` int(11) NOT NULL,
  `top_wasted_1` int(11) DEFAULT NULL,
  `top_wasted_2` int(11) DEFAULT NULL,
  `top_wasted_3` int(11) DEFAULT NULL,
  `top_wasted_4` int(11) DEFAULT NULL,
  `top_wasted_5` int(11) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topwasted`
--

LOCK TABLES `topwasted` WRITE;
/*!40000 ALTER TABLE `topwasted` DISABLE KEYS */;
INSERT INTO `topwasted` VALUES (4,1,2,3,4,5);
/*!40000 ALTER TABLE `topwasted` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-09  2:12:20
