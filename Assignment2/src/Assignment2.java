import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.*;
import java.util.logging.*;

public class Assignment2 {
	private static final Logger Log=Logger.getLogger(MySQLAccess.class.getName());

	public static void main(String args[]) throws SQLException {

		
		Handler consolehandler=null;
		Handler filehandler= null;
		
		try {
			consolehandler=new ConsoleHandler();
			filehandler=new FileHandler("./Logs.log");
		
			Log.addHandler(consolehandler);
			Log.addHandler(filehandler);
	
			consolehandler.setLevel(Level.ALL);
			filehandler.setLevel(Level.ALL);
			Log.setLevel(Level.ALL);
		
			Log.config("Log Configuration Done");
			
			Log.removeHandler(consolehandler);

			MySQLAccess dao = new MySQLAccess();
		Scanner sc = new Scanner(System.in);
		String user;
		boolean create;
		boolean update;

	
			user =  System.getProperty("user.name");
			int decide;

			System.out.println(user);

			if (user !=null) {
				while (true) {
					System.out.println("\nPRESS\n");
					System.out.println(" 1. Insert new record. \n 2. Update record. \n 3. Show all recods. \n 4. Delete\n 5. Exit");
				
					decide = dao.check_int(sc.next());
					if (decide == 5) {
						System.exit(0);
					}
					switch (decide) {
					case 1: {
					
						create = dao.create_New(user);		
						if (create) {
				        Log.log(Level.FINEST, "Record Inserted");
						}
						break;
					}
					case 2: {
						update = dao.update_ex( user);
						if (update) {
		     			Log.log(Level.FINEST, "Record Updated");

						}

						break;

					}
					case 3: {
						System.out.println("Enter the ID to view");
						int a;
						a = dao.check_int(sc.next());
		     			dao.view_details(a);

						break;
					}
					case 4: {
						
						System.out.println("Enter ID to delete");
						int id;
						id =  dao.check_int(sc.next());
						if (dao.delete(id)) {
							Log.log(Level.FINEST, "Record Deleted");
						
						}
						break;
		
					}
					default :
						System.out.println("Please enter a valid option");
						
				}
				}
			}

			else {
				System.out.println("Not able to find user");
				Log.log(Level.WARNING, "Failure");
				
			}
			dao.close();
		}
			catch (Exception e) {
				Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
				e.printStackTrace();
			}
	
		

}
}
