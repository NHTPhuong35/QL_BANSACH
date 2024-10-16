package DAO;

import DAO.connectDatabase;
import DTO.PhieuNhapDTO;
import DTO.ChiTietPhieuNhapDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhieuNhapDAO {
    private connectDatabase conn;

    public PhieuNhapDAO() {
        try {
            conn = new connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PhieuNhapDTO> LayPhieuNhap() {
        ArrayList<PhieuNhapDTO> ds = new ArrayList<>();
        try {
            conn.connect();
            String sql = "SELECT * FROM phieunhap WHERE TRANGTHAI = 1";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String maPN = rs.getString("MAPN");
                String maNCC = rs.getString("MANCC");
                String tenDN = rs.getString("TENDN");
                java.sql.Date ngayNhap = rs.getDate("NGAYNHAP");
                double tongTien = rs.getDouble("TONGTIEN");
                int trangThai = rs.getInt("TRANGTHAI");
                PhieuNhapDTO pn = new PhieuNhapDTO(maPN, maNCC, tenDN, ngayNhap, tongTien, trangThai);
                ds.add(pn);
            }
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<ChiTietPhieuNhapDTO> LayChiTietPhieuNhap(String maPN) {
        ArrayList<ChiTietPhieuNhapDTO> ds = new ArrayList<>();
        try {
            conn.connect();
            String sql = "SELECT * FROM chitietphieunhap WHERE MAPN = ?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, maPN);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String maPN1 = rs.getString("MAPN");
                String maSach = rs.getString("MASACH");
                int soLuong = rs.getInt("SOLUONG");
                double tongTien = rs.getDouble("TONGTIEN");
                double giaNhap = rs.getDouble("GIANHAP");
                ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO(maPN1, maSach, soLuong, tongTien, giaNhap);
                ds.add(ctpn);
            }
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean ThemPhieuNhap(PhieuNhapDTO pn) {
        try {
            conn.connect();
            // Get the latest MAPN and increment by 1
            String getMaxMapnSql = "SELECT MAX(MAPN) AS maxMapn FROM phieunhap";
            PreparedStatement getMaxMapnPre = conn.getConn().prepareStatement(getMaxMapnSql);
            ResultSet rs = getMaxMapnPre.executeQuery();
            String newMapn = "PN01"; // Default value if no records exist
            if (rs.next()) {
                String maxMapn = rs.getString("maxMapn");
                if (maxMapn != null) {
                    int newMapnInt = Integer.parseInt(maxMapn.substring(2)) + 1;
                    newMapn = "PN" + String.format("%d", newMapnInt);
                }
            }

            String sql = "INSERT INTO phieunhap(MAPN, MANCC, TENDN, NGAYNHAP, TONGTIEN, TRANGTHAI) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, newMapn);
            pre.setString(2, pn.getMaNCC());
            pre.setString(3, pn.getTenDN());
            pre.setDate(4, new java.sql.Date(pn.getNgayNhap().getTime()));
            pre.setDouble(5, pn.getTongTien());
            pre.setInt(6, pn.getTrangThai());
            pre.executeUpdate();
            conn.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ThemChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        try {
            conn.connect();
            String sql = "INSERT INTO chitietphieunhap(MAPN, MASACH, GIANHAP, SOLUONG, TONGTIEN) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, chiTietPhieuNhap.getMAPN());
            pre.setString(2, chiTietPhieuNhap.getMASACH());
            pre.setDouble(3, chiTietPhieuNhap.getGIANHAP());
            pre.setInt(4, chiTietPhieuNhap.getSOLUONG());
            pre.setDouble(5, chiTietPhieuNhap.getTONGTIEN());
            pre.executeUpdate();
            conn.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaPhieuNhap(String maPN, String maNCC, String tenDN, java.util.Date ngayNhap, double tongTien,
            int trangThai) {
        try {
            conn.connect();
            String sql = "UPDATE phieunhap SET MANCC = ?, TENDN = ?, NGAYNHAP = ?, TONGTien = ?, TRANGTHAI = ? WHERE MAPN = ?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, maNCC);
            pre.setString(2, tenDN);
            pre.setDate(3, new java.sql.Date(ngayNhap.getTime()));
            pre.setDouble(4, tongTien);
            pre.setInt(5, trangThai);
            pre.setString(6, maPN);
            pre.executeUpdate();
            conn.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        try {
            conn.connect();
            String sql = "UPDATE chitietphieunhap SET MASACH = ?, SOLUONG = ?, TONGTIEN = ?, GIANHAP = ? WHERE MAPN = ?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, chiTietPhieuNhap.getMASACH());
            pre.setInt(2, chiTietPhieuNhap.getSOLUONG());
            pre.setDouble(3, chiTietPhieuNhap.getTONGTIEN());
            pre.setDouble(4, chiTietPhieuNhap.getGIANHAP());
            pre.setString(5, chiTietPhieuNhap.getMAPN());
            pre.executeUpdate();
            conn.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaPhieuNhap(String maPN) {
        try {
            conn.connect();
            String sql = "UPDATE phieunhap SET TRANGTHAI = 0 WHERE MAPN = ?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, maPN);
            pre.executeUpdate();
            conn.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public int getSoLuongSP(String maSP) {
        int soLuong = 0;
        try {
            conn.connect();
            String sql = "SELECT SOLUONG FROM sach WHERE MASACH = ?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, maSP);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("SOLUONG");
            }
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    

    public boolean capNhatChiTietPhieuNhap(String maPN, String maSP, int soLuongDiff) {
        try {
            conn.connect();
            
            // Query to get the price of the product
            String getGiaNhapSql = "SELECT GIANHAP FROM chitietphieunhap WHERE MAPN = ? AND MASACH = ?";
            PreparedStatement getGiaNhapPre = conn.getConn().prepareStatement(getGiaNhapSql);
            getGiaNhapPre.setString(1, maPN);
            getGiaNhapPre.setString(2, maSP);
            ResultSet giaNhapRs = getGiaNhapPre.executeQuery();
            double giaSP = 0;
            if (giaNhapRs.next()) {
                giaSP = giaNhapRs.getDouble("GIANHAP");
            }
            
            // Query to get the quantity of the product in ChiTietPhieuNhap with maPN and maSP
            String getSoLuongSql = "SELECT SOLUONG FROM chitietphieunhap WHERE MAPN = ? AND MASACH = ?";
            PreparedStatement getSoLuongPre = conn.getConn().prepareStatement(getSoLuongSql);
            getSoLuongPre.setString(1, maPN);
            getSoLuongPre.setString(2, maSP);
            ResultSet soLuongRs = getSoLuongPre.executeQuery();
            int soLuongHienTai = 0;
            if (soLuongRs.next()) {
                soLuongHienTai = soLuongRs.getInt("SOLUONG");
            }

            // Query the quantity of the product in Sach
            String getSoLuongSPSql = "SELECT SOLUONG FROM sach WHERE MASACH = ?";
            PreparedStatement getSoLuongSPPre = conn.getConn().prepareStatement(getSoLuongSPSql);
            getSoLuongSPPre.setString(1, maSP);
            ResultSet soLuongSPRs = getSoLuongSPPre.executeQuery();
            int soLuongSP = 0;
            if (soLuongSPRs.next()) {
                soLuongSP = soLuongSPRs.getInt("SOLUONG");
            }
            
            // Check if the new quantity is valid
            if (soLuongSP + soLuongDiff < 0 || soLuongHienTai + soLuongDiff < 0) {
                throw new SQLException("Quantity cannot be negative.");
            }

            // Update the quantity in the database
            String updateSql = "UPDATE sach SET SOLUONG = ? WHERE MASACH = ?";
            PreparedStatement updatePre = conn.getConn().prepareStatement(updateSql);
            updatePre.setInt(1, soLuongSP - soLuongDiff);
            updatePre.setString(2, maSP);
            updatePre.executeUpdate();
            
            // Update the quantity in the ChiTietPhieuNhap table
            String updateChiTietPhieuNhapSql = "UPDATE chitietphieunhap SET SOLUONG = ? WHERE MAPN = ? AND MASACH = ?";
            PreparedStatement updateChiTietPhieuNhapPre = conn.getConn().prepareStatement(updateChiTietPhieuNhapSql);
            updateChiTietPhieuNhapPre.setInt(1, soLuongHienTai + soLuongDiff);
            updateChiTietPhieuNhapPre.setString(2, maPN);
            updateChiTietPhieuNhapPre.setString(3, maSP);
            updateChiTietPhieuNhapPre.executeUpdate();

            // Update the total price in the ChiTietPhieuNhap table
            String updatePhieuNhapSql = "UPDATE chitietphieunhap SET TONGTIEN = ? WHERE MAPN = ? AND MASACH = ?";
            PreparedStatement updatePhieuNhapPre = conn.getConn().prepareStatement(updatePhieuNhapSql);
            updatePhieuNhapPre.setDouble(1, (soLuongHienTai + soLuongDiff) * giaSP);
            updatePhieuNhapPre.setString(2, maPN);
            updatePhieuNhapPre.setString(3, maSP);
            updatePhieuNhapPre.executeUpdate();

            // Update the total price in the PhieuNhap table
            String updateTongTienSql = "UPDATE phieunhap SET TONGTIEN = (SELECT SUM(TONGTIEN) FROM chitietphieunhap WHERE MAPN = ?) WHERE MAPN = ?";
            PreparedStatement updateTongTienPre = conn.getConn().prepareStatement(updateTongTienSql);
            updateTongTienPre.setString(1, maPN);
            updateTongTienPre.setString(2, maPN);
            updateTongTienPre.executeUpdate();

            conn.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


