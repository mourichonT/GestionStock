package views;

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
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class SupplierView  extends JInternalFrame  {
	
	boolean flagContact = false;
	boolean flagSupplier = false;
	int index = 0;
	private String selectedItem;
	private ArrayList<Supplier> suppliers = null;
	private ArrayList<Contact> contacts= null;
	private static final long serialVersionUID = 1L;
	private JTextField searchContact;
	private JTextField searchSupplier;
	private JTable modelSup;
	private JTable modelContact;
	//private JFrame frame;

	/**
	 * Create the application.
	 */
	public SupplierView(String idSupplier) throws SQLException, PropertyVetoException {
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
		
		suppliers = SupplierCrudController.showAllProvider();
		Object[] supplierData = null;
		final Object[][] dataSuppliers = new Object[suppliers.size()][5];
		String[] header = {"Id", "Adresse", "Fournisseur", "Téléphone"};
	
		for (int i = 0; i < suppliers.size(); i++)
		{	
			supplierData = new Object[5];
			supplierData[0] = suppliers.get(i).getSupId();
			supplierData[1] = suppliers.get(i).getSupplierAddress();
			supplierData[2] = suppliers.get(i).getSupplierName();
			supplierData[3] = suppliers.get(i).getSupplierPhone();
			supplierData[4] = suppliers.get(i).getContactId();
			
			dataSuppliers[i] = supplierData;
		}
		
		modelSup = new JTable(dataSuppliers, header);
		
		modelSup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = modelSup.getSelectedRow();
				flagSupplier=true;
				modelSup.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						int id = (Integer)dataSuppliers[index][0];
						
						SupplierCrudController modify = new SupplierCrudController();

						String address=(String) modelSup.getValueAt(index, 1).toString();
						String supplierName=(String) modelSup.getValueAt(index, 2).toString();
						String phone=(String) modelSup.getValueAt(index, 3).toString();
						int contId = (Integer)dataSuppliers[index][4];
						
						System.out.println("je test id :"+id);
						System.out.println("je test adress :"+address);
						System.out.println("je test nom:"+supplierName);
						System.out.println("je test tel :"+phone);
						System.out.println("je test cont :"+contId);
						
						Supplier supplier = new Supplier (id, address, supplierName, phone, contId);
						modify.upDateSup(supplier);
					}
				});
				
				if (flagSupplier) {
					//int selection =(Integer) dataSuppliers[index][4];
					String selection = dataSuppliers[index][2].toString();
					System.out.println(selection);
					filter(selection, modelContact);
				}
			}
		});
		modelSup.setFillsViewportHeight(true);
		modelSup.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)));
		
		final JScrollPane scrollSup = new JScrollPane(modelSup);
		scrollSup.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(102, 102, 102)));
		modelSup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollSup.setColumnHeaderView(modelSup.getTableHeader());
		scrollSup.setBounds(203, 92, 1133, 151);
		
		getContentPane().add(scrollSup);
		
		contacts = ContactCrudController.showListContact();
		Object[] contactsData = null;
		final Object[][] dataContacts = new Object[contacts.size()][7];
		String[] headerContacts = {"Id", "Nom", "Prénom", "Téléphone", "Portable", "Mail", "Fonction"};
	
		for (int i = 0; i < contacts.size(); i++)
		{	
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
		
		final JTextArea textAreaCommentCont = new JTextArea();
		textAreaCommentCont.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.controlDkShadow));
		textAreaCommentCont.setRows(10);
		textAreaCommentCont.setLineWrap(true);
		textAreaCommentCont.setDropMode(DropMode.INSERT);
		textAreaCommentCont.setBounds(1034, 345, 302, 106);
		getContentPane().add(textAreaCommentCont);
		
		modelContact = new JTable(dataContacts, headerContacts);
		modelContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = modelContact.getSelectedRow();
				TableModel tabModel = modelContact.getModel();		
				textAreaCommentCont.setText((dataContacts[index][7]).toString());
				flagContact=true;
				
				modelSup.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						int id = (Integer)dataSuppliers[index][0];
						
						ContactCrudController modifyCont = new ContactCrudController();

						int contId = (Integer)dataSuppliers[index][4];
						String name=(String) modelSup.getValueAt(index, 1).toString();
						String FName=(String) modelSup.getValueAt(index, 2).toString();
						String CPhone=(String) modelSup.getValueAt(index, 3).toString();
						String CCell=(String) modelSup.getValueAt(index, 4).toString();
						String CMail=(String) modelSup.getValueAt(index, 5).toString();
						String CPosition=(String) modelSup.getValueAt(index, 6).toString();
						String CComment=(String) modelSup.getValueAt(index, 7).toString();
						
						System.out.println("je test id :"+contId);
						System.out.println("je test nom :"+name);
						System.out.println("je test prenom:"+FName);
						System.out.println("je test tel :"+CPhone);
						System.out.println("je test mobile :"+CCell);
						System.out.println("je test mail :"+CMail);
						System.out.println("je test fonction :"+CPosition);
						System.out.println("je test comment :"+CComment);
						
						Contact contact = new Contact (contId, name, FName, CPhone, CCell, CMail, CPosition, CComment);
						modifyCont.upDateContact(contact);
					}
				});
				
				if(flagContact) {
					
				}
			}
		});
		modelContact.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		modelContact.setFillsViewportHeight(true);
		modelContact.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)));
		
		final JScrollPane scrollContact = new JScrollPane(modelContact);
		scrollContact.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(102, 102, 102)));
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
		btnAdd.setBounds(56, 142, 104, 21);
		getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flagContact || flagSupplier) {
					if (flagSupplier) {
						SupplierCrudController deleteSupplier = new SupplierCrudController();
						int reply = JOptionPane.showConfirmDialog(null, "Attention, supprimer un fournisseur entrainerai une perte des liaisons avec les articles associés, êtes-vous sur de vouloir supprimer celui-ci", TITLE_PROPERTY, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							System.out.println("deteled");
							String id = dataSuppliers[index][0].toString();
							deleteSupplier.deleteSup(id);
							} else {
						    JOptionPane.showMessageDialog(null, "ok");
						    System.exit(0);
							}
					} else if(flagContact) {
						JOptionPane.showMessageDialog(null, "Vous ne pouvez pas supprimer un contact lié a un fournisseur, veuillez s'il vous plait mettre a jour le contact ");
						//trouver comment deselectionner la row
						}
				}else {
					JOptionPane.showMessageDialog(null, " veuillez s'il vous plait selectionner un fournisseur a supprimer ");
				}
			}
		});
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBounds(new Rectangle(50, 50, 50, 50));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(204, 0, 51));
		btnDelete.setBounds(56, 173, 104, 21);
		getContentPane().add(btnDelete);
		
//		JButton btnModifier = new JButton("Modifier");
//		btnModifier.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				SupplierCrudController modify = new SupplierCrudController();
//				
//				int id = (Integer)dataSuppliers[index][0];
//				String address=(String) modelSup.getValueAt(index, 1).toString();
//				String supplierName=(String) modelSup.getValueAt(index, 2).toString();
//				String phone=(String) modelSup.getValueAt(index, 3).toString();
//				int contId = (Integer)dataSuppliers[index][4];
//				
//				
//				System.out.println("je test id :"+id);
//				System.out.println("je test adress :"+address);
//				System.out.println("je test nom:"+supplierName);
//				System.out.println("je test tel :"+phone);
//				System.out.println("je test cont :"+contId);
//				
//				Supplier supplier = new Supplier (id, address, supplierName, phone, contId);
//				modify.upDateSup(supplier);
//			}
//		});
//		btnModifier.setHorizontalTextPosition(SwingConstants.CENTER);
//		btnModifier.setForeground(Color.WHITE);
//		btnModifier.setBounds(new Rectangle(50, 50, 50, 50));
//		btnModifier.setBorderPainted(false);
//		btnModifier.setBackground(SystemColor.controlShadow);
//		btnModifier.setBounds(55, 178, 104, 21);
//		getContentPane().add(btnModifier);
		
		searchContact = new JTextField();
		searchContact.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = searchContact.getText().toLowerCase();
				filter(search, modelContact);				
			}
		});
		searchContact.setBounds(253, 280, 453, 26);
		getContentPane().add(searchContact);
		searchContact.setColumns(10);
		
		searchSupplier = new JTextField(idSupplier);
		searchSupplier.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String search = searchSupplier.getText().toLowerCase();
				filter(search, modelSup);
				filter(search, modelContact);
			}
		});
		searchSupplier.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = searchSupplier.getText().toLowerCase();
				filter(search, modelSup);
				filter(search, modelContact);
				
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
		
	
		
		
		removeTitleBar();
	}
	

	public void removeTitleBar() {
		putClientProperty("providerList.isPalette", Boolean.TRUE);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.scrollbar, SystemColor.scrollbar, null, null));
		
	}
	
	public void filter (String search, JTable model) {
		//TableModel dm = modelSup.getModel();
		TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(model.getModel());
		model.setRowSorter(trs);
		
		trs.setRowFilter(RowFilter.regexFilter("(?i)"+search));
	}
}
