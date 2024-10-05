package DTO;

import java.util.Date;


public class PhieuNhapDTO {
    private String maPN;
    private String maNCC;
    private String tenDN;
    private Date ngayNhap;
    private double tongTien;
    private int trangThai;
    
    public PhieuNhapDTO(String maPN, String maNCC, String tenDN, Date ngayNhap, double tongTien, int trangThai) {
        this.maPN = maPN;
        this.maNCC = maNCC;
        this.tenDN = tenDN;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public String getMaPN() {
        return maPN;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getTenDN() {
        return tenDN;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
