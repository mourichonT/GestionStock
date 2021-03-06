package controllers;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.User;
import services.DataConnection;
import services.DataConnectionLogin;
import views.HomeView;

public class SecurityController {

	private ResultSet rs = null;
	private PreparedStatement queryConnec = null;
	private Connection accessDataBase = DataConnectionLogin.openConnection();
	public static User role = new User();
	static String useRole = null;
	private ArrayList<User> resultRole = null;

	public int loginRequest(JTextField login_textField, JPasswordField pw) {
		boolean connectOk = false;
		int IdUser = 0;
		resultRole = new ArrayList<User>();
		String passwordHashed = doHashing(pw);
		String request = "CALL login(?,?)";
		try {
			queryConnec = accessDataBase.prepareStatement(request);
			queryConnec.setString(1, login_textField.getText());
			queryConnec.setString(2, new String(passwordHashed));

			rs = queryConnec.executeQuery();

			while (rs.next()) {
				role.setUserId(rs.getInt("user_id"));
				resultRole.add(role);
				IdUser = role.getUserId();
			}

			if (IdUser != 0) {
				getRoleUser(IdUser);
				System.out.println(role.getLogin());
				System.out.println(IdUser);
				connectOk = true;
				HomeView openApp = new HomeView();
				openApp.frame.setVisible(true);

			} else {
				System.out.println("le mot de passe ou le login ne correspondent pas");
			}

		} catch (SQLException ex) {
			Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
		}

		return IdUser;
	}

	public ArrayList<User> getRoleUser(int userId) {
		System.out.println(userId);

		String requestRole = "SELECT `role_user`, `user_id` FROM users INNER JOIN `role` ON  (users.user_role_id = role.role_id ) WHERE user_id =?";
		try {
			queryConnec = accessDataBase.prepareStatement(requestRole);
			queryConnec.setInt(1, userId);

			rs = queryConnec.executeQuery();

			while (rs.next()) {
				role.setRole(rs.getString("role_user"));
				resultRole.add(role);

				useRole = role.getRole();
			}

		} catch (SQLException ex) {
			Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		return resultRole;
	}

	public String doHashing(JPasswordField pw) {

		String password = new String(pw.getPassword());
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(password.getBytes());
			byte[] resultByteArray = md.digest();
			StringBuilder sb = new StringBuilder();

			for (byte b : resultByteArray) {
				sb.append(String.format("%x", b));
			}

			return sb.toString();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";
	}

}
