package models;

public class User {
	
	private int userId;
	private String login;
	private String password;
	public static String role;
	
	
	public User(int userId, String login, String password, String role) {
		super();
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.role = role;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public static String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
}
