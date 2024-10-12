/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author nhatm
 */
public class ChiTietQuyenDTO {
    private String maquyen;
    private String machucnang;
    private String hanhdong;
    
    public ChiTietQuyenDTO(){   
    }
    
    public ChiTietQuyenDTO(String maquyen, String machucnang, String hanhdong){
        this.maquyen = maquyen;
        this.machucnang = machucnang;
        this.hanhdong = hanhdong;
    }
    
    public String getMaQuyen(){
        return maquyen;
    }
    
    public String getMaChucNang(){
        return machucnang;
    }
    
    public String getHanhDong(){
        return hanhdong;
    }
    
    public void setMaQuyen(String maquyen){
        this.maquyen = maquyen;
    }
    
    public void setMaChucNang(String machucnang){
        this.machucnang = machucnang;
    }
    
    public void setHanhDong(String hanhdong){
        this.hanhdong = hanhdong;
    }
}
