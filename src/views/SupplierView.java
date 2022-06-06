package views;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controllers.ArticleCrudController;
import controllers.ContactCrudController;
import controllers.SupplierCrudController;
import models.Article;
import models.Contact;
import models.Supplier;
import models.User;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.KeyAdapter;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class SupplierView extends JInternalFrame {

	static SupplierView pl;
	boolean flagContact = false;
	boolean flagSupplier = false;
	static boolean plClosing = false;
	int index = 0;
	private String selectedItem;
	private ArrayList<Supplier> suppliers = null;
	private ArrayList<Contact> contacts = null;
	private static final long serialVersionUID = 1L;
	private JTextField searchContact;
	private JTextField searchSupplier;
	private JTable modelSup;
	private JTable modelContact;
	private SupplierCrudController supplierCrudController = new SupplierCrudController();

	public SupplierView(final String idSupplier) throws SQLException, PropertyVetoException {

		getContentPane().setForeground(Color.GRAY);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().setBackground(UIManager.getColor("Button.background"));
		setClosable(true);
		setTitle("lists");
		setResizable(true);
		setMaximum(true);
		setIconifiable(true);
		setBounds(10, 110, 1350, 486);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Fournisseurs & Contacts");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 31));

		getContentPane().add(lblNewLabel);
		lblNewLabel.setBounds(10, 10, 369, 42);

		final JTextArea textAreaCommentCont = new JTextArea();
		textAreaCommentCont.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.controlDkShadow));
		textAreaCommentCont.setRows(10);
		textAreaCommentCont.setLineWrap(true);
		textAreaCommentCont.setDropMode(DropMode.INSERT);
		textAreaCommentCont.setBounds(1034, 345, 302, 106);
		getContentPane().add(textAreaCommentCont);

		suppliers = supplierCrudController.showAllProvider();
		Object[] supplierData = null;
		final Object[][] dataSuppliers = new Object[suppliers.size()][4];
		String[] header = { "Id", "Adresse", "Fournisseur", "Téléphone" };

		for (int i = 0; i < suppliers.size(); i++) {
			supplierData = new Object[6];
			supplierData[0] = suppliers.get(i).getSupId();
			supplierData[1] = suppliers.get(i).getSupplierAddress();
			supplierData[2] = suppliers.get(i).getSupplierName();
			supplierData[3] = suppliers.get(i).getSupplierPhone();
			supplierData[4] = suppliers.get(i).getContactId();

			dataSuppliers[i] = supplierData;
		}
		contacts = ContactCrudController.showListContact();
		Object[] contactsData = null;
		final Object[][] dataContacts = new Object[contacts.size()][7];
		String[] headerContacts = { "Id", "Nom", "Prénom", "Téléphone", "Portable", "Mail", "Fonction" };

		for (int i = 0; i < contacts.size(); i++) {
			contactsData = new Object[8];
			contactsData[0] = contacts.get(i).getContId();
			contactsData[1] = contacts.get(i).getContName();
			contactsData[2] = contacts.get(i).getContFName();
			contactsData[3] = contacts.get(i).getContPhone();
			contactsData[4] = contacts.get(i).getContCell();
			contactsData[5] = contacts.get(i).getContMail();
			contactsData[6] = contacts.get(i).getContPosition();
			contactsData[7] = contacts.get(i).getContComment();

			dataContacts[i] = contactsData;
		}

		modelSup = new JTable(dataSuppliers, header);

		modelSup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = modelSup.getSelectedRow();
				flagSupplier = true;
				modelSup.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						int id = (Integer) dataSuppliers[index][0];

						SupplierCrudController modify = new SupplierCrudController();
						String address = (String) modelSup.getValueAt(index, 1).toString();
						String supplierName = (String) modelSup.getValueAt(index, 2).toString();
						String phone = (String) modelSup.getValueAt(index, 3).toString();
						int contId = (Integer) dataSuppliers[index][4];

						Supplier supplier = new Supplier(id, address, supplierName, phone, contId);
						modify.upDateSup(supplier);
					}
				});

				if (flagSupplier) {
					String selection = dataSuppliers[index][2].toString();
					System.out.println(selection);
					filter(selection, modelContact);
					textAreaCommentCont.setText((dataContacts[index][7]).toString());
				}
			}
		});
		modelSup.setFillsViewportHeight(true);
		modelSup.setBorder(
				new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)));

		final JScrollPane scrollSup = new JScrollPane(modelSup);
		scrollSup.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(102, 102, 102)));
		modelSup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollSup.setColumnHeaderView(modelSup.getTableHeader());
		scrollSup.setBounds(203, 92, 1133, 151);

		getContentPane().add(scrollSup);

		modelContact = new JTable(dataContacts, headerContacts);
		modelContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = modelContact.getSelectedRow();
				TableModel tabModel = modelContact.getModel();
				textAreaCommentCont.setText((dataContacts[index][7]).toString());
				flagContact = true;

				modelContact.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						int id = (Integer) dataSuppliers[index][0];

						ContactCrudController modifyCont = new ContactCrudController();

						int contId = (Integer) dataSuppliers[index][4];
						String name = (String) modelContact.getValueAt(index, 1).toString();
						String FName = (String) modelContact.getValueAt(index, 2).toString();
						String CPhone = (String) modelContact.getValueAt(index, 3).toString();
						String CCell = (String) modelContact.getValueAt(index, 4).toString();
						String CMail = (String) modelContact.getValueAt(index, 5).toString();
						String CPosition = (String) modelContact.getValueAt(index, 6).toString();
						String CComment = (String) modelContact.getValueAt(index, 7).toString();

						Contact contact = new Contact(contId, name, FName, CPhone, CCell, CMail, CPosition, CComment);
						modifyCont.upDateContact(contact);
					}
				});
			}
		});
		modelContact.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		modelContact.setFillsViewportHeight(true);
		modelContact.setBorder(
				new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)));

		final JScrollPane scrollContact = new JScrollPane(modelContact);
		scrollContact.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(102, 102, 102)));
		modelContact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollContact.setColumnHeaderView(modelContact.getTableHeader());
		scrollContact.setBounds(10, 319, 996, 132);

		getContentPane().add(scrollContact);

		JButton btnAdd = new JButton("Ajouter");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSupplierView newContact = new AddSupplierView();
				newContact.setVisible(true);
			}
		});
		btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBounds(new Rectangle(50, 50, 50, 50));
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(0, 102, 0));
		if (User.role.equals("admin")) {
			btnAdd.setEnabled(true);
		} else {
			btnAdd.setEnabled(false);
		}
		btnAdd.setBounds(56, 142, 104, 21);
		getContentPane().add(btnAdd);

		JButton btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flagContact || flagSupplier) {
					if (flagSupplier) {
						SupplierCrudController deleteSupplier = new SupplierCrudController();
						int reply = JOptionPane.showConfirmDialog(null,
								"Attention, supprimer un fournisseur entrainerai une perte des liaisons avec les articles associés, êtes-vous sur de vouloir supprimer celui-ci",
								TITLE_PROPERTY, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							System.out.println("deteled");
							String id = dataSuppliers[index][0].toString();
							deleteSupplier.deleteSup(id, User.role);
						} else {
							JOptionPane.showMessageDialog(null, "ok");
							System.exit(0);
						}
					} else if (flagContact) {
						JOptionPane.showMessageDialog(null,
								"Vous ne pouvez pas supprimer un contact lié a un fournisseur, veuillez s'il vous plait mettre a jour le contact ");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							" veuillez s'il vous plait selectionner un fournisseur a supprimer ");
				}
			}
		});
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBounds(new Rectangle(50, 50, 50, 50));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(204, 0, 51));
		btnDelete.setBounds(56, 173, 104, 21);
		if (User.role.equals("admin")) {
			btnDelete.setEnabled(true);
		} else {
			btnDelete.setEnabled(false);
		}
		getContentPane().add(btnDelete);

		JButton btnModifier = new JButton("Effacer la recherche");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					SupplierView.pl = new SupplierView(null);
					HomeView.desk.add(pl);
					textAreaCommentCont.setText(" ");
					pl.show();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModifier.setForeground(Color.WHITE);
		btnModifier.setBounds(new Rectangle(50, 50, 50, 50));
		btnModifier.setBorderPainted(false);
		btnModifier.setBackground(SystemColor.controlShadow);
		btnModifier.setBounds(894, 58, 159, 21);
		getContentPane().add(btnModifier);

		searchContact = new JTextField(idSupplier);
		searchContact.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String searchCont = searchContact.getText().toLowerCase();
				filter(searchCont, modelContact);
			}
		});
		searchContact.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchCont = searchContact.getText().toLowerCase();
				filter(searchCont, modelContact);
			}
		});
		searchContact.setColumns(10);
		searchContact.setBounds(253, 280, 453, 26);
		getContentPane().add(searchContact);

		searchSupplier = new JTextField(idSupplier);
		searchSupplier.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String search = searchSupplier.getText().toLowerCase();
				filter(search, modelSup);
			}
		});
		searchSupplier.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = searchSupplier.getText().toLowerCase();
				filter(search, modelSup);
			}
		});
		searchSupplier.setColumns(10);
		searchSupplier.setBounds(431, 56, 453, 26);
		getContentPane().add(searchSupplier);

		JLabel lblSearchSup = new JLabel("Recherchez un Fournisseur :");
		lblSearchSup.setForeground(Color.GRAY);
		lblSearchSup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSearchSup.setBounds(203, 62, 230, 13);
		getContentPane().add(lblSearchSup);

		JLabel lblSearchCont = new JLabel("Recherchez un Contact :");
		lblSearchCont.setForeground(Color.GRAY);
		lblSearchCont.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSearchCont.setBounds(10, 286, 423, 13);
		getContentPane().add(lblSearchCont);

		JLabel labelCommentCont = new JLabel("Commentaire :");
		labelCommentCont.setBounds(1034, 322, 400, 13);
		getContentPane().add(labelCommentCont);

		if (StockView.flagReturn == true) {

			JButton btnreturn = new JButton("Retour");
			btnreturn.setHorizontalTextPosition(SwingConstants.CENTER);
			btnreturn.setForeground(Color.WHITE);
			btnreturn.setBounds(new Rectangle(50, 50, 50, 50));
			btnreturn.setBorderPainted(false);
			btnreturn.setBackground(Color.GRAY);
			btnreturn.setBounds(1232, 61, 104, 21);
			getContentPane().add(btnreturn);

			btnreturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("ID ENREGISTRE " + StockView.index);
					StockView.flagReturn = false;

					SupplierView.pl.setVisible(false);
					try {
						StockView.sv = new StockView();
						System.out.println();
						HomeView.desk.add(StockView.sv);
						StockView.sv.show();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
		}

		removeTitleBar();
	}

	public void removeTitleBar() {
		putClientProperty("providerList.isPalette", Boolean.TRUE);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.scrollbar, SystemColor.scrollbar, null, null));

	}

	public void filter(String search, JTable model) {
		TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(model.getModel());
		model.setRowSorter(trs);

		trs.setRowFilter(RowFilter.regexFilter("(?i)" + search));
	}
}
