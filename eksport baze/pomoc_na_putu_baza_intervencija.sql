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
-- Table structure for table `intervencija`
--

DROP TABLE IF EXISTS `intervencija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intervencija` (
  `ID_Intervencije` int(11) NOT NULL,
  `ID_Klijenta` int(11) DEFAULT NULL,
  `ID_Vozila` int(11) DEFAULT NULL,
  `ID_Korisnika` int(11) DEFAULT NULL,
  `Vrijeme_Otvaranja` date DEFAULT NULL,
  `Vrijeme_Zatvaranja` date DEFAULT NULL,
  `Napomena` varchar(255) DEFAULT NULL,
  `Status_Intervencije` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Intervencije`),
  KEY `ID_Klijenta` (`ID_Klijenta`),
  KEY `ID_Vozila` (`ID_Vozila`),
  KEY `ID_Korisnika` (`ID_Korisnika`),
  CONSTRAINT `intervencija_ibfk_1` FOREIGN KEY (`ID_Klijenta`) REFERENCES `klijent` (`ID_Klijenta`),
  CONSTRAINT `intervencija_ibfk_2` FOREIGN KEY (`ID_Vozila`) REFERENCES `vozilo` (`ID_Vozila`),
  CONSTRAINT `intervencija_ibfk_3` FOREIGN KEY (`ID_Korisnika`) REFERENCES `korisnik` (`ID_Korisnika`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervencija`
--

LOCK TABLES `intervencija` WRITE;
/*!40000 ALTER TABLE `intervencija` DISABLE KEYS */;
/*!40000 ALTER TABLE `intervencija` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-22 17:27:38
