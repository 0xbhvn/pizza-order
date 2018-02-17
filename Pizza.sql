-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 29, 2017 at 01:00 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Pizza`
--

-- --------------------------------------------------------

--
-- Table structure for table `Customers`
--

CREATE TABLE `Customers` (
  `Id` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Mobile` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Customers`
--

INSERT INTO `Customers` (`Id`, `Name`, `Mobile`) VALUES
(1, 'Bhaven', '9769679870');

-- --------------------------------------------------------

--
-- Table structure for table `Ingredients`
--

CREATE TABLE `Ingredients` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Ingredients`
--

INSERT INTO `Ingredients` (`Id`, `Name`, `Price`) VALUES
(1, 'Our Stuffed Crust', 100),
(2, 'Saucy BBQ Stuffed Crust', 110),
(3, 'Classic Crust', 120),
(4, 'Thin & Crispy', 130),
(5, 'Italian Style', 140),
(6, 'Double Decadence', 150),
(7, 'Our Own Tomato Sauce', 160),
(8, 'BBQ Sauce', 170),
(9, 'Sundried Tomato & Garlic Sauce', 180),
(10, 'Mozarella Cheese', 190),
(11, 'Reduced Fat Cheese', 200),
(12, 'Anchovies', 50),
(13, 'Baby Spinach', 51),
(14, 'Black Olives', 52),
(15, 'Chicken Breast Strips', 53),
(16, 'Chipotle Pulled Pork', 54),
(17, 'Chorizo Sausage', 55),
(18, 'Cumberland Sausage', 56),
(19, 'Our Herbs', 57),
(20, 'Garlic Butter', 58),
(21, 'Green & Red Peppers', 59),
(22, 'Ground Beef', 60),
(23, 'Ham', 61),
(24, 'Jalapeno Pepeers', 62),
(25, 'Mushrooms', 63),
(26, 'Pepperoni', 64),
(27, 'Pineapple', 65),
(28, 'Pork Meatballs', 66),
(29, 'Red Onions', 67),
(30, 'Roquito Sweet Chilli Peppers', 68),
(31, 'Smoked Bacon Rashers', 69),
(32, 'Sunblush Tomatoes', 70),
(33, 'Sweetcorn', 71),
(34, 'Tandoori Chicken', 72),
(35, 'Tomatoes', 73),
(36, 'Tuna', 74),
(37, 'Italiano Ventricina Salami', 75);

-- --------------------------------------------------------

--
-- Table structure for table `Orders`
--

CREATE TABLE `Orders` (
  `Id` int(11) NOT NULL,
  `Crust` varchar(50) NOT NULL,
  `Sauce` varchar(50) NOT NULL,
  `Cheese` varchar(50) NOT NULL,
  `Toppings` varchar(250) NOT NULL,
  `Price` varchar(6) NOT NULL,
  `Mobile` varchar(10) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Staff`
--

CREATE TABLE `Staff` (
  `Id` varchar(4) NOT NULL,
  `Name` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Staff`
--

INSERT INTO `Staff` (`Id`, `Name`, `Password`) VALUES
('0001', 'Bhaven', 'bhaven'),
('0002', 'Admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Customers`
--
ALTER TABLE `Customers`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `Ingredients`
--
ALTER TABLE `Ingredients`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `Orders`
--
ALTER TABLE `Orders`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `Staff`
--
ALTER TABLE `Staff`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Customers`
--
ALTER TABLE `Customers`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Ingredients`
--
ALTER TABLE `Ingredients`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `Orders`
--
ALTER TABLE `Orders`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
