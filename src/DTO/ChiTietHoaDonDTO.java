/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class ChiTietHoaDonDTO {

    private String soHD, maSach;
    private double donGia;
    private int soLuong;
    private SanPhamDTO sp;

    public ChiTietHoaDonDTO(String soHD, String maSach, double donGia, int soLuong, SanPhamDTO sp) {
        this.soHD = soHD;
        this.maSach = maSach;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.sp = sp;
    }

    public ChiTietHoaDonDTO(String soHD, String maSach, int soLuong, double donGia) {
        this.soHD = soHD;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public ChiTietHoaDonDTO(String maSach, double donGia, int soLuong, SanPhamDTO sp) {
        this.maSach = maSach;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.sp = sp;
    }

    public String getSoHD() {
        return soHD;
    }

    public String getMaSach() {
        return maSach;
    }

    public double getdonGia() {
        return donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public SanPhamDTO getSp() {
        return sp;
    }

    public void setSoHD(String soHD) {
        this.soHD = soHD;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setdonGia(double donGia) {
        this.donGia = donGia;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setSp(SanPhamDTO sp) {
        this.sp = sp;
    }

}
