import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLAccess2 {
	
	private static final Logger Log=Logger.getLogger(Assign2.class.getName());
	
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	static int ID_parsed=1;
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	MySQLAccess2() throws SQLException 
	{
		try {
			
		System.out.println("Establishing Connection");
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager
			.getConnection("jdbc:mysql://dev.cs.smu.ca/s_magray?"   //DTP I spelled transactoins wrong oops 
	             + "user=s_magray&password=A00427266"   //Creds
	             +"&useSSL=false&AllowPublicKeyRetrieval=True"                  // b/c localhost
			     +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" // timezone
			     );  
		System.out.println("Connection Established");
	// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		}catch(Exception e) {
			Log.log(Level.FINEST, "SQL Connection Exception thrown",e);
			e.printStackTrace();
		}
	}
	
	//For Entering ID
	public void EnterID() {
		try {
			System.out.println("Enter the ID:");
			ID_parsed=Integer.parseInt(CardAuthorization.checkint());
		}
		catch(Exception e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
			e.printStackTrace();
		}
	}	

	//For Inserting entries
	public void insertcase() {
		try {
			
			if(checktxn().equals(true)){
				System.out.println("Your ID for the transaction is:"+ID_parsed);
				Insert();
				if(checktxn().equals(false))
				{
					System.out.println("Record has been successfully inserted!!!");
				}
				else {
					System.err.println("Something went wrong!! Please try again");
				}
				readDataBase(ID_parsed);
				ID_parsed++;
				
			}
			else {
				System.out.println("Your ID for the transaction is:"+ID_parsed);
				System.out.println("but this ID already exists. Do you wish to update this record (y/n)?");
				String opt=br.readLine();
				if(opt.equals("y")) {
					updatecase();
					
				}
				else
				{
					Maxtrxnid();	// function for getting the max ID to keep ID_parsed and no further conflicts arise
					System.out.println("The Max ID as per the DB is "+(ID_parsed-1)+"\nThe new record will be inserted in "+ID_parsed+" for your ease");
					Insert();
					if(checktxn().equals(false))
					{
						System.out.println("The following Record has been successfully inserted with the ID "+ID_parsed+"!!!\n");
					}
					else {
						System.err.println("Something went wrong!! Please try again");
					}
					readDataBase(ID_parsed);
					ID_parsed++;
				}
			}
		}catch (Exception e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
			e.printStackTrace();
		}
		
	}
	
	//for Updating entries
	public void updatecase() {
		try {
			if(checktxn().equals(false)) {
				readDataBase(ID_parsed);
				writeMetaData(ID_parsed);
				
				System.out.println("Which field do you wish to update?");
				int input=Integer.parseInt(br.readLine());
				
				String field=writeMetaData(input,ID_parsed);
				
				if(input==2||input==3 ||input==8||input==4) {
					Updatevarchar(field,ID_parsed);
					readDataBase(ID_parsed);
				}
				else if(input==1||input==5||input==6 ||input==7){
					Updateint(field,ID_parsed);
					readDataBase(ID_parsed);
				}
				else {
					System.out.println("The selected field cannot be updated as they are fixed");
				}
			}
			else {
				System.out.println("The transaction does not exist on the DB. Please select the main menu options again");
			}

		}catch (Exception e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
			e.printStackTrace();
		}
	}
	
	//For Removing entries
	public void removecase() {
		try {
			if(checktxn().equals(false)) {
				Remove(ID_parsed);
				readDataBase();
			}
			else {
				System.out.println("The transaction does not exist on the DB. Please select the main menu options again");
			}
		}catch (Exception e) {
			Log.log(Level.FINEST, "Exception thrown",e);
			e.printStackTrace();
		}
	}
	
	//Detailed Update
	private void Updatevarchar(String field,int ID)
	{
		try {
			System.out.println("Enter the new field value:");
			String change=br.readLine();
			String query="update s_magray.transaction set "+field+" = ? where ID=?";

			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setString(1, change);
			preparedStatement.setInt(2, ID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			System.out.println("The Updation has been done!!!\nThe updated row is:");
		}catch(Exception e) {
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
		}
	}
	
	private void Updateint(String field,int ID)
	{
		try {
			System.out.println("Enter the new field value:");
			String change=br.readLine();
			String query="update s_magray.transaction set "+field+" = ? where ID=?";

			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setString(1, change);
			preparedStatement.setInt(2, ID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			System.out.println("The Updation has been done!!!\nThe updated row is:");
		}catch(Exception e) {
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
		}
	}
	
	//Detailed Remove
	private void Remove(int ID)
	{
		try {
			preparedStatement = connect.prepareStatement("delete from s_magray.transaction where ID= ? ; ");
			preparedStatement.setInt(1, ID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			System.out.println("The Row has been deleted!!!\nThe remaining rows are:");
			
		}catch(Exception e) {
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
		}
	}
	
	//Reading DB
	public void readDataBase() throws Exception {
		try {
	
			resultSet = statement.executeQuery("select * from s_magray.transaction order by ID asc");
			printResultSet(resultSet);

		} catch (Exception e) {
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
		} 

	}
	
	public void readDataBase(int ID) throws Exception {
		try {
			
			resultSet = statement.executeQuery("select * from s_magray.transaction where id="+ID);
			printResultSet(resultSet);
			
		} catch (Exception e) {
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
		} 

	}

	//Reading columns of DB
	private void writeMetaData(int ID) throws SQLException {
		ResultSet resultSet=statement.executeQuery("select * from s_magray.transaction where id="+ID);
		
		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
				System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
						
		}
	}

	private String writeMetaData(int field,int ID) throws SQLException {
		
		ResultSet resultSet=statement.executeQuery("select * from s_magray.transaction where id="+ID);;
		
		return resultSet.getMetaData().getColumnName(field);
	}

	//Printing and retrieving data from DB
	private void printResultSet(ResultSet resultSet) {
		try {
			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				String ID = resultSet.getString("ID");
				String NameOnCard = resultSet.getString("NameOnCard");
				String CardNumber = resultSet.getString("CardNumber");
				String CardType = resultSet.getString("CardType");
				String ExpDate = resultSet.getString("ExpDate");
				String UnitPrice = resultSet.getString("UnitPrice");
				String qty = resultSet.getString("Quantity");
				String totalPrice = resultSet.getString("TotalPrice");
				String createdOn = resultSet.getString("CreatedOn");
				String createdBy = resultSet.getString("CreatedBy");
	
				System.out.println("ID: " + ID);
				System.out.println("NameOnCard: " + NameOnCard);
				System.out.println("CardNumber: " + CardNumber);
				System.out.println("CardType: " + CardType);
				System.out.println("ExpDate: " + ExpDate);
				System.out.println("UnitPrice: " + UnitPrice);
				System.out.println("Qty: " + qty);
				System.out.println("TotalPrice: " + totalPrice);
				System.out.println("CreatedOn: " + createdOn);
				System.out.println("CreatedBy: " + createdBy+"\n\n");
			}
		}catch(Exception e) {
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
		}
	}

	//Closing all the instances
	public void close() {
		try {
			
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
		}
	}
	
	//Detailed Insert
	private void Insert()
	{
		try {
			
			CardAuthorization ca=new CardAuthorization();
			System.out.print("Please enter your payment info:\nNameOnCard:");
			String namecard=ca.checkchars();
			
			String CardType=ca.detCardType();
			
			System.out.print("CardNumber: ");
			String CardNum=ca.checkcard(CardType);
			
			System.out.print("ExpDate (MMYYYY): ");
			String expdt=ca.checkexp();
			
			System.out.print("UnitPrice: ");
			Double UnitPrice=Double.parseDouble(ca.checkdoub());
			
			System.out.print("Quantity: ");
			int qty=Integer.parseInt(CardAuthorization.checkint());
			
			preparedStatement = connect
					.prepareStatement("insert into  s_magray.transaction values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, ID_parsed);
			preparedStatement.setString(2, namecard);
			preparedStatement.setString(3, CardNum);
			preparedStatement.setString(4, CardType);
			preparedStatement.setDouble(5, UnitPrice);
			preparedStatement.setInt(6, qty);
			preparedStatement.setDouble(7, qty*UnitPrice);
			preparedStatement.setString(8, expdt);
			preparedStatement.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(10, System.getProperty("user.name"));
			preparedStatement.executeUpdate();
			
		}catch(Exception e) {
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
		}
	}
	
	//Checking if txn is present or not
	private Boolean checktxn() {
		int flag=0;	
		try {
			resultSet = statement.executeQuery("select ID from s_magray.transaction order by ID asc");
			while (resultSet.next()) {
				String ID = resultSet.getString("ID");
				if(ID_parsed==Integer.parseInt(ID)) {
					flag=1;
					break;
					
				}
			}
		}
		catch(Exception e){
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
		}
		if(flag==1)
			return false;
		else
			return true;
		
	}
	
	//to set the max id as a ID parsed
	private void Maxtrxnid() {
		// The function is used for updating the highest ID in the DB
		try {
				
			resultSet = statement.executeQuery("select ID from s_magray.transaction order by ID asc");
			String ID=null;
			while (resultSet.next()) {
				ID = resultSet.getString("ID");
			}
			ID_parsed=Integer.parseInt(ID);
			ID_parsed++;
		}
		catch(SQLException e){
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
		}
		
	}

}
