package model;

import java.util.*;

import customExceptions.*;

/**
*	This class allows create users and shifts.
*	@author Jhon Ijaji.
*	@version 1.0
*	@since 1.0
*/
public class ShiftControl {

	private ArrayList<User> users;
	private ArrayList<Shift> shifts;
	
	/**
	 * This method allows create a users list and shifts list.
	 */
	public ShiftControl(){
		users = new ArrayList<>();
		shifts = new ArrayList<>();
	}
	
	/**
	* This method allows add an user in the list.<br>
	* <b>post:</b> The text has been changed.<br>
    * @param docType The document type as an integer for select the indicate type.
    * @param id The user document number.
    * @param names The user names.
    * @param lastName The user last names.
    * @param phone The user phone.
    * @param address The user address.
	*/
	public void addUser(int docType, String id, String names, String lastName, String phone, String address) {
		String dt = selectDT(docType);
		users.add(new User(dt, id, names, lastName, phone, address));
	}
	
	/**
	 * This method allows select the indicate document type.
	 * @param docType The document type to select.
	 * @return A text with the document type.
	 */
	public String selectDT(int docType) {
		String dt = null;
		if(docType==1) {
			dt = User.CC;
		}else if(docType==2){
			dt = User.TI;
		}else if(docType==3){
			dt = User.RC;
		}else if(docType==4){
			dt = User.PP;
		}else if(docType==5){
			dt = User.CE;
		}
		return dt;
	}
	
	/**
	 * This method allows assign an user to one shift.
	 * @param id The user document number.
	 * @return A report of the assigned shift.
	 * @throws AlreadyHasShiftException If the user has an active shift throws this exception.
	 * @throws NullPointerException If the user object is null throws this exception.
	 */
	public String registerShift(String id) throws AlreadyHasShiftException {
		User temp = selectUser(id);
		Shift sft = shifts.get(shifts.size()-1);
		if((checkUserShift(id)==null)) {
			sft.setUser(temp);
			addShift();
		}else {
			throw new AlreadyHasShiftException(id,checkUserShift(id).toString());
		}
		return String.format("The Shift %s has been asigned to %s %s",sft.getCode(),temp.getNames(),temp.getLastNames());
	}
	
	/**
	 * This method allows search an user with the document number.
	 * @param id The user document number.
	 * @return The user searched.
	 * @throws NullPointerException If the list of users is empty throws this exception.
	 */
	public User selectUser(String id) {
		User temp = null;
		if(!users.isEmpty()) {
			for(int i=0;i<users.size()&&temp==null;i++) {
				temp = users.get(i).getId().equalsIgnoreCase(id)?users.get(i):null;
			}
		}else {
			throw new NullPointerException("The list of users is empty.");
		}
		if(temp==null) {
			throw new NullPointerException("The User with Id "+id+", doesn't exist.");
		}
		return temp;
	} 
	
	/**
	 * This method allows check if an user already exist in the list.
	 * @param id The user document number.
	 * @throws ExistException If the user is found in the list throws this exception.
	 */
	public void checkUser(String id) throws ExistException {
		if(!users.isEmpty()) {
			for(int i=0;i<users.size();i++) {
				if(users.get(i).getId().equals(id)) {
					throw new ExistException(id);
				}
			}	
		}
	}
	
	/**
	 * This method allows create a shift with the indicate code.
	 */
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
	
	/**
	 * This method allows attend a shift.
	 * @param status The status to select.
	 */
	public String attendShift(int status) {
		Shift temp = selectToAttendShift();
		if(status==1)temp.setStatus(Shift.ATTENDED);
		if(status==2)temp.setStatus(Shift.NO_USER);
		return String.format("The shift %s has been attended. [%s]",temp.getCode(),temp.getStatus());
	}
	
	/**
	 * This method allows select the next shift to attend.
	 * @return The next shift to attend.
	 */
	public Shift selectToAttendShift() {
		Shift toAttend = null;
		for(int i=0;i<shifts.size()&&toAttend==null;i++) {
			if(shifts.get(i).getUser()!=null&&shifts.get(i).getStatus().equals(Shift.NOT_ATTENDED)) {
				toAttend = shifts.get(i);
			}
		}
		if(toAttend==null)throw new NullPointerException("No more shifts to attend.");
		return toAttend;
	}

	/**
	 * This method allows search the user shift
	 * @param id The user document number.
	 * @return The shift with user assigned and not attended.
	 */
	public Shift checkUserShift(String id) {
		Shift temp = null;
		for(int i=0;i<shifts.size()&&temp==null;i++) {
			if(shifts.get(i).getUser()!=null) {
				if(shifts.get(i).getUser().getId().equalsIgnoreCase(id)
				&&shifts.get(i).getStatus().equals(Shift.NOT_ATTENDED)) {
					temp = shifts.get(i);
				}
			}
		}
		return temp;
	}
	
	/**
	 * This method allows get the users list.
	 * @return The users list.
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * This method allows get the shifts list.
	 * @return The shift list.
	 */
	public ArrayList<Shift> getShifts() {
		return shifts;
	}
	
}
