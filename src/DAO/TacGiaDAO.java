package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.TacGiaDTO;

public class TacGiaDAO extends DAO<TacGiaDTO> {
        private connectDatabase conn;

        public TacGiaDAO() {
            try {
                conn = new connectDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
	@Override
	public ArrayList<TacGiaDTO> getAll() throws SQLException {
		ArrayList<TacGiaDTO> tacgias = new ArrayList<>();
		Statement statement = conn.getConn().createStatement();
		String query = "SELECT * FROM `tacgia`";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			TacGiaDTO tacgia = TacGiaDTO.getFromResultSet(rs);
			tacgias.add(tacgia);
		}
		return tacgias;
	}

	@Override
	public TacGiaDTO get(int id) throws SQLException {
		Statement statement = conn.getConn().createStatement();
		String query = "SELECT * FROM `tacgia` WHERE MATG = " + id;
		ResultSet rs = statement.executeQuery(query);
		if (rs.next()) {
			TacGiaDTO tacgia = TacGiaDTO.getFromResultSet(rs);
			return tacgia;
		}
		return null;
	}

	@Override
	public void save(TacGiaDTO t) throws SQLException {
		if (t == null) {
			throw new SQLException("TacGia rỗng");
		}
		String query = "INSERT INTO `tacgia` (`TENTG`, `TRANGTHAI`) VALUES (?, ?)";

		PreparedStatement stmt = conn.getConn().prepareStatement(query);
		stmt.setNString(1, t.getTenTG());
		stmt.setInt(2, t.getTrangThai());
		int row = stmt.executeUpdate();
	}

	@Override
	public void update(TacGiaDTO t) throws SQLException {
		if (t == null) {
			throw new SQLException("TacGia rỗng");
		}
		String query = "UPDATE `tacgia` SET `TENTG` = ?, `TRANGTHAI` = ? WHERE `id` = ?";

		PreparedStatement stmt = conn.getConn().prepareStatement(query);
		stmt.setNString(1, t.getTenTG());
		stmt.setInt(2, t.getTrangThai());
		stmt.setNString(3, t.getMaTG());
		int row = stmt.executeUpdate();
	}

	@Override
	public void delete(TacGiaDTO t) throws SQLException {
		PreparedStatement stmt = conn.getConn().prepareStatement("DELETE FROM `tacgia` WHERE `MATG` = ?");
		stmt.setNString(1, t.getMaTG());
		stmt.executeUpdate();
		
	}

	@Override
	public void deleteById(int id) throws SQLException {
		PreparedStatement stmt = conn.getConn().prepareStatement("DELETE FROM `tacgia` WHERE `MATG` = ?");
		stmt.setInt(1, id);
		stmt.executeUpdate();
		
	}

}
