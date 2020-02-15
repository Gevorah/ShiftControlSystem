package model;

import java.util.*;

import customExceptions.*;

public class ShiftControl {

	private ArrayList<User> users;
	private ArrayList<Shift> shifts;
	
	public ShiftControl(){
		users = new ArrayList<>();
		shifts = new ArrayList<>();
		}
	
	public void addUser(int docType, String id, String names, String lastName, String phone, String address) {
		String dt = selectDT(docType);
		users.add(new User(dt, id, names, lastName, phone, address));
	}
	
	public String selectDT(int docType) {
		String dt = null;
		if(docType==1) {
			dt = User.CC;
		}else if(docType==2){
			dt = User.TI;
		}else if(docType==3){
			dt = User.RC;
		}else if(docType==4){
			dt = User.PASSPORT;
		}else if(docType==5){
			dt = User.CE;
		}
		return dt;
	}
	
	public String registerShift(String id) throws AlreadyHasShiftException {
		User temp = selectUser(id);
		Shift sft = shifts.get(shifts.size()-1);
		if(temp!=null) {
			if((selectUserShift(id)==null)||
			(selectUserShift(id)!=null&&!selectUserShift(id).getStatus().equals(Shift.NO_ATTENDED))) {
				sft.setUser(temp);
				addShift();
			}else {
				throw new AlreadyHasShiftException(id,selectUserShift(id).toString());
			}
		}else {
			throw new NullPointerException("The User with Id "+id+", doesn't exist.");
		}
		return String.format("The Shift %s has been asigned to %s",sft.getCode(),temp.toString());
	}
	
	public User selectUser(String id) {
		User temp = null;
		if(!users.isEmpty()) {
			for(int i=0;i<users.size()&&temp==null;i++) {
				temp = users.get(i).getId().equalsIgnoreCase(id)?users.get(i):null;
			}
		}else {
			throw new NullPointerException("The list of users is empty.");
		}
		return temp;
	} 
	
	public void checkUser(String id) throws ExistException {
		if(!users.isEmpty()) {
			for(int i=0;i<users.size();i++) {
				if(users.get(i).getId().equals(id)) {
					throw new ExistException(id);
				}
			}	
		}
	}
	
	public void addShift() {
		String code;
		if(shifts.size()==2600) {
			shifts.clear();
		}
		if(shifts.isEmpty()) {
			code = "A00";
		}else {
			code = shifts.get(shifts.size()-1).getCode();
			char first = code.charAt(0);
			int last = Integer.parseInt(code.substring(1));
			if(last==99) {
				first = first=='Z'?'A':(char)(first+1);
				last = 00;
			}else {
				last++;
			}
			code = String.format("%s%02d",first,last);
		}
		shifts.add(new Shift(code));
	}
	
	public void serveShift(int status) {
		Shift temp = selectToServeShift();
		if(status==1)temp.setStatus(Shift.ATTENDED);
		if(status==2)temp.setStatus(Shift.NO_USER);
	}
	
	public Shift selectToServeShift() {
		Shift toServe = null;
		for(int i=0;i<shifts.size()&&toServe==null;i++) {
			if(shifts.get(i).getUser()!=null&&shifts.get(i).getStatus().equals(Shift.NO_ATTENDED)) {
				toServe = shifts.get(i);
			}
		}
		if(toServe==null)throw new NullPointerException("No shifts to serve.");
		return toServe;
	}

	public Shift selectUserShift(String input) {
		Shift temp = null;
		for(int i=0;i<shifts.size()&&temp==null;i++) {
			if(shifts.get(i).getUser()!=null) {
				if(shifts.get(i).getUser().getId().equalsIgnoreCase(input)) {
					temp = shifts.get(i);
				}
			}
		}
		return temp;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public ArrayList<Shift> getShifts() {
		return shifts;
	}
	
}
