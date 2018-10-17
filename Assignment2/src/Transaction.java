public class Transaction {

	private String ID;
	private String nameOnCard;
	private String cardNumber;
	private String unitPrice;
	private String qantity;
	private String totaltPrice;
	private String expDate;
	private String creditCard;
	private String createdBy;
	private String createdON;

	public Transaction() {
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getQantity() {
		return qantity;
	}

	public void setQantity(String qantity) {
		this.qantity = qantity;
	}

	public String getTotaltPrice() {
		return totaltPrice;
	}

	public void setTotaltPrice(String totaltPrice) {
		this.totaltPrice = totaltPrice;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedON() {
		return createdON;
	}

	public void setCreatedON(String createdON) {
		this.createdON = createdON;
	}

	public String toString() {

		String results = new String();

		results = "\nID: " + ID + ", NameOnCard: " + nameOnCard + ", CardNumber: " + cardNumber + ", CardType: "
				+ creditCard ;
		results += "\nUnitPrice: " + unitPrice + ", Quantity: " + qantity + ", TotalPrice: " + totaltPrice;
		results += "\nExpDate: " + expDate + ", CreatedON: " + createdON + "\n";
		return results;

	}

}