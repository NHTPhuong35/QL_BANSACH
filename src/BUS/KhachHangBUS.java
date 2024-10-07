/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import GUI.ShowDiaLog;
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

    private boolean KT_Ten(String ten) {
        String regex = "^[a-zA-ZÀ-ỹ\\s]{2,}$";
        return ten.matches(regex);
    }

    private boolean KT_SDT(String sdt) {
        String regex = "^0\\d{9}$";
        return sdt.matches(regex);
    }

    private boolean KT_SDTTT(String sdt) {
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
        if (KT_Ten(kh.getTenKh()) && KT_SDT(kh.getSdt()) && !KT_SDTTT(kh.getSdt())) {
//            kh.setMaKh(TaoMaKH());
            KhachHangDAO khDAO = new KhachHangDAO();
            if (khDAO.ThemKhachHang(kh)) {
                new ShowDiaLog("Thêm khách hàng thành công", ShowDiaLog.SUCCESS_DIALOG);
                return true;
            } else {
                new ShowDiaLog("Thêm khách hàng thất bại", ShowDiaLog.ERROR_DIALOG);
                return false;
            }
        }
        if (!KT_Ten(kh.getTenKh())) {
            new ShowDiaLog("Tên phải chứa tối thiểu 2 ký tự \n và không chứa ký tự đặc biệt", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        if (!KT_SDT(kh.getSdt())) {
            new ShowDiaLog("Số điện thoại gồm 10 và sô bắt đầu bằng số 0", ShowDiaLog.ERROR_DIALOG);
            return false;
        }

        if (KT_SDTTT(kh.getSdt())) {
            new ShowDiaLog("Số điện thoại đã tồn tại", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return false;
    }

    public boolean SuaKhachHang(KhachHangDTO kh) {
        if (KT_Ten(kh.getTenKh()) && KT_SDT(kh.getSdt())) {
            KhachHangDAO khDAO = new KhachHangDAO();
            if (khDAO.SuaKhachHang(kh)) {
                new ShowDiaLog("Sửa khách hàng thành công", ShowDiaLog.SUCCESS_DIALOG);
                return true;
            } else {
                new ShowDiaLog("Sửa khách hàng thất bại", ShowDiaLog.ERROR_DIALOG);
                return false;
            }
        }
        if (!KT_Ten(kh.getTenKh())) {
            new ShowDiaLog("Tên phải chứa tối thiểu 2 ký tự \n và không chứa ký tự đặc biệt", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        if (!KT_SDT(kh.getSdt())) {
            new ShowDiaLog("Số điện thoại gồm 10 và sô bắt đầu bằng số 0", ShowDiaLog.ERROR_DIALOG);
            return false;
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

    public static void main(String[] agrs) {
        KhachHangBUS khBUS = new KhachHangBUS();

        System.out.println(khBUS.getTenKH("KH01"));
    }
}

