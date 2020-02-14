package model;

public class User {

	public final static String CC = "CC";
	public final static String IC = "IC";
	public final static String CR = "CR";
	public final static String PASSPORT = "Passport";
	public final static String F = "F";
	
	private String documentType;
	private String id;
	private String names;
	private String lastNames;
	private String phone;
	private String address;
	
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
		return String.format("User [%s - %s]",documentType,id);
	}

	public String getDocumentType() {
		return documentType;
	}

	public String getId() {
		return id;
	}

	public String getNames() {
		return names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}
	
}
