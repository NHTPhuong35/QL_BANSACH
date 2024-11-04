/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
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

import BUS.LoginBUS;
import GUI.renderers.RoundJButton;
import GUI.renderers.RoundJPasswordField;
import GUI.renderers.RoundJTextField;


public class LoginGUI extends JFrame {

    private JPanel loginContainer;
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
    private JButton logoutButton;
    private JCheckBox showPass;
    

    public LoginGUI() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        inputLoginPanel = new JPanel();
        inputLoginPanel.setBackground(BASE.color_main);

        loginContainer = new JPanel();
        loginContainer.setBackground(BASE.color_main);
        loginContainer.setLayout(new GridBagLayout());
        loginContainer.setPreferredSize(new Dimension(412, 421));
        GridBagConstraints loginContainergbc = new GridBagConstraints();
        
        logoutButton = new JButton();
        logoutButton.setIcon(new ImageIcon(getClass().getResource("/Image/icons8-power-off-button-501.png")));
        logoutButton.setBackground(BASE.color_main);
        logoutButton.setBorder(null);
        logoutButton.setSize(30, 30);

        loginContainergbc.gridx = 0;
        loginContainergbc.gridy = 0;
        loginContainergbc.anchor = GridBagConstraints.EAST;
        loginContainergbc.insets = new Insets(10, 0, 0, 10);
        loginContainer.add(logoutButton, loginContainergbc);

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
        appname.setFont(new Font("Roboto", Font.BOLD, 17));
        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 1;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanelrgbc.insets = new Insets(15, 0, 15, 0);
        inputLoginPanel.add(appname, inputLoginPanelrgbc);

        dangnhapPanel = new JPanel(new BorderLayout());
        dangnhapPanel.setBackground(BASE.color_main);
        tendangnhap = new JLabel("User Name");
        tendangnhap.setFont(new Font("Roboto", Font.PLAIN, 15));
        inputtendangnhap = new RoundJTextField(25, 25, 25);
        inputtendangnhap.setFont(new Font("Roboto", Font.PLAIN, 14));
        inputtendangnhap.setPreferredSize(new Dimension(380, 29));
        
        dangnhapPanel.add(tendangnhap, BorderLayout.NORTH);
        dangnhapPanel.add(inputtendangnhap, BorderLayout.SOUTH);

        matkhauPanel = new JPanel(new BorderLayout());
        matkhauPanel.setBackground(BASE.color_main);
        matkhau = new JLabel("Password");
        matkhau.setFont(new Font("Roboto", Font.PLAIN, 15));
        inputpassword = new RoundJPasswordField(25, 25, 25);
        inputpassword.setFont(new Font("Roboto", Font.PLAIN, 14));
        inputpassword.setPreferredSize(new Dimension(380, 29));
       
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
        inputLoginPanelrgbc.insets = new Insets(0, 0, 0, 0);
        inputLoginPanel.add(dangnhapPanel, inputLoginPanelrgbc);

        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 3;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanelrgbc.anchor = GridBagConstraints.CENTER;
        inputLoginPanelrgbc.insets = new Insets(0, 0, 0, 0);
        inputLoginPanel.add(matkhauPanel, inputLoginPanelrgbc);

        dangnhapButton = new RoundJButton("Đăng nhập", 30, 30);
        dangnhapButton.setFont(new Font("Roboto", Font.BOLD, 16));
        dangnhapButton.setBorderPainted(false);
        dangnhapButton.setBackground(BASE.color_header_tbl);

        inputLoginPanelrgbc.gridx = 0;
        inputLoginPanelrgbc.gridy = 4;
        inputLoginPanelrgbc.weightx = 1;
        inputLoginPanelrgbc.weighty = 1;
        inputLoginPanelrgbc.fill = GridBagConstraints.BOTH;
        inputLoginPanelrgbc.insets = new Insets(20, 0, 25, 25);
        inputLoginPanel.add(dangnhapButton, inputLoginPanelrgbc);

        setContentPane(loginContainer);
        setUndecorated(true);
        pack();  
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
	
	public JButton getBtnLogout() {
		return logoutButton;
	}	

    public static void main(String args[]) {
        LoginGUI loginGUI = new LoginGUI();
        LoginBUS loginBUS = new LoginBUS(loginGUI);
    }
}
