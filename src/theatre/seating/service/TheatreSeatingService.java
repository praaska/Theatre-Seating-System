package theatre.seating.service;

import java.util.List;

import theatre.seating.model.Row;
import theatre.seating.model.Theatre;
import theatre.seating.model.TicketRequest;

/**
 * This Service has all the methods required for Theatre Seating System
 * @author Prashanth Pulivendula
 *
 */
public interface TheatreSeatingService {
	
	/**
	 * This Service Method is used to construct the theatre layout
	 * @param noOfRows Integer
	 * @param rows List<Row>
	 * @param theatreRow List<String>
	 * @return
	 */
	public void constructTheatreLayout(final Integer noOfRows,List<Row> rows, final List<String> row);
	
	/**
	 * This Service Method is used to construct the Ticket Requests
	 * @param rows List<String>
	 * @return List<TicketRequest>
	 */
	public List<TicketRequest> constructTicketRequests(List<TicketRequest> ticketRequests, final List<String> rows);
	
	/**
	 * This method is used to parse each row in Theatre Layout
	 * and allocate seats based on availability
	 * Rules followed when allocating the seat
	 *  1.	Fill as many orders as possible
	 *	2.	Put parties as close to the front as possible.
	 *	3.	If there are not enough seats available in the theater to handle a party, tell them "Sorry, we can't handle your party."
	 *	4.	Each party must sit in a single row in a single section.  If they won't fit, tell them "Call to split party".
	 * @param ticketRequests List<TicketRequest>
	 * @param theatreLayout  Theatre
	 * @return 
	 */
	public void processTicketRequests(final Theatre theatreLayout, final List<TicketRequest> ticketRequests);

}
