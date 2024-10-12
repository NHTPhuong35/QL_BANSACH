/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BUS.ErrorPopup;
import GUI.renderers.RoundJButton;
import GUI.renderers.RoundJPasswordField;
import GUI.renderers.RoundJTextField;

/**
 *
 * @author Hải Minh
 */
public class LoginGUI extends JFrame {

    private JPanel loginContainer;
    private JButton turnoffButton;
    private JPanel inputLoginPanel;
    private JLabel applogo;
    private JLabel appname;
    private JPanel dangnhapPanel;
    private JLabel tendangnhap;
    private JTextField inputtendangnhap;
    private JPasswordField inputpassword;
    private JLabel matkhau;
    private JPanel matkhauPanel;
    private JLabel quenmatkhau;
    private JButton dangnhapButton;

    public LoginGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Đăng nhập");
        setBackground(Color.white);

        turnoffButton = new JButton();
        turnoffButton.setIcon(new ImageIcon(getClass().getResource("/Image/icon-off.png")));
        turnoffButton.setBorderPainted(false);
        turnoffButton.setBackground(Color.white);
        
        inputLoginPanel = new JPanel();
        inputLoginPanel.setBackground(Color.decode("#98DCE2"));

        loginContainer = new JPanel();
        loginContainer.setBackground(Color.white);
        loginContainer.setLayout(new GridBagLayout());
        loginContainer.setSize(394, 377);
        GridBagConstraints loginContainergbc = new GridBagConstraints();

        loginContainergbc.gridx = 0;
        loginContainergbc.gridy = 0;
        loginContainergbc.weightx = 1;
        loginContainergbc.weighty = 1;
        loginContainergbc.anchor = GridBagConstraints.EAST;
        loginContainer.add(turnoffButton, loginContainergbc);

        loginContainergbc.gridx = 0;
        loginContainergbc.gridy = 1;
        loginContainergbc.weightx = 1;
        loginContainergbc.weighty = 1;
        loginContainergbc.anchor = GridBagConstraints.CENTER;
        loginContainer.add(inputLoginPanel, loginContainergbc);

        inputLoginPanel.setLayout(new GridBagLayout());
        GridBagConstraints inputLoginPanelrgbc = new GridBagConstraints();

        applogo = new JLabel();
        applogo.setSize(97, 75);
        applogo.setIcon(new ImageIcon(getClass().getResource("/Image/guiiconbook.png")));
        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 0;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanelrgbc.insets = new Insets(10, 0, 0, 0);
        inputLoginPanel.add(applogo, inputLoginPanelrgbc);

        appname = new JLabel("Hãy đăng nhập vào cửa hàng");
        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 1;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanel.add(appname, inputLoginPanelrgbc);

        dangnhapPanel = new JPanel();
        dangnhapPanel.setLayout(new BorderLayout());
        dangnhapPanel.setBackground(Color.decode("#98DCE2"));
        tendangnhap = new JLabel("Tên đăng nhập");
        inputtendangnhap = new RoundJTextField(30, 30, 30);
        inputtendangnhap.setPreferredSize(new Dimension(370, 25));
        dangnhapPanel.add(tendangnhap, BorderLayout.NORTH);
        dangnhapPanel.add(inputtendangnhap, BorderLayout.SOUTH);

        matkhauPanel = new JPanel();
        matkhauPanel.setLayout(new BorderLayout());
        matkhauPanel.setBackground(Color.decode("#98DCE2"));
        matkhau = new JLabel("Mật khẩu");
        inputpassword = new RoundJPasswordField(30, 30, 30);
        inputpassword.setPreferredSize(new Dimension(370, 25));
        matkhauPanel.add(matkhau, BorderLayout.NORTH);
        matkhauPanel.add(inputpassword, BorderLayout.SOUTH);

        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 2;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanelrgbc.anchor = GridBagConstraints.WEST;
        inputLoginPanelrgbc.insets = new Insets(0, 14, 0, 14);
        inputLoginPanel.add(dangnhapPanel, inputLoginPanelrgbc);

        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 3;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanelrgbc.anchor = GridBagConstraints.CENTER;
        inputLoginPanelrgbc.insets = new Insets(0, 14, 0, 14);
        inputLoginPanel.add(matkhauPanel, inputLoginPanelrgbc);

        quenmatkhau = new JLabel("Quên mật khẩu");
        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 4;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanelrgbc.insets = new Insets(10, 0, 10, 0);
        inputLoginPanel.add(quenmatkhau, inputLoginPanelrgbc);

        dangnhapButton = new RoundJButton("Đăng nhập", 30, 30);
        dangnhapButton.setBorderPainted(false);
        dangnhapButton.setBackground(Color.decode("#56B7C0"));

        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 5;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanelrgbc.fill = GridBagConstraints.BOTH;
        inputLoginPanelrgbc.insets = new Insets(10, 0, 10, 0);
        inputLoginPanel.add(dangnhapButton, inputLoginPanelrgbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.insets = new Insets(14, 15, 14, 15);
        add(loginContainer, gbc);

        setSize(412, 431);
        setVisible(true);
        setLocationRelativeTo(null);
        
    }
    
	public void showError(String message) {
		ErrorPopup.show(new Exception(message));
	}
	
	public void showError(Exception e) {
		ErrorPopup.show(e);
	}
    
	public JPasswordField getTxtPassword() {
		return inputpassword;
	}

	public JTextField getTxtUsername() {
		return inputtendangnhap;
	}
	
	public JButton getBtnLogin() {
		return dangnhapButton;
	}
	
	public JLabel getLblForgotPassword() {
		return quenmatkhau;
	}

    public static void main(String args[]) {
        new LoginGUI();
    }


}
