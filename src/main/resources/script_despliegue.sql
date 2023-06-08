-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: containers-us-west-111.railway.app    Database: railway
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `nombre` varchar(45) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Álava'),(2,'Albacete'),(3,'Alicante'),(4,'Almería'),(5,'Asturias'),(6,'Ávila'),(7,'Badajoz'),(8,'Barcelona'),(9,'Burgos'),(10,'Cáceres'),(11,'Cádiz'),(12,'Cantabria'),(13,'Castellón'),(14,'Ciudad Real'),(15,'Córdoba'),(16,'Cuenca'),(17,'Gerona'),(18,'Granada'),(19,'Guadalajara'),(20,'Guipúzcoa'),(21,'Huelva'),(22,'Huesca'),(23,'Islas Balears'),(24,'Jaén'),(25,'La Coruña'),(26,'La Rioja'),(27,'Las Palmas'),(28,'León'),(29,'Lérida'),(30,'Lugo'),(31,'Madrid'),(32,'Málaga'),(33,'Murcia'),(34,'Navarra'),(35,'Orense'),(36,'Palencia'),(37,'Pontevedra'),(38,'Salamanca'),(39,'Santa Cruz de Tenerife'),(40,'Segovia'),(41,'Sevilla'),(42,'Soria'),(43,'Tarragona'),(44,'Teruel'),(45,'Toledo'),(46,'Valencia'),(47,'Valladolid'),(48,'Vizcaya'),(49,'Zamora'),(50,'Zaragoza');
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deporte`
--

DROP TABLE IF EXISTS `deporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deporte` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `nombre` varchar(45) NOT NULL,
                           `equipos` tinyint(1) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deporte`
--

LOCK TABLES `deporte` WRITE;
/*!40000 ALTER TABLE `deporte` DISABLE KEYS */;
INSERT INTO `deporte` VALUES (1,'Fútbol Sala',1),(2,'Baloncesto',1),(3,'Pádel',0),(4,'Tenis',0),(5,'Esgrima',0),(6,'Boxeo',0),(7,'Golf',0),(8,'Voleibol',1);
/*!40000 ALTER TABLE `deporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `nombre` varchar(45) NOT NULL,
                          `escudo` varchar(100) NOT NULL,
                          `evento_id` int NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_equipo_evento1_idx` (`evento_id`),
                          CONSTRAINT `fk_equipo_evento1` FOREIGN KEY (`evento_id`) REFERENCES `evento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,'Ciervo Verde','https://i.ibb.co/fYRFPbh/ciervoverde.png',1),(2,'Ballena Azul','https://i.ibb.co/k9LNHCX/ballenazul.png',1),(3,'Ciervo Verde','https://i.ibb.co/fYRFPbh/ciervoverde.png',2),(4,'Ballena Azul','https://i.ibb.co/k9LNHCX/ballenazul.png',2),(5,'Ciervo Verde','https://i.ibb.co/fYRFPbh/ciervoverde.png',3),(6,'Ballena Azul','https://i.ibb.co/k9LNHCX/ballenazul.png',3);
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `hora` varchar(45) NOT NULL,
                          `fecha` date NOT NULL,
                          `ciudad_id` int NOT NULL,
                          `deporte_id` int NOT NULL,
                          `fecha_creacion` date NOT NULL,
                          `estado` varchar(45) NOT NULL,
                          `puntos_local` int DEFAULT NULL,
                          `puntos_visitante` int DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_evento_ciudad1_idx` (`ciudad_id`),
                          KEY `fk_evento_deporte1_idx` (`deporte_id`),
                          CONSTRAINT `fk_evento_ciudad1` FOREIGN KEY (`ciudad_id`) REFERENCES `ciudad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `fk_evento_deporte1` FOREIGN KEY (`deporte_id`) REFERENCES `deporte` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1,'15:00','2023-03-12',1,1,'2023-03-09','EN CURSO',NULL,NULL),(2,'20:00','2023-03-13',2,2,'2023-03-09','EN CURSO',NULL,NULL),(3,'20:00','2023-03-13',1,2,'2023-03-09','EN CURSO',NULL,NULL),(4,'12:30','2023-03-23',3,3,'2023-03-09','EN CURSO',NULL,NULL),(5,'10:10','2023-10-10',17,3,'2023-06-08','FINALIZADO',0,0);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `nombre` varchar(45) NOT NULL,
                           `fecha_nacimiento` date NOT NULL,
                           `avatar` varchar(100) NOT NULL,
                           `clave` varchar(60) NOT NULL,
                           `correo` varchar(100) NOT NULL,
                           `fecha_creacion` date NOT NULL,
                           `fecha_modificacion` date NOT NULL,
                           `rol` enum('ADMIN','USER') NOT NULL DEFAULT 'USER',
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `nombre_UNIQUE` (`nombre`),
                           UNIQUE KEY `correo_UNIQUE` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (0,'Plaza vacante','1901-01-01','https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png','noaccesible','noaccesible','1901-01-01','1901-01-01','USER'),(1,'Juan34','1990-01-01','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','juan@gmail.com','2000-03-09','2000-03-09','USER'),(2,'Maria1','1995-05-05','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','maria@gmail.com','2000-03-09','2000-03-09','USER'),(3,'Pepito','1995-05-05','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','pepito@gmail.com','2000-03-09','2000-03-09','USER'),(4,'danimera','1995-05-05','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','danimera@g.com','2000-03-09','2000-03-09','USER'),(5,'manug','1995-05-05','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','manug@gmail.com','2000-03-09','2000-03-09','USER'),(6,'abcd','1995-05-05','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','abc@g.com','2000-03-09','2000-03-09','USER'),(7,'julia23','1990-01-01','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','julia23@gmail.com','2000-03-09','2000-03-09','USER'),(8,'laura3893','1995-05-05','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','laura3893@gmail.com','2000-03-09','2000-03-09','USER'),(9,'manolo','1995-05-05','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','manolo@gmail.com','2000-03-09','2000-03-09','USER'),(10,'villa','1995-05-05','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','villa@g.com','2000-03-09','2000-03-09','USER'),(11,'oreoo','1995-05-05','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','oreoo@gmail.com','2000-03-09','2000-03-09','USER'),(12,'patatass','1995-05-05','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$h00BLh8xYnC1iaucI087O.y6aIzpdDb6qy.iXpcMy61mdvJeXyqCS','patatass@g.com','2000-03-09','2000-03-09','USER'),(13,'abc2','1990-01-01','https://images.pexels.com/photos/5609026/pexels-photo-5609026.jpeg?auto=compress&cs=tinysrgb&w=600','$2a$10$zxzlZGV1YLQmKHZat44iseuT.NqoS7dEIFI2p1cl9ip6/17/7N5S.','abc@gmail.com','2023-06-08','2023-06-08','USER'),(14,'pepe','1999-10-10','http://crevserverspring-production.up.railway.app:80/media/w.png','$2a$10$JxiiFY99upvML9KUBUgm5.pRK2aTnbB2JV0/0Ox6CEiCgM.v1N1/.','pepe@gmail.com','2023-06-08','2023-06-08','USER');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_has_equipo`
--

DROP TABLE IF EXISTS `usuario_has_equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_has_equipo` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `usuario_id` int NOT NULL,
                                      `equipo_id` int NOT NULL,
                                      PRIMARY KEY (`id`),
                                      KEY `fk_usuario_has_equipo_equipo1_idx` (`equipo_id`),
                                      KEY `fk_usuario_has_equipo_usuario1_idx` (`usuario_id`),
                                      CONSTRAINT `fk_usuario_has_equipo_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT `fk_usuario_has_equipo_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_has_equipo`
--

LOCK TABLES `usuario_has_equipo` WRITE;
/*!40000 ALTER TABLE `usuario_has_equipo` DISABLE KEYS */;
INSERT INTO `usuario_has_equipo` VALUES (1,1,1),(2,2,2),(3,3,1),(4,4,2),(5,0,1),(6,0,2),(7,0,1),(8,0,2),(9,0,1),(10,0,2),(11,1,3),(12,2,4),(13,3,3),(14,4,4),(15,0,3),(16,0,4),(17,0,3),(18,0,4),(19,0,3),(20,0,4),(21,1,5),(22,2,6),(23,3,5),(24,4,6),(25,0,5),(26,0,6),(27,0,5),(28,0,6),(29,0,5),(30,0,6);
/*!40000 ALTER TABLE `usuario_has_equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_has_evento`
--

DROP TABLE IF EXISTS `usuario_has_evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_has_evento` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `usuario_id` int NOT NULL,
                                      `evento_id` int NOT NULL,
                                      PRIMARY KEY (`id`),
                                      KEY `fk_usuario_has_evento_evento1_idx` (`evento_id`),
                                      KEY `fk_usuario_has_evento_usuario_idx` (`usuario_id`),
                                      CONSTRAINT `fk_usuario_has_evento_evento1` FOREIGN KEY (`evento_id`) REFERENCES `evento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT `fk_usuario_has_evento_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_has_evento`
--

LOCK TABLES `usuario_has_evento` WRITE;
/*!40000 ALTER TABLE `usuario_has_evento` DISABLE KEYS */;
INSERT INTO `usuario_has_evento` VALUES (1,1,1),(2,2,1),(3,1,2),(4,2,2),(5,3,3),(6,5,3),(7,3,4),(8,5,4),(9,14,5),(10,4,5);
/*!40000 ALTER TABLE `usuario_has_evento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-08 21:03:58
