-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: qlbs
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chitietphieunhap`
--

DROP TABLE IF EXISTS `chitietphieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietphieunhap` (
  `MAPN` char(10) NOT NULL,
  `MASACH` char(10) NOT NULL,
  `GIANHAP` double NOT NULL,
  `SOLUONG` int NOT NULL,
  `TONGTIEN` double NOT NULL,
  PRIMARY KEY (`MAPN`,`MASACH`),
  KEY `MASACH` (`MASACH`),
  CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`MAPN`) REFERENCES `phieunhap` (`MAPN`),
  CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`MASACH`) REFERENCES `sach` (`MASACH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietphieunhap`
--

LOCK TABLES `chitietphieunhap` WRITE;
/*!40000 ALTER TABLE `chitietphieunhap` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitietphieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietquyen`
--

DROP TABLE IF EXISTS `chitietquyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietquyen` (
  `MAQUYEN` char(10) NOT NULL,
  `MACHUCNANG` char(10) NOT NULL,
  `HANHDONG` varchar(50) NOT NULL,
  PRIMARY KEY (`MAQUYEN`,`MACHUCNANG`),
  KEY `MACHUCNANG` (`MACHUCNANG`),
  CONSTRAINT `chitietquyen_ibfk_1` FOREIGN KEY (`MAQUYEN`) REFERENCES `quyen` (`MAQUYEN`),
  CONSTRAINT `chitietquyen_ibfk_2` FOREIGN KEY (`MACHUCNANG`) REFERENCES `chucnang` (`MACHUCNANG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietquyen`
--

LOCK TABLES `chitietquyen` WRITE;
/*!40000 ALTER TABLE `chitietquyen` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitietquyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chucnang`
--

DROP TABLE IF EXISTS `chucnang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chucnang` (
  `MACHUCNANG` char(10) NOT NULL,
  `TENCHUCNANG` varchar(50) NOT NULL,
  PRIMARY KEY (`MACHUCNANG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chucnang`
--

LOCK TABLES `chucnang` WRITE;
/*!40000 ALTER TABLE `chucnang` DISABLE KEYS */;
/*!40000 ALTER TABLE `chucnang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cthoadon`
--

DROP TABLE IF EXISTS `cthoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cthoadon` (
  `SOHD` char(10) NOT NULL,
  `MASACH` char(10) NOT NULL,
  `SOLUONG` int NOT NULL,
  `DONGIA` double NOT NULL,
  PRIMARY KEY (`MASACH`,`SOHD`),
  KEY `SOHD` (`SOHD`),
  CONSTRAINT `cthoadon_ibfk_1` FOREIGN KEY (`MASACH`) REFERENCES `sach` (`MASACH`),
  CONSTRAINT `cthoadon_ibfk_2` FOREIGN KEY (`SOHD`) REFERENCES `hoadon` (`SOHD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cthoadon`
--

LOCK TABLES `cthoadon` WRITE;
/*!40000 ALTER TABLE `cthoadon` DISABLE KEYS */;
/*!40000 ALTER TABLE `cthoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctsachloai`
--

DROP TABLE IF EXISTS `ctsachloai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctsachloai` (
  `MASACH` char(10) NOT NULL,
  `MALOAI` char(10) NOT NULL,
  PRIMARY KEY (`MASACH`,`MALOAI`),
  KEY `MALOAI` (`MALOAI`),
  CONSTRAINT `ctsachloai_ibfk_1` FOREIGN KEY (`MASACH`) REFERENCES `sach` (`MASACH`),
  CONSTRAINT `ctsachloai_ibfk_2` FOREIGN KEY (`MALOAI`) REFERENCES `loai` (`MALOAI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctsachloai`
--

LOCK TABLES `ctsachloai` WRITE;
/*!40000 ALTER TABLE `ctsachloai` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctsachloai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctsachtacgia`
--

DROP TABLE IF EXISTS `ctsachtacgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctsachtacgia` (
  `MASACH` char(10) NOT NULL,
  `MATG` char(10) NOT NULL,
  PRIMARY KEY (`MASACH`,`MATG`),
  KEY `MATG` (`MATG`),
  CONSTRAINT `ctsachtacgia_ibfk_1` FOREIGN KEY (`MATG`) REFERENCES `tacgia` (`MATG`),
  CONSTRAINT `ctsachtacgia_ibfk_2` FOREIGN KEY (`MASACH`) REFERENCES `sach` (`MASACH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctsachtacgia`
--

LOCK TABLES `ctsachtacgia` WRITE;
/*!40000 ALTER TABLE `ctsachtacgia` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctsachtacgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hinh`
--

DROP TABLE IF EXISTS `hinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hinh` (
  `MASACH` char(10) NOT NULL,
  `TENHINH` char(100) NOT NULL,
  PRIMARY KEY (`MASACH`,`TENHINH`),
  CONSTRAINT `hinh_ibfk_1` FOREIGN KEY (`MASACH`) REFERENCES `sach` (`MASACH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hinh`
--

LOCK TABLES `hinh` WRITE;
/*!40000 ALTER TABLE `hinh` DISABLE KEYS */;
/*!40000 ALTER TABLE `hinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `SOHD` char(10) NOT NULL,
  `MAKH` char(10) NOT NULL,
  `TENDN` char(50) NOT NULL,
  `THOIGIAN` time NOT NULL,
  `NGAYHD` date NOT NULL,
  `TIENGIAMGIA` double NOT NULL,
  `TONGTIEN` double NOT NULL,
  `TRANGTHAI` int NOT NULL,
  PRIMARY KEY (`SOHD`),
  KEY `MAKH` (`MAKH`),
  KEY `TENDN` (`TENDN`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MAKH`) REFERENCES `khachhang` (`MAKH`),
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`TENDN`) REFERENCES `taikhoan` (`TENDN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MAKH` char(10) NOT NULL,
  `TENKH` varchar(100) NOT NULL,
  `SDT` char(10) NOT NULL,
  `DIEMTICHLUY` double NOT NULL,
  `TRANGTHAI` int NOT NULL,
  PRIMARY KEY (`MAKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai`
--

DROP TABLE IF EXISTS `loai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loai` (
  `MALOAI` char(10) NOT NULL,
  `TENLOAI` varchar(50) NOT NULL,
  `TRANGTHAI` int NOT NULL,
  PRIMARY KEY (`MALOAI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai`
--

LOCK TABLES `loai` WRITE;
/*!40000 ALTER TABLE `loai` DISABLE KEYS */;
/*!40000 ALTER TABLE `loai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `MANCC` char(10) NOT NULL,
  `TENNCC` varchar(100) NOT NULL,
  `TRANGTHAI` int NOT NULL,
  PRIMARY KEY (`MANCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhap` (
  `MAPN` char(10) NOT NULL,
  `MANCC` char(10) NOT NULL,
  `TENDN` char(50) NOT NULL,
  `NGAYNHAP` date NOT NULL,
  `TONGTIEN` double NOT NULL,
  `TRANGTHAI` int NOT NULL,
  PRIMARY KEY (`MAPN`),
  KEY `MANCC` (`MANCC`),
  KEY `TENDN` (`TENDN`),
  CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MANCC`) REFERENCES `nhacungcap` (`MANCC`),
  CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`TENDN`) REFERENCES `taikhoan` (`TENDN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen`
--

DROP TABLE IF EXISTS `quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quyen` (
  `MAQUYEN` char(10) NOT NULL,
  `TENQUYEN` varchar(100) NOT NULL,
  PRIMARY KEY (`MAQUYEN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen`
--

LOCK TABLES `quyen` WRITE;
/*!40000 ALTER TABLE `quyen` DISABLE KEYS */;
/*!40000 ALTER TABLE `quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sach`
--

DROP TABLE IF EXISTS `sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sach` (
  `MASACH` char(10) NOT NULL,
  `TENSACH` varchar(100) NOT NULL,
  `NAMXUATBAN` int NOT NULL,
  `NHAXUATBAN` varchar(100) NOT NULL,
  `GIABIA` double NOT NULL,
  `GIABAN` double NOT NULL,
  `SOLUONG` int NOT NULL,
  `TRANGTHAI` int NOT NULL,
  PRIMARY KEY (`MASACH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sach`
--

LOCK TABLES `sach` WRITE;
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
/*!40000 ALTER TABLE `sach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tacgia`
--

DROP TABLE IF EXISTS `tacgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tacgia` (
  `MATG` char(10) NOT NULL,
  `TENTG` varchar(100) NOT NULL,
  `TRANGTHAI` int NOT NULL,
  PRIMARY KEY (`MATG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tacgia`
--

LOCK TABLES `tacgia` WRITE;
/*!40000 ALTER TABLE `tacgia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tacgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `TENDN` char(50) NOT NULL,
  `TENNV` varchar(100) NOT NULL,
  `DIACHI` varchar(250) DEFAULT NULL,
  `SDT` char(10) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `MAQUYEN` char(10) NOT NULL,
  `MATKHAU` char(50) NOT NULL,
  `TRANGTHAI` int NOT NULL,
  PRIMARY KEY (`TENDN`),
  KEY `MAQUYEN` (`MAQUYEN`),
  CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MAQUYEN`) REFERENCES `quyen` (`MAQUYEN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-26 22:17:04
