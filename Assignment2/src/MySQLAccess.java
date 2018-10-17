import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class MySQLAccess {
	
	private static final Logger Log=Logger.getLogger(Assign2.class.getName());
	
	private static Connection connect = null;
	private static Statement statement = null;
	static int ID_parsed=1;
	Scanner sc = new Scanner(System.in);


	MySQLAccess() throws SQLException 
	{
		try {
			
		System.out.println("Establishing Connection");
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager
			.getConnection("jdbc:mysql://localhost/payment_gateway?"   //DTP I spelled transactoins wrong oops 
		             + "user=root&password=infinitE"   //Creds
		             +"&useSSL=false"                  // b/c localhost
				     +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"// timezone
			     );  
		System.out.println("Connection Made");
	// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		}catch(Exception e) {
			Log.log(Level.FINEST, "SQL Connection Exception thrown",e);
			e.printStackTrace();
		}
	}
	
	
	public boolean createTransaction(Transaction insert) {
		try {
		
			ResultSet results = statement.executeQuery("select * from transaction where id='" + insert.getID() + "'");

			if (!results.next()) {

				statement.executeUpdate("INSERT INTO transaction " + "VALUES (" + insert.getID() + ", '"
						+ insert.getNameOnCard() + "', " + "'" + insert.getCardNumber() + "', " + insert.getUnitPrice()
						+ ", " + insert.getQantity() + "," + insert.getUnitPrice() + ",'" + insert.getExpDate() + "',"
						+ insert.getCreatedON() + ", '" + insert.getCreatedON() + "','" + insert.getCreditCard()
						+ "' )");
				
				return true;
			} else {
				System.out.println("ID already exists\nUse Update!!!");
				
				return false;
			}
		} catch (SQLException e) {
			Log.log(Level.FINEST, "SQL Exception thrown",e);
			e.printStackTrace();
			return false;
		}

	}
	boolean delete(int id) {
		int a = id;
		
		try {
		
			ResultSet results = statement.executeQuery("select * from transaction where id='" + a + "'");

			if (results.next()) {
				statement.executeUpdate("DELETE FROM transaction WHERE id =" + a);
				return true;
			} else {
				System.out.println("ID not Exists");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Transaction getForUpdate(int id) {
		Transaction getValues = new Transaction();
		try {
		
			String tempID = Integer.toString(id);
			ResultSet resultSet = statement.executeQuery("select * from transaction where id='" + tempID + "'");
			if (resultSet.next()) {
				getValues.setID(resultSet.getString(1));
				getValues.setNameOnCard(resultSet.getString("NameOnCard"));
				getValues.setCardNumber(resultSet.getString("CardNumber"));
				getValues.setUnitPrice(resultSet.getString("UnitPrice"));
				getValues.setQantity(resultSet.getString("Quantity"));
				getValues.setTotaltPrice(resultSet.getString("TotalPrice"));
				getValues.setExpDate(resultSet.getString("ExpDate"));
				getValues.setCreatedON(resultSet.getString("CreatedON"));
				getValues.setCreatedBy(resultSet.getString("CreatedBY"));
				getValues.setCreditCard(resultSet.getString("transactionid"));
			}
			return getValues;
		} catch (SQLException e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);

			e.printStackTrace();
		}
		return getValues;
	}

	public boolean update_ID(Transaction insert) {
		
		try {
			
			statement.executeUpdate("UPDATE transaction SET NameOnCard='" + insert.getNameOnCard() + "',CardNumber='"
					+ insert.getCardNumber() + "',UnitPrice=" + insert.getUnitPrice() + ",Quantity="
					+ insert.getQantity() + ",TotalPrice=" + insert.getUnitPrice() + ",ExpDate='"
					+ insert.getExpDate() + "',CreatedON=" + insert.getCreatedON() + ",CreatedBY='"
					+ insert.getCreatedON() + "',transactionid='" + insert.getCreditCard() + "' WHERE id = "
					+ insert.getID() + "");
			
			return true;

		} catch (SQLException e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);

			e.printStackTrace();
			return false;
		}

	}

	public boolean create_New(String identify) throws SQLException {
	
	Transaction trxn_Create = new Transaction();
	int id;
	String NameOnCard;
	String CardNumber;
	float UnitPrice;
	int Quantity;
	float TotalPrice;
	String ExpDate;
	String CreatedON = "NOW()";
	String CreatedBY = identify;
	String cc_type;
	String temp;
	System.out.println("Enter the ID");

	temp = sc.next();
	id = check_int(temp, 1);

	System.out.println("Enter the Name of Card Holder");
	NameOnCard = check_string(sc.next());

	System.out.println("Enter Card Number");
	CardNumber = check_string(sc.next());
	while (true) {
		if (CardNumber.length() == 16 && (CardNumber.startsWith("51") || CardNumber.startsWith("52")
				|| CardNumber.startsWith("53") || CardNumber.startsWith("54") || CardNumber.startsWith("55"))) {
			cc_type = "Master Card";
			break;
		} else if (CardNumber.length() == 16 && (CardNumber.startsWith("4"))) {
			cc_type = "VISA Card";
			break;
		} else if (CardNumber.length() == 15 && (CardNumber.startsWith("34") || CardNumber.startsWith("37"))) {
			cc_type = "AMEX";
			break;
		} else {

			System.out.println("Enter Valid Card number");
			CardNumber = check_string(sc.next());
		}
	}
	// System.out.println(cc_type);
	System.out.println("Enter Unit Price");
	temp = sc.next();
	UnitPrice = check_float(temp);
	System.out.println("Enter Quantity");
	temp = sc.next();
	Quantity = check_int(temp);

	TotalPrice = UnitPrice * Quantity;

	System.out.println("Enter Expiry Date");

	ExpDate = check_exp_date(sc.next());

	trxn_Create.setID(Integer.toString(id));
	trxn_Create.setNameOnCard(NameOnCard);
	trxn_Create.setCardNumber(CardNumber);
	trxn_Create.setUnitPrice(Float.toString(UnitPrice));
	trxn_Create.setQantity(Integer.toString(Quantity));
	trxn_Create.setTotaltPrice(Float.toString(TotalPrice));
	trxn_Create.setExpDate(ExpDate);
	trxn_Create.setCreatedON(CreatedON);
	trxn_Create.setCreatedBy(CreatedBY);
	trxn_Create.setCreditCard(cc_type);

	return createTransaction(trxn_Create);

}
	
	public String check_string(String check_string) {
		String a_string;
		a_string = check_string;

		while (true) {
			if (a_string.matches(";") || a_string.contains(":") || a_string.contains("!") || a_string.contains("@")
					|| a_string.contains("^") || a_string.contains("*") || a_string.contains("+")
					|| a_string.contains("?") || a_string.contains("<") || a_string.contains(">")
					|| a_string.contains("#") || a_string.contains("$") || a_string.contains("%")
					|| a_string.contains(",")) {
				System.out.println("Enter valid Input!!");
				a_string = sc.next();
			} else
				break;
		}
		// sc.close();
		return a_string;
	}
	public Float check_float(String check_string) {
		String a_string;
		a_string = check_string;
		Float out;

		while (true) {
			if (!a_string.matches("[a-zA-Z]+") && a_string.matches("[0-9]+")) {
				out = Float.parseFloat(a_string);
				return out;
			} else {
				System.out.println("Re-enter Input");
				a_string = sc.next();
			}
		}

	}

	public int check_int(String check_string) {
		String a_string;
		a_string = check_string;
		int out;

		while (true) {
			if (!a_string.matches("[a-zA-Z]+") && a_string.matches("[0-9]+")) {
				out = Integer.parseInt(a_string);
				return out;
			} else {
				System.out.println("Re-enter Input");
				a_string = sc.next();
			}
		}

	}

	public String check_exp_date(String check_string) {
		String a_string;
		a_string = check_string;
		String out;

		while (true) {
			String temp[] = a_string.split("/");
			if (temp[0].length() == 2 && temp[1].length() == 4 && !temp[0].matches("[a-zA-Z]+")
					&& temp[0].matches("[0-9]+") && !temp[1].matches("[a-zA-Z]+") && temp[1].matches("[0-9]+")
					&& Integer.parseInt(temp[0]) < 13 && Integer.parseInt(temp[0]) > 0
					&& Integer.parseInt(temp[1]) > 2015 && Integer.parseInt(temp[1]) < 2032) {
				return a_string;
			} else {
				System.out.println("Enter correct format");
				a_string = sc.next();
			}
		}

	}

	public int check_int(String check_string, int a) {
		String a_string;
		a_string = check_string;
		int out;

		while (true) {

			if (!a_string.matches("[a-zA-Z]+") && a_string.matches("[0-9]+")) {
				try {
				
		ResultSet results = statement.executeQuery("select * from transaction where id='" + a_string + "'");

					if (!results.next()) {
						out = Integer.parseInt(a_string);
						return out;
					} else {
						System.out.println("ID Already Taken,  Try other");
						a_string = sc.next();
						continue;
					}
				} catch (SQLException e) {
					Log.log(Level.FINEST, "SQL Exception thrown",e);
					e.printStackTrace();
				}

			} else {

				System.out.println("Re-enter Input");
				a_string = sc.next();
				continue;
			}

		}

	}

	public int check_int(String check_string, int a, int b) {
		String a_string;
		a_string = check_string;
		int out;

		while (true) {

			if (!a_string.matches("[a-zA-Z]+") && a_string.matches("[0-9]+")) {
				try {
					
					ResultSet results = statement.executeQuery("select * from transaction where id='" + a_string + "'");

					if (results.next()) {
						out = Integer.parseInt(a_string);
						return out;
					} else {
						System.out.println("ID Not Present, Try other");
						a_string = sc.next();
						continue;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				System.out.println("Re-enter Input");
				a_string = sc.next();
				continue;
			}

		}

	}
	

		public boolean update_ex(String identify) {
			Transaction updateUser = new Transaction();
			Transaction getUserModify = new Transaction();

			int id;
			String NameOnCard;
			String CardNumber;
			float UnitPrice;
			int Quantity;
			float TotalPrice;
			String ExpDate;
			String CreatedON = "NOW()";
			String CreatedBY = identify;
			String cc_type = null;
			String temp;
			System.out.println("Enter ID");
			temp = sc.next();
			id = check_int(temp, 1, 1);

			getUserModify = getForUpdate(id);
			//

			id = Integer.parseInt(getUserModify.getID());
			NameOnCard = getUserModify.getNameOnCard();
			CardNumber = getUserModify.getCardNumber();
			UnitPrice = Float.parseFloat(getUserModify.getUnitPrice());
			Quantity = Integer.parseInt(getUserModify.getQantity());
			TotalPrice = Float.parseFloat(getUserModify.getUnitPrice());
			ExpDate = getUserModify.getExpDate();
			cc_type = getUserModify.getCreditCard();

			char a;

			System.out.println("Want to update Name On Card?" + NameOnCard + "\n");
			System.out.println("Press 'Y'/'y' to update" );

			a = sc.next().charAt(0);
			if (a == 'y' || a == 'Y')
				NameOnCard = check_string(sc.next());
			System.out.println("Want to update Card Number?" + CardNumber + "\n");
			System.out.println("Press 'Y'/'y' to update" );

			a = sc.next().charAt(0);
			if (a == 'y' || a == 'Y') {
				CardNumber = sc.next();
				while (true) {
					if (CardNumber.length() == 16 && (CardNumber.startsWith("51") || CardNumber.startsWith("52")
							|| CardNumber.startsWith("53") || CardNumber.startsWith("54") || CardNumber.startsWith("55"))) {
						cc_type = "Master Card";
						break;
					} else if (CardNumber.length() == 16 && (CardNumber.startsWith("4"))) {
						cc_type = "VISA Card";
						break;
					} else if (CardNumber.length() == 15 && (CardNumber.startsWith("34") || CardNumber.startsWith("37"))) {
						cc_type = "AMEX";
						break;
					} else {

						System.out.println("Enter Valid Card number");
						CardNumber = check_string(sc.next());
					}
				}

			}
			System.out.println("Want to update Unit Price?" + UnitPrice + "\n");
			System.out.println("Press 'Y'/'y' to update" );

			a = sc.next().charAt(0);
			if (a == 'y' || a == 'Y') {
				temp = sc.next();
				UnitPrice = check_float(temp);
			}

			System.out.println("Want to update Quantity?" + Quantity + "\n");
			System.out.println("PPress 'Y'/'y' to update" );

			a = sc.next().charAt(0);
			if (a == 'y' || a == 'Y') {
				temp = sc.next();
				Quantity = check_int(temp);
			}

			System.out.println("Want to update Expiry Date?" + ExpDate + "\n");
			System.out.println("Press 'Y'/'y' to update" );

			a = sc.next().charAt(0);
			if (a == 'y' || a == 'Y')
				ExpDate = check_exp_date(sc.next());

			TotalPrice = Quantity * UnitPrice;

			updateUser.setID(Integer.toString(id));
			updateUser.setNameOnCard(NameOnCard);
			updateUser.setCardNumber(CardNumber);
			updateUser.setUnitPrice(Float.toString(UnitPrice));
			updateUser.setQantity(Integer.toString(Quantity));
			updateUser.setTotaltPrice(Float.toString(TotalPrice));
			updateUser.setExpDate(ExpDate);
			updateUser.setCreatedBy(CreatedBY);
			updateUser.setCreatedON(CreatedON);
			updateUser.setCreditCard(cc_type);

			return update_ID(updateUser);
		}
		public Collection<Transaction> createTrxns(ResultSet resultSet) throws SQLException {
			Collection<Transaction> results = new ArrayList<Transaction>();

			while (resultSet.next()) {
				Transaction trxn = new Transaction();
				trxn.setID(resultSet.getString(1));
				trxn.setNameOnCard(resultSet.getString("NameOnCard"));
				trxn.setCardNumber(resultSet.getString("CardNumber"));
				trxn.setUnitPrice(resultSet.getString("UnitPrice"));
				trxn.setQantity(resultSet.getString("Quantity"));
				trxn.setTotaltPrice(resultSet.getString("TotalPrice"));
				trxn.setExpDate(resultSet.getString("ExpDate"));
				trxn.setCreatedON(resultSet.getString("CreatedON"));
				trxn.setCreatedBy(resultSet.getString("CreatedBY"));
				trxn.setCreditCard(resultSet.getString("transactionid"));
				results.add(trxn);
			}
			return results;
		}
		public Collection<Transaction> view_details(int id) {
			int u_id = id;
			try {
				
				Collection<Transaction> resultSet = null;
				ResultSet results = statement.executeQuery("select * from transaction where id='" + u_id + "'");
				resultSet = createTrxns(results);
				for (Transaction trxn : resultSet) {
					System.out.println(trxn.toString());
				}

				return resultSet;

			} catch (SQLException e) {
				Log.log(Level.FINEST, "SQL Exception thrown",e);
				e.printStackTrace();
			}
			return null;

		}
		public void close() {
			try {

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
		
		}