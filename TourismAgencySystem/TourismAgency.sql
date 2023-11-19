-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 19 Kas 2023, 14:23:31
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
(1, 3, 'Yarım Pansiyon', 1.1),
(3, 4, 'Ultra Herşey Dahil', 3.2),
(4, 4, 'Yarım Pansiyon', 2.1),
(5, 7, 'Sadece Yatak', 1),
(6, 3, 'Herşey Dahil', 3.1),
(7, 5, 'Tam Pansiyon', 2.4),
(8, 3, 'Tam Pansiyon Plus', 4.1),
(9, 10, 'Tam Pansiyon', 1.8),
(11, 10, 'Tam Pansiyon Plus', 3.2),
(12, 10, 'Deluxe', 3.5),
(13, 3, 'Sadece Yatak', 1.3),
(14, 3, 'Tam Pansiyon', 3.1),
(16, 4, 'Herşey Dahil', 2.1),
(17, 7, 'Sadece Yatak', 1.1),
(20, 10, 'Tam Pansiyon Plus', 1.6);

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
(3, 'Park kadayıf Hotel', 'Izmir', 'Bornova', 'kadayıf sokak. no 55', 'emo_emo@gmail.com', '+902213', 5, '', 1, 1, 0, 1, 1, 1, 1),
(4, 'Yeni isim', 'ankara', 'Çankaya', 'testAdres', 'emoemo', '+90555', 2, '', 0, 0, 0, 0, 0, 0, 0),
(5, 'TestName', 'ankara', 'district', 'testAdres', 'edoooool', '+90555', 2, '', 0, 1, 0, 1, 0, 0, 0),
(7, 'Kötü otel', 'Hakkari', 'Yüksekova', 'EYP sokak, patlayıcı mahallesi', 'degisti', '123555732', 5, '', 0, 0, 0, 1, 0, 0, 0),
(10, 'Ramada hotel', 'Ankara', 'Çankaya', 'mekansızlar caddesi', 'rmd_@gmail.cort', '+90000', 6, '', 1, 1, 0, 1, 1, 0, 1),
(14, 'NAMIK HOTEL', 'Zonguldak', 'Ereğli', 'Zorlu yollar mah', '412wq7', '+923145t43', 4, '', 0, 0, 0, 1, 1, 1, 0);

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
  `checkOutDate` date DEFAULT NULL,
  `TCKNo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `NameLastname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `Reservation`
--

INSERT INTO `Reservation` (`id`, `hotel_id`, `boardType_id`, `room_id`, `season_id`, `totalPrice`, `childCount`, `adultCount`, `checkInDate`, `checkOutDate`, `TCKNo`, `NameLastname`) VALUES
(4, 10, 1, 5, 5, 7854.000000000001, 1, 1, '2023-09-01', '2023-09-05', '3123124124', 'Khalid Khasmiri'),
(5, 3, 1, 2, 1, 6174, 2, 2, '2023-02-01', '2023-02-05', '4122646154', 'Irgat Yorgunoğlu'),
(6, 3, 1, 2, 1, 6468.000000000001, 2, 2, '2023-02-01', '2023-02-05', '1243257446', 'Cahit Kharavitar'),
(8, 3, 1, 2, 1, 5544, 1, 2, '2022-12-31', '2023-01-04', '342785725', 'Hızır babaa'),
(10, 7, 12, 5, 8, 27930, 1, 0, '2023-04-30', '2023-05-04', '123455765', 'Orhan Veli'),
(11, 10, 3, 7, 3, 8064, 2, 1, '2023-01-31', '2023-02-01', '12345678', 'emre'),
(14, 4, 17, 8, 8, 6352.500000000001, 1, 1, '2023-05-04', '2023-05-05', '123456', 'deneme');

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
(2, 'Sadece Yatak', 18, 3, 1, 0, 0, 0),
(5, 'Deluxe oda', 12, 10, 3, 1, 1, 1),
(7, 'Normal Oda', 5, 7, 2, 1, 1, 1),
(8, 'Sadece Yatak', 3, 5, 1, 1, 0, 1);

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
(1, 'Normal', 3, '2023-03-05', '2023-03-01', 500, 200),
(3, 'Normal', 3, '2023-03-04', '2030-07-04', 1200, 600),
(5, 'Kış', 2, '2023-11-21', '2024-03-30', 1200, 500),
(6, 'Yaz', 2, '2024-05-14', '2025-08-30', 3000, 1450),
(7, 'Kış', 10, '2023-11-20', '2024-04-01', 2200, 1100),
(8, 'Yaz', 10, '2023-05-03', '2023-10-01', 3600, 1900),
(9, 'Yaz', 14, '2023-04-30', '2023-07-31', 3600, 1900),
(10, 'Kış', 14, '2022-12-31', '2023-03-31', 2600, 1200);

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
(23, 'newuser123', '123123', 'New User Full Name', 'user'),
(26, 'deneme', 'user', 'yeni user', 'user');

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
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Tablo için AUTO_INCREMENT değeri `Hotel`
--
ALTER TABLE `Hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `Reservation`
--
ALTER TABLE `Reservation`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Tablo için AUTO_INCREMENT değeri `Room`
--
ALTER TABLE `Room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `Season`
--
ALTER TABLE `Season`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `User`
--
ALTER TABLE `User`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
