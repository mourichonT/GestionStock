package models;

public class Contact {

	private int contId;
	private String contName;
	private String contFName;
	private String contPhone;
	private String contCell;
	private String contMail;
	private String contPosition;
	private String contComment;
	private int userContId;

	public Contact(int contId, String contName, String contFName, String contPhone, String contCell, String contMail,
			String contPosition, String contComment) {
		this.contId = contId;
		this.contName = contName;
		this.contFName = contFName;
		this.contPhone = contPhone;
		this.contCell = contCell;
		this.contMail = contMail;
		this.contPosition = contPosition;
		this.contComment = contComment;
	}

	public Contact(String contName, String contFName, String contPhone, String contCell, String contMail,
			String contPosition, String contComment, int usercontid) {
		this.contId = contId;
		this.contName = contName;
		this.contFName = contFName;
		this.contPhone = contPhone;
		this.contCell = contCell;
		this.contMail = contMail;
		this.contPosition = contPosition;
		this.contComment = contComment;
		this.userContId = usercontid;
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getContId() {
		return contId;
	}

	public void setContId(int contId) {
		this.contId = contId;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getContFName() {
		return contFName;
	}

	public void setContFName(String contFName) {
		this.contFName = contFName;
	}

	public String getContPhone() {
		return contPhone;
	}

	public void setContPhone(String contPhone) {
		this.contPhone = contPhone;
	}

	public String getContCell() {
		return contCell;
	}

	public void setContCell(String contCell) {
		this.contCell = contCell;
	}

	public String getContMail() {
		return contMail;
	}

	public void setContMail(String contMail) {
		this.contMail = contMail;
	}

	public String getContPosition() {
		return contPosition;
	}

	public void setContPosition(String contPosition) {
		this.contPosition = contPosition;
	}

	public String getContComment() {
		return contComment;
	}

	public void setContComment(String contComment) {
		this.contComment = contComment;
	}

	public int getUserContId() {
		return userContId;
	}

	public void setUserContId(int userContId) {
		this.userContId = userContId;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("contact [contId=").append(contId).append(", contName=").append(contName).append(", contFName=")
				.append(contFName).append(", contPhone=").append(contPhone).append(", contCell=").append(contCell)
				.append(", contMail=").append(contMail).append(", contPosition=").append(contPosition)
				.append(", contComment=").append(contComment).append(", toString()=").append(super.toString())
				.append("]");

		return string.toString();
	}
}
