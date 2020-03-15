package model;

import java.util.*;
import java.io.*;
import customExceptions.AlreadyHasShiftException;
import customExceptions.ExistException;

/**
*	This class allows create users and shifts.
*	@author Jhon Ijaji.
*	@version 2.0
*	@since 1.0
*/
public class ShiftControl implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private static final String NAMES = "data"+File.separator+"Names.txt";
	private static final String LASTNAMES = "data"+File.separator+"LastNames.txt";
	private static final String ID = "data"+File.separator+"Id.txt";
	private ArrayList<User> users;
	private ArrayList<Shift> shifts;
	private DateTime date;
	
	/**
	 * This method allows create a users list and shifts list.
	 */
	public ShiftControl(){
		users = new ArrayList<>();
		shifts = new ArrayList<>();
		date = new DateTime();
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
	 * This method allows add users from files.
	 * @param quantity The quantity of users to add.
	 * @throws FileNotFoundException If the file does not exist.
	 * @throws IOException If an I/O error occurs.
	 */
	public void addUsers(int quantity) throws FileNotFoundException, IOException {
		BufferedReader brN = new BufferedReader(new FileReader(NAMES));
		BufferedReader brLN = new BufferedReader(new FileReader(LASTNAMES));
		BufferedReader brId = new BufferedReader(new FileReader(ID));
		int count = 0;
		do {
			String names = brN.readLine();
			String lstNames = brN.readLine();
			String id = brId.readLine();
			int rand = (int)(Math.random()*5);
			addUser(rand,id,names,lstNames,"","");
		}while(count++<quantity);
		brN.close();
		brLN.close();
		brId.close();
	}
	
	/**
	 * This method allows assign an user to one shift.
	 * @param id The user document number.
	 * @return A report of the assigned shift.
	 * @throws AlreadyHasShiftException If the user has an active shift throws this exception.
	 * @throws NullPointerException If the user object is null throws this exception.
	 */
	public String registerShift(String id) throws AlreadyHasShiftException {
		User temp = searchUserById(id);
		Shift sft = shifts.get(shifts.size()-1);
		if((checkUserShift(id)==null)) {
			sft.setUser(temp);
			float rnd = (float) (Math.random()*5);
			sft.setShType(new ShiftType("",rnd,String.format("%s",date.format(date.currentDateTime()))));
			addShift();
		}else {
			throw new AlreadyHasShiftException(id,checkUserShift(id).toString());
		}
		return String.format("The Shift %s has been asigned to %s %s",sft.getCode(),temp.getNames(),temp.getLastNames());
	}
	
	public void registerShifts(int days,int shiftPerDay) {
		
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
	 * This method allows attend shifts automatically.
	 * @return Some information about the attended shifts.
	 */
	public String attendShifts() {
		float count = 0;
		boolean flag = false;
		String show = "";
		for(int i=0;i<shifts.size()&&flag==false;i++) {
			if(shifts.get(i).getStatus().equals(Shift.NOT_ATTENDED)) {
				String date = shifts.get(i).getShType().getCreation();
				float duration = shifts.get(i).getShType().getTime();
				//Check if the date is before and plus to date the count.
				if(this.date.checkDate(this.date.plus(this.date.parse(date), count))) {
					int x = (int)(Math.random()*2);
					if(x==1) {
						shifts.get(i).setStatus(Shift.ATTENDED);
					}else {
						shifts.get(i).setStatus(Shift.NO_USER);
					}
					searchUserById(shifts.get(i).getUser().getId()).addShift(shifts.get(i));;
					show += "Attended shift: "+shifts.get(i)+
							". Date: "+this.date.plus(this.date.parse(date), count)+"\n";
					count += duration;
					count += 0.25;
				}else {
					flag = true;
				}
			}
		}
		if(show.equals("")){
			show = "No shifts attended.";
		}
		return show;
	}
	
	/**
	 * This method allows search an user by the document number.
	 * @param id The user document number.
	 * @return The user searched.
	 * @throws NullPointerException If the list of users is empty throws this exception.
	 */
	public User searchUserById(String id) {
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
	
	public int binarySearch(String id) { 
		int left = 0;
		int rigth = users.size()-1; 
		while (left<=rigth) { 
		    int mid = left+(rigth-left)/2;
		    if (users.get(mid).getId().compareTo(id)>0) 
		       	rigth = mid-1; 
		    if (users.get(mid).getId().compareTo(id)<0) 
		        left = mid+1; 
		    else {
		    	return mid;
		    }
		}
		return -1;
	}
	
	public void bubbleSortUsersById() {
		for(int i=users.size();i>0;i--) {
			for(int j=0;j<i-1;j++) {
				if(users.get(j).getId().compareTo(users.get(j+1).getId())>0) {
					User temp = users.get(j);
					users.set(j,users.get(j+1));
					users.set(j+1,temp);
				}
			}
		}
	}
	
	public void isertionSortUsersByName() {
		for (int i = 1; i<users.size(); ++i) { 
            User key = users.get(i); 
            int j = i-1; 
            while (j>=0&&users.get(j).getNames().compareTo(key.getNames())>0) { 
            	users.set(j+1,users.get(j--)); 
            } 
            users.set(j+1,key); 
        }
	}
	
	
	/**
	 * This method allows modify the date-time.
	 * @param year The year to set.
	 * @param month The month to set.
	 * @param day The day to set.
	 * @param hour The hour to set.
	 * @param min The minute to set.
	 * @return A message with information about the changes.
	 */
	public String modifyDateTime(int year,int month,int day,int hour,int min) {
		String show = "The given date-time is less than the current date-time.";
		if(!date.checkDate(year,month,day,hour,min)) {
			date.setDateTime(year, month, day, hour, min);
			show = "The date-time has been changed.";
			for(Shift tmp:shifts) {
				if(tmp.getStatus().equals(Shift.NOT_ATTENDED)) {
					int x = (int)(Math.random()*2);
					if(x==1) {
						tmp.setStatus(Shift.ATTENDED);
					}else {
						tmp.setStatus(Shift.NO_USER);
					}
				}
			}
		}
		return show;
	}
	
	public String reportShift(String code) {
		String show = "";
		return show;
	}
	
	public String reportUser(String id) {
		String show = "";
		User temp = searchUserById(id);
		if(!temp.getShifts().isEmpty()) {
			for(int i=0;i<temp.getShifts().size();i++) {
				show += temp.getShifts().get(i).getCode()+"-"+temp.getShifts().get(i).getStatus()+"\n";
			}
		}
		return show;
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

	/**
	 * This method allows get the date.
	 * @return The program date.
	 */
	public DateTime getDate() {
		return date;
	}
	
	
	
}
