package theatre.seating.model;

import java.util.List;

/**
 * This object holds the properties of the Row layout in a movie theatre
 * @author Prashanth Pulivendula
 *
 */
public class Row {

	private String rowNumber;
	private Integer rowSeatingCapacity;
	private List<Section> sections;
	
	public Row(String rowNumber, Integer rowSeatingCapacity, List<Section> sections) {
		super();
		this.rowNumber = rowNumber;
		this.sections = sections;
		this.rowSeatingCapacity = rowSeatingCapacity;
	}
	/**
	 * @return the rowNumber
	 */
	public String getRowNumber() {
		return rowNumber;
	}
	/**
	 * @param rowNumber the rowNumber to set
	 */
	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	/**
	 * @return the rowSeatingCapacity
	 */
	public Integer getRowSeatingCapacity() {
		return rowSeatingCapacity;
	}
	/**
	 * @param rowSeatingCapacity the rowSeatingCapacity to set
	 */
	public void setRowSeatingCapacity(Integer rowSeatingCapacity) {
		this.rowSeatingCapacity = rowSeatingCapacity;
	}
	/**
	 * @return the sections
	 */
	public List<Section> getSections() {
		return sections;
	}
	/**
	 * @param sections the sections to set
	 */
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
	
}
