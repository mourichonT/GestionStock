package views;

import java.awt.*;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import controllers.ArticleCrudController;
import controllers.SupplierCrudController;
import controllers.TypeCrudController;
import models.Article;
import models.Supplier;
import models.Type;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

public class AddArticleView extends JFrame {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtType;
	private JDateChooser dateChooser;
	private String selectedItem;
	private int selectedItemType;
	private Object[] listData = null;
	private Object[] IdSupSelect = null;
	private Object[] IdSTypSelect = null;
	private Object[] listDataType;

	private SupplierCrudController supplierCrudController = new SupplierCrudController();
	private TypeCrudController typeCrudController = new TypeCrudController();

	static Connection accessDataBase = null;
	static ResultSet rs = null;
	static PreparedStatement queryAdd = null;
	private JTextField txtPrice;
	private JTextField textFieldQuant;
	private JTextField textSpec;
	private int supId = 0;
	private int typId = 0;
	// protected static Object add;

	public AddArticleView() {
		setTitle("Frame");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 462, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 426, 41);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ajouter un article");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(10, 0, 406, 35);
		panel.add(lblNewLabel);

		JLabel lblName = new JLabel("Nom :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(25, 85, 97, 28);
		contentPane.add(lblName);

		JLabel lblSpec = new JLabel("Nom :");
		lblSpec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpec.setBounds(25, 115, 97, 28);
		contentPane.add(lblSpec);

		textSpec = new JTextField();
		textSpec.setColumns(10);
		textSpec.setBounds(142, 120, 284, 19);
		contentPane.add(textSpec);

		JLabel lblProv = new JLabel("Fournisseur :");
		lblProv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProv.setBounds(25, 143, 97, 28);
		contentPane.add(lblProv);

		JLabel lblType = new JLabel("Type :");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(25, 205, 97, 28);
		contentPane.add(lblType);

		JLabel lblDate = new JLabel("Date de creation :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(25, 175, 185, 28);
		contentPane.add(lblDate);

		txtName = new JTextField();
		txtName.setBounds(142, 90, 284, 19);
		txtName.setColumns(10);
		contentPane.add(txtName);

		JLabel lblPriceHT = new JLabel("Prix :");
		lblPriceHT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPriceHT.setBounds(25, 276, 97, 28);
		contentPane.add(lblPriceHT);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(142, 281, 48, 19);
		contentPane.add(txtPrice);

		JLabel lbleuro = new JLabel("\u20AC ");
		lbleuro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbleuro.setBounds(200, 276, 97, 28);
		contentPane.add(lbleuro);

		textFieldQuant = new JTextField();
		textFieldQuant.setColumns(10);
		textFieldQuant.setBounds(142, 243, 48, 19);
		contentPane.add(textFieldQuant);

		JLabel lblQuantit = new JLabel("Quantit\u00E9");
		lblQuantit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantit.setBounds(25, 238, 97, 28);
		contentPane.add(lblQuantit);

		final ArrayList<Supplier> listSuppliers = supplierCrudController.listSuppliers();
		Object[] supData = new Object[listSuppliers.size()];
		listData = new Object[listSuppliers.size()];
		IdSupSelect = new Object[listSuppliers.size()];

		for (int i = 0; i < listSuppliers.size(); i++) {
			listData[i] = listSuppliers.get(i).getSupplierName();
			IdSupSelect[i] = listSuppliers.get(i).getSupId();
		}

		final JComboBox<Object> list = new JComboBox(listData);
		list.setSelectedIndex(-1);
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				selectedItem = (String) list.getSelectedItem();
				int selectId = list.getSelectedIndex();
				supId = (Integer) IdSupSelect[selectId];

			}
		});
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(142, 150, 284, 19);
		contentPane.add(list);

		final ArrayList<models.Type> listType = typeCrudController.listTypes();
		Object[] typData = new Object[listSuppliers.size()];
		listDataType = new Object[listType.size()];
		IdSTypSelect = new Object[listType.size()];

		for (int i = 0; i < listType.size(); i++) {
			listDataType[i] = listType.get(i).getTypeName();
			IdSTypSelect[i] = listType.get(i).getTypeId();
		}

		final JComboBox<Object> typList = new JComboBox(listDataType);
		typList.setSelectedIndex(-1);
		typList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				selectedItemType = typList.getSelectedIndex();
				int selectIdtype = typList.getSelectedIndex();
				typId = (Integer) IdSTypSelect[selectIdtype];
			}

		});

		typList.setBackground(new Color(255, 255, 255));
		typList.setBounds(142, 210, 284, 19);
		contentPane.add(typList);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(142, 180, 284, 19);
		contentPane.add(dateChooser);

		JButton btnNewButton = new JButton("Ajouter");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				int id = 0;
				String name = txtName.getText();
				String date = sdf.format(dateChooser.getDate());
				float price = (Float.parseFloat(txtPrice.getText()));
				int TypId = typId;
				int quantity = (Integer.parseInt(textFieldQuant.getText()));
				int SupId = supId;

				Article article = new Article(0, txtName.getText(), textSpec.getText(), (String) list.getSelectedItem(),
						sdf.format(dateChooser.getDate()), price, quantity, TypId, SupId);

				boolean saveOk = false;

				if (date == null) {
					JOptionPane.showMessageDialog(null, "la date n'est pas définis");
					System.out.println("la date n'est pas définis");

				} else if (list.getSelectedItem() == null) {
					System.out.println("le fournisseur article n'est pas définis");
					JOptionPane.showMessageDialog(null, "le fournisseur article n'est pas définis");

				} else if (name == null) {
					System.out.println(" le nom n'est pas définis");
					JOptionPane.showMessageDialog(null, "le nom n'est pas définis");

				} else {

					try {
						ArticleCrudController addArt = new ArticleCrudController();
						JOptionPane.showMessageDialog(null, "enregistré");
						saveOk = addArt.addNewArticle(article, User.role);
						
						StockView skv;
						try {
							skv = new StockView();
							HomeView.desk.add(skv);
							skv.show();
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (PropertyVetoException e1) {
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					contentPane.removeAll();
					if (!saveOk) {
						contentPane.removeAll();
					}

				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(167, 341, 85, 21);
		contentPane.add(btnNewButton);

	}
}
