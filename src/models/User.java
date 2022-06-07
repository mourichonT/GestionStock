package models;

public class User {
	
	
	private String login;
	private String password;
	public static String role;
	private String userRole;
	public static int userId;
	private int idUser;
	private String userName;
	private String userFName;
	private int roleId;
	


	public User(int userId, String login, String password, String userName, String userFName, String role ) {
		this.login = login;
		this.password = password;
		this.userName = userName;
		this.userFName = userFName;
		this.userRole = role;
		this.idUser = userId;
	}
	public User( int idUser, String userName, String userFName, String role) {
		super();
		this.idUser = idUser;
		this.userName = userName;
		this.userFName = userFName;
		this.userRole = role;
		
	}
	public User( int idUser, String password) {
		super();
		this.idUser = idUser;
		this.password = password;
	}
	
	public User( String Login, String userName, String userFName, String password, String role) {
		super();
		this.login = Login;
		this.password = password;
		this.userName = userName;
		this.userFName = userFName;
		this.userRole = role;
		
	}
	
	public User(String userName, String userFName, String role, String password) {
		
		this.password = password;
		this.userName = userName;
		this.userFName = userFName;
		this.userRole = role;
		
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserFName() {
		return userFName;
	}


	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}
	
	
	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("User [idUser=").append(idUser).append(", login=").append(login).append(", userRole=").append(userRole)
				.append(", userName=").append(userName).append(", userFName=").append(userFName).append(roleId).append(", roleId=")
				.append(", toString()=").append(super.toString())
				.append("]");

		return string.toString();
	}
	
	
}
