package DTO;

public class TacGiaDTO {

    private String maTG;
    private String tenTG;
    private int trangThai;

    public TacGiaDTO() {
    }

    public TacGiaDTO(String maTG, String tenTG, int trangThai) {
        this.maTG = maTG;
        this.tenTG = tenTG;
        this.trangThai = trangThai;
    }

    public TacGiaDTO(String maTG, String tenTG) {
        this.maTG = maTG;
        this.tenTG = tenTG;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaTG() {
        return maTG;
    }

    public void setMaTG(String maTG) {
        this.maTG = maTG;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

}
