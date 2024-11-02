/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ChiTietHoaDonBUS;
import BUS.KhachHangBUS;
import BUS.TaiKhoanBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;
import com.itextpdf.text.pdf.BaseFont;

public class PDFExporter {

    public void exportInvoiceToPDF(HoaDonDTO hd) {
        Document document = new Document(new com.itextpdf.text.Rectangle(600f, PageSize.A4.getHeight()));
        try {
            // Get the path to the Downloads folder
            String userHome = System.getProperty("user.home");
            String filePath = userHome + File.separator + "Downloads" + File.separator + "hoadon_" + hd.getSoHD() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            // Load a font that supports Vietnamese
            String fontPath = "C:/Windows/Fonts/times.ttf"; // Đường dẫn tới font hỗ trợ tiếng Việt
            BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(bf, 18, Font.BOLD);
            Font regularFont = new Font(bf, 12);

            // Add title
            document.add(new Paragraph("Hoá đơn", titleFont));
            document.add(new Paragraph("Ngày: " + hd.getNgayHD() + " " + hd.getTGian(), regularFont));
            document.add(new Paragraph("Số hóa đơn: " + hd.getSoHD(), regularFont));

            PdfPTable customerTable = new PdfPTable(2);
            customerTable.setWidthPercentage(100); // Set width to 100%

            // Add customer information
            PdfPCell creatorCell = new PdfPCell(new Phrase("Người lập hóa đơn: " + new TaiKhoanBUS().getTenNhanVien(hd.getTenDN()), regularFont));
            creatorCell.setBorder(PdfPCell.NO_BORDER); // No border for aesthetic
            customerTable.addCell(creatorCell);
            
            PdfPCell customerCell = new PdfPCell(new Phrase("Khách hàng: " + new KhachHangBUS().getTenKH(hd.getMaKH()), regularFont));
            customerCell.setBorder(PdfPCell.NO_BORDER); // No border for aesthetic
            customerTable.addCell(customerCell);
           
            document.add(customerTable);
            document.add(new Paragraph(" ")); // Blank line

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100); // Set the table width to 100%

            // Adjust the column widths to be proportional
            float[] columnWidths = new float[]{3f, 2f, 1f, 1f, 2f}; // Adjust these values as needed
            table.setWidths(columnWidths); // Set the column widths

            addTableHeader(table, bf);
            addRows(table, hd.getSoHD());

            document.add(table);

            // Add total price
            double TongTien = TinhTongTien(hd);

            Paragraph totalParagraph = new Paragraph("Tổng tiền: " + TongTien + " VND", regularFont);
            totalParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalParagraph);

            Paragraph discountParagraph = new Paragraph("Giảm giá: " + hd.getTienGiamGia() + " VND", regularFont);
            discountParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(discountParagraph);

            Paragraph finalAmountParagraph = new Paragraph("Thành tiền: " + (TongTien - hd.getTienGiamGia()) + " VND", regularFont);
            finalAmountParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(finalAmountParagraph);
            new ShowDiaLog("In hóa đơn thành công", ShowDiaLog.SUCCESS_DIALOG);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void addTableHeader(PdfPTable table, BaseFont bf) {
        Font headerFont = new Font(bf, 12, Font.BOLD);
        Stream.of("Tên sản phẩm", "Thể loại", "Số lượng", "Đơn giá", "Thành tiền")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(columnTitle, headerFont));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, String soHD) throws DocumentException, IOException {
        ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS(soHD);
        ArrayList<ChiTietHoaDonDTO> list = bus.getDscthd();
        for (ChiTietHoaDonDTO ct : list) {
            table.addCell(new Phrase(ct.getSp().getTenSach(), new Font(BaseFont.createFont("C:/Windows/Fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12, Font.NORMAL))); // Sử dụng font cho từng ô
            table.addCell(new Phrase(ct.getSp().getLoaiToString(), new Font(BaseFont.createFont("C:/Windows/Fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12, Font.NORMAL)));
            table.addCell(new Phrase(String.valueOf(ct.getSoLuong()), new Font(BaseFont.createFont("C:/Windows/Fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12, Font.NORMAL)));
            table.addCell(new Phrase(String.valueOf(ct.getdonGia()), new Font(BaseFont.createFont("C:/Windows/Fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12, Font.NORMAL)));
            table.addCell(new Phrase(String.valueOf(ct.getSoLuong() * ct.getdonGia()), new Font(BaseFont.createFont("C:/Windows/Fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12, Font.NORMAL)));
        }
    }

    private double TinhTongTien(HoaDonDTO hd) {
        ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS(hd.getSoHD());
        ArrayList<ChiTietHoaDonDTO> list = bus.getDscthd();
        double Tong = 0.0;
        for (ChiTietHoaDonDTO ct : list) {
            Tong += ct.getdonGia() * ct.getSoLuong();
        }
        return Tong;
    }
}

