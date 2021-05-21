-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 21, 2021 at 09:22 AM
-- Server version: 5.5.15
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `itpm`
--

-- --------------------------------------------------------

--
-- Table structure for table `addsessionroom`
--

DROP TABLE IF EXISTS `addsessionroom`;
CREATE TABLE IF NOT EXISTS `addsessionroom` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `availbleSessionID` int(11) NOT NULL,
  `sessionRoom` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `availbleSessionID` (`availbleSessionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `allsessionsrooms`
--

DROP TABLE IF EXISTS `allsessionsrooms`;
CREATE TABLE IF NOT EXISTS `allsessionsrooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sessionID` varchar(100) NOT NULL,
  `sessionRoom` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `allsessionsrooms`
--

INSERT INTO `allsessionsrooms` (`id`, `sessionID`, `sessionRoom`) VALUES
(1, 'Dr.Nuwan Kodagoda-IT2030-DS-Lecture-Y1.S1.IT.01-120-2', 'A502'),
(2, 'Mr.Nuwan Kodagoda-IT1212-oop-Y2.S2.05.03-60-2', 'B402'),
(3, 'Ms.Shashika Lokuliyana-IT3010-NDM-Tutorial-Y3.S1.IT.05-60-2', 'null'),
(4, 'Mr.Kashyapa Karunarathne-IT3010-NDM-Practical-Y3.S1.IT.05.01-30-2', 'A402'),
(5, 'Ms.Kavindi Gunashinghe-IT1020-CS-Tutorial-Y1.S1.IT.01-80-1', 'B403'),
(6, 'Mrs.Madara-IT2340-ITPM-Y2.S2.05-60-2', 'A402'),
(7, 'Mr.Prasanna S Haddela-IT3020-DS-Lecture-Y1.S1.IT.12-60-2', 'B403'),
(8, 'Ms.Thamali Dissanayake-IT3020-DS-Practical-Y1.S1.IT.12-30-2', 'A502');

-- --------------------------------------------------------

--
-- Table structure for table `consecutive`
--

DROP TABLE IF EXISTS `consecutive`;
CREATE TABLE IF NOT EXISTS `consecutive` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Lecturer1` varchar(100) NOT NULL,
  `Lecturer2` varchar(100) NOT NULL,
  `SubjectCode` varchar(30) NOT NULL,
  `Subject` varchar(30) NOT NULL,
  `GroupID` varchar(30) NOT NULL,
  `Tag` varchar(30) NOT NULL,
  `Duration` varchar(30) NOT NULL,
  `sessionID` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consecutive`
--

INSERT INTO `consecutive` (`ID`, `Lecturer1`, `Lecturer2`, `SubjectCode`, `Subject`, `GroupID`, `Tag`, `Duration`, `sessionID`) VALUES
(15, 'Dr.Nuwan Kodagoda', 'Mr.Perera', 'IT2030', 'OOP', 'Y2.S1.IT.01', 'Lecture', '09:30 - 18:00', 'Dr.Nuwan Kodagoda-IT2030-DS-Lecture-Y1.S1.IT.01-120-2'),
(16, 'Mr. Prasanna S Haddela', '', 'IT3020', 'DS', 'Y3.S1.IT.12', 'Lecture', '14:00 - 16:00', 'Mr.Prasanna S Haddela-IT3020-DS-Lecture-Y1.S1.IT.12-60-2'),
(17, 'Mr. Prasanna S Haddela', '', 'IT3020', 'DS', 'Y3.S1.IT.12', 'Tutorial', '16:00 - 17:00', 'Mr.Prasanna S Haddela-IT3020-DS-Lecture-Y1.S1.IT.12-60-1'),
(20, 'Ms.Kavindi Gunashinghe', '', 'IT1020', 'CS', 'Y1.S1.IT.01', 'Lecture', '10:30 - 12:30', 'Ms.Kavindi Gunashinghe-IT1020-CS-Lecture-Y1.S1.IT.01-80-2'),
(21, 'Ms.Shashika Lokuliyana', 'Ms.Sasini', 'IT3010', 'NDM', 'Y3.S1.IT.05', 'Lecture', '11:30 - 14:30', 'Ms.Shashika Lokuliyana-IT3010-NDM-Lecture-Y3.S1.IT.05-60-2'),
(22, 'Ms.Shashika Lokuliyana', 'Ms.Sasini', 'IT3010', 'NDM', 'Y3.S1.IT.05', 'Tutorial', '14:30 - 15:30', 'Ms.Shashika Lokuliyana-IT3010-NDM-Tutorial-Y3.S1.IT.05-60-2'),
(23, 'Mr. Kashyapa Karunarathne', '', 'IT3010', 'NDM', 'Y3.S1.IT.05.01', 'Practical', '10:30 - 12:30', 'Mr.Kashyapa Karunarathne-IT3010-NDM-Practical-Y3.S1.IT.05.01-30-2'),
(24, 'Ms. Thamali Dissanayake', '', 'IT3020', 'DS', 'Y3S1.12.01(IT)	', 'Practical', '08:30 - 10:30', 'Ms.Thamali Dissanayake-IT3020-DS-Practical-Y1.S1.IT.12-30-2'),
(26, 'Mr. Senura Diwantha', '', 'IT1030', 'MC', 'Y1.S1.IT.01', 'Tutorial', '17:00 - 18:00', 'Ms.Kavindi Gunashinghe-IT1020-CS-Tutorial-Y1.S1.IT.01-80-1');

-- --------------------------------------------------------

--
-- Table structure for table `consecutivesessionroom`
--

DROP TABLE IF EXISTS `consecutivesessionroom`;
CREATE TABLE IF NOT EXISTS `consecutivesessionroom` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `conSessionID` varchar(100) NOT NULL,
  `conSessionRoom` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consecutivesessionroom`
--

INSERT INTO `consecutivesessionroom` (`ID`, `conSessionID`, `conSessionRoom`) VALUES
(1, 'Dr.NuwanKodagoda-IT2030-DS-Lecture-Y1.S1.IT.01-120', 'A502'),
(2, 'DeshaniMalsha-IT3050-ESD-Lecture-Y3.S1.SE.1-120-2', 'A402'),
(5, 'Mr.Prasanna S Haddela-IT3020-DS-Lecture-Y1.S1.IT.12-60-2', 'opw'),
(6, 'Ms.Thamali Dissanayake-IT3020-DS-Practical-Y1.S1.IT.12-30-2', 'A502');

-- --------------------------------------------------------

--
-- Table structure for table `lecturers`
--

DROP TABLE IF EXISTS `lecturers`;
CREATE TABLE IF NOT EXISTS `lecturers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `empID` varchar(30) NOT NULL,
  `faculty` varchar(30) NOT NULL,
  `department` varchar(30) NOT NULL,
  `activeDays` varchar(100) NOT NULL,
  `activeHours1` varchar(100) NOT NULL,
  `activeHours2` varchar(100) NOT NULL,
  `center` varchar(100) NOT NULL,
  `building` varchar(100) NOT NULL,
  `level` int(11) NOT NULL,
  `rank` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lecturers`
--

INSERT INTO `lecturers` (`ID`, `name`, `empID`, `faculty`, `department`, `activeDays`, `activeHours1`, `activeHours2`, `center`, `building`, `level`, `rank`) VALUES
(183, 'manudi sathmini', '000008', 'Computing', 'DS', 'Monday Tuesday Wednesday Thursday ', '10:03 PM', '10:03 PM', 'Jaffna', 'J_C Block', 2, '2.000008'),
(185, 'Sajith Perera', '190505', 'Computing', 'IT', 'Monday Tuesday Wednesday Thursday Friday ', '8:50 AM', '11:50 AM', 'Kandy', 'K1_A Block', 3, '3.190505'),
(186, 'Chathurika Pinnaduwa', '909090', 'Computing', 'DS', 'Monday Tuesday Wednesday ', '9:00 AM', '5:00 PM', 'Kandy', 'K1_C Block', 4, '4.909090'),
(188, 'Mr.Perera', '101011', 'Computing', 'IT', 'Monday ', '9:00 PM', '11:00 PM', 'Jaffna', 'J_A Block', 2, '2.101011'),
(189, 'Ms.manudi', '009090', 'Buisness', 'BA', 'Monday ', '9:10 PM', '12:10 PM', 'Malabe', 'M1_A Block', 2, '2.009090'),
(190, 'Mr.N o', '909099', 'Buisness', 'BM', 'Monday ', '9:20 PM', '12:20 PM', 'Malabe', 'M1_A Block', 3, '3.909099'),
(191, 'Mr.Nayantha Lochana', '909096', 'Computing', 'IT', 'Monday ', '9:21 PM', '12:21 PM', 'Kurunegala', 'K2_A Block', 2, '2.909096'),
(192, 'Mr.Nuwan Kodagoda', '101018', 'Buisness', 'BM', 'Monday Wednesday ', '9:00 AM', '11:00 AM', 'Malabe', 'M1_A Block', 3, '3.101010'),
(193, 'Mr.Peries', '111111', 'Architecture', 'Arciture', 'Monday Tuesday ', '9:00 AM', '3:00 PM', 'Kurunegala', 'K2_A Block', 2, '2.111111'),
(194, 'Mrs.Madara', '121212', 'Buisness', 'BA', 'Monday Saturday Sunday ', '12:00 PM', '2:00 PM', 'Jaffna', 'J_A Block', 5, '5.121212');

-- --------------------------------------------------------

--
-- Table structure for table `notavailablelecture`
--

DROP TABLE IF EXISTS `notavailablelecture`;
CREATE TABLE IF NOT EXISTS `notavailablelecture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Lecturer` varchar(100) NOT NULL,
  `mgroup` varchar(100) NOT NULL,
  `SubGroup` varchar(100) NOT NULL,
  `SessionID` varchar(100) NOT NULL,
  `Date` date NOT NULL,
  `Time_from` time NOT NULL,
  `Time_to` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notavailablelecture`
--

INSERT INTO `notavailablelecture` (`id`, `Lecturer`, `mgroup`, `SubGroup`, `SessionID`, `Date`, `Time_from`, `Time_to`) VALUES
(1, 'Mr.Manjula sirisena', 'Y1.S2.03', 'Y1.S2.03.6', 'A502', '2021-05-12', '08:30:00', '09:30:00'),
(2, 'Ms.kavindi Gunasinghe', 'Y1.S2.02', 'Y1.S2.02.1', 'A503', '2021-05-12', '09:00:00', '03:30:00'),
(3, 'Mr.Perera', 'Y2.01', 'Y2.01.01', 'Mr.Perera-Y2.01', '2021-05-19', '08:30:00', '09:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `notavailablelocations`
--

DROP TABLE IF EXISTS `notavailablelocations`;
CREATE TABLE IF NOT EXISTS `notavailablelocations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomId` varchar(100) NOT NULL,
  `datefrom` date NOT NULL,
  `dateto` date NOT NULL,
  `startT` time NOT NULL,
  `endT` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notavailablelocations`
--

INSERT INTO `notavailablelocations` (`id`, `roomId`, `datefrom`, `dateto`, `startT`, `endT`) VALUES
(1, 'A502', '2021-05-12', '2021-05-31', '08:30:00', '17:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `notoverlapping`
--

DROP TABLE IF EXISTS `notoverlapping`;
CREATE TABLE IF NOT EXISTS `notoverlapping` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Lecturer1` varchar(100) NOT NULL,
  `Lecturer2` varchar(100) NOT NULL,
  `SubjectCode` varchar(30) NOT NULL,
  `Subject` varchar(30) NOT NULL,
  `GroupID` varchar(30) NOT NULL,
  `Tag` varchar(30) NOT NULL,
  `Duration` varchar(30) NOT NULL,
  `sessionID` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notoverlapping`
--

INSERT INTO `notoverlapping` (`ID`, `Lecturer1`, `Lecturer2`, `SubjectCode`, `Subject`, `GroupID`, `Tag`, `Duration`, `sessionID`) VALUES
(9, 'Dr.Nuwan Kodagoda', 'Mr.Perera', 'IT2030', 'OOP', 'Y2.S1.IT.01', 'Lecture', '09:30 - 18:00', 'Dr.Nuwan Kodagoda-IT2030-DS-Lecture-Y1.S1.IT.01-120-2'),
(10, 'Mr. Kashyapa Karunarathne', '', 'IT3010', 'NDM', 'Y3.S1.IT.05.01', 'Practical', '10:30 - 12:30', 'Mr.Kashyapa Karunarathne-IT3010-NDM-Practical-Y3.S1.IT.05.01-30-2'),
(11, 'Ms.Kavindi Gunashinghe', '', 'IT1020', 'CS', 'Y1.S1.IT.01', 'Lecture', '10:30 - 12:30', 'Ms.Kavindi Gunashinghe-IT1020-CS-Lecture-Y1.S1.IT.01-80-2'),
(16, 'Mr. Senura Diwantha', '', 'IT1030', 'MC', 'Y1.S1.IT.01', 'Tutorial', '17:00 - 18:00', 'Ms.Kavindi Gunashinghe-IT1020-CS-Tutorial-Y1.S1.IT.01-80-1'),
(17, 'Ms.Shashika Lokuliyana', 'Ms.Sasini', 'IT3010', 'NDM', 'Y3.S1.IT.05', 'Tutorial', '14:30 - 15:30', 'Ms.Shashika Lokuliyana-IT3010-NDM-Tutorial-Y3.S1.IT.05-60-2'),
(18, 'Chathurika Pinnaduwa', 'Mr.Perera', 'IT1010', 'OOP', 'Y2.S2.05', 'Tute', '03:00 - 05:00', 'manudi sathmini-IT1010-OOP-Y2.S2.05-120-2'),
(19, 'Mrs.Madara', 'Chathurika Pinnaduwa', 'IT2340', 'ITPM', 'Y2.S2.05', 'Lecture', '10:30 - 12:30', 'Mrs.Madara-IT2340-ITPM-Y2.S2.05-60-2');

-- --------------------------------------------------------

--
-- Table structure for table `notoverlappingsessionroom`
--

DROP TABLE IF EXISTS `notoverlappingsessionroom`;
CREATE TABLE IF NOT EXISTS `notoverlappingsessionroom` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SessionID` varchar(100) NOT NULL,
  `RoomName` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notoverlappingsessionroom`
--

INSERT INTO `notoverlappingsessionroom` (`ID`, `SessionID`, `RoomName`) VALUES
(4, 'RashiniDinelka-IT3050-ESD-Lecture-Y2.S2.IT.1-120-2', 'A602'),
(5, 'RanjikaKumari-IT2010-OOP-Lecture-Y2.S1.IT.1-120-1', 'B603'),
(6, 'Dr.Nuwan Kodagoda-IT2030-DS-Lecture-Y1.S1.IT.01-120-2', 'opw'),
(7, 'Ms.Kavindi Gunashinghe-IT1020-CS-Tutorial-Y1.S1.IT.01-80-1', 'B403'),
(8, 'Mrs.Madara-IT2340-ITPM-Y2.S2.05-60-2', 'A402');

-- --------------------------------------------------------

--
-- Table structure for table `parallel`
--

DROP TABLE IF EXISTS `parallel`;
CREATE TABLE IF NOT EXISTS `parallel` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Lecturer1` varchar(100) NOT NULL,
  `Lecturer2` varchar(100) NOT NULL,
  `SubjectCode` varchar(10) NOT NULL,
  `Subject` varchar(30) NOT NULL,
  `GroupID` varchar(30) NOT NULL,
  `Tag` varchar(30) NOT NULL,
  `Duration` varchar(30) NOT NULL,
  `sessionID` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parallel`
--

INSERT INTO `parallel` (`ID`, `Lecturer1`, `Lecturer2`, `SubjectCode`, `Subject`, `GroupID`, `Tag`, `Duration`, `sessionID`) VALUES
(11, 'Dr.Nuwan Kodagoda', 'Mr.Perera', 'IT2030', 'OOP', 'Y2.S1.IT.01', 'Lecture', '09:30 - 18:00', 'Dr.Nuwan Kodagoda-IT2030-DS-Lecture-Y1.S1.IT.01-120-2'),
(12, 'Mr. Prasanna S Haddela', '', 'IT3020', 'DS', 'Y3.S1.IT.12', 'Tutorial', '16:00 - 17:00', 'Mr.Prasanna S Haddela-IT3020-DS-Lecture-Y1.S1.IT.12-60-1'),
(13, 'Ms.Shashika Lokuliyana', 'Ms.Sasini', 'IT3010', 'NDM', 'Y3.S1.IT.05', 'Tutorial', '14:30 - 15:30', 'Ms.Shashika Lokuliyana-IT3010-NDM-Tutorial-Y3.S1.IT.05-60-2'),
(14, 'Ms.Shashika Lokuliyana', 'Ms.Sasini', 'IT3010', 'NDM', 'Y3.S1.IT.05', 'Lecture', '11:30 - 14:30', 'Ms.Shashika Lokuliyana-IT3010-NDM-Lecture-Y3.S1.IT.05-60-2'),
(20, 'Mr. Kashyapa Karunarathne', '', 'IT3010', 'NDM', 'Y3.S1.IT.05.01', 'Practical', '10:30 - 12:30', 'Mr.Kashyapa Karunarathne-IT3010-NDM-Practical-Y3.S1.IT.05.01-30-2'),
(21, 'Ms. Thamali Dissanayake', '', 'IT3020', 'DS', 'Y3S1.12.01(IT)	', 'Practical', '08:30 - 10:30', 'Ms.Thamali Dissanayake-IT3020-DS-Practical-Y1.S1.IT.12-30-2'),
(22, 'Mr. Prasanna S Haddela', '', 'IT3020', 'DS', 'Y3.S1.IT.12', 'Lecture', '14:00 - 16:00', 'Mr.Prasanna S Haddela-IT3020-DS-Lecture-Y1.S1.IT.12-60-2'),
(23, 'Ms.Kavindi Gunashinghe', '', 'IT1020', 'CS', 'Y1.S1.IT.01', 'Lecture', '10:30 - 12:30', 'Ms.Kavindi Gunashinghe-IT1020-CS-Lecture-Y1.S1.IT.01-80-2');

-- --------------------------------------------------------

--
-- Table structure for table `parallelsesionroom`
--

DROP TABLE IF EXISTS `parallelsesionroom`;
CREATE TABLE IF NOT EXISTS `parallelsesionroom` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SessionID` varchar(100) NOT NULL,
  `RoomName` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parallelsesionroom`
--

INSERT INTO `parallelsesionroom` (`ID`, `SessionID`, `RoomName`) VALUES
(1, 'HimaliSiriwardana-IT3040-DS-Lecture-Y2.S2.IT.1-120-2', 'B403'),
(2, 'NirmalaSiriwardana-IT3020-DS-Lecture-Y3.S2.SE.1-120-2', 'B503'),
(3, 'Ms.Shashika Lokuliyana-IT3010-NDM-Tutorial-Y3.S1.IT.05-60-2', 'null'),
(4, 'Mr.Kashyapa Karunarathne-IT3010-NDM-Practical-Y3.S1.IT.05.01-30-2', 'A402'),
(5, 'Mr.Prasanna S Haddela-IT3020-DS-Lecture-Y1.S1.IT.12-60-2', 'B403');

-- --------------------------------------------------------

--
-- Table structure for table `prefertime`
--

DROP TABLE IF EXISTS `prefertime`;
CREATE TABLE IF NOT EXISTS `prefertime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sessionID` varchar(100) NOT NULL,
  `preferDay` varchar(20) NOT NULL,
  `preferDate` date NOT NULL,
  `startT` time NOT NULL,
  `endT` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prefertime`
--

INSERT INTO `prefertime` (`id`, `sessionID`, `preferDay`, `preferDate`, `startT`, `endT`) VALUES
(1, 'Dr.Perera-SE-IT8900-Y1S2.IT-2-60', 'Monday', '2021-05-17', '09:30:00', '18:00:00'),
(2, 'Dr.Nuwan Kodagoda-IT-IT9090-Y1S1.IT.1-120', 'Tuesday', '2021-05-10', '03:30:00', '05:30:00'),
(3, 'manudi sathmini-IT1010-OOP-Y2.S2.05-120-2', 'Wednesday', '2021-05-12', '03:00:00', '05:00:00'),
(4, 'Mrs.Madara-IT2340-ITPM-Y2.S2.05-60-2', 'Tuesday', '2021-05-25', '10:30:00', '12:30:00'),
(5, 'Ms.Thamali Dissanayake-IT3020-DS-Practical-Y1.S1.IT.12-30-2', 'Tuesday', '2021-05-25', '10:30:00', '12:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `sessionroom`
--

DROP TABLE IF EXISTS `sessionroom`;
CREATE TABLE IF NOT EXISTS `sessionroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sessionID` varchar(100) NOT NULL,
  `sessionRoom` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessionroom`
--

INSERT INTO `sessionroom` (`id`, `sessionID`, `sessionRoom`) VALUES
(1, 'manudi sathmini-IT1010-OOP-Y2.S2.05-120-2', 'A402');

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
CREATE TABLE IF NOT EXISTS `sessions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Lecturer1` varchar(50) NOT NULL,
  `Lecturer2` varchar(50) NOT NULL,
  `AdditionalLecturer1` varchar(50) NOT NULL,
  `AdditionalLecturer2` varchar(50) NOT NULL,
  `Tag` varchar(20) NOT NULL,
  `GroupID` varchar(30) NOT NULL,
  `SubGroupID` varchar(30) NOT NULL,
  `SubjectCode` varchar(10) NOT NULL,
  `Subject` varchar(50) NOT NULL,
  `NoOfStudents` int(11) NOT NULL,
  `Duration` varchar(10) NOT NULL,
  `sessionID` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`ID`, `Lecturer1`, `Lecturer2`, `AdditionalLecturer1`, `AdditionalLecturer2`, `Tag`, `GroupID`, `SubGroupID`, `SubjectCode`, `Subject`, `NoOfStudents`, `Duration`, `sessionID`) VALUES
(9, 'manudi sathmini', 'Mrs.Madara', 'Sajith Perera', 'Mr.Nuwan Kodagoda', 'Lecture', 'Y2.S2.05', 'null', 'IT1010', 'OOP', 120, '2', 'manudi sathmini-IT1010-OOP-Y2.S2.05-120-2'),
(10, 'Chathurika Pinnaduwa', 'Mr.Perera', 'Sajith Perera', 'Mr.Peries', 'Tute', 'Y2.S2.05', 'null', 'IT1010', 'OOP', 120, '2', 'manudi sathmini-IT1010-OOP-Y2.S2.05-120-2'),
(11, 'Mrs.Madara', 'Mr.Perera', 'Mr.N o', 'Mr.Nuwan Kodagoda', 'Tute', 'Y2.S2.05', 'null', 'IT1010', 'OOP', 120, '1', 'Chathurika Pinnaduwa-IT1010-OOP-Y2.S2.05-120-1'),
(12, 'Mr.Nuwan Kodagoda', 'Mr.Peries', 'Mr.N o', 'Mr.Perera', 'Tute', 'Y2.S2.05', 'null', 'MM0000', 'OO', 60, '2', 'Mr.Nuwan Kodagoda-IT1212-oop-Y2.S2.05.03-60-2'),
(13, 'Mr.Peries', 'Mr.Nuwan Kodagoda', 'Mr.Perera', 'Ms.manudi', 'Lecture', 'Y2.S2.05', 'null', 'IT2340', 'MADD', 120, '2', 'Mr.Peries-IT2340-MADD-Y2.S2.05-120-2'),
(14, 'Sajith Perera', 'Mrs.Madara', 'Mr.Perera', 'Ms.manudi', 'Tute', 'Y2.S2.05', 'null', 'WE9090', 'QW', 120, '2', 'Sajith Perera-WE9090-QW-Y2.S2.05-120-2'),
(15, 'Ms.manudi', 'Sajith Perera', 'Mr.N o', 'Mr.Nuwan Kodagoda', 'Tute', 'Y2.S2.05', 'null', 'WE9090', 'QW', 120, '2', 'Ms.manudi-IT1212-oop-Y2.S2.05-120-2'),
(16, 'Mr.Nayantha Lochana', 'Sajith Perera', 'Mr.Peries', 'Ms.manudi', 'Tute', 'Y1.S2.03', 'null', 'BM0101', 'BM', 120, '1', 'Mr.Nayantha Lochana-BM0101-BM-Y1.S2.03-120-1'),
(17, 'Mrs.Madara', 'Chathurika Pinnaduwa', 'Sajith Perera', 'Ms.manudi', 'Lecture', 'Y2.S2.05', 'Y1.S2.03.06', 'IT2340', 'ITPM', 60, '2', 'Mrs.Madara-IT2340-ITPM-Y2.S2.05-60-2');

-- --------------------------------------------------------

--
-- Table structure for table `studentgroups`
--

DROP TABLE IF EXISTS `studentgroups`;
CREATE TABLE IF NOT EXISTS `studentgroups` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `YearAndSem` varchar(10) NOT NULL,
  `Programme` varchar(20) NOT NULL,
  `GroupNo` varchar(10) NOT NULL,
  `SubGroupNo` varchar(10) NOT NULL,
  `GroupID` varchar(20) NOT NULL,
  `SubGroupID` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentgroups`
--

INSERT INTO `studentgroups` (`ID`, `YearAndSem`, `Programme`, `GroupNo`, `SubGroupNo`, `GroupID`, `SubGroupID`) VALUES
(1, 'Y2.S2', 'IT', '5', '3', 'Y2.S2.05', 'Y2.S2.05.03'),
(2, 'Y1.S2', 'IT', '3', '6', 'Y1.S2.03', 'Y1.S2.03.06'),
(3, 'Y3.S2', 'ISE', '6', '2', 'Y3.S2.06', 'Y3.S2.06.2'),
(4, 'Y2.S2', 'ISE', '6', '2', 'Y2.S2.05', 'Y2.S2.05.04');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
CREATE TABLE IF NOT EXISTS `subjects` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) NOT NULL,
  `semester` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `code` varchar(20) NOT NULL,
  `noOfLecture` int(11) NOT NULL,
  `noOfTute` int(11) NOT NULL,
  `noOfLab` int(11) NOT NULL,
  `noOfEva` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`ID`, `year`, `semester`, `name`, `code`, `noOfLecture`, `noOfTute`, `noOfLab`, `noOfEva`) VALUES
(19, 1, 1, 'OOP', 'IT1010', 2, 2, 2, 1),
(20, 2, 1, 'MS', 'AS1212', 2, 2, 2, 2),
(28, 2, 2, 'SSS', 'SS1111', 2, 2, 2, 2),
(31, 2, 2, 'MADD', 'IT2340', 2, 2, 2, 2),
(32, 1, 1, 'Accounting', 'BM1200', 2, 1, 2, 1),
(33, 2, 2, 'BM', 'IT1111', 2, 1, 2, 1),
(41, 1, 1, 'IT', 'IT1919', 1, 1, 1, 1),
(43, 1, 1, 'QQQ', 'QQ1223', 2, 2, 1, 1),
(46, 2, 2, 'OO', 'MM0000', 1, 1, 1, 1),
(49, 3, 2, 'BM', 'BM0101', 1, 1, 1, 1),
(50, 2, 1, 'IR', 'IR1900', 1, 1, 1, 1),
(51, 1, 2, 'QW', 'WE9090', 1, 1, 1, 1),
(53, 2, 2, 'IT', 'IT0002', 2, 2, 1, 1),
(54, 3, 1, 'PAF', 'IT3030', 2, 1, 2, 1),
(55, 3, 1, 'NDM', 'IT3010', 2, 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE IF NOT EXISTS `tags` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TagName` varchar(30) NOT NULL,
  `RelatedTag` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`ID`, `TagName`, `RelatedTag`) VALUES
(1, 'Tute', 'Tute'),
(2, 'Lecture', 'Lecture'),
(4, 'Practical', 'Practical'),
(5, 'Tute', 'Tutorial');

-- --------------------------------------------------------

--
-- Table structure for table `tbllocation`
--

DROP TABLE IF EXISTS `tbllocation`;
CREATE TABLE IF NOT EXISTS `tbllocation` (
  `LocationID` int(11) NOT NULL AUTO_INCREMENT,
  `BuildingName` varchar(100) NOT NULL,
  `RoomName` varchar(100) NOT NULL,
  `RoomType` varchar(100) NOT NULL,
  `Capacity` int(11) NOT NULL,
  PRIMARY KEY (`LocationID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbllocation`
--

INSERT INTO `tbllocation` (`LocationID`, `BuildingName`, `RoomName`, `RoomType`, `Capacity`) VALUES
(18, 'A', 'A302', 'Lab', 60),
(19, 'A', 'A402', 'Lab', 60),
(20, 'A', 'A502', 'Lab', 100),
(21, 'B', 'B403', 'Lecture Hall', 120),
(22, 'B', 'B405', 'Lecture Hall', 120),
(23, 'A', '602', 'Lab', 100);

-- --------------------------------------------------------

--
-- Table structure for table `ws`
--

DROP TABLE IF EXISTS `ws`;
CREATE TABLE IF NOT EXISTS `ws` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numberOfDays` int(11) NOT NULL,
  `days` text NOT NULL,
  `startT` time NOT NULL,
  `endT` time NOT NULL,
  `slot` int(11) NOT NULL,
  `breakcount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ws`
--

INSERT INTO `ws` (`id`, `numberOfDays`, `days`, `startT`, `endT`, `slot`, `breakcount`) VALUES
(1, 2, 'monday tuesday', '08:30:00', '17:30:00', 30, 4);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `addsessionroom`
--
ALTER TABLE `addsessionroom`
  ADD CONSTRAINT `addsessionroom_ibfk_1` FOREIGN KEY (`availbleSessionID`) REFERENCES `notavailablelecture` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
