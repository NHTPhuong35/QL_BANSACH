
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class NhaCungCapDTO {

    private String MaNhaCungCap, TenNhaCungCap;
    private int TrangThai;


    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(String MaNhaCungCap, String TenNhaCungCap) {
        this.MaNhaCungCap = MaNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public NhaCungCapDTO(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public NhaCungCapDTO(String MaNhaCungCap, String TenNhaCungCap, int TrangThai) {
        this.MaNhaCungCap = MaNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.TrangThai = TrangThai;
    }
    
    public String getMaNhaCungCap() {
        return MaNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setMaNhaCungCap(String MaNhaCungCap) {
        this.MaNhaCungCap = MaNhaCungCap;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

}
