-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: movierental
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `acted`
--
CREATE DATABASE movierental;
USE movierental;
DROP TABLE IF EXISTS `acted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acted` (
  `actorId` int NOT NULL,
  `roles` varchar(25) DEFAULT NULL,
  `movieId` int NOT NULL,
  PRIMARY KEY (`movieId`,`actorId`),
  KEY `actorId` (`actorId`),
  CONSTRAINT `acted_ibfk_1` FOREIGN KEY (`actorId`) REFERENCES `person` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `acted_ibfk_2` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acted`
--

LOCK TABLES `acted` WRITE;
/*!40000 ALTER TABLE `acted` DISABLE KEYS */;
INSERT INTO `acted` VALUES (64,'side role',10),(64,'leading role',11),(72,'leading role',11),(65,'side role',12),(72,'side role',12),(66,'leading role',13),(72,'leading role',13),(66,'side role',14),(67,'side role',14),(64,'side role',15),(68,'side role',15);
/*!40000 ALTER TABLE `acted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `movieId` int NOT NULL,
  `comment` varchar(250) NOT NULL,
  `personId` int NOT NULL,
  PRIMARY KEY (`movieId`,`personId`),
  KEY `personId` (`personId`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (10,'The Mountain is my favorite movie!!!',58),(10,'It is perfect!!',63),(10,'It is perfect!!',70),(11,'It is perfect!!',58),(11,'The Wind is my favorite movie!!!',59),(11,'It is perfect!!',71),(12,'It is perfect!!',59),(12,'Toy Story is my favorite movie!!!',60),(12,'It is perfect!!',72),(13,'My favorite',58),(13,'My favorite',60),(13,'The Farewell is my favorite movie!!!',61),(14,'Delicious',61),(14,'The Knives Out is my favorite movie!!!',62),(14,'Delicious',73),(15,'The US is my favorite movie!!!',65),(15,'I like it',70),(15,'I like it',75);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `personId` int NOT NULL,
  `password` varchar(25) NOT NULL,
  `Adress` varchar(400) DEFAULT NULL,
  `email` varchar(25) NOT NULL,
  PRIMARY KEY (`personId`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (63,'3355','Turkey, Zip:33100,Street: 2-25-9','1015@gmail.com'),(64,'3344','Turkey, Zip:33100,Street: 2-25-9','1016@gmail.com'),(65,'5533','Turkey, Zip:33100,Street: 2-25-9','1017@gmail.com'),(66,'4433','Turkey, Zip:33100,Street: 2-25-9','1018@gmail.com'),(67,'1122','Turkey, Zip:33100,Street: 2-25-9','1019@gmail.com'),(68,'5522','Turkey, Zip:33100,Street: 2-25-9','1020@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `director` (
  `personId` int NOT NULL,
  `company` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`personId`),
  CONSTRAINT `director_ibfk_1` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` VALUES (55,'Netflix');
/*!40000 ALTER TABLE `director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `personId` int NOT NULL,
  `Adress` varchar(400) NOT NULL,
  `salary` int NOT NULL,
  `managerId` int DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(12) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `storeId` int DEFAULT NULL,
  PRIMARY KEY (`personId`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `managerId` (`managerId`),
  KEY `storeId` (`storeId`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`managerId`) REFERENCES `employee` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`storeId`) REFERENCES `store` (`storeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (55,'Turkey, Street: 2-25-9, Zip:2500',33100,NULL,'55@gmail.com','5','532',3),(56,'US, Street: 2-25-9, Zip: 2000',33102,55,'56@gmail.com','5','532',3),(57,'Netherlands, Street: 2-35-9, Zip:2200',33103,55,'57@gmail.com','5','532',3),(58,'Netherlands, Street: 2-35-9, Zip:2200',33104,55,'58@gmail.com','5','532',3),(59,'Turkey, Street: 2-25-9, Zip:2500',33105,55,'59@gmail.com','5','532',3),(60,'Turkey, Street: 2-25-9, Zip:2500',33106,55,'60@gmail.com','5','532',3),(61,'Turkey, Street: 2-25-9, Zip:2500',33166,55,'61@gmail.com','5','532',3),(62,'Turkey, Street: 2-25-9, Zip:2500',33154,55,'62@gmail.com','5','532',3);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hireinfo`
--

DROP TABLE IF EXISTS `hireinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hireinfo` (
  `hireId` int NOT NULL AUTO_INCREMENT,
  `customerId` int DEFAULT NULL,
  `startdate` date NOT NULL,
  `returndate` date NOT NULL,
  `dvdNum` int DEFAULT NULL,
  `totalPrice` int NOT NULL,
  `movieId` int DEFAULT NULL,
  PRIMARY KEY (`hireId`),
  KEY `customerId` (`customerId`),
  CONSTRAINT `hireinfo_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`personId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hireinfo`
--

LOCK TABLES `hireinfo` WRITE;
/*!40000 ALTER TABLE `hireinfo` DISABLE KEYS */;
INSERT INTO `hireinfo` VALUES (19,63,'2020-04-12','2020-04-22',1,20,10),(20,64,'2020-03-12','2020-04-12',2,120,11),(21,65,'2020-04-22','2020-05-12',3,120,12),(22,66,'2020-04-02','2020-04-26',2,48,13),(23,67,'2020-05-02','2020-06-12',2,80,14),(24,68,'2020-04-12','2020-07-12',2,360,15),(25,63,'2020-03-12','2020-04-12',1,90,10),(26,64,'2020-04-22','2020-05-12',1,20,11),(27,65,'2020-04-02','2020-04-12',1,10,12),(28,66,'2020-05-02','2020-06-12',2,80,13),(29,67,'2020-02-25','2020-03-12',2,86,14);
/*!40000 ALTER TABLE `hireinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `langName` varchar(25) NOT NULL,
  PRIMARY KEY (`langName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES ('English'),('Japanese'),('Spanish'),('Turkish');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movieId` int NOT NULL AUTO_INCREMENT,
  `perdayCost` int NOT NULL,
  `country` varchar(25) NOT NULL,
  `uploadTime` date DEFAULT NULL,
  `title` varchar(25) NOT NULL,
  `length` int NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `releaseDate` date DEFAULT NULL,
  `imdb` decimal(4,2) DEFAULT NULL,
  `directorId` int DEFAULT NULL,
  `picture` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`movieId`),
  KEY `directorId` (`directorId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (10,2,'Turkey','2020-06-21','The Mountain',121,'The Mountain is a 2012 adventure Turkish drama film directed by Alper Caglar.','2019-06-21',7.90,1,'The Mountain.jpg'),(11,5,'Turkey','2018-06-21','The Wind',121,'is good movie.','2019-05-21',7.90,1,'The Wind.jpg'),(12,2,'United States','2020-06-02','Toy Story',100,'Toy Story is good movie.','2015-06-21',8.00,2,'Toy Story.jpg'),(13,7,'Madagascar','2020-06-01','The Farewell',150,'The Farewell is good movie.','2018-06-11',6.90,1,'The Farewell.jpg'),(14,8,'United States','2018-06-21','The Knives Out',91,'The Knives Out is good movie.','2020-06-21',7.90,2,'The Knives Out.jpg'),(15,12,'Turkey','2020-05-21','The US',121,'The US is good movie.','2020-06-21',7.90,2,'The US.jpg');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moviecategory`
--

DROP TABLE IF EXISTS `moviecategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moviecategory` (
  `catgName` varchar(25) NOT NULL,
  `movieId` int NOT NULL,
  PRIMARY KEY (`catgName`,`movieId`),
  KEY `movieId` (`movieId`),
  CONSTRAINT `moviecategory_ibfk_1` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moviecategory`
--

LOCK TABLES `moviecategory` WRITE;
/*!40000 ALTER TABLE `moviecategory` DISABLE KEYS */;
INSERT INTO `moviecategory` VALUES ('Action',10),('Comedy',11),('Drama',11),('Action',12),('Comedy',12),('Drama',13),('Horror',13),('Comedy',14),('Fantasy',14),('Horror',15);
/*!40000 ALTER TABLE `moviecategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movielang`
--

DROP TABLE IF EXISTS `movielang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movielang` (
  `langName` varchar(25) NOT NULL,
  `motherTong` char(1) NOT NULL,
  `subtitles` char(1) NOT NULL,
  `dubbing` char(1) NOT NULL,
  `movieId` int NOT NULL,
  PRIMARY KEY (`movieId`,`langName`),
  KEY `langName` (`langName`),
  CONSTRAINT `movielang_ibfk_1` FOREIGN KEY (`langName`) REFERENCES `language` (`langName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `movielang_ibfk_2` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movielang`
--

LOCK TABLES `movielang` WRITE;
/*!40000 ALTER TABLE `movielang` DISABLE KEYS */;
INSERT INTO `movielang` VALUES ('Spanish','F','F','T',10),('Turkish','F','F','F',10),('English','T','T','T',11),('Turkish','F','F','F',12),('Japanese','T','T','F',13),('English','F','F','F',14),('Turkish','F','T','T',14),('English','F','F','T',15),('Spanish','F','F','F',15);
/*!40000 ALTER TABLE `movielang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movierate`
--

DROP TABLE IF EXISTS `movierate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movierate` (
  `customerId` int NOT NULL,
  `movieId` int NOT NULL,
  `score` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`movieId`,`customerId`),
  KEY `customerId` (`customerId`),
  CONSTRAINT `movierate_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `movierate_ibfk_2` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movierate`
--

LOCK TABLES `movierate` WRITE;
/*!40000 ALTER TABLE `movierate` DISABLE KEYS */;
INSERT INTO `movierate` VALUES (65,10,7,'2020-04-22'),(63,11,9,'2020-04-12'),(68,11,9,'2020-04-12'),(64,12,8,'2020-03-12'),(66,12,3,'2020-05-02'),(65,13,6,'2020-04-02'),(64,14,7,'2020-04-22'),(66,14,6,'2020-04-02'),(67,14,3,'2020-05-02'),(63,15,8,'2020-03-12'),(67,15,4,'2020-02-25');
/*!40000 ALTER TABLE `movierate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moviestore`
--

DROP TABLE IF EXISTS `moviestore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moviestore` (
  `storeId` int NOT NULL,
  `movieId` int NOT NULL,
  `movieNum` int DEFAULT NULL,
  PRIMARY KEY (`movieId`,`storeId`),
  KEY `storeId` (`storeId`),
  CONSTRAINT `moviestore_ibfk_1` FOREIGN KEY (`storeId`) REFERENCES `store` (`storeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `moviestore_ibfk_2` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moviestore`
--

LOCK TABLES `moviestore` WRITE;
/*!40000 ALTER TABLE `moviestore` DISABLE KEYS */;
INSERT INTO `moviestore` VALUES (3,10,500),(3,11,800),(3,12,600),(3,13,500),(3,14,80);
/*!40000 ALTER TABLE `moviestore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `personId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `birthDate` date NOT NULL,
  PRIMARY KEY (`personId`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (55,'Mert Yüksek','male','1998-02-17'),(56,'Murat Yılma','female','1999-02-17'),(57,'Mahmut Konuc','male','1997-02-17'),(58,'Mert Saracoglu','male','1994-02-17'),(59,'Fatma Kardelen','female','1991-02-17'),(60,'Mert Yüksekova','male','1990-02-17'),(61,'Merve Yüksek','female','1995-02-17'),(62,'Ali Tonugc','male','1993-02-17'),(63,'Mert Lee','male','1990-02-17'),(64,'Merve Genc','female','1995-02-17'),(65,'Mert Arslan','male','1998-02-17'),(66,'Ali Fırtına','male','1993-02-17'),(67,'Merve Dogan','female','1995-02-17'),(68,'Mert Tunc','male','1998-02-17'),(69,'Ali Memic','male','1993-02-17'),(70,'Alihan Kartal','male','1993-02-17'),(71,'Berkan Gelincik','male','1993-02-17'),(72,'Kazim Yüksek','male','1993-02-17'),(73,'Doguan Yıldız','male','1993-02-17'),(74,'Ali Güney','male','1993-02-17'),(75,'Fatima Kış','female','1993-02-17');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phonecustomer`
--

DROP TABLE IF EXISTS `phonecustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phonecustomer` (
  `customerId` int NOT NULL,
  `phone` varchar(12) NOT NULL,
  PRIMARY KEY (`customerId`,`phone`),
  CONSTRAINT `phonecustomer_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phonecustomer`
--

LOCK TABLES `phonecustomer` WRITE;
/*!40000 ALTER TABLE `phonecustomer` DISABLE KEYS */;
INSERT INTO `phonecustomer` VALUES (63,'123777789167'),(63,'523456789124'),(64,'123777789125'),(64,'923777896189'),(65,'100125889100'),(65,'623667789125'),(66,'123557789133'),(67,'724447789121'),(68,'823771111445');
/*!40000 ALTER TABLE `phonecustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phoneemployee`
--

DROP TABLE IF EXISTS `phoneemployee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phoneemployee` (
  `employeeId` int NOT NULL,
  `phone` varchar(12) NOT NULL,
  PRIMARY KEY (`employeeId`,`phone`),
  CONSTRAINT `phoneemployee_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phoneemployee`
--

LOCK TABLES `phoneemployee` WRITE;
/*!40000 ALTER TABLE `phoneemployee` DISABLE KEYS */;
INSERT INTO `phoneemployee` VALUES (55,'123456789123'),(56,'123777789123'),(57,'123667789123'),(58,'123557789123'),(59,'124447789123'),(60,'123771111423'),(61,'123777789123'),(62,'100125889123'),(62,'123777896103');
/*!40000 ALTER TABLE `phoneemployee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produced`
--

DROP TABLE IF EXISTS `produced`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produced` (
  `producerId` int NOT NULL,
  `movieId` int NOT NULL,
  PRIMARY KEY (`movieId`,`producerId`),
  KEY `producerId` (`producerId`),
  CONSTRAINT `produced_ibfk_1` FOREIGN KEY (`producerId`) REFERENCES `person` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `produced_ibfk_2` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produced`
--

LOCK TABLES `produced` WRITE;
/*!40000 ALTER TABLE `produced` DISABLE KEYS */;
INSERT INTO `produced` VALUES (59,13),(59,15),(60,12),(60,14),(61,10),(62,11);
/*!40000 ALTER TABLE `produced` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producer`
--

DROP TABLE IF EXISTS `producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producer` (
  `personId` int NOT NULL,
  `cost` int DEFAULT NULL,
  PRIMARY KEY (`personId`),
  CONSTRAINT `producer_ibfk_1` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producer`
--

LOCK TABLES `producer` WRITE;
/*!40000 ALTER TABLE `producer` DISABLE KEYS */;
INSERT INTO `producer` VALUES (59,25000),(60,30000),(61,32000),(62,50000);
/*!40000 ALTER TABLE `producer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `storeId` int NOT NULL AUTO_INCREMENT,
  `storeName` varchar(25) NOT NULL,
  `storeAdress` varchar(250) NOT NULL,
  `managerId` int DEFAULT NULL,
  PRIMARY KEY (`storeId`),
  KEY `managerId` (`managerId`),
  CONSTRAINT `store_ibfk_1` FOREIGN KEY (`managerId`) REFERENCES `employee` (`personId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (3,'Mersin-Store','Resitpasa, Katar Cd D:6/18, 34467 Sariyer/Mersin',55),(4,'Eskisehir-Store','Resitpasa, Katar Cd D:6/18, 34467 Sariyer/Eskisehir',55);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'movierental'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-05 22:40:28
