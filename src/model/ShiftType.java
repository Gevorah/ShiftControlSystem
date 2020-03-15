package model;

/**
*	This class allows create shifts types.
*	@author Jhon Ijaji.
*	@version 2.0
*	@since 1.0
*/
public class ShiftType {

	private String name;
	private float time;
	private String creation;
	
	/**
	 * This method allows create a shift type.
	 * @param name The name.
	 * @param time The time.
	 */
	public ShiftType(String name, float time, String creation) {
		this.name = name;
		this.time = time;
		this.creation = creation;
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
	public float getTime() {
		return time;
	}

	/**
	 * This method allows get the creation date-time.
	 * @return The creation date-time.
	 */
	public String getCreation() {
		return creation;
	}
	
	
	
}
