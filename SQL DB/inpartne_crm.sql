-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 16, 2022 at 09:14 PM
-- Server version: 5.7.23-23
-- PHP Version: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inpartne_crm`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `name`, `email`, `password`, `status`, `created_at`) VALUES
(1, 'Juan Jesus Herrera Benitez', 'juanjesus1894@hotmail.com', 'd67668a5d98d2a783b2e573b8b6a7a46', 1, '2022-11-16 06:22:02'),
(2, 'OrionTek Recruitment', 'recruitment@oriontek.do', '084e0343a0486ff05530df6c705c8bb4', 1, '2022-11-16 06:22:02');

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

CREATE TABLE `addresses` (
  `id` int(11) NOT NULL,
  `customer` int(11) NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `country` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL DEFAULT '1',
  `main` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`id`, `customer`, `address`, `city`, `country`, `created_at`, `status`, `main`) VALUES
(1, 24, 'General Leger # 108', 'San Cristobal', 'Republica Dominicana', '2022-11-17 02:22:12', 1, 0),
(2, 24, 'Jose Antonio Luna #25', 'San Cristobal', 'Republica Dominicana', '2022-11-17 02:22:12', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `name`, `email`, `status`, `created_at`, `phone`) VALUES
(1, 'Ramasundar', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '2147483647'),
(2, 'Alex ', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '2147483647'),
(3, 'Alford', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '2147483647'),
(4, 'Ravi Kumar', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '2147483647'),
(5, 'Santakumar', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '722388644'),
(6, 'Lucida', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '2147483647'),
(7, 'Anderson', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '2147483647'),
(8, 'Subbarao', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '2147483647'),
(9, 'Mukesh', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '2147483647'),
(10, 'McDen', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '2147483647'),
(11, 'Ivan', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '822544166'),
(12, 'Benjamin', 'noemail@mail.com', 1, '2022-11-16 09:31:39', '822536178'),
(24, 'Juan Jesus Herrera Benitez', 'juanjesus1894@hotmail.com', 1, '2022-11-16 19:10:59', '8299350432');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
