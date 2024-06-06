-- MySQL dump 10.13  Distrib 8.4.0, for Win64 (x86_64)
--
-- Host: localhost    Database: kursova
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `channels`
--

DROP TABLE IF EXISTS `channels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `channels` (
  `ChannelID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `ChannelName` varchar(20) NOT NULL,
  `Active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ChannelID`),
  UNIQUE KEY `channels_unique` (`ChannelName`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channels`
--

LOCK TABLES `channels` WRITE;
/*!40000 ALTER TABLE `channels` DISABLE KEYS */;
INSERT INTO `channels` VALUES (1,'alpha',1),(2,'delta',1),(3,'beta',1),(4,'theta',1),(5,'gamma',1),(6,'whatever',1),(7,'nothing',1),(8,'empty',1),(9,'woah',1),(10,'alphaaa',1),(11,'alphaaaa',1),(12,'aaa',1);
/*!40000 ALTER TABLE `channels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourites`
--

DROP TABLE IF EXISTS `favourites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favourites` (
  `UserID` bigint unsigned NOT NULL,
  `ShowID` bigint unsigned NOT NULL,
  PRIMARY KEY (`UserID`,`ShowID`),
  KEY `favourites_shows_FK` (`ShowID`),
  CONSTRAINT `favourites_shows_FK` FOREIGN KEY (`ShowID`) REFERENCES `shows` (`ShowID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favourites_users_FK` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourites`
--

LOCK TABLES `favourites` WRITE;
/*!40000 ALTER TABLE `favourites` DISABLE KEYS */;
/*!40000 ALTER TABLE `favourites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `RoleID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(20) NOT NULL,
  PRIMARY KEY (`RoleID`),
  UNIQUE KEY `roles_unique` (`RoleName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'admin'),(1,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `ShowID` bigint unsigned NOT NULL,
  `ShowTime` datetime NOT NULL,
  KEY `schedule_shows_FK` (`ShowID`),
  CONSTRAINT `schedule_shows_FK` FOREIGN KEY (`ShowID`) REFERENCES `shows` (`ShowID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'2024-06-04 12:00:00'),(2,'2024-06-04 16:00:00');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sessions` (
  `UserID` bigint unsigned NOT NULL,
  `Token` varchar(64) NOT NULL,
  `ExpireDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Token`),
  KEY `sessions_users_FK` (`UserID`),
  CONSTRAINT `sessions_users_FK` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
INSERT INTO `sessions` VALUES (4,'-mrrj43N1Vb7LYgN5rtzwCC24GbOS1uAR3wdKgFJPdaO-pxAlfpwFYR4CRSZ_Bl9','2024-06-06 20:31:14'),(4,'-vyHA1ny04-JxDDsOcMVQGe6WuTB6OVbxCJ5B_UWOzhx_sLoLNIo49b9-ndcX18u','2024-06-06 18:29:35'),(4,'1y6wqwrOZWw5wH4PJfvyPACuZj8WJSwzENObaXWD9IAvv2mnsc8dOhWnkLGn_CE2','2024-06-06 18:26:16'),(4,'2RVabwQY0mx1xkK8NjcGxkH8ThjWjunSO4jEJ6T5EAIa5DCpciSkWkqQp_3jN6XI','2024-06-06 18:30:32'),(4,'F88a3GE51gp9D305vIGHqVK43-sz3xX5NwFJF_Y7h8-9JTK-6zCaeSq5H2uVe6p-','2024-06-06 18:44:42'),(4,'KsdrFmGMQHF4ZqLWFZMuzCMHBbxHNr9FGojTK_Odb95_wWYqJOjuCKQ-sHCD3QYZ','2024-06-06 18:28:22'),(4,'s9FmI3h9ep2y-9UjZF8Lj9oVXzxiX23IJGjgMDs9XOgKEXAAwqHW6S8rTtGOXCMu','2024-06-06 18:29:35'),(4,'tWc0dQBVLPON1KIjrx34uJInY0P-oE78CsN40v6ImBSX_BG1CGSPwLBPHoAbcjJz','2024-06-06 18:29:17'),(4,'yyz6z93GLbBrdFIktvZgMeCL1Q9wb8CWKTaZ2T56YjOY-oWS9VyHBzHPCod11GpE','2024-06-06 19:13:13');
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shows`
--

DROP TABLE IF EXISTS `shows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shows` (
  `ShowID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `ShowName` varchar(20) NOT NULL,
  `ChannelID` bigint unsigned NOT NULL,
  `Active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ShowID`),
  UNIQUE KEY `shows_unique` (`ShowName`),
  KEY `shows_channels_FK` (`ChannelID`),
  CONSTRAINT `shows_channels_FK` FOREIGN KEY (`ChannelID`) REFERENCES `channels` (`ChannelID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shows`
--

LOCK TABLES `shows` WRITE;
/*!40000 ALTER TABLE `shows` DISABLE KEYS */;
INSERT INTO `shows` VALUES (1,'omega stupid',2,1),(2,'meow',6,1),(3,'aaa',10,1);
/*!40000 ALTER TABLE `shows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `Login` varchar(20) NOT NULL,
  `PasswordHash` varchar(72) NOT NULL,
  `UserID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `RoleID` bigint unsigned DEFAULT '1',
  PRIMARY KEY (`UserID`),
  KEY `users_roles_FK` (`RoleID`),
  CONSTRAINT `users_roles_FK` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`RoleID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','root',4,2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-05 20:56:54
