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

    private ArrayList<TaiKhoanDTO> dsTK; // danh sách tài khoản đang hoạt động/đã khoá (0/1)
    private ArrayList<TaiKhoanDTO> accountList; //danh sách tất cả tài khoản bao gồm đã xoá (0/1/2)
    
    public TaiKhoanBUS() {
        getList();
        
        dsTK = new ArrayList<>();
        for(TaiKhoanDTO tk : accountList){
            if(tk.getTrangThai() != 2){
                dsTK.add(tk);
            }
        }
    }

    public ArrayList<TaiKhoanDTO> getDsTK() {  
        return dsTK;
    }
    
    public void setDsTK(ArrayList<TaiKhoanDTO> dsTK) {
        this.dsTK = dsTK;
    }

    private void getList() {
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        accountList = new ArrayList<>();
        accountList = tkDAO.DanhSachTaiKhoan();
    }

    public boolean add(TaiKhoanDTO tk) {
        String tenDN = TaoMaTK();
        tk.setTenDN(tenDN);
        dsTK.add(tk);
        accountList.add(tk);
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        return tkDAO.themTaiKhoan(tk);
    }

    //Sủa tài khoản
    public boolean set(TaiKhoanDTO tk) {
        for (int i = 0; i < dsTK.size(); i++) {
            if (dsTK.get(i).getTenDN().equals(tk.getTenDN())) {
                dsTK.set(i, tk);
                accountList.set(i, tk);
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();
                return tkDAO.suaTaiKhoan(tk);
            }
        }
        return false;
    }

    public boolean delete(String tenDN) {
        for (int i = 0; i < dsTK.size(); i++) {
            if (dsTK.get(i).getTenDN().equals(tenDN)) {
                dsTK.remove(i);
                accountList.remove(i);
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();
                return tkDAO.xoaTaiKhoan(tenDN);
            }
        }
        return false;
    }

    public String TaoMaTK() {
        String maLonNhat = "NV01";
        for (TaiKhoanDTO tk : accountList) {
            String tenDN = tk.getTenDN();
            if (tenDN.compareTo(maLonNhat) > 0) {
                maLonNhat = tenDN;
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
            return String.format("NV0%d", soCuoi);
        } else {
            return String.format("NV%d", soCuoi);
        }
    }

    public String kiemTraTenNV(String tenNV) {
        // Kiểm tra tên không được để trống hoặc chứa khoảng trắng ở đầu/cuối
        if (tenNV == null || tenNV.trim().isEmpty()) {
            return "<html>Tên nhân viên không được để trống.</html>"; // Lỗi: Tên rỗng
        }
        if (!tenNV.equals(tenNV.trim())) {
            return "<html>Tên nhân viên không được có<br> khoảng trắng ở đầu hoặc cuối.</html>"; // Lỗi: Khoảng trắng thừa
        }

        // Kiểm tra tên chỉ chứa ký tự chữ cái có dấu và khoảng trắng
        if (!tenNV.matches("[a-zA-Z\\p{L}\\s]+")) {
            return "<html>Tên nhân viên chỉ được chứa<br> chữ cái có dấu và khoảng trắng.</html>"; // Lỗi: Ký tự không hợp lệ
        }

        return "Hợp lệ"; // Thành công: Tên hợp lệ
    }

    public String kiemTraDiaChi(String diaChi) {
        // Kiểm tra địa chỉ không được để trống hoặc chứa khoảng trắng ở đầu/cuối
        if (diaChi == null || diaChi.trim().isEmpty() || !diaChi.equals(diaChi.trim())) {
            return "<html>Địa chỉ không được để trống hoặc<br> có khoảng trắng ở đầu/cuối.</html>";
        }

        // Kiểm tra độ dài của địa chỉ (giới hạn từ 5 đến 100 ký tự)
        if (diaChi.length() < 5 || diaChi.length() > 100) {
            return "<html>Địa chỉ phải có độ dài từ 5 đến 100 ký tự.</html>";
        }

        // Kiểm tra địa chỉ chỉ chứa các ký tự hợp lệ (chữ cái có dấu, số, khoảng trắng, dấu phẩy, dấu chấm, dấu gạch ngang)
        if (!diaChi.matches("[a-zA-Z0-9\\p{L}\\s,.-]+")) {
            return "<html>Địa chỉ chỉ được chứa chữ cái, số,<br> khoảng trắng và các ký tự hợp lệ (,.-).</html>";
        }

        return "Hợp lệ"; // Nếu hợp lệ, trả về chuỗi "Địa chỉ hợp lệ."
    }

    public String kiemTraSoDienThoai(String SDT) {
        // Kiểm tra số điện thoại bị rỗng
        if (SDT == null || SDT.trim().isEmpty()) {
            return "<html>Số điện thoại không được để trống.</html>"; // Lỗi: Số điện thoại rỗng
        }

        // Kiểm tra số điện thoại không đúng định dạng
        if (!SDT.matches("\\d{10}")) {
            return "<html>Số điện thoại phải là số<br> có đúng 10 chữ số.</html>"; // Lỗi: Số điện thoại không đúng định dạng
        }
        
        // Kiểm tra số điện thoại có bắt đầu bằng số 0
        if (!SDT.startsWith("0")) {
            return "<html>Số điện thoại phải bắt đầu bằng số 0.</html>"; // Lỗi: Số điện thoại không bắt đầu bằng 0
        }

        // Kiểm tra số điện thoại trùng lặp
        for (TaiKhoanDTO tk : accountList) {
            if (tk.getSDT().equals(SDT)) {
                return "<html>Số điện thoại này đã tồn tại.</html>"; // Lỗi: Số điện thoại bị trùng
            }
        }

        return "Hợp lệ"; // Thành công: Số điện thoại hợp lệ
    }

    public String kiemTraEmail(String email) {
        // Kiểm tra email không được để trống
        if (email == null || email.trim().isEmpty()) {
            return "<html>Email không được để trống.</html>";
        }

        // Kiểm tra email không chứa khoảng trắng ở đầu/cuối
        if (!email.equals(email.trim())) {
            return "<html>Email không được chứa khoảng trắng<br> ở đầu hoặc cuối.</html>";
        }

        // Biểu thức chính quy để kiểm tra email hợp lệ
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        // Kiểm tra email có khớp với biểu thức chính quy hay không
        if (!email.matches(emailRegex)) {
            return "<html>Email không đúng định dạng<br> (ví dụ: user@gmail.com).</html>";
        }

        return "Hợp lệ"; // Email hợp lệ
    }

    public String kiemTraMatKhau(String matKhau) {
        // Kiểm tra mật khẩu không được để trống
        if (matKhau == null || matKhau.trim().isEmpty()) {
            return "<html>Mật khẩu không được để trống.</html>";
        }

        // Kiểm tra độ dài mật khẩu
        if (matKhau.length() < 5 || matKhau.length() > 50) {
            return "<html>Mật khẩu phải có độ dài từ 5 đến 50 ký tự.</html>";
        }

        // Kiểm tra mật khẩu có chứa ít nhất một chữ cái viết hoa
        if (!matKhau.matches(".*[A-Z].*")) {
            return "<html>Mật khẩu phải chứa ít nhất<br> một chữ cái viết hoa.</html>";
        }

        // Kiểm tra mật khẩu có chứa ít nhất một số
        if (!matKhau.matches(".*\\d.*")) {
            return "<html>Mật khẩu phải chứa ít nhất một số.</html>";
        }

        // Kiểm tra mật khẩu có chứa ít nhất một ký tự đặc biệt
        if (!matKhau.matches(".*[@#$%^&+=!].*")) {
            return "<html>Mật khẩu phải chứa ít nhất<br> một ký tự đặc biệt.</html>";
        }

        // Kiểm tra mật khẩu không chứa khoảng trắng
        if (matKhau.contains(" ")) {
            return "<html>Mật khẩu không được chứa khoảng trắng.</html>";
        }
//
//        // Kiểm tra mật khẩu không chứa ký tự dấu tiếng Việt
//        if (!matKhau.matches("[a-zA-Z0-9@#$%^&+=!]+")) {
//            return "<html>Mật khẩu không được chứa ký tự dấu.</html>";
//        }

        return "Hợp lệ"; // Trả về "Hợp lệ" nếu mật khẩu đúng
    }
    
    public String getTenNhanVien(String MaNV) {
        for (TaiKhoanDTO tk : accountList) {
            if (tk.getTenDN().equals(MaNV)) {
                return tk.getTenNV();
            }
        }
        return "";
    }
}