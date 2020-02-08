package model;

import java.util.*;

public class User {

	private String documentType;
	private String id;
	private String names;
	private String lastName;
	private String phone;
	private String address;
	private ArrayList<Shift> shifts;
	
	public User(String documentType, String id, String names, String lastName, String phone, String address) {
		this.documentType = documentType;
		this.id = id;
		this.names = names;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		shifts = new ArrayList<>();
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
	
	public ArrayList<Shift> getShifts() {
		return shifts;
	}
	
}
