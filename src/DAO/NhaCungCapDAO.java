package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.NhaCungCapDTO;

public class NhaCungCapDAO {
	
 	private connectDatabase conn;

    public  NhaCungCapDAO() {
        try {
            conn = new connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NhaCungCapDTO> dsNhaCungCap() {
        ArrayList<NhaCungCapDTO> ds = new ArrayList<>();
        try {
            conn.connect();
            String sql = "SELECT * FROM nhacungcap";
            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    NhaCungCapDTO l = new NhaCungCapDTO(rs.getString("MANCC"), rs.getString("TENNCC"));
                    ds.add(l);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean ThemNhaCungCap(NhaCungCapDTO l) {
        try {
            conn.connect();
            String sql = "INSERT INTO NHACUNGCAP(MANCC,TENNCC,TRANGTHAI) VALUES (?,?,?)";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, l.getMaNhaCungCap());
            pre.setString(2, l.getTenNhaCungCap());
            pre.setInt(3, 1);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean SuaNhaCungCap(NhaCungCapDTO l) {
        try {
            conn.connect();
            String sql = "UPDATE nhacungcap SET TENNCC=? WHERE MANCC= ?";
            PreparedStatement pre = conn.getConn().prepareStatement(sql);
            pre.setString(1, l.getTenNhaCungCap());
            pre.setString(2, l.getMaNhaCungCap());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
	public boolean XoaNhaCungCap(String maTL) {
		try {
			conn.connect();
			String sql = "DELETE FROM `nhacungcap` WHERE `MANCC` = ?";
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
        NhaCungCapDAO dao = new NhaCungCapDAO();
        ArrayList<NhaCungCapDTO> ds = dao.dsNhaCungCap();
        for (NhaCungCapDTO sp : ds) {
            System.out.println(sp.getMaNhaCungCap() + " " + sp.getTenNhaCungCap());
        }
    }


}
