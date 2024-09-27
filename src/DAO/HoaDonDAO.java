/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.HoaDonDTO;
import java.sql.*;
import java.util.ArrayList;

public class HoaDonDAO {

    private connectDatabase conn;

    public HoaDonDAO() {
        try {
            conn = new connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HoaDonDTO> DanhSachHoaDon() {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        try {
            conn.connect();
            String sql = "SELECT * FROM HOADON";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql);) {
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

    public boolean ThemHoaDon(HoaDonDTO hd) {
        boolean success = false;
        try {
            conn.connect();
            String sql = "INSERT INTO HOADON(SOHD,MAKH,TENDN,THOIGIAN,NGAYHD,TIENGIAMGIA,TONGTIEN,TRANGTHAI) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, hd.getMaKH());
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

    public static void main(String[] agrs) {
        HoaDonDAO dao = new HoaDonDAO();
        ArrayList<HoaDonDTO> list = dao.DanhSachHoaDon();
        for (HoaDonDTO hd : list) {
            System.out.println(hd.getSoHD() + " " + hd.getMaKH() + " " + hd.getTenDN());
        }
    }

}
