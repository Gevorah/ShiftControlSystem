package ui;

import java.util.*;
import model.ShiftControl;

public class Main {

	private Scanner reader;
	private ShiftControl control;

	public Main() {
		reader = new Scanner(System.in);
	}

	public void init() {
		control = new ShiftControl();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.init();
		boolean end = false;
		boolean moreUsers = false;
		boolean moreShifts = false;
		boolean check = false;
		do {
			System.out.println(main.menu());
			int s = Integer.parseInt(main.reader.nextLine());
			switch (s) {
			case 1:
				do {
					moreUsers = false;
					check = false;
					int docType = -1;
					String id = null;
					String names = null;
					String lastNames = null;
					do {
						try {
							System.out.printf("%n%s%n%s%s%s%s%s", "Document Type: ", "", "", "", "", "");
							docType = Integer.parseInt(main.reader.nextLine());
							if (docType > 0 && docType < 6) {
								check = true;
							} else {
								System.out.printf("Incorrect selection.");
							}
						} catch (InputMismatchException e) {
							System.err.printf("%n%s",e.getMessage());
						}
					} while (check);
					check = false;
					do {
						try {
							System.out.printf("%nDocument Number: ");
							id = main.reader.nextLine();
							if(main.control.checkUser(id)==null) {
								check = true;
								System.out.printf("The Document Number Already Exist.");
							}else if(lastNames.trim().contentEquals("")){
								System.out.printf("Error.");
							}
						} catch (Exception e) {
							System.err.printf("%n%s",e.getMessage());
						}
					} while (check);
					check = false;
					do {
						System.out.printf("%nNames: ");
						names = main.reader.nextLine();
						if(names.trim().contentEquals("")) {
							check = true;
						}else {
							System.out.printf("Error.");
						}
					} while (check);
					check = false;
					do {
						System.out.printf("%nLast Name: ");
						lastNames = main.reader.nextLine();
						if(lastNames.trim().contentEquals("")) {
							check = true;
						}else {
							System.out.printf("Error.");
						}
					} while (check);
					System.out.printf("%nPhone: ");
					String phone = main.reader.nextLine();
					System.out.printf("%nAddress: ");
					String address = main.reader.nextLine();
					main.control.addUser(docType, id, names, lastNames, phone, address);
					System.out.printf("%n%s%s", "1.Add another user. ", " 2.Back to the menu.");
					int condition = Integer.parseInt(main.reader.nextLine());
					moreUsers = condition == 1 ? true : false;
				} while (moreUsers);
				break;
			case 2:
				do {
					moreShifts = false;
					try {
						System.out.printf("%nDocument Number: ");
						String rs = main.reader.nextLine();
						main.control.registerShift(rs);
					}catch(NullPointerException e) {
						System.err.printf("%n%s",e.getMessage());
					}
				}while(moreShifts);
				break;
			case 3:
				try {
					System.out.printf("%n%s%30s%n%30s", main.control.selectShift().getCode(), "1.Attend", "2.No user");
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

	public String menu() {
		return String.format("%n%s%n%s%n%s%n%s", "1.Add an user", "2.Register a shift", "3.Serve a shift", "4.Exit");
	}

	public boolean check(String input) {
		boolean check = input != null && input.contentEquals("") ? true : false;
		return check;
	}
}