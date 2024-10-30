package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.LoaiDTO;
import java.sql.Statement;
import java.util.List;

public class TheLoaiDAO {
	
 	private connectDatabase conn;

    public  TheLoaiDAO() {
        try {
            conn = new connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<LoaiDTO> dsTheLoai() {
        ArrayList<LoaiDTO> ds = new ArrayList<>();
        try {
            conn.connect();
            String sql = "SELECT * FROM LOAI";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    LoaiDTO l = new LoaiDTO(rs.getString("MALOAI"), rs.getString("TENLOAI"));
                    ds.add(l);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean ThemTheLoai(LoaiDTO l) {
        try {
            conn.connect();
            String sql = "INSERT INTO LOAI(MALOAI,TENLOAI,TRANGTHAI) VALUES (?,?,?)";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, l.getMaLoai());
            pre.setString(2, l.getTenLoai());
            pre.setInt(3, 1);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean SuaTheLoai(LoaiDTO l) {
        try {
            conn.connect();
            String sql = "UPDATE LOAI SET TENLOAI=? WHERE MALOAI= ?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, l.getTenLoai());
            pre.setString(2, l.getMaLoai());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
	public boolean XoaTheLoai(String maTL) {
		try {
			conn.connect();
			String sql = "DELETE FROM `LOAI` WHERE `MALOAI` = ?";
			try (PreparedStatement pre = conn.getConn().prepareStatement(sql)){
				pre.setString(1, maTL);
				pre.executeUpdate();
			}
			conn.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    
    public String getTenTheLoaiByMaLoai(String maloai){
        String query = "SELECT * FROM loai WHERE MALOAI = ?";
        String tenloai = "";
        try{
            conn.connect();
            PreparedStatement stmt = conn.getConn().prepareStatement(query);
            stmt.setString(1, maloai);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                tenloai = rs.getString("TENLOAI");
            }
            conn.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return tenloai;
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

    public String[] DanhSachTenLoai() {
        String query = "SELECT * FROM `loai`";
        List<String> dstenloai = new ArrayList<>();
        try {
            conn.connect();
            Statement stmt = conn.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            dstenloai.add("Tất cả");
            while (rs.next()) {
                dstenloai.add(rs.getNString(2));
            }
            conn.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dstenloai.toArray(new String[0]);
    }
    
    public static void main(String[] agrs) {
        TheLoaiDAO dao = new TheLoaiDAO();
        ArrayList<LoaiDTO> ds = dao.dsTheLoai();
        for (LoaiDTO sp : ds) {
            System.out.println(sp.getMaLoai() + " " + sp.getTenLoai());
        }
    }


}
