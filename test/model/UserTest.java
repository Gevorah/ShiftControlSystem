package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void userTest() {
		User usr = new User(User.CC,"123","Adolf","G.L.","3693","agl");
		assertEquals(User.CC,usr.getDocumentType(),"F 1");
		assertEquals("123",usr.getId(),"F 2");
		assertEquals("Adolf",usr.getNames(),"F 3");
		assertEquals("G.L.",usr.getLastNames(),"F 4");
		assertEquals("3693",usr.getPhone(),"F 5");
		assertEquals("agl",usr.getAddress(),"F 6");
	}

}
