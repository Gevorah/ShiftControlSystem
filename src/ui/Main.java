package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import customExceptions.*;
import model.ShiftControl;

/**
*	This class allows control.
*	@author Jhon Ijaji.
*	@version 2.0
*	@since 1.0
*/
public class Main {


	private static final String FILE = "data"+File.separator+"control.txt";
	private Scanner reader;
	private ShiftControl control;

	/*
	 * This method allows create a scanner.
	 */
	public Main() {
		reader = new Scanner(System.in);
	}

	/**
	 * This method allows initialize objects.
	 */
	public void init() {
		control = new ShiftControl();
		control.addShift();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.init();
		boolean end = false;
		boolean moreUsers = true;
		boolean moreShifts = true;
		boolean attendMore = true;
		boolean check = false;
		do {
			System.out.println(main.menu());
			int s = Integer.parseInt(main.reader.nextLine());
			switch (s) {
			case 1:
				do {
					moreUsers = true;
					check = false;
					int docType = -1;
					String id = "";
					String names = "";
					String lastNames = "";
					do {
						try {
							System.out.printf("%n%s%n(%s  %s  %s  %s  %s) ", "Document Type: ", 
							"1.CC", "2.TI", "3.RC", "4.PASSPORT", "5.EC");
							docType = Integer.parseInt(main.reader.nextLine());
							if (docType > 0 && docType < 6) {
								check = true;
							} else {
								System.err.printf("Incorrect selection.");
							}
						} catch (InputMismatchException e) {
							System.err.printf(e.getMessage());
						}
					} while (check==false);
					check = false;
					do {
						try {
							System.out.printf("%nDocument Number: ");
							id = main.reader.nextLine();
							main.control.checkUser(id);
							check = main.check(id);
						} catch (ExistException e) {
							System.err.printf(e.getMessage());
						}catch(DataException e) {
							System.err.printf(e.getMessage());
						}
					} while (check==false);
					check = false;
					do {
						try {
							System.out.printf("%nNames: ");
							names = main.reader.nextLine();
							check = main.check(names);
						}catch(DataException e) {
							System.err.printf(e.getMessage());
						}
					} while (check==false);
					check = false;
					do {
						try {
							System.out.printf("%nLast Names: ");
							lastNames = main.reader.nextLine();
							check = main.check(lastNames);
						} catch (DataException e) {
							System.err.printf(e.getMessage());
						}
					} while (check==false);
					System.out.printf("%nPhone: ");
					String phone = main.reader.nextLine();
					System.out.printf("%nAddress: ");
					String address = main.reader.nextLine();
					main.control.addUser(docType, id, names, lastNames, phone, address);
					check = false;
					do {
						try {
							System.out.printf("%n%s%s", "1.Add another User. ", " 2.Back to the Menu.");
							int condition = Integer.parseInt(main.reader.nextLine());
							if(condition==1) {
								check = true;
							}else if(condition==2) {
								moreUsers = false;
								check = true;
							}else {
								System.err.printf("Incorrect selection.");
							}
						}catch(InputMismatchException e) {
							System.err.printf(e.getMessage());
						}
					}while(check==false);
				} while (moreUsers==true);
				break;
			case 2:
				try {
					do {
						String rs = "";
						moreShifts = true;
						check = false;	
						System.out.printf("%nDocument Number: ");
						rs = main.reader.nextLine();
						System.out.printf("%n%s%n%s",main.control.selectUser(rs).toString(),"1.Assign shift.");
						do {
							try {
								int condition = Integer.parseInt(main.reader.nextLine());
								if(condition==1) {
									System.out.printf("%n%s",main.control.registerShift(rs));
									check = true;
								}else {
									System.out.printf("Incorrect selection.");
								}
							}catch(InputMismatchException e) {
								System.err.printf(e.getMessage());
							}
						}while(check==false);
						check = false;
						do {
							try {
								System.out.printf("%n%s%s", "1.Register another Shift. ", " 2.Back to the menu.");
								int condition = Integer.parseInt(main.reader.nextLine());
								if(condition==1) {
									check = true;
								}else if(condition==2) {
									moreShifts = false;
									check = true;
								}else {
									System.out.printf("Incorrect selection.");
								}
							}catch(InputMismatchException e) {
								System.err.printf(e.getMessage());
							}
						}while(check==false);
					}while(moreShifts==true);
				}catch(NullPointerException e) {
					System.err.printf(e.getMessage());
				} catch (AlreadyHasShiftException e) {
					System.err.printf(e.getMessage());
				}
				break;
			case 3:
					try {
						do {
							attendMore = true;
							System.out.printf("%n%s%n[%s]%n%s   %s ", "Actual Shift:",
							main.control.selectToAttendShift().getCode(), "1.Attend", "2.No user");
							int atnd = Integer.parseInt(main.reader.nextLine());
							if(atnd==1||atnd==2) {
								System.out.printf(main.control.attendShift(atnd));
							}else {
								System.err.printf("Incorrect selection.");
							}
						}while(attendMore==true);
					} catch (NullPointerException e) {
						System.err.printf("%n%s",e.getMessage());
					}
				break;
			case 4:
				System.out.printf("%nGG.");
				end = true;
				break;
			}
		} while (end == false);
		main.reader.close();
	}

	/**
	 * This method allows show the menu options.
	 * @return The menu options.
	 */
	public String menu() {
		return String.format("%n%s%n%s%n%s%n%s", "1.Add an user", "2.Register a shift", "3.Serve a shift", "4.Exit");
	}

	/**
	 * This method allows check a text.
	 * @param input The text to check.
	 * @return true if the text have data.
	 * @throws DataException If the text is empty throws this exception.
	 */
	public boolean check(String input) throws DataException {
		boolean check = false;
		String toTrim = input.trim();
		if(toTrim.trim().contentEquals("")){
			throw new DataException();
		}else {
			check = true;
		}
		return check;
	}
	
	/**
	 * This method allows load the data.
	 * @throws FileNotFoundException  If the file does not exist.
	 * @throws IOException  If an I/O error occurs while reading stream header.
	 * @throws ClassNotFoundException If class of a serialized object cannot be found.
	 */
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE));
		control = (ShiftControl) ois.readObject();
		ois.close();
	}
	
	/**
	 * This method allows save the data.
	 * @throws FileNotFoundException If the file does not exist
	 * @throws IOException  if an I/O error occurs while reading stream header.
	 */
	public void save() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE,true));
		oos.writeObject(control);
		oos.close();
	}
}