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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import models.Supplier;
import services.DataConnection;

public class SupplierCrudController {

	private Connection accessDataBase = null;
	private ResultSet rs = null;
	private PreparedStatement query = null;

	static boolean executeOk = false;

	public boolean addNewSupplier(Supplier supplier, String role) throws SQLException {

		if (role == "admin") {

			try {
				accessDataBase = DataConnection.openConnection();
				String requestAdd = "INSERT INTO `supplier` (sup_address, sup_name, sup_phone, sup_cont_id) VALUES(?,?,?,?)";
				query = accessDataBase.prepareStatement(requestAdd);
				query.setString(1, supplier.getSupplierAddress());
				query.setString(2, supplier.getSupplierName());
				query.setString(3, supplier.getSupplierPhone());
				query.setInt(4, supplier.getContactId());

				executeOk = query.execute();

			} catch (SQLException ex) {
				Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex.getMessage());
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vous n'avez pas les autorisation necessaire");
		}

		return executeOk;
	}

	public ArrayList<Supplier> listSuppliers() {
		accessDataBase = DataConnection.openConnection();
		ArrayList<Supplier> resultList = new ArrayList<Supplier>();
		String query = "SELECT `sup_id`, `sup_name` FROM supplier";

		try {
			Statement stm = accessDataBase.createStatement();
			ResultSet rs = stm.executeQuery(query);
			Supplier supplierList = null;
			while (rs.next()) {
				supplierList = new Supplier();
				supplierList.setSupId(rs.getInt("sup_id"));
				supplierList.setSupplierName(rs.getString("sup_name"));

				resultList.add(supplierList);
			}

		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return resultList;
	}

	public ArrayList<Supplier> selectedProvider(String name) throws SQLException {
		System.out.println(name);
		accessDataBase = DataConnection.openConnection();
		ArrayList<Supplier> resultSelect = new ArrayList<Supplier>();
		String querySelect = "SELECT * FROM supplier where sup_name = ?";
		query = accessDataBase.prepareStatement(querySelect);
		query.setString(1, name);
		try {

			ResultSet rs = query.executeQuery();

			Supplier selection = null;
			while (rs.next()) {
				selection = new Supplier();
				selection.setSupId(rs.getInt("sup_id"));
				selection.setSupplierAddress(rs.getString("sup_address"));
				selection.setSupplierName(rs.getString("sup_name"));
				selection.setSupplierPhone(rs.getString("sup_phone"));
				selection.setSupplierContact(rs.getString("sup_cont_id"));
				resultSelect.add(selection);
			}

		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return resultSelect;
	}

	public ArrayList<Supplier> showAllProvider() {

		accessDataBase = DataConnection.openConnection();
		ArrayList<Supplier> result = new ArrayList<Supplier>();
		String query = "SELECT * FROM supplier";
		try {
			Statement stm = accessDataBase.createStatement();
			ResultSet rs = stm.executeQuery(query);
			Supplier supplier = null;
			while (rs.next()) {
				supplier = new Supplier();
				supplier.setSupId(rs.getInt("sup_id"));
				supplier.setSupplierAddress(rs.getString("sup_address"));
				supplier.setSupplierName(rs.getString("sup_name"));
				supplier.setSupplierPhone(rs.getString("sup_phone"));
				supplier.setContactId(rs.getInt("sup_cont_id"));
				result.add(supplier);
			}

		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return result;
	}

	public void deleteSup(String id, String role) {
		if (role == "admin") {
			try {
				accessDataBase = DataConnection.openConnection();
				String requestDelete = "DELETE FROM `supplier` WHERE `sup_id` = ?";
				query = accessDataBase.prepareStatement(requestDelete);
				query.setString(1, id);
				System.out.println(query);
				executeOk = query.execute();
				System.out.println("deleted");
			} catch (SQLException ex) {
				System.out.println(ex);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vous n'avez pas les autorisation necessaire");
		}

	}

	public void upDateSup(Supplier supplier, String role) {
		if (role == "admin") {

			try {
				accessDataBase = DataConnection.openConnection();
				String requestUpDate = "UPDATE `supplier` SET `sup_address`=?, `sup_name` = ?, `sup_phone`=?  WHERE `sup_id` = ?";

				query = accessDataBase.prepareStatement(requestUpDate);
				query.setString(1, supplier.getSupplierAddress());
				query.setString(2, supplier.getSupplierName());
				query.setString(3, supplier.getSupplierPhone());
				query.setInt(4, supplier.getSupId());

				System.out.println(query);

				executeOk = query.execute();
			} catch (SQLException ex) {
				System.out.println(ex);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vous n'avez pas les autorisation necessaire");
		}

	}
}
