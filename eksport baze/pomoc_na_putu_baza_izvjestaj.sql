-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pomoc_na_putu_baza
-- ------------------------------------------------------
-- Server version	5.5.60-log

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
-- Table structure for table `izvjestaj`
--

DROP TABLE IF EXISTS `izvjestaj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `izvjestaj` (
  `ID_Izvjestaja` int(11) NOT NULL,
  `ID_Korisnika` int(11) DEFAULT NULL,
  `ID_Intervencije` int(11) DEFAULT NULL,
  `Napomena` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Izvjestaja`),
  KEY `ID_Korisnika` (`ID_Korisnika`),
  KEY `ID_Intervencije` (`ID_Intervencije`),
  CONSTRAINT `izvjestaj_ibfk_1` FOREIGN KEY (`ID_Korisnika`) REFERENCES `korisnik` (`ID_Korisnika`),
  CONSTRAINT `izvjestaj_ibfk_2` FOREIGN KEY (`ID_Intervencije`) REFERENCES `intervencija` (`ID_Intervencije`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `izvjestaj`
--

LOCK TABLES `izvjestaj` WRITE;
/*!40000 ALTER TABLE `izvjestaj` DISABLE KEYS */;
/*!40000 ALTER TABLE `izvjestaj` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-22 17:27:39
