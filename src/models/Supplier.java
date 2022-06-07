package models;

public class Supplier {

	private int supId;
	private String supplierAddress;
	private String supplierName;
	private String supplierPhone;
	private String supplierContactId;
	private int contactId;

	public Supplier(int supId, String supplierAddress, String supplierName, String supplierPhone,
			String supplierContactId) {
		super();
		this.supId = supId;
		this.supplierAddress = supplierAddress;
		this.supplierName = supplierName;
		this.supplierPhone = supplierPhone;
		this.supplierContactId = supplierContactId;

	}

	public Supplier() {
		super();
	}

	public Supplier(String supplierAddress, String supplierName, String supplierPhone) {
		this.supplierAddress = supplierAddress;
		this.supplierName = supplierName;
		this.supplierPhone = supplierPhone;
	}

	
	public Supplier(int supId, String supplierAddress, String supplierName, String supplierPhone,
			int supplierContactId) {
		super();
		this.supId = supId;
		this.supplierAddress = supplierAddress;
		this.supplierName = supplierName;
		this.supplierPhone = supplierPhone;
		this.contactId = supplierContactId;

	}
	
	public int getSupId() {
		return supId;
	}

	public void setSupId(int supId) {
		this.supId = supId;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getSupplierContactId() {
		return supplierContactId;
	}

	public void setSupplierContact(String supplierContactId) {
		this.supplierContactId = supplierContactId;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("suppliers [supId=").append(supId)
		.append(", supplierAddress=").append(supplierAddress)
		.append(", supplierName=").append(supplierName)
		.append(", supplierPhone=").append(supplierPhone)
		.append(", supplierContactId=").append(supplierContactId)
		.append(", supplierContactId=").append(contactId)	
		.append(", toString()=").append(super.toString())
		.append("]");

		return string.toString();
	}

}
