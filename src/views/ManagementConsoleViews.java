package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableModel;

import controllers.ArticleCrudController;
import controllers.SecurityController;
import controllers.UserCrudController;
import models.Article;
import models.RoleUser;
import models.Type;
import models.User;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

public class ManagementConsoleViews extends JInternalFrame {
	private ArrayList<User> users = null;
	private ArrayList<User> Rolelist = null;
	private ArrayList<RoleUser> RolelistView = null;
	private int index = 0;
	private int idSelected = 0;
	private UserCrudController userCrudController = new UserCrudController();
	private Object[] userData = null;
	private Object[] roleListSelected = null;
	private TableModel tabModel;
	private String selectedItemRole;
	private boolean flagShowPwd = false;
	private boolean flagSelected = false;
	private UserCrudController changedUser = new UserCrudController();
	private SecurityController sc = new SecurityController();
	boolean flag = false;

	public ManagementConsoleViews() throws SQLException, PropertyVetoException {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		getContentPane().setForeground(Color.GRAY);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().setBackground(UIManager.getColor("Button.background"));
		setClosable(true);
		setTitle("lists");
		setResizable(true);
		setMaximum(true);
		setBounds(10, 110, 1350, 486);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Gestion des Utilisateurs");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 31));

		getContentPane().add(lblNewLabel);
		lblNewLabel.setBounds(10, 10, 369, 42);

		users = userCrudController.showListUsers();

		final Object[][] data = new Object[users.size()][5];
		String[] header = { "Id", "Login", "Nom", "Prénom", "Rôle" };

		for (int i = 0; i < users.size(); i++) {
			userData = new Object[5];
			userData[0] = users.get(i).getIdUser();
			userData[1] = users.get(i).getLogin();
			userData[2] = users.get(i).getUserName();
			userData[3] = users.get(i).getUserFName();
			userData[4] = users.get(i).getUserRole();

			data[i] = userData;

			System.out.println(userData[0].toString());
			System.out.println(userData[2].toString());
			System.out.println(userData[4].toString());
		}

		JPanel panelUser = new JPanel();
		panelUser.setLayout(null);
		panelUser.setBorder(new TitledBorder(

				new TitledBorder(

						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),

								new Color(160, 160, 160)),

						"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)),

				"", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUser.setBounds(595, 95, 741, 280);
		getContentPane().add(panelUser);

		JLabel lblNouvelUtilisateur = new JLabel("Ajouter ou Modifier un Utilisateur");
		lblNouvelUtilisateur.setVerticalAlignment(SwingConstants.TOP);
		lblNouvelUtilisateur.setHorizontalAlignment(SwingConstants.LEFT);
		lblNouvelUtilisateur.setForeground(Color.GRAY);
		lblNouvelUtilisateur.setFont(new Font("Impact", Font.PLAIN, 17));
		lblNouvelUtilisateur.setBounds(10, 10, 570, 28);
		panelUser.add(lblNouvelUtilisateur);

		JLabel labelNameCont = new JLabel("Login :");
		labelNameCont.setForeground(Color.GRAY);
		labelNameCont.setBounds(10, 68, 92, 13);
		panelUser.add(labelNameCont);

		JLabel labelPhoneCont = new JLabel("Confirmer mot de passe :");
		labelPhoneCont.setForeground(Color.GRAY);
		labelPhoneCont.setBounds(347, 91, 178, 13);
		panelUser.add(labelPhoneCont);

		JLabel labelMailCont = new JLabel("Nom :");
		labelMailCont.setForeground(Color.GRAY);
		labelMailCont.setBounds(10, 115, 80, 13);
		panelUser.add(labelMailCont);

		JLabel labelFNameCont = new JLabel("Mot de Passe :");
		labelFNameCont.setForeground(Color.GRAY);
		labelFNameCont.setBounds(347, 68, 152, 13);
		panelUser.add(labelFNameCont);

		JLabel labelCellCont = new JLabel("Pr\u00E9nom :");
		labelCellCont.setForeground(Color.GRAY);
		labelCellCont.setBounds(10, 91, 92, 13);
		panelUser.add(labelCellCont);

		final JTextPane textPaneLogin = new JTextPane();
		textPaneLogin.setForeground(Color.BLACK);
		textPaneLogin.setBackground(Color.WHITE);
		textPaneLogin.setBounds(145, 65, 178, 19);
		panelUser.add(textPaneLogin);

		final JPasswordField pw1 = new JPasswordField(20);
		pw1.setHorizontalAlignment(SwingConstants.CENTER);
		pw1.setPreferredSize(new Dimension(19, 19));
		pw1.setBounds(535, 65, 139, 19);
		panelUser.add(pw1);

		final JPasswordField pw2 = new JPasswordField(20);
		pw2.setHorizontalAlignment(SwingConstants.CENTER);
		pw2.setPreferredSize(new Dimension(19, 19));
		pw2.setBounds(535, 88, 139, 19);
		panelUser.add(pw2);

		final JTextPane textPaneFName = new JTextPane();
		textPaneFName.setForeground(new Color(0, 0, 0));
		textPaneFName.setBackground(Color.WHITE);
		textPaneFName.setBounds(145, 88, 178, 19);
		panelUser.add(textPaneFName);

		final JTextPane textPaneName = new JTextPane();
		textPaneName.setForeground(new Color(0, 0, 0));
		textPaneName.setBackground(Color.WHITE);
		textPaneName.setBounds(145, 111, 178, 21);
		panelUser.add(textPaneName);

		JLabel labelRole = new JLabel("Role :");
		labelRole.setForeground(Color.GRAY);
		labelRole.setBounds(10, 157, 80, 13);
		panelUser.add(labelRole);

		RolelistView = userCrudController.selectRole();
		final String[] listDataRole = new String[RolelistView.size()];

		for (int i = 0; i < RolelistView.size(); i++) {
			listDataRole[i] = RolelistView.get(i).getUserRole();
		}

		final JComboBox<Object> listRole = new JComboBox(listDataRole);
		listRole.setSelectedIndex(-1);
		listRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				selectedItemRole = (String) listRole.getSelectedItem();
			}
		});
		listRole.setBackground(new Color(255, 255, 255));
		listRole.setBounds(145, 154, 178, 19);
		panelUser.add(listRole);

		ImageIcon imageIconShow = new ImageIcon(ManagementConsoleViews.class.getResource("/icons/showpwd.png"));
		Image image = imageIconShow.getImage();
		Image resizedImg = image.getScaledInstance(20, 10, java.awt.Image.SCALE_SMOOTH);

		ImageIcon imageIconHide = new ImageIcon(ManagementConsoleViews.class.getResource("/icons/hidepwd.png"));
		Image imagehide = imageIconHide.getImage();
		Image resizedImgHide = imagehide.getScaledInstance(20, 10, java.awt.Image.SCALE_SMOOTH);

		final JToggleButton tbtnpwd1 = new JToggleButton();
		tbtnpwd1.setSize(new Dimension(20, 10));
		tbtnpwd1.setSelectedIcon(new ImageIcon(ManagementConsoleViews.class.getResource("/icons/hidepwd.png")));
		tbtnpwd1.setBackground(SystemColor.control);
		tbtnpwd1.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(200, 200, 200)), null));
		tbtnpwd1.setIcon(new ImageIcon(resizedImg));
		tbtnpwd1.setBounds(681, 64, 35, 21);
		panelUser.add(tbtnpwd1);

		tbtnpwd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean selected = tbtnpwd1.getModel().isSelected();
				pw1.setEchoChar(selected ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
			}
		});

		final JToggleButton tbtnpwd2 = new JToggleButton();
		tbtnpwd2.setSize(new Dimension(20, 10));
		tbtnpwd2.setIcon(new ImageIcon(resizedImg));
		tbtnpwd2.setSelectedIcon(new ImageIcon(ManagementConsoleViews.class.getResource("/icons/hidepwd.png")));
		tbtnpwd2.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(200, 200, 200)), null));
		tbtnpwd2.setBounds(681, 87, 35, 21);
		panelUser.add(tbtnpwd2);

		tbtnpwd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean selected = tbtnpwd2.getModel().isSelected();
				pw2.setEchoChar(selected ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
			}
		});

		final JTable model = new JTable(data, header);
		model.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				flagSelected = true;
				index = model.getSelectedRow();
				tabModel = model.getModel();
				String userLogin = tabModel.getValueAt(index, 1).toString();
				String userName = tabModel.getValueAt(index, 2).toString();
				String userFName = tabModel.getValueAt(index, 3).toString();
				String nameRole = tabModel.getValueAt(index, 4).toString();

				try {
					RolelistView = userCrudController.listRole(nameRole);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				roleListSelected = new Object[RolelistView.size()];
				for (int i = 0; i < RolelistView.size(); i++) {
					roleListSelected = new Object[2];
					roleListSelected[0] = RolelistView.get(i).getRoleIdUser();
					roleListSelected[1] = RolelistView.get(i).getUserRole();
				}

				textPaneLogin.setText(userLogin);
				textPaneFName.setText(userFName);
				textPaneName.setText(userName);
				listRole.setSelectedItem((roleListSelected[1]).toString());
				pw1.setText(null);
				pw2.setText(null);

				flag = true;
			}
		});
		model.setFillsViewportHeight(true);
		model.setBorder(
				new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)));

		final JScrollPane scroll = new JScrollPane(model);
		scroll.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(102, 102, 102)));
		model.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scroll.setColumnHeaderView(model.getTableHeader());
		scroll.setBounds(10, 95, 575, 280);

		getContentPane().add(scroll);

		JButton btnModify = new JButton("Valider les changements");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idSelected = (Integer) tabModel.getValueAt(index, 0);
				String changedFName = textPaneFName.getText().toString();
				String changedName = textPaneName.getText().toString();
				String changedRole = selectedItemRole;
				String changedPwd = new String(pw1.getPassword());
				String changedPwdConf = new String(pw2.getPassword());

				if (changedFName.isEmpty()) {
					System.out.println("Prénom n'a pas était saisie");
				} else if (changedName.isEmpty()) {
					System.out.println("Nom n'a pas était saisie");
					/*
					 * } else if (changedPwd.isEmpty()) {
					 * System.out.println("Le mot de passe est vide "); } else if
					 * (!changedPwd.equals(changedPwdConf)) {
					 * System.out.println("Les mots de passes ne sont pas identique ");
					 */
				} else {

					String newPassWordString = sc.doHashing(pw1);

					User userSelected = new User(idSelected, changedName, changedFName, changedRole);
					changedUser.updateUser(userSelected);
					ManagementConsoleViews mgv;
					try {
						mgv = new ManagementConsoleViews();
						HomeView.desk.add(mgv);
						mgv.show();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnModify.setBounds(145, 230, 180, 21);
		panelUser.add(btnModify);

		JButton btnChangerMotDe = new JButton("Changer mot de passe");
		btnChangerMotDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idSelected = (Integer) tabModel.getValueAt(index, 0);
				String changedPwd = new String(pw1.getPassword());
				String changedPwdConf = new String(pw2.getPassword());

				if (changedPwd.isEmpty() || changedPwdConf.isEmpty()) {
					System.out.println("Le mot de passe est vide ");
				} else if (!changedPwd.equals(changedPwdConf)) {
					System.out.println("Les mots de passes ne sont pas identique ");
				} else {

					String newPassWordString = sc.doHashing(pw1);

					User userSelected = new User(idSelected, newPassWordString);
					changedUser.changedPassWord(userSelected);
				}
			}
		});
		btnChangerMotDe.setHorizontalTextPosition(SwingConstants.CENTER);
		btnChangerMotDe.setForeground(Color.WHITE);
		btnChangerMotDe.setBorderPainted(false);
		btnChangerMotDe.setBackground(new Color(0, 102, 0));
		btnChangerMotDe.setBounds(535, 132, 139, 21);
		panelUser.add(btnChangerMotDe);

		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setBounds(491, 64, 92, 21);
		getContentPane().add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AddUserView addUser = new AddUserView();
				addUser.setVisible(true);
			}
		});
		btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(0, 102, 0));

		JButton btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idSelected = (Integer) tabModel.getValueAt(index, 0);

				if (flag) {
					UserCrudController removeUser = new UserCrudController();
					try {
						int reply = JOptionPane.showConfirmDialog(null,
								"êtes-vous sur de vouloir supprimer cet utilisateur", TITLE_PROPERTY,
								JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							removeUser.deleteUser(idSelected, User.role);
							ManagementConsoleViews mgv;
							try {
								mgv = new ManagementConsoleViews();
								HomeView.desk.add(mgv);
								mgv.show();
							} catch (SQLException e1) {
								e1.printStackTrace();
							} catch (PropertyVetoException e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "ok");
							System.exit(0);
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "veuillez selectionner un article supprimer");
				}
			}
		});
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBounds(new Rectangle(50, 50, 50, 50));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(204, 0, 51));
		btnDelete.setBounds(368, 64, 113, 21);
		getContentPane().add(btnDelete);

		removeTitleBar();
	}

	public void removeTitleBar() {
		putClientProperty("ManagementConsoleViews.isPalette", Boolean.TRUE);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.scrollbar, SystemColor.scrollbar, null, null));

	}
}
