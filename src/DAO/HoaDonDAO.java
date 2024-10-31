/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.HoaDonDTO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class HoaDonDAO {

    private connectDatabase conn;

    public HoaDonDAO() {
        try {
            conn = new connectDatabase();
            conn.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HoaDonDTO> DanhSachHoaDon() {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        try {
            conn.connect();
            String sql = "SELECT * FROM HOADON";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    HoaDonDTO hd = new HoaDonDTO(rs.getString("SOHD"),
                            rs.getString("MAKH"),
                            rs.getString("TENDN"),
                            rs.getString("THOIGIAN"),
                            rs.getString("NGAYHD"),
                            rs.getDouble("TIENGIAMGIA"),
                            rs.getDouble("TONGTIEN"),
                            rs.getInt("TRANGTHAI")
                    );
                    list.add(hd);
                }
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<HoaDonDTO> DanhSachHoaDonDaDuocThanhToan(LocalDate start, LocalDate end) {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        try {
            conn.connect();
            String sql = "SELECT * FROM HOADON WHERE TRANGTHAI = 1 AND NGAYHD >= ? AND NGAYHD <= ?";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                pre.setDate(1, Date.valueOf(start));
                pre.setDate(2, Date.valueOf(end));
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    HoaDonDTO hd = new HoaDonDTO(rs.getString("SOHD"),
                            rs.getString("MAKH"),
                            rs.getString("TENDN"),
                            rs.getString("THOIGIAN"),
                            rs.getString("NGAYHD"),
                            rs.getDouble("TIENGIAMGIA"),
                            rs.getDouble("TONGTIEN"),
                            rs.getInt("TRANGTHAI")
                    );
                    list.add(hd);
                }
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<HoaDonDTO> DanhSachHoaDonDaDuocThanhToanTheoThoiGian(LocalDate start, LocalDate end, String thoigian) {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        try {
            conn.connect();
            switch (thoigian) {
                case "Ngày" -> {
                    String sql = "SELECT * FROM HOADON WHERE TRANGTHAI = 1 AND NGAYHD >= ? AND NGAYHD <= ?";
                    try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                        pre.setDate(1, Date.valueOf(start));
                        pre.setDate(2, Date.valueOf(end));
                        ResultSet rs = pre.executeQuery();
                        while (rs.next()) {
                            HoaDonDTO hd = new HoaDonDTO(rs.getString("SOHD"),
                                    rs.getString("MAKH"),
                                    rs.getString("TENDN"),
                                    rs.getString("THOIGIAN"),
                                    rs.getString("NGAYHD"),
                                    rs.getDouble("TIENGIAMGIA"),
                                    rs.getDouble("TONGTIEN"),
                                    rs.getInt("TRANGTHAI")
                            );
                            list.add(hd);
                        }
                    }
                    break;
                }
                case "Tháng" -> {
                    String sql = "SELECT * FROM HOADON WHERE TRANGTHAI = 1 AND MONTH(NGAYHD) >= ? AND MONTH(NGAYHD) <= ? " +
                                 "AND YEAR(NGAYHD) >= ? AND YEAR(NGAYHD) <= ?";
                    try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                        pre.setInt(1, start.getMonthValue()); // Lấy tháng từ LocalDate start
                        pre.setInt(2, end.getMonthValue());   // Lấy tháng từ LocalDate end
                        pre.setInt(3, start.getYear());       // Lấy năm từ LocalDate start
                        pre.setInt(4, end.getYear());         // Lấy năm từ LocalDate end
                        ResultSet rs = pre.executeQuery();
                        while (rs.next()) {
                            HoaDonDTO hd = new HoaDonDTO(rs.getString("SOHD"),
                                    rs.getString("MAKH"),
                                    rs.getString("TENDN"),
                                    rs.getString("THOIGIAN"),
                                    rs.getString("NGAYHD"),
                                    rs.getDouble("TIENGIAMGIA"),
                                    rs.getDouble("TONGTIEN"),
                                    rs.getInt("TRANGTHAI")
                            );
                            list.add(hd);
                        }
                    }
                    break;
                }
                case "Năm" -> {
                    String sql = "SELECT * FROM HOADON WHERE TRANGTHAI = 1 AND YEAR(NGAYHD) >= ? AND YEAR(NGAYHD) <= ?";
                    try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                        pre.setInt(1, start.getYear()); // Lấy năm từ LocalDate start
                        pre.setInt(2, end.getYear());   // Lấy năm từ LocalDate end
                        ResultSet rs = pre.executeQuery();
                        while (rs.next()) {
                            HoaDonDTO hd = new HoaDonDTO(rs.getString("SOHD"),
                                    rs.getString("MAKH"),
                                    rs.getString("TENDN"),
                                    rs.getString("THOIGIAN"),
                                    rs.getString("NGAYHD"),
                                    rs.getDouble("TIENGIAMGIA"),
                                    rs.getDouble("TONGTIEN"),
                                    rs.getInt("TRANGTHAI")
                            );
                            list.add(hd);
                        }
                    }
                    break;
                }
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int SoHoaDonHuyByTheLoaiWithThoiGian(LocalDate start, LocalDate end, String theloai, String thoigian) {
        String query;
        int sohoadonhuy = 0;

        try {
            conn.connect();
            PreparedStatement stmt = null;
            switch (thoigian) {
                case "Ngày" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                                SELECT COUNT(*) AS SOHOADONHUYBYTHELOAI FROM `hoadon`
                                INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                                INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                                INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                                WHERE hoadon.TRANGTHAI = 0
                                AND DATE(NGAYHD) >= ? 
                                AND DATE(NGAYHD) <= ? 
                                """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setDate(1, Date.valueOf(start));
                        stmt.setDate(2, Date.valueOf(end));
                    }else{
                        query = """
                            SELECT COUNT(*) AS SOHOADONHUYBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 0
                            AND loai.TENLOAI = ?
                            AND DATE(NGAYHD) >= ? 
                            AND DATE(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setDate(2, Date.valueOf(start));
                        stmt.setDate(3, Date.valueOf(end));
                    }
                    
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        sohoadonhuy = rs.getInt("SOHOADONHUYBYTHELOAI");
                    }
                }
                case "Tháng" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT COUNT(*) AS SOHOADONHUYBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 0
                            AND MONTH(NGAYHD) >= ? 
                            AND MONTH(NGAYHD) <= ? 
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setInt(1, start.getMonthValue()); // Tháng bắt đầu
                        stmt.setInt(2, end.getMonthValue());   // Tháng kết thúc
                        stmt.setInt(3, start.getYear());       // Năm bắt đầu
                        stmt.setInt(4, end.getYear());         // Năm kế
                    }else{
                        query = """
                            SELECT COUNT(*) AS SOHOADONHUYBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 0
                            AND loai.TENLOAI = ?
                            AND MONTH(NGAYHD) >= ? 
                            AND MONTH(NGAYHD) <= ? 
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setInt(2, start.getMonthValue()); // Tháng bắt đầu
                        stmt.setInt(3, end.getMonthValue());   // Tháng kết thúc
                        stmt.setInt(4, start.getYear());       // Năm bắt đầu
                        stmt.setInt(5, end.getYear());         // Năm kế
                    }
                    
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        sohoadonhuy = rs.getInt("SOHOADONHUYBYTHELOAI");
                    }
                }
                case "Năm" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT COUNT(*) AS SOHOADONHUYBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 0
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setInt(1, start.getYear()); // Năm bắt đầu
                        stmt.setInt(2, end.getYear());   // Năm kết thúc
                    }else{
                        query = """
                            SELECT COUNT(*) AS SOHOADONHUYBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 0
                            AND loai.TENLOAI = ?
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setInt(2, start.getYear()); // Năm bắt đầu
                        stmt.setInt(3, end.getYear());   // Năm kết thúc
                    }
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        sohoadonhuy = rs.getInt("SOHOADONHUYBYTHELOAI");
                    }
                }
            }
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sohoadonhuy;
    }
    
    public int SoHoaDonByTheLoaiWithThoiGian(LocalDate start, LocalDate end, String theloai, String thoigian) {
        String query;
        int sohoadon = 0;
        PreparedStatement stmt = null;
        try {
            conn.connect();
            switch (thoigian) {
                case "Ngày" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT COUNT(DISTINCT hoadon.SOHD) AS SOHOADONBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND DATE(NGAYHD) >= ? 
                            AND DATE(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setDate(1, Date.valueOf(start));
                        stmt.setDate(2, Date.valueOf(end));
                    }else{
                        query = """
                            SELECT COUNT(DISTINCT hoadon.SOHD) AS SOHOADONBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND loai.TENLOAI = ?
                            AND DATE(NGAYHD) >= ? 
                            AND DATE(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setDate(2, Date.valueOf(start));
                        stmt.setDate(3, Date.valueOf(end));
                    }

                    ResultSet rs = stmt.executeQuery();
                    
                    if (rs.next()) {
                        sohoadon = rs.getInt("SOHOADONBYTHELOAI");
                    }
                }
                case "Tháng" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT COUNT(DISTINCT hoadon.SOHD) AS SOHOADONBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND MONTH(NGAYHD) >= ? 
                            AND MONTH(NGAYHD) <= ? 
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setInt(1, start.getMonthValue()); // Tháng bắt đầu
                        stmt.setInt(2, end.getMonthValue());   // Tháng kết thúc
                        stmt.setInt(3, start.getYear());       // Năm bắt đầu
                        stmt.setInt(4, end.getYear());         // Năm kết thúc
                    }else{
                        query = """
                            SELECT COUNT(DISTINCT hoadon.SOHD) AS SOHOADONBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND loai.TENLOAI = ?
                            AND MONTH(NGAYHD) >= ? 
                            AND MONTH(NGAYHD) <= ? 
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setInt(2, start.getMonthValue()); // Tháng bắt đầu
                        stmt.setInt(3, end.getMonthValue());   // Tháng kết thúc
                        stmt.setInt(4, start.getYear());       // Năm bắt đầu
                        stmt.setInt(5, end.getYear());         // Năm kết thúc
                    }

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        sohoadon = rs.getInt("SOHOADONBYTHELOAI");
                    }
                }
                case "Năm" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT COUNT(DISTINCT hoadon.SOHD) AS SOHOADONBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setInt(1, start.getYear()); // Năm bắt đầu
                        stmt.setInt(2, end.getYear());   // Năm kết thúc

                    }else{
                        query = """
                            SELECT COUNT(DISTINCT hoadon.SOHD) AS SOHOADONBYTHELOAI FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND loai.TENLOAI = ?
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setInt(2, start.getYear()); // Năm bắt đầu
                        stmt.setInt(3, end.getYear());   // Năm kết thúc
                    }

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        sohoadon = rs.getInt("SOHOADONBYTHELOAI");
                    }
                }
            }
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sohoadon;
    }
    
    public int TongQuyenSachByTheLoaiWithThoiGian(LocalDate start, LocalDate end, String theloai, String thoigian) {
        String query;
        int tongquyensach = 0;

        try {
            conn.connect();
            PreparedStatement stmt = null;
            switch (thoigian) {
                case "Ngày" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT SUM(cthoadon.SOLUONG) AS TONGQUYENSACH FROM `cthoadon`
                            INNER JOIN `hoadon` ON cthoadon.SOHD = hoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND DATE(NGAYHD) >= ? 
                            AND DATE(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setDate(1, Date.valueOf(start));
                        stmt.setDate(2, Date.valueOf(end));
                    }else{
                        query = """
                            SELECT SUM(cthoadon.SOLUONG) AS TONGQUYENSACH FROM `cthoadon`
                            INNER JOIN `hoadon` ON cthoadon.SOHD = hoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND loai.TENLOAI = ?
                            AND DATE(NGAYHD) >= ? 
                            AND DATE(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setDate(2, Date.valueOf(start));
                        stmt.setDate(3, Date.valueOf(end));
                    }

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        tongquyensach = rs.getInt("TONGQUYENSACH");
                    }
                }
                case "Tháng" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT SUM(cthoadon.SOLUONG) 
                            AS TONGQUYENSACH FROM `cthoadon`
                            INNER JOIN `hoadon` ON cthoadon.SOHD = hoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND MONTH(NGAYHD) >= ? 
                            AND MONTH(NGAYHD) <= ? 
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setInt(1, start.getMonthValue()); // Tháng bắt đầu
                        stmt.setInt(2, end.getMonthValue());   // Tháng kết thúc
                        stmt.setInt(3, start.getYear());       // Năm bắt đầu
                        stmt.setInt(4, end.getYear());         // Năm kết thúc
                    }else{
                        query = """
                            SELECT SUM(cthoadon.SOLUONG) 
                            AS TONGQUYENSACH FROM `cthoadon`
                            INNER JOIN `hoadon` ON cthoadon.SOHD = hoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND loai.TENLOAI = ?
                            AND MONTH(NGAYHD) >= ? 
                            AND MONTH(NGAYHD) <= ? 
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setInt(2, start.getMonthValue()); // Tháng bắt đầu
                        stmt.setInt(3, end.getMonthValue());   // Tháng kết thúc
                        stmt.setInt(4, start.getYear());       // Năm bắt đầu
                        stmt.setInt(5, end.getYear());         // Năm kết thúc
                    }

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        tongquyensach = rs.getInt("TONGQUYENSACH");
                    }
                }
                case "Năm" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT SUM(cthoadon.SOLUONG) 
                            AS TONGQUYENSACH FROM `cthoadon`
                            INNER JOIN `hoadon` ON cthoadon.SOHD = hoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setInt(1, start.getYear()); // Năm bắt đầu
                        stmt.setInt(2, end.getYear());   // Năm kết thúc
                    }else{
                        query = """
                            SELECT SUM(cthoadon.SOLUONG) 
                            AS TONGQUYENSACH FROM `cthoadon`
                            INNER JOIN `hoadon` ON cthoadon.SOHD = hoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND loai.TENLOAI = ?
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setInt(2, start.getYear()); // Năm bắt đầu
                        stmt.setInt(3, end.getYear());   // Năm kết thúc
                    }
                    
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        tongquyensach = rs.getInt("TONGQUYENSACH");
                    }
                }
            }
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tongquyensach;
    }
    
    public double TongDoanhThuByTheLoaiWithThoiGian(LocalDate start, LocalDate end, String theloai, String thoigian) {
        String query;
        int tongdoanhthu = 0;

        try {
            PreparedStatement stmt = null;
            conn.connect();
            switch (thoigian) {
                case "Ngày" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT SUM(cthoadon.DONGIA*cthoadon.SOLUONG) AS TONGTIEN FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND DATE(NGAYHD) >= ? 
                            AND DATE(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setDate(1, Date.valueOf(start));
                        stmt.setDate(2, Date.valueOf(end));
                    }else{
                        query = """
                            SELECT SUM(cthoadon.DONGIA*cthoadon.SOLUONG) AS TONGTIEN FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND loai.TENLOAI = ?
                            AND DATE(NGAYHD) >= ? 
                            AND DATE(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setDate(2, Date.valueOf(start));
                        stmt.setDate(3, Date.valueOf(end));
                    }

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        tongdoanhthu = rs.getInt("TONGTIEN");
                    }
                }
                case "Tháng" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT SUM(cthoadon.DONGIA*cthoadon.SOLUONG) 
                            AS TONGTIEN FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND MONTH(NGAYHD) >= ? 
                            AND MONTH(NGAYHD) <= ? 
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setInt(1, start.getMonthValue()); // Tháng bắt đầu
                        stmt.setInt(2, end.getMonthValue());   // Tháng kết thúc
                        stmt.setInt(3, start.getYear());       // Năm bắt đầu
                        stmt.setInt(4, end.getYear());         // Năm kết thúc
                    }else{
                        query = """
                            SELECT SUM(cthoadon.DONGIA*cthoadon.SOLUONG) 
                            AS TONGTIEN FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND loai.TENLOAI = ?
                            AND MONTH(NGAYHD) >= ? 
                            AND MONTH(NGAYHD) <= ? 
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setInt(2, start.getMonthValue()); // Tháng bắt đầu
                        stmt.setInt(3, end.getMonthValue());   // Tháng kết thúc
                        stmt.setInt(4, start.getYear());       // Năm bắt đầu
                        stmt.setInt(5, end.getYear());         // Năm kết thúc
                    }
                    
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        tongdoanhthu = rs.getInt("TONGTIEN");
                    }
                }
                case "Năm" -> {
                    if(theloai.equals("Tất cả")){
                        query = """
                            SELECT SUM(cthoadon.DONGIA*cthoadon.SOLUONG) 
                            AS TONGTIEN FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setInt(1, start.getYear());
                        stmt.setInt(2, end.getYear()); 
                    }else{
                        query = """
                            SELECT SUM(cthoadon.DONGIA*cthoadon.SOLUONG) 
                            AS TONGTIEN FROM `hoadon`
                            INNER JOIN `cthoadon` ON hoadon.SOHD = cthoadon.SOHD
                            INNER JOIN `ctsachloai` ON cthoadon.MASACH = ctsachloai.MASACH
                            INNER JOIN `loai` ON ctsachloai.MALOAI = loai.MALOAI
                            WHERE hoadon.TRANGTHAI = 1
                            AND loai.TENLOAI = ?
                            AND YEAR(NGAYHD) >= ? 
                            AND YEAR(NGAYHD) <= ? 
                            """;
                        stmt = conn.getConn().prepareStatement(query);
                        stmt.setString(1, theloai);
                        stmt.setInt(2, start.getYear()); // Năm bắt đầu
                        stmt.setInt(3, end.getYear());   // Năm kết thúc
                    }
                    
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        tongdoanhthu = rs.getInt("TONGTIEN");
                    }
                }
            }
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tongdoanhthu;
    }
    
    public boolean ThemHoaDon(HoaDonDTO hd) {
        boolean success = false;
        try {
            conn.connect();
            String sql = "INSERT INTO HOADON(SOHD,MAKH,TENDN,THOIGIAN,NGAYHD,TIENGIAMGIA,TONGTIEN,TRANGTHAI) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, hd.getSoHD());
            pre.setString(2, hd.getMaKH());
            pre.setString(3, hd.getTenDN());
            pre.setString(4, hd.getTGian());
            pre.setString(5, hd.getNgayHD());
            pre.setDouble(6, hd.getTienGiamGia());
            pre.setDouble(7, hd.getTongTien());
            pre.setInt(8, 1);
            success = pre.executeUpdate() > 0;
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean SuaHoaDonTheoSoHD(HoaDonDTO hd) {
        boolean success = false;
        try {
            conn.connect();
            String sql = "UPDATE HOADON SET MAKH=?, TENDN=?, THOIGIAN=?, NGAYHD=?, TIENGIAMGIA=?, TONGTIEN=?, TRANGTHAI=? WHERE SOHD=?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, hd.getMaKH());
            pre.setString(2, hd.getTenDN());
            pre.setString(3, hd.getTGian());
            pre.setString(4, hd.getNgayHD());
            pre.setDouble(5, hd.getTienGiamGia());
            pre.setDouble(6, hd.getTongTien());
            pre.setInt(7, hd.getTrangThai());
            pre.setString(8, hd.getSoHD());
            success = pre.executeUpdate() > 0;
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean XoaHoaDon(String soHD) {
        boolean success = false;
        try {
            conn.connect();
            String sql = "DELETE FROM HOADON WHERE SOHD=?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, soHD);
            success = pre.executeUpdate() > 0;
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean CapNhatTrangThaiHD(String MaHD) {
        boolean success = false;
        try {
            conn.connect();

            // Sửa cú pháp SQL: thêm dấu phẩy trước 'TRANGTHAI'
            String sql = "UPDATE HOADON SET TRANGTHAI = 0 WHERE SOHD = ?";

            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, MaHD);

            // Kiểm tra số hàng bị ảnh hưởng
            success = pre.executeUpdate() > 0;

            // Đảm bảo đóng PreparedStatement
            pre.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
    
    public static void main(String[] agrs) {
        HoaDonDAO dao = new HoaDonDAO();
        ArrayList<HoaDonDTO> list = dao.DanhSachHoaDon();
        for (HoaDonDTO hd : list) {
            System.out.println(hd.getSoHD() + " " + hd.getMaKH() + " " + hd.getTenDN());
        }
    }

}

