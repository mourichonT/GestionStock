package models;

public class Type {

	private int TypeId;
	private String typeName;
	

	public Type(int typeId, String typeName) {
		
		TypeId = typeId;
		this.typeName = typeName;
	}
	
	public Type() {
		super();
	}
	
	public int getTypeId() {
		return TypeId;
	}
	public void setTypeId(int typeId) {
		TypeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Type [TypeId=").append(TypeId)
		.append(", typeName=").append(typeName)
		.append(", toString()=").append(super.toString()).append("]");
		return string.toString();

		}
	
}
