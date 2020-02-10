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
	private String lastName;
	private String phone;
	private String address;
	
	public User(String documentType, String id, String names, String lastName, String phone, String address) {
		this.documentType = documentType;
		this.id = id;
		this.names = names;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
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

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}
	
}
