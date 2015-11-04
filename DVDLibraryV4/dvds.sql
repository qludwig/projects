-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 03, 2015 at 09:19 PM
-- Server version: 5.5.46-0ubuntu0.14.04.2
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dvd_list`
--

-- --------------------------------------------------------

--
-- Table structure for table `dvds`
--

CREATE TABLE IF NOT EXISTS `dvds` (
  `dvd_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `release_date` varchar(50) DEFAULT NULL,
  `mpaa_rating` varchar(50) DEFAULT NULL,
  `director` varchar(50) DEFAULT NULL,
  `studio` varchar(50) DEFAULT NULL,
  `note` varchar(100) DEFAULT NULL,
  `img_src` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`dvd_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `dvds`
--

INSERT INTO `dvds` (`dvd_id`, `title`, `release_date`, `mpaa_rating`, `director`, `studio`, `note`, `img_src`) VALUES
(23, 'Kingsman: The Secret Service', '2015', 'R', 'Matthew Vaughn', 'Marv Films', 'manners maketh man', 'https://upload.wikimedia.org/wikipedia/en/8/8b/Kingsman_The_Secret_Service_poster.jpg'),
(24, 'Back to the Future', '1985', 'PG', 'Robert Zemeckis', 'Universal', '2015? You mean we''re in the future!', 'https://upload.wikimedia.org/wikipedia/en/d/d2/Back_to_the_Future.jpg'),
(25, 'The Grand Budapest Hotel', '2014', 'R', 'Wes Anderson', 'Fox', '', 'https://upload.wikimedia.org/wikipedia/en/a/a6/The_Grand_Budapest_Hotel_Poster.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
