package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import com.mysql.cj.jdbc.CallableStatement;

import models.Article;
import models.User;
import services.DataConnection;

public class ArticleCrudController {
	private Connection accessDataBase = null;
	private ResultSet rs = null;
	private PreparedStatement query = null;
	// private ArrayList<Article> result = new ArrayList<Article>();
	static boolean executeOk = false;

	public boolean addNewArticle(Article article, String role) throws SQLException {
		if (role.equals("admin")) {
			try {
				accessDataBase = DataConnection.openConnection();

				String requestAdd = "INSERT INTO `article` (name, spec_art, supplier, creation_date, price_art, quantity_art, type_art_id, art_prov_id,art_user_id ) VALUES(?,?,?,?,?,?,?,?,?)";
				query = accessDataBase.prepareStatement(requestAdd);
				query.setString(1, article.getName());
				query.setString(2, article.getSpectArt());
				query.setString(3, article.getFournisseur());
				query.setString(4, article.getDateFormat());
				query.setFloat(5, article.getPrice());
				query.setInt(6, article.getQuantityArt());
				query.setInt(7, article.getTypeId());
				query.setInt(8, article.getFournisseurID());
				query.setInt(9, User.userId);

				executeOk = query.execute();
				
				System.out.println(requestAdd);
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vous n'avez pas les autorisation necessaire");
		}
		return executeOk;
	}

	public void deleteArt(JTextPane textPaneID, String role) throws SQLException {
		System.out.println(role);
		if (role.equals("admin") ) {
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
		} else {
			JOptionPane.showMessageDialog(null, "Vous n'avez pas les autorisation necessaire");
		}
	}

	public ArrayList<Article> showListArticle() {
		accessDataBase = DataConnection.openConnection();
		ArrayList<Article> result = new ArrayList<Article>();
		String query = "CALL getArticles()";
		Article article = null;
		try {
			CallableStatement stm = (CallableStatement) accessDataBase.prepareCall(query);
			ResultSet rs = stm.executeQuery();

			System.out.println(stm);
			while (rs.next()) {
				article = new Article();
				article.setID(rs.getInt("id_article"));
				article.setName(rs.getString("name"));
				article.setSpectArt(rs.getString("spec_art"));
				article.setFournisseur(rs.getString("sup_name"));
				article.setDateFormat(rs.getString("creation_date"));
				article.setPrice(rs.getFloat("price_art"));
				article.setQuantityArt(rs.getInt("quantity_art"));
				article.setType(rs.getString("type_name"));
				article.setFournisseurID(rs.getInt("art_prov_id"));
				article.setPriceTTC(rs.getFloat("convert_ht_to_ttc(`price_art`)"));
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
			String requestUpDate = "CALL UpDateArticle(?,?,?,?,?,?,?,?,?)";
			CallableStatement statement = (CallableStatement) accessDataBase.prepareCall(requestUpDate);
			statement.setString(1, article.getName());
			statement.setString(2, article.getSpectArt());
			statement.setString(3, article.getFournisseur());
			statement.setFloat(4, article.getPrice());
			statement.setInt(5, article.getQuantityArt());
			statement.setString(6, article.getType());
			statement.setString(7, article.getComment());
			statement.setInt(8, article.getID());
			statement.setInt(9, article.getUserId());
			
			executeOk = statement.execute();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

}
