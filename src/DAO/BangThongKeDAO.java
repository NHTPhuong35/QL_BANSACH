/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author nhatm
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
//import models.Employee;
//import models.Statistical;
import DTO.BangThongKeDTO;
import utils.TrangThaiHoaDon;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;


public class BangThongKeDAO {
    
    private connectDatabase conn;
    
    public BangThongKeDAO() {
        try {
            conn = new connectDatabase();
            conn.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (conn.getConn() != null) {
            System.out.println("Kết nối thành công");
        } else {
            System.out.println("Kết nối thất bại");
        }
    }
    
    private BangThongKeDTO bangthongkeDTO;
//    public int getTotalOrder(Timestamp start, Timestamp end, String tendn) throws SQLException {
//        String query = "SELECT COUNT(*) AS totalOrder FROM `hoadon` WHERE TRANGTHAI = ? AND NGAYHD >= ? AND NGAYHD <= ? AND TENDN = ?";
//        PreparedStatement statement = conn.getConn().prepareStatement(query);
//        statement.setNString(1, TrangThaiHoaDon.PAID.getId()); //lấy ID được liên kết với trạng thái PAID
//        statement.setTimestamp(2, start);
//        statement.setTimestamp(3, end);
//        statement.setNString(4, tendn);
//        ResultSet rs = statement.executeQuery();
//        if (rs.next()) {
//            return rs.getInt("totalOrder");
//        }
//        return 0;
//    }

//    public int getTotalOrder(Timestamp start, Timestamp end) throws SQLException {
//        String query = "SELECT COUNT(*) AS totalOrder FROM `hoadon` WHERE TRANGTHAI = ? AND NGAYHD >= ? AND NGAYHD <= ?";
//        PreparedStatement statement = conn.getConn().prepareStatement(query);
//        statement.setNString(1, TrangThaiHoaDon.PAID.getId());
//        statement.setTimestamp(2, start);
//        statement.setTimestamp(3, end);
//        ResultSet rs = statement.executeQuery();
//        if (rs.next()) {
//            return rs.getInt("totalOrder");
//        }
//        return 0;
//    }

//    public int getTotalIncome(Timestamp start, Timestamp end) throws SQLException {
//        String query = "SELECT SUM(TONGTIEN) AS totalIncome FROM `hoadon` WHERE TRANGTHAI = ? AND DATE(NGAYHD) >= DATE(?) AND DATE(NGAYHD) <= DATE(?)";
//        PreparedStatement statement = conn.getConn().prepareStatement(query);
//        statement.setNString(1, TrangThaiHoaDon.PAID.getId());
//        statement.setTimestamp(2, start);
//        statement.setTimestamp(3, end);
//        ResultSet rs = statement.executeQuery();
//        if (rs.next()) {
//            return rs.getInt("totalIncome");
//        }
//        return 0;
//    }

//    public int getTotalIncomeByid(Timestamp start, Timestamp end, String tendn) throws SQLException {
//        String query = "SELECT SUM(TONGTIEN) AS totalIncome FROM `hoadon` WHERE TRANGTHAI = ? AND DATE(NGAYHD) >= DATE(?) AND DATE(NGAYHD) <= DATE(?) AND TENDN = ?";
//        PreparedStatement statement = conn.getConn().prepareStatement(query);
//        statement.setNString(1, TrangThaiHoaDon.PAID.getId());
//        statement.setTimestamp(2, start);
//        statement.setTimestamp(3, end);
//        statement.setNString(4, tendn);
//        ResultSet rs = statement.executeQuery();
//        if (rs.next()) {
//            return rs.getInt("totalIncome");
//        }
//        return 0;
//    }


//    public ArrayList<Statistical.EmployeeIncome> getListTotalIncomeByEmployee(Timestamp start, Timestamp end) throws SQLException {
//        ArrayList<Statistical.EmployeeIncome> incomes = new ArrayList<>();
//        String query = "SELECT `idEmployee`, SUM(paidAmount) AS totalIncome, COUNT(id) AS totalOrder FROM `order` WHERE status = ? AND DATE(orderDate) >= DATE(?) AND DATE(orderDate) <= DATE(?) GROUP BY `idEmployee`  ORDER BY `totalIncome` DESC";
//        PreparedStatement statement = conn.getConn().prepareStatement(query);
//        statement.setNString(1, OrderStatus.PAID.getId());
//        statement.setTimestamp(2, start);
//        statement.setTimestamp(3, end);
//        ResultSet rs = statement.executeQuery();
//        while (rs.next()) {
//            Statistical.EmployeeIncome income = statistical.new EmployeeIncome();
//            income.employee = employeeDao.get(rs.getInt("idEmployee"));
//            income.totalIncome = rs.getInt("totalIncome");
//            income.totalOrder = rs.getInt("totalOrder");
//            incomes.add(income);
//        }
//        return incomes;
//    }

    public ArrayList<BangThongKeDTO.TaiKhoanIncome> getListTotalIncomeByDate(LocalDate start, LocalDate end) throws SQLException {
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = new ArrayList<>();
        String query = "SELECT DATE(NGAYHD) AS hoadonDate, COUNT(SOHD) AS totalOrder, SUM(TONGTIEN) AS totalIncome \n" +
               "FROM `hoadon` \n" +
               "WHERE TRANGTHAI = ? \n" +
               "AND DATE(NGAYHD) >= ? \n" +
               "AND DATE(NGAYHD) <= ? \n" +
               "GROUP BY hoadonDate \n" +
               "ORDER BY hoadonDate ASC \n" +
               "LIMIT 0, 1000;";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start)); //sử dụng Date.valueOf khi sử dụng LocalDate
        statement.setDate(3, Date.valueOf(end));
        ResultSet rs = statement.executeQuery();
        //Test
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        System.out.println("EndLocalldate: " + Date.valueOf(end));
        System.out.println("StartLocaldate: " + Date.valueOf(start));

        //
        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.TaiKhoanIncome income = bangthongkeDTO.new TaiKhoanIncome();
            Date sqlhoadonDate = rs.getDate("hoadonDate");
            if (sqlhoadonDate != null) {
//                System.out.println("hoadonDate: " + sqlhoadonDate.toLocalDate());
                income.date = sqlhoadonDate.toLocalDate();
            } else {
//                System.out.println("hoadonDate: null");
                income.date = null;
            }
            income.totalIncome = rs.getInt("totalIncome");
            income.totalOrder = rs.getInt("totalOrder");
            
//            System.out.println("totalIncome: " + income.totalIncome);
//            System.out.println("totalOrder: " + income.totalOrder);
            
            incomes.add(income);
        }
        return incomes;
    }
    
    public ArrayList<BangThongKeDTO.TaiKhoanNhapHangPriceImport> getListTotalImportPriceByDate(LocalDate start, LocalDate end) throws SQLException {
        ArrayList<BangThongKeDTO.TaiKhoanNhapHangPriceImport> prices = new ArrayList<>();
        String query = "SELECT DATE(NGAYNHAP) AS lohangnhapDate, COUNT(MAPN) AS totalPhieunhap, SUM(TONGTIEN) AS totalImportPrice \n" +
               "FROM `phieunhap` \n" +
               "WHERE TRANGTHAI = ? \n" +
               "AND DATE(NGAYNHAP) >= ? \n" +
               "AND DATE(NGAYNHAP) <= ? \n" +
               "GROUP BY lohangnhapDate \n" +
               "ORDER BY lohangnhapDate ASC \n" +
               "LIMIT 0, 1000;";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start)); //sử dụng Date.valueOf khi sử dụng LocalDate
        statement.setDate(3, Date.valueOf(end));
        ResultSet rs = statement.executeQuery();
        //Test
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        System.out.println("EndLocalldate: " + Date.valueOf(end));
        System.out.println("StartLocaldate: " + Date.valueOf(start));

        //
        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.TaiKhoanNhapHangPriceImport price = bangthongkeDTO.new TaiKhoanNhapHangPriceImport();
            Date sqlhoadonDate = rs.getDate("lohangnhapDate");
            if (sqlhoadonDate != null) {
//                System.out.println("hoadonDate: " + sqlhoadonDate.toLocalDate());
                price.date = sqlhoadonDate.toLocalDate();
            } else {
//                System.out.println("hoadonDate: null");
                price.date = null;
            }
            price.totalImportPrice = rs.getInt("totalImportPrice");
            
//            System.out.println("totalIncome: " + income.totalIncome);
//            System.out.println("totalOrder: " + income.totalOrder);
            
            prices.add(price);
        }
        return prices;
    }
    
    public ArrayList<BangThongKeDTO.ChiTietHoaDon> getListTotalBooksByDate(LocalDate start, LocalDate end) throws SQLException {
        ArrayList<BangThongKeDTO.ChiTietHoaDon> books = new ArrayList<>();
        String query = "SELECT " +
                "DATE(hoadon.NGAYHD) AS hoadonDate, " +
                "COUNT(DISTINCT cthoadon.SOHD) AS totalHoaDon, " +
                "SUM(cthoadon.SOLUONG) AS totalBooks " +
                "FROM hoadon " +
                "INNER JOIN cthoadon ON cthoadon.SOHD = hoadon.SOHD " +
                "WHERE hoadon.TRANGTHAI = ? " +
                "AND DATE(hoadon.NGAYHD) >= ? " +
                "AND DATE(hoadon.NGAYHD) <= ? " +
                "GROUP BY hoadonDate " +
                "ORDER BY hoadonDate ASC " +
                "LIMIT 0, 1000;";

        PreparedStatement statement = conn.getConn().prepareStatement(query);
        statement.setInt(1, TrangThaiHoaDon.PAID.getId());
        statement.setDate(2, Date.valueOf(start)); //sử dụng Date.valueOf khi sử dụng LocalDate
        statement.setDate(3, Date.valueOf(end));
        ResultSet rs = statement.executeQuery();
        //Test
        System.out.println("TrangThai: " + TrangThaiHoaDon.PAID.getId());
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        System.out.println("EndLocalldate: " + Date.valueOf(end));
        System.out.println("StartLocaldate: " + Date.valueOf(start));

        //
        bangthongkeDTO = new BangThongKeDTO();
        while (rs.next()) {
            BangThongKeDTO.ChiTietHoaDon book = bangthongkeDTO.new ChiTietHoaDon();
            Date sqlhoadonDate = rs.getDate("hoadonDate");
            if (sqlhoadonDate != null) {
//                System.out.println("hoadonDate: " + sqlhoadonDate.toLocalDate());
                book.date = sqlhoadonDate.toLocalDate();
            } else {
//                System.out.println("hoadonDate: null");
                book.date = null;
            }
            book.totalBooks = rs.getInt("totalBooks");
            book.totalHoaDon = rs.getInt("totalHoaDon");

//            System.out.println("totalIncome: " + income.totalIncome);
//            System.out.println("totalOrder: " + income.totalOrder);
            
            books.add(book);
        }
        return books;
    }
}


//    public ArrayList<Statistical.EmployeeIncome> getListTotalIncomeByDate(Timestamp start, Timestamp end, int idEmployee) throws SQLException {
//        ArrayList<Statistical.EmployeeIncome> incomes = new ArrayList<>();
//        String query = "SELECT DATE(orderDate) AS orderDate, SUM(paidAmount) AS totalIncome "
//                + "FROM `order` WHERE status = ? AND DATE(orderDate) >= DATE(?) AND DATE(orderDate) <= DATE(?) AND idEmployee = ? "
//                + "GROUP BY orderDate ORDER BY `orderDate` ASC";
//        PreparedStatement statement = conn.getConn().prepareStatement(query);
//        statement.setNString(1, OrderStatus.PAID.getId());
//        statement.setTimestamp(2, start);
//        statement.setTimestamp(3, end);
//        statement.setInt(4, idEmployee);
//        ResultSet rs = statement.executeQuery();
//        Employee e = employeeDao.get(idEmployee);
//        while (rs.next()) {
//            Statistical.EmployeeIncome income = statistical.new EmployeeIncome();
//            income.date = rs.getDate("orderDate");
//            income.totalIncome = rs.getInt("totalIncome");
//            income.employee = e;
//        }
//        return incomes;
//    }
//
//    public int getTotalEmployee() throws SQLException {
//        Statement statement = conn.getConn().createStatement();
//        String query = "SELECT COUNT(*) AS total FROM employee";
//        ResultSet rs = statement.executeQuery(query);
//        if (rs.next()) {
//            return rs.getInt("total");
//        }
//        return 0;
//    }
//
//    public int getTotalCustomer() throws SQLException {
//        Statement statement = conn.getConn().createStatement();
//        String query = "SELECT COUNT(*) AS total FROM customer";
//        ResultSet rs = statement.executeQuery(query);
//        if (rs.next()) {
//            return rs.getInt("total");
//        }
//        return 0;
//    }

//    public ArrayList<Statistical.ProductIncome> getQuantityItemByCategory(Timestamp start, Timestamp end, int Catetory) throws SQLException {
//        ArrayList<Statistical.ProductIncome> itemProducts = new ArrayList<>();
//        String query = "SELECT food_item.id AS idFoodItem, `name`, SUM(quantity) AS sum "
//                + "FROM `order_item`, `food_item`, `order` "
//                + "WHERE `idFoodItem` = food_item.id AND idCategory = ? "
//                + "AND `idOrder` = order.id AND DATE(orderDate) >= DATE(?) AND DATE(orderDate) <= DATE(?) "
//                + "GROUP BY food_item.id, `name` "
//                + "ORDER BY sum DESC";
//
//
//        PreparedStatement statement = conn.prepareStatement(query);
//        statement.setInt(1, Catetory);
//        statement.setTimestamp(2, start);
//        statement.setTimestamp(3, end);
//        ResultSet rs = statement.executeQuery();
//        while (rs.next()) {
//            Statistical.ProductIncome itemProduct = statistical.new ProductIncome();
//            itemProduct.name = rs.getString("name");
//            itemProduct.quantity = rs.getInt("sum");
//            itemProducts.add(itemProduct);
//        }
//        return itemProducts;
//    }
//
//    public ArrayList<Statistical.ProductIncome> getQuantityItem(Timestamp start, Timestamp end) throws SQLException {
//        ArrayList<Statistical.ProductIncome> itemProducts = new ArrayList<>();
//       String query = "SELECT `idFoodItem`, `name`, SUM(quantity) as sum, (foodPrice * SUM(quantity)) as amount "
//                + "FROM `order_item`, `food_item`, `order` "
//                + "WHERE `idFoodItem` = food_item.id AND `idOrder` = order.id "
//                + "AND DATE(orderDate) >= DATE(?) AND DATE(orderDate) <= DATE(?) "
//                + "AND order.status = ? "
//                + "GROUP BY `idFoodItem`, `name`, `foodPrice` "
//                + "ORDER BY sum DESC";
//
//
//
//        PreparedStatement statement = conn.prepareStatement(query);
//        statement.setTimestamp(1, start);
//        statement.setTimestamp(2, end);
//        statement.setNString(3, OrderStatus.PAID.getId());
//        ResultSet rs = statement.executeQuery();
//        while (rs.next()) {
//            Statistical.ProductIncome itemProduct = statistical.new ProductIncome();
//            itemProduct.name = rs.getString("name");
//            itemProduct.quantity = rs.getInt("sum");
//            itemProduct.id = rs.getInt("idFoodItem");
//            itemProduct.amount = rs.getInt("amount");
//            itemProducts.add(itemProduct);
//        }
//        return itemProducts;
//    }


