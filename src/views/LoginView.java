package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.SecurityController;
import javax.swing.SwingConstants;
import java.awt.Window.Type;

public class LoginView {

	private JFrame ident;
	private JLabel Password;
	private JTextField Login_textField;
	public static boolean flag =false;
	public static boolean connectOk =false;
	private SecurityController securityController =  new SecurityController();
	private JLabel Identitification;

	public LoginView() {
		initialize();
		ident.setVisible(true);
	}

	private void initialize() {
		ident = new JFrame();
		ident.setType(Type.POPUP);
		ident.setAutoRequestFocus(false);
		ident.setBounds(100, 100, 441, 407);
		ident.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ident.getContentPane().setLayout(null);
		
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screen.height;
		int width = screen.width;
		
		ident.setLocationRelativeTo(null);
		
		JLabel Login = new JLabel("Login");
		Login.setForeground(Color.LIGHT_GRAY);
		Login.setBounds(54, 83, 299, 49);
		Login.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Login.setFocusTraversalKeysEnabled(false);
		ident.getContentPane().add(Login);
		
		Password = new JLabel("Password");
		Password.setForeground(Color.LIGHT_GRAY);
		Password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Password.setFocusTraversalKeysEnabled(false);
		Password.setBounds(54, 172, 299, 49);
		ident.getContentPane().add(Password);
		
		Login_textField = new JTextField();
		Login_textField.setHorizontalAlignment(SwingConstants.CENTER);
		Login_textField.setFont(new Font("Tahoma", Font.ITALIC, 16));
		Login_textField.setPreferredSize(new Dimension(19, 19));
		Login_textField.setBounds(54, 130, 299, 43);
		ident.getContentPane().add(Login_textField);
		Login_textField.setColumns(10);
	
		
		final JPasswordField pw = new JPasswordField(20);
		pw.setHorizontalAlignment(SwingConstants.CENTER);
		pw.setPreferredSize(new Dimension(19, 19));
		pw.setBounds(54, 219, 299, 43);
		ident.getContentPane().add(pw);

		
		Identitification = new JLabel("Identification");
		Identitification.setForeground(Color.GRAY);
		Identitification.setFont(new Font("Impact", Font.PLAIN, 31));
		Identitification.setBackground(Color.WHITE);
		Identitification.setBounds(24, 10, 966, 63);
		ident.getContentPane().add(Identitification);
		
		JButton Connexion = new JButton("Connexion");
		Connexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(securityController.loginRequest(Login_textField, pw)!=0){
					ident.setVisible(false);
					} else {
						System.out.println("test si faux");
						Login_textField.setText("");
						pw.setText("");
					}
			}
		});
		Connexion.setFont(new Font("Tahoma", Font.PLAIN, 21));
		Connexion.setBounds(131, 303, 151, 35);
		ident.getContentPane().add(Connexion);
	
	}
}
