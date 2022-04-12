CREATE DATABASE  IF NOT EXISTS `dacn2022` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dacn2022`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dacn2022
-- ------------------------------------------------------
-- Server version	8.0.13
 SET NAMES utf8 ;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `fullname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uname_UNIQUE` (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `dacn2022`.`user` (`id`, `uname`, `pwd`, `status`) VALUES ('1', 'ha', '123', '1');

