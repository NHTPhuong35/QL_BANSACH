package MAIN;

import BUS.LoginBUS;
import GUI.LoginGUI;

public class main {
	public static void main(String[] args) {
		// Set up look and feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
			System.out.println("Khởi tạo look and feel thành công!");
		} catch (Exception ex) {
			System.err.println("Khởi tạo look and feel thất bại!");
		}

		LoginBUS login = new LoginBUS(new LoginGUI());
	}
}
