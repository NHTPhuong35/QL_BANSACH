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
            String sql = "SELECT C.MASACH, C.DONGIA, C.SOLUONG, S.TENSACH, L.TENLOAI "
                    + "FROM cthoadon C "
                    + "JOIN SACH S ON C.MASACH = S.MASACH "
                    + "JOIN CTSACHLOAI CSL ON S.MASACH = CSL.MASACH "
                    + "JOIN LOAI L ON CSL.MALOAI = L.MALOAI "
                    + "WHERE C.SOHD = ?";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql);) {
                pre.setString(1, SoHD);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    LoaiDTO loai = new LoaiDTO();
                    loai.setTenLoai(rs.getString("TENLOAI"));

                    SanPhamDTO sp = new SanPhamDTO(rs.getString("MASACH"), rs.getString("TENSACH"), loai);

                    ChiTietHoaDonDTO ct = new ChiTietHoaDonDTO(SoHD, rs.getString("MASACH"),
                            rs.getDouble("DONGIA"), rs.getInt("SOLUONG"), sp);

                    ds.add(ct);
                }
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public static void main(String[] args) {
        ChiTietHoaDonDAO dao = new ChiTietHoaDonDAO();
        ArrayList<ChiTietHoaDonDTO> list = dao.LayChiTietMotHD("HD01");
        for (ChiTietHoaDonDTO ct : list) {
            System.out.println(ct.getSp().getTenSach() + "  " + ct.getSoLuong() + "  " + ct.getdonGia() + "  " + ct.getSoLuong() * ct.getdonGia() + "  " + ct.getSp().getLoai().getTenLoai());
        }
    }
}
