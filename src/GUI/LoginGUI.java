/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import GUI.renderers.RoundJButton;
import GUI.renderers.RoundJPasswordField;
import GUI.renderers.RoundJTextField;
import BUS.LoginBUS;

/**
 *
 * @author Hải Minh
 */
public class LoginGUI extends JFrame {

    private JPanel loginContainer;
//    private JButton turnoffButton;
    private JPanel inputLoginPanel;
    private JLabel applogo;
    private JLabel appname;
    private JPanel dangnhapPanel;
    private JLabel tendangnhap;
    private JTextField inputtendangnhap;
    private JPasswordField inputpassword;
    private JLabel matkhau;
    private JPanel matkhauPanel;

    private JButton dangnhapButton;
    private JCheckBox showPass;
    

    public LoginGUI() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Đăng nhập");
        setBackground(Color.white);
    
        inputLoginPanel = new JPanel();
        inputLoginPanel.setBackground(BASE.color_main);

        loginContainer = new JPanel();
        loginContainer.setBackground(Color.white);
        loginContainer.setLayout(new GridBagLayout());
        loginContainer.setSize(394, 402);
        GridBagConstraints loginContainergbc = new GridBagConstraints();

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
        inputLoginPanelrgbc.insets = new Insets(20, 0, 0, 0);
        inputLoginPanel.add(applogo, inputLoginPanelrgbc);

        appname = new JLabel("Hãy đăng nhập vào cửa hàng");
        appname.setFont(new Font("Roboto", Font.BOLD, 16));
        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 1;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanelrgbc.insets = new Insets(15, 0, 15, 0);
        inputLoginPanel.add(appname, inputLoginPanelrgbc);

        dangnhapPanel = new JPanel(new BorderLayout());
        dangnhapPanel.setBackground(BASE.color_main);
        tendangnhap = new JLabel("User Name");
        tendangnhap.setFont(new Font("Roboto", Font.PLAIN, 14));
        inputtendangnhap = new RoundJTextField(25, 25, 25);
        inputtendangnhap.setFont(new Font("Roboto", Font.PLAIN, 14));
        inputtendangnhap.setPreferredSize(new Dimension(370, 25));
        
        dangnhapPanel.add(tendangnhap, BorderLayout.NORTH);
        dangnhapPanel.add(inputtendangnhap, BorderLayout.SOUTH);

        matkhauPanel = new JPanel(new BorderLayout());
        matkhauPanel.setBackground(BASE.color_main);
        matkhau = new JLabel("Password");
        matkhau.setFont(new Font("Roboto", Font.PLAIN, 14));
        inputpassword = new RoundJPasswordField(25, 25, 25);
        inputpassword.setFont(new Font("Roboto", Font.PLAIN, 14));
        inputpassword.setPreferredSize(new Dimension(370, 25));
       
        showPass = new JCheckBox();
        showPass.setIcon(new ImageIcon(getClass().getResource("/Image/eye_hide.png")));
        showPass.setBackground(BASE.color_main);
        showPass.addActionListener(new ActionListener() {
            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (showPass.isSelected()) {
	                	
	                		inputpassword.setEchoChar((char) 0);
	                        showPass.setIcon(new ImageIcon(getClass().getResource("/Image/eye.png")));
	                } 
	                else {
	            		inputpassword.setEchoChar('•');
                        showPass.setIcon(new ImageIcon(getClass().getResource("/Image/eye_hide.png")));
	                }
	        	}
	        });  
                    
            
        matkhauPanel.add(matkhau, BorderLayout.NORTH);
        matkhauPanel.add(inputpassword, BorderLayout.WEST);
        matkhauPanel.add(showPass, BorderLayout.EAST);
        

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

        dangnhapButton = new RoundJButton("Đăng nhập", 30, 30);
        dangnhapButton.setFont(new Font("Roboto", Font.BOLD, 15));
        dangnhapButton.setBorderPainted(false);
        dangnhapButton.setBackground(BASE.color_header_tbl);

        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 4;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanelrgbc.fill = GridBagConstraints.BOTH;
        inputLoginPanelrgbc.insets = new Insets(20, 14, 30, 39);
        inputLoginPanel.add(dangnhapButton, inputLoginPanelrgbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(loginContainer, gbc);

        setSize(412, 410);
        setVisible(true);
        setLocationRelativeTo(null);
        
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

    public static void main(String args[]) {
        LoginGUI loginGUI = new LoginGUI();
        LoginBUS loginBUS = new LoginBUS(loginGUI);
    }
}
