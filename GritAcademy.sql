-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Värd: localhost
-- Tid vid skapande: 05 feb 2024 kl 15:00
-- Serverversion: 10.4.28-MariaDB
-- PHP-version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databas: `GritAcademy`
--

-- --------------------------------------------------------

--
-- Tabellstruktur `attendance`
--

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL,
  `students_id` int(11) NOT NULL,
  `courses_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `attendance`
--

INSERT INTO `attendance` (`id`, `students_id`, `courses_id`) VALUES
(1, 1, 3),
(2, 1, 6),
(3, 1, 9),
(4, 2, 8),
(5, 2, 2),
(6, 3, 6),
(7, 3, 1),
(8, 4, 4),
(9, 4, 5),
(10, 5, 8),
(11, 5, 4),
(12, 6, 9),
(13, 6, 6),
(14, 7, 1),
(15, 7, 5),
(16, 8, 9),
(17, 8, 2),
(18, 9, 1),
(19, 9, 6),
(20, 10, 9),
(21, 10, 6),
(22, 11, 7),
(23, 11, 1);

-- --------------------------------------------------------

--
-- Tabellstruktur `courses`
--

CREATE TABLE `courses` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `YHP` int(11) NOT NULL,
  `description` text NOT NULL,
  `lector` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `courses`
--

INSERT INTO `courses` (`id`, `name`, `YHP`, `description`, `lector`) VALUES
(1, 'Matematik I', 15, 'Grundläggande matematiska koncept', 'Johan Andersson'),
(2, 'Svenska A', 15, 'Språk och litteratur', 'Alex Johansson'),
(3, 'Programmering I', 20, 'Introduktion till programmering', 'Julia Eriksson'),
(4, 'Historia', 10, 'Världens historiska händelser', 'Tilda Lindgren'),
(5, 'Biologi', 5, 'Livets processer och mångfald', 'Rasmus Nilsson'),
(6, 'Ekonomi och Företagande', 20, 'Grundläggande ekonomiska principer', 'Stefan Holm'),
(7, 'Engelska B', 15, 'Fortsättning av engelska språket', 'Jessica Persson'),
(8, 'Konst och Design', 1, 'Kreativt skapande och konstnärlig uttryck', 'Samuel Gustafsson'),
(9, 'Fysik', 20, 'Naturvetenskaplig studie av fysiska fenomen', 'Isac Eriksson'),
(10, 'Psykologi', 15, 'Mänskligt beteende och mentala processer', 'Isabelle Andersson');

-- --------------------------------------------------------

--
-- Tabellstruktur `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `fname` text NOT NULL,
  `lname` text DEFAULT NULL,
  `town` text DEFAULT NULL,
  `hobby` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumpning av Data i tabell `students`
--

INSERT INTO `students` (`id`, `fname`, `lname`, `town`, `hobby`) VALUES
(1, 'Karl', 'Kowal', 'Malmö', 'Läsa'),
(2, 'Anna', 'Andersson', 'Oxie', 'Måla'),
(3, 'Erik', 'Svensson', 'Hör', 'Fotboll'),
(4, 'Maria', 'Lindgren', 'Trelleborg', 'Musik'),
(5, 'Karl', 'Johansson', 'Ystad', 'Teater'),
(6, 'Sofia', 'Nilsson', 'Stockholm', 'Läsning'),
(7, 'Oscar', 'Pettersson', 'Lund', 'Simning'),
(8, 'Emelie', 'Gustafsson', 'Umeå', 'Dans'),
(9, 'Gustav', 'Eriksson', 'Rinkeby', 'Film'),
(10, 'Hanna', 'Persson', 'Lidingö', 'Trädgårdsarbete'),
(11, 'Andreas', 'Holm', 'Göteborg', 'Basket');

-- --------------------------------------------------------

--
-- Tabellstruktur `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index för dumpade tabeller
--

--
-- Index för tabell `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `students_id` (`students_id`),
  ADD KEY `courses_id` (`courses_id`);

--
-- Index för tabell `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`);

--
-- Index för tabell `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);

--
-- Index för tabell `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `studentID` (`studentID`);

--
-- AUTO_INCREMENT för dumpade tabeller
--

--
-- AUTO_INCREMENT för tabell `attendance`
--
ALTER TABLE `attendance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT för tabell `courses`
--
ALTER TABLE `courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT för tabell `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT för tabell `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restriktioner för dumpade tabeller
--

--
-- Restriktioner för tabell `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`students_id`) REFERENCES `students` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`courses_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE;

--
-- Restriktioner för tabell `Users`
--
ALTER TABLE `Users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `students` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
