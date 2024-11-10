
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class NhaCungCapDTO {

    private String MaNhaCungCap, TenNhaCungCap, DiaChi, Sdt, Email;
    private int TrangThai;


    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(String MaNhaCungCap, String TenNhaCungCap, String DiaChi, String Email, String Sdt) {
        this.MaNhaCungCap = MaNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.DiaChi = DiaChi;
        this.Sdt = Sdt;
        this.Email = Email;
    }

    public NhaCungCapDTO(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public NhaCungCapDTO(String MaNhaCungCap, String TenNhaCungCap, String DiaChi, String Email, String Sdt, int TrangThai) {
        this.MaNhaCungCap = MaNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.DiaChi = DiaChi;
        this.Sdt = Sdt;
        this.Email = Email;
        this.TrangThai = TrangThai;
    }
    
    public String getMaNhaCungCap() {
        return MaNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }
    
    public String getDiaChi() {
        return DiaChi;
    }
    
    public String getEmail() {
        return Email;
    }
    
    public String getSdt() {
        return Sdt;
    }
    


    public int getTrangThai() {
        return TrangThai;
    }

    public void setMaNhaCungCap(String MaNhaCungCap) {
        this.MaNhaCungCap = MaNhaCungCap;
    }
    
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public void setSdt(String sdt) {
        this.Sdt = sdt;
    }
    
    public void setDiaChi(String diachi) {
        this.DiaChi = diachi;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

}
