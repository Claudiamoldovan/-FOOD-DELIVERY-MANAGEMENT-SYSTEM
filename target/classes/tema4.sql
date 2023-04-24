CREATE DATABASE  IF NOT EXISTS `tema4` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tema4`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: schooldb
-- ------------------------------------------------------
-- Server version	5.7.12-log  
SET GLOBAL time_zone = '+3:00';
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

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `nume` varchar(45) DEFAULT NULL,
  `parola` varchar(45) DEFAULT NULL,
  `tip` int(11) not null ,
 
) ;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

SELECT * FROM user;
INSERT INTO `user` values ('Andrei','blabla','1');
INSERT INTO `user` values ('catalina','catalina','0');
SELECT * FROM user;


-- Dump completed on 2017-04-03 15:51:37
