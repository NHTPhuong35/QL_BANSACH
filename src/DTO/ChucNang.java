/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nhatm
 */
public class ChucNang {
    private int machucnang;
    private String tenchucnang;
    
    public void setMaChucNang(int machucnang){
        this.machucnang = machucnang;
    }
    
    public int getMaChucNang(){
        return machucnang;
    }
    
    public void setTenChucNang(String tenchucnang){
        this.tenchucnang = tenchucnang;
    }
    
    public String getTenChucNang(){
        return tenchucnang;
    }
    
    public static ChucNang getFromResultSet(ResultSet rs) throws SQLException {
        ChucNang c = new ChucNang();
        c.setMaChucNang(rs.getInt("MACHUCNANG"));
        c.setTenChucNang(rs.getNString("TENCHUCNANG"));
        return c;
    }
}
