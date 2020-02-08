package model;

import java.util.*;

public class ShiftControl {

	private ArrayList<User> users;
	
	public ShiftControl(){
		users = new ArrayList<>();
	}
	
	public void addUser(String documentType, String id, String names, String lastName, String phone, String address) {
		users.add(new User(documentType, id, names, lastName, phone, address));
	}
	
	public void registerShift(String id) {
		boolean flag = false;
		String code = "";
		for(int i=0;i<users.size()&&flag==false;i++) {
			if(users.get(i).getId().equals(id)) {
				users.get(i).getShifts().add(new Shift(code));
			}
		}
	}
}
