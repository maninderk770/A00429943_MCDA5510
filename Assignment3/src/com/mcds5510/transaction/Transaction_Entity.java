package com.mcds5510.transaction;

public class Transaction_Entity {
	private String id;
	private String nameOnCard;
	private String cardNumber;
	private String unitPrice;
	private String quantity;
	private String totaltPrice;
	private String expDate;
	private String creditCard;
	private String createdBy;
	private String createdON;

	public String getID() {
		return id;
	}

	public void setID(String iD) {
		id = iD;
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
		return quantity;
	}

	public void setQantity(String qantity) {
		this.quantity = qantity;
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

		results = "ID: " + id + ", NameOnCard: " + nameOnCard + ", CardNumber/CardType: " + cardNumber + " & "
				+ creditCard ;
		results += "\nUnitPrice: " + unitPrice + ", Quantity: " + quantity + ", TotalPrice: " + totaltPrice;
		results += "\nExpDate: " + expDate + ", CreatedBy: " + createdBy + ", CreatedON: " + createdON;
		return results;

	}
}
