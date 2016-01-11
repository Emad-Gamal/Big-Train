-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.33


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema train_reservation
--

CREATE DATABASE IF NOT EXISTS train_reservation;
USE train_reservation;

--
-- Definition of table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phone` int(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


--
-- Definition of table `lut_city`
--

DROP TABLE IF EXISTS `lut_city`;
CREATE TABLE `lut_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT 'none',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `lut_city`
--

/*!40000 ALTER TABLE `lut_city` DISABLE KEYS */;
INSERT INTO `lut_city` (`id`,`name`) VALUES 
 (1,'cairo'),
 (2,'alex'),
 (3,'assuit'),
 (4,'Mansora');
/*!40000 ALTER TABLE `lut_city` ENABLE KEYS */;


--
-- Definition of table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
CREATE TABLE `passenger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` int(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `passenger`
--

/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;


--
-- Definition of table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tripID` int(11) DEFAULT NULL,
  `passengerID` int(11) DEFAULT NULL,
  `paymentCardNumber` int(11) NOT NULL,
  `numberOfSeats` int(11) NOT NULL,
  `totalCost` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `tripID_idx` (`tripID`),
  KEY `passengerID_idx` (`id`,`passengerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reservation`
--

/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;


--
-- Definition of table `train`
--

DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) NOT NULL,
  `model` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `train`
--

/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` (`id`,`capacity`,`model`) VALUES 
 (1,200,'AB200'),
 (2,150,'AA150'),
 (3,220,'AC220'),
 (4,150,'AA150'),
 (5,250,'AD250');
/*!40000 ALTER TABLE `train` ENABLE KEYS */;


--
-- Definition of table `trip`
--

DROP TABLE IF EXISTS `trip`;
CREATE TABLE `trip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sourceID` int(11) NOT NULL,
  `destinationID` int(11) NOT NULL,
  `trainID` int(11) NOT NULL,
  `pricePerPerson` int(11) NOT NULL,
  `date` date NOT NULL,
  `departureTime` time NOT NULL,
  `arrivalTime` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `trainID_idx` (`trainID`),
  KEY `sourceID` (`sourceID`),
  KEY `destiantionID` (`destinationID`),
  CONSTRAINT `destiantionID` FOREIGN KEY (`destinationID`) REFERENCES `lut_city` (`id`),
  CONSTRAINT `sourceID` FOREIGN KEY (`sourceID`) REFERENCES `lut_city` (`id`),
  CONSTRAINT `trainID` FOREIGN KEY (`trainID`) REFERENCES `train` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trip`
--

/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` (`id`,`sourceID`,`destinationID`,`trainID`,`pricePerPerson`,`date`,`departureTime`,`arrivalTime`) VALUES 
 (1,2,3,4,123,'2015-12-18','17:40:13','00:00:00'),
 (2,2,1,4,100,'2015-12-22','19:03:48','19:03:49'),
 (3,2,3,2,88,'2015-12-22','19:04:07','19:04:08');
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
