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

-- Copiando estrutura do banco de dados para pepper
CREATE DATABASE IF NOT EXISTS `pepper` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pepper`;


-- Copiando estrutura para tabela pepper.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `numcadnac` bigint(20) DEFAULT NULL,
  `numcadest` int(11) DEFAULT NULL,
  `endereco` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `tipo` int(12) DEFAULT NULL,
  `telefone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin7;

-- Copiando dados para a tabela pepper.cliente: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
REPLACE INTO `cliente` (`codigo`, `nome`, `numcadnac`, `numcadest`, `endereco`, `tipo`, `telefone`) VALUES
	(61, 'asdfasdf', 3456345634, 567856, 'dsfgfg', 0, 4634563);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


-- Copiando estrutura para tabela pepper.conta
CREATE TABLE IF NOT EXISTS `conta` (
  `id_conta` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `tipo` char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela pepper.conta: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
REPLACE INTO `conta` (`id_conta`, `descricao`, `tipo`) VALUES
	(1, 'PAGAMENTOS', 'D'),
	(2, 'RECEBIMENTOS', 'C'),
	(3, 'VENDAS', 'C');
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;


-- Copiando estrutura para tabela pepper.financeiro
CREATE TABLE IF NOT EXISTS `financeiro` (
  `id_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `discriminacao` varchar(50) NOT NULL,
  `tipo_lanc` char(50) NOT NULL,
  `id_conta` int(11) NOT NULL,
  `valor` float NOT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela pepper.financeiro: ~12 rows (aproximadamente)
/*!40000 ALTER TABLE `financeiro` DISABLE KEYS */;
REPLACE INTO `financeiro` (`id_codigo`, `discriminacao`, `tipo_lanc`, `id_conta`, `valor`, `data`) VALUES
	(1, 'teste', 'C', 0, 50, NULL),
	(2, 'atoa', 'D', 0, 40, NULL),
	(3, 'testee de novo', 'C', 0, 500.03, NULL),
	(4, 'bosta de andorinha', 'C', 0, 60, NULL),
	(6, 'asads', 'C', 0, 55, NULL),
	(7, 'asdas', 'C', 0, 645, NULL),
	(8, 'Wilton', 'C', 1, 55, NULL),
	(10, 'teste', 'C', 1, 50, '2015-11-05'),
	(11, 'teste', 'D', 2, 2000, '2015-11-05'),
	(12, 'lançamento', 'C', 1, 100, '2015-11-09'),
	(13, 'teste', 'C', 1, 100, '2015-11-12'),
	(14, 'teste', 'D', 2, 25, '2015-11-12');
/*!40000 ALTER TABLE `financeiro` ENABLE KEYS */;


-- Copiando estrutura para tabela pepper.itemvenda
CREATE TABLE IF NOT EXISTS `itemvenda` (
  `codigo` bigint(20) NOT NULL,
  `venda` bigint(20) NOT NULL,
  `quantidade` bigint(20) NOT NULL,
  `produto` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`,`venda`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pepper.itemvenda: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `itemvenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemvenda` ENABLE KEYS */;


-- Copiando estrutura para tabela pepper.producao
CREATE TABLE IF NOT EXISTS `producao` (
  `id_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `data_producao` date NOT NULL,
  `data_lanc` date NOT NULL,
  `usuario_lanc` varchar(50) NOT NULL,
  PRIMARY KEY (`id_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pepper.producao: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `producao` DISABLE KEYS */;
/*!40000 ALTER TABLE `producao` ENABLE KEYS */;


-- Copiando estrutura para tabela pepper.producao_item
CREATE TABLE IF NOT EXISTS `producao_item` (
  `id_producao` int(11) NOT NULL AUTO_INCREMENT,
  `id_produto` int(11) NOT NULL,
  `produtoitem` varchar(50) NOT NULL,
  `quantidade` float NOT NULL,
  `unidade` varchar(5) NOT NULL,
  PRIMARY KEY (`id_producao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pepper.producao_item: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `producao_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `producao_item` ENABLE KEYS */;


-- Copiando estrutura para tabela pepper.produto
CREATE TABLE IF NOT EXISTS `produto` (
  `id_produto` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  `estoque` float NOT NULL,
  `pr_custo` float NOT NULL,
  `pr_venda` float NOT NULL,
  `margem_lucro` float NOT NULL,
  PRIMARY KEY (`id_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pepper.produto: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
REPLACE INTO `produto` (`id_produto`, `descricao`, `estoque`, `pr_custo`, `pr_venda`, `margem_lucro`) VALUES
	(1, 'teset', 100, 100, 150, 50),
	(2, 'teste', 100, 100, 200, 100);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;


-- Copiando estrutura para tabela pepper.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `nivel` char(50) NOT NULL,
  PRIMARY KEY (`id_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela pepper.usuarios: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
REPLACE INTO `usuarios` (`id_codigo`, `nome`, `usuario`, `senha`, `nivel`) VALUES
	(1, 'A', 'A', 'A', 'L'),
	(2, 'Administrador', 'admin', 'admin', 'G'),
	(3, 'Limitado', 'limitado', 'limitado', 'L');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;


-- Copiando estrutura para tabela pepper.venda
CREATE TABLE IF NOT EXISTS `venda` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `cliente` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela pepper.venda: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
REPLACE INTO `venda` (`codigo`, `cliente`) VALUES
	(33, 61),
	(34, 61),
	(35, 61),
	(36, 1),
	(37, 61);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
