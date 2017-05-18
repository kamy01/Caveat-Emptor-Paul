-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bidding
-- ------------------------------------------------------
-- Server version	5.6.35-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `parent_id` bigint(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id_idx` (`parent_id`),
  CONSTRAINT `parent_id` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'root','root',NULL),(2,'PC Components','pc\'s',1),(27,'Nvidia','video',2),(28,'AMD','video',2),(30,'I5 5670K','CPU',2),(35,'I5 577090','CPU',NULL),(39,'AMD','Central Processing Unit',2),(41,'FX-8350','Central Processing Unit',39),(42,'FX-8370','Central Processing Unit',39),(43,'GT 1060','video',27),(44,'RX 580','video',28),(60,'Headphones','',1),(61,'Steelseries','',60),(80,'Kingston','',60);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` bigint(8) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(8) NOT NULL,
  `initial_price` bigint(8) NOT NULL,
  `best_bid` bigint(8) NOT NULL,
  `bids` bigint(8) NOT NULL,
  `closing_date` date NOT NULL,
  `opening_date` date NOT NULL,
  `status` varchar(45) NOT NULL,
  `winner_id` bigint(8) DEFAULT NULL,
  `creator_id` bigint(8) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `winner_id_idx` (`winner_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `winner_id` FOREIGN KEY (`winner_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `validation_time` bigint(10) NOT NULL,
  `validation_code` varchar(45) NOT NULL,
  `user_id` bigint(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register`
--

LOCK TABLES `register` WRITE;
/*!40000 ALTER TABLE `register` DISABLE KEYS */;
INSERT INTO `register` VALUES (49,1493821789565,'fbg7c3vhoajvk08j0raa0bbissca6lkb87l9tqp9',52),(50,1493882615134,'83m1cttgbvm9fks0f0qf5b8macka6kc8gs451hih',53),(51,1493887655168,'d40l2e07e47bv7vs5119olut1vmj1i3on3oiq92g',54),(52,1493893893208,'m3qbm5o0ghj386msqoredbus9p9c8rtgnla95jdt',55),(53,1493895438518,'k5gecj1sp739gnqvfrnc15a4sp9rcpvqlpk51cc1',56),(54,1493907421835,'rjoalq6cuscoo1a3mlcvpfth22schgdgmsa75hnm',57),(55,1493975674930,'g4e431of1f15o60f64j8eojohdiv7bpa87smtcoc',58),(56,14939804159000,'s246v2jr2skel45l4gch2to5torkm68rc24vf167',59),(57,1493995625349,'6hu73t7injjuopld915bvoud2i2aafof5e0bs8lg',60),(58,1493995909733,'sa6cvfk81bibug56d0s9e31uidiv1n96i42gs4q5',61),(59,1494052624074,'87psc7rs17mvbjkt06m11dmij297i84u9efc2psh',62),(60,1494058785536,'26s951909c1e9dljfrs47i74i0ae09h60n66bq1m',63),(61,1494060980453,'524fn12mmg7ml7l3mrett4da6fk1dlk7ht3olflm',64),(62,1494346690199,'l7piu3geb08neg9gro0vhbn2f9b3h05ca904u4i4',65),(63,1494396357109,'q359n6sm2vgmfg0lhmm8bo16oos9h738alshfjdo',66),(64,1494397994135,'lrm80uvccqtsk9174k2gggb3hul1oaqfvb3tcsvu',67),(65,1494398304181,'k39agba9na5l7jk82tldjl9s6der4q4sk920ss5h',68),(66,1494398373270,'cr8oen87lese1dff549dpqk87iq2n95psudpgf5m',69);
/*!40000 ALTER TABLE `register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `account_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `activated` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (52,'puscasu494@gmail.com','something','something3','someone','somewhere',1),(53,'puscasu494@gmail.com','something6','something3','someone','somewhere',1),(54,'puscasu494@gmail.com','Something12','something3','someone','somewhere',1),(55,'puscasu494@gmail.com','Something13','something3','someone','somewhere',1),(56,'puscasu494@gmail.com','usernameus','something3','someone','somewhere',0),(57,'puscasu494@gmail.com','something2as','something3','someone','somewhere',1),(58,'puscasu494@gmail.com','username','password3','asdsddsda','aasddad',1),(59,'puscasu494@gmail.com','bronzon','bronzon3','asdsddsda','aasddad',1),(60,'puscasu494@gmail.com','username3232','password3','sdasdad','adsasdas',1),(61,'puscasu494@gmail.com','ghbjuti','password3','WDFDDSSDF','SDFSDFSDFSDF',0),(62,'puscasu494@gmail.com','usernamesxc','password3','asdadkasdjask','posdijiopsdfgjsdfoi',1),(63,'puscasu494@gmail.com','sdasdasd','asdasd3','asdsdas','sxcv',0),(64,'puscasu494@gmail.com','username5','password3','asdadkasdjask','posdijiopsdfgjsdfoi',1),(65,'puscasu494@gmail.com','johnCena','asdas3','asdadkasdjask','posdijiopsdfgjsdfoi',1),(66,'puscasu494@gmail.com','johnCena5','asdas3','dfafasd','sdfsdf',0),(67,'puscasu494@gmail.com','johnCena987','asdas3','dfafasd','iiiiiii',0),(68,'puscasu494@gmail.com','johnCena546','asdas3','dfafasd','iiiiiii',1),(69,'puscasu494@gmail.com','johnCena5674','asdas3','dfafasd','sdfsdf',0);
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

-- Dump completed on 2017-05-18 17:54:44
