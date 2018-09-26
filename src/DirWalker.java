import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;



public class DirWalker {
    
	public void walk( String path ) throws IOException {
	   

		final long startTime = System.currentTimeMillis();

		String date_file=null;
	
		int total_valid=0, total_invalid=0;
		

      	BufferedWriter  bw_log=null;
	    FileWriter  fw_log=null;
	    Reader inn;	
			
			
  	

fw_log = new FileWriter("logs/logs.txt",true);
bw_log = new BufferedWriter(fw_log);

Writer writer = Files.newBufferedWriter(Paths.get("output/output.csv"),StandardCharsets.UTF_8, 
        StandardOpenOption.WRITE);

CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("First Name","Last Name","Street Number","Street","City","Province","Postal Code","Country","Phone Number","email Address","Date"));

        File root = new File( path );
        File[] lists = root.listFiles();
        String date_str = null;
        String file_path=null;

        for ( File f : lists ){

        	if ( f.isDirectory() ) {
                walk( f.getPath() );
           
           }
             
        	else {
        		if(f.getPath().endsWith(".DS_Store"))
        			continue;
        		
        		   file_path=f.getPath().toString().trim();
        		  
        			  // System.out.println(file_path1);
        			   inn= new FileReader(file_path);
       				
           			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(inn);
           			int valid=0, invalid=0;
           			date_str=f.getPath().toString();  
        	           if (date_str.length()>19) {
        	           date_file=date_str.substring(12,21);
        	           System.out.println(date_file);
           			for (CSVRecord record : records) {
           				

           	         
           			if (record.size()==10){
           			if((record.get(0).isEmpty()) || (record.get(0).equals("First Name")) || (record.get(1).isEmpty()) || (record.get(2).isEmpty())||(record.get(3).isEmpty())||(record.get(4).isEmpty())||(record.get(5).isEmpty())||(record.get(6).isEmpty())||(record.get(7).isEmpty())|| (record.get(8).isEmpty())|| (record.get(9).isEmpty())) {
           			invalid++;	
           			
           			}
           			else {
           			valid++;
           	 csvPrinter.printRecord(record.get(0),record.get(1),record.get(2),record.get(3), record.get(4), record.get(5), record.get(6),record.get(7), record.get(8), record.get(9), date_file);
           			}
           			bw_log.write("\n\nFile :"+ "\nInvalid rows: "+invalid+"\n"+"Valid rows: "+valid);
           			total_valid = total_valid+valid;
             		    total_invalid = total_invalid+invalid;
           			}
           			}
               	  
        	}
        }
        }
     
    
        csvPrinter.flush();
	     csvPrinter.close();
      //  System.out.println("Done");
        bw_log.write("\n_____________________________________________________");
    	bw_log.write("\nTotal invalid rows: "+total_invalid+"\n"+"Total valid rows: "+total_valid);


    			
    		     
    		     final long endTime = System.currentTimeMillis();
    				bw_log.write("\nTotal execution time: " + (endTime - startTime) +" ms");
    		   
    				
    				
           
           if (bw_log != null ) {
				bw_log.close();
			
			}
           else if (fw_log != null ) {
				fw_log.close();
			   
			}

            }
        
	

    public static void main(String[] args) {
    	DirWalker fwa = new DirWalker();
        try {
			fwa.walk("Sample Data/" );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    }

