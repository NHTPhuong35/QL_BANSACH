/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.ChucNang;
import java.sql.PreparedStatement;
/**
 *
 * @author nhatm
 */
public class ChucNangDAO extends DAO<ChucNang> {
    @Override
    public ArrayList<ChucNang> getAll() throws SQLException {
        ArrayList<ChucNang> chucnangs = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `chucnang`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            ChucNang chucnang = ChucNang.getFromResultSet(rs);
            chucnangs.add(chucnang);
        }
        return chucnangs;
    }
    
    @Override
    public ChucNang get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `chucnang` WHERE MACHUCNANG = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            ChucNang chucnang = ChucNang.getFromResultSet(rs);
            return chucnang;
        }
        return null;
    }
    
    @Override
    public void save(ChucNang t) throws SQLException { // hàm Thêm(insert)
        if (t == null) {
            throw new SQLException("Customer rỗng");
        }
        String query = "INSERT INTO `chucnang` (`TENCHUCNANG`) VALUES (?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getTenChucNang());
        int row = stmt.executeUpdate();
    }
    
    @Override
    public void update(ChucNang t) throws SQLException {
        if (t == null) {
            throw new SQLException("Customer rỗng");
        }
        String query = "UPDATE `chucnang` SET `TENCHUCNANG` = ? WHERE `id` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getTenChucNang());
        int row = stmt.executeUpdate();

    }

    @Override
    public void delete(ChucNang t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `chucnang` WHERE `id` = ?");
        stmt.setInt(1, t.getMaChucNang());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `chucnang` WHERE `id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
    
    

}
