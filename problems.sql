-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: db11.grserver.gr:3306
-- Generation Time: May 07, 2017 at 04:26 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `HelpMyCity`
--

-- --------------------------------------------------------

--
-- Table structure for table `problems`
--

CREATE TABLE `problems` (
  `prid` int(11) NOT NULL,
  `name` varchar(70) DEFAULT NULL,
  `lastname` varchar(70) DEFAULT NULL,
  `prdescription` varchar(700) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `report_date` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `problems`
--

INSERT INTO `problems` (`prid`, `name`, `lastname`, `prdescription`, `longitude`, `latitude`, `title`, `report_date`) VALUES
(22, 'ÎšÏŽÏƒÏ„Î±Ï‚ ', 'ÎœÎ±ÏÎºÏŒÏ€Î¿Ï…Î»Î¿Ï‚ ', 'Î­Ï‡Î¿Ï…Î½Îµ ÏƒÎ¿Î²Î±ÏÎ¬ Ï€ÏÎ¿Î²Î»Î®Î¼Î±Ï„Î± Î¼Îµ Ï„Î·Î½ Î¬ÏƒÏ†Î±Î»Ï„Î¿ ÏƒÏ„Î·Î½ Î³ÎµÎ¹Ï„Î¿Î½Î¹Î¬ Î¼Î¿Ï… ', 23.547290973365307, 41.08937016806673, 'Î›Î±ÎºÎ¿ÏÎ²ÎµÏ‚ ÏƒÏ„Î¿Î½ Î´ÏÏŒÎ¼Î¿ ', '7-5-2017'),
(23, 'ÎœÎ±ÏÎ¯Î± ', 'Î–Î±ÏÎ¿Î³Î¿Ï…Î»Î¹Î´Î¿Ï…', 'ÎˆÏ‡Î¿Ï…Î½ Ï€ÎµÏÎ¬ÏƒÎµÎ¹ Î´ÏÎ¿ Î¼Î®Î½ÎµÏ‚ Î±Ï€ÏŒ Ï„ÏŒÏ„Îµ Ï€Î¿Ï… ÏƒÏ…Î¼Î¼Î¿ÏÎ¯ÎµÏ‚ Î­Ï‡Î¿Ï…Î½ Ï€ÏÎ¿ÎºÎ±Î»Î­ÏƒÎµÎ¹ ÏƒÎ¿Î²Î±ÏÎ­Ï‚ Î¶Î·Î¼Î¹Î­Ï‚ ÏƒÏ„Î·Î½ Ï€ÎµÏÎ¹Î¿Ï‡Î® ', 23.54141056537628, 41.08963979062593, 'Î‘Î»Î®Ï„ÎµÏ‚ Ï„Î·Ï‚ Î³ÎµÎ¹Ï„Î¿Î½Î¹Î¬Ï‚ ', '7-5-2017'),
(24, 'Î‘Î½Î±ÏƒÏ„Î¬ÏƒÎ·Ï‚', 'Î–Î±ÏÎ¿ÏÎ¿Ï…Î»Î¯Î´Î·Ï‚', 'Î”ÎµÎ½ Î¼Ï€Î¿ÏÎ¿ÏÎ¼Îµ Î½Î± Î­Ï‡Î¿Ï…Î¼Îµ Î±Ï…Ï„Î¬ Ï„Î± Î»Ï…ÏƒÎ±ÏƒÎ¼ÎµÎ½Î± ÏƒÎºÏ…Î»Î¹Î¬ Î½Î± ÎºÏ…ÎºÎ»Î¿Ï†Î¿ÏÎ¿ÏÎ½ ÎµÎ»ÎµÏÎ¸ÎµÏÎ± Ï‡Ï‰ÏÎ¯Ï‚ ÎµÏ€Î¯Î²Î»ÎµÏˆÎ· ', 23.552739545702934, 41.08979873349062, 'Î›Ï…ÎºÎ¿ÏƒÎºÏ…Î»Î± ÏƒÏ„Î·Î½ ÎµÎ¼Ï€Î¿ÏÎ¯Î¿Ï…', '7-5-2017'),
(25, 'Î¦ÏŽÏ„Î·Ï‚ ', 'Î’Î¿Ï…Ï„ÏƒÎ±Ï‚', 'ÎšÎ±Ï„Î±Î»Î±Î²Î±Î¯Î½Ï‰ ÏŒÏ„Î¹ Î¿Î¹ Î³ÎµÎ¯Ï„Î¿Î½ÎµÏ‚ Î¼Î¿Ï… ÎµÎ¯Î½Î±Î¹ Î»Î¬Ï„ÏÎµÏ‚ Ï„Î¹Ï‚ Î¼Î¿Ï…ÏƒÎ¹ÎºÎ®Ï‚ Î±Î»Î»Î¬ Î´ÎµÎ½ Ï‡ÏÎµÎ¹Î¬Î¶ÎµÏ„Î±Î¹ Î½Î± Ï„Î¿ Î´ÎµÎ¯Ï‡Î½Î¿Ï…Î½ ÏƒÏ„Î·Ï‚ 5:00 Ï„Î± Ï‡Î±ÏÎ¬Î¼Î±Ï„Î± ', 23.552762009203438, 41.089313564877244, 'Î¦Î±ÏƒÎ±ÏÎ±Ï„Î¶Î¹Î´ÎµÏ‚', '7-5-2017'),
(28, 'Î–Î±Ï‡Î±ÏÎ¯Î±Ï‚ ', 'ÎšÎ±ÏÎ±Î³Î¹Î¬Î½Î½Î·Ï‚', 'Ï€Î¬ÎµÎ¹ Î¿ Ï€Î±Î»Î¹ÏŒÏ‚ ÎºÎ±Î»ÏŒÏ‚ ÎºÎ±Î¹ÏÏŒÏ‚ Ï€Î¿Ï… Ï„Î± Ï€Î±Î¹Ï‡Î½Î¯Î´Î¹Î± ÏƒÏ„Î¿ ÎºÎ±ÏÎ½Î±Î²Î¬Î»Î¹ Î®Ï„Î±Î½ Î´ÏÏƒÎºÎ¿Î»Î±, Ï†Î­ÏÏ„Îµ Î¼Î¹Î± ÏƒÎ¿Î²Î±ÏÎ® Ï€ÏÏŒÎºÎ»Î·ÏƒÎ· ', 23.54776237159967, 41.0902055648267, 'Î Î±Î¹Ï‡Î½Î¹Î´Î¹Î± ÏƒÏ„Î¿ ÎºÎ±ÏÎ½Î±Î²Î¬Î»Î¹ ', '7-5-2017');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `problems`
--
ALTER TABLE `problems`
  ADD PRIMARY KEY (`prid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `problems`
--
ALTER TABLE `problems`
  MODIFY `prid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
