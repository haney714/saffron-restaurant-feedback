-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2016 at 03:22 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `haney`
--

-- --------------------------------------------------------

--
-- Table structure for table `saffron`
--

CREATE TABLE `saffron` (
  `index` int(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `email_address` varchar(65) NOT NULL,
  `rat_quality` varchar(5) NOT NULL,
  `rat_varity` varchar(5) NOT NULL,
  `rat_service` varchar(5) NOT NULL,
  `rat_money_for_value` varchar(5) NOT NULL,
  `feedback` varchar(400) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saffron`
--

INSERT INTO `saffron` (`index`, `user_name`, `email_address`, `rat_quality`, `rat_varity`, `rat_service`, `rat_money_for_value`, `feedback`) VALUES
(1, 'haney', 'haney714@gmail.com', '3.0', '3.0', '3.0', '3.0', 'good');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `saffron`
--
ALTER TABLE `saffron`
  ADD KEY `index` (`index`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `saffron`
--
ALTER TABLE `saffron`
  MODIFY `index` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
