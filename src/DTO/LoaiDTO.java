
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class LoaiDTO {

    private String MaLoai, TenLoai;
    private int TrangThai;


    public LoaiDTO() {
    }

    public LoaiDTO(String MaLoai, String TenLoai) {
        this.MaLoai = MaLoai;
        this.TenLoai = TenLoai;
    }

    public LoaiDTO(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public LoaiDTO(String MaLoai, String TenLoai, int TrangThai) {
        this.MaLoai = MaLoai;
        this.TenLoai = TenLoai;
        this.TrangThai = TrangThai;
    }
    
    public String getMaLoai() {
        return MaLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

}
