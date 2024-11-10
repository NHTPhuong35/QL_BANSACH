/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class BASE {
    public static Color btnThem = Color.decode("#5DADE2");
    public static Color btnSua = Color.decode("#F4D03F");
    public static Color btnXoa = Color.decode("#E57373");
    public static Color color_table_heaer = Color.decode("#E0AC69");
    public static Color color_heaer = Color.decode("#57B8C1");
    public static Color color_text = Color.decode("#0A0F0F");
    public static String typeface = "typeface";
    public static Font font_header = new Font("typeface", Font.BOLD, 16);
    public static Font font_title = new Font("typeface", Font.BOLD, 20);
    public static Font font = new Font("typeface", Font.PLAIN, 15);
    public static Font font_header_frame = new Font("typeface", Font.BOLD, 14);
    public static Font font_frame = new Font("typeface", Font.PLAIN, 12);
    public static Font font_error = new Font("typeface", Font.PLAIN, 11);
    public static Color color_table_header;
    
    public static Color color_header_tbl = Color.decode("#E0AC69");
    public static Color color_main = Color.decode("#C68642");
    public static Color color_btTim = Color.decode("#8B5E2E");
    public static Color color_btLamMoi = Color.decode("#6DA06F");
    public static Color color_btLamXoa = Color.decode("#E57373");
    public static Color color_btBFD = Color.decode("#FF8963");
    public static Color color_header = Color.decode("#F1C27D");
    public static Color color_btAdd = Color.decode("#5DADE2");
    public static Color color_btEdit = Color.decode("#F4D03F");
    public static Color color_btXacNhan = Color.decode("#6DA67A");
    public static Color color_btHuy = Color.decode("#D47965");
    
    public static ImageIcon createResizedIcon(Class<?> clazz, String imagePath, int width, int height) {
        URL imageURL = clazz.getResource(imagePath);
        if (imageURL == null) {
            System.err.println("Resource not found: " + imagePath);
            return null;
        }
        ImageIcon originalIcon = new ImageIcon(imageURL);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}

