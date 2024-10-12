package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.LoaiDTO;

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
	
    public static void main(String[] agrs) {
        TheLoaiDAO dao = new TheLoaiDAO();
        ArrayList<LoaiDTO> ds = dao.dsTheLoai();
        for (LoaiDTO sp : ds) {
            System.out.println(sp.getMaLoai() + " " + sp.getTenLoai());
        }
    }


}
