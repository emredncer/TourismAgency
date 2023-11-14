-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 14 Kas 2023, 15:39:38
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `TourismAgency`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `BoardType`
--

CREATE TABLE `BoardType` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `name` varchar(999) COLLATE utf8mb4_general_ci NOT NULL,
  `priceRate` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `BoardType`
--

INSERT INTO `BoardType` (`id`, `hotel_id`, `name`, `priceRate`) VALUES
(1, 3, 'asd', 1.1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Hotel`
--

CREATE TABLE `Hotel` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `district` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `adress` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `star` tinyint NOT NULL,
  `boardType` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hasFreeParking` tinyint(1) NOT NULL,
  `hasWifi` tinyint(1) NOT NULL,
  `hasSwimmingPool` tinyint(1) NOT NULL,
  `hasGym` tinyint(1) NOT NULL,
  `hasHotelConcierge` tinyint(1) NOT NULL,
  `hasRoomService` tinyint(1) NOT NULL,
  `hasSpa` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `Hotel`
--

INSERT INTO `Hotel` (`id`, `name`, `city`, `district`, `adress`, `email`, `phone`, `star`, `boardType`, `hasFreeParking`, `hasWifi`, `hasSwimmingPool`, `hasGym`, `hasHotelConcierge`, `hasRoomService`, `hasSpa`) VALUES
(3, 'TestName', 'ankara', 'district', 'testAdres', 'memin gmail', '+90555', 2, 'com.TourismAgencySystem.Operations.HotelOperations', 1, 1, 0, 0, 1, 0, 0),
(4, 'Yeni isim', 'ankara', 'district', 'testAdres', 'memin gmail', '+90555', 2, 'tipler', 1, 1, 0, 0, 1, 0, 0),
(5, 'TestName', 'ankara', 'district', 'testAdres', 'memin gmail', '+90555', 2, 'com.TourismAgencySystem.Operations.HotelOperations', 1, 1, 0, 0, 1, 0, 0),
(6, 'TestName', 'ankara', 'district', 'testAdres', 'memin gmail', '+90555', 2, 'com.TourismAgencySystem.Operations.HotelOperations', 1, 1, 0, 0, 1, 0, 0),
(7, 'Kötü otel', 'Hakkari', 'Yüksekova', 'EYP sokak, patlayıcı mahallesi', 'denemem', '123555732', 3, '', 1, 1, 0, 0, 0, 1, 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Reservation`
--

CREATE TABLE `Reservation` (
  `id` int NOT NULL,
  `hotel_id` int DEFAULT NULL,
  `boardType_id` int DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `season_id` int DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `childCount` int DEFAULT NULL,
  `adultCount` int DEFAULT NULL,
  `checkInDate` date DEFAULT NULL,
  `checkOutDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `Reservation`
--

INSERT INTO `Reservation` (`id`, `hotel_id`, `boardType_id`, `room_id`, `season_id`, `totalPrice`, `childCount`, `adultCount`, `checkInDate`, `checkOutDate`) VALUES
(4, 3, 1, 2, 1, 6174, 2, 2, '3923-02-01', '3923-02-05'),
(5, 3, 1, 2, 1, 6174, 2, 2, '2023-02-01', '2023-02-05'),
(6, 3, 1, 2, 1, 6174, 2, 2, '2023-02-01', '2023-02-05');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Room`
--

CREATE TABLE `Room` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `stock` int NOT NULL,
  `hotel_id` int NOT NULL,
  `bedCount` int NOT NULL,
  `hasTV` tinyint(1) NOT NULL,
  `hasMinibar` tinyint(1) NOT NULL,
  `hasSafeBox` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `Room`
--

INSERT INTO `Room` (`id`, `name`, `stock`, `hotel_id`, `bedCount`, `hasTV`, `hasMinibar`, `hasSafeBox`) VALUES
(2, 'testRoom3', 20, 3, 3, 1, 1, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Season`
--

CREATE TABLE `Season` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL,
  `seasonStartDate` date NOT NULL,
  `seasonEndingDate` date NOT NULL,
  `adultPrice` double NOT NULL,
  `childPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `Season`
--

INSERT INTO `Season` (`id`, `name`, `hotel_id`, `seasonStartDate`, `seasonEndingDate`, `adultPrice`, `childPrice`) VALUES
(1, 'testSeason', 3, '2023-01-01', '2023-01-05', 500, 200),
(2, 'Test1', 3, '3925-06-05', '3925-06-10', 10, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `User`
--

CREATE TABLE `User` (
  `id` int NOT NULL,
  `uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name_lastname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `User`
--

INSERT INTO `User` (`id`, `uname`, `pass`, `name_lastname`, `role`) VALUES
(1, 'abdulrezzak', 'yanyanagezen', 'abdulrezzak yanyanagezen', 'admin'),
(2, 'edincer', '1234', 'emre dinçer', 'user'),
(20, 'user', 'user', 'user', 'user'),
(22, 'memin', '123', 'memin', 'user'),
(23, 'newuser123', '123123', 'New User Full Name', 'user');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `BoardType`
--
ALTER TABLE `BoardType`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Hotel`
--
ALTER TABLE `Hotel`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Reservation`
--
ALTER TABLE `Reservation`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Room`
--
ALTER TABLE `Room`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Season`
--
ALTER TABLE `Season`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `BoardType`
--
ALTER TABLE `BoardType`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `Hotel`
--
ALTER TABLE `Hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `Reservation`
--
ALTER TABLE `Reservation`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `Room`
--
ALTER TABLE `Room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `Season`
--
ALTER TABLE `Season`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `User`
--
ALTER TABLE `User`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
