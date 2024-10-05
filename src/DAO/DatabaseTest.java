/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author nhatm
 */

import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) {
        connectDatabase db = null;
        try {
            // Tạo đối tượng kết nối
            db = new connectDatabase();
            db.connect(); // Kết nối đến cơ sở dữ liệu

            // Kiểm tra xem kết nối có thành công hay không
            if (db.getConn() != null) {
                System.out.println("Kết nối thành công!");
            } else {
                System.out.println("Kết nối thất bại!");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
        } finally {
            // Đảm bảo rằng kết nối được đóng nếu đã được mở
            if (db != null) {
                try {
                    db.disconnect();
                    System.out.println("Kết nối đã được đóng.");
                } catch (SQLException e) {
                    System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
                }
            }
        }
    }
}
