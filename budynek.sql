-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Cze 25, 2024 at 02:15 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `budynek`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `data`
--

CREATE TABLE `data` (
  `watertemp` double NOT NULL,
  `airtemp` double NOT NULL,
  `ligthstatus` tinyint(1) NOT NULL,
  `powerstatus` tinyint(1) NOT NULL,
  `isopen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`watertemp`, `airtemp`, `ligthstatus`, `powerstatus`, `isopen`) VALUES
(29, 13.5, 1, 1, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `issues`
--

CREATE TABLE `issues` (
  `Data` datetime NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Text` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `issues`
--

INSERT INTO `issues` (`Data`, `Type`, `Title`, `Text`) VALUES
('2024-06-18 17:28:05', 'Usterka', 'da', '1'),
('2024-06-18 17:34:09', 'Usterka', 'brak wody', '...'),
('2024-06-18 17:35:12', 'inne', 'ciemność', 'jest brzydko, trzeba przemalowac na jaśniejsze kolory'),
('2024-06-18 22:32:48', 'Usterka', 'brak prądu', 'w dzisiejszych czasach nie do pomyślenia sobie takie rzeczy robić'),
('2024-06-18 22:34:23', 'Usterka', 'a', '1'),
('2024-06-21 15:44:20', 'Usterka', 'spalona żarówka', ''),
('2024-06-21 15:44:41', 'Usterka', '', 'spalona zaróweka'),
('2024-06-24 20:34:11', 'włamanie', 'Ukradzono rower', 'Znikło około godziny 13, widziałem podejrzanego kierowce który jeździł wokół niego o numerze rejestracyjnym xxx'),
('2024-06-24 21:05:50', 'włamanie', 'brak podstawowych przedmiotów', 'Brak papiero toaletowego');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `passwords`
--

CREATE TABLE `passwords` (
  `isAdmin` tinyint(1) NOT NULL,
  `password` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `passwords`
--

INSERT INTO `passwords` (`isAdmin`, `password`) VALUES
(0, 0),
(1, 123),
(0, 1234);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
