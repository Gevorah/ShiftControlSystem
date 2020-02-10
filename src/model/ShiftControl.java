package model;

import java.util.*;

public class ShiftControl {

	private ArrayList<User> users;
	private ArrayList<Shift> shifts;
	
	public ShiftControl(){
		users = new ArrayList<>();
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
			dt = User.IC;
		}else if(docType==3){
			dt = User.CR;
		}else if(docType==4){
			dt = User.PASSPORT;
		}else if(docType==5){
			dt = User.F;
		}
		return dt;
	}
	
	public void registerShift(String id) {
		boolean flag = false;
		String code = null;
		for(int i=0;i<users.size()&&flag==false;i++) {
			if(users.get(i).getId().equals(id)) {
				code = selectCode();
				shifts.add(new Shift(code,users.get(i)));
				flag = true;
			}
		}
	}
	
	public String selectCode() {
		String code = null;
		if(shifts.isEmpty()) {
			code = "A00";
		}else {
			code = shifts.get(shifts.size()-1).getCode();
			char first = code.charAt(0);
			int last = Integer.parseInt(code.substring(1));
			if(last==99) {
				first = first=='Z'?'A':(char)(first+1);
				last = 00;
			}
			code = String.format("%s%02d",first,last);
		}
		return code;
	}
	
	public void serveShift(int status) {
		Shift temp = selectShift();
		if(status==1)temp.setStatus(Shift.ATTENDED);
		if(status==2)temp.setStatus(Shift.NO_USER);
	}
	
	public Shift selectShift() {
		Shift toAttend = null;
		boolean flag = false;
		for(int i=0;i<shifts.size()&&flag==false;i++) {
			if(shifts.get(i).getStatus().equals(Shift.NO_ATTENDED)) {
				toAttend = shifts.get(i);
				flag = true;
			}
		}
		return toAttend;
	}
}
