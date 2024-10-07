/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.LoaiDTO;
import DTO.SanPhamDTO;
import DTO.TacGiaDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class SanPhamDAO {

    private connectDatabase conn;

    public SanPhamDAO() {
        try {
            conn = new connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SanPhamDTO> DanhSachSanPham() {
        ArrayList<SanPhamDTO> sanPhamList = new ArrayList<>();
        try {
            conn.connect();
            String sql = "SELECT \n"
                    + "    s.MASACH, \n"
                    + "    s.TENSACH, \n"
                    + "    s.NAMXUATBAN, \n"
                    + "    s.NHAXUATBAN, \n"
                    + "    s.GIABIA, \n"
                    + "    s.GIABAN, \n"
                    + "    s.SOLUONG, \n"
                    + "    s.TRANGTHAI, \n"
                    + "    GROUP_CONCAT(DISTINCT h.TENHINH) AS HINH,\n"
                    + "    GROUP_CONCAT(DISTINCT tg.MATG) AS TACGIA_ID,\n"
                    + "    GROUP_CONCAT(DISTINCT tg.TENTG) AS TACGIA_NAME,\n"
                    + "    GROUP_CONCAT(DISTINCT l.MALOAI) AS LOAI_ID,\n"
                    + "    GROUP_CONCAT(DISTINCT l.TENLOAI) AS LOAI_NAME\n"
                    + "FROM \n"
                    + "    sach s\n"
                    + "LEFT JOIN \n"
                    + "    hinh h ON s.MASACH = h.MASACH\n"
                    + "LEFT JOIN \n"
                    + "    ctsachtacgia ctg ON s.MASACH = ctg.MASACH\n"
                    + "LEFT JOIN \n"
                    + "    tacgia tg ON ctg.MATG = tg.MATG\n"
                    + "LEFT JOIN \n"
                    + "    ctsachloai cl ON s.MASACH = cl.MASACH\n"
                    + "LEFT JOIN \n"
                    + "    loai l ON cl.MALOAI = l.MALOAI\n"
                    + "GROUP BY \n"
                    + "    s.MASACH;";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    String maSach = rs.getString("MASACH");
                    String tenSach = rs.getString("TENSACH");
                    int namXB = rs.getInt("NAMXUATBAN");
                    String nxb = rs.getString("NHAXUATBAN");
                    double giaBia = rs.getDouble("GIABIA");
                    double giaBan = rs.getDouble("GIABAN");
                    int soLuong = rs.getInt("SOLUONG");
                    int trangThai = rs.getInt("TRANGTHAI");

                    // Tạo danh sách hình ảnh
                    String hinh = rs.getString("HINH");
                    ArrayList<String> tenHinh = hinh != null ? new ArrayList<>(Arrays.asList(hinh.split(","))) : new ArrayList<>();

                    // Tạo danh sách tác giả
                    String tacGiaIdsStr = rs.getString("TACGIA_ID");
                    String tacGiaNamesStr = rs.getString("TACGIA_NAME");
                    ArrayList<TacGiaDTO> tacGiaList = new ArrayList<>();
                    if (tacGiaIdsStr != null && tacGiaNamesStr != null) {
                        String[] tacGiaIds = tacGiaIdsStr.split(",");
                        String[] tacGiaNames = tacGiaNamesStr.split(",");
                        for (int i = 0; i < tacGiaIds.length; i++) {
                            tacGiaList.add(new TacGiaDTO(tacGiaIds[i], tacGiaNames[i]));
                        }
                    }

                    // Tạo danh sách loại
                    String loaiIdsStr = rs.getString("LOAI_ID");
                    String loaiNamesStr = rs.getString("LOAI_NAME");
                    ArrayList<LoaiDTO> loaiList = new ArrayList<>();
                    if (loaiIdsStr != null && loaiNamesStr != null) {
                        String[] loaiIds = loaiIdsStr.split(",");
                        String[] loaiNames = loaiNamesStr.split(",");
                        for (int i = 0; i < loaiIds.length; i++) {
                            loaiList.add(new LoaiDTO(loaiIds[i], loaiNames[i]));
                        }
                    }

                    // Tạo đối tượng SanPhamDTO
                    SanPhamDTO sanPham = new SanPhamDTO(maSach, tenSach, nxb, namXB, soLuong, trangThai, giaBia, giaBan, tenHinh, tacGiaList, loaiList);
                    sanPhamList.add(sanPham);
                }
            } catch (Exception e) {
                e.printStackTrace(); // In ra lỗi nếu có
            } finally {
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return sanPhamList;
    }

    public boolean themSanPham(SanPhamDTO sp) {
        try {
            conn.connect();
            String sql = "INSERT INTO sach (MASACH, TENSACH, NAMXUATBAN, NHAXUATBAN, GIABIA, GIABAN, SOLUONG, TRANGTHAI) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                pre.setString(1, sp.getMaSach());
                pre.setString(2, sp.getTenSach());
                pre.setInt(3, sp.getNamXB());
                pre.setString(4, sp.getNxb());
                pre.setDouble(5, sp.getGiaBia());
                pre.setDouble(6, sp.getGiaBan());
                pre.setInt(7, sp.getSoLuong());
                pre.setInt(8, sp.getTrangthai());

                pre.executeUpdate();

                // Thêm vào bảng liên quan như ct_sach_tacgia, ct_sach_loai, hinh
                themTacGiaSanPham(sp);
                themLoaiSanPham(sp);
                themHinhSanPham(sp);
            }
            conn.disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void themTacGiaSanPham(SanPhamDTO sp) throws Exception {
        String sql = "INSERT INTO ctsachtacgia (MASACH, MATG) VALUES (?, ?)";
        try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
            for (TacGiaDTO tg : sp.getTacGia()) {
                pre.setString(1, sp.getMaSach());
                pre.setString(2, tg.getMaTG());
                pre.executeUpdate();
            }
        }
    }

    private void themLoaiSanPham(SanPhamDTO sp) throws Exception {
        String sql = "INSERT INTO ctsachloai (MASACH, MALOAI) VALUES (?, ?)";
        try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
            for (LoaiDTO loai : sp.getLoai()) {
                pre.setString(1, sp.getMaSach());
                pre.setString(2, loai.getMaLoai());
                pre.executeUpdate();
            }
        }
    }

    private void themHinhSanPham(SanPhamDTO sp) throws Exception {
        String sql = "INSERT INTO hinh (MASACH, TENHINH) VALUES (?, ?)";
        try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
            for (String hinh : sp.getTenHinh()) {
                pre.setString(1, sp.getMaSach());
                pre.setString(2, hinh);
                pre.executeUpdate();
            }
        }
    }

    public boolean suaSanPham(SanPhamDTO sp) {
        try {
            conn.connect();
            String sql = "UPDATE sach SET TENSACH = ?, NAMXUATBAN = ?, NHAXUATBAN = ?, GIABIA = ?, GIABAN = ?, SOLUONG = ?, TRANGTHAI = ? WHERE MASACH = ?";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                pre.setString(1, sp.getTenSach());
                pre.setInt(2, sp.getNamXB());
                pre.setString(3, sp.getNxb());
                pre.setDouble(4, sp.getGiaBia());
                pre.setDouble(5, sp.getGiaBan());
                pre.setInt(6, sp.getSoLuong());
                pre.setInt(7, sp.getTrangthai());
                pre.setString(8, sp.getMaSach());

                pre.executeUpdate();

                // Cập nhật bảng liên quan
                capNhatTacGiaSanPham(sp);
                capNhatLoaiSanPham(sp);
                capNhatHinhSanPham(sp);
            }
            conn.disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void capNhatTacGiaSanPham(SanPhamDTO sp) throws Exception {
        // Xóa các tác giả cũ
        String deleteSql = "DELETE FROM ctsachtacgia WHERE MASACH = ?";
        try (PreparedStatement pre = conn.getConn().prepareStatement(deleteSql)) {
            pre.setString(1, sp.getMaSach());
            pre.executeUpdate();
        }
        // Thêm tác giả mới
        themTacGiaSanPham(sp);
    }

    private void capNhatLoaiSanPham(SanPhamDTO sp) throws Exception {
        // Xóa các loại cũ
        String deleteSql = "DELETE FROM ctsachloai WHERE MASACH = ?";
        try (PreparedStatement pre = conn.getConn().prepareStatement(deleteSql)) {
            pre.setString(1, sp.getMaSach());
            pre.executeUpdate();
        }
        // Thêm loại mới
        themLoaiSanPham(sp);
    }

    private void capNhatHinhSanPham(SanPhamDTO sp) throws Exception {
        // Xóa các hình cũ
        String deleteSql = "DELETE FROM hinh WHERE MASACH = ?";
        try (PreparedStatement pre = conn.getConn().prepareStatement(deleteSql)) {
            pre.setString(1, sp.getMaSach());
            pre.executeUpdate();
        }
        // Thêm hình mới
        themHinhSanPham(sp);
    }

    public boolean xoaSanPham(String maSach, Boolean ktraPN) {
        try {
            conn.connect();
            if (ktraPN) {
                // Nếu ktraPN = true, chỉ cập nhật trạng thái về 0
                String sql = "UPDATE sach SET TRANGTHAI = 0 WHERE MASACH = ?";
                try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                    pre.setString(1, maSach);
                    pre.executeUpdate();
                }
            } else {
                // Nếu ktraPN = false, xóa tất cả bản ghi liên quan
                xoaTacGiaSanPham(maSach);
                xoaLoaiSanPham(maSach);
                xoaHinhSanPham(maSach);

                // Xóa từ bảng `sach`
                String sql = "DELETE FROM sach WHERE MASACH = ?";
                try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                    pre.setString(1, maSach);
                    pre.executeUpdate();
                }
            }
            conn.disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void xoaTacGiaSanPham(String maSach) throws Exception {
        String sql = "DELETE FROM ctsachtacgia WHERE MASACH = ?";
        try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
            pre.setString(1, maSach);
            pre.executeUpdate();
        }
    }

    private void xoaLoaiSanPham(String maSach) throws Exception {
        String sql = "DELETE FROM ctsachloai WHERE MASACH = ?";
        try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
            pre.setString(1, maSach);
            pre.executeUpdate();
        }
    }

    private void xoaHinhSanPham(String maSach) throws Exception {
        String sql = "DELETE FROM hinh WHERE MASACH = ?";
        try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
            pre.setString(1, maSach);
            pre.executeUpdate();
        }
    }
    
    public void CapNhatSoLuongSP(String MaSach, int SoLuong) {
        try{
            conn.connect();
            String sql = "UPDATE SACH SET SOLUONG=? WHERE MASACH =?";
            try(PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                pre.setInt(1, SoLuong);
                pre.setString(2, MaSach);
                pre.executeUpdate();
            }
            conn.disconnect();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        SanPhamDAO dao = new SanPhamDAO();
        ArrayList<SanPhamDTO> list = dao.DanhSachSanPham();
        for (SanPhamDTO sp : list) {
            System.out.println("Tên sách: " + sp.getTenSach() + "  Thể loại: " + sp.getLoaiToString() + "   Tác giả: " + sp.getTacGiaToString() + "   hinh: " + sp.getTenHinh());
        }

        ArrayList<TacGiaDTO> tgia = new ArrayList<>();
        tgia.add(new TacGiaDTO("TG01", "Nguyễn Anh Dũng"));
        tgia.add(new TacGiaDTO("TG04", "Phan Văn Trường"));

        ArrayList<LoaiDTO> loaiSP = new ArrayList<>();
        loaiSP.add(new LoaiDTO("L03", "Quản trị - lãnh đạo"));
        loaiSP.add(new LoaiDTO("L02", "Tâm lý học"));

        ArrayList<String> anh = new ArrayList<>();
        anh.add("a1.jpg");
        anh.add("b1.jpg");
        anh.add("c1.jpg");
        SanPhamDTO sp1 = new SanPhamDTO("SP06", "Đắc nhân tâm 123", "Văn học Nhà Nội 123", 2022, 10, 1, 52000, 0, anh, tgia, loaiSP);
//        dao.themSanPham(sp1);
//        if (dao.xoaSanPham("SP111",false)) {
//            System.out.println("Xoá thành công!");
//        } else {
//            System.out.println("Xoá thất bại!");
//        }
    }
    
    
}

