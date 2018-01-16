package theatre.seating.model;

/**
 * This object holds the person details and tickets for each person
 * @author Prashanth Pulivendula
 *
 */
public class TicketRequest {

	private Person person;
	private Ticket ticket;
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	/**
	 * @return the ticket
	 */
	public Ticket getTicket() {
		return ticket;
	}
	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	
}
