/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhachHangDTO;
import java.sql.*;
import java.util.ArrayList;

public class KhachHangDAO {

    private connectDatabase conn;

    public KhachHangDAO() {
        try {
            conn = new connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<KhachHangDTO> dsKhachHang() {
        ArrayList<KhachHangDTO> ds = new ArrayList<>();
        try {
            conn.connect();
            String sql = "SELECT * FROM KHACHHANG";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    KhachHangDTO kh = new KhachHangDTO(rs.getString("MAKH"), rs.getString("TENKH"), rs.getString("SDT"), rs.getDouble("DIEMTICHLUY"));
                    ds.add(kh);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean ThemKhachHang(KhachHangDTO kh) {
        try {
            conn.connect();
            String sql = "INSERT INTO KHACHHANG(MAKH,TENKH, SDT, DIEMTICHLUY, TRANGTHAI) VALUES (?,?,?,?,?)";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, kh.getMaKh());
            pre.setString(2, kh.getTenKh());
            pre.setString(3, kh.getSdt());
            pre.setDouble(4, 0);
            pre.setInt(5, 1);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean SuaKhachHang(KhachHangDTO kh) {
        try {
            conn.connect();
            String sql = "UPDATE KHACHHANG SET TENKH=?, SDT=? WHERE MAKH= ?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, kh.getTenKh());
            pre.setString(2, kh.getSdt());
            pre.setString(3, kh.getMaKh());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void CapNhatDiemTL(String MaKH, double diem) {
        try {
            conn.connect();
            String sql = "UPDATE KHACHHANG SET DIEMTICHLUY = ? WHERE MAKH=?";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                pre.setDouble(1, diem);
                pre.setString(2, MaKH);
                pre.executeUpdate();
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String TimTenKhachHangWithId(String makh) {
        String query = "SELECT TENKH FROM khachhang WHERE MAKH = ?";
        String tenkh = "";
        try {
            conn.connect();
            PreparedStatement stmt = conn.getConn().prepareStatement(query);
            stmt.setString(1, makh);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tenkh = rs.getNString("TENKH");
            }
            conn.disconnect();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tenkh;
    }

    public boolean checkPhoneExits(String phone) {
        try {
            conn.connect();
            String sql = "SELECT COUNT(*) FROM KHACHHANG WHERE SDT = ?";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                pre.setString(1, phone);
                ResultSet rs = pre.executeQuery();
                if(rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            conn.disconnect();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] agrs) {
        KhachHangDAO dao = new KhachHangDAO();
//        ArrayList<KhachHangDTO> ds = dao.dsKhachHang();
//        for (KhachHangDTO sp : ds) {
//            System.out.println(sp.getMaKh() + " " + sp.getDiemTichluy() + " " + sp.getTenKh());
//        }
        dao.CapNhatDiemTL("KH01", 120);
    }
}
