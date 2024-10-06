/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author nhatm
 */

import DAO.HoaDonDAO;
import GUI.BangThongKeByDateGUI;
import DAO.BangThongKeDAO;
import DTO.BangThongKeDTO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.time.YearMonth;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class BangThongKeBUS {
    
    BangThongKeDAO bangthongkeDAO = new BangThongKeDAO();
    static HoaDonDAO hoadonDao = new HoaDonDAO();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

    private CategoryDataset createDataset(LocalDate start, LocalDate end) throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // Tạo tập dữ liệu
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = bangthongkeDAO.getListTotalIncomeByDate(start, end); // Cập nhật phương thức DAO để trả về LocalDate

        for (LocalDate date : getAllDateBetween(start, end)) { 
            BangThongKeDTO.TaiKhoanIncome income = findByDate(incomes, date);
            if (income != null) {
                dataset.addValue(income.totalIncome / 100000, "TN", date.format(formatter));
            } else {
                dataset.addValue(0, "TN", date.format(formatter));
            }
        }
        return (CategoryDataset) dataset;
    }
    
    private CategoryDataset createDatasetByMonth(LocalDate start, LocalDate end) throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // Tạo tập dữ liệu
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = bangthongkeDAO.getListTotalIncomeByDate(start, end); // Cập nhật phương thức DAO để trả về LocalDate

        YearMonth startMonth = YearMonth.from(start);
        YearMonth endMonth = YearMonth.from(end);

        for (YearMonth month = startMonth; !month.isAfter(endMonth); month = month.plusMonths(1)) {
            int totalIncomeForMonth = 0;

            for (LocalDate date : getAllDateInMonth(month)) {
                BangThongKeDTO.TaiKhoanIncome income = findByDate(incomes, date);
                if (income != null) {
                    totalIncomeForMonth += income.totalIncome;
                }
            }

            dataset.addValue(totalIncomeForMonth / 100000.0, "TN", month.format(DateTimeFormatter.ofPattern("MM/yyyy")));
        }

        return (CategoryDataset) dataset;
    }

    private List<LocalDate> getAllDateInMonth(YearMonth month) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate firstDay = month.atDay(1);
        LocalDate lastDay = month.atEndOfMonth();

        for (LocalDate date = firstDay; !date.isAfter(lastDay); date = date.plusDays(1)) {
            dates.add(date);
        }

        return dates;
    }
    
    public double getTotalIncome(LocalDate start, LocalDate end){
        try{
            double totalIncome = 0;
        
            ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = bangthongkeDAO.getListTotalIncomeByDate(start, end); 

            for (BangThongKeDTO.TaiKhoanIncome income : incomes) {
                totalIncome += income.totalIncome;
            }

            return totalIncome;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Lỗi kết nối csdl");
        }
        return 0;
    }
    
    public double getTotalImportPrice(LocalDate start, LocalDate end){
        try{
            double totalImportPrice = 0;
        
            ArrayList<BangThongKeDTO.TaiKhoanNhapHangPriceImport> prices = bangthongkeDAO.getListTotalImportPriceByDate(start, end); 

            for (BangThongKeDTO.TaiKhoanNhapHangPriceImport price : prices) {
                totalImportPrice += price.totalImportPrice;
            }

            return totalImportPrice;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Lỗi kết nối csdl");
        }
        return 0;
    }
    
    public int getTotalBooks(LocalDate start, LocalDate end){
        try{
            int totalBooks = 0;
        
            ArrayList<BangThongKeDTO.ChiTietHoaDon> books = bangthongkeDAO.getListTotalBooksByDate(start, end); 

            for (BangThongKeDTO.ChiTietHoaDon book : books) {
                totalBooks += book.totalBooks;
            }

            return totalBooks;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Lỗi kết nối csdl");
        }
        return 0;
    }
    
    public int getTotalHoaDon(LocalDate start, LocalDate end){
        try{
            int totalHoaDon = 0;
        
            ArrayList<BangThongKeDTO.ChiTietHoaDon> hoadons = bangthongkeDAO.getListTotalBooksByDate(start, end); 

            for (BangThongKeDTO.ChiTietHoaDon hoadon : hoadons) {
                totalHoaDon += hoadon.totalHoaDon;
            }

            return totalHoaDon;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Lỗi kết nối csdl");
        }
        return 0;
    }
    
    private BangThongKeDTO.TaiKhoanIncome findByDate(ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes, LocalDate date) {
        for (BangThongKeDTO.TaiKhoanIncome income : incomes) {
            if (income.equalDate(date)) {
                return income;
            }
        }
        return null;
    }

    private static ArrayList<LocalDate> getAllDateBetween(LocalDate start, LocalDate end) {
        ArrayList<LocalDate> dates = new ArrayList<>();

        LocalDate currentDate = start; // Bắt đầu từ ngày bắt đầu
        while (!currentDate.isAfter(end)) { // Kiểm tra nếu ngày hiện tại không lớn hơn ngày kết thúc
            dates.add(currentDate); // Thêm ngày hiện tại vào danh sách
            currentDate = currentDate.plusDays(1); // Thêm 1 ngày
        }
    
        return dates;
    }

    public void show(JPanel panelRoot, LocalDate start, LocalDate end) {
    try {
        // Kiểm tra panelRoot
        if (panelRoot == null) {
            System.out.println("panelroot null");
            throw new IllegalArgumentException("panelRoot null");
        }

        CategoryDataset dataset = createDataset(start, end);
        
        
        // Kiểm tra dataset
        if (dataset == null) {
            System.out.println("Dataset null");
            throw new IllegalStateException("Dataset null"); 

        }

        JPanel chartPanel = BangThongKeByDateGUI.createChartPanel(dataset);
        
        // Kiểm tra chartPanel
        if (chartPanel == null) {
            System.out.println("chartPanel null");
            throw new IllegalStateException("chartPanel null");
        }
        
        panelRoot.setLayout(new BorderLayout());
        panelRoot.removeAll();

        panelRoot.add(chartPanel, BorderLayout.CENTER);

        chartPanel.setMaximumSize(new Dimension(panelRoot.getWidth(), panelRoot.getHeight()));
        
        panelRoot.revalidate();
        panelRoot.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panelRoot, "Đã xảy ra lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panelRoot, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void showbymonth(JPanel panelRoot, LocalDate start, LocalDate end) {
    try {
        // Kiểm tra panelRoot
        if (panelRoot == null) {
            System.out.println("panelroot null");
            throw new IllegalArgumentException("panelRoot null");
        }

        CategoryDataset dataset = createDataset(start, end);
        
        
        // Kiểm tra dataset
        if (dataset == null) {
            System.out.println("Dataset null");
            throw new IllegalStateException("Dataset null"); 

        }

        JPanel chartPanel = BangThongKeByDateGUI.createChartPanel(dataset);
        
        // Kiểm tra chartPanel
        if (chartPanel == null) {
            System.out.println("chartPanel null");
            throw new IllegalStateException("chartPanel null");
        }
        
        panelRoot.setLayout(new BorderLayout());
        panelRoot.removeAll();

        panelRoot.add(chartPanel, BorderLayout.CENTER);

        chartPanel.setMaximumSize(new Dimension(panelRoot.getWidth(), panelRoot.getHeight()));
        
        panelRoot.revalidate();
        panelRoot.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panelRoot, "Đã xảy ra lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panelRoot, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

//    public static void main(String[] args) {
//        LocalDate end = LocalDate.now();
//        
//        LocalDate start = end.minus(3, ChronoUnit.DAYS);
//        
//        // In ra tất cả các ngày trong khoảng từ start đến end
//        for (LocalDate date : getAllDateBetween(start, end)) {
//            System.out.println(date);
//        }
//    }


