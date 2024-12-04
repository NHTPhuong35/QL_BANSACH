package BUS;

import java.util.ArrayList;

import DAO.TheLoaiDAO;
import DTO.LoaiDTO;
import GUI.ShowDiaLog;

public class TheLoaiBUS {
	private ArrayList<LoaiDTO> ds;
	
    public TheLoaiBUS() {
        ds = new ArrayList<>();
        init();
    }

    public void init() {
        TheLoaiDAO dao = new TheLoaiDAO();
        ds = dao.dsTheLoai();
    }
	
    private boolean KT_Ten(String ten) {
        String regex = "^[a-zA-ZÀ-ỹ\\s-/]{2,}$";
        return ten.matches(regex);
    }
    
    public String TaoMaLOAI() {
        String maLonNhat = "L01";
        for (LoaiDTO l : ds) {
            String maLOAI = l.getMaLoai();
            if (maLOAI.compareTo(maLonNhat) > 0) {
                maLonNhat = maLOAI;
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
            return String.format("L0%d", soCuoi);
        } else {
            return String.format("L%d", soCuoi);
        }
    }
    public boolean ThemTheLoai(LoaiDTO l) {
    	String tenTl = l.getTenLoai();
        if (tenTl == null || tenTl.trim().isEmpty()) {
            new ShowDiaLog("<html>Tên thể loại không được để trống.</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        if (!tenTl.equals(tenTl.trim())) {
            new ShowDiaLog("<html>Tên thể loại không được có<br> khoảng trắng ở đầu hoặc cuối.</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        if (KT_Ten(tenTl)) {
            l.setMaLoai(TaoMaLOAI());
            TheLoaiDAO tlDAO = new TheLoaiDAO();
            if (tlDAO.ThemTheLoai(l)) {
                new ShowDiaLog("Thêm thể loại thành công", ShowDiaLog.SUCCESS_DIALOG);
                return true;
            } else {
                new ShowDiaLog("Thêm thể loại thất bại", ShowDiaLog.ERROR_DIALOG);
                return false;
            }
        }
        if (!KT_Ten(tenTl)) {
            new ShowDiaLog("<html>Tên thể loại phải chứa tối thiểu 2 ký tự <br> ( Không nhập số ) )</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return false;
    }

    public boolean SuaTheLoai(LoaiDTO l) {
    	String tenTl = l.getTenLoai();
        if (tenTl == null || tenTl.trim().isEmpty()) {
            new ShowDiaLog("<html>Tên thể loại không được để trống.</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        if (!tenTl.equals(tenTl.trim())) {
            new ShowDiaLog("<html>Tên thể loại không được có<br> khoảng trắng ở đầu hoặc cuối.</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        if (KT_Ten(tenTl)) {
            TheLoaiDAO tlDAO = new TheLoaiDAO();
            if (tlDAO.SuaTheLoai(l)) {
                new ShowDiaLog("Sửa thể loại thành công", ShowDiaLog.SUCCESS_DIALOG);
                return true;
            } else {
                new ShowDiaLog("Sửa thể loại thất bại", ShowDiaLog.ERROR_DIALOG);
                return false;
            }
        }
        if (!KT_Ten(tenTl)) {
            new ShowDiaLog("<html>Tên thể loại phải chứa tối thiểu 2 ký tự <br>( Không nhập số )</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return false;
    }
    
    public boolean XoaTheLoai(String maTL) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getMaLoai().equals(maTL)) {
                ds.remove(i);
                TheLoaiDAO tlDAO = new TheLoaiDAO();
                return tlDAO.XoaTheLoai(maTL);
            }
        }
        return false;
    }
    
    public LoaiDTO layTLTheoMa(String MaTL) {
        LoaiDTO l = null;
        for (LoaiDTO x : ds) {
            if (x.getMaLoai().equals(MaTL)) {
                l = new LoaiDTO(x.getMaLoai(), x.getTenLoai());
                break;
            }
        }
        return l;
    }
    
    public ArrayList<LoaiDTO> getDs() {
        return ds;
    }
    
    public static void main(String[] agrs) {
        TheLoaiBUS tlBUS = new TheLoaiBUS();

//        TacGiaDTO tg = tgBUS.layTGTheoMa("TG06");
//
//        tg.setTenTG("Ten");
//        boolean kq = tgBUS.SuaTacGia(tg);
//        System.out.println(kq);
          LoaiDTO l = new LoaiDTO("","Phươngggg");
          if(tlBUS.ThemTheLoai(l)){
              System.out.println("Thanh cong");
          }
          
    }
}
