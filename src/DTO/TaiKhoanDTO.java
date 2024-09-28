/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class TaiKhoanDTO {
    private String TenDn,TenNV,DiaChi, Sdt, Email, MaQuyen, MatKhau;
    private int TrangThai;

    public TaiKhoanDTO() {
    }

    
    public TaiKhoanDTO(String TenDn, String TenNV, String DiaChi, String Sdt, String Email, String MaQuyen, String MatKhau, int TrangThai) {
        this.TenDn = TenDn;
        this.TenNV = TenNV;
        this.DiaChi = DiaChi;
        this.Sdt = Sdt;
        this.Email = Email;
        this.MaQuyen = MaQuyen;
        this.MatKhau = MatKhau;
        this.TrangThai = TrangThai;
    }

    public String getTenDn() {
        return TenDn;
    }

    public String getTenNV() {
        return TenNV;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public String getEmail() {
        return Email;
    }

    public String getMaQuyen() {
        return MaQuyen;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTenDn(String TenDn) {
        this.TenDn = TenDn;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setMaQuyen(String MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
