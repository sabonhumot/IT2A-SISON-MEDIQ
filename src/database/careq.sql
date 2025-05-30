-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2025 at 09:12 AM
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
(8, 23, '2025-05-05', '15:00', 'kalibanga', 14, 'Accepted'),
(9, 24, '2025-05-29', '10:00', 'i miss u', 21, 'Accepted'),
(10, 26, '2025-05-30', '17:00', 'sakit tiyan', 25, 'Accepted'),
(12, 23, '2025-05-30', '12:00', 'I have a sharp pain on the lower part of my abdomen and I am vomiting blood', 21, 'Accepted'),
(13, 24, '2025-06-01', '09:00', 'asdads', 21, 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `diagnosis`
--

CREATE TABLE `diagnosis` (
  `diagnosis_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `doctor` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `d_diagnosis` varchar(255) NOT NULL,
  `notes` varchar(9999) DEFAULT NULL,
  `diagnosis_date` date NOT NULL DEFAULT current_timestamp(),
  `diagnosis_time` time NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `diagnosis`
--

INSERT INTO `diagnosis` (`diagnosis_id`, `appointment_id`, `doctor`, `patient_id`, `d_diagnosis`, `notes`, `diagnosis_date`, `diagnosis_time`) VALUES
(2, 10, 26, 25, 'suka kalibanga ', 'inom plajil', '2025-05-29', '17:00:15'),
(4, 12, 23, 21, 'Acute Diverticulitis with Suspected Gastrointestinal Bleeding (Hematemesis)', 'Patient complains of sharp, persistent pain localized in the lower left quadrant of the abdomen, accompanied by episodes of vomiting blood (hematemesis). Vital signs show mild tachycardia. No previous history of gastrointestinal issues reported. On physical examination, tenderness and mild guarding were noted in the lower left abdomen. Differential diagnosis includes acute diverticulitis, peptic ulcer with upper GI bleed, or colorectal pathology. Immediate laboratory tests and abdominal imaging (CT scan) are recommended. The patient is advised to be admitted for observation, IV fluids, and further diagnostic work-up including endoscopy.  ', '2025-05-30', '11:50:18');

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
(25, 23, 'Accepted an appointment', '2025-05-04', '17:09'),
(26, 23, 'Accepted an appointment', '2025-05-29', '06:49'),
(27, 1, 'Activated an account', '2025-05-29', '07:44'),
(28, 21, 'Made an appointment', '2025-05-29', '09:43'),
(29, 24, 'Accepted an appointment', '2025-05-29', '09:43'),
(30, 24, 'Made a diagnosis', '2025-05-29', '10:11'),
(31, 24, 'Made a diagnosis', '2025-05-29', '10:24'),
(32, 24, 'Made a diagnosis', '2025-05-29', '10:27'),
(33, 24, 'Made a diagnosis', '2025-05-29', '10:28'),
(34, 24, 'Made a diagnosis', '2025-05-29', '10:28'),
(35, 1, 'Activated an account', '2025-05-29', '16:57'),
(36, 1, 'Activated an account', '2025-05-29', '16:57'),
(37, 25, 'Made an appointment', '2025-05-29', '16:59'),
(38, 26, 'Accepted an appointment', '2025-05-29', '16:59'),
(39, 26, 'Made a diagnosis', '2025-05-29', '17:00'),
(40, 23, 'Made a diagnosis', '2025-05-30', '08:36'),
(41, 21, 'Made an appointment', '2025-05-30', '09:55'),
(42, 23, 'Accepted an appointment', '2025-05-30', '09:57'),
(43, 21, 'Made an appointment', '2025-05-30', '11:23'),
(44, 23, 'Accepted an appointment', '2025-05-30', '11:26'),
(45, 21, 'Made an appointment', '2025-05-30', '11:28'),
(46, 23, 'Accepted an appointment', '2025-05-30', '11:45'),
(47, 23, 'Made a diagnosis', '2025-05-30', '11:48'),
(48, 23, 'Made a diagnosis', '2025-05-30', '11:50');

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
  `specialty` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `u_pfp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`u_id`, `u_fname`, `u_lname`, `u_email`, `u_pnum`, `sex`, `age`, `u_user`, `u_pass`, `type`, `specialty`, `status`, `u_pfp`) VALUES
(1, 'joshua', 'gwapo', 'gwapoolok321@gmail.com', '09090909090', '', '', 'admin', 'JAvlGPq9JyTdtvBO6x2llnRI1+gxwIyPqCKAn3THIKk=', 'administrator', NULL, 'active', 'src/user_img/2e83c67ca33455cfacf66717c923eefc.jpg'),
(14, 'Louigie', 'Borja', 'borjak@gmail.com', '12345678941', 'Male', '24', 'pacurebota123', 'bItZVbKg+/xCEuTTrgHxsHkZXfb2acWj7NdFn26kNgY=', 'Patient', NULL, 'Active', 'src/user_img/borjak.jpg'),
(17, 'Bombardino', 'Crocodilo', 'bombardo_c@gmail.com', '09325648245', '', '', 'bombardino', 'S/oy7zM+IizvxEWqQoikMDo53s47de3rVdBbFWJE4ew=', 'Patient', NULL, 'Active', 'src/user_img/Bombardiro_crocodilo_cover.jpg'),
(18, 'Trixie', 'Mae', 'raikonnenwhahah@gmail.com', '09875757654', '', '', 'trixielovenorrie', 'n4HRZCTdUMYTsAuYPmd1V1hDnQ5xBb2KhhAHqE3fdbU=', 'Doctor', 'Optomology', 'Active', NULL),
(19, 'Joshua', 'Gwapo', 'joshuagwapo@gmail.com', '09090945124', '', '', 'joshuagwaps', 'goAXAbp531py6wn5mqsFydzuNz5sqLzXnnTJfz0uGQM=', 'Patient', NULL, 'Active', NULL),
(20, 'Norrie', 'Ugly', 'norrie@gmail.com', '09451265845', '', '', 'norrie', 'KecLiTYwylt2E2ESWrV7Ysd7Vgae2DiT36m58r3Sl7w=', 'Patient', NULL, 'Active', NULL),
(21, 'Arl Joshua', 'Sison', 'arljoshua9@gmail.com', '09912191641', 'Male', '20', 'goodboyarl', 'goAXAbp531py6wn5mqsFydzuNz5sqLzXnnTJfz0uGQM=', 'Patient', NULL, 'Active', NULL),
(22, 'Norrie', 'Pangit', 'jopedregosa1980@gmail.com', '09657654567', 'Female', '20', 'norriepangit', 'KecLiTYwylt2E2ESWrV7Ysd7Vgae2DiT36m58r3Sl7w=', 'Patient', NULL, 'Active', NULL),
(23, 'Abdul', 'Jamal', 'arlverstappen911@gmail.com', '09456821595', 'Male', '45', 'abduljamal', 'Al2vBYAyDWFhEGr4I78yTL5GDPwuOBEna9QFv7Iv4sE=', 'Doctor', 'Internal Medicine', 'Active', 'src/user_img/Friendly Doctor in Clean Whites.png'),
(24, 'Lillix', 'Zyrese', 'zyrese@gmail.com', '09912191641', 'Female', '20', 'lixzyrese', 'GJNeiwA5Vgcl0D9uDQAJK3XjWGannPjZxm1jdsPelng=', 'Doctor', 'Pediatrics', 'Active', NULL),
(25, 'Jocelyn', 'Pedregosa', 'jopedregosa@gmail.com', '09154789512', 'Female', '44', 'jopedregosa', 'p320u5aJwLQSvAEPWe20bTXQZkWjW2BeuTwlfOhnHus=', 'Patient', '(Choose specialty)', 'Active', NULL),
(26, 'Arnel', 'Sison', 'arnel@gmail.com', '09568426789', 'Male', '48', 'lingz16', 'zD8xFRc05K5J/QbCm2hvCsq/ketQPDvFTgI6K/rHkjo=', 'Doctor', 'Surgery', 'Active', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `appointments_ibfk_2` (`patient_id`),
  ADD KEY `appointments_ibfk_3` (`doctor_id`);

--
-- Indexes for table `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD PRIMARY KEY (`diagnosis_id`),
  ADD KEY `diagnosis_ibfk_1` (`appointment_id`),
  ADD KEY `diagnosis_ibfk_2` (`doctor`),
  ADD KEY `diagnosis_ibfk_3` (`patient_id`);

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
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `diagnosis`
--
ALTER TABLE `diagnosis`
  MODIFY `diagnosis_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD CONSTRAINT `diagnosis_ibfk_1` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`appointment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `diagnosis_ibfk_2` FOREIGN KEY (`doctor`) REFERENCES `appointments` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `diagnosis_ibfk_3` FOREIGN KEY (`patient_id`) REFERENCES `appointments` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
