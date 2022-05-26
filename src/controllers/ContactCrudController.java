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

import models.Contact;
import models.Supplier;
import services.DataConnection;

public class ContactCrudController {

	static Connection accessDataBase = null;
	static ResultSet rs = null;
	static PreparedStatement query = null;

	static boolean executeOk = false;

	public int addNewContact(Contact contact) throws SQLException {

		int lastId =0;
		try {
			accessDataBase = DataConnection.openConnection();
			
			String requestAdd = "INSERT INTO `contact` (cont_name, cont_f_name,cont_phone,cont_cell, cont_mail, cont_position, cont_comment) VALUES(?,?,?,?,?,?,?)";
			query = accessDataBase.prepareStatement(requestAdd, Statement.RETURN_GENERATED_KEYS);
			query.setString(1, contact.getContName());
			query.setString(2, contact.getContFName());
			query.setString(3, contact.getContPhone());
			query.setString(4, contact.getContCell());
			query.setString(5, contact.getContMail());
			query.setString(6, contact.getContPosition());
			query.setString(7, contact.getContComment());
System.out.println(query);

			query.execute();
			
			rs = query.getGeneratedKeys();
			if(rs.next()) {
				lastId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex.getMessage());
		}
		return lastId;
	}

	public void deleteCont(JTextPane textPaneID) throws SQLException {
		try {
			accessDataBase = DataConnection.openConnection();
			String requestDelete = "DELETE FROM `contact` WHERE `cont_id` = ?";
			query = accessDataBase.prepareStatement(requestDelete);
			query.setString(1, textPaneID.getText());

			executeOk = query.execute();
			System.out.println("deleted");
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	
	public static ArrayList<Contact> seletedContact(String id){
		
		System.out.println(id);
		accessDataBase = DataConnection.openConnection();
		ArrayList<Contact> resultSelect = new ArrayList<Contact>();
		String querySelect = "SELECT * FROM contact where cont_id = ?";
		try {
			query = accessDataBase.prepareStatement(querySelect);
			query.setString(1, id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			ResultSet rs = query.executeQuery();
			
			Contact selection = null;
			while (rs.next()) {
				selection = new Contact();
				selection.setContId(rs.getInt("cont_id"));
				selection.setContName(rs.getString("cont_name"));
				selection.setContFName(rs.getString("cont_f_name"));
				selection.setContPhone(rs.getString("cont_phone"));
				selection.setContCell(rs.getString("cont_cell"));
				selection.setContMail(rs.getString("cont_mail"));
				selection.setContPosition(rs.getString("cont_Mail"));
				selection.setContComment(rs.getString("cont_comment"));
				resultSelect.add(selection);
			}
			
		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
	
		return resultSelect;
	
	}
	
	
	public static ArrayList<Contact> showListContact() {
		accessDataBase = DataConnection.openConnection();
		ArrayList<Contact> result = new ArrayList<Contact>();
		String query = "SELECT * FROM contact";
		try {
			Statement stm = accessDataBase.createStatement();
			ResultSet rs = stm.executeQuery(query);
			Contact contact = null;
			while (rs.next()) {
				contact = new Contact();
				contact.setContId(rs.getInt("cont_id"));
				contact.setContName(rs.getString("cont_name"));
				contact.setContFName(rs.getString("cont_f_name"));
				contact.setContPhone(rs.getString("cont_phone"));
				contact.setContCell(rs.getString("cont_cell"));
				contact.setContMail(rs.getString("cont_mail"));
				contact.setContPosition(rs.getString("cont_position"));
				contact.setContComment(rs.getString("cont_comment"));
				result.add(contact);
			}

		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return result;
	}
	
	public void upDateContact(Contact contact) {
		try {
			accessDataBase = DataConnection.openConnection();
			String requestUpDate = "UPDATE `contact` SET `cont_name`=?, `cont_f_name` = ?, `cont_phone`=?, `cont_cell` = ?, `cont_mail` = ?,  `cont_position` = ?  WHERE `sup_id` = ?";
			
			query = accessDataBase.prepareStatement(requestUpDate);
			query.setString(1, contact.getContName());
			query.setString(2, contact.getContFName());
			query.setString(3, contact.getContPhone());
			query.setString(4, contact.getContCell());
			query.setString(5, contact.getContMail());
			query.setString(6, contact.getContPosition());

			System.out.println(query);
			
			executeOk = query.execute();
			} catch (SQLException ex) {
			System.out.println(ex);
				}
		}
}

