-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.6.16 - MySQL Community Server (GPL)
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- Exportação de dados foi desmarcado.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

use perpper
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `cpf/cnpj` int(2) DEFAULT NULL,
  `rg/ie` int(11) DEFAULT NULL,
  `endereco` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `tipo` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `telefone` int(10) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin7;

#
# Data for table "cliente"
#

INSERT INTO `cliente` VALUES (1,'Wilton',480283,5454,'IPORa','FISICA',54654654),(2,'Fabio',54654,654654,'IPORA','JURIDICA',546465),(3,'LAURO',54564,654654,'IVOLANDIA','FISICA',54654),(4,'asd',NULL,NULL,'Teste',NULL,0),(5,'',NULL,NULL,'Teste',NULL,0),(6,'asdasdasd',NULL,NULL,'Teste',NULL,0),(7,'asdas',NULL,NULL,'Teste',NULL,0),(8,'asdasd',NULL,NULL,'Teste',NULL,0),(9,'asdasd',NULL,NULL,'Teste',NULL,0),(10,'asdasdwww',NULL,NULL,'Teste',NULL,0),(11,'asdasdw2d',NULL,NULL,'Teste',NULL,0),(12,'asdw2222',NULL,NULL,'Teste',NULL,0),(13,'asdaczxczxc',NULL,NULL,'Teste',NULL,0),(14,'we1e1wdasdxc',NULL,NULL,'Teste',NULL,0),(15,'asdddd',NULL,NULL,'Teste',NULL,0),(16,'aaa',NULL,NULL,'Teste',NULL,0),(17,'aaa',NULL,NULL,'Teste',NULL,0),(18,'aaa',NULL,NULL,'Teste',NULL,0),(19,'aaa',NULL,NULL,'Teste',NULL,0),(20,'aaa',NULL,NULL,'Teste',NULL,0),(21,'aaa',NULL,NULL,'Teste',NULL,0),(22,'aaa',NULL,NULL,'Teste',NULL,0),(23,'aaa',NULL,NULL,'Teste',NULL,0),(24,'aaa',NULL,NULL,'Teste',NULL,0),(25,'aaa',NULL,NULL,'Teste',NULL,0),(26,'aaa',NULL,NULL,'Teste',NULL,0),(27,'aaa',NULL,NULL,'Teste',NULL,0),(28,'aaa',NULL,NULL,'Teste',NULL,0),(29,'aaa',NULL,NULL,'Teste',NULL,0),(30,'aaa',NULL,NULL,'Teste',NULL,0),(31,'aaa',NULL,NULL,'Teste',NULL,0),(32,'aaa',NULL,NULL,'Teste',NULL,0);