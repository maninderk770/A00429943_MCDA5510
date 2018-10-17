import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardAuthorization {

	private static final Logger Log=Logger.getLogger(Assign2.class.getName());
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	
	public String checkexp() {
		String input=null;
		try {
			input=br.readLine();
			Pattern pattern = Pattern.compile("[0-1][0-2][2][0][1-3][0-9]");
			Matcher matcher = pattern.matcher(input);
			if(input.length()!=6) {
				System.out.println("Please enter a valid input( Date between 012016 to 122031 as MMYYYY format!! )"); 
				return checkexp();
			}
			int month=Integer.parseInt(input.substring(0,2));
			int year=Integer.parseInt(input.substring(2,6));
			if (!(matcher.matches()
					&& (month>=01 && month<=12 && year>=2016 &&year<=2031) 
					)) {
				System.out.println("Please enter a valid input( Date between 012016 to 122031 as MMYYYY format!! )"); 
				return checkexp();
			} 	
			
		}catch(Exception e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
			e.printStackTrace();
		}
		return input;
	}

	public String checkcard(String cardtype) {
		String input=null;
		try {
			input=br.readLine();
			if(cardtype.equals("AmericanExpress")) {
				Pattern pattern = Pattern.compile("[3][4-7][0-9]*");
				Matcher matcher = pattern.matcher(input);
				if (!(matcher.matches()&& input.length()==15)) {
					System.out.println("Please enter a valid input( Enter a valid card number with 15 digits for AM(prefix 34-37)!! )"); 
					return checkcard("AmericanExpress");
				}
			}else if(cardtype.equals("MasterCard")) {
				Pattern pattern = Pattern.compile("[5][1-5][0-9]*");
				Matcher matcher = pattern.matcher(input);
				if (!(matcher.matches()&& input.length()==16)) {
					System.out.println("Please enter a valid input( Enter a valid card number with 16 digits for MC(prefix 51-55)!! )"); 
					return checkcard("MasterCard");
				}
			}else if(cardtype.equals("Visa")) {
				Pattern pattern = Pattern.compile("[4][0-9]*");
				Matcher matcher = pattern.matcher(input);
				if (!(matcher.matches()&& input.length()==16)) {
					System.out.println("Please enter a valid input( Enter a valid card number with 16 digits for Visa (prefix 4)!! )"); 
					return checkcard("Visa");
				}
			}
			
		}catch(Exception e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
			e.printStackTrace();
		}
		return input;
	}

	public static String checkint() {
		String input=null;
		try {
			input=br.readLine();
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher matcher = pattern.matcher(input);
			 
			if (!matcher.matches()) {
				System.out.println("Please enter a valid input( Only numbers allowed!! )"); 
				return checkint();
			} 	
			
		}catch(Exception e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
			e.printStackTrace();
		}
		return input;
	}

	public String checkchars() {
		String input=null;
		try {
			input=br.readLine();
			Pattern pattern = Pattern.compile("[a-z A-Z0-9]*");
			Matcher matcher = pattern.matcher(input);
			 
			if (!matcher.matches()) {
				System.out.println("Please enter a valid input( Only alphabets and numbers allowed!! )"); 
				return checkchars();
			} 	
			
		}catch(Exception e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
			e.printStackTrace();
		}
		return input;
	}
	
	public String checkdoub() {
		String input=null;
		try {
			input=br.readLine();
			Pattern pattern = Pattern.compile("^[1-9]\\d*(\\.\\d+)?$");
			Matcher matcher = pattern.matcher(input);
			 
			if (!matcher.matches()) {
				System.out.println("Please enter a valid input( Only numbers with one decimal allowed!! )"); 
				return checkdoub();
			} 	
			
		}catch(Exception e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
			e.printStackTrace();
		}
		return input;
	}

	public String detCardType() {
		System.out.println("Enter which card do you possess:\n1.Visa\n2.MasterCard\n3.American Express");
		String type=null;
		try {
			int opt=Integer.parseInt(br.readLine());
			if(opt==1) {
				type="Visa";
			}else if(opt==2) {
				type="MasterCard";
			}else if(opt==3) {
				type="AmericanExpress";
			}else {
				System.out.println("Enter a valid option!!");
				return detCardType();
			}
		}catch(Exception e) {
			Log.log(Level.FINEST, "Buffered Reader Exception thrown",e);
			e.printStackTrace();
		}
		return type;
	}
	
}
