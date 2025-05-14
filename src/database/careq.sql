-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 04, 2025 at 12:19 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `careq`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appointment_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(10) NOT NULL,
  `notes` text NOT NULL,
  `patient_id` int(11) NOT NULL,
  `appointment_status` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appointment_id`, `doctor_id`, `date`, `time`, `notes`, `patient_id`, `appointment_status`) VALUES
(7, 18, '2025-05-05', '09:30', 'i miss u', 14, 'Accepted'),
(8, 23, '2025-05-05', '15:00', 'kalibanga', 14, 'Accepted');

-- --------------------------------------------------------

--
-- Table structure for table `diagnosis`
--

CREATE TABLE `diagnosis` (
  `diagnosis_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `doctor` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `d_diagnosis` int(11) NOT NULL,
  `notes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `log_id` int(11) NOT NULL,
  `u_id` int(11) DEFAULT NULL,
  `action` varchar(255) NOT NULL,
  `action_date` text NOT NULL DEFAULT current_timestamp(),
  `action_time` text NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`log_id`, `u_id`, `action`, `action_date`, `action_time`) VALUES
(1, 1, 'Deleted an account', '2025-05-02', '20:59:15.232'),
(2, 1, 'Activated an account', '2025-05-02', '21:08'),
(3, 1, 'Deleted an account', '2025-05-02', '21:08'),
(4, 1, 'Deleted an account', '2025-05-02', '21:08'),
(5, 1, 'Updated profile photo', '2025-05-02', '21:08'),
(6, 1, 'Activated an account', '2025-05-03', '09:38'),
(7, 18, 'Changed password', '2025-05-03', '13:10'),
(8, 18, 'Accepted an appointment', '2025-05-03', '13:53'),
(9, 1, 'Activated an account', '2025-05-03', '15:39'),
(10, 1, 'Activated an account', '2025-05-03', '15:41'),
(11, 23, 'Updated profile photo', '2025-05-03', '15:42:34.458'),
(12, 21, 'Changed password', '2025-05-04', '10:00'),
(13, 21, 'Made an appointment', '2025-05-04', '10:00'),
(14, 18, 'Accepted an appointment', '2025-05-04', '10:01'),
(15, 21, 'Made an appointment', '2025-05-04', '11:06'),
(16, 18, 'Accepted an appointment', '2025-05-04', '11:13'),
(17, 14, 'Made an appointment', '2025-05-04', '12:21'),
(18, 14, 'Made an appointment', '2025-05-04', '12:22'),
(19, 14, 'Made an appointment', '2025-05-04', '12:24'),
(20, 14, 'Made an appointment', '2025-05-04', '12:25'),
(21, 18, 'Accepted an appointment', '2025-05-04', '12:33'),
(22, 23, 'Removed profile photo', '2025-05-04', '17:05'),
(23, 23, 'Updated profile photo', '2025-05-04', '17:05:16.533'),
(24, 14, 'Made an appointment', '2025-05-04', '17:09'),
(25, 23, 'Accepted an appointment', '2025-05-04', '17:09');

-- --------------------------------------------------------

--
-- Table structure for table `otps`
--

CREATE TABLE `otps` (
  `u_id` varchar(10) NOT NULL,
  `otp_code` varchar(6) NOT NULL,
  `otp_duration` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `otps`
--

INSERT INTO `otps` (`u_id`, `otp_code`, `otp_duration`) VALUES
('21', '572840', '2025-05-03 01:44:07'),
('21', '376208', '2025-05-03 02:31:05'),
('21', '847813', '2025-05-03 02:31:28'),
('21', '722490', '2025-05-03 02:40:35'),
('21', '412322', '2025-05-03 02:43:31'),
('21', '373031', '2025-05-03 02:45:33'),
('21', '213910', '2025-05-03 02:47:32'),
('21', '318177', '2025-05-03 03:23:33'),
('18', '365091', '2025-05-03 05:08:24'),
('18', '541385', '2025-05-03 05:09:45'),
('18', '963011', '2025-05-03 05:10:09'),
('18', '768920', '2025-05-03 05:10:43'),
('21', '903099', '2025-05-03 05:17:44'),
('18', '842259', '2025-05-03 05:21:28'),
('22', '437201', '2025-05-03 07:38:43'),
('22', '904748', '2025-05-03 07:42:53'),
('21', '953844', '2025-05-04 02:04:34');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `u_id` int(11) NOT NULL,
  `u_fname` varchar(255) NOT NULL,
  `u_lname` varchar(255) NOT NULL,
  `u_email` varchar(255) NOT NULL,
  `u_pnum` varchar(255) NOT NULL,
  `sex` text NOT NULL,
  `age` varchar(10) NOT NULL,
  `u_user` varchar(255) NOT NULL,
  `u_pass` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `u_pfp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`u_id`, `u_fname`, `u_lname`, `u_email`, `u_pnum`, `sex`, `age`, `u_user`, `u_pass`, `type`, `status`, `u_pfp`) VALUES
(1, 'joshua', 'gwapo', 'gwapoolok321@gmail.com', '09090909090', '', '', 'admin', 'JAvlGPq9JyTdtvBO6x2llnRI1+gxwIyPqCKAn3THIKk=', 'administrator', 'active', 'src/user_img/2e83c67ca33455cfacf66717c923eefc.jpg'),
(14, 'Louigie', 'Borja', 'borjak@gmail.com', '12345678941', '', '', 'pacurebota123', 'bItZVbKg+/xCEuTTrgHxsHkZXfb2acWj7NdFn26kNgY=', 'Patient', 'Active', 'src/user_img/borjak.jpg'),
(17, 'Bombardino', 'Crocodilo', 'bombardo_c@gmail.com', '09325648245', '', '', 'bombardino', 'S/oy7zM+IizvxEWqQoikMDo53s47de3rVdBbFWJE4ew=', 'Patient', 'Active', 'src/user_img/Bombardiro_crocodilo_cover.jpg'),
(18, 'Trixie', 'Mae', 'raikonnenwhahah@gmail.com', '09875757654', '', '', 'trixielovenorrie', 'n4HRZCTdUMYTsAuYPmd1V1hDnQ5xBb2KhhAHqE3fdbU=', 'Doctor', 'Active', NULL),
(19, 'Joshua', 'Gwapo', 'joshuagwapo@gmail.com', '09090945124', '', '', 'joshuagwaps', 'goAXAbp531py6wn5mqsFydzuNz5sqLzXnnTJfz0uGQM=', 'Patient', 'Active', NULL),
(20, 'Norrie', 'Ugly', 'norrie@gmail.com', '09451265845', '', '', 'norrie', 'KecLiTYwylt2E2ESWrV7Ysd7Vgae2DiT36m58r3Sl7w=', 'Patient', 'Active', NULL),
(21, 'Arl Joshua', 'Sison', 'arljoshua9@gmail.com', '09912191641', '', '', 'goodboyarl', 'goAXAbp531py6wn5mqsFydzuNz5sqLzXnnTJfz0uGQM=', 'Patient', 'Active', NULL),
(22, 'Norrie', 'Pangit', 'jopedregosa1980@gmail.com', '09657654567', 'Female', '20', 'norriepangit', 'KecLiTYwylt2E2ESWrV7Ysd7Vgae2DiT36m58r3Sl7w=', 'Patient', 'Active', NULL),
(23, 'Abdul', 'Jamal', 'arlverstappen911@gmail.com', '09456821595', 'Male', '45', 'abduljamal', 'Al2vBYAyDWFhEGr4I78yTL5GDPwuOBEna9QFv7Iv4sE=', 'Doctor', 'Active', 'src/user_img/Friendly Doctor in Clean Whites.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `doctor_id` (`doctor_id`);

--
-- Indexes for table `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD PRIMARY KEY (`diagnosis_id`),
  ADD KEY `appointment_id` (`appointment_id`),
  ADD KEY `doctor` (`doctor`),
  ADD KEY `patient_id` (`patient_id`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `u_id` (`u_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `diagnosis`
--
ALTER TABLE `diagnosis`
  MODIFY `diagnosis_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD CONSTRAINT `diagnosis_ibfk_1` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`appointment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `diagnosis_ibfk_2` FOREIGN KEY (`doctor`) REFERENCES `appointments` (`doctor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `diagnosis_ibfk_3` FOREIGN KEY (`patient_id`) REFERENCES `appointments` (`patient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
