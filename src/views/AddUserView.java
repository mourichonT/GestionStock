package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableModel;

import controllers.SecurityController;
import controllers.UserCrudController;
import models.RoleUser;
import models.User;

public class AddUserView extends JFrame {

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
	private JPanel contentPane;
	private int rolId = 0;
	private Object[] IdRoleSelect = null;

	public AddUserView() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(595, 95, 741, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelUser = new JPanel();
		panelUser.setLayout(null);
		panelUser.setBorder(new TitledBorder(

				new TitledBorder(

						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),

								new Color(160, 160, 160)),

						"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)),

				"", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUser.setBounds(10, 10, 707, 216);
		contentPane.add(panelUser);

		JLabel lblNouvelUtilisateur = new JLabel("Ajouter ou Modifier un Utilisateur");
		lblNouvelUtilisateur.setVerticalAlignment(SwingConstants.TOP);
		lblNouvelUtilisateur.setHorizontalAlignment(SwingConstants.LEFT);
		lblNouvelUtilisateur.setForeground(Color.GRAY);
		lblNouvelUtilisateur.setFont(new Font("Impact", Font.PLAIN, 17));
		lblNouvelUtilisateur.setBounds(10, 10, 570, 28);
		panelUser.add(lblNouvelUtilisateur);

		JLabel labelNameCont = new JLabel("Login :");
		labelNameCont.setForeground(Color.GRAY);
		labelNameCont.setBounds(10, 67, 92, 13);
		panelUser.add(labelNameCont);

		JLabel labelPhoneCont = new JLabel("Confirmer mot de passe :");
		labelPhoneCont.setForeground(Color.GRAY);
		labelPhoneCont.setBounds(347, 90, 178, 13);
		panelUser.add(labelPhoneCont);

		JLabel labelMailCont = new JLabel("Nom :");
		labelMailCont.setForeground(Color.GRAY);
		labelMailCont.setBounds(10, 115, 80, 13);
		panelUser.add(labelMailCont);

		JLabel labelFNameCont = new JLabel("Mot de Passe :");
		labelFNameCont.setForeground(Color.GRAY);
		labelFNameCont.setBounds(347, 67, 152, 13);
		panelUser.add(labelFNameCont);

		JLabel labelCellCont = new JLabel("Pr\u00E9nom :");
		labelCellCont.setForeground(Color.GRAY);
		labelCellCont.setBounds(10, 90, 92, 13);
		panelUser.add(labelCellCont);

		final JTextPane textPaneLogin = new JTextPane();
		textPaneLogin.setForeground(Color.BLACK);
		textPaneLogin.setBackground(Color.WHITE);
		textPaneLogin.setBounds(145, 64, 178, 19);
		panelUser.add(textPaneLogin);

		final JPasswordField pw1 = new JPasswordField(20);
		pw1.setHorizontalAlignment(SwingConstants.CENTER);
		pw1.setPreferredSize(new Dimension(19, 19));
		pw1.setBounds(509, 64, 139, 19);
		panelUser.add(pw1);

		final JPasswordField pw2 = new JPasswordField(20);
		pw2.setHorizontalAlignment(SwingConstants.CENTER);
		pw2.setPreferredSize(new Dimension(19, 19));
		pw2.setBounds(509, 87, 139, 19);
		panelUser.add(pw2);

		final JTextPane textPaneFName = new JTextPane();
		textPaneFName.setForeground(new Color(0, 0, 0));
		textPaneFName.setBackground(Color.WHITE);
		textPaneFName.setBounds(145, 87, 178, 19);
		panelUser.add(textPaneFName);

		final JTextPane textPaneName = new JTextPane();
		textPaneName.setForeground(new Color(0, 0, 0));
		textPaneName.setBackground(Color.WHITE);
		textPaneName.setBounds(145, 111, 178, 21);
		panelUser.add(textPaneName);

		JLabel labelRole = new JLabel("Role  :");
		labelRole.setForeground(Color.GRAY);
		labelRole.setBounds(10, 157, 80, 13);
		panelUser.add(labelRole);

		try {
			RolelistView = userCrudController.selectRole();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
		tbtnpwd1.setBounds(655, 63, 35, 21);
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
		tbtnpwd2.setBounds(655, 86, 35, 21);
		panelUser.add(tbtnpwd2);

		tbtnpwd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean selected = tbtnpwd2.getModel().isSelected();
				pw2.setEchoChar(selected ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
			}
		});

		JButton btnAdd = new JButton("Valider");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userLogin = textPaneLogin.getText().toString();
				String userFName = textPaneFName.getText().toString();
				String userName = textPaneName.getText().toString();
				String userRole = selectedItemRole;
				String userPwd = new String(pw1.getPassword());
				String userPwdConf = new String(pw2.getPassword());

				if (userFName.isEmpty()) {
					System.out.println("Prénom n'a pas était saisie");
				} else if (userName.isEmpty()) {
					System.out.println("Nom n'a pas était saisie");
				} else if (userPwd.isEmpty()) {
					System.out.println("Le mot de passe est vide ");
				} else if (!userPwd.equals(userPwdConf)) {
					System.out.println("Les mots de passes ne sont pas identique ");
				} else {
					String newPassWordString = sc.doHashing(pw1);
					System.out.println(userRole);
					User newUser = new User(userLogin, userName, userFName, newPassWordString, userRole);
					ManagementConsoleViews mgv;
					try {
						userCrudController.addNewUser(newUser, User.role);
						try {
							mgv = new ManagementConsoleViews();
							HomeView.desk.add(mgv);
							mgv.show();
							setVisible(false);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBounds(new Rectangle(50, 50, 50, 50));
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(0, 102, 0));
		btnAdd.setBounds(598, 153, 92, 21);
		panelUser.add(btnAdd);

	}

}
