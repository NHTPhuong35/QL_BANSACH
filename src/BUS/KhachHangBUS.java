/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class KhachHangBUS {

    private ArrayList<KhachHangDTO> ds;

    public KhachHangBUS() {
        ds = new ArrayList<>();
        init();
    }

    public void init() {
        KhachHangDAO dao = new KhachHangDAO();
        ds = dao.dsKhachHang();
    }

    public boolean checkPhoneExits(String sdt) {
        for (KhachHangDTO kh : ds) {
            if (kh.getSdt().equals(sdt)) {
                return true;
            }
        }
        return false;
    }

    public String TaoMaKH() {
        String maLonNhat = "KH01";
        for (KhachHangDTO kh : ds) {
            String maKH = kh.getMaKh();
            if (maKH.compareTo(maLonNhat) > 0) {
                maLonNhat = maKH;
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
            return String.format("KH0%d", soCuoi);
        } else {
            return String.format("KH%d", soCuoi);
        }
    }

    public KhachHangDTO layKHTheoMa(String MaKH) {
        KhachHangDTO kh = null;
        for (KhachHangDTO x : ds) {
            if (x.getMaKh().equals(MaKH)) {
                kh = new KhachHangDTO(x.getMaKh(), x.getTenKh(), x.getSdt(), x.getDiemTichluy());
                break;
            }
        }
        return kh;
    }

    public ArrayList<KhachHangDTO> getDs() {
        return ds;
    }

    public boolean ThemKhachHang(KhachHangDTO kh) {
        String name = kh.getTenKh();
        String phone = kh.getSdt();
        String nameRegex = "^(?! )[\\p{L} .'-]{1,35}(?<! )$"; 
        String phoneRegex = "^0[0-9]{9}$"; 

        if (phone != null && phone.matches(phoneRegex) && !checkPhoneExits(phone)) {
            if (name != null && name.matches(nameRegex)) {
                KhachHangDAO khDAO = new KhachHangDAO();
                return khDAO.ThemKhachHang(kh);
            }
        }
        return false;
    }

    public boolean SuaKhachHang(KhachHangDTO kh) {
        String name = kh.getTenKh();
        String phone = kh.getSdt();
        String nameRegex = "^(?! )[\\p{L} .'-]{1,35}(?<! )$";
        String phoneRegex = "^0[0-9]{9}$";

        if (name != null && name.matches(nameRegex) && phone.matches(phoneRegex) && phone != null) {
            KhachHangDAO khDAO = new KhachHangDAO();
            return khDAO.SuaKhachHang(kh);
        }
        return false;
    }

    public String getTenKH(String MaKH) {
        KhachHangBUS khBUS = new KhachHangBUS();
        ArrayList<KhachHangDTO> dsKH = khBUS.getDs();
        for (KhachHangDTO x : dsKH) {
            if (x.getMaKh().equals(MaKH)) { // So sánh mã khách hàng
                return x.getTenKh(); // Trả về tên khách hàng
            }
        }
        return "";
    }

    public void CapNhatDiemTL(String MaKH, double diem) {
        KhachHangDAO khDAO = new KhachHangDAO();
        khDAO.CapNhatDiemTL(MaKH, diem);
    }
}
