package theatre.seating.system;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import theatre.seating.model.Row;
import theatre.seating.model.Theatre;
import theatre.seating.model.TicketRequest;
import theatre.seating.service.TheatreSeatingService;
import theatre.seating.service.impl.TheatreSeatingServiceImpl;

/**
 * This is the Theatre Seating System Program that handles the 
 * list of ticket requests and allocates the seats based on availability
 * @author Prashanth Pulivendula
 */
public class TheatreSeatingSystem {
    
    /**
     * This is the entry point for Theatre Seating System 
     * @param file_name
     */
	public static void readTheatreLayout(String file_name){
		try {
			writeOutPutToFile();
			FileInputStream fileInputStream = new FileInputStream(file_name);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
			String stringLine;
            int noOfRows = 0;
            Boolean blankline = false;
            Theatre theatreLayout = new Theatre();
            List<Row> rows = new ArrayList<>();
            List<TicketRequest> ticketRequests = new ArrayList<>();
            TheatreSeatingService theatreSeatingService = new TheatreSeatingServiceImpl();
            
			while ((stringLine = bufferedReader.readLine()) != null) {
				String[] array = stringLine.split("\n");
				if(stringLine.isEmpty()) {
					blankline = true;
					continue;
				}
				List<String> thatreRow = new ArrayList<>(Arrays.asList(array[0].split(" ")));
				if(!blankline) {
				  //construct Theatre Layout
					noOfRows++;
				   theatreSeatingService.constructTheatreLayout(noOfRows, rows, thatreRow);
				}else {
				 //construct ticket requests after blank line in input file
				   theatreSeatingService.constructTicketRequests(ticketRequests,thatreRow);
			   }
			}
			theatreLayout.setRows(rows);
			int theatreSeatingCapacity = rows.stream().mapToInt(obj -> obj.getRowSeatingCapacity()).sum();
			theatreLayout.setMaxSeatingCapacity(theatreSeatingCapacity);
			System.out.println("No Of Rows in Theatre = "+rows.size());
			System.out.println("No Of Ticket Requests = "+ticketRequests.size());
			System.out.println("Total Seating Capacity : "+theatreSeatingCapacity);
			System.out.println("--------------------------------------------");
			theatreSeatingService.processTicketRequests(theatreLayout, ticketRequests);
			dataInputStream.close();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * This method helps to write the system generated outputs 
	 * on to a file named output.txt
	 */
	private static void writeOutPutToFile() {
        PrintStream output = null;
		try {
			output = new PrintStream(new File("output.txt"));
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println(fileNotFoundException.getMessage());
		}
 
        // Assign o to output stream
        System.setOut(output);
        System.setErr(output);
	}

	public static void main(String[] args) {
		readTheatreLayout(args[0]);
	}
}
