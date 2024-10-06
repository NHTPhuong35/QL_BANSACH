/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class HoaDonBUS {

    private ArrayList<HoaDonDTO> dsHD;

    public HoaDonBUS() {
        dsHD = new ArrayList<>();
        init();
    }

    public void init() {
        HoaDonDAO hd = new HoaDonDAO();
        dsHD = hd.DanhSachHoaDon();
    }

    public ArrayList<HoaDonDTO> getDsHD() {
        return dsHD;
    }

    public void CapNhatTrangThaiHD(String soHD) {
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn hủy hóa đơn " + soHD + " không?", "Xác nhận hủy hóa đơn", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            HoaDonDAO dao = new HoaDonDAO();
            if(dao.CapNhatTrangThaiHD(soHD)) {
                JOptionPane.showMessageDialog(null, "Hóa đơn đã được hủy thành công!");
            }else{
                 JOptionPane.showMessageDialog(null, "Hóa đơn hủy thất bại!");
            }   
        }
    }
    
    }
