/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanBUS {
    private ArrayList<TaiKhoanDTO> dsTK;

    public TaiKhoanBUS() {
        getList();
    }

    public ArrayList<TaiKhoanDTO> getDsTK() {
        return dsTK;
    }

    public void setDsTK(ArrayList<TaiKhoanDTO> dsTK) {
        this.dsTK = dsTK;
    }
    
    private void getList() {
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        dsTK = new ArrayList<>();
        dsTK = tkDAO.DanhSachTaiKhoan();
    }
    
    public boolean add(TaiKhoanDTO tk){
        dsTK.add(tk);
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        return tkDAO.themTaiKhoan(tk);
    }
    
    public boolean set(TaiKhoanDTO tk){
        for(int i=0; i<dsTK.size(); i++){
            if(dsTK.get(i).getTenDN().equals(tk.getTenDN())){
                dsTK.set(i, tk);
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();
                return tkDAO.suaTaiKhoan(tk);
            }
        }
        return false;
    }
    
    public boolean delete(String tenDN, Boolean ktra){
        for(int i=0; i<dsTK.size(); i++){
            if(dsTK.get(i).getTenDN().equals(tenDN)){
                dsTK.remove(i);
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();
                return tkDAO.xoaTaiKhoan(tenDN, ktra);
            }
        }
        return false;
    }
    
    public String getTenNhanVien(String MaNV) {
        for(TaiKhoanDTO tk : dsTK) {
            if(tk.getTenDN().equals(MaNV)) {
                return tk.getTenNV();
            }
        }
        return "";
    }
}
