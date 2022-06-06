package views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;

import models.User;

import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class HomeView {
	
	public JFrame frame;
	static JDesktopPane desk;
	
	public HomeView() {
		super();
		initialize();
	}
	
	public JDesktopPane getDesk() {
		return desk;
	}

	public void setDesk(JDesktopPane desk) {
		this.desk = desk;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1389, 686);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				

		desk = new JDesktopPane();
		desk.setBounds(10, 110, 1355, 486);
		frame.getContentPane().add(desk);
		
		JButton providers = new JButton("Fournisseurs");
		providers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				desk.removeAll();
				desk.repaint();
				try {
					SupplierView pl = new SupplierView(null);
					desk.add(pl);
					pl.show();
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		
		frame.getContentPane().add(desk);
		providers.setFont(new Font("Tahoma", Font.PLAIN, 20));						
		providers.setBounds(585, 31, 205, 58);
		frame.getContentPane().add(providers);

		JButton stock = new JButton("Stock");
		stock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desk.removeAll();
				desk.repaint();				
				
				try {
					StockView stockView = new StockView();
					desk.add(stockView);
					stockView.show();
				}catch(Exception e3) {
					System.err.println(e3 +"une erreur a ete trouvé dans la partie trycatch2 ");
				}
			}
		});

		stock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		stock.setBounds(980, 31, 205, 58);
		frame.getContentPane().add(stock);
		
		JButton user = new JButton("Utilisateur");
		if (User.role.equals("admin")) {
			user.setEnabled(true);
		} else {
			user.setEnabled(false);
		}
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				desk.removeAll();
				desk.repaint();
				try {
					ManagementConsoleViews mgv = new ManagementConsoleViews();
					desk.add(mgv);
					mgv.show();
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}				
			}
		});
		frame.getContentPane().add(user);
		
		user.setFont(new Font("Tahoma", Font.PLAIN, 20));
		user.setBounds(190, 31, 205, 58);
		frame.getContentPane().add(user);
		
		

		}
}
