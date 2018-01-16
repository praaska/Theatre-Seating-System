package theatre.seating.model;

import java.util.List;

/**
 * This object holds the properties of the Movie Theatre layout
 * @author Prashanth Pulivendula
 *
 */
public class Theatre {
	
	private List<Row> rows;
	private Integer maxSeatingCapacity;
	
	/**
	 * @return the rows
	 */
	public List<Row> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	/**
	 * @return the maxSeatingCapacity
	 */
	public Integer getMaxSeatingCapacity() {
		return maxSeatingCapacity;
	}
	/**
	 * @param maxSeatingCapacity the maxSeatingCapacity to set
	 */
	public void setMaxSeatingCapacity(Integer maxSeatingCapacity) {
		this.maxSeatingCapacity = maxSeatingCapacity;
	}
}
