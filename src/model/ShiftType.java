package model;

/**
*	This class allows create shifts types.
*	@author Jhon Ijaji.
*	@version 2.0
*	@since 1.0
*/
public class ShiftType {

	private String name;
	private String time;
	
	/**
	 * This method allows create a shift type.
	 * @param name The name.
	 * @param time The time.
	 */
	public ShiftType(String name, String time) {
		this.name = name;
		this.time = time;
	}

	/**
	 * This method allows get the name.
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method allows get the time.
	 * @return The time
	 */
	public String getTime() {
		return time;
	}
	
	
}
