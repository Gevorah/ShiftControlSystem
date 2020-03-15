package model;

/**
*	This class allows create shifts.
*	@author Jhon Ijaji.
*	@version 2.0
*	@since 1.0
*/
public class Shift {
	
	public final static String ATTENDED = "Attended";
	public final static String NOT_ATTENDED = "Not Attended";
	public final static String NO_USER = "No user";
	
	private String code;
	private User user;
	private String status;
	private ShiftType shType;
	
	/**
	 * This method allows create a shift.
	 * @param code The shift code.
	 */
	public Shift(String code) {
		this.code = code;
		this.status = NOT_ATTENDED;
		this.user = null;
		this.shType = null;
	}
	
	@Override
	public String toString() {
		return String.format("Shift [%s: %s]",code,status);
	}

	/**
	 * This method allows get the shift code.
	 * @return	The shift code.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * This method allows get the shift status
	 * @return The shift status.
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * This method allows get the shift user.
	 * @return The shift user.
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * This method allows set the shift user.
	 * @param user The user to set.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * This method allows set the shift status.
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * This method allows get the shift type.
	 * @return The shift type.
	 */
	public ShiftType getShType() {
		return shType;
	}

	/**
	 * This method allows set the shift type.
	 * @param shType The shift type to set
	 */
	public void setShType(ShiftType shType) {
		this.shType = shType;
	}
	
	
}
