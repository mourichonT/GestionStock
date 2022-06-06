package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import models.Type;
import models.User;
import services.DataConnection;

public class UserCrudController {
	private Connection accessDataBase = null;
	private ResultSet rs = null;
	private PreparedStatement query = null;
	static boolean executeOk = false;
	
	
	public ArrayList<User> showListUsers() {
		accessDataBase = DataConnection.openConnection();
		ArrayList<User> result = new ArrayList<User>();
		String query = "SELECT user_id, login, user_name, user_f_name, role_user FROM users ";
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
				//System.out.println(user.getUserId());
				//System.out.println(user.getRole());
				//System.out.println(user.getUserName());
			}
		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return result;
	}
	

	public ArrayList<User> listRole( String role)throws SQLException{
		accessDataBase = DataConnection.openConnection();
		ArrayList<User> resultList = new ArrayList<User>();
		String querySelect = "SELECT `user_id`, `role_user`FROM users WHERE role_user = ?";
		query = accessDataBase.prepareStatement(querySelect);
		query.setString(1, role);
		
		try {
			ResultSet rs = query.executeQuery();
			User roleList = null;
			while (rs.next()) {
				roleList = new User();
				roleList.setIdUser(rs.getInt("user_id"));
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
			String requestUpDate = "UPDATE  users SET `user_name`= ?, `user_f_name`=?, `role_user`=?, `password`=? WHERE `user_id`=?";
			CallableStatement statement = (CallableStatement) accessDataBase.prepareCall(requestUpDate);
			
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getUserFName());
			statement.setString(3, user.getUserRole());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getIdUser());
			
			System.out.println("ID SELECTION CRUD" +user.getIdUser());

			executeOk = statement.execute();
			
			
		} catch (Exception e) {
			System.out.println(e);	
		System.out.println("USER GET PASSWORD "+user.getPassword());	
		}
	}
	

}
