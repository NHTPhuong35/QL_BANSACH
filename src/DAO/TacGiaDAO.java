package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.TacGiaDTO;

public class TacGiaDAO  {

	 	private connectDatabase conn;

	    public  TacGiaDAO() {
	        try {
	            conn = new connectDatabase();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public ArrayList<TacGiaDTO> dsTacGia() {
	        ArrayList<TacGiaDTO> ds = new ArrayList<>();
	        try {
	            conn.connect();
	            String sql = "SELECT * FROM TACGIA";
	            try (PreparedStatement pre = conn.getConn().prepareStatement(sql)) {
	                ResultSet rs = pre.executeQuery();
	                while (rs.next()) {
	                    TacGiaDTO tg = new TacGiaDTO(rs.getString("MATG"), rs.getString("TENTG"));
	                    ds.add(tg);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return ds;
	    }

	    public boolean ThemTacGia(TacGiaDTO tg) {
	        try {
	            conn.connect();
	            String sql = "INSERT INTO TACGIA(MATG,TENTG,TRANGTHAI) VALUES (?,?,?)";
	            PreparedStatement pre = conn.getConn().prepareStatement(sql);
	            pre.setString(1, tg.getMaTG());
	            pre.setString(2, tg.getTenTG());
	            pre.setInt(5, 1);
	            return pre.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public boolean SuaTacGia(TacGiaDTO tg) {
	        try {
	            conn.connect();
	            String sql = "UPDATE TACGIA SET TENTG=? WHERE MATG= ?";
	            PreparedStatement pre = conn.getConn().prepareStatement(sql);
	            pre.setString(1, tg.getTenTG());
	            pre.setString(2, tg.getMaTG());
	            return pre.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
		public boolean XoaTacGia(String maTG) {
			try {
				conn.connect();
				String sql = "DELETE FROM `TACGIA` WHERE `MATG` = ?";
				try (PreparedStatement pre = conn.getConn().prepareStatement(sql)){
					pre.setString(1, maTG);
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
	        TacGiaDAO dao = new TacGiaDAO();
	        ArrayList<TacGiaDTO> ds = dao.dsTacGia();
	        for (TacGiaDTO sp : ds) {
	            System.out.println(sp.getMaTG() + " " + sp.getTenTG());
	        }
	    }


}
