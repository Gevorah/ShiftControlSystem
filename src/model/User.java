package model;

import java.util.ArrayList;

/**
*	This class allows create users.
*	@author Jhon Ijaji.
*	@version 2.0
*	@since 1.0
*/
public class User implements Comparable<User>{

	public final static String CC = "CC";
	public final static String TI = "TI";
	public final static String RC = "RC";
	public final static String PP = "PP";
	public final static String CE = "CE";
	
	private String documentType;
	private String id;
	private String names;
	private String lastNames;
	private String phone;
	private String address;
	private int faults;
	private DateTime suspend;
	ArrayList<Shift> shifts;
	
	/**
	 * This method allows create an user.
	 * @param documentType The user document type.
	 * @param id The user document number.
	 * @param names The user names.
	 * @param lastNames The user last names.
	 * @param phone The user phone.
	 * @param address The user address.
	 */
	public User(String documentType, String id, String names, String lastNames, String phone, String address) {
		this.documentType = documentType;
		this.id = id;
		this.names = names;
		this.lastNames = lastNames;
		this.phone = phone;
		this.address = address;
		faults = 0;
		shifts = new ArrayList<Shift>();
	}

	public void addShift(Shift shift) {
		shifts.add(shift);
	}
	
	@Override
	public String toString() {
		return String.format("%-5s%-15s%s%n%-5s%s %-15s%s",
				"DT","Name","Phone",documentType,names,lastNames,phone);
	}

	/**
	 * This method allows get the user document type.
	 * @return The user document type.
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * This method allows get the user document number.
	 * @return The user document number.
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method allows get the user names.
	 * @return The user names.
	 */
	public String getNames() {
		return names;
	}

	/**
	 * This method allows get the user last names.
	 * @return The user last names.
	 */
	public String getLastNames() {
		return lastNames;
	}
	
	/**
	 * This method allows get the user phone.
	 * @return The user phone.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method allows get the user address.
	 * @return The user address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method allows get the strike.
	 * @return the strike
	 */
	public int getFaults() {
		return faults;
	}

	/**
	 * This method allows get the suspend
	 * @return the suspend
	 */
	public DateTime getSuspend() {
		return suspend;
	}

	/**
	 * @param suspend the suspend to set
	 */
	public void setSuspend(DateTime suspend) {
		this.suspend = suspend;
	}

	/**
	 * @param faults the faults to set
	 */
	public void setFaults(int faults) {
		this.faults = faults;
	}

	/**
	 * @return the shifts
	 */
	public ArrayList<Shift> getShifts() {
		return shifts;
	}

	/**
	 * @param shifts the shifts to set
	 */
	public void setShifts(ArrayList<Shift> shifts) {
		this.shifts = shifts;
	}

	@Override
	public int compareTo(User o) {
		if(id.compareTo(o.id)>0) {
			return 1;
		}else if(id.compareTo(o.id)<0) {
			return -1;
		}else {
			return 0;
		}
	}
	
}
