/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanDAO {

    private connectDatabase conn;

    public TaiKhoanDAO() {
        try {
            conn = new connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TaiKhoanDTO> DanhSachTaiKhoan() {
        ArrayList<TaiKhoanDTO> dsTK = new ArrayList<>();
        try {
            conn.connect();
            // Câu truy vấn thêm điều kiện trạng thái khác 2
            String sql = "SELECT tk.TENDN, tk.TENNV, tk.DIACHI, tk.SDT, tk.EMAIL, tk.MATKHAU, tk.TRANGTHAI, q.MAQUYEN, q.TENQUYEN "
                    + "FROM taikhoan tk "
                    + "JOIN quyen q ON tk.MAQUYEN = q.MAQUYEN "
                    + "WHERE tk.TRANGTHAI != 2"; // Điều kiện trạng thái khác 2
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                String tenDN = rs.getString("TENDN");
                String tenNV = rs.getString("TENNV");
                String diaChi = rs.getString("DIACHI");
                String SDT = rs.getString("SDT");
                String email = rs.getString("EMAIL");
                String matKhau = rs.getString("MATKHAU");
                int trangThai = rs.getInt("TRANGTHAI");

                // Lấy thông tin quyền
                String maQuyen = rs.getString("MAQUYEN");
                String tenQuyen = rs.getString("TENQUYEN");

                // Tạo đối tượng QuyenDTO và TaiKhoanDTO
                QuyenDTO quyen = new QuyenDTO(maQuyen, tenQuyen);
                TaiKhoanDTO tk = new TaiKhoanDTO(tenDN, tenNV, diaChi, SDT, email, matKhau, quyen, trangThai);

                // Thêm tài khoản vào danh sách
                dsTK.add(tk);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTK;
    }

    public boolean themTaiKhoan(TaiKhoanDTO tk) {
        boolean result = false;
        try {
            conn.connect();
            String sql = "INSERT INTO taikhoan (TENDN, TENNV, DIACHI, SDT, EMAIL, MATKHAU, MAQUYEN, TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, tk.getTenDN());
            pre.setString(2, tk.getTenNV());
            pre.setString(3, tk.getDiaChi());
            pre.setString(4, tk.getSDT());
            pre.setString(5, tk.getEmail());
            pre.setString(6, tk.getMatKhau());
            pre.setString(7, tk.getQuyen().getMaQuyen());
            pre.setInt(8, tk.getTrangThai());

            int rowsAffected = pre.executeUpdate();
            result = rowsAffected > 0;
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Phương thức sửa tài khoản
    public boolean suaTaiKhoan(TaiKhoanDTO tk) {
        boolean result = false;
        try {
            conn.connect();
            String sql = "UPDATE taikhoan SET TENNV = ?, DIACHI = ?, SDT = ?, EMAIL = ?, MATKHAU = ?, MAQUYEN = ?, TRANGTHAI = ? WHERE TENDN = ?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, tk.getTenNV());
            pre.setString(2, tk.getDiaChi());
            pre.setString(3, tk.getSDT());
            pre.setString(4, tk.getEmail());
            pre.setString(5, tk.getMatKhau());
            pre.setString(6, tk.getQuyen().getMaQuyen());
            pre.setInt(7, tk.getTrangThai());
            pre.setString(8, tk.getTenDN());

            int rowsAffected = pre.executeUpdate();
            result = rowsAffected > 0;
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Phương thức xoá tài khoản
    public boolean xoaTaiKhoan(String tenDN) {
        boolean result = false;
        try {
            conn.connect();

            // Kiểm tra tài khoản có liên kết với bảng phieunhap hoặc hoadon hay không
            String checkSql = "SELECT COUNT(*) FROM ( "
                    + "SELECT TENDN FROM phieunhap WHERE TENDN = ? "
                    + "UNION "
                    + "SELECT TENDN FROM hoadon WHERE TENDN = ? "
                    + ") AS temp";
            PreparedStatement checkStmt = conn.getConn().prepareStatement(checkSql);
            checkStmt.setString(1, tenDN);
            checkStmt.setString(2, tenDN);

            ResultSet rs = checkStmt.executeQuery();
            boolean hasReferences = false;
            if (rs.next()) {
                hasReferences = rs.getInt(1) > 0;
            }

            if (hasReferences) {
                // Nếu có liên kết, cập nhật trạng thái về 2
                String updateSql = "UPDATE taikhoan SET TRANGTHAI = 2 WHERE TENDN = ?";
                PreparedStatement updateStmt = conn.getConn().prepareStatement(updateSql);
                updateStmt.setString(1, tenDN);
                int rowsAffected = updateStmt.executeUpdate();
                result = rowsAffected > 0;
            } else {
                // Nếu không có liên kết, xóa tài khoản
                String deleteSql = "DELETE FROM taikhoan WHERE TENDN = ?";
                PreparedStatement deleteStmt = conn.getConn().prepareStatement(deleteSql);
                deleteStmt.setString(1, tenDN);
                int rowsAffected = deleteStmt.executeUpdate();
                result = rowsAffected > 0;
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public TaiKhoanDTO timTaiKhoan(String userName) throws SQLException {
        conn.connect();
        String sql = "SELECT * FROM taikhoan WHERE taikhoan.TENDN = '" + userName + "'";
        PreparedStatement pre = conn.getConn().prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            TaiKhoanDTO taikhoan = TaiKhoanDTO.getFromResultSet(rs);
            return taikhoan;
        }
        return null;
    }

    public static void main(String[] args) {
        TaiKhoanDAO tk = new TaiKhoanDAO();
        for (TaiKhoanDTO t : tk.DanhSachTaiKhoan()) {
            System.out.println("TENDN: " + t.getTenDN() + ",TENNV: " + t.getTenNV() + ",TENQUYEN: " + t.getQuyen().getTenQuyen());
        }
        QuyenDTO q = new QuyenDTO("QL", "Quản lý");
        TaiKhoanDTO tkDTO = new TaiKhoanDTO("NV07", "Phương123", "Quận 8", "0983456789", "Phuong579@gmail.com", "55345678", q, 0);
//        tk.themTaiKhoan(new TaiKhoanDTO("NV07","Phương","Quận 7","0123456789","Phuong123@gmail.com","12345678",q,1));
//        tk.suaTaiKhoan(tkDTO);
//        tk.xoaTaiKhoan("NV07",false);
    }
}
