/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.Calendar;

public class SanPhamBUS {

    private ArrayList<SanPhamDTO> dsSP;

    public SanPhamBUS() {
        getList();
    }

    public ArrayList<SanPhamDTO> getDsSP() {
        return dsSP;
    }

    public void setDsSP(ArrayList<SanPhamDTO> dsSP) {
        this.dsSP = dsSP;
    }

    private void getList() {
        SanPhamDAO spDAO = new SanPhamDAO();
        dsSP = spDAO.DanhSachSanPham();
    }

    public boolean add(SanPhamDTO sp) {
        String maSP = TaoMaSP();
        sp.setMaSach(maSP);
        dsSP.add(sp);
        SanPhamDAO spDAO = new SanPhamDAO();
        return spDAO.themSanPham(sp);
    }

    public boolean set(SanPhamDTO sp) {
        for (int i = 0; i < dsSP.size(); i++) {
            if (dsSP.get(i).getMaSach().equals(sp.getMaSach())) {
                dsSP.set(i, sp);
                SanPhamDAO spDAO = new SanPhamDAO();
                return spDAO.suaSanPham(sp);
            }
        }
        return false;
    }

    public boolean delete(String maSP, Boolean ktraPN) {
        for (int i = 0; i < dsSP.size(); i++) {
            if (dsSP.get(i).getMaSach().equals(maSP)) {
                dsSP.remove(i);
                SanPhamDAO spDAO = new SanPhamDAO();
                return spDAO.xoaSanPham(maSP, ktraPN);
            }
        }
        return false;
    }

    public String TaoMaSP() {
        String maLonNhat = "SP01";
        for (SanPhamDTO sp : dsSP) {
            String maSP = sp.getMaSach();
            if (maSP.compareTo(maLonNhat) > 0) {
                maLonNhat = maSP;
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
            return String.format("SP0%d", soCuoi);
        } else {
            return String.format("SP%d", soCuoi);
        }
    }

    public ArrayList<SanPhamDTO> getDanhSachBan() {
        SanPhamDAO dao = new SanPhamDAO();
        ArrayList<SanPhamDTO> list_all = dao.DanhSachSanPham();
        ArrayList<SanPhamDTO> list = new ArrayList<>();
        for (SanPhamDTO sp : list_all) {
            if (sp.getGiaBan() > 0 && sp.getTrangthai() == 1) {
                list.add(sp);
            }
        }
        return list;
    }

    public void CapNhatSoLuongSP(String MaSach, int SoLuong) {
        SanPhamDAO dao = new SanPhamDAO();
        dao.CapNhatSoLuongSP(MaSach, SoLuong);
    }

    public SanPhamDTO getSP(String MaSach) {
        SanPhamDAO spDAO = new SanPhamDAO();
        ArrayList<SanPhamDTO> ds = spDAO.DanhSachSanPham();
        for (SanPhamDTO sp : ds) {
            if (sp.getMaSach().equals(MaSach)) {
                return sp;
            }
        }
        return null;
    }

    public String kiemTraTenSach(String tenSach) {
        // Kiểm tra tên sách không được để trống hoặc chứa khoảng trắng ở đầu/cuối
        if (tenSach == null || tenSach.trim().isEmpty()) {
            return "<html>Tên sách không được để trống.</html>"; // Lỗi: Tên rỗng
        }

        // Kiểm tra tên sách có độ dài từ 3 đến 50 ký tự
        if (tenSach.length() < 3 || tenSach.length() > 50) {
            return "<html>Tên sách phải có độ dài từ 3 đến 50 ký tự.</html>"; // Lỗi: Độ dài không hợp lệ
        }

        // Kiểm tra tên sách không chứa khoảng trắng ở đầu hoặc cuối
        if (!tenSach.equals(tenSach.trim())) {
            return "<html>Tên sách không được có<br> khoảng trắng ở đầu hoặc cuối.</html>"; // Lỗi: Khoảng trắng thừa
        }

        // Kiểm tra tên sách chỉ chứa chữ cái tiếng Việt có dấu, số, khoảng trắng, dấu ngoặc đơn và dấu gạch ngang
        String tenSachRegex = "[a-zA-Z0-9\\p{L}\\s()\\-]+";
        if (!tenSach.matches(tenSachRegex)) {
            return "<html>Tên sách chỉ được chứa chữ cái, số,<br> dấu ngoặc đơn và dấu gạch ngang.</html>"; // Lỗi: Ký tự không hợp lệ
        }

        return "Hợp lệ"; // Thành công: Tên sách hợp lệ
    }

    public String kiemTraTenNXB(String tenNXB) {
        /// Kiểm tra tên không được để trống hoặc chứa khoảng trắng ở đầu/cuối
        if (tenNXB == null || tenNXB.trim().isEmpty()) {
            return "<html>Tên nhà xuất bản không được để trống.</html>"; // Lỗi: Tên rỗng
        }
        if (!tenNXB.equals(tenNXB.trim())) {
            return "<html>Tên nhà xuất bản không được có<br> khoảng trắng ở đầu hoặc cuối.</html>"; // Lỗi: Khoảng trắng thừa
        }

        // Kiểm tra tên chỉ chứa ký tự chữ cái có dấu và khoảng trắng
        if (!tenNXB.matches("[a-zA-Z\\p{L}\\s]+")) {
            return "<html>Tên nhà xuất bản chỉ được chứa<br> chữ cái có dấu và khoảng trắng.</html>"; // Lỗi: Ký tự không hợp lệ
        }

        return "Hợp lệ"; // Thành công: Tên hợp lệ
    }

    public String kiemTraGiaBia(String giaBia) {
        // Kiểm tra giá bìa không được để trống
        if (giaBia == null || giaBia.trim().isEmpty()) {
            return "<html>Giá bìa không được để trống.</html>"; // Lỗi: Giá bìa rỗng
        }

        return "Hợp lệ"; // Thành công: Giá bìa hợp lệ
    }

    public String kiemTraNamXB(String namXB) {
        int namHienTai = Calendar.getInstance().get(Calendar.YEAR);
        if (namXB == null || namXB.isEmpty()) {
            return "<html>Năm xuất bản không được rỗng</html>";
        }
        // Kiểm tra năm xuất bản
        if (Integer.parseInt(namXB) < 1900) {
            return "<html>Năm xuất bản không hợp lệ:<br> Phải lớn hơn hoặc bằng 1900.</html>";
        }
        if (Integer.parseInt(namXB) > namHienTai) {
            return "<html>Năm xuất bản không hợp lệ:<br> Phải &lt= " + namHienTai + ".</html>";
        }
        return "Hợp lệ";
    }
}
