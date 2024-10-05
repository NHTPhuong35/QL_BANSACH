/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.LoaiDTO;
import DTO.SanPhamDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietHoaDonDAO {

    private connectDatabase conn;

    public ChiTietHoaDonDAO() {
        try {
            conn = new connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ChiTietHoaDonDTO> LayChiTietMotHD(String SoHD) {
    ArrayList<ChiTietHoaDonDTO> ds = new ArrayList<>();
    try {
        conn.connect();
        String sql = "SELECT C.MASACH, C.DONGIA, C.SOLUONG, S.TENSACH, "
                   + "GROUP_CONCAT(DISTINCT L.TENLOAI ORDER BY L.TENLOAI ASC) AS LOAI_NAME "
                   + "FROM cthoadon C "
                   + "JOIN SACH S ON C.MASACH = S.MASACH "
                   + "JOIN CTSACHLOAI CSL ON S.MASACH = CSL.MASACH "
                   + "JOIN LOAI L ON CSL.MALOAI = L.MALOAI "
                   + "WHERE C.SOHD = ? "
                   + "GROUP BY C.MASACH, C.DONGIA, C.SOLUONG, S.TENSACH";

        try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
            pre.setString(1, SoHD);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                // Lấy thông tin từ ResultSet
                String maSach = rs.getString("MASACH");
                String tenSach = rs.getString("TENSACH");
                double donGia = rs.getDouble("DONGIA");
                int soLuong = rs.getInt("SOLUONG");
                
                // Tạo đối tượng LoaiDTO
                ArrayList<LoaiDTO> loaiList = new ArrayList<>();
                String loaiName = rs.getString("LOAI_NAME");
                if (loaiName != null) {
                    String[] loaiArray = loaiName.split(",");
                    for (String loai : loaiArray) {
                        LoaiDTO loaiDTO = new LoaiDTO(loai.trim());
                        loaiList.add(loaiDTO);
                    }
                }

                // Tạo đối tượng SanPhamDTO
                SanPhamDTO sp = new SanPhamDTO(maSach, tenSach, loaiList);
                
                // Tạo đối tượng ChiTietHoaDonDTO
                ChiTietHoaDonDTO ct = new ChiTietHoaDonDTO(SoHD, maSach, donGia, soLuong, sp);
                
                // Thêm vào danh sách kết quả
                ds.add(ct);
            }
        }
        conn.disconnect();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ds;
}
    
    public boolean ThemCTHoaDon(ChiTietHoaDonDTO ct) {
        boolean success = false;
        try {
            conn.connect();
            String sql = "INSERT INTO cthoadon(SOHD,MASACH,SOLUONG,DONGIA) VALUES(?,?,?,?)";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, ct.getSoHD());
            pre.setString(2, ct.getMaSach());
            pre.setInt(3, ct.getSoLuong());
            pre.setDouble(4, ct.getdonGia());
            success = pre.executeUpdate() > 0;
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    
    public static void main(String[] args) {
        ChiTietHoaDonDAO dao = new ChiTietHoaDonDAO();
        ArrayList<ChiTietHoaDonDTO> list = dao.LayChiTietMotHD("HD01");
        for (ChiTietHoaDonDTO ct : list) {
            System.out.println(ct.getSp().getTenSach() + "  " + ct.getSoLuong() + "  " + ct.getdonGia() + "  " + ct.getSoLuong() * ct.getdonGia() + "  " + ct.getSp().getLoaiToString());
        }
    }
}
