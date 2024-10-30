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
import DAO.ChiTietHoaDonDAO;
import DAO.ChiTietSachLoaiDAO;
import DAO.KhachHangDAO;
import DAO.SanPhamDAO;
import DAO.TheLoaiDAO;
import DTO.BangThongKeDTO;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;
import GUI.BangThongKeByMonthGUI;
import GUI.BangThongKeByYearGUI;
import GUI.ChiTietThongKeGUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.time.YearMonth;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import utils.MoneyFormatter;


public class BangThongKeBUS {
    ChiTietHoaDonDAO chitiethoadonDao = new ChiTietHoaDonDAO();
    KhachHangDAO khachhangDao = new KhachHangDAO();
    BangThongKeDAO bangthongkeDAO = new BangThongKeDAO();
    SanPhamDAO sanphamDao = new SanPhamDAO();
    ChiTietSachLoaiDAO chitietsachloaiDao = new ChiTietSachLoaiDAO();
    static HoaDonDAO hoadonDao = new HoaDonDAO();
    TheLoaiDAO theloaiDao = new TheLoaiDAO();
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

    private CategoryDataset createDatasetByTheLoaiDate(LocalDate start, LocalDate end, String theloai) throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // Tạo tập dữ liệu
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = null;
        
        if(theloai.equals("Tất cả")){
            incomes = bangthongkeDAO.getListTotalIncomeAllByDate(start, end);
        }else{
            incomes = bangthongkeDAO.getListTotalIncomeByDateWithTheLoai(start, end, theloai);
        }
        
        for (LocalDate date : getAllDateBetween(start, end)) { 
            BangThongKeDTO.TaiKhoanIncome income = findByDate(incomes, date);
            if (income != null) {
                System.out.println("Date: " + date + ", Income: " + income.totalIncome);
                dataset.addValue(income.totalIncome , "TN", date.format(formatter));
            } else {
                dataset.addValue(0, "TN", date.format(formatter));
            }
        }
        return (CategoryDataset) dataset;
    }
    

    private CategoryDataset createDatasetByTheLoaiMonth(LocalDate start, LocalDate end, String theloai) throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // Tạo tập dữ liệu
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = null;
        
        if(theloai.equals("Tất cả")){
            incomes = bangthongkeDAO.getListTotalIncomeAllByMonth(start, end);
        }else{
            incomes = bangthongkeDAO.getListTotalIncomeByMonthWithTheLoai(start, end, theloai);
        }
        
        YearMonth startMonth = YearMonth.from(start);
        YearMonth endMonth = YearMonth.from(end);

        for (YearMonth month = startMonth; !month.isAfter(endMonth); month = month.plusMonths(1)) {
            int totalIncomeForMonth = 0;

            // Tính tổng thu nhập cho tháng hiện tại
            for (LocalDate date : getAllDateInMonth(month)) {
                BangThongKeDTO.TaiKhoanIncome income = findByDate(incomes, date);
                if (income != null) {
                    totalIncomeForMonth += income.totalIncome;
                }
            }

            dataset.addValue(totalIncomeForMonth, "TN", month.format(DateTimeFormatter.ofPattern("MM/yyyy")));
        }

        return dataset;
    }

    private CategoryDataset createDatasetByTheLoaiYear(LocalDate start, LocalDate end, String theloai) throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // Tạo tập dữ liệu
        ArrayList<BangThongKeDTO.TaiKhoanIncome> incomes = null;
        if(theloai.equals("Tất cả")){
            incomes = bangthongkeDAO.getListTotalIncomeAllByYear(start, end);
        }else{
            incomes = bangthongkeDAO.getListTotalIncomeByYearWithTheLoai(start, end, theloai);
        }
        
        int startYear = start.getYear();
        int endYear = end.getYear();

        // Lặp qua từng năm trong khoảng thời gian
        for (int year = startYear; year <= endYear; year++) {
            int totalIncomeForYear = 0;

            // Tính tổng thu nhập cho năm hiện tại
            for (BangThongKeDTO.TaiKhoanIncome income : incomes) {
                if (income.date.getYear() == year) {
                    totalIncomeForYear += income.totalIncome;
                }
            }

            dataset.addValue(totalIncomeForYear, "TN", String.valueOf(year));
        }

        return dataset;
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
     
    public double getTotalIncomeByTheLoai(LocalDate start, LocalDate end, String theloai, String thoigian){
        try{
            double totalIncome = hoadonDao.TongDoanhThuByTheLoaiWithThoiGian(start, end, theloai, thoigian);
            return totalIncome;
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Lỗi kết nối csdl");
        }
        return 0;
    }
    
    public int getTotalHoaDonByTheLoai(LocalDate start, LocalDate end, String theloai, String thoigian){
        try{
            int totalhoadon = hoadonDao.SoHoaDonByTheLoaiWithThoiGian(start, end, theloai, thoigian);      
            return totalhoadon;
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Lỗi kết nối csdl");
        }
        return 0;
    }
    
    public int getTotalBooksByTheLoai(LocalDate start, LocalDate end, String theloai, String thoigian){
        try{
            int totalbooks = hoadonDao.TongQuyenSachByTheLoaiWithThoiGian(start, end, theloai, thoigian);      
            return totalbooks;
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Lỗi kết nối csdl");
        }
        return 0;
    }
    
    public int getTotalHoaDonBiHuyByTheLoai(LocalDate start, LocalDate end, String theloai, String thoigian){
        try{
            int totalhoadon = hoadonDao.SoHoaDonHuyByTheLoaiWithThoiGian(start, end, theloai, thoigian);
            return totalhoadon;  
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Lỗi kết nối csdl");
        }
        return 0;
    }
      
    public List<String> getThongTinChiTiet(LocalDate start, LocalDate end){
        ArrayList<HoaDonDTO> dshoadondaduocthanhtoan = hoadonDao.DanhSachHoaDonDaDuocThanhToan(start, end);
        List<String> dschuoithongtintable = new ArrayList<>();
        for(HoaDonDTO hoadon : dshoadondaduocthanhtoan){
            ArrayList<ChiTietHoaDonDTO> dschitiethoadon = chitiethoadonDao.LayChiTietMotHD(hoadon.getSoHD());
            for(ChiTietHoaDonDTO sach : dschitiethoadon){
                String tenkhachhang = khachhangDao.TimTenKhachHangWithId(hoadon.getMaKH());
                SanPhamDTO sanpham = sanphamDao.getTenSachByMaSach(sach.getMaSach());
                String chuoithongtin = tenkhachhang + "," + hoadon.getTGian() + "," + hoadon.getNgayHD() + "," + sanpham.getTenSach() + "," + sach.getSoLuong() + "," + sanpham.getGiaBan() + "," + sanpham.getGiaBan()*sach.getSoLuong();
                dschuoithongtintable.add(chuoithongtin);
            }
        }
        return dschuoithongtintable;
    }
    
    public void putdschuoithongtin(ChiTietThongKeGUI chitietthongkegui, LocalDate start, LocalDate end){
        List<String> dsthongtin = getThongTinChiTiet(start, end);
        for(String thongtin : dsthongtin){
            String[] values = thongtin.split(",");
            String tenkh = values[0];
            String thoigian = values[1];
            String ngay = values[2];
            String tensach = values[3];
            String soluong = values[4];
            String giaban = values[5];
            String tongtien = values[6];
            System.out.print(thongtin);
            chitietthongkegui.addKH(tenkh, LocalTime.parse(thoigian), LocalDate.parse(ngay), tensach, Integer.parseInt(soluong), MoneyFormatter.formatToVND(Double.parseDouble(giaban)), MoneyFormatter.formatToVND(Double.parseDouble(tongtien)));
        }
    }
    
        public List<String> getThongTinChiTietTheLoai(LocalDate start, LocalDate end, String theloai, String time){
            ArrayList<HoaDonDTO> dshoadondaduocthanhtoan = hoadonDao.DanhSachHoaDonDaDuocThanhToanTheoThoiGian(start, end, time);
            List<String> dschuoithongtintable = new ArrayList<>();
            if(theloai.equals("Tất cả")){
                for(HoaDonDTO hoadon : dshoadondaduocthanhtoan){
                    ArrayList<ChiTietHoaDonDTO> dschitiethoadon = chitiethoadonDao.LayChiTietMotHD(hoadon.getSoHD());
                    for(ChiTietHoaDonDTO sach : dschitiethoadon){
                        String tenkhachhang = khachhangDao.TimTenKhachHangWithId(hoadon.getMaKH());
                        SanPhamDTO sanpham = sanphamDao.getTenSachByMaSach(sach.getMaSach());
                        String chuoithongtin = tenkhachhang + "," + hoadon.getTGian() + "," + hoadon.getNgayHD() + "," + sanpham.getTenSach() + "," + sach.getSoLuong() + "," + sanpham.getGiaBan() + "," + sanpham.getGiaBan()*sach.getSoLuong();
                        dschuoithongtintable.add(chuoithongtin);
                    }
                }
            }else{
                for(HoaDonDTO hoadon : dshoadondaduocthanhtoan){
                    ArrayList<ChiTietHoaDonDTO> dschitiethoadon = chitiethoadonDao.LayChiTietMotHD(hoadon.getSoHD());
                    for(ChiTietHoaDonDTO sach : dschitiethoadon){
                        String tenkhachhang = khachhangDao.TimTenKhachHangWithId(hoadon.getMaKH());
                        SanPhamDTO sanpham = sanphamDao.getTenSachByMaSach(sach.getMaSach());
                        String maloai = chitietsachloaiDao.getMaLoaiByMaSach(sach.getMaSach());
                        String tenloai = theloaiDao.getTenTheLoaiByMaLoai(maloai);
                        if(tenloai.equals(theloai)){
                            String chuoithongtin = tenkhachhang + "," + hoadon.getTGian() + "," + hoadon.getNgayHD() + "," + sanpham.getTenSach() + "," + sach.getSoLuong() + "," + sanpham.getGiaBan() + "," + sanpham.getGiaBan()*sach.getSoLuong();
                            dschuoithongtintable.add(chuoithongtin);
                        }
                    }
                }
            }
            
        return dschuoithongtintable;
    }
        
    public void putdschuoithongtincuatheloai(ChiTietThongKeGUI chitietthongkegui, LocalDate start, LocalDate end, String theloai, String time){
        List<String> dsthongtin = getThongTinChiTietTheLoai(start, end, theloai, time);
        for(String thongtin : dsthongtin){
            String[] values = thongtin.split(",");
            String tenkh = values[0];
            String thoigian = values[1];
            String ngay = values[2];
            String tensach = values[3];
            String soluong = values[4];
            String giaban = values[5];
            String tongtien = values[6];
            System.out.print(thongtin);
            chitietthongkegui.addKH(tenkh, LocalTime.parse(thoigian), LocalDate.parse(ngay), tensach, Integer.parseInt(soluong), MoneyFormatter.formatToVND(Double.parseDouble(giaban)), MoneyFormatter.formatToVND(Double.parseDouble(tongtien)));
        }
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
 
    public void showDateByTheLoai(JPanel panelRoot, LocalDate start, LocalDate end, String theloai) {
        try {
            // Kiểm tra panelRoot
            if (panelRoot == null) {
                System.out.println("panelroot null");
                throw new IllegalArgumentException("panelRoot null");
            }

            CategoryDataset dataset = createDatasetByTheLoaiDate(start, end, theloai);
           
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
    
    public void showMonthByTheLoai(JPanel panelRoot, LocalDate start, LocalDate end, String theloai) {
        try {
            // Kiểm tra panelRoot
            if (panelRoot == null) {
                System.out.println("panelroot null");
                throw new IllegalArgumentException("panelRoot null");
            }

            CategoryDataset dataset = createDatasetByTheLoaiMonth(start, end, theloai);
            
            // Kiểm tra dataset
            if (dataset == null) {
                System.out.println("Dataset null");
                throw new IllegalStateException("Dataset null"); 

            }

            JPanel chartPanel = BangThongKeByMonthGUI.createChartPanel(dataset);

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
    
    public void showYearByTheLoai(JPanel panelRoot, LocalDate start, LocalDate end, String theloai) {
        try {
            // Kiểm tra panelRoot
            if (panelRoot == null) {
                System.out.println("panelroot null");
                throw new IllegalArgumentException("panelRoot null");
            }
            
            CategoryDataset dataset = createDatasetByTheLoaiYear(start, end, theloai);
            
   
            // Kiểm tra dataset
            if (dataset == null) {
                System.out.println("Dataset null");
                throw new IllegalStateException("Dataset null"); 

            }

            JPanel chartPanel = BangThongKeByYearGUI.createChartPanel(dataset);

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


