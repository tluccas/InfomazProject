-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: infomaz
-- ------------------------------------------------------
-- Server version	9.3.0

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
-- Table structure for table `cadastro_clientes`
--

DROP TABLE IF EXISTS `cadastro_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cadastro_clientes` (
  `idCliente` int NOT NULL,
  `nomeCliente` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dataCadastro` date DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cadastro_clientes`
--

LOCK TABLES `cadastro_clientes` WRITE;
/*!40000 ALTER TABLE `cadastro_clientes` DISABLE KEYS */;
INSERT INTO `cadastro_clientes` VALUES (2001,'João Silva Almeida','2022-03-15'),(2002,'Maria Oliveira Santos','2021-05-22'),(2003,'Carlos Eduardo Pereira','2023-01-10'),(2004,'Ana Paula Costa Lima','2020-11-03'),(2005,'Pedro Henrique Souza','2022-07-28'),(2006,'Juliana Martins Rodrigues','2021-09-14'),(2007,'Ricardo Fernandes Gomes','2022-12-05'),(2008,'Amanda Ribeiro Alves','2023-02-19'),(2009,'Marcos Antonio Nogueira','2021-04-30'),(2010,'Patricia Cunha Melo','2020-08-08'),(2011,'Lucas Gabriel Dias','2022-10-25'),(2012,'Fernanda Beatriz Castro','2021-06-17'),(2013,'Roberto Andrade Pinheiro','2023-03-02'),(2014,'Camila Duarte Vasconcelos','2020-05-11'),(2015,'Gustavo Henrique Barros','2022-09-29'),(2016,'Isabela Freitas Monteiro','2021-12-07'),(2017,'Rafael Carvalho Santos','2023-04-20'),(2018,'Larissa Moura Brito','2020-08-13'),(2019,'Bruno Costa Teixeira','2022-01-26'),(2020,'Vanessa Almeida Rocha','2021-07-09'),(2021,'Diego Pereira Lima','2023-10-31'),(2022,'Daniela Soares Campos','2020-02-22'),(2023,'Thiago Nascimento Oliveira','2022-05-18'),(2024,'Beatriz Cunha Xavier','2021-09-04'),(2025,'Leonardo Martins Barbosa','2023-11-15'),(2026,'Tatiane Ferreira Cardoso','2020-03-27'),(2027,'Felipe Augusto Dias','2022-06-10'),(2028,'Laura Mendes Sousa','2021-08-23'),(2029,'André Luiz Rios','2023-12-06'),(2030,'Claudia Regina Moraes','2020-04-19'),(2031,'Eduardo Sampaio Neto','2022-07-02'),(2032,'Renata Bastos Franco','2021-10-14'),(2033,'Rodrigo Pires Albuquerque','2023-01-28'),(2034,'Mariana Torres Lemos','2020-03-11'),(2035,'Alexandre Campos Dutra','2022-06-24'),(2036,'Viviane Castro Neves','2021-09-08'),(2037,'Hugo Leonardo Peixoto','2023-12-21'),(2038,'Silvia Helena Prado','2020-05-03'),(2039,'Paulo Roberto Viana','2022-08-16'),(2040,'Cristina Machado Guimarães','2021-11-29');
/*!40000 ALTER TABLE `cadastro_clientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-16 14:43:01
