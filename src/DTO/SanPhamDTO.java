package DTO;

import java.util.ArrayList;

public class SanPhamDTO {

    private String maSach, tenSach, nxb;
    private int namXB, soLuong, trangthai;
    private double giaBia, giaBan;
    public ArrayList<String> tenHinh;
    private ArrayList<TacGiaDTO> tacGia;
    private ArrayList<LoaiDTO> loai;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String maSach, String tenSach, String nxb, int namXB, int soLuong, int trangthai, double giaBia, double giaBan, ArrayList<String> tenHinh, ArrayList<TacGiaDTO> tacGia, ArrayList<LoaiDTO> loai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.nxb = nxb;
        this.namXB = namXB;
        this.soLuong = soLuong;
        this.trangthai = trangthai;
        this.giaBia = giaBia;
        this.giaBan = giaBan;
        this.tenHinh = tenHinh;
        this.tacGia = tacGia;
        this.loai = loai;
    }

    public SanPhamDTO(String maSach, String tenSach, ArrayList<LoaiDTO> loai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.loai = loai;
    }

    public String getTenHinhToString(){
        StringBuilder hinh = new StringBuilder();
        for (String h : tenHinh) {
            if (hinh.length() > 0) {
                hinh.append(", ");
            }
            hinh.append(h);
        }
        return hinh.toString();
    }
    
    public String getLoaiToString() { //Dạng ten a, b, c
        StringBuilder tenLoai = new StringBuilder();
        for (LoaiDTO loaiDTO : loai) {
            if (tenLoai.length() > 0) {
                tenLoai.append(", ");
            }
            tenLoai.append(loaiDTO.getTenLoai());
        }
        return tenLoai.toString();
    }

    public String getTacGiaToString() {
        StringBuilder tenTacGia = new StringBuilder();
        for (TacGiaDTO tacGiaDTO : tacGia) {
            if (tenTacGia.length() > 0) {
                tenTacGia.append(", ");
            }
            tenTacGia.append(tacGiaDTO.getTenTG());
        }
        return tenTacGia.toString();
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

    public ArrayList<String> getTenHinh() {
        return tenHinh;
    }

    public void setTenHinh(ArrayList<String> tenHinh) {
        this.tenHinh = tenHinh;
    }

    public ArrayList<TacGiaDTO> getTacGia() {
        return tacGia;
    }

    public void setTacGia(ArrayList<TacGiaDTO> tacGia) {
        this.tacGia = tacGia;
    }

    public ArrayList<LoaiDTO> getLoai() {
        return loai;
    }

    public void setLoai(ArrayList<LoaiDTO> loai) {
        this.loai = loai;
    }

}
