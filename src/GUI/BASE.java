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

/**
 *
 * @author Admin
 */
public class BASE {
    public static Color btnThem = Color.decode("#A6E3A1");
    public static Color btnSua = Color.decode("#B4BEFE");
    public static Color btnXoa = Color.decode("#EBA0AC");
    public static Color color_table_heaer = Color.decode("#6BD7E1");
    public static Color color_heaer = Color.decode("#57B8C1");
    public static Color color_text = Color.decode("#0A0F0F");
    public static String typeface = "typeface";
    public static Font font_header = new Font("typeface", Font.BOLD, 16);
    public static Font font_title = new Font("typeface", Font.BOLD, 20);
    public static Font font = new Font("typeface", Font.PLAIN, 15);
    public static Color color_table_header;
    
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
