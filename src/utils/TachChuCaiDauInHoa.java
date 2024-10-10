/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author nhatm
 */
public class TachChuCaiDauInHoa {
    public static String LayCumTuInHoa(String phrase) {
        // Tách chuỗi thành mảng từ
        String[] words = phrase.split("\\s+"); // Tách theo khoảng trắng
        StringBuilder initialsBuilder = new StringBuilder();

        for (String word : words) {
            // Lấy ký tự đầu tiên và chuyển thành chữ hoa
            if (!word.isEmpty()) {
                char firstChar = word.charAt(0);
                initialsBuilder.append(Character.toUpperCase(firstChar));
            }
        }
        return initialsBuilder.toString(); 
    }
}
