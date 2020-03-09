package model;

/**
*	This class allows create users.
*	@author Jhon Ijaji.
*	@version 2.0
*	@since 1.0
*/
public class User {

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
	
}
