package ui;

import java.util.*;
import model.ShiftControl;

public class Main{
	
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
		do {
			System.out.println(main.menu());
			int s = Integer.parseInt(main.reader.nextLine());
			switch(s) {
				case 1:
					System.out.printf("%n%s%n%s%s%s%s%s","Document Type: ","","","","","");
					int dt = Integer.parseInt(main.reader.nextLine());
					System.out.printf("%nDocument Number: ");
					String id = main.reader.nextLine();
					System.out.printf("%nNames: ");
					String names = main.reader.nextLine();
					System.out.printf("%nLast Name: ");
					String lns = main.reader.nextLine();
					System.out.printf("%nPhone: ");
					String phone = main.reader.nextLine();
					System.out.printf("%nAddress: ");
					String address = main.reader.nextLine();
					main.control.addUser(dt,id,names,lns,phone,address);
					break;
				case 2:
					System.out.printf("%nDocument Number: ");
					String rs = main.reader.nextLine();
					main.control.registerShift(rs);
					break;
				case 3:
					try {
						System.out.printf("%n%s%30s%n%30s",main.control.selectShift().getCode(),"1.Attend","2.No user");
					}catch(NullPointerException e) {
						System.out.printf("%n%s","Error");
					}					
					break;
				case 4:
					System.out.printf("%nGG.");
					end = true;
					break;
			}
		}while(end==false);
		main.reader.close();
	}
	
	public String menu() {
		return String.format("%n%s%n%s%n%s%n%s","1.Add an user","2.Register a shift","3.Serve a shift","4.Exit");
	}
}