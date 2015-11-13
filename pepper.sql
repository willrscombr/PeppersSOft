-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 13-Nov-2015 às 21:42
-- Versão do servidor: 5.6.21
-- PHP Version: 5.6.3

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

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
`codigo` int(11) NOT NULL,
  `nome` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `numcadnac` bigint(20) DEFAULT NULL,
  `numcadest` int(11) DEFAULT NULL,
  `endereco` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `tipo` int(12) DEFAULT NULL,
  `telefone` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin7;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`codigo`, `nome`, `numcadnac`, `numcadest`, `endereco`, `tipo`, `telefone`) VALUES
(61, 'asdfasdf', 3456345634, 567856, 'dsfgfg', 0, 4634563);

-- --------------------------------------------------------

--
-- Estrutura da tabela `conta`
--

CREATE TABLE IF NOT EXISTS `conta` (
  `id_conta` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `tipo` char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `conta`
--

INSERT INTO `conta` (`id_conta`, `descricao`, `tipo`) VALUES
(1, 'PAGAMENTOS', 'D'),
(2, 'RECEBIMENTOS', 'C'),
(3, 'VENDAS', 'C');

-- --------------------------------------------------------

--
-- Estrutura da tabela `financeiro`
--

CREATE TABLE IF NOT EXISTS `financeiro` (
`id_codigo` int(11) NOT NULL,
  `discriminacao` varchar(50) NOT NULL,
  `tipo_lanc` char(50) NOT NULL,
  `id_conta` int(11) NOT NULL,
  `valor` float NOT NULL,
  `data` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

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
(14, 'teste', 'D', 2, 25, '2015-11-12');

-- --------------------------------------------------------

--
-- Estrutura da tabela `producao`
--

CREATE TABLE IF NOT EXISTS `producao` (
`id_codigo` int(11) NOT NULL,
  `data_producao` date NOT NULL,
  `data_lanc` date NOT NULL,
  `usuario_lanc` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `producao_item`
--

CREATE TABLE IF NOT EXISTS `producao_item` (
`id_producao` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `produtoitem` varchar(50) NOT NULL,
  `quantidade` float NOT NULL,
  `unidade` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
`id_produto` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `estoque` float NOT NULL,
  `pr_custo` float NOT NULL,
  `pr_venda` float NOT NULL,
  `margem_lucro` float NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id_produto`, `descricao`, `estoque`, `pr_custo`, `pr_venda`, `margem_lucro`) VALUES
(1, 'teset', 100, 100, 150, 50),
(2, 'teste', 100, 100, 200, 100);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
`id_codigo` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `nivel` char(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id_codigo`, `nome`, `usuario`, `senha`, `nivel`) VALUES
(1, 'A', 'A', 'A', 'L'),
(2, 'Administrador', 'admin', 'admin', 'G'),
(3, 'Limitado', 'limitado', 'limitado', 'L');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
 ADD PRIMARY KEY (`codigo`), ADD UNIQUE KEY `codigo` (`codigo`);

--
-- Indexes for table `financeiro`
--
ALTER TABLE `financeiro`
 ADD PRIMARY KEY (`id_codigo`);

--
-- Indexes for table `producao`
--
ALTER TABLE `producao`
 ADD PRIMARY KEY (`id_codigo`);

--
-- Indexes for table `producao_item`
--
ALTER TABLE `producao_item`
 ADD PRIMARY KEY (`id_producao`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
 ADD PRIMARY KEY (`id_produto`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`id_codigo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=62;
--
-- AUTO_INCREMENT for table `financeiro`
--
ALTER TABLE `financeiro`
MODIFY `id_codigo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `producao`
--
ALTER TABLE `producao`
MODIFY `id_codigo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `producao_item`
--
ALTER TABLE `producao_item`
MODIFY `id_producao` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
MODIFY `id_produto` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
MODIFY `id_codigo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
