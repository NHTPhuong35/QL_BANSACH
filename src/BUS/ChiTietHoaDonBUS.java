/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;
import java.util.ArrayList;

public class ChiTietHoaDonBUS {
    private ArrayList<ChiTietHoaDonDTO> dscthd;
    private String SoHD;
    
    public ChiTietHoaDonBUS(String SoHD) {
        dscthd = new ArrayList<>();
        this.SoHD = SoHD;
        init();
    }
    
    public ChiTietHoaDonBUS() {
        dscthd = new ArrayList<>();
        init();
    }
    
    public void init() {
        ChiTietHoaDonDAO dao = new ChiTietHoaDonDAO();
        dscthd = dao.LayChiTietMotHD(SoHD);
    }

    public ArrayList<ChiTietHoaDonDTO> getDscthd() {
        return dscthd;
    }
    
    public void ThemCTHoaDon(ChiTietHoaDonDTO ct) {
       ChiTietHoaDonDAO dao = new ChiTietHoaDonDAO();
       dao.ThemCTHoaDon(ct);
    }
    
//    public void ChiTietHD() {
//        for(ChiTietHoaDonDTO ct : dscthd) {
//            if(ct.getSoHD().equals(SoHD)) {
//                System.out.println(ct.getSoHD() + " " + ct.getMaSach() + " " + ct.getSoLuong() + " " + ct.getdonGia());
//            }
//        }
//    }
    
    public static void main(String[] agrs) {
        ChiTietHoaDonBUS khBUS = new ChiTietHoaDonBUS("HD01");
        
    }
}
