package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Supplier;
import models.Type;
import services.DataConnection;

public class TypeCrudController {
	private Connection accessDataBase = null;
	private ResultSet rs = null;
	private PreparedStatement query = null;

	static boolean executeOk = false;

	public ArrayList<Type> listTypes() {
		accessDataBase = DataConnection.openConnection();
		ArrayList<Type> resultList = new ArrayList<Type>();
		String query = "SELECT `id_type`, `type_name` FROM type";

		try {
			Statement stm = accessDataBase.createStatement();
			ResultSet rs = stm.executeQuery(query);
			Type TypList = null;
			while (rs.next()) {
				TypList = new Type();
				TypList.setTypeId(rs.getInt("id_type"));
				TypList.setTypeName(rs.getString("type_name"));

				resultList.add(TypList);
			}

		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return resultList;
	}

	public ArrayList<Type> selectedType(String type) throws SQLException {
		accessDataBase = DataConnection.openConnection();
		ArrayList<Type> resultSelect = new ArrayList<Type>();
		String querySelect = "SELECT * FROM type where type_name = ?";
		query = accessDataBase.prepareStatement(querySelect);
		query.setString(1, type);
		try {

			ResultSet rs = query.executeQuery();

			Type selection = null;
			while (rs.next()) {
				selection = new Type();
				selection.setTypeId(rs.getInt("id_type"));
				selection.setTypeName(rs.getString("type_name"));

				resultSelect.add(selection);
			}

		} catch (Exception e) {
			System.err.println("erreur dans la recupération de la requete" + e);
		}
		return resultSelect;
	}

}
