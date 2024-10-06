/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author nhatm
 */
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;


public class BangThongKeDTO {

//    public class ProductIncome {
//
//        public item;
//        public int quantity, amount, id;
//        public String name;
//    }

    public class TaiKhoanIncome {

        public TaiKhoanDTO taikhoan;
        public int totalIncome, totalOrder;
        public LocalDate date;

        @Override
        public String toString() {
            return "TotalIncome{" + "employee=" + taikhoan + ", totalIncome=" + totalIncome + ", totalOrder=" + totalOrder + ", date=" + date + '}';
        }

        public boolean equalDate(LocalDate date) {
            return this.date.isEqual(date);
        }

    }
    
    public class TaiKhoanNhapHangPriceImport {
        public TaiKhoanDTO taikhoan;
        public int totalImportPrice;
        public LocalDate date;
        
        @Override
        public String toString() {
            return "TotalImportPrice{" + "employee=" + taikhoan + ", totalimportPrice=" + totalImportPrice+  ", date=" + date + '}';
        }

        public boolean equalDate(LocalDate date) {
            return this.date.isEqual(date);
        }
    }
    
    public class ChiTietHoaDon {
        public SanPhamDTO sanpham;
        public int totalBooks;
        public int totalHoaDon;
        public LocalDate date;
        
        @Override
        public String toString() {
            return "TotalBooks{" + "Book=" + sanpham + ", totalBooks=" + totalBooks+  ", date=" + date + ", totalHoaDon=" + totalHoaDon + '}';
        }

        public boolean equalDate(LocalDate date) {
            return this.date.isEqual(date);
        }
    }

}

