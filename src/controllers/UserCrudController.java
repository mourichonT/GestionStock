package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.management.relation.Role;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import com.mysql.cj.jdbc.CallableStatement;

import models.Article;
import models.Type;
import models.RoleUser;
import models.User;
import services.DataConnection;

public class UserCrudController {
	private Connection accessDataBase = null;
	private ResultSet rs = null;
	private PreparedStatement query = null;
	public boolean executeOk = false;

	public ArrayList<User> showListUsers() {
		accessDataBase = DataConnection.openConnection();
		ArrayList<User> result = new ArrayList<User>();
		String query = "SELECT `user_id`, `login`, `user_name`, `user_f_name`, `role_user` FROM users INNER JOIN `role` ON (users.user_role_id = role.role_id)";
		User user = null;
		try {
			CallableStatement stm = (CallableStatement) accessDataBase.prepareCall(query);
			ResultSet rs = stm.executeQuery();

			System.out.println(stm);
			while (rs.next()) {
				user = new User();
				user.setIdUser(rs.getInt("user_id"));
				user.setLogin(rs.getString("login"));
				user.setUserName(rs.getString("user_name"));
				user.setUserFName(rs.getString("user_f_name"));
				user.setUserRole(rs.getString("role_user"));

				result.add(user);
				// System.out.println(user.getUserId());
				// System.out.println(user.getRole());
				// System.out.println(user.getUserName());
			}
		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return result;
	}

	public ArrayList<RoleUser> selectRole() throws SQLException {
		accessDataBase = DataConnection.openConnection();
		ArrayList<RoleUser> resultList = new ArrayList<RoleUser>();
		String querySelect = "SELECT * FROM `role`";
		query = accessDataBase.prepareStatement(querySelect);
		// query.setString(1, role);

		try {
			ResultSet rs = query.executeQuery();
			RoleUser roleList = null;

			while (rs.next()) {
				roleList = new RoleUser();
				roleList.setRoleIdUser(rs.getInt("role_id"));
				roleList.setUserRole(rs.getString("role_user"));

				resultList.add(roleList);
			}
		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return resultList;
	}

	public ArrayList<RoleUser> listRole(String role) throws SQLException {
		accessDataBase = DataConnection.openConnection();
		ArrayList<RoleUser> resultList = new ArrayList<RoleUser>();
		String querySelect = "SELECT * FROM `role` WHERE `role_user` =?";
		query = accessDataBase.prepareStatement(querySelect);
		query.setString(1, role);

		try {
			ResultSet rs = query.executeQuery();
			RoleUser roleList = null;

			while (rs.next()) {
				roleList = new RoleUser();
				roleList.setRoleIdUser(rs.getInt("role_id"));
				roleList.setUserRole(rs.getString("role_user"));

				resultList.add(roleList);
			}
		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return resultList;
	}

	public void updateUser(User user) {

		try {
			accessDataBase = DataConnection.openConnection();
			String requestUpDate = "UPDATE users SET `user_name`= ?, `user_f_name`= ?, `user_role_id`=(SELECT `role_id` FROM role WHERE `role_user` = ?) WHERE `user_id`=?";
			CallableStatement statement = (CallableStatement) accessDataBase.prepareCall(requestUpDate);

			statement.setString(1, user.getUserName().toUpperCase());
			statement.setString(2, user.getUserFName());
			statement.setString(3, user.getUserRole());
			statement.setInt(4, user.getIdUser());

			System.out.println(statement);

			executeOk = statement.execute();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("USER GET PASSWORD " + user.getPassword());
		}
	}

	public void changedPassWord(User user) {
		
			try {
				accessDataBase = DataConnection.openConnection();
				String requestUpDate = "UPDATE users SET `password`=? WHERE `user_id`=?";
				CallableStatement statement = (CallableStatement) accessDataBase.prepareCall(requestUpDate);

				statement.setString(1, user.getPassword());
				statement.setInt(2, user.getIdUser());

				System.out.println(statement);

				executeOk = statement.execute();

			} catch (Exception e) {
				System.out.println(e);
				System.out.println("USER GET PASSWORD " + user.getPassword());
			}
		
	}

	public boolean addNewUser(User user, String role) throws SQLException {
		if (role.equals("admin")) {
			try {
				accessDataBase = DataConnection.openConnection();

				String requestAdd = "CALL add_user(?,?,?,?,?)";
				query = accessDataBase.prepareStatement(requestAdd);

				query.setString(1, user.getLogin());
				query.setString(2, user.getUserName().toUpperCase());
				query.setString(3, user.getUserFName());
				query.setString(4, user.getPassword());
				query.setString(5, user.getUserRole());

				System.out.println("CRUD RETOUR    " + user.getUserRole());

				System.out.println(query);
				executeOk = query.execute();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println(query);
			}
		} else {
			System.out.println(role);
			JOptionPane.showMessageDialog(null, "Vous n'avez pas les autorisation necessaire");
		}
		return executeOk;
	}

	public void deleteUser(int index, String role) throws SQLException {

		try {
			accessDataBase = DataConnection.openConnection();
			String requestDelete = "DELETE FROM `users` WHERE `user_id` = ?";
			query = accessDataBase.prepareStatement(requestDelete);
			query.setInt(1, index);

			executeOk = query.execute();
			System.out.println("deleted");
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
