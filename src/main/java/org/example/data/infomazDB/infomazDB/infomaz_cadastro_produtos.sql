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
-- Table structure for table `cadastro_produtos`
--

DROP TABLE IF EXISTS `cadastro_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cadastro_produtos` (
  `idProduto` int NOT NULL,
  `idEstoque` int DEFAULT NULL,
  `nomeProduto` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `categoria` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idProduto`),
  KEY `idEstoque` (`idEstoque`),
  CONSTRAINT `fk_produto_estoque` FOREIGN KEY (`idEstoque`) REFERENCES `cadastro_estoque` (`idEstoque`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cadastro_produtos`
--

LOCK TABLES `cadastro_produtos` WRITE;
/*!40000 ALTER TABLE `cadastro_produtos` DISABLE KEYS */;
INSERT INTO `cadastro_produtos` VALUES (1001,5001,'Notebook EliteBook','Eletrônicos'),(1002,5002,'Smartphone Galaxy S23','Eletrônicos'),(1003,5003,'Mesa de Escritório','Móveis'),(1004,5004,'Cadeira Ergonômica','Móveis'),(1005,5005,'Monitor 24\" Full HD\"','Eletrônicos'),(1006,5006,'Teclado Sem Fio','Eletrônicos'),(1007,5007,'Mouse Gamer','Eletrônicos'),(1008,5008,'Impressora Multifuncional','Eletrônicos'),(1009,5009,'HD Externo 1TB','Eletrônicos'),(1010,5010,'Webcam Full HD','Eletrônicos'),(1011,5011,'Headphone Bluetooth','Eletrônicos'),(1012,5012,'Estante em Madeira','Móveis'),(1013,5013,'Armário de Aço','Móveis'),(1014,5014,'Luminária de Mesa','Decoração'),(1015,5015,'Quadro Decorativo','Decoração'),(1016,5016,'Tapete Retangular','Decoração'),(1017,5017,'Cafeteira Elétrica','Eletrodomésticos'),(1018,5018,'Ventilador de Mesa','Eletrodomésticos'),(1019,5019,'Liquidificador','Eletrodomésticos'),(1020,5020,'Ferro de Passar','Eletrodomésticos'),(1021,5021,'Furadeira 12V','Ferramentas'),(1022,5022,'Jogo de Chaves','Ferramentas'),(1023,5023,'Serra Elétrica','Ferramentas'),(1024,5024,'Kit Pincéis','Ferramentas'),(1025,5025,'Mochila para Notebook','Acessórios'),(1026,5026,'Suporte para Monitor','Acessórios'),(1027,5027,'Organizador de Cabos','Acessórios'),(1028,5028,'Carregador Portátil','Acessórios'),(1029,5029,'Fone de Ouvido com Fio','Acessórios'),(1030,5030,'Adaptador USB-C','Acessórios'),(1031,5031,'Livro: Gestão de Projetos','Livros'),(1032,5032,'Livro: Marketing Digital','Livros'),(1033,5033,'Livro: Python para Iniciantes','Livros'),(1034,5034,'Caneta Esferográfica','Papelaria'),(1035,5035,'Caderno Universitário','Papelaria'),(1036,5036,'Grampeador','Papelaria'),(1037,5037,'Calculadora Financeira','Papelaria'),(1038,5038,'Roteador Wi-Fi 5G','Eletrônicos'),(1039,5039,'Tablet 10\"\"','Eletrônicos'),(1040,5040,'Smartwatch','Eletrônicos');
/*!40000 ALTER TABLE `cadastro_produtos` ENABLE KEYS */;
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
