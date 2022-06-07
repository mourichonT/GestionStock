package models;

public class RoleUser {

	
	private int RoleIdUser;
	private String userRole;
	
	
	public RoleUser(int roleIdUser, String userRole) {
		
		RoleIdUser = roleIdUser;
		this.userRole = userRole;
	}
	
	public RoleUser() {
		super();
	}
	public RoleUser(String userRole) {
		super();
	}
	
	public int getRoleIdUser() {
		return RoleIdUser;
	}
	
	public void setRoleIdUser(int roleId) {
		RoleIdUser = roleId;
	}
	
	public String getUserRole() {
		return userRole;
	}
	
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Type [RoleIdUser=").append(RoleIdUser)
		.append(", userRole=").append(userRole)
		.append(", toString()=").append(super.toString()).append("]");
		return string.toString();

		}
}
