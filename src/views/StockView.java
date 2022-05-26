package views;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import controllers.ArticleCrudController;
import controllers.ContactCrudController;
import controllers.SupplierCrudController;
import models.Article;
import models.Contact;
import models.Supplier;

import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableModel;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;


public class StockView extends JInternalFrame {

	boolean flag = false;
	int index = 0;
	private String selectedItem;
	private ArrayList<Article> articles = null;
	private ArrayList<Supplier> listSuppliers = null;
	private ArrayList<Contact> allContacts= null;
	
	
	public StockView () throws SQLException, PropertyVetoException {
		getContentPane().setBackground(UIManager.getColor("Button.background"));
		setClosable(true);
		setTitle("lists");
		setResizable(true);
		setMaximum(true);
		setIconifiable(true);
		setBounds(10, 110, 1355, 486);
		getContentPane().setLayout(null);
		JLabel title = new JLabel("Gestion de stock");
		title.setForeground(Color.GRAY);
		title.setFont(new Font("Impact", Font.PLAIN, 31));
		
		getContentPane().add(title);
		title.setBounds(10, 10, 396, 39);
		
		articles = ArticleCrudController.showListArticle();
		Object[] columnData = null;
		Object[][] data = new Object[articles.size()][5];
		String[] header = {"Id", "Nom Produit", "Fournisseur", "Date d'ajout", "Type"};
	
		for (int i = 0; i < articles.size(); i++)
		{	
			columnData = new Object[5];
			columnData[0] = articles.get(i).getID();
			columnData[1] = articles.get(i).getName();
			columnData[2] = articles.get(i).getFournisseur();
			columnData[3] = articles.get(i).getDateCreation();
			columnData[4] = articles.get(i).getType();
			
			data[i] = columnData;
		}
		
		final JTable model = new JTable(data, header);
		model.setFillsViewportHeight(true);
		model.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)));
		
		final JScrollPane scroll = new JScrollPane(model);
		scroll.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(102, 102, 102)));
		model.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scroll.setColumnHeaderView(model.getTableHeader());
		scroll.setBounds(10, 95, 716, 356);
		
		getContentPane().add(scroll);
		
		JPanel buttonsArea = new JPanel();
		buttonsArea.setBounds(10, 49, 716, 40);
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setHorizontalAlignment(SwingConstants.TRAILING);
		btnAdd.setBounds(new Rectangle(50, 50, 50, 50));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(0, 102, 0));
		btnAdd.setBorderPainted(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddArticleView addArticle = new AddArticleView();
				addArticle.setVisible(true);
			}
		});
		btnAdd.setBounds(54, 148, 247, 142);
		buttonsArea.add(btnAdd);
		getContentPane().add(buttonsArea);
		
		JPanel panelInfoArt = new JPanel();
		panelInfoArt.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfoArt.setBounds(736, 49, 298, 197);
		getContentPane().add(panelInfoArt);
		panelInfoArt.setLayout(null);
		
		JLabel labelId = new JLabel("ID Article :");
		labelId.setForeground(Color.GRAY);
		labelId.setBounds(10, 70, 92, 13);
		panelInfoArt.add(labelId);
		
		JLabel labelInfoArt = new JLabel("Information Article");
		labelInfoArt.setForeground(Color.GRAY);
		labelInfoArt.setBounds(10, 10, 270, 28);
		labelInfoArt.setVerticalAlignment(SwingConstants.TOP);
		labelInfoArt.setHorizontalAlignment(SwingConstants.LEFT);
		labelInfoArt.setFont(new Font("Impact", Font.PLAIN, 17));
		panelInfoArt.add(labelInfoArt);
		
		JLabel labelName = new JLabel("D\u00E9nomination :");
		labelName.setForeground(Color.GRAY);
		labelName.setBounds(10, 93, 92, 13);
		panelInfoArt.add(labelName);
		
		JLabel labelType = new JLabel("Type :");
		labelType.setForeground(Color.GRAY);
		labelType.setBounds(10, 116, 92, 13);
		panelInfoArt.add(labelType);
		
		JLabel labelDate = new JLabel("Date d'ajout :");
		labelDate.setForeground(Color.GRAY);
		labelDate.setBounds(10, 139, 92, 13);
		panelInfoArt.add(labelDate);
		
		final JTextPane textPaneID = new JTextPane();
		textPaneID.setForeground(SystemColor.controlShadow);
		textPaneID.setBackground(new Color(245, 245, 245));
		textPaneID.setEditable(false);
		textPaneID.setBounds(102, 64, 178, 19);
		panelInfoArt.add(textPaneID);
		
		final JTextPane textPaneName = new JTextPane();
		textPaneName.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textPaneName.setBounds(102, 87, 178, 19);
		panelInfoArt.add(textPaneName);
		
		final JTextPane textPaneType = new JTextPane();
		textPaneType.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textPaneType.setBounds(102, 110, 178, 19);
		panelInfoArt.add(textPaneType);
		
		final JTextPane textPaneDate = new JTextPane();
		textPaneDate.setForeground(SystemColor.controlShadow);
		textPaneDate.setBackground(new Color(245, 245, 245));
		textPaneDate.setEditable(false);
		textPaneDate.setBounds(102, 133, 178, 19);
		panelInfoArt.add(textPaneDate);
		
		JPanel panelInfoProvider = new JPanel();
		panelInfoProvider.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfoProvider.setLayout(null);
		panelInfoProvider.setBounds(1035, 49, 298, 197);
		getContentPane().add(panelInfoProvider);
		
		JLabel labelIdProv = new JLabel("ID fournisseur :");
		labelIdProv.setForeground(Color.GRAY);
		labelIdProv.setBounds(10, 68, 92, 13);
		panelInfoProvider.add(labelIdProv);
		
		JLabel labelInfoProv = new JLabel("Informations Fournisseur");
		labelInfoProv.setForeground(Color.GRAY);
		labelInfoProv.setVerticalAlignment(SwingConstants.TOP);
		labelInfoProv.setHorizontalAlignment(SwingConstants.LEFT);
		labelInfoProv.setFont(new Font("Impact", Font.PLAIN, 17));
		labelInfoProv.setBounds(10, 10, 270, 28);
		panelInfoProvider.add(labelInfoProv);
		
		JLabel labelNameProv = new JLabel("Nom :");
		labelNameProv.setForeground(Color.GRAY);
		labelNameProv.setBounds(10, 91, 92, 13);
		panelInfoProvider.add(labelNameProv);
		
		JLabel labelAdressProv = new JLabel("Adresse :");
		labelAdressProv.setForeground(Color.GRAY);
		labelAdressProv.setBounds(10, 114, 80, 13);
		panelInfoProvider.add(labelAdressProv);
		
		JLabel labelPhoneProv = new JLabel("T\u00E9l\u00E9phone :");
		labelPhoneProv.setForeground(Color.GRAY);
		labelPhoneProv.setBounds(10, 137, 80, 13);
		panelInfoProvider.add(labelPhoneProv);
		
		final JTextPane textPaneIDProv = new JTextPane();
		textPaneIDProv.setForeground(SystemColor.controlShadow);
		textPaneIDProv.setBorder(new EmptyBorder(0, 0, 0, 0));
		textPaneIDProv.setBackground(new Color(245, 245, 245));
		textPaneIDProv.setEditable(false);
		textPaneIDProv.setBounds(100, 68, 180, 19);
		panelInfoProvider.add(textPaneIDProv);
	
		
		listSuppliers = SupplierCrudController.listSuppliers();
		String[] listData = new String [listSuppliers.size()];
		
		for (int i = 0; i < listSuppliers.size(); i++)
		{	
			listData[i] = listSuppliers.get(i).getSupplierName();
		}
		
		final JComboBox<Object> list = new JComboBox(listData);
	    list.setSelectedIndex(-1);
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			selectedItem = (String) list.getSelectedItem();
			}
		});
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(100, 91, 180, 19);
		panelInfoProvider.add(list);
		
		final JTextPane textPaneaddressProv = new JTextPane();
		textPaneaddressProv.setForeground(SystemColor.controlShadow);
		textPaneaddressProv.setBorder(new EmptyBorder(0, 0, 0, 0));
		textPaneaddressProv.setBackground(new Color(245, 245, 245));
		textPaneaddressProv.setEditable(false);
		textPaneaddressProv.setBounds(100, 114, 180, 19);
		panelInfoProvider.add(textPaneaddressProv);
		
		final JTextPane textPanePhoneProv = new JTextPane();
		textPanePhoneProv.setForeground(SystemColor.controlShadow);
		textPanePhoneProv.setBorder(new EmptyBorder(0, 0, 0, 0));
		textPanePhoneProv.setBackground(new Color(245, 245, 245));
		textPanePhoneProv.setEditable(false);
		textPanePhoneProv.setBounds(100, 137, 180, 19);
		panelInfoProvider.add(textPanePhoneProv);
		
		JButton btnSeeSup = new JButton("Voir la fiche");
		btnSeeSup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierView pl;
				try {
					pl = new SupplierView(selectedItem);
					setContentPane(pl);
					pl.show();
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSeeSup.setBounds(157, 166, 123, 21);
		panelInfoProvider.add(btnSeeSup);
		
		final JPanel panelContact = new JPanel();
		panelContact.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelContact.setLayout(null);
		panelContact.setBounds(736, 247, 597, 204);
		getContentPane().add(panelContact);
		
		JLabel labelInfoCont = new JLabel("Personne de contact");
		labelInfoCont.setForeground(Color.GRAY);
		labelInfoCont.setVerticalAlignment(SwingConstants.TOP);
		labelInfoCont.setHorizontalAlignment(SwingConstants.LEFT);
		labelInfoCont.setFont(new Font("Impact", Font.PLAIN, 17));
		labelInfoCont.setBounds(10, 10, 270, 28);
		panelContact.add(labelInfoCont);
		
		JLabel labelNameCont = new JLabel("Nom :");
		labelNameCont.setForeground(Color.GRAY);
		labelNameCont.setBounds(10, 35, 92, 13);
		panelContact.add(labelNameCont);
		
		JLabel labelPosittionCont = new JLabel("Fonction :");
		labelPosittionCont.setForeground(Color.GRAY);
		labelPosittionCont.setBounds(302, 35, 80, 13);
		panelContact.add(labelPosittionCont);
		
		JLabel labelPhoneCont = new JLabel("T\u00E9l. fixe :");
		labelPhoneCont.setForeground(Color.GRAY);
		labelPhoneCont.setBounds(10, 81, 92, 13);
		panelContact.add(labelPhoneCont);
		
		JLabel labelMailCont = new JLabel("Mail :");
		labelMailCont.setForeground(Color.GRAY);
		labelMailCont.setBounds(10, 123, 80, 13);
		panelContact.add(labelMailCont);
		
		JLabel labelFNameCont = new JLabel("Pr\u00E9nom :");
		labelFNameCont.setForeground(Color.GRAY);
		labelFNameCont.setBounds(10, 58, 92, 13);
		panelContact.add(labelFNameCont);
		
		JLabel labelCellCont = new JLabel("T\u00E9l. mobile :");
		labelCellCont.setForeground(Color.GRAY);
		labelCellCont.setBounds(10, 104, 92, 13);
		panelContact.add(labelCellCont);
		
		JLabel labelCommentCont = new JLabel("Commentaire :");
		labelCommentCont.setForeground(Color.GRAY);
		labelCommentCont.setBounds(302, 58, 109, 13);
		panelContact.add(labelCommentCont);
		
		final JTextArea textAreaCommentCont = new JTextArea();
		textAreaCommentCont.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textAreaCommentCont.setRows(10);
		textAreaCommentCont.setLineWrap(true);
		textAreaCommentCont.setDropMode(DropMode.INSERT);
		textAreaCommentCont.setBounds(400, 58, 180, 78);
		panelContact.add(textAreaCommentCont);
		
		final JTextPane textPaneNameCont = new JTextPane();
		textPaneNameCont.setEditable(false);
		textPaneNameCont.setForeground(SystemColor.controlShadow);
		textPaneNameCont.setBackground(new Color(245, 245, 245));
		textPaneNameCont.setBounds(102, 35, 178, 19);
		panelContact.add(textPaneNameCont);
		
		final JTextPane textPaneFNameCont = new JTextPane();
		textPaneFNameCont.setEditable(false);
		textPaneFNameCont.setForeground(SystemColor.controlShadow);
		textPaneFNameCont.setBackground(new Color(245, 245, 245));
		textPaneFNameCont.setBounds(102, 58, 178, 19);
		panelContact.add(textPaneFNameCont);
		
		final JTextPane textPanePhoneCont = new JTextPane();
		textPanePhoneCont.setEditable(false);
		textPanePhoneCont.setForeground(SystemColor.controlShadow);
		textPanePhoneCont.setBackground(new Color(245, 245, 245));
		textPanePhoneCont.setBounds(102, 81, 178, 19);
		panelContact.add(textPanePhoneCont);
		
		final JTextPane textPaneCellCont = new JTextPane();
		textPaneCellCont.setEditable(false);
		textPaneCellCont.setForeground(SystemColor.controlShadow);
		textPaneCellCont.setBackground(new Color(245, 245, 245));
		textPaneCellCont.setBounds(102, 104, 178, 19);
		panelContact.add(textPaneCellCont);
		
		final JTextPane textPaneMailCont = new JTextPane();
		textPaneMailCont.setEditable(false);
		textPaneMailCont.setForeground(SystemColor.controlShadow);
		textPaneMailCont.setBackground(new Color(245, 245, 245));
		textPaneMailCont.setBounds(102, 127, 178, 21);
		panelContact.add(textPaneMailCont);
		
		final JTextPane textPanePositionCont = new JTextPane();
		textPanePositionCont.setEditable(false);
		textPanePositionCont.setForeground(SystemColor.controlShadow);
		textPanePositionCont.setBackground(new Color(245, 245, 245));
		textPanePositionCont.setBounds(400, 29, 180, 19);
		panelContact.add(textPanePositionCont);
		
		
		
		model.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = model.getSelectedRow();
				TableModel tabModel = model.getModel();
				String nameSupplier = tabModel.getValueAt(index, 2).toString();

				//ArrayList<Supplier> allSuppliers=new ArrayList<Supplier>();
				allContacts=new ArrayList<Contact>();
				
				try {
					listSuppliers = SupplierCrudController.selectedProvider(nameSupplier);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Object[] supData = new Object [listSuppliers.size()];
				
				for (int i = 0; i < listSuppliers.size(); i++)
				{	supData = new Object[5];
					supData[0] = listSuppliers.get(i).getSupId();
					supData[1] = listSuppliers.get(i).getSupplierAddress();
					supData[2] = listSuppliers.get(i).getSupplierName();
					supData[3] = listSuppliers.get(i).getSupplierPhone();
					supData[4] = listSuppliers.get(i).getSupplierContactId();
				}
				String selectContact =  supData[4].toString();
				
				allContacts = ContactCrudController.seletedContact(selectContact);
				Object[] contData = new Object [allContacts.size()];
				for (int i = 0; i< allContacts.size();i++)
				{
					contData = new Object[8];
					contData[0] = allContacts.get(i).getContId();
					contData[1] = allContacts.get(i).getContName();
					contData[2] = allContacts.get(i).getContFName();
					contData[3] = allContacts.get(i).getContPhone();
					contData[4] = allContacts.get(i).getContCell();
					contData[5] = allContacts.get(i).getContMail();
					contData[6] = allContacts.get(i).getContPosition();
					contData[7] = allContacts.get(i).getContComment();
					
				}
				
				textPaneID.setText(tabModel.getValueAt(index, 0).toString());
				textPaneName.setText(tabModel.getValueAt(index, 1).toString());
				textPaneDate.setText(tabModel.getValueAt(index, 3).toString());
				textPaneType.setText(tabModel.getValueAt(index, 4).toString());

				textPaneIDProv.setText((supData[0]).toString());
				textPaneaddressProv.setText((supData[1]).toString());
				//textPaneNameProv.setText((supData[2]).toString());
				list.setSelectedItem((supData[2]).toString());
				textPanePhoneProv.setText((supData[3]).toString());
				
				textPaneNameCont.setText((contData[1]).toString());
				textPaneFNameCont.setText((contData[2]).toString());
				textPanePhoneCont.setText((contData[3]).toString());
				textPaneCellCont.setText((contData[4]).toString());
				textPaneMailCont.setText((contData[5]).toString());
				textPanePositionCont.setText((contData[6]).toString());
				textAreaCommentCont.setText((contData[7]).toString());
				
				flag=true;
			}
		});

		JButton btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(flag) {
				ArticleCrudController deleteArticle = new ArticleCrudController();
					try {
						int reply = JOptionPane.showConfirmDialog(null, "êtes-vous sur de vouloir supprimer cet article", TITLE_PROPERTY, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							deleteArticle.deleteArt(textPaneID);
							
							}else {
						    JOptionPane.showMessageDialog(null, "ok");
						    System.exit(0);
							}
				
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "veuillez selectionner un article supprimer");
				}
			}
		});
		btnDelete.setHorizontalAlignment(SwingConstants.TRAILING);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBounds(new Rectangle(50, 50, 50, 50));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(204, 0, 51));
		buttonsArea.add(btnDelete);
		
		JButton btnModify = new JButton("Valider les changements");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textPaneID.getText());
				Article article = new Article(id,textPaneName.getText(), textPaneDate.getText(), textPaneType.getText(), 
						(String) list.getSelectedItem() ,1,1);
				ArticleCrudController upDateArticle = new ArticleCrudController();
				upDateArticle.upDateArt(article);
			}
		});
		btnModify.setBounds(400, 173, 180, 21);
		panelContact.add(btnModify);
		
		removeTitleBar();
	}
	
	public void removeTitleBar() {
		putClientProperty("StockView.isPalette", Boolean.TRUE);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.scrollbar, SystemColor.scrollbar, null, null));
		
	}
}

