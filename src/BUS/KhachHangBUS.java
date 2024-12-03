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

    public boolean validatePhone(String sdt) {
        KhachHangDAO dao = new KhachHangDAO();
        return dao.checkPhoneExits(sdt);
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
        KhachHangDAO khDAO = new KhachHangDAO();
        return khDAO.ThemKhachHang(kh);
    }

    public boolean SuaKhachHang(KhachHangDTO kh) {
        KhachHangDAO khDAO = new KhachHangDAO();
        return khDAO.SuaKhachHang(kh);
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
