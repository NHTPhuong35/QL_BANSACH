package BUS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.PhieuNhapDTO;
import DTO.ChiTietPhieuNhapDTO;
import DAO.PhieuNhapDAO;
import javax.swing.JOptionPane;

public class PhieuNhapBUS {

    private ArrayList<PhieuNhapDTO> dsPN;

    public PhieuNhapBUS() {
        dsPN = new ArrayList<>();
        getAllPhieuNhap();
    }

    public List<PhieuNhapDTO> getAllPhieuNhap() {
        PhieuNhapDAO pn = new PhieuNhapDAO();
        dsPN = pn.LayPhieuNhap();
        return dsPN;
    }

    public ArrayList<PhieuNhapDTO> getDsPN() {
        return dsPN;
    }

    public void XoaPhieuNhap(String maPN) {
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phiếu nhập " + maPN + " không?",
                "Xác nhận xóa phiếu nhập", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            PhieuNhapDAO dao = new PhieuNhapDAO();
            if (dao.xoaPhieuNhap(maPN)) {
                JOptionPane.showMessageDialog(null, "Phiếu nhập đã được xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Xóa phiếu nhập thất bại!");
            }
        }
    }
    
    public void ThemPhieuNhap(String maPN, String maNCC, String tenDN, Date ngayNhap, double tongTien, int trangThai) {
        PhieuNhapDAO dao = new PhieuNhapDAO();
        PhieuNhapDTO phieuNhap = new PhieuNhapDTO(maPN, maNCC, tenDN, ngayNhap, tongTien, trangThai);
        if (dao.ThemPhieuNhap(phieuNhap)) {
            JOptionPane.showMessageDialog(null, "Thêm phiếu nhập thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm phiếu nhập thất bại!");
        }
    }

    public void suaPhieuNhap(String maPN, String maNCC, String tenDN, Date ngayNhap, double tongTien, int trangThai) {
        PhieuNhapDAO dao = new PhieuNhapDAO();
        PhieuNhapDTO phieuNhap = new PhieuNhapDTO(maPN, maNCC, tenDN, ngayNhap, tongTien, trangThai);
        if (dao.suaPhieuNhap(phieuNhap)) {
            JOptionPane.showMessageDialog(null, "Cập nhật phiếu nhập thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật phiếu nhập thất bại!");
        }
    }

    public ArrayList<ChiTietPhieuNhapDTO> LayChiTietPhieuNhap(String maPN) {
        PhieuNhapDAO dao = new PhieuNhapDAO();
        return dao.LayChiTietPhieuNhap(maPN);
    }

    public void ThemChiTietPhieuNhap(String maPN, String maSP, int soLuong, double tongTien, double giaNhap) {
        PhieuNhapDAO dao = new PhieuNhapDAO();
        ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO();
        if (dao.ThemChiTietPhieuNhap(ctpn)) {
            JOptionPane.showMessageDialog(null, "Thêm chi tiết phiếu nhập thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm chi tiết phiếu nhập thất bại!");
        }
    }

    public void suaChiTietPhieuNhap(String maPN, String maSP, int soLuong, double tongTien, double giaNhap) {
        PhieuNhapDAO dao = new PhieuNhapDAO();
        ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO();
        if (dao.suaChiTietPhieuNhap(ctpn)) {
            JOptionPane.showMessageDialog(null, "Cập nhật chi tiết phiếu nhập thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật chi tiết phiếu nhập thất bại!");
        }
    }
}
