-- phpMyAdmin SQL Dump
-- version 4.4.13.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 10-Nov-2015 às 21:51
-- Versão do servidor: 5.6.27-0ubuntu1
-- PHP Version: 5.6.11-1ubuntu3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin7;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`codigo`, `nome`, `numcadnac`, `numcadest`, `endereco`, `tipo`, `telefone`) VALUES
(62, 'WILTON RIBEIRO SILVA', 1111111, 1111, 'IPORA', 1, 888);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `financeiro`
--

INSERT INTO `financeiro` (`id_codigo`, `discriminacao`, `tipo_lanc`, `id_conta`, `valor`, `data`) VALUES
(1, 'teste', 'C', 0, 50, NULL),
(2, 'atoa', 'D', 0, 40, NULL),
(5, 'Teste', 'C', 0, 55, NULL),
(6, 'asads', 'C', 0, 55, NULL),
(7, 'asdas', 'C', 0, 645, NULL),
(8, 'Wilton', 'C', 1, 55, NULL),
(9, 'CHUPA VINICIUS', 'C', 2, 1111110, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id_produto`, `descricao`, `estoque`, `pr_custo`, `pr_venda`, `margem_lucro`) VALUES
(1, 'PIMENTA MALAGUETA', 10, 2.5, 5.6, 0),
(5, 'teste', 20, 10, 15, 50),
(6, 'pimenta de cheiro', 50, 10, 30, 200);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_codigo` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `nivel` char(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id_codigo`, `nome`, `usuario`, `senha`, `nivel`) VALUES
(7, 'Administrador', 'admin', 'admin', 'G');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `codigo` (`codigo`);

--
-- Indexes for table `financeiro`
--
ALTER TABLE `financeiro`
  ADD PRIMARY KEY (`id_codigo`);

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
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=63;
--
-- AUTO_INCREMENT for table `financeiro`
--
ALTER TABLE `financeiro`
  MODIFY `id_codigo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id_produto` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_codigo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;