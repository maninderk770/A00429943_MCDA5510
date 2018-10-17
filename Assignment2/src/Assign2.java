import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Assign2 {

	private static final Logger Log=Logger.getLogger(MySQLAccess.class.getName());
	
	
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		
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
	
			MySQLAccess2 dao = new MySQLAccess2();
			
			int exit=0;
			
			while(exit==0) {
				int option=0;
				
				System.out.println("-----MENU-----\n\nPlease choose an option::");
				System.out.println("1.Insert Record\n2.Update Record\n3.Delete Record\n4:Display all records in DB\n5.Exit");
				
				option=Integer.parseInt(br.readLine());

				switch(option) {
				case 1:
					Log.log(Level.FINEST, "Record Insertion Initiated");
					dao.EnterID();
					dao.insertcase();
					break;
				
				case 2:
					Log.log(Level.INFO, "Record Updation Initiated");
					dao.EnterID();
					dao.updatecase();
					break;
				
				case 3:
					Log.log(Level.INFO, "Record Removal Initiated");
					dao.EnterID();
					dao.removecase();
					break;
				
				case 4:
					Log.log(Level.INFO, "Record Retrival Initiated");
					dao.readDataBase();
					break;
				
				case 5:
					Log.log(Level.INFO, "Exiting Application Initiated");
					exit=1;
					System.out.println("Exiting application");
					break;

				default:
					System.out.println("Please enter a valid option");
				}
				
			}
			dao.close();

		}catch (Exception e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
			e.printStackTrace();
			
		}
	}	

}
