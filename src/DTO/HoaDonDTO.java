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
    private int TrangThai;
    private TaiKhoanDTO tk;
    private KhachHangDTO kh;


    public HoaDonDTO(String SoHD, String MaKH, String TenDN, String TGian, String NgayHD, double TienGiamGia, double TongTien, int TrangThai) {
        this.SoHD = SoHD;
        this.MaKH = MaKH;
        this.TenDN = TenDN;
        this.TGian = TGian;
        this.NgayHD = NgayHD;
        this.TienGiamGia = TienGiamGia;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public HoaDonDTO(String SoHD, String MaKH, String TenDN, String TGian, String NgayHD, double TienGiamGia, double TongTien) {
        this.SoHD = SoHD;
        this.MaKH = MaKH;
        this.TenDN = TenDN;
        this.TGian = TGian;
        this.NgayHD = NgayHD;
        this.TienGiamGia = TienGiamGia;
        this.TongTien = TongTien;
    }


    public HoaDonDTO(String MaKH, String TenDN, String TGian, String NgayHD, double TienGiamGia, double TongTien, TaiKhoanDTO tk, KhachHangDTO kh) {
        this.MaKH = MaKH;
        this.TenDN = TenDN;
        this.TGian = TGian;
        this.NgayHD = NgayHD;
        this.TienGiamGia = TienGiamGia;
        this.TongTien = TongTien;
        this.tk = tk;
        this.kh = kh;
    }

    public HoaDonDTO(String MaKH, String TenDN, double TienGiamGia, double TongTien) {
        this.MaKH = MaKH;
        this.TenDN = TenDN;
        this.TienGiamGia = TienGiamGia;
        this.TongTien = TongTien;
    }
    
    
    

    public TaiKhoanDTO getTk() {
        return tk;
    }

    public KhachHangDTO getKh() {
        return kh;
    }

    public void setTk(TaiKhoanDTO tk) {
        this.tk = tk;
    }

    public void setKh(KhachHangDTO kh) {
        this.kh = kh;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getTrangThai() {
        return TrangThai;
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

