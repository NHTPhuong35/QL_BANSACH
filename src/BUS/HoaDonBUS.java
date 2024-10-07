/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    public String TaoMaHD() {
        String maLonNhat = "HD01";
        for (HoaDonDTO hd : dsHD) {
            String maHD = hd.getSoHD();
            if (maHD.compareTo(maLonNhat) > 0) {
                maLonNhat = maHD;
            }
        }
        String soPhanSo = maLonNhat.substring(2);
        int soCuoi;

        try {
            soCuoi = Integer.parseInt(soPhanSo);
        } catch (NumberFormatException e) {
            soCuoi = 0;
        }

        soCuoi++;

        if (soCuoi < 10) {
            return String.format("HD0%d", soCuoi);
        } else {
            return String.format("HD%d", soCuoi);
        }
    }

    public boolean CapNhatTrangThaiHD(String soHD) {
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn hủy hóa đơn " + soHD + " không?", "Xác nhận hủy hóa đơn", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            HoaDonDAO dao = new HoaDonDAO();
            if (dao.CapNhatTrangThaiHD(soHD)) {
                JOptionPane.showMessageDialog(null, "Hóa đơn đã được hủy thành công!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Hóa đơn hủy thất bại!");
                return false;
            }
        }
        return false;
    }

    public boolean ThemHoaDon(HoaDonDTO hd) {
        HoaDonDAO dao = new HoaDonDAO();
//        hd.setSoHD(TaoMaHD());

        LocalTime thoiGian = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m:s"); // Định dạng 10:10:2
        String formattedThoiGian = thoiGian.format(timeFormatter);

        LocalDate ngayHD = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d"); // Định dạng 2024-10-5
        String formattedNgayHD = ngayHD.format(dateFormatter);

        hd.setTGian(formattedThoiGian);
        hd.setNgayHD(formattedNgayHD);
        return dao.ThemHoaDon(hd);
    }

    public HoaDonDTO getHD(String MaHD) {
        HoaDonBUS hdBUS = new HoaDonBUS();
        ArrayList<HoaDonDTO> ds = hdBUS.dsHD;
        for (HoaDonDTO hd : ds) {
            if (hd.getSoHD().equals(MaHD)) {
                return hd;
            }
        }
        return null;
    }
    
    public static void main(String[] args){
        HoaDonBUS bus = new HoaDonBUS();
        HoaDonDTO hd = new HoaDonDTO("KH01", "NV02", 0, 100000);
        bus.ThemHoaDon(hd);
    }

}

