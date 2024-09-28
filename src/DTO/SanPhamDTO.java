package DTO;

public class SanPhamDTO {
    private String maSach, tenSach, maLoai, nxb;
    private int namXB, soLuong, trangthai;
    private double giaBia, giaBan;
    private String[] tenHinh;
    private String tacGia;
    private LoaiDTO loai;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String maSach, String tenSach, String maLoai, String nxb, int namXB, int soLuong, int trangthai, double giaBia, double giaBan, String[] tenHinh, String tacGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maLoai = maLoai;
        this.nxb = nxb;
        this.namXB = namXB;
        this.soLuong = soLuong;
        this.trangthai = trangthai;
        this.giaBia = giaBia;
        this.giaBan = giaBan;
        this.tenHinh = tenHinh;
        this.tacGia = tacGia;
    }
    
    public SanPhamDTO(String maSach, String tenSach, LoaiDTO loai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.loai = loai;
    }

    public LoaiDTO getLoai() {
        return loai;
    }

    public void setLoai(LoaiDTO loai) {
        this.loai = loai;
    }

    public String[] getTenHinh() {
        return tenHinh;
    }

    public void setTenHinh(String[] tenHinh) {
        this.tenHinh = tenHinh;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public double getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(double giaBia) {
        this.giaBia = giaBia;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
}
