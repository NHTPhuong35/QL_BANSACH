package BUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import GUI.HomeGUI;
import GUI.LoginGUI;
import GUI.ShowDiaLog;

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
			// Kiểm tra nếu username hoặc password là rỗng hoặc null
			if (username == null || username.trim().isEmpty()||password == null || password.trim().isEmpty()) {
				new ShowDiaLog("Không được để trống!", 1);
				return;
			}
			if (taikhoan == null) {
				new ShowDiaLog("Không tồn tại tài khoản!", 1);
				return;
			}
			if (!taikhoan.checkPassword(password)) {
				new ShowDiaLog("Mật khẩu sai!", 1);
			
				return;
			}
			HomeBUS home = new HomeBUS(new HomeGUI());
			view.dispose();			
			
		} catch (Exception e) {
            e.printStackTrace();
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

	}

}
