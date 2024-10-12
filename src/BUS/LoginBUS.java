package BUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import GUI.HomeGUI;
import GUI.LoginGUI;

public class LoginBUS {
	
	private LoginGUI view;
	TaiKhoanDAO TkDAO = new TaiKhoanDAO();

	public LoginBUS(LoginGUI view) {
		this.view = view;
		view.setVisible(true);
		addEvent();
	}

	public LoginGUI getView() {
		return view;
	}

	public void setView(LoginGUI view) {
		this.view = view;
	}

	public void login() {
		String username = view.getTxtUsername().getText();
		String password = new String(view.getTxtPassword().getPassword());
		try {
			TaiKhoanDTO taikhoan = TkDAO.timTaiKhoan(username);

			if (taikhoan == null) {
				view.showError("Không tồn tại tài khoản!");
				return;
			}
			if (!taikhoan.checkPassword(password)) {
				view.showError("Mật khẩu sai");
				return;
			}
			
			HomeGUI homeGUI = new HomeGUI();
			homeGUI.setVisible(true);
			view.dispose();
			
		} catch (Exception e) {
			view.showError(e);
		}
	}

	// Tạo sự kiện
	public void addEvent() {
		// Sự kiện login
		view.getTxtPassword().addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					view.getBtnLogin().doClick();
				}
			}
		});
		view.getBtnLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				login();
			}
		});
		view.getLblForgotPassword().addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				view.showError("Chưa hỗ trợ!");
			}
		});
	}
}
