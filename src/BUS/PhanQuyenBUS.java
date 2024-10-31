/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import javax.swing.table.DefaultTableModel;
import DAO.QuyenDAO;
import DTO.QuyenDTO;
import utils.TachChuCaiDauInHoa;

import DTO.ChiTietQuyenDTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author nhatm
 */
public class PhanQuyenBUS {
    private QuyenDAO quyenDAO;
    
    public PhanQuyenBUS(){
        quyenDAO = new QuyenDAO();
    }
    
    public void updatePhanQuyen(String maphanquyen, boolean newValue, int row, int column){
        ChiTietQuyenDTO chitietquyen = new ChiTietQuyenDTO();
        String machucnang = new String();
        String hanhdong = new String();
        if(newValue == true){
            switch (row) {
                case 0 -> machucnang = "TG";
                case 1 -> machucnang = "LOAI";
                case 2 -> machucnang = "NCC";
                case 3 -> machucnang = "KH";
                case 4 -> machucnang = "PN";
                case 5 -> machucnang = "SP";
                case 6 -> machucnang = "BH";
                case 7 -> machucnang = "HD";
                case 8 -> machucnang = "THONGKE";
                case 9 -> machucnang = "TK";
                case 10 -> machucnang = "PQ";
                default -> {
                }
            }
            switch(column){
                case 1 -> hanhdong = "Xem";
                case 2 -> hanhdong = "Thêm";
                case 3 -> hanhdong = "Xóa";
                case 4 -> hanhdong = "Sửa";
            }
            
            chitietquyen.setMaQuyen(maphanquyen);
            chitietquyen.setMaChucNang(machucnang);
            chitietquyen.setHanhDong(hanhdong);
               
            chitietquyen.setHanhDong(hanhdong);
            
            //Test
//            System.out.println(maphanquyen + " " + machucnang + " " + hanhdong);
            //
            
            quyenDAO.ThemChiTietQuyen(chitietquyen);
            
        }else{
            if(newValue == false){
                switch (row) {
                case 0 -> machucnang = "TG";
                case 1 -> machucnang = "LOAI";
                case 2 -> machucnang = "NCC";
                case 3 -> machucnang = "KH";
                case 4 -> machucnang = "PN";
                case 5 -> machucnang = "SP";
                case 6 -> machucnang = "BH";
                case 7 -> machucnang = "HD";
                case 8 -> machucnang = "THONGKE";
                case 9 -> machucnang = "TK";
                case 10 -> machucnang = "PQ";
                default -> {
                }
            }
            switch(column){
                case 1 -> hanhdong = "Xem";
                case 2 -> hanhdong = "Thêm";
                case 3 -> hanhdong = "Xóa";
                case 4 -> hanhdong = "Sửa";
            }
            
            chitietquyen.setMaQuyen(maphanquyen);
            chitietquyen.setMaChucNang(machucnang);
            chitietquyen.setHanhDong(hanhdong);
            
            //Test
            //System.out.println(maphanquyen + " " + machucnang + " " + hanhdong);
            //
            quyenDAO.XoaChiTietQuyen(chitietquyen);
            }
        }
    }
    
    public String[] getTenPhanQuyenList(){
        
        ArrayList<QuyenDTO> quyenlist = quyenDAO.getQuyenList();
        List<String> tenquyenlist = new ArrayList<>();
        
        for(QuyenDTO quyen : quyenlist){
            tenquyenlist.add(quyen.getTenQuyen());
        }
        
        String[] tenquyenArray = new String[tenquyenlist.size()];
        tenquyenlist.toArray(tenquyenArray);
        
        return tenquyenArray;
    }
    
    public String getMaPhanQuyenByTenPhanQuyen(String tenphanquyen){
        
        ArrayList<QuyenDTO> quyenlist = quyenDAO.getQuyenList();
        String maphanquyen = new String();
        
        for(QuyenDTO quyen : quyenlist){
            if(quyen.getTenQuyen().equals(tenphanquyen)){
                maphanquyen = quyen.getMaQuyen();
                break;
            }
        }
        
        return maphanquyen;
    }
    
    public void ThemQuyen(String tenquyen){
        try{
            QuyenDTO quyen = new QuyenDTO();
            
            TachChuCaiDauInHoa tachchucaidauinhoa = new TachChuCaiDauInHoa();
            quyen.setMaQuyen(tachchucaidauinhoa.LayCumTuInHoa(tenquyen));
            quyen.setTenQuyen(tenquyen);
            
            quyenDAO.ThemPhanQuyen(quyen);
    //        System.out.println(tachchucaidauinhoa.LayCumTuInHoa(tenquyen));
    //        System.out.println(tenquyen);
    
            JOptionPane.showMessageDialog(null, "Thêm quyền thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm quyền: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }  
    }
    
    public Object[][] getPhanQuyenListByRole(String quyen){
        ArrayList<ChiTietQuyenDTO> chitietquyenlist = quyenDAO.getQuyenListByRole(quyen);
        boolean[][] quyenlist = new boolean[11][4];
        Object[][] mangdanhsachphanquyen = new Object[11][5];
        
        for(ChiTietQuyenDTO chitietquyen : chitietquyenlist){
      
            int getchucnangindex = chucnangindex(chitietquyen.getMaChucNang());
            int gethanhdongindex = hanhdongindex(chitietquyen.getHanhDong());
            
            quyenlist[getchucnangindex][gethanhdongindex] = true;

//            System.out.println(chitietquyen.getMaChucNang());
//            System.out.println(chitietquyen.getHanhDong());
            
//            System.out.println(getchucnangindex);
//            System.out.println(gethanhdongindex);
   
        }
        mangdanhsachphanquyen[0][0] = "Tác giả";
        mangdanhsachphanquyen[1][0] = "Loại";
        mangdanhsachphanquyen[2][0] = "Nhà cung cấp";
        mangdanhsachphanquyen[3][0] = "Khách hàng";
        mangdanhsachphanquyen[4][0] = "Phiếu nhập";
        mangdanhsachphanquyen[5][0] = "Sản phẩm";
        mangdanhsachphanquyen[6][0] = "Bán hàng";
        mangdanhsachphanquyen[7][0] = "Hóa đơn";
        mangdanhsachphanquyen[8][0] = "Thống kê";
        mangdanhsachphanquyen[9][0] = "Tài khoản";
        mangdanhsachphanquyen[10][0] = "Phân quyền";
        
        for (int i = 0; i < quyenlist.length; i++) {
            for (int j = 0; j < quyenlist[i].length; j++) {
                mangdanhsachphanquyen[i][j+1] = quyenlist[i][j];
            }
        }

        return mangdanhsachphanquyen;
    }
    
//    public boolean[][] Test(String quyen){
//        ArrayList<ChiTietQuyenDTO> chitietquyenlist = quyenDAO.getQuyenListByRole(quyen);
//        boolean[][] quyenlist = new boolean[10][4];
////        Object[][] mangdanhsachphanquyen = new Object[10][5];
//        
//        for(ChiTietQuyenDTO chitietquyen : chitietquyenlist){
//      
//            int getchucnangindex = chucnangindex(chitietquyen.getMaChucNang());
//            int gethanhdongindex = hanhdongindex(chitietquyen.getHanhDong());
//                        
//             if (getchucnangindex >= 0 && getchucnangindex < quyenlist.length && gethanhdongindex >= 0 && gethanhdongindex < quyenlist[0].length) {
//                quyenlist[getchucnangindex][gethanhdongindex] = true;
//            } else {
//                System.out.println("Chỉ số không hợp lệ: Chức năng index = " + getchucnangindex + ", Hành động index = " + gethanhdongindex);
//            }
//             
//            System.out.println(chitietquyen.getMaChucNang());
////            System.out.println(chitietquyen.getHanhDong());
//            
////            System.out.println(getchucnangindex);
////            System.out.println(gethanhdongindex);
//   
//        }
////        mangdanhsachphanquyen[0][0] = "Tác giả";
////        mangdanhsachphanquyen[1][0] = "Loại";
////        mangdanhsachphanquyen[2][0] = "Nhà cung cấp";
////        mangdanhsachphanquyen[3][0] = "Khách hàng";
////        mangdanhsachphanquyen[4][0] = "Phiếu nhập";
////        mangdanhsachphanquyen[5][0] = "Sản phẩm";
////        mangdanhsachphanquyen[6][0] = "Hóa đơn";
////        mangdanhsachphanquyen[7][0] = "Thống kê";
////        mangdanhsachphanquyen[8][0] = "Tài khoản";
////        mangdanhsachphanquyen[9][0] = "Phân quyền";
//        
////        for (int i = 0; i < quyenlist.length; i++) {
////            for (int j = 0; j < quyenlist[i].length; j++) {
////                mangdanhsachphanquyen[i][j+1] = quyenlist[i][j];
////            }
////        }
//
//        return quyenlist;
//    }
    
    public int chucnangindex(String chucnang){
        return switch (chucnang) {
            case "TG" -> 0;
            case "LOAI" -> 1;
            case "NCC" -> 2;
            case "KH" -> 3;
            case "PN" -> 4;
            case "SP" -> 5;
            case "BH" -> 6;
            case "HD" -> 7;
            case "THONGKE" -> 8;
            case "TK" -> 9;
            case "PQ" -> 10;
            default -> -1;
        };
    }
    
    public int hanhdongindex(String hanhdong){
//        System.out.println("Giá trị hanhdong: '" + hanhdong + "'");
        return switch (hanhdong) {
            case "Xem" -> 0;
            case "Thêm" -> 1;
            case "Xóa" -> 2;
            case "Sửa" -> 3;
            default -> -1; 
        };
    }
    
    
    

    public static void main(String[] args) {
        String query = "ADMIN";
        PhanQuyenBUS  phanquyenbus = new PhanQuyenBUS();
        
//        boolean[][] result = phanquyenbus.Test(query);
        Object[][] result = phanquyenbus.getPhanQuyenListByRole(query);
//
        System.out.println("Ma trận quyền hạn:");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

//           String maphanquyen;
//           maphanquyen = phanquyenbus.getMaPhanQuyenByTenPhanQuyen("Admin");
//           System.out.println(maphanquyen);
        
//        phanquyenbus.ThemQuyen("Nhân viên Kho");
    }

}
