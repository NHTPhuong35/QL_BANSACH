/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author nhatm
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
//import models.Employee;
//import models.Statistical;
import DTO.BangThongKeDTO;
import utils.TrangThaiHoaDon;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;


public class BangThongKeDAO {
    
    private connectDatabase conn;
    
    public BangThongKeDAO() {
        try {
            conn = new connectDatabase();
            conn.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (conn.getConn() != null) {
            System.out.println("Kết nối thành công");
        } else {
            System.out.println("Kết nối thất bại");
        }
    }
    
    private BangThongKeDTO bangthongkeDTO;

    public ArrayList<BangThongKeDTO.TaiKhoanIncome> getListTotalIncomeAllByDate(LocalDate start, LocalDate end) throws SQLException {
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = new ArrayList<>();
        String query = """
                       SELECT DATE(NGAYHD) AS hoadonDate, COUNT(SOHD) AS totalOrder, SUM(TONGTIEN) AS totalIncome 
                       FROM `hoadon` 
                       WHERE TRANGTHAI = ? 
                       AND DATE(NGAYHD) >= ? 
                       AND DATE(NGAYHD) <= ? 
                       GROUP BY hoadonDate 
                       ORDER BY hoadonDate ASC 
                       LIMIT 0, 1000;""";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start)); //sử dụng Date.valueOf khi sử dụng LocalDate
        statement.setDate(3, Date.valueOf(end));
        ResultSet rs = statement.executeQuery();
        //Test
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        System.out.println("EndLocalldate: " + Date.valueOf(end));
        System.out.println("StartLocaldate: " + Date.valueOf(start));

        //
        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.TaiKhoanIncome income = bangthongkeDTO.new TaiKhoanIncome();
            Date sqlhoadonDate = rs.getDate("hoadonDate");
            if (sqlhoadonDate != null) {
//                System.out.println("hoadonDate: " + sqlhoadonDate.toLocalDate());
                income.date = sqlhoadonDate.toLocalDate();
            } else {
//                System.out.println("hoadonDate: null");
                income.date = null;
            }
            income.totalIncome = rs.getInt("totalIncome");
            income.totalOrder = rs.getInt("totalOrder");
            
//            System.out.println("totalIncome: " + income.totalIncome);
//            System.out.println("totalOrder: " + income.totalOrder);
            
            incomes.add(income);
        }
        return incomes;
    }
    
    public ArrayList<BangThongKeDTO.TaiKhoanIncome> getListTotalIncomeByDateWithTheLoai(LocalDate start, LocalDate end, String theloai) throws SQLException {
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = new ArrayList<>();
        String query = """
                       SELECT DATE(NGAYHD) AS hoadonDate, COUNT(hoadon.SOHD) AS totalOrder, SUM(cthoadon.soluong * cthoadon.dongia) AS totalIncome 
                       FROM `hoadon` 
                       INNER JOIN cthoadon ON hoadon.SOHD = cthoadon.SOHD
                       INNER JOIN ctsachloai ON cthoadon.MASACH = ctsachloai.MASACH
                       INNER JOIN loai ON loai.MALOAI = ctsachloai.MALOAI
                       WHERE hoadon.TRANGTHAI = ? 
                       AND DATE(NGAYHD) >= ? 
                       AND DATE(NGAYHD) <= ? 
                       AND loai.TENLOAI = ? 
                       GROUP BY hoadonDate 
                       ORDER BY hoadonDate ASC 
                       LIMIT 0, 1000;""";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start)); //sử dụng Date.valueOf khi sử dụng LocalDate
        statement.setDate(3, Date.valueOf(end));
        statement.setString(4, theloai);
        ResultSet rs = statement.executeQuery();
        //Test
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        System.out.println("EndLocalldate: " + Date.valueOf(end));
        System.out.println("StartLocaldate: " + Date.valueOf(start));

        //
        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.TaiKhoanIncome income = bangthongkeDTO.new TaiKhoanIncome();
            Date sqlhoadonDate = rs.getDate("hoadonDate");
            if (sqlhoadonDate != null) {
//                System.out.println("hoadonDate: " + sqlhoadonDate.toLocalDate());
                income.date = sqlhoadonDate.toLocalDate();
            } else {
//                System.out.println("hoadonDate: null");
                income.date = null;
            }
            income.totalIncome = rs.getInt("totalIncome");
            income.totalOrder = rs.getInt("totalOrder");
            
//            System.out.println("totalIncome: " + income.totalIncome);
//            System.out.println("totalOrder: " + income.totalOrder);
            
            incomes.add(income);
        }
        return incomes;
    }
    
    
    public ArrayList<BangThongKeDTO.TaiKhoanIncome> getListTotalIncomeByMonthWithTheLoai(LocalDate start, LocalDate end, String theloai) throws SQLException {
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = new ArrayList<>();
        String query = """
                       SELECT YEAR(NGAYHD) AS year, MONTH(NGAYHD) AS month, COUNT(hoadon.SOHD) AS totalOrder, 
                              SUM(cthoadon.soluong * cthoadon.dongia) AS totalIncome 
                       FROM `hoadon` 
                       INNER JOIN cthoadon ON hoadon.SOHD = cthoadon.SOHD
                       INNER JOIN ctsachloai ON cthoadon.MASACH = ctsachloai.MASACH
                       INNER JOIN loai ON loai.MALOAI = ctsachloai.MALOAI
                       WHERE hoadon.TRANGTHAI = ? 
                       AND NGAYHD >= ? 
                       AND NGAYHD <= ? 
                       AND loai.TENLOAI = ? 
                       GROUP BY year, month 
                       ORDER BY year, month ASC 
                       LIMIT 0, 1000;""";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start));
        statement.setDate(3, Date.valueOf(end));
        statement.setString(4, theloai);
        ResultSet rs = statement.executeQuery();

        // Kiểm tra và in thông tin
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);

        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.TaiKhoanIncome income = bangthongkeDTO.new TaiKhoanIncome();
            int year = rs.getInt("year");
            int month = rs.getInt("month");
            income.date = LocalDate.of(year, month, 1); // Chỉ cần ngày 1 cho tháng
            income.totalIncome = rs.getInt("totalIncome");
            income.totalOrder = rs.getInt("totalOrder");

            incomes.add(income);
        }
        return incomes;
    }
    
    public ArrayList<BangThongKeDTO.TaiKhoanIncome> getListTotalIncomeAllByMonth(LocalDate start, LocalDate end) throws SQLException {
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = new ArrayList<>();
        String query = """
                       SELECT YEAR(NGAYHD) AS year, MONTH(NGAYHD) AS month, COUNT(hoadon.SOHD) AS totalOrder, 
                              SUM(cthoadon.soluong * cthoadon.dongia) AS totalIncome 
                       FROM `hoadon` 
                       INNER JOIN cthoadon ON hoadon.SOHD = cthoadon.SOHD
                       INNER JOIN ctsachloai ON cthoadon.MASACH = ctsachloai.MASACH
                       INNER JOIN loai ON loai.MALOAI = ctsachloai.MALOAI
                       WHERE hoadon.TRANGTHAI = ? 
                       AND NGAYHD >= ? 
                       AND NGAYHD <= ? 
                       GROUP BY year, month 
                       ORDER BY year, month ASC 
                       LIMIT 0, 1000;""";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start));
        statement.setDate(3, Date.valueOf(end));
        ResultSet rs = statement.executeQuery();

        // Kiểm tra và in thông tin
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);

        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.TaiKhoanIncome income = bangthongkeDTO.new TaiKhoanIncome();
            int year = rs.getInt("year");
            int month = rs.getInt("month");
            income.date = LocalDate.of(year, month, 1); // Chỉ cần ngày 1 cho tháng
            income.totalIncome = rs.getInt("totalIncome");
            income.totalOrder = rs.getInt("totalOrder");

            incomes.add(income);
        }
        return incomes;
    }
    
    public ArrayList<BangThongKeDTO.TaiKhoanIncome> getListTotalIncomeAllByYear(LocalDate start, LocalDate end) throws SQLException {
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = new ArrayList<>();
        String query = """
                       SELECT YEAR(NGAYHD) AS year, COUNT(hoadon.SOHD) AS totalOrder, 
                              SUM(cthoadon.soluong * cthoadon.dongia) AS totalIncome 
                       FROM `hoadon` 
                       INNER JOIN cthoadon ON hoadon.SOHD = cthoadon.SOHD
                       INNER JOIN ctsachloai ON cthoadon.MASACH = ctsachloai.MASACH
                       INNER JOIN loai ON loai.MALOAI = ctsachloai.MALOAI
                       WHERE hoadon.TRANGTHAI = ? 
                       AND NGAYHD >= ? 
                       AND NGAYHD <= ? 
                       GROUP BY year 
                       ORDER BY year ASC 
                       LIMIT 0, 1000;""";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start));
        statement.setDate(3, Date.valueOf(end));
        ResultSet rs = statement.executeQuery();

        // Kiểm tra và in thông tin
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);

        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.TaiKhoanIncome income = bangthongkeDTO.new TaiKhoanIncome();
            int year = rs.getInt("year");
            income.date = LocalDate.of(year, 1, 1); // Chỉ cần ngày 1 cho năm
            income.totalIncome = rs.getInt("totalIncome");
            income.totalOrder = rs.getInt("totalOrder");

            incomes.add(income);
        }
        return incomes;
    }
    
    public ArrayList<BangThongKeDTO.TaiKhoanIncome> getListTotalIncomeByYearWithTheLoai(LocalDate start, LocalDate end, String theloai) throws SQLException {
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = new ArrayList<>();
        String query = """
                       SELECT YEAR(NGAYHD) AS year, COUNT(hoadon.SOHD) AS totalOrder, 
                              SUM(cthoadon.soluong * cthoadon.dongia) AS totalIncome 
                       FROM `hoadon` 
                       INNER JOIN cthoadon ON hoadon.SOHD = cthoadon.SOHD
                       INNER JOIN ctsachloai ON cthoadon.MASACH = ctsachloai.MASACH
                       INNER JOIN loai ON loai.MALOAI = ctsachloai.MALOAI
                       WHERE hoadon.TRANGTHAI = ? 
                       AND NGAYHD >= ? 
                       AND NGAYHD <= ? 
                       AND loai.TENLOAI = ? 
                       GROUP BY year 
                       ORDER BY year ASC 
                       LIMIT 0, 1000;""";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start));
        statement.setDate(3, Date.valueOf(end));
        statement.setString(4, theloai);
        ResultSet rs = statement.executeQuery();

        // Kiểm tra và in thông tin
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);

        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.TaiKhoanIncome income = bangthongkeDTO.new TaiKhoanIncome();
            int year = rs.getInt("year");
            income.date = LocalDate.of(year, 1, 1); // Chỉ cần ngày 1 cho năm
            income.totalIncome = rs.getInt("totalIncome");
            income.totalOrder = rs.getInt("totalOrder");

            incomes.add(income);
        }
        return incomes;
    }

    
    public ArrayList<BangThongKeDTO.TaiKhoanNhapHangPriceImport> getListTotalImportPriceByDate(LocalDate start, LocalDate end) throws SQLException {
        ArrayList<BangThongKeDTO.TaiKhoanNhapHangPriceImport> prices = new ArrayList<>();
        String query = "SELECT DATE(NGAYNHAP) AS lohangnhapDate, COUNT(MAPN) AS totalPhieunhap, SUM(TONGTIEN) AS totalImportPrice \n" +
               "FROM `phieunhap` \n" +
               "WHERE TRANGTHAI = ? \n" +
               "AND DATE(NGAYNHAP) >= ? \n" +
               "AND DATE(NGAYNHAP) <= ? \n" +
               "GROUP BY lohangnhapDate \n" +
               "ORDER BY lohangnhapDate ASC \n" +
               "LIMIT 0, 1000;";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start)); //sử dụng Date.valueOf khi sử dụng LocalDate
        statement.setDate(3, Date.valueOf(end));
        ResultSet rs = statement.executeQuery();
        //Test
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        System.out.println("EndLocalldate: " + Date.valueOf(end));
        System.out.println("StartLocaldate: " + Date.valueOf(start));

        //
        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.TaiKhoanNhapHangPriceImport price = bangthongkeDTO.new TaiKhoanNhapHangPriceImport();
            Date sqlhoadonDate = rs.getDate("lohangnhapDate");
            if (sqlhoadonDate != null) {
//                System.out.println("hoadonDate: " + sqlhoadonDate.toLocalDate());
                price.date = sqlhoadonDate.toLocalDate();
            } else {
//                System.out.println("hoadonDate: null");
                price.date = null;
            }
            price.totalImportPrice = rs.getInt("totalImportPrice");
            
//            System.out.println("totalIncome: " + income.totalIncome);
//            System.out.println("totalOrder: " + income.totalOrder);
            
            prices.add(price);
        }
        return prices;
    }
    
    public ArrayList<BangThongKeDTO.ChiTietHoaDon> getListTotalBooksByDate(LocalDate start, LocalDate end) throws SQLException {
        ArrayList<BangThongKeDTO.ChiTietHoaDon> books = new ArrayList<>();
        String query = "SELECT " +
                "DATE(hoadon.NGAYHD) AS hoadonDate, " +
                "COUNT(DISTINCT cthoadon.SOHD) AS totalHoaDon, " +
                "SUM(cthoadon.SOLUONG) AS totalBooks " +
                "FROM hoadon " +
                "INNER JOIN cthoadon ON cthoadon.SOHD = hoadon.SOHD " +
                "WHERE hoadon.TRANGTHAI = ? " +
                "AND DATE(hoadon.NGAYHD) >= ? " +
                "AND DATE(hoadon.NGAYHD) <= ? " +
                "GROUP BY hoadonDate " +
                "ORDER BY hoadonDate ASC " +
                "LIMIT 0, 1000;";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start)); //sử dụng Date.valueOf khi sử dụng LocalDate
        statement.setDate(3, Date.valueOf(end));
        ResultSet rs = statement.executeQuery();
        //Test
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        System.out.println("EndLocalldate: " + Date.valueOf(end));
        System.out.println("StartLocaldate: " + Date.valueOf(start));

        //
        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.ChiTietHoaDon book = bangthongkeDTO.new ChiTietHoaDon();
            Date sqlhoadonDate = rs.getDate("hoadonDate");
            if (sqlhoadonDate != null) {
//                System.out.println("hoadonDate: " + sqlhoadonDate.toLocalDate());
                book.date = sqlhoadonDate.toLocalDate();
            } else {
//                System.out.println("hoadonDate: null");
                book.date = null;
            }
            book.totalBooks = rs.getInt("totalBooks");
            book.totalHoaDon = rs.getInt("totalHoaDon");

//            System.out.println("totalIncome: " + income.totalIncome);
//            System.out.println("totalOrder: " + income.totalOrder);
            
            books.add(book);
        }
        return books;
    }
}



