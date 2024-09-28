package DTO;

public class TacGiaDTO {
    private String maTG;
    private String tenTG;

    public TacGiaDTO() {
    }

    public TacGiaDTO(String maTG, String tenTG) {
        this.maTG = maTG;
        this.tenTG = tenTG;
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
