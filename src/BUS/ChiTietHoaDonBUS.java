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
    
    public void init() {
        ChiTietHoaDonDAO dao = new ChiTietHoaDonDAO();
        dscthd = dao.LayChiTietMotHD(SoHD);
    }

    public ArrayList<ChiTietHoaDonDTO> getDscthd() {
        return dscthd;
    }
    
}
