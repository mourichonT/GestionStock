package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextPane;

import models.Article;
import services.DataConnection;

public class ArticleCrudController {
	static Connection accessDataBase = null;
	static ResultSet rs = null;
	static PreparedStatement query = null;

	static boolean executeOk = false;

	public boolean addNewArticle(Article article) throws SQLException {

		try {
			accessDataBase = DataConnection.openConnection();

			String requestAdd = "INSERT INTO `article` (name, supplier, creation_date, type, art_prov_id, art_price_id ) VALUES(?,?,?,?,?,?)";
			query = accessDataBase.prepareStatement(requestAdd);
			query.setString(1, article.getName());
			query.setString(2, article.getFournisseur());
			query.setString(3, article.getDateCreation());
			query.setString(4, article.getType());
			query.setInt(5, article.getFournisseurID());
			query.setInt(6, article.getPriceId());

			executeOk = query.execute();

		} catch (SQLException ex) {
			System.out.println(article.getName());
			System.out.println(article.getFournisseur());
			System.out.println(article.getDateCreation());
			System.out.println(article.getType());
			System.out.println(article.getFournisseurID());
			System.out.println(article.getPriceId());
			
			Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex.getMessage());
		}
		return executeOk;
	}

	public void deleteArt(JTextPane textPaneID) throws SQLException {
		try {
			accessDataBase = DataConnection.openConnection();
			String requestDelete = "DELETE FROM `article` WHERE `id_article` = ?";
			query = accessDataBase.prepareStatement(requestDelete);
			query.setString(1, textPaneID.getText());

			executeOk = query.execute();
			System.out.println("deleted");
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public static ArrayList<Article> showListArticle() {
		accessDataBase = DataConnection.openConnection();
		ArrayList<Article> result = new ArrayList<Article>();
		String query = "SELECT * FROM article";
		try {
			Statement stm = accessDataBase.createStatement();
			ResultSet rs = stm.executeQuery(query);
			Article article = null;
			while (rs.next()) {
				article = new Article();
				article.setID(rs.getInt("id_article"));
				article.setName(rs.getString("name"));
				article.setFournisseur(rs.getString("supplier"));
				article.setDateCreation(rs.getString("creation_date"));
				article.setType(rs.getString("type"));
				result.add(article);
			}
			
		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return result;
	}
	
	public void upDateArt(Article article) {
		try {
			accessDataBase = DataConnection.openConnection();
			String requestUpDate = "UPDATE `article` SET `name`=?, `supplier` = ?, `type`=?  WHERE `id_article` = ?";
			
			query = accessDataBase.prepareStatement(requestUpDate);
			query.setString(1, article.getName());
			query.setString(2, article.getFournisseur());
			query.setString(3, article.getType());
			query.setInt(4, article.getID());

			System.out.println(query);
			
			System.out.println("modifier");
			System.out.println(article.getName());
			System.out.println(article.getFournisseur());
			System.out.println(article.getType());
			System.out.println(article.getID());

			executeOk = query.execute();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		
	}
}
