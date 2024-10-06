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
                String maSP = rs.getString("MASP");
                int soLuong = rs.getInt("SOLUONG");
                double tongTien = rs.getDouble("TONGTIEN");
                double giaNhap = rs.getDouble("GIANHAP");
                ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO();
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

    public boolean suaPhieuNhap(PhieuNhapDTO pn) {
        try {
            conn.connect();
            String sql = "UPDATE phieunhap SET MANCC = ?, TENDN = ?, NGAYNHAP = ?, TONGTIEN = ?, TRANGTHAI = ? WHERE MAPN = ?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, pn.getMaNCC());
            pre.setString(2, pn.getTenDN());
            pre.setDate(3, new java.sql.Date(pn.getNgayNhap().getTime()));
            pre.setDouble(4, pn.getTongTien());
            pre.setInt(5, pn.getTrangThai());
            pre.setString(6, pn.getMaPN());
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

    // public boolean xoaChiTietPhieuNhap(String maPN) {
    //     try {
    //         conn.connect();
    //         String sql = "DELETE FROM chitietphieunhap WHERE MAPN = ?";
    //         PreparedStatement pre = conn.getConn().prepareStatement(sql);
    //         pre.setString(1, maPN);
    //         pre.executeUpdate();
    //         conn.disconnect();
    //         return true;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }

}
