package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void userTest() {
		User u = new User(User.CC,"123","Adolf","G.L.","3693","agl");
		assertEquals(User.CC,u.getDocumentType(),"F");
		assertEquals("123",u.getId(),"F");
		assertEquals("Adolf",u.getNames(),"F");
		assertEquals("G.L.",u.getLastNames(),"F");
		assertEquals("3693",u.getPhone(),"F");
		assertEquals("agl",u.getAddress(),"F");
	}

}
