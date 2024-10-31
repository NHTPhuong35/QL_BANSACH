/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nhatm
 */
public class ChiTietSachLoaiDAO {
    private connectDatabase conn;
    public ChiTietSachLoaiDAO(){
        try {
            conn = new connectDatabase();           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getMaLoaiByMaSach(String masach){
        String query = "SELECT * FROM ctsachloai WHERE MASACH = ?";
        String maloai = "";
        try{
            conn.connect();
            PreparedStatement stmt = conn.getConn().prepareStatement(query);
            stmt.setString(1, masach);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                maloai = rs.getString("MALOAI");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return maloai;
    }
}
