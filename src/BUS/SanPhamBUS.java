	package BUS;

import java.util.ArrayList;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

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
}
