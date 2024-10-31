/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author nhatm
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

import DTO.QuyenDTO;
import DTO.ChiTietQuyenDTO;


public class QuyenDAO {
    private connectDatabase conn;
    
    public QuyenDAO(){
        try {
	    conn = new connectDatabase();
            conn.connect();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public ArrayList<QuyenDTO> getQuyenList(){
        ArrayList<QuyenDTO> list = new ArrayList<>();
        String query = "SELECT * FROM quyen";
        try{
            PreparedStatement pstm = conn.getConn().prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                QuyenDTO quyen = new QuyenDTO();
                quyen.setMaQuyen(rs.getString(1));
                quyen.setTenQuyen(rs.getString(2));
                list.add(quyen);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<ChiTietQuyenDTO> getQuyenListByRole(String quyen){
        ArrayList<ChiTietQuyenDTO> list = new ArrayList<>();
        String query = "SELECT * FROM chitietquyen WHERE MAQUYEN = ?";
        try{
            PreparedStatement pstm = conn.getConn().prepareStatement(query);
            pstm.setString(1, quyen);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                ChiTietQuyenDTO chitietquyen = new ChiTietQuyenDTO();
                chitietquyen.setMaQuyen(rs.getString(1));
                chitietquyen.setMaChucNang(rs.getString(2));
                chitietquyen.setHanhDong(rs.getString(3));
                list.add(chitietquyen);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
    public QuyenDTO getquyen(String maquyen){
        String query = "SELECT * FROM quyen WHERE MAQUYEN = ?";
        QuyenDTO quyen = new QuyenDTO();
        try{
            PreparedStatement pstm = conn.getConn().prepareStatement(query);
            pstm.setString(1, maquyen);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                quyen.setMaQuyen(rs.getString(1));
                quyen.setTenQuyen(rs.getString(2));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return quyen;
    }
    
    public void ThemChiTietQuyen(ChiTietQuyenDTO chitietquyen){
        String query = "INSERT INTO `chitietquyen`(MAQUYEN, MACHUCNANG, HANHDONG) VALUES (?, ?, ?)";
        try{
            PreparedStatement pstm = conn.getConn().prepareStatement(query);
            pstm.setString(1, chitietquyen.getMaQuyen());
            pstm.setString(2, chitietquyen.getMaChucNang());
            pstm.setString(3, chitietquyen.getHanhDong());
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void XoaChiTietQuyen(ChiTietQuyenDTO chitietquyen){
        String query = "DELETE FROM `chitietquyen` WHERE MAQUYEN = ? AND MACHUCNANG = ? AND HANHDONG = ?";
        try{
            PreparedStatement pstm = conn.getConn().prepareStatement(query);
            pstm.setString(1, chitietquyen.getMaQuyen());
            pstm.setString(2, chitietquyen.getMaChucNang());
            pstm.setString(3, chitietquyen.getHanhDong());
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void ThemPhanQuyen(QuyenDTO qu){
        String query = "INSERT INTO `quyen`(MAQUYEN, TENQUYEN) VALUES (?, ?)";
        PreparedStatement pstm = null;
        try{
            pstm = conn.getConn().prepareStatement(query);
            pstm.setString(1, qu.getMaQuyen());
            pstm.setString(2, qu.getTenQuyen());
            pstm.executeUpdate();
        }catch(SQLException e){
            if (e.getErrorCode() == 1062) { //mã lỗi trùng lặp key
            System.out.println("Lỗi: Mã quyền đã tồn tại!");
            } else {
                e.printStackTrace();
            }
        }finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public ArrayList<ChiTietQuyenDTO> getQuyenListByRole() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
