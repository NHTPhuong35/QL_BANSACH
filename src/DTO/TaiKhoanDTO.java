/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import DAO.QuyenDAO;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanDTO {

    private String tenDN, tenNV, diaChi, SDT, email, matKhau;
    private QuyenDTO quyen;
    private int trangThai;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String tenDN, String tenNV, String diaChi, String SDT, String email, String matKhau, QuyenDTO quyen, int trangThai) {
        this.tenDN = tenDN;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.email = email;
        this.matKhau = matKhau;
        this.quyen = quyen;
        this.trangThai = trangThai;
    }

    public QuyenDTO getQuyen() {
        return quyen;
    }

    public void setQuyen(QuyenDTO quyen) {
        this.quyen = quyen;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public boolean checkPassword(String password) {
        return this.matKhau.equals(password);
    }

    public static TaiKhoanDTO getFromResultSet(ResultSet rs) throws SQLException {
        TaiKhoanDTO tk = new TaiKhoanDTO();
        QuyenDTO quyen = new QuyenDTO();
        QuyenDAO quyendao = new QuyenDAO();
        quyen = quyendao.getquyen(rs.getString("MAQUYEN"));
        tk.setTenDN(rs.getString("TENDN"));
        tk.setTenNV(rs.getString("TENNV"));
        tk.setDiaChi(rs.getString("DIACHI"));
        tk.setQuyen(quyen);
        tk.setSDT(rs.getString("SDT"));
        tk.setEmail(rs.getString("EMAIL"));
        tk.setMatKhau(rs.getString("MATKHAU"));
        tk.setTrangThai(rs.getInt("TRANGTHAI"));
        return tk;
    }
}
