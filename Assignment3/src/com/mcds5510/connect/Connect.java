package com.mcds5510.connect;

import java.sql.*;


public class Connect {
	
	private static Connect my_Connect=new Connect();
	
	private Connect()
	{
		
	}
	
	public static Connect getObject()
	{
		return my_Connect;
	}
	
	public Connection getConnection()
	{
		Connection myCon=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myCon = DriverManager.getConnection("jdbc:mysql://localhost/mk_baria?"   //DTP I spelled transactoins wrong oops 
		             + "user=mk_baria&password=infinitE"   //Creds
		             +"&useSSL=false"                  // b/c localhost
				     +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			return myCon;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return myCon;
		
	}
}
