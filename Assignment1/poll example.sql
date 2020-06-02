-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: poll example
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `polls`
--

DROP TABLE IF EXISTS `polls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `polls` (
  `PollID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` varchar(8) NOT NULL,
  `Title` varchar(40) NOT NULL,
  `Question` varchar(100) NOT NULL,
  `Answer1` varchar(100) DEFAULT NULL,
  `Answer2` varchar(100) DEFAULT NULL,
  `Answer3` varchar(100) DEFAULT NULL,
  `Answer4` varchar(100) DEFAULT NULL,
  `Votes1` int(11) DEFAULT NULL,
  `Votes2` int(11) DEFAULT NULL,
  `Votes3` int(11) DEFAULT NULL,
  `Votes4` int(11) DEFAULT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `PollOpen` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`PollID`),
  UNIQUE KEY `PollID` (`PollID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `polls`
--

LOCK TABLES `polls` WRITE;
/*!40000 ALTER TABLE `polls` DISABLE KEYS */;
INSERT INTO `polls` VALUES (1,'KaiAdeeb','Java for Web Applications vs. Mobile App','Do you think the future of Java is in Web Applications or in Mobile Technology?','Web Applications','Mobile Technology','Both','Neither',1,0,0,0,'2011-11-20','2011-11-27',0),(6,'JoeBlow','Candy Poll','What\'s your favourite kind of candy?','Smarties','Salt Water Taffy','Candy Canes','I Hate Candy',1,1,0,0,'2013-01-01','2013-03-01',0),(8,'JoeBlow','Sheridan Poll','How would you rate your Sheridan experience?','Amazing','OK','Not that great','Lousy',2,0,0,0,'2012-02-01','2012-02-08',0);
/*!40000 ALTER TABLE `polls` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-08 22:35:08
