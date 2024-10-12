package DTO;

public class ChiTietPhieuNhapDTO {
    private String MAPN;
    private String MASACH;
    private double GIANHAP;
    private int SOLUONG;
    private double TONGTIEN;

    public ChiTietPhieuNhapDTO() {
    }
    
    public ChiTietPhieuNhapDTO(String MAPN, String MASACH, int SOLUONG, double TONGTIEN, double GIANHAP) {

        this.MAPN = MAPN;

        this.MASACH = MASACH;

        this.SOLUONG = SOLUONG;

        this.TONGTIEN = TONGTIEN;

        this.GIANHAP = GIANHAP;

    }

    // Getters and Setters
    public String getMAPN() {
        return MAPN;
    }

    public void setMAPN(String MAPN) {
        this.MAPN = MAPN;
    }

    public String getMASACH() {
        return MASACH;
    }

    public void setMASACH(String MASACH) {
        this.MASACH = MASACH;
    }

    public double getGIANHAP() {
        return GIANHAP;
    }

    public void setGIANHAP(double GIANHAP) {
        this.GIANHAP = GIANHAP;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public double getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(double TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }
    
}
