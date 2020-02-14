package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import customExceptions.AlreadyHasShiftException;

class ShiftControlTest {

	private ShiftControl sc = new ShiftControl();
	
	public void setup1() {
		sc.addUser(1,"123","Adolf","G.L.","","");
	}
	
	public void setup2() {
		sc.addUser(2,"369","Ric","M.L.","","");
	}
	
	@Test
	void addUserTest() {
		try {
			setup1();
			sc.addUser(1,"321","Leon","M.C.","3693","agl");
			assertEquals("Leon",sc.checkUser("321").getNames(),"F 1");
			assertEquals("M.C.",sc.checkUser("321").getLastNames(),"F 2");
			assertEquals("3693",sc.checkUser("321").getPhone(),"F 3");
			assertEquals("agl",sc.checkUser("321").getAddress(),"F 4");
			sc.addUser(1,"123","Re","Chicken","","");
			sc.getUsers().clear();
			sc.addUser(3,"123","Marc","M.Z.","","");
		}catch(NullPointerException e) {
			System.err.println("F");
		}	
	}

	@Test
	void checkTest() {
		try {
			setup1();
			assertEquals("Adolf",sc.checkUser("123").getNames(),"F 1");
		}catch(NullPointerException e) {
			System.err.println("F");
		}
	}
	
	@Test
	void registerShiftTest(){
		try {
			setup1();
			sc.registerShift("123");
			sc.registerShift("123");
		}catch(AlreadyHasShiftException e) {
			System.err.println("Exist.");
		}catch(NullPointerException e) {
			System.err.println("Null.");
		}
	}
	
	@Test
	void selectCodeTest() {
		
	}
}
