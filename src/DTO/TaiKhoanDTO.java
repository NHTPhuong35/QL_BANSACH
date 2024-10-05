/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

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

}
