package theatre.seating.model;

/**
 * This object holds the properties of the Section in each row of the Movie Theatre
 * @author Prashanth Pulivendula
 *
 */
public class Section {

	private String sectionNumber;
	private Integer sectionSeatingCapcity;
	private Integer maxSectionSeatingCapacity;
	
	public Section(String sectionNumber, Integer sectionSeatingCapcity) {
		super();
		this.sectionNumber = sectionNumber;
		this.sectionSeatingCapcity = sectionSeatingCapcity;
	}
	
	/**
	 * @return the sectionNumber
	 */
	public String getSectionNumber() {
		return sectionNumber;
	}
	/**
	 * @param sectionNumber the sectionNumber to set
	 */
	public void setSectionNumber(String sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	/**
	 * @return the sectionSeatingCapcity
	 */
	public Integer getSectionSeatingCapcity() {
		return sectionSeatingCapcity;
	}
	/**
	 * @param sectionSeatingCapcity the sectionSeatingCapcity to set
	 */
	public void setSectionSeatingCapcity(Integer sectionSeatingCapcity) {
		this.sectionSeatingCapcity = sectionSeatingCapcity;
	}
	/**
	 * @return the maxSectionSeatingCapacity
	 */
	public Integer getMaxSectionSeatingCapacity() {
		return maxSectionSeatingCapacity;
	}
	/**
	 * @param maxSectionSeatingCapacity the maxSectionSeatingCapacity to set
	 */
	public void setMaxSectionSeatingCapacity(Integer maxSectionSeatingCapacity) {
		this.maxSectionSeatingCapacity = maxSectionSeatingCapacity;
	}
}
