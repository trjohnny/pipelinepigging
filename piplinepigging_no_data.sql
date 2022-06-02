-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pipelinepigging
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `anomalia`
--

DROP TABLE IF EXISTS `anomalia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anomalia` (
  `IDAnomalia` int NOT NULL AUTO_INCREMENT,
  `Progressiva` float NOT NULL,
  `Gravita` int NOT NULL,
  `Temperatura` int DEFAULT NULL,
  `DataManutenzione` date DEFAULT NULL,
  `Scansione` varchar(100) DEFAULT NULL,
  `Descrizione` varchar(100) NOT NULL,
  `CorsaPig` int NOT NULL,
  `Sezione` int NOT NULL,
  PRIMARY KEY (`IDAnomalia`),
  KEY `Sezione` (`Sezione`),
  KEY `anomalia_corsapig_IDCorsa_fk` (`CorsaPig`),
  CONSTRAINT `anomalia_corsapig_IDCorsa_fk` FOREIGN KEY (`CorsaPig`) REFERENCES `corsapig` (`IDCorsa`),
  CONSTRAINT `anomalia_ibfk_1` FOREIGN KEY (`Sezione`) REFERENCES `sezione` (`IDSezione`),
  CONSTRAINT `Gravita_CK` CHECK ((`Gravita` between 1 and 10)),
  CONSTRAINT `Temperatura_CK` CHECK ((`Temperatura` between -(20) and 150))
) ENGINE=InnoDB AUTO_INCREMENT=54039 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `SmartCheck` BEFORE INSERT ON `anomalia` FOR EACH ROW BEGIN
        DECLARE tipo INT;
        SET tipo = (SELECT P.tipo FROM Pig P
                INNER JOIN CorsaPig CP on P.IDPig = CP.Pig
                WHERE CP.IDCorsa = NEW.CorsaPig);
        IF tipo <> 2 THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='La corsa del Pig deve essere di uno Smart Pig';
        END IF;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `SezioneCK` BEFORE INSERT ON `anomalia` FOR EACH ROW BEGIN
            DECLARE DiamPig INT;
            DECLARE DiamSez INT;
            SET DiamPig = (SELECT P.Diametro FROM Pig P
                INNER JOIN CorsaPig CP on P.IDPig = CP.Pig
                WHERE NEW.CorsaPig=CP.IDCorsa);
            SET DiamSez = (SELECT S.Diametro FROM Sezione S
                WHERE NEW.Sezione=S.IDSezione);
            IF DiamPig > DiamSez THEN
                    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Il diametro del Pig usato in
                                        questa corsa è maggiore del diametro della sezione';
                END IF;
            END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ManutenzioneCheck` BEFORE INSERT ON `anomalia` FOR EACH ROW BEGIN
            DECLARE DM DATE;
            SET DM = (SELECT DataArrivo FROM CorsaPig WHERE IDCorsa = NEW.CorsaPig);
            IF NEW.DataManutenzione < DM THEN
                        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='La data di manutenzione deve essere
                                                                    successiva all arrivo del pig';
            END IF;
        END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `AddAnomalia` AFTER INSERT ON `anomalia` FOR EACH ROW BEGIN
            DECLARE DM DATE;
            SET DM = (SELECT DataManutenzione FROM Sezione WHERE IDSezione = NEW.Sezione);
            IF NEW.DataManutenzione > DM OR DM IS NULL THEN
                UPDATE Sezione S SET S.DataManutenzione = NEW.DataManutenzione WHERE S.IDSezione = NEW.Sezione;
            END IF;
        END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `DelAnomalia` AFTER DELETE ON `anomalia` FOR EACH ROW BEGIN
        UPDATE Sezione S SET S.DataManutenzione = null WHERE S.IDSezione = OLD.Sezione;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `corsapig`
--

DROP TABLE IF EXISTS `corsapig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corsapig` (
  `IDCorsa` int NOT NULL AUTO_INCREMENT,
  `DataPartenza` date NOT NULL,
  `DataArrivo` date NOT NULL,
  `OraPartenza` time NOT NULL,
  `OraArrivo` time NOT NULL,
  `Pig` int NOT NULL,
  PRIMARY KEY (`IDCorsa`),
  KEY `Pig` (`Pig`),
  CONSTRAINT `corsapig_ibfk_1` FOREIGN KEY (`Pig`) REFERENCES `pig` (`IDPig`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `DateCheck` BEFORE INSERT ON `corsapig` FOR EACH ROW BEGIN
            IF NEW.DataPartenza <= DATE_ADD((SELECT MAX(DataPartenza) FROM corsapig), INTERVAL 7 DAY ) THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='DataPartenza deve distare più di una settimana dall ultima';
            END IF;
        END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `AddCorsa` AFTER INSERT ON `corsapig` FOR EACH ROW BEGIN
            UPDATE Pig SET NumeroCorse = NumeroCorse+1 WHERE IDPig = NEW.Pig;
        END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `SubCorsa` AFTER DELETE ON `corsapig` FOR EACH ROW BEGIN
        UPDATE Pig SET NumeroCorse = NumeroCorse-1 WHERE IDPig = OLD.Pig;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `dittapig`
--

DROP TABLE IF EXISTS `dittapig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dittapig` (
  `PartitaIVA` varchar(11) NOT NULL,
  `RagioneSociale` varchar(30) NOT NULL,
  `FormaGiuridica` varchar(30) NOT NULL,
  `Telefono` varchar(15) NOT NULL,
  `SitoWeb` varchar(50) DEFAULT NULL,
  `Indirizzo` varchar(50) NOT NULL,
  `Comune` varchar(40) NOT NULL,
  `Provincia` char(2) NOT NULL,
  PRIMARY KEY (`PartitaIVA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fabbricasezione`
--

DROP TABLE IF EXISTS `fabbricasezione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fabbricasezione` (
  `PartitaIVA` varchar(11) NOT NULL,
  `RagioneSociale` varchar(30) NOT NULL,
  `FormaGiuridica` varchar(30) NOT NULL,
  `Telefono` varchar(15) NOT NULL,
  `SitoWeb` varchar(50) DEFAULT NULL,
  `Indirizzo` varchar(50) NOT NULL,
  `Comune` varchar(40) NOT NULL,
  `Provincia` char(2) NOT NULL,
  PRIMARY KEY (`PartitaIVA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interruzione`
--

DROP TABLE IF EXISTS `interruzione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interruzione` (
  `CorsaPig` int NOT NULL,
  `Kilometro` float NOT NULL,
  `Descrizione` varchar(100) DEFAULT NULL,
  `DataOra` datetime NOT NULL,
  PRIMARY KEY (`CorsaPig`),
  CONSTRAINT `interruzione_corsapig_IDCorsa_fk` FOREIGN KEY (`CorsaPig`) REFERENCES `corsapig` (`IDCorsa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pig`
--

DROP TABLE IF EXISTS `pig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pig` (
  `IDPig` int NOT NULL AUTO_INCREMENT,
  `Lunghezza` float NOT NULL,
  `Diametro` int NOT NULL,
  `Tipo` int NOT NULL,
  `Prezzo` float NOT NULL,
  `NumeroCorse` int NOT NULL DEFAULT '0',
  `DataAcquisto` date NOT NULL,
  `DittaPig` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`IDPig`),
  KEY `DittaPig` (`DittaPig`),
  CONSTRAINT `pig_ibfk_1` FOREIGN KEY (`DittaPig`) REFERENCES `dittapig` (`PartitaIVA`),
  CONSTRAINT `Tipo_CK` CHECK ((`Tipo` in (1,2)))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sezione`
--

DROP TABLE IF EXISTS `sezione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sezione` (
  `IDSezione` int NOT NULL AUTO_INCREMENT,
  `Materiale` varchar(20) NOT NULL,
  `DataManutenzione` date DEFAULT NULL,
  `DataInstallazione` date NOT NULL,
  `Lunghezza` float NOT NULL,
  `Diametro` int NOT NULL,
  `Fabbrica` varchar(11) NOT NULL,
  PRIMARY KEY (`IDSezione`),
  KEY `Fabbrica` (`Fabbrica`),
  CONSTRAINT `sezione_ibfk_1` FOREIGN KEY (`Fabbrica`) REFERENCES `fabbricasezione` (`PartitaIVA`)
) ENGINE=InnoDB AUTO_INCREMENT=74474 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-02 12:16:21
