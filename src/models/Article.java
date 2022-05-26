package models;

public class Article {

	private int ID;
	private String Name;
	private String DateFormat;
	private String Type;
	private String Fournisseur;
	private int FournisseurID;
	private int PriceId;

	
	
	public Article(int iD, String name, String dateFormat, String type, String fournisseur, int fournisseurID,
			int priceId) {
		ID = iD;
		Name = name;
		DateFormat = dateFormat;
		Type = type;
		Fournisseur = fournisseur;
		FournisseurID = fournisseurID;
		PriceId = priceId;
	}

	public Article() {
		super();
	
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public String getDateCreation() {
		return DateFormat;
	}

	public void setDateCreation(String dateFormat) {
		DateFormat = dateFormat;
	}

	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getFournisseur() {
		return Fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		Fournisseur = fournisseur;
	}
	
	public int getFournisseurID() {
		return FournisseurID;
	}
	public void setFournisseurID(int fournisseurID) {
		FournisseurID = fournisseurID;
	}
	public int getPriceId() {
		return PriceId;
	}
	public void setPriceId(int priceId) {
		this.PriceId = priceId;
	}
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Article [ID=").append(ID)
		.append(", Name=").append(Name)
		.append(", DateFormat=").append(DateFormat)
		.append(", Type=").append(Type)
		.append(", Fournisseur=").append(Fournisseur)
		.append(", FournisseurID=").append(FournisseurID)
		.append(", PriceId=").append(PriceId)
		.append(", toString()=").append(super.toString()).append("]");
		
		return string.toString();
	}
	
	
	
}
