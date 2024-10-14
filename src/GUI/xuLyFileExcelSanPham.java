/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.SanPhamDTO;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;

public class xuLyFileExcelSanPham {

    public void xuatExcel(ArrayList<SanPhamDTO> dsSP) {
        try {
//            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new fileChooser("export/");
            chooser.setDialogTitle("Lưu vào");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                // Lấy đường dẫn vừa chọn + tên tệp
                String path = chooser.getSelectedFile().getPath();
                if (!path.contains(".xlsx")) {
                    path += ".xlsx";
                }

                XSSFWorkbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Sheet 1");

                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 14);
                headerFont.setColor(IndexedColors.WHITE.getIndex());
                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);

                headerCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex()); // Sửa thành setFillForegroundColor()

                headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerCellStyle.setBorderTop(BorderStyle.THIN);
                headerCellStyle.setBorderBottom(BorderStyle.THIN);
                headerCellStyle.setBorderLeft(BorderStyle.THIN);
                headerCellStyle.setBorderRight(BorderStyle.THIN);
                headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

                Row headerRow = sheet.createRow(0);

                // Tạo header
                String[] header = {"Mã sách", "Tên sách", "Loại", "Tác giả", "Nhà xuất bản", "Năm xuất bản", "Số lượng", "Giá bìa", "Giá Bán", "Tên hình"};
                for (int i = 0; i < header.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(header[i]);
                    cell.setCellStyle(headerCellStyle);
                    sheet.autoSizeColumn(i);
                }

                Font contentFont = workbook.createFont();
                contentFont.setBold(false);
                contentFont.setFontHeightInPoints((short) 13);
                contentFont.setColor(IndexedColors.BLACK.getIndex());
                CellStyle contentCellStyle = workbook.createCellStyle();
                contentCellStyle.setFont(contentFont);
                contentCellStyle.setBorderTop(BorderStyle.THIN);
                contentCellStyle.setBorderBottom(BorderStyle.THIN);
                contentCellStyle.setBorderLeft(BorderStyle.THIN);
                contentCellStyle.setBorderRight(BorderStyle.THIN);

                for (int i = 0; i < dsSP.size(); i++) {
                    Row row = sheet.createRow(i + 1);
                    String[] data = {dsSP.get(i).getMaSach(), dsSP.get(i).getTenSach(), dsSP.get(i).getLoaiToString(),
                        dsSP.get(i).getTacGiaToString(), dsSP.get(i).getNxb(), dsSP.get(i).getNamXB() + "",
                        dsSP.get(i).getSoLuong() + "", dsSP.get(i).getGiaBia() + "", dsSP.get(i).getGiaBan() + "", dsSP.get(i).getTenHinhToString()};
                    for (int j = 0; j < header.length; j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(data[j]);
                        cell.setCellStyle(contentCellStyle);
                        sheet.autoSizeColumn(j);
                    }
                }

                // Ghi file
                FileOutputStream fileOut = new FileOutputStream(path);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

                new ShowDiaLog("Xuất file thành công!", ShowDiaLog.SUCCESS_DIALOG);
            }
        } catch (Exception e) {
            new ShowDiaLog("Xuất file thất bại!", ShowDiaLog.ERROR_DIALOG);
        }
    }

}
