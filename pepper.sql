-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 03-Dez-2015 às 01:31
-- Versão do servidor: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pepper`
--

-- --------------------------------------------------------


-- Estrutura da tabela `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `numcadnac` bigint(20) DEFAULT NULL,
  `numcadest` bigint(20) DEFAULT NULL,
  `endereco` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `tipo` int(12) DEFAULT NULL,
  `telefone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin7 AUTO_INCREMENT=67 ;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`codigo`, `nome`, `numcadnac`, `numcadest`, `endereco`, `tipo`, `telefone`) VALUES
(62, 'Wilton Ribeiro Silva', 48028354654, 1245789, 'Ipora', 1, 6492324654654),
(63, 'Fabio Naves', 111111111, 12458, 'Ipora', 0, 4654654650),
(64, 'lauro Henrique', 54654654, 65465465465465465, 'Ipora', 0, 5465465464),
(65, 'IF GOIANO ', 123456789012312, 123456789, 'Ipora', 0, 516436740004);

-- --------------------------------------------------------

--
-- Estrutura da tabela `conta`
--

CREATE TABLE IF NOT EXISTS `conta` (
  `id_conta` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  `tipo` char(50) NOT NULL,
  PRIMARY KEY (`id_conta`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Extraindo dados da tabela `conta`
--

INSERT INTO `conta` (`id_conta`, `descricao`, `tipo`) VALUES
(1, 'PAGAMENTOS', 'D'),
(2, 'RECEBIMENTOS', 'C'),
(3, 'VENDAS', 'C'),
(4, 'teste', 'C'),
(5, 'teste', 'C'),
(6, 'testee', 'D'),
(7, '', 'C');

-- --------------------------------------------------------

--
-- Estrutura da tabela `financeiro`
--

CREATE TABLE IF NOT EXISTS `financeiro` (
  `id_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `discriminacao` varchar(50) NOT NULL,
  `tipo_lanc` char(50) NOT NULL,
  `id_conta` int(11) NOT NULL,
  `valor` float NOT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id_codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=29 ;

--
-- Extraindo dados da tabela `financeiro`
--

INSERT INTO `financeiro` (`id_codigo`, `discriminacao`, `tipo_lanc`, `id_conta`, `valor`, `data`) VALUES
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
(14, 'teste', 'D', 2, 25, '2015-11-12'),
(15, ' : Venda58', 'C', 3, 1600, '2015-12-02'),
(16, ' Venda : 59Cliente: IF GOIANO ', 'C', 3, 1430, '2015-12-02'),
(17, ' Venda : 60Cliente: lauro Henrique', 'C', 3, 4340, '2015-12-02'),
(18, ' Venda : 61Cliente: Wilton Ribeiro Silva', 'C', 3, 525.6, '2015-12-02'),
(19, ' Venda : 62Cliente: IF GOIANO ', 'C', 3, 100, '2015-12-02'),
(20, ' Venda : 63Cliente: Wilton Ribeiro Silva', 'C', 3, 139.2, '2015-12-02'),
(21, ' Venda : 64Cliente: Wilton Ribeiro Silva', 'C', 3, 490, '2015-12-02'),
(22, ' Venda : 65Cliente: Wilton Ribeiro Silva', 'C', 3, 35, '2015-12-02'),
(23, ' Venda : 66Cliente: Wilton Ribeiro Silva', 'C', 3, 79, '2015-12-02'),
(24, ' Venda : 67Cliente: Fabio Naves', 'C', 3, 80, '2015-12-02'),
(25, ' Venda : 68Cliente: Wilton Ribeiro Silva', 'C', 3, 9, '2015-12-02'),
(26, ' Venda : 69Cliente: Fabio Naves', 'C', 3, 10, '2015-12-02'),
(27, ' Venda : 70Cliente: asdfasdf', 'C', 3, 208, '2015-12-02'),
(28, ' Venda : 71 Cliente: Fabio Naves', 'C', 3, 10, '2015-12-02');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemvenda`
--

CREATE TABLE IF NOT EXISTS `itemvenda` (
  `codigo` bigint(20) NOT NULL,
  `venda` bigint(20) NOT NULL,
  `quantidade` bigint(20) NOT NULL,
  `produto` bigint(20) NOT NULL,
  `preco` float NOT NULL,
  PRIMARY KEY (`codigo`,`venda`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `itemvenda`
--

INSERT INTO `itemvenda` (`codigo`, `venda`, `quantidade`, `produto`, `preco`) VALUES
(1, 62, 1, 5, 12),
(1, 63, 12, 0, 10),
(1, 64, 10, 0, 24),
(1, 65, 1, 0, 5),
(1, 66, 1, 0, 4),
(1, 67, 10, 0, 8),
(1, 68, 1, 2, 5),
(1, 69, 1, 3, 2),
(1, 70, 1, 4, 8),
(1, 71, 1, 3, 2),
(2, 62, 10, 2, 5.2),
(2, 63, 2, 0, 12),
(2, 64, 10, 0, 10),
(2, 65, 15, 0, 2),
(2, 66, 15, 0, 5),
(2, 68, 1, 5, 4),
(2, 69, 1, 4, 8),
(2, 71, 1, 4, 8),
(3, 64, 15, 0, 10),
(3, 70, 10, 4, 8),
(4, 62, 1, 4, 24),
(4, 70, 15, 4, 8),
(5, 62, 1, 5, 12);

-- --------------------------------------------------------

--
-- Estrutura da tabela `producao`
--

CREATE TABLE IF NOT EXISTS `producao` (
  `id_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `data_producao` date NOT NULL,
  `data_lanc` date NOT NULL,
  `responsavel` varchar(50) NOT NULL,
  `usuario_lanc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Extraindo dados da tabela `producao`
--

INSERT INTO `producao` (`id_codigo`, `data_producao`, `data_lanc`, `responsavel`, `usuario_lanc`) VALUES
(7, '2015-12-02', '2015-12-02', 'jovem', NULL),
(8, '2015-12-02', '2015-12-02', 'jovem', NULL),
(9, '2015-12-02', '2015-12-02', 'juventudes', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `producao_item`
--

CREATE TABLE IF NOT EXISTS `producao_item` (
  `id_producao` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `quantidade` float NOT NULL,
  `unidade` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `id_produto` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  `estoque` float NOT NULL,
  `pr_custo` float NOT NULL,
  `pr_venda` float NOT NULL,
  `margem_lucro` float NOT NULL,
  PRIMARY KEY (`id_produto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id_produto`, `descricao`, `estoque`, `pr_custo`, `pr_venda`, `margem_lucro`) VALUES
(2, 'teste', 100, 5, 5.2, 4),
(3, 'Pimenta de cheiro', 2, 2, 10, 400),
(4, 'Pimenta Rosa', 100, 8, 24, 200),
(5, 'Pimenta Dedo de Moça', 100, 4, 12, 200);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `nivel` char(50) NOT NULL,
  PRIMARY KEY (`id_codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id_codigo`, `nome`, `usuario`, `senha`, `nivel`) VALUES
(1, 'A', 'A', 'A', 'L'),
(2, 'Administrador', 'admin', 'admin', 'G'),
(3, 'Limitado', 'limitado', 'limitado', 'L');

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE IF NOT EXISTS `venda` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `cliente` bigint(20) NOT NULL DEFAULT '0',
  `totalvenda` float NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=72 ;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`codigo`, `cliente`, `totalvenda`) VALUES
(62, 65, 100),
(63, 62, 139.2),
(64, 62, 490),
(65, 62, 35),
(66, 62, 79),
(67, 63, 80),
(68, 62, 9),
(69, 63, 10),
(70, 61, 208),
(71, 63, 10);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
