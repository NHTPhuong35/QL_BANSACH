package BUS;

import java.util.ArrayList;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import GUI.ShowDiaLog;

public class NhaCungCapBUS {
	private ArrayList<NhaCungCapDTO> ds;
	
    public NhaCungCapBUS() {
        ds = new ArrayList<>();
        init();
    }

    public void init() {
        NhaCungCapDAO dao = new NhaCungCapDAO();
        ds = dao.dsNhaCungCap();
    }
	
    private boolean KT_Ten(String ten) {
        String regex = "^[a-zA-ZÀ-ỹ\\s-/]{2,}$";
        return ten.matches(regex);
    }
    
    public String TaoMaNCC() {
        String maLonNhat = "NCC01";  // Khởi tạo mã lớn nhất ban đầu
        for (NhaCungCapDTO l : ds) {
            String maNCC = l.getMaNhaCungCap();  // Lấy mã nhà cung cấp hiện tại
            if (maNCC.compareTo(maLonNhat) > 0) {
                maLonNhat = maNCC;  // Cập nhật mã lớn nhất nếu tìm thấy mã lớn hơn
            }
        }
        
        String soPhanSo = maLonNhat.substring(3);  // Lấy phần số từ vị trí thứ 3 (sau 'NCC')
        int soCuoi;
    
        try {
            soCuoi = Integer.parseInt(soPhanSo);  // Chuyển phần số sang kiểu số nguyên
        } catch (NumberFormatException e) {
            soCuoi = 0;  // Nếu xảy ra lỗi, gán số cuối bằng 0
        }
    
        soCuoi++;  // Tăng giá trị phần số lên 1
    
        // Định dạng lại mã mới với tiền tố 'NCC', nếu số < 10 thì thêm '0' vào trước
        if (soCuoi < 10) {
            return String.format("NCC0%d", soCuoi);
        } else {
            return String.format("NCC%d", soCuoi);
        }
    }
    
    public boolean ThemNhaCungCap(NhaCungCapDTO l) {
        if (KT_Ten(l.getTenNhaCungCap())) {
            l.setMaNhaCungCap(TaoMaNCC());
            NhaCungCapDAO nccDAO = new NhaCungCapDAO();
            if (nccDAO.ThemNhaCungCap(l)) {
                new ShowDiaLog("Thêm nhà cung cấp thành công", ShowDiaLog.SUCCESS_DIALOG);
                return true;
            } else {
                new ShowDiaLog("Thêm nhà cung cấp thất bại", ShowDiaLog.ERROR_DIALOG);
                return false;
            }
        }
        if (!KT_Ten(l.getTenNhaCungCap())) {
            new ShowDiaLog("<html>Tên nhà cung cấp phải chứa tối thiểu 2 ký tự <br> ( Không nhập số ) )</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return false;
    }

    public boolean SuaNhaCungCap(NhaCungCapDTO l) {
        if (KT_Ten(l.getTenNhaCungCap())) {
            NhaCungCapDAO nccDAO = new NhaCungCapDAO();
            if (nccDAO.SuaNhaCungCap(l)) {
                new ShowDiaLog("Sửa nhà cung cấp thành công", ShowDiaLog.SUCCESS_DIALOG);
                return true;
            } else {
                new ShowDiaLog("Sửa nhà cung cấp thất bại", ShowDiaLog.ERROR_DIALOG);
                return false;
            }
        }
        if (!KT_Ten(l.getTenNhaCungCap())) {
            new ShowDiaLog("<html>Tên nhà cung cấp phải chứa tối thiểu 2 ký tự <br>( Không nhập số )</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return false;
    }
    
    public boolean XoaNhaCungCap(String maTL) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getMaNhaCungCap().equals(maTL)) {
                ds.remove(i);
                NhaCungCapDAO nccDAO = new NhaCungCapDAO();
                return nccDAO.XoaNhaCungCap(maTL);
            }
        }
        return false;
    }
    
    public NhaCungCapDTO layNCCTheoMa(String MaTL) {
        NhaCungCapDTO l = null;
        for (NhaCungCapDTO x : ds) {
            if (x.getMaNhaCungCap().equals(MaTL)) {
                l = new NhaCungCapDTO(x.getMaNhaCungCap(), x.getTenNhaCungCap());
                break;
            }
        }
        return l;
    }
    
    public ArrayList<NhaCungCapDTO> getDs() {
        return ds;
    }
    
    public static void main(String[] agrs) {
        NhaCungCapBUS nccBUS = new NhaCungCapBUS();         
    }
}
