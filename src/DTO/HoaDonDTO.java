/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class HoaDonDTO {
    private String SoHD;
    private String MaKH;
    private String TenDN;
    private String TGian;
    private String NgayHD;
    private double TienGiamGia;
    private double TongTien;

    public HoaDonDTO(String SoHD, String MaKH, String TenDN, String TGian, String NgayHD, double TienGiamGia, double TongTien) {
        this.SoHD = SoHD;
        this.MaKH = MaKH;
        this.TenDN = TenDN;
        this.TGian = TGian;
        this.NgayHD = NgayHD;
        this.TienGiamGia = TienGiamGia;
        this.TongTien = TongTien;
    }

    public String getSoHD() {
        return SoHD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public String getTenDN() {
        return TenDN;
    }

    public String getTGian() {
        return TGian;
    }

    public String getNgayHD() {
        return NgayHD;
    }

    public double getTienGiamGia() {
        return TienGiamGia;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setSoHD(String SoHD) {
        this.SoHD = SoHD;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setTenDN(String TenDN) {
        this.TenDN = TenDN;
    }

    public void setTGian(String TGian) {
        this.TGian = TGian;
    }

    public void setNgayHD(String NgayHD) {
        this.NgayHD = NgayHD;
    }

    public void setTienGiamGia(double TienGiamGia) {
        this.TienGiamGia = TienGiamGia;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
    
    
}
