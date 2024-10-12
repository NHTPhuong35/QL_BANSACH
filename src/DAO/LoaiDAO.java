/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.LoaiDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class LoaiDAO {

    private connectDatabase conn;

    public LoaiDAO() {
        try {
            conn = new connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<LoaiDTO> DanhSachLoai() {
        ArrayList<LoaiDTO> dsLoai = new ArrayList<>();
        try {
            conn.connect();
            String sql = "SELECT * FROM loai";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    String maLoai = rs.getString("MALOAI");
                    String tenLoai = rs.getString("TENLOAI");
                    int trangThai = rs.getInt("TRANGTHAI");

                    // Tạo đối tượng LoaiDTO và thêm vào danh sách
                    LoaiDTO loai = new LoaiDTO(maLoai, tenLoai, trangThai);
                    dsLoai.add(loai);
                }
                conn.disconnect();
            }
        } catch (Exception e) {
        }
        return dsLoai;
    }
    
    public static void main(String[] args) {
        
    }
}
