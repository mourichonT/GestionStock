package models;

public class Article {

	private int ID;
	private String Name;
	private String SpectArt;
	private String Fournisseur;
	private String DateFormat;
	private float Price;
	private int QuantityArt;
	private int TypeId;
	private String Type;
	private int FournisseurID;
	private String Comment;
	private Float priceTTC;
	private int UserId;

	public Article(int iD, String name, String spectArt, String fournisseur, String dateFormat, float price,
			int quantityArt, int typeId, int fournisseurID) {
		ID = iD;
		Name = name;
		SpectArt = spectArt;
		Fournisseur = fournisseur;
		DateFormat = dateFormat;
		Price = price;
		QuantityArt = quantityArt;
		TypeId = typeId;
		FournisseurID = fournisseurID;
	}

	public Article(int iD, String name, String spectArt, String fournisseur, String dateFormat, float price,
			int quantityArt, int typeId) {
		ID = iD;
		Name = name;
		SpectArt = spectArt;
		Fournisseur = fournisseur;
		DateFormat = dateFormat;
		Price = price;
		QuantityArt = quantityArt;
		TypeId = typeId;

	}

	public Article(int iD, String name, String spectArt, String fournisseur, String dateFormat, float price,
			int quantityArt, String type, int fournisseurID) {

		ID = iD;
		Name = name;
		SpectArt = spectArt;
		Fournisseur = fournisseur;
		DateFormat = dateFormat;
		Price = price;
		QuantityArt = quantityArt;
		Type = type;
		FournisseurID = fournisseurID;
	}

	public Article(int iD, String name, String spectArt, String fournisseur, String dateFormat, float price,
			int quantityArt) {

		ID = iD;
		Name = name;
		SpectArt = spectArt;
		Fournisseur = fournisseur;
		DateFormat = dateFormat;
		Price = price;
		QuantityArt = quantityArt;

	}

	public Article(int iD, String name, String spectArt, String fournisseur, float price, int quantityArt,String comment, String type, int userId
			) {

		ID = iD;
		Name = name;
		SpectArt = spectArt;
		Fournisseur = fournisseur;
		Price = price;
		QuantityArt = quantityArt;
		Type = type;
		UserId = userId;
		Comment = comment;

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

	public String getSpectArt() {
		return SpectArt;
	}

	public void setSpectArt(String spectArt) {
		SpectArt = spectArt;
	}

	public String getFournisseur() {
		return Fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		Fournisseur = fournisseur;
	}

	public String getDateFormat() {
		return DateFormat;
	}

	public void setDateFormat(String dateFormat) {
		DateFormat = dateFormat;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public int getQuantityArt() {
		return QuantityArt;
	}

	public void setQuantityArt(int quantityArt) {
		QuantityArt = quantityArt;
	}

	public int getTypeId() {
		return TypeId;
	}

	public void setTypeId(int typeId) {
		TypeId = typeId;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getFournisseurID() {
		return FournisseurID;
	}

	public void setFournisseurID(int fournisseurID) {
		FournisseurID = fournisseurID;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public Float getPriceTTC() {
		return priceTTC;
	}

	public void setPriceTTC(Float priceTTC) {
		this.priceTTC = priceTTC;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		this.UserId = userId;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Article [ID=").append(ID).append(", Name=").append(Name).append(", SpecArt=").append(SpectArt)
				.append(", Fournisseur=").append(Fournisseur).append(", DateFormat=").append(DateFormat)
				.append(", TypeId=").append(TypeId).append(", Type=").append(Type).append(", FournisseurID=")
				.append(FournisseurID).append(", Price=").append(Price).append(", QuantityArt=").append(QuantityArt)
				.append(", PriceId=").append(Price).append(", PriceTTC=").append(priceTTC).append(", Comment=")
				.append(", UserId=").append(UserId).append(Comment).append(", toString()=").append(super.toString())
				.append("]");

		return string.toString();
	}

}
