# Host: localhost  (Version: 5.6.16)
# Date: 2015-10-20 20:19:04
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES latin1 */;

#
# Structure for table "cliente"
#

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `numcadnac` bigint(20) DEFAULT NULL,
  `numcadest` int(11) DEFAULT NULL,
  `endereco` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `tipo` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `telefone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin7;

#
# Data for table "cliente"
#

INSERT INTO `cliente` VALUES (1,'Wilton',480283,5454,'IPORa','FISICA',54654654),(2,'Fabio',54654,654654,'IPORA','JURIDICA',546465),(3,'LAURO',54564,654654,'IVOLANDIA','FISICA',54654),(4,'asd',NULL,NULL,'Teste',NULL,0),(5,'',NULL,NULL,'Teste',NULL,0),(6,'asdasdasd',NULL,NULL,'Teste',NULL,0),(7,'asdas',NULL,NULL,'Teste',NULL,0),(8,'asdasd',NULL,NULL,'Teste',NULL,0),(9,'asdasd',NULL,NULL,'Teste',NULL,0),(10,'asdasdwww',NULL,NULL,'Teste',NULL,0),(11,'asdasdw2d',NULL,NULL,'Teste',NULL,0),(12,'asdw2222',NULL,NULL,'Teste',NULL,0),(13,'asdaczxczxc',NULL,NULL,'Teste',NULL,0),(14,'we1e1wdasdxc',NULL,NULL,'Teste',NULL,0),(15,'asdddd',NULL,NULL,'Teste',NULL,0),(16,'aaa',NULL,NULL,'Teste',NULL,0),(17,'aaa',NULL,NULL,'Teste',NULL,0),(18,'aaa',NULL,NULL,'Teste',NULL,0),(19,'aaa',NULL,NULL,'Teste',NULL,0),(20,'aaa',NULL,NULL,'Teste',NULL,0),(21,'aaa',NULL,NULL,'Teste',NULL,0),(22,'aaa',NULL,NULL,'Teste',NULL,0),(23,'aaa',NULL,NULL,'Teste',NULL,0),(24,'aaa',NULL,NULL,'Teste',NULL,0),(25,'aaa',NULL,NULL,'Teste',NULL,0),(26,'aaa',NULL,NULL,'Teste',NULL,0),(27,'aaa',NULL,NULL,'Teste',NULL,0),(28,'aaa',NULL,NULL,'Teste',NULL,0),(29,'aaa',NULL,NULL,'Teste',NULL,0),(30,'aaa',NULL,NULL,'Teste',NULL,0),(31,'aaa',NULL,NULL,'Teste',NULL,0),(32,'aaa',NULL,NULL,'Teste',NULL,0),(33,'asdasd',NULL,NULL,'Teste',NULL,0),(34,'Wilton',NULL,NULL,'Teste',NULL,0),(35,'Wilton',NULL,NULL,'Teste',NULL,0),(36,'Wilton',54654,654654,'asdasdasd',NULL,646546546),(37,'Wiltonasdasdasd',54654,654654,'asdasdasd',NULL,646546546),(38,'Teste',4654,654654,'addasda',NULL,646546),(39,'asdasdasd',1565,54654,'asdasda',NULL,46546),(40,'www',546,54654,'assfdsf',NULL,45464),(41,'asdasdasd3333',54654,654654,'asdasdasd',NULL,65454),(42,'Feijao',45646,654654,'adasdasd',NULL,54645654),(43,'Feijao',45646,654654,'adasdasd',NULL,54645654),(44,'asdasd33',654654,654654,'dasda',NULL,654654),(45,'asdas',546,654654,'asdas',NULL,5646),(46,'asdas',4654,54654,'asdasd',NULL,546546),(47,'asdas88',546,654654,'asdas',NULL,654654),(48,'WIlotn8888',4654,65465,'asdasd',NULL,654654),(49,'açksjdçlakjsd888888',5464,65464,'asdasdasd',NULL,6546456),(50,'Wilton Ribeiro Silva',48028331,2145789,'Posse',NULL,92345905),(51,'Wilton Ribeiro Silva',5444,2145789,'Posse',NULL,92345905),(52,'asda',54,654,'6a65s4d',NULL,654),(53,'asda',48028331,654,'6a65s4d',NULL,654),(54,'asda',480283311,654,'6a65s4d',NULL,654),(55,'Wilton Ribeiro Silva',4802833113,1254789,'Asdasdasd',NULL,6492345905),(56,'IF',0,124547987,'asdasd',NULL,64595858541),(57,'IF',1000000000000,124547987,'asdasd',NULL,64595858541),(58,'asdas',464,54654,'6asdasd',NULL,65464654),(59,'asdasd',54654,654654,'asda',NULL,654654),(60,'99',99,99,'99',NULL,99);
