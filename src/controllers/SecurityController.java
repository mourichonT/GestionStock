package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import services.DataConnection;
import services.DataConnectionLogin;
import views.HomeView;
import views.LoginView;

public class SecurityController {
	
	static ResultSet rs =null;
	static PreparedStatement queryConnec = null;
	static Connection accessDataBase = DataConnectionLogin.openConnection();
	
	public static boolean loginRequest (JTextField login_textField, JPasswordField pw) {
	boolean connectOk = false;
	//String request = "SELECT * FROM users WHERE login =? and password=? ";
	String request = "SELECT password FROM users WHERE login == =?";
	try {
		queryConnec = accessDataBase.prepareStatement(request);
		queryConnec.setString(1, login_textField.getText());
		queryConnec.setString(2,  new String(pw.getPassword()));
		
		rs = queryConnec.executeQuery();
		
		if(rs.next()) {
			System.out.println("Bravo vous etes connecter");
			connectOk = true;
			try {
				
				HomeView openApp = new HomeView();
				openApp.frame.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
			return connectOk ;
		} else {
		System.out.println("le mot de passe ou le login ne correspondent pas");	
		}
	}catch(SQLException ex){
		Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
	
	return connectOk ;
	}
}
