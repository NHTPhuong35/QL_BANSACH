package BUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GUI.HomeGUI;
import GUI.LoginGUI;

public class HomeBUS {
	
	private HomeGUI view;
	public HomeBUS(HomeGUI view) {
		this.view = view;
		view.setVisible(true);
		addEvent();
	}
	private void addEvent() { // Tạo sự kiện
		view.getBtnLogout().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				int confirm = JOptionPane.showConfirmDialog(view, "Bạn thực sự muốn đăng xuất?");
				if (confirm != JOptionPane.YES_OPTION) {
					return;
				}
				view.dispose();
				new LoginBUS(new LoginGUI());
			}
		});
	}
}
