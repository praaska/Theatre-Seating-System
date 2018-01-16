package theatre.seating.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import theatre.seating.model.Person;
import theatre.seating.model.Row;
import theatre.seating.model.Section;
import theatre.seating.model.Theatre;
import theatre.seating.model.Ticket;
import theatre.seating.model.TicketRequest;
import theatre.seating.service.TheatreSeatingService;

/**
 * This Service has all the methods required for Theatre Seating System
 * @author Prashanth Pulivendula
 */
public class TheatreSeatingServiceImpl implements TheatreSeatingService{

	private static final String SECTION = "S";
	private static final String ROW = "R";
	
	/**
	 * This Service Method is used to construct the theatre layout
	 * @param noOfRows Integer
	 * @param rows List<Row>
	 * @param theatreRow List<String>
	 * @return
	 */
	public void constructTheatreLayout(final Integer noOfRows,List<Row> rows, final List<String> thatreRow) {
		int noOfSections = 0;
		List<Section> sections = new ArrayList<>();
		for(String theatreSection : thatreRow) {
				noOfSections++;
				int sectionSeatingCapcity = Integer.valueOf(theatreSection);
				Section section = new Section(SECTION+noOfSections,sectionSeatingCapcity);
				sections.add(section);
		}
		int rowSeatingCapacity = sections.stream().mapToInt(obj -> obj.getSectionSeatingCapcity()).sum();
		Row row = new Row(ROW+noOfRows,rowSeatingCapacity, sections);
		rows.add(row);
	}
	
	/**
	 * This Service Method is used to construct the Ticket Requests
	 * @param rows List<String>
	 * @return List<TicketRequest>
	 */
	public List<TicketRequest> constructTicketRequests(List<TicketRequest> ticketRequests, final List<String> requests) {
        TicketRequest ticketRequest = new TicketRequest();
		Person person = new Person();
		person.setName(requests.get(0));
		Ticket ticket = new Ticket();
		ticket.setCount(Integer.valueOf(requests.get(1)));
		ticketRequest.setPerson(person);
		ticketRequest.setTicket(ticket);
		ticketRequests.add(ticketRequest);
		return ticketRequests;
	}
	
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
	public void processTicketRequests(final Theatre theatreLayout, final List<TicketRequest> ticketRequests) {
		int maxSeatingCapacity = theatreLayout.getMaxSeatingCapacity();
		Map<String,Integer> rowSectionMap = parseTheatreLayout(theatreLayout);
		allocateSeats(ticketRequests, maxSeatingCapacity, rowSectionMap);	
	}
	

	/**
	 * This method iterates over input ticket requests and calls
	 * seat book method to allocate seats
	 * @param theatreLayout  Theatre
	 * @return 
	 */
	private void allocateSeats(final List<TicketRequest> ticketRequests, int maxSeatingCapacity,Map<String, Integer> rowSectionMap) {
		int remainingCapacity = maxSeatingCapacity;
		for (TicketRequest ticketRequest : ticketRequests) {
			int orderValue =  ticketRequest.getTicket().getCount();
			String orderedByPerson = ticketRequest.getPerson().getName();
		    System.out.println("Order By = " + orderedByPerson + ", for tickets = " + orderValue);
		    remainingCapacity = bookSeats(rowSectionMap,ticketRequest, maxSeatingCapacity);
		    maxSeatingCapacity = remainingCapacity;
		}
	}

	/**
	 * This method parses the theatre layout and prepares row section map for
	 * easier computation.
	 * @param theatreLayout Theatre
	 * @return rowSectionMap
	 */
	private Map<String,Integer> parseTheatreLayout(Theatre theatreLayout) {
        final List<Row> rows =  theatreLayout.getRows();
        Map<String,Integer> rowSectionMap = new LinkedHashMap<>();
		for(Row row : rows) {
			for(Section section : row.getSections()) {
				rowSectionMap.put(row.getRowNumber()+section.getSectionNumber(), section.getSectionSeatingCapcity());
			}
		}
		return rowSectionMap;
	}
	
	/**
	 * This method is used to book the seats in the theatre for each request
	 * @param rowSectionMap Map<String, Integer>
	 * @param TicketRequest ticketRequest
	 * @param remainingCapacity int
	 * @return rowSectionMap
	 */
	private  int bookSeats(Map<String, Integer> rowSectionMap, TicketRequest ticketRequest, int remainingCapacity) {
		
		    int orderValue = ticketRequest.getTicket().getCount();
		    String orderedByPerson = ticketRequest.getPerson().getName();
			for(Map.Entry<String, Integer> rowSectionEntry : rowSectionMap.entrySet()) {
				String rowSection = rowSectionEntry.getKey();
				int sectionCapcity = rowSectionEntry.getValue();
			    if(orderValue < remainingCapacity) {
			    	if(orderValue <= sectionCapcity) {
			    		System.out.println(orderedByPerson+" Allocated in " + rowSection);
			    		rowSectionMap.put(rowSection, (sectionCapcity -orderValue));
			    		remainingCapacity = remainingCapacity - orderValue;
			    		break;
			    	}else {
			    		int currentSectionCapacity = Collections.max(rowSectionMap.values());
                        if(orderValue > currentSectionCapacity) {
			    			System.out.println(orderedByPerson+" Call to split party.");
				    		break;
                        }
			    	}
			    }else {
			    	System.out.println(orderedByPerson+" Sorry, we can't handle your party.");
			    	break;
			    }
			}
			System.out.println("Remaining Capacity : "+remainingCapacity);
			System.out.println("--------------------------------------------");
		return remainingCapacity;
	}
}
