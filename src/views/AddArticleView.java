package views;


import java.awt.*;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import controllers.ArticleCrudController;
import controllers.SupplierCrudController;
import models.Article;
import models.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
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
		private String[] listData =null;
		
		public String[] getListData() {
			return listData;
		}

		public void setListData(String[] listData) {
			this.listData = listData;
		}

		static Connection accessDataBase = null;
		static ResultSet rs =null;
		static PreparedStatement queryAdd = null;
		//protected static Object add;
		
		public AddArticleView() {
			setTitle("Frame");
			
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 462, 309);
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
			
			JLabel lblName = new JLabel("Nom");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblName.setBounds(25, 90, 97, 28);
			contentPane.add(lblName);
			
			JLabel lblProv = new JLabel("Fournisseur");
			lblProv.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblProv.setBounds(25, 118, 97, 28);
			contentPane.add(lblProv);
			
			JLabel lblType = new JLabel("Type");
			lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblType.setBounds(25, 175, 97, 28);
			contentPane.add(lblType);
			
			JLabel lblDate = new JLabel("Date de creation");
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDate.setBounds(25, 147, 97, 28);
			contentPane.add(lblDate);
			
			txtName = new JTextField();
			txtName.setBounds(142, 97, 284, 19);
			txtName.setColumns(10);
			contentPane.add(txtName);
			
			ArrayList<Supplier> listSuppliers = SupplierCrudController.listSuppliers();
			listData = new String [listSuppliers.size()];
			
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
			list.setBounds(142, 125, 284, 19);
			contentPane.add(list);
			
			txtType = new JTextField();
			txtType.setColumns(10);
			txtType.setBounds(142, 182, 284, 19);
			contentPane.add(txtType);
			
			dateChooser = new JDateChooser();
	 		dateChooser.setBounds(142, 156, 284, 19);
			contentPane.add(dateChooser);

			JButton btnNewButton = new JButton("Ajouter");
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
					int id = 0;
					String name = txtName.getText();
					String date = sdf.format(dateChooser.getDate()); 
					String type = txtType.getText(); 
					
					Article article = new Article(0,txtName.getText(), sdf.format(dateChooser.getDate()), txtType.getText(), (String) list.getSelectedItem() ,1,1);
					boolean saveOk = false;	
					
					if(date == null) {
						JOptionPane.showMessageDialog(null, "la date n'est pas définis");
						System.out.println("la date n'est pas définis");

					}else if(list.getSelectedItem()==null){
						System.out.println("le fournisseur article n'est pas définis");
						JOptionPane.showMessageDialog(null, "le fournisseur article n'est pas définis");

					}else if(name ==null){
						System.out.println(" le nom n'est pas définis");
						JOptionPane.showMessageDialog(null, "le nom n'est pas définis");

					}else if (type ==null) {
						JOptionPane.showMessageDialog(null, "le Type article n'est pas définis");
						System.out.println("le Type article n'est pas définis");
					}else {
						
						ArticleCrudController addArt = new ArticleCrudController();
						try {
							JOptionPane.showMessageDialog(null, "enregistré");
							saveOk = addArt.addNewArticle (article);
							contentPane.removeAll();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if (!saveOk) {
							contentPane.removeAll();
							}	
						
					}
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnNewButton.setBounds(183, 242, 85, 21);
			contentPane.add(btnNewButton);
			
		}
	}
