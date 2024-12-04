package BUS;

import java.util.ArrayList;

import DAO.TacGiaDAO;
import DTO.TacGiaDTO;
import GUI.ShowDiaLog;

public class TacGiaBUS {
	private ArrayList<TacGiaDTO> ds;
	
    public TacGiaBUS() {
        ds = new ArrayList<>();
        init();
    }

    public void init() {
        TacGiaDAO dao = new TacGiaDAO();
        ds = dao.dsTacGia();
    }
	
    private boolean KT_Ten(String ten) {
        String regex = "^[a-zA-ZÀ-ỹ\\s]{2,}$";
        return ten.matches(regex);
    }
    
    public String TaoMaTG() {
        String maLonNhat = "TG01";
        for (TacGiaDTO tg : ds) {
            String maTG = tg.getMaTG();
            if (maTG.compareTo(maLonNhat) > 0) {
                maLonNhat = maTG;
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
            return String.format("TG0%d", soCuoi);
        } else {
            return String.format("TG%d", soCuoi);
        }
    }
    public boolean ThemTacGia(TacGiaDTO tg) {
    	String tenTg = tg.getTenTG();
        if (tenTg == null || tenTg.trim().isEmpty()) {
            new ShowDiaLog("<html>Tên tác giả không được để trống.</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        if (!tenTg.equals(tenTg.trim())) {
            new ShowDiaLog("<html>Tên tác giả không được có<br> khoảng trắng ở đầu hoặc cuối.</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        if (KT_Ten(tenTg)) {
            tg.setMaTG(TaoMaTG());
            TacGiaDAO tgDAO = new TacGiaDAO();
            if (tgDAO.ThemTacGia(tg)) {
                new ShowDiaLog("Thêm tác giả thành công", ShowDiaLog.SUCCESS_DIALOG);
                return true;
            } else {
                new ShowDiaLog("Thêm tác giả thất bại", ShowDiaLog.ERROR_DIALOG);
                return false;
            }
        }
        if (!KT_Ten(tenTg)) {
            new ShowDiaLog("<html>Tên tác giả chỉ được nhập chữ<br> và phải chứa tối thiểu 2 ký tự</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return false;
    }

    public boolean SuaTacGia(TacGiaDTO tg) {
    	String tenTg = tg.getTenTG();
        if (tenTg == null || tenTg.trim().isEmpty()) {
            new ShowDiaLog("<html>Tên tác giả không được để trống.</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        if (!tenTg.equals(tenTg.trim())) {
            new ShowDiaLog("<html>Tên tác giả không được có<br> khoảng trắng ở đầu hoặc cuối.</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        if (KT_Ten(tenTg)) {
            TacGiaDAO tgDAO = new TacGiaDAO();
            if (tgDAO.SuaTacGia(tg)) {
                new ShowDiaLog("Sửa tác giả thành công", ShowDiaLog.SUCCESS_DIALOG);
                return true;
            } else {
                new ShowDiaLog("Sửa tác giả thất bại", ShowDiaLog.ERROR_DIALOG);
                return false;
            }
        }
        if (!KT_Ten(tenTg)) {
            new ShowDiaLog("<html>Tên tác giả chỉ được nhập chữ <br> và phải chứa tối thiểu 2 ký tự</html>", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return false;
    }
    
    public boolean XoaTacGia(String maTG) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getMaTG().equals(maTG)) {
                ds.remove(i);
                TacGiaDAO tgDAO = new TacGiaDAO();
                return tgDAO.XoaTacGia(maTG);
            }
        }
        return false;
    }
    
    public TacGiaDTO layTGTheoMa(String MaTG) {
        TacGiaDTO tg = null;
        for (TacGiaDTO x : ds) {
            if (x.getMaTG().equals(MaTG)) {
                tg = new TacGiaDTO(x.getMaTG(), x.getTenTG());
                break;
            }
        }
        return tg;
    }
    
    public ArrayList<TacGiaDTO> getDs() {
        return ds;
    }
    
    public static void main(String[] agrs) {
        TacGiaBUS tgBUS = new TacGiaBUS();

//        TacGiaDTO tg = tgBUS.layTGTheoMa("TG06");
//
//        tg.setTenTG("Ten");
//        boolean kq = tgBUS.SuaTacGia(tg);
//        System.out.println(kq);
          TacGiaDTO t = new TacGiaDTO("","Phươngggg");
          if(tgBUS.ThemTacGia(t)){
              System.out.println("Thanh cong");
          }
          
    }
}

