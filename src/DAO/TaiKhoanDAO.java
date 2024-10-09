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
            // Câu truy vấn kết hợp giữa bảng 'taikhoan' và 'quyen'
            String sql = "SELECT tk.TENDN, tk.TENNV, tk.DIACHI, tk.SDT, tk.EMAIL, tk.MATKHAU, tk.TRANGTHAI, q.MAQUYEN, q.TENQUYEN "
                    + "FROM taikhoan tk "
                    + "JOIN quyen q ON tk.MAQUYEN = q.MAQUYEN";
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
    public boolean xoaTaiKhoan(String tenDN, Boolean ktra) {
        boolean result = false;
        try {
            conn.connect();
            // Nếu ktra là true, chỉ cập nhật trạng thái
            if (ktra) {
                String sql = "UPDATE taikhoan SET TRANGTHAI = 0 WHERE TENDN = ?";
                PreparedStatement pre = conn.getConn().prepareStatement(sql);
                pre.setString(1, tenDN);
                int rowsAffected = pre.executeUpdate();
                result = rowsAffected > 0;
            } else { // Nếu ktra là false, thực hiện xóa
                String sql = "DELETE FROM taikhoan WHERE TENDN = ?";
                PreparedStatement pre = conn.getConn().prepareStatement(sql);
                pre.setString(1, tenDN);
                int rowsAffected = pre.executeUpdate();
                result = rowsAffected > 0;
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public TaiKhoanDTO timTaiKhoan(String userName)throws SQLException {
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
