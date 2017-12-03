-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 03, 2017 at 10:53 AM
-- Server version: 5.7.20-0ubuntu0.16.04.1-log
-- PHP Version: 5.5.38-3+deb.sury.org~xenial+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `plivo`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `auth_id` varchar(40) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `auth_id`, `username`) VALUES
(1, '20S0KPNOIM', 'plivo1'),
(2, '54P2EOKQ47', 'plivo2'),
(3, '9LLV6I4ZWI', 'plivo3'),
(4, 'YHWE3HDLPQ', 'plivo4'),
(5, '6DLH8A25XZ', 'plivo5');

-- --------------------------------------------------------

--
-- Table structure for table `phone_number`
--

CREATE TABLE `phone_number` (
  `id` int(11) NOT NULL,
  `number` varchar(40) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `phone_number`
--

INSERT INTO `phone_number` (`id`, `number`, `account_id`) VALUES
(1, '4924195509198', 1),
(2, '4924195509196', 1),
(3, '4924195509197', 1),
(4, '4924195509195', 1),
(5, '4924195509049', 1),
(6, '4924195509012', 1),
(7, '4924195509193', 1),
(8, '4924195509029', 1),
(9, '4924195509192', 1),
(10, '4924195509194', 1),
(11, '31297728125', 1),
(12, '3253280312', 1),
(13, '3253280311', 1),
(14, '3253280315', 1),
(15, '3253280313', 1),
(16, '3253280329', 1),
(17, '441224459508', 1),
(18, '441224980086', 1),
(19, '441224980087', 1),
(20, '441224980096', 1),
(21, '441224980098', 1),
(22, '441224980099', 1),
(23, '441224980100', 1),
(24, '441224980094', 2),
(25, '441224459426', 2),
(26, '13605917249', 2),
(27, '441224459548', 2),
(28, '441224459571', 2),
(29, '441224459598', 2),
(30, '13605895047', 2),
(31, '14433600975', 2),
(32, '16052299352', 2),
(33, '13602092244', 2),
(34, '441224459590', 2),
(35, '441224459620', 2),
(36, '441224459660', 2),
(37, '234568266473', 2),
(38, '441224980091', 2),
(39, '441224980092', 2),
(40, '441224980089', 2),
(41, '441224459482', 2),
(42, '441224980093', 2),
(43, '441887480051', 2),
(44, '441873440028', 2),
(45, '441873440017', 3),
(46, '441970450009', 3),
(47, '441235330075', 3),
(48, '441235330053', 3),
(49, '441235330044', 3),
(50, '441235330078', 3),
(51, '34881254103', 3),
(52, '61871112946', 3),
(53, '61871112915', 3),
(54, '61881666904', 3),
(55, '61881666939', 3),
(56, '61871112913', 3),
(57, '61871112901', 3),
(58, '61871112938', 3),
(59, '61871112934', 3),
(60, '61871112902', 3),
(61, '61881666926', 4),
(62, '61871705936', 4),
(63, '61871112920', 4),
(64, '61881666923', 4),
(65, '61871112947', 4),
(66, '61871112948', 4),
(67, '61871112921', 4),
(68, '61881666914', 4),
(69, '61881666942', 4),
(70, '61871112922', 4),
(71, '61871232393', 4),
(72, '61871112916', 5),
(73, '61881666921', 5),
(74, '61871112905', 5),
(75, '61871112937', 5),
(76, '61361220301', 5),
(77, '61871112931', 5),
(78, '61871112939', 5),
(79, '61871112940', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `phone_number`
--
ALTER TABLE `phone_number`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `phone_number`
--
ALTER TABLE `phone_number`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
