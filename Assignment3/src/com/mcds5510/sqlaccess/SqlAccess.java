package com.mcds5510.sqlaccess;
import java.sql.*;

import com.mcds5510.connect.Connect;
import com.mcds5510.transaction.*;

public class SqlAccess {
	
	private static SqlAccess mysql=new SqlAccess();
	private SqlAccess()
	{
		
	}
	
	public static SqlAccess getObject()
	{
		return mysql;
	}
	
	public boolean insertToDatabase(Transaction_Entity trans_Entity) 
	{
		Connect myCon=Connect.getObject();
		Connection my_Connection=myCon.getConnection();
		try {
			Statement stmt=my_Connection.createStatement();
			ResultSet results = stmt.executeQuery("select * from transaction where id='" + trans_Entity.getID() + "'");
			if (!results.next()) {
				
				stmt.executeUpdate("INSERT INTO transaction " + "VALUES (" + trans_Entity.getID() + ", '"
						+ trans_Entity.getNameOnCard() + "', " + "'" + trans_Entity.getCardNumber() + "', " + trans_Entity.getUnitPrice()
						+ ", " + trans_Entity.getQantity() + "," + trans_Entity.getTotaltPrice() + ",'" + trans_Entity.getExpDate() + "',"
						+ trans_Entity.getCreatedON() + ", '" + trans_Entity.getCreatedBy() + "','" + trans_Entity.getCreditCard()
						+ "' )");
				my_Connection.close();
				return true;
			} else {
				System.out.println("ID already exists\nUse Update!!!");
				my_Connection.close();
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateToDatabase(Transaction_Entity trans_Entity) 
	{
		Connect myCon=Connect.getObject();
		Connection my_Connection=myCon.getConnection();
		try {
			Statement stmt=my_Connection.createStatement();
			ResultSet results = stmt.executeQuery("select * from transaction where id='" + trans_Entity.getID() + "'");
			if (results.next()) {

				stmt.executeUpdate("UPDATE transaction SET NameOnCard='" + trans_Entity.getNameOnCard() + "',CardNumber='"
						+ trans_Entity.getCardNumber() + "',UnitPrice=" + trans_Entity.getUnitPrice() + ",Quantity="
						+ trans_Entity.getQantity() + ",TotalPrice=" + trans_Entity.getTotaltPrice() + ",ExpDate='"
						+ trans_Entity.getExpDate() + "',CreatedON=" + trans_Entity.getCreatedON() + ",CreatedBY='"
						+ trans_Entity.getCreatedBy() + "',transactionid='" + trans_Entity.getCreditCard() + "' WHERE id = "
						+ trans_Entity.getID() + "");
				my_Connection.close();
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean removeFromDatabase(Transaction_Entity trans_Delete) 
	{
		Connect myCon=Connect.getObject();
		Connection my_Connection=myCon.getConnection();
		try {
			Statement stmt=my_Connection.createStatement();
			ResultSet results = stmt.executeQuery("select * from transaction where id='" + trans_Delete.getID() + "'");
			if (results.next()) {

				stmt.executeUpdate("DELETE FROM transaction WHERE id =" + trans_Delete.getID());
				my_Connection.close();
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public Transaction_Entity viewFromDatabase(Transaction_Entity trans_View) 
	{
		Connect myCon=Connect.getObject();
		Connection my_Connection=myCon.getConnection();
		Transaction_Entity trans_Display=new Transaction_Entity();
		
		try {
			Statement stmt=my_Connection.createStatement();
			ResultSet results = stmt.executeQuery("select * from transaction where id='" + trans_View.getID() + "'");
			if (results.next()) {

				trans_Display.setID(results.getString(1));
				trans_Display.setNameOnCard(results.getString("NameOnCard"));
				trans_Display.setCardNumber(results.getString("CardNumber"));
				trans_Display.setUnitPrice(results.getString("UnitPrice"));
				trans_Display.setQantity(results.getString("Quantity"));
				trans_Display.setTotaltPrice(results.getString("TotalPrice"));
				trans_Display.setExpDate(results.getString("ExpDate"));
				trans_Display.setCreatedON(results.getString("CreatedON"));
				trans_Display.setCreatedBy(results.getString("CreatedBY"));
				trans_Display.setCreditCard(results.getString("transactionid"));
				
				my_Connection.close();
				return trans_Display;
			} else {
				return trans_Display;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trans_Display;
	}
}
