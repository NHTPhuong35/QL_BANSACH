package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

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
}
