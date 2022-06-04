package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.relation.Role;

import controllers.SecurityController;
import models.User;


public class DataConnection {
	
	static Connection accessDataBase = null;
	private static ResultSet rs =null;
	private PreparedStatement queryConnec = null;
	//private static DataConnectionLogin login = new DataConnectionLogin();
	//login.getLogin();
	
	
	

	public static Connection openConnection() {
		//User role.getRole();
		
		//System.out.println(userRole);
		/* Parametres de connexion */
		String url = "jdbc:mysql://127.0.0.1:3306/nesti2";

	 	String utilisateur = "admin";
		String motDePasse = "admin";
			try {
				accessDataBase = DriverManager.getConnection(url, utilisateur, motDePasse);
				System.out.println("try to connect...");
				
			} catch (SQLException ex) {
				Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
			}
			System.out.println("connexion réussi");
			return accessDataBase;
		}

		/**
		 * True si la connexion est OK
		 *
		 * @return
		 */
		public static boolean testConnection() {
			boolean flag = false;
			try {
				if (accessDataBase != null) {
					if (!accessDataBase.isClosed()) {
						System.out.println("Connection au serveur... OK");
						flag = true;
					}
				}
			} catch (SQLException ex) {
				System.out.println("connection failed");
				Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
			}
			return flag;
		}

		
		public static void closeConnection() {
			if (accessDataBase != null) {
				try {
					accessDataBase.close();
					System.out.println("Close connection");
				} catch (SQLException e) {
					System.err.println("Erreur fermreture: " + e.getMessage());
				}
			}
		}
}
