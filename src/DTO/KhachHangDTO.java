/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class KhachHangDTO {
    private String MaKh, TenKh, Sdt;
    private double  DiemTichluy;
    private int TrangThai;

    public KhachHangDTO(String MaKh, String TenKh, String Sdt, double DiemTichluy, int TrangThai) {
        this.MaKh = MaKh;
        this.TenKh = TenKh;
        this.Sdt = Sdt;
        this.DiemTichluy = DiemTichluy;
        this.TrangThai = TrangThai;
    }

    public String getMaKh() {
        return MaKh;
    }

    public String getTenKh() {
        return TenKh;
    }

    public String getSdt() {
        return Sdt;
    }

    public double getDiemTichluy() {
        return DiemTichluy;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setMaKh(String MaKh) {
        this.MaKh = MaKh;
    }

    public void setTenKh(String TenKh) {
        this.TenKh = TenKh;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public void setDiemTichluy(double DiemTichluy) {
        this.DiemTichluy = DiemTichluy;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
