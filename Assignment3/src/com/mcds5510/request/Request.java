package com.mcds5510.request;
import com.mcds5510.transaction.*;
import com.mcds5510.sqlaccess.*;

public class Request {
	
public String create(String id, String nameOnCard, String cardNumber,String unitPrice, 
		String quantity, String expDate) 
{

	Transaction_Entity trans_Insert=new Transaction_Entity();
	SqlAccess insert= SqlAccess.getObject();
	
	String createdON = "NOW()";
	String createdBy = System.getProperty("user.name");
	String cc_type;
		if (cardNumber.length() == 16 && (cardNumber.startsWith("51") || cardNumber.startsWith("52")
				|| cardNumber.startsWith("53") || cardNumber.startsWith("54") || cardNumber.startsWith("55"))) {
			cc_type = "Master Card";
		} else if (cardNumber.length() == 16 && (cardNumber.startsWith("4"))) {
			cc_type = "VISA Card";
		} else if (cardNumber.length() == 15 && (cardNumber.startsWith("34") || cardNumber.startsWith("37"))) {
			cc_type = "AMEX";
		} else {

			return "Invalid";
		}
		if (nameOnCard.matches(";") || nameOnCard.contains(":") || nameOnCard.contains("!") || nameOnCard.contains("@")
				|| nameOnCard.contains("#") || nameOnCard.contains("$") || nameOnCard.contains("%")
				|| nameOnCard.contains("^") || nameOnCard.contains("*") || nameOnCard.contains("+")
				|| nameOnCard.contains("?") || nameOnCard.contains("<") || nameOnCard.contains(">")
				|| nameOnCard.contains(",")) {
			return "Invalid";
		} 
	trans_Insert.setID(id);
	trans_Insert.setNameOnCard(nameOnCard);
	trans_Insert.setCardNumber(cardNumber);
	trans_Insert.setUnitPrice(unitPrice);
	trans_Insert.setQantity(quantity);
	float totaltPrice=Float.parseFloat(unitPrice)*Float.parseFloat(quantity);
	trans_Insert.setTotaltPrice(String.valueOf(totaltPrice));
	trans_Insert.setExpDate(expDate);
	trans_Insert.setCreditCard(cc_type);
	trans_Insert.setCreatedBy(createdBy);
	trans_Insert.setCreatedON(createdON);
	
	boolean result=insert.insertToDatabase(trans_Insert);
	if (result==true)
		return "ID is Inserted";
	else
		return "ID is Not Inserted";
	}


public String update(String id, String nameOnCard, String cardNumber,String unitPrice, 
	String quantity, String expDate) 
{
//Validation_Entity check_input=new Validation_Entity();
Transaction_Entity trans_Update=new Transaction_Entity();
SqlAccess update= SqlAccess.getObject();

String createdON = "NOW()";
String createdBy = System.getProperty("user.name");
String cc_type;
if (cardNumber.length() == 16 && (cardNumber.startsWith("51") || cardNumber.startsWith("52")
		|| cardNumber.startsWith("53") || cardNumber.startsWith("54") || cardNumber.startsWith("55"))) {
	cc_type = "Master Card";
} else if (cardNumber.length() == 16 && (cardNumber.startsWith("4"))) {
	cc_type = "VISA Card";
} else if (cardNumber.length() == 15 && (cardNumber.startsWith("34") || cardNumber.startsWith("37"))) {
	cc_type = "AMEX";
} else {

	return "Invalid";
}
if (nameOnCard.matches(";") || nameOnCard.contains(":") || nameOnCard.contains("!") || nameOnCard.contains("@")
		|| nameOnCard.contains("#") || nameOnCard.contains("$") || nameOnCard.contains("%")
		|| nameOnCard.contains("^") || nameOnCard.contains("*") || nameOnCard.contains("+")
		|| nameOnCard.contains("?") || nameOnCard.contains("<") || nameOnCard.contains(">")
		|| nameOnCard.contains(",")) {
	return "Invalid";
} 
trans_Update.setID(id);
trans_Update.setNameOnCard(nameOnCard);
trans_Update.setCardNumber(cardNumber);
trans_Update.setUnitPrice(unitPrice);
trans_Update.setQantity(quantity);
float totaltPrice=Float.parseFloat(unitPrice)*Float.parseFloat(quantity);
trans_Update.setTotaltPrice(String.valueOf(totaltPrice));
trans_Update.setExpDate(expDate);
trans_Update.setCreditCard(cc_type);

trans_Update.setCreatedBy(createdBy);
trans_Update.setCreatedON(createdON);


boolean result=update.updateToDatabase(trans_Update);
if (result==true)
	return "ID is Updated";
else
	return "ID Not Exists\nUse Create";
}

public String delete(String id) 
	{
	//Validation_Entity check_input=new Validation_Entity();
	Transaction_Entity trans_Delete=new Transaction_Entity();
	SqlAccess delete= SqlAccess.getObject();

	trans_Delete.setID(id);
	

	boolean result=delete.removeFromDatabase(trans_Delete);
	if (result==true)
		return "ID is Deleted";
	else
		return "ID Not Exists\nUse Create";
	}

public String view(String id) 
{
//Validation_Entity check_input=new Validation_Entity();
Transaction_Entity trans_View=new Transaction_Entity();
SqlAccess view= SqlAccess.getObject();

trans_View.setID(id);



Transaction_Entity result=view.viewFromDatabase(trans_View);
if (result!=null)
{
	return result.toString();
}
else
	return "ID Not Exists\nUse Create";
}

}
