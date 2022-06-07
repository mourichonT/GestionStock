package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import com.toedter.calendar.JDateChooser;

import controllers.ArticleCrudController;
import controllers.ContactCrudController;
import controllers.SupplierCrudController;
import models.Article;
import models.Contact;
import models.Supplier;
import models.User;

import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;

public class AddSupplierView extends JFrame {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtNameCont;
	private String selectedItem;
	private String[] listData = null;
	private SupplierCrudController supplierCrudController = new SupplierCrudController();
	private NumberFormat format = NumberFormat.getInstance();
	private NumberFormatter formatter = new NumberFormatter(format);

	public String[] getListData() {
		return listData;
	}

	public void setListData(String[] listData) {
		this.listData = listData;
	}

	static Connection accessDataBase = null;
	static ResultSet rs = null;
	static PreparedStatement queryAdd = null;
	private JTextField textAddress;
	private JFormattedTextField txtPhone;
	private JTextField txtFName;
	private JTextField txtPosition;
	private JTextField txtPhoneCont;
	private JTextField txtCell;
	private JTextField txtMail;

	public AddSupplierView() {

		setTitle("Frame");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 462, 671);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//formatter.setValueClass(Integer.class);
		formatter.setMinimum(-1);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 426, 41);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ajouter un Fournisseur");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 31));
		lblNewLabel.setBounds(10, 0, 406, 52);
		panel.add(lblNewLabel);

		JLabel lblName = new JLabel("Nom :");
		lblName.setForeground(Color.GRAY);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(25, 146, 97, 28);
		contentPane.add(lblName);

		JLabel lblProv = new JLabel("Adresse :");
		lblProv.setForeground(Color.GRAY);
		lblProv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProv.setBounds(25, 184, 97, 28);
		contentPane.add(lblProv);

		JLabel lblType = new JLabel("Nom :");
		lblType.setForeground(Color.GRAY);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblType.setBounds(25, 338, 97, 28);
		contentPane.add(lblType);

		JLabel lblDate = new JLabel("T\u00E9l\u00E9phone :");
		lblDate.setForeground(Color.GRAY);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBounds(25, 222, 97, 28);
		contentPane.add(lblDate);

		txtName = new JTextField();
		txtName.setBounds(142, 153, 284, 19);
		txtName.setColumns(10);
		contentPane.add(txtName);

		ArrayList<Supplier> listSuppliers = supplierCrudController.listSuppliers();
		listData = new String[listSuppliers.size()];

		for (int i = 0; i < listSuppliers.size(); i++) {
			listData[i] = listSuppliers.get(i).getSupplierName();
		}

		txtNameCont = new JTextField();
		txtNameCont.setColumns(10);
		txtNameCont.setBounds(142, 345, 284, 19);
		contentPane.add(txtNameCont);

		JButton btnNewButton = new JButton("Ajouter");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				int id = 0;
				String name = txtName.getText();
				String type = txtNameCont.getText();

				String contComment = "commentaire test";
				int userContId = User.userId;
				boolean saveOk = false;

				SupplierCrudController addSup = new SupplierCrudController();
				ContactCrudController addContact = new ContactCrudController();
				try {
					Contact contact = new Contact(txtNameCont.getText(), txtFName.getText(), txtPhoneCont.getText(),
							txtCell.getText(), txtMail.getText(), txtPosition.getText(), contComment, userContId);

					int lastContactId = addContact.addNewContact(contact);
					Supplier supplier = new Supplier(textAddress.getText(), txtName.getText(), txtPhone.getText());

					JOptionPane.showConfirmDialog(null, "enregistré");

					saveOk = addSup.addNewSupplier(supplier, User.role, lastContactId);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (!saveOk) {
					contentPane.removeAll();
				} else {
					contentPane.setVisible(false);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(179, 595, 85, 21);
		contentPane.add(btnNewButton);

		textAddress = new JTextField();
		textAddress.setColumns(10);
		textAddress.setBounds(142, 191, 284, 19);
		contentPane.add(textAddress);

		
		
		
		txtPhone = new JFormattedTextField(formatter);
		txtPhone.setColumns(10);
		txtPhone.setBounds(142, 229, 284, 19);
		contentPane.add(txtPhone);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(22, 284, 409, 7);
		contentPane.add(separator);

		JLabel lblAjouterVotreFournisseur = new JLabel("Ajouter votre fournisseur");
		lblAjouterVotreFournisseur.setForeground(Color.GRAY);
		lblAjouterVotreFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAjouterVotreFournisseur.setBounds(25, 96, 406, 28);
		contentPane.add(lblAjouterVotreFournisseur);

		JLabel lblAjouterUnContact = new JLabel("Ajouter un contact");
		lblAjouterUnContact.setForeground(Color.GRAY);
		lblAjouterUnContact.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAjouterUnContact.setBounds(25, 301, 406, 28);
		contentPane.add(lblAjouterUnContact);

		JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setForeground(Color.GRAY);
		lblPrnom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrnom.setBounds(25, 376, 97, 28);
		contentPane.add(lblPrnom);

		txtFName = new JTextField();
		txtFName.setColumns(10);
		txtFName.setBounds(142, 383, 284, 19);
		contentPane.add(txtFName);

		JLabel lblPosition = new JLabel("Fonction :");
		lblPosition.setForeground(Color.GRAY);
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPosition.setBounds(25, 414, 97, 28);
		contentPane.add(lblPosition);

		txtPosition = new JTextField();
		txtPosition.setColumns(10);
		txtPosition.setBounds(142, 421, 284, 19);
		contentPane.add(txtPosition);

		JLabel lblPhoneCont = new JLabel("T\u00E9l\u00E9phone :");
		lblPhoneCont.setForeground(Color.GRAY);
		lblPhoneCont.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhoneCont.setBounds(25, 452, 97, 28);
		contentPane.add(lblPhoneCont);

		txtPhoneCont = new JTextField();
		txtPhoneCont.setColumns(10);
		txtPhoneCont.setBounds(142, 459, 284, 19);
		contentPane.add(txtPhoneCont);

		txtCell = new JTextField();
		txtCell.setColumns(10);
		txtCell.setBounds(142, 501, 284, 19);
		contentPane.add(txtCell);

		JLabel lblCellCont = new JLabel("Mobile :");
		lblCellCont.setForeground(Color.GRAY);
		lblCellCont.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCellCont.setBounds(25, 494, 97, 28);
		contentPane.add(lblCellCont);

		JLabel lblMail = new JLabel("Mail :");
		lblMail.setForeground(Color.GRAY);
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMail.setBounds(25, 530, 97, 28);
		contentPane.add(lblMail);

		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(142, 537, 284, 19);
		contentPane.add(txtMail);

		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(339, 598, 92, 19);
		contentPane.add(formattedTextField);

	}
}
